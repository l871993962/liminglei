package com.yss.ams.product.information.support.modules.ab.port.service;

import com.yss.framework.api.common.co.Port;
import com.yss.framework.api.dataservice.GenericPojo;
import com.yss.framework.api.dataservice.IControlDataService;
import com.yss.framework.api.dataservice.IKeyConvertDataService;
import com.yss.framework.api.restful.annotations.RestfulSupported;

/**
 * B区 组合代码转资产代码使用
 * 
 * @author weijj
 * 
 */
/**
 * <产品基本信息>B区组合数据服务接口，组合代码转资产代码使用
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
@RestfulSupported
@GenericPojo(pojo = Port.class)
public interface IPortBDataService extends IControlDataService,
		IKeyConvertDataService {

}
