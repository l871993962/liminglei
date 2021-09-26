package com.yss.ams.sec.information.modules.sv.base.dao;

/**
 * 期货结算债券转换信息 数据库表对应字段
 * @author gongyue
 * 资讯信息拆分	STORY #42948 资讯信息管理组件化拆分
 */
public enum FutureFactorColumnName {
	
	/**
	 * 合约代码
	 */
	c_CONTRACT_CODE("C_CONTRACT_CODE"),
	
	/**
	 * 债券代码
	 */
	c_SEC_CODE("C_SEC_CODE"),
	
	/**
	 * 交易市场
	 */
	c_MKT_CODE("C_MKT_CODE"),
	
	/**
	 * 转换因子
	 */
	n_CONVERT_FACTOR("N_CONVERT_FACTOR"),
	
	id("C_IDEN"),
	
	operator("C_CHECK_BY"),

	auditDate("C_CHECK_TIME"),

	modifier("C_UPDATE_BY"),

	modifyDate("C_UPDATE_TIME"),

	auditState("N_CHECK_STATE");

	private String value;

	private FutureFactorColumnName(String value){
	this.value = value;
	}

	public String toString(){
	return this.value.toString();
	}
}
