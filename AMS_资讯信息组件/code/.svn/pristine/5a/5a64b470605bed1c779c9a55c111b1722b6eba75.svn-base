package com.yss.ams.sec.information.support.modules.mp.hggthq.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.sec.information.support.common.YssServiceIdConstant;
import com.yss.ams.sec.information.support.modules.mp.hggthq.pojo.CounterRate;
import com.yss.ams.sec.information.support.modules.mp.hggthq.pojo.CounterRateVo;
import com.yss.ams.sec.information.support.modules.mp.hggthq.service.ICounterRateService;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;

/**
*
* @author neil
* @date 2020-09-10 15:18:11
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_SECINFO,
impl = "com.yss.ams.sec.information.modules.mp.hggthq.service.impl.CounterRateService",
interfaceClass = com.yss.ams.sec.information.support.modules.mp.hggthq.service.ICounterRateService.class,
productLine = ProductLine.SECINFO, serviceMapId = "ICounterRateService", menuId = "sv_counterrate")
public interface ICounterRateController extends IBaseServiceBusController<CounterRate,ICounterRateService> {


    @POST
    @Path("/updateSecRate")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public int updateSecRate(CounterRateVo vo);

}