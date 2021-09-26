package com.yss.ams.base.information.modules.bi.hdaygroup.dao;

/**
 * 节假日群类型表名
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
public enum HdayGroupTableName {
	userInfo("T_P_BI_HDAY"),
	recycle("R_P_BI_HDAY_R");
	
	private String value ;
	
	private HdayGroupTableName(String value){
		this.value = value;
	}
	
	/* 重写toString方法：获取枚举
	 * @see java.lang.Enum#toString()
	 */
	public String toString(){
		return this.value.toString();
	}
}
