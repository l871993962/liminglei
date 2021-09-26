package com.yss.ifa.szt.tool.para.dao;

public enum DzParaTableName {
	userInfo("T_D_ER_RELA"),
	recycle("R_D_ER_RELA_R");
	
	private String value;

	private DzParaTableName(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value.toString();
	}
	
}
