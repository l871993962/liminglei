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
import com.yss.ifa.szt.tool.para.service.IErParaService;
import com.yss.ifa.szt.tool.pojo.ErPara;
import com.yss.ifa.szt.tool.pojo.MrInfo;

/**
*
* @author neil
* @date 2020-09-07 18:11:10
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_BASEINFO,
impl = "com.yss.ifa.szt.tool.para.service.impl.ErParaService",
interfaceClass = com.yss.ifa.szt.tool.para.service.IErParaService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IErParaService", menuId = "erPara")
public interface IErParaController extends IBaseServiceBusController<ErPara,IErParaService> {


    @POST
    @Path("/queryMrInfos")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<MrInfo> queryMrInfos(String c_Para_Code);

}