package com.yss.ams.base.information.support.sys.secvar.service;

import java.util.HashMap;

import com.yss.ams.base.information.support.sys.secvar.pojo.SecVar;
import com.yss.framework.api.cache.IDataServiceForCache;
import com.yss.framework.api.dataservice.GenericPojo;
import com.yss.framework.api.dataservice.IControlDataService;
import com.yss.framework.api.dataservice.IDataService;
import com.yss.framework.api.dataservice.IKeyConvertDataService;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.service.ServiceException;
//import dataservice.comm.pojo.SecVar;

/**
 * 证券品种字典数据服务接口，主要进行跨应用数据获取
 * 字典表：T_S_DA_SEC_VAR
 *
 */
@RestfulSupported
@GenericPojo(pojo=SecVar.class)
public interface ISecVarDataService extends IDataService,
		IControlDataService,IKeyConvertDataService,IDataServiceForCache {
	/**
	 * 取得所有数据(只包含代码和名称)
	 * @author liuxiang
	 * @date 2014-6-9
	 * @return
	 */
	public HashMap<String, String> getShortDataMap() throws ServiceException;
	
	/**
	 * Fixed by huangsq 20160729 BUG #135169 算法公式重新导入后不启作用<br />
	 * 重新加载缓存
	 */
	public void reloadCache();

	
}
