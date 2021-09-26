package com.yss.ams.base.information.modules.sys.voc.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.cache.VocCache;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.CacheRefreshInfo;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.platform.support.dataservice.pojo.dict.Vocabulary;
import com.yss.platform.support.dataservice.service.IVocCacheDataService;

public class VocCacheDataService implements IVocCacheDataService{
	
	private VocCache vocCache = null;
	public VocCacheDataService(){
		vocCache = CacheManager.getInstance().getCache(CacheGroup.VOC);
	}

	@Override
	public List<BasePojo> getVocByType(HashMap<String, Object> paraMap,
			Class<?> clazz) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> getShortDataMap(String type)
			throws ServiceException {
		return null;
	}

	@Override
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		return vocCache.getKeyConvertMap();
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		HashMap<String,String> mapAll = vocCache.getKeyConvertMap();
		HashMap<String,String> map = new HashMap<String,String>();
		for(String key : listKey){
			if(mapAll.containsKey(key)){
				map.put(key, mapAll.get(key));
			}
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		return (List<K>)vocCache.getCacheList();
	}

	@Override
	public QueryRes getDataListRes() throws ServiceException {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <K extends BasePojo> K getDataByCode(String dataCode)
			throws ServiceException {
		return (K)vocCache.getCacheByKey(dataCode);
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
		return (K)vocCache.getCacheByKey(pojoCode);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <K extends BasePojo> List<K> getDataListByTypes(String[] types)
			throws ServiceException {
		List<K> list = new ArrayList<K>();
		for (String type : types) {
			/*- Fixed by huangsq 20160728 解决key为空字符串时多次查询数据库，已经返回空对象导致页面显示空白项 */
			if (type != null && !"".equals(type.trim())) {
				List<K> cacheByKey = (List<K>)vocCache.getCacheByType(type);
				if (cacheByKey != null) {
					list.addAll((List<K>) cacheByKey);
				}
			}
		}
		return list;
	}

	@Override
	public QueryRes getQueryResByTypes(String[] types) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <K extends BasePojo> List<K> getDataListByKeys(String[] keys)
			throws ServiceException {
		List<K> list = new ArrayList<K>();
		for (String key : keys) {
			/*- Fixed by huangsq 20160728 解决key为空字符串时多次查询数据库，已经返回空对象导致页面显示空白项 */
			if (key != null && !"".equals(key.trim())) {
				Vocabulary cacheByKey = vocCache.getCacheByKey(key);
				if (cacheByKey != null) {
					list.add((K) cacheByKey);
				}
			}
		}
		return list;
	}

	@Override
	public QueryRes getQueryResByKeys(String[] keys) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CacheData updateByTimestamp(String timestamp) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Fixed by huangsq 20160729 BUG #135169 算法公式重新导入后不启作用<br />
	 * 重新加载缓存
	 */
	@Override
	public void reloadCache() {
		vocCache.reloadData();
	}
	
	@Override
	public <T extends BasePojo> List<T> queryByIds(String ids) {
		return null;//词汇表无c_iden字段
	}

	@Override
	public CacheData updateByTimestampAndKey(String timestamp, String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> getVocDic(List<String> keyList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vocabulary> getDataListForStock(String paramsString)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

/*	@Override
	public CacheData updateByTimestampAndKey(String timestamp, String key) {
		// TODO Auto-generated method stub
		return null;
	}*/
	@Override
	public void update(CacheRefreshInfo info){
		vocCache.update(info);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Vocabulary getCacheByKey(String key){
		return vocCache.getCacheByKey(key);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Vocabulary> getCacheByTypes(String[] vocTypes){
		return vocCache.getCacheByTypes(vocTypes);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vocabulary> getCacheByType(String vocType){
		return vocCache.getCacheByType(vocType);
	}
}
