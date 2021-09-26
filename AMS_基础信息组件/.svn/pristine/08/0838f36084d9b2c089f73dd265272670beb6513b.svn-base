package com.yss.ams.base.information.modules.sys.dvaitem.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.sys.dvaitem.controller.IDvaItemCacheDataController;
import com.yss.ams.base.information.support.sys.dvaitem.pojo.DvaItem;
import com.yss.ams.base.information.support.sys.dvaitem.service.IDvaItemCacheDataService;
import com.yss.framework.api.cache.CacheRefreshInfo;
import com.yss.framework.api.restful.base.AbstractBaseController;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class DvaItemCacheDataControllerImpl extends AbstractBaseController<IDvaItemCacheDataService> implements IDvaItemCacheDataController {


	@Override
	public DvaItem getCacheByKey(String key) {
		return getService().getCacheByKey(key);
	}

	@Override
	public List<DvaItem> getCacheList() {
		return getService().getCacheList();
	}

	@Override
	public void update(CacheRefreshInfo cacheRefreshInfo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HashMap<String, String> getKeyConvertMap() {
		return getService().getKeyConvertMap();
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey) {
		return getService().getKeyConvertMap(listKey);
	}

  
}