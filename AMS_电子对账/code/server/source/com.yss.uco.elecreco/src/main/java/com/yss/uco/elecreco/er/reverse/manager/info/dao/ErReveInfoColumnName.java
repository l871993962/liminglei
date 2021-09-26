package com.yss.uco.elecreco.er.reverse.manager.info.dao;
public enum ErReveInfoColumnName {

	c_DV_HANDLE_STATE("C_DV_HANDLE_STATE"),
	c_HANDLE_INFO("C_HANDLE_INFO"),
	/**
	*
	*/
	id("C_IDEN"),

	/**
	*对账编号
	*/
	c_SN("C_SN"),

	/**
	*对账方向
	*/
	c_DV_ER_WAY("C_DV_ER_WAY"),

	/**
	*投资组合
	*/
	c_PORT_CODE("C_PORT_CODE"),

	/**
	*对账类型
	*/
	c_FILE_TYPE("C_FILE_TYPE"),

	/**
	*报表类型
	*/
	c_RPT_TYPE("C_RPT_TYPE"),

	/**
	*反馈给托管行的次数
	*/
	n_FK_STATE("n_FK_STATE"),
	/**
	*供页面显示的反馈状态
	*/
	c_FK_STATE(""),

	/**
	*锁定状态（SDZT_NO不锁定，SDZT_YES锁定）
	*/
	c_DV_LOCK_STATE("C_DV_LOCK_STATE"),

	/**
	*对账日期
	*/
	d_DATE("D_DATE"),

	/**
	*对账结果(DZ_RESULT_SAME：对账一致;DZ_RESULT_DIFF：对账不一致)
	*/
	c_DV_DZ_RESULT("C_DV_DZ_RESULT"),
	
	/**
	*本方数据
	*/
	c_B_DATA(""),
	
	/**
	*对方数据
	*/
	c_D_DATA(""),

	/**
	*备注
	*/
	c_REMARK("C_REMARK"),

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

	private ErReveInfoColumnName(String value) {
		this.value = value;
	}
	public String toString() {
		return this.value.toString();
	}
}