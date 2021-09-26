package com.yss.ams.sec.information.modules.pub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import org.wltea.expression.ExpressionEvaluator;
import org.wltea.expression.PreparedExpression;
import org.wltea.expression.datameta.Variable;

import com.yss.ams.sec.information.activator.SecInfoActivator;
import com.yss.ams.sec.information.consts.ParamsConsts;
import com.yss.ams.sec.information.modules.pub.dao.ActHelp;
import com.yss.ams.sec.information.modules.pub.dao.AdmFactRate;
import com.yss.ams.sec.information.modules.pub.dao.FiHundFactorService;
import com.yss.ams.sec.information.support.cache.SecBaseCache;
import com.yss.ams.sec.information.support.modules.pub.pojo.FiHundIntFactorBean;
import com.yss.ams.sec.information.support.modules.pub.pojo.FiPerHundredInterestBean;
import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecBase;
//import com.yss.bundle.activator.UcoActivator;
//import com.yss.cache.MktCache;
//import com.yss.cache.SecBaseCache;
//import com.yss.dayf.act.consts.ParamsConsts;
//import com.yss.dayf.act.consts.VocabularyConsts;
//import com.yss.dayf.act.process.ActHelp;
//import com.yss.dayf.actProvider.dbUtils.DBUtils;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.co.BaseBean;
import com.yss.framework.api.common.co.Mkt;
import com.yss.ams.base.information.support.bi.mkt.service.IMktDataService;
import com.yss.ams.base.information.support.cache.MktCache;
import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.exception.YssException;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.util.ReflectionUtil;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.api.util.VocabularyConsts;
import com.yss.framework.api.util.YssD;
import com.yss.framework.api.util.YssFun;
import com.yss.framework.context.ContextFactory;
import com.yss.framework.db.OraDbTool;
//import com.yss.pub.func.fi.pojo.FiHundIntFactorBean;
//import com.yss.pub.func.fi.pojo.FiPerHundredInterestBean;
//import com.yss.uco.clear.api.IBaseServiceFactory;
//import com.yss.uco.clear.api.ServiceFactoryUtil;
//import com.yss.uco.clear.api.param.aoparam.service.IAoParamFactory;
//import com.yss.uco.clear.api.param.aoparam.service.IAoParamService;

//import dataservice.comm.pojo.SecBase;
//import dataservice.service.IMktDataService;

/**
 * chenbo 2017-08-19 TASK #332232 
 * 公共信息处理界面中的系统初始化下的接口拆分到资讯组件中，以提供每百元利息生成功能
 * 
 */
/**
 * @classDesc 债券每百元利息处理类
 * @version 1.0 2011-12-9
 * @author yh
 */
public class AdmFiPerHundredInterest extends BaseBean {
	
	private GeneralDao dao = null;
	/**
	 * 构造方法
	 * 
	 * @param AdmFiPerHundredInterest
	 */
	public AdmFiPerHundredInterest() {

		try {
			dao = new GeneralDao(YssDbPoolFactory.getInstance().getDbPool(SecInfoActivator.class), null);
		} catch (Exception e) {
			//e.printStackTrace();
			logger.error(e.getMessage());
		}
	}
	
	/**
	 * @return the Dao
	 */
	public GeneralDao getDao() {
		return dao;
	}

	/**
	 * @param dao the dao to set
	 */
	public void setDao(GeneralDao dao) {
		this.dao = dao;
	}

	/**
	 * 增加序列 by lihaizhi 20130620
	 */
	private static final long serialVersionUID = 8044295886175511209L;
	// =====为不重复解析,把已经解析好的表达式相关信息缓存下来
	private HashMap<String, List<String>> exprFields = new HashMap<String, List<String>>();
	private HashMap<String, List<Variable>> exprVariables = new HashMap<String, List<Variable>>();
	private HashMap<String, PreparedExpression> exprPe = new HashMap<String, PreparedExpression>();
	HashMap<String, String> PubParams = new HashMap<String, String>();
	private Logger logger = LogManager.getLogger(AdmFiPerHundredInterest.class);

	/**
	 * 获取清算公共的设置参数
	 * 
	 * @param c_Dsp_Code
	 *            参数代码
	 * @return 设置的参数值
	 */
	public String getPubParams(String c_Dsp_Code, Date date) throws Exception {
//		BUG #134663 findbugs代码检查和PMD数据库连接检查问题修复  edit by 20160718
//		PubParams在定义时已经实例化，因此无需做控制判断
//		if (PubParams == null || PubParams.get(c_Dsp_Code)==null) {
		//chenbo
//		if (PubParams.get(c_Dsp_Code)==null) {
//			IBaseServiceFactory serviceFactory = (IBaseServiceFactory)ServiceFactoryUtil.getServiceFactory(IAoParamFactory.class);
//			IAoParamService aoParam = (IAoParamService)serviceFactory.getNewInstance();
//			String lcblwsValue = aoParam.getPubParams(c_Dsp_Code,date);
//			// editby liyonjun 2016-07-22 BUG #135042 【公共信息处理】【资产数据清算】接口清算失败
//			if (lcblwsValue == null || "".equals(lcblwsValue)){
//				lcblwsValue = "12";
//			}
//			PubParams.put(c_Dsp_Code, lcblwsValue);
//		}
		return PubParams.get(c_Dsp_Code);
	}
	/**
	 * 计算一组债券在特定计息日期的每百元利息
	 * 
	 * @param secCodeList
	 *            证券代码列表
	 * @param accrualDate
	 *            每百元利息日期
	 * @return
	 * @throws Exception
	 */
	public List<FiPerHundredInterestBean> calcPerHundredInterest(
			List<String> secCodeList, Date accrualDate) throws Exception {
		List<FiPerHundredInterestBean> interestBeanList = new ArrayList<FiPerHundredInterestBean>();
		FiHundFactorService factorService = new FiHundFactorService();
		HashMap<String, List<FiHundIntFactorBean>> factorMap = factorService
				.queryFiHundIntFactors(secCodeList, accrualDate);
		HashMap<String, String> accrualExpressions = getHundIntExpressions();
		if (null == factorMap || null == accrualExpressions) {
			return null;
		}
		for (String secCode : secCodeList) {
			FiPerHundredInterestBean interestBean = calcPerHunderedInterest(
					secCode, accrualDate, factorMap.get(secCode),
					accrualExpressions);
			if (null != interestBean) {
				interestBeanList.add(interestBean);
			}
		}
		return interestBeanList;

	}

	/**
	 * 计算一只债券的每百元利息
	 * 
	 * @param bondCode
	 *            债券代码
	 * @param accrualDate
	 *            每百元利息日期
	 * @param factors
	 *            计算因子
	 * @param accrualExpressions
	 *            计算公式
	 * @return
	 * @throws Exception
	 */
	public FiPerHundredInterestBean calcPerHunderedInterest(String bondCode,
			Date accrualDate, List<FiHundIntFactorBean> factors,
			HashMap<String, String> accrualExpressions) throws Exception {
		if (null == factors || factors.isEmpty())
			return null;
		// 如果查询出的历史付息数据数量>0,分别按照单调息日期和多调息日期两种方式来处理
		FiPerHundredInterestBean hundredInterestBean = null;
		if (factors.size() == 1) {
			FiHundIntFactorBean oneFactor = factors.get(0);
			hundredInterestBean = this.singleAdjustOper(accrualDate, oneFactor,
					accrualExpressions);
		} else if (factors.size() > 1) {
			hundredInterestBean = this.multipleAdjustOper(accrualDate, factors,
					accrualExpressions);
		}
		return hundredInterestBean;

	}

	/**
	 * 计算债券每百元利息
	 * 
	 * @param bondCode
	 *            债券代码
	 * @param accrualDate
	 *            每百元日期
	 * @throws Exception
	 */
	/*
	 * public FiPerHundredInterestBean generatePerHunderedInterest( String
	 * bondCode, Date accrualDate) throws Exception { List<FiHundIntFactorBean>
	 * factors = null; HashMap<String, String> accrualExpressions = null; //
	 * 首先查询需要计算每百元债券利息的债券计息信息 factors = queryFiHundIntFactors(bondCode,
	 * accrualDate); if (null == factors || factors.isEmpty()) return null; //
	 * 查询债券每百元利息计息公式 accrualExpressions = this.getHundIntExpressions(); //
	 * 如果查询出的历史付息数据数量>0,分别按照单调息日期和多调息日期两种方式来处理 FiPerHundredInterestBean
	 * hundredInterestBean = null; if (factors.size() == 1) {
	 * FiHundIntFactorBean oneFactor = factors.get(0); hundredInterestBean =
	 * this.singleAdjustOper(accrualDate, oneFactor, accrualExpressions); } else
	 * if (factors.size() > 1) { hundredInterestBean =
	 * this.multipleAdjustOper(accrualDate, factors, accrualExpressions); }
	 * return hundredInterestBean;
	 * 
	 * }
	 */

	/**
	 * 计算债券每百元利息
	 * 
	 * @param bondCode
	 *            债券代码
	 * @param beginDate
	 *            每百元日期起始日
	 * @param endDate
	 *            每百元日期截止日
	 * @throws Exception
	 */
	/*
	 * public List<FiPerHundredInterestBean> generatePerHunderedInterest( String
	 * bondCode, Date beginDate, Date endDate) throws Exception {
	 * List<FiPerHundredInterestBean> hundredInterestBeans = null; for (int i =
	 * 0; i <= YssFun.dateDiff(beginDate, endDate); i++) { // 计算每一天,计息日期递增
	 * FiPerHundredInterestBean oneDayInterest = generatePerHunderedInterest(
	 * bondCode, YssFun.addDay(beginDate, i)); if (null == hundredInterestBeans)
	 * { hundredInterestBeans = new ArrayList<FiPerHundredInterestBean>(); } if
	 * (null != oneDayInterest) { hundredInterestBeans.add(oneDayInterest); } }
	 * return hundredInterestBeans; }
	 */

