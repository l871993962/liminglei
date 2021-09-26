package com.yss.ams.base.information.modules.sys.dai.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.cache.DaiCache;
import com.yss.ams.base.information.support.sys.dai.pojo.Dai;
import com.yss.ams.base.information.support.sys.dai.service.IDaiCacheDataService;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.YssConstant;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.api.util.JsonUtil;
import com.yss.framework.db.DbPoolFactory;
import com.yss.mvc.service.ServiceAssistance;


/**
 * 核算项目字典表T_S_DAI_ITEM 缓存服务类 
 *
 */
public class DaiCacheDataService extends ServiceBus<DaiCacheDataService> implements IDaiCacheDataService {
	
	private DaiCache daiCache = null;
	
	public DaiCacheDataService(){
		daiCache = CacheManager.getInstance().getCache(CacheGroup.DAI);
	}
	
	@Override
	public List<Dai> getCacheList() {
		return daiCache.getCacheList();
	}
	
	@Override
	public Dai getCacheByKey(String key) {
		return daiCache.getCacheByKey(key);
	}

	@Override
	public HashMap<String, String> getKeyConvertMap() {
		return daiCache.getKeyConvertMap();
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey) {
		return daiCache.getKeyConvertMap(listKey);
	}
	
}
