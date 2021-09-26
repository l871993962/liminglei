package com.yss.ams.product.information.modules.ab.port.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.modules.ab.port.admin.PortAdmin;
import com.yss.ams.product.information.modules.ab.port.dao.PortSqlBuilder;
import com.yss.ams.product.information.support.modules.ab.port.service.IPortBDataService;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.cache.assist.DefaultCacheRefresh;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.db.DbPoolFactory;

/**
 * <产品基本信息>B区组合数据服务类
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
@DefaultCacheRefresh(group = CacheGroup.PORT)
public class PortBDataService implements IPortBDataService {

	private PortAdmin portAdmin = null;
	private final String _defaultMenuId = "pd_portfolio";

	public PortBDataService() {
		portAdmin = new PortAdmin(DbPoolFactory.getInstance().getPool(),
				new PortSqlBuilder());
	}

	@Override
	public <K extends BasePojo> List<K> getDataListByKeys(String[] arg0)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <K extends BasePojo> List<K> getDataListByTypes(String[] arg0)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <K extends BasePojo> K getPojoByCode(String arg0)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryRes getQueryResByKeys(String[] arg0) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryRes getQueryResByTypes(String[] arg0) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <K extends BasePojo> K getDataByCode(String arg0)
			throws ServiceException {
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
	public String getMenuId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMenuId(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		try {
			return portAdmin.getKeyConvertMap2();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> arg0)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
