package com.yss.uco.elecreco.er.spilt.rela.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;
import com.yss.uco.elecreco.er.spilt.rela.pojo.ErSplitRela;
import com.yss.uco.elecreco.er.spilt.rela.service.IErSplitRelaService;
import com.yss.uco.elecreco.support.util.ElecrecoServiceIdConstant;

/**
*
* @author tongdengke
* @date 2020-09-19 11:12:12
*/
@Path("")
@RestfulClient(serviceId = ElecrecoServiceIdConstant.OSGI_ELECRECO,
impl = "com.yss.uco.elecreco.er.spilt.rela.service.impl.ErSplitRelaService",
interfaceClass = com.yss.uco.elecreco.er.spilt.rela.service.IErSplitRelaService.class,
productLine = ProductLine.ELECRECO, serviceMapId = "IErSplitRelaService", menuId = "erSplitRela")
public interface IErSplitRelaServiceController extends IBaseServiceBusController<ErSplitRela,IErSplitRelaService> {


    @POST
    @Path("/getErSplitRelasByPortCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<ErSplitRela> getErSplitRelasByPortCode(String port);

}