package com.yss.ams.base.information.modules.sys.dttdmode.dao;

/**
 * 交易方式字典表T_S_DT_TD_MODE列名
 *
 */
public enum DtatdAttrDataColumnName {
	/**
	 * 交易方式代码
	 */
	c_DT_CODE("C_DT_CODE"),
	
	/**
	 * 交易方式名称
	 */
	c_DT_NAME("C_DT_NAME"), 
	
	/**
	 * 业务类型
	 */
	c_BUSI_TYPE("C_BUSI_TYPE"),
	
	/**
	 * 资金方向（1－流入, -1-流出，0－无)
	 */
	n_FUND_WAY("N_FUND_WAY"),
	
	/**
	 * 资本方向（1－流入, -1-流出，0－无)
	 */
	n_CAPI_WAY("N_CAPI_WAY"),
	
	/**
	 * 编号
	 */
	n_ORDER("N_ORDER"),
	
	id("");

	private String value;

	private DtatdAttrDataColumnName(String value) {
		this.value = value;
	}

	public String toString() {
		return this.value.toString();
	}
}
