package com.yss.ams.base.information.support.cache;

import java.util.ArrayList;
import java.util.List;

import com.yss.ams.base.information.support.bi.org.pojo.Org;
import com.yss.ams.base.information.support.bi.org.service.IOrgDataService;
import com.yss.framework.api.cache.assist.BaseCache;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.service.YssServiceFactory;


/**
 * 机构信息缓存
 * 
 * @author liuxiang
 * @date 2016年2月26日 STORY #28246 【自动化款指令】及【划款指令管理】模块界面问题优化
 */
public class OrgCache extends BaseCache<Org> {

	IOrgDataService orgDataService = null;

	@Override
	protected void loadData() {
		//orgDataService = new OrgDataService();
		
		//TASK #556020 分布式缓存改造 edit by sunyanlin 20190114
		orgDataService = YssServiceFactory.getInstance().createService(IOrgDataService.class);
		
		CacheData data = orgDataService.updateByTimestamp(getTimestamp());
		this.setTimestamp(data.getTimestamp());
		List<Org> lstOrg = this.cacheData2List(data.getDataList(), Org.class);
		for (Org org : lstOrg) {
			this.mapT.put(org.getC_ORG_CODE(), org);
			keyMap.put(org.getC_ORG_CODE(), org.getC_ORG_NAME());
			this.idMap.put(org.getId(), org);
		}
	}

	@Override
	public CacheGroup getCacheGroup() {
		return CacheGroup.ORG;
	}

	@Override
	public List<String> bindServiceNames() {
		List<String> list = new ArrayList<String>();
		list.add(IOrgDataService.class.getSimpleName());
		return list;
	}

	@Override
	public List<Org> getCacheList() {
		List<Org> list = new ArrayList<Org>();

		rwl.readLock();
		try {
			list = this.mapT.getAllValues();
		} finally {
			rwl.readUnLock();
		}

		return list;
	}

	@Override
	public Org getCacheByKey(String key) {
		return this.mapT.get(key);
	}

	/**
	 * 更新一条数据到缓存
	 * 
	 * @author liuxiang
	 * @date 2016年2月26日
	 * @param key
	 */
	private void update(String key) {
		rwl.writeLock();
		try {
			orgDataService = YssServiceFactory.getInstance().createService(
					IOrgDataService.class);
			Org org = orgDataService.getDataByCode(key);
			if (org != null) {
				this.mapT.put(org.getC_ORG_CODE(), org);
			}
		} finally {
			rwl.writeUnLock();
		}
	}

	//STORY #40717 缓存刷新增加删除和审核数据时增量刷新 雷建华 20170329
	@Override
	protected String getCacheDataCode(BasePojo pojo) {
		return ((Org)pojo).getC_ORG_CODE();
	}

	@Override
	protected void loadDataByIds(String ids) {
		List<Org> lstOrg = orgDataService.queryByIds(ids);
		for (Org org : lstOrg) {
			this.mapT.put(org.getC_ORG_CODE(), org);
			keyMap.put(org.getC_ORG_CODE(), org.getC_ORG_NAME());
			this.idMap.put(org.getId(), org);
		}		
	}
}
