package com.yss.ams.base.information.modules.sys.dtatdattr.service.impl;

import java.util.HashMap;
import java.util.List;
import com.yss.ams.base.information.support.cache.DtatdAttrCache;
import com.yss.ams.base.information.support.sys.dtatdattr.pojo.DtatdAttr;
import com.yss.ams.base.information.support.sys.dtatdattr.service.IDtaTdAttrCacheDataService;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;


/**
 * 交易属性字典T_S_DTA_TD_ATTR service
 *
 */
public class DtaTdAttrCacheDataService implements IDtaTdAttrCacheDataService {

	private DtatdAttrCache dtaTdAttrCache = null;
	public DtaTdAttrCacheDataService(){
		dtaTdAttrCache = CacheManager.getInstance().getCache(CacheGroup.DTATDATTR);
	}
	
	@Override
	public HashMap<String, String> getShortDataMap() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <K extends BasePojo> List<K> getDataListByCodes(String[] codes)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <K extends BasePojo> K getPojoByCode(String pojoCode)
			throws ServiceException {
		// TODO Auto-generated method stub
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

	@SuppressWarnings("unchecked")
	@Override
	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		return (List<K>) dtaTdAttrCache.getCacheList();
	}

	@Override
	public QueryRes getDataListRes() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <K extends BasePojo> K getDataByCode(String dataCode)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
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
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
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
	public DtatdAttr getCacheByKey(String key) {
		return dtaTdAttrCache.getCacheByKey(key);
	}

	@Override
	public List<DtatdAttr> getCacheList() {
		return dtaTdAttrCache.getCacheList();
	}

	@Override
	public HashMap<String, String> getKeyConvertMap() {
		return dtaTdAttrCache.getKeyConvertMap();
	}

}
