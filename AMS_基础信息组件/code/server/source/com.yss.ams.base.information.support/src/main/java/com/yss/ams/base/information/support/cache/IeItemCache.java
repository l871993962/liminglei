package com.yss.ams.base.information.support.cache;

import java.util.ArrayList;
import java.util.List;



//import com.yss.ams.base.information.modules.sys.ieItem.service.impl.IeItemDataService;
import com.yss.ams.base.information.support.sys.ieItem.pojo.IeItem;
import com.yss.ams.base.information.support.sys.ieItem.service.IIeItemDataService;
import com.yss.framework.api.cache.CacheRefreshInfo;
import com.yss.framework.api.cache.assist.BaseCache;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.service.YssServiceFactory;



/**
 * 收支项目缓存
 * @author leeyu
 * 
 */
public class IeItemCache extends BaseCache<IeItem> {

	private IIeItemDataService ieItemDataService = null;

	@Override
	protected void loadData() {

		//ieItemDataService = new IeItemDataService();
		
		//TASK #556020 分布式缓存改造 edit by sunyanlin 20190114
		ieItemDataService = YssServiceFactory.getInstance().createService(IIeItemDataService.class);
		
		CacheData data = ieItemDataService.updateByTimestamp(getTimestamp());
		this.setTimestamp(data.getTimestamp());
		List<IeItem> lstIe = this.cacheData2List(data.getDataList(),IeItem.class);
		for (IeItem ie : lstIe) {
			this.mapT.put(ie.getC_IE_CODE(), ie);
			keyMap.put(ie.getC_IE_CODE(), ie.getC_IE_NAME());
			this.idMap.put(ie.getId(), ie);
		}
	}

	/**
	 * 获取收支项目缓存标识符
	 */
	@Override
	public CacheGroup getCacheGroup() {
		return CacheGroup.IEITEM;
	}

	/**
	 * 绑定收支结转数据字典service
	 */
	@Override
	public List<String> bindServiceNames() {
		List<String> list = new ArrayList<String>();
		list.add(IIeItemDataService.class.getSimpleName());
		return list;
	}

	/**
	 * 根据key 获取收支项目缓存中的数据
	 * @return IeItem收支项目字典pojo
	 */
	@Override
	public IeItem getCacheByKey(String key) {
		IeItem item = null;
		item = this.mapT.get(key);
		return item;
	}

	/**
	 *  获取所有收支项目缓存中的数据
	 * @return IeItem收支项目字典pojo集合
	 */
	@Override
	public List<IeItem> getCacheList() {
		List<IeItem> list = new ArrayList<IeItem>();
		
		rwl.readLock();
		try{
		list = this.mapT.getAllValues();
		}
		finally{
			rwl.readUnLock();
		}
		
		return list;
	}

	/**
	 * STORY #40717 缓存刷新增加删除和审核数据时增量刷新 雷建华 20170329
	 */
	//STORY #40717 缓存刷新增加删除和审核数据时增量刷新 雷建华 20170329
	@Override
	protected String getCacheDataCode(BasePojo pojo) {
		return ((IeItem)pojo).getC_IE_CODE();
	}

	/**
	 * 根据ID获取 收支项目代码和收支项目名称 Map集合，以及收支项目代码和收支项目字典IeItem对象集合
	 */
	@Override
	protected void loadDataByIds(String ids) {
		List<IeItem> lstIe = ieItemDataService.queryByIds(ids);
		if(lstIe!=null && lstIe.size()>0){
			for (IeItem ie : lstIe) {
				this.mapT.put(ie.getC_IE_CODE(), ie);
				keyMap.put(ie.getC_IE_CODE(), ie.getC_IE_NAME());
				this.idMap.put(ie.getId(), ie);
			}		
		}
	}
}
