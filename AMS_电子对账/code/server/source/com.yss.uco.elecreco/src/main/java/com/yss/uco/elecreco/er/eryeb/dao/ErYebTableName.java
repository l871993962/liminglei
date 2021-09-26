package com.yss.uco.elecreco.er.eryeb.dao;

public enum ErYebTableName {
	yeInfo("TDZBALANCE"),
	recycle("RDZBALANCE_R");
	
	private String value ;
	
	private ErYebTableName(String value){
		this.value = value;
	}
	
	/* 重写toString方法：获取枚举�?
	 * @see java.lang.Enum#toString()
	 */
	public String toString(){
		return this.value.toString();
	}
}
