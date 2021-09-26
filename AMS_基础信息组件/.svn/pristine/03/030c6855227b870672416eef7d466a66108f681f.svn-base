package com.yss.ams.base.information.modules.bi.account.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.modules.bi.account.admin.FundAccAdmin;
import com.yss.ams.base.information.modules.bi.account.dao.FundAccSqlBuilder;
import com.yss.ams.base.information.support.bi.account.pojo.FundAcc;
import com.yss.ams.base.information.support.bi.account.service.IFundAccDataService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.api.util.JsonUtil;
import com.yss.framework.db.DbPoolFactory;
import com.yss.mvc.service.ServiceAssistance;

/**
 * 资金账户参数数据服务
 * @author meip
 * @date 2014-10-08
 * @version V4.5.0.1
 */
public class FundAccDataService implements IFundAccDataService{
	private FundAccAdmin admin = null;
	public FundAccDataService(){
		admin = new FundAccAdmin(DbPoolFactory.getInstance().getPool(), new FundAccSqlBuilder());
	}

	@Override
	public <K extends BasePojo> K getDataByCode(String dataCode)
			throws ServiceException {
		try {
			return admin.getDataByCode(dataCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		try {
			return admin.getAllDataList();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public <K extends BasePojo> List<K> getAllDataByPort(String portCode) throws ServiceException {
		try {
			return admin.getAllDataByPort(portCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public QueryRes getDataListRes() throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = admin.getAllDataList();
			res.setDataList(pojoList);
			res.setMenuId(menuId);
			res.setHeadKeyList(ServiceAssistance.getListHead("ie"));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	

	@Override
	public <K extends BasePojo> List<K> getDataListByKeys(String[] keys)
			throws ServiceException {
		try {
			return admin.getDataListByKeys(keys);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public <K extends BasePojo> List<K> getDataListByTypes(String[] types)
			throws ServiceException {
		try {
			return admin.getDataListByTypes(types);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	public <K extends BasePojo> List<K> getDataListByAccTypes(String[] types)
			throws ServiceException {
		try {
			return admin.getDataListByTypes2(types);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	

	public <K extends BasePojo> List<K> getDataListByOrg(String orgCode)
			throws ServiceException {
		try {
			return admin.getDataListByOrg(orgCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	@Override
	public QueryRes getQueryResByKeys(String[] keys) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = admin.getDataListByKeys(keys);
			res.setDataList(pojoList);
			res.setMenuId(menuId);
			res.setHeadKeyList(ServiceAssistance.getListHead("ie"));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	@Override
	public QueryRes getQueryResByTypes(String[] types) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = admin.getDataListByTypes(types);
			res.setDataList(pojoList);
			res.setMenuId(menuId);
			res.setHeadKeyList(ServiceAssistance.getListHead("ie"));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	@Override
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		try {
			return admin.getKeyConvertMap();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	private String menuId = "fundAccInfo";

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
			return admin.getDataByCode(pojoCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		try {
			return admin.getKeyConvertMap(listKey);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public List<FundAcc> getDataListByPort(String portCode) {
		List<FundAcc> dataList = null;
		dataList = (List<FundAcc>) admin.getDataListByPort(portCode);
		return dataList;
	}
	
	/**
	 * 根据物理主键获取数据
	 * @param ids
	 * @return
	 */
	@Override
	public List<FundAcc> getDataListByIds(String ids) {
		List<FundAcc> dataList = null;
		dataList = (List<FundAcc>) admin.getDataListByIds(ids);
		return dataList;
	}
	
	/**
	 * 根据物理主键获取数据
	 * @param ids
	 * @return
	 */
	@Override
	public List<FundAcc> getDataListByAssCode(String ids) {
		List<FundAcc> dataList = null;
		dataList = (List<FundAcc>) admin.getDataListByAssCode(ids);
		return dataList;
	}
	

	/**
	 * 更新账户信息缓存
	 * 
	 */
	@Override
	public CacheData updateByTimestamp(String timestamp) {
		Date d = new Date(System.currentTimeMillis());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String t = df.format(d);

		List<BasePojo> list = null;
		CacheData data = new CacheData();
		if (timestamp == null || timestamp.equals("")) {
			list = this.getDataList();
		} else {
			list = admin.getDataListByTimestamp(timestamp);
		}

		data.setDataList(JsonUtil.toString(list));
		if (list != null && list.size() > 0) {
			data.setTimestamp(t);
		}
		return data;
	}

	@Override
	public <T extends BasePojo> List<T> queryByIds(String ids) {
		return null;
	}
	
	public List<FundAcc> getAccNameAndCaCode() {
		List<FundAcc> dataList = null;
		dataList = admin.getAccNameAndCaCode();
	    return dataList;
	}
}
