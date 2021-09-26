package com.yss.uco.elecreco.er.erresview.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseController;
import com.yss.uco.elecreco.er.erresview.service.IErResviewService;
import com.yss.uco.elecreco.support.util.ElecrecoServiceIdConstant;

/**
*
* @author tongdengke
* @date 2020-09-19 11:12:12
*/
@Path("")
@RestfulClient(serviceId = ElecrecoServiceIdConstant.OSGI_ELECRECO,
impl = "com.yss.uco.elecreco.er.erresview.service.impl.ErResviewService",
interfaceClass = com.yss.uco.elecreco.er.erresview.service.IErResviewService.class,
productLine = ProductLine.ELECRECO, serviceMapId = "IErResviewService", menuId = "base_erzyzb")
public interface IErResviewServiceController extends IBaseController<IErResviewService> {
	
    @POST
    @Path("/queryItemCodesByPlanCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<String> queryItemCodesByPlanCode(String code);

    @POST
    @Path("/deleteByPlanCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public void deleteByPlanCode(String code);

}