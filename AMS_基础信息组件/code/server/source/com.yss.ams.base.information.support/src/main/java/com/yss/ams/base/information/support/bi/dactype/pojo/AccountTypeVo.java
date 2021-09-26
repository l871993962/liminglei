package com.yss.ams.base.information.support.bi.dactype.pojo;

import java.util.List;

import com.yss.framework.api.restful.annotations.ControllerMixArgu;
@ControllerMixArgu
public class AccountTypeVo {
	private List<String> list;
	private String type;
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
