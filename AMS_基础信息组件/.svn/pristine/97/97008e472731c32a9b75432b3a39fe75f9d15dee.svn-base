package com.yss.ams.base.information.modules.bi.accountTreeA.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.modules.bi.accountTreeA.dao.AccountTreeADao;
import com.yss.ams.base.information.modules.bi.accountTreeA.dao.AccountTreeASqlBuilder;
import com.yss.ams.base.information.support.bi.accountTreeA.service.IAccountTreeADataService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.db.DbPoolFactory;

public class AccountTreeADataService implements IAccountTreeADataService {

	private AccountTreeADao serviceDao = null;

	/**
	 * 账户树形结构A区数据服务
	 * @throws Exception
	 */
	public AccountTreeADataService() throws Exception {
		serviceDao = new AccountTreeADao(DbPoolFactory.getInstance().getPool(), new AccountTreeASqlBuilder());
	}
	
	@Override
	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		return serviceDao.getDataList();
	}

	@Override
	public QueryRes getDataListRes() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <K extends BasePojo> K getDataByCode(String dataCode)
			throws ServiceException {
		return serviceDao.getDataByCode(dataCode);
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
	public <K extends BasePojo> K getPojoByCode(String pojoCode)
			throws ServiceException {
		// TODO Auto-generated method stub
		return serviceDao.getDataByCode(pojoCode);
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
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		return serviceDao.getKeyConvertMap();
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		return serviceDao.getKeyConvertMap();
	}

}
