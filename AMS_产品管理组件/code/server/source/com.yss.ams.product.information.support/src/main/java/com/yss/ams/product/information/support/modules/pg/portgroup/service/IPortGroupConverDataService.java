package com.yss.ams.product.information.support.modules.pg.portgroup.service;

import com.yss.framework.api.cache.IDataServiceForCache;
import com.yss.framework.api.dataservice.IControlDataService;
import com.yss.framework.api.dataservice.IKeyConvertDataService;
import com.yss.framework.api.dataservice.ITreeViewDataService;
import com.yss.framework.api.restful.annotations.RestfulSupported;

/**
 * @author Jinghehe
 * 群组和组合代码转名称使用的服务类 
 * 涉及组合和群组参数的界面要进行代码转名称
 * 
 */
/**
 * <群组管理>群组和组合代码转名称使用的服务接口
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
@RestfulSupported
public interface IPortGroupConverDataService extends IControlDataService,
		IKeyConvertDataService, ITreeViewDataService, IDataServiceForCache {

}
