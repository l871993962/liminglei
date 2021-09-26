package com.yss.ams.sec.information.modules.pub.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.cache.MktCache;
import com.yss.ams.base.information.support.util.holidays.HolidaysAide;
import com.yss.ams.sec.information.activator.SecInfoActivator;
import com.yss.ams.sec.information.support.cache.SecBaseCache;
import com.yss.ams.sec.information.support.modules.pub.pojo.FiPayFactorBean;
import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecBase;
import com.yss.ams.sec.information.support.modules.sv.fipay.pojo.FiPay;
//import com.yss.bundle.activator.UcoActivator;
//import com.yss.cache.SecBaseCache;
//import com.yss.dayf.act.consts.VocabularyConsts;
//import com.yss.dayf.actProvider.actParams.SecurityHandler;
//import com.yss.dayf.actProvider.dbUtils.DBUtils;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.co.BEN_RECORD;
import com.yss.framework.api.common.co.BEN_RECORD.DoingType;
import com.yss.framework.api.common.co.BaseBean;
import com.yss.framework.api.common.co.Mkt;
import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.exception.YssException;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.util.DateUtil;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.api.util.VocabularyConsts;
import com.yss.framework.api.util.YssFun;
import com.yss.framework.context.ContextFactory;
import com.yss.framework.db.OraDbTool;
//import com.yss.para.co.fipay.pojo.FiPay;
//import com.yss.pub.func.fi.pojo.FiPayFactorBean;
//import com.yss.pub.util.HolidaysAide;
//
//import dataservice.comm.pojo.SecBase;

/**
 * chenbo 2017-08-19 TASK #332232 
 * 公共信息处理界面中的系统初始化下的接口拆分到资讯组件中，以提供每百元利息生成功能
 * 
 */
/**
 * @classDesc 债券历史付息信息的处理类，根据债券信息产生债券历史付息数据
 * @version 1.0 2011-12-8
 * @author yh
 */
public class AdmFiHistoryPay extends BaseBean {

	/**
	 *    增加序列 by lihaizhi 20130620
	 */
	private static final long serialVersionUID = 2707041541399106808L;

	/**
	 * 生成债券历史付息数据的生成日期
	 */
	private Date markDate = null;

	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private GeneralDao dao = null;
	// 保存获取的证券代码信息byleeyu20140306 在getFiPayFactorsWhereMarkDateInPayPeriod()方法中赋值
	private List<String> lstSec = new ArrayList<String>();
	private Logger logger = LogManager.getLogger(AdmFiHistoryPay.class);

	/**
	 * 构造方法
	 * 
	 * @param generateDate
	 */
	public AdmFiHistoryPay(Date generateDate) {

		try {
			generateDate = dateFormat.parse(dateFormat.format(generateDate));
			dao = new GeneralDao(YssDbPoolFactory.getInstance().getDbPool(SecInfoActivator.class), null);

		} catch (ParseException e) {
//			e.printStackTrace();
			logger.log("初始化债券每百元：债券历史付息信息的处理类初始化出错", e);
		}
		this.markDate = generateDate;
	}

	/**
	 * 构造方法
	 * 
	 * @param generateDate
	 */
	public AdmFiHistoryPay() {

		try {
			dao = new GeneralDao(YssDbPoolFactory.getInstance().getDbPool(SecInfoActivator.class), null);
		} catch (Exception e) {
//			e.printStackTrace();
			logger.log("初始化债券每百元：债券历史付息信息的处理类初始化出错", e);
		}
	}
	
	private String[] getSec(){
		String[] arrSec = new String[lstSec.size()];
		return lstSec.toArray(arrSec);
	}

	/**
	 * 根据一组债券付息信息,产生相应的历史付息数据,保存债券历史付息信息表中
	 * 
	 * @param fiPayFactors
	 * @throws Exception
	 */
	public void generateFiHistoryPayData(List<FiPayFactorBean> fiPayFactors,BEN_RECORD ben_Record)
			throws Exception {
		if (null != fiPayFactors && 0 != fiPayFactors.size()) {
			HashMap<FiPay,List<FiPay>> fiPayHistroyMap = produceHistoryPayData(fiPayFactors,ben_Record);
			List<FiPay> fiPayHistroyList = new ArrayList<FiPay>();
			for(FiPay fiPay : fiPayHistroyMap.keySet()) {
				fiPayHistroyList.addAll(fiPayHistroyMap.get(fiPay));
			}
			// 首先删除数据
			//deleteHistoryPayData(fiPayHistroyList);
			// 删除调息日大于>库存最大起息日的数据
//			deleteHistoryPaybyData(fiPayHistroyMap);
//			saveHistoryPayData(fiPayHistroyList);
			//BUG #155809 嘉实基金-债券历史付息，初始化债券每百元利息时，系统报错，导致债券每百元历史付息信息被删除			
			Connection dbConn = null;
			try {
				//获取连接				
				dbConn = dao.loadNewConnection();
				// 事务开始
				dbConn.setAutoCommit(false);				
				// 删除调戏日大于>库存最大起息日的数据
				deleteHistoryPaybyData(fiPayHistroyMap,dbConn);
				// 首先删除数据
				//deleteHistoryPayData(fiPayHistroyList);
				saveHistoryPayData(fiPayHistroyList,dbConn);
				// 业务处理完毕，事务提交
				dbConn.commit();
				dbConn.setAutoCommit(true);		
			} catch (Exception e) {
				//e.printStackTrace();
				throw (Exception) e;
			
			} finally {
				dao.releaseConnection(dbConn);
			}		
		}

	}

	/**
	 * 根据证券信息表债券信息,产生相应的历史付息数据,保存在债券历史付息信息表中
	 * 
	 * @throws Exception
	 */
	/*public void generateFiHistoryPayData() throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<FiPayFactorBean> fiPayFactors = null;
		FiPayFactorBean fiPayFactor = null;
		String sql = " SELECT C_SEC_CODE,C_MKT_CODE,N_FV_ISSUE,N_FV_IR,C_DV_VAR_DUR AS FREQUENCY,D_AI_BEGIN,D_AI_END "
				+ " FROM T_P_SV_SEC_BASE A "
				+
				// " JOIN (SELECT C_SEC_VAR_CODE,C_DA_CODE FROM T_P_BI_SEC_VAR WHERE C_DA_CODE LIKE 'ZQ%' ) B ON A.C_SEC_VAR_CODE = B.C_SEC_VAR_CODE"+
				" JOIN (SELECT C_SEC_VAR_CODE,C_DA_CODE FROM V_S_DA_SEC_VAR WHERE C_DA_CODE LIKE 'ZQ%' ) B ON A.C_SEC_VAR_CODE = B.C_SEC_VAR_CODE"
				+ " WHERE A.N_CHECK_STATE = 1";
		Connection conn = null;
		try {
			conn = dao.loadNewConnection();
			pst = dao.openPreparedStatement(sql, conn);
			rs = pst.executeQuery();
			while (rs.next()) {
				if (null == fiPayFactors) {
					fiPayFactors = new ArrayList<FiPayFactorBean>();
				}
				fiPayFactor = new FiPayFactorBean();
				fiPayFactor.setSecCode(rs.getString("C_SEC_CODE"));
				fiPayFactor.setMarket(rs.getString("C_MKT_CODE"));
				fiPayFactor.setIssueFaceValue(rs.getDouble("N_FV_ISSUE"));
				fiPayFactor.setCouponRate(rs.getDouble("N_FV_IR"));
				fiPayFactor.setPayFrequency(rs.getString("FREQUENCY"));
				fiPayFactor.setBondBeginDate(rs.getDate("D_AI_BEGIN"));
				fiPayFactor.setBondEndDate(rs.getDate("D_AI_END"));
				fiPayFactors.add(fiPayFactor);
			}
			if (null != fiPayFactors)
				fiPayFactors.trimToSize();
			this.saveHistoryPayData(this.produceHistoryPayData(fiPayFactors));
		} catch (SQLException e) {
			e.printStackTrace();
			throw (Exception) e;
		} finally {
			dao.closeResultSetFinal(rs);
			dao.closeStatementFinal(pst);
			dao.releaseConnection(conn);
		}
	}*/

