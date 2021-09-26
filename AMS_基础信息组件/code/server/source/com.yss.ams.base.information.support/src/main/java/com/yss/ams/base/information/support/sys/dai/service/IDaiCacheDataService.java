package com.yss.ams.base.information.support.sys.dai.service;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.sys.dai.pojo.Dai;
import com.yss.framework.api.dataservice.GenericPojo;
import com.yss.framework.api.restful.annotations.LinkControllerMethod;
import com.yss.framework.api.restful.annotations.RestfulSupported;
/**
 * 核算项目字典数据服务接口，主要进行跨应用数据获取
 *
 */
@RestfulSupported
@GenericPojo(pojo = Dai.class)
public interface IDaiCacheDataService {

	public List<Dai> getCacheList();
	
	public Dai getCacheByKey(String key);
	
	@LinkControllerMethod(value="getKeyConvertMap")
	public HashMap<String, String> getKeyConvertMap();
	
	@LinkControllerMethod(value="getKeyConvertMap",arguTypes = List.class)
	public HashMap<String, String> getKeyConvertMap(List<String> listKey);
	
}
