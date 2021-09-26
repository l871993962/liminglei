package com.yss.fast.systemmanager.port;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.support.cache.PortCache;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.cache.assist.DefaultCacheRefresh;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.Port;
import com.yss.framework.api.context.YssContextFactory;
import com.yss.framework.api.dataservice.IDataService;
import com.yss.framework.api.service.HttpServiceFactory;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.platform.support.dataservice.service.IPortSTDataService;

/**
 * 
 * @author huyingzhao STORY #59765 【南方基金】由于部分基金全称名字太长，需要将系统界面里展示全称的界面改成产品简称
 */
@DefaultCacheRefresh(group = CacheGroup.PORT)
public class PortSTDataService implements IPortSTDataService {

	private PortCache portCache =  null;
	@Override
	public <K extends BasePojo> K getPojoByCode(String pojoCode)
			throws ServiceException {
		return null;
	}

	@Override
	public <K extends BasePojo> List<K> getDataListByTypes(String[] types)
			throws ServiceException {
		return null;
	}

	@Override
	public QueryRes getQueryResByTypes(String[] types) throws ServiceException {
		return null;
	}

	@Override
	public <K extends BasePojo> List<K> getDataListByKeys(String[] keys)
			throws ServiceException {
		return null;
	}

	@Override
	public QueryRes getQueryResByKeys(String[] keys) throws ServiceException {
		return null;
	}

	@Override
	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		return null;
	}

	@Override
	public QueryRes getDataListRes() throws ServiceException {
		return null;
	}

	@Override
	public <K extends BasePojo> K getDataByCode(String dataCode)
			throws ServiceException {
		return null;
	}

	@Override
	public String getMenuId() {
		return null;
	}

	@Override
	public void setMenuId(String menuId) {
	}

	/**
	 * BUG #310253 净值确认管理查询慢+锁账/反锁账操作页面刷新不及时
	 */
	@Override
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		HashMap<String, String> portMap = new HashMap<String, String>();
		if(null == portCache){
			portCache = CacheManager.getInstance().getCache(CacheGroup.PORT);
		}
		
		List<Port> ports = portCache.getCacheList();
		for(Port port : ports){
			portMap.put(port.getC_PORT_CODE(), port.getC_PORT_NAME_ST());
		}
		return portMap;
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		HashMap<String, String> c_PORT_NAME_STMap = new HashMap<String, String>();
		if(null == portCache){
			portCache = CacheManager.getInstance().getCache(CacheGroup.PORT);
	}
		for (String portStr : listKey){			
			if(null!=portCache.getCacheByKey(portStr)) {
				c_PORT_NAME_STMap.put(portStr, portCache.getCacheByKey(portStr).getC_PORT_NAME_ST());
			}
		}
		return c_PORT_NAME_STMap;
	}
}
