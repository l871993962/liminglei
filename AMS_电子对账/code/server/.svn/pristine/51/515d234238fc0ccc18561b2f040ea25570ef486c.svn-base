package com.yss.uco.elecreco.er.reverse.compare.ye.dao;

import java.beans.PropertyDescriptor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import com.yss.uco.elecreco.er.eryeb.dao.ErYebSqlBuilder;
import com.yss.uco.elecreco.er.eryeb.pojo.ErYeb;
import com.yss.uco.elecreco.er.reverse.compare.dao.DataCompareDao;
import com.yss.uco.elecreco.er.reverse.compare.dao.DataCompareSqlBuilder;
import com.yss.uco.elecreco.er.reverse.out.eryeb.dao.ErYebOutSqlBuilder;
import com.yss.uco.elecreco.er.reverse.out.eryeb.pojo.ErYebOut;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.util.PojoUtils;

public class YeDataCompareDao extends DataCompareDao {

	public YeDataCompareDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
	}

	@Override
	public void deleteInnerData(String portCode, String dzDate, String way,
			String fileType, String rptType, Connection conn) {
		PreparedStatement pstmt = null;
		String sql = "";
		try {
			sql = ((DataCompareSqlBuilder) sqlbuilder).getDeleteInnerDataSql();
			pstmt = conn.prepareStatement(sql);
			int i = 1;
			pstmt.setString(i++, portCode);
			pstmt.setString(i++, fileType);
			pstmt.setString(i++, rptType);
			pstmt.setString(i++, way);
			pstmt.setString(i++, dzDate);
			pstmt.executeUpdate();
		} catch (Exception ex) {
			throw new DataAccessException("删除内部估值表数据失败：" + ex.getMessage(), ex);
		} finally {
			this.closeStatementFinal(pstmt);
		}
	}

	@Override
	public Map<String, BasePojo> getInnerData(String sn) {
		Map<String, BasePojo> map = new HashMap<String, BasePojo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ResultSetTools rsTools = null;
		try {
			conn = this.loadNewConnection();
			rsTools = new ResultSetTools(dbNameResolver, new ErYebSqlBuilder());
			sql = ((DataCompareSqlBuilder) sqlbuilder).getInnerDataSql();
			pstmt = conn.prepareStatement(sql);
			int i = 1;
			pstmt.setString(i++, sn);
			rs = pstmt.executeQuery();
			ErYeb erYeb = new ErYeb();
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(erYeb);
			while (rs.next()) {
				BasePojo t = setResultSet(rsTools,rs, erYeb.getClass(), props); // 提供可以重写的方法byleeyu20130420 
				getConvertKey(props,t);
				erYeb = (ErYeb) t;
				if(erYeb != null && !StringUtil.IsNullOrEmptyT(erYeb.getC_KM_CODE()))
				{
					map.put(erYeb.getC_KM_CODE(), erYeb);
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

	@Override
	public Map<String, BasePojo> getOutData(String portCode, String gzDate,
			String tghCode, String rptType) {
		Map<String, BasePojo> map = new HashMap<String, BasePojo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ResultSetTools rsTools = null;
		try {
			conn = this.loadNewConnection();
			rsTools = new ResultSetTools(dbNameResolver, new ErYebOutSqlBuilder());
			sql = ((DataCompareSqlBuilder) sqlbuilder).getOutDataSql();
			pstmt = conn.prepareStatement(sql);
			int i = 1;
			pstmt.setString(i++, portCode);
			pstmt.setString(i++, tghCode);
			pstmt.setString(i++, gzDate);
			rs = pstmt.executeQuery();
			ErYebOut erYeb = new ErYebOut();
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(erYeb);
			while (rs.next()) {
				BasePojo t = setResultSet(rsTools,rs, erYeb.getClass(), props); // 提供可以重写的方法byleeyu20130420 
				getConvertKey(props,t);
				erYeb = (ErYebOut) t;
				//过滤无效数据
				if(erYeb != null && !StringUtil.IsNullOrEmptyT(erYeb.getC_KM_CODE()) && !"null".equalsIgnoreCase(erYeb.getC_KM_CODE()))
				{
					map.put(erYeb.getC_KM_CODE(), erYeb);
				}
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询外部余额表失败：" + ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return map;
	}

	@Override
	public boolean updateReportWay(String value, String sn, String portCode,
			String dzDate, String fileType, String rptType, Connection conn) {
		boolean result = false;
		PreparedStatement pstmt = null;
		String sql = "";
		try {
			sql = ((DataCompareSqlBuilder) sqlbuilder).getUpdateReportWaySql();
			pstmt = conn.prepareStatement(sql);
			int index = 1;
			pstmt.setString(index++, value);
			pstmt.setString(index++, sn);
			pstmt.setString(index++, portCode);
			pstmt.setString(index++, dzDate);
			pstmt.setString(index++, dzDate);
			pstmt.setString(index++, fileType);
			pstmt.setString(index++, rptType);
			pstmt.executeUpdate();
			result = true;
			return result;
		} catch (Exception ex) {
			throw new DataAccessException("更新余额表对账方向失败：" + ex.getMessage(), ex);
		} finally {
			closeStatementFinal(pstmt);
		}
	}
	
}
