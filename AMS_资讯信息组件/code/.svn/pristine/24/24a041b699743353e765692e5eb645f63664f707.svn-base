package com.yss.ams.sec.information.support.modules.sv.base.service;

import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.dataservice.IKeyConvertDataService;
import com.yss.framework.api.restful.annotations.RestfulSupported;

/**
 * 实现基金类证券信息的代码转换
 * @author leeyu
 *
 */
@RestfulSupported
public interface ISecBaseJJDataService extends IKeyConvertDataService{

	/**解决 BUG #127360::YSS-版本自动打包报错
	 * 获取货币基金日历表中的基金品种信息
	 * @param paraMap
	 * @return
	 */
	List<BasePojo> getSecCache(HashMap<String, String> paraMap) throws Exception;
}
