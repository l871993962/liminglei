package com.yss.uco.elecreco.er.reverse.compare.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseController;
import com.yss.uco.elecreco.er.reverse.compare.service.IReveDzService;
import com.yss.uco.elecreco.er.reverse.manager.info.pojo.ErReveInfo;
import com.yss.uco.elecreco.support.util.ElecrecoServiceIdConstant;

/**
*
* @author tongdengke
* @date 2020-09-19 11:12:12
*/
@Path("")
@RestfulClient(serviceId = ElecrecoServiceIdConstant.OSGI_ELECRECO,
impl = "com.yss.uco.elecreco.er.reverse.compare.service.impl.ReveDzService",
interfaceClass = com.yss.uco.elecreco.er.reverse.compare.service.IReveDzService.class,
productLine = ProductLine.ELECRECO, serviceMapId = "IReveDzService")
public interface IReveDzServiceController extends IBaseController<IReveDzService> {


    @POST
    @Path("/compareErDataOper")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String compareErDataOper(List<ErReveInfo> infos);

}