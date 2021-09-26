package com.yss.uco.elecreco.er.erkmb.controller;

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
import com.yss.uco.elecreco.er.erkmb.pojo.ErKmb;
import com.yss.uco.elecreco.er.erkmb.service.IErKmbService;
import com.yss.uco.elecreco.support.util.ElecrecoServiceIdConstant;

/**
*
* @author tongdengke
* @date 2020-09-19 11:12:11
*/
@Path("")
@RestfulClient(serviceId = ElecrecoServiceIdConstant.OSGI_ELECRECO,
impl = "com.yss.uco.elecreco.er.erkmb.service.impl.ErKmbService",
interfaceClass = com.yss.uco.elecreco.er.erkmb.service.IErKmbService.class,
productLine = ProductLine.ELECRECO, serviceMapId = "IErKmbService", menuId = "erKmb")
public interface IErKmbServiceController extends IBaseServiceBusController<ErKmb,IErKmbService> {


    @POST
    @Path("/getKmData")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<ErKmb> getKmData(HashMap<String,Object> paraMap);

}