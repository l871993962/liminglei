package com.yss.uco.elecreco.er.erzcfzb.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.uco.elecreco.er.erzcfzb.controller.IErZcfzbServiceController;
import com.yss.uco.elecreco.er.erzcfzb.pojo.ErZcfzb;
import com.yss.uco.elecreco.er.erzcfzb.service.IErZcfzbService;

/**
 * 
 * @author tongdengke
 * @date 2020-09-19 11:12:15
 */
public class ErZcfzbServiceControllerImpl extends
		AbstractBaseServiceBusController<ErZcfzb, IErZcfzbService> implements
		IErZcfzbServiceController {

	@Override
	public List<ErZcfzb> getZcfzData(HashMap<String, Object> paraMap) {
		return getService().getZcfzData(paraMap);
	}

}