package com.yss.ams.sec.information.support.cache;

import java.util.ArrayList;
import java.util.List;

import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecBase;
import com.yss.ams.sec.information.support.modules.sv.base.service.ISecBaseGPDataService;
import com.yss.ams.sec.information.support.modules.sv.base.service.ISecBaseHGDataService;
import com.yss.ams.sec.information.support.modules.sv.base.service.ISecBaseInfoDataService;
import com.yss.ams.sec.information.support.modules.sv.base.service.ISecBaseJJDataService;
import com.yss.ams.sec.information.support.modules.sv.base.service.ISecBaseQHDataService;
import com.yss.ams.sec.information.support.modules.sv.base.service.ISecBaseQZDataService;
import com.yss.ams.sec.information.support.modules.sv.base.service.ISecBaseZQDataService;
import com.yss.framework.api.cache.assist.BaseCache;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.common.co.CacheDataExtend;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.util.StringUtil;



/**
 * 证券基本信息的缓存
 * @author leeyu
 * 
 */
public class SecBaseCache extends BaseCache<SecBase> { 

	private ISecBaseInfoDataService secBaseInfoDataService = null;

	@Override
	protected void loadData() {
		
		secBaseInfoDataService = YssServiceFactory.getInstance().createService(ISecBaseInfoDataService.class);
		
		/*
		 * STORY #48424 【易方达基金】新增证券品种作应税设置时提醒
		 * add by yangru 20171112
		 */
		
		//外部组件无法访问到UCO的启动项，但又不能进行直接引用，此处先注释，后续若需要修改 请找陈尤龙，王能国，蒋锦商定    TASK #556020 分布式缓存改造  edit by sunyanlin 20190114
		/*String currUserCode = YssContextFactory.getInstance()
				.getAppContext(UcoActivator.class).getUserCode();
		secBaseInfoDataService.setCurrUser(currUserCode); */
		
		//CacheData data = secBaseInfoDataService.updateByTimestamp(this.getTimestamp());
		//查询出的结果集不再转化为json消耗系统内存,查询结果放在cachaDatalist中，在下面的逻辑中直接做转化即可  BUG #227317 系统启动时内存溢出  edit by sunyanlin
		CacheDataExtend data = secBaseInfoDataService.updateByTimestampNew(this.getTimestamp());
		
		this.setTimestamp(data.getTimestamp());
		//List<SecBase> lstSecBase = this.cacheData2List(data.getDataList(),SecBase.class);
		List<BasePojo> lstSecBase = data.getCacheDataList();
		for (BasePojo secpojo : lstSecBase) {
			SecBase sec = (SecBase)secpojo;
			SecBase oldSec = this.mapT.get(sec.getC_SEC_CODE());
			if(oldSec != null){
				this.idMap.remove(oldSec.getId());
			}
			this.mapT.put(sec.getC_SEC_CODE(), sec);
			// 将转换代码放到集合中
			this.keyMap.put(sec.getC_SEC_CODE(), sec.getC_SEC_NAME());
			this.idMap.put(sec.getId(), sec);
		}
	}

	@Override
	public CacheGroup getCacheGroup() {
		return CacheGroup.SECBASE;
	}
	
	@Override
	public List<String> bindServiceNames() {
		List<String> list = new ArrayList<String>();
		list.add(ISecBaseInfoDataService.class.getSimpleName());
		list.add(ISecBaseGPDataService.class.getSimpleName());
		list.add(ISecBaseHGDataService.class.getSimpleName());
		list.add(ISecBaseJJDataService.class.getSimpleName());
		list.add(ISecBaseQHDataService.class.getSimpleName());
		list.add(ISecBaseQZDataService.class.getSimpleName());		
		list.add(ISecBaseZQDataService.class.getSimpleName());
		return list;
	}
	
