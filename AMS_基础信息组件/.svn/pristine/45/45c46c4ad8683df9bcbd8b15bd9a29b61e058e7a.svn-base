package com.yss.ams.base.information.modules.bi.org.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.ams.base.information.activator.InformationActivator;
import com.yss.ams.base.information.modules.bi.org.admin.OrgDataAdmin;
import com.yss.ams.base.information.modules.bi.org.dao.OrgDao;
import com.yss.ams.base.information.modules.bi.org.dao.OrgSqlBuilder;
import com.yss.ams.base.information.support.bi.account.cache.BaseFundAccCache;
import com.yss.ams.base.information.support.bi.org.pojo.Org;
import com.yss.ams.base.information.support.bi.org.service.IOrgDataService;
import com.yss.ams.base.information.support.cache.OrgCache;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.cache.assist.DefaultCacheRefresh;
import com.yss.framework.api.common.YssConstant;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.api.util.JsonUtil;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.db.DbPoolFactory;
import com.yss.mvc.service.ServiceAssistance;

/**
 * 机构数据服务类
 * @author 马向峰 拆分
 *@Date 20170531
 */
@DefaultCacheRefresh(group = CacheGroup.ORG)
public class OrgDataService implements IOrgDataService {
	private OrgDataAdmin orgAdmin = null;
	private OrgDao orgDao = null;

