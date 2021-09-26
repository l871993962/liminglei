package com.yss.ams.base.information.support.sys.dvaitem.service;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.sys.dvaitem.pojo.DvaItem;
import com.yss.framework.api.cache.CacheRefreshInfo;
import com.yss.framework.api.dataservice.GenericPojo;
import com.yss.framework.api.restful.annotations.LinkControllerMethod;
import com.yss.framework.api.restful.annotations.RestfulSupported;

/**
 * 核算业务项字典数据服务接口，主要进行跨应用数据获取
 * 字典表：T_S_DVA_ITEM
 */
@RestfulSupported
@GenericPojo(pojo = DvaItem.class)
public interface IDvaItemCacheDataService  {


	public DvaItem getCacheByKey(String key);
	
	public List<DvaItem> getCacheList();
	
	public void update(CacheRefreshInfo cacheRefreshInfo);
	
	@LinkControllerMethod(value="getKeyConvertMap")
	public HashMap<String, String> getKeyConvertMap();
	
	@LinkControllerMethod(value="getKeyConvertMap",arguTypes = List.class)
	public HashMap<String, String> getKeyConvertMap(List<String> listKey);
	 
}
