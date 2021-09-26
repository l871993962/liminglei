package com.yss.uco.elecreco.er.reverse.portrela.controller;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;
import com.yss.uco.elecreco.er.reverse.portrela.service.IElecPortRelaService;
import com.yss.uco.elecreco.support.util.ElecrecoServiceIdConstant;

/**
*
* @author tongdengke
* @date 2020-09-19 11:12:12
*/
@Path("")
@RestfulClient(serviceId = ElecrecoServiceIdConstant.OSGI_ELECRECO,
impl = "com.yss.uco.elecreco.er.reverse.portrela.service.impl.ElecPortRelaService",
interfaceClass = com.yss.uco.elecreco.er.reverse.portrela.service.IElecPortRelaService.class,
productLine = ProductLine.ELECRECO, serviceMapId = "IElecPortRelaService", menuId = "portfolio")
public interface IElecPortRelaServiceController extends IBaseServiceBusController<BasePojo,IElecPortRelaService> {


    @POST
    @Path("/queryPortRelaOrgan")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<BasePojo> queryPortRelaOrgan(HashMap<String,Object> paraMap);

}