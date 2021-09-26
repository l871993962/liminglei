package com.yss.ams.sec.information.modules.sv.fipay.dao;


/**
 * 债券历史付息信息表列名和字段对应关系
 * @author yuankai 
 * 资讯信息拆分	2017.7.3 STORY #42948 资讯信息管理组件化拆分
 */
public enum FiPayColumnName {

	/**
	* 债券代码 
	*/
	c_SEC_CODE("C_SEC_CODE"),

	/**
	* 调息日期 
	*/
	d_ADJ("D_ADJ"),

	/**
	* 票面利率 
	*/
	n_COUP_RATE("N_COUP_RATE"),

	/**
	* 剩余本金 
	*/
	n_REM_COR("N_REM_COR"),
	
	/**
	* 偿还数量
	*/
	n_REPAY_AMOUNT("N_REPAY_AMOUNT"),

	/**
	* 本次起息日 
	*/
	d_BEGIN("D_BEGIN"),

	/**
	* 本次截息日 
	*/
	d_END("D_END"),

	/**
	* 描述 
	*/
	c_DESC("C_DESC"),

	/**
	* 周期新利率 
	*/
	c_DV_BOOL_TYPE("C_DV_BOOL_TYPE"),

	/**
	* 交易市场 
	*/
	c_MKT_CODE("C_MKT_CODE"),
//  edit tdf BUG #115588 [紧急]浮动利率债券没有清算出财汇资讯数据 20150715
	/**
	* 数据来源
	*/
	c_DATA_IDF("C_DATA_IDF"),
	
	/**
	 * 申购期间收益
	 */
	c_SGQJSY(""),
	
	/**
	 * 是否期内还本
	 */
	n_QNHB("N_QNHB"),
	
	/**
	 * 期内还本日期
	 */
	d_QNHB("D_QNHB"),

	endUseDate(""),
	
	startUseDate(""),

	id("C_IDEN"),

	operator("C_CHECK_BY"),

	auditDate("C_CHECK_TIME"),

	modifier("C_UPDATE_BY"),

	modifyDate("C_UPDATE_TIME"),

	auditState("N_CHECK_STATE");

	private String value ;

	private FiPayColumnName(String value){
	this.value = value;
	}

	public String toString(){
	return this.value.toString();
	}

}
