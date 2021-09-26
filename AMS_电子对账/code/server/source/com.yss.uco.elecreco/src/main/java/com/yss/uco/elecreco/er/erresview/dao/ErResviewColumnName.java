package com.yss.uco.elecreco.er.erresview.dao;

public enum ErResviewColumnName {
	
	id("C_IDEN"),
	c_PLAN_CODE("C_PLAN_CODE"),
	c_PLAN_NAME("C_PLAN_NAME"),
	c_ITEM_CODE("C_ITEM_CODE"),
	c_PLAN_TYPE("C_PLAN_TYPE"),
	c_DESC("C_DESC"),
	operator("C_CHECK_BY"),

	auditDate("C_CHECK_TIME"),

	modifier("C_UPDATE_BY"),

	modifyDate("C_UPDATE_TIME"),

	auditState("N_CHECK_STATE");
	  
	private String value;

	private ErResviewColumnName(String value) {
		this.value = value;
	}

	public String toString() {
		return this.value.toString();
	}

}
