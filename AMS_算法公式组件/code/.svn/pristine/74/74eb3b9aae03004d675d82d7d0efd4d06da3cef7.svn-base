package com.yss.ams.visaval.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.yss.ams.visaval.support.context.AvalAPIContext;
import com.yss.ams.visaval.support.service.IVisKeyWordParamService;
import com.yss.framework.api.util.JsonUtil;

public class KeyWordParamService implements IVisKeyWordParamService{

	@Override
	public String getKeyWordParamValues(String keyWord) {
		return JsonUtil.toString(AvalAPIContext.getInstance().getKeyWordParamMapParamMap(keyWord));
	}

	@Override
	public String getAllKeyWord() {
		Map<String, Map<String, String>> keyWordParamMap = AvalAPIContext.getInstance().getKeyWordParamMap();
		List<String> list = new ArrayList<String>();
		Iterator<String> ite = keyWordParamMap.keySet().iterator();
		while (ite.hasNext()) {
			String str = (String) ite.next();
			list.add(str);
		}
		return JsonUtil.toString(list);
	}

}
