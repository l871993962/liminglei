package com.yss.ams.base.information.support.sys.ieItem.controller;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.ams.base.information.support.sys.ieItem.pojo.IeItem;
import com.yss.ams.base.information.support.sys.ieItem.service.IIeItemCacheDataService;
import com.yss.ams.base.information.support.sys.ieItem.service.IIeItemDataService;
import com.yss.framework.api.cache.IDataServiceForCache;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.dataservice.GenericPojo;
import com.yss.framework.api.dataservice.IControlDataService;
import com.yss.framework.api.dataservice.IKeyConvertDataService;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.restful.base.IBaseController;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;

//import dataservice.comm.pojo.IeItem;

/**
 * 收支项目字典数据服务接口，主要进行跨应用数据获取
 * 字典表：T_S_IE_ITEM
 *
 */
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_BASEINFO,
impl = "com.yss.ams.base.information.modules.sys.ieItem.service.impl.IeItemCacheDataService",
interfaceClass = com.yss.ams.base.information.support.sys.ieItem.service.IIeItemCacheDataService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IIeItemCacheDataService", menuId = "IIeItemCacheDataService")
public interface IIeItemCacheDataController extends IBaseController<IIeItemCacheDataService>{
	
    @POST
    @Path("/getCacheByKey")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public IeItem getCacheByKey(String key);

    @POST
    @Path("/getPojoByCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public IeItem getPojoByCode(String pojoCode)
			throws ServiceException;

    @POST
    @Path("/getDataList")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public List<IeItem> getDataList() throws ServiceException;

    @POST
    @Path("/getDataByCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public IeItem getDataByCode(String dataCode)
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
}
