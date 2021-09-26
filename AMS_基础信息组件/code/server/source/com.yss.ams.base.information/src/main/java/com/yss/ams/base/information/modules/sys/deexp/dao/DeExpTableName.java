package com.yss.ams.base.information.modules.sys.deexp.dao;

/**
 * 表达式字典表T_S_DE_EXP
 *
 */
public enum DeExpTableName {
	userInfo("T_S_DE_EXP"),
	recycle("T_S_DE_EXP_R");
	private String value;
	private DeExpTableName(String value){
		this.value = value;
	}
	public String toString(){
		return this.value.toString();
	}
}
