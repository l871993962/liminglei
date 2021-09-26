package com.yss.uco.elecreco.er.erjzcbdb.controller;

import javax.ws.rs.Path;

import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;
import com.yss.uco.elecreco.er.erjzcbdb.pojo.ErJzcbdb;
import com.yss.uco.elecreco.er.erjzcbdb.service.IErJzcbdbService;
import com.yss.uco.elecreco.support.util.ElecrecoServiceIdConstant;

/**
*
* @author tongdengke
* @date 2020-09-19 11:12:11
*/
@Path("")
@RestfulClient(serviceId = ElecrecoServiceIdConstant.OSGI_ELECRECO,
impl = "com.yss.uco.elecreco.er.erjzcbdb.service.impl.ErJzcbdbService",
interfaceClass = com.yss.uco.elecreco.er.erjzcbdb.service.IErJzcbdbService.class,
productLine = ProductLine.ELECRECO, serviceMapId = "IErJzcbdbService", menuId = "erJzcbdb")
public interface IErJzcbdbServiceController extends IBaseServiceBusController<ErJzcbdb,IErJzcbdbService> {


}