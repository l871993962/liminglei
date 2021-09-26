package com.yss.ifa.szt.tool.para.dao;

public enum DzPortRelaTableName {
	userInfo("T_P_AB_PORT_RELA"),
	recycle("R_P_AB_PORT_RELA_R");
	
	private String value ;
	
	private DzPortRelaTableName(String value){
		this.value = value;
	}
	
	/* 重写toString方法：获取枚举值
	 * @see java.lang.Enum#toString()
	 */
	public String toString(){
		return this.value.toString();
	}
}
