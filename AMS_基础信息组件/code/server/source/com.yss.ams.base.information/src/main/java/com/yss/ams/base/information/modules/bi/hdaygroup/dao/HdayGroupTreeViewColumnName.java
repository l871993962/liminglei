package com.yss.ams.base.information.modules.bi.hdaygroup.dao;


/**
 * 节假日群类型表列名和字段对应关系
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
public enum HdayGroupTreeViewColumnName {
	/**
	* 节假日群代码 
	*/
	c_HDAY_CODE("C_HDAY_CODE"),

	/**
	* 节假日群名称 
	*/
	c_HDAY_NAME("C_HDAY_NAME"),

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

	auditState("N_CHECK_STATE"),
	
	n_Level("N_LEVEL"),
	
	nodeCode("nodeCode"),
	
	parentCode("fParaentCode");

	private String value;

	private HdayGroupTreeViewColumnName(String value) {
		this.value = value;
	}

	public String toString() {
		return this.value.toString();
	}
}
