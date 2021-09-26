package com.yss.ams.product.information.support.modules.aa.portcls.vo;

import java.util.Date;

import com.yss.framework.api.restful.annotations.ControllerMixArgu;

@ControllerMixArgu
public class QueryPortClsSortVo {

	private String portCode;
	private Date actDate; 
	private boolean sort;
	public String getPortCode() {
		return portCode;
	}
	public void setPortCode(String portCode) {
		this.portCode = portCode;
	}
	public Date getActDate() {
		return actDate;
	}
	public void setActDate(Date actDate) {
		this.actDate = actDate;
	}
	public boolean isSort() {
		return sort;
	}
	public void setSort(boolean sort) {
		this.sort = sort;
	}
	
	
}
