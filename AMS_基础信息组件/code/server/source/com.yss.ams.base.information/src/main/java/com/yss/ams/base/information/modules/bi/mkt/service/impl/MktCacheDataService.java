package com.yss.ams.base.information.modules.bi.mkt.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.common.co.Mkt;
import com.yss.ams.base.information.support.bi.mkt.service.IMktCacheDataService;
import com.yss.ams.base.information.support.cache.MktCache;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;

/**
 * 交易市场数据服务类
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
public class MktCacheDataService implements IMktCacheDataService {

	private MktCache mktCache = null;
	public MktCacheDataService(){
		mktCache = CacheManager.getInstance().getCache(CacheGroup.MKT);
	}
	
	
	@Override
	public HashMap<String, String> getShortDataMap() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <K extends BasePojo> List<K> getAllDataSqlByKeys(String[] keys)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <K extends BasePojo> List<K> getDataListAux()
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAllDataSql() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		return (List<K>) mktCache.getCacheList();
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
		return (K) mktCache.getCacheByKey(dataCode);
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
		return (K) mktCache.getCacheByKey(pojoCode);
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
		return mktCache.getKeyConvertMap();
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		HashMap<String,String> mapAll = mktCache.getKeyConvertMap();
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
	public Mkt getCacheByKey(String key) {
		return mktCache.getCacheByKey(key);
	}


	@Override
	public List<Mkt> getCacheList() {
		return mktCache.getCacheList();
	}


	@Override
	public void reloadData() {
		mktCache.reloadData();
	}
	

}
