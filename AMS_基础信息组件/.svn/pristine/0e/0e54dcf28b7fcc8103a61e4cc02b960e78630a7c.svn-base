package com.yss.ams.base.information.modules.bi.ie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.bi.ie.pojo.Ie;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.db.OraDbTool;

/**
 * 收支代码设置dao层
 * @author yuankai 公共信息拆分  2017.5.31
 *
 */
public class IeDao extends GeneralDao {

	/**
	 * @param pool
	 * @param sqlBuilder
	 */
	public IeDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 根据结果集设置收支名称后返回收支代码设置pojo
	 * @param rsTools
	 * @param rs
	 * @param clazz
	 */
	@Override
	protected  BasePojo setResultSet(ResultSetTools rsTools,ResultSet rs,Class<?> clazz) throws Exception{
		BasePojo pojo = rsTools.ResultToBean(rs, clazz);
		((Ie)pojo).setC_FEE_NAME(rs.getString(IeColumnName.c_FEE_NAME.name()));
		return pojo;
	}
	
	/**
	 * 获取所有收支项目设置所有数据
	 * @return	收支项目设置数据集合
	 * @throws Exception
	 */
	/* START 数据服务方法 */
	public List<Ie> getAllDataList() throws Exception {
		List<Ie> pojoList = new ArrayList<Ie>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		IeSqlBuilder dsServiceBuilder = null;
		Ie t = null;
		try {
			dsServiceBuilder = new IeSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			sql = dsServiceBuilder.getAllDataSql();
			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, Ie.class);
				t.setC_FEE_NAME(rs.getString(IeColumnName.c_FEE_NAME.name()));
				pojoList.add(t);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return pojoList;
	}
	
	/**
	 * 根据收支项目代码获取收支项目设置
	 * @param code  收支代码
	 * @return
	 * @throws Exception
	 */
	public Ie getDataByCode(String code) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		IeSqlBuilder dsServiceBuilder = null;
		Ie t = null;
		try {
			dsServiceBuilder = new IeSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();

			sql = dsServiceBuilder.getDataByCode();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);

			logger.debug(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, Ie.class);
				t.setC_FEE_NAME(rs.getString(IeColumnName.c_FEE_NAME.name()));
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return t;
	}
	
	/**
	 * 根据收支项目类型获取收支项目设置
	 * @param types 收支类型
	 * @return
	 * @throws Exception
	 */
	public List<Ie> getDataListByTypes(String[] types) throws Exception {
		List<Ie> pojoList = new ArrayList<Ie>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		IeSqlBuilder dsServiceBuilder = null;
		Ie t = null;
		try {
			dsServiceBuilder = new IeSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();

			sql = dsServiceBuilder.getDataByTypes();

			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(types,conn));

			logger.debug(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, Ie.class);
				t.setC_FEE_NAME(rs.getString(IeColumnName.c_FEE_NAME.name()));
				pojoList.add(t);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return pojoList;
	}
	
	/**
	 * 获取收支项目设置转换map
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKeyConvertMap() throws Exception {
		HashMap<String, String> keyValueMap = new HashMap<String, String>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";

		IeSqlBuilder dsServiceBuilder = null;
		try {
			dsServiceBuilder = new IeSqlBuilder();

			conn = this.loadNewConnection();

			sql = dsServiceBuilder.getAllDataSql();

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				keyValueMap.put(
						rs.getString(IeColumnName.c_FEE_CODE.toString()), rs
								.getString(IeColumnName.c_FEE_NAME.name()));
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

	/**
	 * 根据多个收支项目类型获取收支项目设置
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public List<Ie> getDataListByKeys(String[] keys) throws Exception {
		List<Ie> pojoList = new ArrayList<Ie>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		IeSqlBuilder dsServiceBuilder = null;
		Ie t = null;
		try {
			dsServiceBuilder = new IeSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();

			sql = dsServiceBuilder.getDataByTypes();

			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(keys,conn));

			logger.debug(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, Ie.class);
				t.setC_FEE_NAME(rs.getString(IeColumnName.c_FEE_NAME.name()));
				pojoList.add(t);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return pojoList;
	}
	/* END 数据服务方法 */

	/**
	 * 根据时间戳获取收支设置，用于缓存刷新
	 * @param timestamp
	 * @return
	 */
	public List<BasePojo> getDataListByTimestamp(String timestamp) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		IeSqlBuilder dsServiceBuilder = null;
		Ie t = null;
		try {
			dsServiceBuilder = new IeSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();

			sql = dsServiceBuilder.getDataListByTimestamp();

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, Ie.class);
				pojoList.add(t);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return pojoList;
	}
	
	public List<Ie> getDataListByFeeCodes(String[] keys) throws Exception {
		List<Ie> pojoList = new ArrayList<Ie>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		IeSqlBuilder dsServiceBuilder = null;
		Ie t = null;
		try {
			dsServiceBuilder = new IeSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();

			sql = dsServiceBuilder.getDataListByFeeCodes();

			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(keys,conn));

			logger.debug(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, Ie.class);
				t.setC_FEE_NAME(rs.getString(IeColumnName.c_FEE_NAME.name()));
				pojoList.add(t);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return pojoList;
	}
	
		public List<Ie> getDataListByType(String[] types) throws Exception {
		List<Ie> pojoList = new ArrayList<Ie>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		IeSqlBuilder dsServiceBuilder = null;
		Ie t = null;
		try {
			dsServiceBuilder = new IeSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();

			sql = dsServiceBuilder.getDataByType();

			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(types,conn));

			logger.debug(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, Ie.class);
				t.setC_FEE_NAME(rs.getString(IeColumnName.c_FEE_NAME.name()));
				pojoList.add(t);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return pojoList;
	}
}
