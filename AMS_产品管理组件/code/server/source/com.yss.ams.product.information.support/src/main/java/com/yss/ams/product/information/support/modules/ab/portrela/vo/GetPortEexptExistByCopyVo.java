package com.yss.ams.product.information.support.modules.ab.portrela.vo;

import com.yss.ams.product.information.support.modules.ab.portrela.pojo.PortRela;
import com.yss.framework.api.restful.annotations.ControllerMixArgu;
@ControllerMixArgu
public class GetPortEexptExistByCopyVo {

	private String ports;
	private PortRela pojo;
	public String getPorts() {
		return ports;
	}
	public void setPorts(String ports) {
		this.ports = ports;
	}
	public PortRela getPojo() {
		return pojo;
	}
	public void setPojo(PortRela pojo) {
		this.pojo = pojo;
	}
	
	
}
