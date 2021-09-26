package com.yss.ams.product.information.modules.aa.portcls.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.ams.product.information.support.cache.PortClsCache;
import com.yss.ams.product.information.support.modules.aa.portcls.pojo.PortCls;
import com.yss.ams.product.information.support.modules.aa.portcls.service.IClsPortCacheDataService;


public class PortClsCacheDataService implements IClsPortCacheDataService {

	private PortClsCache portClsCache = null;
	public PortClsCacheDataService(){
		portClsCache = CacheManager.getInstance().getCache(CacheGroup.PORTCLS);
	}
	
	@Override
	public String getKey(String cPortCode, String cPortCls) {
		return portClsCache.getKey(cPortCode, cPortCls);
	}

	@Override
	public PortCls getCacheByKey(String key) {
		return portClsCache.getCacheByKey(key);
	}

	@Override
	public PortCls getCacheByPortAndPortCls(String portCode, String portClsCode) {
		return portClsCache.getCacheByPortAndPortCls(portCode, portClsCode);
	}

	@Override
	public List<PortCls> getCashListByPort(String portCode) {
		return portClsCache.getCashListByPort(portCode);
	}

	@Override
	public List<PortCls> getCashListByPortWDQ(String portCode, Date accDate) {
		return portClsCache.getCashListByPortWDQ(portCode, accDate);
	}

	@Override
	public List<PortCls> getCacheList() {
		return portClsCache.getCacheList();
	}

	@Override
	public HashMap<String, String> getKeyConvertMap() {
		return portClsCache.getKeyConvertMap();
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey) {
		return portClsCache.getKeyConvertMap(listKey);
	}

	@Override
	public void reloadData() {
		portClsCache.reloadData();
	}
	
}
