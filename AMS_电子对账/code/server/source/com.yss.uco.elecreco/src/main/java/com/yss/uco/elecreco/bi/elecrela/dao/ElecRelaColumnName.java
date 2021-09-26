package com.yss.uco.elecreco.bi.elecrela.dao;

/**
 * 电子对账指标关联映射关系
 * @author chenyoulong 201221213
 * @version v1.0.0.6
 *
 */
public enum ElecRelaColumnName {

	//STORY53570嘉实基金-电子对账-月报 产品分类配置   新增财务报表字段
	c_REPORT_CODE("C_REPORT_CODE"),
	c_ROW("C_ROW"),
	//BUG229285【融通基金】电子对账指标关联报错
	c_REPORT_NAME(""),
	//STORY47037嘉实基金电子对账管理查看托管人反馈的信息时，分级指标名称需要展示至具体的分级代码   增加字段级别类型
	c_DV_PORT_CLS("C_DV_PORT_CLS"),
	
	c_ZB_CODE("C_ZB_CODE"),
	
	c_ZB_NAME("C_ZB_NAME"),
	
	c_DZ_CODE("C_DZ_CODE"),
	
	c_PORT_CODE_CLS("C_PORT_CODE_CLS"),
	
	c_DV_PORT_CLS_TYPE("C_DV_PORT_CLS_TYPE"),
	
	c_DV_PORT_CLS_LEVEL("C_DV_PORT_CLS_LEVEL"),
	
	c_DV_ZB_CODE("C_DV_ZB_CODE"),
	
	c_ORG_CODE("C_ORG_CODE"),
	
	id("C_IDEN"),

	creator("C_CREATE_BY"),

	createDate("C_CREATE_TIME"),

	operator("C_CHECK_BY"),

	auditDate("C_CHECK_TIME"),

	modifier("C_UPDATE_BY"),

	modifyDate("C_UPDATE_TIME"),

	auditState("N_CHECK_STATE"),
	
	/**
	* 开始日期 
	*/
	startUseDate(""),

	/**
	* 结束日期 
	*/
	endUseDate("");

	private String value ;

	private ElecRelaColumnName(String value){
	this.value = value;
	}

	public String toString(){
	return this.value.toString();
	}

}
