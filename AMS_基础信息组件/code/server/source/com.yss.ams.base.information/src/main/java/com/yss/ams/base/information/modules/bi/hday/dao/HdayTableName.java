package com.yss.ams.base.information.modules.bi.hday.dao;

/**
 * 节假日群设置表名
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
public enum HdayTableName {
	userInfo("T_P_BI_HDAY_SUB"),
	recycle("R_P_BI_HDAY_SUB_R");
	
	private String value ;
	
	private HdayTableName(String value){
		this.value = value;
	}
	
	/* 重写toString方法：获取枚举
	 * @see java.lang.Enum#toString()
	 */
	public String toString(){
		return this.value.toString();
	}
}