	/**
	 * 单调息日期债券利息处理
	 * 
	 * @param oneFactor
	 *            计算因子
	 * @param accrualExpressions
	 *            百元利息公式信息
	 * @param accrualDate
	 *            计息日期
	 * @return
	 * @throws Exception
	 */
	private FiPerHundredInterestBean singleAdjustOper(Date accrualDate,
			FiHundIntFactorBean oneFactor,
			HashMap<String, String> accrualExpressions) throws Exception {
		FiPerHundredInterestBean hundIntPojo = null;
		double beforeTax = 0; // 税前利息
		double afterTax = 0; // 税后利息
		double dueBeforeTax = 0; // 到期税前利息
		double dueAfterTax = 0; // 到期税后利息
		int point = 8;// 保留位数
		
		hundIntPojo = new FiPerHundredInterestBean();
		hundIntPojo.setSecCode(oneFactor.getSecCode());// 证券代码
		hundIntPojo.setAccrualDate(accrualDate);// 计息日期
		hundIntPojo.setQuoteMode(oneFactor.getQuoteMode());// 报价方式

		/**
		 * zhengguiyu BUG #80394 债券每天利息的计提天数计算有问题，要根据计息方式来计算
		 * 单独计算计息方式为FI_30_360的计提天数 修改内容：如果计息方式为FI_A_365F，那么要判断这个时间段内有几个闰二月
		 * 有几个闰二月就要在这个时间算出的计息天数中减去几天 修改人：zhengguiyu 修改时间：2014-4-11
		 */
		this.getFiPerHundredInterestDays(oneFactor, hundIntPojo, accrualDate);
		hundIntPojo.setCouponRate(oneFactor.getCouponRate());// 票面利率
		hundIntPojo.setRemainMoney(oneFactor.getRemainMoney());// 剩余本金
		// 一级市场保留12位小数
		/*
		 * IMktDataService mktDataService = YssServiceFactory.getInstance()
		 * .createService(IMktDataService.class); Mkt mkt = (Mkt)
		 * mktDataService.getDataByCode(oneFactor.getMarket());
		 */
		//从缓存中取证券基本信息  modify by yewenke 2015-11-16
		SecBaseCache secCache = CacheManager.getInstance().getCache(
				CacheGroup.SECBASE);
		SecBase secInfo = secCache.getCacheByKey(oneFactor.getSecCode());
		MktCache mktCache = CacheManager.getInstance().getCache(CacheGroup.MKT);
		Mkt mkt = mktCache.getCacheByKey(oneFactor.getMarket());
		//BUG #121984 汇添富基金，资本子公司理财产品，生成的每日利息保留12位，在金额较大的情况下，每日计提利息会有1分钱差异，请在生成利息时增至15位（后台表字段类型为NUM(30,15)）。  modify by yewenke 2015-11-16
		// edit by chenyoulong if判断，常量用该放在前，否则当get出来的值为null,回报空指针异常 20160618
		if (mkt != null && VocabularyConsts.OTC.equalsIgnoreCase(mkt.getC_DV_MKT_TYPE()) && secInfo.getC_SEC_VAR_CODE().indexOf("LC")==0) {
			//BUG #132478 【非常紧急】【钜盛华】新增理财品种生成每日利息和历史付息报错
			point =  YssFun.toInt(getPubParams(ParamsConsts.CO_LCMBYLXBLWS,accrualDate));
		}else if(mkt != null && VocabularyConsts.OTC.equalsIgnoreCase(mkt.getC_DV_MKT_TYPE()) && secInfo.getC_SEC_VAR_CODE().indexOf("LC")!=0){
			point = 12;
		}
		//STORY #38323 loft基金，每百元息保留位数可以设置  tianpeng
		if (mkt != null && secInfo.getC_SEC_VAR_CODE().endsWith("LOF")) {
			point =  Integer.parseInt(getPubParams(ParamsConsts.CO_LOFMBYLXBLWS,accrualDate));
		}
		// 二级市场保留8位小数
		else if (mkt != null
				&& mkt.getC_DV_MKT_TYPE()
						.equalsIgnoreCase(VocabularyConsts.FTM)) {
			point = 8;
		}
		/*STORY24914【紧急】贴现债选择计息方式计息错误 --LIUCHI/2015-8-11
		  如果计息方式为贴现 and 票面利率为空，then 计算：票面利率=（发行面值-发行价格）/发行面值*100*/
		// 贴现债
		//如果计息方式为贴现 and 票面利率为空
		//if (VocabularyConsts.FI_DC.equalsIgnoreCase(oneFactor.getAccrualMode())&& oneFactor.getCouponRate() == 0) 
		if (VocabularyConsts.FI_DC.equalsIgnoreCase(oneFactor.getAccrualMode())) {
			// 计息公式：Round((发行面值-发行价格)/(债券截息日-债券起息日+1)*(计息日期-起息日+1),保留位数)
			beforeTax = YssD.round(
					(oneFactor.getIssueFaceValue() - oneFactor.getIssuePrice())
							/ (YssFun.dateDiff(oneFactor.getBondBeginDate(),
									oneFactor.getBondEndDate()) + 1)
							* (YssFun.dateDiff(
									oneFactor.getCurPeriodBeginDate(),
									accrualDate) + 1), point);
			// BUG162647【嘉实基金21.5】中登清算明细接口新；清算后债券交易业务的应缴税费没有出来
			// 由于“BUG151776贴现债券每百元计息与计息公式和付息公式无关”未考虑贴现债维护税率的情况下，税后每百元要通过税率重新计算
			afterTax = beforeTax * (1 - oneFactor.getTaxRate());
			dueBeforeTax = beforeTax;
			dueAfterTax = afterTax;
			//oneFactor.setCouponRate((oneFactor.getIssueFaceValue() - oneFactor.getIssuePrice())/oneFactor.getIssueFaceValue()*100);
			//hundIntPojo.setCouponRate(oneFactor.getCouponRate());// 票面利率
			hundIntPojo.setBeforeTaxInterest(beforeTax);
			hundIntPojo.setAfterTaxInterest(afterTax);
			hundIntPojo.setBeforeTaxInterestDue(dueBeforeTax);
			hundIntPojo.setAfterTaxInterestDue(dueAfterTax);

			return hundIntPojo;
		} 

			double[] interest = null;
			//当计息公式为FI_30_360时 付息日期传入参数不同
			//BUG149454【钜盛华】【紧急】公共信息处理那里有10多个接口清算失败    空指针异常处理  by guohui 20170105
			if("FI_30_360".equals(oneFactor.getAccrualFormula()) && YssFun.addDay(accrualDate,1).getDate()!=31) {
					// add by yh 2015.1.23 30/360的算法表达式中，已经考虑了计息天数的逻辑，因此传入的计息日期是不需要做调整的
					//30日不计息 31日计息
					interest = this.calcInterest(oneFactor, accrualExpressions
							.get(oneFactor.getAccrualFormula()), accrualDate, oneFactor
							.getCurPeriodBeginDate());
			}else if(VocabularyConsts.JXRGC.equalsIgnoreCase(oneFactor.getBackWard())){
				//Edit by liuxiang 2013/12/26 STORY #14457 理财品种信息增加一种正推的计息模式
				//根据倒置算法中新增“计息到期日轧差“出每日每百元，每日每百元值都一样，计息天数不同
				interest = this.calcInterest(oneFactor, accrualExpressions
						.get(oneFactor.getAccrualFormula()), accrualDate, accrualDate);
			}else if(VocabularyConsts.DQRGC.equalsIgnoreCase(oneFactor.getBackWard())){
				//add by dingsahlu  2016/02/05 BUG #126222 理财产品计息方式错误
				//倒置算法: “到期日轧差“  每日每百元利息值都一样
				interest = this.calcInterest(oneFactor, accrualExpressions
						.get(oneFactor.getAccrualFormula()), accrualDate, accrualDate);
			}else {
				interest = this.calcInterest(oneFactor, accrualExpressions
						.get(oneFactor.getAccrualFormula()), accrualDate, oneFactor
						.getCurPeriodBeginDate());
			}
			
			//// 期内还本
			if(oneFactor.getIsQnhb() == 1){
				FiHundIntFactorBean lastFiHund = new FiHundIntFactorBean(); // 上个付息区间
				lastFiHund = getLastFiHund(oneFactor.getSecCode(), oneFactor.getAdjustDate()); 
				double remainMoney = oneFactor.getRemainMoney();
				if(lastFiHund != null){
					oneFactor.setRemainMoney(lastFiHund.getRemainMoney());
					hundIntPojo.setRemainMoney(lastFiHund.getRemainMoney());
				}else{
					oneFactor.setRemainMoney(oneFactor.getIssueFaceValue());
					hundIntPojo.setRemainMoney(oneFactor.getIssueFaceValue());
				}
				if(YssFun.dateDiff(accrualDate, oneFactor.getQnhbDate()) > 0){
					interest = this.calcInterest(oneFactor, accrualExpressions
							.get(oneFactor.getAccrualFormula()), accrualDate, oneFactor
							.getCurPeriodBeginDate());
					oneFactor.setRemainMoney(remainMoney);
				}else{
					double[] interest1 = null;
					double[] interest2 = null;
					//第一段计算
					interest1 = this.calcInterest(oneFactor, accrualExpressions
						.get(oneFactor.getAccrualFormula()), YssFun.addDay(oneFactor.getQnhbDate(),-1) , oneFactor
						.getCurPeriodBeginDate());
					oneFactor.setRemainMoney(remainMoney);
					hundIntPojo.setRemainMoney(remainMoney);
					//第二段计算
					interest2 = this.calcInterest(oneFactor, accrualExpressions
						.get(oneFactor.getAccrualFormula()), accrualDate, oneFactor.getQnhbDate());
					if(null != interest1 && interest1.length >=2 && null != interest2 && interest2.length >=2){
					interest[0] = interest1[0]+interest2[0];
					interest[1] = interest1[1]+interest2[1];
					}
				}
			}
			
			if(null != interest
					&& interest.length >=2){
				beforeTax = YssD.round(interest[0], point);
				afterTax = YssD.round(interest[1], point);
			}
		
			// 如果计息日期是付息期间截息日,根据付息公式计算
			if (accrualDate.equals(oneFactor.getCurPeriodEndDate())) {
				String accrualFormula = oneFactor.getAccrualFormula();
				// CL 2013-03-14 理财产品收益类型借用了付息公式字段
				if (!"FIXED".equalsIgnoreCase(oneFactor.getPayMode())
						&& !"FLOAT".equalsIgnoreCase(oneFactor.getPayMode())) {
					// 把当前计息公式设置为付息公式
					oneFactor.setAccrualFormula(oneFactor.getPayMode());
					if(oneFactor.getAccrualFormula().equals("FI_30_360") && YssFun.addDay(accrualDate,1).getDate()!=31) {
						// add by yh 2015.1.23 30/360的算法表达式中，已经考虑了计息天数的逻辑，因此传入的计息日期是不需要做调整的
						//30日不计息 31日计息
						interest = this.calcInterest(oneFactor, accrualExpressions
								.get(oneFactor.getAccrualFormula()), accrualDate, oneFactor
								.getCurPeriodBeginDate());
					}else {
						interest = this.calcInterest(oneFactor, accrualExpressions
								.get(oneFactor.getPayMode()), accrualDate,
								oneFactor.getCurPeriodBeginDate());
					}
					oneFactor.setAccrualFormula(accrualFormula);
				}
				if(null != interest
						&& interest.length >=2){
					dueBeforeTax = YssD.round(interest[0], point);
					dueAfterTax = YssD.round(interest[1], point);
				}
			} else {
				if(null != interest
						&& interest.length >=2){
					dueBeforeTax = YssD.round(interest[0], point);
					dueAfterTax = YssD.round(interest[1], point);
				}
			}
		hundIntPojo.setBeforeTaxInterest(beforeTax);
		hundIntPojo.setAfterTaxInterest(afterTax);
		hundIntPojo.setBeforeTaxInterestDue(dueBeforeTax);
		hundIntPojo.setAfterTaxInterestDue(dueAfterTax);

		return hundIntPojo;
	}

	/**
	 * 多调息日期债券利息处理
	 * 
	 * @param factors
	 *            一组计算因子
	 * @param accrualExpressions
	 *            百元利息公式信息
	 * @param accrualDate
	 *            计息日期
	 * @return
	 * @throws Exception
	 */
	private FiPerHundredInterestBean multipleAdjustOper(Date accrualDate,
			List<FiHundIntFactorBean> factors,
			HashMap<String, String> accrualExpressions) throws Exception {
		FiPerHundredInterestBean hundIntPojo = null;
		double beforeTax = 0; // 税前利息
		double afterTax = 0; // 税后利息
		double dueBeforeTax = 0; // 到期税前利息
		double dueAfterTax = 0; // 到期税后利息
		int point = 8;// 保留位数
		// edit by yh 2014.2.12 当一个期间存在多次调息时的每百元利息算法调整
		// 根据调息日期和截止日期，定位计息日期所在的历史付息记录
		FiHundIntFactorBean atPeriodFactor = null;
		int atIndex = 0;
		for (FiHundIntFactorBean eachFactor : factors) {
			int moreThan = YssFun.dateDiff(eachFactor.getAdjustDate(),
					accrualDate);
			int lessThan = YssFun.dateDiff(eachFactor.getCurPeriodEndDate(),
					accrualDate);
			if (moreThan >= 0 && lessThan <= 0) {
				atPeriodFactor = eachFactor;
				atIndex = factors.indexOf(eachFactor);
			}
		}
		// FiHundIntFactorBean maxAdjust = factors.get(0);;
		// hundIntPojo = new FiPerHundredInterestBean();
		// hundIntPojo.setSecCode(maxAdjust.getSecCode());// 证券代码
		// hundIntPojo.setAccrualDate(accrualDate);// 计息日期
		// hundIntPojo.setQuoteMode(maxAdjust.getQuoteMode());// 计息方式
		// hundIntPojo.setInterestDays(YssFun.dateDiff(maxAdjust
		// .getCurPeriodBeginDate(), accrualDate) + 1);// 已计提天数
		// hundIntPojo.setCouponRate(maxAdjust.getCouponRate());// 票面利率
		// hundIntPojo.setRemainMoney(maxAdjust.getRemainMoney());// 剩余本金
		hundIntPojo = new FiPerHundredInterestBean();
		hundIntPojo.setAccrualDate(accrualDate);// 计息日期
		//Fortify 规范代码改造
		if(atPeriodFactor!=null){
		hundIntPojo.setSecCode(atPeriodFactor.getSecCode());// 证券代码
		hundIntPojo.setQuoteMode(atPeriodFactor.getQuoteMode());// 计息方式
		hundIntPojo.setInterestDays(YssFun.dateDiff(
				atPeriodFactor.getCurPeriodBeginDate(), accrualDate) + 1);// 已计提天数
		hundIntPojo.setCouponRate(atPeriodFactor.getCouponRate());// 票面利率
		hundIntPojo.setRemainMoney(atPeriodFactor.getRemainMoney());// 剩余本金

		// 场外交易市场保留12位小数
		IMktDataService mktDataService = YssServiceFactory.getInstance()
				.createService(IMktDataService.class);
		Mkt mkt = (Mkt) mktDataService
				.getDataByCode(atPeriodFactor.getMarket());
		//从缓存中取证券基本信息  modify by yewenke 2015-11-16
		SecBaseCache secCache = CacheManager.getInstance().getCache(CacheGroup.SECBASE);
		SecBase secInfo = secCache.getCacheByKey(atPeriodFactor.getSecCode());
		//BUG #121984 汇添富基金，资本子公司理财产品，生成的每日利息保留12位，在金额较大的情况下，每日计提利息会有1分钱差异，请在生成利息时增至15位（后台表字段类型为NUM(30,15)）。  modify by yewenke 2015-11-16
		if (mkt != null && mkt.getC_DV_MKT_TYPE().equalsIgnoreCase(VocabularyConsts.OTC)&&secInfo.getC_SEC_VAR_CODE().indexOf("LC")==0) {
			point =  Integer.parseInt(getPubParams(ParamsConsts.CO_LCMBYLXBLWS,accrualDate));
		}else if(mkt != null && mkt.getC_DV_MKT_TYPE().equalsIgnoreCase(VocabularyConsts.OTC)&&secInfo.getC_SEC_VAR_CODE().indexOf("LC")!=0){
			point = 12;
		}else if (mkt != null && mkt.getC_DV_MKT_TYPE().equalsIgnoreCase(VocabularyConsts.FTM)) {// 场内交易市场保留8位小数
			point = 8;
		}
		
		//STORY #38323 loft基金，每百元息保留位数可以设置  tianpeng
		if (mkt != null && secInfo.getC_SEC_VAR_CODE().endsWith("LOF")) {
			point =  Integer.parseInt(getPubParams(ParamsConsts.CO_LOFMBYLXBLWS,accrualDate));
		}
		/**
		 * 常量应该放在前边，避免出现空指针异常
		 */
		if (VocabularyConsts.FI_FT.equalsIgnoreCase(
				atPeriodFactor.getAccrualMode()) || "FLOAT".equals(atPeriodFactor.getPayMode())) {
			double[] interest = null;
			if (atPeriodFactor.isApplyNewRate()) {
				interest = this.calcInterest(atPeriodFactor, accrualExpressions
						.get(atPeriodFactor.getAccrualFormula()), accrualDate,
						atPeriodFactor.getCurPeriodBeginDate());
				if(null != interest && interest.length >=2)
				{
					beforeTax += YssD.round(interest[0], point);
					afterTax += YssD.round(interest[1], point);
					dueBeforeTax += YssD.round(interest[0], point);
					dueAfterTax += YssD.round(interest[1], point);
				}
			} else {
				// 如果不启用新利率，从期间起息日开始，按照调息日期分段
				for (FiHundIntFactorBean eachFactor : factors) {
					int periodOrder = factors.indexOf(eachFactor);
					// 计息日期之后的调息日期段，不需要处理
					if (periodOrder > atIndex) {
						break;
					}
					Date interestBegin = null;
					Date interestEnd = null;
					// 如果调息期间是在计息日期所在调息日期段之前的期间
					if (periodOrder < atIndex) {
						interestBegin = eachFactor.getAdjustDate();
						interestEnd = YssFun.addDay(factors
								.get(periodOrder + 1).getAdjustDate(), -1);
					} else if (periodOrder == atIndex) {
						interestBegin = eachFactor.getAdjustDate();
						interestEnd = accrualDate;
					}
					interest = this.calcInterest(eachFactor, accrualExpressions
							.get(eachFactor.getAccrualFormula()), interestEnd,
							interestBegin);
					//// 期内还本
					if(eachFactor.getIsQnhb() == 1){
						FiHundIntFactorBean lastFiHund = new FiHundIntFactorBean(); // 上个付息区间
						lastFiHund = getLastFiHund(eachFactor.getSecCode(), eachFactor.getAdjustDate()); 
						double remainMoney = eachFactor.getRemainMoney();
						if (lastFiHund != null) {
							eachFactor.setRemainMoney(lastFiHund.getRemainMoney());
							if (YssFun.dateDiff(accrualDate, eachFactor.getQnhbDate()) > 0) {
								hundIntPojo.setRemainMoney(lastFiHund.getRemainMoney());
							}
						} else {
							eachFactor.setRemainMoney(eachFactor.getIssueFaceValue());
							if (YssFun.dateDiff(accrualDate, eachFactor.getQnhbDate()) > 0) {
								hundIntPojo.setRemainMoney(eachFactor.getIssueFaceValue());
							}
						}
						if(YssFun.dateDiff(accrualDate, eachFactor.getQnhbDate()) > 0){
							interest = this.calcInterest(eachFactor, accrualExpressions
									.get(eachFactor.getAccrualFormula()), interestEnd, interestBegin);
							eachFactor.setRemainMoney(remainMoney);
						}else{
							double[] interest1 = null;
							double[] interest2 = null;
							//第一段计算
							interest1 = this.calcInterest(eachFactor, accrualExpressions
								.get(eachFactor.getAccrualFormula()), YssFun.addDay(eachFactor.getQnhbDate(),-1), interestBegin);
							eachFactor.setRemainMoney(remainMoney);
							//第二段计算
							interest2 = this.calcInterest(eachFactor, accrualExpressions
								.get(eachFactor.getAccrualFormula()), interestEnd, eachFactor.getQnhbDate());
							if(null != interest1 && interest1.length >=2 && null != interest2 && interest2.length >=2){
							interest[0] = interest1[0]+interest2[0];
							interest[1] = interest1[1]+interest2[1];
							}
						}
					}
					if(null != interest && interest.length >=2)
					{
						beforeTax += YssD.round(interest[0], point);
						afterTax += YssD.round(interest[1], point);
						dueBeforeTax += YssD.round(interest[0], point);
						dueAfterTax += YssD.round(interest[1], point);
					}
				}
			}
		}

		/*
		 * if
		 * (maxAdjust.getAccrualMode().equalsIgnoreCase(VocabularyConsts.FI_FT)
		 * && !maxAdjust.isApplyNewRate()) { // 分段计算每百元利息 //
		 * 计息期间分为几段,最后一段为:最大调息日期-计息日期，前面段为两次调息日期间隔段 double[] interest = null; //
		 * ========首先算出最后一段利息 interest = this.calcInterest(maxAdjust,
		 * accrualExpressions .get(maxAdjust.getAccrualFormula()), accrualDate,
		 * maxAdjust .getCurPeriodBeginDate()); beforeTax +=
		 * YssD.round(interest[0], point); afterTax += YssD.round(interest[1],
		 * point);
		 * 
		 * // 如果计息日期是付息期间截息日,根据付息公式计算 if (factors.indexOf(maxAdjust) == 0 &&
		 * accrualDate.equals(maxAdjust.getCurPeriodEndDate())) { interest =
		 * this.calcInterest(maxAdjust, accrualExpressions
		 * .get(maxAdjust.getPayMode()), accrualDate, maxAdjust
		 * .getCurPeriodBeginDate()); dueBeforeTax += YssD.round(interest[0],
		 * point); dueAfterTax += YssD.round(interest[1], point); } else {
		 * dueBeforeTax += YssD.round(interest[0], point); dueAfterTax +=
		 * YssD.round(interest[1], point); } // ==================== //
		 * ===================== for (FiHundIntFactorBean oneFactor : factors) {
		 * // 从最后一个期间向前推 Date lastAdjustDate = oneFactor.getAdjustDate(); //
		 * 修改计息日期,最后一个区间,计息日期为当前计息日期。其它段计息日期为后一段调息日期. //
		 * 如果某一段启用新利率,那么计息日期为后一段调息日期
		 * ,调息日期为付息区间起息日期.并且中断循环.否则,计息日期为后一段调息日期,调息日期为本段调息日期 if
		 * (factors.indexOf(oneFactor) != 0) { if (oneFactor.isApplyNewRate()) {
		 * interest = this.calcInterest(oneFactor,
		 * accrualExpressions.get(oneFactor .getAccrualFormula()),
		 * lastAdjustDate, oneFactor.getCurPeriodBeginDate()); beforeTax +=
		 * YssD.round(interest[0], point); afterTax += YssD.round(interest[1],
		 * point); dueBeforeTax += YssD.round(interest[0], point); dueAfterTax
		 * += YssD.round(interest[1], point); break; } else { interest =
		 * this.calcInterest(oneFactor, accrualExpressions.get(oneFactor
		 * .getAccrualFormula()), lastAdjustDate, oneFactor.getAdjustDate());
		 * beforeTax += YssD.round(interest[0], point); afterTax +=
		 * YssD.round(interest[1], point); dueBeforeTax +=
		 * YssD.round(interest[0], point); dueAfterTax +=
		 * YssD.round(interest[1], point); } } }
		 * 
		 * } else { double[] interest = null;
		 * 
		 * //Edit by liuxiang 2013/12/26 STORY #14457 理财品种信息增加一种正推的计息模式
		 * //根据倒置算法中新增“计息到期日轧差“出每日每百元，每日每百元值都一样，计息天数不同；
		 * if(VocabularyConsts.JXRGC.equalsIgnoreCase(maxAdjust.getBackWard())){
		 * interest = this.calcInterest(maxAdjust, accrualExpressions
		 * .get(maxAdjust.getAccrualFormula()), accrualDate, accrualDate);
		 * }else{ interest = this.calcInterest(maxAdjust, accrualExpressions
		 * .get(maxAdjust.getAccrualFormula()), accrualDate, maxAdjust
		 * .getCurPeriodBeginDate()); } beforeTax = YssD.round(interest[0],
		 * point); afterTax = YssD.round(interest[1], point); //
		 * 如果计息日期是付息期间截息日,根据付息公式计算 if
		 * (accrualDate.equals(maxAdjust.getCurPeriodEndDate())) { // CL
		 * 2013-03-14 理财产品收益类型借用了付息公式字段 if
		 * (!"FIXED".equalsIgnoreCase(maxAdjust.getPayMode()) &&
		 * !"FLOAT".equalsIgnoreCase(maxAdjust.getPayMode())) { interest =
		 * this.calcInterest(maxAdjust, accrualExpressions
		 * .get(maxAdjust.getPayMode()), accrualDate,
		 * maxAdjust.getCurPeriodBeginDate()); } dueBeforeTax =
		 * YssD.round(interest[0], point); dueAfterTax = YssD.round(interest[1],
		 * point); } else { dueBeforeTax = YssD.round(interest[0], point);
		 * dueAfterTax = YssD.round(interest[1], point); } }
		 */
		}
		hundIntPojo.setBeforeTaxInterest(beforeTax);
		hundIntPojo.setAfterTaxInterest(afterTax);
		hundIntPojo.setBeforeTaxInterestDue(dueBeforeTax);
		hundIntPojo.setAfterTaxInterestDue(dueAfterTax);

		return hundIntPojo;
	}

