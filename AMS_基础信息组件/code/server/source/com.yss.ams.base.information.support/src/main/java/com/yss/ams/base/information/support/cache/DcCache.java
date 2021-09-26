package com.yss.ams.base.information.support.cache;


import java.util.ArrayList;
import java.util.List;

import com.yss.ams.base.information.support.sys.dccury.pojo.DcCury;
import com.yss.ams.base.information.support.sys.dccury.service.IDCDataService;
import com.yss.framework.api.cache.CacheRefreshInfo;
import com.yss.framework.api.cache.assist.BaseCache;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.service.YssServiceFactory;


/**
 * 交易币种缓存
 * @author leeyu
 * 
 */
public class DcCache extends BaseCache<DcCury>{

	private IDCDataService dcDataService = null;

	@Override
	protected void loadData() {
		//dcDataService = new DcCuryDataService();
		
		//TASK #556020 分布式缓存改造 edit by sunyanlin 20190114
		dcDataService = YssServiceFactory.getInstance().createService(IDCDataService.class);
		
		CacheData data = dcDataService.updateByTimestamp(getTimestamp());
		this.setTimestamp(data.getTimestamp());
		List<DcCury> lstDc = this.cacheData2List(data.getDataList(), DcCury.class);
		for (DcCury dc : lstDc) {
			this.mapT.put(dc.getC_DC_CODE(), dc);
			keyMap.put(dc.getC_DC_CODE(), dc.getC_DC_NAME());
			this.idMap.put(dc.getId(), dc);
		}
	}

	@Override
	public CacheGroup getCacheGroup() {
		return CacheGroup.DC;
	}
	
	@Override
	public List<String> bindServiceNames() {
		List<String> list = new ArrayList<String>();
		list.add(IDCDataService.class.getSimpleName());
		return list;
	}

	@Override
	public DcCury getCacheByKey(String key) {
		DcCury cury = null;
		cury = this.mapT.get(key);
		return cury;
	}

	@Override
	public List<DcCury> getCacheList() {
		List<DcCury> list = new ArrayList<DcCury>();
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
		return ((DcCury)pojo).getC_DC_CODE();
	}

	@Override
	protected void loadDataByIds(String ids) {
		List<DcCury> lstDc = dcDataService.queryByIds(ids);
		if(lstDc!= null && lstDc.size()>0){
			for (DcCury dc : lstDc) {
				this.mapT.put(dc.getC_DC_CODE(), dc);
				keyMap.put(dc.getC_DC_CODE(), dc.getC_DC_NAME());
				this.idMap.put(dc.getId(), dc);
			}
		}
	}
}
