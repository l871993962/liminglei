package com.yss.uco.elecreco.mgr.controller.impl;

import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.ifa.szt.tool.thread.mgr.controller.IDzdzMgrController;
import com.yss.ifa.szt.tool.thread.mgr.service.IDzdzMgrService;

public class DzdzMgrControllerImpl extends AbstractBaseController<IDzdzMgrService> implements IDzdzMgrController{

	@Override
	public boolean isConnect() {
		return getService().isConnect();
	}

}
