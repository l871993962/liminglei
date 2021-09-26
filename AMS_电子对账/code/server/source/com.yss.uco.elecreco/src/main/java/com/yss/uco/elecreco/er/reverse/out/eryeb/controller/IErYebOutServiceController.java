package com.yss.uco.elecreco.er.reverse.out.eryeb.controller;

import javax.ws.rs.Path;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;
import com.yss.uco.elecreco.er.reverse.out.eryeb.service.IErYebOutService;
import com.yss.uco.elecreco.support.util.ElecrecoServiceIdConstant;

/**
*
* @author tongdengke
* @date 2020-09-19 11:12:12
*/
@Path("")
@RestfulClient(serviceId = ElecrecoServiceIdConstant.OSGI_ELECRECO,
impl = "com.yss.uco.elecreco.er.reverse.out.eryeb.service.impl.ErYebOutService",
interfaceClass = com.yss.uco.elecreco.er.reverse.out.eryeb.service.IErYebOutService.class,
productLine = ProductLine.ELECRECO, serviceMapId = "IErYebOutService", menuId = "reveDzYebOut")
public interface IErYebOutServiceController extends IBaseServiceBusController<BasePojo,IErYebOutService> {


}