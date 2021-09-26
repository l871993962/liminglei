package com.yss.ams.product.information.support.modules.ab.assetsTree_a.controller;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.product.information.support.modules.ab.assetsTree_a.pojo.AssetTreeATreeView;
import com.yss.ams.product.information.support.modules.ab.assetsTree_a.pojo.AssetsTree_A;
import com.yss.ams.product.information.support.modules.ab.assetsTree_a.service.IAssetsTree_AService;
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
impl = "com.yss.ams.product.information.modules.ab.assetsTree_a.service.impl.AssetsTree_AService",
interfaceClass = com.yss.ams.product.information.support.modules.ab.assetsTree_a.service.IAssetsTree_AService.class,
productLine = ProductLine.PRODUCTINFO, serviceMapId = "IAssertTree_AService", menuId = "pd_assetsTree_A")
public interface IAssetsTree_AController extends IBaseServiceBusController<AssetsTree_A,IAssetsTree_AService> {


    @POST
    @Path("/getTreeViewData")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<AssetsTree_A> getTreeViewData(HashMap<String,Object> paraMap);

    @POST
    @Path("/getTreeViewDataList")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<AssetTreeATreeView> getTreeViewDataList(HashMap<String,Object> paraMap);

    @POST
    @Path("/getNettingGroup")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<AssetsTree_A> getNettingGroup();

    @POST
    @Path("/updateAssOrder")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String updateAssOrder(List<String> pojoList);

}