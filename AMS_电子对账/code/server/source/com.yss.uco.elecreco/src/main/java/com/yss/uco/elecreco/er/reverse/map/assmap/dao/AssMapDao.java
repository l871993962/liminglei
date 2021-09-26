package com.yss.uco.elecreco.er.reverse.map.assmap.dao;
import java.beans.PropertyDescriptor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.yss.uco.elecreco.er.reverse.map.assmap.pojo.AssMap;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.util.PojoUtils;

public class AssMapDao extends GeneralDao  {

private AssMapSqlBuilder sqlBuilder = null;
	public AssMapDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		this.sqlBuilder = (AssMapSqlBuilder) sqlBuilder;
	}
	
	/**
	 * 根据组合代码获取适用于所有对账类型的资产映射（对账类型为空）
	 * @param portCode
	 * @return
	 */
	public List<AssMap> getCommonAssMapByPortCode(String portCode) {
		List<AssMap> pojoList = new ArrayList<AssMap>();
	

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			
			conn = this.loadNewConnection();
			sql = this.sqlBuilder.getCommonAssMapSqlByPortCode();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, portCode);
			rs = pstmt.executeQuery();
			AssMap pojo = new AssMap();
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(pojo);
			while (rs.next()) {
				AssMap t = (AssMap) setResultSet(rsTools,rs, pojo.getClass(), props); // 提供可以重写的方法byleeyu20130420 
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
	
	
	/**
	 * 根据组合代码和对账类型获取资产映射
	 * @param portCode
	 * @param fileType
	 * @return
	 */
	public List<AssMap> getAssMapByPortCodeAndFileType(String portCode,String fileType) {
		List<AssMap> pojoList = new ArrayList<AssMap>();
	

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			
			conn = this.loadNewConnection();
			sql = this.sqlBuilder.getAssMapSqlByPortCodeAndFileType();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, portCode);
			pstmt.setString(2, fileType);
			rs = pstmt.executeQuery();
			AssMap pojo = new AssMap();
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(pojo);
			while (rs.next()) {
				AssMap t = (AssMap) setResultSet(rsTools,rs, pojo.getClass(), props); // 提供可以重写的方法byleeyu20130420 
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
	
	public String getDzMode(String portCode, String fileType) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		String dzMode = "";
		try {
			conn = this.loadNewConnection();
			// conn.setAutoCommit(false);
			sql = sqlBuilder.getDzModeSql();
			
			
			pstmt = conn.prepareStatement(sql);

			int index = 1;
			pstmt.setString(index++, portCode);
			pstmt.setString(index++, fileType);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dzMode = rs.getString("C_DV_DZ_MODE");
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询对账模式失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return dzMode;
	}
	
	public List<String> getTghCodesByPortCode(String portCode) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		List<String> tghCodes = new ArrayList<String>();
		try {
			conn = this.loadNewConnection();
			// conn.setAutoCommit(false);
			sql = sqlBuilder.getTghCodesByPortCode();
			pstmt = conn.prepareStatement(sql);
			int index = 1;
			pstmt.setString(index++, portCode);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String s = rs.getString("C_TGH_CODE");
				tghCodes.add(s);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询托管行失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return tghCodes;
	}
	

}