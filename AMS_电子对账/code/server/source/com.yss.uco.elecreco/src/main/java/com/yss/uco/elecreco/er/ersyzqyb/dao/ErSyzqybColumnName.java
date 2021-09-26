package com.yss.uco.elecreco.er.ersyzqyb.dao;

public enum ErSyzqybColumnName {

	id("C_IDEN"),
	
	c_SN("C_SN"),

	c_ASS_CODE("C_ASS_CODE"),

	c_FILE_TYPE("C_FILE_TYPE"),

	c_RPT_TYPE("C_RPT_TYPE"),

	d_START_DATE("D_START_DATE"),

	d_END_DATE("D_END_DATE"),
	
	c_DEPT_CODE("C_DEPT_CODE"),
	
	c_CERT_ID("C_CERT_ID"),
	
	c_INDEX_CODE("C_INDEX_CODE"),
	
	c_INDEX_NAME("C_INDEX_NAME"),
	
	n_THIS_NAV("N_THIS_NAV"),
	
	n_THIS_UNPROFIT("n_this_unprofit"),
	
	n_THIS_INTERESTS("n_THIS_INTERESTS"),
	
	n_LAST_NAV("N_LAST_NAV"),
	
	n_LAST_UNPROFIT("N_LAST_UNPROFIT"),
	
	n_LAST_INTERESTS("N_LAST_INTERESTS");

	private String value ;

	private ErSyzqybColumnName(String value){
	this.value = value;
	}

	public String toString(){
	return this.value.toString();
	}
	
}
