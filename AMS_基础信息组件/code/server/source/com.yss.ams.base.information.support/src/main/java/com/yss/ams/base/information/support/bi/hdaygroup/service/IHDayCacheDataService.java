package com.yss.ams.base.information.support.bi.hdaygroup.service;


import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.bi.hdaygroup.pojo.HdayGroup;
import com.yss.framework.api.dataservice.GenericPojo;
import com.yss.framework.api.restful.annotations.RestfulSupported;

/**
 * 节假日数据缓存服务接口，主要进行跨应用数据获取
 */

@RestfulSupported
@GenericPojo(pojo=HdayGroup.class)
public interface IHDayCacheDataService  {
	
	public HdayGroup getCacheByKey(String key);
	
	public List<HdayGroup> getCacheList();
	
	public HashMap<Integer, List<Date>> getHDayGroupAllDate(String hdayCode);
	
	public HashMap<String, String> getKeyConvertMap(List<String> listKey);
	
}
