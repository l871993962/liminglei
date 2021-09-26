package com.yss.ams.base.information.modules.bi.salesnet.dao;

/**
 * 销售网点设置表名
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
public enum SalesNetTableName {
	userInfo("T_P_BI_SALES_NET"),
	recycle("R_P_BI_SALES_NET_R");
	
	private String value ;
	
	private SalesNetTableName(String value){
		this.value = value;
	}
	
	/* 重写toString方法：获取枚举
	 * @see java.lang.Enum#toString()
	 */
	public String toString(){
		return this.value.toString();
	}
}
