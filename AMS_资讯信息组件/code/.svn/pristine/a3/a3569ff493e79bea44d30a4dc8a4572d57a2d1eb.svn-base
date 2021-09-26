package com.yss.ams.sec.information.modules.sv.base.cache.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.sec.information.support.cache.SecBaseCache;
import com.yss.ams.sec.information.support.modules.sv.base.cache.service.ISecbaseCacheDataService;
import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecBase;
import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecShortPojo;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.CacheRefreshInfo;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.common.co.CacheDataExtend;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.common.co.ShortDataListPackage;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;

public class SecbaseCacheDataService implements ISecbaseCacheDataService {

	private SecBaseCache secBaseCache = null;

	public SecbaseCacheDataService() {
		secBaseCache = CacheManager.getInstance().getCache(CacheGroup.SECBASE);
	}
	
	@Override
	public <K extends BasePojo> List<K> getDataListByTypesAndMkt(String[] types)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <K extends BasePojo> List<K> getDataListByDaes(String parameter)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BasePojo getSecBaseInfoDataBySecCode(String cSecCode)
			throws ServiceException {
		return secBaseCache.getCacheByKey(cSecCode);
	}

	@Override
	public BasePojo getSecBaseInfoDataBySecCodeFromDb(String cSecCode)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SecBase getSecCacheByCode(String secCode) {
		return secBaseCache.getCacheByKey(secCode);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		// TODO Auto-generated method stub
		return (List<K>) secBaseCache.getCacheList();
	}

	@Override
	public SecBase getSecByVarDur(SecBase secBase) throws ServiceException {
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
	public ShortDataListPackage<SecShortPojo> getShortDataList(String[] types,
			String like, PageInation page) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <K extends BasePojo> List<K> getDataListByTypesAndDate(
			String[] types, String dateStr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> getShortDataMap() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <K extends BasePojo> List<K> getAllIndexDataList()
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(List<SecBase> list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SecBase getRate(SecBase secBase) throws ServiceException {
		return secBaseCache.getCacheByKey(secBase.getC_SEC_CODE());
	}

	@Override
	public SecBase getSec(SecBase secBase) throws ServiceException {
		return secBaseCache.getCacheByKey(secBase.getC_SEC_CODE());
	}

	@Override
	public SecBase getSecPortCode(SecBase secBase, String portCode)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SecBase> dbjxSecs(List<String> secCodeList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SecBase getDataBySecMktCodeAndMktCode(String secMktCode,
			String mktCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CacheData updateByTimestampPage(String timestamp, PageInation page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUpdateByTimestampCount(String timestamp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <K extends BasePojo> List<K> getDataListBySjsszq(String[] types) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <K extends BasePojo> List<K> getDataListByIndiv()
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String isExistsStock(String secCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMktNo() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CacheDataExtend updateByTimestampNew(String timestamp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String isExistsAct(String secCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String isExistsStk(String secCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CacheData updateByIds(String ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCurrUser(String userCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BasePojo> getDataListByTypes(String[] types, String paraValue) {
		// TODO Auto-generated method stub
		return null;
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
	public <K extends BasePojo> K getPojoByCode(String pojoCode)
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
		return secBaseCache.getKeyConvertMap();
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		HashMap<String,String> mapAll = secBaseCache.getKeyConvertMap();
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
	public List<String> UpdateDifferent(String codes){
		// TODO Auto-generated method stub
				return null;
	}
	
	@Override
	public CacheData updateByCodes(String codes){
		// TODO Auto-generated method stub
				return null;
	}

	@Override
	public SecBase getCacheByKey(String key) {
		return secBaseCache.getCacheByKey(key);
	}

	@Override
	public int getCountFromDb() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<SecBase> getSecBaseListBySecCodeListFromDb(List<String> secCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SecBase> getCacheList() {
		return secBaseCache.getCacheList();
	}

	@Override
	public List<SecBase> getCacheList(int begin, int end) {
		return secBaseCache.getCacheList(begin,end);
	}

	@Override
	public List<SecBase> getDataListBySecVars(String[] secVars) {
		return secBaseCache.getDataListBySecVars(secVars);
	}

	@Override
	public List<SecBase> getDataListSB(String mktCode, String secVarPre)
			throws ServiceException {
		return secBaseCache.getDataListSB(mktCode, secVarPre);
	}

	@Override
	public void deleteData(List<String> secCodes) {
		secBaseCache.deleteData(secCodes);
	}

	@Override
	public void update(CacheRefreshInfo cacheRefreshInfo) {
		secBaseCache.update(cacheRefreshInfo);
	}

	@Override
	public void reloadData() {
		secBaseCache.reloadData();
	}

	@Override
	public String getTimestamp() {
		return secBaseCache.getTimestamp();
	}

	@Override
	public int getCacheListCount() {
		return secBaseCache.getCacheListCount();
	}
	
}
