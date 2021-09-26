package com.yss.uco.elecreco.er.task.controller.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.yss.framework.api.busoperservice.BizItem;
import com.yss.framework.api.common.co.BEN_RECORD;
import com.yss.framework.api.restful.base.AbstractBaseOperController;
import com.yss.framework.api.service.ServiceException;
import com.yss.uco.elecreco.support.controller.ITaskSendServiceController;
import com.yss.uco.elecreco.support.service.ITaskSendService;

/**
 * 
 * @author tongdengke
 * @date 2020-09-19 11:12:16
 */
public class TaskSendServiceControllerImpl extends
		AbstractBaseOperController<ITaskSendService> implements
		ITaskSendServiceController {

	@Override
	public String doBusOper(HashMap<String, Object> hmData) {
		return getService().doBusOper(hmData);
	}

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

}