package com.yss.ams.base.information.support.sys.dai.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.ams.base.information.support.sys.dai.pojo.Dai;
import com.yss.ams.base.information.support.sys.dai.service.IDaiDictService;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;

/**
*
* @author neil
* @date 2020-09-07 18:11:11
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_BASEINFO,
impl = "com.yss.ams.base.information.modules.sys.dai.service.impl.DaiDictService",
interfaceClass = com.yss.ams.base.information.support.sys.dai.service.IDaiDictService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IDaiDictService", menuId = "base_DaiDict")
public interface IDaiDictController extends IBaseServiceBusController<Dai,IDaiDictService> {


    @POST
    @Path("/queryByCacheForKm")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<Dai> queryByCacheForKm();

}