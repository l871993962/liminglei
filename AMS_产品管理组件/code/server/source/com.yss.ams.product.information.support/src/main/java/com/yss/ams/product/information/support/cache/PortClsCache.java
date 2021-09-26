package com.yss.ams.product.information.support.cache;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yss.framework.api.cache.CacheRefreshInfo;
import com.yss.framework.api.cache.assist.BaseCache;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.util.YssFun;
import com.yss.ams.product.information.support.modules.aa.portcls.pojo.PortCls;
import com.yss.ams.product.information.support.modules.aa.portcls.service.IClsPortDataService;

/**
 * 组合分级缓存
 * @author leeyu
 *
 */
public class PortClsCache extends BaseCache<PortCls> {	
	
	private IClsPortDataService clsPortDataService = null;
	
	/**
	 * 生成业务复合主键
	 * @param cPortCode组合代码
	 * @param cPortCls分级组合代码
	 * @return
	 */
	public String getKey(String cPortCode, String cPortCls) {
		return String.valueOf(cPortCode + "," + cPortCls + ";");
	}
	
	@Override
	protected void loadData(){
		//clsPortDataService = new PortClsDataService();
		
		//TASK #556020 分布式缓存改造 edit by sunyanlin 20190114
		clsPortDataService = YssServiceFactory.getInstance().createService(IClsPortDataService.class);
		
		CacheData data = clsPortDataService.updateByTimestamp(getTimestamp());
		this.setTimestamp(data.getTimestamp());
		List<PortCls> lstPortcls = this.cacheData2List(data.getDataList(),PortCls.class);
		
		for(PortCls portcls : lstPortcls){
			this.mapT.put(getKey(portcls.getC_PORT_CODE(),portcls.getC_PORT_CLS_CODE()), portcls);
			keyMap.put(portcls.getC_PORT_CLS_CODE(), portcls.getC_PORT_CLS_NAME());
			this.idMap.put(portcls.getId(), portcls);
		}
	}
	
	@Override
	public CacheGroup getCacheGroup() {
		return CacheGroup.PORTCLS;
	}
	
	@Override
	public List<String> bindServiceNames() {
		List<String> list = new ArrayList<String>();
		list.add(IClsPortDataService.class.getSimpleName());
		return list;
	}

	@Override
	public PortCls getCacheByKey(String key) {
		PortCls cls = null;
		
		rwl.readLock();
		try{
			cls = this.mapT.get(key);
		}
		finally{
			rwl.readUnLock();
		}
		
		if(null == cls){
			this.update(new CacheRefreshInfo());
			
			rwl.readLock();
			try{
				cls = this.mapT.get(key);
			}
			finally{
				rwl.readUnLock();
			}
		}
		
		return cls;
	}
	
