package com.yss.ams.product.information.support.modules.ab.port.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.product.information.support.modules.ab.port.service.IPortFundRelaService;
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
impl = "com.yss.ams.product.information.modules.ab.port.service.impl.PortFundRelaService",
interfaceClass = com.yss.ams.product.information.support.modules.ab.port.service.IPortFundRelaService.class,
productLine = ProductLine.PRODUCTINFO, serviceMapId = "IPortFundRelaService", menuId = "pd_relaPort")
public interface IPortFundRelaController extends IBaseServiceBusController<Port,IPortFundRelaService> {


    @POST
    @Path("/deletePortFundRela")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public String deletePortFundRela(@FormParam("portCodes") String portCodes,@FormParam("fundAccId") String fundAccId);

}