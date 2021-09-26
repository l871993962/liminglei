package com.yss.uco.elecreco.er.transrepcfg.dao;

public enum DzTransRepCfgTableName {
	recycle("R_P_ER_TRANSREPCFG_R"),
	
	userInfo("T_P_ER_TRANSREPCFG");
	
	private String value;

	private DzTransRepCfgTableName(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value.toString();
	}

}
