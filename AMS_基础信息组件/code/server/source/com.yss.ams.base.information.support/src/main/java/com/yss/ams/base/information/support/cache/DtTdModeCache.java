package com.yss.ams.base.information.support.cache;

import java.util.ArrayList;
import java.util.List;






//import com.yss.ams.base.information.modules.sys.dttdmode.service.impl.DtTdModeDataService;
import com.yss.ams.base.information.support.sys.dttdmode.pojo.DttdMode;
import com.yss.ams.base.information.support.sys.dttdmode.service.IDtTdModeDataService;
//import com.yss.dict.dttdmode.service.impl.DtTdModeDataService;
import com.yss.framework.api.cache.CacheRefreshInfo;
import com.yss.framework.api.cache.assist.BaseCache;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.service.YssServiceFactory;

//import dataservice.comm.pojo.DttdMode;
//import dataservice.service.IDtTdModeDataService;

/**
 * 交易(销售)方式缓存
 * @author leeyu
 *
 */
public class DtTdModeCache extends BaseCache<DttdMode>{

	private IDtTdModeDataService dttdModeDataService = null;
	
	@Override
	protected void loadData(){
		//dttdModeDataService = new DtTdModeDataService();
		
		//TASK #556020 分布式缓存改造 edit by sunyanlin 20190114
		dttdModeDataService = YssServiceFactory.getInstance().createService(IDtTdModeDataService.class);
		
		CacheData data = dttdModeDataService.updateByTimestamp(getTimestamp());
		this.setTimestamp(data.getTimestamp());
		List<DttdMode> lstMode = this.cacheData2List(data.getDataList(),DttdMode.class);
		for(DttdMode dttdMode : lstMode){
			this.mapT.put(dttdMode.getC_DT_CODE(), dttdMode);
			keyMap.put(dttdMode.getC_DT_CODE(), dttdMode.getC_DT_NAME());
			this.idMap.put(dttdMode.getId(), dttdMode);
		}
		
	}
	
	/**
	 * 获取缓存标识符
	 */
	@Override
	public CacheGroup getCacheGroup() {
		return CacheGroup.DTTDMODE;
	}
	
	/**
	 * 绑定IDtTdModeDataService服务名称
	 */
	@Override
	public List<String> bindServiceNames() {
		List<String> list = new ArrayList<String>();
		list.add(IDtTdModeDataService.class.getSimpleName());
		return list;
	}

	/**
	 * 根据id获取缓存
	 */
	@Override
	public DttdMode getCacheByKey(String key) {
		DttdMode mode = null;
		mode = this.mapT.get(key);
		return mode;
	}

	/**
	 * 获取所有缓存数据
	 */
	@Override
	public List<DttdMode> getCacheList() {
		List<DttdMode> list = new ArrayList<DttdMode>();
		
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
	
	/**
	 * 根据pojo获取交易方式代码C_DT_CODE
	 */
	@Override
	protected String getCacheDataCode(BasePojo pojo) {
		return ((DttdMode)pojo).getC_DT_CODE();
	}

	/**
	 * 根据ID加载数据
	 */
	@Override
	protected void loadDataByIds(String ids) {
		List<DttdMode> lstMode = dttdModeDataService.queryByIds(ids);
		if(lstMode!=null && lstMode.size()>0){
			for(DttdMode dttdMode : lstMode){
				this.mapT.put(dttdMode.getC_DT_CODE(), dttdMode);
				keyMap.put(dttdMode.getC_DT_CODE(), dttdMode.getC_DT_NAME());
				this.idMap.put(dttdMode.getId(), dttdMode);
			}	
		}
	}

}