	/**
	 * 计算每百元债券利息
	 * 
	 * @param factor
	 *            计息因子
	 * @param expression
	 *            计息公式
	 * @return
	 * @throws YssException
	 */

	private double[] calcInterest(FiHundIntFactorBean factor,
			String expression, Date origAccrualDate, Date origBeginDate)
			throws YssException {
		double[] interest = null;
		Date accrualDate = null;// 计息日期
		Date beginDate = null;// 起息日期
		if (null == factor || null == expression || null == origAccrualDate
				|| null == origBeginDate)
			return new double[2];//ADD BY LIYANJUN 2015-2-4 BUG #107497
		// 根据不同的计息方式调整参与计算的计息日期,起息日期,截息日期,票面利率
		Calendar beginCal = Calendar.getInstance();
		Calendar accrCal = Calendar.getInstance();
		beginCal.setTime(origBeginDate);
		accrCal.setTime(origAccrualDate);
		// 一次性付息债券，并且付息期间超过1年的债券利息计算
		if (factor.getPayFrequency().equalsIgnoreCase(VocabularyConsts.FI_ONCE)
				&& YssFun.dateDiff(origBeginDate,
						YssFun.addYear(origBeginDate, 1)) > 0)
			return this.calcInterestWhenPayOnce(factor, expression,
					origAccrualDate, origBeginDate);
		// 其他债券的利息计算
		// add by wty 20160314 bug127781  当计息公式为FI_A_A_BONDF,29日当天没有计息
		if (factor.getAccrualFormula().equalsIgnoreCase(
				VocabularyConsts.FI_A_365F) || factor.getAccrualFormula().equalsIgnoreCase(
						VocabularyConsts.FI_A_A_BONDF)) {
			// 当计息公式为A/365F时,if计息日期和起息日之间包含2月29日,then把起息日期加一天
			boolean isHave29 = this.isHas2And29(origBeginDate, origAccrualDate);
			/*
			 * //如果计息日期和起息日相等,并且等于2月29号
			 * if(origAccrualDate.equals(origBeginDate)&&
			 * beginCal.get(Calendar.MONTH)==1 &&
			 * beginCal.get(Calendar.DAY_OF_MONTH)==29){ isHave29 = true; }
			 * else{//从起息日向后推算，判断到计息日之间，存在2月29号
			 * while(!beginCal.getTime().equals(origAccrualDate)){
			 * beginCal.add(Calendar.DATE, 1);
			 * if(beginCal.get(Calendar.MONTH)==1 &&
			 * beginCal.get(Calendar.DAY_OF_MONTH)==29){ isHave29 = true; break;
			 * } } }
			 */
			beginCal.setTime(origBeginDate);
			if (isHave29) {
				beginCal.add(Calendar.DATE, 1);
			}
		} else if (factor.getAccrualFormula().equalsIgnoreCase("30U/360")) {
			// 当计息公式为30U/360,
			// if 计息日期是31号 and (起息日期 = 30号 or 起息日期 = 31号),then 计息日期 = 30号.
			// if 起息日期 = 31号,then 起息日 = 30号

			if (accrCal.get(Calendar.DAY_OF_MONTH) == 31
					&& (beginCal.get(Calendar.DAY_OF_MONTH) == 30 || beginCal
							.get(Calendar.DAY_OF_MONTH) == 31)) {
				accrCal.set(Calendar.DAY_OF_MONTH, 30);
			} else if (beginCal.get(Calendar.DAY_OF_MONTH) == 31) {
				beginCal.set(Calendar.DAY_OF_MONTH, 30);
			}
		} else if (factor.getAccrualFormula().equalsIgnoreCase("30E/360")) {
			// 当计息公式为30E/360,
			// if 计息日期 = 31号, then 计息日期 = 30号.
			// if 起息日期 = 31号,then 起息日 = 30号
			if (accrCal.get(Calendar.DAY_OF_MONTH) == 31) {
				accrCal.set(Calendar.DAY_OF_MONTH, 30);
			} else if (beginCal.get(Calendar.DAY_OF_MONTH) == 31) {
				beginCal.set(Calendar.DAY_OF_MONTH, 30);
			}
		} else if (factor.getAccrualFormula().equalsIgnoreCase("30E/360L")) {
			// 当计息公式为30E/360L,
			// if 计息日期是当月最后一天 and not(计息日期=截息日 and 计息日期月份是2月份) then 计息日期 = 30号
			// if 起息日是起息日所在月最后一天,then 起息日 = 30号
			if (accrCal.get(Calendar.DAY_OF_MONTH) == accrCal
					.getActualMaximum(Calendar.DATE)
					&& !(accrCal.getTime().equals(factor.getCurPeriodEndDate()) && accrCal
							.get(Calendar.MONTH) == 1)) {
				accrCal.set(Calendar.DAY_OF_MONTH, 30);
			} else if (beginCal.get(Calendar.DAY_OF_MONTH) == beginCal
					.getActualMaximum(Calendar.DATE)) {
				beginCal.set(Calendar.DAY_OF_MONTH, 30);
			}
		}else if (factor.getAccrualFormula().equalsIgnoreCase("FI_30U_360")) {
			// 当计息公式为30U/360,
			// if 计息日期是31号 and (起息日期 = 30号 or 起息日期 = 31号),then 计息日期 = 30号.
			// if 起息日期 = 31号,then 起息日 = 30号

			if (accrCal.get(Calendar.DAY_OF_MONTH) == 31
					&& ((beginCal.get(Calendar.DAY_OF_MONTH) == 30 || beginCal
							.get(Calendar.DAY_OF_MONTH) == 31))) {
				accrCal.set(Calendar.DAY_OF_MONTH, 30);
				
			} else if (beginCal.get(Calendar.DAY_OF_MONTH) == 31) {
				beginCal.set(Calendar.DAY_OF_MONTH, 30);
			}
		} else if (factor.getAccrualFormula().equalsIgnoreCase("FI_30E_360")) {
			// 当计息公式为30E/360,
			// if 计息日期 = 31号, then 计息日期 = 30号.
			// if 起息日期 = 31号,then 起息日 = 30号
			if (accrCal.get(Calendar.DAY_OF_MONTH) == 31) {
				accrCal.set(Calendar.DAY_OF_MONTH, 30);
			} else if (beginCal.get(Calendar.DAY_OF_MONTH) == 31) {
				beginCal.set(Calendar.DAY_OF_MONTH, 30);
			}
		}
		beginDate = beginCal.getTime();
		accrualDate = accrCal.getTime();

		// 计算利率
		double[] rate = new double[2];
		rate[0] = factor.getCouponRate();
		rate[1] = factor.getCouponRate() * (1 - factor.getTaxRate());
		interest = this.calcInterestBaseExpr(accrualDate, beginDate, rate,
				expression, factor);

		return interest;
	}

