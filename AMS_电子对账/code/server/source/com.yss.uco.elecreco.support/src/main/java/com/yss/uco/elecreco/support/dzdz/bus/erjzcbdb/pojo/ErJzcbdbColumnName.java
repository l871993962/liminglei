package com.yss.uco.elecreco.support.dzdz.bus.erjzcbdb.pojo;

public enum ErJzcbdbColumnName {
	c_INDEX_CODE("C_INDEX_CODE"),

	n_CUR_VALUE("N_CUR_VALUE"),

	n_TOL_VALUE("N_TOL_VALUE");
	
	private String value;

	private ErJzcbdbColumnName(String value) {
		this.value = value;
	}

	public String toString() {
		return this.value.toString();
	}
}
