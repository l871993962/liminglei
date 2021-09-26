package com.yss.uco.elecreco.er.erresview.dao;

public enum ErResviewTableName {
	userInfo("T_D_ER_RESVIEW"),
	recycle("R_D_ER_RESVIEW_R");
	
	private String value ;
	
	private ErResviewTableName(String value){
		this.value = value;
	}
	
	/* 重写toString方法：获取枚举�?
	 * @see java.lang.Enum#toString()
	 */
	public String toString(){
		return this.value.toString();
	}
}