	/** 
	 * @Title calcPhaseInfo 
	 * @Description BUG #152210 【紧急】太平保险-理财计息错误 添加计息年度概念，对factor进行操作推算出本年年度天数 edit by dingxukun 2017-02-10
	 * @author dingxuxkun@ysstech.com
	 * @date 2017年2月20日下午6:08:03
	 * @param factor
	 * @return void
	 */
	private Date[] calcPhaseInfo(FiHundIntFactorBean factor,Date accrulDate){
		SecBaseCache secCache = CacheManager.getInstance().getCache(CacheGroup.SECBASE);
		SecBase secInfo = secCache.getCacheByKey(factor.getSecCode());
		Date phaseDate = null;
		Date aiBeginDate = null;
		Date jxndBeginDate = null;//本期所在计息年度开始日期
		Date jxndEndDate = null;//本息所在计息年度结束日期
		Date[] dateArray = new Date[2];
		try {
			phaseDate = YssFun.toDate(secInfo.getD_SQAI_BEGIN());
			aiBeginDate = YssFun.toDate(secInfo.getD_AI_BEGIN());
		} catch (YssException ex) {
			logger.log("获取计息年度出错", ex.getMessage());
			dateArray[0] = factor.getCurPeriodBeginDate();
			dateArray[1] = factor.getCurPeriodEndDate();
			return dateArray;
		}
		int aiYear = YssFun.getYear(aiBeginDate);//获取起息日年份
		int phaseYear = YssFun.getYear(phaseDate);
		int accrualYear = YssFun.getYear(accrulDate);
		if(phaseDate != null && phaseDate.after(factor.getBondEndDate())){ //首期起息日>债券截息日，则返回原来计算逻辑的值
			jxndBeginDate = YssFun.addYear(aiBeginDate, accrualYear-aiYear);;
			if(YssFun.dateDiff(jxndBeginDate,accrulDate)>=0){
				dateArray[0] = jxndBeginDate;
			}else{
				dateArray[0] = YssFun.addYear(jxndBeginDate,-1);
			}
			if(YssFun.dateDiff(jxndBeginDate,accrulDate)>=0){
				dateArray[0] = jxndBeginDate;
			}else{
				dateArray[0] = YssFun.addYear(jxndBeginDate,-1);
			}
			dateArray[1] = YssFun.addDay(YssFun.addYear(dateArray[0], 1),-1);//根据首期起息日+1年-1天获取本期计息年度结束日;
			dateArray[1] = YssFun.addDay(YssFun.addYear(dateArray[0], 1),-1);//根据首期起息日+1年-1天获取本期计息年度结束日;
			return dateArray;
		}
		if(null != phaseDate 
				&& phaseDate.before(aiBeginDate)){//首期起息日<起息日
			jxndBeginDate = YssFun.addYear(phaseDate, aiYear-phaseYear);//首期起息日+相差年数
			jxndBeginDate = YssFun.addYear(jxndBeginDate, accrualYear-aiYear);//首期起息日+相差年数
			jxndEndDate = YssFun.addDay(YssFun.addYear(jxndBeginDate, 1),-1);//根据首期起息日+1年-1天获取本期计息年度结束日
			if(YssFun.dateDiff(jxndBeginDate,accrulDate)>=0){
				dateArray[0] = jxndBeginDate;
			}else{
				dateArray[0] = YssFun.addYear(jxndBeginDate,-1);
			}
			dateArray[1] = YssFun.addDay(YssFun.addYear(dateArray[0], 1),-1);//根据首期起息日+1年-1天获取本期计息年度结束日;
			
		}else{//首期起息日>=起息日
			jxndBeginDate = YssFun.addYear(aiBeginDate, phaseYear - aiYear);//起息日期+（计提年份-起息年份之差）年
			jxndBeginDate = YssFun.addYear(jxndBeginDate, accrualYear - aiYear);//首期起息日+相差年数
			jxndEndDate = YssFun.addDay(YssFun.addYear(jxndBeginDate, 1),-1);//根据首期起息日+1年-1天获取本期计息年度结束日
			if(YssFun.dateDiff(jxndBeginDate,accrulDate)>=0){
				dateArray[0] = jxndBeginDate;
			}else{
				dateArray[0] = YssFun.addYear(jxndBeginDate,-1);
			}
			dateArray[1] = YssFun.addDay(YssFun.addYear(dateArray[0], 1),-1);//根据首期起息日+1年-1天获取本期计息年度结束日;
		}
		
		return dateArray;
	}
	/**
	 * 当付息频率是一次性时，债券每百元利息的算法
	 * 
	 * @param factor
	 *            计息因子
	 * @param expression
	 *            表达式
	 * @param origAccrualDate
	 *            计息日期
	 * @param origBeginDate
	 *            起息日期
	 * @param origEndDate
	 *            截息日期
	 * @return
	 * @throws YssException
	 */
	private double[] calcInterestWhenPayOnce(FiHundIntFactorBean factor,
			String expression, Date origAccrualDate, Date origBeginDate)
			throws YssException {
		Calendar origCurBeginDate = Calendar.getInstance();
		origCurBeginDate.setTime(origBeginDate);
		Calendar origCurEndDate = Calendar.getInstance();
		origCurEndDate.setTime(factor.getCurPeriodEndDate());
		double[] interest = null;
		// 一次性付息，并且付息期间超过一年的债券的每百元利息处理如下:
		// 利息分为两部分：第一部分：年数 * 票面利率.第二部分：剩余天数的利息
		//上面第一部分的公式对于面值不为100的是错的，应该是：年数 * 票面利率 /100 * 票面金额    modify liuyanni 20160301
		String accrualFormula = factor.getAccrualFormula();
		// 计算利率
		double[] rate = new double[2];
		rate[0] = factor.getCouponRate();
		rate[1] = factor.getCouponRate() * (1 - factor.getTaxRate());
		// 第一部分
		int yearNumbers = this.calcYearNumbers(origAccrualDate, origBeginDate,
				factor.getAccrualFormula());
		double[] firstPart = new double[2];
		firstPart[0] = YssD.div(YssD.mul(yearNumbers, rate[0], factor.getRemainMoney()),100);
		firstPart[1] = YssD.div(YssD.mul(yearNumbers, rate[1], factor.getRemainMoney()),100);
		// 第二部分
		Date beginDate = null;// 起息日期
		Date endDate = null; // 截息日期
		// 当计息公式为A/A
		if (accrualFormula.equalsIgnoreCase(VocabularyConsts.FI_A_A)) {
			/*
			 * beginDate = YssFun.addYear(origBeginDate, yearNumbers); endDate =
			 * YssFun.addDay(YssFun.addYear(origBeginDate, yearNumbers + 1),
			 * -1);
			 */
			/**Start 20150520 modified by liubo.BUG #112329 一次性付息债券每百元利息计算错误
			 * 经过重新推算，A/A一次性付息的计息公式为：
			 * x*票面利率+round((计息日-(起息日+x年))/(起息日+x为年从起息日推算一年的天数)*票面利率/100*剩余本金,保留位数)
			 * 根据这个公式，firstPart这个数组不应该清0，另外取期间起息日的时候，不应该直接取债券基本信息的计息起始日，而是应该逐年推算*/
//			firstPart[0] = 0;
//			firstPart[1] = 0;
			int yearNumbers1 = this.calcYearNumbers(origAccrualDate, factor.getBondBeginDate(),
					factor.getAccrualFormula());
			//注意一下，这里给每年的起息日-1的原因是为了抵消现有的A/A公式中，( $DATEDIFF($DATE(adjustDate),$DATE(accrualDate))+ 1.0)的那个+1.0的部分
			beginDate = YssFun.addYear(factor.getBondBeginDate(), yearNumbers1);
			//-1之后的起息日，实际就是前一年的结息日，所以直接+1年，就是本年的结息日
			endDate = YssFun.addDay(YssFun.addYear(beginDate, 1),-1) ;
			/**End 20150520 modified by liubo.BUG #112329 一次性付息债券每百元利息计算错误*/
		}
		// 当计息公式为A/A-Bond
		else if (accrualFormula.equalsIgnoreCase(VocabularyConsts.FI_A_A_BOND) || accrualFormula.equalsIgnoreCase(VocabularyConsts.FI_A_A_BONDF)) {
			firstPart[0] = 0;
			firstPart[1] = 0;
			beginDate = factor.getCurPeriodBeginDate();
			endDate = factor.getCurPeriodEndDate();
		}
		// 当计息公式为A/365,起息日期 = 原起息日期 + 年数 * 365，截息日期 = 原起息日期 + （年数 + 1）* 365
		else if (accrualFormula.equalsIgnoreCase(VocabularyConsts.FI_A_365)) {
			beginDate = YssFun.addDay(origBeginDate, yearNumbers * 365);
			// endDate = YssFun.addDay(origBeginDate, (yearNumbers + 1) * 365
			// -1);
		}
		//edit tdf STORY #24491 【紧急】债券基本信息计息公式支持“A/366”模式20150722
		// 当计息公式为A/366,起息日期 = 原起息日期 + 年数 * 366，截息日期 = 原起息日期 + （年数 + 1）* 366
		else if (accrualFormula.equalsIgnoreCase(VocabularyConsts.FI_A_366)) {
					beginDate = YssFun.addDay(origBeginDate, yearNumbers * 366);
					// endDate = YssFun.addDay(origBeginDate, (yearNumbers + 1) * 366
					// -1);
				}
		// 当计息公式为A/365F,起息日期 = 原起息日期 + 年数
		else if (accrualFormula.equalsIgnoreCase(VocabularyConsts.FI_A_365F)) {
			beginDate = YssFun.addYear(origBeginDate, yearNumbers);
			boolean isHave29 = this.isHas2And29(beginDate, origAccrualDate);
			if (isHave29) {
				beginDate = YssFun.addDay(beginDate, 1);
			}
		}
		// 当计息公式为30/360,起息日期 = 原起息日期 + 年数 * 360，截息日期 = 原起息日期 + （年数 + 1）*
		// 360
		else if (accrualFormula.equalsIgnoreCase(VocabularyConsts.FI_30_360)
				|| accrualFormula.equalsIgnoreCase(VocabularyConsts.FI_30E_360)) {//modified by dingshalu 20160829 STORY #33515 （紧急）境外债券计息公式缺失
			beginDate = YssFun.addDay(origBeginDate, yearNumbers * 360);
			endDate = YssFun.addDay(origBeginDate, (yearNumbers + 1) * 360 - 1);
		}
		else if (accrualFormula.equalsIgnoreCase(VocabularyConsts.FI_30ST_360)) {
			beginDate = origBeginDate;
			endDate = factor.getCurPeriodEndDate();
		}
		//add by shijian 2017-01-17 BUG #148442 嘉实基金QD--30E/360 公式逢31号的计息天数计算异常
		else if(accrualFormula.equalsIgnoreCase(VocabularyConsts.FI_30E_360))
		{
			beginDate = YssFun.addYear(origBeginDate, yearNumbers);
			endDate = YssFun.addDay(YssFun.addYear(beginDate, 1),-1);
		}
		//STORY36544【招商基金】银行间债券XYDZ01计息公式A/360：支持在计息期间第二年度的第一天计息冲减上一年度多计息的金额 add by liuyanni 2016-12-02
		//当计息公式为A/360, 起息日=原起息日加年数的对日，结息日期=原起息日加(年数+1)的对日的前一日
		else if (accrualFormula.equalsIgnoreCase(VocabularyConsts.FI_A_360)) {
			beginDate = YssFun.addYear(origBeginDate, yearNumbers);
			endDate = YssFun.addDay(YssFun.addYear(origBeginDate, yearNumbers + 1), -1);
		}
		// 当计息公式为A/A-LSplit
		else if(accrualFormula.equalsIgnoreCase(VocabularyConsts.FI_A_A_LSPLIT)){
			firstPart[0] = 0;
			firstPart[1] = 0;
			beginDate = factor.getCurPeriodBeginDate();
			endDate = factor.getCurPeriodEndDate();
		}
		// add by wangtangyao 20160405 STORY #28884 理财产品信息计息公式：A/A-F，请增加一项计息天数
		else if (accrualFormula.equalsIgnoreCase(VocabularyConsts.FI_A_A_F)) {
			firstPart[0] = 0;
			firstPart[1] = 0;
			beginDate = factor.getCurPeriodBeginDate();
			endDate = factor.getCurPeriodEndDate();
		}

		// 计算每百元利息
		interest = new double[2];
		if (accrualFormula.equalsIgnoreCase(VocabularyConsts.FI_30ST_360)) {
			double[] secondPart = this.calcInterestBaseExpr(origAccrualDate,
					beginDate, rate, expression, factor);
			interest[0] = secondPart[0];
			interest[1] = secondPart[1];
		}else{
			// 修改因子的截息日期
			factor.setCurPeriodEndDate(endDate);
			// 修改因子的区间起息日期
			factor.setCurPeriodBeginDate(beginDate);
			double[] secondPart = null;
			//付息频率为“一次性” 且 倒置算法为“计息到期日轧差” 时 , 调息日期为“计息日期”当天 ， 已计息天数为1天
			//BUG #126780 【紧急】【招商基金】信托的付息频率为一次性倒置算法为计息到期日扎差时计息天数和税前税后利息计息不对  edit by LY 20160223
			if(factor.getPayFrequency().equals("FI_ONCE") && factor.getBackWard() != null && factor.getBackWard().equals("JXRGC")){
				secondPart = this.calcInterestBaseExpr(origAccrualDate, origAccrualDate, rate, expression, factor);
			}
			else{
				secondPart = this.calcInterestBaseExpr(origAccrualDate, beginDate, rate, expression, factor);
			}
			interest[0] = firstPart[0] + secondPart[0];
			interest[1] = firstPart[0] + secondPart[1];
		}
		// 恢复因子的期间起息日期和期间截息日期
		factor.setCurPeriodBeginDate(origCurBeginDate.getTime());
		factor.setCurPeriodEndDate(origCurEndDate.getTime());
		return interest;
	}

