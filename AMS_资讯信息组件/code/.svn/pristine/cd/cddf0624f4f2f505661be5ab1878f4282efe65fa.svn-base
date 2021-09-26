package com.yss.ams.sec.information.support.modules.mp.indexmp.controller;

import javax.ws.rs.Path;

import com.yss.ams.sec.information.support.common.YssServiceIdConstant;
import com.yss.ams.sec.information.support.modules.mp.indexmp.service.IIndexMpService;
import com.yss.ams.sec.information.support.modules.mp.secmkt.pojo.SecMkt;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;

/**
*
* @author neil
* @date 2020-09-10 15:18:11
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_SECINFO,
impl = "com.yss.ams.sec.information.modules.mp.indexmp.service.impl.IndexMpService",
interfaceClass = com.yss.ams.sec.information.support.modules.mp.indexmp.service.IIndexMpService.class,
productLine = ProductLine.SECINFO, serviceMapId = "IIndexMpService", menuId = "sv_indexmp")
public interface IIndexMpController extends IBaseServiceBusController<SecMkt,IIndexMpService> {


}