package com.yss.ams.sec.information.modules.mp.hggthq.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.YssFun;

/**
 * 回购收益行情 dao
 * @author gongyue
 * 资讯信息拆分	2017.7.5 STORY #42948 资讯信息管理组件化拆分
 */
public class CounterRateDao extends GeneralDao {

	public CounterRateDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
	}
	
	/**
	 * 根据公共回购收益行情变更对应证券利率
	 * @param mktDate 行情日期
	 * @param duration 回购期限
	 * @param rate 变更后利率
	 * @return count 修改数量
	 */
	public int updateSecRate(Date mktDate, int duration, double rate) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		int count = 0;
		CounterRateSqlBuilder sqlBuilder = null;
		try {
			sqlBuilder = new CounterRateSqlBuilder();

			conn = this.loadNewConnection();

			sql = sqlBuilder.UpdateSetRateSql();

			pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, rate);
			pstmt.setDate(2, YssFun.toSqlDate(mktDate));
			pstmt.setInt(3, duration);
			
			count = pstmt.executeUpdate();
			logger.log("修改证券回购收益行情"+count+"条");
		} catch (Exception ex) {
			throw new DataAccessException("修改证券回购收益行情失败：" + ex.getMessage(), ex);
		} finally {
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return count;
	}
	
	/**
	 * 根据行情日期 ，回购期限，操作类型对回购收益行情数据进行处理，返回数据修改的数量
	 * @param mktDate 行情日期
	 * @param duration 回购期限
	 * @param operType 操作类型
	 * @return count 修改数量
	 */
	public int syncSecRate(Date mktDate, int duration, String operType) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		int count = 0;
		CounterRateSqlBuilder sqlBuilder = null;
		try {
			sqlBuilder = new CounterRateSqlBuilder();

			conn = this.loadNewConnection();

			sql = sqlBuilder.syncSecRateSql(operType);

			pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, YssFun.toSqlDate(mktDate));
			pstmt.setInt(2, duration);
			
			count = pstmt.executeUpdate();
			logger.log("修改证券回购收益行情"+count+"条");
		} catch (Exception ex) {
			throw new DataAccessException("修改证券回购收益行情失败：" + ex.getMessage(), ex);
		} finally {
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return count;
	}

}
