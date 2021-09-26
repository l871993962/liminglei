package com.yss.ams.base.information.support.cache;

import java.util.ArrayList;
import java.util.List;

//import com.yss.ams.base.information.modules.sys.dvaitem.service.impl.DvaItemDataService;
import com.yss.ams.base.information.support.sys.dvaitem.pojo.DvaItem;
import com.yss.ams.base.information.support.sys.dvaitem.service.IDvaItemDataService;
//import com.yss.dict.dvaitem.service.impl.DvaItemDataService;
import com.yss.framework.api.cache.CacheRefreshInfo;
import com.yss.framework.api.cache.assist.BaseCache;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.util.StringUtil;


/**
 * 核算业务项缓存
 * @author leeyu
 *
 */
public class DvaItemCache extends BaseCache<DvaItem>{
	
	private IDvaItemDataService dvaItemDataService = null;
	
	@Override
	protected void loadData(){
		//dvaItemDataService = new DvaItemDataService();

		//TASK #556020 分布式缓存改造 edit by sunyanlin 20190114
		dvaItemDataService = YssServiceFactory.getInstance().createService(IDvaItemDataService.class);

		CacheData data = dvaItemDataService.updateByTimestamp(getTimestamp());
		this.setTimestamp(data.getTimestamp());
		List<DvaItem> lstDva = this.cacheData2List(data.getDataList(),DvaItem.class);
		for(DvaItem dvaitem : lstDva){
			this.mapT.put(dvaitem.getC_DVA_ITEM_CODE(), dvaitem);
			keyMap.put(dvaitem.getC_DVA_ITEM_CODE(), dvaitem.getC_DVA_ITEM_NAME());
			this.idMap.put(dvaitem.getId(), dvaitem);
		}
	}
	
	@Override
	public CacheGroup getCacheGroup() {
		return CacheGroup.DVAITEM;
	}

	@Override
	public List<String> bindServiceNames() {
		List<String> list = new ArrayList<String>();
		list.add(IDvaItemDataService.class.getSimpleName());
		return list;
	}

	@Override
	public DvaItem getCacheByKey(String key) {
		if (StringUtil.IsNullOrEmptyT(key)) {
			return null;
		}
		DvaItem item = null;
		item = this.mapT.get(key);
		return item;
	}

	@Override
	public List<DvaItem> getCacheList() {
		List<DvaItem> list = new ArrayList<DvaItem>();
		
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
		return ((DvaItem)pojo).getC_DVA_ITEM_CODE();
	}

	/**
	 * 根据id获取缓存数据（核算项目名称、核算业务项字典数据）
	 */
	@Override
	protected void loadDataByIds(String ids) {
		List<DvaItem> lstDva = dvaItemDataService.queryByIds(ids);
		if(lstDva!=null && lstDva.size()>0){
			for(DvaItem dvaitem : lstDva){
				this.mapT.put(dvaitem.getC_DVA_ITEM_CODE(), dvaitem);
				keyMap.put(dvaitem.getC_DVA_ITEM_CODE(), dvaitem.getC_DVA_ITEM_NAME());
				this.idMap.put(dvaitem.getId(), dvaitem);
			}	
		}
			
	}
}
