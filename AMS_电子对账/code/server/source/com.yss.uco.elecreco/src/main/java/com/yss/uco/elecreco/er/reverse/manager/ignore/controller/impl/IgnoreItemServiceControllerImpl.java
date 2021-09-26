package com.yss.uco.elecreco.er.reverse.manager.ignore.controller.impl;

import java.util.List;

import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.uco.elecreco.er.reverse.manager.ignore.controller.IIgnoreItemServiceController;
import com.yss.uco.elecreco.er.reverse.manager.ignore.pojo.IgnoreItem;
import com.yss.uco.elecreco.er.reverse.manager.ignore.service.IIgnoreItemService;

/**
 * 
 * @author tongdengke
 * @date 2020-09-19 11:12:15
 */
public class IgnoreItemServiceControllerImpl extends
		AbstractBaseServiceBusController<IgnoreItem, IIgnoreItemService>
		implements IIgnoreItemServiceController {

	@Override
	public List<IgnoreItem> getCompareIgnoreItem(String portCode, String tgh,
			String fileType) {
		return getService().getCompareIgnoreItem(portCode, tgh, fileType);
	}

}