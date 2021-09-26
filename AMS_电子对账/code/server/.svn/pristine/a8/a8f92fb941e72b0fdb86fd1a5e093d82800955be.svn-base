package com.yss.uco.elecreco.er.spilt.rule.dao;
import java.beans.PropertyDescriptor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.uco.elecreco.er.erkmb.pojo.ErKmb;
import com.yss.uco.elecreco.er.spilt.rela.pojo.ErSplitRela;
import com.yss.uco.elecreco.er.spilt.rule.pojo.ErSplitRule;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.exception.YssRuntimeException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.util.PojoUtils;

public class ErSplitRuleDao extends GeneralDao  {

private ErSplitRuleSqlBuilder sqlBuilder = null;
	public ErSplitRuleDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		this.sqlBuilder = (ErSplitRuleSqlBuilder) sqlBuilder;
	}

	private String saveRelaDetailKmInfo(List<ErSplitRule> rules,Connection conn)
	{
		String result = "false";
		insert(rules, conn);
		result = "true";
		return result;
	}
	
	private String removeRelaDetailKmInfo(List<ErSplitRule> rules,Connection conn) throws Exception
	{
		String result = "false";
		for(ErSplitRule rule : rules)
		{
			HashMap<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("C_KM_CODE", rule.getC_KM_CODE());
			//BUG224960北京-【新华资产】电子对账【高】（拆分完科目，再次修改或新增拆分科目会导致已拆分的科目遗失）
			paraMap.put("C_IDEN_RELA", rule.getC_IDEN_RELA());
			List<BasePojo> list = this.queryByCondition(paraMap, ErSplitRule.class);
			deleteById(list);
		}
		result = "true";
		return result;
	}
	
	public String updateRelaDetailKmInfo(List<ErSplitRule> addRules,List<ErSplitRule> removeRules) throws Exception
	{
		String result = "false";
		Connection conn = null;
		try {
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			if (addRules != null && addRules.size() > 0) {
				saveRelaDetailKmInfo(addRules, conn);
			}
			if (removeRules != null && removeRules.size() > 0) {
				removeRelaDetailKmInfo(removeRules, conn);
			}
			conn.commit();
			conn.setAutoCommit(true);
			result = "true";
		} catch (Exception ex) {
			if(ex instanceof YssRuntimeException){
				throw ex;
			}else{
				logger.error("删除失败：" + ex.getMessage());
				throw new DataAccessException("删除失败：" + ex.getMessage(), ex);
			}
		} finally {
			this.releaseConnection(conn);
		}
		return result;
	}


	public List<BasePojo> showRelaDetailKmInfo(String id) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			conn = this.loadNewConnection();
			sql = this.sqlBuilder.getSplitRulesBySplitRelaSql();
			pstmt = conn.prepareStatement(sql);
			int i = 1;
			pstmt.setString(i++, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ErKmb pojo = new ErKmb();
				pojo.setC_KM_CODE(rs.getString("C_KM_CODE"));
				pojo.setC_KM_NAME(rs.getString("C_KM_NAME"));
				//只有明细科目
				pojo.setN_DETAIL(1);
				pojoList.add(pojo);
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


	public List<BasePojo> showUnSplitDetailKmInfo(String portCode, String date) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			conn = this.loadNewConnection();
			sql = this.sqlBuilder.getKmInfoSqlByPortWithDate();
			pstmt = conn.prepareStatement(sql);
			int index = 1;
			pstmt.setString(index++, portCode);
			pstmt.setString(index++, date);
			pstmt.setString(index++, date);
			pstmt.setString(index++, portCode);
			pstmt.setString(index++, date);
			pstmt.setString(index++, portCode);
			pstmt.setString(index++, date);
			rs = pstmt.executeQuery();
			Map<String, ErSplitRule> map = getSplitRuleMapBySplitRela(portCode);
			ErKmb kmb = null;
			while (rs.next()) {
				kmb = getErKmByRs(rs);
				//排除掉已经添加过映射关系的科目
				if(!map.containsKey(kmb.getC_KM_CODE()))
				{
					pojoList.add(kmb);
				}
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
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private ErKmb getErKmByRs(ResultSet rs) throws SQLException
	{
		ErKmb pojo = new ErKmb();
		pojo.setC_KM_CODE(rs.getString("C_KM_CODE"));
		pojo.setC_KM_CODE_P(rs.getString("C_KM_CODE_P"));
		pojo.setC_KM_NAME(rs.getString("C_KM_NAME"));
		pojo.setN_DETAIL(rs.getInt("N_DETAIL"));
		return pojo;
	}
	
	public Map<String,ErSplitRule> getSplitRuleMapBySplitRela(String portCode) {
		Map<String,ErSplitRule> map = new HashMap<String, ErSplitRule>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";

		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			conn = this.loadNewConnection();
			sql = this.sqlBuilder.getSplitRulesSqlByPortCode();
			pstmt = conn.prepareStatement(sql);
			int i = 1;
			pstmt.setString(i++, portCode);
			rs = pstmt.executeQuery();
			ErSplitRule pojo = new ErSplitRule();
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(pojo);
			while (rs.next()) {
				pojo = (ErSplitRule) setResultSet(rsTools, rs, pojo.getClass(), props);
				getConvertKey(props, pojo);
				map.put(pojo.getC_KM_CODE(), pojo);
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

	public List<ErSplitRule> getSplitRulesBySplitRela(ErSplitRela rela) {
		List<ErSplitRule> pojoList = new ArrayList<ErSplitRule>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";

		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			conn = this.loadNewConnection();
			sql = this.sqlBuilder.getSplitRulesBySplitRelaSql();
			pstmt = conn.prepareStatement(sql);
			int i = 1;
			pstmt.setString(i++, rela.getId());
			rs = pstmt.executeQuery();
			ErSplitRule pojo = new ErSplitRule();
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(pojo);
			while (rs.next()) {
				pojo = (ErSplitRule) setResultSet(rsTools, rs, pojo.getClass(), props);
				getConvertKey(props, pojo);
				pojoList.add(pojo);
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