	/**
	 * 从证券信息表中查询历史付息期间不包含基准日期的债券
	 * 
	 * @throws Exception
	 */
	public ArrayList<FiPayFactorBean> getFiPayFactorsWherePeroidNotHasMarkDate()
			throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<FiPayFactorBean> fiPayFactors = null;
		FiPayFactorBean fiPayFactor = null;
		String sql = " SELECT C_SEC_CODE,C_MKT_CODE,N_FV_ISSUE,N_FV_IR,C_DV_VAR_DUR AS FREQUENCY,D_AI_BEGIN,D_AI_END "
				+ " FROM T_P_SV_SEC_BASE A "
				+
				// " JOIN (SELECT C_SEC_VAR_CODE,C_DA_CODE FROM T_P_BI_SEC_VAR WHERE C_DA_CODE LIKE 'ZQ%' ) B ON A.C_SEC_VAR_CODE = B.C_SEC_VAR_CODE"
				// +
				" JOIN (SELECT C_SEC_VAR_CODE,C_DA_CODE FROM V_S_DA_SEC_VAR WHERE C_DA_CODE LIKE 'ZQ%' ) B ON A.C_SEC_VAR_CODE = B.C_SEC_VAR_CODE"
				+ " WHERE A.N_CHECK_STATE = 1 "
				+ " AND NOT EXISTS (SELECT C_SEC_CODE, D_BEGIN, D_END FROM  T_D_SV_FI_PAY B "
				+ " WHERE B.N_CHECK_STATE = 1 AND B.D_BEGIN <= ? AND B.D_END >= ? AND A.C_SEC_CODE = B.C_SEC_CODE)";
		Connection conn = null;
		try {
			conn = dao.loadNewConnection();
			pst = dao.openPreparedStatement(sql, conn);
			pst.setDate(1, YssFun.toSqlDate(markDate));
			pst.setDate(2, YssFun.toSqlDate(markDate));
			rs = pst.executeQuery();
			while (rs.next()) {
				if (null == fiPayFactors) {
					fiPayFactors = new ArrayList<FiPayFactorBean>();
				}
				fiPayFactor = new FiPayFactorBean();
				fiPayFactor.setSecCode(rs.getString("C_SEC_CODE"));
				fiPayFactor.setMarket(rs.getString("C_MKT_CODE"));
				fiPayFactor.setIssueFaceValue(rs.getDouble("N_FV_ISSUE"));
				fiPayFactor.setCouponRate(rs.getDouble("N_FV_IR"));
				fiPayFactor.setPayFrequency(rs.getString("FREQUENCY"));
				fiPayFactor.setBondBeginDate(rs.getDate("D_AI_BEGIN"));
				fiPayFactor.setBondEndDate(rs.getDate("D_AI_END"));
				fiPayFactors.add(fiPayFactor);
			}
			if (null != fiPayFactors)
				fiPayFactors.trimToSize();
		} catch (SQLException e) {
//			e.printStackTrace();
			logger.log("初始化债券每百元：从证券信息表中查询历史付息期间不包含基准日期的债券出错", e);
			throw (Exception) e;
		} finally {
			dao.closeResultSetFinal(rs);
			dao.closeStatementFinal(pst);
			dao.releaseConnection(conn);
		}
		return fiPayFactors;
	}

	/**
	 * 查询债券中计息起始日期和计息截息日期包含基准日期的债券，并构造为债券付息因子
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<FiPayFactorBean> getFiPayFactorsWhereMarkDateInPayPeriod()
			throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<FiPayFactorBean> fiPayFactors = null;
		FiPayFactorBean fiPayFactor = null;
		String sql = " SELECT C_SEC_CODE  "
				+ " FROM T_P_SV_SEC_BASE A "
				+ " JOIN (SELECT C_SEC_VAR_CODE,C_DA_CODE FROM V_S_DA_SEC_VAR WHERE C_DA_CODE LIKE 'ZQ%' ) B ON A.C_SEC_VAR_CODE = B.C_SEC_VAR_CODE"
				+ " WHERE A.N_CHECK_STATE = 1 AND A.D_AI_BEGIN <= ? AND A.D_AI_END >= ? ";
		// CL 20130308 增加理财品种每百元初始化,同时增加证券品种属性便于后面的计算
		sql += " UNION ALL SELECT C_SEC_CODE  "
			+ " FROM T_P_SV_SEC_BASE A "
			+ " JOIN (SELECT C_SEC_VAR_CODE,C_DA_CODE FROM V_S_DA_SEC_VAR WHERE C_DA_CODE LIKE 'LC%' OR C_DA_CODE LIKE 'JJ%') B ON A.C_SEC_VAR_CODE = B.C_SEC_VAR_CODE"
			+ " WHERE A.N_CHECK_STATE = 1 AND A.C_DV_PI_MOD IN('FIXED','FLOAT') AND A.D_AI_BEGIN <= ? AND A.D_AI_END >= ? ";
		Connection conn = null;
		try {
			conn = dao.loadNewConnection();
			pst = dao.openPreparedStatement(sql, conn);
			pst.setDate(1, YssFun.toSqlDate(markDate));
			pst.setDate(2, YssFun.toSqlDate(markDate));
			pst.setDate(3, YssFun.toSqlDate(markDate));
			pst.setDate(4, YssFun.toSqlDate(markDate));
			rs = pst.executeQuery();
			while (rs.next()) {
				if (null == fiPayFactors) {
					fiPayFactors = new ArrayList<FiPayFactorBean>();
				}
				SecBaseCache secCache = CacheManager.getInstance().getCache(CacheGroup.SECBASE);
				SecBase secInfo = secCache.getCacheByKey(rs.getString("C_SEC_CODE"));
				fiPayFactor = buildFiPayFactorBeanBySec(secInfo);
				if(null != fiPayFactor){
					fiPayFactors.add(fiPayFactor);
					// BUG #134663 findbugs代码检查和PMD数据库连接检查问题修复 添加空值判断 edit by chenyoulong 20160719
					lstSec.add(fiPayFactor.getSecCode());
				}
//				lstSec.add(fiPayFactor.getSecCode());
			}
			if (null != fiPayFactors)
				fiPayFactors.trimToSize();
		} catch (SQLException e) {
//			e.printStackTrace();
			logger.log("初始化债券每百元：查询债券中计息起始日期和计息截息日期包含基准日期的债券，并构造为债券付息因子出错", e);
			throw (Exception) e;
		} finally {
			dao.closeResultSetFinal(rs);
			dao.closeStatementFinal(pst);
			dao.releaseConnection(conn);
		}
		return fiPayFactors;
	}
	
	/**
	 * 查询债券中计息起始日期和计息截息日期包含基准日期的债券，并构造为债券付息因子
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<FiPayFactorBean> getFiPayFactorsWhereEqualsPayInterestDate()
			throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<FiPayFactorBean> fiPayFactors = null;
		FiPayFactorBean fiPayFactor = null;
		String sql = "SELECT C_SEC_CODE FROM T_D_SV_FI_PAY  WHERE D_END = ? AND N_CHECK_STATE = 1 ";
		Connection conn = null;
		try {
			conn = dao.loadNewConnection();
			pst = dao.openPreparedStatement(sql, conn);
			pst.setDate(1, YssFun.toSqlDate(markDate));
			rs = pst.executeQuery();
			while (rs.next()) {
				if (null == fiPayFactors) {
					fiPayFactors = new ArrayList<FiPayFactorBean>();
				}
				SecBaseCache secCache = CacheManager.getInstance().getCache(CacheGroup.SECBASE);
				SecBase secInfo = secCache.getCacheByKey(rs.getString("C_SEC_CODE"));
				//// 没有基本信息的数据过滤掉 BY Jinghehe 2015-04-25
				if(null == secInfo)
				{
					continue;
				}
				fiPayFactor = buildFiPayFactorBeanBySec(secInfo);
				if(null != fiPayFactor){
					fiPayFactors.add(fiPayFactor);
					// BUG #134663 findbugs代码检查和PMD数据库连接检查问题修复 添加空值判断 edit by chenyoulong 20160719
					lstSec.add(fiPayFactor.getSecCode());
				}
//				lstSec.add(fiPayFactor.getSecCode());
			}
			if (null != fiPayFactors)
				fiPayFactors.trimToSize();
		} catch (SQLException e) {
//			e.printStackTrace();
			logger.log("初始化债券每百元：查询债券中计息起始日期和计息截息日期包含基准日期的债券，并构造为债券付息因子", e);
			throw (Exception) e;
		} finally {
			dao.closeResultSetFinal(rs);
			dao.closeStatementFinal(pst);
			dao.releaseConnection(conn);
		}
		return fiPayFactors;
	}
	
	private FiPayFactorBean buildFiPayFactorBeanBySec(SecBase secBase) throws YssException{
		if(null == secBase){
			return null;
		}
		FiPayFactorBean fiPayFactor = new FiPayFactorBean();
		fiPayFactor.setSecCode(secBase.getC_SEC_CODE());
		fiPayFactor.setMarket(secBase.getC_MKT_CODE());
		fiPayFactor.setIssueFaceValue(Double.parseDouble(secBase.getN_FV_ISSUE()));
		fiPayFactor.setCouponRate(secBase.getN_FV_IR().doubleValue());
		fiPayFactor.setPayFrequency(secBase.getC_DV_VAR_DUR());
		fiPayFactor.setBondBeginDate(YssFun.toDate(secBase.getD_AI_BEGIN()));
		fiPayFactor.setBondEndDate(YssFun.toDate(secBase.getD_AI_END()));
		fiPayFactor.setLicaiBeginDate(YssFun.toDate((secBase.getD_SQAI_BEGIN()==null || secBase.getD_SQAI_BEGIN().length()<8)?"9998-12-31":secBase.getD_SQAI_BEGIN()));
		// add by yh 2015.05.18 增加属性C_DV_ASSURE
		fiPayFactor.setC_DV_ASSURE(secBase.getC_DV_ASSURE());
		return fiPayFactor;
		
	}

	/**
	 * 根据一组债券付息信息,产生对应的历史付息数据
	 * modifiy by liyanjun 2016-3-4 BUG #127263 债券历史付息信息更新有误
	 * modifiy by guohui 2016-11-1 STORY35631【紧急】太平保险-部分债券历史付息生成错误导致债券计息溢折价错误
	 * 系统自动产生的债券历史付息数据来源为“库存”，在重新生成时只更新来源为“库存”的数据，其它自动或手工数据不更新
	 * @param fiPayFactors
	 * @return
	 * @throws Exception
	 */
	private HashMap<FiPay,List<FiPay>> produceHistoryPayData(List<FiPayFactorBean> fiPayFactors,BEN_RECORD ben_Record)
			throws Exception {
		HashMap<FiPay,List<FiPay>> fiHistoryPayMap = new HashMap<FiPay,List<FiPay>>();
		ArrayList<FiPay> fiHistoryPayData = null;
		FiPay fiHistoryPayPojo = null;
		SecBaseCache secCache = CacheManager.getInstance().getCache(CacheGroup.SECBASE);
		//外部已经判断fiPayFactors链表size>0了
		SecBase sec = secCache.getCacheByKey(fiPayFactors.get(0).getSecCode());
		if("NETWORTH".equals(sec.getC_DV_PI_MOD())){
			return fiHistoryPayMap;
		}
//		FiPay lastHistoryPayPojo = null;
//		List<String> historyPayNotS = null;
		Connection conn = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			conn = dao.loadNewConnection();
			if (null == fiPayFactors || fiPayFactors.isEmpty()) {
				return null;
			}
			double remainMoney = 0;// 剩余本金
			double couponRate = 0;// 票面利率
			String secCode = null;
			Date curBeginRateDay = null;// 债券当前第一个计息期间的起息日期
			List<String> secCodeList = new ArrayList<String>();
			for(FiPayFactorBean payFactor : fiPayFactors){
				secCodeList.add(payFactor.getSecCode());
			}
			// 查询出所有数据来源不是“库存”的债券历史付息的调息日期，HashMap<证券代码,List<调息日期>>， 
//			HashMap<String,List<String>> allHistoryPayNotS = AdmFiHistoryPay.getAllHistoryPayNotS(conn, secCodeList);
//			HashMap<String,FiPay> lastPayMap = AdmFiHistoryPay.getLastHistoryPay(conn, secCodeList);
//			FiPay lastPayMap = this.getLastEditHistoryPay(conn, secCode);
			//STORY #37869 【南方基金】生成债券每百元信息时不覆盖读入的还本信息并生成还本区间段的每百元信息
			HashMap<String,List<FiPay>> fiPays = AdmFiHistoryPay.getChangedHistoryPay(conn, secCodeList);
			for (FiPayFactorBean fiPayFactor : fiPayFactors) {
				fiHistoryPayData = new ArrayList<FiPay>();
				Date handEnd = null;    //非库存的最大截至日期
				secCode = fiPayFactor.getSecCode();
				//返回值 中hashMap里的Key值  为后续删除时传D2值，避免再次筛选D2值
				FiPay fiPay = new FiPay();
				fiPay.setC_SEC_CODE(secCode);
				//将债券基本信息的起息日保存以下--后续要删除起息日之前的数据
				fiPay.setD_BEGIN(dateFormat.format(fiPayFactor.getBondBeginDate()));
				FiPay lastPayMap = AdmFiHistoryPay.getLastEditHistoryPay(conn, secCode);
				//BUG #155913 有债券历史付息的情况下，修改债券票面利率，重新生成每日利息报错，客户端崩溃退出  modify by zhangquan 2017-05-05
				if(lastPayMap == null){
					lastPayMap = AdmFiHistoryPay.getLastHistoryPay(conn, secCode);
				}
				List<FiPay> changedPays = fiPays.get(fiPayFactor.getSecCode());
				Date lastEndDate = null;
				double pmll = 0;
				double sybj = 0;
				//STORY #37869 【南方基金】生成债券每百元信息时不覆盖读入的还本信息并生成还本区间段的每百元信息 
				//修改自动生成债券历史付息期间逻辑，扩展对 “最晚的期间的截止日D1”的判定为：（最晚一次手工/库存转手工的期间） 或 （剩余本金或利率有变动）
				if(null != changedPays) {
					pmll = changedPays.get(changedPays.size()-1).getN_COUP_RATE().doubleValue();
					sybj = changedPays.get(changedPays.size()-1).getN_REM_COR().doubleValue();
					for(int j = changedPays.size()-1; j>=0; j--) {
						FiPay changePay = changedPays.get(j);
						if(0 != j) {
							//b.如非第一个历史付息期间，与上一期间比较  剩余本金或利率有变动。
							if(pmll != changePay.getN_COUP_RATE().doubleValue() || sybj != changePay.getN_REM_COR().doubleValue()) {
								lastEndDate = YssFun.toDate(changePay.getD_END());
								pmll = changePay.getN_COUP_RATE().doubleValue();
								sybj = changePay.getN_REM_COR().doubleValue();
								break;
							}
						} else {
							//a.如为第一个历史付息期间，与债券基本信息比较  剩余本金或利率有变动；
							if(changePay.getN_COUP_RATE().doubleValue() != fiPayFactor.getCouponRate() || changePay.getN_REM_COR().doubleValue() != fiPayFactor.getIssueFaceValue()) {
								lastEndDate = YssFun.toDate(changePay.getD_END());
								pmll = changePay.getN_COUP_RATE().doubleValue();
								sybj = changePay.getN_REM_COR().doubleValue();
								break;
							}
						}
					}
				}

				//BUG #151947 太平保险-理财产品信息初始化历史付息错误 edit by dingxukun 20170216  -----begin
				//如果首期起息日<起息日期，则生成一条本期起息日为首期起息日，本期截息日为起息日期-1日的债券历史付息数据
				if (YssFun.dateDiff(fiPayFactor.getLicaiBeginDate(), fiPayFactor.getBondBeginDate())>0){
					
					fiHistoryPayPojo = new FiPay();
					// 证券代码
					fiHistoryPayPojo.setC_SEC_CODE(fiPayFactor
							.getSecCode());
					// 市场代码
					fiHistoryPayPojo.setC_MKT_CODE(fiPayFactor
							.getMarket());
					// 票面利率
					fiHistoryPayPojo.setN_COUP_RATE(BigDecimal.valueOf(fiPayFactor.getCouponRate()));
					// fiHistoryPayPojo.setN_COUP_RATE(String
					// .valueOf(couponRate));
					// 剩余本金
					fiHistoryPayPojo.setN_REM_COR(BigDecimal.valueOf(fiPayFactor.getIssueFaceValue()));
					// fiHistoryPayPojo.setN_REM_COR(String
					// .valueOf(remainMoney));
					// 是否启用新利率:默认为否
					fiHistoryPayPojo.setC_DV_BOOL_TYPE(String
							.valueOf(0));
					// 本期起息日
					fiHistoryPayPojo.setD_BEGIN(dateFormat.format(fiPayFactor.getLicaiBeginDate()));
					//本期截息日
					fiHistoryPayPojo.setD_END(dateFormat.format(YssFun.addDay(fiPayFactor.getBondBeginDate(), -1)));
					
					// 调息日:默认等于本期起息日
					fiHistoryPayPojo.setD_ADJ(dateFormat.format(fiPayFactor.getLicaiBeginDate()));
					//数据来源
					fiHistoryPayPojo.setC_DATA_IDF("S");
					fiHistoryPayData.add(fiHistoryPayPojo);
				}
				//BUG #151947 太平保险-理财产品信息初始化历史付息错误 edit by dingxukun 20170216  -----end

				// 首先从债券历史付息数据表中获取该只债券最近的历史付息数据
				/*lastHistoryPayPojo = AdmFiHistoryPay.getLastHistoryPay(
						conn, fiPayFactor.getSecCode());*/
//				if(null != lastPayMap){
//					lastHistoryPayPojo = lastPayMap.get(fiPayFactor.getSecCode());
//				}
				
//				if(null != allHistoryPayNotS){
//					historyPayNotS = allHistoryPayNotS.get(fiPayFactor.getSecCode());
//				}
				// 如果没有历史付息数据,剩余本金 = 债券信息发行面值 ,票面利率 =
				// 债券信息票面利率,当前第一个计息期间的起息日期 = 债券起息日
				curBeginRateDay = fiPayFactor.getBondBeginDate();  //债券起息日
				if (null == lastPayMap) {
//					remainMoney = fiPayFactor.getIssueFaceValue();
//					couponRate = fiPayFactor.getCouponRate();
////					curBeginRateDay = fiPayFactor.getBondBeginDate();
//				}
//				if (null == lastPayMap && null == lastEndDate) {
					remainMoney = fiPayFactor.getIssueFaceValue();
					couponRate = fiPayFactor.getCouponRate();
				}
				// 如果有最近历史付息数据,剩余本金 = 上一次付息剩余本金 ,票面利率 =
				// 上一次付息票面利率,当前第一个计息期间的起息日期 = 上一次历史付息截息日+1
				else {
//					remainMoney = lastPayMap
//							.getN_REM_COR().doubleValue();
//					couponRate = lastPayMap
//							.getN_COUP_RATE().doubleValue();
					//取（最晚一次手工/库存转手工的期间） 或 （剩余本金或利率有变动）最晚的那条剩余本金和票面利率
					if(null == lastEndDate || (null!=lastEndDate && null != lastPayMap&& lastEndDate.compareTo(YssFun.toDate(lastPayMap.getD_END())) <= 0)) {
						remainMoney = lastPayMap.getN_REM_COR().doubleValue();
						couponRate = lastPayMap.getN_COUP_RATE().doubleValue();
					} else {
						remainMoney = sybj;
						couponRate = pmll;
					}
					
					// 如果上一次截息日期等于债券截息日期，中断循环
					if (YssFun.toDate(lastPayMap.getD_END())
							.compareTo(fiPayFactor.getBondEndDate()) != 0) {
						//curBeginRateDay = YssFun.addDay(YssFun.toDate(lastPayMap.getD_END()), 1);
						//取（最晚一次手工/库存转手工的期间） 或 （剩余本金或利率有变动）最晚的截息日D1
						if(null == lastEndDate || (null!=lastEndDate && null != lastPayMap&& lastEndDate.compareTo(YssFun.toDate(lastPayMap.getD_END())) <= 0)) {
							handEnd = sdf.parse(lastPayMap.getD_END());   //非库存数据的最大截息日
						} else {
							handEnd = lastEndDate;   //非库存数据的最大截息日
						}
						
					} else {
						break;
					}
					// 如果上一次截息日期等于债券截息日期，中断循环
					// BUG #143147 【紧急】太平保险-一年多次付息债券溢折价摊销错误  modified by dingxukun 20161022
					// 修改判断条件,由原来的!=0改为<0,债券的历史付息信息中的截息日应小于债券基本信息中的截息日
					// 
					//BUG #150966 嘉实基金QD-债券历史付息自动转手工后，生成重复付息信息 
					//注释掉判断条件,所有手工日期均取非库存的最大截息日
//					if (YssFun.toDate(lastPayMap.getD_END())
//							.compareTo(fiPayFactor.getBondEndDate()) < 0) {
//						curBeginRateDay = YssFun.addDay(YssFun
//								.toDate(lastHistoryPayPojo.getD_END()), 1);
//					}
				}
				
				Date nextBegin = null;  //下一个起息日
				int year;     
				int month;
				int day;
				int realDay;    //当年当月最后一天
				
				int m = 0;
				int n = 1;
				int i = 0;
				String a = fiPayFactor.getPayFrequency();
				if(a.equals("FI_1M")){  //每月
					n = m = 1;
				}else if(a.equals("FI_3M")){  //每季度
					n = m = 3;
				}else if(a.equals("FI_HALF_Y")){  //每半年
					n = m = 6;
				}else if(a.equals("FI_1Y")){  //每年
					n = m = 12;
				}
				Date firstDate  = curBeginRateDay; //第一次起息日
				//如果债券的起息日有变动（可能晚于上面计算出的重新生成的初始起息日）则从债券起息日开始重新生成
				if(null != handEnd && firstDate.compareTo(YssFun.addDay(handEnd,1)) >= 0) {
					remainMoney = fiPayFactor.getIssueFaceValue();
					couponRate = fiPayFactor.getCouponRate();
				}

				// 根据债券付息频率，产生付息期间的数据,当付息期间的起息日>产生日期时,不再产生付息期间的数据
				boolean isDo = true;
				while (isDo) {
					// 如果当前计息期间起息日>产生日期,之后的期间数据不再产生
					/**Start 20150213 added by liubo.STORY #21257 债券初始化需求生产所有历史付息期间
					 * 去掉这个if节点，使能够生成当前执行日期所属计息期间之后的付息期间数据*/
//					if (curBeginRateDay.after(markDate)) 
//					{
//						isDo = false;
//						break;
//					} 
					//modified by xzl 2015.08.24 因为产生所有的债券历史付息数据，如果截息日期为9998-12-31或类似结息日为999的债券，循环次数过多，无法终止。
					// 结息日大于3000-1-1的都不做处理
					// BUG #117967 （紧急）海通版本20.4.4.1031，初始化债券每百元利息时，页面停滞不动。
					//BUG #119756 【紧急】汇添富基金，新增理财产品信息，执行公共信息处理-系统初始化操作，无法生成理财产品的历史付息信息。 modify by yewenke 2015-11-17 理财产品如果是一次性付息，就循环一次而已。
					if(fiPayFactor.getBondEndDate().after(YssFun.toDate("3000-1-1")) && !VocabularyConsts.FI_ONCE.equalsIgnoreCase(fiPayFactor.getPayFrequency())){
						break;
					}
//					//add by yh 2015.05.19 因为产生所有的债券历史付息数据，如果截息日期为9998-12-31的债券，循环次数过多，无法终止。
//					if(YssFun.dateDiff(fiPayFactor.getBondEndDate(),YssFun.toDate("9998-12-31"))==0){
//						break;
//					}
					/**End 20150213 added by liubo.STORY #21257 债券初始化需求生产所有历史付息期间*/
					fiHistoryPayPojo = new FiPay();
					// 证券代码
					fiHistoryPayPojo.setC_SEC_CODE(fiPayFactor
							.getSecCode());
					// 市场代码
					fiHistoryPayPojo.setC_MKT_CODE(fiPayFactor
							.getMarket());
					// 票面利率
					fiHistoryPayPojo.setN_COUP_RATE(BigDecimal.valueOf(couponRate));
					// fiHistoryPayPojo.setN_COUP_RATE(String
					// .valueOf(couponRate));
					// 剩余本金
					fiHistoryPayPojo.setN_REM_COR(BigDecimal.valueOf(remainMoney));
					// fiHistoryPayPojo.setN_REM_COR(String
					// .valueOf(remainMoney));
					// 是否启用新利率:默认为否
					fiHistoryPayPojo.setC_DV_BOOL_TYPE(String
							.valueOf(0));
					// 本期起息日
					fiHistoryPayPojo.setD_BEGIN(dateFormat
							.format(curBeginRateDay));
					// 下期起息日nextBegin
					int x = m / 12;   //根据增加的月份得到具体增加了多少年，多少月
					int y = m % 12;

					year = YssFun.getYear(firstDate) + x;       //获取年份
			        month = YssFun.getMonth(firstDate) + y;     //获取月份
			        day = YssFun.getDay(firstDate);             //获取日
			        realDay = YssFun.endOfMonth(year, month);   //当月最后一天
			        //若获取到的日期大于当月最大天数，则取当月最后一天，否则去获取的日期
			        if (day >= realDay){
			        	nextBegin = YssFun.toDate(year, month, realDay);
			        }else{
			        	nextBegin = YssFun.toDate(year, month, day);
			        }
			        
			        // 本期截息日   = 下次起息日的前一天
					fiHistoryPayPojo.setD_END(dateFormat.format(YssFun.addDay(nextBegin, -1)));
					
					// 调息日:默认等于起息日
					fiHistoryPayPojo.setD_ADJ(fiHistoryPayPojo
							.getD_BEGIN());
					// edit tdf BUG #115588 [紧急]浮动利率债券没有清算出财汇资讯数据 20150715
					//数据来源
					fiHistoryPayPojo.setC_DATA_IDF("S");
					// 本期截息日
					// 如果付息频率为一个月,截息日 = 起息日+一个月
//					if (VocabularyConsts.FI_1M.equalsIgnoreCase(fiPayFactor.getPayFrequency())) {
//						fiHistoryPayPojo.setD_END(dateFormat
//								.format(YssFun.addDay(YssFun.addMonth(
//										curBeginRateDay, 1), -1)));
//					}
					// 如果付息频率为三个月,截息日 = 起息日+三个月
//					if (VocabularyConsts.FI_3M.equalsIgnoreCase(fiPayFactor.getPayFrequency())) {
//						fiHistoryPayPojo.setD_END(dateFormat
//								.format(YssFun.addDay(YssFun.addMonth(
//										curBeginRateDay, 3), -1)));
//					}
					// 如果付息频率为半年,截息日 = 起息日+六个月
//					if (VocabularyConsts.FI_HALF_Y.equalsIgnoreCase(fiPayFactor.getPayFrequency())) {
//						fiHistoryPayPojo.setD_END(dateFormat
//								.format(YssFun.addDay(YssFun.addMonth(
//										curBeginRateDay, 6), -1)));
//					}
					// 如果付息频率为一年,截息日 = 起息日+一年
//					if (VocabularyConsts.FI_1Y.equalsIgnoreCase(fiPayFactor.getPayFrequency())) {
						//一个债券他从2-29日起息日，你就将所有债券全部改为2月29起息，影响到别家客户的债券计息。这里退回处理，只能帮你特殊处理，具体你自己改。modify by yewenke 2016-07-19
						//BUG #123627 128024 CY这个债券润年2012-2-29日起息日，系统历史付息日2016-2-29日没有当作本期起息日 guoguangyi 2015-12-9
//						if(fiPayFactor.getSecCode().indexOf("128024 CY") ==0){
//							Date rq = YssFun.addYear(curBeginRateDay,1);
//							String str = YssFun.formatDate(curBeginRateDay, "MMdd");
//							if (str.equals("0228") && YssFun.isLeapYear(rq)){
//								fiHistoryPayPojo.setD_END(dateFormat.format(rq));
//							}else{
//								fiHistoryPayPojo.setD_END(dateFormat.format(YssFun.addDay(rq,-1)));
//							}
//						}else{
						// BUG #136113 【汇添富】债券每日利息问题2
						// BUG #123627逻辑会导致其他所有债券历史付息日期推算错误，原有逻辑是错误且没有确认的，还原以上逻辑
						// xiaozhilong 20160808
//						fiHistoryPayPojo.setD_END(dateFormat.format(YssFun.addDay(YssFun.addYear(curBeginRateDay, 1), -1)));	
//						}
//					}
					// 如果付息频率为一次性,截息日 = 截息日
					//luxiaoying:BUG #154442 太平保险-理财产品非标资产初始化报错,因为非标资产没有封装付息频率的条件，所以判断条件加上付息频率为空的情况
					if (VocabularyConsts.FI_ONCE.equalsIgnoreCase(fiPayFactor.getPayFrequency())||fiPayFactor.getPayFrequency().equals(" ")) {
						fiHistoryPayPojo.setD_END(dateFormat
								.format(fiPayFactor.getBondEndDate()));
					}
					
					//add by yewenke STORY #28953 【招商证券】债券基本信息和理财产品信息界面的付息频率设置需要增加按周及自定义频率的选项 2016-09-09
					//如果付息频率为一周,截息日 = 起息日+一周
					if(VocabularyConsts.FI_1W.equalsIgnoreCase(fiPayFactor.getPayFrequency())){
						fiHistoryPayPojo.setD_END(dateFormat.format(YssFun.addDay(YssFun.addDay(curBeginRateDay, 7),-1)));
					}
					//add by yewenke STORY #28953 【招商证券】债券基本信息和理财产品信息界面的付息频率设置需要增加按周及自定义频率的选项 2016-09-09
					//如果付息频率为自定义,截息日 = 起息日+自定义的日期
					if(fiPayFactor.getPayFrequency().contains(VocabularyConsts.FI_CUSTOM)){
						fiHistoryPayPojo.setD_END(this.getZDYPLDate(fiPayFactor.getPayFrequency(),curBeginRateDay));
					}
					//STORY37118【紧急】【招商基金】理财信托产品需支持“按季末月第N个自然日”付息
					// 如果付息频率为每季末月第N个自然日,截息日 = 起息日+3个月+N个自然日
					if (fiPayFactor.getPayFrequency().contains(VocabularyConsts.FI_QN)) {
						fiHistoryPayPojo.setD_END(dateFormat.format(getFxDate(fiPayFactor.getPayFrequency(),curBeginRateDay)));
					}
					// 如果付息频率为每年末月第N个自然日,截息日 = 起息日所在年份的12月的第N个自然日
					if (fiPayFactor.getPayFrequency().contains(VocabularyConsts.FI_YN)) {
						fiHistoryPayPojo.setD_END(dateFormat.format(getFxDate(fiPayFactor.getPayFrequency(),curBeginRateDay)));
					}
					// 如果付息频率为每月第N个自然日,截息日 = (起息日+1个月）所在月份的第N个自然日
					if (fiPayFactor.getPayFrequency().contains(VocabularyConsts.FI_MN)) {
						fiHistoryPayPojo.setD_END(dateFormat.format(getFxDate(fiPayFactor.getPayFrequency(),curBeginRateDay)));
					}
					
					// BUG #109562  这里添加判断，将空字符串过滤掉 by yuyongjiang20150319
					if(fiHistoryPayPojo.getD_END().trim().length()==0){
						break;
					}
					
					// 这里添加判断，将非法数据过滤掉byleeyu20131126
					if(!YssFun.isDate(fiHistoryPayPojo.getD_END())){
						isDo = false;
						String detail = fiHistoryPayPojo.getC_SEC_CODE()
								+ "----" + fiHistoryPayPojo.getC_MKT_CODE()
								+ " 参数有误!";
						ben_Record.appendDetailMes_Red(detail);
						ben_Record.setC_Doing_Type(DoingType.Fail);
						break;
					}
					// 如果计算出的截息日>债券付息信息到期日,那么付息期间的截息日为债券付息信息中的截息日
					if (YssFun.toDate(fiHistoryPayPojo.getD_END())
							.after(fiPayFactor.getBondEndDate())) {
						fiHistoryPayPojo.setD_END(dateFormat
								.format(fiPayFactor
								.getBondEndDate()));
					}
					//STORY #14638 针对特殊债券，调整债券历史付息中计息起始日、截止日 --liuchi/2013.12.30
					//修改词汇 -- /2014.2.20
					// add by yh 2015.05.18 修改业务处理，不要在循环中做任何DAO处理
					//if( "DT_NA_WD".equals(getGZBySEC(fiPayFactor.getSecCode(),conn))
					if( "DT_NA_WD".equals(fiPayFactor.getC_DV_ASSURE())
							&&!VocabularyConsts.FI_ONCE.equalsIgnoreCase(fiPayFactor.getPayFrequency())){
						Date d_next = getNextBegin(fiPayFactor.getPayFrequency(), curBeginRateDay, fiPayFactor.getBondBeginDate());
						//因节假日引起的日期偏移
						Date d_correctHoliday = HolidaysAide.getTargetDateOnMkt(YssFun.addDay(d_next, -1), fiPayFactor.getMarket(), 
							1, VocabularyConsts.DATETYPE_WORK);
						if (null == d_correctHoliday) {
							break;
						}
						fiHistoryPayPojo.setD_END(YssFun.formatDate(YssFun.addDay(d_correctHoliday, -1)));
						
					}
					//STORY #66280 STORY 4.5境外债券的付息方式系统不支持 请在债品截息日规则中增加一条
					if( "DT_WD1_NA".equals(fiPayFactor.getC_DV_ASSURE())
							&&!VocabularyConsts.FI_ONCE.equalsIgnoreCase(fiPayFactor.getPayFrequency()) && fiHistoryPayData.size()>=1){
						/*
						 * STORY #66280 STORY 4.5境外债券的付息方式系统不支持 请在债品截息日规则中增加一条
						 * 生成债券历史付息时，增加【截息日规则】选择“按付息日（工作日）-1自然日倒推”的推算规则：
						 * 从第二个付息期间开始判断，当前期间生成的理论起息日落在节假日，则往后递延一个工作日，若当年无节假日群，则判断理论起息日落在周六、周日则递延至周一，同步调息日；
						 * 并更新上一期间截息日=该付息日-1自然日；
						 * 最后一个付息期间本期截息日不变；
						 * 备注：某个付息日递延后， 下一理论付息日 不按照 上一实际付息日来推算， 还是根据逻辑独立判断每个理论付息日是否落在节假日或周六、周日，如是则递延。
						 * edit by liuyazhou 20200701
						 */
						//根据市场代码获取节假日群代码
						MktCache mktCache = CacheManager.getInstance().getCache(CacheGroup.MKT);
						Mkt mkt = mktCache.getCacheByKey(fiPayFactor.getMarket());
						String holidaysCode = mkt.getC_HDAY_CODE();
						//判断某市场的本期起息日是否是节假日
						boolean isHoliday = HolidaysAide.isHoliday(curBeginRateDay, holidaysCode);
						if(isHoliday){
							//本期起息日根据节假日群代码获取的工作日日期
							Date d_correctHoliday = HolidaysAide.getWorkDayByHolidayCode(curBeginRateDay, 1, holidaysCode);
							//本期起息日为递延后的工作日
							fiHistoryPayPojo.setD_BEGIN(YssFun.formatDate(d_correctHoliday));
							//更新调息日
							fiHistoryPayPojo.setD_ADJ(fiHistoryPayPojo.getD_BEGIN());
						}
						//上期结息日=付息日-1自然日
						fiHistoryPayData.get(fiHistoryPayData.size()-1).setD_END(YssFun.formatDate(YssFun.addDay(YssFun.toSqlDate(fiHistoryPayPojo.getD_BEGIN()), -1)));
						
					}

					//如果有非库存数据，且本次理论期间的截息日大于非库存最大的截息日，本次起息日、调息日 = 非库存最大截息日+1，否则，直接加
					if(null != handEnd){
						if(YssFun.toDate(fiHistoryPayPojo.getD_END()).after(handEnd)){
							i++;
							if(i == 1){   //第一次匹配比较时改变起息日和调息日，后续无关
								Date Sdate = YssFun.addDay(handEnd,1);
								//如果起息日大于上一期间截息日+1 则赋值债券起息日为第一期间
								if(curBeginRateDay.compareTo(Sdate)>=0) {
									Sdate = curBeginRateDay;
								}
								fiHistoryPayPojo.setD_BEGIN(dateFormat.format(Sdate));
								fiHistoryPayPojo.setD_ADJ(dateFormat.format(Sdate));
							}
							//// 设计时认为有手工调整过的数据时，表示之前日期的都是没问题的，故对前面的数据不做处理不删不增 BUG #148052 
							if(null == lastPayMap ||
									(null != lastPayMap && YssFun.dateDiff(YssFun.toDate(lastPayMap.getD_END()), YssFun.addDay(nextBegin, -1))>0)){
								fiHistoryPayData.add(fiHistoryPayPojo);
							}
						}
					}else{
					//// 设计时认为有手工调整过的数据时，表示之前日期的都是没问题的，故对前面的数据不做处理不删不增 BUG #148052 
						if(null == lastPayMap ||
								(null != lastPayMap && YssFun.dateDiff(YssFun.toDate(lastPayMap.getD_END()), YssFun.addDay(nextBegin, -1))>0)){
							fiHistoryPayData.add(fiHistoryPayPojo);    //如果只有库存数据，直接插入
						}
					}
					// 如果期间截息日=债券截息日，中断循环
					if (YssFun.toDate(fiHistoryPayPojo.getD_END())
							.compareTo(fiPayFactor.getBondEndDate()) == 0) {
						break;
					}
//					curBeginRateDay = YssFun.addDay(
//							YssFun.toDate(fiHistoryPayPojo.getD_END()),
//							1);
					// 下次起息日赋值给curBeginRateDay  继续循环
					curBeginRateDay = nextBegin;
					m = m + n;  // 第一次起息日每次增加固定月份n 得到后续起息日
				}
				if (null != fiHistoryPayData) {
					fiHistoryPayData.trimToSize();
					fiHistoryPayMap.put(fiPay, fiHistoryPayData);
				}
			}
		
			if(fiPayFactors.get(0).getLicaiBeginDate().after(fiPayFactors.get(0).getBondBeginDate())
					&& fiPayFactors.get(0).getLicaiBeginDate().before(fiPayFactors.get(0).getBondEndDate())){
				ArrayList<FiPay> newFiHistoryPayData = new ArrayList<FiPay>();
				for(FiPay PayData : fiHistoryPayMap.keySet()){
					for(FiPay fiPay : fiHistoryPayMap.get(PayData)) {
						if(YssFun.toDate(fiPay.getD_END()).before(fiPayFactors.get(0).getLicaiBeginDate())){
							fiHistoryPayMap.get(PayData).remove(fiPay);
							newFiHistoryPayData.add(PayData);
							continue;
						}
						else{
							break;
						}
					}
					
				}
//				if(newFiHistoryPayData.size()>0
//						&& fiHistoryPayMap.size()>0
//						&& fiHistoryPayMap.get(0).size()>0
//						&& null != fiHistoryPayMap.get(0).get(0)
//						&& fiPayFactors.size()>0
//						&& null != fiPayFactors.get(0)){
//					fiHistoryPayMap.get(0).get(0).setD_BEGIN(YssFun.formatDate(fiPayFactors.get(0).getLicaiBeginDate()));
//				}
			}
		} catch (Exception ex) {
//			throw ex;
			logger.log("债券每百元利息初始化计算失败", ex);
			throw ex;
		} finally {
			dao.releaseConnection(conn);
		}
		return fiHistoryPayMap;
	}

	/**
	 * add by yewenke 2016-09-21 STORY #28953 【招商证券】债券基本信息和理财产品信息界面的付息频率设置需要增加按周及自定义频率的选项 
	 * @param unitStr
	 * @param pfInt 
	 * @param curBeginRateDay 当前日期
	 * @return 付息日期
	 */
	public String getZDYPLDate(String payFrequency,Date curBeginRateDay){
		String pf=payFrequency;
		int pfInt = Integer.parseInt(pf.substring(pf.indexOf("|")+1, pf.lastIndexOf("|")));
		String unit=pf.substring(pf.lastIndexOf("|")+1, pf.length());
		if(VocabularyConsts.DA.equals(unit)){
			return dateFormat.format(YssFun.addDay(YssFun.addDay(curBeginRateDay, pfInt),-1));
		}
		if(VocabularyConsts.WK.equals(unit)){
			return dateFormat.format(YssFun.addDay(YssFun.addDay(curBeginRateDay, pfInt*7),-1));
		}
		if(VocabularyConsts.MO.equals(unit)){
			return dateFormat.format(YssFun.addDay(YssFun.addMonth(curBeginRateDay, pfInt), -1));
		}
		if(VocabularyConsts.SE.equals(unit)){
			return dateFormat.format(YssFun.addDay(YssFun.addMonth(curBeginRateDay, 3*pfInt), -1));
		}
		if(VocabularyConsts.YR.equals(unit)){
			return dateFormat.format(YssFun.addDay(YssFun.addYear(curBeginRateDay, pfInt), -1));
		}
		return null;
	}
	
	/**
	 * 获取一组债券所有的历史付息数据
	 * STORY37869【南方基金】生成债券每百元信息时不覆盖读入的还本信息并生成还本区间段的每百元信息 add by zhaijiajia 20170214
	 * 以便实现对于财汇处理来的还本信息（即剩余本金或利率变动）数据，系统重新生成【债券历史付息】时不做覆盖操作，且认为该区间之前的数据均正确，推算生成该区间之后的区间数据
	 * 筛选出剩余本金和票面利率有变动的数据  此区间及以前期间数据不需重新生成
	 * @param conn 数据库连接
	 * @param secCodes 证券代码
	 * @return
	 * @throws Exception
	 */
	public static HashMap<String,List<FiPay>> getChangedHistoryPay(Connection conn, List<String> secCodes)
			throws Exception {
		//STORY #38597 【南方基金】公共信息处理-初始化债券每百元利息 执行速度慢 
		String sql1 = "INSERT INTO R_D_CLR_PARAM (C_PORT_CODE) VALUES (?)";
		PreparedStatement pst1 = null;
		boolean bTrans = false;//edit by sunhe 20170109 公共处理初始化债券每百元利息时并没有生成每日利息
		conn.setAutoCommit(bTrans);//edit by sunhe 20170109 公共处理初始化债券每百元利息时并没有生成每日利息
		HashMap<String,List<FiPay>> fiPayMap = new HashMap<String,List<FiPay>>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		if(null == secCodes){
			return null;
		}
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT A.C_SEC_CODE, A.D_BEGIN, A.D_END, A.N_COUP_RATE, A.N_REM_COR ");
		sqlBuffer.append(" FROM T_D_SV_FI_PAY A ");
		sqlBuffer.append(" WHERE A.C_SEC_CODE IN (SELECT * FROM R_D_CLR_PARAM) ");
		sqlBuffer.append(" AND A.N_CHECK_STATE = 1 ORDER BY A.D_BEGIN ");
		String sql = sqlBuffer.toString();
		try {
			pst1 = conn.prepareStatement(sql1);
			for(int i =0;i<secCodes.size();i++){
				pst1.setString(1, secCodes.get(i));
				pst1.addBatch();
			}
			pst1.executeBatch();
			pst1.clearBatch();//addbyleeyu20151016
			
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				List<FiPay> fiPays = null;
				FiPay fiHistoryPay = new FiPay();
				// 证券代码
				fiHistoryPay.setC_SEC_CODE(rs.getString("C_SEC_CODE"));
				// 本期起息日
				fiHistoryPay.setD_BEGIN(rs.getDate("D_BEGIN").toString());
				// 本期截息日
				fiHistoryPay.setD_END(rs.getDate("D_END").toString());
				// 票面利率
				fiHistoryPay.setN_COUP_RATE(YssFun.toDecimal(rs.getString("N_COUP_RATE")));
				// 剩余本金
				fiHistoryPay.setN_REM_COR(YssFun.toDecimal(rs.getString("N_REM_COR")));
				if(fiPayMap.containsKey(rs.getString("C_SEC_CODE"))) {
					fiPays = fiPayMap.get(rs.getString("C_SEC_CODE"));
				} else {
					fiPays = new ArrayList<FiPay>();
				}
				fiPays.add(fiHistoryPay);
				fiPayMap.put(fiHistoryPay.getC_SEC_CODE(), fiPays);
			}
			conn.commit();
			bTrans = true;
			conn.setAutoCommit(bTrans);
		} catch (SQLException e) {
			throw e;
		} finally {
			//chenbo
//			DBUtils.cleanUp(rs, pst); //调用公共方法byleeyu20151015
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst1,pst);
		}
		return fiPayMap;
	}
	
	/**
	 * STORY37118【紧急】【招商基金】理财信托产品需支持“按季末月第N个自然日”付息 add by liuyanni 2016-12-22
	 * @param payFrequency    付息频率
	 * @param curBeginRateDay 当前日期
	 * @return
	 */
	public Date getFxDate(String payFrequency,Date curBeginRateDay) throws Exception {
		String pf=payFrequency;
		String pl=pf.substring(0,pf.indexOf("|"));
		int pfInt = Integer.parseInt(pf.substring(pf.indexOf("|")+1)); //第N个自然日
		Date date1 = null;
		Date date2 = null;
		Date date = null;//计算出的结息日
		if(VocabularyConsts.FI_QN.equalsIgnoreCase(pl)){//每季末月第N个自然日
			date1 = DateUtil.getFirstNatureDayOnQuarterEnd(curBeginRateDay);// 所在季度的最后一个月
			//因为这里都是获取的自然日来计算的，所以跟节假日没有关系，这里都默认节假日为CN
			date2 = HolidaysAide.getTargetDateOnHoliday(date1,"CN",pfInt-2,VocabularyConsts.DATETYPE_NATURE);//获取第n_MARK_DAYS个自然日
			if(date2.after(curBeginRateDay)){ 
				date = date2;
			}else{
				date = YssFun.addMonth(date2,3);
			}
		}
		if(VocabularyConsts.FI_YN.equals(pl)){//每年末月第N个自然日
			date1 = DateUtil.getFirstNatureDayOnYearEnd(curBeginRateDay);// 所在年份的最后一个月
			date2 = HolidaysAide.getTargetDateOnHoliday(date1,"CN",pfInt-2,VocabularyConsts.DATETYPE_NATURE);//获取第n_MARK_DAYS个自然日
			if(date2.after(curBeginRateDay)){ 
				date = date2;
			}else{
				date = YssFun.addYear(date1,1);
			}
		}
		if(VocabularyConsts.FI_MN.equals(pl)){//每月第N个自然日
			Date one = DateUtil.getFirstNatureDayOnMonth(curBeginRateDay);//获取所在月份的第一个自然日，即第一天
			date1 = HolidaysAide.getTargetDateOnHoliday(one,"CN",pfInt-2,VocabularyConsts.DATETYPE_NATURE);//获取第accrualDate个自然日
			if(date1.after(curBeginRateDay) || date1.equals(curBeginRateDay)){
				date = date1;
			}else{
				date = YssFun.addMonth(one,1);
			}
		}
		return date;
	}
	
	/**
	 * 获取一只债券最近的历史付息数据
	 * 
	 * @param secCode
	 * @return
	 * @throws YssException 
	 * @throws Exception
	 */
	public static FiPay getLastHistoryPay(Connection conn, String secCode)
			throws SQLException, YssException {
		FiPay fiHistoryPay = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "SELECT C_SEC_CODE,D_BEGIN, D_END,N_COUP_RATE,N_REM_COR FROM T_D_SV_FI_PAY WHERE N_CHECK_STATE = 1 "
				+ " AND D_END = (SELECT MAX(D_END) FROM T_D_SV_FI_PAY WHERE N_CHECK_STATE = 1 AND C_SEC_CODE = ?)"
				+ " AND C_SEC_CODE = ?";
		if (null != secCode) {
			try {
				pst = conn.prepareStatement(sql);
				pst.setString(1, secCode);
				pst.setString(2, secCode);
				rs = pst.executeQuery();
				while (rs.next()) {
					fiHistoryPay = new FiPay();
					// 证券代码
					fiHistoryPay.setC_SEC_CODE(rs.getString("C_SEC_CODE"));
					// 本期起息日
					fiHistoryPay.setD_BEGIN(rs.getDate("D_BEGIN").toString());
					// 本期截息日
					fiHistoryPay.setD_END(rs.getDate("D_END").toString());
					// 票面利率
					fiHistoryPay.setN_COUP_RATE(YssFun.toDecimal(rs.getString("N_COUP_RATE")));
					// fiHistoryPay.setN_COUP_RATE(String.valueOf(rs
					// .getDouble("N_COUP_RATE")));
					// 剩余本金
					fiHistoryPay.setN_REM_COR(YssFun.toDecimal(rs.getString("N_REM_COR")));
					// fiHistoryPay.setN_REM_COR(String.valueOf(rs
					// .getDouble("N_REM_COR")));
				}
			} catch (SQLException e) {
//				e.printStackTrace();
				throw e;
			} finally {
				//chenbo
				DbFun.closeResultSetFinal(rs);
				DbFun.closeStatementFinal(pst);
//				DBUtils.cleanUp(rs, pst); //调用公共方法byleeyu20151015
//				if (null != pst)
//					pst.close();
//				if (null != rs)
//					rs.close();
			}
		}
		return fiHistoryPay;
	}
	
	/**
	 * 获取一只债券最近的非库存历史付息数据
	 * 
	 * @param secCode
	 * @return
	 * @throws YssException 
	 * @throws Exception
	 */
	public static FiPay getLastEditHistoryPay(Connection conn, String secCode)
			throws SQLException, YssException {
		FiPay fiHistoryPay = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "SELECT C_SEC_CODE,D_BEGIN, D_END,N_COUP_RATE,N_REM_COR FROM T_D_SV_FI_PAY WHERE N_CHECK_STATE = 1 "
				+ " AND D_END = (SELECT MAX(D_END) FROM T_D_SV_FI_PAY WHERE N_CHECK_STATE = 1 AND C_DATA_IDF != 'S' AND C_SEC_CODE = ?)"
				+ " AND C_SEC_CODE = ? AND C_DATA_IDF != 'S' ";
		if (null != secCode) {
			try {
				pst = conn.prepareStatement(sql);
				pst.setString(1, secCode);
				pst.setString(2, secCode);
				rs = pst.executeQuery();
				while (rs.next()) {
					fiHistoryPay = new FiPay();
					// 证券代码
					fiHistoryPay.setC_SEC_CODE(rs.getString("C_SEC_CODE"));
					// 本期起息日
					fiHistoryPay.setD_BEGIN(rs.getDate("D_BEGIN").toString());
					// 本期截息日
					fiHistoryPay.setD_END(rs.getDate("D_END").toString());
					// 票面利率
					fiHistoryPay.setN_COUP_RATE(YssFun.toDecimal(rs.getString("N_COUP_RATE")));
					// fiHistoryPay.setN_COUP_RATE(String.valueOf(rs
					// .getDouble("N_COUP_RATE")));
					// 剩余本金
					fiHistoryPay.setN_REM_COR(YssFun.toDecimal(rs.getString("N_REM_COR")));
					// fiHistoryPay.setN_REM_COR(String.valueOf(rs
					// .getDouble("N_REM_COR")));
				}
			} catch (SQLException e) {
				//logger.log("删除非库存数据截息日之后的债券历史付息数据出错", e);
			} finally {
				//chenbo
				DbFun.closeResultSetFinal(rs);
				DbFun.closeStatementFinal(pst);
//				DBUtils.cleanUp(rs, pst); //调用公共方法byleeyu20151015
			}
		}
		return fiHistoryPay;
	}
	
	/**
	 * 获取一组债券最近的历史付息数据
	 * @param conn 数据库连接
	 * @param secCodes 证券代码
	 * @return
	 * @throws Exception
	 */
	public static HashMap<String,FiPay> getLastHistoryPay(Connection conn, List<String> secCodes)
			throws Exception {
		//STORY #38597 【南方基金】公共信息处理-初始化债券每百元利息 执行速度慢 
		String sql1 = "INSERT INTO R_D_CLR_PARAM (C_PORT_CODE) VALUES (?)";
		PreparedStatement pst1 = null;
		boolean bTrans = false;//edit by sunhe 20170109 公共处理初始化债券每百元利息时并没有生成每日利息
		conn.setAutoCommit(bTrans);//edit by sunhe 20170109 公共处理初始化债券每百元利息时并没有生成每日利息
		
		HashMap<String,FiPay> fiPayMap = new HashMap<String,FiPay>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		if(null == secCodes){
			return null;
		}
		StringBuffer sqlBuffer = new StringBuffer();
//		sqlBuffer.append(" SELECT A.C_SEC_CODE, A.D_BEGIN, A.D_END, A.N_COUP_RATE, A.N_REM_COR ");
//		sqlBuffer.append(" FROM T_D_SV_FI_PAY A");
//		sqlBuffer.append(" JOIN TABLE(?) B ON A.C_SEC_CODE = B.COLUMN_VALUE ");
//		sqlBuffer.append(" WHERE NOT EXISTS (SELECT 1");
//		sqlBuffer.append("       FROM T_D_SV_FI_PAY B");
//		sqlBuffer.append("       WHERE B.D_END > A.D_END");
//		sqlBuffer.append("       AND A.C_SEC_CODE = B.C_SEC_CODE)");
//		sqlBuffer.append("       AND A.N_CHECK_STATE = 1");
		// modifiy by liyanjun 2016-3-4 BUG #127263 债券历史付息信息更新有误
		sqlBuffer.append(" SELECT A.C_SEC_CODE, A.D_BEGIN, A.D_END, A.N_COUP_RATE, A.N_REM_COR ");
		sqlBuffer.append(" FROM T_D_SV_FI_PAY A ");
		sqlBuffer.append(" JOIN (SELECT MAX(P.D_END) as D_END, P.C_SEC_CODE ");
		// BUG #133717 【南方资本】债券历史付息截息日修改后重新产生历史付息不会按修改后的截息日+1天作为下一周期的起息日产生历史付息
		// xiaozhilong 20160711
		// 查询最近历史付息信息时不应加入数据来源判断，原有逻辑会导致手工数据无法查询到，导致重新生成历史付息数据日期错误
		sqlBuffer.append(" FROM T_D_SV_FI_PAY P WHERE P.N_CHECK_STATE = 1 ");
		// sqlBuffer.append(" FROM T_D_SV_FI_PAY P WHERE P.N_CHECK_STATE = 1 AND P.C_DATA_IDF = 'S' ");
		sqlBuffer.append(" GROUP BY P.C_SEC_CODE) B  on A.D_END = B.D_END ");
		sqlBuffer.append(" AND A.C_SEC_CODE = B.C_SEC_CODE ");
		sqlBuffer.append(" JOIN R_D_CLR_PARAM C  ON A.C_SEC_CODE = C.C_PORT_CODE");
		String sql = sqlBuffer.toString();
		try {
			pst1 = conn.prepareStatement(sql1);
			for(int i =0;i<secCodes.size();i++){
				pst1.setString(1, secCodes.get(i));
				pst1.addBatch();
			}
			pst1.executeBatch();
			pst1.clearBatch();//addbyleeyu20151016
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				FiPay fiHistoryPay = new FiPay();
				// 证券代码
				fiHistoryPay.setC_SEC_CODE(rs.getString("C_SEC_CODE"));
				// 本期起息日
				fiHistoryPay.setD_BEGIN(rs.getDate("D_BEGIN").toString());
				// 本期截息日
				fiHistoryPay.setD_END(rs.getDate("D_END").toString());
				// 票面利率
				fiHistoryPay.setN_COUP_RATE(YssFun.toDecimal(rs.getString("N_COUP_RATE")));
				// 剩余本金
				fiHistoryPay.setN_REM_COR(YssFun.toDecimal(rs.getString("N_REM_COR")));
				fiPayMap.put(fiHistoryPay.getC_SEC_CODE(), fiHistoryPay);
			}
			conn.commit();
			bTrans = true;
			conn.setAutoCommit(bTrans);
		} catch (SQLException e) {
//			e.printStackTrace();
			throw e;
		} finally {
			//chenbo
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
//			DBUtils.cleanUp(rs, pst); //调用公共方法byleeyu20151015
			DbFun.closeStatementFinal(pst1);
//			if (null != pst)
//				pst.close();
//			if (null != rs)
//				rs.close();
		}
		return fiPayMap;
	}

	/**
	 * 获取一只债券历史付息数据
	 * 
	 * @param secCode
	 * @return
	 * @throws YssException 
	 * @throws Exception
	 */
	public static FiPay getUniqueHistoryPay(Connection conn, String secCode,
			Date date) throws SQLException, YssException {
		FiPay fiHistoryPay = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "SELECT C_SEC_CODE,D_ADJ,D_BEGIN, D_END,N_COUP_RATE,N_REM_COR "
				+ " FROM T_D_SV_FI_PAY "
				+ " WHERE N_CHECK_STATE = 1 "
				+ " AND D_BEGIN <= ? AND D_END >= ? AND C_SEC_CODE = ?";
		if (null == secCode || null == date)
			return null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setDate(1, YssFun.toSqlDate(date));
			pst.setDate(2, YssFun.toSqlDate(date));
			pst.setString(3, secCode);
			rs = pst.executeQuery();
			while (rs.next()) {
				fiHistoryPay = new FiPay();
				// 证券代码
				fiHistoryPay.setC_SEC_CODE(rs.getString("C_SEC_CODE"));
				// 调息日期
				fiHistoryPay.setD_ADJ(rs.getDate("D_ADJ").toString());
				// 本期起息日
				fiHistoryPay.setD_BEGIN(rs.getDate("D_BEGIN").toString());
				// 本期截息日
				fiHistoryPay.setD_END(rs.getDate("D_END").toString());
				// 票面利率
				fiHistoryPay.setN_COUP_RATE(YssFun.toDecimal(rs.getString("N_COUP_RATE")));
				// fiHistoryPay.setN_COUP_RATE(String.valueOf(rs
				// .getDouble("N_COUP_RATE")));
				// 剩余本金
				fiHistoryPay.setN_REM_COR(YssFun.toDecimal(rs.getString("N_REM_COR")));
				// fiHistoryPay.setN_REM_COR(String.valueOf(rs
				// .getDouble("N_REM_COR")));
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			throw e;
		} finally {
			//chenbo
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
//			DBUtils.cleanUp(rs, pst); //调用公共方法byleeyu20151015
//			if (null != pst)
//				pst.close();
//			if (null != rs)
//				rs.close();
		}

		return fiHistoryPay;
	}
	
	/**
	 * 获取一只债券历史付息数据
	 * 
	 * @param secCode
	 * @return
	 * @throws YssException 
	 * @throws Exception
	 */
	public static HashMap<String, FiPay> getUniqueHistoryPay(Connection conn, java.sql.Array secCodeArray,
			Date date) throws SQLException, YssException {
		HashMap<String, FiPay> hisInterestMap = new HashMap<String, FiPay>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "SELECT C_SEC_CODE,D_ADJ,D_BEGIN, D_END,N_COUP_RATE,N_REM_COR "
				+ " FROM T_D_SV_FI_PAY "
				+ " WHERE N_CHECK_STATE = 1 "
				+ " AND D_BEGIN <= ? AND D_END >= ? AND C_SEC_CODE IN (SELECT * FROM TABLE(?))";
		if (null == secCodeArray || null == date)
			return null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setDate(1, YssFun.toSqlDate(date));
			pst.setDate(2, YssFun.toSqlDate(date));
			pst.setArray(3, secCodeArray);
			rs = pst.executeQuery();
			while (rs.next()) {
				FiPay fiHistoryPay = new FiPay();
				// 证券代码
				fiHistoryPay.setC_SEC_CODE(rs.getString("C_SEC_CODE"));
				// 调息日期
				fiHistoryPay.setD_ADJ(rs.getDate("D_ADJ").toString());
				// 本期起息日
				fiHistoryPay.setD_BEGIN(rs.getDate("D_BEGIN").toString());
				// 本期截息日
				fiHistoryPay.setD_END(rs.getDate("D_END").toString());
				// 票面利率
				fiHistoryPay.setN_COUP_RATE(YssFun.toDecimal(rs.getString("N_COUP_RATE")));
				// 剩余本金
				fiHistoryPay.setN_REM_COR(YssFun.toDecimal(rs.getString("N_REM_COR")));
				hisInterestMap.put(rs.getString("C_SEC_CODE"), fiHistoryPay);
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			throw e;
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
		}

		return hisInterestMap;
	}

	/**
	 * 计算债券总付息次数
	 * 
	 * @param conn
	 *            数据库连接
	 * @param secCode
	 *            证券代码
	 * @param bondEndDate 截息日期
	 * @return
	 * @throws SQLException
	 */
	public static int calcFiTotalPayTimes(Connection conn, String secCode,Date bondEndDate)
			throws SQLException {
		int totalPayTimes = 0;
		PreparedStatement pst = null;
		ResultSet rs = null;
		if (null == secCode)
			return 0;
		//modified by dingxukun 20161022 BUG #143147 【紧急】太平保险-一年多次付息债券溢折价摊销错误
		//添加起息日<=截息日判断,以过滤垃圾数据
		String sql = "SELECT COUNT(*) AS COUNT  FROM T_D_SV_FI_PAY  WHERE N_CHECK_STATE = 1  AND C_SEC_CODE = ? and d_end <= ? AND D_BEGIN<=D_END ";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, secCode);
			pst.setDate(2, YssFun.toSqlDate(bondEndDate));
			rs = pst.executeQuery();
			while (rs.next()) {
				totalPayTimes = rs.getInt("COUNT");
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			throw e;
		} finally {
			//chenbo
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
//			DBUtils.cleanUp(rs, pst); //调用公共方法byleeyu20151015
//			if (null != pst)
//				pst.close();
//			if (null != rs)
//				rs.close();
		}
		return totalPayTimes;
	}

	/**
	 * 计算债券已经付息的次数
	 * 
	 * @param conn
	 *            数据库连接
	 * @param secCode
	 *            证券代码
	 * @param markDate
	 *            基准日期
	 * @return
	 * @throws SQLException
	 */
	public static int calcFiAlreadyPayTimes(Connection conn, String secCode,
			Date markDate) throws SQLException {
		int alreadyPayTimes = 0;
		PreparedStatement pst = null;
		ResultSet rs = null;
		//modified by dingxukun 20161022 BUG #143147 【紧急】太平保险-一年多次付息债券溢折价摊销错误
		//添加起息日<=截息日判断,以过滤垃圾数据
		String sql = "SELECT COUNT(*) AS COUNT FROM T_D_SV_FI_PAY  WHERE N_CHECK_STATE = 1  AND  D_END < ? AND C_SEC_CODE = ? AND D_BEGIN <= D_END";
		if (null == secCode || null == markDate)
			return 0;
		try {
			pst = conn.prepareStatement(sql);
			pst.setDate(1, YssFun.toSqlDate(markDate));
			pst.setString(2, secCode);
			rs = pst.executeQuery();
			while (rs.next()) {
				alreadyPayTimes = rs.getInt("COUNT");
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			throw e;
		} finally {
			//chenbo
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
//			DBUtils.cleanUp(rs, pst); //调用公共方法byleeyu20151015
//			if (null != pst)
//				pst.close();
//			if (null != rs)
//				rs.close();
		}
		return alreadyPayTimes;
	}

	/**
	 * 保存债券历史付息数据
	 * 
	 * @param historyPayData
	 * @throws Exception
	 */
	private void saveHistoryPayData(List<FiPay> historyPayData,Connection dbConn)
			throws Exception {
		PreparedStatement pst = null;// 预处理语句
		String strSql = "";// 查询字符串
//		Connection conn = null;
		try {
//			conn = dao.loadNewConnection();
			// 添加非空处理 xzl 
			if (null != historyPayData && !historyPayData.isEmpty()) {
//				conn.setAutoCommit(false);
				HashMap<String, String> handHisPayMap = checkHandHistoryPayData(historyPayData,dbConn);
				String curTime = YssFun.formatDatetime(new Date());
				/*STORY #38597 【南方基金】公共信息处理-初始化债券每百元利息 执行速度慢
				 * 多线程时执行用户如果为空则使用sys */
				String userCode = ContextFactory.getContext().getUserCode();
				userCode = StringUtil.IsNullOrEmpty(userCode) ? "sys" : userCode;
				//strSql = "INSERT INTO T_D_SV_FI_PAY ("
				//		+ " C_IDEN, C_SEC_CODE, D_ADJ, N_COUP_RATE, N_REM_COR, D_BEGIN, D_END, C_DESC,"
				//		+ " N_CHECK_STATE,C_UPDATE_BY,C_UPDATE_TIME,C_DV_BOOL_TYPE,C_MKT_CODE,C_CHECK_BY,C_CHECK_TIME)"
				//		+ " VALUES (SEQU_D_SV_FI_PAY.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				// edit tdf BUG #115588 [紧急]浮动利率债券没有清算出财汇资讯数据 20150715 
				strSql = "INSERT INTO T_D_SV_FI_PAY ("
						+ " C_IDEN, C_SEC_CODE, D_ADJ, N_COUP_RATE, N_REM_COR, D_BEGIN, D_END, C_DESC,"
						+ " N_CHECK_STATE,C_UPDATE_BY,C_UPDATE_TIME,C_DV_BOOL_TYPE,C_MKT_CODE,C_CHECK_BY,C_CHECK_TIME,C_DATA_IDF)"
						+ " VALUES (SEQU_D_SV_FI_PAY.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				
				pst = dbConn.prepareStatement(strSql);
				for (int i = 0; i < historyPayData.size(); i++) {
					FiPay fi = historyPayData.get(i);// 债券付息pojo类
					// BUG #120773 xzl 
					// 存在自动转手工或手工历史付息数据时不做重复插入
					String continueCond = fi.getC_SEC_CODE()+VocabularyConsts.splitMark+YssFun.formatDate(fi.getD_ADJ());
					if (handHisPayMap.containsKey(continueCond)) {
						continue;
					}
					pst.setString(1, fi.getC_SEC_CODE());
					pst.setDate(2, YssFun.toSqlDate(fi.getD_ADJ()));
					pst.setBigDecimal(3, fi.getN_COUP_RATE());
					// pst.setDouble(3, YssFun.toDouble(fi.getN_COUP_RATE()));
					pst.setBigDecimal(4, fi.getN_REM_COR());
					// pst.setDouble(4, YssFun.toDouble(fi.getN_REM_COR()));
					pst.setDate(5, YssFun.toSqlDate(fi.getD_BEGIN()));
					pst.setDate(6, YssFun.toSqlDate(fi.getD_END()));
					pst.setString(7, fi.getC_DESC());
					pst.setInt(8, 1);
					pst.setString(9, userCode);
					pst.setString(10, curTime);
					pst.setString(11, fi.getC_DV_BOOL_TYPE());
					pst.setString(12, fi.getC_MKT_CODE());
					pst.setString(13, userCode); // 添加审核人审核时间byleeyu20130719
					pst.setString(14, curTime); //　添加审核时间byleeyu20130719
					pst.setString(15, fi.getC_DATA_IDF());//edit tdf BUG #115588 [紧急]浮动利率债券没有清算出财汇资讯数据 20150715
					pst.addBatch();
				}// end for
				pst.executeBatch();
				pst.clearBatch(); //addClearBatchbyleeyu20151015
//				conn.commit();
//				conn.setAutoCommit(true);
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			logger.log("初始化债券每百元：保存债券历史付息出错", e);
			throw (Exception) e;
		} catch (YssException e) {
//			e.printStackTrace();
			logger.log("初始化债券每百元：保存债券历史付息出错", e);
			throw (Exception) e;
		} finally {
			dao.closeStatementFinal(pst);
//			dao.releaseConnection(conn);
		}
	}

	/**
	 * 删除债券历史付息数据
	 * modifiy by liyanjun 2016-3-4 BUG #127263 债券历史付息信息更新有误
	 * @param historyPayData
	 * @throws Exception
	 */
	private void deleteHistoryPayData(List<FiPay> historyPayData) throws Exception {
		PreparedStatement pst = null;// 预处理语句
		Connection conn = null;
		if (null == historyPayData) {
			return;
		}
		try {
			String sql = "DELETE FROM T_D_SV_FI_PAY WHERE C_DATA_IDF = 'S' AND C_SEC_CODE = ? AND D_ADJ = ? AND D_BEGIN = ? AND D_END = ?";
			conn = dao.loadNewConnection();
			pst = conn.prepareStatement(sql);
			for (int i = 0; i < historyPayData.size(); i++) {
				FiPay fi = historyPayData.get(i);// 债券付息pojo类
				pst.setString(1, fi.getC_SEC_CODE());
				pst.setDate(2, YssFun.toSqlDate(fi.getD_ADJ()));
				pst.setDate(3, YssFun.toSqlDate(fi.getD_BEGIN()));
				pst.setDate(4, YssFun.toSqlDate(fi.getD_END()));
				pst.addBatch();
			}// end for
			pst.executeBatch();
			pst.clearBatch(); //addbyleeyu20151015
			conn.commit();
		} catch (SQLException e) {
//			e.printStackTrace();
			logger.log("初始化债券每百元：删除债券历史付息出错", e);
			throw (Exception) e;
		} finally {
			dao.closeStatementFinal(pst);
			dao.releaseConnection(conn);
		}
	}
	
	/**
	 * 删除非库存数据最大起息日之后的债券历史付息数据
	 * add by guohui 2016-11-2 STORY35631【紧急】太平保险-部分债券历史付息生成错误导致债券计息溢折价错误
	 * @param historyPayData
	 * @throws Exception
	 */
	private void deleteHistoryPaybyData(HashMap<FiPay,List<FiPay>> fiPayHistroyMap,Connection dbConn) throws Exception {
		if (null == fiPayHistroyMap) {
			return;
		}
		
		PreparedStatement pst = null;// 预处理语句
		Connection conn = null;
		Connection conn1 = null;
		Date handBegin = null;
		String secCode = null;
		StringBuffer sql = new StringBuffer();
		
		try {
			conn = dbConn;
			sql.append(" DELETE FROM T_D_SV_FI_PAY WHERE C_SEC_CODE = ? AND ");
			sql.append(" (((C_DATA_IDF = 'S' or (C_DATA_IDF != 'S' and N_CHECK_STATE = 0)) and D_END > ?) or ");
			sql.append(" D_BEGIN < ?)");
			conn1 = dao.loadNewConnection();
			pst = conn.prepareStatement(sql.toString());
			for(FiPay fiPay : fiPayHistroyMap.keySet()){
				secCode = fiPay.getC_SEC_CODE();
				FiPay lastPayMap = AdmFiHistoryPay.getLastEditHistoryPay(conn1, secCode);
				if(null == lastPayMap) {
					if(!"".equals(fiPay.getD_END())) {
						handBegin = dateFormat.parse(fiPay.getD_END());
					} else {
						handBegin = dateFormat.parse("1900-01-01");
					}
				} else {
					if(!"".equals(fiPay.getD_END())) {
						if(dateFormat.parse(lastPayMap.getD_END()).compareTo(dateFormat.parse(fiPay.getD_END())) <=0 ) {
							handBegin = dateFormat.parse(lastPayMap.getD_END());
						} else {
							handBegin = dateFormat.parse(fiPay.getD_END());
						}
					} else {
						handBegin = dateFormat.parse(lastPayMap.getD_END());
					}
				}
				
				pst.setString(1, secCode);
				pst.setDate(2, YssFun.toSqlDate(handBegin));
				pst.setDate(3, YssFun.toSqlDate(fiPay.getD_BEGIN()));
				pst.addBatch();
			}
			pst.executeBatch();
			pst.clearBatch(); 
		}
		catch (SQLException e) {
			logger.log("删除非库存数据截息日之后的债券历史付息数据出错", e);
		} finally {
			dao.closeStatementFinal(pst);
//			dao.releaseConnection(conn);
			dao.releaseConnection(conn1);
		}
	}
	
	/**
	 * 删除非库存数据最大起息日之后的债券历史付息数据
	 * add by guohui 2016-11-2 STORY35631【紧急】太平保险-部分债券历史付息生成错误导致债券计息溢折价错误
	 * @param historyPayData
	 * @throws Exception
	 */
	private void deleteHistoryPaybyData(List<FiPayFactorBean> fiPayFactors) throws Exception {
		if (null == fiPayFactors) {
			return;
		}
		
		PreparedStatement pst = null;// 预处理语句
		Connection conn = null;
		Connection conn1 = null;
		Date handBegin = null;
		String secCode = null;
		StringBuffer sql = new StringBuffer();
		
		try {
			conn = dao.loadNewConnection();
			sql.append(" DELETE FROM T_D_SV_FI_PAY WHERE (C_DATA_IDF = 'S' or (C_DATA_IDF != 'S' and N_CHECK_STATE = 0)) AND C_SEC_CODE = ? AND D_END > ?");
			conn1 = dao.loadNewConnection();
			pst = conn.prepareStatement(sql.toString());
			for(FiPayFactorBean payFactor : fiPayFactors){
				secCode = payFactor.getSecCode();
				FiPay lastPayMap = this.getLastEditHistoryPay(conn1, secCode);
				if(lastPayMap != null){
					handBegin = dateFormat.parse(lastPayMap.getD_END());
				}else{
					handBegin = dateFormat.parse("1900-01-01");
				}
				
				pst.setString(1, secCode);
				pst.setDate(2, YssFun.toSqlDate(handBegin));
				pst.addBatch();
			}
			pst.executeBatch();
			pst.clearBatch(); 
		}
		catch (SQLException e) {
			//logger.log("删除非库存数据截息日之后的债券历史付息数据出错", e);
		} finally {
			dao.closeStatementFinal(pst);
			dao.releaseConnection(conn);
			dao.releaseConnection(conn1);
		}
	}
	
	/**
	 * 删除单只债券历史付息数据
	 * modifiy by liyanjun 2016-3-4 BUG #127263 债券历史付息信息更新有误
	 * @param historyPayData
	 * @throws Exception
	 */
	public void deleteHistoryPayData(String secCode) throws Exception {
		PreparedStatement pst = null;// 预处理语句
		Connection conn = null;
		try {
			String sql = " DELETE FROM T_D_SV_FI_PAY WHERE C_SEC_CODE = ?  AND C_DATA_IDF = 'S' ";
			conn = dao.loadNewConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, secCode);
			pst.executeUpdate();
		
		} catch (SQLException e) {
//			e.printStackTrace();
			logger.log("初始化债券每百元：删除单只债券历史付息数据出错", e);
			throw (Exception) e;
		} finally {
			dao.closeStatementFinal(pst);
			dao.releaseConnection(conn);
		}
	}
	
	/**
	 * 删除单只债券历史付息数据
	 * 
	 * @param historyPayData
	 * @throws Exception
	 */
	public void deleteHistoryPerHundData(String secCode) throws Exception {
		PreparedStatement pst = null;// 预处理语句
		Connection conn = null;
		try {
			String sql = " DELETE FROM T_D_SV_FI_INCOME WHERE C_SEC_CODE = ? ";
			conn = dao.loadNewConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, secCode);
			pst.executeUpdate();
		
		} catch (SQLException e) {
//			e.printStackTrace();
			logger.log("初始化债券每百元：删除单只债券历史付息数据出错", e);
			throw (Exception) e;
		} finally {
			dao.closeStatementFinal(pst);
			dao.releaseConnection(conn);
		}
	}
	
	/**
	 * 获取手动债券历史付息数据
	 * BUG #120773 xzl 
	 * @param historyPayData
	 * @throws Exception
	 */
	public HashMap<String, String> checkHandHistoryPayData(List<FiPay> fiPayList,Connection conn) throws Exception {
		HashMap<String, String> handHisPayMap = new HashMap<String, String>();
		PreparedStatement pst = null;// 预处理语句
//		Connection conn = null;
		ResultSet rsResultSet = null;
		try {
			String sql = " SELECT C_SEC_CODE, D_ADJ FROM T_D_SV_FI_PAY WHERE C_SEC_CODE IN (SELECT * FROM TABLE(?)) AND C_DATA_IDF <> 'Z' AND N_CHECK_STATE = 1";
//			conn = dao.loadNewConnection();
			pst = conn.prepareStatement(sql);
//			com.yss.framework.db.OraDbTool dbTool = com.yss.framework.db.OraDbTool
//					.newInstance();
//			dbTool.set(YssConstant.DBSERVICE_NAME);
			pst.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(buildSecCodesString(fiPayList),conn));
			rsResultSet = pst.executeQuery();
			while (rsResultSet.next()) {
				handHisPayMap.put(rsResultSet.getString("C_SEC_CODE")+VocabularyConsts.splitMark+YssFun.formatDate(rsResultSet.getDate("D_ADJ")), "");
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			logger.log("初始化债券每百元：获取手动债券历史付息数据出错", e);
			throw (Exception) e;
		} finally {
			// BUG #134663 findbugs代码检查和PMD数据库连接检查问题修复 edit by chenyoulong  20160719 
			// 关闭结果集
			dao.closeResultSetFinal(rsResultSet);
			dao.closeStatementFinal(pst);
//			dao.releaseConnection(conn);
		}
		return handHisPayMap;
	}
	
	/**
	 * 构造证券代码字符串
	 * @param fiPayList
	 * @return
	 */
	private String buildSecCodesString(List<FiPay> fiPayList) {
		String secCodes = "";
		for (FiPay fiPay : fiPayList) {
			if (!secCodes.contains(fiPay.getC_SEC_CODE())) {
				secCodes = secCodes+","+fiPay.getC_SEC_CODE();
			}
		}
		secCodes = secCodes.substring(1, secCodes.length());
		return secCodes;
	}
	
	/**
	 * 获取一只债券历史付息数据
	 * 
	 * @param secCode
	 * @return
	 * @throws Exception
	 */
	public FiPay getUniqueHistoryPay(String secCode, Date date) throws SQLException {
		FiPay fiHistoryPay = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		StringBuffer buff = new StringBuffer();
		buff.append(" SELECT C_SEC_CODE,D_ADJ,D_BEGIN, D_END,N_COUP_RATE,N_REM_COR, ");
		buff.append(" C_MKT_CODE FROM T_D_SV_FI_PAY WHERE N_CHECK_STATE = 1 ");
		buff.append(" AND D_BEGIN <= ? AND D_END >= ? AND C_SEC_CODE = ? ");
		if (null == secCode || null == date)
			return null;
		try {
			conn = dao.loadNewConnection();
			pst = conn.prepareStatement(buff.toString());
			pst.setDate(1, YssFun.toSqlDate(date));
			pst.setDate(2, YssFun.toSqlDate(date));
			pst.setString(3, secCode);
			rs = pst.executeQuery();
			while (rs.next()) {
				fiHistoryPay = new FiPay();
				// 证券代码
				fiHistoryPay.setC_SEC_CODE(rs.getString("C_SEC_CODE"));
				// 调息日期
				fiHistoryPay.setD_ADJ(rs.getDate("D_ADJ").toString());
				// 本期起息日
				fiHistoryPay.setD_BEGIN(rs.getDate("D_BEGIN").toString());
				// 本期截息日
				fiHistoryPay.setD_END(rs.getDate("D_END").toString());
				// 票面利率
				fiHistoryPay.setN_COUP_RATE(BigDecimal.valueOf(rs.getDouble("N_COUP_RATE")));
				// 剩余本金
				fiHistoryPay.setN_REM_COR(BigDecimal.valueOf(rs.getDouble("N_REM_COR")));
				// 市场
				fiHistoryPay.setC_MKT_CODE(rs.getString("C_MKT_CODE"));
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			logger.log("初始化债券每百元：获取一只债券历史付息数据出错", e);
			throw e;
		} finally {
			dao.closeResultSetFinal(rs);
			dao.closeStatementFinal(pst);
			dao.releaseConnection(conn);
		}
		return fiHistoryPay;
	}
	
	/**
	 * 获取一只债券历史付息数据<br>
	 * 注： 此方法必须与getFiPayFactorsWhereMarkDateInPayPeriod()方法连用
	 * @param secCode
	 * @return
	 * @throws Exception
	 */
	public HashMap<String,FiPay> getUniqueHistoryPay(Date date) throws SQLException {
		HashMap<String,FiPay> mapFi = new HashMap<String,FiPay>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		StringBuffer buff = new StringBuffer();
		String[] arrSec = getSec();
		buff.append(" SELECT C_SEC_CODE,D_ADJ,D_BEGIN, D_END,N_COUP_RATE,N_REM_COR, ");
		buff.append(" C_MKT_CODE FROM T_D_SV_FI_PAY WHERE N_CHECK_STATE = 1 ");
		buff.append(" AND D_BEGIN <= ? AND D_END >= ? AND C_SEC_CODE IN (SELECT * FROM TABLE(?)) ");
		if (null == arrSec || null == date)
			return mapFi;
		try {
			conn = dao.loadNewConnection();
			pst = conn.prepareStatement(buff.toString());
			pst.setDate(1, YssFun.toSqlDate(date));
			pst.setDate(2, YssFun.toSqlDate(date));
			//pst.setString(3, secCode);
			pst.setArray(3, OraDbTool.newInstance().sqlOverLongCondition(arrSec,conn));
			rs = pst.executeQuery();
			while (rs.next()) {
				FiPay fiHistoryPay = new FiPay();
				// 证券代码
				fiHistoryPay.setC_SEC_CODE(rs.getString("C_SEC_CODE"));
				// 调息日期
				fiHistoryPay.setD_ADJ(rs.getDate("D_ADJ").toString());
				// 本期起息日
				fiHistoryPay.setD_BEGIN(rs.getDate("D_BEGIN").toString());
				// 本期截息日
				fiHistoryPay.setD_END(rs.getDate("D_END").toString());
				// 票面利率
				fiHistoryPay.setN_COUP_RATE(BigDecimal.valueOf(rs.getDouble("N_COUP_RATE")));
				// 剩余本金
				fiHistoryPay.setN_REM_COR(BigDecimal.valueOf(rs.getDouble("N_REM_COR")));
				// 市场
				fiHistoryPay.setC_MKT_CODE(rs.getString("C_MKT_CODE"));
				mapFi.put(fiHistoryPay.getC_SEC_CODE(),fiHistoryPay);
			}
		} catch (Exception e) {
//			e.printStackTrace();
			logger.log("初始化债券每百元：获取一只债券历史付息数据出错", e);
			throw new SQLException(e);
		} finally {
			dao.closeResultSetFinal(rs);
			dao.closeStatementFinal(pst);
			dao.releaseConnection(conn);
		}
		return mapFi;
	}
	
	/**
	 * 获取债券理财基本信息
	 * @param secCode
	 * @return
	 * @throws SQLException 
	 * @throws YssException 
	 */
	public SecBase getFiInfo(String secCode) throws YssException{
		SecBase secInfo = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		
		try {
			conn = dao.loadNewConnection();
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT A.*,B.C_DA_CODE FROM T_P_SV_SEC_BASE A JOIN ");
			buff.append(" (SELECT C_SEC_VAR_CODE ,C_DA_CODE FROM V_S_DA_SEC_VAR ) B ");
			buff.append(" ON A.C_SEC_VAR_CODE = B.C_SEC_VAR_CODE ");
			buff.append(" WHERE A.C_SEC_CODE = ? AND A.N_CHECK_STATE = 1 AND (B.C_DA_CODE LIKE 'ZQ%' ");
			buff.append(" OR B.C_DA_CODE LIKE 'LC%' OR B.C_DA_CODE LIKE 'JJ%' )");
			pst = conn.prepareStatement(buff.toString());
			pst.setString(1, secCode);
			rs = pst.executeQuery();
			while(rs.next()){
//				secInfo = new BEN_SEC_BASE();
//				secInfo.parseRsToAttr(rs);
				//chenbo
				secInfo = SecurityHandler.getInstance().toBean(rs);
			}
		} catch (Exception e) {
//			e.printStackTrace();
			logger.log("初始化债券每百元：获取债券理财基本信息出错", e);
			throw new YssException(e);
		} finally{
			dao.closeResultSetFinal(rs);
			dao.closeStatementFinal(pst);
			dao.releaseConnection(conn);
		}
		return secInfo;
	}
	
	/**
	 * 获取债券理财基本信息<br>
	 * 注：此方法必须与getFiPayFactorsWhereMarkDateInPayPeriod()方法连用
	 * @return key:证券代码,证券信息
	 * @throws SQLException 
	 * @throws YssException 
	 */
	public HashMap<String,SecBase> getFiInfo() throws YssException{
		HashMap<String,SecBase> mapSec = new HashMap<String,SecBase>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;		
		try {
			String[] arrSec = getSec();
			if(arrSec == null){
				return mapSec;
			}
			conn = dao.loadNewConnection();
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT A.*,B.C_DA_CODE FROM T_P_SV_SEC_BASE A JOIN ");
			buff.append(" (SELECT C_SEC_VAR_CODE ,C_DA_CODE FROM V_S_DA_SEC_VAR ) B ");
			buff.append(" ON A.C_SEC_VAR_CODE = B.C_SEC_VAR_CODE ");
			// editbyliyongjun 2016-07-22  STORY18596优先股业务  初始化优先股权益信息
			buff.append(" LEFT JOIN (select C_SEC_CODE,N_Check_State,c_DV_ACCOUNT_CODE from T_D_MP_PRE_STOCK WHERE N_Check_State = 1) PRE");
			buff.append(" ON PRE.C_SEC_CODE = A.C_SEC_CODE ");
			buff.append(" WHERE A.C_SEC_CODE IN(SELECT * FROM TABLE(?)) AND A.N_CHECK_STATE = 1 AND (B.C_DA_CODE LIKE 'ZQ%' ");
			buff.append(" OR B.C_DA_CODE LIKE 'LC%' OR B.C_DA_CODE LIKE 'JJ%' OR (B.C_DA_CODE LIKE '%YXG%' AND PRE.c_DV_ACCOUNT_CODE = 'HSLX_JRFZ') )");
			pst = conn.prepareStatement(buff.toString());
			//pst.setString(1, secCode);
			pst.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(arrSec,conn));
			rs = pst.executeQuery();
			while(rs.next()){
//				secInfo = new BEN_SEC_BASE();
//				secInfo.parseRsToAttr(rs);
				SecBase secInfo = SecurityHandler.getInstance().toBean(rs);
				mapSec.put(secInfo.getC_SEC_CODE(), secInfo);
			}
		} catch (Exception e) {
//			e.printStackTrace();
			logger.log("初始化债券每百元：获取债券理财基本信息出错", e);
			throw new YssException(e);
		} finally{
			dao.closeResultSetFinal(rs);
			dao.closeStatementFinal(pst);
			dao.releaseConnection(conn);
		}
		return mapSec;
	}
	
