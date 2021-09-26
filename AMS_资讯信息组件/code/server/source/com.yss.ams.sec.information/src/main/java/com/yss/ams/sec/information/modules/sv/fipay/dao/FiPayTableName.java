package com.yss.ams.sec.information.modules.sv.fipay.dao;


/**
 * 债券历史付息信息表名
 * @author yuankai 
 * 资讯信息拆分	2017.7.3 STORY #42948 资讯信息管理组件化拆分
 */
public enum FiPayTableName {
	userInfo("T_D_SV_FI_PAY"),
	recycle("R_D_SV_FI_PAY_R");
	
	private String value ;
	
	private FiPayTableName(String value){
		this.value = value;
	}
	
	/* 重写toString方法：获取枚举
	 * @see java.lang.Enum#toString()
	 */
	public String toString(){
		return this.value.toString();
	}
}
