package com.yss.ams.base.information.support.sys.daeelem.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.ams.base.information.support.sys.daeelem.pojo.DaeElem;
import com.yss.ams.base.information.support.sys.daeelem.service.IDaeElemService;
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
impl = "com.yss.ams.base.information.modules.sys.daeelem.service.impl.DaeElemService",
interfaceClass = com.yss.ams.base.information.support.sys.daeelem.service.IDaeElemService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IDaeElemService", menuId = "base_DaeElem")
public interface IDaeElemController extends IBaseServiceBusController<DaeElem,IDaeElemService> {


    @POST
    @Path("/getDaeNameByDaeCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String getDaeNameByDaeCode(String daeCode);

    @POST
    @Path("/getDaeCodesByCondition")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public ArrayList<String> getDaeCodesByCondition(HashMap<String,String> paraMap);

    @POST
    @Path("/isDetailByCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String isDetailByCode(String code);

    @POST
    @Path("/getParentCodeByCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String getParentCodeByCode(String code);

}