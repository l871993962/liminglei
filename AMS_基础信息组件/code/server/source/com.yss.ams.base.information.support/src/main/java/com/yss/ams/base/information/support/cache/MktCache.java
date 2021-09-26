package com.yss.ams.base.information.support.cache;

import java.util.ArrayList;
import java.util.List;

import com.yss.framework.api.common.co.Mkt;
import com.yss.ams.base.information.support.bi.mkt.service.IMktDataService;
import com.yss.framework.api.cache.assist.BaseCache;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.service.YssServiceFactory;

/**
 * 交易市场缓存
 * @author leeyu
 *
 */
public class MktCache extends BaseCache<Mkt> {

	private IMktDataService mktDataService = null;
	
	@Override
	protected void loadData() {

		//mktDataService = new MktDataService();
		
		//TASK #556020 分布式缓存改造 edit by sunyanlin 20190114
		mktDataService = YssServiceFactory.getInstance().createService(IMktDataService.class);
		
		CacheData data = mktDataService.updateByTimestamp(getTimestamp());
		this.setTimestamp(data.getTimestamp());
		List<Mkt> mktList = this.cacheData2List(data.getDataList(),Mkt.class);
		for(Mkt mkt : mktList){
			this.mapT.put(mkt.getC_MKT_CODE(), mkt);
			keyMap.put(mkt.getC_MKT_CODE(), mkt.getC_MKT_NAME());
			this.idMap.put(mkt.getId(), mkt);
		}
	}
	
	@Override
	public CacheGroup getCacheGroup() {
		return CacheGroup.MKT;
	}
	
	@Override
	public List<String> bindServiceNames() {
		List<String> list = new ArrayList<String>();
		list.add(IMktDataService.class.getSimpleName());
		return list;
	}

	@Override
	public Mkt getCacheByKey(String key) {
		Mkt mkt = null;
		mkt = this.mapT.get(key);
		return mkt;
	}

	@Override
	public List<Mkt> getCacheList() {
		List<Mkt> list = new ArrayList<Mkt>();
		
		rwl.readLock();
		try{
		list = this.mapT.getAllValues();
		}
		finally{
			rwl.readUnLock();
		}
		
		return list;
	}

	//STORY #40717 缓存刷新增加删除和审核数据时增量刷新 雷建华 20170329
	@Override
	protected String getCacheDataCode(BasePojo pojo) {
		return ((Mkt)pojo).getC_MKT_CODE();
	}

	@Override
	protected void loadDataByIds(String ids) {
		List<Mkt> mktList = mktDataService.queryByIds(ids);
		for(Mkt mkt : mktList){
			this.mapT.put(mkt.getC_MKT_CODE(), mkt);
			keyMap.put(mkt.getC_MKT_CODE(), mkt.getC_MKT_NAME());
			this.idMap.put(mkt.getId(), mkt);
		}		
	}
}
