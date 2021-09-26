package com.yss.ams.base.information.modules.bi.account.service.impl;

import java.util.List;

import com.yss.ams.base.information.support.bi.account.cache.BaseFundAccCache;
import com.yss.ams.base.information.support.bi.account.pojo.FundAcc;
import com.yss.ams.base.information.support.bi.account.service.IFundAccCacheDataService;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;

/**
 * 资金账户参数数据服务
 * @author meip
 * @date 2014-10-08
 * @version V4.5.0.1
 */
public class FundAccCacheDataService implements IFundAccCacheDataService{

	

	private BaseFundAccCache fundAccCache = null;
	public FundAccCacheDataService(){
		fundAccCache = CacheManager.getInstance().getCache(CacheGroup.FUNDACC);
	}
	@Override
	public List<FundAcc> getCacheList() {
		return fundAccCache.getCacheList();
	}
	@Override
	public List<String> bindServiceNames() {
		return fundAccCache.bindServiceNames();
	}
	@Override
	public FundAcc getCacheByKey(String key) {
		return fundAccCache.getCacheByKey(key);
	}
	
	
}
