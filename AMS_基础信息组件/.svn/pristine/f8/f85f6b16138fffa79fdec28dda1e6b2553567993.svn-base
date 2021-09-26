package com.yss.ams.base.information.support.cache;

import java.util.ArrayList;
import java.util.List;

import com.yss.ams.base.information.support.bi.ie.pojo.Ie;
import com.yss.ams.base.information.support.bi.ie.service.IIeDataService;
import com.yss.framework.api.cache.CacheRefreshInfo;
import com.yss.framework.api.cache.assist.BaseCache;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.service.YssServiceFactory;


/**
 * 收支代码缓存
 * @author leeyu
 *
 */
public class IeCache extends BaseCache<Ie> {
	
	private IIeDataService ieDataService = null;
	
	@Override
	protected void loadData(){
		//ieDataService = new IeDataService();
		
		//TASK #556020 分布式缓存改造 edit by sunyanlin 20190114
		ieDataService = YssServiceFactory.getInstance().createService(IIeDataService.class);
		
		CacheData data = ieDataService.updateByTimestamp(getTimestamp());
		this.setTimestamp(data.getTimestamp());
		List<Ie> lstIe = this.cacheData2List(data.getDataList(),Ie.class);
		
		for(Ie ie : lstIe){
			this.mapT.put(ie.getC_FEE_CODE(), ie);
			keyMap.put(ie.getC_FEE_CODE(), ie.getC_FEE_NAME());
			this.idMap.put(ie.getId(), ie);
		}
	}
	
	@Override
	public Ie getCacheByKey(String key) {
		Ie ie = null;
		ie = this.mapT.get(key);
		return ie;
	}

	@Override
	public CacheGroup getCacheGroup() {
		return CacheGroup.IE;
	}
	
	@Override
	public List<String> bindServiceNames() {
		List<String> list = new ArrayList<String>();
		list.add(IIeDataService.class.getSimpleName());
		return list;
	}

	@Override
	public List<Ie> getCacheList() {
		List<Ie> list = new ArrayList<Ie>();
		
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
		return ((Ie)pojo).getC_FEE_CODE();
	}

	@Override
	protected void loadDataByIds(String ids) {
		List<Ie> lstIe = ieDataService.queryByIds(ids);
		if(lstIe!=null && lstIe.size()>0){
			for(Ie ie : lstIe){
				this.mapT.put(ie.getC_FEE_CODE(), ie);
				keyMap.put(ie.getC_FEE_CODE(), ie.getC_FEE_NAME());
				this.idMap.put(ie.getId(), ie);
			}
		}
	}
}
