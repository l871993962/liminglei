package com.yss.ams.product.information.support.cache;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.yss.ams.base.information.support.sys.acctype.pojo.AccType;
import com.yss.ams.base.information.support.sys.acctype.service.IAccTypeDataService;
import com.yss.ams.product.information.support.modules.aa.portcustom.service.IPortCustomDataService;
import com.yss.framework.api.common.co.Port;
import com.yss.ams.product.information.support.modules.ab.port.pojo.PortCacheData;
import com.yss.ams.product.information.support.modules.ab.port.service.IPortDataService;
import com.yss.fast.cache.CacheManager;
import com.yss.framework.api.cache.CacheRefreshInfo;
import com.yss.framework.api.cache.assist.BaseCache;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.cache.assist.CacheOper;
import com.yss.framework.api.common.YssConstant;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.api.util.YssFun;
import com.yss.framework.db.DbPoolFactory;


/**
 * 组合的缓存
 * 
 * @author leeyu
 * 
 */
public class PortCache extends BaseCache<Port> {

	//IPortDataService 针对估值的缓存,IPortDataService引用不要用产品组件中的服务类
	private IPortDataService portDataService = null;
	
	private IPortCustomDataService portCustomDataService = null;

	private IAccTypeDataService assTypeDataService = null;

	/**
	 * 组合集合
	 */
	private static ConcurrentHashMap<String, AccType> asstypeMap = new ConcurrentHashMap<String, AccType>();
	
	/**
	 * 缓存 根据不同trCode时查询到的组合集合，
	 * 供其他查询组件在PortDataService中查询组合时直接从缓存里面取，提升效率。
	 * 
	 * trCode为null或空时，设置key为：NULL_TRCODE
	 * */
	private static  ConcurrentHashMap<String, HashMap<String, Port>> cachedTrCodeMap = new ConcurrentHashMap<String, HashMap<String, Port>>();


	/**
	 * 组合集合（资产类型）
	 */
	private static HashMap<String,HashMap<String, Port>> portCacheMap_ZCLX = new HashMap<String,HashMap<String, Port>>();
	
	/**
	 * 是否已经初始化加载完成
	 */
	private static boolean hasBeenLoad = false;

	public HashMap<String,HashMap<String, Port>> getPortCacheMap_ZCLX(){
		return portCacheMap_ZCLX;
	}

	/**
	 * 加载缓存
	 */
	@Override
	protected void loadData() {
		 portDataService = YssServiceFactory.getInstance().createService(
				IPortDataService.class);
		// modified by HeLiang 2017-06-09 产品信息组件拆分
		// AccTypeDataService这个实现服务已迁移到com.yss.ams.base.information项目，故应该用工厂方法实例化
		// assTypeDataService = new AccTypeDataService();
		assTypeDataService = YssServiceFactory.getInstance().createService(
				IAccTypeDataService.class);

		CacheData data = portDataService.updateByTimestamp(getTimestamp());
		this.setTimestamp(data.getTimestamp());
		List<Port> portList = this.cacheData2List(data.getDataList(),
				Port.class);
		for (Port port : portList) {
			this.mapT.put(port.getC_PORT_CODE(), port);
			this.keyMap.put(port.getC_PORT_CODE(), port.getC_PORT_NAME());
			this.idMap.put(port.getId(), port);
		}
		// 添加资产树形结构,用于在前台展示byleeyu20140320
		List<AccType> assList = assTypeDataService.getDataList();
		asstypeMap.clear();
		for (AccType accType : assList) {
			asstypeMap.put(accType.getC_DAT_CODE(), accType);
		}
		//加载产品树形结构
		loadAssetTree();
	}
	
	private boolean newportDataService (){
		try{
			portDataService = YssServiceFactory.getInstance().createService(
					IPortDataService.class);
		} catch (Exception ex){
			logger.log(getCacheGroup()+"缓存启动失败：portDataService未能成功创建", ex);
			return false;
		}
		return true;
	}

	/**
	 * 获取缓存标识
	 * 
	 * @return
	 */
	@Override
	public CacheGroup getCacheGroup() {
		return CacheGroup.PORT;
	}

	@Override
	public List<String> bindServiceNames() {
		List<String> list = new ArrayList<String>();
		list.add(IPortDataService.class.getSimpleName());
		return list;
	}

