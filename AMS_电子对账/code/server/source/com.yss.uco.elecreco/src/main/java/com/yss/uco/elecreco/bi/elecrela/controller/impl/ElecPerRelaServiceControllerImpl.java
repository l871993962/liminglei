package com.yss.uco.elecreco.bi.elecrela.controller.impl;

import java.util.HashMap;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.uco.elecreco.bi.elecrela.controller.IElecPerRelaServiceController;
import com.yss.uco.elecreco.bi.elecrela.pojo.ElecPerRela;
import com.yss.uco.elecreco.bi.elecrela.service.IElecPerRelaService;

/**
 * 
 * @author tongdengke
 * @date 2020-09-19 11:12:15
 */
public class ElecPerRelaServiceControllerImpl extends
		AbstractBaseServiceBusController<ElecPerRela, IElecPerRelaService>
		implements IElecPerRelaServiceController {

	@Override
	public BasePojo getPerRelaByCodeAndName(String c_ZB_CODE, String c_ZB_Name,
			String c_DZ_CODE) {
		return getService().getPerRelaByCodeAndName(c_ZB_CODE, c_ZB_Name,
				c_DZ_CODE);
	}

	@Override
	public BasePojo getPerRelaByCode(String c_ZB_CODE) {
		return getService().getPerRelaByCode(c_ZB_CODE);
	}

	@Override
	public HashMap<String, ElecPerRela> getPerRelaByPortAndDZCode(
			String c_PORT_CODE, String c_DZ_CODE) {
		return getService().getPerRelaByPortAndDZCode(c_PORT_CODE, c_DZ_CODE);
	}

}