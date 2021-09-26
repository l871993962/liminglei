package com.yss.ams.sec.information.support.modules.ac.etfskep.controller;

import com.yss.ams.sec.information.support.modules.ac.etfskep.pojo.EtfSkep;
import com.yss.ams.sec.information.support.modules.ac.etfskep.service.IEtfSkepService;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;
import com.yss.ams.sec.information.support.common.YssServiceIdConstant;
import javax.ws.rs.Path;
/**
*
* @author neil
* @date 2020-09-10 10:34:58
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_SECINFO,
impl = "com.yss.ams.sec.information.modules.ac.etfskep.service.impl.EtfSkepService",
interfaceClass = com.yss.ams.sec.information.support.modules.ac.etfskep.service.IEtfSkepService.class,
productLine = ProductLine.SECINFO, serviceMapId = "IEtfSkepService", menuId = "sv_etfgpbakt")
public interface IEtfSkepController extends IBaseServiceBusController<EtfSkep,IEtfSkepService> {


}