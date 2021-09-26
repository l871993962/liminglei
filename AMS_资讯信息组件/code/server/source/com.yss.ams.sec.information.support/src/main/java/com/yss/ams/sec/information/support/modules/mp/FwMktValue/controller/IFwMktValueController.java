package com.yss.ams.sec.information.support.modules.mp.FwMktValue.controller;

import javax.ws.rs.Path;

import com.yss.ams.sec.information.support.common.YssServiceIdConstant;
import com.yss.ams.sec.information.support.modules.mp.FwMktValue.pojo.FwMktValue;
import com.yss.ams.sec.information.support.modules.mp.FwMktValue.service.IFwMktValueService;
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
impl = "com.yss.ams.sec.information.modules.mp.FwMktValue.service.impl.FwMktValueService",
interfaceClass = com.yss.ams.sec.information.support.modules.mp.FwMktValue.service.IFwMktValueService.class,
productLine = ProductLine.SECINFO, serviceMapId = "IFwMktValueService", menuId = "sv_fwmktvalue")
public interface IFwMktValueController extends IBaseServiceBusController<FwMktValue,IFwMktValueService> {


}