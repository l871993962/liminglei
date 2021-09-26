package com.yss.ams.base.information.modules.bi.tdchan.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.bi.tdchan.controller.ITdchanCacheDataController;
import com.yss.ams.base.information.support.bi.tdchan.pojo.TdChan;
import com.yss.ams.base.information.support.bi.tdchan.service.ITdChanCacheDataService;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.service.ServiceException;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class TdchanCacheDataControllerImpl extends AbstractBaseController<ITdChanCacheDataService> implements ITdchanCacheDataController {

	@Override
	public List<TdChan> getTdChanDataList() throws Exception {
		return getService().getTdChanDataList();
	}

	@Override
	public TdChan getDataByOrgCode(String orgCode) throws ServiceException {
		return (TdChan) getService().getDataByOrgCode(orgCode);
	}

	@Override
	public TdChan getDataByCode(String dataCode) throws ServiceException {
		return getService().getDataByCode(dataCode);
	}

	@Override
	public TdChan getPojoByCode(String pojoCode) throws ServiceException {
		return getService().getPojoByCode(pojoCode);
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
	public TdChan getCacheByKey(String key) {
		return getService().getCacheByKey(key);
	}

	@Override
	public List<TdChan> getCacheList() {
		return getService().getCacheList();
	}

	@Override
	public String getTimestamp() {
		return getService().getTimestamp();
	}

    
}