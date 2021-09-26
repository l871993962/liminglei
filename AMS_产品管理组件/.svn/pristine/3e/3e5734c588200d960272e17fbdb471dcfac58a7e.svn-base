package com.yss.ams.product.information.support.modules.cp.fax.controller;

import javax.ws.rs.Path;

import com.yss.ams.product.information.support.modules.cp.fax.pojo.BaseSealRelaInfo;
import com.yss.ams.product.information.support.modules.cp.fax.service.IElecSealRelaService;
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
impl = "com.yss.ams.product.information.modules.cp.fax.service.imp.ElecSealRelaService",
interfaceClass = com.yss.ams.product.information.support.modules.cp.fax.service.IElecSealRelaService.class,
productLine = ProductLine.PRODUCTINFO, serviceMapId = "IElecSealRelaService", menuId = "portRelaSealInfo")
public interface IElecSealRelaController extends IBaseServiceBusController<BaseSealRelaInfo,IElecSealRelaService> {


}