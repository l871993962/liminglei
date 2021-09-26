package com.yss.ams.base.information.modules.sys.acctype.dao;

public enum AccTypeColumnName {
	/**
	 * 资产类型代码
	 */
	c_DAT_CODE("C_DAT_CODE"), 
	
	/**
	 * 资产类型名称
	 */
	c_DAT_NAME("C_DAT_NAME"),
	
	/**
	 * 编号
	 */
	n_ORDER("N_ORDER"),
	
	c_DAT_CODE_P("C_DAT_CODE_P"),
	
	c_DAT_TYPE("C_DAT_TYPE"),
	
	id("");
	
	private String value;

	private AccTypeColumnName(String value) {
		this.value = value;
	}

	public String toString() {
		return this.value.toString();
	}
}
