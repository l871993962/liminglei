package com.yss.ams.sec.information.support.modules.plateset.platesub.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.sec.information.support.common.YssServiceIdConstant;
import com.yss.ams.sec.information.support.modules.plateset.platesub.pojo.PlateSub;
import com.yss.ams.sec.information.support.modules.plateset.platesub.service.IPlateSubService;
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
impl = "com.yss.ams.sec.information.modules.plateset.platesub.service.impl.PlateSubService",
interfaceClass = com.yss.ams.sec.information.support.modules.plateset.platesub.service.IPlateSubService.class,
productLine = ProductLine.SECINFO, serviceMapId = "IPlateSubService", menuId = "sv_sectorChild")
public interface IPlateSubController extends IBaseServiceBusController<PlateSub,IPlateSubService> {


    @POST
    @Path("/updatePlateById")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String updatePlateById(List<PlateSub> pojoList);

}