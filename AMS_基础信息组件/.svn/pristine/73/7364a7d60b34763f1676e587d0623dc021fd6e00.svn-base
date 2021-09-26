package com.yss.ams.base.information.support.bi.org.service;

import com.yss.ams.base.information.support.bi.org.pojo.Org;
import com.yss.framework.api.cache.CacheRefreshInfo;
import com.yss.framework.api.dataservice.GenericPojo;
import com.yss.framework.api.restful.annotations.RestfulSupported;

/**
 * 关联机构设置数据服务接口，主要进行跨应用数据获取
 * 
 * neil
 *
 */
@RestfulSupported
@GenericPojo(pojo = Org.class)
public interface IOrgCacheDataService extends IOrgDataService{
	
	public Org getCacheByKey(String key);
	
	public void update(CacheRefreshInfo info);
}
