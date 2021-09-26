package com.yss.ams.visaval.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.ams.visaval.activator.AvalActivator;
import com.yss.ams.visaval.admin.AdvAlgoDataAdmin;
import com.yss.ams.visaval.cache.AlgoCache;
import com.yss.ams.visaval.dao.AdvAlgoSqlBuilder;
import com.yss.ams.visaval.support.api.pojo.ParamFromSql;
import com.yss.ams.visaval.support.pojo.AdvAlgo;
import com.yss.ams.visaval.support.service.IVisAlgoDataService;
import com.yss.ams.visaval.support.service.IVisJythonFactoryWrap;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.exception.ErrorMessageException;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.api.util.JsonUtil;
import com.yss.mvc.service.ServiceAssistance;

public class AdvAlgoDataService extends ServiceBus<AdvAlgoDataService> implements IVisAlgoDataService {

	private AdvAlgoDataAdmin algoAdmin = null;
	
	public AdvAlgoDataService() {
		algoAdmin = new AdvAlgoDataAdmin(YssDbPoolFactory.getInstance().getDbPool(AvalActivator.class),
				new AdvAlgoSqlBuilder());
	}
	
	public <K extends BasePojo> K getDataByCode(String code) throws ServiceException {
		try {
			return algoAdmin.getDataByCode(code);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		try {
			return algoAdmin.getAllDataList();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public QueryRes getDataListRes() throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = algoAdmin.getAllDataList();
			res.setDataList(pojoList);
			res.setMenuId("AdvancedAlgorithm");
			res.setHeadKeyList(ServiceAssistance.getListHead("AdvancedAlgorithm",AvalActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	public <K extends BasePojo> List<K> getDataListByTypes(String[] types)
			throws ServiceException {
		try {
			return algoAdmin.getDataListByTypes(types);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public QueryRes getQueryResByTypes(String[] types) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = algoAdmin.getDataListByTypes(types);
			res.setDataList(pojoList);
			res.setMenuId("AdvancedAlgorithm");
			res.setHeadKeyList(ServiceAssistance.getListHead("AdvancedAlgorithm",AvalActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		try {
			return algoAdmin.getKeyConvertMap();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public <K extends BasePojo> List<K> getDataListByKeys(String[] keys)
			throws ServiceException {
		try {
			return algoAdmin.getDataListByKeys(keys);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public QueryRes getQueryResByKeys(String[] keys) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = algoAdmin.getDataListByKeys(keys);
			res.setDataList(pojoList);
			res.setMenuId("AdvancedAlgorithm");
			res.setHeadKeyList(ServiceAssistance.getListHead("AdvancedAlgorithm",AvalActivator.class));
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

	@SuppressWarnings("unchecked")
	@Override
	public <K extends BasePojo> K getPojoByCode(String pojoCode)
			throws ServiceException {
		AlgoCache algoCache = CacheManager.getInstance().getCache(CacheGroup.ALGO);
		return (K)algoCache.getCacheByKey(pojoCode);
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		AlgoCache algoCache = CacheManager.getInstance().getCache(CacheGroup.ALGO);
		return algoCache.getKeyConvertMap(listKey);
	}
	
	@Override
	public CacheData updateByTimestamp(String timestamp) {
		Date d = new Date(System.currentTimeMillis());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String t = df.format(d);
		
		List<BasePojo> list = null;
		//DOTO 需要补充按时间戳取数逻辑
		CacheData data = new CacheData();
		data.setTimestamp(timestamp);//带上原来的时间戳
		if(timestamp == null || timestamp.equals("")){
			list = this.getDataList();
		}
		else{
			list = algoAdmin.getDataListByTimestamp(timestamp);
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
		AlgoCache algoCache = CacheManager.getInstance().getCache(CacheGroup.ALGO);
		algoCache.reloadData();
	}

	/**
	 * add by wangtangyao 20160804 BUG #128486 【紧急】【广发证券】导入分级产品参数公式无效
	 * 由于跑业务调用算法取得是Python中存储的缓存而不是算法缓存中取，所以只刷新算法缓存无用。此处导入更新对应算法的Python缓存
	 */
	@Override
	public void updateimportAlgo(List<String> list) {
		AlgoCache algoCache = CacheManager.getInstance().getCache(CacheGroup.ALGO);
		try {
			if (this.safeData != null && this.safeData.getN_CHECK() <= 0) {
				for (String key : list) {
					//导入后已更新过缓存 此处从缓存中取而不是从后台文件中取，防止操作系统不一样导致换行符不同后面拆分不了
					AdvAlgo advpojo = algoCache.getCacheByKey(key);
					IVisJythonFactoryWrap factory = YssServiceFactory.getInstance().createService(IVisJythonFactoryWrap.class);
					factory.updateAlgo(key, advpojo.getC_ALGO_FORMULA());
				}
			}
		} catch (Exception ex) {
			throw new ErrorMessageException(ex, "更新算法公式python缓存出错");
		}
	}
	
	@Override
	public <T extends BasePojo> List<T> queryByIds(String ids){
		return algoAdmin.queryByIds(ids, AdvAlgo.class);
	}

	@Override
	public List<ParamFromSql> getParamFromSql(String sql) {
		return algoAdmin.getParamFromSql(sql);
	}

	@Override
	public BasePojo getAlgoByCode(String code) {
		return algoAdmin.getAlgoByCode(code);
	}
	
	
}
