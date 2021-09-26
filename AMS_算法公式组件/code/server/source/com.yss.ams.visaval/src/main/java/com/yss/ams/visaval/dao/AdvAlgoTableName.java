package com.yss.ams.visaval.dao;



public enum AdvAlgoTableName {
	userInfo("T_V_AA_ADV_ALGO"),
	recycle("R_V_AA_ADV_ALGO_R");
	
	private String value ;
	
	private AdvAlgoTableName(String value){
		this.value = value;
	}
	
	/* 重写toString方法：获取枚举
	 * @see java.lang.Enum#toString()
	 */
	public String toString(){
		return this.value.toString();
	}
}
