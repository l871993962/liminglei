package com.yss.ams.sec.information.support.modules.sv.suspendedcond.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.sec.information.support.common.YssServiceIdConstant;
import com.yss.ams.sec.information.support.modules.sv.suspendedcond.pojo.SuspendedCond;
import com.yss.ams.sec.information.support.modules.sv.suspendedcond.service.ISuspendedCondService;
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
impl = "com.yss.ams.sec.information.modules.sv.suspendedcond.service.impl.SuspendedCondService",
interfaceClass = com.yss.ams.sec.information.support.modules.sv.suspendedcond.service.ISuspendedCondService.class,
productLine = ProductLine.SECINFO, serviceMapId = "ISuspendedCondService", menuId = "sv_suspended_para")
public interface ISuspendedCondController extends IBaseServiceBusController<SuspendedCond,ISuspendedCondService> {


    @POST
    @Path("/updateConds")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String updateConds(List<SuspendedCond> pojoList);

    @POST
    @Path("/getCondList")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<SuspendedCond> getCondList(String port);

}