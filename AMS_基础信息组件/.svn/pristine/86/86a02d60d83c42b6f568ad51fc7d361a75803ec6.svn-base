package com.yss.ams.base.information.support.util.holidays.controller;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.ams.base.information.support.util.holidays.IHolidaysAideService;
import com.yss.ams.base.information.support.util.holidays.vo.HolidaysAideVo;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.exception.YssException;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseController;

/**
*
* @author neil
* @date 2020-09-07 18:11:10
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_BASEINFO,
impl = "com.yss.ams.base.information.util.holidays.HolidaysAideService",
interfaceClass = com.yss.ams.base.information.support.util.holidays.IHolidaysAideService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "HolidaysAideService", menuId = "base_holidays")
public interface IHolidaysAideController extends IBaseController<IHolidaysAideService> {


    @POST
    @Path("/isHoliday")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public boolean isHoliday(HolidaysAideVo vo)throws YssException;

    @POST
    @Path("/getWorkDayByHolidayCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public Date getWorkDayByHolidayCode(HolidaysAideVo vo) throws YssException;

    @POST
    @Path("/getLastTradeDate")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public Date getLastTradeDate(@FormParam("bdhy") String bdhy,@FormParam("marketCode") String marketCode,@FormParam("bdpz") String bdpz) throws YssException;

    @POST
    @Path("/getWorkDay")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public Date getWorkDay(HolidaysAideVo vo) throws YssException;

}