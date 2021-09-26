package com.yss.ams.base.information.modules.sys.dai.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;



//import com.yss.ams.base.information.modules.sys.dai.cache.DaiCache;
import com.yss.ams.base.information.modules.sys.dai.dao.DaiDao;
import com.yss.ams.base.information.modules.sys.dai.dao.DaiSqlBuilder;
import com.yss.ams.base.information.support.cache.DaiCache;
import com.yss.ams.base.information.support.sys.dai.pojo.Dai;
import com.yss.ams.base.information.support.sys.dai.service.IDaiDictService;
//import com.yss.ams.base.information.support.sys.dai.service.IDaiDictService;
//import com.yss.cache.DaiCache;
//import com.yss.dict.dai.dao.DaiDao;
//import com.yss.dict.dai.dao.DaiSqlBuilder;
//import com.yss.dict.dai.service.IDaiDictService;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.cache.assist.DefaultCacheRefresh;
//import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.db.DbPoolFactory;

//import dataservice.comm.pojo.Dai;

/**
 * 核算项目字典表T_S_DAI_ITEM service类 
 *
 */
@DefaultCacheRefresh(group = CacheGroup.DAI)
public class DaiDictService extends ServiceBus<DaiDictService> implements IDaiDictService {

	private DaiDao serviceDao = null;

	/**
	 * 构造方法
	 * @throws Exception
	 */
	public DaiDictService() throws Exception {
		serviceDao = new DaiDao(DbPoolFactory.getInstance().getPool(
				), new DaiSqlBuilder());
		dao = serviceDao;
	}

    /*STORY #26934 byleeyu20151105*/
	@Override
	public List<Dai> queryByCacheForKm() {
		DaiCache daiCache = CacheManager.getInstance().getCache(CacheGroup.DAI);
		List<Dai> list = daiCache.getCacheList();
		TreeMap<Integer,Dai> sortList = new TreeMap<Integer,Dai>();
		for(Dai dai : list){
			sortList.put(dai.getN_ORDER(), dai);
		}
		return new ArrayList<Dai>(sortList.values());
	}

	

}
