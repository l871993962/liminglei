package com.yss.ams.base.information.modules.sys.ieItem.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.sys.ieItem.controller.IIeItemCacheDataController;
import com.yss.ams.base.information.support.sys.ieItem.controller.IIeItemDataController;
import com.yss.ams.base.information.support.sys.ieItem.pojo.IeItem;
import com.yss.ams.base.information.support.sys.ieItem.service.IIeItemCacheDataService;
import com.yss.ams.base.information.support.sys.ieItem.service.IIeItemDataService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;
import com.yss.framework.api.service.ServiceException;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class IeItemCacheDataControllerImpl extends AbstractBaseController<IIeItemCacheDataService> implements IIeItemCacheDataController{

	@Override
	public IeItem getCacheByKey(String key) {
		return getService().getCacheByKey(key);
	}

	@Override
	public IeItem getPojoByCode(String pojoCode)
			throws ServiceException {
		return getService().getCacheByKey(pojoCode);
	}

	@Override
	public List<IeItem> getDataList() throws ServiceException {
		return getService().getDataList();
	}

	@Override
	public IeItem getDataByCode(String dataCode)
			throws ServiceException {
		return getService().getDataByCode(dataCode);
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


}