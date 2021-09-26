package com.yss.ams.base.information.modules.sys.dccury.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.cache.DcCache;
import com.yss.ams.base.information.support.sys.dccury.pojo.DcCury;
import com.yss.ams.base.information.support.sys.dccury.service.IDCCacheDataService;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.CacheRefreshInfo;
import com.yss.framework.api.cache.assist.CacheGroup;

/**
 * 国际货币数据服务类
 * @author 
 *
 */
public class DcCacheDataService implements IDCCacheDataService {

	private DcCache DcCache = null;
	public DcCacheDataService(){
		DcCache = CacheManager.getInstance().getCache(CacheGroup.DC);
	}
	@Override
	public List<DcCury> getCacheList() {
		return DcCache.getCacheList();
	}
	@Override
	public DcCury getCacheByKey(String key) {
		return DcCache.getCacheByKey(key);
	}
	@Override
	public void update(CacheRefreshInfo cacheRefreshInfo) {
		DcCache.update(cacheRefreshInfo);
	}
	@Override
	public HashMap<String, String> getKeyConvertMap() {
		return DcCache.getKeyConvertMap();
	}
	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey) {
		return DcCache.getKeyConvertMap(listKey);
	}
	@Override
	public void reloadData() {
		DcCache.reloadData();
	}

}
