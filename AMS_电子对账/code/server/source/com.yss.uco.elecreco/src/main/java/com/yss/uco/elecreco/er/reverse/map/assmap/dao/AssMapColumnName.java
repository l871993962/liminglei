package com.yss.uco.elecreco.er.reverse.map.assmap.dao;
public enum AssMapColumnName {

	/**
	*
	*/
	id("C_IDEN"),

	/**
	*投资组合代码
	*/
	c_PORT_CODE("C_PORT_CODE"),

	/**
	*对账类型
	*/
	c_FILE_TYPE("C_FILE_TYPE"),

	/**
	*托管银行
	*/
	c_TGH_CODE("C_TGH_CODE"),

	/**
	*托管行资产代码
	*/
	c_PORT_CODE_OUT("C_PORT_CODE_OUT"),

	/**
	*对账模式（DZMODE_SZT：深圳通模式；DZMODE_QT：其他模式）
	*/
	c_DV_DZ_MODE("C_DV_DZ_MODE"),

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

	private AssMapColumnName(String value) {
		this.value = value;
	}
	public String toString() {
		return this.value.toString();
	}
}