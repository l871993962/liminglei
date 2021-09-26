package com.yss.ams.product.information.support.modules.pg.operport.controller;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.product.information.support.modules.pg.operport.service.IOperPortService;
import com.yss.common.YssServiceIdConstant;
import com.yss.framework.api.common.co.Port;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;

/**
*
* @author neil
* @date 2020-09-14 11:34:49
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_PRODUCTINFO,
impl = "com.yss.ams.product.information.modules.pg.operport.impl.OperPortService",
interfaceClass = com.yss.ams.product.information.support.modules.pg.operport.service.IOperPortService.class,
productLine = ProductLine.PRODUCTINFO, serviceMapId = "IOperPortService", menuId = "pd_operPort")
public interface IOperPortController extends IBaseServiceBusController<Port,IOperPortService> {


    @POST
    @Path("/getPortByGroupCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public Map<String,Port> getPortByGroupCode(String groupCode);

    @POST
    @Path("/getAllPortGroup")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public Map<String,String> getAllPortGroup();

}