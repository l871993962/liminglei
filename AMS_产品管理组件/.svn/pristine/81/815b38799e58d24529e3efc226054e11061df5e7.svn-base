package com.yss.ams.product.information.support.modules.ab.portrela.controller;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.product.information.support.modules.ab.portrela.pojo.PortRelaIndex;
import com.yss.ams.product.information.support.modules.ab.portrela.service.IPortRelaIndexService;
import com.yss.ams.product.information.support.modules.ab.portrela.vo.QueryPortRelaIndexPageVo;
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
impl = "com.yss.ams.product.information.modules.ab.portrela.service.impl.PortRelaIndexService",
interfaceClass = com.yss.ams.product.information.support.modules.ab.portrela.service.IPortRelaIndexService.class,
productLine = ProductLine.PRODUCTINFO, serviceMapId = "IPortRelaIndexService", menuId = "pd_portindex")
public interface IPortRelaIndexController extends IBaseServiceBusController<PortRelaIndex,IPortRelaIndexService>{


    @POST
    @Path("/queryPortRelaIndexPage")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<PortRelaIndex> queryPortRelaIndexPage(QueryPortRelaIndexPageVo vo);

    @POST
    @Path("/queryPortRelaIndex")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<PortRelaIndex> queryPortRelaIndex(HashMap<String,Object> paraMap);

}