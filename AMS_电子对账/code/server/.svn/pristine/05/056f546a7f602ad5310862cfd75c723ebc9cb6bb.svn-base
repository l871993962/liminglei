package com.yss.uco.elecreco.support.controller;

import javax.ws.rs.Path;

import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;
import com.yss.uco.elecreco.support.transrepcfg.IDzTransRepCfgService;
import com.yss.uco.elecreco.support.transrepcfg.pojo.DzTransRepCfg;
import com.yss.uco.elecreco.support.util.ElecrecoServiceIdConstant;

/**
*
* @author tongdengke
* @date 2020-09-19 11:12:12
*/
@Path("")
@RestfulClient(serviceId = ElecrecoServiceIdConstant.OSGI_ELECRECO,
impl = "com.yss.uco.elecreco.er.transrepcfg.DzTransRepCfgService",
interfaceClass = com.yss.uco.elecreco.support.transrepcfg.IDzTransRepCfgService.class,
productLine = ProductLine.ELECRECO, serviceMapId = "IDzTransRepCfgService", menuId = "dzTransRepCfg")
public interface IDzTransRepCfgServiceController extends IBaseServiceBusController<DzTransRepCfg,IDzTransRepCfgService> {


}