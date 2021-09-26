package com.yss.ams.base.information.support.bi.hday.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.ams.base.information.support.bi.hday.pojo.Hday;
import com.yss.ams.base.information.support.bi.hday.service.IHdayService;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;

/**
*
* @author neil
* @date 2020-09-07 18:11:10
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_BASEINFO,
impl = "com.yss.ams.base.information.modules.bi.hday.service.impl.HdayService",
interfaceClass = com.yss.ams.base.information.support.bi.hday.service.IHdayService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IHdayService", menuId = "base_holidays")
public interface IHdayController extends IBaseServiceBusController<Hday,IHdayService> {


    @POST
    @Path("/getAllYear")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<String> getAllYear(String code);

    @POST
    @Path("/getAllHoiday")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<String> getAllHoiday(HashMap<String,Object> hashMap);

    @POST
    @Path("/getHoidayByCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<Date> getHoidayByCode(String C_HDAY_CODE);
    
    @POST
    @Path("/getAuditHoidayByCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<Date> getAuditHoidayByCode(String C_HDAY_CODE);

    @POST
    @Path("/getHoidayByCodeAndCheck")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public List<Date> getHoidayByCodeAndCheck(@FormParam("C_HDAY_CODE") String C_HDAY_CODE,@FormParam("N_CHECK_STATE") String N_CHECK_STATE);

    @POST
    @Path("/getSameHoiday")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String getSameHoiday(HashMap<String,Object> hashMap);

    @POST
    @Path("/getDay")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public String getDay(@FormParam("date") String date,@FormParam("offset") String offset,@FormParam("hdayCode") String hdayCode,@FormParam("hdayType") String hdayType);

    @POST
    @Path("/getWorkDayInMonth")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public String getWorkDayInMonth(@FormParam("date") String date,@FormParam("indexDay") int indexDay,@FormParam("C_HDAY_CODE") String C_HDAY_CODE);

    @POST
    @Path("/getWorkday")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public String getWorkday(@FormParam("specifiedDate") String specifiedDate,@FormParam("offset") String offset,@FormParam("portCode") String portCode);

    @POST
    @Path("/getHdayMap")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public HashMap<String,String> getHdayMap();

    @POST
    @Path("/getHdayByHday")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public Hday getHdayByHday(@FormParam("hdayCode") String hdayCode,@FormParam("hday") String hday);

    @POST
    @Path("/getHdayByYearOrMonth")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public List<Hday> getHdayByYearOrMonth(@FormParam("hdayCode") String hdayCode,@FormParam("yearOrMonth") String yearOrMonth);

}