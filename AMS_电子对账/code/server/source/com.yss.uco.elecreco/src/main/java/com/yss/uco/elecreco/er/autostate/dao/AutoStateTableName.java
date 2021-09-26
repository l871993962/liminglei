package com.yss.uco.elecreco.er.autostate.dao;

public enum AutoStateTableName {
	Table("T_D_ER_AUTOSTATE");
	
	private String value ;
	
	private AutoStateTableName(String value){
		this.value = value;
	}
	
	/* 重写toString方法：获取枚举�?
	 * @see java.lang.Enum#toString()
	 */
	public String toString(){
		return this.value.toString();
	}
}
