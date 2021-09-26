package com.yss.ifa.szt.tool.para.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.context.Context;
import com.yss.framework.api.context.YssContextFactory;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.ifa.szt.tool.activator.SztActivator;
import com.yss.ifa.szt.tool.para.admin.ErVocAdmin;
import com.yss.ifa.szt.tool.para.service.IErVocDataService;

public class ErVocDataService implements IErVocDataService{
	
	private String menuId = "base_ervoc";
	private ErVocAdmin erVocAdmin = null;
	public ErVocDataService() {
		erVocAdmin = new ErVocAdmin();
	}

	@Override
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		try {
			return erVocAdmin.getKeyConvertMap();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		try {
			return erVocAdmin.getKeyConvertMap(listKey);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		try {
			return erVocAdmin.getAllData();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public QueryRes getDataListRes() throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> dataList = null;
		try {
			dataList = erVocAdmin.getAllData();
			res.setDataList(dataList);
			this.menuId = "base_ervoc";
			Context context = YssContextFactory.getInstance().getAppContext(SztActivator.class);
			res.setHeadKeyList(context.getListHeadMap(menuId).getHeadKeyList());
			res.setMenuId(menuId);
		} catch (Exception ex) {
			throw new ServiceException(ex);
		}

		return res;
	}

	@Override
	public <K extends BasePojo> K getDataByCode(String dataCode)
			throws ServiceException {
		try {
			return erVocAdmin.getDataByCode(dataCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public String getMenuId() {
		// TODO Auto-generated method stub
		return this.menuId;
	}

	@Override
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	@Override
	public <K extends BasePojo> K getPojoByCode(String pojoCode)
			throws ServiceException {
		try {
			return erVocAdmin.getDataByCode(pojoCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public <K extends BasePojo> List<K> getDataListByTypes(String[] types)
			throws ServiceException {
		try {
			return erVocAdmin.getDataByTypes(types);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public QueryRes getQueryResByTypes(String[] types) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> dataList = null;
		try {
			dataList = erVocAdmin.getDataByTypes(types);
			res.setDataList(dataList);
			this.menuId = "base_ervoc";
			Context context = YssContextFactory.getInstance().getAppContext(SztActivator.class);
			res.setHeadKeyList(context.getListHeadMap(menuId).getHeadKeyList());
			res.setMenuId(menuId);
		} catch (Exception ex) {
			throw new ServiceException(ex);
		}

		return res;
	}

	@Override
	public <K extends BasePojo> List<K> getDataListByKeys(String[] keys)
			throws ServiceException {
		try {
			return erVocAdmin.getDataByKeys(keys);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public QueryRes getQueryResByKeys(String[] keys) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> dataList = null;
		try {
			dataList = erVocAdmin.getDataByKeys(keys);
			res.setDataList(dataList);
			this.menuId = "base_ervoc";
			Context context = YssContextFactory.getInstance().getAppContext(SztActivator.class);
			res.setHeadKeyList(context.getListHeadMap(menuId).getHeadKeyList());
			res.setMenuId(menuId);
		} catch (Exception ex) {
			throw new ServiceException(ex);
		}

		return res;
	}

}
