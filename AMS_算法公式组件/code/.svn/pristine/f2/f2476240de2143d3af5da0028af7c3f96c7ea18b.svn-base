package com.yss.ams.visaval.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.yss.ams.visaval.support.api.pojo.ParamFromSql;
import com.yss.ams.visaval.support.context.AvalAPIContext;
import com.yss.ams.visaval.support.service.IVisAlgoDataService;
import com.yss.framework.api.service.YssServiceFactory;

public class Param_From_Sql_Init {

	private Map<String, List<ParamFromSql>> map = new HashMap<String, List<ParamFromSql>>();

	private static Param_From_Sql_Init instance = null;

	private Param_From_Sql_Init() {
		initData();
	}

	public static Param_From_Sql_Init getInstance() {

		if (null == instance) {
			synchronized (Param_From_Sql_Init.class) {
				if (null == instance) {
					instance = new Param_From_Sql_Init();
				}
			}
		}

		return instance;
	}

	private void initData() {
		Map<String, String> sqlMap = AvalAPIContext.getInstance().getParamsFromSqlMap();
		Iterator<String> ite = sqlMap.keySet().iterator();
		while (ite.hasNext()) {
			String key = (String) ite.next();
			IVisAlgoDataService service = YssServiceFactory.getInstance().createService(IVisAlgoDataService.class);
			List<ParamFromSql> listParamFromSqls = service.getParamFromSql(sqlMap.get(key));
			map.put(key, listParamFromSqls);
		}
	}
	
	public Map<String, List<ParamFromSql>> getData(){
		return map;
	}
}
