package com.yss.uco.elecreco.er.reverse.manager.info.dao;
public enum ErReveInfoTableName {

	table("T_D_ER_REVE_INFO"),
	recycle("R_D_ER_REVE_INFO_R");
	private String value;

	private ErReveInfoTableName(String value) {
		this.value = value;
	}
	public String toString() {
		return this.value.toString();
	}
}