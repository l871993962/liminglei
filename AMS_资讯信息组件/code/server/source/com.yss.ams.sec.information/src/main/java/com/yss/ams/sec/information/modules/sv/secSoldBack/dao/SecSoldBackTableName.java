package com.yss.ams.sec.information.modules.sv.secSoldBack.dao;


/**
 * #42948 资讯信息管理组件化拆分
 * @author chenbo
 *2017-07-06
 */
public enum SecSoldBackTableName {
	userInfo("T_P_SV_SEC_SOLDBACK");
	
	private String value ;
	
	private SecSoldBackTableName(String value){
		this.value = value;
	}
	
	/* 重写toString方法：获取枚举
	 * @see java.lang.Enum#toString()
	 */
	public String toString(){
		return this.value.toString();
	}
}
