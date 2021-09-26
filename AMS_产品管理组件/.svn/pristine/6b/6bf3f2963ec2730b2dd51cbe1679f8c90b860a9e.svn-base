package com.yss.ams.product.information.support.fast.controller;

import javax.ws.rs.Path;

import com.yss.common.YssServiceIdConstant;
import com.yss.framework.api.commonInfo.controller.IFastAssetsTreeController;
import com.yss.framework.api.commonInfo.service.IFastAssetsTreeService;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;

@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_PRODUCTINFO,
impl = "com.yss.ams.product.information.fast.service.FastAssetsTreeService",
interfaceClass = com.yss.framework.api.commonInfo.service.IFastAssetsTreeService.class,
productLine = ProductLine.PRODUCTINFO, serviceMapId = "IFastAssetsTreeService", menuId = "IFastAssetsTreeService")
public interface IBaseFastAssetsTreeController extends IFastAssetsTreeController<IFastAssetsTreeService>{

}
