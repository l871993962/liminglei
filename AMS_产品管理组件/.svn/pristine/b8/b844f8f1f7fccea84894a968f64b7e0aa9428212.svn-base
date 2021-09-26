package com.yss.ams.product.information.support.modules.ab.portrela.controller;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.framework.api.common.co.Port_A;
import com.yss.ams.product.information.support.modules.ab.portrela.pojo.PortRelaOrgan;
import com.yss.ams.product.information.support.modules.ab.portrela.service.IPortRelaOrganServcie;
import com.yss.ams.product.information.support.modules.ab.portrela.vo.QueryPortRelaOrganPageVo;
import com.yss.common.YssServiceIdConstant;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;

/**
*
* @author neil
* @date 2020-09-14 11:34:49
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_PRODUCTINFO,
impl = "com.yss.ams.product.information.modules.ab.portrela.service.impl.PortRelaOrganService",
interfaceClass = com.yss.ams.product.information.support.modules.ab.portrela.service.IPortRelaOrganServcie.class,
productLine = ProductLine.PRODUCTINFO, serviceMapId = "IPortRelaOrganService", menuId = "pd_portrelaorgan")
public interface IPortRelaOrganController extends IBaseServiceBusController<PortRelaOrgan,IPortRelaOrganServcie> {


    @POST
    @Path("/queryPortRelaOrgan")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<PortRelaOrgan> queryPortRelaOrgan(HashMap<String,Object> paraMap) throws Exception;

    @POST
    @Path("/queryPortRelaOrganPage")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<PortRelaOrgan> queryPortRelaOrganPage(QueryPortRelaOrganPageVo vo) throws Exception;

    @POST
    @Path("/checkORGConsignerForPort")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String checkORGConsignerForPort(HashMap<String,String> map);

    @POST
    @Path("/getPortInfoByConsigner")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<Port_A> getPortInfoByConsigner(String taxConsigner);

    @POST
    @Path("/getPortRelaOrgByPortAndDvType")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public HashMap<String,List<String>> getPortRelaOrgByPortAndDvType(@FormParam("portCodes") String portCodes,@FormParam("dvType") String dvType);

}