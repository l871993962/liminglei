package com.yss.uco.elecreco.er.reverse.manager.ignore.dao;
public enum IgnoreItemColumnName {

	/**
	*
	*/
	id("C_IDEN"),

	/**
	*对账类型
	*/
	c_FILE_TYPE("C_FILE_TYPE"),

	/**
	*产品组合
	*/
	c_PORT_CODE("C_PORT_CODE"),

	/**
	*托管机构
	*/
	c_TGH_CODE("C_TGH_CODE"),

	/**
	*忽略类型(HL_ROW:行忽略;HL_COL:列忽略;HL_CELL:单元格忽略)
	*/
	c_DV_IGNORE_TYPE("C_DV_IGNORE_TYPE"),

	/**
	*忽略方向(IGNORE_SCOPE_INNER：本方;IGNORE_SCOPE_OUT:对方)
	*/
	c_DV_IGNORE_SCOPE("C_DV_IGNORE_SCOPE"),

	/**
	*应用下级科目(SUB_SUIT_YES:是；SUB_SUIT_NO:否)
	*/
	c_DV_SUB_SUIT("C_DV_SUB_SUIT"),

	/**
	*列标识
	*/
	c_COL_FLAG("C_COL_FLAG"),

	/**
	*行标识
	*/
	c_ROW_FLAG("C_ROW_FLAG"),

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

	private IgnoreItemColumnName(String value) {
		this.value = value;
	}
	public String toString() {
		return this.value.toString();
	}
}