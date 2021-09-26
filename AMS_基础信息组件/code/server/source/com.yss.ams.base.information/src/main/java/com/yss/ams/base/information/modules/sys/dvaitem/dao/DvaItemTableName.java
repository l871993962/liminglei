package com.yss.ams.base.information.modules.sys.dvaitem.dao;

/**
 * 核算业务项字典T_S_DVA_ITEM
 *
 */
public enum DvaItemTableName {
	userInfo("T_S_DVA_ITEM"),
	recycle("T_S_DVA_ITEM_R");
	private String value;
	private DvaItemTableName(String value){
		this.value = value;
	}
	public String toString(){
		return this.value.toString();
	}
}
