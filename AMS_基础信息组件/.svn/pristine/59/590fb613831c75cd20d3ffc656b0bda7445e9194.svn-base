package com.yss.ams.base.information.support.sys.dvaitem.service;

import java.util.List;

import com.yss.ams.base.information.support.sys.dvaitem.pojo.DvaItem;
import com.yss.framework.api.cache.CacheRefreshInfo;
import com.yss.framework.api.cache.IDataServiceForCache;
import com.yss.framework.api.dataservice.GenericPojo;
import com.yss.framework.api.dataservice.IControlDataService;
import com.yss.framework.api.dataservice.IKeyConvertDataService;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.service.ServiceException;

//import dataservice.comm.pojo.DvaItem;

/**
 * 核算业务项字典数据服务接口，主要进行跨应用数据获取
 * 字典表：T_S_DVA_ITEM
 */
@RestfulSupported
@GenericPojo(pojo = DvaItem.class)
public interface IDvaItemDataService extends IControlDataService,IKeyConvertDataService,IDataServiceForCache {

	/**
	 * 根据需要执行的缓存操作更新缓存
	 * add by qkw 2017/5/24
	 */
	public void doUpdate(CacheRefreshInfo cacheRefreshInfo) throws ServiceException;

	/**
	 *  获取核算业务项字典T_S_DVA_ITEM的所有数据树形结构
	 * @return
	 */
	public List<DvaItem> getTreeViewForDayfItems() throws ServiceException;
}
