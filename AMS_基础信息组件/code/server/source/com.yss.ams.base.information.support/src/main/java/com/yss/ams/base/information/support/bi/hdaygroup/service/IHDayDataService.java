package com.yss.ams.base.information.support.bi.hdaygroup.service;


import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.bi.hdaygroup.pojo.HdayGroup;
import com.yss.framework.api.cache.IDataServiceForCache;
import com.yss.framework.api.dataservice.GenericPojo;
import com.yss.framework.api.dataservice.IControlDataService;
import com.yss.framework.api.dataservice.IDataService;
import com.yss.framework.api.dataservice.IKeyConvertDataService;
import com.yss.framework.api.restful.annotations.RestfulSupported;

/**
 * 节假日数据服务接口，主要进行跨应用数据获取
 */

/**
 * @author yuankai 公共信息拆分 2017.5.31
 */
@RestfulSupported
@GenericPojo(pojo=HdayGroup.class)
public interface IHDayDataService extends IDataService,
		IControlDataService,IKeyConvertDataService,IDataServiceForCache {
	/**
	 * 根据节假日群代码通过缓存获取节假日群下的全部节假日
	 * @param holidaysCode 节假日群代码
	 * @return
	 */
	HashMap<Integer, List<Date>> getHDayGroupAllDate(String holidaysCode);
}
