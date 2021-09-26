package com.yss.ifa.szt.tool.rptlog.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;
import com.yss.ifa.szt.tool.pojo.ErRptLog;
import com.yss.ifa.szt.tool.rptlog.service.IErRptLogService;

/**
*
* @author neil
* @date 2020-09-07 18:11:10
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_BASEINFO,
impl = "com.yss.ifa.szt.tool.rptlog.service.impl.ErRptLogService",
interfaceClass = com.yss.ifa.szt.tool.rptlog.service.IErRptLogService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IErRptLogService", menuId = "erRptLog")
public interface IErRptLogController extends IBaseServiceBusController<ErRptLog,IErRptLogService> {


    @POST
    @Path("/deleteRptLog")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public void deleteRptLog(int day);

    @POST
    @Path("/queryLogById")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String queryLogById(String id);

    @POST
    @Path("/updateSN")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public void updateSN(@FormParam("oldSn") String oldSn,@FormParam("newSn") String newSn);

}