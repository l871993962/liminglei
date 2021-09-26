package com.yss.ams.base.information.modules.bi.account.admin;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.modules.bi.account.dao.FundAccDao;
import com.yss.ams.base.information.support.bi.account.pojo.FundAcc;
import com.yss.framework.api.common.co.BaseAdmin;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;


public class FundAccAdmin extends BaseAdmin {
	private FundAccDao dao = null;

	public FundAccAdmin(DbPool pool, SQLBuilder sqlBuilder) {
		dao = new FundAccDao(pool, sqlBuilder);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getAllDataList() throws Exception {
		return (List<T>) dao.getAllDataList();
	}
	
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getAllDataByPort(String portCode) throws Exception {
		return (List<T>) dao.getAllDataList();
	}

	@SuppressWarnings("unchecked")
	public <T extends BasePojo> T getDataByCode(String code) throws Exception {
		return (T) dao.getDataByCode(code);
	}

	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByTypes(String[] types)
			throws Exception {
		return (List<T>) dao.getDataListByTypes(types);
	}

	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByTypes2(String[] types)
			throws Exception {
		return (List<T>) dao.queryFundAccByType2(types);
	}
	public HashMap<String, String> getKeyConvertMap() throws Exception {
		return dao.getKeyConvertMap();
	}
	
	public HashMap<String, String> getKeyConvertMap(List<String> listKey) throws Exception {
		return dao.getKeyConvertMap(listKey);
	}

	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByKeys(String[] types)
			throws Exception {
		return (List<T>) dao.getDataListByTypes(types);
	}
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByOrg(String types)
			throws Exception {
		return (List<T>) dao.getDataListByOrg(types);
	}
	
	public List<FundAcc> getDataListByPort(String portCode) {
		return  dao.getDataListByPort(portCode);
	}

	public List<FundAcc> getDataListByIds(String ids) {
		// TODO Auto-generated method stub
		return  dao.getDataListByIds(ids);
	}
	
	public List<FundAcc> getDataListByAssCode(String assCode) {
		// TODO Auto-generated method stub
		return  dao.getDataListByAssCode(assCode);
	}
	
	/**
	 * 更新缓存
	 * @return
	 */
	public List<BasePojo> getDataListByTimestamp(String timestamp) {
		return dao.getDataListByTimestamp(timestamp);
	}
	public List<FundAcc> getAccNameAndCaCode() {
		// TODO Auto-generated method stub
		return dao.getAccNameAndCaCode();
	}
}
