package com.yss.ams.sec.information.modules.pub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;




import com.yss.ams.sec.information.support.modules.func.pojo.FiFactRateBean;
import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecBase;
//import com.yss.cache.SecBaseCache;
//import com.yss.dayf.act.consts.VocabularyConsts;
//import com.yss.dayf.actProvider.actParams.FiParaHandler;
//import com.yss.dayf.actProvider.dbUtils.DBUtils;
//import com.yss.dayf.actProvider.dbUtils.QueryRunner;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.co.BaseBean;
import com.yss.framework.api.context.AppContext;
import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.api.util.VocabularyConsts;
import com.yss.framework.api.util.YssFun;


/**
 * chenbo 2017-08-19 TASK #332232 
 * 公共信息处理界面中的系统初始化下的接口拆分到资讯组件中，以提供每百元利息生成功能
 * 
 */
/**
 * @classDesc 计算实际利率的辅助算法类，为计算债券全价，人民银行利率算法，交通银行利率算法等做公共处理
 * @version 1.0 2011-12-23
 * @author yh
 */
public class AdmFactRate extends BaseBean {

	/**
	 * 增加序列 by lihaizhi 20130620
	 */
	private static final long serialVersionUID = 8057451317119394585L;
	/**
	 * 表达式：key值为计息公式,value为计算到期本息和的公式
	 */
	public static HashMap<String, String> dueSumExpressions = new HashMap<String, String>();

	static {
		// 计息公式 到期本息和公式 表达式
		// A/A 剩余本金+票面利率 |||| remainMoney+couponRate
		// A/A-Bond 剩余本金+票面利率/付息频率 |||| remainMoney+couponRate/payFrequency
		// A/365 剩余本金+(债券截息日-起息日+1)/365*票面利率 |||| remainMoney +
		// ($DATEDIFF($DATE(curPeriodBeginDate),$DATE(bondEndDate))+1)/365*couponRate
		// A/365F 剩余本金+(债券截息日-起息日+1-N)/365*票面利率 |||| remainMoney +
		// ($DATEDIFF($DATE(curPeriodBeginDate),$DATE(bondEndDate))+1-N)/365*couponRate
		// 其中N值为：债券截息日和起息日期之间包含的2月29日的出现次数
		// A/360 剩余本金+(债券截息日-起息日+1)/360*票面利率 |||| remainMoney +
		// ($DATEDIFF($DATE(curPeriodBeginDate),$DATE(bondEndDate))+1)/360*couponRate
		// 30/360 剩余本金 +
		// (360*(债券截息日.年-计息日.年)+30*(债券截息日.月-计息日.月)+(债券截息日.日-起息日.日+1))/360*票面利率
		// remainMoney +
		// (360*($DATEYEAR(bondEndDate)-$DATEYEAR(curPeriodBeginDate))+30*($DATEMONTH(bondEndDate)-$DATEMONTH(curPeriodBeginDate))+($DATEDAY(bondEndDate)-$DATEDAY(curPeriodBeginDate)+1))/360*couponRate
		dueSumExpressions.put("FI_A_A", "remainMoney+couponRate");
		dueSumExpressions.put("FI_A_A_BOND",
				"remainMoney+couponRate/payFrequency");
		dueSumExpressions
				.put("FI_A_365",
						"remainMoney + ($DATEDIFF($DATE(curPeriodBeginDate),$DATE(bondEndDate))+1)/365*couponRate");
		dueSumExpressions
				.put("FI_A_365F",
						"remainMoney + ($DATEDIFF($DATE(curPeriodBeginDate),$DATE(bondEndDate))+1-N)/365*couponRate");
		dueSumExpressions
				.put("FI_A_360",
						"remainMoney + ($DATEDIFF($DATE(curPeriodBeginDate),$DATE(bondEndDate))+1)/360*couponRate");
		dueSumExpressions
				.put("FI_30_360",
						"remainMoney + (360*($DATEYEAR(bondEndDate)-$DATEYEAR(curPeriodBeginDate))+30*($DATEMONTH(bondEndDate)-$DATEMONTH(curPeriodBeginDate))+($DATEDAY(bondEndDate)-$DATEDAY(curPeriodBeginDate)+1))/360*couponRate");
	}

	/**
	 * 构造实际利率基本信息
	 * 
	 * @param port
	 *            组合代码
	 * @param secCode
	 *            证券代码
	 * @param investClass
	 *            投资分类
	 * @param issueMode
	 *            发行方式
	 * @param tradeAttribute
	 *            交易属性
	 * @param tradeDate
	 *            交易日期
	 * @param changeDate
	 *            变动日期
	 * @param factRateType
	 *            实际利率类型
	 * @return
	 */
	public static FiFactRateBean buildFiFactRateBaseBean(String port,
			String secCode, String investClass, String issueMode,
			String tradeAttribute, Date tradeDate, Date changeDate,
			String factRateType) {
		FiFactRateBean factRatePojo = null;
		factRatePojo = new FiFactRateBean();
		factRatePojo.setPort(port);// 投资组合
		factRatePojo.setSecCode(secCode);// 债券代码
		factRatePojo.setTradeDate(tradeDate);// 交易日期
		factRatePojo.setChangeDate(changeDate);// 变动日期
		factRatePojo.setInvestClass(investClass);// 投资分类
		factRatePojo.setIssueMode(issueMode);// 发行方式
		factRatePojo.setTradeAttribute(tradeAttribute);// 交易属性
		factRatePojo.setExpressinType(factRateType);// 人民银行算法
		return factRatePojo;
	}

