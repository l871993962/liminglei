package com.yss.uco.elecreco.er.spilt.rela.dao;
public enum ErSplitRelaTableName {

	table("T_D_ER_SPLIT_RELA"),
	recycle("R_D_ER_SPLIT_RELA_R");
	private String value;

	private ErSplitRelaTableName(String value) {
		this.value = value;
	}
	public String toString() {
		return this.value.toString();
	}
}