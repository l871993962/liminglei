package com.yss.ams.base.information.modules.sys.dtatdattr.dao;

/**
 * 交易属性字典T_S_DTA_TD_ATTR
 *
 */
public enum DtatdAttrTableName {
	userInfo("T_S_DTA_TD_ATTR"),
	recycle("T_S_DTA_TD_ATTR_R");
	private String value;

	private DtatdAttrTableName(String value) {
		this.value = value;
	}

	public String toString() {
		return this.value.toString();
	}
}
