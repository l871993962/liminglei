package com.yss.ams.base.information.support.fast.controller;

import javax.ws.rs.Path;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.framework.api.commonInfo.controller.IFastMktDataController;
import com.yss.framework.api.commonInfo.service.IFastMktDataService;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;


@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_BASEINFO,
impl = "com.yss.ams.base.information.fast.service.FastMktDataService",
interfaceClass = com.yss.framework.api.commonInfo.service.IFastMktDataService.class,
productLine = ProductLine.PRODUCTINFO, serviceMapId = "IFastMktDataService", menuId = "IFastMktDataService")
public interface IBaseFastMktDataController extends IFastMktDataController<IFastMktDataService> {

}