	/**
	 * 根据计息公式求出计息日期和起息日期之间的整年数
	 * 
	 * @param accrualDate
	 *            计息日期
	 * @param beginDate
	 *            起息日期
	 * @param accrualFormula
	 *            计息公式
	 * @return
	 */
	public int calcYearNumbers(Date accrualDate, Date beginDate,
			String accrualFormula) {
		int yearNumbers = 0;
		Calendar accrualCal = Calendar.getInstance();
		accrualCal.setTime(accrualDate);
		Calendar beginCal = Calendar.getInstance();
		beginCal.setTime(beginDate);
		// 当计息公式为A/A或者A/A-Bond,整年数 = 计息日期，起息日之间的整年数
		if (accrualFormula.equalsIgnoreCase(VocabularyConsts.FI_A_A)
				|| accrualFormula
						.equalsIgnoreCase(VocabularyConsts.FI_A_A_BOND)
						|| accrualFormula.equalsIgnoreCase(VocabularyConsts.FI_A_A_BONDF)) {
			beginCal.add(Calendar.YEAR, 1);
			//beginCal.add(Calendar.DATE, -1);
			//注：例如 2015-04-28起息，2016-04-27为截息, beginDate为下一个付息区间的起息日期，只需直接加一年为下一个起息日
			//BUG #126780 【紧急】【招商基金】信托的付息频率为一次性倒置算法为计息到期日扎差时计息天数和税前税后利息计息不对
			while (beginCal.compareTo(accrualCal) <= 0) {
				beginCal.add(Calendar.DATE, 1);
				beginCal.add(Calendar.YEAR, 1);
				beginCal.add(Calendar.DATE, -1);
				yearNumbers++;
			}
		}
		// 当计息公式为A/365或者A/365F,整年数 = 计息日期，起息日之间365的个数
		else if (accrualFormula.equalsIgnoreCase(VocabularyConsts.FI_A_365)
				|| accrualFormula.equalsIgnoreCase(VocabularyConsts.FI_A_365F)) {
			/**Start 20150520 modified by liubo.BUG #112329 一次性付息债券每百元利息计算错误
			 * 这里需要算本年起息日的时候，应该是+1年，否则若当年为闰年，直接+365天会取到本年起息日的前一天，对A/365F会有影响*/
			beginCal.add(Calendar.YEAR, 1);
			beginCal.add(Calendar.DATE, -1);
			while (beginCal.compareTo(accrualCal) <= 0) {
				beginCal.add(Calendar.DATE, 1);
				beginCal.add(Calendar.YEAR, 1);
				beginCal.add(Calendar.DATE, -1);
				yearNumbers++;
			}
			/**End 20150520 modified by liubo.BUG #112329 一次性付息债券每百元利息计算错误*/
		}
		// 当计息公式为A/366,整年数 = 计息日期，起息日之间366的个数
		//edit tdf STORY #24491 【紧急】债券基本信息计息公式支持“A/366”模式20150722
		else if (accrualFormula.equalsIgnoreCase(VocabularyConsts.FI_A_366)
				) {
			beginCal.add(Calendar.DATE, 366);
			beginCal.add(Calendar.DATE, -1);
			while (beginCal.compareTo(accrualCal) <= 0) {
				beginCal.add(Calendar.DATE, 1);
				beginCal.add(Calendar.YEAR, 1);
				beginCal.add(Calendar.DATE, -1);
				yearNumbers++;
			}
		}
		// 当计息公式为30/360,整年数 = 计息日期，起息日之间360的个数
		else if (accrualFormula.equalsIgnoreCase(VocabularyConsts.FI_30_360)
				|| accrualFormula.equalsIgnoreCase(VocabularyConsts.FI_30E_360)
				|| accrualFormula.equalsIgnoreCase(VocabularyConsts.FI_30ST_360)) {//add by dingshalu 20160829 STORY #33515 （紧急）境外债券计息公式缺失
			beginCal.add(Calendar.DATE, 360);
			beginCal.add(Calendar.DATE, -1);
			while (beginCal.compareTo(accrualCal) <= 0) {
				beginCal.add(Calendar.DATE, 1);
				beginCal.add(Calendar.DATE, 360);
				beginCal.add(Calendar.DATE, -1);
				yearNumbers++;
			}
		}
		//STORY36544【招商基金】银行间债券XYDZ01计息公式A/360：支持在计息期间第二年度的第一天计息冲减上一年度多计息的金额 add by liuyanni 2016-1202
		//当计息公式为A/360 
		else if (accrualFormula.equalsIgnoreCase(VocabularyConsts.FI_A_360)) {
			beginCal.add(Calendar.YEAR, 1);
			beginCal.add(Calendar.DATE, -1);
			while (beginCal.compareTo(accrualCal) < 0) {
				beginCal.add(Calendar.DATE, 1);
				beginCal.add(Calendar.YEAR, 1);
				beginCal.add(Calendar.DATE, -1);
				yearNumbers++;
			}
		}
		return yearNumbers;
	}

	/**
	 * 计算每百元债券税前和税后利息
	 * 
	 * @param accrualDate
	 *            计息日期
	 * @param adjustDate
	 *            调息日期
	 * @param rates
	 *            利率数组:税前票面利率和税后票面利率
	 * @param expression
	 *            算术表达式
	 * @param factor
	 *            计算相关信息
	 * @return
	 * @throws YssException
	 * @throws IllegalAccessException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 */
	private double[] calcInterestBaseExpr(Date accrualDate, Date adjustDate,
			double[] rates, String expression, FiHundIntFactorBean factor)
			throws YssException {
		double[] interest = null;
		if (accrualDate == null)
			return interest;
		List<String> fieldNames = null;
		PreparedExpression pe = null;
		List<Variable> variables = null;
		Calendar accrCal2 = Calendar.getInstance();
		accrCal2.setTime(accrualDate);//accrualDate计息日期即估值日期
		if(factor != null && VocabularyConsts.FI_30ST_360.equals(factor.getAccrualFormula())){
		 if (YssFun.isLeapYear(Integer.valueOf(YssFun.formatDate(accrualDate).substring(0,4)))) {
		    	if((accrCal2.get(Calendar.DAY_OF_MONTH) == 29)&&accrCal2.get(Calendar.DAY_OF_YEAR)>31&&accrCal2.get(Calendar.DAY_OF_YEAR)<=60)
		    	{
		    		expression=expression.replace("+1", "+2");	    		
		    	}		    
		    }else{
		    	if((accrCal2.get(Calendar.DAY_OF_MONTH) == 28)&&accrCal2.get(Calendar.DAY_OF_YEAR)>31&&accrCal2.get(Calendar.DAY_OF_YEAR)<60)
		    	{
		    		expression=expression.replace("+1", "+3");
		    	}				    		
		    }
		}
		SecBaseCache secCache = CacheManager.getInstance().getCache(CacheGroup.SECBASE);
		SecBase secInfo = null;
		if(null != secCache
				&& null != factor)
		{
			secInfo = secCache.getCacheByKey(factor.getSecCode());
		}
		
		String secVarCode = "";
		if(secInfo!=null){
			secVarCode = secInfo.getC_SEC_VAR_CODE();
		}
		//BUG #152210 【紧急】太平保险-理财计息错误 添加计息年度概念，对factor进行操作推算出本年年度天数 edit by dingxukun 2017-02-10
		Date[] dateArray = null;
		//BUG #152891 【紧急】太平保险-债券每百元生成错误 理财产品按照计息年度概念计算计息年度实际天数。但是债券是按照本期计息期间间隔天数+1来计算的 edit by dingxukun 2017-02-10
		if (secVarCode.startsWith("LC") && expression.contains("curPeriodBeginDate") && expression.contains("curPeriodEndDate")){
			dateArray = calcPhaseInfo(factor,accrualDate);
		}
		if (null != factor && null != accrualDate && null != adjustDate
				&& null != expression) {
			if (exprFields.containsKey(expression)) {
				fieldNames = exprFields.get(expression);
			} else {
				//chenbo
				fieldNames = ActHelp.getFieldNamesFromExpression(expression);
				exprFields.put(expression, fieldNames);
			}
			if (exprVariables.containsKey(expression)) {
				variables = exprVariables.get(expression);
			} else {
				//chenbo
				variables = ActHelp.getVariablesFromExpression(expression);
				exprVariables.put(expression, variables);
			}
			if (exprPe.containsKey(expression)) {
				pe = exprPe.get(expression);
			} else {
				pe = ExpressionEvaluator.preparedCompile(expression, variables);
				exprPe.put(expression, pe);
			}

			Object obj = null;
			double beforeTax = 0;
			double afterTax = 0;
			interest = new double[2];
			for (String fieldName : fieldNames) {
				if (fieldName.equalsIgnoreCase("accrualDate")) {
					//modified by dingshalu 20160829 STORY #33515 （紧急）境外债券计息公式缺失
					//Y2.M2.D2表估值日 如D2=31，则D2转为30； D2为二月份最后一天时，并不做特殊处理
					if(factor.getAccrualFormula().equals(VocabularyConsts.FI_30E_360)||factor.getAccrualFormula().equals(VocabularyConsts.FI_30ST_360)){
						Calendar accrCal = Calendar.getInstance();
						accrCal.setTime(accrualDate);
					    if (accrCal.get(Calendar.DAY_OF_MONTH) == 31) {
								accrCal.set(Calendar.DAY_OF_MONTH, 30);
								obj = accrCal.getTime();
					    }else{
					    	obj = accrualDate;		
					    }
					}else{
					obj = accrualDate;			
				   }		
				} else if (fieldName.equalsIgnoreCase("adjustDate")) {
					obj = adjustDate;
				} else if (fieldName.equalsIgnoreCase("curPeriodBeginDate")) {
					//modified by dingshalu 20160829 STORY #33515 （紧急）境外债券计息公式缺失
					//Y1.M1.D1表计息起始日 、如D1=31，则D1转为30；
					if(factor.getAccrualFormula().equals(VocabularyConsts.FI_30E_360)||factor.getAccrualFormula().equals(VocabularyConsts.FI_30ST_360)){
						Calendar beginCal = Calendar.getInstance();
						beginCal.setTime(factor.getCurPeriodBeginDate());
					    if (beginCal.get(Calendar.DAY_OF_MONTH) == 31) {
					    	beginCal.set(Calendar.DAY_OF_MONTH, 30);
								obj = beginCal.getTime();
					    }else{
					    	obj =  factor.getCurPeriodBeginDate();		
					    }
					}else{
					obj = factor.getCurPeriodBeginDate();
					}
				} else if (fieldName.equalsIgnoreCase("couponRate")) {
					obj = rates[0];
				} else if (fieldName.equalsIgnoreCase("payFrequency")) {
					if (factor.getPayFrequency().equals(VocabularyConsts.FI_ONCE)) {
						// 如果是一次性，付息频率等于 12/起息日和截息日之间的月数
						int months = YssFun.monthDiff(factor.getBondBeginDate(),YssFun.addDay(factor.getBondEndDate(), 1));
						months = months == 0 ? 1 : months;
						obj = YssD.div(12, months);
						//BUG117759【紧急】贴现债计息方式错误，系统都按照365计息
						//临时处理 ：公式FI_A_A_BOND，区间小于一年时，频率固定为1 --LIUCHI/2015-7-17
						if((factor.getAccrualFormula().equals(VocabularyConsts.FI_A_A_BOND) || 
								factor.getAccrualFormula().equals(VocabularyConsts.FI_A_A_BONDF)) && months < 12){
							obj = 1;
						}
					} else {
					//	chenbo
						obj = AdmFactRate.convertFreqToNum(factor.getPayFrequency(),accrualDate);
					}

				}
				/////结束日期默认加上一年 BY Jinghehe 2015-4-27 
				else if (factor.getAccrualFormula().equalsIgnoreCase(VocabularyConsts.FI_A_A)
						&& fieldName.equalsIgnoreCase("curPeriodEndDate")) {
					Calendar beginCal = Calendar.getInstance();
					beginCal.setTime(factor.getCurPeriodBeginDate());
					beginCal.add(Calendar.YEAR, 1);
					beginCal.add(Calendar.DATE, -1);
					obj = beginCal.getTime();
				}
				/*////by weijj 20160323  华宝信托半年期计息
				else if (factor.getAccrualFormula().equalsIgnoreCase(VocabularyConsts.FI_A_A_BONDF)
						&& fieldName.equalsIgnoreCase("curPeriodEndDate")) {
					Calendar beginCal = Calendar.getInstance();
					beginCal.setTime(factor.getCurPeriodEndDate());
					if(isHas2And29(factor.getCurPeriodBeginDate(), factor.getCurPeriodEndDate())){
						beginCal.add(Calendar.DATE, -1);
					}
					obj = beginCal.getTime();
				}*/
				else {
					obj = ReflectionUtil.getFieldValue(factor, fieldName);
				}
				pe.setArgument(fieldName, obj);
			}
			// 计算税前利息
			beforeTax = (Double) pe.execute();
			interest[0] = beforeTax;
			for (String fieldName : fieldNames) {
				if (fieldName.equalsIgnoreCase("accrualDate")) {
					//modified by dingshalu 20160829 STORY #33515 （紧急）境外债券计息公式缺失
					//Y2.M2.D2表估值日 如D2=31，则D2转为30； D2为二月份最后一天时，并不做特殊处理
					if(factor.getAccrualFormula().equals(VocabularyConsts.FI_30E_360)||factor.getAccrualFormula().equals(VocabularyConsts.FI_30ST_360)){
						Calendar accrCal = Calendar.getInstance();
						accrCal.setTime(accrualDate);
					    if (accrCal.get(Calendar.DAY_OF_MONTH) == 31) {
								accrCal.set(Calendar.DAY_OF_MONTH, 30);
								obj = accrCal.getTime();
					    }else{
					    	obj = accrualDate;		
					    }
					}else{
						obj = accrualDate;		
				   }		
				} else if (fieldName.equalsIgnoreCase("adjustDate")) {
					obj = adjustDate;
				} else if (fieldName.equalsIgnoreCase("curPeriodBeginDate")) {
					//edit by wangtangyao  20160525针对计息公式FI_A_A_BONDF闰年2月29不计息，在此判断当前计息区间内是否包含该日期。如果包含，计息区间实际天数要-1，用起息日加1来实现。
					if(factor.getAccrualFormula().equals(VocabularyConsts.FI_A_A_BONDF)){
						if(isHas2And29(factor.getCurPeriodBeginDate(),factor.getCurPeriodEndDate())){
						Calendar beginCal = Calendar.getInstance();
						beginCal.setTime(factor.getCurPeriodBeginDate());
						beginCal.add(Calendar.DATE, 1);
						obj = beginCal.getTime();
					}
					//modified by dingshalu 20160829 STORY #33515 （紧急）境外债券计息公式缺失
				    //add by huangshui 20161207 STORY35683【招商基金】增加债券计息公式：大月31号不计息，2月份 
					//Y1.M1.D1表计息起始日 、如D1=31，则D1转为30；
					}else if(factor.getAccrualFormula().equals(VocabularyConsts.FI_30E_360)||factor.getAccrualFormula().equals(VocabularyConsts.FI_30ST_360)){
						Calendar beginCal = Calendar.getInstance();
						beginCal.setTime(factor.getCurPeriodBeginDate());
					    if (beginCal.get(Calendar.DAY_OF_MONTH) == 31) {
					    	beginCal.set(Calendar.DAY_OF_MONTH, 30);
								obj = beginCal.getTime();
					    
						}		
				
					}else{
					    	//obj =  factor.getCurPeriodBeginDate();
							//BUG #152210 【紧急】太平保险-理财计息错误 添加计息年度概念，对factor进行操作推算出本年年度天数 edit by dingxukun 2017-02-10
							if(dateArray != null){
								obj = dateArray[0];
							}else{
								obj = factor.getCurPeriodBeginDate();
							}
					}
				} else if (fieldName.equalsIgnoreCase("couponRate")) {
					obj = rates[1];
				} else if (fieldName.equalsIgnoreCase("payFrequency")) {
					if (factor.getPayFrequency().equals(
							VocabularyConsts.FI_ONCE)&& YssFun.dateDiff(YssFun.addYear(accrualDate, 1),factor.getBondEndDate()) > 0) {////by weijj 广发证券证券计息小于1年时计息错误
						// 如果是一次性，付息频率等于 12/起息日和截息日之间的月数
						int months = YssFun.monthDiff(
								factor.getBondBeginDate(),
								YssFun.addDay(factor.getBondEndDate(), 1));
						months = months == 0 ? 1 : months;
						obj = YssD.div(12, months);
						//BUG117759【紧急】贴现债计息方式错误，系统都按照365计息
						//临时处理 ：公式FI_A_A_BOND，区间小于一年时，频率固定为1 --LIUCHI/2015-7-17
						if((factor.getAccrualFormula().equals(VocabularyConsts.FI_A_A_BOND) || 
								factor.getAccrualFormula().equals(VocabularyConsts.FI_A_A_BONDF)) && months < 12){
							obj = 1;
						}
					} else {
						//chenbo
					obj = AdmFactRate.convertFreqToNum(factor.getPayFrequency(),accrualDate);
					}
				} 
				/////结束日期默认加上一年 BY Jinghehe 2015-4-27 
				else if (factor.getAccrualFormula().equalsIgnoreCase(
						VocabularyConsts.FI_A_A)
						&& fieldName.equalsIgnoreCase("curPeriodEndDate")) {
					//BUG #152210 【紧急】太平保险-理财计息错误 添加计息年度概念，对factor进行操作推算出本年年度天数 edit by dingxukun 2017-02-10
					if(dateArray != null){
						obj = dateArray[1];
					}else{
						Calendar beginCal = Calendar.getInstance();
						beginCal.setTime(factor.getCurPeriodBeginDate());
						beginCal.add(Calendar.YEAR, 1);
						beginCal.add(Calendar.DATE, -1);
						obj = beginCal.getTime();
					}
				}
				/*////by weijj 20160323  华宝信托半年期计息
				else if (factor.getAccrualFormula().equalsIgnoreCase(VocabularyConsts.FI_A_A_BONDF)
						&& fieldName.equalsIgnoreCase("curPeriodEndDate")) {
					Calendar beginCal = Calendar.getInstance();
					beginCal.setTime(factor.getCurPeriodEndDate());
					if(isHas2And29(factor.getCurPeriodBeginDate(), factor.getCurPeriodEndDate())){
						beginCal.add(Calendar.DATE, -1);
					}
					obj = beginCal.getTime();
				}*/
				else {
					obj = ReflectionUtil.getFieldValue(factor, fieldName);
				}
				pe.setArgument(fieldName, obj);
			}
			// 计算税后利息
			afterTax = (Double) pe.execute();
			interest[1] = afterTax;
		}
		return interest;
	}

