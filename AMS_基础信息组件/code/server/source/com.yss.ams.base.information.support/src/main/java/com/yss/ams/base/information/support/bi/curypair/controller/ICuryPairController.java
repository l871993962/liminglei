package com.yss.ams.base.information.support.bi.curypair.controller;

import javax.ws.rs.Path;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.ams.base.information.support.bi.curypair.pojo.CuryPair;
import com.yss.ams.base.information.support.bi.curypair.service.ICuryPairService;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;

/**
*
* @author neil
* @date 2020-09-07 18:11:10
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_BASEINFO,
impl = "com.yss.ams.base.information.modules.bi.curypair.service.impl.CuryPairService",
interfaceClass = com.yss.ams.base.information.support.bi.curypair.service.ICuryPairService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "ICuryPairService", menuId = "base_currencyPair")
public interface ICuryPairController extends IBaseServiceBusController<CuryPair,ICuryPairService>{


}