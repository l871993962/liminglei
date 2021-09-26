package com.yss.ams.base.information.modules.sys.dttdmode.dao;

import java.beans.PropertyDescriptor;
import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.sys.dttdmode.pojo.DttdMode;
import com.yss.framework.api.common.co.BaseBean;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.IEffectivable;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.common.co.ParamPojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.exception.InvalidDataException;
import com.yss.framework.api.mvc.AutoDateProc;
import com.yss.framework.api.mvc.dao.DaoAssistance;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.SysUtil;
import com.yss.framework.db.OraDbTool;
import com.yss.framework.util.PojoUtils;

//import dataservice.comm.pojo.DttdMode;

/**
 * 交易方式字典表T_S_DT_TD_MODE dao
 *
 */
public class DttdModeDao extends GeneralDao{

	/**
	 * 构造方法
	 * @param pool
	 * @param sqlBuilder
	 */
	public DttdModeDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
	}
	

	/**
	 * 根据条件 获取符合条件的交易方式字典视图V_S_DT_TD_MODE的数据信息,并分页处理
	 * @param paraMap查询条件
	 */
	@Override
	public List<BasePojo> queryByConditionPage(HashMap<String, Object> paraMap,
			PageInation page, Class<?> clazz) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		List<String> paraNameList;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsMeta = null;
		PropertyDescriptor[] propertys = null;
		String sql = "";

		Object resValue = null;
		//ResultSetTools rsTools = null;
		try {
			//rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			paraNameList = getParaName(paraMap);
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = sqlbuilder.getQueryConditionSql(paraNameList);
			sql = buildPagingSql(sql, page);

			pstmt = conn.prepareStatement(sql);

			logger.debug(sql);

			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}
			int index = 1;
			Object paraValue;
			for (String valueFieldName : paraNameList) {
				if ("N_CHECK_STATE".equals(valueFieldName)) {
					continue;
				}

				if ("id".equals(valueFieldName)) {
					continue;
				}

				if (valueFieldName.startsWith("ARRAY_")) {
					pstmt.setArray(index, OraDbTool.newInstance().sqlOverLongCondition(String
							.valueOf(paraMap.get(valueFieldName)),conn));
				} else {
					paraValue = paraMap.get(valueFieldName);
					if (java.util.Date.class.equals(paraValue)) {
						Date dateValue = new Date(((java.util.Date) paraValue)
								.getTime());
						// BUG #134663 findbugs代码检查和PMD数据库连接检查问题修复 edit by chenyoulong 20160718
						pstmt.setDate(index, dateValue);
//						pstmt.setObject(index, paraMap.get(dateValue));
					} else {
						pstmt.setObject(index, paraMap.get(valueFieldName));
					}

				}
				index++;
			}

			rs = pstmt.executeQuery();

			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				BasePojo t = (BasePojo) clazz.newInstance();

				// pojo的属性不用对查询结果的每一行都重复获取
				if (null == propertys) {
					propertys = getPropertyDescriptors(t);
				}

				if (rsMeta == null) {
					rsMeta = rs.getMetaData();
				}

				for (int i = 0; i < propertys.length; i++) {
					PropertyDescriptor prop = propertys[i];

					if (prop.getPropertyType().isAssignableFrom(Class.class)) {
						continue;
					}

					if (!SysUtil.isBaseType(prop.getPropertyType())) {
						continue;
					}

					if ("c_USER_PWD".equals(prop.getName())) {
						continue;
					}

					if ("primeKey".equals(prop.getName())) {
						continue;
					}

					if ("id".equals(prop.getName())) {
						continue;
					}

					String name = prop.getName();
					String columnName = this.sqlbuilder
							.getColumnNameByProperty(dbNameResolver, name);
					if (!"".equals(columnName)) {
						try {
							resValue = rs.getObject(columnName);
						} catch (Exception e) {
							throw new Exception(e.getMessage() + " : "
									+ columnName);
						}

						if (resValue != null) {
							resValue = DaoAssistance.resultSetValueConvert(
									resValue, prop);

							try {
								prop.getWriteMethod().invoke(t, resValue);
							} catch (Exception e) {
								throw new Exception(e.getMessage() + " : "
										+ columnName);
							}
						}
					}

				}
				pojoList.add(t);
			}
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("重写基类查询方法查询时出错！", ex);
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return pojoList;
	}

	/**
	 * 根据条件 获取符合条件的交易方式字典视图V_S_DT_TD_MODE的数据信息
	 * @param paraNameList查询条件
	 */
	public List<BasePojo> queryByCondition(HashMap<String, Object> paraMap,
			Class<?> clazz) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		List<String> paraNameList;
		PropertyDescriptor[] propertys = null;
		Object resValue = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		//ResultSetTools rsTools = null;
		try {
			//rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			paraNameList = getParaName(paraMap);
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			sql = sqlbuilder.getQueryConditionSql(paraNameList);

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);

			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}

			int index = 1;
			for (String valueFieldName : paraNameList) {
				if ("N_CHECK_STATE".equals(valueFieldName)) {
					continue;
				}

				if (valueFieldName.startsWith("ARRAY_")) {
					pstmt.setArray(index, OraDbTool.newInstance().sqlOverLongCondition(String
							.valueOf(paraMap.get(valueFieldName)),conn));
				} else {
					pstmt.setObject(index, paraMap.get(valueFieldName));
				}

				index++;
			}

			rs = pstmt.executeQuery();

			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				BasePojo t = (BasePojo) clazz.newInstance();
				if (null == propertys) {
					propertys = getPropertyDescriptors(t);
				}

				for (int i = 0; i < propertys.length; i++) {
					PropertyDescriptor prop = propertys[i];
					if (prop.getPropertyType().isAssignableFrom(Class.class)) {
						continue;
					}

					if (!SysUtil.isBaseType(prop.getPropertyType())) {
						continue;
					}

					if ("c_USER_PWD".equals(prop.getName())) {
						continue;
					}

					if ("id".equals(prop.getName())) {
						continue;
					}

					String name = prop.getName();
					String columnName = this.sqlbuilder.getColumnNameByProperty(dbNameResolver, name);
					try {
						resValue = rs.getObject(columnName);
					} catch (Exception e) {
						throw new Exception(e.getMessage() + " : " + columnName);
					}
					if (resValue != null) {
						resValue = DaoAssistance.resultSetValueConvert(
								resValue, prop);
						try {
							prop.getWriteMethod().invoke(t, resValue);
						} catch (Exception e) {
							throw new Exception(e.getMessage() + " : "
									+ columnName);
						}
					}

				}
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
	
	/**
	 * Author : ChenLong
	 * Date   : 2013-11-18
	 * Status : Add
	 * Comment: 插入数据的CIDEN返回值集合
	 * */
	@Override
	public <T extends BaseBean> List<String> insert(List<T> list, Connection conn)
			throws DataAccessException {
		/*
		 * Author : ChenLong
		 * Date   : 2013-11-18
		 * Status : Add
		 * Comment: 插入数据的CIDEN返回值集合
		 * */
		List<String> cidenList = new ArrayList<String>();
		
		PreparedStatement pstmt = null;
		boolean sqlIsWaiting = false;
		try {

			for (T baseBean : list) {
				if (baseBean instanceof IEffectivable) {
					autDateProc = new AutoDateProc(dbNameResolver, sqlbuilder,
							conn);
					autDateProc.effectiveData((ParamPojo) baseBean);
				}

				PropertyDescriptor[] proDescriptors = this
						.getPropertyDescriptors(baseBean);

				StringBuffer fieldNames = new StringBuffer();
				StringBuffer wildcards = new StringBuffer();

				for (PropertyDescriptor prop : proDescriptors) {
					if (prop.getPropertyType().isAssignableFrom(Class.class)) {
						continue;
					}
					if ("id".equals(prop.getName())) {
						continue;
					}

					if (!SysUtil.isBaseType(prop.getPropertyType())) {
						continue;
					}
					if ("primeKey".equals(prop.getName())) {
						continue;
					}

					this.buildFieldByComma(fieldNames, prop, baseBean);
					this.buildWildcardsByComma(wildcards, prop, baseBean);
				}

				if (sqlIsWaiting == false) {
					StringBuffer sqlBuffer = new StringBuffer();
					sqlBuffer.append("insert into ");
					sqlBuffer.append(this.sqlbuilder
							.getTableName(this.dbNameResolver));

					if (wildcards.length() > 0) {
						sqlBuffer.append(" (");
						sqlBuffer.append(fieldNames.substring(0, (fieldNames
								.length() - 1)));
						sqlBuffer.append(")");
						sqlBuffer.append(" values ");
						sqlBuffer.append(" (");
						sqlBuffer.append(wildcards.substring(0, (wildcards
								.length() - 1)));
						sqlBuffer.append(")");
					} else {
						throw new InvalidDataException(baseBean.getClass()
								.toString()
								+ "的实例没有属性值");
					}

					pstmt = conn.prepareStatement(sqlBuffer.toString());

					sqlIsWaiting = true;
				}

				int index = 1;
				for (int i = 0; i < proDescriptors.length; i++) {
					PropertyDescriptor prop = proDescriptors[i];
					if (!DaoAssistance.isAppendEffectDate(baseBean, prop
							.getName())) {
						continue;
					}
					// 去掉getClass方法
					if (prop.getPropertyType().isAssignableFrom(Class.class)) {
						continue;
					}
					if ("id".equals(prop.getName())) {
						continue;
					}

					if (!SysUtil.isBaseType(prop.getPropertyType())) {
						continue;
					}

					if ("primeKey".equals(prop.getName())) {
						continue;
					}

					index = this.setFieldsValue(prop, baseBean, index, pstmt);
				}
				//Fortify 规范代码改造避免空指针异常
				if(pstmt !=null){
					pstmt.addBatch();
				}
			}
			//Fortify 规范代码改造避免空指针异常
			if(pstmt !=null){
				pstmt.executeBatch();
				pstmt.clearBatch();
			}
		} catch (Exception ex) {
			throw new DataAccessException("插入失败：" + ex.getMessage(), ex);
		} finally {		
			this.closeStatementFinal(pstmt);
//			try {
//				if (pstmt != null) {
//					pstmt.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
		}
		return cidenList;
	}
	
	/* START 数据服务方法 */

	/**
	 * 获取所有的交易方式字典视图V_S_DT_TD_MODE的数据信息
	 * @return 所有的交易方式字典视图V_S_DT_TD_MODE的数据信息
	 */
	public List<DttdMode> getAllDataList() throws Exception {
		List<DttdMode> pojoList = new ArrayList<DttdMode>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		DttdModeDaoSqlBuilder dsServiceBuilder = null;
		DttdMode t = null;
		try {
			dsServiceBuilder = new DttdModeDaoSqlBuilder();
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
				t = rsTools.ResultToBeanGeneric(rs, DttdMode.class);
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
	 *  联合查询业务类型，交易方式数据,每个业务类型下的交易方式数据
	 */
	public List<DttdMode> getTreeDataList() throws Exception {
		List<DttdMode> pojoList = new ArrayList<DttdMode>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		DttdModeDaoSqlBuilder dsServiceBuilder = null;
		DttdMode t = null;
		try {
			dsServiceBuilder = new DttdModeDaoSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = dsServiceBuilder.getTreeData();

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, DttdMode.class);
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
	 *  根据 交易方式代码C_DT_CODE 获取交易方式字典视图V_S_DT_TD_MODE的数据信息
	 */
	public DttdMode getDataByCode(String code) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		DttdModeDaoSqlBuilder dsServiceBuilder = null;
		DttdMode t = null;
		try {
			dsServiceBuilder = new DttdModeDaoSqlBuilder();
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
				t = rsTools.ResultToBeanGeneric(rs, DttdMode.class);
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
	 *  根据 业务类型C_BUSI_TYPE数组 获取所有交易方式字典视图V_S_DT_TD_MODE的数据信息
	 */
	public List<DttdMode> getDataListByTypes(String[] types) throws Exception {
		List<DttdMode> pojoList = new ArrayList<DttdMode>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		DttdModeDaoSqlBuilder dsServiceBuilder = null;
		DttdMode t = null;
		try {
			dsServiceBuilder = new DttdModeDaoSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = dsServiceBuilder.getDataListByTypes();

			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(types,conn));

			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, DttdMode.class);
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
	 * 根据业务类型C_BUSI_TYPE数组 或者 交易方式代码C_DT_CODE数组，获取业务类型，交易方式联合树形结构
	 * @return
	 */
	public List<DttdMode> getTreeDataByTypes(String[] types) throws Exception {
		List<DttdMode> pojoList = new ArrayList<DttdMode>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		DttdModeDaoSqlBuilder dsServiceBuilder = null;
		DttdMode t = null;
		try {
			dsServiceBuilder = new DttdModeDaoSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = dsServiceBuilder.getTreeDataByTypes();

			pstmt = conn.prepareStatement(sql);
			Array array = OraDbTool.newInstance().sqlOverLongCondition(types,conn);
			pstmt.setArray(1, array);
			pstmt.setArray(2, array);

			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);
			
			while(rs.next()){
				t = rsTools.ResultToBeanGeneric(rs, DttdMode.class);
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
	 *  联合查询业务类型，交易方式所有数据
	 */
	public HashMap<String, String> getKeyConvertMap() throws Exception {
		HashMap<String, String> keyValueMap = new HashMap<String, String>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";

		DttdModeDaoSqlBuilder dsServiceBuilder = null;
		try {
			dsServiceBuilder = new DttdModeDaoSqlBuilder();

			conn = this.loadNewConnection();

			sql = dsServiceBuilder.getKeyConvertSql();

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				keyValueMap.put(rs.getString(DttdModeColumnName.c_DT_CODE.toString()), 
						rs.getString(DttdModeColumnName.c_DT_NAME.toString()));
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
	 *  根据 交易方式代码C_DT_CODE数组 获取所有交易方式字典视图V_S_DT_TD_MODE的数据信息
	 */
	public List<DttdMode> getDataListByKeys(String[] keys) throws Exception {
		List<DttdMode> pojoList = new ArrayList<DttdMode>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		DttdModeDaoSqlBuilder dsServiceBuilder = null;
		DttdMode t = null;
		try {
			dsServiceBuilder = new DttdModeDaoSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = dsServiceBuilder.getDataByKeys();

			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(keys,conn));

			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, DttdMode.class);
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
	/* END 数据服务方法 */
	/**
	 * 根据时间戳获取所有交易方式字典视图V_S_DT_TD_MODE的数据信息
	 * @return
	 */
	public List<BasePojo> getDataListByTimestamp(String timestamp) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		DttdModeDaoSqlBuilder dsServiceBuilder = null;
		DttdMode t = null;
		try {
			dsServiceBuilder = new DttdModeDaoSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();

			sql = dsServiceBuilder.getDataListByTimestamp();

			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, DttdMode.class);
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
	 * 加载分组拆分 交易方式 
	 * By Jinghehe 2015-11-08
	 * @return
	 */
	public List<DttdMode> getTreeDataForRule() {
		List<DttdMode> pojoList = new ArrayList<DttdMode>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		DttdModeDaoSqlBuilder dsServiceBuilder = null;
		DttdMode t = null;
		try {
			dsServiceBuilder = new DttdModeDaoSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = dsServiceBuilder.getTreeDataForRule();

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, DttdMode.class);
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
	 * 查询分组拆分 交易方式中父节点的业务 
	 * By wangyaokang 2015-11-26
	 * @return
	 */
	public DttdMode getDataByCodeForRule(String code) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		DttdModeDaoSqlBuilder dsServiceBuilder = null;
		DttdMode t = null;
		try {
			dsServiceBuilder = new DttdModeDaoSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = dsServiceBuilder.getDataByCodeForRule();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);

			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, DttdMode.class);
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
	
	/***
	 * add by liyanjun 2016-2-20 STORY #28608 【广发证券】在分组恒生交易数据文件接口添加控制
	 * 根据清算接口代码，构造恒生交易业务树形结构数据
	 * 背景：在清算分组恒生交易数据文件接口时，根据清算参数“恒生-交易数据清算业务类型选择”，勾选不同市场的各个业务，实现只处理被勾选的业务类型。
	 * 新增字典表T_S_DV_TD_ITEM（恒生交易数据业务分类表），包含的各业务的业务代码和清算条件
	 * @param cfgCodes 接口代码
	 * @return
	 */
	public List<DttdMode> getTreeDataByCfgCode(String[] cfgCodes) {
		List<DttdMode> pojoList = new ArrayList<DttdMode>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		DttdModeDaoSqlBuilder dsServiceBuilder = null;
		DttdMode t = null;
		try {
			dsServiceBuilder = new DttdModeDaoSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = dsServiceBuilder.getTreeDataByCfgCode();

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(cfgCodes,conn));
			pstmt.setArray(2, OraDbTool.newInstance().sqlOverLongCondition(cfgCodes,conn));
			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, DttdMode.class);
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
	
	/***
	 * add by wangtangyao 20160815 STORY #28887 保险资产证券清算款、其他应收款科目处理
	 * 根据词汇分类，获取非明细业务类型（针对证券清算款非T+1），不从销售方式表中获取
	 * 证券清算款非T+1核算项下拉列表要加载开放申赎业务类型
	 * @param type 词汇分类
	 * @return
	 */
	public List<DttdMode> getSQKDataListByTypes(String type) {
		List<DttdMode> pojoList = new ArrayList<DttdMode>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		DttdModeDaoSqlBuilder dsServiceBuilder = null;
		DttdMode t = null;
		try {
			dsServiceBuilder = new DttdModeDaoSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = dsServiceBuilder.getSQKDataListByType();

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);
			pstmt.setString(1, type);
			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, DttdMode.class);
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
	 * 支持多参数以数组形式传入
	 * @param type
	 * @return
	 */
	public List<DttdMode> getSQKDataListByTypes(String[] type) {
		List<DttdMode> pojoList = new ArrayList<DttdMode>();
//		DttdMode dm = new DttdMode();
//		dm.setC_DT_CODE("[NA]");
//		dm.setC_DT_NAME("[NA]");
//		pojoList.add(dm);
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		DttdModeDaoSqlBuilder dsServiceBuilder = null;
		DttdMode t = null;
		try {
			dsServiceBuilder = new DttdModeDaoSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = dsServiceBuilder.getSQKDataListByTypes();

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);
//			pstmt.setString(1, type);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(type,conn));
			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);
			// 南方基金性能优化 zhanghualin 2017-3-16
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(new DttdMode());
			while (rs.next()) {
				//t = rsTools.ResultToBeanGeneric(rs, DttdMode.class);
				t = rsTools.ResultToBeanGeneric(rs, DttdMode.class, props);
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
	 * add by yuanyafeng 20180911 STORY #61545 【紧急】太平保险-附件管理优化（二期）
	 * 根据模块功能代码取对应模块的交易方式，没有的返回空
	 * @param funCode 功能代码
	 * @return 交易方式
	 */
	public List<DttdMode> getDataListByFun(String funCode) {
		List<DttdMode> pojoList = new ArrayList<DttdMode>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		DttdModeDaoSqlBuilder dsServiceBuilder = null;
		DttdMode t = null;
		try {
			dsServiceBuilder = new DttdModeDaoSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = dsServiceBuilder.getDataListByFun();

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);
			pstmt.setString(1, funCode);
			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, DttdMode.class);
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
}
