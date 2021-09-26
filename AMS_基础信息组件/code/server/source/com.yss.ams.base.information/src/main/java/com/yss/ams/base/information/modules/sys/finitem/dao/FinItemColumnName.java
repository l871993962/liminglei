package com.yss.ams.base.information.modules.sys.finitem.dao;

/**
 * 财务项目字典表T_S_FIN_ITEM 列名
 *
 */
public enum FinItemColumnName {
	/**
	 * 财务项目代码
	 */
	c_FIN_CODE("C_FIN_CODE"),
	
	/**
	 * 财务项目名称
	 */
	c_FIN_NAME("C_FIN_NAME"),
	
	/**
	 * 财务项目父级代码
	 */
	c_FIN_CODE_P("C_FIN_CODE_P"),
	
	/**
	 * 序号
	 */
	n_ORDER("N_ORDER");

	private String value ;

	private FinItemColumnName(String value){
	this.value = value;
	}

	public String toString(){
	return this.value.toString();
	}

}
