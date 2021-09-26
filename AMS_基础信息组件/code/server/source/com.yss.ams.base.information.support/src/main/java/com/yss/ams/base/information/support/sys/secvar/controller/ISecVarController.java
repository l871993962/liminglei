package com.yss.ams.base.information.support.sys.secvar.controller;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.ams.base.information.support.sys.secvar.pojo.SecVar;
import com.yss.ams.base.information.support.sys.secvar.pojo.SecVarVo;
import com.yss.ams.base.information.support.sys.secvar.service.ISecVarService;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;

/**
*
* @author neil
* @date 2020-09-07 18:11:11
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_BASEINFO,
impl = "com.yss.ams.base.information.modules.sys.secvar.service.impl.SecVarService",
interfaceClass = com.yss.ams.base.information.support.sys.secvar.service.ISecVarService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "ISecVarService", menuId = "base_seccategory")
public interface ISecVarController extends IBaseServiceBusController<SecVar,ISecVarService> {


    @POST
    @Path("/selectWithSecAttr")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<SecVar> selectWithSecAttr(SecVarVo vo);

    @POST
    @Path("/queryIdxCtrlSec")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<SecVar> queryIdxCtrlSec(HashMap<String,Object> paraMap);

    @POST
    @Path("/queryVarcodeByCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<SecVar> queryVarcodeByCode(String seccode);

    @POST
    @Path("/querySecVar")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public HashMap<String,List<SecVar>> querySecVar() throws Exception;

}