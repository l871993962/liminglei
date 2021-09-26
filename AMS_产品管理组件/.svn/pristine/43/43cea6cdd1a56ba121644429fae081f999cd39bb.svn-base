package com.yss.ams.product.information.support.modules.ab.productInfoQuery.controller;

import javax.ws.rs.Path;

import com.yss.ams.product.information.support.modules.ab.productInfoQuery.service.IProductInfoService;
import com.yss.common.YssServiceIdConstant;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseController;

/**
*
* @author wanghaocheng
* @date 2021-03-14 11:34:48
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_PRODUCTINFO,
impl = "com.yss.ams.product.information.modules.ab.productInfoQuery.service.impl.ProductInfoService",
interfaceClass = com.yss.ams.product.information.support.modules.ab.productInfoQuery.service.IProductInfoService.class,
productLine = ProductLine.PRODUCTINFO, serviceMapId = "IProductInfoService", menuId = "ProductInfoQuery")
public interface IProductInfoQueryController extends IBaseController<IProductInfoService> {


  
}