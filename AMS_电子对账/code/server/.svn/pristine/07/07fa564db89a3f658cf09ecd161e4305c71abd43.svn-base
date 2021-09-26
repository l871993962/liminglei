package com.yss.uco.elecreco.er.erdata.controller.impl;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.uco.elecreco.er.erdata.controller.IErDataServiceController;
import com.yss.uco.elecreco.er.erdata.service.IErDataService;
import com.yss.uco.elecreco.support.dzdz.common.pojo.XmlFile;

/**
 * 
 * @author tongdengke
 * @date 2020-09-19 11:12:15
 */
public class ErDataServiceControllerImpl extends
		AbstractBaseServiceBusController<BasePojo, IErDataService> implements
		IErDataServiceController {

	@Override
	public XmlFile getXmlFileRoot(String fsn, String fileType, String cAssCode) {
		return getService().getXmlFileRoot(fsn, fileType, cAssCode);
	}

}