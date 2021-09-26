package com.yss.ams.base.information.support.sys.secvar.controller;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.ams.base.information.support.sys.secvar.pojo.SecVar;
import com.yss.ams.base.information.support.sys.secvar.service.ISecVarCacheDataService;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseController;

/**
*
* @author neil
* @date 2020-09-07 18:11:11
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_BASEINFO,
impl = "com.yss.ams.base.information.modules.sys.secvar.service.impl.SecVarCacheDataService",
interfaceClass =com.yss.ams.base.information.support.sys.secvar.service.ISecVarCacheDataService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "ISecVarCacheDataService", menuId = "ISecVarCacheDataService")
public interface ISecVarCacheDataController extends IBaseController<ISecVarCacheDataService> {


    @POST
    @Path("/getDataList")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<SecVar> getDataList();

    @POST
    @Path("/getCacheByKey")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public SecVar getCacheByKey(String key);

    @POST
    @Path("/getCacheList")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<SecVar> getCacheList();
    
    @POST
    @Path("/getKeyConvertMap")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public HashMap<String, String> getKeyConvertMap(List<String> listKey);
	
    @POST
    @Path("/reloadData")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public void reloadData();

   
}