	/**
	 * 判断两个日期之间是否包含2月29号
	 * 
	 * @param origBeginDate
	 *            开始日期
	 * @param origAccrualDate
	 *            计息日期
	 * @return
	 */
	private boolean isHas2And29(Date origBeginDate, Date origAccrualDate) {
		boolean isHave29 = false;
		Calendar beginCalendar = Calendar.getInstance();
		Calendar endCalendar = Calendar.getInstance();
		if (YssFun.dateDiff(origBeginDate, origAccrualDate) >= 0) {
			beginCalendar.setTime(origBeginDate);
			endCalendar.setTime(origAccrualDate);
		} else {
			beginCalendar.setTime(origAccrualDate);
			endCalendar.setTime(origBeginDate);
		}
		if (isEquals2And29(beginCalendar.getTime())
				|| isEquals2And29(endCalendar.getTime())) {
			isHave29 = true;
		} else {// 从起息日向后推算，判断到计息日之间，存在2月29号
			while (!beginCalendar.after(endCalendar)) {
				// CL 20121018 判断是否跨2月29时，不能先将日期加1，因为计息日期为28号时加1在判断就错了
				// beginCalendar.add(Calendar.DATE, 1);
				if (isEquals2And29(beginCalendar.getTime())) {
					isHave29 = true;
					break;
				}
				beginCalendar.add(Calendar.DATE, 1);
			}
		}
		return isHave29;
	}

	/**
	 * 判断一个日期是否是2月29号
	 * 
	 * @param date
	 * @return
	 */
	private boolean isEquals2And29(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return (calendar.get(Calendar.MONTH) == 1 && calendar
				.get(Calendar.DAY_OF_MONTH) == 29);
	}

	/**
	 * 单独计算计息方式为F/30/360的计息天数
	 * 
	 * @author zhengguiyu BUG #80394 债券每天利息的计提天数计算有问题，要根据计息方式来计算
	 * @param accrualDate
	 * @param adjustDate
	 * @param rates
	 * @param expression
	 * @param factor
	 * @return
	 */
	public int getDateDiff(java.util.Date dDate1, java.util.Date dDate2) {
		int year1 = 0;
		int month1 = 0;
		int day1 = 0;
		int year2 = 0;
		int month2 = 0;
		int day2 = 0;
		int dayPeriod = 0;
		try {
			GregorianCalendar cl1 = new GregorianCalendar();
			GregorianCalendar cl2 = new GregorianCalendar();
			cl1.setTime(dDate1);
			cl2.setTime(dDate2);
			year1 = cl1.get(Calendar.YEAR);
			month1 = cl1.get(Calendar.MONTH) + 1;
			day1 = cl1.get(Calendar.DATE);
			year2 = cl2.get(Calendar.YEAR);
			month2 = cl2.get(Calendar.MONTH) + 1;
			day2 = cl2.get(Calendar.DATE);
			dayPeriod = (360 * (year2 - year1) + 30 * (month2 - month1) + (day2 - day1));
			// STORY14795调整债券计息30/360，支持30号不计息、31号计息
			// 以上公式为30计息，31不计息
			// modified by dingshalu   BUG #147127 BUG单-海通a版本 债券每日利息计息天数计算错误
			// 处理逻辑：每日利息计息公式未处理30计息，31不计息，因此在此处计算天数时，暂时将此逻辑注释，以保证计息公式的天数与界面显示计息天数一致
			//if (day2 == 31) {
				// 30不计息，31计息
				//dayPeriod--;
			//}

		} catch (Exception e) {
//			e.printStackTrace();
			logger.log("公共信息处理：初始化每百元时，计算计息方式为F/30/360的计息天数失败", e);
		}
		return Math.abs(dayPeriod);
	}
	
	/**单独计算计息方式为FI_30ST_360的计息天数
	 * @author huangshui 20161207 STORY35683【招商基金】增加债券计息公式：大月31号不计息，2月份 28/29号视平闰年计3/2天息
	 * @param dDate1  计息起息日
	 * @param dDate2 估值日
	 * @return 计息天数
	 * 计算公式：
	 * Y1.M1.D1表计息起始日，Y2.M2.D2表估值日					
		1、如果D2 =31 and D1 = 30 or 31 => change D2 to 30.					
        2、如果D1 = 31 => change D1 to 30.					
                      计息天数=(Y2-Y1)*360+(M2-M1)*30+(D2-D1+1)					
                      顺序处理1、2点
	 */
	public int getDateDiffFor30ST(java.util.Date dDate1, java.util.Date dDate2) {
		int year1 = 0;
		int month1 = 0;
		int day1 = 0;
		int year2 = 0;
		int month2 = 0;
		int day2 = 0;
		int dayPeriod = 0;
		try {
			GregorianCalendar cl1 = new GregorianCalendar();
			GregorianCalendar cl2 = new GregorianCalendar();
			cl1.setTime(dDate1);
			cl2.setTime(dDate2);
			year1 = cl1.get(Calendar.YEAR);
			month1 = cl1.get(Calendar.MONTH) + 1;
			day1 = cl1.get(Calendar.DATE);
			year2 = cl2.get(Calendar.YEAR);
			month2 = cl2.get(Calendar.MONTH) + 1;
			day2 = cl2.get(Calendar.DATE);
			//1、如果D2 = 31 => change D2 to 30.
			if(day2 == 31){
				day2 = 30;
			}
			//2、如果D1 = 31 => change D1 to 30.
			if(day1 ==31){
				day1 = 30;
			}
			if(YssFun.isLeapYear(year2))
			{
                 if(month2==2 && day2==29){
					day2 = 30;
				}
			}else{
				 if(month2==2 && day2==28){
						day2 = 30;
					}
			}
			dayPeriod = (360 * (year2 - year1) + 30 * (month2 - month1) + (day2 - day1+1));

		} catch (Exception e) {
		}
		return Math.abs(dayPeriod);
	}
	
	/**
	 * 单独计算计息方式为F/30E/360的计息天数
	 * 
	 * @author DINGSHALU STORY33515 （紧急）境外债券计息公式缺失
	 * @param accrualDate
	 * @param adjustDate
	 * @param rates
	 * @param expression
	 * @param factor
	 * @return
	 */
	public int getDateDiffFor30E(java.util.Date dDate1, java.util.Date dDate2) {
		int year1 = 0;
		int month1 = 0;
		int day1 = 0;
		int year2 = 0;
		int month2 = 0;
		int day2 = 0;
		int dayPeriod = 0;
		try {
			GregorianCalendar cl1 = new GregorianCalendar();
			GregorianCalendar cl2 = new GregorianCalendar();
			cl1.setTime(dDate1);
			cl2.setTime(dDate2);
			year1 = cl1.get(Calendar.YEAR);
			month1 = cl1.get(Calendar.MONTH) + 1;
			day1 = cl1.get(Calendar.DATE);
			year2 = cl2.get(Calendar.YEAR);
			month2 = cl2.get(Calendar.MONTH) + 1;
			day2 = cl2.get(Calendar.DATE);
			dayPeriod = (360 * (year2 - year1) + 30 * (month2 - month1) + (day2 - day1));
			// STORY14795调整债券计息30/360，支持30号不计息、31号计息
			// 以上公式为30计息，31不计息
			if (day1 == 31) {
				dayPeriod++;
			}
			if (day2 == 31) {
				// 30不计息，31计息
				dayPeriod--;
			}

		} catch (Exception e) {
//			e.printStackTrace();
			logger.log("计算计息方式为F/30E/360的计息天数错误！",e);
		}
		return Math.abs(dayPeriod);
	}

	/**
	 * 获取债券每百元利息计算公式,key值为计息方式,value值为对应的计息算数表达式
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getHundIntExpressions() throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		HashMap<String, String> accrualExpressions = null;
		String sql = "SELECT C_EXP_CODE,C_VALUE FROM T_S_DE_EXP WHERE C_DV_EXP_TYPE = 'EXP_BOND'";
		Connection conn = null;
		try {
			conn = YssDbPoolFactory.getInstance().getDbPool(SecInfoActivator.class).getConnection();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				if (null == accrualExpressions) {
					accrualExpressions = new HashMap<String, String>();
				}
				accrualExpressions.put(rs.getString("C_EXP_CODE"),
						rs.getString("C_VALUE"));
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			logger.log("公共信息处理：初始化每百元时，获取债券每百元利息计算公式失败", e);
			throw (Exception) e;
		} finally {
			//chenbo
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
//			DBUtils.cleanUp(rs, pst); //调用公共方法byleeyu20151015
			DbFun.releaseConnection(conn);
//			if (null != pst)
//				pst.close();
//			if (null != rs)
//				rs.close();
//			if (null != conn) {
//				try {
//					conn.close();
//				} catch (Exception ex) {
//					ex.printStackTrace();
//				}
//			}
		}

		return accrualExpressions;
	}

	/**
	 * 保存一组每百元利息数据到每百元利息表中
	 * 
	 * @param perHundIntPojos
	 * @throws Exception
	 */
	public void savePerHundInt(List<FiPerHundredInterestBean> perHundIntPojos,Connection dbConn)
			throws Exception {
		PreparedStatement pst = null;
//		Connection conn = null;
		try {
//			conn = YssDbPoolFactory.getInstance().getDbPool(UcoActivator.class).getConnection();
//			conn.setAutoCommit(false);
			String sql = " INSERT INTO T_D_SV_FI_INCOME (C_IDEN,C_SEC_CODE,D_INCOME,C_DV_QUT_MOD,N_INCOME_DAYS,N_COUP_RATE,N_REM_COR,"
					+ " N_INCOME_PT,N_INCOME_AT,N_INCOME_PT_DUE,N_INCOME_AT_DUE,N_CHECK_STATE,C_UPDATE_BY,C_UPDATE_TIME,C_CHECK_BY,C_CHECK_TIME) VALUES "
					+ " (SEQU_D_SV_FI_INCOME.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			String updateTime = YssFun.formatDatetime(new Date());
			/*STORY #38597 【南方基金】公共信息处理-初始化债券每百元利息 执行速度慢
			 * 多线程时执行用户如果为空则使用sys */
			String userCode = ContextFactory.getContext().getUserCode();
			userCode = StringUtil.IsNullOrEmpty(userCode) ? "sys" : userCode;
			if (null != perHundIntPojos && 0 != perHundIntPojos.size()) {
					pst = dbConn.prepareStatement(sql);
					for (FiPerHundredInterestBean perHundIntBean : perHundIntPojos) {
						// 证券代码
						pst.setString(1, perHundIntBean.getSecCode());
						// 计息日期
						pst.setDate(2, YssFun.toSqlDate(perHundIntBean
								.getAccrualDate()));
						// 报价方式
						//理财品种的QuoteMode可能为NULL，添加空值处理
						pst.setString(3, StringUtil.IsNullOrEmpty(perHundIntBean.getQuoteMode())?" ":perHundIntBean.getQuoteMode());
						// 计息天数
						pst.setDouble(4, perHundIntBean.getInterestDays());
						// 票面利率
						pst.setDouble(5, perHundIntBean.getCouponRate());
						// 剩余本金
						pst.setDouble(6, perHundIntBean.getRemainMoney());
						// 税前利息
						pst.setDouble(7, perHundIntBean.getBeforeTaxInterest());
						// 税后利息
						pst.setDouble(8, perHundIntBean.getAfterTaxInterest());
						// 税前利息(为计息使用)
						pst.setDouble(9,
								perHundIntBean.getBeforeTaxInterestDue());
						// 税后利息(为计息使用)
						pst.setDouble(10,
								perHundIntBean.getAfterTaxInterestDue());
						// 审核状态
						pst.setDouble(11, 1);
						// 创建人
						pst.setString(12, userCode);
						// 创建时间
						pst.setString(13, updateTime);
						// 审核人 byleeyu20130719
						pst.setString(14, userCode);
						// 审核时间 byleeyu20130719
						pst.setString(15, updateTime);
						pst.addBatch();
					}
					pst.executeBatch();
					pst.clearBatch();//addbyleeyu20151015
				
//				finally {
//					if (null != pst)
//						pst.close();
//				}
			}
//			conn.commit();
//			conn.setAutoCommit(true);
		} catch (SQLException e) {
//			e.printStackTrace();
			logger.log("公共信息处理：初始化每百元时，保存每百元利息数据失败", e);
			throw (Exception) e;
		} 
		 catch (Exception ex) {
//			DbFun.endTransFinal(conn, true); // 采用标准方式关闭连接byleeyu20161011
			throw ex;
		} finally {
			DbFun.closeStatementFinal(pst);
//			DbFun.releaseConnection(conn);
//			try {
//				conn.close();
//			} catch (Exception ex) {
////				ex.printStackTrace();
//				logger.log("公共信息处理：初始化每百元时，保存每百元利息数据失败", ex);
//			}
		}
	}

