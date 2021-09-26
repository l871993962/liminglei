package com.yss.ams.base.information.modules.bi.tdchan.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.activator.InformationActivator;
import com.yss.ams.base.information.modules.bi.tdchan.admin.SecSeatDataAdmin;
import com.yss.ams.base.information.modules.bi.tdchan.dao.TdChanDao;
import com.yss.ams.base.information.modules.bi.tdchan.dao.TdChanSqlBuilder;
import com.yss.ams.base.information.support.bi.tdchan.pojo.TdChan;
import com.yss.ams.base.information.support.bi.tdchan.service.ISecSeatDataService;
import com.yss.ams.base.information.support.cache.TdChanCache;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.api.util.JsonUtil;
import com.yss.framework.db.DbPoolFactory;
import com.yss.mvc.service.ServiceAssistance;

public class SecSeatDataService implements ISecSeatDataService {
	private TdChanDao tdChanDao = null;
	private SecSeatDataAdmin secSeatAdmin = null;

	public SecSeatDataService() {
		secSeatAdmin = new SecSeatDataAdmin(DbPoolFactory.getInstance()
				.getPool(), new TdChanSqlBuilder());
		tdChanDao = new TdChanDao(YssDbPoolFactory.getInstance()
				.getDbPool(InformationActivator.class), new TdChanSqlBuilder());
	}

	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		try {
			return secSeatAdmin.getAllDataList();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public QueryRes getDataListRes() throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = secSeatAdmin.getAllDataList();
			res.setDataList(pojoList);
			res.setMenuId("tradeSeat");
			res.setHeadKeyList(ServiceAssistance.getListHead("base_tradeSeat",InformationActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	public <K extends BasePojo> K getDataByCode(String dataCode)
			throws ServiceException {
		try {
			return secSeatAdmin.getDataByCode(dataCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public <K extends BasePojo> List<K> getDataListByTypes(String[] types)
			throws ServiceException {
		try {
			return secSeatAdmin.getDataListByTypes(types);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public QueryRes getQueryResByTypes(String[] types) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = secSeatAdmin.getDataListByTypes(types);
			res.setDataList(pojoList);
			res.setMenuId("tradeSeat");
			res.setHeadKeyList(ServiceAssistance.getListHead("base_tradeSeat",InformationActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		try {
			return secSeatAdmin.getKeyConvertMap();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public <T extends BasePojo> List<T> getTdChanDataList() throws Exception {
		return secSeatAdmin.getAllDataList();
	}

	public QueryRes getTdChanDataListRes() throws Exception {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList = secSeatAdmin.getAllDataList();
		res.setDataList(pojoList);
		res.setMenuId("tradeSeat");
		res.setHeadKeyList(ServiceAssistance.getListHead("base_tradeSeat",InformationActivator.class));
		return res;
	}

	public <K extends BasePojo> List<K> getDataListByKeys(String[] keys)
			throws ServiceException {
		try {
			return secSeatAdmin.getDataListByKeys(keys);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public QueryRes getQueryResByKeys(String[] keys) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = secSeatAdmin.getAllDataList();
			res.setDataList(pojoList);
			res.setMenuId("tradeSeat");
			res.setHeadKeyList(ServiceAssistance.getListHead("base_tradeSeat",InformationActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	private String menuId = "";

	public String getMenuId() {
		return this.menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <K extends BasePojo> K getPojoByCode(String pojoCode)
			throws ServiceException {
		TdChanCache tdChan = CacheManager.getInstance().getCache(CacheGroup.TDCHAN);
		return (K)tdChan.getCacheByKey(pojoCode);
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		TdChanCache tdChan = CacheManager.getInstance().getCache(CacheGroup.TDCHAN);
		return tdChan.getKeyConvertMap(listKey);
	}

	@Override
	public CacheData updateByTimestamp(String timestamp) {
		Date d = new Date(System.currentTimeMillis());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String t = df.format(d);
		
		List<BasePojo> list = null;
		//DOTO 需要补充按时间戳取数逻辑
		CacheData data = new CacheData();
		data.setTimestamp(timestamp);//带上时间戳先
		if(timestamp == null || timestamp.equals("")){
			list = this.getDataList();
		}
		else{
			list = secSeatAdmin.getDataListByTimestamp(timestamp);
		}
		
		data.setDataList(JsonUtil.toString(list));
		if(list != null && list.size() > 0){
			data.setTimestamp(t);
		}
		return data;
	}
	
	@Override
	public HashMap<String, String> getShortDataMap() throws ServiceException{
		HashMap<String, String> map = new HashMap<String, String>();
		try{
			TdChanCache cache =  CacheManager.getInstance().getCache(CacheGroup.TDCHAN);
			for (TdChan tdChan : cache.getCacheList()) {
				if(!map.containsKey(tdChan.getC_TD_CHAN_CODE())){
					map.put(tdChan.getC_TD_CHAN_CODE(), tdChan.getC_TD_CHAN_NAME());
				}
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return map;
	}

	@Override
	public String insertTdChan(BasePojo pojo) throws ServiceException {
		// TODO Auto-generated method stub
		return tdChanDao.insert(pojo);
	}

	@Override
	public String getMaxTdChanCode() throws ServiceException {
		// TODO Auto-generated method stub
		return tdChanDao.getMaxTdChanCode();
	}

	@Override
	public BasePojo getDataByOrgCode(String orgCode) throws ServiceException {
		// TODO Auto-generated method stub
		return tdChanDao.getDataByOrgCode(orgCode);
	}
	
	/**
	 * By Jinghehe 2015-9-29 
	 * 获取所有渠道数据，包括ALL 构造的数据
	 */
	@Override
	public <K extends BasePojo> List<K> getDataListByComm(String[] types)
			throws ServiceException {
		try {
			return secSeatAdmin.getDataListByComm(types);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * 根据组合代码获取组合对应席位
	 * zhoushuhang 2016-4-7  在ETF补票日期界面中增加补票席位。通过选择组合带出组合对应补票席位。
	 */
	@Override
	public List<BasePojo> queryPortRelaTradeSeat(String portCode) {
		return tdChanDao.queryTradeSeatDao(portCode);
	}
	
	@Override
	public <T extends BasePojo> List<T> queryByIds(String ids) {
		return tdChanDao.queryByIds(ids, TdChan.class);
	}
	
	/**
	 * BUG #172866 【加急】【南方基金】支持场外申赎业务流水的EXCEL格式的导入--存在问题汇总
	 * edit by zouyuan 20170914 根据选择的组合信息，加载关联的交易渠道信息，以及所有机构信息
	 * @param types
	 * @return
	 */
	@Override
	public List<BasePojo> getDataListByPort(String[] portCode) {
		return tdChanDao.getDataListByPort(portCode);
	}
	
	public <K extends BasePojo> List<K> getDataListByPorts(String[] types)
			throws ServiceException {
		try {
			return secSeatAdmin.getDataListByPort(types);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
}
