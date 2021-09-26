package com.yss.ams.base.information.modules.bi.accountType.admin;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.modules.bi.accountType.dao.AccountTypeDao;
import com.yss.framework.api.common.co.BaseAdmin;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;

/**
 * @ClassName HkTypeUnifypayDataAdmin
 * @Description 款项类型设置
 * @author liminghong@ysstech.com
 * @CreateDate 2017年5月22日
 * @Version V1.21.5.0
 * @Copyright (c) 2017, 深圳赢时胜 All Rights Reserved.
 */
public class AccountTypeDataAdmin extends BaseAdmin{
	AccountTypeDao svcDao = null;
	
	public AccountTypeDataAdmin(DbPool pool, SQLBuilder sqlBuilder) {
		svcDao = new AccountTypeDao(pool, sqlBuilder);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getAllDataList() throws Exception {
		return (List<T>) svcDao.getAllDataList();
	}
	
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> T getDataByCode(String code) throws Exception {
		return (T) svcDao.getDataByCode(code);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByTypes(String[] types) throws Exception {
		return (List<T>) svcDao.getDataListByTypes(types);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByKeys(String[] keys) throws Exception {
		return (List<T>) svcDao.getDataListByKeys(keys);
	}
	
	public HashMap<String, String> getKeyConvertMap() throws Exception {
		return svcDao.getKeyConvertMap();
	}
	
	public HashMap<String, String> getKeyConvertMap(List<String> listKey) throws Exception {
		return svcDao.getKeyConvertMap(listKey);
	}
}
