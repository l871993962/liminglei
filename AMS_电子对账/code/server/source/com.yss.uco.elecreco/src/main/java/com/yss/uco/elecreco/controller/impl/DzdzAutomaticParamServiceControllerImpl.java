package com.yss.uco.elecreco.controller.impl;

import java.util.List;
import java.util.Map;

import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.uco.elecreco.support.controller.IDzdzAutomaticParamServiceController;
import com.yss.uco.elecreco.support.service.IDzdzAutomaticParamService;
import com.yss.uco.elecreco.support.vo.AutomaticParamVo;

/**
 * 
 * @author tongdengke
 * @date 2020-09-19 11:12:16
 */
public class DzdzAutomaticParamServiceControllerImpl extends
		AbstractBaseController<IDzdzAutomaticParamService> implements
		IDzdzAutomaticParamServiceController {

	@Override
	public Map<String, List<String>> getLinkPortbyEtfPort(AutomaticParamVo vo) {
		return getService()
				.getLinkPortbyEtfPort(vo.getPortList(), vo.getDate());
	}

}