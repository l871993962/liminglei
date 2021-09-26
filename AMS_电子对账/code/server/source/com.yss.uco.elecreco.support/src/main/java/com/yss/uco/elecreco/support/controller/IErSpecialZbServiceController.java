package com.yss.uco.elecreco.support.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;
import com.yss.uco.elecreco.support.bean.ErSpecialZb;
import com.yss.uco.elecreco.support.service.IErSpecialZbService;
import com.yss.uco.elecreco.support.util.ElecrecoServiceIdConstant;

/**
*
* @author tongdengke
* @date 2020-09-19 11:12:11
*/
@Path("")
@RestfulClient(serviceId = ElecrecoServiceIdConstant.OSGI_ELECRECO,
impl = "com.yss.uco.elecreco.er.special.zb.service.impl.ErSpecialZbService",
interfaceClass = com.yss.uco.elecreco.support.service.IErSpecialZbService.class,
productLine = ProductLine.ELECRECO, serviceMapId = "IErSpecialZbService", menuId = "erSpecialZb")
public interface IErSpecialZbServiceController extends IBaseServiceBusController<ErSpecialZb,IErSpecialZbService> {


    @POST
    @Path("/getAllData")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<ErSpecialZb> getAllData();

}