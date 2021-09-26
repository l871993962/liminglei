package com.yss.ams.base.information.modules.sys.dtatdattr.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.sys.dtatdattr.controller.IDtaTdAttrCacheDataController;
import com.yss.ams.base.information.support.sys.dtatdattr.pojo.DtatdAttr;
import com.yss.ams.base.information.support.sys.dtatdattr.service.IDtaTdAttrCacheDataService;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.service.ServiceException;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class DtaTdAttrCacheDataControllerImpl extends AbstractBaseController<IDtaTdAttrCacheDataService> implements IDtaTdAttrCacheDataController {

	@Override
	public List<DtatdAttr> getDataList() throws ServiceException {
		return getService().getDataList();
	}

	@Override
	public DtatdAttr getCacheByKey(String key) {
		return getService().getCacheByKey(key);
	}

	@Override
	public List<DtatdAttr> getCacheList() {
		return getService().getCacheList();
	}

	@Override
	public HashMap<String, String> getKeyConvertMap() {
		return getService().getKeyConvertMap();
	}

    
}