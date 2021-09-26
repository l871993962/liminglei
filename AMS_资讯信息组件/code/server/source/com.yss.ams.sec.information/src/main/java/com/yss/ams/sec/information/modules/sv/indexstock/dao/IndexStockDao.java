package com.yss.ams.sec.information.modules.sv.indexstock.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.sec.information.support.modules.sv.indexstock.pojo.IndexStock;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.exception.InvalidParametersException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;


/**
 * 
 * @author chenbo
 *2017-06-22
 *#42948 资讯信息管理组件化拆分
 */
public class IndexStockDao extends GeneralDao {

	private IndexStockSqlBuilder indexStockSqlBuilder = null;

	public IndexStockDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		indexStockSqlBuilder = (IndexStockSqlBuilder) sqlBuilder;
	}

	/**
	 * 获得已选择证券MAP
	 * 
	 * @param c_Index_Code
	 *            指数代码
	 * @param d_Begin
	 *            启用日期
	 * @return 已选证券内码
	 * @throws Exception
	 */
	public HashMap<String, String> getSelectedSecMap(String c_Index_Code,
			String d_Begin) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			conn = this.loadNewConnection();
			sql = indexStockSqlBuilder.getSelectedSecMapSql();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c_Index_Code);
			pstmt.setString(2, d_Begin);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				map.put(rs.getString("C_SEC_CODE"), rs.getString("C_SEC_CODE"));
			}
		} catch (SQLException e) {
			throw (e);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return map;
	}

	/**
	 * 更新数据(先删除后插入)
	 * 
	 * @param pojoList
	 * @throws Exception
	 */
	public void updateList(List<BasePojo> pojoList) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			if (pojoList == null) {
				throw new InvalidParametersException("list数据不能为空");
			}

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			// // 先根据指数代码和启用日期删除数据
			IndexStock pojo = (IndexStock) pojoList.get(0);
			deleteByCodeAndDate(pojo.getC_INDEX_CODE(), pojo.getD_BEGIN(), conn);

			// //在插入数据
			insert(pojoList, conn);

			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			throw (e);
		} finally {
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
	}

	/**
	 * 根据指数代码和启用日期删除数据
	 * 
	 * @param c_INDEX_CODE
	 *            指数代码
	 * @param d_BEGIN
	 *            启用日期
	 * @param conn
	 *            连接
	 */
	private void deleteByCodeAndDate(String c_INDEX_CODE, Date d_BEGIN,
			Connection conn) {
		PreparedStatement pstmt = null;
		String sql = "";
		try {
			sql = indexStockSqlBuilder.getDeleteByCodeAndDateSql();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c_INDEX_CODE);
			pstmt.setDate(2, DbFun.sqlDate(d_BEGIN));
			pstmt.executeUpdate();
		} catch (Exception ex) {
			throw new DataAccessException("删除失败：" + ex.getMessage(), ex);
		} finally {
			this.closeStatementFinal(pstmt);
		}
	}
	
	/**
	 * 获得最近已选择证券MAP
	 * 
	 * @param c_Index_Code
	 *            指数代码
	 * @param d_Begin
	 *            启用日期
	 * @return 已选证券内码
	 * @throws Exception
	 */
	public HashMap<String, String> getLastSelectedSecMap(String c_Index_Code,
			String d_Begin) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			conn = this.loadNewConnection();
			sql = indexStockSqlBuilder.getLastSelectedSecMapSql();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c_Index_Code);
			pstmt.setString(2, d_Begin);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				map.put(rs.getString("C_SEC_CODE"), rs.getString("C_SEC_CODE"));
			}
		} catch (SQLException e) {
			throw (e);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return map;
	}
}
