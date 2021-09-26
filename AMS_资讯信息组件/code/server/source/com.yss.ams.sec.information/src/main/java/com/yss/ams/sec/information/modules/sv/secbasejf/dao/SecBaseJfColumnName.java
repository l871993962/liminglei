package com.yss.ams.sec.information.modules.sv.secbasejf.dao;

/*
 * added by HeLiang.2016-09-08.STORY #31596 运营费用-支持资产净值扣不计费证券需求 
 */

/**
 * 计费证券信息表列名和字段对应关系
 * @author yuankai 
 * 资讯信息拆分	2017.7.3 STORY #42948 资讯信息管理组件化拆分
 */
public enum SecBaseJfColumnName {

	/**
	 * 证券代码
	 */
	c_SEC_CODE("C_SEC_CODE"),

	/**
	 * 证券名称
	 */
	c_SEC_NAME("C_SEC_NAME"),

	/**
	 * 上市代码
	 */
	c_SEC_MKT_CODE("C_SEC_MKT_CODE"),

	/**
	 * 证券品种代码
	 */
	c_SEC_VAR_CODE("C_SEC_VAR_CODE"),

	/**
	 * 是否计提
	 */
	c_SFJT("C_SFJT"),
	
	/**
	 * 组合代码
	 */
	c_PORT_CODE("C_PORT_CODE"),
	
	id("C_IDEN"),
	
	operator("C_CHECK_BY"),

	auditDate("C_CHECK_TIME"),

	modifier("C_UPDATE_BY"),

	modifyDate("C_UPDATE_TIME"),

	auditState("N_CHECK_STATE");

	private String value;

	private SecBaseJfColumnName(String value) {
		this.value = value;
	}

	public String toString() {
		return this.value.toString();
	}

}
