package com.yss.uco.elecreco.er.reverse.manager.info.controller.impl;

import java.util.List;

import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.uco.elecreco.er.reverse.manager.info.controller.IErReveInfoServiceController;
import com.yss.uco.elecreco.er.reverse.manager.info.pojo.ErReveInfo;
import com.yss.uco.elecreco.er.reverse.manager.info.service.IErReveInfoService;
import com.yss.uco.elecreco.er.reverse.manager.info.vo.ErReveInfoVo;

/**
 * 
 * @author tongdengke
 * @date 2020-09-19 11:12:15
 */
public class ErReveInfoServiceControllerImpl extends
		AbstractBaseServiceBusController<ErReveInfo, IErReveInfoService>
		implements IErReveInfoServiceController {

	@Override
	public String unSdDzResult(List<ErReveInfo> list) {
		return getService().unSdDzResult(list);
	}

	@Override
	public String sdDzResult(List<ErReveInfo> list) {
		return getService().sdDzResult(list);
	}

	@Override
	public String editDzResult(ErReveInfoVo vo) {
		return getService().editDzResult(vo.getList(), vo.getDzResult(), vo.getXgsm());
	}

}