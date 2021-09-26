package com.yss.ams.base.information.support.sys.dtatdattr.controller;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.ams.base.information.support.sys.dtatdattr.pojo.DtatdAttr;
import com.yss.ams.base.information.support.sys.dtatdattr.service.IDtaTdAttrCacheDataService;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseController;
import com.yss.framework.api.service.ServiceException;

/**
*
* @author neil
* @date 2020-09-07 18:11:11
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_BASEINFO,
impl = "com.yss.ams.base.information.modules.sys.dtatdattr.service.impl.DtaTdAttrCacheDataService",
interfaceClass = com.yss.ams.base.information.support.sys.dtatdattr.service.IDtaTdAttrCacheDataService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IDtaTdAttrCacheDataService", menuId = "IDtaTdAttrCacheDataService")
public interface IDtaTdAttrCacheDataController extends IBaseController<IDtaTdAttrCacheDataService> {


	@POST
	@Path("/getDataList")
	@Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	@Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public List<DtatdAttr> getDataList() throws ServiceException;
	
	@POST
	@Path("/getCacheByKey")
	@Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	@Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public DtatdAttr getCacheByKey(String key);
	
	@POST
	@Path("/getCacheList")
	@Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	@Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public List<DtatdAttr> getCacheList();
	
	@POST
	@Path("/getKeyConvertMap")
	@Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	@Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public HashMap<String, String> getKeyConvertMap();
	
}