package com.yss.ams.base.information.support.sys.dccury.service;

import java.util.List;

import com.yss.ams.base.information.support.sys.dccury.pojo.DcCury;
import com.yss.framework.api.cache.IDataServiceForCache;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.dataservice.GenericPojo;
import com.yss.framework.api.dataservice.IControlDataService;
import com.yss.framework.api.dataservice.IKeyConvertDataService;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;

/**
 * 国际货币设置数据服务接口，主要进行跨应用数据获取
 * @author 马向峰  拆分  2017.0527
 *
 */
@RestfulSupported
@GenericPojo(pojo = DcCury.class)
public interface IDCDataService extends IControlDataService, IKeyConvertDataService, IDataServiceForCache {

	public <T extends BasePojo> List<T> getPortCurruncyList() throws Exception;

	public QueryRes getPortCurruncyListRes() throws Exception;

	/**
	 * Fixed by huangsq 20160729 BUG #135169 算法公式重新导入后不启作用<br />
	 * 重新加载缓存
	 */
	public void reloadCache();
	  
}