	public SecBase getSecCacheByCode(String key) {
		SecBase sec = null;
		
		if(!StringUtil.IsNullOrEmptyT(key)) {
			sec =  this.mapT.get(key);
		}
		//BUG #349423::【招商基金】【0831】【性能】系统空闲时还能检测到CPU使用率在70%以上需优化证券缓存获取逻辑
		if(sec == null && !StringUtil.IsNullOrEmptyT(key) && !"NULL".equalsIgnoreCase(key)){
			sec = getSecBaseInfoDataBySecCodeFromDb(key);	
		}
		
		return sec;
	}

	@Override
	public SecBase getCacheByKey(String key) {
		SecBase sec = null;
		//rwl.readLock();
		//try {
			sec = this.mapT.get(key);
		//} finally {
		//	rwl.readUnLock();
		//}
		
//Orlando 20140807 这里实在影响效率，受不了鸟
//		if (null == sec) {
//			this.update(new CacheRefreshInfo());
//
//			rwl.readLock();
//			try {
//				sec = this.mapT.get(key);
//			} finally {
//				rwl.readUnLock();
//			}
//		}
		
		/*
		 * Author : ChenLong
		 * Date   : 2014-08-20
		 * Status : Add
		 * Comment: 缓存不存在数据时 从数据库中取并更新缓存 不再全部更新缓存
		 * */
		if(sec == null && !StringUtil.IsNullOrEmptyT(key) && !"NULL".equalsIgnoreCase(key) && !"*".equalsIgnoreCase(key)){
			sec = getSecBaseInfoDataBySecCodeFromDb(key);
			if(sec != null){
				this.mapT.put(key, sec);
				this.keyMap.put(sec.getC_SEC_CODE(), sec.getC_SEC_NAME());
				
			}			
		}

		return sec;
	}

	@Override
	public List<SecBase> getCacheList() {
		List<SecBase> list = new ArrayList<SecBase>();
		rwl.readLock();
		try {
			list = this.mapT.getAllValues();
		} finally {
			rwl.readUnLock();
		}
		int count = getCountFromDb();
		if(count > list.size()){
			List<String> dbSecCodeList= new ArrayList<String>();
			for (SecBase object : list) {
				dbSecCodeList.add(object.getC_SEC_CODE());
			}
			
			List<SecBase> lstSecBase = getSecBaseListBySecCodeListFromDb(dbSecCodeList);
			list.addAll(lstSecBase);
			for (SecBase sec : lstSecBase) {
				SecBase oldSec = this.mapT.get(sec.getC_SEC_CODE());
				if(oldSec != null){
					this.idMap.remove(oldSec.getId());
				}
				this.mapT.put(sec.getC_SEC_CODE(), sec);
				// 将转换代码放到集合中
				this.keyMap.put(sec.getC_SEC_CODE(), sec.getC_SEC_NAME());
				this.idMap.put(sec.getId(), sec);
			}
		}
		return list;
	}
	/**
	 * 分页取缓存数据
	 * @param begin
	 * @param end
	 * @return
	 */
	@Override
	public List<SecBase> getCacheList(int begin,int end) {
		List<SecBase> list = new ArrayList<SecBase>();
		rwl.readLock();
		try {
			list = this.mapT.getAllValues(begin,end);
		} finally {
			rwl.readUnLock();
		}

		return list;
	}
	
	/**
	 * 按证券品种查相应的证券信息（查多个品种时条件为或者）
	 * 
	 * @param secVars
	 *            　一组证券品种（查以证券品种打头）
	 * @return　满足条件的证券信息
	 * @throws ServiceException
	 */
	public List<SecBase> getDataListBySecVars(String[] secVars)
			throws ServiceException {
		List<SecBase> list = new ArrayList<SecBase>();

		rwl.readLock();
		try {
			List<SecBase> lstSecBase = this.mapT.getAllValues();
			for (SecBase sec : lstSecBase) {
				if (isSecVar(secVars, sec.getC_SEC_VAR_CODE())) // 当满足条件就添加
					list.add(sec);
			}
		} finally {
			rwl.readUnLock();
		}

		return list;
	}

