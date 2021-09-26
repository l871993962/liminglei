package com.yss.ams.product.information.modules.ab.portPdAttribute.dao;

/**
 * <产品属性分类>表名定义类
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
public enum PortPdAttributeTableName {
	userInfo("T_P_AB_PORT_PD"),
	recycle("R_P_AB_PORT_PD_R");
	
	private String value ;
	
	private PortPdAttributeTableName(String value){
		this.value = value;
	}
	
	/* 重写toString方法：获取枚举�?
	 * @see java.lang.Enum#toString()
	 */
	public String toString(){
		return this.value.toString();
	}
}
