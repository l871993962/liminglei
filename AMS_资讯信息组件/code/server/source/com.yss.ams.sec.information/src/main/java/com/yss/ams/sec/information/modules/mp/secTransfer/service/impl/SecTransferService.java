package com.yss.ams.sec.information.modules.mp.secTransfer.service.impl;

import java.util.HashMap;

import com.yss.ams.sec.information.activator.SecInfoActivator;
import com.yss.ams.sec.information.modules.mp.secTransfer.dao.SecTransferDao;
import com.yss.ams.sec.information.modules.mp.secTransfer.dao.SectransferSqlBuilder;
import com.yss.ams.sec.information.support.modules.mp.secTransfer.service.ISecTransferService;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.mvc.biz.ServiceBus;


/**
 * #42948 资讯信息管理组件化拆分
 * @author chenbo
 *2017-07-06
 */
/**
 * @ClassName SecTransferService
 * @Description 证券代码转换
 * @author guohui
 * @CreateDate 2016年10月24日下午2:11:59
 * @Version V4.5.0.1
 * @Copyright (c) 2016, 深圳赢时胜 All Rights Reserved.
 */
public class SecTransferService extends ServiceBus<SecTransferService>
		implements ISecTransferService {
	
	private SecTransferDao serviceDao = null;

	public SecTransferService() throws Exception {
		serviceDao = new SecTransferDao(YssDbPoolFactory.getInstance()
				.getDbPool(SecInfoActivator.class), new SectransferSqlBuilder());
		dao = serviceDao;
	}
	
	/**
	 * 
	 * @Title isEnableCache 
	 * @Description 获取缓存状态,true代表缓存可用
	 * @author zhanghualin@ysstech.com
	 * @date 2016年11月24日下午3:12:13
	 * @return
	 */
	public static boolean isEnableCache() {
		return SecTransferDao.isEnableCache();
	}

	public static void setEnableCache(boolean enableCache) {
		SecTransferDao.setEnableCache(enableCache);
	}
	
	/**
	 * 根据转换代码获取证券代码转换表,查询无结果返回 new HashMap<String, String>()
	 * @Title getSecTranMapByTranCode 
	 * @Description STORY #36252 【南方基金】【紧急】社保组合估值表导出要也按照社保理事会债券转换规则进行转换导出 生成确认单
	 * @author zhanghualin@ysstech.com
	 * @date 2016年11月25日下午3:46:36
	 * @param tranCode 转换代码
	 * @return
	 */
	public static HashMap<String, String> getSecTranMapByTranCode(String tranCode) {
		return SecTransferDao.getSecTranMapByTranCode(tranCode);
	}
	
	/**
	 * 静态方法,清空并重新加载证券转换字典
	 * @Title reloadSecTranMap 
	 * @Description STORY #36252 【南方基金】【紧急】社保组合估值表导出要也按照社保理事会债券转换规则进行转换导出 生成确认单
	 * @author zhanghualin@ysstech.com
	 * @date 2016年11月25日下午2:25:49
	 */
	public static void reloadSecTranMap() {
		SecTransferDao.reloadSecTranMap();
	}
	
	@Override
	public HashMap<String, String> getSecTranMapByCondition(HashMap<String, String> paramList) {
		return serviceDao.getSecTranMapByCondition(paramList);
	}
	
	@Override
	public String getParamValue(String portCode, String dateStr, String dspCode) {
		return serviceDao.getParamValue(portCode,dateStr,dspCode);
	}
}
