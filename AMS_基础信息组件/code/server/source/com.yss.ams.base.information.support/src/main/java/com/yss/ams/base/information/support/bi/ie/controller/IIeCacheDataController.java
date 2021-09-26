package com.yss.ams.base.information.support.bi.ie.controller;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.ams.base.information.support.bi.ie.pojo.Ie;
import com.yss.ams.base.information.support.bi.ie.service.IIeCacheDataService;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseController;
import com.yss.framework.api.service.ServiceException;

/**
*
* @author neil
* @date 2020-09-07 18:11:10
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_BASEINFO,
impl = "com.yss.ams.base.information.modules.bi.ie.service.impl.IeCacheDataService",
interfaceClass = com.yss.ams.base.information.support.bi.ie.service.IIeCacheDataService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IIeCacheDataService", menuId = "IIeCacheDataService")
public interface IIeCacheDataController extends IBaseController<IIeCacheDataService> {


	@POST
	@Path("/getSecCacheByCode")
	@Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	@Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public Ie getCacheByIeCode(String secCode);
	
	@POST
	@Path("/getCacheByKey")
	@Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	@Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public Ie getCacheByKey(String key);
	
	@POST
	@Path("/getDataList")
	@Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	@Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public List<Ie> getDataList() throws ServiceException;
	
	@POST
	@Path("/getKeyConvertMap")
	@Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	@Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public HashMap<String, String> getKeyConvertMap() throws ServiceException;
	
	@POST
	@Path("/getKeyConvertMap1")
	@Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	@Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException ;
	@POST
	@Path("/getCacheList")
	@Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	@Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public List<Ie> getCacheList();

}