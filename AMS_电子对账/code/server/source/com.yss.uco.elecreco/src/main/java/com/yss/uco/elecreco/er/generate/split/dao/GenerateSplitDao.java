package com.yss.uco.elecreco.er.generate.split.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.uco.elecreco.er.erkmb.pojo.ErKmb;
import com.yss.uco.elecreco.er.spilt.rela.dao.ErSplitRelaSqlBuilder;
import com.yss.uco.elecreco.er.spilt.rela.pojo.ErSplitRela;
import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.sql.DefaultDBNameResolver;

public class GenerateSplitDao {
	protected GenerateSplitSqlBuilder sqlBuilder = null;
	public GenerateSplitDao(GenerateSplitSqlBuilder sqlBuilder)
	{
		this.sqlBuilder = sqlBuilder;
	}
	public ErSplitRela getErSplitRela(String portCode, String geneDate,
			String tghCode, String splitCode, Connection conn) throws Exception {
		ErSplitRela rela = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			DefaultDBNameResolver dbNameResolver = new DefaultDBNameResolver();
			ResultSetTools rsTools = new ResultSetTools(dbNameResolver,
					new ErSplitRelaSqlBuilder());
			stat = conn.prepareStatement(sqlBuilder.getErSplitRelaSql(splitCode));
			int index = 1;
			stat.setString(index++, portCode);
			stat.setString(index++, geneDate);
			stat.setString(index++, geneDate);
			stat.setString(index++, tghCode);
			stat.setString(index++, splitCode);
			rs = stat.executeQuery();
			if (rs.next()) {
				rela = (ErSplitRela) rsTools.ResultToBean(rs, ErSplitRela.class);
			}
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(stat);
		}
		return rela;
	}

	public List<ErSplitRela> getErSplitRelas(String portCode, String date,
			Connection conn) throws Exception {
		List<ErSplitRela> relas = new ArrayList<ErSplitRela>();
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			DefaultDBNameResolver dbNameResolver = new DefaultDBNameResolver();
			ResultSetTools rsTools = new ResultSetTools(dbNameResolver,
					new ErSplitRelaSqlBuilder());
			stat = conn.prepareStatement(sqlBuilder.getErSplitRelasSql());
			int index = 1;
			stat.setString(index++, portCode);
			stat.setString(index++, date);
			stat.setString(index++, date);
			rs = stat.executeQuery();
			while (rs.next()) {
				ErSplitRela rela = (ErSplitRela) rsTools.ResultToBean(rs, ErSplitRela.class);
				relas.add(rela);
			}
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(stat);
		}
		return relas;
	}
	
	public Map<String,String> getHaveRuleKmSqlByPortCode(String portCode,Connection conn) {
		Map<String,String> map = new HashMap<String, String>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			sql = this.sqlBuilder.getHaveRuleKmSqlByPortCode();
			pstmt = conn.prepareStatement(sql);
			int i = 1;
			pstmt.setString(i++, portCode);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				map.put(rs.getString("C_KM_CODE"), rs.getString("C_KM_NAME"));
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pstmt);
		}
		return map;
	}

	public List<ErKmb> getUnSplitRuleDetailKm(String portCode,String date,Connection conn) throws SQLException {
		List<ErKmb> kms = new ArrayList<ErKmb>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Map<String, String> map = this.getHaveRuleKmSqlByPortCode(portCode, conn);
			pstmt = conn.prepareStatement(sqlBuilder.getUnSplitRuleDetailKmSql());
			int index = 1;
			pstmt.setString(index++, portCode);
			pstmt.setString(index++, date);
			pstmt.setString(index++, date);
			pstmt.setString(index++, portCode);
			pstmt.setString(index++, date);
			pstmt.setString(index++, portCode);
			pstmt.setString(index++, date);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String code = rs.getString("C_KM_CODE");
				if("1".equalsIgnoreCase(rs.getString("N_DETAIL")) && !map.containsKey(code))
				{
					ErKmb km = new ErKmb();
					km.setC_KM_CODE(code);
					km.setC_KM_NAME(rs.getString("C_KM_NAME"));
					kms.add(km);
				}
			}
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pstmt);
		}
		return kms;
	}
	public Map<String,String> getSplitRuleDetailKmBySplitRela(String relaId,
			Connection conn) {
		Map<String,String> map = new HashMap<String, String>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			sql = this.sqlBuilder.getSplitRuleDetailKmBySplitRelaSql();
			pstmt = conn.prepareStatement(sql);
			int i = 1;
			pstmt.setString(i++, relaId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				map.put(rs.getString("C_KM_CODE"), rs.getString("C_KM_NAME"));
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pstmt);
		}
		return map;
	}
	
}
