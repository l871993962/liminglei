package com.yss.ams.base.information.support.bi.mkt.service;


import com.yss.framework.api.dataservice.IKeyConvertDataService;
import com.yss.framework.api.restful.annotations.RestfulSupported;

/**
 * 市场字典数据服务接口，主要进行跨应用数据获取<br>
 * 用于市场代码中文转换<br>
 */
@RestfulSupported
public interface IMktVarDataService extends IKeyConvertDataService {
	/**
	 * 重新加载交易市场缓存
	 */
	public void reloadCache();

}
