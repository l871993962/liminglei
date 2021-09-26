package com.yss.ams.base.information.modules.bi.salesnet.dao;

/**
 * 销售网点设置表列名和字段对应关系
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
public enum SalesNetColumnName {

	/**
	* TA销售网点代码 
	*/
	c_NET_CODE("C_NET_CODE"),

	/**
	* TA销售网点名称 
	*/
	c_NET_NAME("C_NET_NAME"),

	/**
	* TA销售网点类型 
	*/
	c_DV_NET_TYPE("C_DV_NET_TYPE"),
	
	/**
	* TA销售网点来源 
	*/
	c_NET_SOURCE("C_NET_SOURCE"),

	/**
	* TA销售网点类型名称 
	*/
	c_DESC("C_DESC"),
	/**
	 * 销售商代码
	 */
	c_VENDOR_CODE("C_VENDOR_CODE"),
	
	/**
	 * 渠道类型
	 */
	c_CHANNEL_TYPE("C_CHANNEL_TYPE"),


	id("C_IDEN"),
	
	endUseDate(""),
	
	startUseDate(""),

	operator("C_CHECK_BY"),

	auditDate("C_CHECK_TIME"),

	modifier("C_UPDATE_BY"),

	modifyDate("C_UPDATE_TIME"),

	auditState("N_CHECK_STATE");

	private String value ;

	private SalesNetColumnName(String value){
	this.value = value;
	}

	public String toString(){
	return this.value.toString();
	}

}
