package com.yss.ams.base.information.modules.bi.srcsign.dao;

/**
 * 来源标识相关表
 * @author 马向峰 拆分
 *@Date 20170531
 */
public enum SrcSignTableName {
	userInfo("T_P_BI_SRC_SIGN"),
	recycle("R_P_BI_SRC_SIGN_R");
	
	private String value ;
	
	private SrcSignTableName(String value){
		this.value = value;
	}
	
	/* 重写toString方法：获取枚举
	 * @see java.lang.Enum#toString()
	 */
	public String toString(){
		return this.value.toString();
	}
}
