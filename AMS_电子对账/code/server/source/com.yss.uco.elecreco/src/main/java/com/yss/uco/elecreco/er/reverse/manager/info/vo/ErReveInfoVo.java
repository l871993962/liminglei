package com.yss.uco.elecreco.er.reverse.manager.info.vo;

import java.util.List;

import com.yss.uco.elecreco.er.reverse.manager.info.pojo.ErReveInfo;

public class ErReveInfoVo {

	private List<ErReveInfo> list;
	
	private String dzResult;
	
	private String xgsm;

	public List<ErReveInfo> getList() {
		return list;
	}

	public void setList(List<ErReveInfo> list) {
		this.list = list;
	}

	public String getDzResult() {
		return dzResult;
	}

	public void setDzResult(String dzResult) {
		this.dzResult = dzResult;
	}

	public String getXgsm() {
		return xgsm;
	}

	public void setXgsm(String xgsm) {
		this.xgsm = xgsm;
	}
	
}
