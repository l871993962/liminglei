package com.yss.ams.product.information.support.modules.ab.port.cache.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.cache.CacheRefreshInfo;
import com.yss.framework.api.common.co.Port;
import com.yss.ams.product.information.support.modules.ab.port.pojo.PortCacheData;
import com.yss.ams.product.information.support.modules.ab.port.service.IPortDataService;
import com.yss.ams.product.information.support.modules.ab.port.vo.GetCacheByPortAndBuildDateVo;
import com.yss.ams.product.information.support.modules.ab.port.vo.GetListByCodeAndBuildDateVo;
import com.yss.framework.api.dataservice.GenericPojo;
import com.yss.framework.api.restful.annotations.LinkControllerMethod;
import com.yss.framework.api.restful.annotations.RestfulSupported;


/**
 * 组合缓存查询服务，从缓存中查询
 * @author leeyu
 *
 */
/**
 * <产品基本信息>组合缓存数据服务类
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
@RestfulSupported
@GenericPojo(pojo = Port.class)
public interface IPortCacheDataService extends IPortDataService {
	/**
	 * 获取产品资产树MAP缓存
	 * @param cacheKey 资产树根节点（默认资产树为：NULL_TRCODE）
	 * @return  HashMap<组合代码, 组合>
	 */
	public PortCacheData getCacheTrCodeDataMapByKey(String cacheKey);
	
	/**
	 * 获取已缓存的产品资产树Key
	 * @return
	 */
	public List<String> getCacheTrCodeDataKey();
	
	public Port getCacheByKey(String key);
	
	public HashMap<String,HashMap<String, Port>> getPortCacheMap_ZCLX();
	
	@LinkControllerMethod(value="getCacheByPortAndBuildDate",arguTypes = GetCacheByPortAndBuildDateVo.class)
	public Port getCacheByPortAndBuildDate(String portCode, Date buildDate);
	
	public List<Port> getCacheList();
	
	public HashMap<String,Port> getPortByRight(List<String> rights);
	
	public void update(CacheRefreshInfo info);
	
	public void refreshAssetTreeByTrCode(String trCode);
	
	public void reloadData();
}
