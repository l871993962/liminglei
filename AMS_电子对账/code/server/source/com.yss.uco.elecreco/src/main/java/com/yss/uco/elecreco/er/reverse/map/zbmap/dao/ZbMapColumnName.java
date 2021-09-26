package com.yss.uco.elecreco.er.reverse.map.zbmap.dao;
public enum ZbMapColumnName {

	/**
	*
	*/
	id("C_IDEN"),

	/**
	*对账类型
	*/
	c_FILE_TYPE("C_FILE_TYPE"),

	/**
	*内部指标代码
	*/
	c_ZB_CODE("C_ZB_CODE"),

	/**
	*内部指标名称
	*/
	c_ZB_NAME("C_ZB_NAME"),

	/**
	*外部指标代码
	*/
	c_ZB_CODE_OUT("C_ZB_CODE_OUT"),

	/**
	*外部指标名称
	*/
	c_ZB_NAME_OUT("C_ZB_NAME_OUT"),

	/**
	*产品组合
	*/
	c_PORT_CODE("C_PORT_CODE"),

	/**
	*托管机构
	*/
	c_TGH_CODE("C_TGH_CODE"),

	/**
	*
	*/
	auditState("N_CHECK_STATE"),

	/**
	*
	*/
	modifier("C_UPDATE_BY"),

	/**
	*
	*/
	modifyDate("C_UPDATE_TIME"),

	/**
	*
	*/
	operator("C_CHECK_BY"),

	/**
	*
	*/
	auditDate("C_CHECK_TIME"),

	endUseDate(""),

	startUseDate("");

	private String value;

	private ZbMapColumnName(String value) {
		this.value = value;
	}
	public String toString() {
		return this.value.toString();
	}
}