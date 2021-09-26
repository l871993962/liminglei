package com.yss.uco.elecreco.er.repcolcfg.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;
import com.yss.uco.elecreco.er.repcolcfg.pojo.DzRepColCfg;
import com.yss.uco.elecreco.er.repcolcfg.service.IDzRepColCfgService;
import com.yss.uco.elecreco.support.util.ElecrecoServiceIdConstant;

/**
*
* @author tongdengke
* @date 2020-09-19 11:12:12
*/
@Path("")
@RestfulClient(serviceId = ElecrecoServiceIdConstant.OSGI_ELECRECO,
impl = "com.yss.uco.elecreco.er.repcolcfg.service.impl.DzRepColCfgService",
interfaceClass = com.yss.uco.elecreco.er.repcolcfg.service.IDzRepColCfgService.class,
productLine = ProductLine.ELECRECO, serviceMapId = "IDzRepColCfgService", menuId = "dzRepColCfg")
public interface IDzRepColCfgServiceController extends IBaseServiceBusController<DzRepColCfg,IDzRepColCfgService> {


    @POST
    @Path("/getDzRepColCfgs")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public List<DzRepColCfg> getDzRepColCfgs(@FormParam("dzType") String dzType,@FormParam("reportCode") String reportCode);

}