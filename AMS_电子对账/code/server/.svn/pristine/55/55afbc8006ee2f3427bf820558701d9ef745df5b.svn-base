package com.yss.uco.elecreco.er.reverse.portrela.dao;

import java.beans.PropertyDescriptor;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.IEffectivable;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.DaoAssistance;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.db.OraDbTool;
import com.yss.framework.util.PojoUtils;


public class ElecPortRelaDao extends GeneralDao {
	private ElecPortRelaSqlBuilder portRelsSqlBuilder;
	public ElecPortRelaDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		portRelsSqlBuilder = (ElecPortRelaSqlBuilder) sqlBuilder;
	}
	
	/**
	 * 将结果集转换为pojo
	 * @Title ResultToBean 
	 * @Description 
	 * @param rs
	 * @param pojoClass 
	 * @return
	 * @throws Exception
	 * @return BasePojo
	 */
	public BasePojo ResultToOrg(ResultSet rs, Class<?> pojoClass) throws Exception{
		BasePojo pojo = (BasePojo) pojoClass.newInstance();

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
					if ("startUseDate".equalsIgnoreCase(name)
							|| "endUseDate".equalsIgnoreCase(name)) {
						continue;
					}
				}
				String columnName = "";
				try{
					columnName = OrgColumnName.valueOf(name).toString().toUpperCase();
				}
				catch(Exception ex){
				}
				if (!"".equalsIgnoreCase(columnName)) {
					try {
						resValue = rs.getObject(columnName);
					} catch (Exception e) {
						//throw new Exception(e.getMessage() + " : " + columnName);
						resValue = null;
//						if(throwException){
//							throw new Exception(e.getMessage() + " : " + columnName);
//						}
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
	 * 将结果集转换为pojo
	 * @Title ResultToBean 
	 * @Description 
	 * @param rs
	 * @param pojoClass 
	 * @return
	 * @throws Exception
	 * @return BasePojo
	 */
	public BasePojo ResultToPort(ResultSet rs, Class<?> pojoClass) throws Exception{
		BasePojo pojo = (BasePojo) pojoClass.newInstance();

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
					if ("startUseDate".equalsIgnoreCase(name)
							|| "endUseDate".equalsIgnoreCase(name)) {
						continue;
					}
				}
				String columnName = "";
				try{
					columnName = PortColumnName.valueOf(name).toString().toUpperCase();
				}
				catch(Exception ex){
				}
				if (!"".equalsIgnoreCase(columnName)) {
					try {
						resValue = rs.getObject(columnName);
					} catch (Exception e) {
						//throw new Exception(e.getMessage() + " : " + columnName);
						resValue = null;
//						if(throwException){
//							throw new Exception(e.getMessage() + " : " + columnName);
//						}
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
	 * 关联机构
	 * 
	 * @param paraMap
	 * @param page
	 * @param clazz
	 * @return
	 */
	public List<BasePojo> queryOrganByPort(HashMap<String, Object> paraMap, Class<?> pojoClas) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		List<String> paraNameList;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		try {
			paraNameList = getParaName(paraMap);
			conn = this.loadNewConnection();
			sql = portRelsSqlBuilder.getOrganByPortSql(paraNameList);
			pstmt = conn.prepareStatement(sql);
			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}

			int index = 1;
			Object paraValue;
			for (String valueFieldName : paraNameList) {
				if ("N_CHECK_STATE".equalsIgnoreCase(valueFieldName)) {
					continue;
				}

				if (valueFieldName.startsWith("ARRAY_")) {
					pstmt.setArray(index, OraDbTool.newInstance().sqlOverLongCondition(String
							.valueOf(paraMap.get(valueFieldName)),conn));
				} else {
					paraValue = paraMap.get(valueFieldName);
					if (paraValue instanceof java.util.Date) {
						Date dateValue = new Date(
								((java.util.Date) paraValue).getTime());
						pstmt.setDate(index, dateValue);
					} else {
						pstmt.setObject(index, paraMap.get(valueFieldName));
					}

				}

				index++;
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BasePojo t = ResultToOrg(rs, pojoClas);
				pojoList.add(t);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
			// try {
			// if (conn != null) {
			// conn.close();
			// }
			// } catch (SQLException e) {
			// e.printStackTrace();
			// }
		}

		return pojoList;
	}


	/**
	 * 关联机构
	 * 
	 * @param paraMap
	 * @param page
	 * @param clazz
	 * @return
	 */
	public List<BasePojo> queryOrganDao(HashMap<String, Object> paraMap, Class<?> clazz) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		List<String> paraNameList;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		try {
			paraNameList = getParaName(paraMap);
			conn = this.loadNewConnection();
			sql = portRelsSqlBuilder.getQueryOrganSql(paraNameList);
			pstmt = conn.prepareStatement(sql);
			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}

			int index = 1;
			Object paraValue;
			for (String valueFieldName : paraNameList) {
				if ("N_CHECK_STATE".equalsIgnoreCase(valueFieldName)) {
					continue;
				}

				if (valueFieldName.startsWith("ARRAY_")) {
					pstmt.setArray(index, OraDbTool.newInstance().sqlOverLongCondition(String
							.valueOf(paraMap.get(valueFieldName)),conn));
				} else {
					paraValue = paraMap.get(valueFieldName);
					if (paraValue instanceof java.util.Date) {
						Date dateValue = new Date(
								((java.util.Date) paraValue).getTime());
						pstmt.setDate(index, dateValue);
					} else {
						pstmt.setObject(index, paraMap.get(valueFieldName));
					}

				}

				index++;
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BasePojo t = ResultToPort(rs, clazz);
				pojoList.add(t);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
			// try {
			// if (conn != null) {
			// conn.close();
			// }
			// } catch (SQLException e) {
			// e.printStackTrace();
			// }
		}

		return pojoList;
	}

}
