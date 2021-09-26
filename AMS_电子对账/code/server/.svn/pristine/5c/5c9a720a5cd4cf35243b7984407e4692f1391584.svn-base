package com.yss.uco.elecreco.er.ersyzqyb.controller;

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
import com.yss.uco.elecreco.er.ersyzqyb.pojo.ErSyzqyb;
import com.yss.uco.elecreco.er.ersyzqyb.service.IErSyzqybService;
import com.yss.uco.elecreco.support.util.ElecrecoServiceIdConstant;

/**
*
* @author tongdengke
* @date 2020-09-19 11:12:12
*/
@Path("")
@RestfulClient(serviceId = ElecrecoServiceIdConstant.OSGI_ELECRECO,
impl = "com.yss.uco.elecreco.er.ersyzqyb.service.impl.ErSyzqybService",
interfaceClass = com.yss.uco.elecreco.er.ersyzqyb.service.IErSyzqybService.class,
productLine = ProductLine.ELECRECO, serviceMapId = "IErSyzqybService", menuId = "erSyzqyb")
public interface IErSyzqybServiceController extends IBaseServiceBusController<ErSyzqyb,IErSyzqybService> {


    @POST
    @Path("/getSyzqyData")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<ErSyzqyb> getSyzqyData(HashMap<String,Object> paraMap);

}