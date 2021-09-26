package com.yss.ams.base.information.support.sys.voc.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.ams.base.information.support.sys.voc.pojo.UcoVocVo;
import com.yss.ams.base.information.support.sys.voc.service.IUcoVocService;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseController;

/**
*
* @author neil
* @date 2020-09-07 18:11:11
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_BASEINFO,
impl = "com.yss.ams.base.information.modules.sys.voc.service.impl.UcoVocService",
interfaceClass = com.yss.ams.base.information.support.sys.voc.service.IUcoVocService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IUcoVocService")
public interface IUcoVocController extends IBaseController<IUcoVocService> {


    @POST
    @Path("/addAndUpdUcoVoc")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public void addAndUpdUcoVoc(UcoVocVo vo)throws Exception;

}