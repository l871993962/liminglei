package com.yss.ams.sec.information.support.modules.sv.base.controller;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.sec.information.support.common.YssServiceIdConstant;
import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecBase;
import com.yss.ams.sec.information.support.modules.sv.base.service.ISecBaseLcService;
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
impl = "com.yss.ams.sec.information.modules.sv.base.service.impl.SecBaseLcService",
interfaceClass = com.yss.ams.sec.information.support.modules.sv.base.service.ISecBaseLcService.class,
productLine = ProductLine.SECINFO, serviceMapId = "ISecBaseLcService", menuId = "sv_financial")
public interface ISecBaseLcController extends IBaseServiceBusController<SecBase,ISecBaseLcService> {


    @POST
    @Path("/singleSecInitFi")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String singleSecInitFi(String secCode) throws Exception;

    @POST
    @Path("/multipleSecInitFi")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String multipleSecInitFi(List<SecBase> secList) throws Exception;

    @POST
    @Path("/getSecBasesByCondition")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<SecBase> getSecBasesByCondition(HashMap<String,Object> paraMap);

}