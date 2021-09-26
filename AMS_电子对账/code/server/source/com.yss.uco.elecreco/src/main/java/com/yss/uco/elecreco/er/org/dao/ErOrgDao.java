package com.yss.uco.elecreco.er.org.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.uco.elecreco.er.org.pojo.ErOrg;

/**
 * BUG230634电子对账界面卡顿严重
 * @author Lenovo
 *
 */
public class ErOrgDao extends GeneralDao {

	private ErOrgSqlBuilder orgSqlBuilder;
	
	public ErOrgDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		this.orgSqlBuilder = (ErOrgSqlBuilder)sqlBuilder;
	}
	
	public List<ErOrg> getTrusteeOrgs()
	{
		List<ErOrg> pojoList = new ArrayList<ErOrg>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		Set<String> set = new HashSet<String>();
		try {
			conn = this.loadNewConnection();
			//基类需重新实现此方法
			sql = orgSqlBuilder.getTrusteeOrgs();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ErOrg org = createOrg(rs);
				//BUG268295电子对账模块对账指标关联set页面托管行栏位加载托管行数量有误 去重
				if(!set.contains(org.getC_ORG_CODE()))
				{
					set.add(org.getC_ORG_CODE());
					pojoList.add(org);
				}
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
	public List<ErOrg> getManagerOrgs()
	{
		List<ErOrg> pojoList = new ArrayList<ErOrg>();
		Set<String> set = new HashSet<String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			conn = this.loadNewConnection();
			//基类需重新实现此方法
			sql = orgSqlBuilder.getManagerOrgs();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ErOrg org = createOrg(rs);
				//BUG268295电子对账模块对账指标关联set页面托管行栏位加载托管行数量有误 去重
				if(!set.contains(org.getC_ORG_CODE()))
				{
					set.add(org.getC_ORG_CODE());
					pojoList.add(org);
				}
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
	 * 
	 * @return
	 * @throws SQLException 
	 */
	private ErOrg createOrg(ResultSet rs) throws SQLException
	{
		ErOrg org = new ErOrg();
		org.setC_ORG_CODE(rs.getString("C_ORG_CODE"));
		org.setC_ORG_NAME(rs.getString("C_ORG_NAME"));
		org.setC_ORG_CODE_P(rs.getString("C_ORG_CODE_P"));
		return org;
	}

}
