package com.yss.ifa.szt.tool.para.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.db.OraDbTool;
import com.yss.ifa.szt.tool.pojo.ErVoc;

public class ErVocDao extends GeneralDao {

	public ErVocDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
	}

	public List<ErVoc> getAllData() {
		List<ErVoc> dataList = new ArrayList<ErVoc>();
		ErVoc erVoc = null;

		String sql = "";
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;

		ErVocSqlBuilder dsSqlBuilder = null;
		ResultSetTools rsTools = null;
		try {
			dsSqlBuilder = new ErVocSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsSqlBuilder);

			conn = loadNewConnection();
			sql = dsSqlBuilder.getAllDataSql();
			ptmt = conn.prepareStatement(sql);

			rs = ptmt.executeQuery();

			while (rs.next()) {
				erVoc = (ErVoc) rsTools.ResultToBean(rs, ErVoc.class);
				dataList.add(erVoc);
			}
		} catch (Exception ex) {
			throw new DataAccessException("获取电子对账词汇字典数据列表出错", ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(ptmt);
			releaseConnection(conn);
		}

		return dataList;
	}

	public List<ErVoc> getDataByKeys(String[] keys) {
		List<ErVoc> dataList = new ArrayList<ErVoc>();
		ErVoc erVoc = null;

		String sql = "";
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;

		ErVocSqlBuilder dsSqlBuilder = null;
		ResultSetTools rsTools = null;
		try {
			dsSqlBuilder = new ErVocSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsSqlBuilder);

			conn = loadNewConnection();
			sql = dsSqlBuilder.getDataByKeysSql();
			ptmt = conn.prepareStatement(sql);

			ptmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(keys,conn));
			rs = ptmt.executeQuery();

			while (rs.next()) {
				erVoc = (ErVoc) rsTools.ResultToBean(rs, ErVoc.class);
				dataList.add(erVoc);
			}
		} catch (Exception ex) {
			throw new DataAccessException("获取电子对账词汇字典数据列表出错", ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(ptmt);
			releaseConnection(conn);
		}

		return dataList;
	}

	public List<ErVoc> getDataByTypes(String[] types) {
		List<ErVoc> dataList = new ArrayList<ErVoc>();
		ErVoc erVoc = null;

		String sql = "";
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;

		ErVocSqlBuilder dsSqlBuilder = null;
		ResultSetTools rsTools = null;
		try {
			dsSqlBuilder = new ErVocSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsSqlBuilder);

			conn = loadNewConnection();
			sql = dsSqlBuilder.getDataByTypesSql();
			ptmt = conn.prepareStatement(sql);

			ptmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(types,conn));
			rs = ptmt.executeQuery();

			while (rs.next()) {
				erVoc = (ErVoc) rsTools.ResultToBean(rs, ErVoc.class);
				dataList.add(erVoc);
			}
		} catch (Exception ex) {
			throw new DataAccessException("获取电子对账词汇字典数据列表出错", ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(ptmt);
			releaseConnection(conn);
		}

		return dataList;
	}

	public ErVoc getDataByCode(String code) {
		ErVoc erVoc = null;

		String sql = "";
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;

		ErVocSqlBuilder dsSqlBuilder = null;
		ResultSetTools rsTools = null;
		try {
			dsSqlBuilder = new ErVocSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsSqlBuilder);

			conn = loadNewConnection();
			sql = dsSqlBuilder.getDataByCodeSql();
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, code);

			rs = ptmt.executeQuery();

			while (rs.next()) {
				erVoc = (ErVoc) rsTools.ResultToBean(rs, ErVoc.class);
			}
		} catch (Exception ex) {
			throw new DataAccessException("获取对账类型字典数据列表出错", ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(ptmt);
			releaseConnection(conn);
		}

		return erVoc;
	}

	public HashMap<String, String> getKeyConvertMap() {
		HashMap<String, String> keyConvertMap = new HashMap<String, String>();

		String sql = "";
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;

		ErVocSqlBuilder dsSqlBuilder = null;
		String mapKey = "", mapValue = "";
		try {
			dsSqlBuilder = new ErVocSqlBuilder();

			conn = loadNewConnection();
			sql = dsSqlBuilder.getAllDataSql();
			ptmt = conn.prepareStatement(sql);

			rs = ptmt.executeQuery();

			while (rs.next()) {
				mapKey = rs.getString(ErVocColumnName.c_DV_CODE.toString());
				mapValue = rs.getString(ErVocColumnName.c_DV_NAME.toString());

				keyConvertMap.put(mapKey, mapValue);
			}
		} catch (Exception ex) {
			throw new DataAccessException("获取对账类型字典数据列表出错", ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(ptmt);
			releaseConnection(conn);
		}

		return keyConvertMap;
	}

	public HashMap<String, String> getKeyConvertMap(List<String> listKey) {
		HashMap<String, String> keyValueMap = new HashMap<String, String>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ErVocSqlBuilder dsSqlBuilder = null;
		String mapKey = "", mapValue = "";
		try {
			String[] strArr = new String[listKey.size()];
			dsSqlBuilder = new ErVocSqlBuilder();

			conn = loadNewConnection();
			sql = dsSqlBuilder.getDataByKeysSql();
			pstmt = conn.prepareStatement(sql);
			
			listKey.toArray(strArr);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(strArr,conn));

			rs = pstmt.executeQuery();
			conn.commit();

			while (rs.next()) {
				mapKey = rs.getString(ErVocColumnName.c_DV_CODE.toString());
				mapValue = rs.getString(ErVocColumnName.c_DV_NAME.toString());

				keyValueMap.put(mapKey, mapValue);
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

}
