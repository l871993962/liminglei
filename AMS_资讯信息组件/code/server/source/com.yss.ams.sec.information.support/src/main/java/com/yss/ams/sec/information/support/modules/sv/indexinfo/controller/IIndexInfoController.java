package com.yss.ams.sec.information.support.modules.sv.indexinfo.controller;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.sec.information.support.common.YssServiceIdConstant;
import com.yss.ams.sec.information.support.modules.sv.indexinfo.pojo.IndexInfo;
import com.yss.ams.sec.information.support.modules.sv.indexinfo.service.IIndexInfoService;
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
impl = "com.yss.ams.sec.information.modules.sv.indexinfo.service.impl.IndexInfoService",
interfaceClass = com.yss.ams.sec.information.support.modules.sv.indexinfo.service.IIndexInfoService.class,
productLine = ProductLine.SECINFO, serviceMapId = "IIndexInfoService", menuId = "sv_indexinfo")
public interface IIndexInfoController extends IBaseServiceBusController<IndexInfo,IIndexInfoService> {


    @POST
    @Path("/getPortRelaIndex")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<IndexInfo> getPortRelaIndex(HashMap<String,Object> paraMap);

}