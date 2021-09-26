package com.yss.ams.base.information.support.sys.dai.service;

import java.util.List;

import com.yss.ams.base.information.support.sys.dai.pojo.Dai;
import com.yss.framework.api.cache.IDataServiceForCache;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.dataservice.GenericPojo;
import com.yss.framework.api.dataservice.IControlDataService;
import com.yss.framework.api.dataservice.IKeyConvertDataService;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.service.ServiceException;

//import dataservice.comm.pojo.Dai;

/**
 * 核算项目字典数据服务接口，主要进行跨应用数据获取
 *
 */
@RestfulSupported
@GenericPojo(pojo = Dai.class)
public interface IAccProDataService extends IControlDataService,IKeyConvertDataService,IDataServiceForCache {

	/**
	 * 根据科目类别获取核算项目
	 * @param kmCls　科目类型
	 * @return 项目列表
	 * @throws ServiceException
	 */
	public List<BasePojo> getAccProDataByKmCls(String[] kmClss) throws ServiceException;
}
