package com.yss.ams.product.information.modules.dataCopy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.support.modules.dataCopy.pojo.CopyData;
import com.yss.fast.right.support.right.pojo.UserPostData;
import com.yss.fast.right.support.right.service.IUserPostDataService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.SysFun;
import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.dataservice.IFunDataService;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.db.OraDbTool;
import com.yss.uco.dataintegration.support.dataservice.service.IBasicDefineDataService;
import com.yss.uco.dataintegration.support.trep.deploy.pojo.BasicDefine;

public class DataCopyDao extends GeneralDao {

	public DataCopyDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
	}
	
	public String getService() throws DataAccessException {
		String services = "";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = this.loadNewConnection();
			String sql = "SELECT DECODE(C_DATA_CODE,'planRelaPort',C_DATA_PARA,C_DATA_CODE) AS C_DATA_CODE,C_SERVICE_CODE FROM T_S_DATA_COPY WHERE C_DATA_CODE_P != '[root]'";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				services += rs.getString("C_DATA_CODE") + "=" + rs.getString("C_SERVICE_CODE") + "@@";
			}
		} catch (Exception e) {
			logger.error("查询失败："+e.getMessage(), e);
			throw new DataAccessException(e);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return services;
	}
	
	/**
	 * STORY #85993 产品参数复制从FAST迁移到产品管理组件
	 * 修改 queryByCondition，FAST表使用服务查询，查询所有表集合后，再进行分组，使用临时表
	 * @param paraMap in
	 * @param clazz out
	 * @return
	 */
	public List<BasePojo> queryByCondition(HashMap<String, Object> paraMap, Class<?> clazz) {
		HashMap<String, Object> paraMap1=new HashMap<String, Object>();
		paraMap1.put("dataClass", paraMap.get("dataClass"));
		List<BasePojo> pojoList=super.queryByCondition(paraMap1, clazz);		
		List<String> parentIds =new ArrayList<String>();
		boolean needGetRPT=false;
		for(int i=0;i<pojoList.size();i++){
			CopyData cp=(CopyData)pojoList.get(i);
			if(!parentIds.contains(cp.getC_DATA_CODE_P())){
				parentIds.add(cp.getC_DATA_CODE_P());			
			}
			if(null!=cp.getC_DATA_CODE() && cp.getC_DATA_CODE().equals("Expconfig")){
				needGetRPT=true;
			}
		}
		
		//用户岗位
		if(null!=paraMap.get("C_PORT_CODE_POST_SHOW")&&"true".equals(paraMap.get("C_PORT_CODE_POST_SHOW").toString())){
			String c_DATA_CODE="";
			if(null!=paraMap.get("C_PORT_CODE_USER"))
			{
				c_DATA_CODE=paraMap.get("C_PORT_CODE_USER").toString();
			}
			getUserAndPost(pojoList,parentIds,c_DATA_CODE);
		}
		//添加报表
		if(needGetRPT){
			getInterfacePort(pojoList,paraMap);
		}
		
		//功能菜单
		getFun(pojoList,parentIds);
		
		List<BasePojo> target=new ArrayList<BasePojo>();
		//最后进行查询
		getCopyDataTree(pojoList,target,parentIds);
		
		return target;
	}
	
	/**
	 * 先插入临时表，再从临时表分组查询
	 * STORY #85993 产品参数复制从FAST迁移到产品管理组件
	 * @param pojoList in
	 * @param target out
	 * @param parentIds in
	 * @return
	 */
	public void getCopyDataTree(List<BasePojo> pojoList,List<BasePojo> target,List<String> parentIds) {
		if(null==pojoList||pojoList.size()<=0){
			return;
		}
		boolean bTrans = true; // 代表是否开始了事务
		PreparedStatement pst = null;// 定义预处理对象
		PreparedStatement pst2 = null;// 定义预处理对象
		Connection conn = null; // 获取数据库连接		
		ResultSet rs = null; 
		try {
			//先插入临时表，再从临时表分组查询
			conn = loadNewConnection(); // 获取数据库连接
			conn.setAutoCommit(false);
			pst = conn.prepareStatement(((DataCopyBuilder) sqlbuilder).getInsertCopyRSql());
			for (int i=0;i<pojoList.size();i++) {
				CopyData cp=(CopyData)pojoList.get(i);
				pst.setString(1, cp.getC_DATA_NAME());
				pst.setString(2, cp.getC_DATA_CODE());
				pst.setString(3, cp.getC_DATA_CODE_P());
				pst.setString(4, cp.getC_DV_STATE());
				pst.setString(5, cp.getC_SERVICE_CODE());
				pst.setObject(6, null!=cp.getN_ORDER()?cp.getN_ORDER():0);
				pst.setString(7, cp.getC_DATA_PARA());
				pst.setString(8, "tmp_"+i+"_"+cp.getId());
				pst.addBatch();
			} 
			pst.executeBatch();
//			conn.commit();
//			bTrans = true;
//			conn.setAutoCommit(true);
			pst2 = conn.prepareStatement(((DataCopyBuilder) sqlbuilder).getSelectCopyRSql());	
			pst2.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(parentIds.toArray(new String[parentIds.size()]),conn));

			rs = pst2.executeQuery();
			while (rs.next()) {
				CopyData cp=new CopyData();
	        	cp.setC_DATA_NAME(rs.getString("C_DATA_NAME")); 
	        	cp.setC_DATA_CODE(rs.getString("C_DATA_CODE"));
	        	cp.setC_DATA_CODE_P(rs.getString("C_DATA_CODE_P"));
	        	cp.setC_SERVICE_CODE(rs.getString("C_SERVICE_CODE"));
	        	cp.setC_DV_STATE(rs.getString("C_DV_STATE")); 	                
	        	target.add(cp);
			}
		} catch (Exception e) {
			logger.error("查询失败："+e.getMessage(), e);
			throw new DataAccessException(e);
		} finally {			
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst2);
			DbFun.closeStatementFinal(pst);
			DbFun.endTransFinal(conn, bTrans);
			DbFun.releaseConnection(conn);
		}
	}
 
	/**
	 * 获取菜单，添加到pojoList
	 * STORY #85993 产品参数复制从FAST迁移到产品管理组件
	 * @param pojoList out
	 * @param parentIds  in
	 */
	public void getFun(List<BasePojo> pojoList,List<String> parentIds){		
		IFunDataService funDataService = YssServiceFactory.getInstance().createService(IFunDataService.class);
		List<SysFun> listSysFun= funDataService.getDataList();

		if (listSysFun != null) { 
            for (SysFun tmp : listSysFun) {
            	CopyData cp=new CopyData();
            	cp.setId(tmp.getId());
            	cp.setC_DATA_NAME(tmp.getC_FUN_NAME()); 
            	cp.setC_DATA_CODE(tmp.getC_FUN_CODE());
            	cp.setC_DATA_CODE_P(tmp.getC_FUN_CODE_P());
            	cp.setC_SERVICE_CODE("");
            	cp.setC_DV_STATE("1"); 	
                pojoList.add(cp);
            }
		}
		
//		Connection conn = null;
//		PreparedStatement pst = null;
//		ResultSet rs = null;
//		conn = this.loadNewConnection();
//		StringBuffer sb = new StringBuffer();
//		sb.append("select distinct(C_DATA_CODE||C_DATA_CODE_P),C_DATA_CODE ,C_DATA_CODE_P,C_DATA_NAME,C_SERVICE_CODE,C_DV_STATE,N_ORDER from ( ");
//		sb.append("select * from (");
//		sb.append("select b.c_iden, b.c_fun_name as c_data_name,b.c_fun_code as c_data_code , b.c_fun_code_p as c_data_code_p,'' as c_service_code,'1' as c_dv_state,b.n_order from v_s_fun b");
//		sb.append(" ) c  ");
////		sb.append(" start with c.c_data_Code_p in (SELECT * FROM TABLE(?)) Connect by prior  c.c_data_code_p = c.c_data_code ");
////		sb.append(" order by c.N_Order,Level desc");
//		sb.append(" )");
//		try {
//			pst = conn.prepareStatement(sb.toString());	
////			pst.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(parentIds.toArray(new String[parentIds.size()]),conn));			 
//			rs = pst.executeQuery();
//			while (rs.next()) {
//				CopyData cp=new CopyData();
//	        	cp.setC_DATA_NAME(rs.getString("C_DATA_NAME")); 
//	        	cp.setC_DATA_CODE(rs.getString("C_DATA_CODE"));
//	        	cp.setC_DATA_CODE_P(rs.getString("C_DATA_CODE_P"));
//	        	cp.setC_SERVICE_CODE(rs.getString("C_SERVICE_CODE"));
//	        	cp.setC_DV_STATE(rs.getString("C_DV_STATE")); 
//	        	cp.setN_ORDER(rs.getBigDecimal("N_ORDER"));
//	            pojoList.add(cp);
//			}
//		} catch (Exception e) {
//			logger.error("查询失败："+e.getMessage(), e);
//			throw new DataAccessException(e);
//		} finally {
//			this.closeResultSetFinal(rs);
//			this.closeStatementFinal(pst);
//			this.releaseConnection(conn);
//		}
	}
	
	/**
	 * STORY #85993 产品参数复制从FAST迁移到产品管理组件
	 * 获取 用户岗位数据， 添加到pojoList
	 * @param pojoList out
	 * @param parentIds out
	 * @param c_DATA_CODE
	 */
	public void getUserAndPost(List<BasePojo> pojoList,List<String> parentIds,String c_DATA_CODE){		
		IUserPostDataService userPostDataService = YssServiceFactory.getInstance().createService(IUserPostDataService.class);		
		List<BasePojo> listUserPostData=userPostDataService.getUserAndPost(c_DATA_CODE);
		List<String> tmpUsers=new ArrayList<String>();
		if (listUserPostData != null) { 
            for (BasePojo pojo : listUserPostData) {
            	UserPostData tmp=(UserPostData)pojo; 
            	CopyData cp=new CopyData();
	        	cp.setC_DATA_NAME(tmp.getC_DATA_NAME()); 
	        	cp.setC_DATA_CODE(tmp.getC_DATA_CODE());
	        	cp.setC_DATA_CODE_P(tmp.getC_DATA_CODE_P());
	        	cp.setC_SERVICE_CODE("IRightManageCopyService");
	        	cp.setC_DV_STATE("1"); 
	            pojoList.add(cp);
	            if(!tmpUsers.contains(cp.getC_DATA_CODE_P() + cp.getC_DATA_CODE())){
	            	tmpUsers.add(cp.getC_DATA_CODE_P()+ cp.getC_DATA_CODE());
	            	parentIds.add(cp.getC_DATA_CODE());
	            }
            }
		}
		
//		Connection conn = null;
//		PreparedStatement pst = null;
//		ResultSet rs = null;
//		conn = this.loadNewConnection();
//		StringBuffer sb = new StringBuffer();
//		sb.append("select distinct(C_DATA_CODE||C_DATA_CODE_P),C_DATA_CODE ,C_DATA_CODE_P,C_DATA_NAME,C_SERVICE_CODE,C_DV_STATE,N_ORDER from ( ");
//		sb.append("select * from (");
//		sb.append("select distinct b.c_user_name as c_data_name ,a.c_user_code as c_data_code,'RightManage' as c_data_code_p,'' as c_service_code,'1' as c_dv_state,0 as n_order from t_s_user_post_data a join t_s_user b on a.c_user_code = b.c_user_code where a.n_check_state = 1 and c_data_code = ? ");
//		sb.append(" union all ");
//		sb.append("select distinct b.c_post_name as c_data_name,a.c_user_code || '_' || a.c_post_code as c_data_code,a.c_user_code as c_data_code_p,'' as c_service_code,0 as c_dv_state,'' as n_order from t_s_user_post_data a join t_s_post b on a.c_post_code = b.c_post_code where a.n_check_state = 1 and c_data_code = ? ");
//		sb.append(" ) c  ");
//		sb.append(" )");
//		try {
//			List<String> tmpUsers=new ArrayList<String>();
//			pst = conn.prepareStatement(sb.toString());	
//			pst.setString(1, c_DATA_CODE);
//			pst.setString(2, c_DATA_CODE);			 
//			rs = pst.executeQuery();
//			while (rs.next()) {
//				CopyData cp=new CopyData();
//	        	cp.setC_DATA_NAME(rs.getString("C_DATA_NAME")); 
//	        	cp.setC_DATA_CODE(rs.getString("C_DATA_CODE"));
//	        	cp.setC_DATA_CODE_P(rs.getString("C_DATA_CODE_P"));
//	        	cp.setC_SERVICE_CODE("IRightManageCopyService");
//	        	cp.setC_DV_STATE(rs.getString("C_DV_STATE")); 
//	        	cp.setN_ORDER(rs.getBigDecimal("N_ORDER"));
//	            pojoList.add(cp);
//	            if(rs.getString("C_DATA_CODE_P").equals("RightManage")&& !tmpUsers.contains(cp.getC_DATA_CODE())){
//	            	tmpUsers.add(cp.getC_DATA_CODE());
//	            	parentIds.add(cp.getC_DATA_CODE());
//	            }
//			}
//		} catch (Exception e) {
//			logger.error("查询失败："+e.getMessage(), e);
//			throw new DataAccessException(e);
//		} finally {
//			this.closeResultSetFinal(rs);
//			this.closeStatementFinal(pst);
//			this.releaseConnection(conn);
//		}
		
	}
	
	/**
	 * 获取导入接口数据，添加到pojoList
	 * @param pojoList
	 * @param paraMap
	 */
	public void getInterfacePort(List<BasePojo> pojoList,HashMap<String, Object> paraMap){		
		try { 
			IBasicDefineDataService basicDataService = YssServiceFactory.getInstance().createService(IBasicDefineDataService.class);
			List<BasicDefine> pojoBasicDefine = basicDataService.queryAllBasicDefine();
			if (pojoBasicDefine != null) { 
	            for (BasicDefine tmp : pojoBasicDefine) {	            	 
	            	CopyData cp=new CopyData();
	            	cp.setC_DATA_NAME(tmp.getReportName());
	            	cp.setC_DATA_CODE(tmp.getReportCode());
	            	cp.setC_DATA_CODE_P("EXPCONFIG");
	            	cp.setC_SERVICE_CODE("IEXPSETTCOPYSERVICE");
	            	cp.setC_DV_STATE("1"); 	                
	                pojoList.add(cp);
	            }
	        }
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);			
		}		
	}
	
}
