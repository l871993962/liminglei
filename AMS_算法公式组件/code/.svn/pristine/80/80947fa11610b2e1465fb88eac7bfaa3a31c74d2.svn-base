package com.yss.ams.visaval.cache;

import java.util.ArrayList;
import java.util.List;

import com.yss.ams.visaval.support.pojo.AdvAlgo;
import com.yss.ams.visaval.support.service.IVisAlgoDataService;
import com.yss.framework.api.cache.CacheRefreshInfo;
import com.yss.framework.api.cache.assist.BaseCache;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.service.YssServiceFactory;

/**
 * 高级算法缓存
 * @author leeyu
 * 
 */
public class AlgoCache extends BaseCache<AdvAlgo> {

	private IVisAlgoDataService algoDataService = null;

	@Override
	protected void loadData() {
		algoDataService =  YssServiceFactory.getInstance().createService(IVisAlgoDataService.class);

		CacheData data = algoDataService.updateByTimestamp(getTimestamp());
		this.setTimestamp(data.getTimestamp());
		List<AdvAlgo> lstAlgo = this.cacheData2List(data.getDataList(), AdvAlgo.class);
		
		for (AdvAlgo advAlgo : lstAlgo) {
			this.mapT.put(advAlgo.getC_ALGO_CODE(), advAlgo);
			this.putKeyMap(advAlgo.getC_ALGO_CODE(), advAlgo.getC_ALGO_NAME());
			this.idMap.put(advAlgo.getId(), advAlgo);
		}
	}

	/**
	 * STORY #31713 【产品优化】算法公式配置优化
	 * 20170906
	 * 马向峰
	 * 审核后将数据添加到缓存
	 * @param advList
	 */
	/*public void addData2Cache(List<BasePojo> advList){
		for(BasePojo pojo : advList){
			
			AdvAlgo advAlgo = (AdvAlgo) pojo;
			advAlgo = (AdvAlgo)algoDataService.getAlgoByCode(advAlgo.getC_ALGO_CODE());
			this.mapT.put(advAlgo.getC_ALGO_CODE(), advAlgo);
			this.putKeyMap(advAlgo.getC_ALGO_CODE(), advAlgo.getC_ALGO_NAME());
			this.idMap.put(advAlgo.getId(), advAlgo);
		}
	}*/
	
	/**
	 * STORY #31713 【产品优化】算法公式配置优化
	 * 20170906
	 * 马向峰
	 * 反审核后将数据移出缓存
	 * @param advList
	 */
	/*public void removeDataFromCache(List<BasePojo> advList){
		for(BasePojo pojo : advList){
			AdvAlgo advAlgo = (AdvAlgo) pojo;
			if(this.mapT.containsKey(advAlgo.getC_ALGO_CODE())){
				this.mapT.remove(advAlgo.getC_ALGO_CODE());
			}
			
			if(this.keyMap.containsKey(advAlgo.getC_ALGO_CODE())){
				this.keyMap.remove(advAlgo.getC_ALGO_CODE());
			}
			
			if(this.idMap.containsKey(advAlgo.getId())){
				this.idMap.remove(advAlgo.getId());
			}
		}
	}*/

	@Override
	public CacheGroup getCacheGroup() {
		return CacheGroup.ALGO;
	}

	@Override
	public List<String> bindServiceNames() {
		List<String> list = new ArrayList<String>();
		list.add(IVisAlgoDataService.class.getSimpleName());
		return list;
	}

	@Override
	public AdvAlgo getCacheByKey(String key) {
		AdvAlgo algo = null;
		rwl.readLock();
		try {
			algo = this.mapT.get(key);
		} finally {
			rwl.readUnLock();
		}

		// 二次加载
		if (null == algo) {
			this.update(new CacheRefreshInfo());

			rwl.readLock();
			try {
				algo = this.mapT.get(key);
			} finally {
				rwl.readUnLock();
			}
		}

		return algo;
	}

	@Override
	public List<AdvAlgo> getCacheList() {
		List<AdvAlgo> list = new ArrayList<AdvAlgo>();
		rwl.readLock();
		try {
			list = this.mapT.getAllValues();
		} finally {
			rwl.readUnLock();
		}
		return list;
	}

	//STORY #40717 缓存刷新增加删除和审核数据时增量刷新 雷建华 20170329
	@Override
	protected String getCacheDataCode(BasePojo pojo) {
		return ((AdvAlgo)pojo).getC_ALGO_CODE();
	}



	@Override
	protected void loadDataByIds(String ids) {
		List<AdvAlgo> list = algoDataService.queryByIds(ids);
		if(list!=null && list.size()>0){
			for (AdvAlgo advAlgo : list) {
				this.mapT.put(advAlgo.getC_ALGO_CODE(), advAlgo);
				this.putKeyMap(advAlgo.getC_ALGO_CODE(), advAlgo.getC_ALGO_NAME());
				this.idMap.put(advAlgo.getId(), advAlgo);
			}
		}
	}
}
