package com.yss.ams.base.information.modules.sys.automaticSet.dao;

/** 
 * 自动化业务设置数据库字段枚举类
 * @ClassName: AutomaticSetColumnName
 * @date 2020年12月24日
 * @Stroy90952
 * @author yangze
 */
public enum AutomaticSetColumnName {
	/**
	 * 业务类型代码
	 */
	c_BUSINESS_TYPE_CODE("C_BUSINESS_TYPE_CODE"),
	
	/**
	 * 明细类型代码
	 */
	c_BUSINESS_CODE("C_BUSINESS_CODE"),
	
	/**
	 * 组合代码
	 */
	c_PORT_CODE("C_PORT_CODE"),
	
	id("C_IDEN"),
	
	modifier("C_UPDATE_BY"),

	modifyDate("C_UPDATE_TIME"),

	auditState("N_CHECK_STATE"),
	
	operator("C_CHECK_BY"),
	
	auditDate("C_CHECK_TIME");

	private String value;
	
	private AutomaticSetColumnName(String value) {
		this.value = value;
	}
	
	public String toString() {
		return value;
	}
}
