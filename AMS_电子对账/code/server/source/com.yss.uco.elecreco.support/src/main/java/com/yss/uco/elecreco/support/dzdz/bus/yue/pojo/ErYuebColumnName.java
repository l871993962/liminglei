package com.yss.uco.elecreco.support.dzdz.bus.yue.pojo;

public enum ErYuebColumnName {

	c_KM_CODE("C_KM_CODE"),

	c_DC_CODE("C_DC_CODE"),
	
	n_ORIG_STARTBAL("N_ORIG_STARTBAL"),

	n_ORIG_DEBIT("N_ORIG_DEBIT"),

	n_ORIG_CREDIT("N_ORIG_CREDIT"),

	n_ORIG_ENDBAL("N_ORIG_ENDBAL"),

	n_PORT_STARTBAL("N_PORT_STARTBAL"),
	
	n_PORT_DEBIT("N_PORT_DEBIT"),

	n_PORT_CREDIT("N_PORT_CREDIT"),

	n_PORT_ENDBAL("N_PORT_ENDBAL"),

	n_AMOUNT_STARTBAL("N_AMOUNT_STARTBAL"),

	n_AMOUNT_DEBIT("N_AMOUNT_DEBIT"),
	
	n_AMOUNT_CREDIT("N_AMOUNT_CREDIT"),

	n_AMOUNT_ENDBAL("N_AMOUNT_ENDBAL"),

	n_DETAIL("N_DETAIL"),

	n_J_TOLTAL_AMOUNT("N_J_TOLTAL_AMOUNT"),

	n_D_TOLTAL_AMOUNT("N_D_TOLTAL_AMOUNT"),

	c_KM_CODE_P("C_KM_CODE_P");

	private String value;

	private ErYuebColumnName(String value) {
		this.value = value;
	}

	public String toString() {
		return this.value.toString();
	}
}
