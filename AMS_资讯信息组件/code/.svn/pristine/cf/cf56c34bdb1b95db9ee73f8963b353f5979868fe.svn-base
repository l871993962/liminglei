package com.yss.ams.sec.information.support.modules.plateset.plate.service;

import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.dataservice.IControlDataService;
import com.yss.framework.api.dataservice.IKeyConvertDataService;
import com.yss.framework.api.restful.annotations.RestfulSupported;

/**
 * 版块信息数据服务
 * @author shepherd
 */
@RestfulSupported
public interface IPlateDataService extends IControlDataService,
		IKeyConvertDataService {

	/**
	 * 彭博证券信息清算时更新数据需要重写方法，避免基类中的日趋连续性逻辑
	 * 保证数据更新正确
	 * STORY #33682 彭博证券信息接口_重新设计
	 * xiaozhilong 20161122
	 * @param pojoList
	 * @return
	 */
	/**
	 * 马向峰拆分  #41193 基础信息设置组件化拆分
	 * @param pojoList
	 * @return
	 */
	public String updatePlateById(List<BasePojo> pojoList);
	
	public void insert(List<BasePojo> list);

}
