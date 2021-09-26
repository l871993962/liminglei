package com.yss.uco.elecreco.er.reverse.map.assmap.dao;
public enum AssMapTableName {

	table("T_D_ER_REVE_ASS_MAP"),
	recycle("R_D_ER_REVE_ASS_MAP_R");
	private String value;

	private AssMapTableName(String value) {
		this.value = value;
	}
	public String toString() {
		return this.value.toString();
	}
}