	/**
	 * 删除一组每百元利息数据
	 * 
	 * @param perHundIntPojos
	 * @throws Exception
	 */
	public void deletePerHundInt(List<FiPerHundredInterestBean> perHundIntPojos,Connection dbConn)
			throws Exception {
		PreparedStatement pst = null;
		String sql = "DELETE FROM T_D_SV_FI_INCOME WHERE C_SEC_CODE = ? AND D_INCOME = ?";
		if (null != perHundIntPojos && 0 != perHundIntPojos.size()) {
//			Connection conn = null;
			try {
//				conn = YssDbPoolFactory.getInstance().getDbPool(UcoActivator.class).getConnection();
//				conn.setAutoCommit(false);
				pst = dbConn.prepareStatement(sql);
				for (FiPerHundredInterestBean perHundIntBean : perHundIntPojos) {
					pst.setString(1, perHundIntBean.getSecCode());
					pst.setDate(2,
							YssFun.toSqlDate(perHundIntBean.getAccrualDate()));
					pst.addBatch();
				}
				pst.executeBatch();
				pst.clearBatch();//addbyleeyu20151015
//				conn.commit();
//				conn.setAutoCommit(true);
				
			} catch (SQLException e) {
//				e.printStackTrace();
//                DbFun.endTransFinal(conn, true);
				logger.log("公共信息处理：初始化每百元时，删除每百元利息数据失败", e);
				throw (Exception) e;
			} finally {
			    DbFun.closeStatementFinal(pst);
//				DbFun.releaseConnection(conn);
//				if (null != pst)
//					pst.close();
//				try {
//					conn.close();
//				} catch (Exception ex) {
////					ex.printStackTrace();
//					logger.log("公共信息处理：初始化每百元时，删除每百元利息数据失败", ex);
//				}
			}
		}
	}
	
	/**
	 * 删除一组每百元利息数据
	 * 
	 * @param perHundIntPojos
	 * @throws Exception
	 */
	public void deletePerHundInt(String secCode, Date beginDate ,Date endDate)
			throws Exception {
		PreparedStatement pst = null;
		String sql = "DELETE FROM T_D_SV_FI_INCOME WHERE C_SEC_CODE = ? AND D_INCOME >= ? AND D_INCOME <= ?";//zzk 20151110 BUG #121924 债券在生成历史付息和每百元的时候，点击生成按钮，系统崩掉
		Connection conn = null;
		try {
			conn = YssDbPoolFactory.getInstance().getDbPool(SecInfoActivator.class).getConnection();
			conn.setAutoCommit(false);
			pst = conn.prepareStatement(sql);
			pst.setString(1,secCode);
			pst.setDate(2, YssFun.toSqlDate(beginDate));
			pst.setDate(3, YssFun.toSqlDate(endDate));
			pst.executeUpdate();//byleeyu20151015
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
//			e.printStackTrace();
            DbFun.endTransFinal(conn, true);
			logger.log("公共信息处理：初始化每百元时，删除每百元利息数据失败", e);
			throw (Exception) e;
		} finally {
			DbFun.closeStatementFinal(pst);
			DbFun.releaseConnection(conn);
//			try {
//				conn.close();
//			} catch (Exception ex) {
////				ex.printStackTrace();
//				logger.log("公共信息处理：初始化每百元时，删除每百元利息数据失败", ex);
//			}
		}
	
	}

	/**
	 * 查询某一日的每百元利息
	 * 
	 * @param conn
	 *            数据库连接
	 * @param secCode
	 *            证券代码
	 * @param beginDate
	 *            起始日期
	 * @param endDate
	 *            结束日期
	 * @return
	 * @throws SQLException
	 */
	public static List<FiPerHundredInterestBean> getPeriodFiHundInt(
			Connection conn, String secCode, Date beginDate, Date endDate)
			throws SQLException {
		List<FiPerHundredInterestBean> fiHundIntBeans = null;
		FiPerHundredInterestBean fiHundIntBean = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "SELECT D_INCOME,C_SEC_CODE,N_COUP_RATE,N_REM_COR,N_INCOME_PT,N_INCOME_AT,N_INCOME_PT_DUE,N_INCOME_AT_DUE "
				+ " FROM T_D_SV_FI_INCOME "
				+ " WHERE N_CHECK_STATE = 1 AND  C_SEC_CODE = ? AND D_INCOME >= ? AND D_INCOME <= ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, secCode);
			pst.setDate(2, YssFun.toSqlDate(beginDate));
			pst.setDate(3, YssFun.toSqlDate(endDate));
			rs = pst.executeQuery();
			while (rs.next()) {
				if (null == fiHundIntBeans) {
					fiHundIntBeans = new ArrayList<FiPerHundredInterestBean>();
				}
				fiHundIntBean = new FiPerHundredInterestBean();
				// 证券代码
				fiHundIntBean.setSecCode(rs.getString("C_SEC_CODE"));
				// 每百元日期
				fiHundIntBean.setAccrualDate(rs.getDate("D_INCOME"));
				// 票面利率
				fiHundIntBean.setCouponRate(rs.getDouble("N_COUP_RATE"));
				// 剩余本金
				fiHundIntBean.setRemainMoney(rs.getDouble("N_REM_COR"));
				// 税前每百元利息(买卖使用)
				fiHundIntBean.setBeforeTaxInterest(rs.getDouble("N_INCOME_PT"));
				// 税后每百元利息(买卖使用)
				fiHundIntBean.setAfterTaxInterest(rs.getDouble("N_INCOME_AT"));
				// 税前每百元利息(计息使用)
				fiHundIntBean.setBeforeTaxInterestDue(rs
						.getDouble("N_INCOME_PT_DUE"));
				// 税后每百元利息(计息使用)
				fiHundIntBean.setAfterTaxInterestDue(rs
						.getDouble("N_INCOME_AT_DUE"));
				fiHundIntBeans.add(fiHundIntBean);
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			throw e;
		} finally {
//			if (null != pst)
//				pst.close();
//			if (null != rs)
//				rs.close();
			//chenbo
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
//			DBUtils.cleanUp(rs, pst); //调用公共方法byleeyu20151015
		}
		return fiHundIntBeans;
	}

	/**
	 * 查询某一日的每百元利息
	 * 
	 * @param conn
	 *            数据库连接
	 * @param secCode
	 *            证券代码
	 * @param incomeDate
	 *            每百元利息日期
	 * @return
	 * @throws SQLException
	 */
	public static FiPerHundredInterestBean getUnqiueFiHundInt(Connection conn,
			String secCode, Date incomeDate) throws SQLException {
		FiPerHundredInterestBean fiHundIntBean = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "SELECT C_SEC_CODE,N_COUP_RATE,N_REM_COR,N_INCOME_PT,N_INCOME_AT,N_INCOME_PT_DUE,N_INCOME_AT_DUE "
				+ " FROM T_D_SV_FI_INCOME "
				+ " WHERE N_CHECK_STATE = 1 AND  C_SEC_CODE = ? AND D_INCOME = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, secCode);
			pst.setDate(2, YssFun.toSqlDate(incomeDate));
			rs = pst.executeQuery();
			while (rs.next()) {
				fiHundIntBean = new FiPerHundredInterestBean();
				// 证券代码
				fiHundIntBean.setSecCode(rs.getString("C_SEC_CODE"));
				// 每百元日期
				fiHundIntBean.setAccrualDate(incomeDate);
				// 票面利率
				fiHundIntBean.setCouponRate(rs.getDouble("N_COUP_RATE"));
				// 剩余本金
				fiHundIntBean.setRemainMoney(rs.getDouble("N_REM_COR"));
				// 税前每百元利息(买卖使用)
				fiHundIntBean.setBeforeTaxInterest(rs.getDouble("N_INCOME_PT"));
				// 税后每百元利息(买卖使用)
				fiHundIntBean.setAfterTaxInterest(rs.getDouble("N_INCOME_AT"));
				// 税前每百元利息(计息使用)
				fiHundIntBean.setBeforeTaxInterestDue(rs
						.getDouble("N_INCOME_PT_DUE"));
				// 税后每百元利息(计息使用)
				fiHundIntBean.setAfterTaxInterestDue(rs
						.getDouble("N_INCOME_AT_DUE"));
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			throw e;
		} finally {
//			if (null != pst)
//				pst.close();
//			if (null != rs)
//				rs.close();
			//chenbo
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
//			DBUtils.cleanUp(rs, pst); //调用公共方法byleeyu20151015
		}
		return fiHundIntBean;
	}
	
	/**
	 * 查询某一日一批债券的每百元利息
	 * 
	 * @param conn
	 *            数据库连接
	 * @param secCodeArray
	 *            证券代码数组
	 * @param incomeDate
	 *            每百元利息日期
	 * @return
	 * @throws SQLException
	 */
	public static HashMap<String, FiPerHundredInterestBean> getUnqiueFiHundInt(Connection conn,
			java.sql.Array secCodeArray, Date incomeDate) throws SQLException {
		HashMap<String, FiPerHundredInterestBean> interestMap = new HashMap<String, FiPerHundredInterestBean>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "SELECT C_SEC_CODE,N_COUP_RATE,N_REM_COR,N_INCOME_PT,N_INCOME_AT,N_INCOME_PT_DUE,N_INCOME_AT_DUE "
				+ " FROM T_D_SV_FI_INCOME "
				+ " WHERE N_CHECK_STATE = 1 AND  C_SEC_CODE IN (SELECT * FROM TABLE(?)) AND D_INCOME = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setArray(1, secCodeArray);
			pst.setDate(2, YssFun.toSqlDate(incomeDate));
			rs = pst.executeQuery();
			while (rs.next()) {
				FiPerHundredInterestBean fiHundIntBean = new FiPerHundredInterestBean();
				// 证券代码
				fiHundIntBean.setSecCode(rs.getString("C_SEC_CODE"));
				// 每百元日期
				fiHundIntBean.setAccrualDate(incomeDate);
				// 票面利率
				fiHundIntBean.setCouponRate(rs.getDouble("N_COUP_RATE"));
				// 剩余本金
				fiHundIntBean.setRemainMoney(rs.getDouble("N_REM_COR"));
				// 税前每百元利息(买卖使用)
				fiHundIntBean.setBeforeTaxInterest(rs.getDouble("N_INCOME_PT"));
				// 税后每百元利息(买卖使用)
				fiHundIntBean.setAfterTaxInterest(rs.getDouble("N_INCOME_AT"));
				// 税前每百元利息(计息使用)
				fiHundIntBean.setBeforeTaxInterestDue(rs
						.getDouble("N_INCOME_PT_DUE"));
				// 税后每百元利息(计息使用)
				fiHundIntBean.setAfterTaxInterestDue(rs
						.getDouble("N_INCOME_AT_DUE"));
				interestMap.put(rs.getString("C_SEC_CODE"), fiHundIntBean);
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			throw e;
		} finally {
//			if (null != pst)
//				pst.close();
//			if (null != rs)
//				rs.close();
			//chenbo
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
//			DBUtils.cleanUp(rs, pst); //调用公共方法byleeyu20151015
		}
		return interestMap;
	}

	/**
	 * 根据结果集为每百元债券利息各项计算因子赋值
	 * 
	 * @param rs
	 * @param factor
	 */
	/*
	 * private void parseHundIntFactor(ResultSet rs, FiHundIntFactorBean factor)
	 * { try { if (null != rs && null != factor) { // 证券代码
	 * factor.setSecCode(rs.getString("C_SEC_CODE")); // 市场代码
	 * factor.setMarket(rs.getString("C_MKT_CODE")); // 调息日期
	 * factor.setAdjustDate(rs.getDate("D_ADJ")); // 是否启用新利率
	 * factor.setApplyNewRate(rs.getInt("C_DV_BOOL_TYPE") == 1 ? true : false);
	 * // 票面利率 factor.setCouponRate(rs.getDouble("N_COUP_RATE")); // 剩余本金
	 * factor.setRemainMoney(rs.getDouble("N_REM_COR")); // 当前付息期间起息日
	 * factor.setCurPeriodBeginDate(rs.getDate("D_BEGIN")); // 当前付息期间截息日
	 * factor.setCurPeriodEndDate(rs.getDate("D_END")); // 报价方式
	 * factor.setQuoteMode(rs.getString("C_DV_QUT_MOD")); // 发行面值
	 * factor.setIssueFaceValue(rs.getDouble("N_FV_ISSUE")); // 发行价格 // CL
	 * 20121129 发行价格 set错字段 factor.setIssuePrice(rs.getDouble("N_PRICE_ISSUE"));
	 * // 税率 factor.setTaxRate(rs.getDouble("N_RATE")); // 计息方式
	 * factor.setAccrualMode(rs.getString("C_DV_AI_MOD")); // 计息公式
	 * factor.setAccrualFormula(rs.getString("C_DV_AI_EXPR")); // 付息方式
	 * factor.setPayMode(rs.getString("C_DV_PI_MOD")); // 债券起息日
	 * factor.setBondBeginDate(rs.getDate("D_AI_BEGIN")); // 债券截息日
	 * factor.setBondEndDate(rs.getDate("D_AI_END")); // 付息频率
	 * factor.setPayFrequency(rs.getString("FREQUENCY")); }
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); }
	 * 
	 * }
	 */

