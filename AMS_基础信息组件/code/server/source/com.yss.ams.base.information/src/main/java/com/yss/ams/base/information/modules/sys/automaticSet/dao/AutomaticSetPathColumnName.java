package com.yss.ams.base.information.modules.sys.automaticSet.dao;

/**
 * 自动化业务设置数据库字段枚举类
 * 
 * @ClassName: AutomaticSetPathColumnName
 * @date 2021年06月01日
 * @Stroy105821
 * @author zhuziqing
 */
public enum AutomaticSetPathColumnName {

	/**
	 * 渠道明细代码
	 */
	c_CHANEL_CODE("C_CHANEL_CODE"),
	/**
	 * 渠道明细类型
	 */
	c_CHANEL_TYPE("C_CHANEL_TYPE"),

	/**
	 * 产品业务代码
	 */
	c_PRODUCT_CODE("C_PRODUCT_CODE"),
	/**
	 * 产品业务分类
	 */
	c_PRODUCT_NAME("C_PRODUCT_NAME"),
	/**
	 * 组合代码
	 */
	c_PORT_CODE("C_PORT_CODE"),
	/**
	 * 组合名称
	 */
	c_PORT_NAME("C_PORT_NAME"),
	/**
	 * 接口分组
	 */
	c_INTERFACE_GROUP("C_INTERFACE_GROUP"),
	/**
	 * 接口代码
	 */
	c_INTERFACE_CODE("C_INTERFACE_CODE"),
	/**
	 * 接口名称
	 */
	c_INTERFACE_NAME("C_INTERFACE_NAME"),
	/**
	 * 接口父级ID
	 */
	c_INTERFACE_P_ID("C_INTERFACE_P_ID"),	
	/**
	 * 接口对应的路径
	 */
	c_INTERFACE_PATH("C_INTERFACE_PATH"),
	/**
	 * 指标编号
	 */
	c_INDEX_ORDER("C_INDEX_ORDER"),
	/**
	 * 指标代码
	 */
	c_INDEX_CODE("C_INDEX_CODE"),
	/**
	 * 指标名称
	 */
	c_INDEX_NAME("C_INDEX_NAME"),
	/**
	 * 估值表别名
	 */
	c_VA_ALIAS("C_VA_ALIAS"),
	/**
	 * 所属估值表日期
	 */
	c_VA_TIME("C_VA_TIME"),
	/**
	 * 业务类型代码
	 */
	c_BUSINESS_TYPE_CODE("C_BUSINESS_TYPE_CODE"),

	id("C_IDEN"),

	modifier("C_UPDATE_BY"),

	modifyDate("C_UPDATE_TIME"),

	auditState("N_CHECK_STATE"),

	operator("C_CHECK_BY"),

	auditDate("C_CHECK_TIME");

	private String value;

	private AutomaticSetPathColumnName(String value) {
		this.value = value;
	}

	public String toString() {
		return value;
	}
}
