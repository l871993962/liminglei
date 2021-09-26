package com.yss.uco.elecreco.er.erzcfzb.controller;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;
import com.yss.uco.elecreco.er.erzcfzb.pojo.ErZcfzb;
import com.yss.uco.elecreco.er.erzcfzb.service.IErZcfzbService;
import com.yss.uco.elecreco.support.util.ElecrecoServiceIdConstant;

/**
*
* @author tongdengke
* @date 2020-09-19 11:12:11
*/
@Path("")
@RestfulClient(serviceId = ElecrecoServiceIdConstant.OSGI_ELECRECO,
impl = "com.yss.uco.elecreco.er.erzcfzb.service.impl.ErZcfzbService",
interfaceClass = com.yss.uco.elecreco.er.erzcfzb.service.IErZcfzbService.class,
productLine = ProductLine.ELECRECO, serviceMapId = "IErZcfzbService", menuId = "erZcfzb")
public interface IErZcfzbServiceController extends IBaseServiceBusController<ErZcfzb,IErZcfzbService> {


    @POST
    @Path("/getZcfzData")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<ErZcfzb> getZcfzData(HashMap<String,Object> paraMap);

}