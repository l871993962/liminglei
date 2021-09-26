package com.yss.uco.elecreco.er.reverse.manager.ignore.dao;
public enum IgnoreItemTableName {

	table("T_D_ER_REVE_IGNORE"),
	recycle("R_D_ER_REVE_IGNORE_R");
	private String value;

	private IgnoreItemTableName(String value) {
		this.value = value;
	}
	public String toString() {
		return this.value.toString();
	}
}