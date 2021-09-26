package com.yss.ams.base.information.support.sys.portbusinessrange.pojo;

import java.util.HashMap;

import com.yss.framework.api.restful.annotations.ControllerMixArgu;
@ControllerMixArgu
public class PortBusinessRangePojoVo {

	private String type; 
	private HashMap<String, String> paraMap;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public HashMap<String, String> getParaMap() {
		return paraMap;
	}
	public void setParaMap(HashMap<String, String> paraMap) {
		this.paraMap = paraMap;
	}
	
	
}