	@Override
	public Port getCacheByKey(String key) {
		Port port = null;
		port = this.mapT.get(key);
		// 二次加载
		if (port == null && !StringUtil.IsNullOrEmptyT(key) && !"NULL".equalsIgnoreCase(key)) {
			if(portDataService==null) {
				portDataService = YssServiceFactory.getInstance().createService(
						IPortDataService.class);
			}
			port = portDataService.getPortInfo(key);
			//BUG #328137 申万宏源证券-新成立的产品复制创建，保存的时候系统卡顿半小时（0430-0717）
			/*if(port != null && !StringUtil.IsNullOrEmptyT(key) && !"NULL".equalsIgnoreCase(key)) {
				rwl.writeLock();
				try {
					this.mapT.put(port.getC_PORT_CODE(), port);
					this.keyMap.put(port.getC_PORT_CODE(), port.getC_PORT_NAME());
					this.idMap.put(port.getId(), port);
				} finally {
					rwl.writeUnLock();
				}
			}*/
		}
		return port;
	}

	/**
	 * 根据组合代码与成立日期获取组合信息
	 * 
	 * @param portCode
	 *            组合代码
	 * @param buildDate
	 *            成立日期
	 * @return
	 */
	public Port getCacheByPortAndBuildDate(String portCode, Date buildDate) {
		Port port1 = null;

		rwl.readLock();
		try {
			List<Port> portList = this.mapT.getAllValues();

			for (Port port : portList) {
				if (port.getC_PORT_CODE().equalsIgnoreCase(portCode)
						&& port.getD_BUILD().equalsIgnoreCase(
								YssFun.formatDate(buildDate))) {
					port1 = port;
					break;
				}
			}
		} finally {
			rwl.readUnLock();
		}

		// 二次加载
		if (port1 == null) {

			this.update(new CacheRefreshInfo());

			rwl.readLock();
			try {
				List<Port> portList = this.mapT.getAllValues();

				for (Port port : portList) {
					if (port.getC_PORT_CODE().equalsIgnoreCase(portCode)
							&& port.getD_BUILD().equalsIgnoreCase(
									YssFun.formatDate(buildDate))) {
						port1 = port;
						break;
					}
				}
			} finally {
				rwl.readUnLock();
			}
		}

		return port1;
	}

	@Override
	public List<Port> getCacheList() {
		List<Port> list = new ArrayList<Port>();
		rwl.readLock();
		try {
			list = this.mapT.getAllValues();
		} finally {
			rwl.readUnLock();
		}

		return list;
	}

	public HashMap<String,Port> getPortByRight(List<String> rights) {
		HashMap<String,Port> map = new HashMap<String,Port>();
		rwl.readLock();
		try {
			for (String portCode : rights) {
				Port port = this.mapT.get(portCode);
				if (port != null) {
					map.put(portCode, port);
				}
			}
			
		} finally {
			rwl.readUnLock();
		}
		return map;
	}

	public List<AccType> getAccType(List<String> accType) {
		List<AccType> list = new ArrayList<AccType>();
		HashMap<Integer, AccType> map = new HashMap<Integer, AccType>();
		try {
			for (String acctype : accType) {
				if (asstypeMap != null && asstypeMap.containsKey(acctype)) {
					AccType acc = asstypeMap.get(acctype);
					map.put(acc.getN_ORDER(), acc);
				}
			}
			Object[] arr = map.keySet().toArray();
			Arrays.sort(arr);
			for (Object obj : arr) {
				list.add(map.get(obj));
			}
		} catch (Exception ex) {
		} finally {
			map.clear();
		}
		return list;
	}

	//STORY #40717 缓存刷新增加删除和审核数据时增量刷新 雷建华 20170329
	@Override
	protected String getCacheDataCode(BasePojo pojo) {
		return ((Port)pojo).getC_PORT_CODE();
	}

	@Override
	protected void loadDataByIds(String ids) {
		List<Port> portList = portDataService.queryByIds(ids);
		for (Port port : portList) {
			this.mapT.put(port.getC_PORT_CODE(), port);
			this.keyMap.put(port.getC_PORT_CODE(), port.getC_PORT_NAME());
			this.idMap.put(port.getId(), port);
		}
//		// 添加资产树形结构,用于在前台展示byleeyu20140320
//		List<AccType> assList = assTypeDataService.getDataList();
//		asstypeMap.clear();
//		for (AccType accType : assList) {
//			asstypeMap.put(accType.getC_DAT_CODE(), accType);
//		}		
	}
	
	/**
	 * 根据id更新缓存
	 */
	public void updateByIds(String ids) {
		this.loadDataByIds(ids);
	} 
	