	/**
	 * 根据组合代码、分级组合代码查询分级组合
	 * @param portCode 组合代码
	 * @param portClsCode 分级代码
	 * @return
	 */
	public PortCls getCacheByPortAndPortCls(String portCode,String portClsCode){
		//PortCls cls = null;
		portCode = portCode == null ? "" : portCode.trim();
		portClsCode = portClsCode == null ? "" : portClsCode.trim();
		String key = this.getKey(portCode, portClsCode);
		//// 更新缓存机制byleeyu20140423
		if (!this.mapT.containsKey(key) && portCode.length() > 0
				&& portClsCode.length() > 0) {
			this.update(new CacheRefreshInfo());
		}
		
		if(this.mapT.containsKey(key)){
			return this.mapT.get(key);
		}
//		java.util.Date start1 = new java.util.Date();
//		rwl.readLock();
//		try{
//			List<PortCls> lstPortcls = this.mapT.getAllValues();
//			for(PortCls portcls : lstPortcls){
//				if(portcls.getC_PORT_CODE().equalsIgnoreCase(portCode) && portcls.getC_PORT_CLS_CODE().equalsIgnoreCase(portClsCode)){
//					cls = portcls;
//					break;
//				}
//			}
//		}
//		finally{
//			rwl.readUnLock();
//		}
//		java.util.Date end1 = new java.util.Date();
//		String s1 = YssFun.formatDate(new java.util.Date(end1.getTime() - start1.getTime()),"HH:mm:ss SSSS");
//		java.util.Date start2 = new java.util.Date();
//		if(null == cls){
//			this.update(new CacheRefreshInfo());
//			
//			rwl.readLock();
//			try{
//				List<PortCls> lstPortcls = this.mapT.getAllValues();
//				for(PortCls portcls : lstPortcls){
//					if(portcls.getC_PORT_CODE().equalsIgnoreCase(portCode) && portcls.getC_PORT_CLS_CODE().equalsIgnoreCase(portClsCode)){
//						cls = portcls;
//						break;
//					}
//				}
//			}
//			finally{
//				rwl.readUnLock();
//			}
//		}
//		java.util.Date end2 = new java.util.Date();
//		String s2 = YssFun.formatDate(new java.util.Date(end2.getTime() - start2.getTime()),"HH:mm:ss SSSS");
		return new PortCls();
	}
	
	/**
	 * 查询组合下的全部分级组合信息
	 * @param portCode 组合代码
	 * @return
	 */
	public List<PortCls> getCashListByPort(String portCode){
		List<PortCls> list = new ArrayList<PortCls>();
		rwl.readLock();
		try{
			List<PortCls> lstPortcls = this.mapT.getAllValues();
			for(PortCls portcls : lstPortcls){
				if(portcls.getC_PORT_CODE().equalsIgnoreCase(portCode)){
					list.add(portcls);
				}
			}
		}
		finally{
			rwl.readUnLock();
		}
		
		return list;
	}
	
	/**
	 * 查询组合下的全部未到期分级组合信息,需要考虑成立日期
	 * @param portCode 组合代码
	 * @return
	 */
	public List<PortCls> getCashListByPortWDQ(String portCode,Date accDate){
		List<PortCls> list = new ArrayList<PortCls>();
		rwl.readLock();
		try{
			List<PortCls> lstPortcls = this.mapT.getAllValues();
			for(PortCls portcls : lstPortcls){
				//BUG #277121 核算级别方案中没有交易类型辅助元素,凭证检查中却提示未找到'交易类型'的科目设置,提示信息有问题；分级组合未考虑分级产品参数的成立日期，成立日前核算产生了【root】错误数据
				//判断是不是分级组合，要考虑分级产品参数里的成立日期
				//if(portcls.getC_PORT_CODE().equalsIgnoreCase(portCode) && !accDate.after(portcls.getD_LIQUID_DATE()) ){
				if(portcls.getC_PORT_CODE().equalsIgnoreCase(portCode) && !accDate.after(portcls.getD_LIQUID_DATE()) && YssFun.dateDiff(portcls.getD_TO_LIST(), accDate) >= 0){
					list.add(portcls);
				}
			}
		}
		finally{
			rwl.readUnLock();
		}
		
		return list;
	}

	@Override
	public List<PortCls> getCacheList() {
		List<PortCls> list = new ArrayList<PortCls>();
		
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
	    PortCls portCls = (PortCls) pojo;
		return getKey(portCls.getC_PORT_CODE(), portCls.getC_PORT_CLS_CODE());
	}

	@Override
	protected void loadDataByIds(String ids) {
		List<PortCls> lstPortcls = clsPortDataService.queryByIds(ids);
		
		for(PortCls portcls : lstPortcls){
			this.mapT.put(getKey(portcls.getC_PORT_CODE(),portcls.getC_PORT_CLS_CODE()), portcls);
			keyMap.put(portcls.getC_PORT_CLS_CODE(), portcls.getC_PORT_CLS_NAME());
			this.idMap.put(portcls.getId(), portcls);
		}		
	}
}
