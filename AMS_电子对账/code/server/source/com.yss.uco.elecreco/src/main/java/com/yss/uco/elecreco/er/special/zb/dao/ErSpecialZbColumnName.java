package com.yss.uco.elecreco.er.special.zb.dao;
public enum ErSpecialZbColumnName {

	/**
	*主键ID
	*/
	id("C_IDEN"),

	/**
	*指标代码
	*/
	c_KEY_CODE("C_KEY_CODE"),

	/**
	*指标名称
	*/
	c_KEY_NAME("C_KEY_NAME"),

	/**
	*科目代码
	*/
	c_KM_CODE("C_KM_CODE"),

	/**
	*资产类别
	*/
	c_DAT_CLS("C_DAT_CLS"),

	/**
	*修改人
	*/
	modifier("C_UPDATE_BY"),

	/**
	*检查时间
	*/
	auditDate("C_CHECK_TIME"),

	/**
	*修改时间
	*/
	modifyDate("C_UPDATE_TIME"),

	/**
	*审核状态
	*/
	auditState("N_CHECK_STATE"),

	/**
	*检查人
	*/
	operator("C_CHECK_BY"),

	/**
	*资产类别名称
	*/
	c_DAT_CLS_NAME("C_DAT_CLS_NAME"),

	endUseDate(""),

	startUseDate("");

	private String value;

	private ErSpecialZbColumnName(String value) {
		this.value = value;
	}
	public String toString() {
		return this.value.toString();
	}
}