	/**
	 * 根据债券代码和计息日期查询债券计算利率的相关信息
	 * 
	 * @param fiFactRateBean
	 *            实际利率基本信息
	 * @param yzjStock
	 *            溢折价库存
	 * @param cbStock
	 *            成本库存
	 * @param yslxStock
	 *            应收利息库存
	 * @return
	 * @throws Exception
	 */
//	public static FiFactRateFactorBean buildFiFactRateFactor(Connection conn,
//			FiFactRateBean fiFactRateBean, double yzj, double cbsl, double yslx)
//			throws Exception {
//		FiFactRateFactorBean factor = null;
//		PreparedStatement pst = null;
//		ResultSet rs = null;
//		String sql = "SELECT A.C_SEC_CODE," + "A.C_MKT_CODE,"
//				+ "A.N_COUP_RATE," + "A.N_REM_COR," + "A.D_BEGIN," + "A.D_END,"
//				+ "B.N_RATE," + "B.N_FV_ISSUE," + "B.C_DV_AI_MOD,"
//				+ "B.C_DV_AI_EXPR," + "B.D_AI_BEGIN," + "B.D_AI_END,"
//				+ "B.FREQUENCY," + "B.C_SEC_VAR_CODE" + " FROM T_D_SV_FI_PAY A"
//				+ " JOIN (SELECT C_SEC_CODE,C_SEC_VAR_CODE," + "N_RATE,"
//				+ "N_FV_ISSUE," + "C_DV_AI_MOD," + "C_DV_AI_EXPR,"
//				+ "D_AI_BEGIN," + "D_AI_END," + "C_DV_VAR_DUR AS FREQUENCY"
//				+ " FROM T_P_SV_SEC_BASE"
//				+ " WHERE N_CHECK_STATE = 1) B ON A.C_SEC_CODE = B.C_SEC_CODE"
//				+ " WHERE A.N_CHECK_STATE = 1" + " AND A.D_BEGIN <= ?"
//				+ " AND A.D_END >= ?" + " AND A.C_SEC_CODE = ? "
//				+ " ORDER BY A.D_END DESC ";
//		try {
//			pst = conn.prepareStatement(sql);
//			int i = 0;
//			pst.setDate(++i, YssFun.toSqlDate(fiFactRateBean.getChangeDate()));
//			pst.setDate(++i, YssFun.toSqlDate(fiFactRateBean.getChangeDate()));
//			pst.setString(++i, fiFactRateBean.getSecCode());
//			rs = pst.executeQuery();
//			HashMap<String, FiAct> fiActParams = FiParaHandler.getInstance()
//					.queryFiActSettings(conn, fiFactRateBean.getPort(),
//							fiFactRateBean.getChangeDate());
//			while (rs.next()) {
//				factor = new FiFactRateFactorBean();
//				// 证券代码
//				factor.setSecCode(rs.getString("C_SEC_CODE"));
//				// 市场代码
//				factor.setMarket(rs.getString("C_MKT_CODE"));
//				// 票面利率
//				factor.setCouponRate(rs.getDouble("N_COUP_RATE"));
//				// 剩余本金
//				factor.setRemainMoney(rs.getDouble("N_REM_COR"));
//				// 发行面值
//				factor.setIssueFaceValue(rs.getDouble("N_FV_ISSUE"));
//				// 本期起息日
//				factor.setCurPeriodBeginDate(rs.getDate("D_BEGIN"));
//				// 本期截息日
//				factor.setCurPeriodEndDate(rs.getDate("D_END"));
//				// 税率
//				factor.setTaxRate(rs.getDouble("N_RATE"));
//				// 计息方式
//				// add by zhd 2017-07-04
//				// BUG #164903 [汇添富专户升级]债券核算方案设置两条，没有对计息方式判断
////				factor.setAccrualMode(rs.getString("C_DV_AI_MOD"));
//				String aiMod = rs.getString("C_DV_AI_MOD");
//				factor.setAccrualMode(aiMod);
//				// 计息公式
//				factor.setAccrualFormula(rs.getString("C_DV_AI_EXPR"));
//				// 债券起息日
//				factor.setBondBeginDate(rs.getDate("D_AI_BEGIN"));
//				// 债券截息日
//				factor.setBondEndDate(rs.getDate("D_AI_END"));
//				// 频率
//				factor.setPayFrequency(rs.getString("FREQUENCY"));
//				// add by zhd 2017-07-04
//				// BUG #164903 [汇添富专户升级]债券核算方案设置两条，没有对计息方式判断
////				String interestMode = FiParaHandler.getInstance()
////						.getFiInterestMode(fiActParams,
////								rs.getString("C_MKT_CODE"),
////								rs.getString("C_SEC_VAR_CODE"));
//				String interestMode = FiParaHandler.getInstance()
//						.getFiInterestMode(fiActParams,
//								rs.getString("C_MKT_CODE"),
//								rs.getString("C_SEC_VAR_CODE"), aiMod,fiFactRateBean.getInvestClass());
//				// 利息方式
//				factor.setInterestMode(interestMode);
//				// 投资组合
//				factor.setPort(fiFactRateBean.getPort());
//				// 投资分类
//				factor.setInvestClass(fiFactRateBean.getInvestClass());
//				// 发行方式
//				factor.setIssueMode(fiFactRateBean.getIssueMode());
//				// 交易属性
//				factor.setTradeAttribute(fiFactRateBean.getTradeAttribute());
//				// 交易日期
//				factor.setTradeDate(fiFactRateBean.getTradeDate());
//				// 变动日期
//				factor.setChangeDate(fiFactRateBean.getChangeDate());
//				// 溢折价
//				factor.setYzj(yzj);
//				// 数量
//				factor.setCbsl(cbsl);
//				// 应收利息库存
//				factor.setYslx(yslx);
//				break;
//			}
//		} catch (SQLException e) {
//			// e.printStackTrace();
//			throw (Exception) e;
//		} finally {
//			/*
//			 * if(null != pst) pst.close(); if(null != rs) rs.close();
//			 */
//			// by chenbo 20170322 解决 result 结果集没有关闭的问题
//			DbFun.closeResultSetFinal(rs);
//			DbFun.closeStatementFinal(pst);
//		}
//		return factor;
//	}

