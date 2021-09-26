package com.yss.ams.base.information.modules.sys.accele.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.activator.InformationActivator;
import com.yss.ams.base.information.modules.sys.accele.admin.DaeElemDataAdmin;
import com.yss.ams.base.information.modules.sys.accele.dao.AccEleSqlBuilder;
import com.yss.ams.base.information.support.sys.accele.pojo.AccEle;
import com.yss.ams.base.information.support.sys.accele.service.IDaeElemDataService;
//import com.yss.bundle.activator.UcoActivator;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.mvc.biz.BusinessException;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.db.DbPoolFactory;
import com.yss.mvc.service.ServiceAssistance;
//import com.yss.para.bi.accele.admin.DaeElemDataAdmin;
//import com.yss.para.bi.accele.dao.AccEleSqlBuilder;

//import dataservice.comm.pojo.AccEle;
//import dataservice.service.IDaeElemDataService;

public class DaeElemDataService implements IDaeElemDataService {
	private DaeElemDataAdmin daeElemAdmin = null;

	public DaeElemDataService() {
		daeElemAdmin = new DaeElemDataAdmin(DbPoolFactory.getInstance()
				.getPool(), new AccEleSqlBuilder());
	}

	@SuppressWarnings("unchecked")
	public List<AccEle> getDataList() throws ServiceException {
		try {
			return daeElemAdmin.getAllDataList();
		} catch (BusinessException e) {
			throw new ServiceException(e);
		}
	}

	public QueryRes getDataListRes() throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList = null;
		try {
			pojoList = daeElemAdmin.getAllDataList();
			res.setDataList(pojoList);
			res.setMenuId("base_accelequy");
			res.setHeadKeyList(ServiceAssistance.getListHead("base_accelequy",InformationActivator.class));
		} catch (BusinessException e) {
			throw new ServiceException(e);
		}
		return res;
	}

	@SuppressWarnings("unchecked")
	public AccEle getDataByCode(String dataCode) throws ServiceException {
		try {
			return daeElemAdmin.getDataByCode(dataCode);
		} catch (BusinessException e) {
			throw new ServiceException(e);
		}
	}

	public <K extends BasePojo> List<K> getDataListByTypes(String[] types)
			throws ServiceException {
		try {
			return daeElemAdmin.getDataListByTypes(types);
		} catch (BusinessException e) {
			throw new ServiceException(e);
		}
	}

	public QueryRes getQueryResByTypes(String[] types) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = daeElemAdmin.getDataListByTypes(types);
			res.setDataList(pojoList);
			res.setMenuId("base_accelequy");
			res.setHeadKeyList(ServiceAssistance.getListHead("base_accelequy",InformationActivator.class));
		} catch (BusinessException e) {
			throw new ServiceException(e);
		}
		return res;
	}

	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		try {
			return daeElemAdmin.getKeyConvertMap();
		} catch (BusinessException e) {
			throw new ServiceException(e);
		}
	}

	public <K extends BasePojo> List<K> getDataListByKeys(String[] keys)
			throws ServiceException {
		try {
			return daeElemAdmin.getDataListByKeys(keys);
		} catch (BusinessException e) {
			throw new ServiceException(e);
		}
	}

	public QueryRes getQueryResByKeys(String[] keys) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = daeElemAdmin.getDataListByKeys(keys);
			res.setDataList(pojoList);
			res.setMenuId("base_accelequy");
			res.setHeadKeyList(ServiceAssistance.getListHead("base_accelequy",InformationActivator.class));
		} catch (BusinessException e) {
			throw new ServiceException(e);
		}
		return res;
	}

	private String menuId = "";

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	@Override
	public <K extends BasePojo> K getPojoByCode(String pojoCode)
			throws ServiceException {
		try {
			return daeElemAdmin.getPojoByCode(pojoCode);
		} catch (BusinessException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		try {
			return daeElemAdmin.getKeyConvertMap(listKey);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
}
