package com.yss.ams.base.information.support.bi.account.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseController;
import com.yss.framework.api.restful.base.IBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;
import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.ams.base.information.support.bi.account.service.IPortRelaDataService;

/**
*
* @author neil
* @date 2020-09-07 14:27:01
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_BASEINFO,
impl = "com.yss.ams.base.information.modules.bi.account.service.impl.PortRelaDataServiceImpl",
interfaceClass = com.yss.ams.base.information.support.bi.account.service.IPortRelaDataService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IPortRelaDataService", menuId = "base_portRela")
public interface IPortRelaDataController extends IBaseController<IPortRelaDataService> {


    @POST
    @Path("/getCashPortByInfo")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String getCashPortByInfo(String relaCode) throws Exception;

    @POST
    @Path("/deletePortRela")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public void deletePortRela(@FormParam("relaCode") String relaCode,@FormParam("portCodes") String portCodes) throws Exception;

    @POST
    @Path("/updatePortRela")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public void updatePortRela(@FormParam("relaCode") String relaCode,@FormParam("portCodes") String portCodes) throws Exception;

    @POST
    @Path("/insertPortRela")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public void insertPortRela(@FormParam("relaCode") String relaCode,@FormParam("portCodes") String portCodes) throws Exception;;

}