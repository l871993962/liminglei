package com.yss.uco.elecreco.support.controller;

import javax.ws.rs.Path;

import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseOperController;
import com.yss.uco.elecreco.support.service.IElecTaskService;
import com.yss.uco.elecreco.support.util.ElecrecoServiceIdConstant;

/**
*
* @author tongdengke
* @date 2020-09-19 11:12:12
*/
@Path("")
@RestfulClient(serviceId = ElecrecoServiceIdConstant.OSGI_ELECRECO,
impl = "com.yss.uco.elecreco.er.task.service.ElecTaskService",
interfaceClass = com.yss.uco.elecreco.support.service.IElecTaskService.class,
productLine = ProductLine.ELECRECO, serviceMapId = "IElecTaskService", menuId = "elecGene")
public interface IElecTaskServiceController extends IBaseOperController<IElecTaskService> {


}