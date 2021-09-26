package com.yss.ams.base.information.support.sys.finitem.controller;

import javax.ws.rs.Path;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.ams.base.information.support.sys.finitem.pojo.FinItem;
import com.yss.ams.base.information.support.sys.finitem.service.IFinItemService;
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
impl = "com.yss.ams.base.information.modules.sys.finitem.service.impl.FinItemService",
interfaceClass = com.yss.ams.base.information.support.sys.finitem.service.IFinItemService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IFinItemService", menuId = "base_finitemquy")
public interface IFinItemController extends IBaseServiceBusController<FinItem,IFinItemService> {


}