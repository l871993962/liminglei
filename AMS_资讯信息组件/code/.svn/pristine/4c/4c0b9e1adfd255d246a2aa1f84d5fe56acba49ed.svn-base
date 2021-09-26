package com.yss.ams.sec.information.modules.mp.preStockInterest.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.sec.information.activator.SecInfoActivator;
import com.yss.ams.sec.information.modules.mp.preStockInterest.dao.PreStockInterestDao;
import com.yss.ams.sec.information.modules.mp.preStockInterest.dao.PreStockInterestSqlBuilder;
import com.yss.ams.sec.information.support.cache.SecBaseCache;
import com.yss.ams.sec.information.support.modules.mp.preStockInterest.pojo.PreStockInterest;
import com.yss.ams.sec.information.support.modules.mp.preStockInterest.service.IPreStockInterestService;
import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecBase;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.cache.assist.DefaultCacheRefresh;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.util.YssFun;
import com.yss.framework.db.DbPoolFactory;
import com.yss.mvc.returninfo.ReturnInfoGenerator;


/**
 * 优先股计息信息实现类
 * @author yuankai
 * 资讯信息拆分	2017.7.3 STORY #42948 资讯信息管理组件化拆分
 *
 */
@DefaultCacheRefresh(group = CacheGroup.SECBASE)
public class PreStockInterestService extends ServiceBus<PreStockInterest> implements
IPreStockInterestService{
	private PreStockInterestDao serviceDao = null;
//	private StockDao stockDao = null;
	public PreStockInterestService() throws Exception {
		serviceDao = new PreStockInterestDao(DbPoolFactory.getInstance().getPool(), new PreStockInterestSqlBuilder());
//		stockDao = new StockDao(YssDbPoolFactory.getInstance().getDbPool(SecInfoActivator.class), new StockSqlBuilder());
		dao = serviceDao;
	}

	/**
	 * 优先股计息信息生成历史计息信息
	 * @param pre
	 * @return
	 * @throws Exception
	 */
	@Override
	public String singleSecInitFi(PreStockInterest pre) throws Exception {
//		IAssetStatsCtlInitService iAssetStatsCtlInitService = 
//				YssServiceFactory.getInstance().createService(IAssetStatsCtlInitService.class);
			SecBaseCache secCache = CacheManager.getInstance().getCache(CacheGroup.SECBASE);
			SecBase secBase = secCache.getCacheByKey(pre.getC_SEC_CODE());
			if(secBase != null){
			secBase.setC_SEC_CODE_TRG(pre.getC_SEC_CODE_TRG());
			secBase.setD_AI_BEGIN(pre.getD_AI_BEGIN().toString());
			secBase.setD_AI_END(pre.getD_AI_END().toString());
			secBase.setN_FV_IR(pre.getN_FV_IR());
			secBase.setN_RATE(pre.getN_RATE());
			secBase.setC_DV_AI_MOD(pre.getC_DV_AI_MOD());
			secBase.setC_DV_AI_EXPR(pre.getC_DV_AI_EXPR());
			secBase.setC_DV_VAR_DUR(pre.getC_DV_VAR_DUR());
			secBase.setC_DV_PI_MOD(pre.getC_DV_PI_MOD());
			// add by liyanjun 2016-9-27 BUG #141675 优先股业务测试问题汇总01
			secBase.setD_AI_BEGIN(YssFun.formatDate(pre.getD_AI_BEGIN(),"yyyy-MM-dd"));
			secBase.setD_AI_END(YssFun.formatDate(pre.getD_AI_END(),"yyyy-MM-dd"));
			HashMap<String, Object> hmData = new HashMap<String, Object>();
			hmData.put("C_ITEM_CODE", "initBondYxgPerHund,");
			hmData.put("D_BEGIN_DATE", YssFun.formatDate(secBase.getD_AI_BEGIN(), "yyyy-MM-dd"));
			hmData.put("D_END_DATE", YssFun.formatDate(secBase.getD_AI_END(), "yyyy-MM-dd"));
			hmData.put("C_OPER_CODE", String.valueOf(new Date().getTime()));
//			iAssetStatsCtlInitService.initSingleSecBond(secBase, hmData);
			}
			
			return ReturnInfoGenerator.getUpdateOKStr();
	}

	/**
	 * 生成历史计息信息
	 * @param preList
	 * @return
	 * @throws Exception
	 */
	@Override
	public String multiplePreInitFi(List<PreStockInterest> preList) throws Exception {
//		IAssetStatsCtlInitService iAssetStatsCtlInitService = 
//				YssServiceFactory.getInstance().createService(IAssetStatsCtlInitService.class);
		List<SecBase> secList = new ArrayList<SecBase>();
		if (preList != null)
		{
			for (PreStockInterest pre:preList)
			{
				SecBaseCache secCache = CacheManager.getInstance().getCache(CacheGroup.SECBASE);
				SecBase secBase = secCache.getCacheByKey(pre.getC_SEC_CODE());
				secBase.setC_SEC_CODE_TRG(pre.getC_SEC_CODE_TRG());
				secBase.setD_AI_BEGIN(YssFun.formatDate(pre.getD_AI_BEGIN(),"yyyy-MM-dd"));
				secBase.setD_AI_END(YssFun.formatDate(pre.getD_AI_END(),"yyyy-MM-dd"));
				secBase.setN_FV_IR(pre.getN_FV_IR());
				secBase.setN_RATE(pre.getN_RATE());
				secBase.setC_DV_AI_MOD(pre.getC_DV_AI_MOD());
				secBase.setC_DV_AI_EXPR(pre.getC_DV_AI_EXPR());
				secBase.setC_DV_VAR_DUR(pre.getC_DV_VAR_DUR());
				secBase.setC_DV_PI_MOD(pre.getC_DV_PI_MOD());
				secList.add(secBase);
			}
			for(SecBase secBase : secList) {
				HashMap<String, Object> hmData = new HashMap<String, Object>();
				hmData.put("C_ITEM_CODE", "initBondYxgPerHund,");
				hmData.put("D_BEGIN_DATE", YssFun.formatDate(secBase.getD_AI_BEGIN(), "yyyy-MM-dd"));
				hmData.put("D_END_DATE", YssFun.formatDate(secBase.getD_AI_END(), "yyyy-MM-dd"));
				hmData.put("C_OPER_CODE", String.valueOf(new Date().getTime()));
//				iAssetStatsCtlInitService.initSingleSecBond(secBase, hmData);
			}
			return ReturnInfoGenerator.getUpdateOKStr();
		}else
		{
			return "";
		}
	}
	
	/**
	 * 添加调用系统初始化
	 */
	public void sysInitOper() {
//		IAssetStatsCtlInitService iAssetStatsCtlInitService = 
//			YssServiceFactory.getInstance().createService(IAssetStatsCtlInitService.class);
		// 获取最近一个库存日期
//		Date lastStockDate = stockDao.getLastStockDate();
		HashMap<String, Object> hmData = new HashMap<String, Object>();
		hmData.put("C_ITEM_CODE", "initBondYxgPerHund,");
//		hmData.put("D_END_DATE", YssFun.formatDate(lastStockDate, "yyyy-MM-dd"));
//		hmData.put("D_BEGIN_DATE", YssFun.formatDate(lastStockDate, "yyyy-MM-dd"));
//		hmData.put("C_OPER_CODE", String.valueOf(new Date().getTime()));
//		iAssetStatsCtlInitService.doBusOper(hmData);
	}
}
