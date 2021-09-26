package com.yss.ams.base.information.modules.bi.account.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.modules.bi.account.admin.AreaCityDataMapAdmin;
import com.yss.ams.base.information.modules.bi.account.dao.AreaCitySqlBuilder;
import com.yss.ams.base.information.support.bi.account.service.IAreaCityDataMapService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.db.DbPoolFactory;

/**
 * 
 * @ClassName AreaCityDataMapService
 * @Description 省份城市转换服务类
 * @author hehonghui@ysstech.com
 * @CreateDate 2017年11月29日下午7:04:18
 * @Version V4.5.0.1 
 * @Copyright (c) 2016, 深圳赢时胜 All Rights Reserved.
 */
public class AreaCityDataMapService implements IAreaCityDataMapService {

	public AreaCityDataMapAdmin  areaCityAdmin = null;
	
	public AreaCityDataMapService() {
		areaCityAdmin = new AreaCityDataMapAdmin(DbPoolFactory.getInstance()
				.getPool(), new AreaCitySqlBuilder());
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
	
	private String menuId = "";

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	@Override
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		try {
			return areaCityAdmin.getKeyConvertMap();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		try {
			return areaCityAdmin.getKeyConvertMap(listKey);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

}
