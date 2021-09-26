package com.yss.uco.elecreco.er.reverse.manager.result.controller;

import javax.ws.rs.Path;

import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;
import com.yss.uco.elecreco.er.reverse.manager.result.pojo.ReveResult;
import com.yss.uco.elecreco.er.reverse.manager.result.service.IReveResultService;
import com.yss.uco.elecreco.support.util.ElecrecoServiceIdConstant;

/**
*
* @author tongdengke
* @date 2020-09-19 11:12:12
*/
@Path("")
@RestfulClient(serviceId = ElecrecoServiceIdConstant.OSGI_ELECRECO,
impl = "com.yss.uco.elecreco.er.reverse.manager.result.service.impl.ReveResultService",
interfaceClass = com.yss.uco.elecreco.er.reverse.manager.result.service.IReveResultService.class,
productLine = ProductLine.ELECRECO, serviceMapId = "IReveResultService", menuId = "reveDzResultDetail")
public interface IReveResultServiceController extends IBaseServiceBusController<ReveResult,IReveResultService> {


}