package com.yss.ams.base.information.modules.sys.dccury.dao;

import java.beans.PropertyDescriptor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.sys.dccury.pojo.DcCury;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.JdbcSupport;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.exception.InvalidParametersException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.db.OraDbTool;
import com.yss.framework.util.PojoUtils;

/**
 * 国际货币dao 
 * @author 马向峰  拆分  2017.0527
 *
 */
public class DcCuryDao extends GeneralDao {
	/*- Added by huangsq 20160728 STORY #32244 词汇资源可控制是否显示 */
	private JdbcSupport jdbcSupport;

	public DcCuryDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		jdbcSupport = new JdbcSupport(pool);
		//sqlbuilder=sqlBuilder;
	}

	/* START 数据服务 */
	/**
	 * 获取所有的国际货币信息
	 * @return	国际货币列表
	 * @throws ServiceException
	 */
	public List<DcCury> getAllDataList() throws ServiceException {
		List<DcCury> pojoList = new ArrayList<DcCury>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		DcCurySqlBuilder dsServiceBuilder = null;
		DcCury t = null;
		try {
			dsServiceBuilder = new DcCurySqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			/*加载新的数据库连接，并设置不自动提交事务*/
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			
			/*生成获取所有国际货币的SQL*/
			sql = dsServiceBuilder.getAllDataSql();

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, DcCury.class);
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
	 * 根据币种代码查询货币
	 * @param code	币种代码
	 * @return	货币信息
	 * @throws ServiceException
	 */
	public DcCury getDataByCode(String code) throws ServiceException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		DcCurySqlBuilder dsServiceBuilder = null;
		DcCury t = null;
		try {
			dsServiceBuilder = new DcCurySqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			/*生成带参数的查询语句*/
			sql = dsServiceBuilder.getDataByCode();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);

			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, DcCury.class);
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
	 * 根据币种代码查询货币信息
	 * @param types	币种代码数组
	 * @return	币种信息集合
	 * @throws ServiceException
	 */
	public List<DcCury> getDataListByTypes(String[] types)
			throws ServiceException {
		List<DcCury> pojoList = new ArrayList<DcCury>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		DcCurySqlBuilder dsServiceBuilder = null;
		DcCury t = null;
		try {
			dsServiceBuilder = new DcCurySqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			//获取查询SQL
			sql = dsServiceBuilder.getDataByTypes();

			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(types,conn));//设置参数

			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, DcCury.class);
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
	 * 查询所有国际货币信息并封装为Map集合，HashMap<String, String>，key为币种代码，value为币种名称
	 * @return	封装了币种信息的Map
	 * @throws Exception
	 */
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		HashMap<String, String> keyValueMap = new HashMap<String, String>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		DcCurySqlBuilder dsServiceBuilder = null;
		DcCury t = null;
		try {
			dsServiceBuilder = new DcCurySqlBuilder();
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
				t = rsTools.ResultToBeanGeneric(rs, DcCury.class);
				keyValueMap.put(t.getC_DC_CODE(), t.getC_DC_NAME());
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
	 * 根据币种代码的集合查询货币信息
	 * @param types 币种代码数组
	 * @return	货币信息List集合
	 * @throws Exception
	 */
	public List<DcCury> getDataListByKeys(String[] keys)
			throws ServiceException {
		List<DcCury> pojoList = new ArrayList<DcCury>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		DcCurySqlBuilder dsServiceBuilder = null;
		DcCury t = null;
		try {
			dsServiceBuilder = new DcCurySqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = dsServiceBuilder.getDataByTypes();

			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(keys,conn));

			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, DcCury.class);
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
	/* END 数据服务 */

	/**
	 * 根据时间戳查询所有国际货币信息
	 * @param timestamp	时间戳
	 * @return	国际货币信息
	 */
	public List<BasePojo> getDataListByTimestamp(String timestamp) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		DcCurySqlBuilder dsServiceBuilder = null;
		DcCury t = null;
		try {
			dsServiceBuilder = new DcCurySqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();

			sql = dsServiceBuilder.getDataListByTimestamp();

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, DcCury.class);
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
	 * Added by huangsq 20160728 STORY #32244 词汇资源可控制是否显示<br />
	 * 重写根据主键更新对象方法
	 */
	@Override
	public <T extends BasePojo> void updateById(T basePojo) throws DataAccessException {
		if (basePojo == null) {
			throw new InvalidParametersException("baseBean数据实例不能为空");
		}

		List<T> pojoList = new ArrayList<T>();
		pojoList.add(basePojo);
		this.updateById(pojoList);
	}

	/**
	 * Added by huangsq 20160728 STORY #32244 词汇资源可控制是否显示<br />
	 * 重写根据主键更新对象方法
	 */
	@Override
	public <T extends BasePojo> void updateById(List<T> pojoList) throws DataAccessException {
		try {
			if (pojoList == null || pojoList.size() == 0) {
				throw new InvalidParametersException("baseBean数据实例不能为空");
			}
			Object[][] args = new Object[pojoList.size()][];
			for (int i = 0; i < pojoList.size(); i++) {
				DcCury dcCury = (DcCury) pojoList.get(i);
				args[i] = new Object[] { dcCury.getC_DC_CODE(), dcCury.getC_DC_NAME(), dcCury.getC_DC_SIGN(), dcCury.getC_DV_STATE(),
						dcCury.getN_ORDER(), dcCury.getId() };
			}
			String updateSql = "update T_S_DC_CURY set C_DC_CODE=?,C_DC_NAME=?,C_DC_SIGN=?,C_DV_STATE=?,N_ORDER=? where C_IDEN=?";
			jdbcSupport.beginTransaction();
			jdbcSupport.executeBatch(updateSql, 100, args);
			jdbcSupport.commit();
		} catch (Exception ex) {
			jdbcSupport.rollback();
			logger.error(ex.getMessage());
			throw new DataAccessException("更新失败：" + ex.getMessage(), ex);
		}
	}
}
