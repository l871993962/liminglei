package com.yss.ams.product.information.modules.ab.port.dao;

/**
 * <产品基本信息>显示用组合树列名定义类
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
public enum PortTreeViewColumnName {
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
	 * 备案日期
	 * STORY #68355 【南方资本】产品基本信息新增字段备案日期  by maweiye  20190315
	 */
	d_RECORD("D_RECORD"),

	id("C_IDEN"),
	
	n_Level("N_LEVEL"),

	nodeCode("nodeCode"),

	parentCode("fParaentCode"),
	
	operator("C_CHECK_BY"),

	auditDate("C_CHECK_TIME"),

	modifier("C_UPDATE_BY"),

	modifyDate("C_UPDATE_TIME"),

	auditState("N_CHECK_STATE");

	private String value ;

	private PortTreeViewColumnName(String value){
	this.value = value;
	}

	public String toString(){
	return this.value.toString();
	}
}
