package com.yss.ams.sec.information.modules.sv.indexinfo.dao;


/**
 * 
 * @author chenbo
 *2017-06-22
 *#42948 资讯信息管理组件化拆分
 */
public enum IndexInfoTableName {
	indexinfo("T_P_SV_INDEX"), recycle("R_P_SV_INDEX_R");

	private String value;

	private IndexInfoTableName(String value) {
		this.value = value;
	}

	public String toString() {
		return this.value.toString();
	}
}
