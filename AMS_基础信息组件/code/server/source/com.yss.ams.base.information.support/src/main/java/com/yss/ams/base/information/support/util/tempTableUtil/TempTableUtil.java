
package com.yss.ams.base.information.support.util.tempTableUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.api.util.YssFun;

/**
 * @classDesc 核算临时表处理类，暂提供对临时表R_D_FEE_ID的insert和truncate操作
 * @version 1.0 2012-2-1
 * @author yh
 */
/**
 * 这个工具类公共接口和系统初始化接口都要用到，所以将其从YSSUCO中复制一份放到基础组件中提供给其他组件使用
 * @author chenbo  20170821
 *
 */
public class TempTableUtil {
	
	private static Logger logger = LogManager.getLogger(TempTableUtil.class);
	
	/**
	 * 把费用ID插入临时表中
	 * @param idList
	 * @throws SQLException
	 */
	public static void insertTempFeeId(Connection conn,List<String> idList) throws SQLException{
		String sql = "INSERT INTO R_D_FEE_ID (ID_D_AC_TD_IVT) VALUES (?)";
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql);
			for(int i =0;i<idList.size();i++){
				pst.setString(1, idList.get(i));
				pst.addBatch();
			}
			pst.executeBatch();
		} catch (SQLException e) {
//			e.printStackTrace();
			logger.log("核算处理：把费用ID插入临时表[R_D_FEE_ID]失败", e);
		} finally{
			if(null != pst) {
				pst.clearBatch(); //clearBatchbyleeyu20151015
			}
			DbFun.closeStatementFinal(pst);
		}
	}
	
	/**
	 * 丢弃表中的全部数据
	 * @throws SQLException
	 */
	public static void clearTempFeeId(Connection conn) throws SQLException{
		Statement stat = null;
		try {
			stat = conn.createStatement();
			//执行truncate命令会自动提交事务，因为放弃此命令
			//stat.executeUpdate("TRUNCATE TABLE R_D_FEE_ID");
			stat.executeUpdate("DELETE FROM  R_D_FEE_ID");
		} catch (SQLException e) {
//			e.printStackTrace();
			logger.log("核算处理：删除临时表[R_D_FEE_ID]中数据失败", e);
		} finally{
			if(null != stat){
				DbFun.closeStatementFinal(stat);
			}
		}
	}
	/**
	 * 丢弃表中的全部数据
	 * @throws SQLException
	 */
	public static void clearTempStock(Connection conn) throws SQLException{
		Statement stat = null;
		try {
			stat = conn.createStatement();
			stat.executeUpdate("DELETE FROM  R_D_FEE_ID");
		} catch (SQLException e) {
			logger.log("核算处理：删除临时表[R_D_FEE_ID]中数据失败", e);
		} finally{
			if(null != stat){
				DbFun.closeStatementFinal(stat);
			}
		}
	}
	
	public static void truncateTempFeeId(Connection conn) throws SQLException{
		Statement stat = null;
		try {
			stat = conn.createStatement();
			//执行truncate命令会自动提交事务，因为放弃此命令
			stat.executeUpdate("TRUNCATE TABLE R_D_FEE_ID");
		} catch (SQLException e) {
//			e.printStackTrace();
			logger.log("核算处理：TRUNCATE临时表[R_D_FEE_ID]中数据失败", e);
		} finally{
			if(null != stat){
				DbFun.closeStatementFinal(stat);
			}
		}
	}
	
	/**
	 * 把证券代码，估值日期，数据来源等插入临时表中
	 * @param idList
	 * @throws SQLException
	 */
	public static void insertActAideTable(Connection conn,List<Object[]> values) throws SQLException{
		String sql = "INSERT   INTO R_D_ACT_AIDE (c_value1,d_date1,c_value2,c_value3,c_value4) VALUES (?,?,?,?,?)";
		PreparedStatement pst = null;
		try {
			
			pst = conn.prepareStatement(sql);
			for(int i =0;i<values.size();i++){
				Object[] eachValue = values.get(i);
				int size = eachValue.length;
				pst.setString(1, (String)eachValue[0]);
				pst.setDate(2, (null != eachValue[1] ? YssFun.toSqlDate((java.util.Date)eachValue[1]) : null));
				pst.setString(3, (String)eachValue[2]);
				pst.setString(4, (String)eachValue[3]);
				pst.setString(5, (size>4 ? (String)eachValue[4] :null));
				pst.addBatch();
			}
			pst.executeBatch();
		} catch (SQLException e) {
//			e.printStackTrace();
			logger.log("核算处理：把证券代码，估值日期，数据来源等插入临时表[R_D_ACT_AIDE]中数据失败", e);
		} finally{
			
			if(null != pst) {
				pst.clearBatch(); //clearBatchbyleeyu20151015
			}
			DbFun.closeStatementFinal(pst);
		}
	}
	
	/**
	 * 丢弃临时表中的全部数据
	 * @throws SQLException
	 */
	public static void deleteActAideTable(Connection conn) throws SQLException{
		Statement stat = null;
		try {
			stat = conn.createStatement();
			//执行truncate命令会自动提交事务，因为放弃此命令
			//stat.executeUpdate("TRUNCATE TABLE R_D_FEE_ID");
			stat.executeUpdate("DELETE FROM  R_D_ACT_AIDE");
		} catch (SQLException e) {
//			e.printStackTrace();
			logger.log("核算处理：删除临时表[R_D_ACT_AIDE]中数据失败", e);
		} finally{
			if(null != stat){
				DbFun.closeStatementFinal(stat);
			}
		}
	}
	
	public static void truncateActAideTable(Connection conn) throws SQLException{
		Statement stat = null;
		try {
			stat = conn.createStatement();
			stat.executeUpdate("TRUNCATE TABLE R_D_ACT_AIDE");
		} catch (SQLException e) {
//			e.printStackTrace();
			logger.log("核算处理：删除临时表[R_D_ACT_AIDE]中数据失败", e);
		} finally{
			if(null != stat){
				DbFun.closeStatementFinal(stat);
			}
		}
	}
	
	/**
	 * 把股票篮子T_D_MP_ETF_SKEP_DETAIL表中的最近一日成交日期、资产代码插入临时表中
	 * 提高关联查询的效率
	 * @param values
	 * @throws SQLException
	 */
	public static void insertTempSkepDetail(Connection conn,List<Object[]> values) throws SQLException{
		String sql = "INSERT INTO R_D_MP_ETF_SKEP_DETAIL (d_trade,c_trade_code) VALUES (?,?)";
		PreparedStatement pst = null;
		try {
			
			pst = conn.prepareStatement(sql);
			for(int i =0;i<values.size();i++){
				Object[] eachValue = values.get(i);
				pst.setDate(1, (null != eachValue[0] ? YssFun.toSqlDate((java.util.Date)eachValue[0]) : null));
				pst.setString(2, (String)eachValue[1]);
				pst.addBatch();
			}
			pst.executeBatch();
		} catch (SQLException e) {
//			e.printStackTrace();
			logger.log("核算处理：把股票篮子T_D_MP_ETF_SKEP_DETAIL表中的最近一日成交日期、资产代码插入临时表[R_D_MP_ETF_SKEP_DETAIL]中失败", e);
		} finally{			
			if(null != pst){
				pst.clearBatch(); //clearBatchbyleeyu20151015
			}
			DbFun.closeStatementFinal(pst);
		}
	}
	
	/**
	 * 丢弃表中的全部数据
	 * @throws SQLException
	 */
	public static void clearTempSkepDetail(Connection conn) throws SQLException{
		Statement stat = null;
		try {
			stat = conn.createStatement();
			stat.executeUpdate("DELETE FROM  R_D_MP_ETF_SKEP_DETAIL");
		} catch (SQLException e) {
//			e.printStackTrace();
			logger.log("核算处理：删除临时表[R_D_MP_ETF_SKEP_DETAIL]中数据失败", e);
		} finally{
			if(null != stat){
				DbFun.closeStatementFinal(stat);
			}
		}
	}
	
	/**
	 * STORY #33039 大版本清算核算性能调优
	 * 把证券行情T_D_MP_SEC_MKT表中的最近一日行情日期、证券内码插入临时表中
	 * 提高关联查询的效率
	 * 
	 * @param values
	 * @throws SQLException
	 */
	public static void insertTempMpSecMkt(Connection conn,List<Object[]> values) throws SQLException{
		String sql = "INSERT INTO R_D_MP_SEC_MKT (D_MKT,C_SEC_CODE) VALUES (?,?)";
		PreparedStatement pst = null;
		try {
			
			pst = conn.prepareStatement(sql);
			for(int i =0;i<values.size();i++){
				Object[] eachValue = values.get(i);
				pst.setDate(1, (null != eachValue[0] ? YssFun.toSqlDate((java.util.Date)eachValue[0]) : null));
				pst.setString(2, (String)eachValue[1]);
				pst.addBatch();
			}
			pst.executeBatch();
		} catch (SQLException e) {
//			e.printStackTrace();
			logger.log("核算处理：把证券行情T_D_MP_SEC_MKT表中的最近一日行情日期、证券内码插入临时表[R_D_MP_SEC_MKT]中失败", e);
		} finally{
			if(null != pst){
				pst.clearBatch(); //clearBatchbyleeyu20151015
			}	
			DbFun.closeStatementFinal(pst);
		}
	}
	
	/**
	 * STORY #33039 大版本清算核算性能调优
	 * 丢弃表中的全部数据
	 * @throws SQLException
	 */
	public static void clearTempMpSecMkt(Connection conn) throws SQLException{
		Statement stat = null;
		try {
			stat = conn.createStatement();
			stat.executeUpdate("DELETE FROM  R_D_MP_SEC_MKT");
		} catch (SQLException e) {
//			e.printStackTrace();
			logger.log("核算处理：删除临时表[R_D_MP_SEC_MKT]中数据失败", e);
		} finally{
			if(null != stat) {
				DbFun.closeStatementFinal(stat);
			}
		}
	}
	
	/**
	 * STORY #33039 大版本清算核算性能调优
	 * 把证券行情T_D_MP_SEC_MKT表中的最近一日行情日期、证券内码插入临时表中
	 * 提高关联查询的效率
	 * 获取小于等于指定日期的证券最近一日行情日期
	 * @param date
	 * @throws SQLException
	 */
	public static void insertTempMpSecMkt(Connection conn, Date date) throws SQLException{
		StringBuffer buf = new StringBuffer();
		PreparedStatement pst = null;
		try {
			buf.append(" INSERT INTO R_D_MP_SEC_MKT(D_MKT, C_SEC_CODE) ");
			buf.append(" SELECT max(D_MKT) as D_MKT, C_SEC_CODE ");
			buf.append(" FROM R_D_MP_SEC_MKT ");
//			buf.append(" WHERE D_MKT <= ? AND N_CHECK_STATE = 1 ");
			buf.append(" WHERE D_MKT <= ? ");
			buf.append(" group BY C_SEC_CODE ");
			pst = conn.prepareStatement(buf.toString());
			
			pst.setDate(1, YssFun.toSqlDate(date));
			pst.executeUpdate();
		} catch (SQLException e) {
//			e.printStackTrace();
			logger.log("核算处理：把证券行情T_D_MP_SEC_MKT表中的最近一日行情日期、证券内码插入临时表[R_D_MP_SEC_MKT]中失败", e);
		} finally{
			StringUtil.clearStringBuffer(buf);
			if(null != pst){
				DbFun.closeStatementFinal(pst);
			}
		}
	}
	
	/**
	 * STORY #33039 大版本清算核算性能调优
	 * 把股票篮子T_D_MP_ETF_SKEP_DETAIL表中的最近一日成交日期、资产代码插入临时表中
	 * 提高关联查询的效率
	 * @param date
	 * @throws SQLException
	 */
	public static void insertTempSkepDetail(Connection conn, Date date) throws SQLException{
		StringBuffer buf = new StringBuffer();
		PreparedStatement pst = null;
		try {
			buf.append(" INSERT INTO R_D_MP_ETF_SKEP_DETAIL(d_trade, c_trade_code) ");
			buf.append(" SELECT max(d_trade) as d_trade, c_trade_code ");
			buf.append(" FROM T_D_MP_ETF_SKEP_DETAIL ");
			buf.append(" WHERE d_trade <= ?  ");
			buf.append(" group BY c_trade_code ");
			pst = conn.prepareStatement(buf.toString());
			
			pst.setDate(1, YssFun.toSqlDate(date));
			pst.executeUpdate();
		} catch (SQLException e) {
//			e.printStackTrace();
			logger.log("核算处理：把股票篮子T_D_MP_ETF_SKEP_DETAIL表中的最近一日成交日期、资产代码插入临时表[R_D_MP_ETF_SKEP_DETAIL]中失败", e);
		} finally{
			StringUtil.clearStringBuffer(buf);
			if(null != pst){
				DbFun.closeStatementFinal(pst);
			}
		}
	}
	
}
