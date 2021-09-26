package com.yss.ams.product.information.support.modules.ab.assetsTree_a_rule.controller;

import javax.ws.rs.Path;

import com.yss.ams.product.information.support.modules.ab.assetsTree_a_rule.pojo.AssetsTree_A_Rule;
import com.yss.ams.product.information.support.modules.ab.assetsTree_a_rule.service.IAssetsTree_A_RuleService;
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
impl = "com.yss.ams.product.information.modules.ab.assetsTree_a_rule.service.impl.AssetsTree_A_RuleService",
interfaceClass = com.yss.ams.product.information.support.modules.ab.assetsTree_a_rule.service.IAssetsTree_A_RuleService.class,
productLine = ProductLine.PRODUCTINFO, serviceMapId = "IAssetsTree_A_RuleService", menuId = "assetsTree_A_rule")
public interface IAssetsTree_A_RuleController extends IBaseServiceBusController<AssetsTree_A_Rule,IAssetsTree_A_RuleService> {


}