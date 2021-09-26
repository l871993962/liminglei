package com.yss.ams.sec.information.modules.mp.secTransfer.dao;


/**
 * #42948 资讯信息管理组件化拆分
 * @author chenbo
 *2017-07-06
 */
/**
 * @ClassName SecTransferColumnName
 * @Description 证券代码转换ColumnName
 * @author guohui
 * @CreateDate 2016年10月24日下午2:32:26
 * @Version V4.5.0.1 
 * @Copyright (c) 2016, 深圳赢时胜 All Rights Reserved.
 */
public enum SecTransferColumnName {
	/**
	 * 证券代码
	 */
	c_SEC_CODE("C_SEC_CODE"),
	
	/**
	 * 转换规则
	 */
	c_TRANSFER_CODE("C_TRANSFER_CODE"),
	
	/**
	 * 披露代码
	 */	
	c_PUB_CODE("C_PUB_CODE"),
	
	/**
	* 数据来源
	*/
	c_DATA_IDF("C_DATA_IDF"),
	
	/**
	 * 描述
	 */
	c_DESC("C_DESC"),
	
	c_TYPE("C_TYPE"),
	
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

	private SecTransferColumnName(String value) {
		this.value = value;
	}

	public String toString() {
		return this.value.toString();
	}
}
