package com.yss.ams.base.information.support.bi.region.service;

import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.dataservice.IControlDataService;
import com.yss.framework.api.dataservice.IDataService;
import com.yss.framework.api.dataservice.IKeyConvertDataService;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.service.ServiceException;

/**
 * 地区信息设置数据服务接口，主要进行跨应用数据获取
 * @author 马向峰 拆分
 *
 */
@RestfulSupported
public interface IAreaDataService extends IDataService,IControlDataService,IKeyConvertDataService{

	/**
	 * 获取所有的地区信息
	 * @return 地区信息集合
	 * @throws ServiceException
	 */
	public <K extends BasePojo> List<K> getAllAreas();
	
	/**
	 * 获取所有地区非最上层所属地区
	 * added by HeLiang 2017-10-27 STORY #46800 Web_基金经理管理
	 * 
	 * @return 地区信息集合
	 * @throws ServiceException
	 */
	public <K extends BasePojo> List<K> getAllNotTopAreas();
}
