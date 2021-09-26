package com.yss.uco.elecreco.er.org.controller.impl;

import java.util.List;

import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.uco.elecreco.er.org.controller.IErOrgServiceController;
import com.yss.uco.elecreco.er.org.pojo.ErOrg;
import com.yss.uco.elecreco.er.org.service.IErOrgService;

/**
 * 
 * @author tongdengke
 * @date 2020-09-19 11:12:15
 */
public class ErOrgServiceControllerImpl extends
		AbstractBaseServiceBusController<ErOrg, IErOrgService> implements
		IErOrgServiceController {

	@Override
	public List<ErOrg> getTrusteeOrgs() {
		return getService().getTrusteeOrgs();
	}

	@Override
	public List<ErOrg> getManagerOrgs() {
		return getService().getManagerOrgs();
	}

}