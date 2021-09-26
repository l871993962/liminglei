package com.yss.ams.product.information.support.modules.aa.portcls.controller;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.product.information.support.modules.aa.portcls.pojo.PortCls;
import com.yss.ams.product.information.support.modules.aa.portcls.service.IPortClsService;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.DeleteByIdVo;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.InsertVo;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.UpdateByIdVo;
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
impl = "com.yss.ams.product.information.modules.aa.portcls.service.impl.PortClsService",
interfaceClass = com.yss.ams.product.information.support.modules.aa.portcls.service.IPortClsService.class,
productLine = ProductLine.PRODUCTINFO, serviceMapId = "IPortClsService", menuId = "pd_productgrade")
public interface IPortClsController extends IBaseServiceBusController<PortCls,IPortClsService> {


    @POST
    @Path("/updateDueDate")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public void updateDueDate(@FormParam("portCode") String portCode,@FormParam("dueDate") String dueDate);

    @POST
    @Path("/getPortClsCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public String getPortClsCode(@FormParam("portCode") String portCode,@FormParam("portClsType") String portClsType);

    @POST
    @Path("/getPortClsByUser")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<PortCls> getPortClsByUser(String userCode);

    @POST
    @Path("/getPortClsByPortCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<PortCls> getPortClsByPortCode(String portCode);

    @POST
    @Path("/checkDate")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String checkDate(HashMap<String,String> paraMap);

    @POST
    @Path("/checkDateQSRQ")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String checkDateQSRQ(HashMap<String,String> paraMap);

    @POST
    @Path("/insert")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String insert(InsertVo vo);

    @POST
    @Path("/deleteById")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String deleteById(DeleteByIdVo vo);

    @POST
    @Path("/updateById")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String updateById(UpdateByIdVo vo);

}