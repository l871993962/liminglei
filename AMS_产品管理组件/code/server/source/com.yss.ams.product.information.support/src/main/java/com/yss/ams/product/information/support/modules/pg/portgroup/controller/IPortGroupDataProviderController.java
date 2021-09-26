package com.yss.ams.product.information.support.modules.pg.portgroup.controller;

import javax.ws.rs.Path;

import com.yss.ams.product.information.support.modules.pg.portgroup.service.IPortGroupDataProvider;
import com.yss.common.YssServiceIdConstant;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseDataRightProviderController;
import com.yss.framework.api.restful.base.IBaseServiceBusController;
import com.yss.right.pojo.DataRight;


@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_PRODUCTINFO,
impl = "com.yss.ams.product.information.modules.pg.portgroup.service.impl.PortGroupConverDataService",
interfaceClass = com.yss.ams.product.information.support.modules.pg.portgroup.service.IPortGroupDataProvider.class,
productLine = ProductLine.PRODUCTINFO, serviceMapId = "IPortGroupDataProvider", menuId = "IPortGroupDataProvider")
public interface IPortGroupDataProviderController extends IBaseDataRightProviderController<IPortGroupDataProvider>,IBaseServiceBusController<DataRight,IPortGroupDataProvider>{
	
	
}
