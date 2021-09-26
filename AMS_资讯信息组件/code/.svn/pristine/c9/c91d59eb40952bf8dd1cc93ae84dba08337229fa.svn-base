package com.yss.ams.sec.information.modules.pub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import com.yss.ams.base.information.support.util.tempTableUtil.TempTableUtil;
import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecBase;
import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.util.YssFun;

/**
 * chenbo 2017-08-19 TASK #332232 
 * 公共信息处理界面中的系统初始化下的接口拆分到资讯组件中，以提供每百元利息生成功能
 * 
 */
/**
 * @classDesc
 * @version 1.0 2012-11-5
 * @author yh
 */
public class SecurityHandler {
	
	private Logger logger = LogManager.getLogger(getClass());
	
	/**
	 * 该类的唯一实例
	 */
	private static SecurityHandler instance = new SecurityHandler();

	/**
	 * 私有的构造方法
	 */
	private SecurityHandler() {

	}

	/**
	 * 获取类实例
	 * 
	 * @return
	 */
	public static SecurityHandler getInstance(){
		return instance;
	}
	
	
	
	/**
	 * 查询证券信息
	 * @param conn 数据库连接
	 * @param secCode 证券代码
	 * @param businessDate 业务日期
	 * @return
	 * @throws SQLException
	 */
	public SecBase querySecuriry(Connection conn,String secCode,Date businessDate) throws SQLException{
		if(null == secCode || null == businessDate){
			return null;
		}
		SecBase security = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM T_P_SV_SEC_BASE WHERE N_CHECK_STATE = 1 AND C_SEC_CODE = ? AND D_TO_LIST <= ? AND D_OFF_LIST >= ? "	;
		try {
			//chenbo 20170821 TASK #332232   这个类QueryRunner在YSSUCO中未拆分出来，所以改为使用JAVA自己的方法
		//	pst = QueryRunner.preparedStatement(conn, sql, new Object[]{secCode,businessDate,businessDate});
		    pst=conn.prepareStatement(sql);
		    pst.setString(1, secCode);
		    pst.setDate(2, (java.sql.Date) businessDate);
		    pst.setDate(3, (java.sql.Date) businessDate);
			rs = pst.executeQuery();
			if(rs.next()){
				security = toBean(rs);
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			logger.log("查询证券信息时失败!", e);
			throw e;
		} finally{
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
//			DBUtils.cleanUp(rs,pst);
		}
		return security;
	}
	
	/**
	 * 查询证券信息
	 * @param conn 数据库连接
	 * @param secCode 证券代码
	 * @return
	 * @throws SQLException
	 */
	public SecBase querySecuriry(Connection conn,String secCode) throws SQLException{
		if(null == secCode ){
			return null;
		}
		SecBase security = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM T_P_SV_SEC_BASE WHERE N_CHECK_STATE = 1 AND C_SEC_CODE = ? ORDER BY D_OFF_LIST DESC"	;
		try {
			//chenbo 20170821 TASK #332232   这个类QueryRunner在YSSUCO中未拆分出来，所以改为使用JAVA自己的方法
		//	pst = QueryRunner.preparedStatement(conn, sql, new Object[]{secCode});
		    pst=conn.prepareStatement(sql);
		    pst.setString(1, secCode);
			rs = pst.executeQuery();
			if(rs.next()){
				security = toBean(rs);
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			logger.log("查询证券信息时失败!", e);
			throw e;
		} finally{
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
//			DBUtils.cleanUp(rs,pst);
		}
		return security;
	}
	
	/**
	 * 查询证券信息
	 * @param conn 数据库连接
	 * @param secCodes 证券代码列表
	 * @return
	 * @throws SQLException
	 */
	public HashMap<String, SecBase> querySecurity(Connection conn, List<String> secCodes) throws SQLException{
		HashMap<String, SecBase> secMap = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT A.* FROM T_P_SV_SEC_BASE A, R_D_FEE_ID B WHERE A.C_SEC_CODE = B.ID_D_AC_TD_IVT AND A.N_CHECK_STATE = 1 "	;
			TempTableUtil.clearTempFeeId(conn);
			TempTableUtil.insertTempFeeId(conn, secCodes);
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()){
				if(null == secMap){
					secMap = new HashMap<String, SecBase>();
				}
				SecBase security = toBean(rs);
				secMap.put(security.getC_SEC_CODE(), security);
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			logger.log("查询证券信息时失败!", e);
			throw e;
		} finally{
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
//			DBUtils.cleanUp(rs,pst);
		}
		return secMap;
		
	}
	
	
	
	/**
	 * to bean
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public SecBase toBean(ResultSet rs) throws SQLException{
		SecBase security = null;
		if(null == rs){
			return null;
		}
		security = new SecBase();
		security.setC_SEC_CODE(rs.getString("C_SEC_CODE"));
		security.setC_SEC_NAME(rs.getString("C_SEC_NAME"));
		security.setC_SEC_MKT_CODE(rs.getString("C_SEC_MKT_CODE"));
		security.setC_SEC_ISIN_CODE(rs.getString("C_SEC_ISIN_CODE"));
		security.setC_MKT_CODE(rs.getString("C_MKT_CODE"));
		security.setC_SEC_VAR_CODE(rs.getString("C_SEC_VAR_CODE"));
		security.setC_DC_CODE(rs.getString("C_DC_CODE"));
		security.setN_PRICE_FCR(rs.getBigDecimal("N_PRICE_FCR")+"");
		security.setC_SEC_CODE_TRG(rs.getString("C_SEC_CODE_TRG"));
		security.setN_AMOUNT_HD(rs.getBigDecimal("N_AMOUNT_HD")+"");
		security.setN_FV_ISSUE(rs.getBigDecimal("N_FV_ISSUE")+"");
//		security.setStartUseDate(rs.getDate("D_TO_LIST"));
//		security.setEndUseDate(rs.getDate("D_OFF_LIST"));
		security.setD_TO_LIST(rs.getDate("D_TO_LIST"));
		security.setD_OFF_LIST(rs.getDate("D_OFF_LIST"));
		security.setC_DV_VAR_DUR(rs.getString("C_DV_VAR_DUR"));
		security.setC_DV_QUT_MOD(rs.getString("C_DV_QUT_MOD"));
		security.setN_RATE(rs.getBigDecimal("N_RATE"));
		security.setN_FV_IR(rs.getBigDecimal("N_FV_IR"));
		security.setN_PRICE_ISSUE(rs.getBigDecimal("N_PRICE_ISSUE"));
		security.setC_DV_AI_MOD(rs.getString("C_DV_AI_MOD"));
		security.setC_DV_PI_MOD(rs.getString("C_DV_PI_MOD"));
		security.setD_AI_BEGIN(YssFun.formatDate(rs.getDate("D_AI_BEGIN")));
		security.setD_AI_END(YssFun.formatDate(rs.getDate("D_AI_END")));
		security.setC_DESC(rs.getString("C_DESC"));
		security.setC_DV_AI_EXPR(rs.getString("C_DV_AI_EXPR"));
		security.setC_ORG_CODE(rs.getString("C_ORG_CODE"));
		security.setC_ORG_CODE(rs.getString("C_ORG_CODE"));
		security.setN_RATIO(rs.getBigDecimal("N_RATIO"));
		security.setD_END(YssFun.formatDate(rs.getDate("D_END")));
		return security;
	}
	
	/**
	 * addByLiyongjun 20161210根据标识改变交易市场（深圳跨市场ETF联接基金）
	 * BUG #147412 【南方基金】（紧急）ETF联接基金深圳跨市场ETF联接基金现金替代款科目进上海现金替代—跨市，科目进错，应该进深圳跨市场现金替代—跨市
	 * 查询证券信息
	 * @param conn 数据库连接
	 * @param secCodes 证券代码列表
	 * @return
	 * @throws SQLException
	 */
	public HashMap<String, SecBase> querySecurityByMKT(Connection conn, List<String> secCodes, String mktCode) throws SQLException{
		HashMap<String, SecBase> secMap = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT A.* FROM T_P_SV_SEC_BASE A, R_D_FEE_ID B WHERE A.C_SEC_CODE = B.ID_D_AC_TD_IVT AND A.N_CHECK_STATE = 1 "	;
			TempTableUtil.clearTempFeeId(conn);
			TempTableUtil.insertTempFeeId(conn, secCodes);
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()){
				if(null == secMap){
					secMap = new HashMap<String, SecBase>();
				}
				SecBase security = toBean(rs);
				if(security.getC_MKT_CODE().equalsIgnoreCase("XSHG")){
					security.setC_MKT_CODE("XSHE");
				}
				secMap.put(security.getC_SEC_CODE(), security);
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			throw new SQLException("查询证券出错",e);
		} finally{
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
//			DBUtils.cleanUp(rs,pst);
		}
		return secMap;
		
	}
}