	public OrgDataService() {
		orgAdmin = new OrgDataAdmin(DbPoolFactory.getInstance().getPool(
				YssConstant.DBSERVICE_NAME), new OrgSqlBuilder());
		orgDao = new OrgDao(YssDbPoolFactory.getInstance()
				.getDbPool(InformationActivator.class), new OrgSqlBuilder());
	}

	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		try {
			OrgCache cache = (OrgCache)CacheManager.getInstance().getCache(CacheGroup.ORG);
			List<Org> list = null;
			if(cache != null){
				list = cache.getCacheList();
			}
			if(list != null && list.size() > 0){
				return (List<K>) list;
			}else{
				return orgAdmin.getAllDataList();
			}
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public QueryRes getDataListRes() throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = orgAdmin.getAllDataList();
			res.setDataList(pojoList);
			res.setMenuId("base_organ");
			res.setHeadKeyList(ServiceAssistance.getListHead("base_organ",InformationActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	public <K extends BasePojo> K getDataByCode(String dataCode)
			throws ServiceException {
		try {
			return orgAdmin.getDataByCode(dataCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public <K extends BasePojo> List<K> getDataListByTypes(String[] types)
			throws ServiceException {
		try {
			return orgAdmin.getDataListByTypes(types);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * 根据主体资质获取数据
	 * @param types
	 * @return
	 * @throws ServiceException
	 */
	public <K extends BasePojo> List<K> getDataListByZtzz(String[] types)
			throws ServiceException {
		try {
			return orgAdmin.getDataListByZtzz(types);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public QueryRes getQueryResByTypes(String[] types) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = orgAdmin.getDataListByTypes(types);
			res.setDataList(pojoList);
			res.setMenuId("base_organ");
			res.setHeadKeyList(ServiceAssistance.getListHead("base_organ",InformationActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		try {
			return orgAdmin.getKeyConvertMap();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public <K extends BasePojo> List<K> getDataListByKeys(String[] keys)
			throws ServiceException {
		try {
			return orgAdmin.getDataListByKeys(keys);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public QueryRes getQueryResByKeys(String[] keys) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = orgAdmin.getDataListByKeys(keys);
			res.setDataList(pojoList);
			res.setMenuId("base_organ");
			res.setHeadKeyList(ServiceAssistance.getListHead("base_organ",InformationActivator.class));
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
			return orgAdmin.getDataByCode(pojoCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		try {
			return orgAdmin.getKeyConvertMap(listKey);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public BasePojo getDataByCounterpartyName(
			String counterpartyName) throws ServiceException {
		return orgDao.getDataByCounterpartyName(counterpartyName);
	}

	@Override
	public String insertOrg(BasePojo pojo) throws ServiceException {
		String result = "";
		try{
			orgDao.insert(pojo);
			result = "SUCCESS";
		} catch (Exception e) {
			result = "FAIL";
			throw new ServiceException(e);
		}
		return result;
	}

	@Override
	public String getMaxOrgCode() throws ServiceException {
		// TODO Auto-generated method stub
		return orgDao.getMaxOrgCode();
	}

	/**
	 * 根据付款账号获得父级机构代码
	 */
	@Override
	public List<String> getOrgCodebyAccNo(String AccNo) {
		return orgAdmin.getOrgCodebyAccNo(AccNo);
	}

	/**
	 * 更新机构信息缓存
	 * 
	 * @author liuxiang 2016-2-26 STORY #28246 【自动化款指令】及【划款指令管理】模块界面问题优化
	 */
	@Override
	public CacheData updateByTimestamp(String timestamp) {
		Date d = new Date(System.currentTimeMillis());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String t = df.format(d);

		List<BasePojo> list = null;
		CacheData data = new CacheData();
		data.setTimestamp(timestamp);
		if (timestamp == null || timestamp.equals("")) {
			list = this.getDataList();
		} else {
			list = orgAdmin.getDataListByTimestamp(timestamp);
		}

		data.setDataList(JsonUtil.toString(list));
		if (list != null && list.size() > 0) {
			data.setTimestamp(t);
		}
		return data;
	}
	
	/**
	 * 按条件获取数据
	 * add by shijian 2016-10-12 STORY #35056 嘉实基金--成交清算日报表--增加名义管理人等字段
	 */
	@Override
	public List<BasePojo> getDataListByCondition(String condition) {
		return orgDao.getDataListByCondition(condition);
	}
	
	@Override
	public <T extends BasePojo> List<T> queryByIds(String ids) {
		return orgAdmin.queryByIds(ids, Org.class);
	}
	
	@Override
	public <T extends BasePojo> List<T> getAllBankHead()
			throws ServiceException {
		try {
			return orgAdmin.getAllBankHead();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public <T extends BasePojo> List<T> getBankBranchByHead(String[] param)
			throws ServiceException {
		try {
			return orgAdmin.getBankBranchByHead(param);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}


	@Override
	public void insert(List<BasePojo> orgList) {
		orgDao.insert(orgList);
	}
	

	@Override
	public Map<String, String> insert(String c_Org_Code) {
		return orgDao.insert(c_Org_Code);
	}

	@Override
	public <K extends BasePojo> List<K> getDataListByAptitude(String[] types) throws ServiceException {
		return orgDao.getDataListByAptitude(types);
	}

	@Override
	public <K extends BasePojo> List<K> getParentListByTypes(String[] types) throws ServiceException {
		return orgDao.getParentListByTypes(types);
	}
	
	@Override
	public String getUpdateByTimestampCount(String timestamp){
		if(StringUtil.IsNullOrEmpty(timestamp) || "empty".equals(timestamp)){
			return orgAdmin.getDataListCount();
		}else {
			return orgAdmin.getDataListByTimestampCount(timestamp);
		}
	}
	
	@Override
	public CacheData updateByTimestampPage(String timestamp,PageInation page){
		Date d = new Date(System.currentTimeMillis());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String t = df.format(d);
		
		List<BasePojo> list = null;
		//DOTO 需要补充按时间戳取数逻辑
		CacheData data = new CacheData();
		if(timestamp == null || timestamp.equals("") || "empty".equals(timestamp)){
			//张绍林。要直接从数据库里拿
			//BUG #119297 V4.5客户端缓存文件CacheData\10.1.12.11.8083.mdb存在重复数据
			//张绍林-20150924
			list = orgAdmin.getDataListPageFromDb(page);
		}
		else{
			list = orgAdmin.getDataListByTimestampPage(timestamp, page);
		}
		
		data.setDataList(JsonUtil.toString(list));
		if(list != null && list.size() > 0){
			data.setTimestamp(t);
		}
		else{
			data.setTimestamp(timestamp);
		}
		return data;
	}
	
	@Override
	public CacheData updateByIds(String ids){
		List<Org> dataList = orgAdmin.queryByIds(ids, Org.class);
		CacheData data = new CacheData();
		data.setDataList(JsonUtil.toString(dataList));
		return data;
	}
	
	@Override
	public List<Org> queryOrgByPort(String portCode,String c_dv_type){
		return this.orgDao.queryOrgByPort(portCode,c_dv_type);	
	}
}
