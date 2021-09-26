package com.yss.ams.base.information.modules.bi.account.dao;

/**
 * @ClassName FundAccUnifypayColumnName
 * @Description 产品账户信息
 * @author liminghong@ysstech.com
 * @CreateDate 2017年5月22日
 * @Version V1.21.5.0
 * @Copyright (c) 2017, 深圳赢时胜 All Rights Reserved.
 */
public enum FundAccColumnName {
	
	c_PORT_CODE("C_PORT_CODE"),
	
    c_FLOW_ACC_NO("C_FLOW_ACC_NO"),
	
	c_OPEN_ACC_NAME("C_OPEN_ACC_NAME"),

	c_CA_CODE("C_CA_CODE"),

	c_DC_CODE("C_DC_CODE"),

	c_OPEN_ADDR("C_OPEN_ADDR"),

	c_OPEN_ACC_NO("C_OPEN_ACC_NO"),
	
	c_ORG_CODE("C_ORG_CODE"),
	
	////c_SYS_CODE("C_SYS_CODE"),
	
	c_USAGE("C_USAGE"),

	c_DESC("C_DESC"),
	
	c_HOLDER("C_HOLDER"),
	
	c_ASS_CODE("C_ASS_CODE"),
	
	c_ACCOUNT_TYPE("C_ACCOUNT_TYPE"),

	c_SOURCE_FLAG(""),
	
	//BUG #157433 清算指令界面报错
	//STORY #39954 支付产品账户信息list界面增加大额支付号列
	c_PAY_CODE("C_PAY_CODE"),
	
	/**
	 * STORY #34439 【招商基金】【紧急】QDII：划款指令模板设计 
	 */
	c_INTER_ORG_CODE("C_INTER_ORG_CODE"),
	
	id("C_IDEN"),
	
	/**
	 * 开户省份
	 * BUG #173020 支付产品账户信息：点击新增，维护‘开户省份’和‘开户城市’后，点击保存，未成功保存‘开户省份’和‘开户城市’
	 */
	c_PROVINCE("C_PROVINCE"),
	
	/**
	 * 开户城市
	 * BUG #173020 支付产品账户信息：点击新增，维护‘开户省份’和‘开户城市’后，点击保存，未成功保存‘开户省份’和‘开户城市’
	 */
	c_CITY("C_CITY"),
	
	c_OPEN_JC("C_OPEN_JC"),
	
	/**
	* 开始日期 
	*/
	startUseDate(""),

	/**
	* 结束日期 
	*/
	endUseDate(""),
	
	/**
	 * 开户日期
	 */
	d_BEGIN("D_BEGIN"),
	
	/**
	 * 关户日期
	 */
	d_END("D_END"),
	
	c_HAVEUSED("C_HAVEUSED"),
	
	/**
	 * 附件数量
	 */
	n_FILE_COUNT(""),
	
	/**
	 * 开户方式
	 */
	c_OPEN_MODE("C_OPEN_MODE"),
	
	/**
	 * 中行机构号
	 */
	c_BC_ORG_CODE("C_BC_ORG_CODE"),
	
	/**
	 * 流水账号
	 */
	c_RUNNING_ACC("C_RUNNING_ACC"),
	
	/**
	 * 中行联行号
	 */
	c_BC_LINK_NO("C_BC_LINK_NO"),
	
	/**
	 * 虚拟号
	 */
	c_CNX("C_CNX"),
	
	/**
	 * 银行国际统一码
	 */
	c_SWIFT_CODE("C_SWIFT_CODE"),
	
	/**
	 * 开户行地址
	 */
	c_BANK_ADDR("C_BANK_ADDR"),
	
	/**
	 * 币种名称
	 */
	c_DC_NAME("C_DC_NAME"),
	
	/**
	 * 账户状态
	 */
	c_DISCARD_STATUS ("C_DISCARD_STATUS"),
	
	/**
	 * 划款密钥
	 */
	c_PAYMENT_KEY("C_PAYMENT_KEY"),
	
	/**
	 * 
	 */
	c_CREATE_BY("C_CREATE_BY"),
	
	creator("C_CREATE_BY"),

	createDate("C_CREATE_TIME"),

	operator("C_CHECK_BY"),

	auditDate("C_CHECK_TIME"),

	modifier("C_UPDATE_BY"),

	modifyDate("C_UPDATE_TIME"),

	auditState("N_CHECK_STATE");

	
	private String value ;

	private FundAccColumnName(String value){
	this.value = value;
	}

	public String toString(){
	return this.value.toString();
	}

}
