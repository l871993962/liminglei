package com.yss.uco.elecreco.er.erzcfzb.dao;

public enum ErZcfzbTableName {
	zcfzInfo("T_D_ER_ZCFZ"),
	recycle("RDZBALANCE_R");
	
	private String value ;
	
	private ErZcfzbTableName(String value) {
		this.value = value;
	}
	
	/* 重写toString方法
	 * @see java.lang.Enum#toString()
	 */
	public String toString(){
		return this.value.toString();
	}
}
