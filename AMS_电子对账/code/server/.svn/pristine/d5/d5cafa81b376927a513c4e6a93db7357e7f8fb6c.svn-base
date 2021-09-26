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
import com.yss.uco.elecreco.support.dzdz.common.pojo.ErDspManager;
import com.yss.uco.elecreco.support.service.IErDspManagerService;
import com.yss.uco.elecreco.support.util.ElecrecoServiceIdConstant;

/**
*
* @author tongdengke
* @date 2020-09-19 11:12:12
*/
@Path("")
@RestfulClient(serviceId = ElecrecoServiceIdConstant.OSGI_ELECRECO,
impl = "com.yss.uco.elecreco.er.dsp.manager.service.impl.ErDspManagerService",
interfaceClass = com.yss.uco.elecreco.support.service.IErDspManagerService.class,
productLine = ProductLine.ELECRECO, serviceMapId = "IErDspManagerService", menuId = "dzDspPara")
public interface IErDspManagerServiceController extends IBaseServiceBusController<ErDspManager,IErDspManagerService> {


    @POST
    @Path("/upadteParam")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String upadteParam(List<ErDspManager> list);

}