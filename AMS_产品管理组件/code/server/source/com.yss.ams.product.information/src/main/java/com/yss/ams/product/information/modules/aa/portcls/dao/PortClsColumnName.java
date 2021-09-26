package com.yss.ams.product.information.modules.aa.portcls.dao;

/**
 * 分级产品字段枚举类
 * 
 * Added by shiliang,产品管理组件拆分2017-06-23
 * 
 * */
public enum PortClsColumnName {
	/**
	* 组合代码 
	*/
	c_PORT_CODE("C_PORT_CODE"),

	/**
	* 分级组合代码 
	*/
	c_PORT_CLS_CODE("C_PORT_CLS_CODE"),

	/**
	* 分级组合名称 
	*/
	c_PORT_CLS_NAME("C_PORT_CLS_NAME"),

	/**
	* 分级类型 
	*/
	c_DV_PORT_CLS_TYPE("C_DV_PORT_CLS_TYPE"),

	/**
	* 分级级别 
	*/
	c_DV_PORT_CLS_LEVEL("C_DV_PORT_CLS_LEVEL"),

	/**
	* 计算公式 
	*/
	c_ALGO_CODE("C_ALGO_CODE"),

	/**
	* 描述 
	*/
	c_DESC("C_DESC"),
	
	/**
	* 成立日期 
	*/
	d_TO_LIST("D_TO_LIST"),
	
	/**
	* 终止日期 
	*/
	d_OFF_LIST("D_OFF_LIST"),

	/**
	* 清盘日期 
	*/
	d_LIQUID_DATE("D_LIQUID_DATE"),
//	/**
//	* 成立日期 
//	*/
//	startUseDate(""),
//
//	/**
//	* 终止日期 
//	*/
//	endUseDate(""),

	/**
	* 级别类型 
	*/
	c_DV_PORT_CLS("C_DV_PORT_CLS"),

	/**
	* 分级币种 
	*/
	c_DC_CODE("C_DC_CODE"),
	
	/**
	* 上级分级组合 
	*/
	c_PORT_CLS_CODE_P("C_PORT_CLS_CODE_P"),
	
	/**
	 * 收益分配
	 */
	c_DV_INC_DIS("C_DV_INC_DIS"),
	
	/**
	 * 轧差
	 */
	c_DV_NETTING("C_DV_NETTING"),
	
	/**
	 * 级别轧差
	 */
	c_DV_LevelNETTING("C_DV_LEVELNETTING"),	
	
	/**
	 * 年化收益率
	 * @author tangshifeng 
	 * 20130613
	 */
	n_YEAR_INCOME("N_YEAR_INCOME"),
	
	/**
	 * 收益率公式
	 * @author tangshifeng 
	 * 20130613
	 */
	c_ALGO_CODE_I("C_ALGO_CODE_I"),
	
	/**
	 * 收益率类型
	 * @author meip
	 * 20131225
	 */
	c_INCOME_TYPE("C_INCOME_TYPE"),
	
	/**
	 * 比率公式
	 * @author meip
	 * 20140120
	 */
	c_FORMULA_CODE("C_FORMULA_CODE"),
	
	/**
	 * STORY #51721 光大证券-监管类信息完善 add by lujianhao 20180705
	 */
	c_XYPJ("C_XYPJ"),
	
	/**
	 * 期货公司
	 */
	c_DV_QHGS("C_DV_QHGS"),
	
	/**
	 * 分级编码
	 */
	c_FJBM("C_FJBM"),
	
	/**
	 * 销售对象
	 */
	c_XSDX("C_XSDX"),
	
	/**
	 * 同业客户类型
	 */
	c_TYKHLX("C_TYKHLX"),

	id("C_IDEN"),

	operator("C_CHECK_BY"),

	auditDate("C_CHECK_TIME"),

	modifier("C_UPDATE_BY"),

	modifyDate("C_UPDATE_TIME"),

	auditState("N_CHECK_STATE");

	private String value ;

	private PortClsColumnName(String value){
	this.value = value;
	}

	public String toString(){
	return this.value.toString();
	}

}
