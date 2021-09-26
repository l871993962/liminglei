package com.yss.ams.base.information.support.bi.hdaygroup.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.ams.base.information.support.bi.hdaygroup.pojo.HdayGroup;
import com.yss.ams.base.information.support.bi.hdaygroup.service.IHDayCacheDataService;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseController;

/**
*
* @author neil
* @date 2020-09-07 18:11:10
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_BASEINFO,
impl = "com.yss.ams.base.information.modules.bi.hdaygroup.service.impl.HDayCacheDataService",
interfaceClass = com.yss.ams.base.information.support.bi.hdaygroup.service.IHDayCacheDataService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IHDayCacheDataService", menuId = "IHDayCacheDataService")
public interface IHDayCacheDataController extends IBaseController<IHDayCacheDataService> {


    @POST
    @Path("/getCacheByKey")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public HdayGroup getCacheByKey(String key);
	
    @POST
    @Path("/getCacheList")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public List<HdayGroup> getCacheList();
	
    @POST
    @Path("/getHDayGroupAllDate")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public HashMap<Integer, List<Date>> getHDayGroupAllDate(String hdayCode);
    
    @POST
    @Path("/getKeyConvertMap")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public HashMap<String, String> getKeyConvertMap(List<String> listKey);

}