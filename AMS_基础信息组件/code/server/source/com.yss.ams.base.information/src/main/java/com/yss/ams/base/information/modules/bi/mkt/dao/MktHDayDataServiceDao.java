package com.yss.ams.base.information.modules.bi.mkt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.bi.hday.pojo.Hday;
import com.yss.framework.api.common.co.Mkt;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.util.YssFun;
import com.yss.framework.db.OraDbTool;
import com.yss.framework.util.DateUtil;

/**
 * 交易市场数据dao层
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
public class MktHDayDataServiceDao extends GeneralDao {

	public MktHDayDataServiceDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
	}

	/**
	 * 查询所有交易市场
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public List<Mkt> getAllDataList() throws ServiceException {
		List<Mkt> pojoList = new ArrayList<Mkt>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ResultSetTools rsTools = null;
		MktHDayDataSqlBuilder dsServiceBuilder = null;
		Mkt t = null;
		try {
			dsServiceBuilder = new MktHDayDataSqlBuilder();
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
				t = rsTools.ResultToBeanGeneric(rs, Mkt.class);
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
	 * 根据市场代码查询交易市场
	 * 
	 * @param code
	 *            市场代码
	 * @return 交易市场实体
	 * @throws ServiceException
	 */
	public Mkt getDataByCode(String code) throws ServiceException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ResultSetTools rsTools = null;
		MktHDayDataSqlBuilder dsServiceBuilder = null;
		Mkt mkt = null;
		try {
			dsServiceBuilder = new MktHDayDataSqlBuilder();
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
				mkt = rsTools.ResultToBeanGeneric(rs, Mkt.class);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return mkt;
	}

	/**
	 * 根据市场代码查询多个交易市场
	 * 
	 * @param types
	 *            市场代码
	 * @return 交易市场列表
	 * @throws ServiceException
	 */
	public List<Mkt> getDataListByTypes(String[] types) throws ServiceException {
		List<Mkt> pojoList = new ArrayList<Mkt>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ResultSetTools rsTools = null;
		MktHDayDataSqlBuilder dsServiceBuilder = null;
		Mkt mkt = null;
		try {
			dsServiceBuilder = new MktHDayDataSqlBuilder();
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
				mkt = rsTools.ResultToBeanGeneric(rs, Mkt.class);
				pojoList.add(mkt);
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
	 * 获取交易市场代码转换MAP
	 * 
	 * @return 代码转换MAP
	 * @throws ServiceException
	 */
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		HashMap<String, String> keyValueMap = new HashMap<String, String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ResultSetTools rsTools = null;
		MktHDayDataSqlBuilder dsServiceBuilder = null;
		Mkt mkt = null;
		try {
			dsServiceBuilder = new MktHDayDataSqlBuilder();
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
				mkt = rsTools.ResultToBeanGeneric(rs, Mkt.class);
				keyValueMap.put(mkt.getC_MKT_CODE(), mkt.getC_MKT_NAME());
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
	 * 根据节假日群代码查询对应节假日信息
	 * 
	 * @param code
	 *            节假日群代码
	 * @return MAP<"节假日群代码",<"年份","节假日期">>
	 * @throws ServiceException
	 */
	public HashMap<String, HashMap<Integer, List<Date>>> getHolidaysByCode(
			String code) throws ServiceException {
		List<Date> dateList = null;
		HashMap<Integer, List<Date>> hMap = new HashMap<Integer, List<Date>>();
		HashMap<String, HashMap<Integer, List<Date>>> hashMap = new HashMap<String, HashMap<Integer, List<Date>>>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		MktHDayDataSqlBuilder dsServiceBuilder = null;
		Hday hday = null;
		try {
			dsServiceBuilder = new MktHDayDataSqlBuilder();
			conn = this.loadNewConnection();
			sql = dsServiceBuilder.getHoliDayDataByCode();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);
			logger.debug(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				hday = new Hday();
				hday.setId(rs.getString("C_IDEN"));
				hday.setC_HDAY_CODE(rs.getString("C_HDAY_CODE"));
				hday.setD_HDAY(YssFun.formatDate(rs.getDate("D_HDAY")));
				hday.setC_DATE_TYPE(rs.getString("C_DATE_TYPE"));
				hday.setC_DATE_TYPE(rs.getString("C_DESC"));
				hday.setN_YEAR(rs.getInt("N_YEAR"));
				hday.setAuditState(rs.getInt("N_CHECK_STATE"));
				hday.setAuditDate(rs.getString("C_CHECK_TIME"));
				hday.setOperator(rs.getString("C_CHECK_BY"));
				hday.setModifier(rs.getString("C_UPDATE_BY"));
				hday.setModifyDate(rs.getString("C_UPDATE_TIME"));
				int year = hday.getN_YEAR();
				if (hMap.get(year) != null && hMap.get(year).size() > 0) {
					hMap.get(year).add(
							DateUtil.stringtoDate(hday.getD_HDAY(),
									DateUtil.LONG_DATE_FORMAT));
				} else {
					dateList = new ArrayList<Date>();
					dateList.add(DateUtil.stringtoDate(hday.getD_HDAY(),
							DateUtil.LONG_DATE_FORMAT));
					hMap.put(year, dateList);
				}
			}
			hashMap.put(code, hMap);
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return hashMap;
	}
}
