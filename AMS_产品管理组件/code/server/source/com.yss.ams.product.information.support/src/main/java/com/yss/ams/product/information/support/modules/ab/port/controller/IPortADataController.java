package com.yss.ams.product.information.support.modules.ab.port.controller;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.product.information.support.modules.ab.port.pojo.Port_A;
import com.yss.ams.product.information.support.modules.ab.port.service.IPortADataService;
import com.yss.common.YssServiceIdConstant;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;

/**
*
* @author neil
* @date 2020-09-14 11:34:49
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_PRODUCTINFO,
impl = "com.yss.ams.product.information.modules.ab.port.service.impl.PortADataService",
interfaceClass = com.yss.ams.product.information.support.modules.ab.port.service.IPortADataService.class,
productLine = ProductLine.PRODUCTINFO, serviceMapId = "IPortADataService", menuId = "pd_portfolio_A")
public interface IPortADataController extends IBaseController<IPortADataService> {


    @POST
    @Path("/getDataList")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<Port_A> getDataList();

    @POST
    @Path("/getDataListRes")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<Port_A> getDataListRes();

    @POST
    @Path("/getDataByCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public Port_A getDataByCode(String dataCode);

    @POST
    @Path("/doPortFilterRes")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public RestfulQueryResult<Port_A> doPortFilterRes(@FormParam("isDataRight") String isDataRight,@FormParam("datClass") String datClass,@FormParam("dvPortCode") String dvPortCode,@FormParam("trCode") String trCode);

    @POST
    @Path("/getAssList")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public HashMap<String,Port_A> getAssList();

    @POST
    @Path("/saveToOftenUsePort")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String saveToOftenUsePort(List<Port_A> basePojoList);

    @POST
    @Path("/getOftenUsePortList")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<String> getOftenUsePortList();

    @POST
    @Path("/deleteOftenUsePort")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String deleteOftenUsePort(List<Port_A> basePojoList);

    @POST
    @Path("/doPortFilterResByMenuid")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public RestfulQueryResult<Port_A> doPortFilterRes(@FormParam("isDataRight") String isDataRight,@FormParam("datClass") String datClass,@FormParam("dvPortCode") String dvPortCode,@FormParam("trCode") String trCode,@FormParam("menuId") String menuId);

}