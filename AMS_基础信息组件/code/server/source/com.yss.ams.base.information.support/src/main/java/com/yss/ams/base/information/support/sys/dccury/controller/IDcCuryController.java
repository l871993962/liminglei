package com.yss.ams.base.information.support.sys.dccury.controller;

import javax.ws.rs.Path;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.ams.base.information.support.sys.dccury.pojo.DcCury;
import com.yss.ams.base.information.support.sys.dccury.service.IDcCuryService;
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
impl = "com.yss.ams.base.information.modules.sys.dccury.service.impl.DcCuryService",
interfaceClass = com.yss.ams.base.information.support.sys.dccury.service.IDcCuryService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IDcCuryService", menuId = "base_currency")
public interface IDcCuryController extends IBaseServiceBusController<DcCury,IDcCuryService> {


}