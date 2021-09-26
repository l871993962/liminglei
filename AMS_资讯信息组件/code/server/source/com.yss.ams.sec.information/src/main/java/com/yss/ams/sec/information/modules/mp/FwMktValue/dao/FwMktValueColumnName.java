package com.yss.ams.sec.information.modules.mp.FwMktValue.dao;

/**
 * 远期外汇行情 数据库表对应字段
 * @author gongyue
 * 资讯信息拆分	2017.7.5 STORY #42948 资讯信息管理组件化拆分
 */
public enum FwMktValueColumnName {

	/**
	 * 货币对
	 */
	c_SEC_CODE("C_SEC_CODE"),

	/**
	 * 行情日期
	 */
	d_MKT("D_MKT"),
	
	/**
	 * 行情状态 add by wangpeixu 2016-11-25 
	 * STORY23212YSS公共行情表增加行情标识区分停牌、停牌除权以及无成交
	 */
	c_HQZT_CODE("N_STATE"),

	/**
	 * 行情分类
	 */
	c_MKT_CLS("C_MKT_CLS"),

	/**
	 * 品种期限
	 */
	c_DV_VAR_DUR("C_DV_VAR_DUR"),

	/**
	 * 行情时间
	 */
	c_MKT_TIME("C_MKT_TIME"),

	/**
	 * 即期日期
	 */
	d_SPOT("D_SPOT"),

	/**
	 * 远期日期
	 */
	d_FW("D_FW"),

	/**
	 * 买入价
	 */
	n_PRICE_BUY("N_PRICE_BUY"),

	/**
	 * 卖出价
	 */
	n_PRICE_SELL("N_PRICE_SELL"),

	/**
	 * 买入点数
	 */
	n_POINT_BUY("N_POINT_BUY"),

	/**
	 * 卖出点数
	 */
	n_POINT_SELL("N_POINT_SELL"),

	/**
	 * 描述
	 */
	c_DESC("C_DESC"),

	/**
	 * 数据来源
	 */
	c_DATA_IDF("C_DATA_IDF"),

	id("C_IDEN"),
	
	endUseDate(""),
	
	startUseDate(""),

	creator("C_CREATE_BY"),

	createDate("C_CREATE_TIME"),

	operator("C_CHECK_BY"),

	auditDate("C_CHECK_TIME"),

	modifier("C_UPDATE_BY"),

	modifyDate("C_UPDATE_TIME"),

	auditState("N_CHECK_STATE");

	private String value;

	private FwMktValueColumnName(String value) {
		this.value = value;
	}

	public String toString() {
		return this.value.toString();
	}

}
