package com.yss.ams.product.information.support.modules.dataCopy.vo;

import java.util.HashMap;

import com.yss.framework.api.restful.annotations.ControllerMixArgu;

@ControllerMixArgu
public class QueryCopyDataVo {

	private HashMap<String, Object> paraMap;
	private Class<?> clazz;
	public HashMap<String, Object> getParaMap() {
		return paraMap;
	}
	public void setParaMap(HashMap<String, Object> paraMap) {
		this.paraMap = paraMap;
	}
	public Class<?> getClazz() {
		return clazz;
	}
	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}
	
	
	
}
