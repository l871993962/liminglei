package com.yss.ams.sec.information.modules.mp.hggthq.dao;

/**
 * 回购收益行情 数据库表对应字段
 * @author gongyue
 * 资讯信息拆分	2017.7.5 STORY #42948 资讯信息管理组件化拆分
 */
public enum CounterRateColumnName {
	
	/**
	 * 行情日期
	 */
	d_MKT("D_MKT"),

	/**
	 * 启用日期
	 */
	d_BEGIN("D_BEGIN"),

	/**
	 * 停用日期
	 */
	d_END("D_END"),

	/**
	 * 回购期限
	 */
	n_DURATION("N_DURATION"),

	/**
	 * 回购利率
	 */
	n_RATE("N_RATE"),

	/**
	 * 业务类型
	 */
	c_BIZ_TYPE("C_BIZ_TYPE"),

	/**
	 * 公共 标志
	 */
	c_IS_PUBLIC("C_IS_PUBLIC"),

	/**
	 * 证券代码
	 */
	c_SEC_CODE("C_SEC_CODE"),
	
	id("C_IDEN"),

	endUseDate(""),
	
	startUseDate(""),
	
	operator("C_CHECK_BY"),

	auditDate("C_CHECK_TIME"),

	modifier("C_UPDATE_BY"),

	modifyDate("C_UPDATE_TIME"),

	auditState("N_CHECK_STATE");
	
	private String value ;
	
	private CounterRateColumnName(String value){
		this.value = value;
	}
	
	public String toString(){
		return this.value.toString();
	}
}
