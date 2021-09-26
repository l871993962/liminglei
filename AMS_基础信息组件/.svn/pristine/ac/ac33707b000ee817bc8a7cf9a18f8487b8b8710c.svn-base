package com.yss.ams.base.information.modules.bi.account.controller.impl;


import java.util.List;

import com.yss.ams.base.information.support.bi.account.controller.IFundAccCacheDataController;
import com.yss.ams.base.information.support.bi.account.pojo.FundAcc;
import com.yss.ams.base.information.support.bi.account.service.IFundAccCacheDataService;
import com.yss.framework.api.restful.base.AbstractBaseController;

/**
* STORY #94053 业务组件osgi的restful改造
* @author neil
* @date 2020-08-24 16:19:33
*/
public class FundAccCacheDataControllerImpl extends AbstractBaseController<IFundAccCacheDataService> implements IFundAccCacheDataController {

	@Override
	public List<FundAcc> getCacheList() {
		return getService().getCacheList();
	}

	@Override
	public List<String> bindServiceNames() {
		return getService().bindServiceNames();
	}

	@Override
	public FundAcc getCacheByKey(String key) {
		return getService().getCacheByKey(key);
	}

	
}


