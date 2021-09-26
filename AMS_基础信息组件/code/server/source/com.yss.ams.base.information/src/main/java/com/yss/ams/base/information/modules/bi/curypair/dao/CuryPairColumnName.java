package com.yss.ams.base.information.modules.bi.curypair.dao;

/**
 * 货币对  数据库表字段
 * @author 马向峰  拆分  2017.0527 
 *
 */
public enum CuryPairColumnName {

	/**
	* 货币对代码 
	*/
	c_CURY_PAIR_CODE("C_CURY_PAIR_CODE"),

	/**
	* 货币对名称 
	*/
	c_CURY_PAIR_NAME("C_CURY_PAIR_NAME"),

	/**
	* 基准货币 
	*/
	c_DC_CODE_MARK("C_DC_CODE_MARK"),

	/**
	* 计价货币 
	*/
	c_DC_CODE_PRICE("C_DC_CODE_PRICE"),

	/**
	* 报价因子 
	*/
	n_QTE_FACTO("N_QTE_FACTO"),

	/**
	* 描述 
	*/
	c_DESC("C_DESC"),



	id("C_IDEN"),
	
	endUseDate(""),
	
	startUseDate(""),

	operator("C_CHECK_BY"),

	auditDate("C_CHECK_TIME"),

	modifier("C_UPDATE_BY"),

	modifyDate("C_UPDATE_TIME"),

	auditState("N_CHECK_STATE");

	private String value ;

	private CuryPairColumnName(String value){
	this.value = value;
	}

	public String toString(){
	return this.value.toString();
	}

}
