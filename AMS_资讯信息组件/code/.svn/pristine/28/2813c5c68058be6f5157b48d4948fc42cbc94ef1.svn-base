package com.yss.ams.sec.information.modules.sv.base.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.ams.sec.information.activator.SecInfoActivator;
import com.yss.ams.sec.information.modules.mp.secTransfer.dao.SecTransferDao;
import com.yss.ams.sec.information.modules.mp.secTransfer.dao.SectransferSqlBuilder;
import com.yss.ams.sec.information.modules.sv.base.dao.SecBaseDao;
import com.yss.ams.sec.information.modules.sv.base.dao.SecBaseZqSqlBuilder;
import com.yss.ams.sec.information.support.modules.pub.service.IAssetStatsCtlInitService;
import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecBase;
import com.yss.ams.sec.information.support.modules.sv.base.service.ISecBaseZqService;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.cache.assist.DefaultCacheRefresh;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.util.YssFun;
import com.yss.mvc.returninfo.ReturnInfoGenerator;

/**
 * 债券基本信息普通服务类
 * @author 马向峰
 *
 */
@DefaultCacheRefresh(group = CacheGroup.SECBASE)
public class SecBaseZqService extends ServiceBus<SecBaseService> implements ISecBaseZqService {

	private SecBaseDao serviceDao = null;
	private SecTransferDao transDao = null;
	/*private StockDao stockDao = null;
	private SecTransferDao transDao = null;
	AdvAlgoDao svcDao = null;*/
	public SecBaseZqService() throws Exception{
		serviceDao = new SecBaseDao(YssDbPoolFactory.getInstance().getDbPool(SecInfoActivator.class), new SecBaseZqSqlBuilder());
		transDao = new SecTransferDao(YssDbPoolFactory.getInstance()
				.getDbPool(SecInfoActivator.class), new SectransferSqlBuilder());
		/*stockDao = new StockDao(YssDbPoolFactory.getInstance().getDbPool(UcoActivator.class), new StockSqlBuilder());
		transDao = new SecTransferDao(YssDbPoolFactory.getInstance()
				.getDbPool(UcoActivator.class), new SectransferSqlBuilder());
		svcDao = new AdvAlgoDao(YssDbPoolFactory.getInstance().getDbPool(UcoActivator.class), new AdvAlgoSqlBuilder());*/
		dao = serviceDao;
		}
	
	/*
	 * add by fjl 20131219 需求13983 start
	 * 1.不具有审核机制时在新增和修改时进行初始化操作;
     * 2.具有审核机制时保存和修改时不进行初始化操作，在审核时进行。
	 */
	
//	@Override
//	public String insert(List<BasePojo> pojoList) {
//		String retStr = super.insert(pojoList);
//		if (retStr.contains("Success")) {
//			if(safeData.getN_CHECK() == 0) {
//				sysInitOper(pojoList);
//			}
//		}
//		return retStr;
//	}

//	@Override
//	public String updateById(List<BasePojo> pojoList) {
//		String retStr =  super.updateById(pojoList);
//		if (retStr.contains("Success")) {
//			if(safeData.getN_CHECK() == 0) {
//				sysInitOper(pojoList);
//			}
//		}
//		return retStr;
//	}

//	@Override
//	public String auditById(List<BasePojo> pojoList) {
//		String retStr = super.auditById(pojoList);
//		if (retStr.contains("Success")) {
//			if(safeData.getN_CHECK() == 1) {
//				sysInitOper(pojoList);
//			}
//		}
//		return retStr;
//	}
	
	public String singleSecInitFi(String secCode) throws Exception {
		/*IAssetStatsCtlInitService iAssetStatsCtlInitService = 
			YssServiceFactory.getInstance().createService(IAssetStatsCtlInitService.class);
		SecBaseCache secCache = CacheManager.getInstance().getCache(CacheGroup.SECBASE);
		SecBase secInfo = secCache.getCacheByKey(secCode);
		if(secInfo != null){
		HashMap<String, Object> hmData = new HashMap<String, Object>();
		hmData.put("C_ITEM_CODE", "initBondPerHundInter,");
		hmData.put("D_BEGIN_DATE", YssFun.formatDate(secInfo.getD_AI_BEGIN(), "yyyy-MM-dd"));
		//add by yh 2015.1.22 暂时调整一下，产生整个债券起息日-截息日的历史付息数据和每百元利息
		hmData.put("D_END_DATE", YssFun.formatDate(secInfo.getD_AI_END(), "yyyy-MM-dd"));
		hmData.put("C_OPER_CODE", String.valueOf(new Date().getTime()));
		iAssetStatsCtlInitService.initSingleSecBond(secInfo, hmData);
		}*/
		
		return ReturnInfoGenerator.getUpdateOKStr();
	}
	
