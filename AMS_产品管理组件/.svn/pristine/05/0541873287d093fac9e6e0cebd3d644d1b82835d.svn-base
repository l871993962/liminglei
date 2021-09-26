package com.yss.ams.product.information.support.modules.aa.portcustom.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.product.information.support.modules.aa.portcustom.pojo.PortCustom;
import com.yss.ams.product.information.support.modules.aa.portcustom.service.IPortCustomService;
import com.yss.ams.product.information.support.modules.aa.portcustom.vo.InsertCustomPortVo;
import com.yss.common.YssServiceIdConstant;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;

/**
*
* @author neil
* @date 2020-09-14 11:34:49
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_PRODUCTINFO,
impl = "com.yss.ams.product.information.modules.aa.portcustom.service.impl.PortCustomService",
interfaceClass = com.yss.ams.product.information.support.modules.aa.portcustom.service.IPortCustomService.class,
productLine = ProductLine.PRODUCTINFO, serviceMapId = "IPortCustomService", menuId = "pd_portcustom")
public interface IPortCustomController extends IBaseServiceBusController<PortCustom,IPortCustomService> {


    @POST
    @Path("/getUserDefaultPort")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public ArrayList<String> getUserDefaultPort(HashMap<String,String> paradict);

    @POST
    @Path("/getAssetType")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public ArrayList<String> getAssetType();

    @POST
    @Path("/getShowType")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String getShowType(HashMap<String,String> codeMap);

    @POST
    @Path("/deleteCustomPort")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String deleteCustomPort(HashMap<String,String> param);

    @POST
    @Path("/insertCustomPort")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String insertCustomPort(InsertCustomPortVo vo);

}