package com.yss.ams.base.information.modules.bi.accountType.service.imp;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.modules.bi.accountType.admin.AccountTypeDataAdmin;
import com.yss.ams.base.information.modules.bi.accountType.dao.AccountTypeSqlBuilder;
import com.yss.ams.base.information.support.bi.dactype.pojo.AccountType;
import com.yss.ams.base.information.support.bi.dactype.service.IAccountTypeDataService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.db.DbPoolFactory;
import com.yss.mvc.service.ServiceAssistance;

/**
 * @ClassName HkTypeUnifypayDataService
 * @Description 款项类型设置
 * @author liminghong@ysstech.com
 * @CreateDate 2017年5月22日
 * @Version V1.21.5.0
 * @Copyright (c) 2017, 深圳赢时胜 All Rights Reserved.
 */
public class AccountTypeDataService implements IAccountTypeDataService {
	private AccountTypeDataAdmin accountTypeAdmin = null;

	public AccountTypeDataService() {
		accountTypeAdmin = new AccountTypeDataAdmin(DbPoolFactory.getInstance()
				.getPool(), new AccountTypeSqlBuilder());
	}

	@SuppressWarnings("unchecked")
	public List<AccountType> getDataList() throws ServiceException {
		try {
			return accountTypeAdmin.getAllDataList();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public QueryRes getDataListRes() throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = accountTypeAdmin.getAllDataList();
			res.setDataList(pojoList);
			res.setMenuId("hkcode");
			res.setHeadKeyList(ServiceAssistance.getListHead("hkcode"));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	@SuppressWarnings("unchecked")
	public AccountType getDataByCode(String dataCode)
			throws ServiceException {
		try {
			return accountTypeAdmin.getDataByCode(dataCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public <K extends BasePojo> List<K> getDataListByTypes(String[] types)
			throws ServiceException {
		try {
			return accountTypeAdmin.getDataListByTypes(types);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public QueryRes getQueryResByTypes(String[] types) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = accountTypeAdmin.getDataListByTypes(types);
			res.setDataList(pojoList);
			res.setMenuId("hkcode");
			res.setHeadKeyList(ServiceAssistance.getListHead("hkcode"));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		try {
			return accountTypeAdmin.getKeyConvertMap();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public <K extends BasePojo> List<K> getDataListByKeys(String[] keys)
			throws ServiceException {
		try {
			return accountTypeAdmin.getDataListByKeys(keys);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public QueryRes getQueryResByKeys(String[] keys) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = accountTypeAdmin.getDataListByTypes(keys);
			res.setDataList(pojoList);
			res.setMenuId("hkcode");
			res.setHeadKeyList(ServiceAssistance.getListHead("hkcode"));
		} catch (Exception e) {
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
			return accountTypeAdmin.getDataByCode(pojoCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		try {
			return accountTypeAdmin.getKeyConvertMap(listKey);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
}
