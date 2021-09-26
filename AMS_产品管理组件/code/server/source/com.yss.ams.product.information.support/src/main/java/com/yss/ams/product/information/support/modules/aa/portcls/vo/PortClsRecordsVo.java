package com.yss.ams.product.information.support.modules.aa.portcls.vo;

import java.util.Date;

import com.yss.framework.api.restful.annotations.ControllerMixArgu;

@ControllerMixArgu
public class PortClsRecordsVo {

	private Date actDate;
	private String port;
	public Date getActDate() {
		return actDate;
	}
	public void setActDate(Date actDate) {
		this.actDate = actDate;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	
	
}
