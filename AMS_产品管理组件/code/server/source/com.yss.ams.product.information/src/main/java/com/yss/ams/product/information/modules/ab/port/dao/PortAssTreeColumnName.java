package com.yss.ams.product.information.modules.ab.port.dao;

/**
 * <产品基本信息>组合树列名定义类
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
public enum PortAssTreeColumnName {
	/**
	 * 组合代码
	 */
	c_PORT_CODE("C_PORT_CODE"),

	/**
	 * 组合名称
	 */
	c_PORT_NAME("C_PORT_NAME"),

	/**
	 * 组合简称
	 */
	c_PORT_NAME_ST("C_PORT_NAME_ST"),

	/**
	 * 投资组合英文名字
	 */
	c_PORT_NAME_EN("C_PORT_NAME_EN"),

	/**
	 * 资产代码
	 */
	c_ASS_CODE("C_ASS_CODE"),
	/**
	 * 资产种类
	 */
	c_ASSETS_CODE("C_ASSETS_CODE"),
	
	/**
	 * 资产类别
	 */
	c_DAT_CLS("C_DAT_CLS"),  //ADD BY ZHAOXIANLIN 20130514 STORY #3659 关于资产类型改造需求

	/**
	 * 资产类型
	 */
	c_DAT_CODE("C_DAT_CODE"),

	/**
	 * 组合币种
	 */
	c_DC_CODE("C_DC_CODE"),

	/**
	 * 成立日期
	 */
	d_BUILD("D_BUILD"),

	/**
	 * 终止日期
	 */
	d_CLOSE("D_CLOSE"),
	
	/**
	 * 清算日期
	 */
	d_CLEAR("D_CLEAR"),

	/**
	 * 描述
	 */
	c_DESC("C_DESC"),

	/**
	 * 组合级别
	 */
	c_DV_PORT_CODE("C_DV_PORT_CODE"),

	/**
	 * 上级组合代码
	 */
	c_PORT_CODE_P("C_PORT_CODE_P"),

	/**
	 * 节假日群代码
	 */
	c_HDAY_CODE("C_HDAY_CODE"),
	
	/**
	 * 产品状态
	 */
	c_DV_PROD_STATE("C_DV_PROD_STATE"),
	/**
	 * 关账日期
	 */
	d_COLSE_ACC("D_COLSE_ACC"),
	
	/**
	 * STORY #68355 【南方资本】产品基本信息新增字段备案日期  by maweiye  20190315
	 * 备案日期
	 */
	d_RECORD("D_RECORD"), 
	id("C_IDEN"),

	endUseDate(""), startUseDate(""),

	dATA_TYPE("DATA_TYPE"),

	operator("C_CHECK_BY"),

	auditDate("C_CHECK_TIME"),

	modifier("C_UPDATE_BY"),

	modifyDate("C_UPDATE_TIME"),

	auditState("N_CHECK_STATE");

	private String value;

	private PortAssTreeColumnName(String value) {
		this.value = value;
	}

	public String toString() {
		return this.value.toString();
	}
}
