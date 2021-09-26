package com.yss.uco.elecreco.support.dzdz.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.uco.elecreco.support.dzdz.common.pojo.ElecGroupRela;
import com.yss.uco.elecreco.support.service.IErDzTypeDataService;

public class ErConfigUtil {

	public static List<BasePojo> getElecCfg() throws Exception {
		IErDzTypeDataService dzTypeDataService = YssServiceFactory.getInstance().createService(IErDzTypeDataService.class);
		return dzTypeDataService.getGeneDzType();
	}

	public static ElecGroupRela getElecGroupRelaById(String id) {
		IErDzTypeDataService dzTypeDataService = YssServiceFactory.getInstance().createService(IErDzTypeDataService.class);
		List<BasePojo> dzTypes = dzTypeDataService.getAllDzTypeMap();
		for (BasePojo basePojo : dzTypes) {
			ElecGroupRela item = (ElecGroupRela) basePojo;
			if(item.getC_ELEC_CODE().equalsIgnoreCase(id))
			{
				return item;
			}
		}
		return null;
	}
	
	public static Map<String, String> getResultMap() throws Exception{
		Map<String, String> resultMap = new HashMap<String, String>();
		IErDzTypeDataService dzTypeDataService = YssServiceFactory.getInstance().createService(IErDzTypeDataService.class);
		List<BasePojo> dzTypes = dzTypeDataService.getAllDzTypeMap();
		for (BasePojo basePojo : dzTypes) {
			ElecGroupRela item = (ElecGroupRela) basePojo;
			resultMap.put(item.getC_PARENT_CODE()+"_"+item.getC_RESULT_CODE(), item.getC_ELEC_CODE());
		}
		return resultMap;
	}
}
