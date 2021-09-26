package com.yss.ams.sec.information.support.modules.plateset.plate.controller;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.sec.information.support.common.YssServiceIdConstant;
import com.yss.ams.sec.information.support.modules.plateset.plate.pojo.Plate;
import com.yss.ams.sec.information.support.modules.plateset.plate.service.IPlate_AService;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;

/**
*
* @author neil
* @date 2020-09-10 15:18:11
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_SECINFO,
impl = "com.yss.ams.sec.information.modules.plateset.plate.service.impl.Plate_AService",
interfaceClass = com.yss.ams.sec.information.support.modules.plateset.plate.service.IPlate_AService.class,
productLine = ProductLine.SECINFO, serviceMapId = "IPlate_AService", menuId = "sv_sectorChild_A")
public interface IPlate_AController extends IBaseServiceBusController<Plate,IPlate_AService> {


    @POST
    @Path("/getTreeViewData")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<Plate> getTreeViewData(HashMap<String,Object> paraMap);

    @POST
    @Path("/unAuditById")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String unAuditById(List<Plate> pojoList);

    @POST
    @Path("/getSUBData")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String getSUBData(String data);

}