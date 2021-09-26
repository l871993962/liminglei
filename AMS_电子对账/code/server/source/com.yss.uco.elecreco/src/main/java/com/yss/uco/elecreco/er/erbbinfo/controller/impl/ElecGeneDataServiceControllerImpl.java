package com.yss.uco.elecreco.er.erbbinfo.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.service.ServiceException;
import com.yss.uco.elecreco.er.erbbinfo.controller.IElecGeneDataServiceController;
import com.yss.uco.elecreco.er.erbbinfo.service.IElecGeneDataService;

/**
 * 
 * @author tongdengke
 * @date 2020-09-19 11:12:15
 */
public class ElecGeneDataServiceControllerImpl extends
		AbstractBaseController<IElecGeneDataService> implements
		IElecGeneDataServiceController {

	@Override
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		return getService().getKeyConvertMap();
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		return getService().getKeyConvertMap(listKey);
	}

}