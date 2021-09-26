package com.yss.ams.base.information.modules.bi.ieLink.dao;

/**
 * 收支费用表列名和字段对应关系
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
public enum FeeColumnName {

	/**
	* 费用代码 
	*/
	c_FEE_CODE("C_FEE_CODE"),

	/**
	* 费用名称 
	*/
	c_FEE_NAME("C_FEE_NAME"),

	/**
	* 费用类型 
	*/
	c_DV_FEE_TYPE("C_DV_FEE_TYPE"),

	/**
	* 来源标识 
	*/
	c_SRC_MARK("C_SRC_MARK"),

	/**
	* 费用描述 
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

	private FeeColumnName(String value){
	this.value = value;
	}

	public String toString(){
	return this.value.toString();
	}

}
