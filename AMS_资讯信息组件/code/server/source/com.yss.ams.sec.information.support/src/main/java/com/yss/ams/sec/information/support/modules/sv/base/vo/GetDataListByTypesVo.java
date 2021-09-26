package com.yss.ams.sec.information.support.modules.sv.base.vo;

import java.util.List;

import com.yss.framework.api.restful.annotations.ControllerMixArgu;

@ControllerMixArgu
public class GetDataListByTypesVo {

	private List<String> types;
	private String paraValue;
	public List<String> getTypes() {
		return types;
	}
	public void setTypes(List<String> types) {
		this.types = types;
	}
	public String getParaValue() {
		return paraValue;
	}
	public void setParaValue(String paraValue) {
		this.paraValue = paraValue;
	}
	
	
}
