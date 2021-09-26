package com.yss.ams.product.information.support.modules.ab.portrela.controller;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.product.information.support.modules.ab.portrela.pojo.PortRelaTradeSeat;
import com.yss.ams.product.information.support.modules.ab.portrela.service.IPortRelaTradeSeatService;
import com.yss.ams.product.information.support.modules.ab.portrela.vo.QueryPortRelaTradeSeatPageVo;
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
impl = "com.yss.ams.product.information.modules.ab.portrela.service.impl.PortRelaTradeSeatService",
interfaceClass = com.yss.ams.product.information.support.modules.ab.portrela.service.IPortRelaTradeSeatService.class,
productLine = ProductLine.PRODUCTINFO, serviceMapId = "IPortRelaTradeSeatService", menuId = "pd_portTradeSeat")
public interface IPortRelaTradeSeatController extends IBaseServiceBusController<PortRelaTradeSeat,IPortRelaTradeSeatService> {


    @POST
    @Path("/queryPortRelaTradeSeatPage")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<PortRelaTradeSeat> queryPortRelaTradeSeatPage(QueryPortRelaTradeSeatPageVo vo)  throws Exception;

    @POST
    @Path("/queryPortRelaTradeSeat")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<PortRelaTradeSeat> queryPortRelaTradeSeat(HashMap<String,Object> paraMap)  throws Exception;

}