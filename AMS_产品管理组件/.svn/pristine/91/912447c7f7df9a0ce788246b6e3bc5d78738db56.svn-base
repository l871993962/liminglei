package com.yss.ams.product.information.support.modules.aa.portcustom.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.yss.framework.api.dataservice.IDataService;
import com.yss.framework.api.dataservice.IKeyConvertDataService;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.service.ServiceException;

/**
 * <常用产品设置>数据服务接口，主要进行跨应用数据获取
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
@RestfulSupported
public interface IPortCustomDataService extends IDataService,
		IKeyConvertDataService {
	/**
	 * 根据用户代码和功能代码获取显示类型
	 * 
	 * @param codeMap 条件
	 * @return String
	 * @throws ServiceException
	 */
	public String getShowType(HashMap<String, String> codeMap)
			throws ServiceException;
	public ArrayList<String> getAssetTypeOnlyCode() throws ServiceException;
}
