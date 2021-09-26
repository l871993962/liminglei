package com.yss.ams.base.information.modules.bi.ie.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.bi.ie.controller.IIeCacheDataController;
import com.yss.ams.base.information.support.bi.ie.pojo.Ie;
import com.yss.ams.base.information.support.bi.ie.service.IIeCacheDataService;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.service.ServiceException;

public class IeCacheDataControllerImpl extends AbstractBaseController<IIeCacheDataService> implements IIeCacheDataController {

	@Override
	public Ie getCacheByIeCode(String secCode) {
		return getService().getCacheByIeCode(secCode);
	}

	@Override
	public Ie getCacheByKey(String key) {
		return getService().getCacheByKey(key);
	}

	@Override
	public List<Ie> getDataList() throws ServiceException {
		return getService().getDataList();
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
	public List<Ie> getCacheList() {
		return getService().getCacheList();
	}
		
}
