package com.yss.ams.base.information.modules.sys.secvar.dao;

/**
 * 证券品种字典T_S_DA_SEC_VAR 列名
 *
 */
public enum SecVarExtendColumnName {

	/**
	* 证券品种代码 
	*/
	c_SEC_VAR_CODE("C_SEC_VAR_CODE"),

	/**
	* 证券品种名称 
	*/
	c_SEC_VAR_NAME("C_SEC_VAR_NAME"),

	/**
	* 证券属性代码 
	*/
	c_DA_CODE("C_DA_CODE"),

	/**
	* 品种属性的父级代码 
	*/
	c_DA_CODE_P("C_DA_CODE_P"),

	/**
	* 证券属性名称 
	*/
	c_DA_NAME("C_DA_NAME"),

	/**
	* 描述 
	*/
	c_DESC("C_DESC"),
	
	/**
	 * 顺序
	 * add by Yuntao Lau STORY #26999
	 */
	n_ORDER("N_ORDER"),


	id("C_IDEN"),

	operator("C_CHECK_BY"),

	auditDate("C_CHECK_TIME"),

	modifier("C_UPDATE_BY"),

	modifyDate("C_UPDATE_TIME"),

	auditState("N_CHECK_STATE"),
	

	/**
	* 开始日期 
	*/
	startUseDate(""),

	/**
	* 结束日期 
	*/
	endUseDate("");

	private String value ;

	private SecVarExtendColumnName(String value){
	this.value = value;
	}

	public String toString(){
	return this.value.toString();
	}

}
