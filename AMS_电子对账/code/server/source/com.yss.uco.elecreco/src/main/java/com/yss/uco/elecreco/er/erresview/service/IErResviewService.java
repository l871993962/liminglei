package com.yss.uco.elecreco.er.erresview.service;


import java.util.List;

import com.yss.framework.api.dataservice.GenericPojo;
import com.yss.framework.api.dataservice.IControlDataService;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.service.ServiceException;
import com.yss.uco.elecreco.er.erresview.pojo.ErResview;

@RestfulSupported
@GenericPojo(pojo = ErResview.class)
public interface IErResviewService extends IControlDataService,IServiceBus{
	List<String> queryItemCodesByPlanCode(String code);
	void deleteByPlanCode(String code);
}
