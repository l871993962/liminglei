package com.yss.ams.product.information.support.modules.cp.pubacc.controller;

import javax.ws.rs.Path;

import com.yss.ams.product.information.support.modules.cp.pubacc.pojo.PubAcc;
import com.yss.ams.product.information.support.modules.cp.pubacc.service.IPubAccService;
import com.yss.common.YssServiceIdConstant;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;

/**
*
* @author neil
* @date 2020-09-14 11:34:49
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_PRODUCTINFO,
impl = "com.yss.ams.product.information.modules.cp.pubacc.service.impl.PubAccService",
interfaceClass = com.yss.ams.product.information.support.modules.cp.pubacc.service.IPubAccService.class,
productLine = ProductLine.PRODUCTINFO, serviceMapId = "IPubAccService", menuId = "pd_pubAccInfo")
public interface IPubAccController extends IBaseServiceBusController<PubAcc,IPubAccService> {


}