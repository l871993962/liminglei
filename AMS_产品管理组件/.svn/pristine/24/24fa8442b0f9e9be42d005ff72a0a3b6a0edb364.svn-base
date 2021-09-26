package com.yss.ams.product.information.modules.pg.portgroup.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.activator.ProductInfoActivator;
import com.yss.ams.product.information.modules.pg.portgroup.admin.PortGroupAdmin;
import com.yss.ams.product.information.modules.pg.portgroup.dao.PortGroupSqlBuilder;
import com.yss.ams.product.information.support.cache.PortCache;
import com.yss.ams.product.information.support.modules.pg.portgroup.service.IPortGroupConverDataService;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;

/**
 * @author Jinghehe
 * 群组和组合代码转名称使用的服务类 
 * 涉及组合和群组参数的界面要进行代码转名称
 * 仅仅供含有组合和群组的List界面
 */
/**
 * <群组管理>群组和组合代码转名称使用的服务类
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
public class PortGroupConverDataService implements IPortGroupConverDataService {

	private PortGroupAdmin portGroupAdmin = null ;
	private PortCache portCache = null;
	public PortGroupConverDataService() {
		portGroupAdmin = new PortGroupAdmin(YssDbPoolFactory.getInstance().getDbPool(ProductInfoActivator.class), new PortGroupSqlBuilder());
	}
	
	@Override
	public <K extends BasePojo> K getPojoByCode(String pojoCode)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <K extends BasePojo> List<K> getDataListByTypes(String[] types)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryRes getQueryResByTypes(String[] types) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <K extends BasePojo> List<K> getDataListByKeys(String[] keys)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryRes getQueryResByKeys(String[] keys) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryRes getDataListRes() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <K extends BasePojo> K getDataByCode(String dataCode)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMenuId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMenuId(String menuId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		HashMap<String, String> codeToNameMap = new HashMap<String, String>();
		try {
			//// 先把组合全部放进map中去
			portCache = CacheManager.getInstance().getCache(CacheGroup.PORT);
			HashMap<String, String> portMap = portCache.getKeyConvertMap();
			if(null != portMap && !portMap.isEmpty())
			{
				codeToNameMap.putAll(portMap);
			}
			//// 在查询群组的代码转换成名称map
			HashMap<String, String> groupMap = portGroupAdmin.getKeyConvertMap();
			if(null != groupMap && !groupMap.isEmpty())
			{
				codeToNameMap.putAll(groupMap);
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
		return codeToNameMap;
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <K extends BasePojo> List<K> getTreeViewList() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryRes getTreeViewListRes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CacheData updateByTimestamp(String timestamp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends BasePojo> List<T> queryByIds(String ids) {
		// TODO Auto-generated method stub
		return null;
	}

}
