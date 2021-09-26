package com.yss.ams.base.information.modules.sys.dccury.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.sys.dccury.controller.IDcCacheDataController;
import com.yss.ams.base.information.support.sys.dccury.pojo.DcCury;
import com.yss.ams.base.information.support.sys.dccury.service.IDCCacheDataService;
import com.yss.framework.api.cache.CacheRefreshInfo;
import com.yss.framework.api.restful.base.AbstractBaseController;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class DcCacheControllerImpl extends AbstractBaseController<IDCCacheDataService> implements IDcCacheDataController {

	@Override
	public List<DcCury> getCacheList() {
		return getService().getCacheList();
	}

	@Override
	public DcCury getCacheByKey(String key) {
		return getService().getCacheByKey(key);
	}

	@Override
	public void update(CacheRefreshInfo cacheRefreshInfo) {
		 getService().update(cacheRefreshInfo);
	}

	@Override
	public HashMap<String, String> getKeyConvertMap() {
		return getService().getKeyConvertMap();
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey) {
		return getService().getKeyConvertMap(listKey);
	}

	@Override
	public void reloadData() {
		 getService().reloadData();
	}

}