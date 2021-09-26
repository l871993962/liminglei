package com.yss.ams.base.information.modules.sys.sclindex.dao;

/**
 * 合规指标项目字典T_S_CL_INDEX 列名
 *
 */
public enum SClIndexColumnName {

	operator("C_CHECK_BY"),

	auditDate("C_CHECK_TIME"),

	modifier("C_UPDATE_BY"),

	modifyDate("C_UPDATE_TIME"),

	auditState("N_CHECK_STATE"),
	
	id("C_IDEN"),

	/**
	 * 指标代码
	 */
	c_INDEX_CODE("C_INDEX_CODE"),

	/**
	 * 指标名称
	 */
	c_INDEX_NAME("C_INDEX_NAME"),

	/**
	 * 指标类型
	 */
	c_INDEX_TYPE("C_INDEX_TYPE"),

	/**
	 * 指标分类
	 */
	c_INDEX_CLS("C_INDEX_CLS"),

	/**
	 * 指标上级代码
	 */
	c_INDEX_CODE_P("C_INDEX_CODE_P"),

	/**
	 * 序号
	 */
	n_ORDER("N_ORDER"),

	/**
	 * 参考违规阀值
	 */
	c_VALUE_DEF("C_VALUE_DEF");

		
	private String value ;

	private SClIndexColumnName(String value){
	this.value = value;
	}

	public String toString(){
	return this.value.toString();
	}
}
