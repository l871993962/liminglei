package com.yss.uco.elecreco.er.reverse.out.ergzb.controller;

import javax.ws.rs.Path;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;
import com.yss.uco.elecreco.er.reverse.out.ergzb.service.IErGzbOutService;
import com.yss.uco.elecreco.support.util.ElecrecoServiceIdConstant;

/**
*
* @author tongdengke
* @date 2020-09-19 11:12:12
*/
@Path("")
@RestfulClient(serviceId = ElecrecoServiceIdConstant.OSGI_ELECRECO,
impl = "com.yss.uco.elecreco.er.reverse.out.ergzb.service.impl.ErGzbOutService",
interfaceClass = com.yss.uco.elecreco.er.reverse.out.ergzb.service.IErGzbOutService.class,
productLine = ProductLine.ELECRECO, serviceMapId = "IErGzbOutService", menuId = "reveDzGzbOut")
public interface IErGzbOutServiceController extends IBaseServiceBusController<BasePojo,IErGzbOutService> {


}