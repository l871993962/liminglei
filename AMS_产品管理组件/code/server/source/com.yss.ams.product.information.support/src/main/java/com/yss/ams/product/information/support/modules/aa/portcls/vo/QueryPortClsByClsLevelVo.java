package com.yss.ams.product.information.support.modules.aa.portcls.vo;

import java.util.Date;

import com.yss.framework.api.restful.annotations.ControllerMixArgu;

@ControllerMixArgu
public class QueryPortClsByClsLevelVo {

	private String portCode;
	private String clsLevel;
	private Date actDate;
	public String getPortCode() {
		return portCode;
	}
	public void setPortCode(String portCode) {
		this.portCode = portCode;
	}
	public String getClsLevel() {
		return clsLevel;
	}
	public void setClsLevel(String clsLevel) {
		this.clsLevel = clsLevel;
	}
	public Date getActDate() {
		return actDate;
	}
	public void setActDate(Date actDate) {
		this.actDate = actDate;
	}
	
	
}
