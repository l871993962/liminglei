package com.yss.ams.base.information.modules.sys.acctype.dao;

import java.beans.PropertyDescriptor;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.sys.acctype.pojo.AccType;
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
import com.yss.framework.api.mvc.biz.BusinessException;
import com.yss.framework.api.mvc.dao.DaoAssistance;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.SysUtil;
import com.yss.framework.db.OraDbTool;

//import dataservice.comm.pojo.AccType;

public class AccTypeDao extends GeneralDao {
	
	public AccTypeDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
	}

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
						pstmt.setDate(index, dateValue);
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
			logger.log("资产类型字典：查询报错", ex);
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return pojoList;
	}

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
			//update by chenwenhai 2013073 关闭游标
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return pojoList;
	}
	
	@Override
	public <T extends BaseBean> List<String> insert(List<T> list, Connection conn)
			throws DataAccessException {
		PreparedStatement pstmt = null;
		boolean sqlIsWaiting = false;
		/*
		 * Author : ChenLong
		 * Date   : 2013-11-18
		 * Status : Add
		 * Comment: 插入数据的CIDEN返回值集合
		 * */
		List<String> cidenList = new ArrayList<String>();
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
				if( pstmt != null){
					pstmt.addBatch();
				}
			}
			//Fortify 规范代码改造避免空指针异常
			if( pstmt != null){
				pstmt.executeBatch();
				pstmt.clearBatch();//addbyleeyu20151015
			}
		} catch (Exception ex) {
			throw new DataAccessException("插入失败：" + ex.getMessage(), ex);
		} finally {
			//update by chenwenhai 2013073 关闭游标
			this.closeStatementFinal(pstmt);
		}
		return cidenList;
	}
	/* START 数据服务方法 */
	/**
	 * 获取资产类型字典V_S_DAT_ASS_TYPE的所有 
	 * 资产类型代码C_DAT_CODE和资产类型名称C_DAT_NAME的集合
	 * @return hashMap类型的资产类型代码C_DAT_CODE和资产类型名称C_DAT_NAME的集合
	 * @throws Exception
	 */
	public HashMap<String, String> getKeyConvertMap() throws Exception {
		HashMap<String, String> keyValueMap = new HashMap<String,String>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		AccTypeSqlBuilder dsServiceBuilder = null;
		AccType t = null;
		try {
			dsServiceBuilder = new AccTypeSqlBuilder();
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
				t = rsTools.ResultToBeanGeneric(rs, AccType.class);
				keyValueMap.put(t.getC_DAT_CODE(),t.getC_DAT_NAME());
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
	 * 根据资产类型代码C_DAT_CODE获取所有符合条件的数据
	 * @param listKey List类型集合
	 * @return hashMap类型的资产类型代码C_DAT_CODE和资产类型名称C_DAT_NAME的集合
	 * @throws Exception
	 */
	public HashMap<String, String> getKeyConvertMap(List<String> listKey) throws Exception {
		HashMap<String, String> keyValueMap = new HashMap<String,String>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		AccTypeSqlBuilder dsServiceBuilder = null;
		AccType t = null;
		try {
			String[] strArr = new String[listKey.size()];
			int i = 0;
			for (String str : listKey) {
				strArr[i++] = str;
			}
			
			dsServiceBuilder = new AccTypeSqlBuilder();
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

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, AccType.class);
				keyValueMap.put(t.getC_DAT_CODE(),t.getC_DAT_NAME());
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
	 * 获取资产类型字典V_S_DAT_ASS_TYPE的所有记录
	 * @return 资产类型字典V_S_DAT_ASS_TYPE的所有记录的AccType对象集合
	 * @throws Exception
	 */
	public List<AccType> getAllDataList() throws Exception {
		List<AccType> pojoList = new ArrayList<AccType>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		AccTypeSqlBuilder dsServiceBuilder = null;
		AccType t = null;
		try {
			dsServiceBuilder = new AccTypeSqlBuilder();
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
				t = rsTools.ResultToBeanGeneric(rs, AccType.class);
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
	 * 获取资产类型字典V_S_DAT_ASS_TYPE的指定的资产类型代码C_DAT_CODE的一条记录
	 * @param code ：C_DAT_CODE的值
	 * @return 资产类型字典V_S_DAT_ASS_TYPE的指定的资产类型代码C_DAT_CODE的一条记录
	 * @throws Exception
	 */
	public AccType getDataByCode(String code) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		AccTypeSqlBuilder dsServiceBuilder = null;
		AccType t = null;
		try {
			dsServiceBuilder = new AccTypeSqlBuilder();
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
				t = rsTools.ResultToBeanGeneric(rs, AccType.class);
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
	 * 根据类型获取相应资产类型数据
	 * @param 	资产类型types
	 * @return 获取类型相关的资产类型的集合
	 * @throws Exception
	 */
	public List<AccType> getDataListByTypes(String[] types) throws Exception {
		List<AccType> pojoList = new ArrayList<AccType>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		AccTypeSqlBuilder dsServiceBuilder = null;
		AccType t = null;
		try {
			dsServiceBuilder = new AccTypeSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();

			sql = dsServiceBuilder.getDataByTypes();// 根据c_dat_type查询

			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(types,conn));
			
			logger.debug(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, AccType.class);
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
	 * 根据资产类型代码C_DAT_CODE获取所有符合条件的数据
	 * @param keys 资产类型代码C_DAT_CODE组成的数组
	 * @return	符合条件的AccType对象集合
	 * @throws Exception
	 */
	public List<AccType> getDataListByKeys(String[] keys) throws Exception {
		List<AccType> pojoList = new ArrayList<AccType>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		AccTypeSqlBuilder dsServiceBuilder = null;
		AccType t = null;
		try {
			dsServiceBuilder = new AccTypeSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
//			conn.setAutoCommit(false);

			sql = dsServiceBuilder.getDataByCodes();

			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(keys,conn));

			logger.debug(sql);

			rs = pstmt.executeQuery();
//			conn.commit();
//			conn.setAutoCommit(true);

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, AccType.class);
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
     * ===================
     * 改变功能实现位置，迁移到估值
     * ===================
	 * Author : ChenLong
	 * Date   : 2017-05-11
	 * Status : Add
	 * Comment: 增值税方案关联资产类别
	 * @param paraMap
	 * @return
	 */
//	public List<AccType> queryVATPlanRelaCls(HashMap<String, String> paraMap){
//		List<AccType> list = new ArrayList<AccType>();
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try{
//			conn = this.loadNewConnection();
//			StringBuilder sqlBuilder = new StringBuilder();
//			sqlBuilder.append(" select c_dat_code,c_dat_name from t_s_dat_ass_type acc");
//			sqlBuilder.append(" where");
//			sqlBuilder.append(" not exists(");
//			sqlBuilder.append(" select 1 from t_e_exec_plan_dat plan");
//			sqlBuilder.append(" where plan.c_dat_code = acc.c_dat_code");
//			sqlBuilder.append(" and (");
//			sqlBuilder.append(" (to_date(?, 'yyyy-mm-dd') between d_begin and d_end) or");
//			sqlBuilder.append(" (to_date(?, 'yyyy-mm-dd') between d_begin and d_end) or");
//			sqlBuilder.append(" (to_date(?, 'yyyy-mm-dd') < d_begin and to_date(?, 'yyyy-mm-dd') > d_end)");
//			sqlBuilder.append(" )");
//			sqlBuilder.append(" )");
//			sqlBuilder.append(" and c_dat_type = 'CLS'");
//			sqlBuilder.append(" order by n_order");
//			pstmt = conn.prepareStatement(sqlBuilder.toString());
//			pstmt.setString(1, paraMap.get("D_BEGIN"));
//			pstmt.setString(2, paraMap.get("D_END"));
//			pstmt.setString(3, paraMap.get("D_BEGIN"));
//			pstmt.setString(4, paraMap.get("D_END"));
//			rs = pstmt.executeQuery();
//			while(rs.next()){
//				AccType accType = new AccType();
//				accType.setC_DAT_CODE(rs.getString("c_dat_code"));
//				accType.setC_DAT_NAME(rs.getString("c_dat_name"));
//				list.add(accType);
//			}
//		}catch(Exception ex){
//			throw new BusinessException("查询增值税方案关联资产类别信息失败!" + ex.getMessage(),ex);
//		}finally{
//			this.closeResultSetFinal(rs);
//			this.closeStatementFinal(pstmt);
//			this.releaseConnection(conn);
//		}
//		return list;
//	}
}
