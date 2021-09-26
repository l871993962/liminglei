package com.yss.ams.sec.information.modules.sv.indexinfo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.db.OraDbTool;
import com.yss.ams.sec.information.support.modules.sv.indexinfo.pojo.IndexInfo;

/**
 * 
 * @author chenbo
 *2017-06-22
 *#42948 资讯信息管理组件化拆分
 */
public class IndexInfoDao extends GeneralDao {

	public IndexInfoDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
	}

	/** 数据服务开始 **/

	/**
	 * 获取所有数据
	 * @return
	 * @throws ServiceException
	 */
	public List<IndexInfo> getAllDataList() throws ServiceException {
		List<IndexInfo> pojoList = new ArrayList<IndexInfo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		IndexInfoSqlBuilder dsServiceBuilder = null;
		IndexInfo indexInfo = null;
		try {
			dsServiceBuilder = new IndexInfoSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver,
					new IndexInfoSqlBuilder());

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = dsServiceBuilder.getDataList();

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				indexInfo = rsTools.ResultToBeanGeneric(rs, IndexInfo.class);
				pojoList.add(indexInfo);
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
	 * 根据指数代码获取指数基本信息
	 * @param dataCode
	 * @return
	 */
	public IndexInfo getDataByCode(String dataCode) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		IndexInfoSqlBuilder dsServiceBuilder = null;
		IndexInfo indexInfo = null;
		try {
			dsServiceBuilder = new IndexInfoSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver,
					new IndexInfoSqlBuilder());

			conn = this.loadNewConnection();
			List<String> list = new ArrayList<String>();
			list.add("C_INDEX_CODE");
			sql = dsServiceBuilder.getQueryConditionSql(list);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dataCode);
			logger.debug(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				indexInfo = rsTools.ResultToBeanGeneric(rs, IndexInfo.class);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return indexInfo;
	}

	/**
	 * 代码转名称
	 * @param listKey
	 * @return
	 */
	public HashMap<String, String> getKeyConvertMap(List<String> listKey) {
		HashMap<String, String> keyValueMap = new HashMap<String, String>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		IndexInfoSqlBuilder dsServiceBuilder = null;
		IndexInfo indexInfo = null;
		
		String[] keys = new String[listKey.size()];
		keys = listKey.toArray(keys);
		try {
			dsServiceBuilder = new IndexInfoSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver,
					new IndexInfoSqlBuilder());

			conn = this.loadNewConnection();
			List<String> list = new ArrayList<String>();
			list.add("ARRAY_INDEX_CODE");
			sql = dsServiceBuilder.getQueryConditionSql(list);
			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(keys,conn));
			logger.debug(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				indexInfo = rsTools.ResultToBeanGeneric(rs, IndexInfo.class);
				keyValueMap.put(indexInfo.getC_INDEX_CODE(), indexInfo.getC_INDEX_NAME());
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

	public List<BasePojo> getPortRelaIndex(HashMap<String, Object> paraMap,
			Class<?> clazz) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		List<String> paraNameList;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			paraNameList = getParaName(paraMap);
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			sql = ((IndexInfoSqlBuilder)sqlbuilder).getPortRelaIndex(paraNameList);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraMap.get("ARRAY_C_PORT_CODE").toString());

			rs = pstmt.executeQuery();

			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				BasePojo t = rsTools.ResultToBean(rs, clazz);
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
	 * 
	 * @author liuxiang
	 * @date 2015年11月4日 STORY #22070 重复清算行情文件时，能提示系统中已经有自动转手工的数据
	 * @return
	 */
	public HashMap<String, String> getKeyConvertMap() {
		HashMap<String, String> map = new HashMap<String, String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			IndexInfoSqlBuilder dsServiceBuilder = new IndexInfoSqlBuilder();
			conn = this.loadNewConnection();
			sql = dsServiceBuilder.getDataList();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				map.put(rs.getString("C_SEC_CODE"), rs.getString("C_INDEX_NAME"));
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		
		return map;
	}

	/** 数据服务结束 **/
}
