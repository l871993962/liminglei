package com.yss.ams.base.information.support.sys.dai.controller;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.ams.base.information.support.sys.dai.pojo.Dai;
import com.yss.ams.base.information.support.sys.dai.service.IDaiCacheDataService;
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
impl = "com.yss.ams.base.information.modules.sys.dai.service.impl.DaiCacheDataService",
interfaceClass = com.yss.ams.base.information.support.sys.dai.service.IDaiCacheDataService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IDaiCacheDataService", menuId = "IDaiCacheDataService")
public interface IDaiCacheDataController extends IBaseController<IDaiCacheDataService>{

	@POST
	@Path("/getCacheList")
	@Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	@Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public List<Dai> getCacheList();
	
	@POST
	@Path("/getCacheByKey")
	@Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	@Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public Dai getCacheByKey(String key);
	
	@POST
	@Path("/getKeyConvertMap1")
	@Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	@Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public HashMap<String,String> getKeyConvertMap();
	
	@POST
	@Path("/getKeyConvertMap")
	@Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	@Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public HashMap<String,String> getKeyConvertMap(List<String> listKey);
	
}
