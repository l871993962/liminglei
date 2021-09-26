package com.yss.systemmanager.assetstree.controller;

import javax.ws.rs.Path;

import com.yss.common.YssServiceIdConstant;
import com.yss.fast.systemmanager.support.assetstree.controller.IFastAssetTreeController;
import com.yss.fast.systemmanager.support.assetstree.service.IFastAssetTreeService;
import com.yss.framework.api.commonInfo.pojo.FastAssetsTree_A;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;

@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_PRODUCTINFO,
impl = "com.yss.fast.systemmanager.assetstree.service.impl.FastAssetTreeService",
interfaceClass = com.yss.fast.systemmanager.support.assetstree.service.IFastAssetTreeService.class,
productLine = ProductLine.PRODUCTINFO, serviceMapId = "IFastAssetTreeService", menuId = "assetsTreeA")
public interface IBaseAssetTreeController extends IFastAssetTreeController<FastAssetsTree_A,IFastAssetTreeService>{

}
