package com.yss.ams.product.information.modules.ab.portrela.admin;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.modules.ab.portrela.dao.PortRelaDao;
import com.yss.framework.api.common.co.BaseAdmin;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.service.ServiceException;

public class PortRelaTradeChanDataAdmin extends BaseAdmin {
	PortRelaDao svcDao = null;

	public PortRelaTradeChanDataAdmin(DbPool pool, SQLBuilder sqlBuilder) {
		svcDao = new PortRelaDao(pool, sqlBuilder);
	}

	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getAllDataList() throws ServiceException {
		return (List<T>) svcDao.getAllDataList();
	}

	@SuppressWarnings("unchecked")
	public <T extends BasePojo> T getDataByCode(String code) throws ServiceException {
		return (T) svcDao.getDataByCode(code);
	}

	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByTypes(String[] types)
			throws ServiceException {
		return (List<T>) svcDao.getDataListByTypes(types);
	}

	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		return svcDao.getKeyConvertMap();
	}

	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByKeys(String[] types)
			throws ServiceException {
		return (List<T>) svcDao.getDataListByKeys(types);
	}
}
