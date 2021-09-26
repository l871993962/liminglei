package com.yss.ams.sec.information.modules.aa.etf.dao;

/**
 * ETF基本信息 数据库表对应字段
 * @author gongyue
 * 资讯信息拆分	2017.7.5 STORY #42948 资讯信息管理组件化拆分
 */
public enum EtfColumnName{

	/**
	* 投资组合 
	*/
	c_PORT_CODE("C_PORT_CODE"),

	/**
	* 交易市场 
	*/
	c_MKT_CODE("C_MKT_CODE"),

	/**
	* 申赎席位 
	*/
	c_TD_CHAN_CODE("C_TD_CHAN_CODE"),

	/**
	* 补票方式 
	*/
	c_DV_SUPPLY_MODE("C_DV_SUPPLY_MODE"),

	/**
	* 台账显示模式 
	*/
	c_DV_BOOK_MODE("C_DV_BOOK_MODE"),

	/**
	* 申赎代码 
	*/
	c_SR_CODE("C_SR_CODE"),

	/**
	* 交易代码 
	*/
	c_TRADE_CODE("C_TRADE_CODE"),

	/**
	* 资金结算代码 
	*/
	c_SF_CODE("C_SF_CODE"),
	
	/**
	 * 补票汇率
	 */
	c_RATE_SUPPLY("C_RATE_SUPPLY"),
	
	/**
	 * 申赎汇率
	 */
	c_RATE_SR("C_RATE_SR"),

	/**
	* 描述 
	*/
	c_DESC("C_DESC"),

	/**
	* 基准份额 
	*/
	n_BASE_AMOUNT("N_BASE_AMOUNT"),

	/**
	* 开始日期 
	*/
	d_BEGIN("D_BEGIN"),

	/**
	* 结束日期 
	*/
	d_END("D_END"),

	/**
	* 确认天数 
	*/
	n_CONFIRM("N_CONFIRM"),

	/**
	* ETF类型 
	*/
	c_ETF_TYPE("C_ETF_TYPE"),
	
	
	/**
	 * ID
	 */
	id("C_IDEN"),
	
	/**
	 * 创建人
	 */
	operator("C_CHECK_BY"),
	
	/**
	 * 审核时间
	 */
	auditDate("C_CHECK_TIME"),
	
	/**
	 * 修改人
	 */
	modifier("C_UPDATE_BY"),
	
	/**
	 * 修改时间
	 */
	modifyDate("C_UPDATE_TIME"),
	
	/**
	* 开始日期 
	*/
	startUseDate("D_BEGIN"),

	/**
	* 结束日期 
	*/
	endUseDate("D_END"),
	
	/**
	 * 审核状态
	 */
	auditState("N_CHECK_STATE"),
	
	/**
	 * 股票篮溢价比
	 */
	n_INFER_PROTION("N_INFER_PROTION"),
	
	
	/**
	 * 席位类别 By Jinghehe 2014-8-14
	 */
	c_DV_TYPE_CODE("C_DV_TYPE_CODE"),
	
	etfStdList("");
	
	private String value;
	
	private EtfColumnName(String value){
		this.value = value;
	}
	
	public String toString(){
		return this.value.toString();
	}
}
