package com.yss.ams.base.information.support.cache;


import java.util.ArrayList;
import java.util.List;


import com.yss.ams.base.information.support.sys.dai.pojo.Dai;
import com.yss.ams.base.information.support.sys.dai.service.IAccProDataService;
import com.yss.framework.api.cache.assist.BaseCache;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.service.YssServiceFactory;


/**
 * 核算项目缓存
 * @author leeyu
 * 
 */
public class DaiCache extends BaseCache<Dai>{

	private IAccProDataService accProDataService = null;
	
	@Override
	public CacheGroup getCacheGroup() {
		return CacheGroup.DAI;
	}
	
	@Override
	public List<String> bindServiceNames() {
		List<String> list = new ArrayList<String>();
		list.add(IAccProDataService.class.getSimpleName());
		return list;
	}

	/**
	 * 加载缓存数据
	 */
	@Override
	protected void loadData() {
		//accProDataService = new AccProDataService();
		
		//TASK #556020 分布式缓存改造 edit by sunyanlin 20190114
		accProDataService = YssServiceFactory.getInstance().createService(IAccProDataService.class);
		
		CacheData data = accProDataService.updateByTimestamp(getTimestamp());
		this.setTimestamp(data.getTimestamp());
		List<Dai> lstDai = this.cacheData2List(data.getDataList(),Dai.class);
		for (Dai dai : lstDai) {
			this.mapT.put(dai.getC_DAI_CODE(), dai);
			keyMap.put(dai.getC_DAI_CODE(), dai.getC_DAI_NAME());
			this.idMap.put(dai.getId(), dai);
		}
	
	}

	/**
	 * 在缓存中，根据提供的键值，获取对应键的Dai对象
	 */
	@Override
	public Dai getCacheByKey(String key) {
		Dai dai = null;
		dai = this.mapT.get(key);
		return dai;
	}

	/**
	 * 获取缓存中所有数据
	 */
	@Override
	public List<Dai> getCacheList() {
		List<Dai> list = new ArrayList<Dai>();
		
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
		return ((Dai)pojo).getC_DAI_CODE();
	}

	/**
	 * 审核操作时，根据id加载数据
	 */
	@Override
	protected void loadDataByIds(String ids) {
		List<Dai> listDai = accProDataService.queryByIds(ids);
		if(listDai!= null && listDai.size()>0){
			for (Dai dai : listDai) {
				this.mapT.put(dai.getC_DAI_CODE(), dai);
				keyMap.put(dai.getC_DAI_CODE(), dai.getC_DAI_NAME());
				this.idMap.put(dai.getId(), dai);
			}
		}
	}
	
}