	/**
	 * 从债券历史付息表中查询计息日期落在付息期间和债券信息
	 * 
	 * @return 每一只债券计算利息需要用到的计算因子对象
	 * @throws Exception
	 */
	/*
	 * private List<FiHundIntFactorBean> queryFiHundIntFactors(String bondCode,
	 * Date accraulDate) throws Exception { PreparedStatement pst = null;
	 * ResultSet rs = null; FiHundIntFactorBean factor = null;
	 * ArrayList<FiHundIntFactorBean> factors = null; String sql =
	 * "SELECT A.C_SEC_CODE," + " A.C_MKT_CODE," + " A.D_ADJ," +
	 * " A.C_DV_BOOL_TYPE," + " A.N_COUP_RATE," + " A.N_REM_COR," +
	 * " A.D_BEGIN," + " A.D_END," + " B.C_DV_QUT_MOD," + " B.N_FV_ISSUE," +
	 * " B.N_PRICE_ISSUE," + " B.N_RATE," + " B.C_DV_AI_MOD," +
	 * " B.C_DV_AI_EXPR," + " B.C_DV_PI_MOD," + " B.D_AI_BEGIN," +
	 * " B.D_AI_END," + " B.FREQUENCY" + " FROM T_D_SV_FI_PAY A" +
	 * " JOIN (SELECT C_SEC_CODE," + "  C_DV_QUT_MOD," + "  N_FV_ISSUE," +
	 * " N_PRICE_ISSUE," + "  N_RATE," + "  C_DV_AI_MOD," + "  C_DV_AI_EXPR," +
	 * "  C_DV_PI_MOD," + "  D_AI_BEGIN," + "  D_AI_END," +
	 * "	C_DV_VAR_DUR AS FREQUENCY" + " FROM T_P_SV_SEC_BASE" +
	 * " WHERE N_CHECK_STATE = 1) B ON A.C_SEC_CODE = B.C_SEC_CODE" +
	 * " WHERE A.N_CHECK_STATE = 1" + "  AND A.D_BEGIN <= ?" +
	 * " AND A.D_END >= ?" + " AND A.C_SEC_CODE = ?" + " ORDER BY A.D_ADJ";
	 * Connection conn = null; try { conn =
	 * DbPoolFactory.getInstance().getPool().getConnection(); pst =
	 * conn.prepareStatement(sql); pst.setDate(1,
	 * YssFun.toSqlDate(accraulDate)); pst.setDate(2,
	 * YssFun.toSqlDate(accraulDate)); pst.setString(3, bondCode); rs =
	 * pst.executeQuery(); while (rs.next()) { if (null == factors) { factors =
	 * new ArrayList<FiHundIntFactorBean>(); } factor = new
	 * FiHundIntFactorBean(); this.parseHundIntFactor(rs, factor);
	 * factors.add(factor); } } catch (SQLException e) { e.printStackTrace();
	 * throw (Exception) e; } finally { if (null != pst) pst.close(); if (null
	 * != rs) rs.close(); try { conn.close(); } catch (Exception ex) {
	 * ex.printStackTrace(); } } if (null != factors) factors.trimToSize();
	 * return factors; }
	 */

	/**
	 *功能： 根据债券的计息共识来计算计息天数
	 *修改人：zhengguiyu
	 *修改时间：2014-4-14
	 * @param fiHundIntFactorBean
	 * @param hundIntPojo
	 * @param accrualDate
	 */
	public void getFiPerHundredInterestDays(
			FiHundIntFactorBean fiHundIntFactorBean,
			FiPerHundredInterestBean hundIntPojo, Date accrualDate) {
		//如果计息公式为“FI_30_360”
		//BUG149454【钜盛华】【紧急】公共信息处理那里有10多个接口清算失败    空指针异常处理  by guohui 20170105
		if ("FI_30_360".equals(fiHundIntFactorBean.getAccrualFormula())) {
			//modified by dingshalu 20161220 BUG #147127 BUG单-海通a版本 债券每日利息计息天数计算错误
			//更改逻辑： 传入的参数accrualDate不再做+1日处理，而是在getDateDiff执行完的返回值+1日,
			//和计息公式保持一致(计息公式为30/360时，每百元计算公式=ROUND((360*(计息日期.y-起息日.y)+30*(计息日期.m-起息日.m)+(计息日期.d-起息日.d+1))/360*票面利率/100*剩余本金,保留位数)
			//hundIntPojo.setInterestDays(this.getDateDiff(
			//		fiHundIntFactorBean.getCurPeriodBeginDate(),
			//		YssFun.addDay(accrualDate, 1)));
			hundIntPojo.setInterestDays(this.getDateDiff(
					fiHundIntFactorBean.getCurPeriodBeginDate(),
					accrualDate) + 1);
		} else if ("FI_A_365F".equals(fiHundIntFactorBean.getAccrualFormula())
				|| "FI_A_A_BONDF".equals(fiHundIntFactorBean.getAccrualFormula())) {
			//如果计息公式为“FI_A_365F”
			Date beginDate = fiHundIntFactorBean.getCurPeriodBeginDate();
			Date currentDate = accrualDate;
			
			//BUG #144591 嘉实基金---债券基本信息中查询每百元利息，计息天数显示不对
			//int count = function.LEAPYEARS(beginDate, currentDate);
			//计算日期区间内跨越的2月29号的个数
			int count = this.COUNT2And29(beginDate, currentDate);
			
			hundIntPojo.setInterestDays(YssFun.dateDiff(
					fiHundIntFactorBean.getCurPeriodBeginDate(), accrualDate)
					+ 1 - count);// 已计提天数
		// //add by huangshui 20161207 STORY35683【招商基金】增加债券计息公式：大月31号不计息，2月份 28/29号视平闰年计3/2天息
	    }else if (fiHundIntFactorBean.getAccrualFormula().equals("FI_30ST_360")) {
			hundIntPojo.setInterestDays(this.getDateDiffFor30ST(
					fiHundIntFactorBean.getCurPeriodBeginDate(),
					YssFun.addDay(accrualDate, 0)));
        //add by dingshalu 20161017 STORY33515 （紧急）境外债券计息公式缺失
		} else if (fiHundIntFactorBean.getAccrualFormula().equals("FI_30E_360")) {
			hundIntPojo.setInterestDays(this.getDateDiffFor30E(
					fiHundIntFactorBean.getCurPeriodBeginDate(),
					YssFun.addDay(accrualDate, 1)));			
		}else if (fiHundIntFactorBean.getAccrualFormula().equals("FI_30U_360")) {
			hundIntPojo.setInterestDays(this.getDateDiffFor30U(fiHundIntFactorBean, accrualDate));
			//add by lqm 20170411	STORY#40928---	嘉实基金qd--合并需求STORY35683增加债券计息公式：大月31号不计息，2月份 28/29号视平闰年计3/2天息
		}
		else {
			//付息频率为“一次性” 且 倒置算法为“计息到期日轧差” 时 , 调息日期为“计息日期”当天 ， 已计息天数为1天
			//BUG #126780 【紧急】【招商基金】信托的付息频率为一次性倒置算法为计息到期日扎差时计息天数和税前税后利息计息不对  edit by LY 20160223
			if(fiHundIntFactorBean.getPayFrequency().equals("FI_ONCE") && fiHundIntFactorBean.getBackWard() != null && fiHundIntFactorBean.getBackWard().equals("JXRGC")){
				hundIntPojo.setInterestDays(1);// 已计提天数
			}
			else{
			//其他情况
			hundIntPojo
					.setInterestDays(YssFun.dateDiff(
							fiHundIntFactorBean.getCurPeriodBeginDate(),
							accrualDate) + 1);// 已计提天数
			}
		}
	}
	
	/**
	 * 计算日期区间内跨越的2月29号的个数
	 * @param beginDate 开始日期
	 * @param endDate 结束日期
	 * @return
	 */
	private int COUNT2And29(Date beginDate, Date endDate) {
		
		int num = 0;
		int y1 = YssFun.getYear(beginDate);
		int y2 = YssFun.getYear(endDate);
		
		String smd1 = YssFun.formatDate(beginDate, "MMdd");
		String smd2 = YssFun.formatDate(endDate, "MMdd");
		
		

		//计算年份差
		int deltaY = y2-y1;
		
		if(deltaY==0)
		{
		  //区间不跨年
          //是闰年且日期区间包含2月29即可	  
			if(YssFun.isLeapYear(y1)&&smd1.compareTo("0228") <= 0 && smd2.compareTo("0228") > 0)
				  num++;
		}
		else
		{
			//区间跨年
			
			//处理起始日期所在年
			//是闰年并且日期在2月29之前即可
			if(YssFun.isLeapYear(y1) && smd1.compareTo("0228") <= 0)
				  num++;
			
			//处理 起始日期与结束日期之间的年份，
			//只要是闰年则必包含2月29
			for(int i = y1+1; i < y2; i++)
				if(YssFun.isLeapYear(i))
			       num++;
			
			//处理结束日期所在年
			//是闰年且日期在2月28之后即可
			if(YssFun.isLeapYear(y2) && smd2.compareTo("0228") > 0)
				  num++;
			
		}
		return num;
	}
	/**
	 * 获取某一日期区间内，一批债券的每百元利息<br>
	 * xiaozhilong 20160322<br>
	 * STORY #29319 股票、债券、基金派息，债券还本、兑付优化<br>
	 * @param conn 链接
	 * @param secCodes 债券代码
	 * @param maxDate 日期区间最大日期
	 * @param minDate 日期区间最小日期
	 * @return Map : secCode+d_income, 每百元利息POJO
	 * @throws Exception
	 */
	public static HashMap<String, FiPerHundredInterestBean> getBatchFiHundInt(Connection conn,
			String secCodes, Date maxDate, Date minDate) throws Exception {
		HashMap<String, FiPerHundredInterestBean> interestMap = new HashMap<String, FiPerHundredInterestBean>();
		if (null == maxDate || null == minDate) {
			return interestMap;
		}
		PreparedStatement pst = null;
		ResultSet rs = null;
		StringBuffer buffer = null;
		try {
			buffer = new StringBuffer();
			buffer.append(" SELECT C_SEC_CODE,N_COUP_RATE,N_REM_COR,N_INCOME_PT,N_INCOME_AT,N_INCOME_PT_DUE,N_INCOME_AT_DUE, ");
			buffer.append(" D_INCOME FROM T_D_SV_FI_INCOME WHERE N_CHECK_STATE = 1");
			buffer.append(" AND C_SEC_CODE IN (SELECT * FROM TABLE(?)) ");
			buffer.append(" AND D_INCOME BETWEEN ? AND ? ");
			pst = conn.prepareStatement(buffer.toString());
			pst.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(secCodes,conn));
			pst.setDate(2, YssFun.toSqlDate(minDate));
			pst.setDate(3, YssFun.toSqlDate(maxDate));
			rs = pst.executeQuery();
			while (rs.next()) {
				FiPerHundredInterestBean fiHundIntBean = new FiPerHundredInterestBean();
				// 证券代码
				fiHundIntBean.setSecCode(rs.getString("C_SEC_CODE"));
				// 每百元日期
				fiHundIntBean.setAccrualDate(rs.getDate("D_INCOME"));
				// 票面利率
				fiHundIntBean.setCouponRate(rs.getDouble("N_COUP_RATE"));
				// 剩余本金
				fiHundIntBean.setRemainMoney(rs.getDouble("N_REM_COR"));
				// 税前每百元利息(买卖使用)
				fiHundIntBean.setBeforeTaxInterest(rs.getDouble("N_INCOME_PT"));
				// 税后每百元利息(买卖使用)
				fiHundIntBean.setAfterTaxInterest(rs.getDouble("N_INCOME_AT"));
				// 税前每百元利息(计息使用)
				fiHundIntBean.setBeforeTaxInterestDue(rs
						.getDouble("N_INCOME_PT_DUE"));
				// 税后每百元利息(计息使用)
				fiHundIntBean.setAfterTaxInterestDue(rs
						.getDouble("N_INCOME_AT_DUE"));
				interestMap.put(rs.getString("C_SEC_CODE")+YssFun.formatDate(rs.getDate("D_INCOME")), fiHundIntBean);
			}
		} catch (Exception e) {
//			e.printStackTrace();
			throw e;
		} finally {
			//chenbo
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
//			DBUtils.cleanUp(rs, pst);
			StringUtil.clearStringBuffer(buffer);
		}
		return interestMap;
	}
	
	/**
	 * 获取上一历史付息
	 * @throws SQLException
	 */
	public FiHundIntFactorBean getLastFiHund(String secCode, Date adjDate) throws Exception {
		FiHundIntFactorBean fiHundBean = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String sql = "SELECT * FROM T_D_SV_FI_PAY WHERE C_SEC_CODE = ? AND D_ADJ < ? AND N_CHECK_STATE = 1 ORDER BY D_ADJ DESC";
		try {
			conn = YssDbPoolFactory.getInstance().getDbPool(SecInfoActivator.class).getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, secCode);
			pst.setDate(2, YssFun.toSqlDate(adjDate));
			rs = pst.executeQuery();
			if (rs.next()) {
				fiHundBean = new FiHundIntFactorBean();
				// 票面利率
				fiHundBean.setCouponRate(rs.getDouble("N_COUP_RATE"));
				// 剩余本金
				fiHundBean.setRemainMoney(rs.getDouble("N_REM_COR"));
			}
		} catch (Exception e) {
			//e.printStackTrace();
			throw e;
		} finally {
			//chenbo
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
//			DBUtils.cleanUp(rs, pst);
			DbFun.releaseConnection(conn);
		}
		return fiHundBean;
	}
	/**
	 * 计息公式为"FI_30U_360"时，获得计息天数
	 * @param fiHundIntFactorBean
	 * @param accrualDate
	 * @return
	 */
	public int getDateDiffFor30U(FiHundIntFactorBean fiHundIntFactorBean,Date accrualDate){
		Calendar beginCal1 = Calendar.getInstance();
		Calendar accrCal1 = Calendar.getInstance();
		beginCal1.setTime(fiHundIntFactorBean.getCurPeriodBeginDate());
		accrCal1.setTime(accrualDate);		
		if (accrCal1.get(Calendar.DAY_OF_MONTH) == 31
				&& ((beginCal1.get(Calendar.DAY_OF_MONTH) == 30 || beginCal1
						.get(Calendar.DAY_OF_MONTH) == 31))) {
			accrCal1.set(Calendar.DAY_OF_MONTH, 30);
			
		}
		if (beginCal1.get(Calendar.DAY_OF_MONTH) == 31) {
			beginCal1.set(Calendar.DAY_OF_MONTH, 30);
		}
		java.util.Date beginDate =beginCal1.getTime();
		java.util.Date accrDate =accrCal1.getTime();
		int days=this.getDateDiff(beginDate,accrDate)+1;
		return days;
	}
	
	/**
	 * #155809 嘉实基金-债券历史付息，初始化债券每百元利息时，系统报错，导致债券每百元历史付息信息被删除
	 * 删除保存进行事务控制
	 * @throws SQLException
	 */	
	public void insertAllHundredInterest(List<FiPerHundredInterestBean> perHundIntPojos) throws Exception {
		Connection dbConn = null;
		try {
			//获取连接
			dbConn = dao.loadNewConnection();    	
			//事务开始
			dbConn.setAutoCommit(false);			
			// 删除每百元利息
			deletePerHundInt(perHundIntPojos,dbConn);
			// 保存每百元利息
			savePerHundInt(perHundIntPojos,dbConn);
			//事务提交
			dbConn.commit();
			dbConn.setAutoCommit(true);
		} catch (Exception e) {
			throw new Exception(e);
		}finally{
			dao.releaseConnection(dbConn);
		}

	}
}