	@Override
	public String multipleSecInitFi(List<SecBase> secList) throws Exception {
		IAssetStatsCtlInitService iAssetStatsCtlInitService = 
				YssServiceFactory.getInstance().createService(IAssetStatsCtlInitService.class);
		for(SecBase secBase : secList) {
			HashMap<String, Object> hmData = new HashMap<String, Object>();
			hmData.put("C_ITEM_CODE", "initBondPerHundInter,");
			hmData.put("D_BEGIN_DATE", YssFun.formatDate(secBase.getD_AI_BEGIN(), "yyyy-MM-dd"));
			//add by yh 2015.1.22 暂时调整一下，产生整个债券起息日-截息日的历史付息数据和每百元利息
			hmData.put("D_END_DATE", YssFun.formatDate(secBase.getD_AI_END(), "yyyy-MM-dd"));
			hmData.put("C_OPER_CODE", String.valueOf(new Date().getTime()));
			iAssetStatsCtlInitService.initSingleSecBond(secBase, hmData);
		} 
		return ReturnInfoGenerator.getUpdateOKStr();
	}
	
	/**
	 * 重新生成披露代码，并插入到证券代码转换信息表中
	 */
	@Override
	public String transSecToPlCode(List<SecBase> secList, String zhgz){
		/*HashMap<String, Object> map = new HashMap<String, Object>(); //公式参数
		HashMap<String, String> algoResultMap = null; //证券内码、披露内码键值对
		String[] secs = new String[secList.size()];
		List<SecTransfer> SecTrans = new ArrayList<SecTransfer>();
		int i = 0;
		try{
			map.put("secList", secList);
			algoResultMap = transDao.transSeccodeToPlcode(zhgz,map); //根据规则代码对应的公式获取被选中的证券内码的披露代码
			for(SecBase sec : secList){
				secs[i++] = sec.getC_SEC_CODE();
			}
			//删除历史证券转换信息
			transDao.deleteBySecodes(secs);
			
			Date date = new Date();
			for (Map.Entry<String, String> entry : algoResultMap.entrySet()) {  
				//构造证券转换信息实体
				transDao.setSecTran(entry.getKey(), zhgz, entry.getValue(), date, SecTrans);
				if (SecTrans.size() > 1000) {
					transDao.insert(SecTrans);//插入证券代码转换信息
					SecTrans.clear();
				}
		    }  
			if (SecTrans.size() > 0) {
				transDao.insert(SecTrans);
				SecTrans.clear();
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}*/
		return ReturnInfoGenerator.getUpdateOKStr();
	}
	
	/**
	 * STORY32744【南方基金】【v2.5需求】南方基金：社保资产证券代码新规则
	 * @Title getPlcode 
	 * @Description 根据转换规则，获取证券内码对应的披露代码
	 * @author liuyanni@ysstech.com
	 * @date 2016年11月5日下午4:57:47
	 * @param gzCode   规则代码
	 * @param secCodes 证券代码
	 * @throws Exception
	 * @return Map<String, String>  返回证券内码、披露代码键值对 
	 */
	public HashMap<String, String> getPlcode(ArrayList<SecBase> secList, String zhgz) throws Exception{
		HashMap<String, Object> map = new HashMap<String, Object>(); //公式参数
		HashMap<String, String> algoResultMap = new HashMap<String, String>(); //证券内码、披露内码键值对
		try{		
			map.put("secList", secList);
			algoResultMap = transDao.getPlcode(secList,zhgz); //根据规则代码对应的公式获取被选中的证券内码的披露代码
		}catch(Exception e){
			//e.printStackTrace();
			logger.error(e.getMessage());
		}
		return algoResultMap;
		//return null;
	}

	/**
	 * add by fjl 20131219
	 * 需求13983 ：添加调用系统初始化
	 */
	public void sysInitOper() {
		/*IAssetStatsCtlInitService iAssetStatsCtlInitService = 
			YssServiceFactory.getInstance().createService(IAssetStatsCtlInitService.class);
		// 获取最近一个库存日期
		Date lastStockDate = stockDao.getLastStockDate();
		HashMap<String, Object> hmData = new HashMap<String, Object>();
		hmData.put("C_ITEM_CODE", "initBondPerHundInter,");
		hmData.put("D_END_DATE", YssFun.formatDate(lastStockDate, "yyyy-MM-dd"));
		hmData.put("D_BEGIN_DATE", YssFun.formatDate(lastStockDate, "yyyy-MM-dd"));
		hmData.put("C_OPER_CODE", String.valueOf(new Date().getTime()));
		iAssetStatsCtlInitService.doBusOper(hmData);*/
	}
	
	/*add by fjl 20131219 需求13983 end*/
	
	/**   
	* Title: STORY #27843 资讯信息调整增加提示功能
	* Author: chenchen
	* Status: Add
	* Date: 2016.8.25
	* Description: 检查该证券最近一天是否有持仓
	*/
	public String secTypeTip(String secCode) {
		return serviceDao.secTypeTip(secCode);
	}
	
	/**
	 * 判断判断转换规则是否开启
	 * by guohui 20170207 STORY38131【南方基金】【紧急】社保理事会要求交易席位科目是6位，不足6位前面补0
	 */
	@Override
	public String ruleIsOpen(String zhgz,String type){
		String isTrue = "1";
		/*try{
			isTrue = transDao.ruleIsOpen(zhgz,type);
		}catch(Exception  e){
			e.printStackTrace();
		}*/
		return isTrue;
	}
}
