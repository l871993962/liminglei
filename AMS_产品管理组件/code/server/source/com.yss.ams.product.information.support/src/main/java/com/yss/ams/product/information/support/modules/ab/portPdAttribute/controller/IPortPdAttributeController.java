package com.yss.ams.product.information.support.modules.ab.portPdAttribute.controller;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.product.information.support.modules.ab.portPdAttribute.pojo.PortPdAttribute;
import com.yss.ams.product.information.support.modules.ab.portPdAttribute.service.IPortPdAttributeService;
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
impl = "com.yss.ams.product.information.modules.ab.portPdAttribute.service.impl.PortPdAttributeService",
interfaceClass = com.yss.ams.product.information.support.modules.ab.portPdAttribute.service.IPortPdAttributeService.class,
productLine = ProductLine.PRODUCTINFO, serviceMapId = "IPortPdAttributeService", menuId = "pd_portPdAttribute")
public interface IPortPdAttributeController extends IBaseServiceBusController<PortPdAttribute,IPortPdAttributeService> {

    @POST
    @Path("/getVocabularyDict")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public HashMap<String,String> getVocabularyDict();

    @POST
    @Path("/getAssetDict")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public HashMap<String,String> getAssetDict();

    @POST
    @Path("/getPortPojoList")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public HashMap<String,String> getPortPojoList(HashMap<String,String> paraMap);

    @POST
    @Path("/getPortNameDict")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public HashMap<String,String> getPortNameDict(HashMap<String,String> paraMap);

    @POST
    @Path("/queryPortCodesByType")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<String> queryPortCodesByType(String portType);

    @POST
    @Path("/getShortNumPort")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<PortPdAttribute> getShortNumPort();
    
    @POST
    @Path("/getPortCodeForNDay")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public HashMap<String, List<String>> getPortCodeForNDay(String ports) throws Exception;

}