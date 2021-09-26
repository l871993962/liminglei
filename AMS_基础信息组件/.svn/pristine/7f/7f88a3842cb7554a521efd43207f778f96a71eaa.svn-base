package com.yss.ams.base.information.modules.sys.dztype.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.sys.dztype.pojo.DzType;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.db.OraDbTool;

/**
 * 对账类型字典表dao层
 * @author yuankai 公共组件拆分 2017.5.31
 *
 */
public class DzTypeDao extends GeneralDao {

	public DzTypeDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
	}
	
	/**
	 * 按照流水号升序的方式查询所有对账类型字典表数据对应pojo的list
	 * @return
	 * @throws DataAccessException
	 */
	public List<DzType> getAllData() throws DataAccessException {
		List<DzType> dataList = new ArrayList<DzType>();
		DzType dzType = null;

		String sql = "";
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;

		DzTypeSqlBuilder dsSqlBuilder = null;
		ResultSetTools rsTools = null;
		try {
			dsSqlBuilder = new DzTypeSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsSqlBuilder);

			conn = loadNewConnection();
			sql = dsSqlBuilder.getAllDataSql();
			ptmt = conn.prepareStatement(sql);

			rs = ptmt.executeQuery();

			while (rs.next()) {
				dzType = rsTools.ResultToBeanGeneric(rs, DzType.class);
				dataList.add(dzType);
			}
		} catch (Exception ex) {
			throw new DataAccessException("获取对账类型字典数据列表出错", ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(ptmt);
			releaseConnection(conn);
		}

		return dataList;
	}
	
	/**
	 * 根据对账类型代码查询所有对账类型字典表数据对应pojo的list
	 * @param keys
	 * @return
	 * @throws DataAccessException
	 */
	public List<DzType> getDataByKeys(String[] keys) throws DataAccessException {
		List<DzType> dataList = new ArrayList<DzType>();
		DzType dzType = null;

		String sql = "";
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;

		DzTypeSqlBuilder dsSqlBuilder = null;
		ResultSetTools rsTools = null;
		try {
			dsSqlBuilder = new DzTypeSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsSqlBuilder);

			conn = loadNewConnection();
			sql = dsSqlBuilder.getDataByKeysSql();
			ptmt = conn.prepareStatement(sql);
			ptmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(keys,conn));

			rs = ptmt.executeQuery();

			while (rs.next()) {
				dzType = rsTools.ResultToBeanGeneric(rs, DzType.class);
				dataList.add(dzType);
			}
		} catch (Exception ex) {
			throw new DataAccessException("获取对账类型字典数据列表出错", ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(ptmt);
			releaseConnection(conn);
		}

		return dataList;
	}
	
	/**
	 * 根据对账类型代码查询所有对账类型字典表数据对应pojo的list
	 * @param keys
	 * @return
	 * @throws DataAccessException
	 */
	public List<DzType> getDataByTypes(String[] keys)
			throws DataAccessException {
		List<DzType> dataList = new ArrayList<DzType>();
		DzType dzType = null;

		String sql = "";
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;

		DzTypeSqlBuilder dsSqlBuilder = null;
		ResultSetTools rsTools = null;
		try {
			dsSqlBuilder = new DzTypeSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsSqlBuilder);

			conn = loadNewConnection();
			sql = dsSqlBuilder.getDataByKeysSql();
			ptmt = conn.prepareStatement(sql);
			ptmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(keys,conn));

			rs = ptmt.executeQuery();

			while (rs.next()) {
				dzType = rsTools.ResultToBeanGeneric(rs, DzType.class);
				dataList.add(dzType);
			}
		} catch (Exception ex) {
			throw new DataAccessException("获取对账类型字典数据列表出错", ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(ptmt);
			releaseConnection(conn);
		}

		return dataList;
	}
	
	/**
	 * 根据对账类型代码查询所有对账类型字典表数据对应pojo
	 * @param code
	 * @return
	 * @throws DataAccessException
	 */
	public DzType getDataByCode(String code) throws DataAccessException {
		DzType dzType = null;

		String sql = "";
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;

		DzTypeSqlBuilder dsSqlBuilder = null;
		ResultSetTools rsTools = null;
		try {
			dsSqlBuilder = new DzTypeSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsSqlBuilder);

			conn = loadNewConnection();
			sql = dsSqlBuilder.getDataByCodeSql();
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, code);

			rs = ptmt.executeQuery();

			while (rs.next()) {
				dzType = rsTools.ResultToBeanGeneric(rs, DzType.class);
			}
		} catch (Exception ex) {
			throw new DataAccessException("获取对账类型字典数据列表出错", ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(ptmt);
			releaseConnection(conn);
		}

		return dzType;
	}
	
	/**
	 * 按照流水号升序的方式查询所有对账类型字典代码和对账类型字典名称转Map
	 * @return
	 * @throws DataAccessException
	 */
	public HashMap<String, String> getKeyConvertMap()
			throws DataAccessException {
		HashMap<String, String> keyConvertMap = new HashMap<String, String>();

		String sql = "";
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;

		DzTypeSqlBuilder dsSqlBuilder = null;
		String mapKey = "", mapValue = "";
		try {
			dsSqlBuilder = new DzTypeSqlBuilder();

			conn = loadNewConnection();
			sql = dsSqlBuilder.getAllDataSql();
			ptmt = conn.prepareStatement(sql);

			rs = ptmt.executeQuery();

			while (rs.next()) {
				mapKey = rs.getString(DzTypeCloumnName.c_DZ_CODE.toString());
				mapValue = rs.getString(DzTypeCloumnName.c_DZ_NAME.toString());

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
	
	/**
	 * 根据对账类型代码获取所有对账类型字典代码和对账类型字典名称转Map
	 * @param listKey
	 * @return
	 * @throws DataAccessException
	 */
	public HashMap<String, String> getKeyConvertMap(List<String> listKey) throws DataAccessException {
		HashMap<String, String> keyValueMap = new HashMap<String, String>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		DzTypeSqlBuilder dsSqlBuilder = null;
		String mapKey = "", mapValue = "";
		try {
			String[] strArr = new String[listKey.size()];
			dsSqlBuilder = new DzTypeSqlBuilder();

			conn = loadNewConnection();
			sql = dsSqlBuilder.getDataByKeysSql();
			pstmt = conn.prepareStatement(sql);
			
			listKey.toArray(strArr);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(strArr,conn));

			rs = pstmt.executeQuery();
			conn.commit();

			while (rs.next()) {
				mapKey = rs.getString(DzTypeCloumnName.c_DZ_CODE.toString());
				mapValue = rs.getString(DzTypeCloumnName.c_DZ_NAME.toString());

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
