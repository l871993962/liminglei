package com.yss.ams.sec.information.support.modules.mp.hggthq.controller;

import javax.ws.rs.Path;

import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;
import com.yss.ams.sec.information.support.common.YssServiceIdConstant;
import com.yss.ams.sec.information.support.modules.mp.hggthq.pojo.CounterRate;
import com.yss.ams.sec.information.support.modules.mp.hggthq.service.ICounterRateSecService;

/**
*
* @author neil
* @date 2020-09-10 15:18:11
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_SECINFO,
impl = "com.yss.ams.sec.information.modules.mp.hggthq.service.impl.CounterRateSecService",
interfaceClass = com.yss.ams.sec.information.support.modules.mp.hggthq.service.ICounterRateSecService.class,
productLine = ProductLine.SECINFO, serviceMapId = "ICounterRateSecService", menuId = "sv_counterratesec")
public interface ICounterRateSecController extends IBaseServiceBusController<CounterRate,ICounterRateSecService> {


}