package com.yss.uco.elecreco.er.erresview.dao;

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
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.db.OraDbTool;
import com.yss.uco.elecreco.er.erresview.pojo.ErResview;

public class ErResviewDao extends GeneralDao {

	
	public ErResviewDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
	}

	public ErResview getDataByCode(String pojoCode) throws DataAccessException {
		ErResview dzType = null;

		String sql = "";
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;

		ErResviewSqlBuilder dsSqlBuilder = null;
		ResultSetTools rsTools = null;
		try {
			dsSqlBuilder = new ErResviewSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsSqlBuilder);

			conn = loadNewConnection();
			sql = dsSqlBuilder.getDataByCodeSql();
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, pojoCode);

			rs = ptmt.executeQuery();

			while (rs.next()) {
				dzType = rsTools.ResultToBeanGeneric(rs, ErResview.class);
			}
		} catch (Exception ex) {
			throw new DataAccessException("获取主要指标方案字典数据列表出错", ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(ptmt);
			releaseConnection(conn);
		}

		return dzType;
	}

	public List<ErResview> getDataByTypes(String[] types) throws DataAccessException {
		List<ErResview> dataList = new ArrayList<ErResview>();
		ErResview dzType = null;

		String sql = "";
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;

		ErResviewSqlBuilder dsSqlBuilder = null;
		ResultSetTools rsTools = null;
		try {
			dsSqlBuilder = new ErResviewSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsSqlBuilder);

			conn = loadNewConnection();
			sql = dsSqlBuilder.getDataByKeysSql();
			ptmt = conn.prepareStatement(sql);
			ptmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(types,conn));

			rs = ptmt.executeQuery();

			while (rs.next()) {
				dzType = rsTools.ResultToBeanGeneric(rs, ErResview.class);
				dataList.add(dzType);
			}
		} catch (Exception ex) {
			throw new DataAccessException("获取主要指标方案字典数据列表出错", ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(ptmt);
			releaseConnection(conn);
		}

		return dataList;
	}

	public List<ErResview> getDataByKeys(String[] keys) throws DataAccessException {
		List<ErResview> dataList = new ArrayList<ErResview>();
		ErResview dzType = null;

		String sql = "";
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;

		ErResviewSqlBuilder dsSqlBuilder = null;
		ResultSetTools rsTools = null;
		try {
			dsSqlBuilder = new ErResviewSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsSqlBuilder);

			conn = loadNewConnection();
			sql = dsSqlBuilder.getDataByKeysSql();
			ptmt = conn.prepareStatement(sql);
			ptmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(keys,conn));

			rs = ptmt.executeQuery();

			while (rs.next()) {
				dzType = rsTools.ResultToBeanGeneric(rs, ErResview.class);
				dataList.add(dzType);
			}
		} catch (Exception ex) {
			throw new DataAccessException("获取主要指标方案字典数据列表出错", ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(ptmt);
			releaseConnection(conn);
		}

		return dataList;
	}
	
	public List<ErResview> getAllData() throws DataAccessException {
		List<ErResview> dataList = new ArrayList<ErResview>();
		ErResview dzType = null;

		String sql = "";
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;

		ErResviewSqlBuilder dsSqlBuilder = null;
		ResultSetTools rsTools = null;
		try {
			dsSqlBuilder = new ErResviewSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsSqlBuilder);

			conn = loadNewConnection();
			sql = dsSqlBuilder.getAllDataSql();
			ptmt = conn.prepareStatement(sql);

			rs = ptmt.executeQuery();

			while (rs.next()) {
				dzType = rsTools.ResultToBeanGeneric(rs, ErResview.class);
				dataList.add(dzType);
			}
		} catch (Exception ex) {
			throw new DataAccessException("获取主要指标方案字典数据列表出错", ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(ptmt);
			releaseConnection(conn);
		}

		return dataList;
	}

	public List<String> queryItemCodesByPlanCode(String code) throws DataAccessException{
		List<String> codes = new ArrayList<String>();
		String sql = "";
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		ErResviewSqlBuilder sqlBuilder = null;
		try{
			sqlBuilder = new ErResviewSqlBuilder();
			conn = loadNewConnection();
			sql = sqlBuilder.getItemCodesSql();
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, code);
			rs = ptmt.executeQuery();
			while(rs.next())
			{
				codes.add(rs.getString("C_ITEM_CODE"));
			}
		}catch(Exception ex){
			throw new DataAccessException("获取主要指标代码出错",ex);
		}finally{
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(ptmt);
			this.releaseConnection(conn);
		}
		return codes;
	}

	public void deleteByPlanCode(String code) throws DataAccessException{
		String sql = "";
		Connection conn = null;
		PreparedStatement ptmt = null;
		ErResviewSqlBuilder sqlBuilder = null;
		try{
			sqlBuilder = new ErResviewSqlBuilder();
			conn = loadNewConnection();
			sql = sqlBuilder.getdeleteByPlanCodeSql();
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, code);
			ptmt.executeUpdate();
		}catch(Exception ex){
			throw new DataAccessException("删除主要指标代码出错",ex);
		}finally{
			this.closeStatementFinal(ptmt);
			this.releaseConnection(conn);
		}
		
	}
}
