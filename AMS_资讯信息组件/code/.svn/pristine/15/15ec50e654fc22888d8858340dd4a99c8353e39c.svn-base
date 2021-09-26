package com.yss.ams.sec.information.modules.pub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;



import com.yss.framework.api.common.YssConstant;
import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.util.YssFun;
import com.yss.framework.db.OraDbTool;
import com.yss.ams.sec.information.activator.SecInfoActivator;
import com.yss.ams.sec.information.support.modules.pub.pojo.FiHundIntFactorBean;

/**
 * chenbo 2017-08-19 TASK #332232 
 * 公共信息处理界面中的系统初始化下的接口拆分到资讯组件中，以提供每百元利息生成功能
 * 
 */
public class FiHundFactorService {
	private Logger logger = LogManager.getLogger(FiHundFactorService.class);
	
	/**
	 * 从债券历史付息表中查询计息日期落在付息期间和债券信息
	 * modified by liyanjun 2016-5-18 BUG #131076 公共信息处理-初始化债券每百元利息失败，上交所行情清算失败
	 * 逻辑更改添加字段N_RATIO的查询
	 * @return 每一只债券计算利息需要用到的计算因子对象
	 * @throws Exception
	 */
	public List<FiHundIntFactorBean> queryFiHundIntFactors(String bondCode,
			Date accraulDate) throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		FiHundIntFactorBean factor = null;
		ArrayList<FiHundIntFactorBean> factors = new ArrayList<FiHundIntFactorBean>();
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT A.C_SEC_CODE,A.C_MKT_CODE,A.D_ADJ,A.C_DV_BOOL_TYPE,");
		sqlBuffer.append(" A.N_COUP_RATE, A.N_REM_COR, A.D_BEGIN, A.D_END,B.N_RATIO,");
		sqlBuffer.append(" A.N_QNHB,A.D_QNHB, ");
		sqlBuffer.append(" B.C_DV_QUT_MOD, B.N_FV_ISSUE, B.N_PRICE_ISSUE, B.N_RATE, B.C_DV_AI_MOD,");
		sqlBuffer.append(" B.C_DV_AI_EXPR, B.C_DV_PI_MOD, B.D_AI_BEGIN, B.D_AI_END, B.FREQUENCY, B.BACKWARD");// edit by liuxiang 2013/12/26 STORY #14457
		sqlBuffer.append(" FROM T_D_SV_FI_PAY A");
		sqlBuffer.append(" JOIN (SELECT C_SEC_CODE, C_DV_QUT_MOD, N_FV_ISSUE, N_PRICE_ISSUE,N_RATIO, ");
		sqlBuffer.append("      N_RATE, C_DV_AI_MOD, C_DV_AI_EXPR, C_DV_PI_MOD, D_AI_BEGIN, D_AI_END,");
		sqlBuffer.append("      C_DV_VAR_DUR AS FREQUENCY,C_CREDIT_RATING AS BACKWARD FROM T_P_SV_SEC_BASE ");
		sqlBuffer.append("      WHERE N_CHECK_STATE = 1 ");
		// edit by liyongjun 2015-12-23 提取出优先股的利息STORY18596优先股业务
		sqlBuffer.append("      AND (C_SEC_VAR_CODE LIKE 'ZQ%' OR C_SEC_VAR_CODE LIKE 'LC%' OR C_SEC_VAR_CODE LIKE 'JJ%' OR C_SEC_VAR_CODE like 'GP%') ");
		sqlBuffer.append("     ) B ON A.C_SEC_CODE = B.C_SEC_CODE");
		sqlBuffer.append(" WHERE A.N_CHECK_STATE = 1 AND A.D_BEGIN <= ? AND A.D_END >= ? AND A.C_SEC_CODE = ? ");
		sqlBuffer.append(" ORDER BY A.D_ADJ");
		String sql = sqlBuffer.toString();
		Connection conn = null;
		try {
			conn = YssDbPoolFactory.getInstance().getDbPool(SecInfoActivator.class).getConnection();
			pst = conn.prepareStatement(sql);
			pst.setDate(1, YssFun.toSqlDate(accraulDate));
			pst.setDate(2, YssFun.toSqlDate(accraulDate));
			pst.setString(3, bondCode);
			rs = pst.executeQuery();
			while (rs.next()) {
				factor = new FiHundIntFactorBean();
				this.parseHundIntFactor(rs, factor);
				factors.add(factor);
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			logger.log("初始化债券每百元：查询历史付息信息出错", e);
			throw (Exception) e;
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
			DbFun.releaseConnection(conn);
		}
		return factors;
	}
	
