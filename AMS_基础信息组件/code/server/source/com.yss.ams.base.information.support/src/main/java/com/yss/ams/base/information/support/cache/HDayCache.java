package com.yss.ams.base.information.support.cache;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.bi.hdaygroup.pojo.HdayGroup;
import com.yss.ams.base.information.support.bi.hdaygroup.service.IHDayDataService;
import com.yss.ams.base.information.support.bi.mkt.service.IMktHDayDataService;
import com.yss.framework.api.cache.CacheRefreshInfo;
import com.yss.framework.api.cache.IKVPairCache;
import com.yss.framework.api.cache.assist.BaseCache;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.cache.assist.FompShellCacheFactory;
import com.yss.framework.api.cache.assist.TypeReference;
import com.yss.framework.api.cache.ehcache.EHCache;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.exception.YssException;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.service.YssServiceFactory;

/**
 * 节假日缓存
 * @author leeyu
 *
 */
public class HDayCache extends BaseCache<HdayGroup>{
	/**
	 * 保存市场下的节假日
	 */
	//private static EHCache<String, HashMap<Integer, List<Date>>> mapMktHDay = new EHCache<String, HashMap<Integer, List<Date>>>("HdayGroupDate");
	private static IKVPairCache<String, HashMap<Integer, List<Date>>> mapMktHDay = 
            
            FompShellCacheFactory.getCache("HdayGroupDate", new TypeReference<HashMap<Integer, List<Date>>>() {
            });
	
	private IMktHDayDataService mktHDayDataService = null;
	
	private IHDayDataService hDayDataService = null;
	
	@Override
	protected void loadData() {
		try {
			//mktHDayDataService = new MktHDayDataService();
			//hDayDataService = new HDayDataService();
			
			//TASK #556020 分布式缓存改造 edit by sunyanlin 20190114
			mktHDayDataService = YssServiceFactory.getInstance().createService(IMktHDayDataService.class);
			hDayDataService = YssServiceFactory.getInstance().createService(IHDayDataService.class);
			
			CacheData data = hDayDataService.updateByTimestamp(getTimestamp());
			this.setTimestamp(data.getTimestamp());
			List<HdayGroup> lstHDayGroup = this.cacheData2List(data.getDataList(),HdayGroup.class);
			for (HdayGroup group : lstHDayGroup) {
				this.mapT.put(group.getC_HDAY_CODE(), group);
				this.keyMap.put(group.getC_HDAY_CODE(), group.getC_HDAY_NAME());
				this.idMap.put(group.getId(), group);
			}
			
			//每次都是重新加载
			mapMktHDay.clear();
			
			for(HdayGroup group : this.mapT.getAllValues()){
				HashMap<String, HashMap<Integer, List<Date>>> mapTemp = mktHDayDataService
						.getHolidays(group.getC_HDAY_CODE());
				if (mapTemp != null) {
					for (String key : mapTemp.keySet()) {
						mapMktHDay.put(key, mapTemp.get(key));
					}
				}
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(),e);
		}
	}
	
	@Override
	public HdayGroup getCacheByKey(String key) {
		HdayGroup group = null;
		group = this.mapT.get(key);
		return group;
	}

	@Override
	public CacheGroup getCacheGroup() {
		return CacheGroup.HDAY;
	}

	@Override
	public List<String> bindServiceNames() {
		List<String> list = new ArrayList<String>();
		list.add(IMktHDayDataService.class.getSimpleName());
		return list;
	}

	@Override
	public List<HdayGroup> getCacheList() {
		List<HdayGroup> list = new ArrayList<HdayGroup>();

		rwl.readLock();
		try {
			list = this.mapT.getAllValues();
		} finally {
			rwl.readUnLock();
		}

		return list;
	}

	/**
	 * 获取节假日群下的全部节假日
	 * 
	 * @param hdayCode
	 *            节假日代码
	 * @return
	 * @throws YssException 
	 */
	public HashMap<Integer, List<Date>> getHDayGroupAllDate(String hdayCode) {
		HashMap<Integer, List<Date>> map = new HashMap<Integer, List<Date>>();

		rwl.readLock();
		try {
			map = mapMktHDay.get(hdayCode);
			if(null == map
					|| map.isEmpty())
			{
				logger.log("加载节假日,节假日群:"+hdayCode);
				/*HashMap<String, HashMap<Integer, List<Date>>> mapTemp = new MktHDayDataService()
						.getHolidays(hdayCode);
				*/
				
				//TASK #556020 分布式缓存改造 edit by sunyanlin 20190108
				mktHDayDataService = YssServiceFactory.getInstance().createService(
						IMktHDayDataService.class);
				HashMap<String, HashMap<Integer, List<Date>>> mapTemp = mktHDayDataService.getHolidays(hdayCode);
				
				
				if(null != mapTemp)
				{
					map = mapTemp.get(hdayCode);
					for (String key : mapTemp.keySet()) {
						mapMktHDay.put(key, mapTemp.get(key));
					}
				}
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(),e);
		} finally {
			rwl.readUnLock();
		}

		return map;
	}

	//STORY #40717 缓存刷新增加删除和审核数据时增量刷新 雷建华 20170329
	@Override
	protected String getCacheDataCode(BasePojo pojo) {
		return ((HdayGroup)pojo).getC_HDAY_CODE();
	}

	@Override
	protected void loadDataByIds(String ids) {
		try {
			List<HdayGroup> lstHDayGroup = hDayDataService.queryByIds(ids);
			if(lstHDayGroup!=null && lstHDayGroup.size()>0){
				for (HdayGroup group : lstHDayGroup) {
					this.mapT.put(group.getC_HDAY_CODE(), group);
					this.keyMap.put(group.getC_HDAY_CODE(), group.getC_HDAY_NAME());
					this.idMap.put(group.getId(), group);
				}
			}
			
			//每次都是重新加载
			mapMktHDay.clear();
			
			for(HdayGroup group : this.mapT.getAllValues()){
				HashMap<String, HashMap<Integer, List<Date>>> mapTemp = mktHDayDataService
						.getHolidays(group.getC_HDAY_CODE());
				if (mapTemp != null) {
					for (String key : mapTemp.keySet()) {
						mapMktHDay.put(key, mapTemp.get(key));
					}
				}
			}		
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(),e);
		}
	}
}
