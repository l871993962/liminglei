package com.yss.ams.base.information.modules.bi.accountTreeA.dao;

public enum AccountTreeAColumnName {

	c_NODE_CODE("C_NODE_CODE"),
	c_NODE_NAME("C_NODE_NAME"),
	c_NODE_CODE_P("C_NODE_CODE_P"),
	c_POST_CODE("C_POST_CODE"),
	id("C_IDEN"),
	startUseDate(""),
	endUseDate(""),
	operator("C_CHECK_BY"),
	auditDate("C_CHECK_TIME"),
	modifier("C_UPDATE_BY"),
	modifyDate("C_UPDATE_TIME"),
	auditState("N_CHECK_STATE");

	
	private String value ;

	private AccountTreeAColumnName(String value){
	this.value = value;
	}

	public String toString(){
	return this.value.toString();
	}
}
