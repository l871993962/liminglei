package com.yss.uco.elecreco.automic.util;

import java.util.HashMap;
import java.util.List;

public class AutomicUtil {

	/**
	 * 
	 * @Description: 将list按分隔符拼接
	 * @param  list 集合
	 * @param separator 分割符
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @author wulongxing 
	 * @date 2018年3月20日 下午3:18:22
	 */
	public static String listToString(List<String> list, String separator) {
		StringBuilder sb = new StringBuilder();   
		for (int i = 0; i < list.size(); i++) {    
			sb.append(list.get(i));      
			if (i < list.size() - 1) {       
				sb.append(separator);    
			}    
		}    
		return sb.toString();
	}
	
	@SuppressWarnings("unchecked")
	public static String getStringParam(HashMap<String, Object> params, Object paramObj) {
		String paramName = paramObj.toString();
		String value = "";
		if (params.containsKey(paramName)) {
			Object obj = params.get(paramName);

			if (obj instanceof List<?>) {
				List<String> list = (List<String>) obj;
				if (list.size() > 0) {
					value = list.get(0);
				}
			} else {
				value = String.valueOf(obj);
			}
		}

		return value;
	}
}
