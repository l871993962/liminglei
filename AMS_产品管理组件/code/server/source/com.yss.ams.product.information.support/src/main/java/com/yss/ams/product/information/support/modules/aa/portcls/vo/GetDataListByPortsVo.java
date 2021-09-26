package com.yss.ams.product.information.support.modules.aa.portcls.vo;

import java.util.List;

import com.yss.framework.api.restful.annotations.ControllerMixArgu;

@ControllerMixArgu
public class GetDataListByPortsVo {

	private List<String> types;
	private List<String> clsPort;
	public List<String> getTypes() {
		return types;
	}
	public void setTypes(List<String> types) {
		this.types = types;
	}
	public List<String> getClsPort() {
		return clsPort;
	}
	public void setClsPort(List<String> clsPort) {
		this.clsPort = clsPort;
	}
	
	
	
}
