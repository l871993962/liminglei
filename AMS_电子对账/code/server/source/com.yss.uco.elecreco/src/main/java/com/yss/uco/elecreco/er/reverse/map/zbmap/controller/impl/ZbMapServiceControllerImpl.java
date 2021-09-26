package com.yss.uco.elecreco.er.reverse.map.zbmap.controller.impl;

import java.util.List;
import java.util.Map;

import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.uco.elecreco.er.reverse.map.zbmap.controller.IZbMapServiceController;
import com.yss.uco.elecreco.er.reverse.map.zbmap.pojo.ZbMap;
import com.yss.uco.elecreco.er.reverse.map.zbmap.service.IZbMapService;
import com.yss.uco.elecreco.support.bean.ElecRela;

/**
 * 
 * @author tongdengke
 * @date 2020-09-19 11:12:16
 */
public class ZbMapServiceControllerImpl extends
		AbstractBaseServiceBusController<ZbMap, IZbMapService> implements
		IZbMapServiceController {

	@Override
	public Map<String, ElecRela> getZbItems(String fileType) {
		return getService().getZbItems(fileType);
	}

	@Override
	public List<ZbMap> getCompareZbItems(String portCode, String tgh,
			String fileType) {
		return getService().getCompareZbItems(portCode, tgh, fileType);
	}

}