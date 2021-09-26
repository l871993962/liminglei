package com.yss.ams.base.information.support.bi.account.controller;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;
import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.ams.base.information.support.bi.account.pojo.AreaCity;
import com.yss.ams.base.information.support.bi.account.service.IAreaCityDataService;

/**
* STORY #94053 业务组件osgi的restful改造
* @author neil
* @date 2020-08-24 16:19:33
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_BASEINFO,
impl = "com.yss.ams.base.information.modules.bi.account.service.impl.AreaCityDataService",
interfaceClass = com.yss.ams.base.information.support.bi.account.service.IAreaCityDataService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IAreaCityDataService", menuId = "base_areacity")
public interface IAreaCityDataController extends  IBaseServiceBusController<AreaCity,IAreaCityDataService> {


	@POST
    @Path("/getKeyConvertMap")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public HashMap<String,String> getKeyConvertMap() throws Exception;

    @POST
    @Path("/queryAccCityByAccNo")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String queryAccCityByAccNo(String accNo);

    @POST
    @Path("/queryAccProvinceByAccNo")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String queryAccProvinceByAccNo(String accNo);

}