	/**
	 * 解析数据库结果集为实际利率对象
	 * 
	 * @param rs
	 *            数据库对象
	 * @param fiFactRateBean
	 *            实际利率对象
	 * @throws Exception
	 */
	public static void parseFactRateBean(ResultSet rs,
			FiFactRateBean fiFactRateBean) throws SQLException {
		if (null == rs || null == fiFactRateBean)
			return;
		try {
			// 投资组合
			fiFactRateBean.setPort(rs.getString("C_PORT_CODE"));
			// 证券代码
			fiFactRateBean.setSecCode(rs.getString("C_SEC_CODE"));
			// 交易日期
			fiFactRateBean.setTradeDate(rs.getDate("D_TRADE"));
			// 变动日期
			fiFactRateBean.setChangeDate(rs.getDate("D_CHANGE"));
			// 投资分类
			fiFactRateBean.setInvestClass(rs.getString("C_DV_INVEST_CLS"));
			// 发行方式
			fiFactRateBean.setIssueMode(rs.getString("C_DV_ISSUE_MODE"));
			// 交易属性
			fiFactRateBean.setTradeAttribute(rs.getString("C_DTA_CODE"));
			// 算法类型
			fiFactRateBean.setExpressinType(rs.getString("C_DV_EXPR_TYPE"));
			// 利率算法
			fiFactRateBean.setAlgorithmCode(rs.getString("C_DV_AI_EXPR"));
			// 实际利率
			fiFactRateBean.setFactRate(rs.getDouble("N_FACT_RATE"));
			// 溢折价余额
			fiFactRateBean.setOvDscBalance(rs.getDouble("N_OD_BAL"));
			// 核算项目
			fiFactRateBean.setActItem(rs.getString("C_DAI_CODE"));
		} catch (SQLException e) {
			// e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 删除实际利率数据 edit BY LYJ 2016-1-18 STORY #27532
	 * 债券实际利率增加数据来源标识，若存在手工或自动改手工的记录，则以手工为准不重新计算。
	 * 
	 * @param conn
	 *            数据库连接
	 * @param factRatePojo
	 *            实际利率数据对象
	 * @throws Exception
	 */
	public static void deleteFactRate(Connection conn,
			FiFactRateBean factRatePojo) throws SQLException {
		PreparedStatement pst = null;
		String sql = "DELETE FROM T_D_SV_FI_FACTRATE WHERE C_PORT_CODE = ? AND C_SEC_CODE = ?"
				+ " AND D_TRADE = ? AND D_CHANGE = ? AND C_DV_INVEST_CLS = ? AND C_DV_ISSUE_MODE = ? AND C_DTA_CODE = ? AND C_DAI_CODE = ? "
				+ " AND C_DATA_IDF NOT IN ('H','S_H','Z_H') ";
		try {
			if (null != factRatePojo) {
				pst = conn.prepareStatement(sql);
				pst.setString(1, factRatePojo.getPort());
				pst.setString(2, factRatePojo.getSecCode());
				pst.setDate(3, YssFun.toSqlDate(factRatePojo.getTradeDate()));
				pst.setDate(4, YssFun.toSqlDate(factRatePojo.getChangeDate()));
				pst.setString(5, factRatePojo.getInvestClass());
				pst.setString(6, factRatePojo.getIssueMode());
				pst.setString(
						7,
						(null == factRatePojo.getTradeAttribute()) ? VocabularyConsts.Blank
								: factRatePojo.getTradeAttribute());
				pst.setString(8, factRatePojo.getActItem());
				pst.executeUpdate();
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw e;
		} finally {
			if (null != pst){
				DbFun.closeStatementFinal(pst);
			}
		}
	}

	/**
	 * 删除实际利率数据 edit BY LYJ 2016-1-18 STORY #27532
	 * 债券实际利率增加数据来源标识，若存在手工或自动改手工的记录，则以手工为准不重新计算。
	 * 
	 * @param conn
	 *            数据库连接
	 * @param port
	 *            组合代码
	 * @param tradeDate
	 *            交易日期
	 * @param investClass
	 *            投资分类
	 * @param actItem
	 *            核算项目
	 * @throws Exception
	 */
	public static void deleteFactRate(Connection conn, String port,
			Date tradeDate, String investClass, String actItem)
			throws SQLException {
		PreparedStatement pst = null;
		// MODIFIED BY ZXL 20150804 华泰现场 同一天发生债券分销和债券买入情况下，不能同时生产为0的实际利率，
		// 原先的逻辑会删除第一种交易类型 故这里增加 N_FACT_RATE <> 0 条件
		String sql = "DELETE FROM T_D_SV_FI_FACTRATE WHERE C_PORT_CODE = ? AND D_TRADE = ? "
				+ "AND C_DV_INVEST_CLS = ? AND C_DAI_CODE = ? AND N_FACT_RATE <> 0 AND C_DATA_IDF NOT IN ('H','S_H','Z_H') ";
		try {
			if (null != conn && null != port && null != tradeDate
					&& null != investClass) {
				pst = conn.prepareStatement(sql);
				pst.setString(1, port);
				pst.setDate(2, YssFun.toSqlDate(tradeDate));
				pst.setString(3, investClass);
				pst.setString(4, actItem);
				pst.executeUpdate();
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw e;
		} finally {
			if (null != pst){
				DbFun.closeStatementFinal(pst);
			}
		}
	}

	/**
	 * 保存实际利率数据
	 * 
	 * @param conn
	 *            数据库连接
	 * @param factRatePojo
	 *            实际利率数据对象
	 * @throws Exception
	 */
	public static void saveFactRate(Connection conn, FiFactRateBean factRatePojo)
			throws SQLException {
		// ADD BY LYJ 2016-1-18 STORY #27532
		// 债券实际利率增加数据来源标识，若存在手工或自动改手工的记录，则以手工为准不重新计算。
		// 判断是否已经存在手工数据，如果存在就不在插入。已全局搜索，此处不会对债券业务造成影响
		if (AdmFactRate.existHeadFiFactRate(conn, factRatePojo)) {
			return;
		}

		PreparedStatement pst = null;
		String sql = "INSERT INTO T_D_SV_FI_FACTRATE (C_IDEN,C_PORT_CODE,C_SEC_CODE,D_TRADE,D_CHANGE,C_DV_INVEST_CLS,"
				+ "C_DV_ISSUE_MODE,C_DTA_CODE,C_DV_EXPR_TYPE,C_DV_AI_EXPR,N_FACT_RATE,N_OD_BAL,C_DAI_CODE,C_DESC,"
				+ "N_CHECK_STATE,C_UPDATE_BY,C_UPDATE_TIME,C_CHECK_BY,C_CHECK_TIME,C_DATA_IDF )"
				+ "VALUES(SEQU_D_SV_FI_FACTRATE.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		String checkTime = YssFun.formatDatetime(new Date());
		try {
			if (null != factRatePojo) {
				pst = conn.prepareStatement(sql);
				int i = 0;
				// 投资组合
				pst.setString(++i, factRatePojo.getPort());
				// 证券代码
				pst.setString(++i, factRatePojo.getSecCode());
				// 交易日期
				pst.setDate(++i, YssFun.toSqlDate(factRatePojo.getTradeDate()));
				// 变动日期
				pst.setDate(++i, YssFun.toSqlDate(factRatePojo.getChangeDate()));
				// 投资分类
				pst.setString(++i, factRatePojo.getInvestClass());
				// 发行方式
				pst.setString(++i, factRatePojo.getIssueMode());
				// 交易属性
				pst.setString(++i, factRatePojo.getTradeAttribute());
				// 算法类型
				pst.setString(++i, factRatePojo.getExpressinType());
				// 算法代码
				pst.setString(++i, factRatePojo.getAlgorithmCode());
				// 实际利率
				pst.setDouble(++i, factRatePojo.getFactRate());
				// 溢折价余额
				pst.setDouble(++i, factRatePojo.getOvDscBalance());
				// 核算项目
				pst.setString(++i, factRatePojo.getActItem());
				// 描述
				pst.setString(++i, null);
				// 审核状态
				pst.setInt(++i, 1);
				// 修改人 //BUG #145080 【紧急】太平保险-债券实际收益率算的有问题 修改人为不应写死为sys edit by
				// dingxukun 20161116
				pst.setString(++i, AppContext.getInstance().getUserCode());
				// 修改时间
				pst.setString(++i, checkTime);
				// 审核人 //BUG #145080 【紧急】太平保险-债券实际收益率算的有问题 修改人为不应写死为sys edit by
				// dingxukun 20161116
				pst.setString(++i, AppContext.getInstance().getUserCode());
				// 审核时间
				pst.setString(++i, checkTime);
				// 数据来源 库存
				pst.setString(++i, VocabularyConsts.S);
				pst.executeUpdate();
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw e;
		} finally {
			if (null != pst){
				DbFun.closeStatementFinal(pst);
			}
		}
	}

	/**
	 * 根据主键查询一条实际利率记录
	 * 
	 * @param port
	 *            组合代码
	 * @param secCode
	 *            证券代码
	 * @param tradeDate
	 *            交易日期
	 * @param changeDate
	 *            变动日期
	 * @param investClass
	 *            投资分类
	 * @param issueMode
	 *            发行方式
	 * @param tradeAttribute
	 *            交易属性
	 * @param actItem
	 *            核算项目
	 * @return
	 * @throws SQLException
	 */
	public static FiFactRateBean queryUniqueFiFactRate(Connection conn,
			String port, String secCode, Date tradeDate, Date changeDate,
			String investClass, String issueMode, String tradeAttribute,
			String actItem) throws SQLException {
		FiFactRateBean fiFactRateBean = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		if (null == conn)
			return null;
		String sql = " SELECT c_port_code, c_sec_code, d_trade, d_change, c_dv_invest_cls, c_dv_expr_type, c_dv_ai_expr, n_fact_rate, n_od_bal, c_desc, c_update_by, c_update_time, c_check_by, c_check_time, n_check_state, c_dv_issue_mode, c_dta_code,c_dai_code FROM T_D_SV_FI_FACTRATE WHERE C_PORT_CODE = ? AND C_SEC_CODE = ? AND D_TRADE = ? "
				+ " AND D_CHANGE = ? AND C_DV_INVEST_CLS = ? AND C_DV_ISSUE_MODE = ? AND C_DTA_CODE = ? AND C_DAI_CODE = ?";
		try {
			pst = conn.prepareStatement(sql);
			int i = 0;
			pst.setString(++i, port);
			pst.setString(++i, secCode);
			pst.setDate(++i, YssFun.toSqlDate(tradeDate));
			pst.setDate(++i, YssFun.toSqlDate(changeDate));
			pst.setString(++i, investClass);
			pst.setString(++i, issueMode);
			pst.setString(++i, tradeAttribute);
			pst.setString(++i, actItem);
			rs = pst.executeQuery();
			while (rs.next()) {
				fiFactRateBean = new FiFactRateBean();
				AdmFactRate.parseFactRateBean(rs, fiFactRateBean);
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw e;
		} finally {
			//调用框架的工具类  chenbo 20170822
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
//			DBUtils.cleanUp(rs, pst); // 调用公共方法byleeyu20151015
			// if(null != pst)
			// pst.close();
			// if(null != rs)
			// rs.close();
		}

		return fiFactRateBean;
	}

	/**
	 * 查询债券实际利率
	 * 
	 * @param port
	 *            组合代码
	 * @param secCode
	 *            证券代码
	 * @param tradeDate
	 *            交易日期
	 * @param investClass
	 *            投资分类
	 * @param issueMode
	 *            发行方式
	 * @param tradeAttribute
	 *            交易属性
	 * @param actItem
	 *            核算项目
	 * @return
	 * @throws Exception
	 */
	public static FiFactRateBean queryLastFiFactRate(Connection conn,
			String port, String secCode, Date tradeDate, String investClass,
			String issueMode, String tradeAttribute, String actItem)
			throws SQLException {
		FiFactRateBean factRateInfo = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "SELECT c_port_code, c_sec_code, d_trade, d_change, c_dv_invest_cls, c_dv_expr_type, c_dv_ai_expr, n_fact_rate, n_od_bal, c_desc, c_update_by, c_update_time, c_check_by, c_check_time, n_check_state, c_dv_issue_mode, c_dta_code,c_dai_code FROM T_D_SV_FI_FACTRATE "
				+ " WHERE C_PORT_CODE = ? "
				+ " AND C_SEC_CODE = ?"
				+ " AND D_CHANGE <= ?"
				+ " AND C_DV_INVEST_CLS = ?"
				+ " AND C_DV_ISSUE_MODE = ? "
				+ " AND C_DTA_CODE = ? "
				+ " AND C_DAI_CODE = ? "
				+ " AND N_CHECK_STATE = 1"
				+ " ORDER BY D_CHANGE DESC,D_TRADE DESC";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, port);
			pst.setString(2, secCode);
			pst.setDate(3, YssFun.toSqlDate(tradeDate));
			pst.setString(4, investClass);
			pst.setString(5, issueMode);
			pst.setString(6, tradeAttribute);
			pst.setString(7, actItem);
			rs = pst.executeQuery();
			while (rs.next()) {
				factRateInfo = new FiFactRateBean();
				AdmFactRate.parseFactRateBean(rs, factRateInfo);
				break;
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw e;
		} finally {
			//调用框架的工具类  chenbo 20170822
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
//			DBUtils.cleanUp(rs, pst); // 调用公共方法byleeyu20151015
			// if(null != pst) pst.close();
			// if(null != rs) rs.close();
		}

		return factRateInfo;
	}

	/**
	 * 查询是否存在手工的债券实际利率 ADD BY LYJ 2016-1-18 STORY #27532
	 * 债券实际利率增加数据来源标识，若存在手工或自动改手工的记录，则以手工为准不重新计算。
	 * 
	 * @param fiFactRateBean
	 *            组合代码
	 * @return
	 * @throws SQLException
	 */
	public static boolean existHeadFiFactRate(Connection conn,
			FiFactRateBean fiFactRateBean) throws SQLException {
		boolean isExistHead = false;
		PreparedStatement pst = null;
		ResultSet rs = null;
		if (null == conn || null == fiFactRateBean)
			return false;
		String sql = " SELECT '1' "
				+ " FROM T_D_SV_FI_FACTRATE WHERE C_PORT_CODE = ? AND C_SEC_CODE = ? AND D_TRADE = ? "
				+ " AND D_CHANGE = ? AND C_DV_INVEST_CLS = ? AND C_DV_ISSUE_MODE = ? AND C_DTA_CODE = ? AND C_DAI_CODE = ? AND C_DATA_IDF IN ('H','S_H','Z_H') ";
		try {
			pst = conn.prepareStatement(sql);
			int i = 0;
			pst.setString(++i, fiFactRateBean.getPort());
			pst.setString(++i, fiFactRateBean.getSecCode());
			pst.setDate(++i, YssFun.toSqlDate(fiFactRateBean.getTradeDate()));
			pst.setDate(++i, YssFun.toSqlDate(fiFactRateBean.getChangeDate()));
			pst.setString(++i, fiFactRateBean.getInvestClass());
			pst.setString(++i, fiFactRateBean.getIssueMode());
			pst.setString(
					++i,
					(null == fiFactRateBean.getTradeAttribute()) ? VocabularyConsts.Blank
							: fiFactRateBean.getTradeAttribute());
			pst.setString(++i, fiFactRateBean.getActItem());
			rs = pst.executeQuery();
			if (rs.next()) {
				isExistHead = true;
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
//			DBUtils.cleanUp(rs, pst);
		}

		return isExistHead;
	}

	/**
	 * 根据付息频率转换为计算公式的数字
	 * 
	 * @param payFrequncy
	 *            付息频率
	 * @return
	 */
	public static int convertFreqToNum(String payFrequncy, Date accrualDate) {
		int num = 0;
		if (payFrequncy.equalsIgnoreCase(VocabularyConsts.FI_1M)) {
			num = 12;
		} else if (payFrequncy.equalsIgnoreCase(VocabularyConsts.FI_3M)) {
			num = 4;
		} else if (payFrequncy.equalsIgnoreCase(VocabularyConsts.FI_HALF_Y)) {
			num = 2;
		} else if (payFrequncy.equalsIgnoreCase(VocabularyConsts.FI_1Y)) {
			num = 1;
		} else if (payFrequncy.equalsIgnoreCase(VocabularyConsts.FI_ONCE)) {
			num = 1;
		} else if (payFrequncy.equalsIgnoreCase(VocabularyConsts.FI_1W)) {
			num = convertFreqWeekToNum(payFrequncy, accrualDate);
		} else if (payFrequncy.contains(VocabularyConsts.FI_CUSTOM)) {
			num = convertFreqZdyToNum(payFrequncy, accrualDate);
		}
		// 付息频率“每季末月第N个自然日”、“每年末月第N个自然日”、“每月第N个自然日”时、对应年付息频率f的为4、1、12
		else if (payFrequncy.contains(VocabularyConsts.FI_QN)) {
			num = 4;
		} else if (payFrequncy.contains(VocabularyConsts.FI_YN)) {
			num = 1;
		} else if (payFrequncy.contains(VocabularyConsts.FI_MN)) {
			num = 12;
		}
		return num;
	}

	public static int convertFreqWeekToNum(String pf, Date accrualDate) {
		double yearDay = 0;
		if (YssFun.isLeapYear(accrualDate)) {
			yearDay = 366;
		} else {
			yearDay = 365;
		}
		return (int) Math.floor(yearDay / 7);
	}

	public static int convertFreqZdyToNum(String pf, Date accrualDate) {
		double yearDay = 0;
		int PL = 0;
		double pfInt = Double.parseDouble(pf.substring(pf.indexOf("|") + 1,
				pf.lastIndexOf("|")));
		String unit = pf.substring(pf.lastIndexOf("|") + 1, pf.length());
		if (YssFun.isLeapYear(accrualDate)) {
			yearDay = 366;
		} else {
			yearDay = 365;
		}
		if (VocabularyConsts.DA.equals(unit)) {
			PL = (int) Math.floor(yearDay / pfInt);
		}
		if (VocabularyConsts.WK.equals(unit)) {
			PL = (int) Math.floor(yearDay / (pfInt * 7));
		}
		if (VocabularyConsts.MO.equals(unit)) {
			PL = (int) Math.floor(12 / pfInt);
		}
		if (VocabularyConsts.SE.equals(unit)) {
			PL = (int) Math.floor(12 / (pfInt * 3));
		}
		if (VocabularyConsts.YR.equals(unit)) {
			PL = 1;
		}
		if (PL == 0)
			PL = 1;
		return PL;
	}

	/**
	 * 获取参数值：在债券实际利率计算过程中，需要实际利率保留位数，债券全价保留位数，差值保留位数等参数的值
	 * 
	 * @param conn
	 *            数据库连接
	 * @param port
	 *            组合代码
	 * @param paramCode
	 *            参数代码
	 * @param businessDate
	 *            业务日期
	 * @return
	 * @throws Exception
	 */
	public static String getParamValue(Connection conn, String port,
			String paramCode, Date businessDate) throws Exception {
		String value = null;
		ResultSet rs = null;
		PreparedStatement pst = null;
		// BUG #145080 【紧急】太平保险-债券实际收益率算的有问题 modified by dingxukun 20161116
		// 不需要添加参数类别判断,每个参数代码在t_s_dsp_para表中都是唯一的
		String sql = "SELECT C_DSP_CODE ,C_DV_PARAMS_VALUE FROM V_P_AO_PARAMS WHERE C_PORT_CODE = ? "
				+ "AND N_CHECK_STATE = 1 AND C_DSP_CODE = ? AND D_BEGIN <= ? AND D_END >= ? "
				+ "UNION ALL "
				+ "SELECT C_DSP_CODE,C_DV_PLAT_VALUE AS C_DV_PARAMS_VALUE "
				+ "FROM T_S_DSP_PARA WHERE C_DSP_CODE = ? ";
		try {
			//chenbo 2017-08-21 TASK #332232 因为QueryRunner这个类在YSSUCO中未拆分出来，故改为使用JAVA提供的方法
//			Object[] params = new Object[] { port, paramCode, businessDate,
//					businessDate, paramCode };
//			pst = QueryRunner.preparedStatement(conn, sql, params);
			pst=conn.prepareStatement(sql);
			pst.setString(1, port);
			pst.setString(2, paramCode);
			pst.setDate(3, (java.sql.Date) businessDate);
			pst.setDate(4, (java.sql.Date) businessDate);
			pst.setString(5, paramCode);
			rs = pst.executeQuery();
			// modified by dingxukun 20161227 STORY #37429
			// 【南方基金】【专户】计算实际利率时考虑未来的还本
			// while(rs.next()){
			if (rs.next()) {
				value = rs.getString("C_DV_PARAMS_VALUE");
			}
		} catch (SQLException e) {
			// e.printStackTrace();
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
//			DBUtils.cleanUp(rs, pst);
		}
		return value;
	}

	/**
	 * 计算一个日期段之间2月29号出现的次数
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static int calcTimes2And29(Date beginDate, Date endDate) {
		int times = 0;
		if (null != beginDate && null != endDate) {
			Calendar begin = Calendar.getInstance();
			begin.setTime(beginDate);
			while (!begin.getTime().after(endDate)) {
				begin.add(Calendar.DATE, 1);
				if (begin.get(Calendar.MONTH) == 1
						&& begin.get(Calendar.DAY_OF_MONTH) == 29) {
					times++;
				}
			}
		}
		return times;
	}

	/**
	 * @param conn
	 *            数据库连接
	 * @param factRateType
	 *            实际利率算法类型
	 * @param fiFactRateBaseBean
	 *            实际利率基本对象
	 * @param cbsl
	 *            数量
	 * @param yslx
	 *            已计提利息
	 * @param yzj
	 *            待摊销溢折价
	 * @return
	 * @throws Exception
	 */
//	public static FiFactRateBean generateFiFactRate(Connection conn,
//			String factRateType, FiFactRateBean fiFactRateBaseBean,
//			double cbsl, double yslx, double yzj) throws Exception {
//		if (factRateType.equals(VocabularyConsts.FI_RMYH)) {
//			AdmRmyhFactRate rmyhOper = new AdmRmyhFactRate();
//			rmyhOper.conn = conn;
//			FiFactRateFactorBean factor = AdmFactRate.buildFiFactRateFactor(
//					conn, fiFactRateBaseBean, yzj, cbsl, yslx);
//			SvSecBaseCache secCache = CacheManager.getInstance().getCache(
//					CacheGroup.SVSECBASE);
//			SecBase secInfo = secCache.getCacheByKey(factor.getSecCode());
//			// 【南方基金】计提债券利息报非法日期格式错误 edit by chenchangyou 20161217
//			if (secInfo.getD_END() == null || "".equals(secInfo.getD_END())) {
//				secInfo.setD_END(YssFun.formatDate(
//						YssFun.addDay(YssFun.toDate(secInfo.getD_AI_END()), 1),
//						"yyyy-MM-dd"));
//			}
//			// 如果该债券的含权标志为'发行人赎回权'，估值日在含权日期之前时，计算该债券的实际利率时债券的到期日期应该是'含权日期',add
//			// by caowei,20150313,STORY #18932 债券增加含权信息需求
//			// 如果债券基本信息的含权标志不是 “不含权” 那么估值日在含权日期之前时，计算该债券的实际利率时债券的到期日期应该是'含权日期'
//			// xiaozhilong 20160620
//			// BUG #130828 【广发证券】系统债券维护了回售日期后，还是按截息日在摊销
//			// modified by huangjin 2017-5-23 STORY40414含权债实际利息计算参数需求
//			// 当债券摊销方案中‘摊销方式’设置为“行权日”：含权债的实际利率按行权日期计算
//			if (!StringUtil.IsNullOrEmpty(secInfo.getC_DV_RIGHT()) 
//					&& !StringUtil.IsNullOrEmpty(secInfo.getD_END())
//					&& YssFun.dateDiff(fiFactRateBaseBean.getTradeDate(),YssFun.toDate(secInfo.getD_END())) >= 0) {
//				BondAmortizeService bondAmortizeService = new BondAmortizeService();
//				BondAmortize bondAmortize = bondAmortizeService.getMatchedPlan(fiFactRateBaseBean.getPort(), fiFactRateBaseBean.getTradeDate(), factor.getSecCode(), conn);
//            	if(null != bondAmortize 
//            			&& (VocabularyConsts.TX_DATE_ISSUE).equals(bondAmortize.getC_AMORTIZE_MODE())){
//    				factor.setBondEndDate(YssFun.toDate(secInfo.getD_END()));
//            	}
//            }
//			double factRate = rmyhOper.calcFactRate(factor);
//			fiFactRateBaseBean.setFactRate(factRate);
//			fiFactRateBaseBean.setOvDscBalance(yzj);
//		} else if (factRateType.equals(VocabularyConsts.FI_JTYH)) {
//			AdmJtyhFactRate jtyhOper = new AdmJtyhFactRate();
//			jtyhOper.conn = conn;
//			FiFactRateFactorBean factor = AdmFactRate.buildFiFactRateFactor(
//					conn, fiFactRateBaseBean, yzj, cbsl, yslx);
//			SvSecBaseCache secCache = CacheManager.getInstance().getCache(
//					CacheGroup.SVSECBASE);
//			SecBase secInfo = secCache.getCacheByKey(factor.getSecCode());
//			// 【南方基金】计提债券利息报非法日期格式错误 edit by chenchangyou 20161217
//			if (secInfo.getD_END() == null || "".equals(secInfo.getD_END())) {
//				secInfo.setD_END(YssFun.formatDate(
//						YssFun.addDay(YssFun.toDate(secInfo.getD_AI_END()), 1),
//						"yyyy-MM-dd"));
//			}
//			// 如果该债券的含权标志为'发行人赎回权'，估值日在含权日期之前时，计算该债券的实际利率时债券的到期日期应该是'含权日期',add by caowei,20150313,STORY #18932 债券增加含权信息需求
//			if (!StringUtil.IsNullOrEmpty(secInfo.getC_DV_RIGHT())
//					&& !StringUtil.IsNullOrEmpty(secInfo.getD_END())
//					&& YssFun.dateDiff(fiFactRateBaseBean.getTradeDate(),YssFun.toDate(secInfo.getD_END())) >= 0) {
//				BondAmortizeService bondAmortizeService = new BondAmortizeService();
//				BondAmortize bondAmortize = bondAmortizeService.getMatchedPlan(fiFactRateBaseBean.getPort(),fiFactRateBaseBean.getTradeDate(), factor.getSecCode(),conn);
//				if (null != bondAmortize
//						&& (VocabularyConsts.TX_DATE_ISSUE).equals(bondAmortize.getC_AMORTIZE_MODE())) {
//					factor.setBondEndDate(YssFun.toDate(secInfo.getD_END()));
//				}
//			}
//			String param = AdmFactRate.getParamValue(conn, factor.getPort(),
//					"AO_ZQ_TYF_001_001", factor.getChangeDate());
//			double factRate = 0;
//			if (null != param && param.equals(VocabularyConsts.ZHEN)) {
//				factRate = jtyhOper.calcSbFactRate(factor);
//			} else {
//				factRate = jtyhOper.calcFactRate(factor);
//			}
//			fiFactRateBaseBean.setFactRate(factRate);
//			fiFactRateBaseBean.setOvDscBalance(yzj);
//		}
//		return fiFactRateBaseBean;
//	}

}
