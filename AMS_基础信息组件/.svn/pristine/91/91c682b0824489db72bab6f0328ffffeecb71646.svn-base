package com.yss.ams.base.information.support.sys.dttdmode.service;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.sys.dttdmode.pojo.DttdMode;
import com.yss.framework.api.dataservice.GenericPojo;
import com.yss.framework.api.restful.annotations.RestfulSupported;

//import dataservice.comm.pojo.DttdMode;

/**
 * 交易方式字典数据缓存服务接口，主要进行跨应用数据获取
 * 字典表：T_S_DT_TD_MODE
 */
@RestfulSupported
@GenericPojo(pojo = DttdMode.class)
public interface IDtTdModeCacheDataService  {

	
	public List<DttdMode> getCacheList();
	
	public DttdMode getCacheByKey(String key);
	
	public HashMap<String, String> getKeyConvertMap(List<String> listKey);
		
}
