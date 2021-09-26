package com.yss.uco.elecreco.er.spilt.rule.dao;
public enum ErSplitRuleColumnName {

	/**
	*id
	*/
	id("C_IDEN"),

	/**
	*关联的拆分映射关系
	*/
	c_IDEN_RELA("C_IDEN_RELA"),

	/**
	*科目代码
	*/
	c_KM_CODE("C_KM_CODE"),

	/**
	*科目名称
	*/
	c_KM_NAME("C_KM_NAME"),

	/**
	*审核状态
	*/
	auditState("N_CHECK_STATE"),

	/**
	*修改人
	*/
	modifier("C_UPDATE_BY"),

	/**
	*修改时间
	*/
	modifyDate("C_UPDATE_TIME"),

	/**
	*检查人
	*/
	operator("C_CHECK_BY"),

	/**
	*检查时间
	*/
	auditDate("C_CHECK_TIME"),
	
	endUseDate(""),

	startUseDate("");

	private String value;

	private ErSplitRuleColumnName(String value) {
		this.value = value;
	}
	public String toString() {
		return this.value.toString();
	}
}