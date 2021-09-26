package com.yss.ams.base.information.modules.bi.org.dao;

import java.beans.PropertyDescriptor;
import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.ams.base.information.support.bi.org.pojo.Org;
import com.yss.fast.atomicdata.support.dict.pojo.Vocabulary;
import com.yss.framework.api.common.YssConstant;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.biz.BusinessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.api.util.YssFun;
import com.yss.framework.context.ContextFactory;
import com.yss.framework.db.DbPoolFactory;
import com.yss.framework.db.OraDbTool;
import com.yss.framework.util.DateUtil;
import com.yss.framework.util.PojoUtils;
import com.yss.platform.support.dataservice.service.IVocCacheDataService;

/**
 * 机构dao
 * @author 马向峰  拆分 2017.0527
 *
 */
public class OrgDao extends GeneralDao {

	private OrgSqlBuilder orgSqlBuilder;
	
	public OrgDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		this.orgSqlBuilder = (OrgSqlBuilder)sqlBuilder;
	}

	public List<BasePojo> getPortRelaOrgDao(HashMap<String, Object> paraMap,
			Class<?> clazz) {
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
			conn.setAutoCommit(false);
			sql = this.orgSqlBuilder.getPortRelaOrgSql(paraNameList);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraMap.get("ARRAY_C_PORT_CODE") == null ? "" : paraMap.get("ARRAY_C_PORT_CODE").toString());
			// BUG #131260 【紧急】【招商财富】产品基本信息界面做关联机构设置时点击保存按钮没有任何反应也保存不了
			// xiaozhilong 20160520
			// 没什么好注释的，sql写完各种语法错误，改对了
			/*
			 * Author : HeLiang 
			 * Date : 2016-08-16 
			 * Status : Modify 
			 * Comment:
			 * 增加“外包服务机构”到机构资质中，查询的时候少对一个问号进行赋值，添加一个index:15->16，后面的index向后顺延
			 * modified by heliang.STORY #30646 【云平台】万联证券关联机构设置增加外包服务机构类型
			 */
			for (int i = 2; i < 22; i++) {
				pstmt.setString(i, paraMap.get("C_DV_TYPE_CODE").toString());
			}
			// add by Sunhe STORY #28914 20160222
			// modified by HeLiang 2016-08-16 STORY #30646 【云平台】万联证券关联机构设置增加外包服务机构类型
			pstmt.setString(22, paraMap.get("ARRAY_C_PORT_CODE") == null ? "" : paraMap.get("ARRAY_C_PORT_CODE").toString());
			// modified by HeLiang 2016-08-16 STORY #30646 【云平台】万联证券关联机构设置增加外包服务机构类型
			// BUG #147121 【云平台万联证券】主体信息设置，资质为外包服务机构的，无法关联到组合
			for (int i = 23; i <= 43; i++) {
				pstmt.setString(i, paraMap.get("C_DV_TYPE_CODE").toString());
			}
			
			rs = pstmt.executeQuery();

			conn.commit();
			conn.setAutoCommit(true);

			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors((BasePojo)clazz.newInstance());
			while (rs.next()) {
				BasePojo t = rsTools.ResultToBean(rs, clazz, props);
				pojoList.add(t);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
//			try {
//				if (conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
		}

		return pojoList;
	}
	
	/* START 数据服务方法 */
	/**
	 * 获取列表所有数据
	 * @return	所有机构数据
	 * @throws Exception
	 */
	public List<Org> getAllDataList() throws Exception {
		List<Org> pojoList = new ArrayList<Org>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		OrgSqlBuilder dsServiceBuilder = null;
		Org t = null;
		try {
			dsServiceBuilder = new OrgSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = dsServiceBuilder.getAllDataSql();

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(new Org());
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, Org.class, props);
				
				pojoList.add(t);
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
	 * 根据机构代码  获取机构信息
	 * @param code	机构代码
	 * @return  机构信息
	 * @throws Exception
	 */
	public Org getDataByCode(String code) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		OrgSqlBuilder dsServiceBuilder = null;
		Org t = new Org();
		try {
			dsServiceBuilder = new OrgSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = dsServiceBuilder.getDataByCode();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);

			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(new Org());
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, Org.class, props);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return t;
	}

	/**
	 * 根据机构类型组获得机构信息
	 * @param types	机构类型 组
	 * @return 机构列表
	 * @throws Exception
	 */
	public List<Org> getDataListByTypes(String[] types) throws Exception {
		List<Org> pojoList = new ArrayList<Org>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		OrgSqlBuilder dsServiceBuilder = null;
		Org t = null;
		try {
			dsServiceBuilder = new OrgSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = dsServiceBuilder.getHeadDataListByTypes();

			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(types,conn));
			
			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(new Org());
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, Org.class, props);
				pojoList.add(t);
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
	
	public List<Org> getDataListByZtzz(String[] types) {
		List<Org> pojoList = new ArrayList<Org>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		OrgSqlBuilder dsServiceBuilder = null;
		Org t = null;
		try {
			dsServiceBuilder = new OrgSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = dsServiceBuilder.getHeadDataListByZtzz();

			pstmt = conn.prepareStatement(sql);
			for(int i=1; i<19; i++){
				pstmt.setArray(i, OraDbTool.newInstance().sqlOverLongCondition(types,conn));
			}
			pstmt.setString(19, types[0]);
			
			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(new Org());
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, Org.class, props);
				pojoList.add(t);
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
	 * 将机构代码和机构名称转化为map
	 * @return HashMap<机构代码, 机构名称>
	 * @throws Exception
	 */
	public HashMap<String, String> getKeyConvertMap() throws Exception {
		HashMap<String, String> keyValueMap = new HashMap<String, String>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";

		OrgSqlBuilder dsServiceBuilder = null;
		try {
			dsServiceBuilder = new OrgSqlBuilder();
			conn = this.loadNewConnection();
			sql = dsServiceBuilder.getAllDataSql();

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				keyValueMap.put(rs.getString("C_ORG_CODE"), rs.getString("C_ORG_NAME"));
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return keyValueMap;
	}
	
	/**
	 * 根据机构代码集合查询机构信息，并将机构代码和机构名称存到map中
	 * @param listKey	机构代码列表
	 * @return HashMap<机构代码, 机构名称>
	 * @throws Exception
	 */
	public HashMap<String, String> getKeyConvertMap(List<String> listKey) throws Exception {
		HashMap<String, String> keyValueMap = new HashMap<String, String>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		OrgSqlBuilder dsServiceBuilder = null;
		Org t = null;
		try {
			String[] strArr = new String[listKey.size()];
			for(int i = 0; i<listKey.size(); i++) {
				strArr[i] = listKey.get(i);
			}
			dsServiceBuilder = new OrgSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = dsServiceBuilder.getAllDataSqlByKeys();
			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(strArr,conn));
			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(new Org());
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, Org.class, props);
				keyValueMap.put(t.getC_ORG_CODE(), t.getC_ORG_NAME());
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return keyValueMap;
	}
	
	/**
	 * 根据机构类型组查询机构信息
	 * @param types 机构类型组
	 * @return 机构列表
	 * @throws Exception
	 */
	public List<Org> getDataListByKeys(String[] keys) throws Exception {
		List<Org> pojoList = new ArrayList<Org>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		OrgSqlBuilder dsServiceBuilder = null;
		Org t = null;
		try {
			dsServiceBuilder = new OrgSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = dsServiceBuilder.getDataListByTypes();

			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(keys,conn));

			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(new Org());
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, Org.class, props);
				pojoList.add(t);
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
	 * 组织字典信息
	 * @return
	 */
	public List<BasePojo> getOrgVoc(){
		List<BasePojo> orgVocList = new ArrayList<BasePojo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrgSqlBuilder orgSqlBuilder = new OrgSqlBuilder();
		String sql = orgSqlBuilder.getOraVocSql();
		try{
			conn = this.loadNewConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Vocabulary voc = new Vocabulary();
				voc.setC_DV_CODE(rs.getString(1));
				voc.setC_DV_NAME(rs.getString(2));
				orgVocList.add(voc);
			}
		}catch(Exception ex){
			throw new DataAccessException("查询失败"+ex.getMessage(),ex);
		}finally{
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return orgVocList;
	}

	public List<BasePojo> queryPortRelaOrg(HashMap<String, Object> paraMap,
			PageInation page) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "";
		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			conn = this.loadNewConnection();
			sql = this.orgSqlBuilder.queryPortRelaOrgSql(getParaName(paraMap));
			sql = buildPagingSql(sql, page);
			pstmt = conn.prepareStatement(sql);
		  /**Start 20150318 modified by liubo.BUG #109422 产品基本信息，关联界面查询报错
		   * 添加非空判断*/
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(paraMap.get("ARRAY_C_PORT_CODE") == null ? "" : paraMap.get("ARRAY_C_PORT_CODE").toString(),conn));
		  /**End 20150318 modified by liubo.BUG #109422 产品基本信息，关联界面查询报错*/

			rs = pstmt.executeQuery();
			
			IVocCacheDataService vocCacheService = YssServiceFactory.getInstance().createServiceByImplClass(IVocCacheDataService.class, "com.yss.fast.atomicdata.dict.service.impl.VocCacheDataService");
			List<com.yss.platform.support.dataservice.pojo.dict.Vocabulary> list = vocCacheService.getDataListByTypes(new String[]{"RELA_ORG_TYPE"});        
			
			HashMap<String, String> map =new HashMap<String, String>();
			for (com.yss.platform.support.dataservice.pojo.dict.Vocabulary vocabulary : list) {
				map.put(vocabulary.getC_DV_CODE(), vocabulary.getC_DV_NAME());
			}
			
			while (rs.next()) {
				BasePojo t = rsTools.ResultToBean(rs, Org.class);
				/*BUG #108593 产品信息》产品基本信息》关联机构设置
				 * 此处审核状态显示为关联机构关联纪录是否审核,而非关联机构审核状态*/
				((Org)t).setAuditState(rs.getInt("N_CHECK_STATE_B"));
				setQualification(map, t);
				pojoList.add(t);
				Org org = new Org();
				org.setAuditDate(rs.getString("C_CHECK_TIME_B"));
				org.setAuditState(rs.getInt("N_CHECK_STATE_B"));
				org.setC_DESC(rs.getString("C_DESC_B"));
				org.setId(rs.getString("C_IEDN_B"));
				org.setModifier(rs.getString("C_UPDATE_BY_B"));
				org.setModifyDate(rs.getString("C_UPDATE_TIME_B"));
				org.setC_DV_ORG_TYPE(rs.getString("C_DV_TYPE_CODE"));
				org.setC_DC_CODE(rs.getString("C_PORT_CODE"));
				org.setC_ORG_CODE(rs.getString("C_RELA_CODE"));
				org.setC_MKT_CODE(rs.getString("C_RELA_TYPE"));
				pojoList.add(org);
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

	public int queryPortRelaOrgCount(HashMap<String, Object> paraMap) {
		List<String> paraNameList;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		int recCount = 0;
		try {
			paraNameList = getParaName(paraMap);
			conn = this.loadNewConnection();
			sql = this.orgSqlBuilder.getqueryPortRelaOrgCountSql(paraNameList);
			pstmt = conn.prepareStatement(sql);
		  /**Start 20150318 modified by liubo.BUG #109422 产品基本信息，关联界面查询报错
		   * 添加非空判断*/
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(paraMap.get("ARRAY_C_PORT_CODE") == null ? "" : paraMap.get("ARRAY_C_PORT_CODE").toString(),conn));
		  /**End 20150318 modified by liubo.BUG #109422 产品基本信息，关联界面查询报错*/
			rs = pstmt.executeQuery();

			while (rs.next()) {
				recCount = rs.getInt(1);
			}

		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("关联机构设置：根据条件查询机构信息记录数失败", ex);
			throw new DataAccessException("条件查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return recCount;
	}
	
	@Override
	public List<BasePojo> queryByConditionPage(HashMap<String, Object> paraMap,
			PageInation page, Class<?> clazz) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		List<String> paraNameList;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";

		// Object resValue = null;
		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			paraNameList = getParaName(paraMap);
			
			//BUG #185160 机构基本信息：以主体资质为查询条件查询报错FAST1.3.3前台较1.3版本多传递了一个参数N_AUTHORG，为不影响下面处理逻辑此处直接移除。
			if (paraNameList.contains("N_AUTHORG")) {
				paraNameList.remove("N_AUTHORG");
			}
			
			//如果前台没有开启数据安全审核机制，则没有拼装 N_CHECK_STATE条件，无法命中索引
			if (!paraNameList.contains("SearchAll") && !paraNameList.contains("SearchAudit")
					&& !paraNameList.contains("SearchUnAudit")) {
				paraNameList.add("SearchAll");
			}
			
			conn = this.loadNewConnection();
			// conn.setAutoCommit(false);

			sql = sqlbuilder.getQueryConditionSql(paraNameList);
			
			/*- Start Added by jiangzhichao 20171222 添加关联状态表 */
			if (showState != null && showState.isShow() && !showState.getIsPlm()) {
				sql = buildStateSql(sql, this.sqlbuilder.getTableName(this.dbNameResolver));
			} else if(showState != null && showState.getIsPlm()){
				sql = buildRelaSql(sql, this.sqlbuilder.getTableName(this.dbNameResolver), showState);
			}
			/*- End Added by jiangzhichao 添加关联状态表 */
			
			sql = buildPagingSql(sql, page);
			sql = ((OrgSqlBuilder)sqlbuilder).getQueryConditionSql(sql);
			pstmt = conn.prepareStatement(sql);

			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}
			//// 如果SQL中有问号时才进行赋值
			if (sql.indexOf("?") > -1) {
				//获取该类型参数问号数量用于循环赋值 mzy 20170411
				int paraAmount = sql.split("\\?").length-1-(paraNameList.size()-1)-1;  
				int index = 1;
				Object paraValue;
				for (String valueFieldName : paraNameList) {
					if ("N_CHECK_STATE".equals(valueFieldName)) {
						continue;
					}

					if ("N_AUTHORG".equals(valueFieldName)) {
						continue;
					}
					
					if(valueFieldName.equals("ARRAY_C_QUALIFICATION")){
						Array array = OraDbTool.newInstance().sqlOverLongCondition(String.valueOf(paraMap.get(valueFieldName)),conn);
						//// 参数个数有修改，index值未变，导致根据机构资质查询报错，
						/*
						 * Author : HeLiang
						 * Date   : 2016-07-27
						 * Status : Modify
						 * Comment: 增加“外包服务机构”到机构资质中，添加一个index:12->13
						 * modified by heliang.STORY #30646 【云平台】万联证券关联机构设置增加外包服务机构类型
						 * */
						for(int i = 0; i < paraAmount; i++){ 
							pstmt.setArray(index,array);
							index++;
						}
						pstmt.setString(index,String.valueOf(paraMap.get(valueFieldName)));
					}
					else if (valueFieldName.startsWith("ARRAY_")) {
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
			IVocCacheDataService vocCacheService = YssServiceFactory.getInstance().createServiceByImplClass(IVocCacheDataService.class, "com.yss.fast.atomicdata.dict.service.impl.VocCacheDataService");
			List<com.yss.platform.support.dataservice.pojo.dict.Vocabulary> list = vocCacheService.getDataListByTypes(new String[]{"RELA_ORG_TYPE"});        
			
			HashMap<String, String> map =new HashMap<String, String>();
			for (com.yss.platform.support.dataservice.pojo.dict.Vocabulary vocabulary : list) {
				map.put(vocabulary.getC_DV_CODE(), vocabulary.getC_DV_NAME());
			}
			
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(new Org());
			while (rs.next()) {
				//BasePojo t = setResultSet(rsTools,rs, clazz);
				//getConvertKey(PojoUtils.getPropertyDescriptors(t),t);
				BasePojo t = rsTools.ResultToBean(rs, clazz, props);
				getConvertKey(props,t);
				setQualification(map, t);
				pojoList.add(t);
			}

		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("关联机构设置：根据条件查询机构信息失败", ex);
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return pojoList;
	}
	
	@Override
	public List<BasePojo> queryByCondition(HashMap<String, Object> paraMap, Class<?> clazz) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		List<String> paraNameList;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";

		// Object resValue = null;
		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			paraNameList = getParaName(paraMap);
			
			//BUG #185160 机构基本信息：以主体资质为查询条件查询报错FAST1.3.3前台较1.3版本多传递了一个参数N_AUTHORG，为不影响下面处理逻辑此处直接移除。
			if (paraNameList.contains("N_AUTHORG")) {
				paraNameList.remove("N_AUTHORG");
			}
			
			conn = this.loadNewConnection();
			// conn.setAutoCommit(false);

			sql = sqlbuilder.getQueryConditionSql(paraNameList);
			sql = ((OrgSqlBuilder)sqlbuilder).getQueryConditionSql(sql);
			pstmt = conn.prepareStatement(sql);

			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}
			//// 如果SQL中有问号时才进行赋值
			if (sql.indexOf("?") > -1) {
				//获取该类型参数问号数量用于循环赋值 mzy 20170411
				int paraAmount = sql.split("\\?").length-1-(paraNameList.size()-1) -1;
				
				if(paraNameList.contains("N_AUTHORG")){
					paraAmount = paraAmount + 1;
				}
				
				int index = 1;
				Object paraValue;
				for (String valueFieldName : paraNameList) {
					if ("N_CHECK_STATE".equals(valueFieldName)) {
						continue;
					}
					
					if ("N_AUTHORG".equals(valueFieldName)) {
						continue;
					}
					
					if(valueFieldName.equals("ARRAY_C_QUALIFICATION")){
						Array array = OraDbTool.newInstance().sqlOverLongCondition(String
								.valueOf(paraMap.get(valueFieldName)),conn);
						//// 参数个数有修改，index值未变，导致根据机构资质查询报错，
						/*
						 * Author : HeLiang
						 * Date   : 2016-07-27
						 * Status : Modify
						 * Comment: 增加“外包服务机构”到机构资质中，添加一个index:12->13
						 * modified by heliang.STORY #30646 【云平台】万联证券关联机构设置增加外包服务机构类型
						 * */
						for(int i = 0; i < paraAmount; i++){
							pstmt.setArray(index,array);
							index++;
						}
						pstmt.setString(index,String.valueOf(paraMap.get(valueFieldName)));
					}
					else if (valueFieldName.startsWith("ARRAY_")) {
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
			IVocCacheDataService vocCacheService = YssServiceFactory.getInstance().createServiceByImplClass(IVocCacheDataService.class, "com.yss.fast.atomicdata.dict.service.impl.VocCacheDataService");
			List<com.yss.platform.support.dataservice.pojo.dict.Vocabulary> list = vocCacheService.getDataListByTypes(new String[]{"RELA_ORG_TYPE"});        
			
			HashMap<String, String> map =new HashMap<String, String>();
			for (com.yss.platform.support.dataservice.pojo.dict.Vocabulary vocabulary : list) {
				map.put(vocabulary.getC_DV_CODE(), vocabulary.getC_DV_NAME());
			}
			
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors((BasePojo)clazz.newInstance());
			while (rs.next()) {
				//BasePojo t = setResultSet(rsTools,rs, clazz);
				//getConvertKey(PojoUtils.getPropertyDescriptors(t),t);
				BasePojo t = rsTools.ResultToBean(rs, clazz, props);
				getConvertKey(props,t);
				setQualification(map, t);
				pojoList.add(t);
			}

		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("关联机构设置：根据条件查询机构信息失败", ex);
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return pojoList;
	}
	
	@Override
	public int queryByConditionCount(HashMap<String, Object> paraMap) {
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
			sql = sqlbuilder.getQueryConditionCountSql(paraNameList);

			pstmt = conn.prepareStatement(sql);

			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}

			//// 如果SQL中有问号时才进行赋值
			if (sql.indexOf("?") > -1) {
				//获取该类型参数问号数量用于循环赋值 mzy 20170411
				int paraAmount = sql.split("\\?").length-1-(paraNameList.size()-1)-1;
				int index = 1;
				Object paraValue = null;
				for (String valueFieldName : paraNameList) {
					if ("N_CHECK_STATE".equals(valueFieldName)) {
						continue;
					}
					
					if ("N_AUTHORG".equals(valueFieldName)) {
						continue;
					}
					if(valueFieldName.equals("ARRAY_C_QUALIFICATION")){
						Array array = OraDbTool.newInstance().sqlOverLongCondition(String
								.valueOf(paraMap.get(valueFieldName)),conn);
						//// 参数个数有修改，index值未变，导致根据机构资质查询报错，
						/*
						 * Author : HeLiang
						 * Date   : 2016-07-27
						 * Status : Modify
						 * Comment: 增加“外包服务机构”到机构资质中，添加一个index:12->13
						 * modified by heliang.STORY #30646 【云平台】万联证券关联机构设置增加外包服务机构类型
						 * */
						////20161210 added by mzyBUG #147121 【云平台万联证券】主体信息设置，资质为外包服务机构的，无法关联到组合 
						for (int i = 0; i < paraAmount; i++) {
							pstmt.setArray(index, array);
							index++;
						}
						pstmt.setString(index,String.valueOf(paraMap.get(valueFieldName)));
					}
					else if (valueFieldName.startsWith("ARRAY_")) {
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
//			ex.printStackTrace();
			logger.log("关联机构设置：根据条件查询机构信息记录数失败", ex);
			throw new DataAccessException("条件查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return recCount;
	}
	
	/**
	 * 设置机构资质
	 * @param map 值替换MAP
	 * @param t 机构实体对象
	 */
	private void setQualification(HashMap<String, String> map, BasePojo t){
		StringBuffer buf = new StringBuffer();
		try{
			Org org = (Org)t;

			if(map.containsKey(org.getC_DV_MANAGER())){
				buf.append(map.get(org.getC_DV_MANAGER())).append("|");
			}
			
			//STORY #32976 嘉实基金-管理人信息区分实际管理人和名义管理人 add by shijian 2016-09-26
			if(map.containsKey(org.getC_DV_MANAGER_SEC())){
				buf.append(map.get(org.getC_DV_MANAGER_SEC())).append("|");
			}
			
			if(map.containsKey(org.getC_DV_TRUSTEE())){
				buf.append(map.get(org.getC_DV_TRUSTEE())).append("|");
			}
			
			if(map.containsKey(org.getC_DV_TRUSTEE_SEC())){
				buf.append(map.get(org.getC_DV_TRUSTEE_SEC())).append("|");
			}
			
			if(map.containsKey(org.getC_DV_WARRANTOR())){
				buf.append(map.get(org.getC_DV_WARRANTOR())).append("|");
			}
			
			if(map.containsKey(org.getC_DV_INVEST_ADVISER())){
				buf.append(map.get(org.getC_DV_INVEST_ADVISER())).append("|");
			}
			
			if(map.containsKey(org.getC_DV_TRUSTEE_XT())){
				buf.append(map.get(org.getC_DV_TRUSTEE_XT())).append("|");
			}
			
			if(map.containsKey(org.getC_DV_SALES_CHANNELS())){
				buf.append(map.get(org.getC_DV_SALES_CHANNELS())).append("|");
			}
			
			if(map.containsKey(org.getC_DV_CLEARING_MEMBER())){
				buf.append(map.get(org.getC_DV_CLEARING_MEMBER())).append("|");
			}
			
			if(map.containsKey(org.getC_DV_BX_CLIENT())){
				buf.append(map.get(org.getC_DV_BX_CLIENT())).append("|");
			}
			
			if(map.containsKey(org.getC_DV_TRD_CLIENT())){
				buf.append(map.get(org.getC_DV_TRD_CLIENT())).append("|");
			}
			
			if(map.containsKey(org.getC_DV_CONSIGNER())){
				buf.append(map.get(org.getC_DV_CONSIGNER())).append("|");
			}
			
			if(map.containsKey(org.getC_DV_DEPOSITARY())){
				buf.append(map.get(org.getC_DV_DEPOSITARY())).append("|");
			}
			
			/**Start 20160330 added by liubo.STORY #29359 【中行】交易对手方、评级机构的维护*/
			//发行人
			if(map.containsKey(org.getC_DV_ISSUER())){
				buf.append(map.get(org.getC_DV_ISSUER())).append("|");
			}
			//对手方
			if(map.containsKey(org.getC_DV_COUNTERPARTY())){
				buf.append(map.get(org.getC_DV_COUNTERPARTY())).append("|");
			}
			//StringUtil.delLastSplitMark(buf, "|");
			/**End 20160330 added by liubo.STORY #29359 【中行】交易对手方、评级机构的维护*/
			
			/**Start 20160727 added by heliang.STORY #30646 【云平台】万联证券关联机构设置增加外包服务机构类型*/
			//外包服务机构
			if(map.containsKey(org.getC_DV_WBFWJG())){
				buf.append(map.get(org.getC_DV_WBFWJG())).append("|");
			}

			//20161210 added by mzyBUG #147121 【云平台万联证券】主体信息设置，资质为外包服务机构的，无法关联到组合 
			if (map.containsKey(org.getC_DV_TRUSTEE_MA())) {
				buf.append(map.get(org.getC_DV_TRUSTEE_MA())).append("|");
			}
			
			/**
			 *  20170113 added by HeLiang STORY #37662 光大银行新增自定义资产情况报表
			 *  营销机构
			 */
			if (map.containsKey(org.getC_DV_MARKETING())) {
				buf.append(map.get(org.getC_DV_MARKETING())).append("|");
			}
			
			if(map.containsKey(org.getC_ELEC_RECONCILIATION())){
				buf.append(map.get(org.getC_ELEC_RECONCILIATION())).append("|");
			}
			
			/**
			 * 20170804 chenbo STORY #44886 【基础信息组件】 机构信息设置主体资质属性系统存储机制优化
			 */
			if ( null != org.getC_DV_SUM() && org.getC_DV_SUM().trim().length() > 0 ) {			
				List<String> st=new ArrayList<String>();
				String[] str = org.getC_DV_SUM().split(",");
				for(String s1 : str){
						st.add(map.get(s1));
				}
				if(buf.length()>0){
					str=buf.toString().split("\\|");
					buf.delete(0, buf.length());
					for(String s2 : str){
						if(!st.contains(s2)){
							st.add(s2);
						}					
					}
				}
					for (String key : st) {						
							buf.append(key).append("|");					
					}

				
				}

			
			StringUtil.delLastSplitMark(buf, "|");
			/**End 20160727 added by heliang.STORY #30646 【云平台】万联证券关联机构设置增加外包服务机构类型*/
			
			org.setC_QUALIFICATION(buf.toString().trim());
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("关联机构设置：设置机构资质失败", ex);
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			buf = null;
		}
	}
	
	/**
	 * 根据交易对手方(关联机构名称)查询对应的关联机构信息 
	 * BUG #105790 宏源证券：银行间API回购交易清算中交易渠道未取得bug 
	 * @param counterpartyName 对手方名称（对应系统管理机构名称）
	 * @return
	 */
	public BasePojo getDataByCounterpartyName(String counterpartyName){
		String sql = "";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BasePojo pojo = null;
		try {
			ResultSetTools rsTools = null;
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			sql = "select a.* from T_P_BI_ORG a where a.n_check_state = 1 and a.c_org_name = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, counterpartyName);

			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				pojo = rsTools.ResultToBeanGeneric(rs, Org.class);
			}
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("关联机构设置：根据交易对手方(关联机构名称)查询对应的关联机构信息出错 ", ex);
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		
		return pojo;
	}
	
	/**
	 * 根据转换字典获取渠道代码
	 * @param counterpartyName
	 * @return
	 */
	/*分布式部署拆分，此部分代码没有被使用，注释掉
	public String getChanCodeByCounterpartyName(String counterpartyName){
		String chanCode = "";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer buf = new StringBuffer();
		try{
//			ResultSetTools rsTools = null;
//			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			buf.append(" select distinct a.c_s_code, a.c_t_code from (SELECT B.*, c.c_dv_scene FROM T_V_D_GROUP_DETAIL B ");
			buf.append(" JOIN (SELECT C_GROUP_CODE_P, C_GROUP_CODE, A.c_dv_scene  FROM T_V_D_GROUP A START WITH A.C_GROUP_CODE_P = '[root]' ");
			buf.append(" AND A.C_GROUP_CODE = 'EXAPI_PARTY2CHAN' connect by prior a.C_GROUP_CODE = a.C_GROUP_CODE_P) C ");
			buf.append(" ON B.C_GROUP_CODE = C.C_GROUP_CODE where B.N_CHECK_STATE >= 0 ORDER BY N_CHECK_STATE, B.C_S_CODE) a ");
			buf.append(" join t_d_od_api b on  a.c_s_code  = b.bpartynm   where a.c_s_code = ? ");
			pstmt = conn.prepareStatement(buf.toString());
			pstmt.setString(1, counterpartyName);
			
			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);
			
			while(rs.next()){
				chanCode = rs.getString("c_t_code");
			}
			
		}catch(Exception ex){
			logger.log("根据交易对手方(关联机构名称)去转换字典查询对应的关联机构信息出错 ", ex);
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
			
		}finally{
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
			
		}
		
		
		
		return chanCode;
	}*/
	
	/**
	 * 获取机构信息表中最大的机构代码，通过正则表达式，只获取数值类型的机构代码
	 * BUG #105790 宏源证券：银行间API回购交易清算中交易渠道未取得bug 
	 * @return 返回最大机构代码
	 */
	public String getMaxOrgCode(){
		String orgCode = "";
		StringBuffer bufSql = new StringBuffer();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			/*bufSql.append(" select max(to_number(substr(a.c_org_code,5))) as c_org_code ");
			bufSql.append(" from t_p_bi_org a ");
			bufSql.append(" where substr(a.c_org_code,1,4) = 'GLJG' and regexp_like(substr(a.c_org_code,5), '^[[:digit:]]+$')");
			*/
			bufSql.append(" select c_org_code														");
			bufSql.append("   from (select to_number(substr(a.c_org_code, 5)) c_org_code            ");
			bufSql.append("           from t_p_bi_org a                                             ");
			bufSql.append("          where substr(a.c_org_code, 1, 4) = 'GLJG'                      ");
			bufSql.append("            and regexp_like(substr(a.c_org_code, 5), '^[[:digit:]]+$')   ");
			bufSql.append("          order by to_number(substr(a.c_org_code, 5)) desc ) tab         ");
			bufSql.append("  where rownum <= 1                                                      ");
			
			
			
			pstmt = conn.prepareStatement(bufSql.toString());
			
			logger.debug(bufSql.toString());

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				orgCode = rs.getString("c_org_code");
			}
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("关联机构设置：获取机构信息表中最大的机构代码出错 ", ex);
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		
		return orgCode;
	}
	
	/**
	 * 根据付款账号获得所有的父级 机构代码
	 * @return 机构代码列表
	 */
	public List<String> getOrgCodebyAccNo(String accNo) {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		List<String> orgList = new ArrayList<String>();
		try {
			conn = this.loadNewConnection();
			stat = conn.prepareStatement(((OrgSqlBuilder) sqlbuilder).getOrgSqlByAccNo());
			stat.setString(1, accNo);
			rs = stat.executeQuery();
			while (rs.next()) {
				orgList.add(rs.getString("c_org_code"));
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			logger.log("关联机构设置：根据条件获取机构代码出错 ", e);
		} catch (Exception e) {
//			e.printStackTrace();
			logger.log("关联机构设置：根据条件获取机构代码出错 ", e);
		
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(stat);
			this.releaseConnection(conn);
		}
		return orgList;
	}
	
	/* END 数据服务方法 */
	
	/**
	 * 删除机构同时删除机构信息关联表
	 * @param orgCode
	 * @return
	 */
//	public List<BasePojo> searchRela(String orgCode) {
//		List<BasePojo> list = new ArrayList<BasePojo>();
//		Connection conn = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		OrgRela pojo = null;
//		StringBuffer bufSql = new StringBuffer();
//		ResultSetTools tool = new ResultSetTools(dbNameResolver,new OrgRelaSqlBuilder());
//		try{
//			conn = loadNewConnection();
//			bufSql.append(" select t.* ");
//			bufSql.append(" from T_P_BI_ORG_RELA t ");
//			bufSql.append(" where t.C_ORG_CODE = ? ");
//			String sql = bufSql.toString();
//			ps = conn.prepareStatement(sql);
//			ps.setString(1, orgCode);
//			rs = ps.executeQuery();
//			while(rs.next()){				
//				pojo = tool.ResultToBeanGeneric(rs, OrgRela.class);	
//				list.add(pojo);
//			}
//		}catch(Exception ex){
//			throw new DataAccessException(ex);
//		}finally{
//			closeResultSetFinal(rs);
//			closeStatementFinal(ps);
//			releaseConnection(conn);
//		}
//		return list;
//	}

	/**
	 * 更新缓存
	 * 
	 * @author liuxiang
	 * @date 2016年2月26日 STORY #28246 【自动化款指令】及【划款指令管理】模块界面问题优化
	 * @param timestamp
	 *            时间戳
	 * @return
	 */
	public List<BasePojo> getDataListByTimestamp(String timestamp) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuffer buf = new StringBuffer();
		String sql = "";
		ResultSetTools rsTools = null;

		Org t = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, orgSqlBuilder);

			conn = this.loadNewConnection();

			buf.append(" select * from t_p_bi_org a");
			buf.append(" where a.N_CHECK_STATE = 1 and  ");
			buf.append(" TO_DATE(trim(a.C_CHECK_TIME),'yyyy-MM-dd hh24:mi:ss')");
			buf.append(" >= to_date(?,'yyyy-MM-dd hh24:mi:ss') ");
			sql = buf.toString();

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, timestamp);

			rs = pstmt.executeQuery();

			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(new Org());
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, Org.class, props);
				pojoList.add(t);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
			StringUtil.clearStringBuffer(buf);
		}

		return pojoList;
	}
	
	/**
	 * Author : ChenLong
	 * Date   : 2016-09-22
	 * Status : Add
	 * Comment: 获取委托人组织机构
	 * @return
	 */
	public List<Org> getOrgByConsignerType(){
		List<Org> list = new ArrayList<Org>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			String sql = "select c_org_code,c_org_name from t_p_bi_org where c_dv_consigner = 'CONSIGNER'";
			conn = this.loadNewConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Org org = new Org();
				org.setC_ORG_CODE(rs.getString("c_org_code"));
				org.setC_ORG_NAME(rs.getString("c_org_name"));
				list.add(org);
			}
		}catch(Exception ex){
			throw new BusinessException("查询委托人机构失败!",ex);
		}finally{
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return list;
	}
	
	/** 
	 * @Title clearFaxNo 
	 * @Description 该方法用于删除机构关联的传真号码
	 * @author liminghong@ysstech.com
	 * @date 2016年12月13日下午5:36:16
	 * @param orgCode 机构代码
	 * @return 成功-Success失败-Failed
	 */
//	public String clearFaxNo(String orgCode) {
//		String result = "Success";
//		Connection conn = null;
//		PreparedStatement ps = null;
//		StringBuffer bufSql = new StringBuffer();
//		try{
//			conn = loadNewConnection();
//			// 删除机构关联的传真号码
//			bufSql.append("delete from t_p_bi_fax where c_assist_code in ( ");
//			bufSql.append(" select c_rela_code from t_p_bi_org_rela where c_org_code = ? and c_rela_type='fax') ");
//			ps = conn.prepareStatement(bufSql.toString());
//			ps.setString(1, orgCode);
//			ps.executeUpdate();
//			conn.commit();
//		}catch(Exception ex){
//			result = "Failed";
//			logger.log("删除机构关联传真失败", ex);
//			throw new DataAccessException(ex);
//		}finally{
//			closeStatementFinal(ps);
//			releaseConnection(conn);
//		}
//		return result;
//	}

	/**
	 * 通过查询条件查询数据
	 * 
	 * @author shijian
	 * @date 2016年10月26日  STORY #35056 嘉实基金--成交清算日报表--增加名义管理人等字段
	 * @param condition
	 *            查询条件
	 * @return
	 */
	public List<BasePojo> getDataListByCondition(String condition) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuffer buf = new StringBuffer();
		String sql = "";
		ResultSetTools rsTools = null;

		Org t = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, orgSqlBuilder);

			conn = this.loadNewConnection();

			buf.append(" select * from t_p_bi_org ");
			buf.append(" where N_CHECK_STATE = 1 ");
			
			if(condition!=null&&!condition.trim().isEmpty())
			{
				buf.append(" and ");
				buf.append(condition);
			}
			
			
			sql = buf.toString();

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(new Org());
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, Org.class, props);
				pojoList.add(t);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
			StringUtil.clearStringBuffer(buf);
		}

		return pojoList;
	}

	public List<Org> getAllBankHead() {
		List<Org> pojoList = new ArrayList<Org>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		OrgSqlBuilder dsServiceBuilder = null;
		Org t = null;
		try {
			dsServiceBuilder = new OrgSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = dsServiceBuilder.getAllDataSql();
			sql += " and b.c_org_code_p is null and b.C_DV_ORG_TYPE ='ORG_SYYH'";
			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(new Org());
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, Org.class, props);
				
				pojoList.add(t);
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

	public List<Org> getBankBranchByHead(String[] param) {
		List<Org> pojoList = new ArrayList<Org>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		OrgSqlBuilder dsServiceBuilder = null;
		Org t = null;
		try {
			dsServiceBuilder = new OrgSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = dsServiceBuilder.getAllDataSql();
			sql += " AND b.c_org_code_p IN (SELECT * FROM TABLE(?)) ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(param,conn));
			
			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(new Org());
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, Org.class, props);
				pojoList.add(t);
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
	
	public Map<String, String> insert(String c_Org_Code){
		Map<String, String> resMap = new HashMap<String, String>();
		String c_Org_Name = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try{
			if(c_Org_Code == null || c_Org_Code.trim().length() == 0){
				return resMap;
			}
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			String sql = "select * from T_P_BI_ORG where c_org_Code = ? or c_org_name = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c_Org_Code);
			pstmt.setString(2, c_Org_Code);
			rs = pstmt.executeQuery();
			if(!rs.next()){//当机构代码不存在则机构代码为机构名称
				Org org = new Org();
				c_Org_Name = c_Org_Code;
//				IFundApplyDataService fundApplyDataService = YssServiceFactory.getInstance().createService(IFundApplyDataService.class);
//				c_Org_Code = fundApplyDataService.generateMaxOrgCode();
				org.setC_ORG_CODE(c_Org_Code);//机构代码
				org.setC_ORG_NAME(c_Org_Name);//机构名称
				org.setC_DV_ORG_TYPE("ORG_QT");//机构类型
				org.setModifier(ContextFactory.getContext().getUserCode());
				org.setModifyDate(DateUtil.getNow(MvcConstant._DATA_STD_DATE_FORMAT));
				org.setAuditState(YssConstant.STATE_AUDIT); // 设置已审核
				org.setOperator(ContextFactory.getContext().getUserCode()); // 设置已审核的用户
				org.setAuditDate(DateUtil.getNow(MvcConstant._DATA_STD_DATE_FORMAT));
				OrgDao orgDao = new OrgDao(DbPoolFactory.getInstance().getPool(), new OrgSqlBuilder());
				orgDao.insert(org, conn);
				conn.commit();
				conn.setAutoCommit(true);
			}else{
				c_Org_Name = rs.getString("c_org_name");
				c_Org_Code = rs.getString("c_org_code");
			}
			resMap.put("C_ORG_CODE", c_Org_Code);
			resMap.put("C_ORG_NAME", c_Org_Name);
		}catch(Exception ex){
			throw new DataAccessException("生成关联机构失败！" + ex.getMessage(),ex);
		}finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return resMap;
	}

	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T>  getDataListByAptitude(String[] types) {
		List<Org> pojoList = new ArrayList<Org>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ResultSetTools rsTools = null;

		OrgSqlBuilder orgSqlBuilder = null;
		Org t = null;
		try {
			orgSqlBuilder = new OrgSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, orgSqlBuilder);

			conn = this.loadNewConnection();

			pstmt = conn.prepareStatement(orgSqlBuilder.getDataListByAptitude(types));
			rs = pstmt.executeQuery();

			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(new Org());
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, Org.class, props);
				pojoList.add(t);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return (List<T>) pojoList;
	}

	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getParentListByTypes(String[] types) {
		List<Org> pojoList = new ArrayList<Org>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		OrgSqlBuilder dsServiceBuilder = null;
		Org t = null;
		try {
			dsServiceBuilder = new OrgSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();

			sql = dsServiceBuilder.getParentListByTypes();

			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(types,conn));
			
			logger.debug(sql);

			rs = pstmt.executeQuery();

			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(new Org());
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, Org.class, props);
				pojoList.add(t);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return (List<T>) pojoList;
	}
	
	/**
	 * 查询数据量
	 * @return
	 */
	public int getDataListCount(){
		Connection conn=null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		OrgSqlBuilder dsServiceBuilder = null;
		int count = 0;
		try {
			dsServiceBuilder = new OrgSqlBuilder();
			String sql = dsServiceBuilder.getDataListCount();
			
			conn = this.loadNewConnection();
			pst = conn.prepareStatement(sql);
			
			rs = pst.executeQuery();
			
			if(rs.next()){
				count = rs.getInt(1);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pst);
			releaseConnection(conn);
		}
		
		
		return count;
	}

	public int getUpdateByTimestampCount(String timestamp){
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		OrgSqlBuilder dsServiceBuilder = null;
		int count = 0;
		try {
			dsServiceBuilder = new OrgSqlBuilder();
			conn = this.loadNewConnection();
			String sql = dsServiceBuilder.getDataListByTimestampCount();
			pst = conn.prepareStatement(sql);
			pst.setString(1, YssFun.formatDate(YssFun.parseDate(timestamp,"yyyy-MM-dd HH:mm:ss"), "yyyyMMdd HH:mm:ss"));

			
			rs = pst.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally{
			closeResultSetFinal(rs);
			closeStatementFinal(pst);
			releaseConnection(conn);
		}
		
		return count;
	}

	public List<Org> getDataListPage(PageInation page) {
		List<Org> pojoList = new ArrayList<Org>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		OrgSqlBuilder dsServiceBuilder = null;
		Org t = null;
		try {
			dsServiceBuilder = new OrgSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();

			sql = dsServiceBuilder.getAllDataSql();
			sql = buildPagingSql(sql, page);

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(new Org());
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, Org.class, props);
				pojoList.add(t);
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

	public List<BasePojo> getDataListByTimestampPage(String timestamp, PageInation page) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		OrgSqlBuilder dsServiceBuilder = null;
		Org t = null;
		try {
			dsServiceBuilder = new OrgSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);
			conn = this.loadNewConnection();

			sql = dsServiceBuilder.getDataListByTimestamp();
			sql = buildPagingSql(sql, page);

			pstmt = conn.prepareStatement(sql);
			
//			pstmt.setString(1, timestamp);
			//// By Jinghehe 2017-12-07 BUG #179190 性能问题-21.5版本证券、科目缓存更新存在性能问题 
			pstmt.setString(1, YssFun.formatDate(YssFun.parseDate(timestamp,"yyyy-MM-dd HH:mm:ss"), "yyyyMMdd HH:mm:ss"));
		

			rs = pstmt.executeQuery();

			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(new Org());
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, Org.class, props);
				pojoList.add(t);
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
	 * 先删除相同的数据，再插入
	 * @param org
	 */
	public void deleteThenInsert(Org org) {
		Connection conn = null;
		PreparedStatement pst = null;
		String deleteSql = orgSqlBuilder.getDeleteSql();
		try {
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			pst = conn.prepareStatement(deleteSql);
			pst.setString(1, org.getId());
			pst.setString(2, org.getC_ORG_CODE());
			pst.executeUpdate();
			insert(org, conn);
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				//e1.printStackTrace();
				logger.error("先删除后插入数据失败：" + e.getMessage(), e);
			}
			throw new DataAccessException("先删除后插入数据失败：" + e.getMessage(), e);
		} finally {
			closeStatementFinal(pst);
			releaseConnection(conn);
		}
	}
	
	public String getOneManagerFrom(String portCodes) throws DataAccessException {
		StringBuffer buf = new StringBuffer(); 
		String manager = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		buf.append(" select o.c_org_name ");
		buf.append(" from t_p_ab_port p ");
		buf.append(" left join T_P_AB_PORT_RELA r ");
		buf.append(" on p.c_port_code = r.c_port_code ");
		buf.append(" left join t_p_bi_org o ");
		buf.append(" on r.c_rela_code = o.c_org_code ");
		buf.append(" where r.c_dv_type_code = 'MANAGER' and r.n_check_state = 1 and p.c_port_code in (select * from table(?)) ");
		
		try{
			conn = this.loadNewConnection();
			pstmt = conn.prepareStatement(buf.toString());
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(portCodes,conn));
			rs = pstmt.executeQuery();
			while(rs.next() && manager == null){
				manager = rs.getString("c_org_name");
			}
			manager = manager == null? "":manager;
		}catch(Exception ex){
			logger.error("查询失败："+ex.getMessage(), ex);
			throw new DataAccessException(ex);
		}finally{
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return manager;
	}

	public List<Map<String, String>> getCacodes() throws DataAccessException {
		List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.C_IDEN, C_MBR_CODE, a.C_ORG_CODE, a.C_ACC_CODE,b.c_org_name , a.C_CA_CODE,a.C_CA_NAME,");
		sql.append(" a.D_BEGIN,D_END, a.C_DESC, a.N_CHECK_STATE, a.C_UPDATE_BY, a.C_UPDATE_TIME, a.C_CHECK_BY, a.C_CHECK_TIME, a.C_BROKER_CODE ");
		sql.append(" from T_P_BI_ORG_MBR a left join T_P_BI_ORG b ");
		sql.append(" on b.c_org_code=a.c_org_code ");
		sql.append(" where a.n_check_state=1 and b.n_check_state=1 ");
		sql.append(" order by b.c_org_code");
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = this.loadNewConnection();
			pst = conn.prepareStatement(sql.toString());
			rs = pst.executeQuery();
			while(rs.next()){
				Map<String, String> org = new HashMap<String, String>();
				org.put("C_BROKER_CODE", rs.getString("C_BROKER_CODE"));
				org.put("C_MBR_CODE", rs.getString("C_MBR_CODE"));
				org.put("C_ORG_CODE", rs.getString("C_ORG_CODE"));
				org.put("C_ACC_CODE", rs.getString("C_ACC_CODE"));
				org.put("C_ORG_NAME", rs.getString("C_ORG_NAME"));
				org.put("C_CA_CODE", rs.getString("C_CA_CODE"));
				org.put("C_CA_NAME", rs.getString("C_CA_NAME"));
				listMap.add(org);
			}
		} catch (Exception e) {
			logger.error("查询失败："+e.getMessage(), e);
			throw new DataAccessException(e);
		}finally{
			closeResultSetFinal(rs);
			closeStatementFinal(pst);
			releaseConnection(conn);
		}
		return listMap;
	}
	
	/**
	 * 获取组合的管理人名称
	 * STORY #70280 add by xiadeqi 2019-3-20 
	 * @param portCode
	 * @return
	 */
	public String getManagerNameByPortCode(String portCode) {
		String portManagerName = null;
		Connection conn = null;
		PreparedStatement pst = null;
		String sql = "";
		ResultSet rs = null;
		try {
			conn = this.loadNewConnection();
			sql = this.orgSqlBuilder.getManagerNameByPortCode();
			pst = conn.prepareStatement(sql);
			pst.setString(1, portCode);
		
			rs = pst.executeQuery();
			while(rs.next()){
				portManagerName = rs.getString("C_ORG_NAME");
			}
		} catch (Exception e) {
			logger.log("关联机构设置：根据组合代码获取组合的管理人名称失败", e);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return portManagerName;
	}
	/**
	 * Author : zuomingke
	 * Date   : 2019-07-04
	 * Status : Add
	 * Comment:  根据机构代码查询关联组合代码
	 * @return 
	 */
	public List<String> queryPortCodeListByRelaOrgCode(String orgCode) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> portList = new ArrayList<String>();
		try{
			conn = this.loadNewConnection();
			String sql = orgSqlBuilder.getPortByOrgCodeSql();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, orgCode);
			rs = pstmt.executeQuery();
			while(rs.next()){
				portList.add(rs.getString("c_port_code"));
			}
		}catch(Exception ex){
			throw new BusinessException("根据机构代码获取关联组合代码失败!" + ex.getMessage(),ex);
		}finally{
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return portList;
	}
	
	/**
	 * Author : zuomignke
	 * Date   : 2019-07-08
	 * Status : Add
	 * Comment: 根据机构代码获取机构list
	 * @param shAccCodes
	 * @return 
	 */
	public List<Org> getOrgListByOrgCodes(String orgCodes) {
		List<Org> pojoList = new ArrayList<Org>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetTools rsTools = null;
		String sql = "";

		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			conn = this.loadNewConnection();
			if(StringUtil.IsNullOrEmptyT(orgCodes)){
				sql = "SELECT * FROM T_P_BI_ORG A ";
			}else{
				sql = "SELECT * FROM T_P_BI_ORG A WHERE A.C_ORG_CODE IN (SELECT * FROM TABLE(?)) ";
			}
			pstmt = conn.prepareStatement(sql);
			if(!StringUtil.IsNullOrEmptyT(orgCodes)){
				pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(orgCodes,conn));
			}
			rs = pstmt.executeQuery();
			Org t = null;
			while (rs.next()) {
				t = (Org) rsTools.ResultToBean(rs, Org.class);
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
	
	public List<Org> queryOrgByPort(String portCode,String c_dv_type) {
		List<Org> pojoList = new ArrayList<Org>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = this.loadNewConnection();
			String sql = orgSqlBuilder.queryOrgByPortSql(c_dv_type);
			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(portCode,conn));
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Org pojo = new Org();
				pojo.setC_ORG_CODE(rs.getString("C_ORG_CODE"));
				pojoList.add(pojo);
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
     * 获取机构关联联系人信息
     * STORY #81326 【华宝兴业】关联结构设置 中联系信息维护调整
     * @param orgCode
     * @return
     */
    public Map<String, Org> getOrgLinkManData(String orgCode) {
    	Map<String, Org> orgMap = new HashMap<String, Org>();
        PreparedStatement pst = null;
        Connection conn = null;
        ResultSet rs = null;
        String sql = "";
    	try {
    		boolean where = false;
    		if (orgCode != null && !"".equals(orgCode)) {
    			where = true;
    		}
    		conn = this.loadNewConnection();
    		sql = ((OrgSqlBuilder) sqlbuilder).getOrgLinkManDataSql(where);
            pst = conn.prepareStatement(sql);
            if (where) {
            	pst.setObject(1, orgCode);
            }
            rs = pst.executeQuery();
            while (rs.next()) {
            	Org org = new Org();
            	org.setC_LINK_MAN(rs.getString("C_LINK_MAN"));
            	org.setC_POST_NAME(rs.getString("C_POST_NAME"));
            	org.setC_LINK_TEL(rs.getString("C_LINK_TEL"));
            	org.setC_MO_TEL(rs.getString("C_MO_TEL"));
            	org.setC_EMAIL(rs.getString("C_EMAIL"));
            	orgMap.put(rs.getString("C_ORG_CODE"), org);
            }
		} catch (Exception e) {
			throw new DataAccessException("查询失败：" + e.getMessage(), e);
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
			DbFun.releaseConnection(conn);
		}
    	return orgMap;
    }
    
    /**
     * 获取机构关联联系人信息
     * STORY #81326 【华宝兴业】关联结构设置 中联系信息维护调整
     * @param orgCode
     * @return
     */
    public List<Org> getOrgLinkManList(String orgCode) {
    	List<Org> orgList = new ArrayList<Org>();
    	PreparedStatement pst = null;
        Connection conn = null;
        ResultSet rs = null;
        String sql = "";
    	try {
    		conn = this.loadNewConnection();
    		sql = ((OrgSqlBuilder) sqlbuilder).getOrgLinkManListSql();
            pst = conn.prepareStatement(sql);
            pst.setString(1, orgCode);
            rs = pst.executeQuery();
            while (rs.next()) {
            	Org org = new Org();
            	org.setC_ORG_CODE(rs.getString("C_ORG_CODE"));
            	org.setC_LINK_MAN(rs.getString("C_LINK_MAN"));
            	org.setC_POST_NAME(rs.getString("C_POST_NAME"));
            	org.setC_LINK_TEL(rs.getString("C_LINK_TEL"));
            	org.setC_MO_TEL(rs.getString("C_MO_TEL"));
            	org.setC_EMAIL(rs.getString("C_EMAIL"));
            	orgList.add(org);
            }
		} catch (Exception e) {
			throw new DataAccessException("查询失败：" + e.getMessage(), e);
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
			DbFun.releaseConnection(conn);
		}
    	return orgList;
    }
    
    /**
     * 更新机构关联联系人信息
     * STORY #81326 【华宝兴业】关联结构设置 中联系信息维护调整
     * @param orgCode
     * @param orgList
     */
    public void updateOrgLinkMan(String orgCode, List<Org> orgList) {
    	PreparedStatement pst = null;
        Connection conn = null;
        String sql = "";
    	try {
    		conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			// 删除数据
			sql = ((OrgSqlBuilder) sqlbuilder).deleteOrgLinkManSql();
            pst = conn.prepareStatement(sql);
            pst.setString(1, orgCode);
            pst.executeUpdate();
            DbFun.closeStatementFinal(pst);
            // 插入数据
            String userCode = ContextFactory.getContext().getUserCode();
            sql = ((OrgSqlBuilder) sqlbuilder).getInsertOrgLinkManSql();
            pst = conn.prepareStatement(sql);
            for (int i = 0; i < orgList.size(); i++) {
        		Org org = orgList.get(i);
        		String linkMan = org.getC_LINK_MAN();
        		String postName = org.getC_POST_NAME();
        		String linkTel = org.getC_LINK_TEL();
        		String moTel = org.getC_MO_TEL();
        		String email = org.getC_EMAIL();
        		String check = linkMan + postName + linkTel + moTel + email;
        		if (!"".equals(check.trim())) {
        			pst.setString(1, orgCode);
            		pst.setString(2, linkMan);
            		pst.setString(3, postName);
            		pst.setString(4, linkTel);
            		pst.setString(5, moTel);
            		pst.setString(6, email);
            		pst.setInt(7, i);;
            		pst.setString(8, userCode);
            		pst.addBatch();
        		}
        	}
            pst.executeBatch();
            conn.commit();
			conn.setAutoCommit(true);
    	} catch (Exception e) {
			throw new DataAccessException("插入失败：" + e.getMessage(), e);
		} finally {
			DbFun.closeStatementFinal(pst);
			DbFun.releaseConnection(conn);
		}
    }
    
    /**
     * 删除机构关联联系人信息
     * STORY #81326 【华宝兴业】关联结构设置 中联系信息维护调整
     * @param orgCode
     */
    public void deleteOrgLinkMan(String orgCode) {
    	PreparedStatement pst = null;
        Connection conn = null;
        String sql = "";
    	try {
    		conn = this.loadNewConnection();
			sql = ((OrgSqlBuilder) sqlbuilder).deleteOrgLinkManSql();
            pst = conn.prepareStatement(sql);
            pst.setString(1, orgCode);
            pst.executeUpdate();
    	} catch (Exception e) {
			throw new DataAccessException("删除失败：" + e.getMessage(), e);
		} finally {
			DbFun.closeStatementFinal(pst);
			DbFun.releaseConnection(conn);
		}
    }
}
