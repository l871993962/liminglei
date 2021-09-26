package com.yss.ams.base.information.modules.bi.ieLink.dao;

/**
 * @classDesc 收支链接设置
 * @version 1.0 2012-11-29
 * @author yh
 */

/**
 * @author yuankai 公共信息拆分 2017.5.31
 */
public enum IeLinkColumnName {
	
	/**
	 * 上级费用
	 */
	c_FEE_CODE_P("C_FEE_CODE_P"),
	
	/**
	 * 收支代码
	 */
	c_FEE_CODE("C_FEE_CODE"),
	
	/**
	 * 收支名称
	 */
	c_FEE_NAME(""),
	
	/**
	 * 收支项目
	 */
	c_IE_CODE("C_IE_CODE"),
	
	/**
	 * 来源标识
	 */
	c_SRC_MARK("C_SRC_MARK"),
	
	/**
	 * 描述
	 */
	c_DESC("C_DESC"),
	
	/**
	 * 收支名称
	 */
	id("C_IDEN"),
	
	endUseDate(""),
	
	startUseDate(""),

	operator("C_CHECK_BY"),

	auditDate("C_CHECK_TIME"),

	modifier("C_UPDATE_BY"),

	modifyDate("C_UPDATE_TIME"),

	auditState("N_CHECK_STATE");

	private String value ;

	private IeLinkColumnName(String value){
	this.value = value;
	}

	public String toString(){
	return this.value.toString();
	}

}
