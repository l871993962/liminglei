package com.yss.ams.sec.information.modules.sv.fipay.service.impl;


import java.util.ArrayList;
import java.util.List;

import com.yss.ams.sec.information.activator.SecInfoActivator;
import com.yss.ams.sec.information.modules.sv.fipay.dao.FiPayDao;
import com.yss.ams.sec.information.modules.sv.fipay.dao.FiPaySqlBuilder;
import com.yss.ams.sec.information.support.modules.sv.fipay.pojo.FiPay;
import com.yss.ams.sec.information.support.modules.sv.fipay.service.IFiPayService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.util.YssFun;
import com.yss.mvc.returninfo.ReturnInfoGenerator;

//import dayfsupport.service.assetStats.IAssetStatsCtlInitService;

/**
 * 债券历史付息信息实现类
 * @author yuankai
 * 资讯信息拆分	2017.7.3 STORY #42948 资讯信息管理组件化拆分
 *
 */
public class FiPayService extends ServiceBus<FiPayService> implements IFiPayService {

	private FiPayDao serviceDao = null;
	public FiPayService() throws Exception{
		serviceDao = new FiPayDao(YssDbPoolFactory.getInstance().getDbPool(SecInfoActivator.class), new FiPaySqlBuilder());
		dao = serviceDao;
		}
	
	/**
	 * 校验当前选中的需要删除的历史付息数据是否符合删除要求
	 * @param pojoList
	 * @return
	 */
	public String checkDeleteData(List<BasePojo> pojoList) {
		return serviceDao.checkDeleteData(pojoList);
	}

	@Override
	public String singleSecFiPayInit(FiPay fiPay) throws Exception {
//			IAssetStatsCtlInitService iAssetStatsCtlInitService = YssServiceFactory
//					.getInstance().createService(
//							IAssetStatsCtlInitService.class);
			// 证券code list
			List<String> secCodeList = new ArrayList<String>();
			secCodeList.add(fiPay.getC_SEC_CODE());

//			iAssetStatsCtlInitService.initBondPerHundInterest(YssFun.toDate(fiPay.getD_BEGIN()),
//					YssFun.toDate(fiPay.getD_END()), secCodeList);
		return ReturnInfoGenerator.getUpdateOKStr();
	}

	@Override
	public String multipleFiPayInit(List<FiPay> fiPayList) throws Exception {
//		IAssetStatsCtlInitService iAssetStatsCtlInitService = YssServiceFactory
//				.getInstance().createService(
//						IAssetStatsCtlInitService.class);
		// 证券code list
		List<String> secCodeList = new ArrayList<String>();
		for(FiPay fiPay : fiPayList) {
			secCodeList.add(fiPay.getC_SEC_CODE());
//			iAssetStatsCtlInitService.initBondPerHundInterest(YssFun.toDate(fiPay.getD_BEGIN()),
//					YssFun.toDate(fiPay.getD_END()), secCodeList);
			secCodeList.clear();
		}
	return ReturnInfoGenerator.getUpdateOKStr();
	}
	
	
	
}
