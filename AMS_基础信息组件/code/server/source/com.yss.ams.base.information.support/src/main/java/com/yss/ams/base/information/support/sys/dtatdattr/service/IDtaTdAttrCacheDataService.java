package com.yss.ams.base.information.support.sys.dtatdattr.service;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.sys.dtatdattr.pojo.DtatdAttr;
import com.yss.framework.api.cache.IDataServiceForCache;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.dataservice.GenericPojo;
import com.yss.framework.api.dataservice.IControlDataService;
import com.yss.framework.api.dataservice.IKeyConvertDataService;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.service.ServiceException;

//import dataservice.comm.pojo.DtatdAttr;

/**
 * 交易属性字典数据服务接口，主要进行跨应用数据获取
 * 字典表：T_S_DTA_TD_ATTR
 */
@RestfulSupported
@GenericPojo(pojo = DtatdAttr.class)
public interface IDtaTdAttrCacheDataService extends IDtaTdAttrDataService{
	public DtatdAttr getCacheByKey(String key);
	
	public List<DtatdAttr> getCacheList();
	
	public HashMap<String, String> getKeyConvertMap();
}
