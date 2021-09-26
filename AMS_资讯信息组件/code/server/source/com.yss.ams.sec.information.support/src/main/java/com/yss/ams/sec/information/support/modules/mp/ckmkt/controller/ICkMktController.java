package com.yss.ams.sec.information.support.modules.mp.ckmkt.controller;


import javax.ws.rs.Path;
import com.yss.ams.sec.information.support.common.YssServiceIdConstant;
import com.yss.ams.sec.information.support.modules.mp.ckmkt.service.ICkMktService;
import com.yss.ams.sec.information.support.modules.mp.secmkt.pojo.SecMkt;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;

/**
*
* @author neil
* @date 2020-09-10 10:34:58
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_SECINFO,
impl = "com.yss.ams.sec.information.modules.mp.ckmkt.service.impl.CkMktService",
interfaceClass = com.yss.ams.sec.information.support.modules.mp.ckmkt.service.ICkMktService.class,
productLine = ProductLine.SECINFO, serviceMapId = "ICkMktService", menuId = "sv_depositinterest")
public interface ICkMktController extends IBaseServiceBusController<SecMkt,ICkMktService> {

}