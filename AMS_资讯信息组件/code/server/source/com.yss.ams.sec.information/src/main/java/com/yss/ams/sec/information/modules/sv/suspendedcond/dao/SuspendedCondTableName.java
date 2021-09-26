package com.yss.ams.sec.information.modules.sv.suspendedcond.dao;


/**
 * #42948 资讯信息管理组件化拆分
 * @author chenbo
 *2017-07-06
 */
public enum SuspendedCondTableName {
	suspendedCond("T_P_SV_SUSPENDED_COND");

	private String value;

	private SuspendedCondTableName(String value) {
		this.value = value;
	}

	/*
	 * 重写toString方法：获取枚举�?
	 * 
	 * @see java.lang.Enum#toString()
	 */
	public String toString() {
		return this.value.toString();
	}
}
