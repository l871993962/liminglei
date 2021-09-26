package com.yss.ams.base.information.modules.sys.secvar.dao;

import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.sys.secvar.pojo.SecVar;
//import com.yss.para.bi.secvar.pojo.SecVarExtend;
import com.yss.ams.base.information.support.sys.secvar.pojo.SecVarExtend;
//import com.yss.dayf.actProvider.dbUtils.DBUtils;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.IEffectivable;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.DaoAssistance;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.db.OraDbTool;
import com.yss.framework.util.DateUtil;
import com.yss.framework.util.PojoUtils;

//import dataservice.comm.pojo.SecVar;

/**
 * 证券品种字典T_S_DA_SEC_VAR dao
 *
 */
public class SecVarDao extends GeneralDao {

	private SecVarSqlBuilder secVarSqlBuilder;

	public SecVarDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		this.secVarSqlBuilder = (SecVarSqlBuilder) sqlBuilder;
	}

	/**
	 * 根据条件arg0 获取品种属性的父级代码 c_da_code_p为'[root]'的  证券品种字典视图V_S_DA_SEC_VAR的所有数据,
	 * 并进行分页
	 */
	@Override
	public List<BasePojo> queryByConditionRecyclePage(
			HashMap<String, Object> paraMap, PageInation page, Class<?> clazz) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		List<String> paraNameList;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";

		try {
			paraNameList = getParaName(paraMap);
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			sql = secVarSqlBuilder.getQueryConditionSql(paraNameList);
			sql = sql.replace(sqlbuilder.getTableName(dbNameResolver),
					sqlbuilder.getRecycleTableName(dbNameResolver));
			sql = buildPagingSql(sql, page);

			pstmt = conn.prepareStatement(sql);

			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}

			int index = 1;
			for (String valueFieldName : paraNameList) {
				pstmt.setObject(index, paraMap.get(valueFieldName));
				index++;
			}

			rs = pstmt.executeQuery();

			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				SecVarExtend secVarExtend = new SecVarExtend();
				PropertyDescriptor[] propertys = this
						.getPropertyDescriptors(secVarExtend);

				for (int i = 0; i < propertys.length; i++) {
					PropertyDescriptor prop = propertys[i];
					if (prop.getPropertyType().isAssignableFrom(Class.class)) {
						continue;
					}

					if ("c_USER_PWD".equals(prop.getName())) {
						continue;
					}

					String name = prop.getName();
					String columnName = this.secVarSqlBuilder
							.getColumnNameByPropertyExtend(dbNameResolver, name);
					Object resValue = rs.getObject(columnName);
					if (resValue != null) {
						if (resValue.getClass().equals(byte.class)
								|| resValue.getClass().equals(byte[].class)) {
							resValue = resValue.toString();
						} else if (resValue.getClass().equals(Timestamp.class)) {
							if (prop.getPropertyType().equals(String.class)) {
								Timestamp time = new Timestamp(DateUtil
										.stringtoDate(resValue.toString(),
												DateUtil.FORMAT_ONE).getTime());
								resValue = DateUtil.dateToString(time,
										"yyyy-MM-dd");
							}
							// YssFun.formatDate(resValue.toString());
						}

						if (int.class.equals(prop.getPropertyType())) {
							resValue = new BigDecimal(resValue.toString())
									.intValue();
						}

						prop.getWriteMethod().invoke(secVarExtend, resValue);
					}

				}

				pojoList.add(secVarExtend);
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

	/**
	 * 根据条件paraNameList 获取T_S_DA_SEC_ATTR和T_P_BI_SEC_VAR联合查询数据总数
	 * @param paraNameList 查询条件
	 * @return
	 * @throws Exception
	 */
	public int queryByConditionRecycleCount(HashMap<String, Object> paraMap) {
		List<String> paraNameList;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		int recCount = 0;
		try {
			paraNameList = getParaName(paraMap);
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			sql = this.secVarSqlBuilder
					.getQueryConditionExtendCountSql(paraNameList);

			pstmt = conn.prepareStatement(sql);

			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}

			int index = 1;
			for (String valueFieldName : paraNameList) {
				if ("N_CHECK_STATE".equals(valueFieldName)) {
					continue;
				}
				pstmt.setObject(index, paraMap.get(valueFieldName));
				index++;
			}

			rs = pstmt.executeQuery();

			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				recCount = rs.getInt("CNT");
			}

		} catch (Exception ex) {
			throw new DataAccessException("条件查询失败：" + ex.getMessage(), ex);
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

		return recCount;
	}

	/**
	 * 根据条件paraNameList 获取所有符合条件T_S_DA_SEC_ATTR和T_P_BI_SEC_VAR联合查询数据
	 * @param paraNameList 查询条件
	 * @return
	 * @throws Exception
	 */
	public List<BasePojo> querySecVarExtendDataList(
			HashMap<String, Object> paraMap, PageInation page) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		List<String> paraNameList;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";

		try {
			paraNameList = getParaName(paraMap);
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			sql = this.secVarSqlBuilder
					.getQueryConditionExtendSql(paraNameList);
			sql = buildPagingSql(sql, page);

			pstmt = conn.prepareStatement(sql);

			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}

			int index = 1;
			for (String valueFieldName : paraNameList) {
				pstmt.setObject(index, paraMap.get(valueFieldName));
				index++;
			}

			rs = pstmt.executeQuery();

			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				SecVarExtend secVarExtend = new SecVarExtend();
				PropertyDescriptor[] propertys = this
						.getPropertyDescriptors(secVarExtend);

				for (int i = 0; i < propertys.length; i++) {
					PropertyDescriptor prop = propertys[i];
					if (prop.getPropertyType().isAssignableFrom(Class.class)) {
						continue;
					}

					if ("c_USER_PWD".equals(prop.getName())) {
						continue;
					}

					String name = prop.getName();
					String columnName = this.secVarSqlBuilder
							.getColumnNameByPropertyExtend(dbNameResolver, name);
					if(columnName.equalsIgnoreCase("")){
						continue;
					}
					
					Object resValue = rs.getObject(columnName);
					if (resValue != null) {
						if (resValue.getClass().equals(byte.class)
								|| resValue.getClass().equals(byte[].class)) {
							resValue = resValue.toString();
						} else if (resValue.getClass().equals(Timestamp.class)) {
							if (prop.getPropertyType().equals(String.class)) {
								Timestamp time = new Timestamp(DateUtil
										.stringtoDate(resValue.toString(),
												DateUtil.FORMAT_ONE).getTime());
								resValue = DateUtil.dateToString(time,
										"yyyy-MM-dd");
							}
							// YssFun.formatDate(resValue.toString());
						}

						if (int.class.equals(prop.getPropertyType())) {
							resValue = new BigDecimal(resValue.toString())
									.intValue();
						}

						prop.getWriteMethod().invoke(secVarExtend, resValue);
					}

				}

				pojoList.add(secVarExtend);
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

	/**
	 * 根据条件paraNameList 获取T_S_DA_SEC_ATTR和T_P_BI_SEC_VAR联合查询数据总数
	 * @param paraNameList 查询条件
	 * @return
	 * @throws Exception
	 */
	public int querySecVarExtendDataListCount(HashMap<String, Object> paraMap) {
		List<String> paraNameList;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		int recCount = 0;
		try {
			paraNameList = getParaName(paraMap);
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			
			sql = this.secVarSqlBuilder
					.getQueryConditionExtendCountSql(paraNameList);
			sql = sql.replace(sqlbuilder.getTableName(dbNameResolver),
					sqlbuilder.getRecycleTableName(dbNameResolver));
			
			pstmt = conn.prepareStatement(sql);

			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}

			int index = 1;
			for (String valueFieldName : paraNameList) {
				if ("N_CHECK_STATE".equals(valueFieldName)) {
					continue;
				}
				pstmt.setObject(index, paraMap.get(valueFieldName));
				index++;
			}

			rs = pstmt.executeQuery();

			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				recCount = rs.getInt("CNT");
			}

		} catch (Exception ex) {
			throw new DataAccessException("条件查询失败：" + ex.getMessage(), ex);
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

		return recCount;
	}

	/* START 数据服务 */

	/**
	 * 获取 证券品种字典V_S_DA_SEC_VAR所有的数据
	 * @return
	 */
	public List<SecVarExtend> getAllDataList() throws ServiceException {
		List<SecVarExtend> pojoList = new ArrayList<SecVarExtend>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		SecVarSqlBuilder dsServiceBuilder = null;
		SecVarExtend t = null;
		try {
			dsServiceBuilder = new SecVarSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = dsServiceBuilder.getAllDataSql();

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			/*将获取PropertyDescriptor提出到滚动之外，提高rs滚动效率
			  2016-8-19 蒋锦 南方基金现场性能优化*/
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(SecVarExtend.class.newInstance());
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, SecVarExtend.class, props);
				t.setC_DA_CODE_P(rs.getString(SecVarExtendColumnName.c_DA_CODE_P.toString()));
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
	 * 根据 证券品种代码C_SEC_VAR_CODE 获取 一条证券品种字典V_S_DA_SEC_VAR数据
	 * @return
	 */
	public SecVar getDataByCode(String code) throws ServiceException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		SecVarSqlBuilder dsServiceBuilder = null;
		SecVar t = null;
		try {
			dsServiceBuilder = new SecVarSqlBuilder();
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

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, SecVar.class);
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
	 * 根据 证券属性代码 C_DA_CODE数组 获取 符合条件的证券品种字典V_S_DA_SEC_VAR的数据
	 * @return
	 */
	public List<SecVarExtend> getDataListByTypes(String[] types)
			throws ServiceException {
		List<SecVarExtend> pojoList = new ArrayList<SecVarExtend>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		//ResultSetTools rsTools = null;

		SecVarSqlBuilder dsServiceBuilder = null;
		SecVarExtend t = null;
		try {
			dsServiceBuilder = new SecVarSqlBuilder();
			//rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();

			sql = dsServiceBuilder.getDataListByTypes(types);

			pstmt = conn.prepareStatement(sql);
			int i = 1;
			for (String type : types) {
				if (type != null && type.trim().length() > 0) {
					pstmt.setString(i++, type + "%");
				}
			}

			logger.debug(sql);

			rs = pstmt.executeQuery();
			
			/*将获取PropertyDescriptor提出到滚动之外，提高rs滚动效率
			  2016-8-19 蒋锦 南方基金现场性能优化*/
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(SecVarExtend.class.newInstance());

			while (rs.next()) {
				t = ResultToBeanExtendGeneric(rs, SecVarExtend.class, props);
				t.setC_DA_CODE_P(rs.getString(SecVarExtendColumnName.c_DA_CODE_P.toString()));
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
	 * 获取 证券品种字典V_S_DA_SEC_VAR所有的数据（只包含证券品种代码 和证券品种名称）
	 * @return
	 */
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		HashMap<String, String> keyValueMap = new HashMap<String, String>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		SecVarSqlBuilder dsServiceBuilder = null;
		SecVar t = null;
		try {
			dsServiceBuilder = new SecVarSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = dsServiceBuilder.getAllDataSql();

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, SecVar.class);
				keyValueMap.put(t.getC_SEC_VAR_CODE(), t.getC_SEC_VAR_NAME());
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
	 * 根据 证券品种代码C_SEC_VAR_CODE 获取 所有证券品种字典V_S_DA_SEC_VAR数据
	 * @return
	 */
	public List<SecVar> getDataListByKeys(String[] keys)
			throws ServiceException {
		List<SecVar> pojoList = new ArrayList<SecVar>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";

		SecVarSqlBuilder dsServiceBuilder = null;
		SecVar t = null;
		try {
			dsServiceBuilder = new SecVarSqlBuilder();

			conn = this.loadNewConnection();

			sql = dsServiceBuilder.getDataByKeys();

			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(keys,conn));

			logger.debug(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				t = ResultToBeanExtendGeneric(rs, SecVar.class);
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

	/* END 数据服务 */
	
	/**
	 * 将获取PropertyDescriptor提出到滚动之外，提高rs滚动效率
	 *	  2016-8-19 蒋锦 南方基金现场性能优化
	 * @param rs
	 * @param pojoClass
	 * @param props
	 * @return
	 */
	public <T extends BasePojo> T ResultToBeanExtendGeneric(ResultSet rs,
			Class<T> pojoClass, PropertyDescriptor[] props) throws Exception {
		T pojo = (T) pojoClass.newInstance();
		PropertyDescriptor prop = null;

		String name = "";
		Object resValue = null;

		for (int i = 0; i < props.length; i++) {
			prop = props[i];
			if (DaoAssistance.isSetValue(prop)) {
				name = prop.getName();

				if (pojo instanceof IEffectivable) {
				} else {
					if ("startUseDate".equals(name)
							|| "endUseDate".equals(name)) {
						continue;
					}
				}
				
				if("c_DA_NAME".equals(name)){
					continue;
				}
				
				String columnName = "";
				try{
					columnName = this.secVarSqlBuilder.getColumnNameByPropertyExtend(this.dbNameResolver, name);
				}
				catch(Exception ex){
					logger.debug("忽略属性"+pojoClass.getName()+"."+name);
				}
				if (!"".equals(columnName)) {
					try {
						resValue = rs.getObject(columnName);
					} catch (Exception e) {
						throw new Exception(e.getMessage() + " : " + columnName);
					}

					if (resValue != null) {
						resValue = DaoAssistance.resultSetValueConvert(
								resValue, prop);

						try {
							prop.getWriteMethod().invoke(pojo, resValue);
						} catch (Exception e) {
							throw new Exception(e.getMessage() + " : "
									+ columnName);
						}
					}
				}
			}
		}

		return pojo;
	}
	
	public <T extends BasePojo> T ResultToBeanExtendGeneric(ResultSet rs,
			Class<T> pojoClass) throws Exception {
		T pojo = (T) pojoClass.newInstance();
		PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(pojo);
		PropertyDescriptor prop = null;

		String name = "";
		Object resValue = null;

		for (int i = 0; i < props.length; i++) {
			prop = props[i];
			if (DaoAssistance.isSetValue(prop)) {
				name = prop.getName();

				if (pojo instanceof IEffectivable) {
				} else {
					if ("startUseDate".equals(name)
							|| "endUseDate".equals(name)) {
						continue;
					}
				}
				
				if("c_DA_NAME".equals(name)){
					continue;
				}
				
				String columnName = "";
				try{
					columnName = this.secVarSqlBuilder.getColumnNameByPropertyExtend(this.dbNameResolver, name);
				}
				catch(Exception ex){
					logger.debug("忽略属性"+pojoClass.getName()+"."+name);
				}
				if (!"".equals(columnName)) {
					try {
						resValue = rs.getObject(columnName);
					} catch (Exception e) {
						throw new Exception(e.getMessage() + " : " + columnName);
					}

					if (resValue != null) {
						resValue = DaoAssistance.resultSetValueConvert(
								resValue, prop);

						try {
							prop.getWriteMethod().invoke(pojo, resValue);
						} catch (Exception e) {
							throw new Exception(e.getMessage() + " : "
									+ columnName);
						}
					}
				}
			}
		}

		return pojo;
	}

	/**
	 * 获取证券品种代码 c_sec_var_code 为'PJ','LC','ZQ','CK','HG','CJ'  的证券品种字典视图V_S_DA_SEC_VAR的数据
	 * 以及 业务类型c_busi_type 为('HG','CJ','CK')   的交易方式字典V_S_DT_TD_MODE的数据	
	 *   的集合
	 * 
	 * @return
	 */
    public List<BasePojo> queryIdxCtrlSec() {
		List<BasePojo> secList = new ArrayList<BasePojo>();
		SecVar secVar = null;
		Connection conn = null;
		String qrySql = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetTools rsTools = new ResultSetTools(dbNameResolver, secVarSqlBuilder);
		try {
			conn = this.loadNewConnection();			
			qrySql = secVarSqlBuilder.queryProductByCodesSql();
			logger.debug(qrySql);
			pstmt = conn.prepareStatement(qrySql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				secVar = (SecVar)rsTools.ResultToBean(rs, SecVar.class);
				secList.add(secVar);
			}
		} catch (Exception ex) {
			throw new DataAccessException("获取监控资产失败：" + ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		
		return secList;
	}

    /**
     * 获取 证券品种字典V_S_DA_SEC_VAR所有的数据
     * @return
     */
	public List<BasePojo> getDataListByTimestamp(String timestamp) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		SecVarSqlBuilder dsServiceBuilder = null;
		SecVarExtend t = null;
		try {
			dsServiceBuilder = new SecVarSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();

			sql = dsServiceBuilder.getDataListByTimestamp();

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			/*将获取PropertyDescriptor提出到滚动之外，提高rs滚动效率
			  2016-8-19 蒋锦 南方基金现场性能优化*/
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(SecVarExtend.class.newInstance());
			
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, SecVarExtend.class, props);
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
	 * 根据交易证券代码获取对应的品种类型
	 * @param seccode
	 * @return
	 */
	public List<BasePojo> getVarcodeByCode(String seccode) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ResultSetTools rsTools = null;
		SecVarSqlBuilder dsServiceBuilder = null;
		SecVar t = null; 
		try{
			dsServiceBuilder = new SecVarSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);
			conn = this.loadNewConnection();

			sql = dsServiceBuilder.getVarcodeByCode(seccode);

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, SecVar.class);
				pojoList.add(t);
			}
		}catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return pojoList;
	}
	
	/**
	 * 查询证券品种有哪些(分父子)
	 * add by wangpeixu 20170427 
	 * STORY37961财税140号文件，针对资管增值税系统改造需求
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, List<SecVar>> querySecVar() throws Exception{
		HashMap<String, List<SecVar>> secVarMap = new HashMap<String, List<SecVar>>();
		List<SecVar> secVar = new ArrayList<SecVar>();
		List<SecVar> secVarP = new ArrayList<SecVar>();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "SELECT c_sec_var_code,c_sec_var_name,c_da_code,c_da_code_p FROM t_s_da_sec_var WHERE c_dv_state = 'ENAB'";
		try {
			conn = this.loadNewConnection();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()){
				SecVar secVarPojo = new SecVar();
				secVarPojo.setC_SEC_VAR_CODE(rs.getString("C_SEC_VAR_CODE"));
				secVarPojo.setC_SEC_VAR_NAME(rs.getString("C_SEC_VAR_NAME"));
				secVarPojo.setC_DA_CODE(rs.getString("C_DA_CODE"));
				secVarPojo.setC_DA_CODE_P(rs.getString("C_DA_CODE_P"));
				if("[root]".equals(rs.getString("c_da_code_p"))){
					secVarP.add(secVarPojo);
				}else {
					secVar.add(secVarPojo);
				}
			}
			secVarMap.put("Parent", secVarP);
			secVarMap.put("Child", secVar);
		} catch (SQLException e) {
			logger.log("查询证券品种有哪些(分父子)出错！", SQLException.class);
			throw new SQLException("查询证券品种有哪些(分父子)出错！");
		} finally{
//			DBUtils.cleanUp(rs, pst);
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		
		return secVarMap;
	}
}
