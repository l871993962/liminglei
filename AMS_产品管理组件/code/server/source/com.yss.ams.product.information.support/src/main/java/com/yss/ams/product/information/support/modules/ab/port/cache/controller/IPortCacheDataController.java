package com.yss.ams.product.information.support.modules.ab.port.cache.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.product.information.support.modules.ab.port.cache.service.IPortCacheDataService;
import com.yss.ams.product.information.support.modules.ab.port.pojo.PortCacheData;
import com.yss.ams.product.information.support.modules.ab.port.vo.GetCacheByPortAndBuildDateVo;
import com.yss.common.YssServiceIdConstant;
import com.yss.framework.api.cache.CacheRefreshInfo;
import com.yss.framework.api.common.co.Port;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseController;
import com.yss.framework.api.service.ServiceException;

/**
*
* @author neil
* @date 2020-09-14 11:34:49
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_PRODUCTINFO,newService = true,
impl = "com.yss.ams.product.information.modules.ab.port.cache.service.impl.PortCacheDataService",
interfaceClass = com.yss.ams.product.information.support.modules.ab.port.cache.service.IPortCacheDataService.class,
productLine = ProductLine.PRODUCTINFO, serviceMapId = "IPortCacheDataService", menuId = "pd_portfolio")
public interface IPortCacheDataController extends IBaseController<IPortCacheDataService> {


    @POST
    @Path("/getCacheTrCodeDataMapByKey")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public PortCacheData getCacheTrCodeDataMapByKey(String cacheKey);

    @POST
    @Path("/getCacheTrCodeDataKey")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<String> getCacheTrCodeDataKey();

    @POST
    @Path("/getPojoByCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public Port getPojoByCode(String pojoCode)
			throws ServiceException;
    @POST
    @Path("/getDataByCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public Port getDataByCode(String dataCode)
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
			throws ServiceException;
		
    @POST
    @Path("/getCacheByKey")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public Port getCacheByKey(String key);

    @POST
    @Path("/getPortCacheMap_ZCLX")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public HashMap<String,HashMap<String, Port>> getPortCacheMap_ZCLX();
	
    @POST
    @Path("/getCacheByPortAndBuildDate")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public Port getCacheByPortAndBuildDate(GetCacheByPortAndBuildDateVo vo);

    @POST
    @Path("/getCacheList")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public List<Port> getCacheList();
	
    @POST
    @Path("/getPortByRight")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public HashMap<String,Port> getPortByRight(List<String> rights);
	
    @POST
    @Path("/update")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public void update(CacheRefreshInfo info);
	
    @POST
    @Path("/refreshAssetTreeByTrCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public void refreshAssetTreeByTrCode(String trCode);

    @POST
    @Path("/reloadData")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public void reloadData();
    
}