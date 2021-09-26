package com.yss.ams.sec.information.modules.plateset.plate.dao;

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

import com.yss.ams.sec.information.support.modules.plateset.plate.pojo.Plate;
import com.yss.ams.sec.information.support.modules.plateset.plate.pojo.PlateATreeView;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.DaoAssistance;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.db.OraDbTool;
import com.yss.framework.util.DateUtil;

/**
 * 板块dao 
 * @author 马向峰 拆分
 *@Date 20170531
 */
public class PlateDao extends GeneralDao {

	private PlateSqlBuilder sqlBuilder;

	public PlateDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		this.sqlBuilder = (PlateSqlBuilder) sqlBuilder;
	}

	public List<BasePojo> queryTreeViewData(HashMap<String, Object> paraMap) {
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
			sql = sqlBuilder.getTreeViewQuerySql(paraNameList);

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);

			rs = pstmt.executeQuery();

			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				PlateATreeView t = new PlateATreeView();

				PropertyDescriptor[] propertys = this.getPropertyDescriptors(t);

				for (int i = 0; i < propertys.length; i++) {
					PropertyDescriptor prop = propertys[i];
					if (prop.getPropertyType().isAssignableFrom(Class.class)) {
						continue;
					}

					if ("c_USER_PWD".equals(prop.getName())) {
						continue;
					}

					String name = prop.getName();
					String columnName = this.sqlBuilder
							.getColumnNameByPropertyByPlate_A(dbNameResolver,
									name);
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
						resValue = DaoAssistance.resultSetValueConvert(
								resValue, prop);

						try {
							prop.getWriteMethod().invoke(t, resValue);
						} catch (Exception e) {
							throw new Exception(e.getMessage() + " : "
									+ columnName);
						}
						// prop.getWriteMethod().invoke(t, resValue);
					}

				}

				pojoList.add(t);
			}

		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("板块信息功能模块：查询板块信息出错", ex);
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

	// /更新版块时更新相关的子版块父节点
	public void updatePlateRela(List<BasePojo> paramPojo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String strSql = "";

		strSql = "  update T_P_BI_PLATE a set a.C_Plate_Code_P = ?  WHERE  EXISTS ( SELECT DISTINCT '1' FROM t_p_bi_plate b  "
				+ " 	WHERE a.c_plate_code = b.c_plate_code CONNECT BY PRIOR b.c_plate_code = b.c_plate_code_p  "
				+ " 	START WITH b.c_plate_code_p = (SELECT c.c_plate_code  FROM t_p_bi_plate c WHERE c.c_iden=?) ) ";

		try {
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(strSql);
			Plate platePojo;
			for (BasePojo pojo : paramPojo) {
				platePojo = (Plate) pojo;
				pstmt.setString(1, platePojo.getC_PLATE_CODE());
				pstmt.setString(2, platePojo.getId());
				pstmt.executeUpdate();
			}

			conn.commit();
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
	}

	/**
	 * 板块分类
	 * 
	 * @return
	 */
	public List<Plate> getPlateCategory(boolean flag) {
		List<Plate> plateList = new ArrayList<Plate>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
//		zhoushuhang 20170519 BUG #160288 点击资本币种下拉框报错    加载版块信息设置中版块代码为SAC的数据
		String sql = sqlBuilder.getPlateCategotySQL(flag);
		try {
			conn = this.loadNewConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ResultSetTools rsTools = new ResultSetTools(dbNameResolver,
					sqlbuilder);
			while (rs.next()) {
				BasePojo t = rsTools.ResultToBean(rs, Plate.class);
				plateList.add((Plate) t);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询数据出错", ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return plateList;
	}

	/**
	 * 查询板块信息是否有子节点
	 */
	public String checkSubData(String[] datas) {
		String strSql = "";// sql
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;// 结果集
		String result = "true";// 返回的结果
		int count = 0;

		try {
			conn = loadNewConnection();
			strSql = new PlateSqlBuilder().getSubDataSql();

			ptmt = conn.prepareStatement(strSql);
			ptmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(datas,conn));

			rs = ptmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt(1);
			}

			if (count > 0) {
				result = "false";
			}
		} catch (Exception e) {
			throw new DataAccessException("获取板块子节点数据出错", e);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(ptmt);
			releaseConnection(conn);
		}

		return result;
	}
	
	/**开始数据服务**/
	
	public HashMap<String, String> getSubConvertMap() {
		String sqlString = "";
		PreparedStatement pst = null;
		Connection connection = null;
		ResultSet resultSet = null;
		HashMap<String, String> convertMap = null;
		
		try {
			connection = this.loadNewConnection();
			sqlString = new PlateSqlBuilder().getsubConvertSql();
			logger.debug(sqlString);
			pst = connection.prepareStatement(sqlString);
			resultSet = pst.executeQuery();
			
			while (resultSet.next()) {
				if (null == convertMap) {
					convertMap = new HashMap<String, String>();
				}
				convertMap.put(resultSet.getString("C_PLATE_CODE"),
						resultSet.getString("C_PLATE_NAME"));
			}
		} catch (Exception e) {
			throw new DataAccessException("板块代码转换错误", e);
		} finally {
			closeResultSetFinal(resultSet);
			closeStatementFinal(pst);
			releaseConnection(connection);
		}
		
		return convertMap;
	}
	
	public HashMap<String, String> getConvertMap(List<String> listKey) {
		String sqlString = "";
		PreparedStatement pst = null;
		Connection conn = null;
		ResultSet resultSet = null;
		HashMap<String, String> convertMap = null;
		
		try {
			String[] strArr = new String[listKey.size()];
			conn = this.loadNewConnection();
			sqlString = new PlateSqlBuilder().getKeyConvertListMap();
			logger.debug(sqlString);
			pst = conn.prepareStatement(sqlString);
			pst.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(listKey.toArray(strArr),conn));
			resultSet = pst.executeQuery();
			
			while (resultSet.next()) {
				if (null == convertMap) {
					convertMap = new HashMap<String, String>();
				}
				convertMap.put(resultSet.getString("C_PLATE_CODE"),
						resultSet.getString("C_PLATE_NAME"));
			}
		} catch (Exception e) {
			throw new DataAccessException("板块代码转换错误", e);
		} finally {
			closeResultSetFinal(resultSet);
			closeStatementFinal(pst);
			releaseConnection(conn);
		}
		
		return convertMap;
	}
	
	public Plate getPojoByCode(String code) {
		String sqlString = "";
		PreparedStatement pst = null;
		Connection connection = null;
		ResultSet resultSet = null;
		Plate plate = null;
		ResultSetTools rsTools = null;
		try {
			PlateSqlBuilder plateSqlBuilder = new PlateSqlBuilder();
			connection = this.loadNewConnection();
			rsTools = new ResultSetTools(dbNameResolver, plateSqlBuilder);
			sqlString = new PlateSqlBuilder().getPojoByCode();
			logger.debug(sqlString);
			pst = connection.prepareStatement(sqlString);
			pst.setString(1, code);
			resultSet = pst.executeQuery();
			
			while (resultSet.next()) {
				plate = (Plate) rsTools.ResultToBean(resultSet, Plate.class);
			}
		} catch (Exception e) {
			throw new DataAccessException("板块代码转换错误", e);
		} finally {
			closeResultSetFinal(resultSet);
			closeStatementFinal(pst);
			releaseConnection(connection);
		}
		
		return plate;
	}
	/**数据服务结束**/
}
