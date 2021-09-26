package com.yss.ams.base.information.support.bi.dactype.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.ams.base.information.support.bi.dactype.pojo.AccountType;
import com.yss.ams.base.information.support.bi.dactype.pojo.AccountTypeTree;
import com.yss.ams.base.information.support.bi.dactype.pojo.AccountTypeVo;
import com.yss.ams.base.information.support.bi.dactype.service.IAccountTypeService;
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
impl = "com.yss.ams.base.information.modules.bi.accountType.service.imp.AccountTypeService",
interfaceClass = com.yss.ams.base.information.support.bi.dactype.service.IAccountTypeService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IAccountTypeService", menuId = "base_accountType")
public interface IAccountTypeController extends IBaseServiceBusController<AccountType,IAccountTypeService> {


    @POST
    @Path("/accordingChildrenGetTree")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<AccountTypeTree> accordingChildrenGetTree(AccountTypeVo vo);

    @POST
    @Path("/getAccByCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<AccountTypeTree> getAccByCode(List<String> list);

    @POST
    @Path("/getSubAccountsByParent")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<AccountType> getSubAccountsByParent(String parentNode);

}