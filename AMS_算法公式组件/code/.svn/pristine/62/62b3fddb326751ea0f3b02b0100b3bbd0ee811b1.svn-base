package com.yss.ams.visaval.util;

import java.util.HashMap;
import java.util.Map;

import com.yss.ams.visaval.service.impl.AdvAlgoService;

/**
 * 单例类：
 * t_s_param表初始化记录；解决问题：来自该表且type为文本类型   翻译API时加$
 * @author 马向峰
 */
public class Algo_T_S_PARAM_Init {

	private Map<String,String> paramMap = new HashMap<String, String>();
	
	private static Algo_T_S_PARAM_Init instance = null;
	
	private Algo_T_S_PARAM_Init(){
		initData();
	}
	
	public static Algo_T_S_PARAM_Init getInstance(){
		if (null == instance){
			synchronized (Algo_T_S_PARAM_Init.class) {
				if (null == instance){
					instance = new Algo_T_S_PARAM_Init();
				}
			}
		}
		
		return instance;
	}
	
	public Map<String,String> getData(){
		return paramMap;
	}
	
	private void initData(){
		AdvAlgoService advAlgoService;
		try {
			advAlgoService = new AdvAlgoService();
			paramMap = advAlgoService.getT_S_PARAM_Data();
		} catch (Exception e) {
//			e.printStackTrace();
		}
	}
}
