package com.yss.ams.base.information.support.bi.ie.service;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.bi.ie.pojo.Ie;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.dataservice.GenericPojo;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.service.ServiceException;

@RestfulSupported
@GenericPojo(pojo=Ie.class)
public interface IIeCacheDataService extends IIeDataService {

	/**
	 * 根据收支项目从缓存获取收支项目信息
	 * @param ietCode 收支项目代码
	 * @return
	 */
	Ie getCacheByIeCode(String ietCode);
	
	/**
	 * 获取收支项目中所有的收支代码和收支名称
	 * @return
	 */
	HashMap<String, String> getKeyConvertMap() throws ServiceException;
	
	public <K extends BasePojo> List<Ie> getDataListByFeeCodes(String[] keys);

	public Ie getCacheByKey(String key);
	
	public List<Ie> getCacheList();

}
