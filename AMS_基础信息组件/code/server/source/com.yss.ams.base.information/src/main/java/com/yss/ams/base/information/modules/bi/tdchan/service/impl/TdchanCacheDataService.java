package com.yss.ams.base.information.modules.bi.tdchan.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.bi.tdchan.pojo.TdChan;
import com.yss.ams.base.information.support.bi.tdchan.service.ITdChanCacheDataService;
import com.yss.ams.base.information.support.cache.TdChanCache;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;

public class TdchanCacheDataService implements ITdChanCacheDataService {
	
	private TdChanCache tdChanCache = null;
	public TdchanCacheDataService(){
		tdChanCache = CacheManager.getInstance().getCache(CacheGroup.TDCHAN);
	}
	@SuppressWarnings("unchecked")
	@Override
	public <T extends BasePojo> List<T> getTdChanDataList() throws Exception {
		return (List<T>) tdChanCache.getCacheList();
	}
	@Override
	public QueryRes getTdChanDataListRes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public HashMap<String, String> getShortDataMap() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String insertTdChan(BasePojo pojo) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getMaxTdChanCode() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public BasePojo getDataByOrgCode(String orgCode) throws ServiceException {
		return tdChanCache.getCacheByKey(orgCode);
	}
	@Override
	public <K extends BasePojo> List<K> getDataListByComm(String[] types)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<BasePojo> queryPortRelaTradeSeat(String portCode) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<BasePojo> getDataListByPort(String[] portCode) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <K extends BasePojo> List<K> getDataListByPorts(String[] types)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	@SuppressWarnings("unchecked")
	@Override
	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		return (List<K>) tdChanCache.getCacheList();
	}
	@Override
	public QueryRes getDataListRes() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	@SuppressWarnings("unchecked")
	@Override
	public <K extends BasePojo> K getDataByCode(String dataCode)
			throws ServiceException {
		 return (K) tdChanCache.getCacheByKey(dataCode);
	}
	@Override
	public String getMenuId() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setMenuId(String menuId) {
		// TODO Auto-generated method stub
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public <K extends BasePojo> K getPojoByCode(String pojoCode)
			throws ServiceException {
		return (K) tdChanCache.getCacheByKey(pojoCode);
	}
	@Override
	public <K extends BasePojo> List<K> getDataListByTypes(String[] types)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public QueryRes getQueryResByTypes(String[] types) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <K extends BasePojo> List<K> getDataListByKeys(String[] keys)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public QueryRes getQueryResByKeys(String[] keys) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		return tdChanCache.getKeyConvertMap();
	}
	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		return tdChanCache.getKeyConvertMap(listKey);
	}
	@Override
	public CacheData updateByTimestamp(String timestamp) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T extends BasePojo> List<T> queryByIds(String ids) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public TdChan getCacheByKey(String key) {
		return tdChanCache.getCacheByKey(key);
	}
	@Override
	public List<TdChan> getCacheList() {
		return tdChanCache.getCacheList();
	}
	@Override
	public String getTimestamp() {
		return tdChanCache.getTimestamp();
	}
	

}
