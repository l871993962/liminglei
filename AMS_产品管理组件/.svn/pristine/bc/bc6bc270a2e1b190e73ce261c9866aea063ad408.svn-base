package com.yss.ams.product.information.modules.ab.portrela.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.support.modules.ab.portrela.pojo.PortRela;
import com.yss.ams.product.information.support.modules.ab.portrela.pojo.PortRelaInvestMgr;
import com.yss.ams.product.information.support.modules.ab.portrela.pojo.PortRelaTradeSeat;
import com.yss.framework.api.common.co.BaseBean;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.common.co.Port_A;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.exception.InvalidParametersException;
import com.yss.framework.api.mvc.biz.BusinessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.db.OraDbTool;
import com.yss.framework.util.DateUtil;

public class PortRelaDao extends GeneralDao {
	private PortRelaSqlBuilder portRelsSqlBuilder;

	public PortRelaDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		portRelsSqlBuilder = (PortRelaSqlBuilder) sqlBuilder;
	}

	/**
	 * 股东账户
	 * 
	 * @param paraMap
	 * @param page
	 * @param clazz
	 * @return
	 */
	public List<BasePojo> queryCashAccountDao(HashMap<String, Object> paraMap,
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
			conn.setAutoCommit(false);
			sql = portRelsSqlBuilder.getQueryCashAccountSql(paraNameList);
			sql = buildPagingSql(sql, page);
			pstmt = conn.prepareStatement(sql);

			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}

			int index = 1;
			Object paraValue;
			for (String valueFieldName : paraNameList) {
				if ("N_CHECK_STATE".equals(valueFieldName)) {
					continue;
				}

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
						pstmt.setObject(index, paraMap.get(valueFieldName));
					}

				}

				index++;
			}
			rs = pstmt.executeQuery();

			conn.commit();
			conn.setAutoCommit(true);
			while (rs.next()) {
				BasePojo t = rsTools.ResultToBean(rs, clazz);
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

	public int queryCashAccountDaoCount(HashMap<String, Object> paraMap) {
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
			sql = portRelsSqlBuilder.getQueryCashAccountCountSql(paraNameList);
			pstmt = conn.prepareStatement(sql);

			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}

			int index = 1;
			Object paraValue;
			for (String valueFieldName : paraNameList) {
				if ("N_CHECK_STATE".equals(valueFieldName)) {
					continue;
				}

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
						pstmt.setObject(index, paraMap.get(valueFieldName));
					}

				}

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
			// try {
			// if (conn != null) {
			// conn.close();
			// }
			// rs.close();
			// } catch (SQLException e) {
			// e.printStackTrace();
			// }
		}

		return recCount;
	}

	/**
	 * 客户编号
	 */
	public List<BasePojo> queryCashAccountIdDao(
			HashMap<String, Object> paraMap, PageInation page, Class<?> clazz) {
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
			sql = portRelsSqlBuilder.getQueryCashAccountIdSql(paraNameList);
			sql = buildPagingSql(sql, page);
			pstmt = conn.prepareStatement(sql);
			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}

			int index = 1;
			Object paraValue;
			for (String valueFieldName : paraNameList) {
				if ("N_CHECK_STATE".equals(valueFieldName)) {
					continue;
				}

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
						pstmt.setObject(index, paraMap.get(valueFieldName));
					}

				}

				index++;
			}
			rs = pstmt.executeQuery();

			conn.commit();
			conn.setAutoCommit(true);
			while (rs.next()) {
				BasePojo t = rsTools.ResultToBean(rs, clazz);
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

	public int queryCashAccountDaoIdCount(HashMap<String, Object> paraMap) {
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
			sql = portRelsSqlBuilder
					.getQueryCashAccountCountIdSql(paraNameList);
			pstmt = conn.prepareStatement(sql);
			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}

			int index = 1;
			Object paraValue;
			for (String valueFieldName : paraNameList) {
				if ("N_CHECK_STATE".equals(valueFieldName)) {
					continue;
				}

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
						pstmt.setObject(index, paraMap.get(valueFieldName));
					}

				}

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
			// try {
			// if (conn != null) {
			// conn.close();
			// }
			// rs.close();
			// } catch (SQLException e) {
			// e.printStackTrace();
			// }
		}

		return recCount;
	}

	/**
	 * 交易席位
	 */
	public List<BasePojo> queryTradeSeatDao(HashMap<String, Object> paraMap,
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
			conn.setAutoCommit(false);
			sql = portRelsSqlBuilder.getQueryTradeSeatSql(paraNameList);
			sql = buildPagingSql(sql, page);
			pstmt = conn.prepareStatement(sql);
			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}

			int index = 1;
			Object paraValue;
			for (String valueFieldName : paraNameList) {
				if ("N_CHECK_STATE".equals(valueFieldName)) {
					continue;
				}

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
						pstmt.setObject(index, paraMap.get(valueFieldName));
					}

				}

				index++;
			}
			rs = pstmt.executeQuery();

			conn.commit();
			conn.setAutoCommit(true);
			while (rs.next()) {
				BasePojo t = rsTools.ResultToBean(rs, clazz);
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

	public int queryTradeSeatCount(HashMap<String, Object> paraMap) {
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
			sql = portRelsSqlBuilder.getQueryTradeSeatCountSql(paraNameList);
			pstmt = conn.prepareStatement(sql);
			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}

			int index = 1;
			Object paraValue;
			for (String valueFieldName : paraNameList) {
				if ("N_CHECK_STATE".equals(valueFieldName)) {
					continue;
				}

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
						pstmt.setObject(index, paraMap.get(valueFieldName));
					}

				}

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
			// try {
			// if (conn != null) {
			// conn.close();
			// }
			// rs.close();
			// } catch (SQLException e) {
			// e.printStackTrace();
			// }
		}

		return recCount;
	}

	/**
	 * 期货公司
	 */
	public List<BasePojo> queryTradeOrgDao(HashMap<String, Object> paraMap,
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
			conn.setAutoCommit(false);
			sql = portRelsSqlBuilder.getQueryTradeOrgSql(paraNameList);
			sql = buildPagingSql(sql, page);
			pstmt = conn.prepareStatement(sql);
			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}

			int index = 1;
			Object paraValue;
			for (String valueFieldName : paraNameList) {
				if ("N_CHECK_STATE".equals(valueFieldName)) {
					continue;
				}

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
						pstmt.setObject(index, paraMap.get(valueFieldName));
					}

				}

				index++;
			}
			rs = pstmt.executeQuery();

			conn.commit();
			conn.setAutoCommit(true);
			while (rs.next()) {
				BasePojo t = rsTools.ResultToBean(rs, clazz);
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
	 * 期货公司
	 */
	public List<BasePojo> queryTradeOrgSetDao(HashMap<String, Object> paraMap,
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
			conn.setAutoCommit(false);
			sql = portRelsSqlBuilder.getQueryTradeOrgSetSql(paraNameList);
			sql = buildPagingSql(sql, page);
			pstmt = conn.prepareStatement(sql);
			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}

			int index = 1;
			Object paraValue;
			for (String valueFieldName : paraNameList) {
				if ("N_CHECK_STATE".equals(valueFieldName)) {
					continue;
				}

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
						pstmt.setObject(index, paraMap.get(valueFieldName));
					}

				}

				index++;
			}
			rs = pstmt.executeQuery();

			conn.commit();
			conn.setAutoCommit(true);
			while (rs.next()) {
				BasePojo t = rsTools.ResultToBean(rs, clazz);
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
	 * 期货公司
	 */
	public List<BasePojo> getPortRelaTdOrgDao(HashMap<String, Object> paraMap,
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
			conn.setAutoCommit(false);
			sql = portRelsSqlBuilder.getPortRelaTdOrgSql(paraNameList);
			// sql = buildPagingSql(sql, page);
			pstmt = conn.prepareStatement(sql);
			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}

			int index = 1;
			Object paraValue;
			for (String valueFieldName : paraNameList) {
				if ("N_CHECK_STATE".equals(valueFieldName)) {
					continue;
				}

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
						pstmt.setObject(index, paraMap.get(valueFieldName));
					}

				}

				index++;
			}
			rs = pstmt.executeQuery();

			conn.commit();
			conn.setAutoCommit(true);
			while (rs.next()) {
				BasePojo t = rsTools.ResultToBean(rs, clazz);
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

	public int queryTradeOrgCount(HashMap<String, Object> paraMap) {
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
			sql = portRelsSqlBuilder.getQueryTradeOrgCountSql(paraNameList);
			pstmt = conn.prepareStatement(sql);
			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}

			int index = 1;
			Object paraValue;
			for (String valueFieldName : paraNameList) {
				if ("N_CHECK_STATE".equals(valueFieldName)) {
					continue;
				}

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
						pstmt.setObject(index, paraMap.get(valueFieldName));
					}

				}

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
		}

		return recCount;
	}

	/**
	 * 投资经理
	 * 
	 * @param paraMap
	 * @param page
	 * @param clazz
	 * @return
	 */
	public List<BasePojo> queryInvestMgrDao(HashMap<String, Object> paraMap,
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
			conn.setAutoCommit(false);
			sql = portRelsSqlBuilder.getQueryInvestMgrSql(paraNameList);
			sql = buildPagingSql(sql, page);
			pstmt = conn.prepareStatement(sql);
			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}

			int index = 1;
			Object paraValue;
			for (String valueFieldName : paraNameList) {
				if ("N_CHECK_STATE".equals(valueFieldName)) {
					continue;
				}

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
						pstmt.setObject(index, paraMap.get(valueFieldName));
					}

				}

				index++;
			}
			rs = pstmt.executeQuery();

			conn.commit();
			conn.setAutoCommit(true);
			while (rs.next()) {
				BasePojo t = rsTools.ResultToBean(rs, clazz);
				/*
				 * Author : ChenLong Date : 2014-03-12 Status : Modify Comment:
				 * 投资经理没有席位的概念 所以这个字段被用来存放到岗时间
				 */
				PortRelaInvestMgr investMgr = (PortRelaInvestMgr) t;
				investMgr.setC_DV_TYPE_CODE(DateUtil.dateToString(
						rs.getDate("D_DUTY"), "yyyy-MM-dd"));
				pojoList.add(investMgr);
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

	public int queryInvestMgrCount(HashMap<String, Object> paraMap) {
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
			sql = portRelsSqlBuilder.getQueryInvestMgrCountSql(paraNameList);
			pstmt = conn.prepareStatement(sql);
			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}

			int index = 1;
			Object paraValue;
			for (String valueFieldName : paraNameList) {
				if ("N_CHECK_STATE".equals(valueFieldName)) {
					continue;
				}

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
						pstmt.setObject(index, paraMap.get(valueFieldName));
					}

				}

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
			// try {
			// if (conn != null) {
			// conn.close();
			// }
			// rs.close();
			// } catch (SQLException e) {
			// e.printStackTrace();
			// }
		}

		return recCount;
	}

	/**
	 * 指标信息
	 * 
	 * @param paraMap
	 * @param page
	 * @param clazz
	 * @return
	 */
	public List<BasePojo> queryIndexDao(HashMap<String, Object> paraMap,
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
			conn.setAutoCommit(false);
			sql = portRelsSqlBuilder.getQueryIndexSql(paraNameList);
			sql = buildPagingSql(sql, page);
			pstmt = conn.prepareStatement(sql);
			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}

			int index = 1;
			Object paraValue;
			for (String valueFieldName : paraNameList) {
				if ("N_CHECK_STATE".equals(valueFieldName)) {
					continue;
				}

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
						pstmt.setObject(index, paraMap.get(valueFieldName));
					}

				}

				index++;
			}
			rs = pstmt.executeQuery();

			conn.commit();
			conn.setAutoCommit(true);
			while (rs.next()) {
				BasePojo t = rsTools.ResultToBean(rs, clazz);
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

	public int queryIndexCount(HashMap<String, Object> paraMap) {
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
			sql = portRelsSqlBuilder.getQueryIndexCountSql(paraNameList);
			pstmt = conn.prepareStatement(sql);
			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}

			int index = 1;
			Object paraValue;
			for (String valueFieldName : paraNameList) {
				if ("N_CHECK_STATE".equals(valueFieldName)) {
					continue;
				}

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
						pstmt.setObject(index, paraMap.get(valueFieldName));
					}

				}

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
		}

		return recCount;
	}

	/**
	 * 关联机构
	 * 
	 * @param paraMap
	 * @param page
	 * @param clazz
	 * @return
	 */
	public List<BasePojo> queryOrganDao(HashMap<String, Object> paraMap,
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
			conn.setAutoCommit(false);
			sql = portRelsSqlBuilder.getQueryOrganSql(paraNameList);
			sql = buildPagingSql(sql, page);
			pstmt = conn.prepareStatement(sql);
			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}

			int index = 1;
			Object paraValue;
			for (String valueFieldName : paraNameList) {
				if ("N_CHECK_STATE".equals(valueFieldName)) {
					continue;
				}

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
						pstmt.setObject(index, paraMap.get(valueFieldName));
					}

				}

				index++;
			}
			rs = pstmt.executeQuery();

			conn.commit();
			conn.setAutoCommit(true);
			while (rs.next()) {
				BasePojo t = rsTools.ResultToBean(rs, clazz);
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

	// BUG #166485 【海通证券】同一个产品可以选择多个托管人，导致报表数据翻倍。 add by songdabang 20170803
		public int queryOrganCount(String portCode,String organ) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int num = 0;
			String sql = "";
			
			try {
				conn = this.loadNewConnection();
				conn.setAutoCommit(false);
				sql = portRelsSqlBuilder.getQueryOrganCountSql(portCode,organ);
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,portCode);
				pstmt.setString(2, organ);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					num = rs.getInt(1);
				}
			} catch (Exception ex) {
				throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
			} finally {
				this.closeResultSetFinal(rs);
				this.closeStatementFinal(pstmt);
				this.releaseConnection(conn);
			}

			return num;
		}
		
	
	public int queryOrganCount(HashMap<String, Object> paraMap) {
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
			sql = portRelsSqlBuilder.getQueryOrganCountSql(paraNameList);
			pstmt = conn.prepareStatement(sql);
			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}

			int index = 1;
			Object paraValue;
			for (String valueFieldName : paraNameList) {
				if ("N_CHECK_STATE".equals(valueFieldName)) {
					continue;
				}

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
						pstmt.setObject(index, paraMap.get(valueFieldName));
					}

				}

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
			// try {
			// if (conn != null) {
			// conn.close();
			// }
			// rs.close();
			// } catch (SQLException e) {
			// e.printStackTrace();
			// }
		}

		return recCount;
	}

	/* START 数据服务 */

	public List<PortRelaTradeSeat> getAllDataList() throws ServiceException {
		List<PortRelaTradeSeat> pojoList = new ArrayList<PortRelaTradeSeat>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		PortRelaSqlBuilder dsServiceBuilder = null;
		PortRelaTradeSeat t = null;
		try {
			dsServiceBuilder = new PortRelaSqlBuilder();
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
				t = rsTools.ResultToBeanGeneric(rs, PortRelaTradeSeat.class);
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

	public PortRelaTradeSeat getDataByCode(String code) throws ServiceException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		PortRelaSqlBuilder dsServiceBuilder = null;
		PortRelaTradeSeat t = null;
		try {
			dsServiceBuilder = new PortRelaSqlBuilder();
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
				t = rsTools.ResultToBeanGeneric(rs, PortRelaTradeSeat.class);
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

	public List<PortRelaTradeSeat> getDataListByTypes(String[] types)
			throws ServiceException {
		List<PortRelaTradeSeat> pojoList = new ArrayList<PortRelaTradeSeat>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		PortRelaSqlBuilder dsServiceBuilder = null;
		PortRelaTradeSeat t = null;
		try {
			dsServiceBuilder = new PortRelaSqlBuilder();
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
				t = rsTools.ResultToBeanGeneric(rs, PortRelaTradeSeat.class);
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

	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		HashMap<String, String> keyValueMap = new HashMap<String, String>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		// ResultSetTools rsTools = null;

		PortRelaSqlBuilder dsServiceBuilder = null;
		// PortRelaTradeSeat t = null;
		try {
			dsServiceBuilder = new PortRelaSqlBuilder();
			// rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();

			sql = dsServiceBuilder.getAllKeyDataSql();

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// t = rsTools.ResultToBeanGeneric(rs, PortRelaTradeSeat.class);
				keyValueMap.put(rs
						.getString(PortRelaTradeSeatColumnName.c_TD_CHAN_CODE
								.toString()), rs
						.getString(PortRelaTradeSeatColumnName.c_TD_CHAN_NAME
								.toString()));
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

	public List<PortRelaTradeSeat> getDataListByKeys(String[] keys)
			throws ServiceException {
		List<PortRelaTradeSeat> pojoList = new ArrayList<PortRelaTradeSeat>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		PortRelaSqlBuilder dsServiceBuilder = null;
		PortRelaTradeSeat t = null;
		try {
			dsServiceBuilder = new PortRelaSqlBuilder();
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
				t = rsTools.ResultToBeanGeneric(rs, PortRelaTradeSeat.class);
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
	 * 先删后增，根据业务主键处理 C_PORT_CODE, C_RELA_TYPE, C_RELA_CODE, C_DV_TYPE_CODE
	 * 
	 * @param pojoList
	 * @throws DataAccessException
	 */
	public <T extends BasePojo> void delInsert(List<T> pojoList)
			throws DataAccessException {
		Connection conn = null;
		boolean bTrans = false;
		try {
			if (pojoList == null || pojoList.size() == 0) {
				throw new InvalidParametersException("baseBean数据实例不能为空");
			}

			conn = this.loadNewConnection();
			conn.setAutoCommit(bTrans);
			bTrans = true;
			if (pojoList.size() > 0) {
				deleteByRela(pojoList.get(0), conn);
				insert(pojoList, conn);
			}
			conn.commit();
			conn.setAutoCommit(bTrans);
			bTrans = false;
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("产品关联信息：更新关联信息失败", ex);
			throw new DataAccessException("更新失败：" + ex.getMessage(), ex);
		} finally {
			this.endTransFinal(conn, bTrans);
			this.releaseConnection(conn);
		}
	}

	/**
	 * C_PORT_CODE, C_RELA_TYPE, C_RELA_CODE, C_DV_TYPE_CODE 改为根据业务主键删除
	 */
	public <T extends BaseBean> void deleteByRela(BaseBean bean, Connection conn)
			throws DataAccessException {
		PreparedStatement pstmt = null;
		try {
			String sql = portRelsSqlBuilder.getDelInsertSql();
			pstmt = conn.prepareStatement(sql);
			PortRela portRela = (PortRela) bean;
			pstmt.setString(1, portRela.getC_DV_TYPE_CODE());
			pstmt.setString(2, portRela.getC_RELA_CODE());
			pstmt.setString(3, portRela.getC_RELA_TYPE());
			pstmt.executeUpdate();
		} catch (Exception ex) {
			throw new DataAccessException("删除失败：" + ex.getMessage(), ex);
		} finally {
			this.closeStatementFinal(pstmt);
		}
	}

	public <T extends BaseBean> void delete(List<T> pojoList, Connection conn)
			throws DataAccessException {
		PreparedStatement pstmt = null;
		try {
			String sql = portRelsSqlBuilder.getDelSql();
			pstmt = conn.prepareStatement(sql);
			for (BaseBean bean : pojoList) {
				PortRela portRela = (PortRela) bean;
				pstmt.setString(1, portRela.getC_DV_TYPE_CODE());
				pstmt.setString(2, portRela.getC_RELA_CODE());
				pstmt.setString(3, portRela.getC_RELA_TYPE());
				pstmt.setString(4, portRela.getC_PORT_CODE());
				pstmt.addBatch();
			}
			pstmt.executeBatch();
		} catch (Exception ex) {
			throw new DataAccessException("删除失败：" + ex.getMessage(), ex);
		} finally {
			this.closeStatementFinal(pstmt);
		}
	}
	
	
	/**
	 * 先删后增，根据业务主键处理 C_PORT_CODE, C_RELA_TYPE, C_RELA_CODE, C_DV_TYPE_CODE
	 * 
	 * @param pojoList
	 * @throws DataAccessException
	 */
	public <T extends BasePojo> void deleteByYwId(List<T> pojoList)
			throws DataAccessException {
		Connection conn = null;
		boolean bTrans = false;
		try {
			if (pojoList == null || pojoList.size() == 0) {
				throw new InvalidParametersException("baseBean数据实例不能为空");
			}

			conn = this.loadNewConnection();
			conn.setAutoCommit(bTrans);
			bTrans = true;
			if (pojoList.size() > 0) {
				delete(pojoList, conn);
			}
			conn.commit();
			conn.setAutoCommit(bTrans);
			bTrans = false;
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("产品关联信息：根据业务主键删除数据失败", ex);
			throw new DataAccessException("更新失败：" + ex.getMessage(), ex);
		} finally {
			this.endTransFinal(conn, bTrans);
			this.releaseConnection(conn);
		}
	}
	/* END 数据服务 */

	/**
	 * @author liuxiang 2016-5-20 STORY #28860 上交所跨市场ETF基金产品整体需求 
	 * @param paraMap
	 * @param page
	 * @param clazz
	 * @return
	 */
	public List<BasePojo> queryMemberDao(HashMap<String, Object> paraMap,
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
			conn.setAutoCommit(false);
			sql = portRelsSqlBuilder.getQueryMemberSql(paraNameList);
			sql = buildPagingSql(sql, page);
			pstmt = conn.prepareStatement(sql);
			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}

			int index = 1;
			Object paraValue;
			for (String valueFieldName : paraNameList) {
				if ("N_CHECK_STATE".equals(valueFieldName)) {
					continue;
				}

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
						pstmt.setObject(index, paraMap.get(valueFieldName));
					}

				}

				index++;
			}
			rs = pstmt.executeQuery();

			conn.commit();
			conn.setAutoCommit(true);
			while (rs.next()) {
				BasePojo t = rsTools.ResultToBean(rs, clazz);
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
	 * @author liuxiang 2016-5-20 STORY #28860 上交所跨市场ETF基金产品整体需求 
	 * @param paraMap
	 * @return
	 */
	public int queryMemberCount(HashMap<String, Object> paraMap) {
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
			sql = portRelsSqlBuilder.getQueryMemberCountSql(paraNameList);
			pstmt = conn.prepareStatement(sql);
			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}

			int index = 1;
			Object paraValue;
			for (String valueFieldName : paraNameList) {
				if ("N_CHECK_STATE".equals(valueFieldName)) {
					continue;
				}

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
						pstmt.setObject(index, paraMap.get(valueFieldName));
					}

				}

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
		}

		return recCount;
	}
	
	/**
	 * Author : ChenLong
	 * Date   : 2016-09-21
	 * Status : Add
	 * Comment: 检测组合是否已经关联委托人机构了
	 * @param Map
	 * @return
	 */
	public String checkORGConsignerForPort(HashMap<String,String> map){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			String sql = ((PortRelaSqlBuilder)sqlbuilder).getCheckORGConsignerForPortSQL();
			conn = this.loadNewConnection();
			pstmt = conn.prepareStatement(sql);
			String[] ports = map.get("ARRAY_PORT_CODE").split("\\|");
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(ports, conn));
			rs = pstmt.executeQuery();
			if(rs.next()){
				flag = true;
			}
		}catch(Exception ex){
			throw new BusinessException("检测组合是否已经存在委托人出错!", ex);
		}finally{
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		
		return String.valueOf(flag);
	}
	
	/**
	 * Author : ChenLong
	 * Date   : 2016-11-03
	 * Status : Add
	 * Comment: 查询计税委托人下的组合
	 * @param taxConsigner 计税委托人
	 * @return
	 */
	public List<Port_A> getPortInfoByConsigner(String taxConsigner){
		List<Port_A> list = new ArrayList<Port_A>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			StringBuilder sqlBuilder = new StringBuilder();
			sqlBuilder.append(" select t1.c_port_code,t2.c_port_name from t_p_ab_port_rela t1");
			sqlBuilder.append(" left join t_p_ab_port t2 on t1.c_port_code = t2.c_port_code");
			sqlBuilder.append(" where c_dv_type_code = 'CONSIGNER' and c_rela_code = ?");
			sqlBuilder.append(" and t1.n_check_state = 1");
			sqlBuilder.append(" order by t1.c_port_code");
			conn = this.loadNewConnection();
			pstmt = conn.prepareStatement(sqlBuilder.toString());
			pstmt.setString(1, taxConsigner);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Port_A port = new Port_A();
				String portCode = rs.getString("c_port_code");
				String portName = rs.getString("c_port_name");
				port.setC_PORT_CODE(portCode);
				port.setC_PORT_NAME(portName);
				list.add(port);
			}
		}catch(Exception ex){
			throw new BusinessException("查询计税委托人关联的组合");
		}finally{
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return list;
	}
	
	/**
	 * @author guohui 2017-09-04 STORY #37768 【南方基金】组合可以设置绑定多个现金账户，当有界面筛选现金账户时，只显示绑定的现金账户
	 * @param paraMap
	 * @param page
	 * @param clazz
	 * @return
	 */
	public List<BasePojo> queryCashAccDao(HashMap<String, Object> paraMap,
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
			conn.setAutoCommit(false);
			sql = portRelsSqlBuilder.getQueryCashAccSql(paraNameList);
			sql = buildPagingSql(sql, page);
			pstmt = conn.prepareStatement(sql);
			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}

			int index = 1;
			Object paraValue;
			for (String valueFieldName : paraNameList) {
				if ("N_CHECK_STATE".equals(valueFieldName)) {
					continue;
				}

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
						pstmt.setObject(index, paraMap.get(valueFieldName));
					}

				}

				index++;
			}
			rs = pstmt.executeQuery();

			conn.commit();
			conn.setAutoCommit(true);
			while (rs.next()) {
				BasePojo t = rsTools.ResultToBean(rs, clazz);
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

	public HashMap<String, List<String>> getPortRelaOrgByPortAndDvType(
			String portCodes, String dvType) {
		HashMap<String, List<String>> map = new HashMap<String, List<String>>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			String sql = ((PortRelaSqlBuilder)sqlbuilder).getPortRelaOrgByPortAndDvType();
			conn = this.loadNewConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(portCodes, conn));
			pstmt.setString(2, dvType);
			rs = pstmt.executeQuery();
			while(rs.next()){
				String portCode = rs.getString("C_PORT_CODE");
				String orgCode = rs.getString("C_RELA_CODE");
				if(map.containsKey(portCode)){
					List<String> orgCodeList = map.get(portCode);
					orgCodeList.add(orgCode);
					map.put(portCode, orgCodeList);
				}else{
					List<String> orgCodeList = new ArrayList<String>();
					orgCodeList.add(orgCode);
					map.put(portCode, orgCodeList);
				}
			}
		}catch(Exception ex){
			throw new BusinessException("检测组合是否已经存在委托人出错!", ex);
		}finally{
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		
		return map;
	}
}
