package com.yss.ams.sec.information.support.modules.sv.base.cache.controller;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.sec.information.support.common.YssServiceIdConstant;
import com.yss.ams.sec.information.support.modules.sv.base.cache.service.ISecbaseCacheDataService;
import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecBase;
import com.yss.framework.api.cache.CacheRefreshInfo;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseController;
import com.yss.framework.api.service.ServiceException;


/**
 * 证券缓存查询服务，从缓存中查询
 * @author neil
 *
 */
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_SECINFO,
impl = "com.yss.ams.sec.information.modules.sv.base.cache.service.impl.SecbaseCacheDataService",
interfaceClass = com.yss.ams.sec.information.support.modules.sv.base.cache.service.ISecbaseCacheDataService.class,
productLine = ProductLine.SECINFO, serviceMapId = "ISecbaseCacheDataService", menuId = "ISecbaseCacheDataService")
public interface ISecbaseCacheDataController extends IBaseController<ISecbaseCacheDataService> {

	@POST
	@Path("/getSecCacheByCode")
	@Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	@Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public SecBase getSecCacheByCode(String secCode);
	
	@POST
	@Path("/getCacheByKey")
	@Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	@Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public SecBase getCacheByKey(String key);
	
	@POST
	@Path("/getDataList")
	@Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	@Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public List<SecBase> getDataList() throws ServiceException;

	@POST
	@Path("/getRate")
	@Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	@Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public SecBase getRate(SecBase secBase) throws ServiceException;
	
	@POST
	@Path("/getSec")
	@Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	@Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public SecBase getSec(SecBase secBase) throws ServiceException;
	
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
	public List<SecBase> getCacheList();
	
	@POST
	@Path("/getCacheList1")
	@Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
	public List<SecBase> getCacheList(@FormParam("begin")int begin,@FormParam("end")int end);
	
	@POST
	@Path("/getDataListBySecVars")
	@Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	@Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public List<SecBase> getDataListBySecVars(String[] secVars);
	
	@POST
	@Path("/getDataListSB")
	@Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
	public List<SecBase> getDataListSB(@FormParam("mktCode") String mktCode,@FormParam("secVarPre")String secVarPre)
			throws ServiceException;
	
	@POST
	@Path("/deleteData")
	@Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	@Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public void deleteData(List<String> secCodes);

	@POST
	@Path("/reloadData")
	@Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	@Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public void reloadData();
	
	@POST
	@Path("/getTimestamp")
	@Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	@Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public String getTimestamp();
	
	@POST
	@Path("/getCacheListCount")
	@Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	@Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public int getCacheListCount();
	
	@POST
	@Path("/update")
	@Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	@Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public void update(CacheRefreshInfo cacheRefreshInfo);
	
}
