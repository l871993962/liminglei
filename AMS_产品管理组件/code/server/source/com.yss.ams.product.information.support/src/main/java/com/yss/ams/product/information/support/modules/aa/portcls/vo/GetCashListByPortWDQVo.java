package com.yss.ams.product.information.support.modules.aa.portcls.vo;

import java.util.Date;

import com.yss.framework.api.restful.annotations.ControllerMixArgu;

@ControllerMixArgu
public class GetCashListByPortWDQVo {

	private String portCode;
	private Date accDate;
	public String getPortCode() {
		return portCode;
	}
	public void setPortCode(String portCode) {
		this.portCode = portCode;
	}
	public Date getAccDate() {
		return accDate;
	}
	public void setAccDate(Date accDate) {
		this.accDate = accDate;
	}
	
	
}