	/**
	 * 查询一只债券所有历史付息期间的计息因子
	 * @param bondCode
	 * @return
	 */
	public List<FiHundIntFactorBean> queryFiHundIntFactors(String bondCode) throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		FiHundIntFactorBean factor = null;
		ArrayList<FiHundIntFactorBean> factors = new ArrayList<FiHundIntFactorBean>();
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT A.C_SEC_CODE,A.C_MKT_CODE,A.D_ADJ,A.C_DV_BOOL_TYPE,");
		sqlBuffer.append(" A.N_COUP_RATE, A.N_REM_COR, A.D_BEGIN, A.D_END,");
		sqlBuffer.append(" A.N_QNHB,A.D_QNHB, ");
		sqlBuffer.append(" B.C_DV_QUT_MOD, B.N_FV_ISSUE, B.N_PRICE_ISSUE, B.N_RATE, B.C_DV_AI_MOD,");
		sqlBuffer.append(" B.C_DV_AI_EXPR, B.C_DV_PI_MOD, B.D_AI_BEGIN, B.D_AI_END, B.FREQUENCY, B.N_RATIO, B.BACKWARD");// edit by liuxiang 2013/12/26 STORY #14457
		sqlBuffer.append(" FROM T_D_SV_FI_PAY A");
		sqlBuffer.append(" JOIN (SELECT C_SEC_CODE, C_DV_QUT_MOD, N_FV_ISSUE, N_PRICE_ISSUE, ");
		sqlBuffer.append("      N_RATE, C_DV_AI_MOD, C_DV_AI_EXPR, C_DV_PI_MOD, D_AI_BEGIN, D_AI_END,");
		sqlBuffer.append("      C_DV_VAR_DUR AS FREQUENCY,N_RATIO,C_CREDIT_RATING AS BACKWARD FROM T_P_SV_SEC_BASE ");
		sqlBuffer.append("      WHERE N_CHECK_STATE = 1 ");
		sqlBuffer.append("      AND (C_SEC_VAR_CODE LIKE 'ZQ%' OR C_SEC_VAR_CODE LIKE 'LC%' OR C_SEC_VAR_CODE LIKE 'JJ%') ");
		// edit by liyongjun 2015-12-23 STORY18596优先股业务 添加优先股历史付息
		//sqlBuffer.append("and C_SEC_VAR_CODE not like '%YXG%' union all select C.C_SEC_CODE,D.C_DV_QUT_MOD,D.N_FV_ISSUE,D.N_PRICE_ISSUE,");
		//BUG #147738 【紧急】太平保险-理财品种信息每日利息生成不了 edit by dingxukun 20161222 理财品种_优先股不能算是优先股,优先股只是对于股票来说,so删除优先股like条件
		sqlBuffer.append(" union all select C.C_SEC_CODE,D.C_DV_QUT_MOD,D.N_FV_ISSUE,D.N_PRICE_ISSUE,");
		sqlBuffer.append("C.N_RATE,C.C_DV_AI_MOD,C.C_DV_AI_EXPR,C.C_DV_PI_MOD,C.D_AI_BEGIN, C.D_AI_END,C.C_DV_VAR_DUR   AS FREQUENCY,N_RATIO,");
		sqlBuffer.append("D.C_CREDIT_RATING AS BACKWARD from T_D_MP_PRE_STOCK C JOIN T_P_SV_SEC_BASE D ON  C.C_SEC_CODE = D.C_SEC_CODE ");
		sqlBuffer.append(" WHERE C.N_CHECK_STATE = 1 and D.C_SEC_VAR_CODE like '%YXG%' and c.C_DV_ACCOUNT_CODE = 'HSLX_JRFZ' ");
		sqlBuffer.append("     ) B ON A.C_SEC_CODE = B.C_SEC_CODE");
		sqlBuffer.append(" WHERE A.N_CHECK_STATE = 1  AND A.C_SEC_CODE = ? ");
		sqlBuffer.append(" ORDER BY A.D_ADJ");
		String sql = sqlBuffer.toString();
		Connection conn = null;
		try {
			conn = YssDbPoolFactory.getInstance().getDbPool(SecInfoActivator.class).getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, bondCode);
			rs = pst.executeQuery();
			while (rs.next()) {
				factor = new FiHundIntFactorBean();
				this.parseHundIntFactor(rs, factor);
				factors.add(factor);
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			logger.log("初始化债券每百元：查询一只债券所有历史付息期间的计息因子出错", e);
			throw (Exception) e;
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
			DbFun.releaseConnection(conn);
		}
		return factors;
	}
	
	/**
	 * 查询一组债券的计息因子
	 * modified by liyanjun 2016-5-18 BUG #131076 公共信息处理-初始化债券每百元利息失败，上交所行情清算失败
	 * 逻辑更改添加字段N_RATIO的查询
	 * @param bondCodes
	 * @param accrualDate
	 * @return
	 * @throws Exception
	 */
	public HashMap<String,List<FiHundIntFactorBean>> queryFiHundIntFactors(List<String> bondCodes,Date accrualDate) throws Exception {
		String sql2 = "delete from R_D_CLR_PARAM";
		String sql1 = "INSERT INTO R_D_CLR_PARAM (C_PORT_CODE) VALUES (?)";
		PreparedStatement pst1 = null;
		PreparedStatement pst2 = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		boolean bTrans = false;//edit by sunhe 20170109 公共处理初始化债券每百元利息时并没有生成每日利息
		HashMap<String,List<FiHundIntFactorBean>> fiFactorMap = new HashMap<String,List<FiHundIntFactorBean>>();
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT A.C_SEC_CODE,A.C_MKT_CODE,A.D_ADJ,A.C_DV_BOOL_TYPE,");
		// 票面利率从证券品种中获取byleeyu20140208
		sqlBuffer.append(" A.N_COUP_RATE AS N_COUP_RATE, A.N_REM_COR, A.D_BEGIN, A.D_END,B.N_RATIO,");
		sqlBuffer.append(" A.N_QNHB,A.D_QNHB, ");
		sqlBuffer.append(" B.C_DV_QUT_MOD, B.N_FV_ISSUE, B.N_PRICE_ISSUE, B.N_RATE, B.C_DV_AI_MOD,");
		sqlBuffer.append(" B.C_DV_AI_EXPR, B.C_DV_PI_MOD, B.D_AI_BEGIN, B.D_AI_END, B.FREQUENCY,B.BACKWARD");// edit by liuxiang 2013/12/26 STORY #14457
		sqlBuffer.append(" FROM T_D_SV_FI_PAY A");
		sqlBuffer.append(" JOIN (SELECT C_SEC_CODE, C_DV_QUT_MOD, N_FV_ISSUE, N_PRICE_ISSUE,N_FV_IR,N_RATIO, ");
		sqlBuffer.append("      N_RATE, C_DV_AI_MOD, C_DV_AI_EXPR, C_DV_PI_MOD, D_AI_BEGIN, D_AI_END,");
		sqlBuffer.append("      C_DV_VAR_DUR AS FREQUENCY,C_CREDIT_RATING AS BACKWARD  FROM T_P_SV_SEC_BASE ");
		sqlBuffer.append("      WHERE N_CHECK_STATE = 1 ");
		sqlBuffer.append("      AND (C_SEC_VAR_CODE LIKE 'ZQ%' OR C_SEC_VAR_CODE LIKE 'LC%' OR C_SEC_VAR_CODE LIKE 'JJ%') ");
		sqlBuffer.append(" and C_SEC_VAR_CODE not like 'GP%' union all select C.C_SEC_CODE,D.C_DV_QUT_MOD,D.N_FV_ISSUE,D.N_PRICE_ISSUE,");// edit by liyongjun 2015-12-23 STORY18596优先股业务
		sqlBuffer.append(" C.N_FV_IR,N_RATIO,C.N_RATE,C.C_DV_AI_MOD,C.C_DV_AI_EXPR,C.C_DV_PI_MOD,C.D_AI_BEGIN, C.D_AI_END,C.C_DV_VAR_DUR   AS FREQUENCY,");
		sqlBuffer.append(" D.C_CREDIT_RATING AS BACKWARD from T_D_MP_PRE_STOCK C JOIN T_P_SV_SEC_BASE D ON  C.C_SEC_CODE = D.C_SEC_CODE ");
		sqlBuffer.append("     ) B ON A.C_SEC_CODE = B.C_SEC_CODE");
		sqlBuffer.append(" JOIN R_D_CLR_PARAM C ON A.C_SEC_CODE = C.C_PORT_CODE ");
		sqlBuffer.append(" WHERE A.N_CHECK_STATE = 1 AND A.D_BEGIN <= ? AND A.D_END >= ?  ");
		sqlBuffer.append(" ORDER BY A.C_SEC_CODE, A.D_ADJ ASC");
		String sql = sqlBuffer.toString();
		Connection conn = null;
		try {
			conn = YssDbPoolFactory.getInstance().getDbPool(SecInfoActivator.class).getConnection();
			conn.setAutoCommit(bTrans);//edit by sunhe 20170109 公共处理初始化债券每百元利息时并没有生成每日利息
			pst1 = conn.prepareStatement(sql2);
			pst1.execute(); //先删除
			pst2 = conn.prepareStatement(sql1);
			for(int i =0;i<bondCodes.size();i++){
				pst2.setString(1, bondCodes.get(i));
				pst2.addBatch();
			}
			pst2.executeBatch();
			pst2.clearBatch();//addbyleeyu20151016
			
			pst = conn.prepareStatement(sql);
			pst.setDate(1, YssFun.toSqlDate(accrualDate));
			pst.setDate(2, YssFun.toSqlDate(accrualDate));
			rs = pst.executeQuery();
			while (rs.next()) {
				String secCode = rs.getString("C_SEC_CODE");
				FiHundIntFactorBean factor = new FiHundIntFactorBean();
				parseHundIntFactor(rs, factor);
				if(fiFactorMap.containsKey(secCode)){
					List<FiHundIntFactorBean> factorList = fiFactorMap.get(secCode);
					factorList.add(factor);
				}
				else{
					ArrayList<FiHundIntFactorBean> factorList = new ArrayList<FiHundIntFactorBean>();
					factorList.add(factor);
					fiFactorMap.put(secCode, factorList);
				}
			}
			conn.commit();
			bTrans = true;
			conn.setAutoCommit(bTrans);
		} catch (SQLException e) {
//			e.printStackTrace();
			logger.log("初始化债券每百元：查询一组债券的计息因子出错", e);
			throw (Exception) e;
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
			DbFun.closeStatementFinal(pst1);
			DbFun.closeStatementFinal(pst2);
			DbFun.releaseConnection(conn);
		}
		return fiFactorMap;
	}
	
	/**
	 * 根据结果集为每百元债券利息各项计算因子赋值
	 * 
	 * @param rs
	 * @param factor
	 */
	private void parseHundIntFactor(ResultSet rs, FiHundIntFactorBean factor) {
		try {
			if (null != rs && null != factor) {
				// 证券代码
				factor.setSecCode(rs.getString("C_SEC_CODE"));
				// 市场代码
				factor.setMarket(rs.getString("C_MKT_CODE"));
				// 调息日期
				factor.setAdjustDate(rs.getDate("D_ADJ"));
				// 是否启用新利率
				factor.setApplyNewRate(rs.getInt("C_DV_BOOL_TYPE") == 1 ? true
						: false);
				// 票面利率
				factor.setCouponRate(rs.getDouble("N_COUP_RATE"));
				// 剩余本金
				factor.setRemainMoney(rs.getDouble("N_REM_COR"));
				// 当前付息期间起息日
				factor.setCurPeriodBeginDate(rs.getDate("D_BEGIN"));
				// 当前付息期间截息日
				factor.setCurPeriodEndDate(rs.getDate("D_END"));
				// 报价方式
				factor.setQuoteMode(rs.getString("C_DV_QUT_MOD"));
				// 发行面值
				factor.setIssueFaceValue(rs.getDouble("N_FV_ISSUE"));
				// 发行价格
				// CL 20121129 发行价格 set错字段
				factor.setIssuePrice(rs.getDouble("N_PRICE_ISSUE"));
				// 税率
				factor.setTaxRate(rs.getDouble("N_RATE"));
				// 计息方式
				factor.setAccrualMode(rs.getString("C_DV_AI_MOD"));
				// 计息公式
				factor.setAccrualFormula(rs.getString("C_DV_AI_EXPR"));
				// 付息方式
				factor.setPayMode(rs.getString("C_DV_PI_MOD"));
				// 债券起息日
				factor.setBondBeginDate(rs.getDate("D_AI_BEGIN"));
				// 债券截息日
				factor.setBondEndDate(rs.getDate("D_AI_END"));
				// 付息频率
				factor.setPayFrequency(rs.getString("FREQUENCY"));
				// 倒置算法  edit by liuxiang 2013/12/26 STORY #14457
				factor.setBackWard(rs.getString("BACKWARD"));
				// 理财产品利率年化天数  edit by wangtangyao 2016/04/05 STORY #28884
				factor.setIntyeardays(rs.getDouble("N_RATIO"));
				// 期内还本
				factor.setIsQnhb(rs.getInt("N_QNHB"));
				factor.setQnhbDate(rs.getDate("D_QNHB"));
			}

		} catch (SQLException e) {
//			e.printStackTrace();
			logger.log("初始化债券每百元：封装债券每百元计息对象出错", e);
		}

	}

}
