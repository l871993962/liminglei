package com.yss.ams.base.information.modules.bi.account.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.context.ContextFactory;
import com.yss.framework.db.OraDbTool;

/**
 * STORY #76292 【4.5同步】同步“机构名称”、“账户明细类型”、“关联组合”至“现金账户”
 * @author lenovo
 *
 */
public class PortRelaDao extends GeneralDao {

	private PortRelaSqlBuilder builder = null;
	
	public PortRelaDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		builder = (PortRelaSqlBuilder) sqlBuilder;
	}
	
	/**
	 * 查询现金账户信息关联组合
	 * @param relaCode
	 * @return
	 */
	public String getCashPortByInfo(String relaCode) {
		StringBuffer portCodes = new StringBuffer();
		PreparedStatement pst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = this.loadNewConnection();
			String sql = builder.getPortCodeSql();
			pst = conn.prepareStatement(sql);
			pst.setString(1, relaCode);
			rs = pst.executeQuery();
			while (rs.next()) {
				portCodes.append(rs.getString("C_PORT_CODE")).append(",");
			}
			if (portCodes.length() > 0) {
				portCodes.deleteCharAt(portCodes.length() - 1);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询现金账户信息出错：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pst);
			releaseConnection(conn);
		}
		return portCodes.toString();
	}
	
	/**
	 * 批量删除账号组合关联关系
	 * @param relaCode
	 * @param portCodes
	 */
	public void deletePortRela(String relaCode, String portCodes) {
		PreparedStatement pstmt = null;
		Connection conn = null;
		try {
			conn = this.loadNewConnection();
			String sql = builder.getDeletePortRelaSql();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, relaCode);
			pstmt.setArray(2, OraDbTool.newInstance().sqlOverLongCondition(portCodes, conn));
			pstmt.executeUpdate();
		} catch (Exception ex) {
			throw new DataAccessException("删除账号组合关联关系失败：" + ex.getMessage(), ex);
		} finally {
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
	}
	
	/**
	 * 批量更新账号组合关联关系
	 * @param relaCode
	 * @param portCodes
	 */
	public void updatePortRela(String relaCode, String portCodes) {
		PreparedStatement pstmt = null;
		Connection conn = null;
		try {
			String userCode = ContextFactory.getContext().getUserCode();
			userCode = StringUtil.IsNullOrEmpty(userCode) ? " " : userCode;
			
			conn = this.loadNewConnection();
			String sql = builder.getUpdatePortRelaSql();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, relaCode);
			pstmt.setString(2, userCode);
			pstmt.setArray(3, OraDbTool.newInstance().sqlOverLongCondition(portCodes, conn));
			pstmt.executeUpdate();
			
			// 组合不存在就插入
			StringBuffer insertPortCodes = new StringBuffer();
			String[] portCodeStr = portCodes.split(",");
			for (int i = 0; i < portCodeStr.length; i++) {
				String portCode = portCodeStr[i];
				if (getRelaPortCount(portCode) == 0) {
					insertPortCodes.append(portCode).append(",");
				}
			}
			if (insertPortCodes.length() > 0) {
				insertPortCodes.deleteCharAt(insertPortCodes.length() - 1);
				insertPortRela(relaCode, insertPortCodes.toString());
			}
		} catch (Exception ex) {
			throw new DataAccessException("更新账号组合关联关系失败：" + ex.getMessage(), ex);
		} finally {
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
	}
	
	/**
	 * 批量保存账号组合关联关系
	 * @param relaCode
	 * @param portCodes
	 */
	public void insertPortRela(String relaCode, String portCodes) {
		PreparedStatement pstmt = null;
		Connection conn = null;
		try {
			StringBuffer updatePortCodes = new StringBuffer();
			String userCode = ContextFactory.getContext().getUserCode();
			userCode = StringUtil.IsNullOrEmpty(userCode) ? " " : userCode;
			
			conn = this.loadNewConnection();
			String sql = builder.getInsertPortRelaSql();
			pstmt = conn.prepareStatement(sql);
			String[] portCodeStr = portCodes.split(",");
			for (int i = 0; i < portCodeStr.length; i++) {
				String portCode = portCodeStr[i];
				if (getRelaPortCount(portCode) > 0) {
					updatePortCodes.append(portCode).append(",");
					continue;
				}
				pstmt.setString(1, portCode);
				pstmt.setString(2, relaCode);
				pstmt.setString(3, userCode);
				pstmt.setString(4, portCode);
				pstmt.addBatch();
			}
			pstmt.executeBatch();
			
			// 组合存在就更新
			if (updatePortCodes.length() > 0) {
				updatePortCodes.deleteCharAt(updatePortCodes.length() - 1);
				updatePortRela(relaCode, updatePortCodes.toString());
			}
		} catch (Exception ex) {
			throw new DataAccessException("保存账号组合关联关系失败：" + ex.getMessage(), ex);
		} finally {
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
	}
	
	/**
	 * 获取关联现金账户的组合数量
	 * @param portCode
	 * @return
	 */
	private int getRelaPortCount(String portCode) {
		int count = 0;
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pst = null;
		try {
			conn = this.loadNewConnection();
			String sql = builder.getRelaPortCountSql();
			pst = conn.prepareStatement(sql);
			pst.setString(1, portCode);
			rs = pst.executeQuery();
			if (rs.next()) {
				count = rs.getInt("PORT_COUNT");
			}
		} catch (Exception ex) {
			throw new DataAccessException("获取关联现金账户的组合数量出错：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pst);
			releaseConnection(conn);
		}
		return count;
	}
}
