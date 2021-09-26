package com.yss.ams.base.information.support.bi.ieLink.controller;

import javax.ws.rs.Path;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.ams.base.information.support.bi.ieLink.pojo.Fee;
import com.yss.ams.base.information.support.bi.ieLink.service.IFeeService;
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
impl = "com.yss.ams.base.information.modules.bi.ieLink.service.impl.FeeService",
interfaceClass = com.yss.ams.base.information.support.bi.ieLink.service.IFeeService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IFeeService", menuId = "base_feeBreed")
public interface IFeeController extends IBaseServiceBusController<Fee,IFeeService> {


}