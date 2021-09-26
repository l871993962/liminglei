package com.yss.uco.elecreco.er.erinfostate.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.uco.elecreco.support.controller.IErStepStateServiceController;
import com.yss.uco.elecreco.support.dzdz.common.pojo.ErStepState;
import com.yss.uco.elecreco.support.service.IErStepStateService;

/**
 * 
 * @author tongdengke
 * @date 2020-09-19 11:12:16
 */
public class ErStepStateServiceControllerImpl extends
		AbstractBaseServiceBusController<ErStepState, IErStepStateService>
		implements IErStepStateServiceController {

	@Override
	public String insertPojo(ErStepState erStatePojo) {
		return getService().insertPojo(erStatePojo);
	}

	@Override
	public ErStepState buildPojo(String assCode, String fsn,
			String c_FILE_TYPE, String c_RPT_TYPE, String c_STATE,
			String d_DATE, String errInfo) {
		return getService().buildPojo(assCode, fsn, c_FILE_TYPE, c_RPT_TYPE,
				c_STATE, d_DATE, errInfo);
	}

	@Override
	public List<BasePojo> queryListByTypes(HashMap<String, String> paraMap) {
		return getService().queryListByTypes(paraMap);
	}

}