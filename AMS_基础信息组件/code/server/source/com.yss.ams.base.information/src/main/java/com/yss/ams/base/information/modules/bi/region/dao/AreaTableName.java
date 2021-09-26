package com.yss.ams.base.information.modules.bi.region.dao;

/**
 * 地区信息相关表
 * @author 马向峰 拆分
 *@Date 20170601
 */
public enum AreaTableName {
	userInfo("T_P_BI_AREA"),
	recycle("R_P_BI_AREA_R");
	
	private String value ;
	
	private AreaTableName(String value){
		this.value = value;
	}
	
	/* 重写toString方法：获取枚举
	 * @see java.lang.Enum#toString()
	 */
	public String toString(){
		return this.value.toString();
	}
}
