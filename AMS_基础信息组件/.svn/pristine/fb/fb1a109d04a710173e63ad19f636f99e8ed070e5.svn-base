package com.yss.ams.base.information.modules.sys.dai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.sys.dai.pojo.Dai;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.db.OraDbTool;

//import dataservice.comm.pojo.Dai;
/**
 * 核算项目字典表T_S_DAI_ITEM dao类 
 *
 */
public class AccProDao extends GeneralDao {

	public AccProDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
	}

	/**
	 * 查询指定 核算项目代码C_DAI_CODE的所有核算项目字典表T_S_DAI_ITEM的数据
	 * @param dataCode 核算项目代码C_DAI_CODE值
	 * @return
	 */
	public Dai getDataByCode(String dataCode) throws Exception {
		Dai dai = null;
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		String sql = "";
		AccProSqlBuilder accProSqlBuilder = null;
		ResultSetTools rsTool = null;
		try {
			accProSqlBuilder = new AccProSqlBuilder();
			rsTool = new ResultSetTools(dbNameResolver, accProSqlBuilder);

			sql = accProSqlBuilder.getDataByCodeSql(dbNameResolver);
			conn = loadNewConnection();
			ptmt = conn.prepareStatement(sql);

			ptmt.setString(1, dataCode);

			rs = ptmt.executeQuery();

			if (rs.next()) {
				dai = rsTool.ResultToBeanGeneric(rs, Dai.class);
			}

		} catch (Exception e) {
			throw new DataAccessException("根据唯一性标识获取数据出错。", e);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(ptmt);
			releaseConnection(conn);
		}

		return dai;
	}

	/**
	 * 获取所有的核算项目字典表T_S_DAI_ITEM数据,并对结果按 C_DV_KM_CLS,N_ORDER进行排序
	 * @return
	 */
	public List<Dai> getDataList() throws Exception {
		List<Dai> daiList = null;
		Dai dai = null;
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		String sql = "";
		AccProSqlBuilder accProSqlBuilder = null;
		ResultSetTools rsTool = null;
		try {
			daiList = new ArrayList<Dai>();

			accProSqlBuilder = new AccProSqlBuilder();
			rsTool = new ResultSetTools(dbNameResolver, accProSqlBuilder);

			sql = accProSqlBuilder.getAllDataSql(dbNameResolver);
			conn = loadNewConnection();
			ptmt = conn.prepareStatement(sql);

			rs = ptmt.executeQuery();

			while (rs.next()) {
				dai = rsTool.ResultToBeanGeneric(rs, Dai.class);
				daiList.add(dai);
			}

		} catch (Exception e) {
			throw new DataAccessException("获取数据出错。", e);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(ptmt);
			releaseConnection(conn);
		}

		return daiList;
	}

	/**
	 * 查询指定科目类别C_DV_KM_CLS的所有核算项目字典表T_S_DAI_ITEM的数据
	 * @param types 科目类别C_DV_KM_CLS值组成的数组
	 * @return
	 */
	public List<Dai> getDataListByTypes(String[] types) throws Exception {
		List<Dai> daiList = null;
		Dai dai = null;
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		String sql = "";
		AccProSqlBuilder accProSqlBuilder = null;
		ResultSetTools rsTool = null;
		try {
			daiList = new ArrayList<Dai>();

			accProSqlBuilder = new AccProSqlBuilder();
			rsTool = new ResultSetTools(dbNameResolver, accProSqlBuilder);

			sql = accProSqlBuilder.getAllDataSqlByTypes(dbNameResolver);
			conn = loadNewConnection();
			ptmt = conn.prepareStatement(sql);

			ptmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(types,conn));

			rs = ptmt.executeQuery();

			while (rs.next()) {
				dai = rsTool.ResultToBeanGeneric(rs, Dai.class);
				daiList.add(dai);
			}

		} catch (Exception e) {
			throw new DataAccessException("获取数据出错。", e);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(ptmt);
			releaseConnection(conn);
		}

		return daiList;
	}

	/**
	 * 查询 符合条件Conds的所有核算项目字典表T_S_DAI_ITEM的数据
	 * @param Conds 查询条件
	 * @return
	 */
	public List<Dai> getDataListBySqlCond(String conds) throws Exception {
		List<Dai> daiList = null;
		Dai dai = null;
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		String sql = "";
		AccProSqlBuilder accProSqlBuilder = null;
		ResultSetTools rsTool = null;
		try {
			daiList = new ArrayList<Dai>();

			accProSqlBuilder = new AccProSqlBuilder();
			rsTool = new ResultSetTools(dbNameResolver, accProSqlBuilder);

			sql = accProSqlBuilder.getAllDataSqlBySqlConds(dbNameResolver,
					conds);
			conn = loadNewConnection();
			ptmt = conn.prepareStatement(sql);

			rs = ptmt.executeQuery();

			while (rs.next()) {
				dai = rsTool.ResultToBeanGeneric(rs, Dai.class);
				daiList.add(dai);
			}

		} catch (Exception e) {
			throw new DataAccessException("根据唯一性标识获取数据出错。", e);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(ptmt);
			releaseConnection(conn);
		}

		return daiList;
	}

	/**
	 * 查询某些 核算项目代码C_DAI_CODE的所有核算项目字典表T_S_DAI_ITEM的数据
	 * @param keys 核算项目代码C_DAI_CODE值组成的数组
	 * @return
	 */
	public List<Dai> getDataListByKeys(String[] keys) throws Exception {
		List<Dai> daiList = null;
		Dai dai = null;
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		String sql = "";
		AccProSqlBuilder accProSqlBuilder = null;
		ResultSetTools rsTool = null;
		try {
			daiList = new ArrayList<Dai>();

			accProSqlBuilder = new AccProSqlBuilder();
			rsTool = new ResultSetTools(dbNameResolver, accProSqlBuilder);

			sql = accProSqlBuilder.getListByKeysSql(dbNameResolver);
			conn = loadNewConnection();
			ptmt = conn.prepareStatement(sql);
			ptmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(keys,conn));

			rs = ptmt.executeQuery();

			while (rs.next()) {
				dai = rsTool.ResultToBeanGeneric(rs, Dai.class);
				daiList.add(dai);
			}

		} catch (Exception e) {
			throw new DataAccessException("根据唯一性标识获取数据出错。", e);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(ptmt);
			releaseConnection(conn);
		}

		return daiList;
	}

	/**
	 * 获取所有的核算项目字典表T_S_DAI_ITEM的核算项目代码C_DAI_CODE和核算项目名称C_DAI_NAME组成的集合
	 * @return
	 */
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		HashMap<String, String> map = new HashMap<String, String>();
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		String sql = "";
		AccProSqlBuilder accProSqlBuilder = null;
		try {
			accProSqlBuilder = new AccProSqlBuilder();

			sql = accProSqlBuilder.getAllDataSql(dbNameResolver);
			conn = loadNewConnection();
			ptmt = conn.prepareStatement(sql);

			rs = ptmt.executeQuery();

			while (rs.next()) {
				map.put(rs.getString(DaiColumnName.c_DAI_CODE.toString()), rs
						.getString(DaiColumnName.c_DAI_NAME.toString()));
			}

		} catch (Exception e) {
			throw new DataAccessException("获取数据出错。", e);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(ptmt);
			releaseConnection(conn);
		}

		return map;
	}

	/**
	 * 查询指定科目类别C_DV_KM_CLS的所有核算项目字典表T_S_DAI_ITEM的数据
	 * @param kmCls 科目类别C_DV_KM_CLS的值数组
	 * @return
	 */
	public List<BasePojo> getAccProDataByKmCls(String[] kmCls) throws ServiceException {
		List<BasePojo> daiList = null;
		Dai dai = null;
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		String sql = "";
		AccProSqlBuilder accProSqlBuilder = null;
		ResultSetTools rsTool = null;
		try {
			daiList = new ArrayList<BasePojo>();

			accProSqlBuilder = new AccProSqlBuilder();
			rsTool = new ResultSetTools(dbNameResolver, accProSqlBuilder);

			sql = accProSqlBuilder.getKmClsDataSql(dbNameResolver);
			conn = loadNewConnection();
			ptmt = conn.prepareStatement(sql);
			ptmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(kmCls,conn));

			rs = ptmt.executeQuery();

			while (rs.next()) {
				dai = rsTool.ResultToBeanGeneric(rs, Dai.class);
				daiList.add(dai);
			}

		} catch (Exception e) {
			throw new DataAccessException("获取数据出错。", e);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(ptmt);
			releaseConnection(conn);
		}

		return daiList;
	}

	/**
	 * 获取所有的核算项目字典表T_S_DAI_ITEM数据
	 * @param timestamp 时间戳
	 * @return
	 */
	public List<BasePojo> getDataListByTimestamp(String timestamp) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		AccProSqlBuilder dsServiceBuilder = null;
		Dai t = null;
		try {
			dsServiceBuilder = new AccProSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();

			sql = dsServiceBuilder.getDataListByTimestamp(dbNameResolver);

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, Dai.class);
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
