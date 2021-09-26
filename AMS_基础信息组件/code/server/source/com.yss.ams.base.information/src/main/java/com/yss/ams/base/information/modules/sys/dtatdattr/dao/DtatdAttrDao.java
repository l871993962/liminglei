package com.yss.ams.base.information.modules.sys.dtatdattr.dao;

import java.beans.PropertyDescriptor;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.sys.dtatdattr.pojo.DtatdAttr;
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

//import dataservice.comm.pojo.DtatdAttr;


/**
 * 交易属性字典T_S_DTA_TD_ATTR dao
 *
 */
public class DtatdAttrDao extends GeneralDao {

	public DtatdAttrDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
	}

	/**
	 * 根据条件获取符合条件的交易属性字典T_S_DTA_TD_ATTR的所有数据信息,并进行分页
	 * @param paraNameList查询条件参数
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
		// ResultSetTools rsTools = null;
		try {
			// rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
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
						//pstmt.setObject(index, paraMap.get(dateValue));
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
	 * 根据条件获取符合条件的交易属性字典T_S_DTA_TD_ATTR的所有数据信息
	 * @param paraNameList查询条件参数
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
		// ResultSetTools rsTools = null;
		try {
			// rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
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
					String columnName = this.sqlbuilder
							.getColumnNameByProperty(dbNameResolver, name);
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
//			try {
//				if (conn != null) {
//					conn.close();
//				}
				this.closeResultSetFinal(rs);
				this.closeStatementFinal(pstmt);
				this.releaseConnection(conn);
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
				doForlist( baseBean,  conn, sqlIsWaiting, pstmt);
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
	
	public void doForlist(BaseBean baseBean, Connection conn,boolean sqlIsWaiting,PreparedStatement pstmt) throws Exception{
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
				sqlBuffer.append(fieldNames.substring(0,
						(fieldNames.length() - 1)));
				sqlBuffer.append(")");
				sqlBuffer.append(" values ");
				sqlBuffer.append(" (");
				sqlBuffer.append(wildcards.substring(0,
						(wildcards.length() - 1)));
				sqlBuffer.append(")");
			} else {
				throw new InvalidDataException(baseBean.getClass()
						.toString() + "的实例没有属性值");
			}

			pstmt = conn.prepareStatement(sqlBuffer.toString());

			sqlIsWaiting = true;
		}

		int index = 1;
		for (int i = 0; i < proDescriptors.length; i++) {
			PropertyDescriptor prop = proDescriptors[i];
			if (!DaoAssistance.isAppendEffectDate(baseBean,
					prop.getName())) {
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
			pstmt.executeBatch();
			pstmt.clearBatch();
		}
		this.closeStatementFinal(pstmt);
	}
	
	/* START 数据服务方法 */
	/**
	 * 获取所有的交易属性字典T_S_DTA_TD_ATTR数据
	 */
	public List<DtatdAttr> getAllDataList() throws Exception {
		List<DtatdAttr> pojoList = new ArrayList<DtatdAttr>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		DtatdAttrSqlBuilder dsServiceBuilder = null;
		DtatdAttr t = null;
		try {
			dsServiceBuilder = new DtatdAttrSqlBuilder();
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
				t = rsTools.ResultToBeanGeneric(rs, DtatdAttr.class);
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
	 * 根据交易属性代码C_DTA_CODE值获取交易属性字典T_S_DTA_TD_ATTR数据
	 * @return 交易属性字典DtatdAttr pojo对象
	 */
	public DtatdAttr getDataByCode(String code) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		DtatdAttrSqlBuilder dsServiceBuilder = null;
		DtatdAttr t = null;
		try {
			dsServiceBuilder = new DtatdAttrSqlBuilder();
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
				t = rsTools.ResultToBeanGeneric(rs, DtatdAttr.class);
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
	 * 根据业务类型C_BUSI_TYPE值获取所有交易属性字典T_S_DTA_TD_ATTR数据
	 * @param types 业务类型C_BUSI_TYPE值
	 * @return 交易属性字典DtatdAttr pojo对象集合
	 */
	public List<DtatdAttr> getDataListByTypes(String[] types) throws Exception {
		List<DtatdAttr> pojoList = new ArrayList<DtatdAttr>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		DtatdAttrSqlBuilder dsServiceBuilder = null;
		DtatdAttr t = null;
		try {
			dsServiceBuilder = new DtatdAttrSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();

			sql = dsServiceBuilder.getDataListByTypes();

			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(types,conn));
			
			logger.debug(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, DtatdAttr.class);
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
	 * 获取所有的交易属性字典T_S_DTA_TD_ATTR的交易属性代码和交易属性名称的集合
	 */
	public HashMap<String, String> getKeyConvertMap() throws Exception {
		HashMap<String, String> keyValueMap = new HashMap<String,String>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		DtatdAttrSqlBuilder dsServiceBuilder = null;
		DtatdAttr t = null;
		try {
			dsServiceBuilder = new DtatdAttrSqlBuilder();
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
				t = rsTools.ResultToBeanGeneric(rs, DtatdAttr.class);
				keyValueMap.put(t.getC_DTA_CODE(),t.getC_DTA_NAME());
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
	 * 根据业务类型C_BUSI_TYPE值获取所有交易属性字典T_S_DTA_TD_ATTR数据
	 * @param keys 业务类型C_BUSI_TYPE值
	 * @return 交易属性字典DtatdAttr pojo对象集合
	 */
	public List<DtatdAttr> getDataListByKeys(String[] keys) throws Exception {
		List<DtatdAttr> pojoList = new ArrayList<DtatdAttr>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		DtatdAttrSqlBuilder dsServiceBuilder = null;
		DtatdAttr t = null;
		try {
			dsServiceBuilder = new DtatdAttrSqlBuilder();
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

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, DtatdAttr.class);
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
	 * 获取所有的交易属性字典T_S_DTA_TD_ATTR的 交易属性代码C_DTA_CODE,交易属性名称C_DTA_NAME列值组成的集合
	 */
	public HashMap<String, String> getShortDataMap() {
		HashMap<String, String> map= new HashMap<String, String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		try {
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql =(new DtatdAttrSqlBuilder()).getAllShortDataSql();

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				map.put(rs.getString("C_DTA_CODE"), rs.getString("C_DTA_NAME"));
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return map;
	}
	/**
	 * 通过指定codes获取数据   STORY39265商品期权业务
	 * @param codes
	 * @return 数据集合
	 * @throws ServiceException
	 * @author xuyuanhao
	 * @date 2017-3-30
	 * @state add
	 */
	public List<DtatdAttr> getDataListByCodes(String[] codes) throws Exception {
		List<DtatdAttr> pojoList = new ArrayList<DtatdAttr>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		DtatdAttrSqlBuilder dsServiceBuilder = null;
		DtatdAttr t = null;
		try {
			dsServiceBuilder = new DtatdAttrSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = dsServiceBuilder.getDataListByCodes();

			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(codes,conn));

			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, DtatdAttr.class);
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
	 * STORY #96237 凭证处理过程中交易属性需要变更从缓存中处理
	 * @param timestamp	时间戳
	 * @return	
	 */
	public List<BasePojo> getDataListByTimestamp(String timestamp) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		DtatdAttrSqlBuilder dsServiceBuilder = null;
		DtatdAttr t = null;
		try {
			dsServiceBuilder = new DtatdAttrSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();

			sql = dsServiceBuilder.getDataListByTimestamp();

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, DtatdAttr.class);
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
