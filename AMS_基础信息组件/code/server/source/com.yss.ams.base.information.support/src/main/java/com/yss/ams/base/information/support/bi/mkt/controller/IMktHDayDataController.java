package com.yss.ams.base.information.support.bi.mkt.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.ams.base.information.support.bi.mkt.service.IMktHDayDataService;
import com.yss.framework.api.common.co.Mkt;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.exception.YssException;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;

/**
*
* @author neil
* @date 2020-09-07 18:11:10
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_BASEINFO,
impl = "com.yss.ams.base.information.modules.bi.mkt.service.impl.MktHDayDataService",
interfaceClass = com.yss.ams.base.information.support.bi.mkt.service.IMktHDayDataService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IMktHDayDataService", menuId = "base_mkthday")
public interface IMktHDayDataController extends IBaseController<IMktHDayDataService> {


    @POST
    @Path("/getDataList")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<Mkt> getDataList();

    @POST
    @Path("/getDataListRes")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<Mkt> getDataListRes();

    @POST
    @Path("/getDataByCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public Mkt getDataByCode(String dataCode);

    @POST
    @Path("/getMarketHDaysData")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public HashMap<String,String> getMarketHDaysData() throws YssException;

    @POST
    @Path("/getHolidays")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public HashMap<String,HashMap<Integer,List<Date>>> getHolidays() throws YssException;

    @POST
    @Path("/getHolidays1")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public HashMap<String,HashMap<Integer,List<Date>>> getHolidays1(String code) throws YssException;

}