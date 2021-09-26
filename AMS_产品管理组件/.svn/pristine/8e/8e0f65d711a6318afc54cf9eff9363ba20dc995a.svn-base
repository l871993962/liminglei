package com.yss.ams.product.information.support.modules.aa.portcls.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.product.information.support.modules.aa.portcls.pojo.PortCls;
import com.yss.ams.product.information.support.modules.aa.portcls.service.IClsPortCacheDataService;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.GetCashListByPortWDQVo;
import com.yss.common.YssServiceIdConstant;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseController;

/**
*
* @author neil
* @date 2020-09-14 11:34:49
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_PRODUCTINFO,
impl = "com.yss.ams.product.information.modules.aa.portcls.service.impl.PortClsCacheDataService",
interfaceClass = com.yss.ams.product.information.support.modules.aa.portcls.service.IClsPortCacheDataService.class,
productLine = ProductLine.PRODUCTINFO, serviceMapId = "IClsPortCacheDataService", menuId = "IClsPortCacheDataService")
public interface IClsPortCacheDataController extends IBaseController<IClsPortCacheDataService> {

    @POST
    @Path("/getKey")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
	public String getKey(@FormParam("cPortCode")String cPortCode, @FormParam("cPortCls")String cPortCls) ;
	
    @POST
    @Path("/getCacheByKey")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public PortCls getCacheByKey(String key);
	
    @POST
    @Path("/getCacheByPortAndPortCls")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
	public PortCls getCacheByPortAndPortCls(@FormParam("portCode")String portCode,@FormParam("portClsCode")String portClsCode);
	
    @POST
    @Path("/getCashListByPort")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public List<PortCls> getCashListByPort(String portCode);
	
    @POST
    @Path("/getCashListByPortWDQ")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public List<PortCls> getCashListByPortWDQ(GetCashListByPortWDQVo vo);
	
    @POST
    @Path("/getCacheList")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public List<PortCls> getCacheList();
	
    @POST
    @Path("/getKeyConvertMap")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public HashMap<String, String> getKeyConvertMap();
	
    @POST
    @Path("/getKeyConvertMap1")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public HashMap<String, String> getKeyConvertMap(List<String> listKey);
    
    @POST
    @Path("/reloadData")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public void reloadData();
}