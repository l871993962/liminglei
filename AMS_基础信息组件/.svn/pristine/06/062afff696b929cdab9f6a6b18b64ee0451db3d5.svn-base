package com.yss.ams.base.information.modules.bi.hdaygroup.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.bi.hdaygroup.pojo.HdayGroup;
import com.yss.framework.api.bundle.BundleContextWrapper;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.db.OraDbTool;
import com.yss.mvc.pojo.PojoLoader;

/**
 * 节假日群类型dao层
 * @author yuankai 公共信息拆分  2017.5.31
 *
 */
public class HdayGroupDao extends GeneralDao {

	private HdayGroupSqlBuilder sqlBuilder = null;

	public HdayGroupDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		this.sqlBuilder = (HdayGroupSqlBuilder) sqlBuilder;
	}

	/** START 数据服务方法 **/
	/**
	 * 查询所有节假日群
	 * 
	 * @return 节假日群列表
	 * @throws Exception
	 */
	public List<HdayGroup> getAllDataList() throws Exception {
		List<HdayGroup> pojoList = new ArrayList<HdayGroup>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ResultSetTools rsTools = null;
		HdayGroupSqlBuilder dsServiceBuilder = null;
		HdayGroup t = null;
		try {
			dsServiceBuilder = new HdayGroupSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);
			conn = this.loadNewConnection();
			sql = dsServiceBuilder.getAllDataSql();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, HdayGroup.class);
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
	 * 根据节假日群代码获取节假日群实体
	 * 
	 * @param code
	 *            节假日群代码
	 * @return 节假日群实体
	 * @throws Exception
	 */
	public HdayGroup getDataByCode(String code) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ResultSetTools rsTools = null;
		HdayGroupSqlBuilder dsServiceBuilder = null;
		HdayGroup t = null;
		try {
			dsServiceBuilder = new HdayGroupSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			sql = dsServiceBuilder.getDataByCode();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);
			logger.debug(sql);
			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, HdayGroup.class);
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
	 * 根据多个节假日群代码获取节假日群实体
	 * 
	 * @param types
	 *            节假日群代码
	 * @return 节假日群实体列表
	 * @throws Exception
	 */
	public List<HdayGroup> getDataListByTypes(String[] types) throws Exception {
		List<HdayGroup> pojoList = new ArrayList<HdayGroup>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ResultSetTools rsTools = null;
		HdayGroupSqlBuilder dsServiceBuilder = null;
		HdayGroup t = null;
		try {
			dsServiceBuilder = new HdayGroupSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			sql = dsServiceBuilder.getDataByTypes();
			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1,
					OraDbTool.newInstance().sqlOverLongCondition(types, conn));
			logger.debug(sql);
			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, HdayGroup.class);
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
	 * 获取代码转换MAP
	 * 
	 * @return 代码名称转换MAP<"hdayCode",hdayName>
	 * @throws Exception
	 */
	public HashMap<String, String> getKeyConvertMap() throws Exception {
		HashMap<String, String> keyValueMap = new HashMap<String, String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ResultSetTools rsTools = null;
		HdayGroupSqlBuilder dsServiceBuilder = null;
		HdayGroup t = null;
		try {
			dsServiceBuilder = new HdayGroupSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			sql = dsServiceBuilder.getAllDataSql();
			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);
			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, HdayGroup.class);
				keyValueMap.put(t.getC_HDAY_CODE(), t.getC_HDAY_NAME());
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

	public List<HdayGroup> getDataListByKeys(String[] keys) throws Exception {
		List<HdayGroup> pojoList = new ArrayList<HdayGroup>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ResultSetTools rsTools = null;
		HdayGroupSqlBuilder dsServiceBuilder = null;
		HdayGroup t = null;
		try {
			dsServiceBuilder = new HdayGroupSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			sql = dsServiceBuilder.getDataByTypes();
			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1,
					OraDbTool.newInstance().sqlOverLongCondition(keys, conn));
			logger.debug(sql);
			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, HdayGroup.class);
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

	/** END 数据服务方法 **/

	/**
	 * 根据时间戳获取数据，用于缓存更新
	 * 
	 * @param timestamp
	 *            时间戳
	 * @return
	 */
	public List<BasePojo> getDataListByTimestamp(String timestamp) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ResultSetTools rsTools = null;
		HdayGroupSqlBuilder dsServiceBuilder = null;
		HdayGroup t = null;
		try {
			dsServiceBuilder = new HdayGroupSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);
			conn = this.loadNewConnection();
			sql = dsServiceBuilder.getDataListByTimestamp();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, timestamp);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, HdayGroup.class);
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
	 * 查询节假日群数据(树形结构显示)
	 * 
	 * @param paraMap
	 *            参数列表
	 * @param bundleContext
	 *            bundle上下文
	 * @return 节假日列表
	 */
	public List<BasePojo> queryTreeViewData(HashMap<String, String> paraMap,
			BundleContextWrapper bundleContext) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetTools rsTools = null;
		Class<?> clazz = null;
		String sql = "";
		BasePojo t = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			clazz = PojoLoader.getPojoClassById("HdayGroup", bundleContext);
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			sql = sqlBuilder.getQueryConditionSql();
			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);
			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);
			while (rs.next()) {
				t = rsTools.ResultToBean(rs, clazz);
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
	 * STORY #73552 节假日信息设置restful接口 
	 * add by zuomingke
	 * date 20190713
	 * @return List<HdayGroup>
	 */
	public List<HdayGroup> queryAllHdayGroup(){
		List<HdayGroup> pojoList = new ArrayList<HdayGroup>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		HdayGroupSqlBuilder dsServiceBuilder = null;
		HdayGroup t = null;
		try {
			dsServiceBuilder = new HdayGroupSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();

			sql = dsServiceBuilder.getAllHdayGroupSql();

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				t = (HdayGroup)rsTools.ResultToBean(rs, HdayGroup.class);
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
