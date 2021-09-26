package com.yss.ams.product.information.support.modules.ab.port.right.controller;

import javax.ws.rs.Path;

import com.yss.ams.product.information.support.modules.ab.port.right.service.IRightDataRightProviderZH;
import com.yss.ams.product.information.support.modules.pg.portgroup.service.IPortGroupDataProvider;
import com.yss.common.YssServiceIdConstant;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseDataRightProviderController;
import com.yss.framework.api.restful.base.IBaseServiceBusController;
import com.yss.right.pojo.DataRight;

/**
*
* @author neil
* @date 2020-09-14 11:34:49
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_PRODUCTINFO,
impl = "com.yss.ams.product.information.modules.ab.port.right.service.impl.RightDataRightProviderZH",
interfaceClass = com.yss.ams.product.information.support.modules.ab.port.right.service.IRightDataRightProviderZH.class,
productLine = ProductLine.PRODUCTINFO, serviceMapId = "IRightDataRightProviderZH", menuId = "IRightDataRightProviderZH")
public interface IRightDataRightProviderZHController extends IBaseDataRightProviderController<IRightDataRightProviderZH>,IBaseServiceBusController<DataRight,IRightDataRightProviderZH> {


}