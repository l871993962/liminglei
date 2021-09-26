package com.yss.uco.elecreco.er.repcolcfg.controller.impl;

import java.util.List;

import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.uco.elecreco.er.repcolcfg.controller.IDzRepColCfgServiceController;
import com.yss.uco.elecreco.er.repcolcfg.pojo.DzRepColCfg;
import com.yss.uco.elecreco.er.repcolcfg.service.IDzRepColCfgService;

/**
 * 
 * @author tongdengke
 * @date 2020-09-19 11:12:15
 */
public class DzRepColCfgServiceControllerImpl extends
		AbstractBaseServiceBusController<DzRepColCfg, IDzRepColCfgService>
		implements IDzRepColCfgServiceController {

	@Override
	public List<DzRepColCfg> getDzRepColCfgs(String dzType, String reportCode) {
		return getService().getDzRepColCfgs(dzType, reportCode);
	}

}