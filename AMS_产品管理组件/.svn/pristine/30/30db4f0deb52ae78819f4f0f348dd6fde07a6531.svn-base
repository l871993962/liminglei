package com.yss.ams.product.information.support.fast.controller;

import javax.ws.rs.Path;

import com.yss.common.YssServiceIdConstant;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.commonInfo.controller.IFastCommonInfoController;
import com.yss.framework.api.commonInfo.service.IFastCommonInfoService;

@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_PRODUCTINFO,
impl = "com.yss.ams.product.information.fast.service.FastCommonInfoService",
interfaceClass = com.yss.framework.api.commonInfo.service.IFastCommonInfoService.class,
productLine = ProductLine.PRODUCTINFO, serviceMapId = "IFastCommonInfoService", menuId = "IFastAssetsTreeService")
public interface IBaseFastCommonInfoController extends IFastCommonInfoController<IFastCommonInfoService> {

}
