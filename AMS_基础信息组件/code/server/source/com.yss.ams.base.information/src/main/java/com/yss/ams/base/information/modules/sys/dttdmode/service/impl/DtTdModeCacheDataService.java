package com.yss.ams.base.information.modules.sys.dttdmode.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.cache.DtTdModeCache;
import com.yss.ams.base.information.support.sys.dttdmode.pojo.DttdMode;
import com.yss.ams.base.information.support.sys.dttdmode.service.IDtTdModeCacheDataService;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;


/**
 * 交易方式字典表T_S_DT_TD_MODE DataService
 *
 */
public class DtTdModeCacheDataService implements IDtTdModeCacheDataService {
	
	private DtTdModeCache dtTdModeCache = null;
	public DtTdModeCacheDataService(){
		dtTdModeCache = CacheManager.getInstance().getCache(CacheGroup.DTTDMODE);
	}
	@Override
	public List<DttdMode> getCacheList() {
		return dtTdModeCache.getCacheList();
	}
	@Override
	public DttdMode getCacheByKey(String key) {
		return dtTdModeCache.getCacheByKey(key);
	}
	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey) {
		return dtTdModeCache.getKeyConvertMap(listKey);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
