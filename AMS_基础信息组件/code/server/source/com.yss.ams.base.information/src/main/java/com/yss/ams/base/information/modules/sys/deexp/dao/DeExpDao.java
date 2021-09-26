package com.yss.ams.base.information.modules.sys.deexp.dao;

import java.beans.PropertyDescriptor;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

/**
 * 表达式字典表T_S_DE_EXP dao
 *
 */
public class DeExpDao extends GeneralDao{

	public DeExpDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
	}

	/**
	 * 获取符合条件paraNameList的表达式字典表T_S_DE_EXP所有数据信息 并进行分页处理
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
//		ResultSetTools rsTools = null;
		try {
//			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
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
	 * 获取符合条件paraNameList的表达式字典表T_S_DE_EXP所有数据信息
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
//		ResultSetTools rsTools = null;
		try {
//			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
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
}
