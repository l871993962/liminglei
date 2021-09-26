package com.yss.ams.syncdata.modules.base.controller.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.yss.ams.syncdata.support.modules.base.controller.ISyncGenerateController;
import com.yss.ams.syncdata.support.modules.base.service.ISyncGenerateService;
import com.yss.framework.api.busoperservice.BizItem;
import com.yss.framework.api.common.co.BEN_RECORD;
import com.yss.framework.api.restful.base.AbstractBaseOperController;
import com.yss.framework.api.service.ServiceException;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class SyncGenerateControllerImpl extends AbstractBaseOperController<ISyncGenerateService> implements ISyncGenerateController {

	@Override
	public void init(HashMap<String, Object> paraMap) {
		getService().init(paraMap);
		
	}

	@Override
	public List<BizItem> getBizItems() throws ServiceException {
		return getService().getBizItems();
	}

	@Override
	public List<BizItem> getBizItems(List<String> codes)
			throws ServiceException {
		return getService().getBizItems(codes);
	}

	@Override
	public List<BizItem> getRootBizItems() throws ServiceException {
		return getService().getRootBizItems();
	}

	@Override
	public Entry<String, List<BEN_RECORD>> execute() throws Exception {
		return getService().execute();
	}

	@Override
	public List<BEN_RECORD> getListRecord() {
		return getService().getListRecord();
	}

	@Override
	public String doBusOper(HashMap<String, Object> hmData) {
		return getService().doBusOper(hmData);
	}

}