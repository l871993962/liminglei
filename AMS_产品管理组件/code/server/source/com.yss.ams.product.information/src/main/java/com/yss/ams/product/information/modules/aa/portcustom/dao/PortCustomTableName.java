package com.yss.ams.product.information.modules.aa.portcustom.dao;

/**
 * <用户自定义组合>表名定义类
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
public enum PortCustomTableName {
	portCustom("T_P_AB_PORT_CUSTOM"), 
	recycle("R_P_AB_PORT_CUSTOM_R");
	private String value = "";

	private PortCustomTableName(String value) {
		this.value = value;
	}

	public String toString() {
		return this.value.toString();
	}
}
