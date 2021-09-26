package com.yss.ams.product.information.modules.cp.pubacc.dao;

/**
 * 公用账户信息设置数据设计枚举类
 * @author chenyoulong
 * @version v1.0.0.4
 * 20121106
 *
 */
public enum PubAccColumnName {

	c_DV_OPPO_RELA("c_DV_OPPO_RELA"),

	c_OPEN_ACC_NAME("c_OPEN_ACC_NAME"),

	c_DC_CODE("C_DC_CODE"),

	c_OPEN_ADDR("C_OPEN_ADDR"),

	c_OPEN_ACC_NO("C_OPEN_ACC_NO"),
	
	c_SYS_CODE("C_SYS_CODE"),
	
	c_USAGE("C_USAGE"),

	c_DESC("C_DESC"),
	
	id("C_IDEN"),
	
	/**
	* 开始日期 
	*/
	startUseDate(""),

	/**
	* 结束日期 
	*/
	endUseDate(""),

	creator("C_CREATE_BY"),

	createDate("C_CREATE_TIME"),

	operator("C_CHECK_BY"),

	auditDate("C_CHECK_TIME"),

	modifier("C_UPDATE_BY"),

	modifyDate("C_UPDATE_TIME"),

	auditState("N_CHECK_STATE");

	private String value ;

	private PubAccColumnName(String value){
	this.value = value;
	}

	public String toString(){
	return this.value.toString();
	}

}
