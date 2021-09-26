package com.yss.ams.base.information.modules.bi.org.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.ams.base.information.support.bi.org.pojo.Org;
import com.yss.ams.base.information.support.bi.org.service.IOrgCacheDataService;
import com.yss.ams.base.information.support.cache.OrgCache;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.CacheRefreshInfo;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.cache.assist.DefaultCacheRefresh;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;

/**
 * 机构数据服务类
 * @author 马向峰 拆分
 *@Date 20170531
 */
@DefaultCacheRefresh(group = CacheGroup.ORG)
public class OrgCacheDataService implements IOrgCacheDataService {

	private OrgCache orgCache = null;
	public OrgCacheDataService(){
		orgCache = CacheManager.getInstance().getCache(CacheGroup.ORG);
	}
	
	
	@Override
	public BasePojo getDataByCounterpartyName(String counterpartyName)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String insertOrg(BasePojo pojo) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMaxOrgCode() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getOrgCodebyAccNo(String AccNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends BasePojo> List<T> getDataListByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends BasePojo> List<T> getAllBankHead()
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends BasePojo> List<T> getBankBranchByHead(String[] param)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(List<BasePojo> orgList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, String> insert(String c_Org_Code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <K extends BasePojo> List<K> getDataListByAptitude(String[] types)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <K extends BasePojo> List<K> getParentListByTypes(String[] types)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUpdateByTimestampCount(String timestamp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CacheData updateByTimestampPage(String timestamp, PageInation page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <K extends BasePojo> List<K> getDataListByZtzz(String[] types) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CacheData updateByIds(String ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Org> queryOrgByPort(String portCode, String c_dv_type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		return (List<K>) orgCache.getCacheList();
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
		return (K) orgCache.getCacheByKey(dataCode);
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
		return (K) orgCache.getCacheByKey(pojoCode);
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
		return orgCache.getKeyConvertMap();
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		return orgCache.getKeyConvertMap(listKey);
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
	public Org getCacheByKey(String key) {
		return orgCache.getCacheByKey(key);
	}


	@Override
	public void update(CacheRefreshInfo info) {
		orgCache.update(info);
	}
	
}
