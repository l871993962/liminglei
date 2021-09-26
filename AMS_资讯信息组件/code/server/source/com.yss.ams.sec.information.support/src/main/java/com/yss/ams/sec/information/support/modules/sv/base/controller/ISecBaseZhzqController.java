package com.yss.ams.sec.information.support.modules.sv.base.controller;

import javax.ws.rs.Path;

import com.yss.ams.sec.information.support.common.YssServiceIdConstant;
import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecBase;
import com.yss.ams.sec.information.support.modules.sv.base.service.ISecBaseZhzqService;
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
impl = "com.yss.ams.sec.information.modules.sv.base.service.impl.SecBaseZhzqService",
interfaceClass = com.yss.ams.sec.information.support.modules.sv.base.service.ISecBaseZhzqService.class,
productLine = ProductLine.SECINFO, serviceMapId = "ISecBaseZhzqService", menuId = "sv_sec")
public interface ISecBaseZhzqController extends IBaseServiceBusController<SecBase,ISecBaseZhzqService> {


}