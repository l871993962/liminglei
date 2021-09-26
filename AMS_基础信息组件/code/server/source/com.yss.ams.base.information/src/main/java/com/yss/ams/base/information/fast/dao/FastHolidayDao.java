package com.yss.ams.base.information.fast.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.YssFun;

/**
 * @ClassName 
 * @Description 
 * @author houjiaqi
 * @CreateDate 2019年3月5日 上午11:24:30
 * @Copyright (c) 2016, 深圳赢时胜 All Rights Reserved.
 */
public class FastHolidayDao extends GeneralDao {

	public FastHolidayDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
	}

	public Date getWorkDay(Date date, int d) throws DataAccessException {
		Date fileDate = date;
		Connection conn = this.loadNewConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			String sql = " SELECT PKG_FUN_GETDAY_HDAY.GETDAY_HDAY('CN', ?, ?, 'W') AS workDay FROM DUAL ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, YssFun.toSqlDate(date));
			pstmt.setInt(2, d);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				fileDate = rs.getDate("workDay");
			}
		} catch (Exception ex) {
			logger.error("获取下一个工作日失败：" + ex.getMessage(), ex);
			throw new DataAccessException(ex);
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pstmt);
			DbFun.releaseConnection(conn);
		}
		return fileDate;
	}
}
