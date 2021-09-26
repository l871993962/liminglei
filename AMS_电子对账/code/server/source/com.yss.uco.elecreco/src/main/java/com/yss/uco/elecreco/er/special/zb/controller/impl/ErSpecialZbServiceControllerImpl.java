package com.yss.uco.elecreco.er.special.zb.controller.impl;

import java.util.List;

import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.uco.elecreco.support.bean.ErSpecialZb;
import com.yss.uco.elecreco.support.controller.IErSpecialZbServiceController;
import com.yss.uco.elecreco.support.service.IErSpecialZbService;

/**
 * 
 * @author tongdengke
 * @date 2020-09-19 11:12:16
 */
public class ErSpecialZbServiceControllerImpl extends
		AbstractBaseServiceBusController<ErSpecialZb, IErSpecialZbService>
		implements IErSpecialZbServiceController {

	@Override
	public List<ErSpecialZb> getAllData() {
		return getService().getAllData();
	}

}