package com.yss.ams.base.information.support.sys.dccury.service;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.sys.dai.pojo.Dai;
import com.yss.ams.base.information.support.sys.dccury.pojo.DcCury;
import com.yss.framework.api.cache.CacheRefreshInfo;
import com.yss.framework.api.cache.IDataServiceForCache;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.dataservice.GenericPojo;
import com.yss.framework.api.dataservice.IControlDataService;
import com.yss.framework.api.dataservice.IKeyConvertDataService;
import com.yss.framework.api.restful.annotations.LinkControllerMethod;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;

/**
 * 国际货币设置数据服务接口，主要进行跨应用数据获取
 * @author 马向峰  拆分  2017.0527
 *
 */
@RestfulSupported
@GenericPojo(pojo = DcCury.class)
public interface IDCCacheDataService  {

	public List<DcCury> getCacheList();
	
	public DcCury getCacheByKey(String key);
	
	public void update(CacheRefreshInfo cacheRefreshInfo);
	
	@LinkControllerMethod(value="getKeyConvertMap")
	public HashMap<String, String> getKeyConvertMap();
	
	@LinkControllerMethod(value="getKeyConvertMap",arguTypes = List.class)
	public HashMap<String, String> getKeyConvertMap(List<String> listKey);
	
	public void reloadData();
}
