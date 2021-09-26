package com.yss.uco.elecreco.er.ersyzqyb.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.uco.elecreco.er.ersyzqyb.controller.IErSyzqybServiceController;
import com.yss.uco.elecreco.er.ersyzqyb.pojo.ErSyzqyb;
import com.yss.uco.elecreco.er.ersyzqyb.service.IErSyzqybService;

/**
 * 
 * @author tongdengke
 * @date 2020-09-19 11:12:15
 */
public class ErSyzqybServiceControllerImpl extends
		AbstractBaseServiceBusController<ErSyzqyb, IErSyzqybService> implements
		IErSyzqybServiceController {

	@Override
	public List<ErSyzqyb> getSyzqyData(HashMap<String, Object> paraMap) {
		return getService().getSyzqyData(paraMap);
	}

}