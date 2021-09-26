package com.yss.ams.sec.information.modules.plateset.platesub.dao;

/**
 * STORY #33682 彭博证券信息接口_重新设计
 * xiaozhilong 20161122
 */
public enum PlateSubColumnName {

	/**
	* 板块代码 
	*/
	c_PLATE_CODE("C_PLATE_CODE"),

	/**
	* 证券代码 
	*/
	c_SEC_CODE("C_SEC_CODE"),

	/**
	* 描述 
	*/
	c_DESC("C_DESC"),

	/**
	* 交易市场代码 
	*/
	c_MKT_CODE("C_MKT_CODE"),

	/**
	* 总股本 
	*/
	c_Capital("C_Capital"),

	/**
	* 流通股本 
	*/
	c_Cir_Capital("C_Cir_Capital"),

	/**
	* 开始日期 
	*/
	startUseDate("D_BEGIN"),

	/**
	* 结束日期 
	*/
	endUseDate("D_END"),
	
	/**
	* 证券名称 
	*/
	c_SEC_NAME("C_SEC_NAME"),


	id("C_IDEN"),

	operator("C_CHECK_BY"),

	auditDate("C_CHECK_TIME"),

	modifier("C_UPDATE_BY"),

	modifyDate("C_UPDATE_TIME"),

	auditState("N_CHECK_STATE");

	private String value ;

	private PlateSubColumnName(String value){
	this.value = value;
	}

	public String toString(){
	return this.value.toString();
	}

}
