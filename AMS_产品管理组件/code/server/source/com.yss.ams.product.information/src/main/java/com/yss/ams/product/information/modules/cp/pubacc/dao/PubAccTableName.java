package com.yss.ams.product.information.modules.cp.pubacc.dao;



public enum PubAccTableName {
	pubAcc("T_C_CP_PUB_ACC"),
	recycle("R_C_CP_PUB_ACC_R");
	
	private String value ;
	
	private PubAccTableName(String value){
		this.value = value;
	}
	
	/* 重写toString方法：获取枚举�?
	 * @see java.lang.Enum#toString()
	 */
	public String toString(){
		return this.value.toString();
	}
}
