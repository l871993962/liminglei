package com.yss.ams.base.information.modules.bi.org.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.bi.org.controller.IOrgCacheDataController;
import com.yss.ams.base.information.support.bi.org.pojo.Org;
import com.yss.ams.base.information.support.bi.org.service.IOrgCacheDataService;
import com.yss.framework.api.cache.CacheRefreshInfo;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.service.ServiceException;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class OrgCacheDataControllerImpl extends AbstractBaseController<IOrgCacheDataService> implements IOrgCacheDataController{

	@Override
	public Org getDataByCode(String dataCode) throws ServiceException {
		 return getService().getDataByCode(dataCode);
	}

	@Override
	public Org getPojoByCode(String pojoCode) throws ServiceException {
		 return getService().getPojoByCode(pojoCode);
	}

	@Override
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		 return getService().getKeyConvertMap();
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		 return getService().getKeyConvertMap();
	}

	@Override
	public Org getCacheByKey(String key) {
		 return getService().getCacheByKey(key);
	}

	@Override
	public List<Org> getDataList() throws ServiceException {
		 return getService().getDataList();
	}

	@Override
	public void update(CacheRefreshInfo info) {
		getService().update(info);
	}

   
}