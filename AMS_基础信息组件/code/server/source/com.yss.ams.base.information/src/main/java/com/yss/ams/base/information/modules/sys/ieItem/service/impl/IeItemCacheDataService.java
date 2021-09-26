package com.yss.ams.base.information.modules.sys.ieItem.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.cache.IeItemCache;
import com.yss.ams.base.information.support.sys.ieItem.pojo.IeItem;
import com.yss.ams.base.information.support.sys.ieItem.service.IIeItemCacheDataService;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;

/**
 * 收支结转数据字典，不是用来转换费用的，转换费用请参见FeeDataService
 * @author neil
 */
public class IeItemCacheDataService implements IIeItemCacheDataService {

	private IeItemCache ieItemCache = null;
	public IeItemCacheDataService(){
		ieItemCache = CacheManager.getInstance().getCache(CacheGroup.IEITEM);
	}
	
	@Override
	public <K extends BasePojo> List<K> getDataListByIeTypes(String[] types)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <K extends BasePojo> K getPojoByCode(String pojoCode)
			throws ServiceException {
		return (K) ieItemCache.getCacheByKey(pojoCode);
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
	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		return (List<K>) ieItemCache.getCacheList();
	}

	@Override
	public QueryRes getDataListRes() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <K extends BasePojo> K getDataByCode(String dataCode)
			throws ServiceException {
		return (K) ieItemCache.getCacheByKey(dataCode);
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
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		return ieItemCache.getKeyConvertMap();
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		return ieItemCache.getKeyConvertMap(listKey);
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
	public IeItem getCacheByKey(String key) {
		return ieItemCache.getCacheByKey(key);
	}
	
}