//	/**
//	 * 通过证券内码得到截息日规则，即截息日遇节假日是否顺延
//	 * STORY #14638 针对特殊债券，调整债券历史付息中计息起始日、截止日  --liuchi/2014.2.12
//	 */
//	private String getGZBySEC(String c_sec_code ,Connection conn) throws YssException{
//		PreparedStatement pst = null;
//		ResultSet rs = null;
//		String sql = "";
//		String JXRGZ = ""; //截息日规则
//		try {
//			sql = "select C_DV_ASSURE from t_p_sv_sec_base where c_sec_code = ?";
//			pst = conn.prepareStatement(sql);
//			pst.setString(1, c_sec_code);
//			rs = pst.executeQuery();
//			if(rs.next()){
//				JXRGZ = rs.getString(1);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new YssException(e);
//		} finally{
//			dao.closeResultSetFinal(rs);
//			dao.closeStatementFinal(pst);
//		}
//		return JXRGZ;
//	}
//	
	/**
	 * 获取下一个计息区间的起息日，不考虑节假日
	 * @param requency 付息频率
	 * @param curBeginRateDay 当前计息区间起息日
	 * @param D_start 债券的起息日
	 * @author liuchi/2014.2.12
	 */
	private Date getNextBegin(String frequency,Date curBeginRateDay,Date D_start){
		Date d_next = D_start;
		while(!d_next.after(curBeginRateDay)){
			if (VocabularyConsts.FI_1M.equalsIgnoreCase(frequency)) {
				d_next = YssFun.addMonth(d_next, 1);
			}
			// 如果付息频率为三个月,截息日 = 起息日+三个月
			if (VocabularyConsts.FI_3M.equalsIgnoreCase(frequency)) {
				d_next = YssFun.addMonth(d_next, 3);
			}
			// 如果付息频率为半年,截息日 = 起息日+六个月
			if (VocabularyConsts.FI_HALF_Y.equalsIgnoreCase(frequency)) {
				d_next = YssFun.addMonth(d_next, 6);
			}
			// 如果付息频率为一年,截息日 = 起息日+一年
			if (VocabularyConsts.FI_1Y.equalsIgnoreCase(frequency)) {
				d_next = YssFun.addYear(d_next, 1);
			}
		}
		return d_next;
	}

	/**
	 * 查询优先股中计息起始日期和计息截息日期包含基准日期的债券，并构造为付息因子
	 * addbyliyongjun 2015-12-23 STORY18596优先股业务
	 * @return
	 * @throws Exception
	 */
	public ArrayList<FiPayFactorBean> getFiPayFactorsWhereMarkDateInPayPeriodYxg()
			throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<FiPayFactorBean> fiPayFactors = null;
		
		String sql = "select C.C_SEC_CODE,D.C_DV_QUT_MOD,D.N_FV_ISSUE,D.N_PRICE_ISSUE,C.N_RATE,C.C_DV_AI_MOD,C.C_DV_AI_EXPR,c.C_MKT_CODE,C.C_DV_PI_MOD,C.D_AI_BEGIN,"
				+"C.D_AI_END,C.C_DV_VAR_DUR,D.C_DV_ASSURE,C.N_FV_IR from T_D_MP_PRE_STOCK C  "
				+" JOIN T_P_SV_SEC_BASE D ON C.C_SEC_CODE = D.C_SEC_CODE WHERE C.N_CHECK_STATE = 1 and D.C_SEC_VAR_CODE like 'GP%' "
				+" and c.c_DV_ACCOUNT_CODE = 'HSLX_JRFZ' and c.D_AI_BEGIN <= ? AND c.D_AI_END >= ? ";
		Connection conn = null;
		try {
			conn = dao.loadNewConnection();
			pst = dao.openPreparedStatement(sql, conn);
			pst.setDate(1, YssFun.toSqlDate(markDate));
			pst.setDate(2, YssFun.toSqlDate(markDate));
			rs = pst.executeQuery();
			while (rs.next()) {
				if (null == fiPayFactors) {
					fiPayFactors = new ArrayList<FiPayFactorBean>();
				}
				FiPayFactorBean fiPayFactor = new FiPayFactorBean();
				fiPayFactor.setSecCode(rs.getString("C_SEC_CODE"));
				fiPayFactor.setMarket(rs.getString("C_MKT_CODE"));
				fiPayFactor.setIssueFaceValue(Double.parseDouble(rs.getString("N_FV_ISSUE")));
				fiPayFactor.setCouponRate(rs.getDouble("N_FV_IR"));
				fiPayFactor.setPayFrequency(rs.getString("C_DV_VAR_DUR"));
				fiPayFactor.setBondBeginDate(rs.getDate("D_AI_BEGIN"));
				fiPayFactor.setBondEndDate(rs.getDate("D_AI_END"));
				fiPayFactor.setC_DV_ASSURE(rs.getString("C_DV_ASSURE"));
				//chenbo 理财起息日报空指针，先做暂时的处理。
				fiPayFactor.setLicaiBeginDate(rs.getDate("D_AI_BEGIN"));
				if(null != fiPayFactor){
					fiPayFactors.add(fiPayFactor);
				}
				lstSec.add(fiPayFactor.getSecCode());
			}
			if (null != fiPayFactors)
				fiPayFactors.trimToSize();
		} catch (SQLException e) {
//			e.printStackTrace();
			throw (Exception) e;
		} finally {
			dao.closeResultSetFinal(rs);
			dao.closeStatementFinal(pst);
			dao.releaseConnection(conn);
		}
		return fiPayFactors;
	}

	/**
	 * add by liyanjun 2016-3-4 BUG #127263 债券历史付息信息更新有误
	 * 查询出所有数据来源不是“库存”的债券历史付息的调息日期，HashMap<证券代码,List<调息日期>>，日期格式统一为yyyy-MM-dd
	 * @param secCode
	 * @return
	 * @throws YssException 
	 * @throws Exception
	 */
	public static HashMap<String,List<String>> getAllHistoryPayNotS(Connection conn, List<String> secCodes) throws Exception {
		HashMap<String,List<String>> hisInterestMap = new HashMap<String,List<String>>();
		List<String> hisInterest = new ArrayList<String>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "SELECT C_SEC_CODE, TO_CHAR(D_ADJ,'yyyy-MM-dd') AS D_ADJ FROM T_D_SV_FI_PAY WHERE C_DATA_IDF != 'S' AND C_SEC_CODE IN(SELECT * FROM TABLE(?)) ";
		if(null == secCodes){
			return null;
		}
		try {
			pst = conn.prepareStatement(sql);
			pst.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(secCodes.toArray(new String[]{})));
			rs = pst.executeQuery();
			while (rs.next()) {
				// 证券代码
				String sec = rs.getString("C_SEC_CODE");
				if(hisInterestMap.containsKey(sec)){
					hisInterest = hisInterestMap.get(sec);
					hisInterest.add(rs.getString("D_ADJ"));
				}else{
					hisInterest = new ArrayList<String>();
					hisInterest.add(rs.getString("D_ADJ"));
					hisInterestMap.put(sec, hisInterest);
				}
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			throw e;
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
		}

		return hisInterestMap;
	}
	
	/**
	 * 获取一批债券所有历史付息数据
	 * 
	 * @param secCodes 债券代码
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, List<FiPay>> getBatchHistoryPay(String secCodes, Connection conn) throws Exception {
		HashMap<String, List<FiPay>> fiPayMap = new HashMap<String, List<FiPay>>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		StringBuffer buff = new StringBuffer();
		buff.append(" SELECT C_SEC_CODE,D_ADJ,D_BEGIN, D_END,N_COUP_RATE,N_REM_COR, ");
		buff.append(" C_MKT_CODE FROM T_D_SV_FI_PAY WHERE N_CHECK_STATE = 1 ");
		buff.append(" AND C_SEC_CODE IN (SELECT * FROM TABLE(?)) ");
		try {
			pst = conn.prepareStatement(buff.toString());
			pst.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(secCodes,conn));
			rs = pst.executeQuery();
			while (rs.next()) {
				FiPay fiHistoryPay = new FiPay();
				// 证券代码
				fiHistoryPay.setC_SEC_CODE(rs.getString("C_SEC_CODE"));
				// 调息日期
				fiHistoryPay.setD_ADJ(rs.getDate("D_ADJ").toString());
				// 本期起息日
				fiHistoryPay.setD_BEGIN(rs.getDate("D_BEGIN").toString());
				// 本期截息日
				fiHistoryPay.setD_END(rs.getDate("D_END").toString());
				// 票面利率
				fiHistoryPay.setN_COUP_RATE(BigDecimal.valueOf(rs.getDouble("N_COUP_RATE")));
				// 剩余本金
				fiHistoryPay.setN_REM_COR(BigDecimal.valueOf(rs.getDouble("N_REM_COR")));
				// 市场
				fiHistoryPay.setC_MKT_CODE(rs.getString("C_MKT_CODE"));
				if (fiPayMap.containsKey(fiHistoryPay.getC_SEC_CODE())) {
					List<FiPay> list = fiPayMap.get(fiHistoryPay.getC_SEC_CODE());
					list.add(fiHistoryPay);
					fiPayMap.put(fiHistoryPay.getC_SEC_CODE(), list);
				}else {
					List<FiPay> list = new ArrayList<FiPay>();
					list.add(fiHistoryPay);
					fiPayMap.put(fiHistoryPay.getC_SEC_CODE(), list);
				}
			}
		} catch (Exception e) {
//			e.printStackTrace();
			logger.log("初始化债券每百元：批量获取债券历史付息数据出错", e);
			throw e;
		} finally {
			//chenbo
//			DBUtils.cleanUp(rs, pst);
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
		}
		return fiPayMap;
	}
	
	/**
	 * 获取一组债券截息日等于参数的历史付息数据
	 * 用于核算产生兑付数据时计算成交金额使用
	 * STORY29319股票、债券、基金派息，债券还本、兑付优化
	 * xiaozhilong 20160524
	 * @param conn 数据库连接
	 * @param secCodes 证券代码
	 * @param endDate 截息日
	 * @return
	 * @throws Exception
	 */
	public static HashMap<String,FiPay> getHistoryPay(Connection conn, String secCodes, Date endDate)
			throws Exception {
		HashMap<String,FiPay> fiPayMap = new HashMap<String,FiPay>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		if(StringUtil.IsNullOrEmptyT(secCodes)){
			return fiPayMap;
		}
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT A.C_SEC_CODE, A.D_BEGIN, A.D_END, A.N_COUP_RATE, A.N_REM_COR ");
		sqlBuffer.append(" FROM T_D_SV_FI_PAY A ");
		sqlBuffer.append(" JOIN TABLE(?) C  ON A.C_SEC_CODE = C.COLUMN_VALUE");
		sqlBuffer.append(" WHERE A.D_END = ? AND A.N_CHECK_STATE = 1 ");
		String sql = sqlBuffer.toString();
		try {
			pst = conn.prepareStatement(sql);
			pst.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(secCodes, conn));
			pst.setDate(2, YssFun.toSqlDate(endDate));
			rs = pst.executeQuery();
			while (rs.next()) {
				FiPay fiHistoryPay = new FiPay();
				// 证券代码
				fiHistoryPay.setC_SEC_CODE(rs.getString("C_SEC_CODE"));
				// 本期起息日
				fiHistoryPay.setD_BEGIN(rs.getDate("D_BEGIN").toString());
				// 本期截息日
				fiHistoryPay.setD_END(rs.getDate("D_END").toString());
				// 票面利率
				fiHistoryPay.setN_COUP_RATE(YssFun.toDecimal(rs.getString("N_COUP_RATE")));
				// 剩余本金
				fiHistoryPay.setN_REM_COR(YssFun.toDecimal(rs.getString("N_REM_COR")));
				fiPayMap.put(fiHistoryPay.getC_SEC_CODE(), fiHistoryPay);
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			throw e;
		} finally {
			//chenbo
//			DBUtils.cleanUp(rs, pst); 
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
		}
		return fiPayMap;
	}

}
