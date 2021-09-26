package com.yss.uco.elecreco.er.erunport.dao;

public enum ErUnPortTableName {
	unport("T_D_ER_UN_PORT"),
	recycle("R_D_ER_UN_PORT_R");
	
	private String value ;
	
	private ErUnPortTableName(String value){
		this.value = value;
	}
	
	/* 重写toString方法
	 * @see java.lang.Enum#toString()
	 */
	public String toString(){
		return this.value.toString();
	}
}
