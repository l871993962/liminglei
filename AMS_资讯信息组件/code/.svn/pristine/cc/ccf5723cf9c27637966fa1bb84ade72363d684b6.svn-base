package com.yss.ams.sec.information.support.modules.plateset.platesub.service;

import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;

/**
 * STORY #33682 彭博证券信息接口_重新设计
 * xiaozhilong 20161122
 */
@RestfulSupported
public interface IPlateSubService extends IServiceBus {
	/**
	 * 彭博证券信息清算时更新数据需要重写方法，避免基类中的日趋连续性逻辑
	 * 保证数据更新正确
	 * STORY #33682 彭博证券信息接口_重新设计
	 * xiaozhilong 20161122
	 * @param pojoList
	 * @return
	 */
	public String updatePlateById(List<BasePojo> pojoList);

}
