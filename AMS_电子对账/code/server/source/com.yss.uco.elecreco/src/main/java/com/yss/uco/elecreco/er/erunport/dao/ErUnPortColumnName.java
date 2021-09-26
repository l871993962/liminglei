package com.yss.uco.elecreco.er.erunport.dao;

public enum ErUnPortColumnName {
	id("C_IDEN"),

	/*组合代码*/
	c_PORT_CODE("C_PORT_CODE"),
	
	operator("C_OPER_BY"),
	
	operTime("C_OPER_TIME");
	private String value;

	private ErUnPortColumnName(String value) {
		this.value = value;
	}

	public String toString() {
		return this.value.toString();
	}

}
