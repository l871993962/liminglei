package com.yss.ams.base.information.modules.bi.region.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.modules.bi.region.admin.AreaDataAdmin;
import com.yss.ams.base.information.modules.bi.region.dao.AreaSqlBuilder;
import com.yss.ams.base.information.support.bi.region.service.IAreaDataService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.db.DbPoolFactory;

/**
 * 地区信息数据服务类
 * @author 马向峰 拆分
 *@Date 20170601
 */
public class AreaDataService implements IAreaDataService{
	private AreaDataAdmin areaAdmin = null;
	
	public AreaDataService() {
		areaAdmin = new AreaDataAdmin(DbPoolFactory.getInstance().getPool(),
				new AreaSqlBuilder());
	}
	
	/**
	 * 获取所有的地区信息
	 * @return 地区信息集合
	 * @throws ServiceException
	 */
	public <K extends BasePojo> List<K> getAllAreas() throws ServiceException {
		try {
			return areaAdmin.getAllAreas();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * 获取所有地区最上层所属地区
	 * @return List<Area>
	 */
	public <K extends BasePojo> List<K> getAllTopAreas() throws ServiceException {
		try {
			return areaAdmin.getAllTopAreas();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * 获取所有地区非最上层所属地区
	 * @return List<Area>
	 */
	public <K extends BasePojo> List<K> getAllNotTopAreas() throws ServiceException {
		try {
			return areaAdmin.getAllNotTopAreas();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public <K extends BasePojo> K getDataByCode(String arg0)
			throws ServiceException {
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
	public String getMenuId() {
		return null;
	}

	@Override
	public void setMenuId(String arg0) {
		
	}

	@Override
	public <K extends BasePojo> List<K> getDataListByKeys(String[] arg0)
			throws ServiceException {
		return null;
	}

	@Override
	public <K extends BasePojo> List<K> getDataListByTypes(String[] arg0)
			throws ServiceException {
		return null;
	}

	@Override
	public QueryRes getQueryResByKeys(String[] arg0) throws ServiceException {
		return null;
	}

	@Override
	public QueryRes getQueryResByTypes(String[] arg0) throws ServiceException {
		return null;
	}

	/**
	 * 将所有地区信息的编号和名称转化为map
	 * HashMap<编号, 名称>
	 */
	@Override
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		return areaAdmin.getKeyConvertMap();
	}

	@Override
	public <K extends BasePojo> K getPojoByCode(String pojoCode)
			throws ServiceException {
		try {
			return areaAdmin.getDataByCode(pojoCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		try {
			return areaAdmin.getKeyConvertMap(listKey);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	
	
}
