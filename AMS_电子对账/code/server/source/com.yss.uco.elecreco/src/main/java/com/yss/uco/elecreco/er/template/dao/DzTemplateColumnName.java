package com.yss.uco.elecreco.er.template.dao;

/**
 * @author liuxiang 2015年2月13日
 */
public enum DzTemplateColumnName {
	/**
	 * 模板代码
	 */
	c_TMPL_CODE("C_TMPL_CODE"),

	/**
	 * 模板名称
	 */
	c_TMPL_NAME("C_TMPL_NAME"),

	/**
	 * 模板路径
	 */
	c_TMPL_PATH("C_TMPL_PATH"),
	
	/**
	 * 模板类型
	 */
	c_TMPL_TYPE("C_TMPL_TYPE"),

	/**
	 * 版本号
	 */
	c_VERSION("C_VERSION"),

	/**
	 * 模板状态
	 */
	c_DV_TMPL_STATUS("C_DV_TMPL_STATUS"),

	/**
	 * 描述信息
	 */
	c_DESC("C_DESC"),

	id("C_IDEN"),

	modifier("C_UPDATE_BY"),

	modifyDate("C_UPDATE_TIME"),

	startUseDate(""),
	
	endUseDate("");

	private String value;

	private DzTemplateColumnName(String value) {
		this.value = value;
	}

	public String toString() {
		return this.value.toString();
	}
}
