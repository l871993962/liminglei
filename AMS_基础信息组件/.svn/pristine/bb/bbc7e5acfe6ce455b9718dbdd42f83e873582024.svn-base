package com.yss.ams.base.information.modules.bi.accountTreeA.dao;

import java.beans.PropertyDescriptor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.bi.account.pojo.FundAcc;
import com.yss.ams.base.information.support.bi.accountTreeA.pojo.AccountTreeA;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.util.PojoUtils;

public class AccountTreeADao extends GeneralDao {
	private AccountTreeASqlBuilder builder = null;

	public AccountTreeADao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		builder = (AccountTreeASqlBuilder) sqlBuilder;
	}

	public HashMap<String, String> getKeyConvertMap() {
		HashMap<String, String> map = new HashMap<String, String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = this.loadNewConnection();
			String sql = builder
					.getQueryConditionSql(new ArrayList<String>());
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				map.put(rs.getString("C_NODE_CODE"), rs.getString("C_NODE_NAME"));
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询数据失败", ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataList() {
		List<AccountTreeA> pojoList = new ArrayList<AccountTreeA>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		AccountTreeA t = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, builder);

			conn = this.loadNewConnection();

			sql = builder.getQueryConditionSql(new ArrayList<String>());

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);

			rs = pstmt.executeQuery();
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(AccountTreeA.class.newInstance());
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, AccountTreeA.class,props);
				pojoList.add(t);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return (List<T>)pojoList;
	}

	@SuppressWarnings("unchecked")
	public <T extends BasePojo> T getDataByCode(String dataCode) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;
		List<String> paraNameList= new ArrayList<String>();
		paraNameList.add("C_NODE_CODE");
		AccountTreeA t = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, builder);

			conn = this.loadNewConnection();

			sql = builder.getQueryConditionSql(paraNameList);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dataCode);
			logger.debug(sql);

			rs = pstmt.executeQuery();
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(AccountTreeA.class.newInstance());
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, AccountTreeA.class,props);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		
		return (T)t;
	}

}
