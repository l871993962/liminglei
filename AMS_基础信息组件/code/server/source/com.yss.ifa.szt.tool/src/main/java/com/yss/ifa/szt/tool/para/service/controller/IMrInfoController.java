package com.yss.ifa.szt.tool.para.service.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;
import com.yss.ifa.szt.tool.para.service.IMrInfoService;
import com.yss.ifa.szt.tool.pojo.MrInfo;

/**
*
* @author neil
* @date 2020-09-07 18:11:10
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_BASEINFO,
impl = "com.yss.ifa.szt.tool.para.service.impl.MrInfoService",
interfaceClass = com.yss.ifa.szt.tool.para.service.IMrInfoService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IMrInfoService", menuId = "mrInfo")
public interface IMrInfoController extends IBaseServiceBusController<MrInfo,IMrInfoService> {


    @POST
    @Path("/queryAllMrInfos")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<MrInfo> queryAllMrInfos();

    @POST
    @Path("/updateCheckState")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public void updateCheckState(MrInfo mrInfo);

}