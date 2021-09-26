package com.yss.ams.base.information.modules.bi.ie.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.bi.ie.pojo.Ie;
import com.yss.ams.base.information.support.bi.ie.service.IIeCacheDataService;
import com.yss.ams.base.information.support.cache.IeCache;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;

/**
 * 收支代码设置缓存服务类
 * @author neil
 *
 */
public class IeCacheDataService implements IIeCacheDataService {

	private IeCache ieCache = null;
	public IeCacheDataService(){
		ieCache = CacheManager.getInstance().getCache(CacheGroup.IE);
	}
	
	@Override
	public Ie getCacheByIeCode(String ietCode) {
		return ieCache.getCacheByKey(ietCode);
	}

	@Override
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		return ieCache.getKeyConvertMap();
	}

	@Override
	public <K extends BasePojo> List<Ie> getDataListByFeeCodes(String[] keys) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		return (List<K>) ieCache.getCacheList();
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
		return (K)ieCache.getCacheByKey(dataCode);
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
	
	@Override
	public <K extends BasePojo> K getPojoByCode(String pojoCode)
			throws ServiceException {
		return null;
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
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		HashMap<String,String> mapAll = ieCache.getKeyConvertMap();
		HashMap<String,String> map = new HashMap<String,String>();
		for(String key : listKey){
			if(mapAll.containsKey(key))
				map.put(key, mapAll.get(key));
		}
		return map;
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
	public Ie getCacheByKey(String key) {
		return ieCache.getCacheByKey(key);
	}

	@Override
	public List<Ie> getCacheList() {
		return ieCache.getCacheList();
	}
	
}
