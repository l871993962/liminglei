package com.yss.ams.base.information.support.bi.accountTreeA.controller;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;
import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.ams.base.information.support.bi.accountTreeA.pojo.AccountTreeA;
import com.yss.ams.base.information.support.bi.accountTreeA.service.IAccountTreeAService;

/**
*
* @author neil
* @date 2020-09-07 14:27:01
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_BASEINFO,
impl = "com.yss.ams.base.information.modules.bi.accountTreeA.service.impl.AccountTreeAService",
interfaceClass = com.yss.ams.base.information.support.bi.accountTreeA.service.IAccountTreeAService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IAccountTreeAService", menuId = "base_accountTreeA")
public interface IAccountTreeAController extends IBaseServiceBusController<AccountTreeA,IAccountTreeAService> {


    @POST
    @Path("/getTreeViewData")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<AccountTreeA> getTreeViewData(HashMap<String,Object> paraMap);

}