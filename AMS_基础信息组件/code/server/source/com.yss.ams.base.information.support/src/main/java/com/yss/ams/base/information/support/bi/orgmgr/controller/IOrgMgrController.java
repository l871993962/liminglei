package com.yss.ams.base.information.support.bi.orgmgr.controller;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.ams.base.information.support.bi.orgmgr.pojo.OrgMgr;
import com.yss.ams.base.information.support.bi.orgmgr.service.IOrgMgrService;
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
impl = "com.yss.ams.base.information.modules.bi.orgmgr.service.impl.OrgMgrService",
interfaceClass = com.yss.ams.base.information.support.bi.orgmgr.service.IOrgMgrService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IOrgMgrService", menuId = "base_orgmgr")
public interface IOrgMgrController extends IBaseServiceBusController<OrgMgr,IOrgMgrService> {


    @POST
    @Path("/getAllMBRCodes")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<String> getAllMBRCodes();

    @POST
    @Path("/getPortRelaMember")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<OrgMgr> getPortRelaMember(HashMap<String,Object> paraMap);

}