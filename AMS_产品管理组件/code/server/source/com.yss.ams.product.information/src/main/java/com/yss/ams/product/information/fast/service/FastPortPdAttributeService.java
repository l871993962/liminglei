package com.yss.ams.product.information.fast.service;

import java.util.List;

import com.yss.ams.product.information.support.modules.ab.portPdAttribute.service.IPortPdAttributeService;
import com.yss.framework.api.commonInfo.service.IFastPortPdAttributeService;
import com.yss.framework.api.service.YssServiceFactory;


public class FastPortPdAttributeService implements IFastPortPdAttributeService {

	@Override
	public List<String> queryPortCodesByType(String portType) {
		return YssServiceFactory.getInstance().createService(IPortPdAttributeService.class).queryPortCodesByType(portType);
	}
	
}