	@Override
	public void update(CacheRefreshInfo info) {
		rwl.writeLock();
		try {
			// 如果删除了数据，将时间戳置为空
			// 当时间戳为空时取所有数据
			if (info.getOper() == CacheOper.DEL) {
				if(info.getIdList()!=null && info.getIdList().size()>0){
					for(String id : info.getIdList()){
						BasePojo pojo = idMap.get(id);
						if(pojo!=null){
							String key = getCacheDataCode(pojo);
							if(!StringUtil.IsNullOrEmpty(key)){
								this.mapT.remove(key);
								this.keyMap.remove(key);
							}
							idMap.remove(id);
						}
						
					}
				}
//				// 添加资产树形结构,用于在前台展示byleeyu20140320
//				List<AccType> assList = assTypeDataService.getDataList();
//				asstypeMap.clear();
//				for (AccType accType : assList) {
//					asstypeMap.put(accType.getC_DAT_CODE(), accType);
//				}
//				this.mapT.clear();
				this.timestamp = newTimeStamp();
				
			}else if(info.getOper() == CacheOper.AUDIT) {
				try {
					if(info.getIdList()!=null && info.getIdList().size()>0){
						loadDataByIds(StringUtil.join(info.getIdList(), ","));
					}
				} catch (Exception e) {
					logger.log("缓存" + info.getGroups()[0] + "更新失败：" + e.getMessage(), e);
				}
			}else{
				loadData();
			}
			
			//刷新默认树形结构数据
			loadAssetTree();
			
			//BUG #252368 【工银瑞信】修改产品基本信息后，缓存没有同步刷新过来
			//临时通过如下方式实现PORTCACHE的增量刷新缓存，后续STORY #69535 缓存优化，idMap导致内存对象过多中处理
			//STORY #89381 缓存优化
			//CacheManager.getInstance().refreshByIds(CacheGroup.PORT.toString(), info.getIdList());
		} finally {
			rwl.writeUnLock();
		}
	}
	
	/**
	 * 刷新产品树形结构
	 */
	public void loadAssetTree(){
		if(portDataService == null){
			portDataService = YssServiceFactory.getInstance().createService(IPortDataService.class);
		}
		/*
		 * 此处将trCode为空时查询的组合信息缓存起来，供其他用到组合的组件使用，
		 * 比如说任务调度界面在查询组合时，就是在trCode为null的情况下查询组合信息，
		 * 为了区分 trCode的多种情况，这里将 trCode为空时虚造一个key：NULL_TRCODE，用来对应查询的组合信息
		 * */
		HashMap<String/*portCode*/, Port> cachedPortMap = portDataService.getPortDataMapWithNullTrCode();
		//synchronized(PortCache.getCachedTrCodeMap()){
			getCachedTrCodeMap().put("NULL_TRCODE", cachedPortMap);
		//}
		
		
		//产品树形结构已经加载后，不再重复加载
		if(hasBeenLoad && !portCacheMap_ZCLX.isEmpty()){
			return;
		}
				
		IPortCustomDataService portCustomDataService = YssServiceFactory.getInstance().createService(IPortCustomDataService.class);
		//获取系统自带以及自定义的资产类型code
		List<String> assetTypeList = portCustomDataService.getAssetTypeOnlyCode();
		//遍历所有资产类型，将不同资产类型的组合存到portCacheMap_ZCLX集合中，并放到缓存中
		for(int i = 0; i<assetTypeList.size() ; i++){
			refreshAssetTreeByTrCode(assetTypeList.get(i));
		}
		
		hasBeenLoad = true;
	}
	
	/**
	 * 根据资产树形代码更新树形结构
	 * @param trCode
	 */
	public void refreshAssetTreeByTrCode(String trCode){
		if(portDataService == null){
			portDataService = YssServiceFactory.getInstance().createService(IPortDataService.class);
		}
		
		HashMap<String/*portCode*/, Port> cachedPortMap = null;
		if(StringUtil.IsNullOrEmptyT(trCode) || "ASS".equals(trCode)){
			cachedPortMap = portDataService.getPortDataMapWithNullTrCode();
			//synchronized(PortCache.getCachedTrCodeMap()){
				getCachedTrCodeMap().put("NULL_TRCODE", cachedPortMap);
			//}
		}
		
		if("ASS".equals(trCode)){//ASS代表加载所有的组合
			synchronized(PortCache.portCacheMap_ZCLX){
				portCacheMap_ZCLX.put("NULL_TRCODE", cachedPortMap);
			}
		}
		//估值树形结构NSPL特殊处理，这里不加载到缓存中，使用的时候去查，否则其他产品线查询报错
		else if(!"NSPL".equals(trCode)){
			//剩下的key直接存资产类型code
			PortCacheData portCacheData = portDataService.getPortDataMapByTrCode(trCode);
			synchronized(PortCache.portCacheMap_ZCLX){
				portCacheMap_ZCLX.put(trCode, portCacheData.getPortData());
			}
		}
	}

	public static ConcurrentHashMap<String/*trCode*/, HashMap<String, Port>> getCachedTrCodeMap() {
		return cachedTrCodeMap;
	}

	public static void setCachedTrCodeMap(ConcurrentHashMap<String/*trCode*/, HashMap<String, Port>> cachedTrCodeMap) {
		PortCache.cachedTrCodeMap = cachedTrCodeMap;
	}
}
