package com.yss.ams.base.information.support.bi.mkt.controller;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.ams.base.information.support.bi.mkt.service.IMktCacheDataService;
import com.yss.framework.api.common.co.Mkt;
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
impl = "com.yss.ams.base.information.modules.bi.mkt.service.impl.MktCacheDataService",
interfaceClass = com.yss.ams.base.information.support.bi.mkt.service.IMktCacheDataService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IMktCacheDataService", menuId = "IMktCacheDataService")
public interface IMktCacheDataController extends IBaseController<IMktCacheDataService> {

	@POST
    @Path("/getDataList")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public List<Mkt> getDataList() throws ServiceException;

	@POST
    @Path("/getDataByCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public Mkt getDataByCode(String dataCode)
			throws ServiceException;
	@POST
    @Path("/getPojoByCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public Mkt getPojoByCode(String pojoCode)
			throws ServiceException;

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
    @Path("/getCacheByKey")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public Mkt getCacheByKey(String key);
	
	@POST
    @Path("/getCacheList")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public List<Mkt> getCacheList();

	@POST
    @Path("/reloadData")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public void reloadData();
   
}