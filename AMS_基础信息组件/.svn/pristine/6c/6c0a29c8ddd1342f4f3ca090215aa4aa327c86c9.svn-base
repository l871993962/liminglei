package com.yss.ams.base.information.support.bi.org.controller;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.ams.base.information.support.bi.org.pojo.Org;
import com.yss.ams.base.information.support.bi.org.pojo.OrgVo;
import com.yss.ams.base.information.support.bi.org.service.IOrgLinkRelaService;
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
impl = "com.yss.ams.base.information.modules.bi.org.service.impl.OrgLinkRelaService",
interfaceClass = com.yss.ams.base.information.support.bi.org.service.IOrgLinkRelaService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IOrgLinkRelaService", menuId = "base_organ")
public interface IOrgLinkRelaController extends IBaseServiceBusController<Org,IOrgLinkRelaService> {


    @POST
    @Path("/getOrgLinkManList")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<Org> getOrgLinkManList(String orgCode);

    @POST
    @Path("/getOrgLinkManData")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public Map<String,Org> getOrgLinkManData(String orgCode);

    @POST
    @Path("/updateOrgLinkMan")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public void updateOrgLinkMan(OrgVo vo);

    @POST
    @Path("/deleteOrgLinkMan")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public void deleteOrgLinkMan(String orgCode);

}