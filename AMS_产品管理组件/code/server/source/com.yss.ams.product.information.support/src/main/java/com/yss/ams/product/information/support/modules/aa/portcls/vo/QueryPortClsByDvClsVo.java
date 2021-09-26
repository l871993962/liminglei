package com.yss.ams.product.information.support.modules.aa.portcls.vo;

import java.util.Date;

import com.yss.framework.api.restful.annotations.ControllerMixArgu;

@ControllerMixArgu
public class QueryPortClsByDvClsVo {

	private String portCode;
	private String dvCls;
	private Date actDate;
	public String getPortCode() {
		return portCode;
	}
	public void setPortCode(String portCode) {
		this.portCode = portCode;
	}
	public String getDvCls() {
		return dvCls;
	}
	public void setDvCls(String dvCls) {
		this.dvCls = dvCls;
	}
	public Date getActDate() {
		return actDate;
	}
	public void setActDate(Date actDate) {
		this.actDate = actDate;
	}
	
	
}
