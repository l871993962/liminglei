package com.yss.uco.elecreco.er.reverse.out.erkmb.controller;

import javax.ws.rs.Path;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;
import com.yss.uco.elecreco.er.reverse.out.erkmb.service.IErKmbOutService;
import com.yss.uco.elecreco.support.util.ElecrecoServiceIdConstant;

/**
*
* @author tongdengke
* @date 2020-09-19 11:12:12
*/
@Path("")
@RestfulClient(serviceId = ElecrecoServiceIdConstant.OSGI_ELECRECO,
impl = "com.yss.uco.elecreco.er.reverse.out.erkmb.service.impl.ErKmbOutService",
interfaceClass = com.yss.uco.elecreco.er.reverse.out.erkmb.service.IErKmbOutService.class,
productLine = ProductLine.ELECRECO, serviceMapId = "IErKmbOutService", menuId = "reveDzKmbOut")
public interface IErKmbOutServiceController extends IBaseServiceBusController<BasePojo,IErKmbOutService> {


}