package com.yss.ams.product.information.support.modules.pg.portgroup.controller;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.product.information.support.modules.pg.portgroup.pojo.PortGroup;
import com.yss.ams.product.information.support.modules.pg.portgroup.service.IPortGroupService;
import com.yss.common.YssServiceIdConstant;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;

/**
*
* @author neil
* @date 2020-09-14 11:34:49
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_PRODUCTINFO,
impl = "com.yss.ams.product.information.modules.pg.portgroup.service.impl.PortGroupService",
interfaceClass = com.yss.ams.product.information.support.modules.pg.portgroup.service.IPortGroupService.class,
productLine = ProductLine.PRODUCTINFO, serviceMapId = "IPortGroupService", menuId = "pd_portGroup")
public interface IPortGroupController extends IBaseServiceBusController<PortGroup,IPortGroupService> {


    @POST
    @Path("/checkGroupCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String checkGroupCode(String groupCode);

    @POST
    @Path("/checkGroupCode1")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public String checkGroupCode1(@FormParam("groupCode") String groupCode,@FormParam("ciden") String ciden);

    @POST
    @Path("/getPlanRelaPortGroupAdd")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<PortGroup> getPlanRelaPortGroupAdd(HashMap<String,Object> paraMap) throws Exception;

    @POST
    @Path("/getPlanRelaPortGroupBrow")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<PortGroup> getPlanRelaPortGroupBrow(HashMap<String,Object> paraMap)throws Exception;

    @POST
    @Path("/getPortGroupA")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<PortGroup> getPortGroupA(HashMap<String,Object> paraMap);

}