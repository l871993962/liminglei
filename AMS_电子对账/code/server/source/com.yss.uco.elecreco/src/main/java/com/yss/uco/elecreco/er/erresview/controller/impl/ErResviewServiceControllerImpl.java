package com.yss.uco.elecreco.er.erresview.controller.impl;

import java.util.List;

import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.uco.elecreco.er.erresview.controller.IErResviewServiceController;
import com.yss.uco.elecreco.er.erresview.service.IErResviewService;

/**
 * 
 * @author tongdengke
 * @date 2020-09-19 11:12:15
 */
public class ErResviewServiceControllerImpl extends
		AbstractBaseController<IErResviewService> implements
		IErResviewServiceController {
	@Override
	public List<String> queryItemCodesByPlanCode(String code) {
		return getService().queryItemCodesByPlanCode(code);
	}

	@Override
	public void deleteByPlanCode(String code) {
		getService().deleteByPlanCode(code);
	}

}