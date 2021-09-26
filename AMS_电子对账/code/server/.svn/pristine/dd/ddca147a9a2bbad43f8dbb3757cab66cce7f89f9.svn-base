package com.yss.uco.elecreco.er.template.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.service.ServiceException;
import com.yss.uco.elecreco.er.template.controller.IDzTemplateDataServiceController;
import com.yss.uco.elecreco.er.template.service.IDzTemplateDataService;

/**
 * 
 * @author tongdengke
 * @date 2020-09-19 11:12:16
 */
public class DzTemplateDataServiceControllerImpl extends
		AbstractBaseController<IDzTemplateDataService> implements
		IDzTemplateDataServiceController {

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