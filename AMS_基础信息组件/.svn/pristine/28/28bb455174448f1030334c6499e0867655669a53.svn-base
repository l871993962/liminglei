package com.yss.ams.base.information.support.cache;

import java.util.ArrayList;
import java.util.List;

import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.CacheRefreshInfo;
import com.yss.framework.api.cache.assist.BaseCache;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.ams.base.information.support.bi.tdchan.pojo.TdChan;
import com.yss.ams.base.information.support.bi.tdchan.service.ISecSeatDataService;


/**
 * 交易渠道缓存
 * @author leeyu
 * 
 */
public class TdChanCache extends BaseCache<TdChan> {

	private ISecSeatDataService secSeatDataService = null;

	@Override
	protected void loadData() {
		//secSeatDataService = new SecSeatDataService();
		
		//TASK #556020 分布式缓存改造 edit by sunyanlin 20190114
		secSeatDataService = YssServiceFactory.getInstance().createService(ISecSeatDataService.class);
		
		//由于缓存数据加载中使用了词汇转R表的逻辑，若加载此缓存的时候，词汇缓存还未加载，就会在后续的逻辑中产生空指针异常到值报错  edit by  sunyanlin 20190606
				boolean flag = true;
				while(flag){
					VocCache cache = CacheManager.getInstance().getCache(CacheGroup.VOC);
					if(cache == null){
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							logger.error("缓存加载失败！", e);
							Thread.currentThread().interrupt();
						}
					}else{
						flag = false;
					}
				}
		
		CacheData data = secSeatDataService.updateByTimestamp(getTimestamp());
		this.setTimestamp(data.getTimestamp());
		List<TdChan> lstTdChan = this.cacheData2List(data.getDataList(),TdChan.class);
		for (TdChan tdChan : lstTdChan) {
			this.mapT.put(tdChan.getC_TD_CHAN_CODE(), tdChan);
			keyMap.put(tdChan.getC_TD_CHAN_CODE(), tdChan.getC_TD_CHAN_NAME());
			this.idMap.put(tdChan.getId(), tdChan);
		}
	}

	@Override
	public CacheGroup getCacheGroup() {
		return CacheGroup.TDCHAN;
	}
	
	@Override
	public List<String> bindServiceNames() {
		List<String> list = new ArrayList<String>();
		list.add(ISecSeatDataService.class.getSimpleName());
		return list;
	}

	@Override
	public TdChan getCacheByKey(String key) {
		TdChan chan = null;
		chan = this.mapT.get(key);
		return chan;
	}

	@Override
	public List<TdChan> getCacheList() {
		List<TdChan> list = new ArrayList<TdChan>();
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
		return ((TdChan)pojo).getC_TD_CHAN_CODE();
	}

	@Override
	protected void loadDataByIds(String ids) {
		List<TdChan> lstTdChan = secSeatDataService.queryByIds(ids);
		for (TdChan tdChan : lstTdChan) {
			this.mapT.put(tdChan.getC_TD_CHAN_CODE(), tdChan);
			keyMap.put(tdChan.getC_TD_CHAN_CODE(), tdChan.getC_TD_CHAN_NAME());
			this.idMap.put(tdChan.getId(), tdChan);
		}
	}
}
