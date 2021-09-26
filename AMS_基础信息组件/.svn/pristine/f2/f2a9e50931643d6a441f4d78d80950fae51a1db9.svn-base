package com.yss.ams.base.information.modules.bi.mkt.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.bi.mkt.controller.IMktCacheDataController;
import com.yss.ams.base.information.support.bi.mkt.controller.IMktController;
import com.yss.ams.base.information.support.bi.mkt.pojo.MarketVoc;
import com.yss.ams.base.information.support.bi.mkt.pojo.MktExtend;
import com.yss.ams.base.information.support.bi.mkt.pojo.MktVo;
import com.yss.ams.base.information.support.bi.mkt.service.IMktCacheDataService;
import com.yss.ams.base.information.support.bi.mkt.service.IMktService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.Mkt;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;
import com.yss.framework.api.service.ServiceException;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class MktCacheControllerImpl extends AbstractBaseController<IMktCacheDataService> implements IMktCacheDataController {

	@Override
	public List<Mkt> getDataList() throws ServiceException {
		return getService().getDataList();
	}

	@Override
	public Mkt getDataByCode(String dataCode)
			throws ServiceException {
		return getService().getDataByCode(dataCode);
	}

	@Override
	public Mkt getPojoByCode(String pojoCode)
			throws ServiceException {
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
	public Mkt getCacheByKey(String key) {
		return getService().getCacheByKey(key);
    }

	@Override
	public List<Mkt> getCacheList() {
		return getService().getCacheList();
	}

	@Override
	public void reloadData() {
		getService().reloadData();
	}
   
}