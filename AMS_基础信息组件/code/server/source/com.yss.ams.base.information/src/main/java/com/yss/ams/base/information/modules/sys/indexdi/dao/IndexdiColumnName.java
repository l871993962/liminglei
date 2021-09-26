package com.yss.ams.base.information.modules.sys.indexdi.dao;

/**
 * 合规指标类型字典T_S_INDEX 列名
 *
 */
public enum IndexdiColumnName {

	c_INDEX_CODE("C_INDEX_CODE"),
	
	c_INDEX_NAME("C_INDEX_NAME"),
	
	c_DATA_SOURCE("C_DATA_SOURCE"),
	
	c_DATA_TYPE("C_DATA_TYPE"),
	
	n_STATE("N_STATE"),
	
	n_ORDER("N_ORDER"),
	
	c_NAV_TYPE("C_NAV_TYPE"),
	
	n_DETAIL("N_DETAIL"),
	
	c_KEY_CODE("C_KEY_CODE"),
	
	c_KEY_NAME("C_KEY_NAME"),
	
	c_IS_SYS("C_IS_SYS"),
	
	c_TRU("C_TRU"),
	
	c_MODE("C_MODE"),
	
	c_RET("C_RET"),
	
	id("C_IDEN");
	
	private String value;
	
	private IndexdiColumnName(String value){
		this.value = value;
	}
	
	public String toString(){
		return value;
	}
}
