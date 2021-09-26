package com.yss.ams.base.information.modules.bi.hdaygroup.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.bi.hdaygroup.pojo.HdayGroup;
import com.yss.ams.base.information.support.bi.hdaygroup.service.IHDayCacheDataService;
import com.yss.ams.base.information.support.cache.HDayCache;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;

/**
 * 节假日群类型数据服务类
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
public class HDayCacheDataService implements IHDayCacheDataService {

	private HDayCache hDayCache = null;
	public HDayCacheDataService(){
		hDayCache = CacheManager.getInstance().getCache(CacheGroup.HDAY);
	}
	
	
	@Override
	public HdayGroup getCacheByKey(String key) {
		return hDayCache.getCacheByKey(key);
	}

	@Override
	public List<HdayGroup> getCacheList() {
		return hDayCache.getCacheList();
	}

	@Override
	public HashMap<Integer, List<Date>> getHDayGroupAllDate(String hdayCode) {
		return hDayCache.getHDayGroupAllDate(hdayCode);
	}


	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey) {
		return hDayCache.getKeyConvertMap(listKey);
	}
	
}
