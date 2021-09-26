package com.yss.ifa.szt.tool.para.service.controller;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;
import com.yss.ifa.szt.tool.para.service.IDzPortRelaService;
import com.yss.ifa.szt.tool.pojo.DzRelaOrgan;

/**
*
* @author neil
* @date 2020-09-07 18:11:10
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_BASEINFO,
impl = "com.yss.ifa.szt.tool.para.service.impl.DzPortRelaService",
interfaceClass = com.yss.ifa.szt.tool.para.service.IDzPortRelaService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IDzPortRelaService", menuId = "dzRelaOrgan")
public interface IDzPortRelaController extends IBaseServiceBusController<DzRelaOrgan,IDzPortRelaService> {


    @POST
    @Path("/queryPortCodesRelaOrgan")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<String> queryPortCodesRelaOrgan(HashMap<String,Object> paraMap);

    @POST
    @Path("/delInsert")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String delInsert(List<DzRelaOrgan> pojoList);

    @POST
    @Path("/delByYwId")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String delByYwId(List<DzRelaOrgan> pojoList);

}