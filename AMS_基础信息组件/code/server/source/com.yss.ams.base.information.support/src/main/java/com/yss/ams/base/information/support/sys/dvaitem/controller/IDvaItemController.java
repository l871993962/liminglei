package com.yss.ams.base.information.support.sys.dvaitem.controller;

import javax.ws.rs.Path;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.ams.base.information.support.sys.dvaitem.pojo.DvaItem;
import com.yss.ams.base.information.support.sys.dvaitem.service.IDvaItemService;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;

/**
*
* @author neil
* @date 2020-09-07 18:11:11
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_BASEINFO,
impl = "com.yss.ams.base.information.modules.sys.dvaitem.service.impl.DvaItemService",
interfaceClass = com.yss.ams.base.information.support.sys.dvaitem.service.IDvaItemService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IDvaItemService", menuId = "base_dvaitemquy")
public interface IDvaItemController extends IBaseServiceBusController<DvaItem,IDvaItemService> {


}