package com.yss.ams.base.information.modules.sys.cashflow.dao;

/**
 * 现金流标记字典表名
 * @author yuankai 基础信息拆分  2017.5.31
 *
 */
public enum CashFlowTableName {
	userInfo("T_S_CASH_FLOW"), recycle("R_S_CASH_FLOW");

	private String value;

	private CashFlowTableName(String value) {
		this.value = value;
	}

	/*
	 * 重写toString方法：获取枚举值
	 * 
	 * @see java.lang.Enum#toString()
	 */
	public String toString() {
		return this.value.toString();
	}
}
