package com.yss.ams.base.information.modules.sys.dvaitem.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.cache.DvaItemCache;
import com.yss.ams.base.information.support.sys.dvaitem.pojo.DvaItem;
import com.yss.ams.base.information.support.sys.dvaitem.service.IDvaItemCacheDataService;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.CacheRefreshInfo;
import com.yss.framework.api.cache.assist.CacheGroup;


/**
 * 核算业务项字典T_S_DVA_ITEM DataService
 *
 */
public class DvaItemCacheDataService implements IDvaItemCacheDataService {
	
	private DvaItemCache dvaItemCache = null;
	
	public DvaItemCacheDataService(){
		dvaItemCache = CacheManager.getInstance().getCache(CacheGroup.DVAITEM);
	}

	@Override
	public DvaItem getCacheByKey(String key) {
		return dvaItemCache.getCacheByKey(key);
	}

	@Override
	public List<DvaItem> getCacheList() {
		return dvaItemCache.getCacheList();
	}

	@Override
	public void update(CacheRefreshInfo cacheRefreshInfo) {
		dvaItemCache.update(cacheRefreshInfo);
	}

	@Override
	public HashMap<String, String> getKeyConvertMap() {
		return dvaItemCache.getKeyConvertMap();
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey) {
		return dvaItemCache.getKeyConvertMap(listKey);
	}
	
}
