package com.yss.ams.product.information.support.modules.ab.assetsTree_a.service;

import com.yss.framework.api.dataservice.IControlDataService;
import com.yss.framework.api.dataservice.IDataService;
import com.yss.framework.api.dataservice.IKeyConvertDataService;
import com.yss.framework.api.restful.annotations.RestfulSupported;

/**
 * <A区资产树型结构>数据服务接口，主要进行跨应用数据获取
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
@RestfulSupported
public interface IAssetTrees_ADataService extends IDataService,
		IControlDataService, IKeyConvertDataService {
}
