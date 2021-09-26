package com.yss.ams.base.information.modules.bi.srcsign.dao;

/**
 * 来源标识表字段
 * @author 马向峰 拆分
 *@Date 20170531
 */
public enum SrcSignColumnName {

	/**
	* 来源标识代码 
	*/
	c_SRC_SIGN_CODE("C_SRC_SIGN_CODE"),

	/**
	* 来源标识名称 
	*/
	c_SRC_SIGN_NAME("C_SRC_SIGN_NAME"),

	/**
	* 描述 
	*/
	c_DESC("C_DESC"),

	endUseDate(""),
	
	startUseDate(""),

	id("C_IDEN"),

	operator("C_CHECK_BY"),

	auditDate("C_CHECK_TIME"),

	modifier("C_UPDATE_BY"),

	modifyDate("C_UPDATE_TIME"),

	auditState("N_CHECK_STATE");

	private String value ;

	private SrcSignColumnName(String value){
	this.value = value;
	}

	public String toString(){
	return this.value.toString();
	}

}
