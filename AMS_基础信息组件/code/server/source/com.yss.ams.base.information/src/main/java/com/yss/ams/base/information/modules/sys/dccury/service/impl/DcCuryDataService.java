package com.yss.ams.base.information.modules.sys.dccury.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.activator.InformationActivator;
import com.yss.ams.base.information.modules.bi.hday.dao.HdaySqlBuilder;
import com.yss.ams.base.information.modules.sys.dccury.admin.DcCuryDataAdmin;
import com.yss.ams.base.information.modules.sys.dccury.dao.DcCurySqlBuilder;
import com.yss.ams.base.information.support.cache.DcCache;
import com.yss.ams.base.information.support.sys.dccury.pojo.DcCury;
import com.yss.ams.base.information.support.sys.dccury.service.IDCDataService;
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

/**
 * 国际货币数据服务类
 * @author 马向峰 拆分
 *
 */
public class DcCuryDataService implements IDCDataService {
	private DcCuryDataAdmin dcAdmin = null;

	public DcCuryDataService() {
		dcAdmin = new DcCuryDataAdmin(DbPoolFactory.getInstance().getPool(),
				new DcCurySqlBuilder());
	}

	/**
	 * 查询所有国际货币
	 * @return	国际货币列表
	 * @throws Exception
	 */
	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		try {
			return dcAdmin.getAllDataList();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public QueryRes getDataListRes() throws ServiceException {
		QueryRes res = new QueryRes();
		try {
			List<BasePojo> pojoList = dcAdmin.getAllDataList();
			res.setDataList(pojoList);
			res.setMenuId("base_currency");
			res.setHeadKeyList(ServiceAssistance.getListHead("base_currency",InformationActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	/**
	 * 根据币种代码查询货币
	 * @param code	币种代码
	 * @return	货币信息
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <K extends BasePojo> K getDataByCode(String dataCode)
			throws ServiceException {
		try {
			return  dcAdmin.getDataByCode(dataCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据币种代码的集合查询货币信息
	 * @param types 币种代码数组
	 * @return	货币信息List集合
	 * @throws Exception
	 */
	public <K extends BasePojo> List<K> getDataListByTypes(String[] types)
			throws ServiceException {
		try {
			return dcAdmin.getDataListByTypes(types);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public QueryRes getQueryResByTypes(String[] types) throws ServiceException {
		QueryRes res = new QueryRes();
		try {
			List<BasePojo> pojoList = dcAdmin.getDataListByTypes(types);
			res.setDataList(pojoList);
			res.setMenuId("base_currency");
			res.setHeadKeyList(ServiceAssistance.getListHead("base_currency",InformationActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		try {
			return dcAdmin.getKeyConvertMap();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public QueryRes getPortCurruncyListRes() throws Exception {
		return null;
	}

	/**
	 * 根据币种代码的集合查询货币信息
	 * @param types 币种代码数组
	 * @return	货币信息List集合
	 * @throws Exception
	 */
	public <K extends BasePojo> List<K> getDataListByKeys(String[] keys)
			throws ServiceException {
		try {
			return dcAdmin.getDataListByKeys(keys);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public QueryRes getQueryResByKeys(String[] keys) throws ServiceException {
		QueryRes res = new QueryRes();
		try {
			List<BasePojo> pojoList = dcAdmin.getDataListByKeys(keys);
			res.setDataList(pojoList);
			res.setMenuId("base_currency");
			res.setHeadKeyList(ServiceAssistance.getListHead("base_currency",InformationActivator.class));
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
	public <T extends BasePojo> List<T> getPortCurruncyList() throws Exception {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <K extends BasePojo> K getPojoByCode(String pojoCode)
			throws ServiceException {
		DcCache dcCache = CacheManager.getInstance().getCache(CacheGroup.DC);
		return (K)dcCache.getCacheByKey(pojoCode);
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		DcCache dcCache = CacheManager.getInstance().getCache(CacheGroup.DC);
		return dcCache.getKeyConvertMap(listKey);
	}
	
	/**
	 * 根据时间戳更新
	 */
	@Override
	public CacheData updateByTimestamp(String timestamp) {
		Date d = new Date(System.currentTimeMillis());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String t = df.format(d);
		
		List<BasePojo> list = null;
		//DOTO 需要补充按时间戳取数逻辑
		CacheData data = new CacheData();
		data.setTimestamp(timestamp);//把时间带上，因为反审核不会修改修改时间，是获取不到数据的，这时会将时间戳设置为空
		if(timestamp == null || timestamp.equals("")){
			list = this.getDataList();
		}
		else{
			list = dcAdmin.getDataListByTimestamp(timestamp);
		}
		
		data.setDataList(JsonUtil.toString(list));
		if(list != null && list.size() > 0){
			data.setTimestamp(t);
		}
		return data;
	}

	/**
	 * Fixed by huangsq 20160729 BUG #135169 算法公式重新导入后不启作用<br />
	 * 重新加载缓存
	 */
	@Override
	public void reloadCache() {
		DcCache cache = CacheManager.getInstance().getCache(CacheGroup.DC);
		cache.reloadData();
	}
	
	@Override
	public <T extends BasePojo> List<T> queryByIds(String ids) {
		return dcAdmin.queryByIds(ids, DcCury.class);
	}
    	

}
