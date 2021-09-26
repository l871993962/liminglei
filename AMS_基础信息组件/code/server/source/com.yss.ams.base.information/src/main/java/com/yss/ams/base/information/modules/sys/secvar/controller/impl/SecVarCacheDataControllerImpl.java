package com.yss.ams.base.information.modules.sys.secvar.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.sys.secvar.controller.ISecVarCacheDataController;
import com.yss.ams.base.information.support.sys.secvar.pojo.SecVar;
import com.yss.ams.base.information.support.sys.secvar.service.ISecVarCacheDataService;
import com.yss.framework.api.restful.base.AbstractBaseController;

/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class SecVarCacheDataControllerImpl extends AbstractBaseController<ISecVarCacheDataService> implements ISecVarCacheDataController {

	@Override
	public List<SecVar> getDataList() {
		return getService().getCacheList();
	}

	@Override
	public SecVar getCacheByKey(String key) {
		return getService().getCacheByKey(key);
	}

	@Override
	public List<SecVar> getCacheList() {
		return getService().getCacheList();
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey) {
		return getService().getKeyConvertMap(listKey);
	}

	@Override
	public void reloadData() {
		getService().reloadData();
	}

  

}