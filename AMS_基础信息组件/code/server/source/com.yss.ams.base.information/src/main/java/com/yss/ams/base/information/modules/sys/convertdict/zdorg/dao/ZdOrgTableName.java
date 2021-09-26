package com.yss.ams.base.information.modules.sys.convertdict.zdorg.dao;

public enum ZdOrgTableName {
	/**
	 * 表名
	 */
	corporg("T_V_D_GROUP"),
	/**
	 * 回收站对应表名-或者同义词名称
	 */
	recycle("R_V_D_GROUP_R");
	
	private String value ;
	
	private ZdOrgTableName(String value){
		this.value = value;
	}
	
	public String toString(){
		return this.value.toString();
	}
}
