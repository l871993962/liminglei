package com.yss.uco.elecreco.support.vo;

import com.yss.uco.elecreco.support.bean.ErBbInfo;

public class AutoStateVo {

	private ErBbInfo erBbInfo; 
	
	private String bfCodes;
	
	private String checkCondition;

	public ErBbInfo getErBbInfo() {
		return erBbInfo;
	}

	public void setErBbInfo(ErBbInfo erBbInfo) {
		this.erBbInfo = erBbInfo;
	}

	public String getBfCodes() {
		return bfCodes;
	}

	public void setBfCodes(String bfCodes) {
		this.bfCodes = bfCodes;
	}

	public String getCheckCondition() {
		return checkCondition;
	}

	public void setCheckCondition(String checkCondition) {
		this.checkCondition = checkCondition;
	}
	
}
