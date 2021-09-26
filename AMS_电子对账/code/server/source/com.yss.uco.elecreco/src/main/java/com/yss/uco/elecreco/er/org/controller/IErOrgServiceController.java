package com.yss.uco.elecreco.er.org.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;
import com.yss.uco.elecreco.er.org.pojo.ErOrg;
import com.yss.uco.elecreco.er.org.service.IErOrgService;
import com.yss.uco.elecreco.support.util.ElecrecoServiceIdConstant;

/**
*
* @author tongdengke
* @date 2020-09-19 11:12:12
*/
@Path("")
@RestfulClient(serviceId = ElecrecoServiceIdConstant.OSGI_ELECRECO,
impl = "com.yss.uco.elecreco.er.org.service.impl.ErOrgService",
interfaceClass = com.yss.uco.elecreco.er.org.service.IErOrgService.class,
productLine = ProductLine.ELECRECO, serviceMapId = "IErOrgService", menuId = "erOrg")
public interface IErOrgServiceController extends IBaseServiceBusController<ErOrg,IErOrgService> {


    @POST
    @Path("/getTrusteeOrgs")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<ErOrg> getTrusteeOrgs();

    @POST
    @Path("/getManagerOrgs")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<ErOrg> getManagerOrgs();

}