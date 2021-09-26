package com.yss.ams.base.information.support.bi.region.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.ams.base.information.support.bi.region.pojo.Area;
import com.yss.ams.base.information.support.bi.region.service.IAreaService;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;

/**
*
* @author neil
* @date 2020-09-07 18:11:10
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_BASEINFO,
impl = "com.yss.ams.base.information.modules.bi.region.service.impl.AreaService",
interfaceClass = com.yss.ams.base.information.support.bi.region.service.IAreaService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IAreaService", menuId = "base_area")
public interface IAreaController extends IBaseServiceBusController<Area,IAreaService> {


    @POST
    @Path("/getAllAreasByType")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<Area> getAllAreasByType();

    @POST
    @Path("/getAllTopAreas")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<Area> getAllTopAreas();

}