	/**
	 * STORY #39010 【南方基金】【紧急】一键 按照保理事会规则转换代码
	 * add by wgl
	 * @param secVars
	 *            　一组证券品种（查以证券品种打头）
	 * @return　满足条件的证券信息
	 * @throws ServiceException
	 */
	public List<SecBase> getDataListSB(String mktCode,String secVarPre)
			throws ServiceException {
		List<SecBase> list = new ArrayList<SecBase>();

		rwl.readLock();
		try {
			List<SecBase> lstSecBase = this.mapT.getAllValues();
			for (SecBase sec : lstSecBase) {
				if (sec.getC_MKT_CODE().equals(mktCode) && sec.getC_SEC_VAR_CODE().startsWith(secVarPre)) // 当满足条件就添加
					list.add(sec);
			}
		} finally {
			rwl.readUnLock();
		}

		return list;
	}
	
	/**
	 * 判断品种是否
	 * 
	 * @param secVars
	 *            　品种条件
	 * @param secVar
	 *            具体的证券品种
	 * @return　当secVar在secVars中时返回真值(实现条件逻辑为或者)
	 */
	private boolean isSecVar(String[] secVars, String secVar) {
		if (secVars != null && secVars.length > 0) {
			for (String s : secVars) {
				if (secVar.startsWith(s) && s.trim().length() > 0)
					return true; // 当其中的一个条件满足就返回
			}
		}
		return false;
	}
	
	
	private SecBase getSecBaseInfoDataBySecCodeFromDb(String secCode){
		secBaseInfoDataService = YssServiceFactory.getInstance().createService(ISecBaseInfoDataService.class);
		SecBase secBase = (SecBase)secBaseInfoDataService.getSecBaseInfoDataBySecCodeFromDb(secCode);
		return secBase;
	}
	private int getCountFromDb(){
		secBaseInfoDataService = YssServiceFactory.getInstance().createService(ISecBaseInfoDataService.class);
		return secBaseInfoDataService.getCountFromDb();
	}

	private List<SecBase> getSecBaseListBySecCodeListFromDb(List<String> secCode){
		secBaseInfoDataService = YssServiceFactory.getInstance().createService(ISecBaseInfoDataService.class);
		return secBaseInfoDataService.getSecBaseListBySecCodeListFromDb(secCode);
	}
	
	//STORY #40717 缓存刷新增加删除和审核数据时增量刷新 雷建华 20170329
	@Override
	protected String getCacheDataCode(BasePojo pojo) {
		return ((SecBase)pojo).getC_SEC_CODE();
	}

	@Override
	protected void loadDataByIds(String ids) {
		List<SecBase> lstSecBase = secBaseInfoDataService.queryByIds(ids);
		for (SecBase sec : lstSecBase) {
			//判断一下是否内存中已经存在
			SecBase oldSec = this.mapT.get(sec.getC_SEC_CODE());
			if(oldSec!=null){
			    this.idMap.remove(oldSec.getId());
			}
			this.mapT.put(sec.getC_SEC_CODE(), sec);
			// 将转换代码放到集合中
			this.keyMap.put(sec.getC_SEC_CODE(), sec.getC_SEC_NAME());
			this.idMap.put(sec.getId(), sec);
		}		
	}

	/**
	 * 此方法用于根据secCode删除缓存
	 */
	public void deleteData(List<String> secCodes){
		rwl.writeLock();
		try {
			// 如果删除了数据，将时间戳置为空
			for (String secCode : secCodes) {
				if (!StringUtil.IsNullOrEmpty(secCode)) {
					BasePojo pojo = mapT.get(secCode);
					if (pojo!=null) {
						this.mapT.remove(secCode);
						this.keyMap.remove(secCode);
					}
					//idMap.remove(id);
				}
			}
			this.timestamp = newTimeStamp();
		} finally {
			rwl.writeUnLock();
		}
	}
	
}
