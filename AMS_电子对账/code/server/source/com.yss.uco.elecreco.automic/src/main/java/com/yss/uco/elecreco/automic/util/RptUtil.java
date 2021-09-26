package com.yss.uco.elecreco.automic.util;

import java.util.HashMap;
import java.util.Map;

public class RptUtil {
	private static Map<String, String> RPT = new HashMap<String, String>();
	static{
		RPT.put("03", "月报");
		RPT.put("04", "季报");
		RPT.put("05", "半年报");
		RPT.put("06", "年报");
	}
	
	/**
	 * 根据报表类型代码获取报表类型名称
	 * @param code
	 * @return
	 */
	public static String getName(String code)
	{
		return RPT.containsKey(code) ? RPT.get(code) : "";
	}
}
