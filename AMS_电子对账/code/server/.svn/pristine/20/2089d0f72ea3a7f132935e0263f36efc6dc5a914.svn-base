package com.yss.uco.elecreco.er.erlrb.controller;

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
import com.yss.uco.elecreco.er.erlrb.pojo.ErLrb;
import com.yss.uco.elecreco.er.erlrb.service.IErLrbService;
import com.yss.uco.elecreco.support.util.ElecrecoServiceIdConstant;

/**
*
* @author tongdengke
* @date 2020-09-19 11:12:11
*/
@Path("")
@RestfulClient(serviceId = ElecrecoServiceIdConstant.OSGI_ELECRECO,
impl = "com.yss.uco.elecreco.er.erlrb.service.impl.ErLrbService",
interfaceClass = com.yss.uco.elecreco.er.erlrb.service.IErLrbService.class,
productLine = ProductLine.ELECRECO, serviceMapId = "IErLrbService", menuId = "erLrb")
public interface IErLrbServiceController extends IBaseServiceBusController<ErLrb,IErLrbService> {


    @POST
    @Path("/getLrData")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<ErLrb> getLrData(HashMap<String,Object> paraMap);

}