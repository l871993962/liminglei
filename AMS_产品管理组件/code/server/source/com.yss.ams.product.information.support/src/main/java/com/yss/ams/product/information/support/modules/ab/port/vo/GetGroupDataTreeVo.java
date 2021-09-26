package com.yss.ams.product.information.support.modules.ab.port.vo;

import java.util.List;

import com.yss.framework.api.restful.annotations.ControllerMixArgu;
@ControllerMixArgu
public class GetGroupDataTreeVo {

	private String trCode;
	private List<String> portList;
	public String getTrCode() {
		return trCode;
	}
	public void setTrCode(String trCode) {
		this.trCode = trCode;
	}
	public List<String> getPortList() {
		return portList;
	}
	public void setPortList(List<String> portList) {
		this.portList = portList;
	}
	
	
	
}
