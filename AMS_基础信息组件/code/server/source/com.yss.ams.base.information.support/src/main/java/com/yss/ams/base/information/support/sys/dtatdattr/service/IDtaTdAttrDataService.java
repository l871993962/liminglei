package com.yss.ams.base.information.support.sys.dtatdattr.service;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.sys.dtatdattr.pojo.DtatdAttr;
import com.yss.framework.api.cache.IDataServiceForCache;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.dataservice.GenericPojo;
import com.yss.framework.api.dataservice.IControlDataService;
import com.yss.framework.api.dataservice.IKeyConvertDataService;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.service.ServiceException;

//import dataservice.comm.pojo.DtatdAttr;

/**
 * 交易属性字典数据服务接口，主要进行跨应用数据获取
 * 字典表：T_S_DTA_TD_ATTR
 */
@RestfulSupported
@GenericPojo(pojo = DtatdAttr.class)
public interface IDtaTdAttrDataService extends IControlDataService,IKeyConvertDataService,IDataServiceForCache{
	/**
	 * 取得所有数据(只包含代码和名称)
	 * @author liuxiang
	 * @date 2014-6-9
	 * @return
	 */
	public HashMap<String, String> getShortDataMap() throws ServiceException;
	
	/**
	 * 通过交易属性代码获取交易属性实体   STORY39265商品期权业务
	 * @param codes
	 * @return 数据集合
	 * @throws ServiceException
	 * @author xuyuanhao
	 * @date 2017-3-30
	 * @state add
	 */
	public <K extends BasePojo> List<K> getDataListByCodes(String[] codes)
			throws ServiceException;
}
