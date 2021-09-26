package com.yss.uco.elecreco.er.spilt.rela.dao;
public enum ErSplitRelaColumnName {

	/**
	*id
	*/
	id("C_IDEN"),

	/**
	*投资组合
	*/
	c_PORT_CODE("C_PORT_CODE"),

	/**
	*托管银行
	*/
	c_TGH_CODE("C_TGH_CODE"),

	/**
	*拆分代码
	*/
	c_SPLIT_CODE("C_SPLIT_CODE"),

	/**
	*生效日期
	*/
	d_START_DATE("D_START_DATE"),

	/**
	*失效日期
	*/
	d_END_DATE("D_END_DATE"),

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

	private ErSplitRelaColumnName(String value) {
		this.value = value;
	}
	public String toString() {
		return this.value.toString();
	}
}