package com.yss.ams.base.information.support.bi.salesnet.service;


import java.util.HashMap;

import com.yss.ams.base.information.support.bi.salesnet.pojo.SalesNet;
import com.yss.framework.api.dataservice.IControlDataService;
import com.yss.framework.api.dataservice.IDataService;
import com.yss.framework.api.dataservice.IKeyConvertDataService;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.service.ServiceException;

/**
 * 销售网点设置数据服务接口类，主要进行跨应用数据获取
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
@RestfulSupported
public interface INetDataService extends IDataService,
		IControlDataService,IKeyConvertDataService{
	/**
	 * 取得所有销售网点设置数据（只包含代码和名称）
	 * @author liuxiang
	 * @date 2014-6-9
	 * @return
	 */
	public HashMap<String, String> getShortDataMap() throws ServiceException;
	
	/**
	 * 此方法由产品生命周期项目添加<br>
	 * 通过网点代码获取网点信息
	 * @param code
	 * @return
	 * @throws ServiceException
	 */
	public SalesNet getSalesNetByCode(String code) throws ServiceException;
	
	/**
	 * 此方法由产品生命周期项目添加<br>
	 * 获取所有网点的代码和名称
	 * @param code
	 * @return
	 * @throws ServiceException
	 */
	public HashMap<String, String> getAllNetCodeAndName() throws ServiceException;
	
	/**
	 * 根据销售商代码查找网点信息
	 * @param vendorCode
	 * @return
	 */
	public SalesNet getSalesNetByVendorCode(String vendorCode);
}
