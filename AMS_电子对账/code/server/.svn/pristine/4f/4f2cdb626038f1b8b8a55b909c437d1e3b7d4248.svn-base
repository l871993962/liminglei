package com.yss.uco.elecreco.er.reverse.compare.common;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yss.uco.elecreco.er.reverse.compare.service.impl.DataCompareService;
import com.yss.uco.elecreco.er.reverse.map.zbmap.pojo.ZbMap;
import com.yss.uco.elecreco.er.reverse.map.zbmap.service.IZbMapService;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.util.StringUtil;

public class ZbMapManager {
	/**
	 * 一层key:托管行代码
	 *二层 key:指标代码,查询映射关系时，区分优先级,组合>托管行>公共
	 */
	private Map<String,Map<String,ZbMap>> zbMaps = new HashMap<String, Map<String,ZbMap>>();
	
	private DataCompareService dataCompareService = null;
	
	public ZbMapManager(DataCompareService dataCompareService)
	{
		this.dataCompareService = dataCompareService;
	}
	
	/**
	 * 初始化指标映射
	 * @param portCode
	 * @param fileType
	 * @param tghCode
	 * @return 
	 * @throws Exception
	 */
	public void initZbMaps(String portCode, String fileType,String tghCode) throws Exception {
		Map<String,ZbMap> map = new HashMap<String, ZbMap>();
		IZbMapService zbMapService = YssServiceFactory.getInstance().createService(IZbMapService.class);
		List<ZbMap> dataList = zbMapService.getCompareZbItems(portCode, tghCode, fileType);
		if(dataList == null)
		{
			return ;
		}
		//级别高的覆盖级别低的
		String code = "";
		for(ZbMap zb : dataList)
		{
			code = zb.getC_ZB_CODE();
			if(code != null && !StringUtil.IsNullOrEmptyT(code))
			{
				map.put(code, zb);
			}
		}
		this.zbMaps.put(tghCode, map);
	}
	
	public void initZbMaps(String portCode, String fileType,String[] tghCodes) throws Exception
	{
		if(tghCodes == null)
		{
			return;
		}
		for(String tghCode : tghCodes)
		{
			initZbMaps(portCode, fileType, tghCode);
		}
	}
	/**
	 * 通过本方指标代码获取指标映射，支持多个托管行
	 * @param zbCode
	 * @return key:托管行代码
	 */
	public Map<String,ZbMap> getZbMapsByInnerZbCode(String zbCode)
	{
		Map<String,ZbMap> result = new HashMap<String, ZbMap>();
		Set<String> tghCodes = this.zbMaps.keySet();
		for(String tghCode : tghCodes)
		{
			Map<String, ZbMap> map = zbMaps.get(tghCode);
			if(map.containsKey(zbCode))
			{
				result.put(tghCode,map.get(zbCode));
			}
		}
		return result;
	}
	/**
	 * 仅适用于单个托管行
	 * @param zbCode
	 * @return
	 */
	public String getOutZbCodeByInnerZbCode(String zbCode)
	{
		Set<String> tghCodes = this.zbMaps.keySet();
		for(String tghCode : tghCodes)
		{
			Map<String, ZbMap> map = zbMaps.get(tghCode);
			if(map.containsKey(zbCode))
			{
				return map.get(zbCode).getC_ZB_CODE_OUT();
			}
		}
		return null;
	}
	
	/**
	 * 获取本方设置过指标映射的指标代码
	 * @return
	 */
	public Set<String> getInnerZbCodes()
	{
		Set<String> set = new HashSet<String>();
		Set<String> tghCodes = this.zbMaps.keySet();
		for (String tghCode : tghCodes) {
			Map<String, ZbMap> map = this.zbMaps.get(tghCode);
			set.addAll(map.keySet());
		}
		return set;
	}
}
