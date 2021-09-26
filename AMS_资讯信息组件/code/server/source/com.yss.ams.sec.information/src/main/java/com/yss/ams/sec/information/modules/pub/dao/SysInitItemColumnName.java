package com.yss.ams.sec.information.modules.pub.dao;

/**
 * chenbo 2017-08-19 TASK #332232 
 * 公共信息处理界面中的系统初始化下的接口拆分到资讯组件中，以提供每百元利息生成功能
 * 
 */
public enum SysInitItemColumnName {
	/**
	* 分级组合 
	*/
	c_DV_TYPE("C_DV_TYPE"),

	/**
	* 投资组合 
	*/
	c_DV_NAME("C_DV_TYPE"),

	/**
	* 开放类型 
	*/
	c_DV_CODE("C_DV_CODE"),
	
	/**
	* 开放类型 
	*/
	c_DV_ITEM_CODE("C_DV_ITEM_CODE"),

	/**
	* 开放日期 
	*/
	c_DESC("C_DESC"),

	/**
	* 年化收益率 
	*/
	n_OEDER("N_OEDER");



	private String value ;

	private SysInitItemColumnName(String value){
	this.value = value;
	}

	public String toString(){
	return this.value.toString();
	}

}
