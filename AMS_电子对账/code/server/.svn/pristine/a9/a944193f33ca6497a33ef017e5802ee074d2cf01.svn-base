package com.yss.uco.elecreco.er.reverse.compare.dao;

import java.beans.PropertyDescriptor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.util.PojoUtils;
import com.yss.uco.elecreco.bi.elecrela.dao.ElecRelaSqlBuilder;
import com.yss.uco.elecreco.er.erresult.dao.ErResultDao;
import com.yss.uco.elecreco.er.reverse.manager.info.dao.ErReveInfoSqlBuilder;
import com.yss.uco.elecreco.er.reverse.manager.info.pojo.ErReveInfo;
import com.yss.uco.elecreco.er.reverse.manager.result.dao.ReveResultDao;
import com.yss.uco.elecreco.er.reverse.manager.result.dao.ReveResultSqlBuilder;
import com.yss.uco.elecreco.er.reverse.manager.result.pojo.ReveResult;
import com.yss.uco.elecreco.er.reverse.map.assmap.dao.AssMapDao;
import com.yss.uco.elecreco.er.reverse.map.assmap.dao.AssMapSqlBuilder;
import com.yss.uco.elecreco.support.bean.ElecRela;

public abstract class DataCompareDao extends GeneralDao{

	public DataCompareDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
	}
	
	public abstract Map<String, BasePojo> getInnerData(String sn);
	
	public abstract Map<String, BasePojo> getOutData(String portCode, String gzDate, String tghCode, String rptType);
	
	/**
	 * 删除生成的内部数据
	 * @param portCode  组合代码
	 * @param dzDate	对账日期
	 * @param way		对账方向
	 * @param fileType	对账类型
	 * @param rptType	报表类型
	 * @param conn
	 */
	public abstract void deleteInnerData(String portCode,String dzDate,String way,String fileType, String rptType, Connection conn);
	
	public void updateErInfoWay(String value,String sn,String portCode,String dzDate,String fileType,String rptType,Connection conn) {
		PreparedStatement pstmt = null;

		String sql = "";
		try {
			sql = ((DataCompareSqlBuilder) sqlbuilder).getUpdateErInfoWaySql();
			pstmt = conn.prepareStatement(sql);
			int index = 1;
			pstmt.setString(index++, value);
			pstmt.setString(index++, sn);
			pstmt.setString(index++, portCode);
			pstmt.setString(index++, dzDate);
			pstmt.setString(index++, fileType);
			pstmt.setString(index++, rptType);
			pstmt.executeUpdate();
		} catch (Exception ex) {
			throw new DataAccessException("更新正向电子对账表对账方向失败：" + ex.getMessage(), ex);
		} finally {
			closeStatementFinal(pstmt);
		}
	}
	/**
	 * 删除正向电子对账详情
	 * @param value
	 * @param portCode
	 * @param dzDate
	 * @param fileType
	 * @param rptType
	 * @param conn
	 */
	public void deleteErInfo(String value,String portCode,String dzDate,String fileType,String rptType,Connection conn) {
		PreparedStatement pstmt = null;

		String sql = "";
		try {
			sql = ((DataCompareSqlBuilder) sqlbuilder).getDeleteErInfoSql();
			pstmt = conn.prepareStatement(sql);
			int index = 1;
			pstmt.setString(index++, value);
			pstmt.setString(index++, portCode);
			pstmt.setString(index++, dzDate);
			pstmt.setString(index++, fileType);
			pstmt.setString(index++, rptType);
			pstmt.executeUpdate();
		} catch (Exception ex) {
			throw new DataAccessException("删除数据失败：" + ex.getMessage(), ex);
		} finally {
			closeStatementFinal(pstmt);
		}
	}
	/**
	 * 先删除T_D_ER_REVE_RESRELA表里的数据
	 * 再删除T_D_ER_REVE_RESULT表里的数据
	 * @param portCode
	 * @param fileType
	 * @param rptType
	 * @param startDate
	 * @param endDate
	 * @param conn
	 */
	private void deleteReveResRela(String portCode,String fileType,String rptType,String startDate,String endDate,Connection conn)
	{
		PreparedStatement pstmt = null;
		String sql = "";
		try {
			sql = ((DataCompareSqlBuilder) sqlbuilder).getDeleteReveResrelaSql();
			pstmt = conn.prepareStatement(sql);
			int index = 1;
			pstmt.setString(index++, portCode);
			pstmt.setString(index++, fileType);
			pstmt.setString(index++, rptType);
			pstmt.setString(index++, startDate);
			pstmt.setString(index++, endDate);
			pstmt.executeUpdate();
		} catch (Exception ex) {
			throw new DataAccessException("删除数据失败：" + ex.getMessage(), ex);
		} finally {
			closeStatementFinal(pstmt);
		}
	}
	/**
	 * 先删除T_D_ER_REVE_RESRELA表里的数据
	 * 再删除T_D_ER_REVE_RESULT表里的数据
	 * @param portCode
	 * @param fileType
	 * @param rptType
	 * @param startDate
	 * @param endDate
	 * @param conn
	 */
	private void deleteReveResult(String portCode,String fileType,String rptType,String startDate,String endDate,Connection conn)
	{
		PreparedStatement pstmt = null;
		String sql = "";
		try {
			sql = ((DataCompareSqlBuilder) sqlbuilder).getDeleteReveResultSql();
			pstmt = conn.prepareStatement(sql);
			int index = 1;
			pstmt.setString(index++, portCode);
			pstmt.setString(index++, fileType);
			pstmt.setString(index++, rptType);
			pstmt.setString(index++, startDate);
			pstmt.setString(index++, endDate);
			pstmt.executeUpdate();
		} catch (Exception ex) {
			throw new DataAccessException("删除数据失败：" + ex.getMessage(), ex);
		} finally {
			closeStatementFinal(pstmt);
		}
	}
	
	/**
	 * 先删除T_D_ER_REVE_RESRELA表里的数据
	 * 再删除T_D_ER_REVE_RESULT表里的数据
	 * @param portCode
	 * @param fileType
	 * @param rptType
	 * @param startDate
	 * @param endDate
	 * @param conn
	 */
	public void deleteReveDzResult(String portCode,String fileType,String rptType,String startDate,String endDate,Connection conn)
	{
		try {
			deleteReveResRela(portCode, fileType, rptType, startDate, endDate, conn);
			deleteReveResult(portCode, fileType, rptType, startDate, endDate, conn);
		} catch (Exception ex) {
			throw new DataAccessException("删除数据失败：" + ex.getMessage(), ex);
		} finally {
		}
	}
	
	/**
	 * 先删除T_D_ER_REVE_RESRELA表里的数据
	 * 再删除T_D_ER_REVE_RESULT表里的数据
	 * @param portCode
	 * @param fileType
	 * @param rptType
	 * @param startDate
	 * @param endDate
	 */
	public void deleteReveDzResult(String portCode,String fileType,String rptType,String startDate,String endDate)
	{
		Connection conn = null;
		boolean bTrans = false;
		try {
			conn = this.loadNewConnection();
			conn.setAutoCommit(bTrans);
			bTrans = true;
			deleteReveResRela(portCode, fileType, rptType, startDate, endDate, conn);
			deleteReveResult(portCode, fileType, rptType, startDate, endDate, conn);
			conn.commit();
			conn.setAutoCommit(bTrans);
			bTrans = false;
		} catch (Exception ex) {
			throw new DataAccessException("删除数据失败：" + ex.getMessage(), ex);
		} finally {
			DbFun.endTransFinal(conn,bTrans);
			releaseConnection(conn);
		}
	}
	/**
	 * 根据组合，对账类型，报表类型，对账日期删除
	 * @param info
	 */
	public void deleteErReveInfo(ErReveInfo info,Connection conn)
	{
		deleteErReveInfo(info.getC_PORT_CODE(), info.getC_FILE_TYPE(), info.getC_RPT_TYPE(), info.getD_DATE(),conn);
	}
	
	public void deleteErReveInfo(String portCode,String fileType,String rptType,String gzDate,Connection conn)
	{
		PreparedStatement pstmt = null;

		String sql = "";
		try {
			sql = ((DataCompareSqlBuilder) sqlbuilder).deleteErReveInfoSql();
			pstmt = conn.prepareStatement(sql);
			int index = 1;
			pstmt.setString(index++, portCode);
			pstmt.setString(index++, fileType);
			pstmt.setString(index++, rptType);
			pstmt.setString(index++, gzDate);
			pstmt.executeUpdate();
		} catch (Exception ex) {
			throw new DataAccessException("删除失败：" + ex.getMessage(), ex);
		} finally {
			closeStatementFinal(pstmt);
		}
	}
	/**
	 * 先根据根据组合，对账类型，报表类型，对账日期删除数据，在插入
	 * @param info
	 */
	public void saveErReveInfo(ErReveInfo info) 
	{
		Connection conn = null;
		boolean bTrans = false;
		try {
			conn = this.loadNewConnection();
			conn.setAutoCommit(bTrans);
			bTrans = true;
			deleteErReveInfo(info, conn);
			ErResultDao erResultDao = new ErResultDao(pool, new ErReveInfoSqlBuilder());
			erResultDao.insert(info, conn);
			conn.commit();
			conn.setAutoCommit(bTrans);
			bTrans = false;
		} catch (Exception ex) {
			throw new DataAccessException("保存反向对账详情失败：" + ex.getMessage(), ex);
		} finally {
			DbFun.endTransFinal(conn,bTrans);
			releaseConnection(conn);
		}
	}
	
	/**
	 * 先删除上次的对账结果
	 * 再保存
	 * @param resList
	 * @param conn
	 */
	public void saveDzResult(List<ReveResult> resList,Connection conn)
	{
		ReveResultDao reveResultDao = new ReveResultDao(pool, new ReveResultSqlBuilder());
		try {
			reveResultDao.insert(resList, conn);
		} catch (Exception ex) {
			throw new DataAccessException("保存反向对账结果失败：" + ex.getMessage(), ex);
		} finally {
		}
	}
	
	/**
	 * 先删除上次的对账结果
	 * 再保存
	 * @param resList
	 */
	public void saveDzResult(List<ReveResult> resList)
	{
		ReveResultDao reveResultDao = new ReveResultDao(pool, new ReveResultSqlBuilder());
		Connection conn = null;
		try {
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			reveResultDao.insert(resList, conn);
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception ex) {
			throw new DataAccessException("保存反向对账结果失败：" + ex.getMessage(), ex);
		} finally {
			releaseConnection(conn);
		}
	}
	/**
	 * key:zbcode
	 * @param fileType
	 * @return
	 */
	public Map<String,ElecRela> getZbItems(String fileType)
	{
		Map<String,ElecRela> map = new HashMap<String, ElecRela>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ResultSetTools rsTools = null;
		try {
			conn = this.loadNewConnection();
			rsTools = new ResultSetTools(dbNameResolver, new ElecRelaSqlBuilder());
			// conn.setAutoCommit(false);
			sql = ((DataCompareSqlBuilder) sqlbuilder).getInnerZbItemDataSql();

			pstmt = conn.prepareStatement(sql);
			int i = 1;
			pstmt.setString(i++, fileType);
			rs = pstmt.executeQuery();
			ElecRela rela = new ElecRela();
			BasePojo pojo = (BasePojo) rela;
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(pojo);
			while (rs.next()) {
				BasePojo t = setResultSet(rsTools,rs, rela.getClass(), props); // 提供可以重写的方法byleeyu20130420 
				rela = (ElecRela) t;
				if(rela != null && !StringUtil.IsNullOrEmptyT(rela.getC_ZB_CODE()))
				{
					String code = rela.getC_ZB_CODE().trim();
					rela.setC_ZB_CODE(code);
					map.put(code, rela);
				}
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询内部估值表失败：" + ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return map;
	}
	
	public String getDzMode(String portCode,String fileType)
	{
		AssMapDao dao = new AssMapDao(pool, new AssMapSqlBuilder());
		return dao.getDzMode(portCode, fileType);
	}
	/***
	 * 更新报表的对账方向
	 * @param value
	 * @param sn
	 * @param portCode
	 * @param dzDate
	 * @param fileType
	 * @param rptType
	 * @param conn
	 */
	public abstract boolean updateReportWay(String value, String sn, String portCode,
			String dzDate, String fileType, String rptType, Connection conn);
}
