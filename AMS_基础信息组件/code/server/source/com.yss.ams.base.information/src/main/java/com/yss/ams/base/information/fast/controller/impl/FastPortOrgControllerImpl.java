package com.yss.ams.base.information.fast.controller.impl;

import java.util.List;

import com.yss.ams.base.information.support.fast.controller.IBaseFastPortOrgController;
import com.yss.framework.api.commonInfo.pojo.FastPortOrg;
import com.yss.framework.api.commonInfo.service.IFastPortOrgService;
import com.yss.framework.api.restful.base.AbstractBaseController;


public class FastPortOrgControllerImpl extends AbstractBaseController<IFastPortOrgService> implements IBaseFastPortOrgController {

	@Override
	public List<FastPortOrg> queryTrusteeByPorts(List<String> ports) {
		return getService().queryTrusteeByPorts(ports);
	}

	

}
