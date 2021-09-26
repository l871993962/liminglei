package com.yss.ams.syncdata.business.productinfo.dao;

/**
 * <产品基本信息>表名定义类
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
public enum PortTableName {
	userInfo("T_P_AB_PORT"),
	recycle("R_P_AB_PORT_R");
	
	private String value ;
	
	private PortTableName(String value){
		this.value = value;
	}
	
	/* 重写toString方法：获取枚举值
	 * @see java.lang.Enum#toString()
	 */
	public String toString(){
		return this.value.toString();
	}
}
