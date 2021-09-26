package com.yss.ams.base.information.modules.sys.acctype.dao;

public enum AccTypeTableName {
	userInfo("T_S_DAT_ASS_TYPE"), 
	recycle("T_S_DAT_ASS_TYPE_R");

	private String value;

	private AccTypeTableName(String value) {
		this.value = value;
	}

	public String toString() {
		return this.value.toString();
	}
}
