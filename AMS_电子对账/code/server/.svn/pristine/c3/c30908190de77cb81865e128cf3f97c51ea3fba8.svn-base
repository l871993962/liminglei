package com.yss.uco.elecreco.er.repcolcfg.dao;
import java.beans.PropertyDescriptor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.util.PojoUtils;
import com.yss.uco.elecreco.er.repcolcfg.pojo.DzRepColCfg;

public class DzRepColCfgDao extends GeneralDao  {

	public DzRepColCfgDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
	}
	public List<DzRepColCfg> getDzRepColCfgs(String dzType, String reportCode)
	{
		List<DzRepColCfg> pojoList = new ArrayList<DzRepColCfg>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			conn = this.loadNewConnection();
			String sql = ((DzRepColCfgSqlBuilder) sqlbuilder).getDzRepColCfgsSql();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dzType);
			pstmt.setString(2, reportCode);
			rs = pstmt.executeQuery();
			DzRepColCfg pojo = new DzRepColCfg();
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(pojo);
			while (rs.next()) {
				DzRepColCfg t = (DzRepColCfg) setResultSet(rsTools,rs, DzRepColCfg.class, props); // 提供可以重写的方法byleeyu20130420 
				getConvertKey(props,t);
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
//	public boolean isHaveCfg(String reportCode) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			conn = this.loadNewConnection();
//			String sql = ((DzRepColCfgSqlBuilder) sqlbuilder).getIsHaveCfgSql();
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, reportCode);
//			rs = pstmt.executeQuery();
//			if (rs.next()) {
//				return true;
//			}
//		} catch (Exception ex) {
//			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
//		} finally {
//			this.closeResultSetFinal(rs);
//			this.closeStatementFinal(pstmt);
//			this.releaseConnection(conn);
//		}
//		return false;
//	}
}