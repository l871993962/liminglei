package com.yss.ams.base.information.support.cache;
import java.util.ArrayList;
import java.util.List;


//import com.yss.ams.base.information.modules.sys.secvar.service.impl.SecVarDataService;
import com.yss.ams.base.information.support.sys.secvar.pojo.SecVar;
import com.yss.ams.base.information.support.sys.secvar.service.ISecVarDataService;
import com.yss.framework.api.cache.CacheRefreshInfo;
import com.yss.framework.api.cache.assist.BaseCache;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.service.YssServiceFactory;


/**
 * 证券品种缓存
 * @author leeyu
 * 
 */
public class SecVarCache extends BaseCache<SecVar> {

	private ISecVarDataService secvarDataService = null;

	@Override
	protected void loadData() {
		//secvarDataService = new SecVarDataService();
		
		//TASK #556020 分布式缓存改造 edit by sunyanlin 20190114
		secvarDataService = YssServiceFactory.getInstance().createService(ISecVarDataService.class);
		
		CacheData data = secvarDataService.updateByTimestamp(getTimestamp());
		this.setTimestamp(data.getTimestamp());
		List<SecVar> lstSecVar = this.cacheData2List(data.getDataList(),SecVar.class);
		
		for (SecVar secvar : lstSecVar) {
			this.mapT.put(secvar.getC_SEC_VAR_CODE(), secvar);
			keyMap.put(secvar.getC_SEC_VAR_CODE(), secvar.getC_SEC_VAR_NAME());
			this.idMap.put(secvar.getId(), secvar);
		}
	}

	@Override
	public CacheGroup getCacheGroup() {
		return CacheGroup.SECVAR;
	}
	
	@Override
	public List<String> bindServiceNames() {
		List<String> list = new ArrayList<String>();
		list.add(ISecVarDataService.class.getSimpleName());
		return list;
	}

	@Override
	public SecVar getCacheByKey(String key) {
		SecVar secvar = null;
		secvar = this.mapT.get(key);
		return secvar;
	}

	/**
	 *  获取证券品种缓存中所有的数据
	 * 
	 */
	@Override
	public List<SecVar> getCacheList() {
		List<SecVar> list = new ArrayList<SecVar>();
		
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
		return ((SecVar)pojo).getC_SEC_VAR_CODE();
	}

	/**
	 * 根据ID 加载secvar 证券品种pojo数据 或者证券品种名称
	 */
	@Override
	protected void loadDataByIds(String ids) {
		List<SecVar> lstSecVar = secvarDataService.queryByIds(ids);
		
		for (SecVar secvar : lstSecVar) {
			this.mapT.put(secvar.getC_SEC_VAR_CODE(), secvar);
			keyMap.put(secvar.getC_SEC_VAR_CODE(), secvar.getC_SEC_VAR_NAME());
			this.idMap.put(secvar.getId(), secvar);
		}		
	}
}
