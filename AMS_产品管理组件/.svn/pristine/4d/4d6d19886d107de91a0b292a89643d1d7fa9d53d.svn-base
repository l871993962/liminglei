package com.yss.ams.product.information.support.modules.pg.portassgroup.controller;

import javax.ws.rs.Path;

import com.yss.ams.product.information.support.modules.pg.portassgroup.service.IPortAssGroupDataProvider;
import com.yss.common.YssServiceIdConstant;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseDataRightProviderController;
import com.yss.framework.api.restful.base.IBaseServiceBusController;
import com.yss.right.pojo.DataRight;

/**
 * <群组管理>数据服务接口，主要进行群组数据权限的数据获取
 * STORY #66347 【招商基金】系统实现自动授权及权限优化需求 
 * 2019-07-25
 * @author neil
 *
 */
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_PRODUCTINFO,
impl = "com.yss.ams.product.information.modules.pg.portassgroup.service.impl.PortAssGroupDataProvider",
interfaceClass = com.yss.ams.product.information.support.modules.pg.portassgroup.service.IPortAssGroupDataProvider.class,
productLine = ProductLine.PRODUCTINFO, serviceMapId = "IPortAssGroupDataProvider", menuId = "IPortAssGroupDataProvider")
public interface IPortAssGroupDataProviderController extends IBaseDataRightProviderController<IPortAssGroupDataProvider>,IBaseServiceBusController<DataRight,IPortAssGroupDataProvider>{
	
	
}
