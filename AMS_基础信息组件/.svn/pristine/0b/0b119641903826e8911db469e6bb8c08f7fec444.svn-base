package com.yss.ifa.szt.tool.para.service.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;
import com.yss.ifa.szt.tool.para.service.IDzParaService;
import com.yss.ifa.szt.tool.pojo.DzPara;

/**
*
* @author neil
* @date 2020-09-07 18:11:10
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_BASEINFO,
impl = "com.yss.ifa.szt.tool.para.service.impl.DzParaService",
interfaceClass = com.yss.ifa.szt.tool.para.service.IDzParaService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IDzParaService", menuId = "dzPara")
public interface IDzParaController extends IBaseServiceBusController<DzPara,IDzParaService> {


    @POST
    @Path("/getParaByAssCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public DzPara getParaByAssCode(String c_ASS_CODE);

    @POST
    @Path("/encryptData")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String encryptData(String encryptStr) throws Exception;

    @POST
    @Path("/decryptData")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String decryptData(String encryptStr) throws Exception;

}