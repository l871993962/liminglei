package com.yss.ams.sec.information.support.modules.mp.hlmkt.controller;

import javax.ws.rs.Path;

import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;
import com.yss.ams.sec.information.support.common.YssServiceIdConstant;
import com.yss.ams.sec.information.support.modules.mp.hlmkt.service.IHlMktService;
import com.yss.ams.sec.information.support.modules.mp.secmkt.pojo.SecMkt;

/**
*
* @author neil
* @date 2020-09-10 15:18:11
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_SECINFO,
impl = "com.yss.ams.sec.information.modules.mp.hlmkt.service.impl.HlMktService",
interfaceClass = com.yss.ams.sec.information.support.modules.mp.hlmkt.service.IHlMktService.class,
productLine = ProductLine.SECINFO, serviceMapId = "IHlMktService", menuId = "sv_exratepriceadmin")
public interface IHlMktController extends IBaseServiceBusController<SecMkt,IHlMktService> {


}