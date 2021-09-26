package com.yss.ams.syncdata.support.modules.base.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.ams.syncdata.support.modules.base.pojo.SyncData;
import com.yss.ams.syncdata.support.modules.base.pojo.SyncModule;
import com.yss.ams.syncdata.support.modules.base.service.ISyncDataService;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseController;

/**
*
* @author neil
* @date 2020-09-07 18:11:10
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_BASEINFO,
impl = "com.yss.ams.syncdata.modules.base.service.impl.SyncDataService",
interfaceClass = com.yss.ams.syncdata.support.modules.base.service.ISyncDataService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "ISyncDataService", menuId = "syncdata")
public interface ISyncDataController extends IBaseController<ISyncDataService> {


    @POST
    @Path("/getSystemCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String getSystemCode() throws Exception;

    @POST
    @Path("/ignoreMessages")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String ignoreMessages(List<SyncData> syncDatas);

    @POST
    @Path("/syncSuccess")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public void syncSuccess(List<String> ids);

    @POST
    @Path("/queryAllFuncodeCfg")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<SyncModule> queryAllFuncodeCfg();

    @POST
    @Path("/saveSyncModule")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String saveSyncModule(List<SyncModule> syncModule) throws Exception;

}