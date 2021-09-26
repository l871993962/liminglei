package com.yss.ams.visaval.support.api.pojo;

import java.util.List;

public class FunAPI {


	private String code;
	private String parent;
	private String text;
	private String value;
	private String desc;
	private boolean hasConnection;
	private ReturnAPI returnAPI;
	private List<ParamAPI> paramAPIs;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public ReturnAPI getReturnAPI() {
		return returnAPI;
	}
	public void setReturnAPI(ReturnAPI returnAPI) {
		this.returnAPI = returnAPI;
	}
	public List<ParamAPI> getParamAPIs() {
		return paramAPIs;
	}
	public void setParamAPIs(List<ParamAPI> paramAPIs) {
		this.paramAPIs = paramAPIs;
	}
	public boolean isHasConnection() {
		return hasConnection;
	}
	public void setHasConnection(boolean hasConnection) {
		this.hasConnection = hasConnection;
	}
	
	
}
