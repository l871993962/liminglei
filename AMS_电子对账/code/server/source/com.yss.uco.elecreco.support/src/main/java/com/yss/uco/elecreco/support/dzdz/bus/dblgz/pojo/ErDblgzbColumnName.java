package com.yss.uco.elecreco.support.dzdz.bus.dblgz.pojo;

public enum ErDblgzbColumnName {

	c_KM_CODE("C_KM_CODE"),

	c_KM_NAME("C_KM_NAME"),
	
	n_VA_PRICE("n_VA_PRICE"),

	n_QUOT_LOGO("N_QUOT_LOGO"),

	n_AMOUNT("N_AMOUNT"),

	n_PORT_COST("N_PORT_COST"),

	n_PORT_MV("N_PORT_MV"),
	
	n_PORT_IV("N_PORT_IV"),

	n_CB_JZ_BL("N_CB_JZ_BL"),

	n_SZ_JZ_BL("N_SZ_JZ_BL"),

	n_DETAIL("N_DETAIL");

	private String value;

	private ErDblgzbColumnName(String value) {
		this.value = value;
	}

	public String toString() {
		return this.value.toString();
	}

}
