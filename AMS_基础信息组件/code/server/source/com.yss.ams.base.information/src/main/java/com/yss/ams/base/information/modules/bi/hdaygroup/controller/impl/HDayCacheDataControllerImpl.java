package com.yss.ams.base.information.modules.bi.hdaygroup.controller.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.bi.hdaygroup.controller.IHDayCacheDataController;
import com.yss.ams.base.information.support.bi.hdaygroup.pojo.HdayGroup;
import com.yss.ams.base.information.support.bi.hdaygroup.service.IHDayCacheDataService;
import com.yss.framework.api.restful.base.AbstractBaseController;

/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class HDayCacheDataControllerImpl extends AbstractBaseController<IHDayCacheDataService> implements IHDayCacheDataController {


	@Override
	public HdayGroup getCacheByKey(String key) {
		 return getService().getCacheByKey(key);
	}

	@Override
	public List<HdayGroup> getCacheList() {
		 return getService().getCacheList();
	}

	@Override
	public HashMap<Integer, List<Date>> getHDayGroupAllDate(String hdayCode) {
		 return getService().getHDayGroupAllDate(hdayCode);
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey) {
		return getService().getKeyConvertMap(listKey);
	}


   
}