package com.yss.ams.sec.information.modules.sv.base.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.yss.ams.sec.information.activator.SecInfoActivator;
import com.yss.ams.sec.information.modules.sv.base.dao.SecBaseDao;
import com.yss.ams.sec.information.modules.sv.base.dao.SecBaseLcDao;
import com.yss.ams.sec.information.modules.sv.base.dao.SecBaseLcSqlBuilder;
import com.yss.ams.sec.information.support.modules.pub.service.IAssetStatsCtlInitService;
import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecBase;
import com.yss.ams.sec.information.support.modules.sv.base.service.ISecBaseLcService;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.cache.assist.DefaultCacheRefresh;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.util.VocabularyConsts;
import com.yss.framework.api.util.YssFun;
import com.yss.framework.util.DateUtil;
import com.yss.mvc.returninfo.ReturnInfoGenerator;
import com.yss.platform.support.comm.pojo.Remind;
import com.yss.platform.support.comm.pojo.RemindPolicy;

@DefaultCacheRefresh(group = CacheGroup.SECBASE)
public class SecBaseLcService extends ServiceBus<SecBaseService> implements
		ISecBaseLcService {

	private SecBaseDao serviceDao = null;
	//马向峰  注释  引用其他项目
//	private StockDao stockDao = null;
	private SecBaseLcDao secLcDao = null;

	public SecBaseLcService() throws Exception {
		//马向峰  注释  引用其他项目
//		stockDao = new StockDao(YssDbPoolFactory.getInstance().getDbPool(SecInfoActivator.class),
//				new StockSqlBuilder());
		serviceDao = new SecBaseDao(YssDbPoolFactory.getInstance().getDbPool(SecInfoActivator.class),
				new SecBaseLcSqlBuilder());
		dao = serviceDao;
		secLcDao = new SecBaseLcDao(YssDbPoolFactory.getInstance().getDbPool(SecInfoActivator.class),
				new SecBaseLcSqlBuilder());
	}

	/*
	 * add by fjl 20131219 需求13983 start 1.不具有审核机制时在新增和修改时进行初始化操作;
	 * 2.具有审核机制时保存和修改时不进行初始化操作，在审核时进行。
	 */

//	@Override
//	public String insert(List<BasePojo> pojoList) {
//		String retStr = super.insert(pojoList);
//		if (retStr.contains("Success")) {
//			if (safeData.getN_CHECK() == 0) {
//				sysInitOper(pojoList);
//			}
//		}
//		return retStr;
//	}

//	@Override
//	public String updateById(List<BasePojo> pojoList) {
//		String retStr = super.updateById(pojoList);
//		if (retStr.contains("Success")) {
//			if (safeData.getN_CHECK() == 0) {
//				sysInitOper(pojoList);
//			}
//		}
//		return retStr;
//	}

//	@Override
//	public String auditById(List<BasePojo> pojoList) {
//		String retStr = super.auditById(pojoList);
//		if (retStr.contains("Success")) {
//			if (safeData.getN_CHECK() == 1) {
//				sysInitOper(pojoList);
//			}
//		}
//		return retStr;
//	}

	/**
	 * add by fjl 20131219 需求13983 ：添加调用系统初始化
	 */
	//马向峰  注释  引用其他项目
	/*public void sysInitOper() {
		IAssetStatsCtlInitService iAssetStatsCtlInitService = YssServiceFactory
				.getInstance().createService(IAssetStatsCtlInitService.class);
		// 获取最近一个库存日期
		Date lastStockDate = stockDao.getLastStockDate();
		HashMap<String, Object> hmData = new HashMap<String, Object>();
		hmData.put("C_ITEM_CODE", "initBondPerHundInter,");
		hmData.put("D_END_DATE", YssFun.formatDate(lastStockDate, "yyyy-MM-dd"));
		hmData.put("D_BEGIN_DATE",
				YssFun.formatDate(lastStockDate, "yyyy-MM-dd"));
		hmData.put("C_OPER_CODE", String.valueOf(new Date().getTime()));
		iAssetStatsCtlInitService.doBusOper(hmData);
	}*/

//	/**
//	 * 根据一组pojo 初始化一组债券每百元债券利息 By Jinghehe 2014-3-15
//	 * 
//	 * @param pojoList
//	 */
//	private void sysInitOper(List<BasePojo> pojoList) {
//
//		if (null == pojoList || (null != pojoList && pojoList.isEmpty())) {
//			return;
//		}
//		try {
//			IAssetStatsCtlInitService iAssetStatsCtlInitService = YssServiceFactory
//					.getInstance().createService(
//							IAssetStatsCtlInitService.class);
//			// 获取最近一个库存日期
//			Date lastStockDate = stockDao.getLastStockDate();
//			// 证券code list
//			List<String> secCodeList = new ArrayList<String>();
//			for (BasePojo basePojo : pojoList) {
//				secCodeList.add(((SecBase) basePojo).getC_SEC_CODE());
//			}
//
//			iAssetStatsCtlInitService.initBondPerHundInterest(lastStockDate,
//					lastStockDate, secCodeList);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/* add by fjl 20131219 需求13983 end */

	/**
	 * 货币基金分红转投日提醒
	 * 
	 * @param policyMap 策略集合
	 * @return 消息集合
	 * @author liuxiang 2013/12/23
	 *         <P>
	 *         STORY #14414 理财品种信息（货币基金）界面改造与提醒
	 */
	//马向峰  注释  引用其他项目
	/*public List<Remind> remindFhztDate(Map<String,RemindPolicy> policyMap)
			throws Exception {
		PaySettService paySettService = new PaySettService();
		List<Remind> reminds = new ArrayList<Remind>();
		Date fhztDate = null; // 分红转投日期
		int times = 0; // 循环次数
		int way = -1; // 周期方向
		Date currDate = DateUtil.toDate(DateUtil
				.getCurrDate(DateUtil.LONG_DATE_FORMAT));// 当前日期
		RemindPolicy remindPolicy = policyMap.get(UCORemindService.REMIND14);
		if(remindPolicy != null){
			List<SecBase> secBases = secLcDao.remindFhztDate();
			for (SecBase secBase : secBases) {
				times = 0;
				way = -1;
				fhztDate = calcDateAtCycle(currDate, secBase.getC_DV_ISSUE(),
						way, times);
				BaseMktCache mktCache = CacheManager.getInstance().getCache(
						CacheGroup.BASEMKT);
				Mkt mkt = mktCache.getCacheByKey(secBase.getC_MKT_CODE());
				fhztDate = paySettService.calcOffsetDate(fhztDate, mkt
						.getC_HDAY_CODE(), secBase.getC_DV_ASSURE(), secBase
						.getN_PRICE_ISSUE().intValue());
				if (null == fhztDate)
					continue;

				if (DateUtil.dayDiff(currDate, fhztDate) > remindPolicy.getDay()) {// 分投日期大于提醒天数
					way = -1;// 周期向前
					do {
						times++;
						fhztDate = calcDateAtCycle(currDate,
								secBase.getC_DV_ISSUE(), way, times);
						fhztDate = paySettService.calcOffsetDate(fhztDate,
								mkt.getC_HDAY_CODE(), secBase.getC_DV_ASSURE(),
								secBase.getN_PRICE_ISSUE().intValue());

						if (null == fhztDate)
							break;

						if (DateUtil.dayDiff(currDate, fhztDate) > remindPolicy.getDay()) { // 周期向前
						} else if (DateUtil.dayDiff(currDate, fhztDate) < 0) { // 未找到结束
							fhztDate = null;
							way = 1;
						} else {
							way = 0; // 找到正确周期
						}
					} while (way == -1 && times < remindPolicy.getDay());

				} else if (DateUtil.dayDiff(currDate, fhztDate) < 0) { // 分投日期小于当前时间
					way = 1;// 周期向后
					do {
						times++;
						fhztDate = calcDateAtCycle(currDate,
								secBase.getC_DV_ISSUE(), way, times);
						fhztDate = paySettService.calcOffsetDate(fhztDate,
								mkt.getC_HDAY_CODE(), secBase.getC_DV_ASSURE(),
								secBase.getN_PRICE_ISSUE().intValue());

						if (null == fhztDate)
							break;

						if (DateUtil.dayDiff(currDate, fhztDate) < 0) { // 周期向后
						} else if (DateUtil.dayDiff(currDate, fhztDate) > remindPolicy.getDay()) { // 未找到结束
							fhztDate = null;
							way = -1;
						} else {
							way = 0; // 找到正确周期
						}
					} while (way == 1 && times < remindPolicy.getDay());
				}

				if (null == fhztDate) { // 未找到结束
					continue;
				}

				Remind remind = new Remind();
				remind.setMescontent("组合[" + secBase.getC_ORG_CODE() + "]的证券["
						+ secBase.getC_SEC_CODE() + "_"
						+ secBase.getC_SEC_NAME() + "]将于["
						+ YssFun.formatDate(fhztDate, "yyyy-MM-dd")
						+ "]进行分红转投！");
				remind.setMestype(remindPolicy.getMessname());
				remind.setOrgsecurity(secBase.getC_ORG_NAME());
				remind.setDqdate(YssFun.formatDate(fhztDate, "yyyy-MM-dd"));
				remind.setC_SEC_CODE(secBase.getC_SEC_CODE());
				remind.setC_SEC_NAME(secBase.getC_SEC_NAME());
				reminds.add(remind);
			}
		}
		return reminds;
	}*/

	/**
	 * 根据转投周期计算转投基准日期
	 * 
	 * @param accDate
	 *            业务日期
	 * @param cycleType
	 *            周期类型
	 * @param way
	 *            周期方向 1向后, -1向前
	 * @param times
	 *            循环次数
	 * @return
	 */
	public Date calcDateAtCycle(Date accDate, String cycleType, int way,
			int times) {
		if (null == accDate || null == cycleType) {
			return null;
		}
		Date dateAtCycle = null;
		if (cycleType.equalsIgnoreCase(VocabularyConsts.EVERY_DAY)) {
			// 如果结转周期为每天，则返回当天
			dateAtCycle = DateUtil.nextDay(accDate, way * times);
		} else if (cycleType.equalsIgnoreCase(VocabularyConsts.EVERY_MONTH)) {
			// 如果结转周期为每月，则返回n个月的最后一日
			dateAtCycle = DateUtil.nextMonth(accDate, way * times);
			dateAtCycle = DateUtil.getFirstNatureDayOnMonth(dateAtCycle);
			dateAtCycle = DateUtil.nextDay(dateAtCycle, -1);
		} else if (cycleType.equalsIgnoreCase(VocabularyConsts.EVERY_QUARTER)) {
			// 如果结转周期为每季，则返回12.31、3.31、6.30、9.30
			dateAtCycle = DateUtil.nextMonth(accDate, 3 * way * times);
			dateAtCycle = DateUtil.getFirstNatureDayOnQuarter(dateAtCycle);
			dateAtCycle = DateUtil.nextDay(dateAtCycle, -1);
		} else if (cycleType.equalsIgnoreCase("MZ")) {
			//add by liyanjun 2016-8-18 STORY24572 需要可以对不同的货币基金设置对应的红利转投资提醒数据，然后在指定日期进行提醒
			// 如果结转周期为每周，n周的最后一天(周日是一周的开始)
			dateAtCycle = DateUtil.nextMonth(accDate, way * times);
			dateAtCycle = DateUtil.getFirstNatureDayOnWeek(dateAtCycle);
			if(YssFun.dateDiff(accDate, dateAtCycle) == 0){
				dateAtCycle = DateUtil.nextDay(dateAtCycle, -7);
			}
		} else {
			dateAtCycle = accDate;
		}
		return dateAtCycle;
	}
	
	//马向峰  注释  引用其他项目
	/*public String singleSecInitFi(String secCode) throws Exception {
		IAssetStatsCtlInitService iAssetStatsCtlInitService = 
			YssServiceFactory.getInstance().createService(IAssetStatsCtlInitService.class);
		SvSecBaseCache secCache = CacheManager.getInstance().getCache(CacheGroup.SVSECBASE);
		SecBase secInfo = secCache.getCacheByKey(secCode);
		if(null == secInfo ){
			return null;
		}else{
		HashMap<String, Object> hmData = new HashMap<String, Object>();
		hmData.put("C_ITEM_CODE", "initBondPerHundInter,");
		hmData.put("D_BEGIN_DATE", YssFun.formatDate(secInfo.getD_AI_BEGIN(), "yyyy-MM-dd"));
		hmData.put("D_END_DATE", YssFun.formatDate(new Date(), "yyyy-MM-dd"));
		hmData.put("C_OPER_CODE", String.valueOf(new Date().getTime()));
		iAssetStatsCtlInitService.initSingleSecBond(secInfo, hmData);
		return ReturnInfoGenerator.getUpdateOKStr();
		}
	}*/
	
	@Override
	public String multipleSecInitFi(List<SecBase> secList) throws Exception {
		//马向峰  注释  引用其他项目
		 IAssetStatsCtlInitService iAssetStatsCtlInitService = 
				YssServiceFactory.getInstance().createService(IAssetStatsCtlInitService.class);
		for(SecBase secBase : secList) {
			HashMap<String, Object> hmData = new HashMap<String, Object>();
			hmData.put("C_ITEM_CODE", "initBondPerHundInter,");
			hmData.put("D_BEGIN_DATE", YssFun.formatDate(secBase.getD_AI_BEGIN(), "yyyy-MM-dd"));
			hmData.put("D_END_DATE", YssFun.formatDate(new Date(), "yyyy-MM-dd"));
			hmData.put("C_OPER_CODE", String.valueOf(new Date().getTime()));
			iAssetStatsCtlInitService.initSingleSecBond(secBase, hmData);
		}
		return ReturnInfoGenerator.getUpdateOKStr(); 
	}

	@Override
	public List<BasePojo> getSecBasesByCondition(HashMap<String, Object> paraMap) {
		return null;
	}

	@Override
	public String singleSecInitFi(String secCode) throws Exception {
		return null;
	}

	
}
