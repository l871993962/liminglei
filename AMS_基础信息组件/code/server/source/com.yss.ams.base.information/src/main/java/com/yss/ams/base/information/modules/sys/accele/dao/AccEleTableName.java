package com.yss.ams.base.information.modules.sys.accele.dao;

public enum AccEleTableName {
	userInfo("T_S_DAE_ELEM"),
	recycle("T_S_DAE_ELEM_R");
	
	private String value;
	private AccEleTableName(String value){
		this.value = value;
	}
	
	public String toString(){
		return this.value.toString();
	}
}
