package com.yss.ams.base.information.support.bi.org.controller;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.ams.base.information.support.bi.org.pojo.Org;
import com.yss.ams.base.information.support.bi.org.pojo.OrgVo;
import com.yss.ams.base.information.support.bi.org.service.IOrgService;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;
import com.yss.framework.resource.mgr.pojo.FileTrans;

/**
*
* @author neil
* @date 2020-09-07 18:11:10
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_BASEINFO,
impl = "com.yss.ams.base.information.modules.bi.org.service.impl.OrgService",
interfaceClass = com.yss.ams.base.information.support.bi.org.service.IOrgService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IOrgService", menuId = "base_organ")
public interface IOrgController extends IBaseServiceBusController<Org,IOrgService> {


    @POST
    @Path("/getPortRelaOrg")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<Org> getPortRelaOrg(HashMap<String,Object> paraMap);

    @POST
    @Path("/getOrgVoc")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<Org> getOrgVoc();

    @POST
    @Path("/queryPortRelaOrg")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<Org> queryPortRelaOrg(OrgVo vo);

    @POST
    @Path("/uploadLogo")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String uploadLogo(FileTrans file);

    @POST
    @Path("/queryImage")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String queryImage(String imagePath);

    @POST
    @Path("/getOrgByConsignerType")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<Org> getOrgByConsignerType();

    @POST
    @Path("/connInsert")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String connInsert(OrgVo vo);

    @POST
    @Path("/connDelete")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public void connDelete(OrgVo vo);

    @POST
    @Path("/connUpdate")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public void connUpdate(OrgVo vo);

    @POST
    @Path("/updateByIds")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public CacheData updateByIds(String ids);

    @POST
    @Path("/deleteThenInsert")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public void deleteThenInsert(Org org);

    @POST
    @Path("/getDataListByTypes")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<Org> getDataListByTypes(String[] types)  throws Exception;

    @POST
    @Path("/getManagerNameByPortCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String getManagerNameByPortCode(String portCode);

    @POST
    @Path("/getOrgByOrgCodes")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<Org> getOrgByOrgCodes(String orgCodes) throws Exception;

    @POST
    @Path("/getPortCodeListByRelaOrgCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<String> getPortCodeListByRelaOrgCode(String orgCode);

    @POST
    @Path("/getOrgLinkManList")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<Org> getOrgLinkManList(String orgCode);

}