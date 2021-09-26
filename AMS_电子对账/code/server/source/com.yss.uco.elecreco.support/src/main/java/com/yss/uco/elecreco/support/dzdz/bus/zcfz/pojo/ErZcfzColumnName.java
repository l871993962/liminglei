package com.yss.uco.elecreco.support.dzdz.bus.zcfz.pojo;

public enum ErZcfzColumnName {

	c_INDEX_CODE("C_INDEX_CODE"),

	n_BEGIN_VALUE("N_BEGIN_VALUE"),

	n_END_VALUE("N_END_VALUE");
	
	private String value;

	private ErZcfzColumnName(String value) {
		this.value = value;
	}

	public String toString() {
		return this.value.toString();
	}
}
