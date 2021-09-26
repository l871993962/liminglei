package com.yss.ams.base.information.modules.bi.ieLink.dao;


/**
 * 收支连接设置表名
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
public enum IeLinkTableName {
	userInfo("T_P_BI_IE_RELA"),
	recycle("R_P_BI_IE_RELA_R");
	
	private String value ;
	
	private IeLinkTableName(String value){
		this.value = value;
	}
	
	/* 重写toString方法：获取枚举�?
	 * @see java.lang.Enum#toString()
	 */
	public String toString(){
		return this.value.toString();
	}
}
