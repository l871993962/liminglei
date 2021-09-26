package com.yss.ams.product.information.support.modules.ab.assetstree_b.controller;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.product.information.support.modules.ab.assetstree_b.pojo.AssetsTree_B;
import com.yss.ams.product.information.support.modules.ab.assetstree_b.service.IAssetsTree_BService;
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
impl = "com.yss.ams.product.information.modules.ab.assetstree_b.service.impl.AssetsTree_BService",
interfaceClass = com.yss.ams.product.information.support.modules.ab.assetstree_b.service.IAssetsTree_BService.class,
productLine = ProductLine.PRODUCTINFO, serviceMapId = "IAssertTreeService", menuId = "pd_assetsTree")
public interface IAssetsTree_BController extends IBaseServiceBusController<AssetsTree_B,IAssetsTree_BService> {


    @POST
    @Path("/isSameAssetType")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public String isSameAssetType(@FormParam("portCode") String portCode,@FormParam("dragPortCode") String dragPortCode);

    @POST
    @Path("/updateOrdelete")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public int updateOrdelete(@FormParam("id") String id,@FormParam("trCode") String trCode,@FormParam("isParent") String isParent,@FormParam("type") String type);

    @POST
    @Path("/updateOrdelete1")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public int updateOrdelete(@FormParam("id") String id,@FormParam("trCode") String trCode,@FormParam("isParent") String isParent,@FormParam("type") String type,@FormParam("trCodeR") String trCodeR);

    @POST
    @Path("/getUserId")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String getUserId(String quyCon) throws SQLException;

    @POST
    @Path("/getCodeByCId")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String getCodeByCId(String id);
}