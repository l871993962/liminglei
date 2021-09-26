package com.yss.uco.elecreco.er.tmplrela.dao;

/**
 * @author liuxiang 2015年2月13日
 */
public enum DzTmplRelaColumnName {
	/**
	 * 模板代码
	 */
	c_TMPL_CODE("C_TMPL_CODE"),

	/**
	 * 模板名称
	 */
	c_PORT_CODE("C_PORT_CODE"),

	/**
	 * 模板类型
	 */
	c_TMPL_TYPE("C_TMPL_TYPE"),

	/**
	 * 描述信息
	 */
	c_DESC("C_DESC"),

	endUseDate(""),
	
	startUseDate(""),

	id("C_IDEN"),

	operator("C_CHECK_BY"),

	auditDate("C_CHECK_TIME"),

	modifier("C_UPDATE_BY"),

	modifyDate("C_UPDATE_TIME"),

	auditState("N_CHECK_STATE");

	private String value;

	private DzTmplRelaColumnName(String value) {
		this.value = value;
	}

	public String toString() {
		return this.value.toString();
	}
}
