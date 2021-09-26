package com.yss.uco.elecreco.support.dzdz.common.pojo;

public enum XmlRootColumnName {

	c_FILE_TYPE("C_FILE_TYPE"),

	c_ASS_CODE("C_ASS_CODE"),

	c_RPT_TYPE("C_RPT_TYPE"),

	d_START_DATE("D_START_DATE"),
	
	d_END_DATE("D_END_DATE"),
	
	c_DEPT_CODE("C_DEPT_CODE"),

	c_CERT_ID("C_CERT_ID"),

	c_SN("C_SN");
	
	private String value;

	private XmlRootColumnName(String value) {
		this.value = value;
	}

	public String toString() {
		return this.value.toString();
	}
}
