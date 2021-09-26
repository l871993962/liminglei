package com.yss.ams.base.information.modules.sys.dvaitem.dao;

/**
 * 核算业务项字典T_S_DVA_ITEM 列明
 *
 */
public enum DavItemColumnName {
	/**
	 * 核算项目代码
	 */
	c_DVA_ITEM_CODE("C_DVA_ITEM_CODE"),
	
	/**
	 * 核算项目名称
	 */
	c_DVA_ITEM_NAME("C_DVA_ITEM_NAME"),
	
	/**
	 * 父级代码
	 */
	c_DVA_ITEM_CODE_P("C_DVA_ITEM_CODE_P"),
	
	/**
	 * 序号
	 */
	n_ORDER("N_ORDER"),
	
	/**
	 * 是否明细项 1:明细项 0:非明细项
	 */
	n_DETAIL("N_DETAIL"),
	
	id("");
	private String value;
	private DavItemColumnName(String value){
		this.value = value;
	}
	public String toString(){
		return this.value.toString();
	}
	
}
