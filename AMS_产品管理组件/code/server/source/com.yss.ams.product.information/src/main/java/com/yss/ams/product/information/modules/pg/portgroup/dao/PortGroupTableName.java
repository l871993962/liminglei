package com.yss.ams.product.information.modules.pg.portgroup.dao;

/**
 * <群组管理>表名定义类
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
public enum PortGroupTableName {
	clIndex("T_P_AB_GROUP"),
	recycle("R_P_AB_GROUP_R");
	
	private String value ;
	
	private PortGroupTableName(String value){
		this.value = value;
	}
	
	/* 重写toString方法：获取枚举�?
	 * @see java.lang.Enum#toString()
	 */
	public String toString(){
		return this.value.toString();
	}
}
