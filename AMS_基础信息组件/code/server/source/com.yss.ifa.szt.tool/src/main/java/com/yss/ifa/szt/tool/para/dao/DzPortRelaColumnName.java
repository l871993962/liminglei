package com.yss.ifa.szt.tool.para.dao;

public enum DzPortRelaColumnName {
	/**
	 * 关联机构
	 */
	id("C_IDEN"),
	c_PORT_CODE("C_PORT_CODE"),
	c_ORG_CODE("C_ORG_CODE"),
	c_ORG_NAME("C_ORG_NAME"),
	c_ORG_NAME_P("C_ORG_NAME_P"),
	c_DV_ORG_TYPE("C_DV_ORG_TYPE"),
	c_DV_TYPE_CODE("C_DV_TYPE_CODE"),
	c_RELA_TYPE("C_RELA_TYPE"),
	c_RELA_CODE("C_RELA_CODE"),
	operator("C_CHECK_BY"),

	auditDate("C_CHECK_TIME"),

	modifier("C_UPDATE_BY"),

	modifyDate("C_UPDATE_TIME"),

	auditState("N_CHECK_STATE");
	
	private String value ;

	private DzPortRelaColumnName(String value){
	this.value = value;
	}

	public String toString(){
	return this.value.toString();
	}
}
