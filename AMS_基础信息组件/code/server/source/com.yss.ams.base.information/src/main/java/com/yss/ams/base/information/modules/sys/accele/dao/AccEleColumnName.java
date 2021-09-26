package com.yss.ams.base.information.modules.sys.accele.dao;

public enum AccEleColumnName {
	/**
	 * 核算元素代码
	 */
	c_DAE_CODE("C_DAE_CODE"), 
	
	/**
	 * 核算元素名称
	 */
	c_DAE_NAME("C_DAE_NAME"),
	
	/**
	 * 数据来源字符串
	 */
	c_DS_TYPE("C_DS_TYPE"),
	
	/**
	 * 核算级别表的字段名
	 */
	c_DAI_FIELD("C_DAI_FIELD");

	private String value;

	private AccEleColumnName(String value) {
		this.value = value;
	}

	public String toString() {
		return this.value.toString();
	}
}
