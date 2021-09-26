package com.yss.ams.sec.information.modules.sv.fiincome.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import com.yss.ams.sec.information.modules.sv.fiincome.dao.FiIncomeDao;
import com.yss.ams.sec.information.modules.sv.fiincome.dao.FiIncomeSqlBuilder;
import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecBase;
import com.yss.ams.sec.information.support.modules.sv.fiincome.service.IFiIncomeService;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.util.VocabularyConsts;
import com.yss.framework.api.util.YssD;
import com.yss.framework.api.util.YssFun;
import com.yss.framework.db.DbPoolFactory;

/**
 * 债券每日利息实现类
 * @author yuankai
 * 资讯信息拆分	2017.7.3 STORY #42948 资讯信息管理组件化拆分
 *
 */
public class FiIncomeService extends ServiceBus<FiIncomeService> implements
		IFiIncomeService {

	private FiIncomeDao serviceDao = null;

	public FiIncomeService() throws Exception {
		serviceDao = new FiIncomeDao(DbPoolFactory.getInstance().getPool(), new FiIncomeSqlBuilder());
		dao = serviceDao;
	}

	/**
	 * 根据每百元利息界面点击计算税前利息按钮计算方法
	 * @param paraMap
	 * @return 
	 */
	@Override
	public String calcBeforeTaxAndDue(HashMap<String, Object> paraMap) throws Exception {
		/**
		int point = 8;// 保留位数
		String secCode = String.valueOf(paraMap.get("C_SEC_CODE"));  //证券代码
		Date accDate = YssFun.toDate(String.valueOf(paraMap.get("D_DATE")));  //计息日期
		double days = Double.parseDouble(String.valueOf(paraMap.get("N_DAYS")));  //计息天数
		double coupRate = Double.parseDouble(String.valueOf(paraMap.get("N_COUP_RATE")));  //票面利率
		double remCor = Double.parseDouble(String.valueOf(paraMap.get("N_REM_COR")));  //剩余本金
		SvSecBaseCache secCache = CacheManager.getInstance().getCache(CacheGroup.SECBASE);
		SecBase secInfo = secCache.getCacheByKey(secCode);
		BaseMktCache mktCache = CacheManager.getInstance().getCache(CacheGroup.MKT);
		Mkt mkt = mktCache.getCacheByKey(secInfo.getC_MKT_CODE());
		if (mkt != null
				&& mkt.getC_DV_MKT_TYPE()
						.equalsIgnoreCase(VocabularyConsts.OTC)) {
			point = 12;
		}
		// 二级市场保留8位小数
		else if (mkt != null
				&& mkt.getC_DV_MKT_TYPE()
						.equalsIgnoreCase(VocabularyConsts.FTM)) {
			point = 8;
		}
		FiHundIntFactorBean fiHundIntFactorBean = new FiHundIntFactorBean();
		FiHundFactorService factorService = new FiHundFactorService();
		List<FiHundIntFactorBean> factorMap = factorService
				.queryFiHundIntFactors(secCode, accDate);
		if (VocabularyConsts.FI_A_365F.equalsIgnoreCase(secInfo
				.getC_DV_AI_EXPR())
//				| VocabularyConsts.FI_A_365.equalsIgnoreCase(secInfo
				|| VocabularyConsts.FI_A_365.equalsIgnoreCase(secInfo
						.getC_DV_AI_EXPR())) {
			return new BigDecimal(days/365.0*coupRate/100.0*remCor).setScale(point, BigDecimal.ROUND_HALF_UP).toPlainString();
		} else if (VocabularyConsts.FI_30_360.equalsIgnoreCase(secInfo
				.getC_DV_AI_EXPR())) {
			return new BigDecimal(days/360.0*coupRate/100.0*remCor).setScale(point, BigDecimal.ROUND_HALF_UP).toPlainString();
		//STORY36544【招商基金】银行间债券XYDZ01计息公式A/360：支持在计息期间第二年度的第一天计息冲减上一年度多计息的金额  add by liuyanni 2016-12-02
		} else if (VocabularyConsts.FI_A_360.equalsIgnoreCase(secInfo.getC_DV_AI_EXPR())) {
			if(factorMap.size()>0) {
				fiHundIntFactorBean = factorMap.get(0);
				int years = 0;
				Date qxDate = fiHundIntFactorBean.getCurPeriodBeginDate();
				while(YssFun.dateDiff(YssFun.addYear(qxDate,1),accDate) >= 0){
					years ++ ;
					qxDate = YssFun.addYear(qxDate,1);
				}
				days = YssFun.dateDiff(qxDate, accDate) + 1;
				return new BigDecimal(YssD.div(YssD.mul(years, coupRate, remCor),100) + days/360.0*coupRate/100.0*remCor).setScale(point, BigDecimal.ROUND_HALF_UP).toPlainString();
			}
		} else if (VocabularyConsts.FI_A_A.equalsIgnoreCase(secInfo
						.getC_DV_AI_EXPR())) {
			if(factorMap.size()>0) {
				fiHundIntFactorBean = factorMap.get(0);
				return new BigDecimal(days
						/ (365.0 + YssFun.getLeapYears(
								fiHundIntFactorBean.getCurPeriodBeginDate(),
								fiHundIntFactorBean.getCurPeriodEndDate()))
						* coupRate / 100.0 * remCor).setScale(point, BigDecimal.ROUND_HALF_UP).toPlainString();
			}
		} else if(VocabularyConsts.FI_A_A_BOND.equalsIgnoreCase(secInfo
				.getC_DV_AI_EXPR())) {
			if(factorMap.size()>0) {
				fiHundIntFactorBean = factorMap.get(0);
				return new BigDecimal(days
						/ (YssFun.dateDiff(
								fiHundIntFactorBean.getCurPeriodBeginDate(),
								fiHundIntFactorBean.getCurPeriodEndDate()) + 1)
						* coupRate
						/ (AdmFactRate.convertFreqToNum(fiHundIntFactorBean.getPayFrequency(),accDate)) / 100.0 * remCor).setScale(point, BigDecimal.ROUND_HALF_UP).toPlainString();
			}
		}
		*/
		return "0";
	}

	
}
