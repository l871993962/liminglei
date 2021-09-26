package com.yss.ams.base.information.support.bi.ieLink.service;



import java.util.HashMap;

import com.yss.framework.api.dataservice.IControlDataService;
import com.yss.framework.api.dataservice.IKeyConvertDataService;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.service.ServiceException;

/**
 * 收支费用数据服务接口类，主要进行跨应用数据获取
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
@RestfulSupported
public interface IFeeDataService extends IControlDataService, IKeyConvertDataService{
     

	/**
	 * 从缓存中取得所有收支费用数据(只包含代码和名称)
	 * @author liuxiang
	 * @date 2014-6-10
	 * @return
	 */
    public HashMap<String, String> getShortDataMap() throws ServiceException;
    
    /**
	 * 获取收支项目设置中所有收支代码和收支名称的集合map
	 * @return
	 */
    HashMap<String, String> getKeyConvertMap() throws ServiceException;
}
