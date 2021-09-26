package com.yss.ams.base.information.support.sys.ieItem.service;

import java.util.List;

import com.yss.ams.base.information.support.sys.ieItem.pojo.IeItem;
import com.yss.framework.api.cache.IDataServiceForCache;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.dataservice.GenericPojo;
import com.yss.framework.api.dataservice.IControlDataService;
import com.yss.framework.api.dataservice.IKeyConvertDataService;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.service.ServiceException;

//import dataservice.comm.pojo.IeItem;

/**
 * 收支项目字典数据服务接口，主要进行跨应用数据获取
 * 字典表：T_S_IE_ITEM
 *
 */
@RestfulSupported
@GenericPojo(pojo = IeItem.class)
public interface IIeItemDataService extends IControlDataService,IKeyConvertDataService,IDataServiceForCache {

	/**
	 * 根据收支项目类型获取收支项目
	 * @param types 收支项目类型
	 * @return
	 * @throws ServiceException
	 * @author liuxiang 2015-8-14 STORY #24240 太平资产收支结转功能优化
	 */
	public <K extends BasePojo> List<K> getDataListByIeTypes(String[] types)
			throws ServiceException;
}
