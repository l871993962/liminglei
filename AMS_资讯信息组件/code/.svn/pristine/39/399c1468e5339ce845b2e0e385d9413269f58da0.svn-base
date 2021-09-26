package com.yss.ams.sec.information.modules.sv.base.cache.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.sec.information.support.modules.sv.base.cache.controller.ISecbaseCacheDataController;
import com.yss.ams.sec.information.support.modules.sv.base.cache.service.ISecbaseCacheDataService;
import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecBase;
import com.yss.framework.api.cache.CacheRefreshInfo;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.service.ServiceException;

public class SecbaseCacheDataControllerImpl extends AbstractBaseController<ISecbaseCacheDataService> implements ISecbaseCacheDataController {

	@Override
	public SecBase getSecCacheByCode(String secCode) {
		return (SecBase) getService().getSecCacheByCode(secCode);
	}
	
	@Override
	public SecBase getCacheByKey(String key) {
		return getService().getCacheByKey(key);
	}

	@Override
	public List<SecBase> getDataList() throws ServiceException {
		return getService().getDataList();
	}

	@Override
	public SecBase getRate(SecBase secBase) throws ServiceException {
		return getService().getRate(secBase);
	}

	@Override
	public SecBase getSec(SecBase secBase) throws ServiceException {
		return getService().getSec(secBase);
	}

	@Override
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		return getService().getKeyConvertMap();
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		return getService().getKeyConvertMap(listKey);
	}

	@Override
	public List<SecBase> getCacheList() {
		return getService().getCacheList();
	}

	@Override
	public List<SecBase> getCacheList(int begin, int end) {
		return getService().getCacheList(begin,end);
	}

	@Override
	public List<SecBase> getDataListBySecVars(String[] secVars) {
		return getService().getDataListBySecVars(secVars);
	}

	@Override
	public List<SecBase> getDataListSB(String mktCode, String secVarPre)
			throws ServiceException {
		return getService().getDataListSB(mktCode,secVarPre);
	}

	@Override
	public void deleteData(List<String> secCodes) {
		getService().deleteData(secCodes);
	}

	@Override
	public void reloadData() {
		getService().reloadData();
	}

	@Override
	public String getTimestamp() {
		return getService().getTimestamp();
	}

	@Override
	public int getCacheListCount() {
		return getService().getCacheListCount();
	}

	@Override
	public void update(CacheRefreshInfo cacheRefreshInfo) {
		getService().update(cacheRefreshInfo);
	}


	
	
}
