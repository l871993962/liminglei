package com.yss.uco.elecreco.er.repcfg.dao;

public enum DzRepCfgColumnName {

	id("C_IDEN"),

	/**
	 * 机构代码
	 */
	c_ORG_CODE("C_ORG_CODE"),

	/**
	 * 机构名称
	 */
	c_ORG_NAME("C_ORG_NAME"),

	/**
	 * 电子对账报表
	 */
	c_DZ_CODE("C_DZ_CODE"),

	/**
	 * 电子对账报表名称
	 */
	c_DZ_NAME("C_DZ_NAME"),

	/**
	 * 财务报表
	 */
	c_REPORT_CODE("C_REPORT_CODE"),

	/**
	 * 财务报表名称
	 */
	c_REPORT_NAME("C_REPORT_NAME"),

	/**
	 * 组合代码
	 */
	c_PORT_CODE("C_PORT_CODE"),

	/**
	 * 组合名称
	 */
	c_PORT_NAME("C_PORT_NAME"),
	
	/**
	 * 资产类型
	 */
	c_DAT_CODE("C_DAT_CODE"),
	
	/**
	 * 报表类型
	 */
	c_RPT_TYPE("C_RPT_TYPE"),
	
	operator("C_CHECK_BY"),

	auditDate("C_CHECK_TIME"),

	modifier("C_UPDATE_BY"),

	modifyDate("C_UPDATE_TIME"),

	auditState("N_CHECK_STATE"),
	
	endUseDate(""),
	
	startUseDate("");
	
	private String value;

	private DzRepCfgColumnName(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value.toString();
	}
}
