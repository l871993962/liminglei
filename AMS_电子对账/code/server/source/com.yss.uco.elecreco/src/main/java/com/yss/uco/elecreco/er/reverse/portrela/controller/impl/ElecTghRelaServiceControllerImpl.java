package com.yss.uco.elecreco.er.reverse.portrela.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.uco.elecreco.er.reverse.portrela.controller.IElecTghRelaServiceController;
import com.yss.uco.elecreco.er.reverse.portrela.service.IElecTghRelaService;

/**
 * 
 * @author tongdengke
 * @date 2020-09-19 11:12:16
 */
public class ElecTghRelaServiceControllerImpl extends
		AbstractBaseServiceBusController<BasePojo, IElecTghRelaService>
		implements IElecTghRelaServiceController {

	@Override
	public List<BasePojo> queryOrganByPort(HashMap<String, Object> paraMap) {
		return getService().queryOrganByPort(paraMap);
	}

}