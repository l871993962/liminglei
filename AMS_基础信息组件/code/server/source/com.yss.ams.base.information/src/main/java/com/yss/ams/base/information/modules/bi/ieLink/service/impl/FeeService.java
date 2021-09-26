package com.yss.ams.base.information.modules.bi.ieLink.service.impl;

import com.yss.ams.base.information.modules.bi.ieLink.dao.FeeDao;
import com.yss.ams.base.information.modules.bi.ieLink.dao.FeeSqlBuilder;
import com.yss.ams.base.information.support.bi.ieLink.service.IFeeService;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.db.DbPoolFactory;

/**
 * 收支费用服务类
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
public class FeeService extends ServiceBus<FeeService> implements IFeeService {

	private FeeDao serviceDao = null;
	
	public FeeService() throws Exception{
		serviceDao = new FeeDao(DbPoolFactory.getInstance().getPool(), new FeeSqlBuilder());
		dao = serviceDao;
		}
//	@Override
//	public HashMap<String, Double> getFeeMap(HashMap<String, String> tradeRecord) {
//		List<IvtTradeFee> ivtTradeFeeList = new ArrayList<IvtTradeFee>();
//		HashMap<String, Double> feeMap = new HashMap<String, Double>();
//		try {
//			TdFee fee = new TdFee();
//			fee.setC_MKT_CODE("XCFE");
//			fee.setC_SEC_VAR_CODE(tradeRecord.get("C_SEC_VAR_CODE"));
//			fee.setC_SEC_CODE(tradeRecord.get("C_SEC_CODE"));
//			fee.setC_DT_CODE(tradeRecord.get("C_DT_CODE"));
//			// 交易平台为外汇交易中心
//			fee.setC_DV_PLAT("PLAT_CM");
//			fee.setC_PORT_CODE(tradeRecord.get("C_PORT_CODE"));
//			IFeeDataIntService feeDataIntService = YssServiceFactory.getInstance().createService(IFeeDataIntService.class);
//			ApiTrade apiTrade = new ApiTrade();
//			apiTrade.setC_TradeCsAmt(tradeRecord.get("N_TD_MONEY"));
//			apiTrade.setC_LastQty(tradeRecord.get("N_TD_AMOUNT"));
//			apiTrade.setC_ExecID(tradeRecord.get("Id"));
//			/*
//			 * Author : HeLiang
//			 * Date   : 2016-08-01
//			 * Status : Add
//			 * Comment: 获取前端传回的C_BBdCstName(买方债券托管机构名)信息，并设置到ApiTrade中，供后面获取费用信息的判断
//			 * modified by heliang.STORY #32867 【招商财富】上清所的费用也增加像中债一样的自动计算按钮
//			 * */
//			apiTrade.setC_BBdCstName(tradeRecord.get("C_BBdCstName"));
//			// 获取质押物信息
//			List<Underlyings>underlyings = serviceDao.getUnderlyings(tradeRecord.get("Id"), tradeRecord.get("C_TD_TYPE"));
//			apiTrade.setUnderlyings(underlyings);
//			/*
//			 * add by liyanjun 2015-12-15 STORY #27083 [海通证券]银行间债券交易手续费支持按债券面额计算
//		     * 交易费用设置，针对证券品种=‘债券品种’，交易平台=‘外汇’时，计算基准可选择='面值'，
//		     * 费率值同数量一样为非百分数；API系统自动清算数据时，支持面值*费率。面值=成交数量*单张面值。
//		     * 单张证券的面值取历史付息信息中 本次起息日期<=估值日期<=本次截息日期的剩余本金，如果没有历史付息信息，获取债券基本信息中的发行面值
//		     */
//			
//			double mzMoney = 0;
//			double amount = Double.parseDouble(apiTrade.getC_LastQty());
//		    if(fee.getC_SEC_VAR_CODE().startsWith("ZQ")){
//				AdmFiHistoryPay hisPay = new AdmFiHistoryPay();
//				FiPay fiPay = hisPay.getUniqueHistoryPay(fee.getC_SEC_CODE(), DateUtil.toDate(tradeRecord.get("D_TRADE")));
//				if(fiPay != null){
//					mzMoney = fiPay.getN_REM_COR().doubleValue() * amount;
//				}else{
//					SecBaseCache secCache = CacheManager.getInstance().getCache(CacheGroup.SECBASE);
//					SecBase secInfo = secCache.getCacheByKey(fee.getC_SEC_CODE());
//					mzMoney = YssFun.toDouble(secInfo.getN_FV_ISSUE()) * amount;
//				}
//		    }
//		    
//			if (null != apiTrade.getC_TradeCsAmt() && null != Double.valueOf(apiTrade.getC_TradeCsAmt())) {
//					feeDataIntService.getFeeList(fee, ivtTradeFeeList, apiTrade, DateUtil.toDate(tradeRecord.get("D_TRADE")),
//							Double.parseDouble(apiTrade.getC_TradeCsAmt()), amount, mzMoney, fee.getC_SEC_VAR_CODE());
//			}
//		} catch (Exception e) {
////			e.printStackTrace();
//			logger.log("费用品种信息查询失败", e);
//		}
//		
//		for (IvtTradeFee ivtTradeFee : ivtTradeFeeList) {
//			feeMap.put(ivtTradeFee.getC_FEE_CODE(), Double.parseDouble(ivtTradeFee.getN_FEE_MONEY()));
//		}
//		return feeMap;
//	}

}
