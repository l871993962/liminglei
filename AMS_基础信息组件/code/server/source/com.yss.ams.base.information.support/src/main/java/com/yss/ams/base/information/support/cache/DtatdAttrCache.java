package com.yss.ams.base.information.support.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.sys.dccury.service.IDCDataService;
import com.yss.ams.base.information.support.sys.dtatdattr.pojo.DtatdAttr;
import com.yss.ams.base.information.support.sys.dtatdattr.service.IDtaTdAttrDataService;
import com.yss.framework.api.cache.assist.BaseCache;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.service.YssServiceFactory;


/**
 * STORY #96237 凭证处理过程中交易属性需要变更从缓存中处理
 * @author neil
 * 
 */
public class DtatdAttrCache extends BaseCache<DtatdAttr> {

	private IDtaTdAttrDataService dateService = null;
	
	@Override
	protected void loadData() {
		 dateService = YssServiceFactory.getInstance().createService(IDtaTdAttrDataService.class);
		CacheData data = dateService.updateByTimestamp(getTimestamp());
		this.setTimestamp(data.getTimestamp());
		List<DtatdAttr> lstDtatdAttr = this.cacheData2List(data.getDataList(), DtatdAttr.class);
		for (DtatdAttr dtatdAttr : lstDtatdAttr) {
			this.mapT.put(dtatdAttr.getC_DTA_CODE(), dtatdAttr);
			keyMap.put(dtatdAttr.getC_DTA_CODE(), dtatdAttr.getC_DTA_NAME());
			//this.idMap.put(dtatdAttr.getId(), dtatdAttr);
		}
	}


	@Override
	public CacheGroup getCacheGroup() {
		return CacheGroup.DTATDATTR;
	}
	
	@Override
	public List<String> bindServiceNames() {
		List<String> list = new ArrayList<String>();
		list.add(IDtaTdAttrDataService.class.getSimpleName());
		return list;
	}

	@Override
	public DtatdAttr getCacheByKey(String key) {
		DtatdAttr dtatdAttr = null;
		dtatdAttr = this.mapT.get(key);
		return dtatdAttr;
	}

	@Override
	public List<DtatdAttr> getCacheList() {
		List<DtatdAttr> list = new ArrayList<DtatdAttr>();
		rwl.readLock();
		try{
			list = this.mapT.getAllValues();
		}
		finally{
			rwl.readUnLock();
		}
		
		return list;
	}

	@Override
	protected String getCacheDataCode(BasePojo pojo) {
		return ((DtatdAttr)pojo).getC_DTA_CODE();
	}

	@Override
	protected void loadDataByIds(String ids) {
		//交易属性 不会改变，一般改变也是有政策性需求产生，所以不需要根据id更新缓存。
	}
	
	@Override
	public HashMap<String, String> getKeyConvertMap() {
		return keyMap;
	}

	
}
