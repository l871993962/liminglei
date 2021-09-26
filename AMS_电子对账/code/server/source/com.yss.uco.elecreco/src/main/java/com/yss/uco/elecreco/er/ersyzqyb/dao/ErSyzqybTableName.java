package com.yss.uco.elecreco.er.ersyzqyb.dao;

public enum ErSyzqybTableName {

	syzqyInfo("T_D_ER_SYZQYBD"),
	recycle("RDZBALANCE_R");
	
	private String value ;
	
	private ErSyzqybTableName(String value) {
		this.value = value;
	}
	
	/* 重写toString方法
	 * @see java.lang.Enum#toString()
	 */
	public String toString(){
		return this.value.toString();
	}
}
