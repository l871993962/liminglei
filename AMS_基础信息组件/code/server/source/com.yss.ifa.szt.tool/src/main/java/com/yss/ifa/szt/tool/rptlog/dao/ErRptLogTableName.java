package com.yss.ifa.szt.tool.rptlog.dao;
public enum ErRptLogTableName {

	table("T_D_ER_RPT_LOG"),
	recycle("R_D_ER_RPT_LOG_R");
	private String value;

	private ErRptLogTableName(String value) {
		this.value = value;
	}
	public String toString() {
		return this.value.toString();
	}
}