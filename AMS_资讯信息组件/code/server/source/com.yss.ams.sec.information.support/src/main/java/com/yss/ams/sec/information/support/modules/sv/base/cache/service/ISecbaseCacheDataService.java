package com.yss.ams.sec.information.support.modules.sv.base.cache.service;

import java.util.List;

import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecBase;
import com.yss.ams.sec.information.support.modules.sv.base.service.ISecBaseInfoDataService;
import com.yss.framework.api.cache.CacheRefreshInfo;
import com.yss.framework.api.dataservice.GenericPojo;
import com.yss.framework.api.restful.annotations.LinkControllerMethod;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.service.ServiceException;


/**
 * 证券缓存查询服务，从缓存中查询
 * @author neil
 *
 */
@RestfulSupported
@GenericPojo(pojo = SecBase.class)
public interface ISecbaseCacheDataService extends ISecBaseInfoDataService {
	public SecBase getCacheByKey(String key);
	
	@LinkControllerMethod(value="getCacheList")
	public List<SecBase> getCacheList();
	
	@LinkControllerMethod(value="getCacheList",arguTypes = {Integer.class,Integer.class})
	public List<SecBase> getCacheList(int begin,int end);
	
	public List<SecBase> getDataListBySecVars(String[] secVars);
	
	public List<SecBase> getDataListSB(String mktCode,String secVarPre)
			throws ServiceException;
	
	public void deleteData(List<String> secCodes);
	
	public void update(CacheRefreshInfo cacheRefreshInfo);
	
	public void reloadData();
	
	public String getTimestamp();
	
	public int getCacheListCount();
	
	
}
