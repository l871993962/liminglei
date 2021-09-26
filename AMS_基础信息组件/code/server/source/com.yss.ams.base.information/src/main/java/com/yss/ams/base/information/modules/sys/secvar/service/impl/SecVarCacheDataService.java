package com.yss.ams.base.information.modules.sys.secvar.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.cache.SecVarCache;
import com.yss.ams.base.information.support.sys.secvar.pojo.SecVar;
import com.yss.ams.base.information.support.sys.secvar.service.ISecVarCacheDataService;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;




/**
 * 证券品种字典T_S_DA_SEC_VAR  DataService
 *
 */
public class SecVarCacheDataService implements ISecVarCacheDataService {

	private SecVarCache secVarCache = null;
	public SecVarCacheDataService(){
		secVarCache = CacheManager.getInstance().getCache(CacheGroup.SECVAR);
	}
	
	
	@Override
	public SecVar getCacheByKey(String key) {
		return secVarCache.getCacheByKey(key);
	}

	@Override
	public List<SecVar> getCacheList() {
		return secVarCache.getCacheList();
	}


	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey) {
		return secVarCache.getKeyConvertMap(listKey);
	}


	@Override
	public void reloadData() {
		secVarCache.reloadData();
	}
	
}
