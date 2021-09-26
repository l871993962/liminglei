package com.yss.ams.product.information.support.modules.ab.port.controller;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.product.information.support.modules.ab.port.service.IRightPortService;
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
impl = "com.yss.ams.product.information.modules.ab.port.service.impl.PortService",
interfaceClass = com.yss.ams.product.information.support.modules.ab.port.service.IRightPortService.class,
productLine = ProductLine.PRODUCTINFO, serviceMapId = "IRightPortService")
public interface IRightPortController extends IBaseServiceBusController<Port,IRightPortService> {


    @POST
    @Path("/getRightManagePortList")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<Port> getRightManagePortList(HashMap<String,Object> paraMap) throws Exception;

    @POST
    @Path("/getRightManagePortList1")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<Port> getRightManagePortList() throws Exception;

    @POST
    @Path("/getPortInfoList")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<Port> getPortInfoList(HashMap<String,Object> paraMap) throws Exception;

    @POST
    @Path("/getRightManagePortTree")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<Port> getRightManagePortTree(HashMap<String,Object> paraMap) throws Exception;
    
    @POST
    @Path("/getRightManagePortListExpertAdd")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<Port> getRightManagePortListExpertAdd(HashMap<String, Object> paraMap) throws Exception;

}