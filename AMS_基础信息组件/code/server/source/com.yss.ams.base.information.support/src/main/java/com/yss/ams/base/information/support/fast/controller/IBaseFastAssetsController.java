package com.yss.ams.base.information.support.fast.controller;

import javax.ws.rs.Path;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.commonInfo.controller.IFastAssetsController;
import com.yss.framework.api.commonInfo.service.IFastAssetsService;

@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_BASEINFO,
impl = "com.yss.ams.base.information.fast.service.FastAssetsService",
interfaceClass = com.yss.framework.api.commonInfo.service.IFastAssetsService.class,
productLine = ProductLine.PRODUCTINFO, serviceMapId = "IFastAssetsService", menuId = "IFastAssetsService")
public interface IBaseFastAssetsController extends IFastAssetsController<IFastAssetsService> {

}
