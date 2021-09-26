package com.yss.ams.base.information.modules.bi.ieLink.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.bi.ieLink.pojo.IeLink;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.db.OraDbTool;

/**
 * @classDesc 收支连接
 * @version 1.0 2012-11-29
 * @author yh
 */

/**
 * @author yuankai 公共信息拆分  2017.5.31
 */
public class IeLinkDao extends GeneralDao {

	/**
	 * @param pool
	 * @param sqlBuilder
	 */
	public IeLinkDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 根据结果集设置收支名称后返回收支链接设置pojo
	 * @param rsTools
	 * @param rs
	 * @param clazz
	 */
	@Override
	protected  BasePojo setResultSet(ResultSetTools rsTools,ResultSet rs,Class<?> clazz) throws Exception{
		BasePojo pojo = rsTools.ResultToBean(rs, clazz);
		((IeLink)pojo).setC_FEE_NAME(rs.getString(IeLinkColumnName.c_FEE_NAME.name()));
		return pojo;
	}

	/* START 数据服务方法 */
	/**
	 * 获取所有收支链接设置所有数据
	 * @return	收支链接设置数据集合
	 * @throws Exception
	 */
	public List<IeLink> getAllDataList() throws ServiceException {
		List<IeLink> pojoList = new ArrayList<IeLink>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		IeLinkSqlBuilder dsServiceBuilder = null;
		IeLink t = null;
		try {
			dsServiceBuilder = new IeLinkSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = dsServiceBuilder.getAllDataSql();

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			List<String> feeCodes = new ArrayList<String>();
			while (rs.next()) {
				if(feeCodes.contains(rs.getString(IeLinkColumnName.c_FEE_CODE.name()))) {
					continue;
				} else {
					feeCodes.add(rs.getString(IeLinkColumnName.c_FEE_CODE.name()));
				}
				t = rsTools.ResultToBeanGeneric(rs, IeLink.class);
				t.setC_FEE_NAME(rs.getString(IeLinkColumnName.c_FEE_NAME.name()));
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
	 * 根据收支链接代码获取收支链接设置
	 * @param code  收支链接
	 * @return
	 * @throws Exception
	 */
	public IeLink getDataByCode(String code) throws ServiceException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		IeLinkSqlBuilder dsServiceBuilder = null;
		IeLink t = null;
		try {
			dsServiceBuilder = new IeLinkSqlBuilder();
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
				t = rsTools.ResultToBeanGeneric(rs, IeLink.class);
				t.setC_FEE_NAME(rs.getString(IeLinkColumnName.c_FEE_NAME.name()));
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
	 * 根据收支链接代码获取收支链接设置list
	 * @param code  收支链接
	 * @return
	 * @throws Exception
	 */
	public List<IeLink> getDataListByCodes(String[] codes)
			throws ServiceException {
		List<IeLink> pojoList = new ArrayList<IeLink>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		IeLinkSqlBuilder dsServiceBuilder = null;
		IeLink t = null;
		try {
			dsServiceBuilder = new IeLinkSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = dsServiceBuilder.getDataListByCodes();

			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(codes,conn));

			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, IeLink.class);
				t.setC_FEE_NAME(rs.getString(IeLinkColumnName.c_FEE_NAME.name()));
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
	 * 根据收支链接类型获取收支链接设置
	 * @param types 收支链接
	 * @return
	 * @throws Exception
	 */
	public List<IeLink> getDataListByTypes(String[] types)
			throws ServiceException {
		List<IeLink> pojoList = new ArrayList<IeLink>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		IeLinkSqlBuilder dsServiceBuilder = null;
		IeLink t = null;
		try {
			dsServiceBuilder = new IeLinkSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = dsServiceBuilder.getDataListByTypes();

			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(types,conn));

			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, IeLink.class);
				t.setC_FEE_NAME(rs.getString(IeLinkColumnName.c_FEE_NAME.name()));
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
	 * 获取收支链接设置转换map
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		HashMap<String, String> keyValueMap = new HashMap<String, String>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";

		IeLinkSqlBuilder dsServiceBuilder = null;
		IeLink t = null;
		try {
			dsServiceBuilder = new IeLinkSqlBuilder();

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = dsServiceBuilder.getAllDataSql();

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				/*
				 * Author : ChenLong
				 * Date   : 2016-10-18
				 * Status : Modify
				 * Comment: 费用品种代码转名称
				 * */
				keyValueMap.put(rs.getString("c_fee_code"), rs.getString("c_fee_name"));
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
	 * 根据多个收支链接类型获取收支链接设置
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public List<IeLink> getDataListByKeys(String[] keys)
			throws ServiceException {
		List<IeLink> pojoList = new ArrayList<IeLink>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		IeLinkSqlBuilder dsServiceBuilder = null;
		IeLink t = null;
		try {
			dsServiceBuilder = new IeLinkSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = dsServiceBuilder.getDataListByTypes();

			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(keys,conn));

			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, IeLink.class);
				t.setC_FEE_NAME(rs.getString(IeLinkColumnName.c_FEE_NAME.name()));
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
	 * 根据多个收支链接上级费用节点获取收支链接设置list
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public List<IeLink> getDataListByParentCode(String[] codes) {
		List<IeLink> pojoList = new ArrayList<IeLink>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		IeLinkSqlBuilder dsServiceBuilder = null;
		IeLink t = null;
		try {
			dsServiceBuilder = new IeLinkSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = dsServiceBuilder.getDataListByParentCodes();

			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(codes,conn));

			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, IeLink.class);
				t.setC_FEE_NAME(rs.getString(IeLinkColumnName.c_FEE_NAME.name()));
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

	public String getFeeCodeByKmCode(String[] kmcodes) {
		
		return null;
	}

	/**
	 * add by liyanjun 2016-2-17 BUG #126592 科目体系界面的费用代码选项数据重复
	 * @return
	 * @throws ServiceException
	 */
	public List<IeLink> getAllFeeDataList() throws ServiceException {
		List<IeLink> pojoList = new ArrayList<IeLink>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		IeLinkSqlBuilder dsServiceBuilder = null;
		IeLink t = null;
		try {
			dsServiceBuilder = new IeLinkSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = dsServiceBuilder.getAllFeeDataSql();

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, IeLink.class);
				t.setC_FEE_NAME(rs.getString(IeLinkColumnName.c_FEE_NAME.name()));
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
