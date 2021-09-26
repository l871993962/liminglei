package com.yss.ams.product.information.modules.ab.port.cache.controller.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.support.modules.ab.port.cache.controller.IPortCacheDataController;
import com.yss.ams.product.information.support.modules.ab.port.cache.service.IPortCacheDataService;
import com.yss.ams.product.information.support.modules.ab.port.pojo.PortCacheData;
import com.yss.ams.product.information.support.modules.ab.port.vo.GetCacheByPortAndBuildDateVo;
import com.yss.framework.api.cache.CacheRefreshInfo;
import com.yss.framework.api.common.co.Port;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.service.ServiceException;


/**
*
* @author neil
* @date 2020-09-14 11:34:50
*/
public class PortCacheDataControllerImpl extends AbstractBaseController<IPortCacheDataService> implements IPortCacheDataController{

    @Override
    public PortCacheData getCacheTrCodeDataMapByKey(String cacheKey){
        return getService().getCacheTrCodeDataMapByKey(cacheKey);
    }

    @Override
    public List<String> getCacheTrCodeDataKey(){
        return getService().getCacheTrCodeDataKey();
    }

	@Override
	public Port getPojoByCode(String pojoCode) throws ServiceException {
		 return getService().getPojoByCode(pojoCode);
	}

	@Override
	public Port getDataByCode(String dataCode) throws ServiceException {
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

	@Override
	public Port getCacheByKey(String key) {
		 return getService().getCacheByKey(key);
	}

	@Override
	public HashMap<String, HashMap<String, Port>> getPortCacheMap_ZCLX() {
		return getService().getPortCacheMap_ZCLX();
	}

	@Override
	public Port getCacheByPortAndBuildDate(GetCacheByPortAndBuildDateVo vo) {
		return getService().getCacheByPortAndBuildDate(vo.getPortCode(), vo.getBuildDate());
	}

	@Override
	public List<Port> getCacheList() {
		return getService().getCacheList();
	}

	@Override
	public HashMap<String, Port> getPortByRight(List<String> rights) {
		return getService().getPortByRight(rights);
	}

	@Override
	public void update(CacheRefreshInfo info) {
		getService().update(info);
	}

	@Override
	public void refreshAssetTreeByTrCode(String trCode) {
		getService().refreshAssetTreeByTrCode(trCode);
	}

	@Override
	public void reloadData() {
		getService().reloadData();
	}
}