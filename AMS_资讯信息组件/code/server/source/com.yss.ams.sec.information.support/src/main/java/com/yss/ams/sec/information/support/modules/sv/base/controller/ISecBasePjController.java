package com.yss.ams.sec.information.support.modules.sv.base.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.sec.information.support.common.YssServiceIdConstant;
import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecBase;
import com.yss.ams.sec.information.support.modules.sv.base.service.ISecBasePjService;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;

/**
*
* @author neil
* @date 2020-09-10 15:18:11
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_SECINFO,
impl = "com.yss.ams.sec.information.modules.sv.base.service.impl.SecBasePjService",
interfaceClass = com.yss.ams.sec.information.support.modules.sv.base.service.ISecBasePjService.class,
productLine = ProductLine.SECINFO, serviceMapId = "ISecBasePjService", menuId = "sv_billinfo")
public interface ISecBasePjController extends IBaseServiceBusController<SecBase,ISecBasePjService> {


    @POST
    @Path("/queryPjSecBase")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public SecBase queryPjSecBase(String secCode);

}