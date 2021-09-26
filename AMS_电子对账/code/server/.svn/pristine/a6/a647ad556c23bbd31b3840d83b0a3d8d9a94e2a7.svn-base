package com.yss.uco.elecreco.er.reverse.map.assmap.controller.impl;

import java.util.List;

import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.uco.elecreco.er.reverse.map.assmap.controller.IAssMapServiceController;
import com.yss.uco.elecreco.er.reverse.map.assmap.pojo.AssMap;
import com.yss.uco.elecreco.er.reverse.map.assmap.service.IAssMapService;

/**
 * 
 * @author tongdengke
 * @date 2020-09-19 11:12:15
 */
public class AssMapServiceControllerImpl extends
		AbstractBaseServiceBusController<AssMap, IAssMapService> implements
		IAssMapServiceController {

	@Override
	public String getDzMode(String portCode, String fileType) {
		return getService().getDzMode(portCode, fileType);
	}

	@Override
	public List<AssMap> getCommonAssMapByPortCode(String portCode) {
		return getService().getCommonAssMapByPortCode(portCode);
	}

	@Override
	public List<AssMap> getAssMapByPortCodeAndFileType(String portCode,
			String fileType) {
		return getService().getAssMapByPortCodeAndFileType(portCode, fileType);
	}

	@Override
	public List<String> getTghCodesByPortCode(String portCode) {
		return getService().getTghCodesByPortCode(portCode);
	}

}