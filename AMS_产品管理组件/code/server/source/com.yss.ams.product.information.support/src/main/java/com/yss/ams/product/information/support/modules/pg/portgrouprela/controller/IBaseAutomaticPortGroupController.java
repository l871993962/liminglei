package com.yss.ams.product.information.support.modules.pg.portgrouprela.controller;

import javax.ws.rs.Path;

import com.yss.common.YssServiceIdConstant;
import com.yss.fast.task.support.automatic.service.IAutomaticPortGroupService;
import com.yss.fast.task.support.automatic.service.controller.IAutomaticPortGroupController;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;

@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_PRODUCTINFO,
impl = "com.yss.ams.product.information.modules.pg.portgrouprela.service.impl.AutomaticPortGroupServiceImpl",
interfaceClass = com.yss.fast.task.support.automatic.service.IAutomaticPortGroupService.class,
productLine = ProductLine.PRODUCTINFO, serviceMapId = "IAutomaticPortGroupService", menuId = "pd_portGroupRela")
public interface IBaseAutomaticPortGroupController extends IAutomaticPortGroupController<IAutomaticPortGroupService>{

}
