package com.yss.ams.product.information.modules.dataCopy.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.support.modules.dataCopy.pojo.CopyData;
import com.yss.ams.product.information.support.modules.dataCopy.pojo.CopyDataCheck;
import com.yss.fast.right.support.right.pojo.UserPostData;
import com.yss.fast.right.support.right.service.IUserPostDataService;
import com.yss.fast.systemmanager.support.safesys.service.ISafeSysService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.common.co.SafeSystem;
import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.db.OraDbTool;
import com.yss.framework.util.validate.PreventInjectionUtility;
import com.yss.uco.dataintegration.support.dataservice.service.IBasicDefineDataService;
import com.yss.uco.dataintegration.support.trep.deploy.pojo.BasicDefine;
import com.yss.uco.dataintegration.support.trep.deploy.pojo.InterfacePort;
import com.yss.uco.dataintegration.support.trep.deploy.service.IInterfacePortService;

public class DataCopyCustomDao extends GeneralDao {

	public DataCopyCustomDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
	}

	/**
	 * 数据项代码
	 * 
	 * @param dataClassList
	 */
	public void insertData(List<String> dataClassList) {
		boolean bTrans = false; // 代表是否开始了事务
		PreparedStatement pst = null;// 定义预处理对象
		PreparedStatement pst2 = null;// 定义预处理对象
		Connection conn = null; // 获取数据库连接
		String sql = ((DataCopyCustomBuilder) sqlbuilder)
				.getInsertSql();
		String strDel = ((DataCopyCustomBuilder) sqlbuilder)
				.getDelSql();
		try {
			conn = loadNewConnection(); // 获取数据库连接
			conn.setAutoCommit(false);
			pst = conn.prepareStatement(strDel);
			pst.setString(1, "dataCopy");
			pst.executeUpdate();
			pst2 = conn.prepareStatement(sql);
			for (int i = 0; i < dataClassList.size(); i++) {
				pst2.setString(1, dataClassList.get(i));
				pst2.setString(2, "dataCopy");
				pst2.addBatch();
			}
			pst2.executeBatch();
			conn.commit();
			bTrans = false;
			conn.setAutoCommit(true);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		} finally {
			DbFun.endTransFinal(conn, bTrans);
			DbFun.closeStatementFinal(pst);
			DbFun.closeStatementFinal(pst2);
			DbFun.releaseConnection(conn);
		}
	}

	public List<String> getQuerySql(String fun_code) {
		List<String> dataList = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		try {
			conn = this.loadNewConnection();
			sql = ((DataCopyCustomBuilder) sqlbuilder).getQuerySql(fun_code);
			pstmt = conn.prepareStatement(sql);
//			logger.log(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dataList.add(rs.getString("c_data_code"));
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return dataList;
	}
	

	public List<BasePojo> queryCreateCopy(String portCode) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		boolean needGetRPT=false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//BUG #370571 【银华基金-估值核算300.7】产品基本信息复制功能需要去掉权限设置
		boolean flag = false;
		//STORY101429【银华基金-估值核算300.7】产品基本信息复制功能需要去掉权限设置
		//当项目值 = 是并且启用的时候添加权限复制选项
		ISafeSysService safeSysService = YssServiceFactory.getInstance().createService(ISafeSysService.class);
		SafeSystem oneSafeSys = safeSysService.getOneSafeSys("M0");
		if (oneSafeSys != null && "ENAB".equals(oneSafeSys.getC_DV_STATE()) && "1".equals(oneSafeSys.getC_VALUE())) {
			flag = true;
      	} 
		String sql = "";
		try {
			conn = this.loadNewConnection();
			// liuxiang 2015-12-17 STORY #26079 华泰证券：估值4.5_新组合自动添加到TA导出文件报表分组
//			sql = "select *  from T_S_DATA_COPY a order by n_order ";
			sql = ((DataCopyCustomBuilder) sqlbuilder).getCreateCopySql();
			pstmt = conn.prepareStatement(sql);
			//edit by huangjin 2016-9-22 STORY #28950 参数复制功能优化-复制权限和群组以及产品数
			//参照组合改变的时候传参
//			pstmt.setString(1, portCode);
//			pstmt.setString(2, portCode);
//			pstmt.setString(3, portCode);
//			pstmt.setString(4, portCode);
//			pstmt.setString(5, portCode);
//			logger.log(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				if ("RightManage".equals(rs.getString("C_DATA_CODE")) && !flag) {
					continue;
				}
				CopyData t = new CopyData();
				t.setC_DATA_NAME(rs.getString("C_DATA_NAME"));
				t.setC_DATA_CODE(rs.getString("C_DATA_CODE"));
				t.setC_DATA_CODE_P(rs.getString("C_DATA_CODE_P"));
				t.setC_DV_STATE(rs.getString("C_DV_STATE"));
				t.setC_SERVICE_CODE(rs.getString("C_SERVICE_CODE"));
				t.setC_DATA_PARA(rs.getString("C_DATA_PARA"));
				pojoList.add(t);
				if(null!=t.getC_DATA_CODE() && t.getC_DATA_CODE().equals("Expconfig")){
					needGetRPT=true;
				}
			}
			
			if(needGetRPT){
				getInterfacePort(pojoList,portCode);	
			}
			//STORY101429【银华基金-估值核算300.7】产品基本信息复制功能需要去掉权限设置
			//当项目值 = 是并且启用的时候添加权限复制选项
			if (flag) {
			   //用户岗位
        	   getUserAndPost(pojoList,portCode);
          	} 
			
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return pojoList;
	}
	
	/**
	 * STORY #85993 产品参数复制从FAST迁移到产品管理组件
	 * 获取 用户岗位数据， 添加到pojoList
	 * @param pojoList out
	 * @param parentIds out
	 * @param c_DATA_CODE
	 */
	public void getUserAndPost(List<BasePojo> pojoList,String c_DATA_CODE){		
		IUserPostDataService userPostDataService = YssServiceFactory.getInstance().createService(IUserPostDataService.class);		
		List<BasePojo> listUserPostData=userPostDataService.getUserAndPost(c_DATA_CODE);
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
            }
		}
	}
	
	/**
	 * STORY #85993 产品参数复制从FAST迁移到产品管理组件
	 * @param pojoList out
	 * @param clazz
	 */
	public void getInterfacePort(List<BasePojo> pojoList,String portCode){		
		try {		
//			List<BasePojo> pojoInterfacePort=YssServiceFactory.getInstance().createService(InterfacePortService.class).queryByCondition(null).getDataList(); 
			IBasicDefineDataService basicDataService = YssServiceFactory.getInstance().createService(IBasicDefineDataService.class);
			List<BasicDefine> pojoBasicDefine = basicDataService.queryAllBasicDefine();
			IInterfacePortService interfacePortService=YssServiceFactory.getInstance().createService(IInterfacePortService.class);
			List<String> dataReportCodeP1=new ArrayList<String>();//分页
			List<String> dataReportCodeP2=new ArrayList<String>();//分组
			//获取当前组合的 导出接口
			List<BasePojo> basepojos = interfacePortService.getCopyList("",portCode);			
			if (pojoBasicDefine != null) { 
	            for (BasicDefine tmp : pojoBasicDefine) {
	            	if(tmp.getReportMode().equals("TEMP_USABLE")){ //&&"".equals(tmp.getExpMode().trim())
	            		for(BasePojo pojo:basepojos){
	            			InterfacePort pojo1=(InterfacePort)pojo;
	            			if(pojo1.getReportCode().equals(tmp.getReportCode())){
	            				if(!dataReportCodeP1.contains(pojo1.getC_Page_Name())){
	            					dataReportCodeP1.add(pojo1.getC_Page_Name());
	            					CopyData cp=new CopyData();
		    		            	cp.setC_DATA_NAME(pojo1.getC_Page_Name()); 
		    		            	cp.setC_DATA_CODE(pojo1.getC_Page_Name());
		    		            	cp.setC_DATA_CODE_P("Expconfig");
		    		            	cp.setC_SERVICE_CODE("IExpSettCopyService");
		    		            	cp.setC_DV_STATE("0"); 	                
		    		                pojoList.add(cp);
	    		                }
	            				if(!dataReportCodeP2.contains(pojo1.getC_Catalog_Code())){
	            					dataReportCodeP2.add(pojo1.getC_Catalog_Code());
	            					CopyData cp=new CopyData();
		    		            	cp.setC_DATA_NAME(pojo1.getC_Catalog_Code()); 
		    		            	cp.setC_DATA_CODE(pojo1.getC_Catalog_Code());
		    		            	cp.setC_DATA_CODE_P(pojo1.getC_Page_Name());
		    		            	cp.setC_SERVICE_CODE("IExpSettCopyService");
		    		            	cp.setC_DV_STATE("0"); 	                
		    		                pojoList.add(cp);
	    		                }
	            				CopyData cp=new CopyData();
	    		            	cp.setC_DATA_NAME(tmp.getReportName()); 
	    		            	cp.setC_DATA_CODE(pojo1.getReportCode());
	    		            	cp.setC_DATA_CODE_P(pojo1.getC_Catalog_Code());
	    		            	cp.setC_SERVICE_CODE("IExpSettCopyService");
	    		            	cp.setC_DV_STATE("1"); 	                
	    		                pojoList.add(cp);
	            			}
	            		}		            	
	            	}
	            }
	        }
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);			
		}		
	}

	public List<BasePojo> queryPortClsCreateCopy() {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		try {
			conn = this.loadNewConnection();
			sql = " SELECT C_DATA_NAME, DECODE(C_DATA_CODE, ?, C_DATA_PARA, C_DATA_CODE) AS C_DATA_CODE, C_DATA_CODE_P, "
					+" C_DV_STATE, C_SERVICE_CODE, N_ORDER, C_DATA_PARA FROM T_S_DATA_COPY  "
					+ " WHERE C_DATA_CODE IN ('securitiesvaluation', 'checkSet', 'indexmanage') "
					+ " ORDER BY N_ORDER ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "planRelaPort");
//			logger.log(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CopyData t = new CopyData();
				t.setC_DATA_NAME(rs.getString("C_DATA_NAME"));
				t.setC_DATA_CODE(rs.getString("C_DATA_CODE"));
				t.setC_DATA_CODE_P(rs.getString("C_DATA_CODE_P"));
				t.setC_DV_STATE(rs.getString("C_DV_STATE"));
				t.setC_SERVICE_CODE(rs.getString("C_SERVICE_CODE"));
				t.setC_DATA_PARA(rs.getString("C_DATA_PARA"));
				pojoList.add(t);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return pojoList;
	}

	/**
	 * STORY64614富国基金-【运维】产品参数复制功能优化，需考虑重复复制的情况
	 * add by zhaijiajia 20190222
	 * @param paraMap
	 * @param page
	 * @param clazz 
	 * @return
	 */
	public List<BasePojo> queryCopyCheckData(HashMap<String, Object> paraMap,
			PageInation page, Class<?> clazz) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		List<String> paraNameList;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";

		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			paraNameList = getParaName(paraMap);
			conn = this.loadNewConnection();

			sql = ((DataCopyCustomBuilder) sqlbuilder).getCopyCheckDataSql(paraNameList);
			sql = buildPagingSql(sql, page);

			pstmt = conn.prepareStatement(sql);

			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}
			//// 如果SQL中有问号时才进行赋值
			if (sql.indexOf("?") > -1) {
				int index = 1;
				Object paraValue;
				for (String valueFieldName : paraNameList) {
					if ("N_CHECK_STATE".equals(valueFieldName)) {
						continue;
					}
					/**Start 20150430 added by liubo.BUG #111830 数据审核管理点击A区可选项目，有些界面报错
					 * 过滤掉QUERY_SOURCE的赋值，数据审核管理有几个界面会直接走基类查询，然后传入这个参数，这样产生无效的列索引的异常*/
					if ("QUERY_SOURCE".equals(valueFieldName)) {
						continue;
					}
					/**End 20150430 added by liubo.BUG #111830 数据审核管理点击A区可选项目，有些界面报错*/

					if (valueFieldName.startsWith("ARRAY_")) {
						pstmt.setArray(index, OraDbTool.newInstance().sqlOverLongCondition(String
								.valueOf(paraMap.get(valueFieldName)),conn));
					} else {
						paraValue = paraMap.get(valueFieldName);
						if (java.util.Date.class.equals(paraValue)) {
							Date dateValue = new Date(
									((java.util.Date) paraValue).getTime());
							pstmt.setDate(index, dateValue);
						} else {
							pstmt.setObject(index, paraValue);
						}
					}

					index++;
				}
			}
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				CopyDataCheck copyCheck = new CopyDataCheck();
				copyCheck.setId(rs.getString("C_IDEN"));
				copyCheck.setC_COPY_STATE(rs.getString("C_COPY_STATE"));
				copyCheck.setC_DATA_CODE(rs.getString("C_DATA_CODE"));
				copyCheck.setC_DATA_NAME(rs.getString("C_DATA_NAME"));
				copyCheck.setC_PORT_CODE(rs.getString("C_PORT_CODE"));
				copyCheck.setC_SOURCE_CODE(rs.getString("C_SOURCE_CODE"));
				pojoList.add(copyCheck);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return pojoList;
	}

	/**
	 * STORY64614富国基金-【运维】产品参数复制功能优化，需考虑重复复制的情况
	 * add by zhaijiajia 20190222
	 * @param paraMap
	 * @return
	 */
	public int queryCopyCheckDataCount(HashMap<String, Object> paraMap) {
		List<String> paraNameList;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		int recCount = 0;
		try {
			paraNameList = getParaName(paraMap);
			conn = this.loadNewConnection();
			// conn.setAutoCommit(false);
			sql = ((DataCopyCustomBuilder) sqlbuilder).getCopyCheckDataCountSql(paraNameList);

			pstmt = conn.prepareStatement(sql);

			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}

			//// 如果SQL中有问号时才进行赋值
			if (sql.indexOf("?") > -1) {
				int index = 1;
				Object paraValue = null;
				for (String valueFieldName : paraNameList) {
					if ("N_CHECK_STATE".equals(valueFieldName)) {
						continue;
					}
					/**Start 20150430 added by liubo.BUG #111830 数据审核管理点击A区可选项目，有些界面报错
					 * 过滤掉QUERY_SOURCE的赋值，数据审核管理有几个界面会直接走基类查询，然后传入这个参数，这样产生无效的列索引的异常*/
					if ("QUERY_SOURCE".equals(valueFieldName)) {
						continue;
					}
					/**End 20150430 added by liubo.BUG #111830 数据审核管理点击A区可选项目，有些界面报错*/

					if (valueFieldName.startsWith("ARRAY_")) {
						pstmt.setArray(index, OraDbTool.newInstance().sqlOverLongCondition(String
								.valueOf(paraMap.get(valueFieldName)),conn));
					} else {
						paraValue = paraMap.get(valueFieldName);
						if (java.util.Date.class.equals(paraValue)) {
							Date dateValue = new Date(
									((java.util.Date) paraValue).getTime());
							pstmt.setDate(index, dateValue);
						} else {
							pstmt.setObject(index, paraValue);
						}
					}

					index++;
				}
			}
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				recCount = rs.getInt(1);
			}

		} catch (Exception ex) {
			throw new DataAccessException("条件查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return recCount;
	}

	/**
	 * STORY64614富国基金-【运维】产品参数复制功能优化，需考虑重复复制的情况
	 * 获取满足如下条件的sql:
	 * 组合 A已净值确认即THREETYPE (影响估值表、核算、清算的参数不能再次复制)
	 *       已存在凭证即TWOTYPE (影响核算、清算的参数不能再次复制)
	 *       已存在流水即ONETYPE (影响清算的项目不能再次复制)
	 * add by zhaijiajia 20190223
	 * @param portCodeList
	 * @return
	 */
	/*public HashMap<String, String> getPortCopyType(String[] portCodeList) {
		HashMap<String, String> portTypeMap = new HashMap<String, String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		try {
			conn = this.loadNewConnection();
			sql = ((DataCopyCustomBuilder) sqlbuilder).getPortCopyTypeSql();
			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(portCodeList,conn));
			pstmt.setDate(2, YssFun.toSqlDate(DateUtil.getFirstDay(DateUtil.stringtoDate(DateUtil.getNow(), "yyyy-MM-dd"))));
			pstmt.setArray(3, OraDbTool.newInstance().sqlOverLongCondition(portCodeList,conn));
			pstmt.setDate(4, YssFun.toSqlDate(DateUtil.getFirstDay(DateUtil.stringtoDate(DateUtil.getNow(), "yyyy-MM-dd"))));
			pstmt.setArray(5, OraDbTool.newInstance().sqlOverLongCondition(portCodeList,conn));
			pstmt.setDate(6, YssFun.toSqlDate(DateUtil.getFirstDay(DateUtil.stringtoDate(DateUtil.getNow(), "yyyy-MM-dd"))));
			logger.debug(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				portTypeMap.put(rs.getString("C_PORT_CODE"), rs.getString("TYPE"));
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return portTypeMap;
	}*/

	/**
	 * STORY64614富国基金-【运维】产品参数复制功能优化，需考虑重复复制的情况
	 * 前台A区界面选择M个组合，B区展示所有明细项目N个，则保存M*N条复制记录
	 * @param copyDataMap
	 * @throws Exception 
	 */
	public void insertCopyInfos(
			HashMap<String, HashMap<String, BasePojo>> copyDataMap) throws Exception {
		boolean bTrans = false; // 代表是否开始了事务
		PreparedStatement pst = null;// 定义预处理对象
		Connection conn = null; // 获取数据库连接
		ResultSet rs = null;
		try {
			conn = loadNewConnection(); // 获取数据库连接
			conn.setAutoCommit(false);
//			String sql = ((DataCopyCustomBuilder) sqlbuilder)
//					.getInsertCopyInfosSql();
//			pst = conn.prepareStatement(sql);
			for (String dataCode : copyDataMap.keySet()) {
				HashMap<String, BasePojo> copyMap = copyDataMap.get(dataCode);
				for (String portTemp : copyMap.keySet()) {
					CopyDataCheck copyCheck = (CopyDataCheck)copyMap.get(portTemp);
					deleteInfos(copyCheck, conn);
					String typesql = ((DataCopyCustomBuilder) sqlbuilder)
							.getCopyInfosTypeSql();
					int index = 1;
					
					pst = conn.prepareStatement(typesql);
					pst.setString(index++, copyCheck.getC_PORT_CODE());
					pst.setString(index++, copyCheck.getC_DATA_CODE());
					pst.setString(index++, copyCheck.getC_SOURCE_CODE());
					pst.setString(index++, copyCheck.getC_COPY_STATE());
					pst.setString(index++, copyCheck.getC_PORT_CODE());
					pst.setString(index++, copyCheck.getC_DATA_CODE());
					pst.setString(index++, copyCheck.getC_SOURCE_CODE());
					pst.setString(index++, copyCheck.getC_COPY_STATE());
					rs = pst.executeQuery();
					if (rs.next()) {
						if ("NULL".equalsIgnoreCase(rs.getString("TYPE"))) {
							
						} else if ("INSERT".equalsIgnoreCase(rs.getString("TYPE"))) {
							insertInfos(copyCheck, conn);
						} else {
							updateInfos(rs.getString("TYPE"), copyCheck, conn);
						}
					} else {
						insertInfos(copyCheck, conn);
					}
					
					DbFun.closeStatementFinal(pst);
					DbFun.closeResultSetFinal(rs);
				}
			}
			conn.commit();
			bTrans = false;
			conn.setAutoCommit(true);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		} finally {			
			DbFun.closeStatementFinal(pst);
			DbFun.closeResultSetFinal(rs);
			DbFun.releaseConnection(conn);
		}
	}
	
	/**
	 * STORY64614富国基金-【运维】产品参数复制功能优化，需考虑重复复制的情况
	 * 前台A区界面选择M个组合，B区展示所有明细项目N个，则保存M*N条复制记录
	 * @param copyDataMap
	 * @throws Exception 
	 */
	public void insertInfos(CopyDataCheck copyCheck, Connection conn) throws Exception {
		PreparedStatement pst = null;// 定义预处理对象
		try {
//			conn = loadNewConnection(); // 获取数据库连接
			String sql = ((DataCopyCustomBuilder) sqlbuilder)
					.getInsertCopyInfosSql();
			pst = conn.prepareStatement(sql);
			int index = 1;
			pst.setString(index++, copyCheck.getC_PORT_CODE());
			pst.setString(index++, copyCheck.getC_SOURCE_CODE());
			pst.setString(index++, copyCheck.getC_DATA_CODE());
			pst.setString(index++, copyCheck.getC_DATA_NAME());
			pst.setString(index++, copyCheck.getC_COPY_STATE());
			pst.executeUpdate();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		} finally {
			DbFun.closeStatementFinal(pst);
		}
	}
	
	/**
	 * STORY64614富国基金-【运维】产品参数复制功能优化，需考虑重复复制的情况
	 * 前台A区界面选择M个组合，B区展示所有明细项目N个，则保存M*N条复制记录
	 * @param copyDataMap
	 * @throws Exception 
	 */
	public void updateInfos(String columnname, CopyDataCheck copyCheck, Connection conn) throws Exception {
		PreparedStatement pst = null;// 定义预处理对象
		try {
//			conn = loadNewConnection(); // 获取数据库连接
			String sql = ((DataCopyCustomBuilder) sqlbuilder)
					.getUpdteCopyInfosSql(columnname);
			sql = PreventInjectionUtility.sqlInjection1(sql);
			pst = conn.prepareStatement(sql);
			int index = 1;
			if ("C_SOURCE_CODE".equalsIgnoreCase(columnname)) {
				pst.setString(index++, copyCheck.getC_SOURCE_CODE());
			} else {
				pst.setString(index++, copyCheck.getC_COPY_STATE());
			}
			pst.setString(index++, copyCheck.getC_PORT_CODE());
			pst.setString(index++, copyCheck.getC_DATA_CODE());
			if ("C_SOURCE_CODE".equalsIgnoreCase(columnname)) {
				pst.setString(index++, copyCheck.getC_COPY_STATE());
			} else {
				pst.setString(index++, copyCheck.getC_SOURCE_CODE());
			}
			
			pst.executeUpdate();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		} finally {
			DbFun.closeStatementFinal(pst);
		}
	}
	
	/**
	 * STORY64614富国基金-【运维】产品参数复制功能优化，需考虑重复复制的情况
	 * 前台A区界面选择M个组合，B区展示所有明细项目N个，则保存M*N条复制记录
	 * @param copyDataMap
	 * @throws Exception 
	 */
	public void deleteInfos(CopyDataCheck copyCheck, Connection conn) throws Exception {
		PreparedStatement pst = null;// 定义预处理对象
		try {
//			conn = loadNewConnection(); // 获取数据库连接
			String sql = ((DataCopyCustomBuilder) sqlbuilder)
					.getdeleteCopyInfosSql();
			pst = conn.prepareStatement(sql);
			int index = 1;
			pst.setString(index++, copyCheck.getC_PORT_CODE());
			pst.setString(index++, copyCheck.getC_DATA_CODE());
			pst.setString(index++, copyCheck.getC_SOURCE_CODE());
			
			pst.executeUpdate();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		} finally {
			DbFun.closeStatementFinal(pst);
		}
	}
	
	/**
	 * STORY64614富国基金-【运维】产品参数复制功能优化，需考虑重复复制的情况
	 * 获取满足如下条件的sql:
	 * 组合 A已净值确认即THREETYPE (影响估值表、核算、清算的参数不能再次复制)
	 *       已存在凭证即TWOTYPE (影响核算、清算的参数不能再次复制)
	 *       已存在流水即ONETYPE (影响清算的项目不能再次复制)
	 * add by zhaijiajia 20190223
	 * @param portCodeList
	 * @return
	 */
	public boolean existsTable(String tableName) {
		Connection conn = null;
		boolean flag = false;
		try {
			conn = this.loadNewConnection();
			flag = DbFun.existsTable(conn, tableName);
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			this.releaseConnection(conn);
		}
		return flag;
	}
}
