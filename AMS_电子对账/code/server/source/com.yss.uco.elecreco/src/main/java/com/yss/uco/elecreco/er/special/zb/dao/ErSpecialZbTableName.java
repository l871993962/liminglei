package com.yss.uco.elecreco.er.special.zb.dao;
public enum ErSpecialZbTableName {

	table("T_D_ER_SPECIAL_ZB"),
	recycle("R_D_ER_SPECIAL_ZB_R");
	private String value;

	private ErSpecialZbTableName(String value) {
		this.value = value;
	}
	public String toString() {
		return this.value.toString();
	}
}