package com.yss.uco.elecreco.er.erbbinfo.dao;

public enum ErBbInfoColumnName {
	//STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求）
	c_SPLIT_CODE("C_SPLIT_CODE"),
	//STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求）
	c_TGH_CODE("C_TGH_CODE"),
	
	c_DV_RESULT("C_DV_RESULT"),
	
	c_SUMMARY("C_SUMMARY"),
	
	id("C_IDEN"),

	c_SN("C_SN"),

	c_DV_ER_WAY("C_DV_ER_WAY"),
	
	/*组合代码*/
	c_PORT_CODE("C_PORT_CODE"), 
	
	c_ASS_CODE("C_ASS_CODE"), 
	
	c_FILE_TYPE("C_FILE_TYPE"),
	
	c_RPT_TYPE("C_RPT_TYPE"),
	
	c_STATE("C_STATE"),
	
	d_DATE("D_DATE"),
	
	operator("C_CHECK_BY"),

	auditDate("C_CHECK_TIME"),

	modifier("C_UPDATE_BY"),

	modifyDate("C_UPDATE_TIME"),

	auditState("N_CHECK_STATE"),
	
	endUseDate(""),
	
	startUseDate(""),
	
	c_ERR_INFO("C_ERR_INFO"),
	
	c_CONFIRM_ID("C_CONFIRM_ID"),
	
	/**
	 * STORY58759嘉实基金-电子对账-电子对账管理界面净值确认按钮修改
	 */
	c_LOCK_EXECUTE("C_LOCK_EXECUTE"),
	
	n_JZAUDIT_STATE("N_JZAUDIT_STATE"),
	
	c_CONFIRM_EXECUTE("C_CONFIRM_EXECUTE"),
	
	/**
	 * 托管行处理人
	 */
	c_DEALER("C_DEALER");
	
	private String value;

	private ErBbInfoColumnName(String value) {
		this.value = value;
	}

	public String toString() {
		return this.value.toString();
	}

}
