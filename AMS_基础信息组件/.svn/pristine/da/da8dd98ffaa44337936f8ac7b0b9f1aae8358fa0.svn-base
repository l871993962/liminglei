package com.yss.ams.base.information.support.sys.dttdmode.controller;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.ams.base.information.support.sys.dttdmode.pojo.DttdMode;
import com.yss.ams.base.information.support.sys.dttdmode.service.IDtTdModeCacheDataService;
import com.yss.ams.base.information.support.sys.dttdmode.service.IDtTdModeDataService;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;

/**
*
* @author neil
* @date 2020-09-07 18:11:11
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_BASEINFO,
impl = "com.yss.ams.base.information.modules.sys.dttdmode.service.impl.DtTdModeCacheDataService",
interfaceClass = com.yss.ams.base.information.support.sys.dttdmode.service.IDtTdModeCacheDataService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IDtTdModeCacheDataService", menuId = "IDtTdModeCacheDataService")
public interface IDtTdModeCacheDataController extends IBaseController<IDtTdModeCacheDataService> {


    @POST
    @Path("/getCacheList")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<DttdMode> getCacheList();
	
    @POST
    @Path("/getCacheByKey")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public DttdMode getCacheByKey(String key);
	
    @POST
    @Path("/getKeyConvertMap")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public HashMap<String, String> getKeyConvertMap(List<String> listKey);
	
	
	

}