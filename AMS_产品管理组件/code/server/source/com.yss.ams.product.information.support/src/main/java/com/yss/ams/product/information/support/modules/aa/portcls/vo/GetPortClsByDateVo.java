package com.yss.ams.product.information.support.modules.aa.portcls.vo;

import java.util.Date;

import com.yss.framework.api.restful.annotations.ControllerMixArgu;

@ControllerMixArgu
public class GetPortClsByDateVo {

	
	private String PortCode;
	private Date dueDate;
	public String getPortCode() {
		return PortCode;
	}
	public void setPortCode(String portCode) {
		PortCode = portCode;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	
	
	
}
