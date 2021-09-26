package com.yss.ams.base.information.modules.bi.tdchan.dao;

public enum TdChanExtendColumnName {

	c_P_CODE("C_P_CODE"),
	c_MKT_NAME("C_MKT_NAME");
	
	private String value ;

	private TdChanExtendColumnName(String value){
	this.value = value;
	}

	public String toString(){
	return this.value.toString();
	}
}
