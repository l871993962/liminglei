package com.yss.ams.base.information.modules.sys.deexp.dao;

/**
 * 表达式字典表T_S_DE_EXP列明
 *
 */
public enum DeExpColumnName {
	/**
	 * 表达式代码
	 */
	c_EXP_CODE("C_EXP_CODE"),
	
	/**
	 * 表达式名称
	 */
	c_EXP_NAME("C_EXP_NAME"),
	
	/**
	 * 表达式类型
	 */
	c_DV_EXP_TYPE("C_DV_EXP_TYPE"),
	
	/**
	 * 表达式内容
	 */
	c_VALUE("C_VALUE");
	
	private String value;
	private DeExpColumnName(String value){
		this.value = value;
	}
	public String toString(){
		return this.value.toString();
	}
}
