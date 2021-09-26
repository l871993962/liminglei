package com.yss.ams.sec.information.modules.sv.secSoldBack.dao;


/**
 * #42948 资讯信息管理组件化拆分
 * @author chenbo
 *2017-07-06
 */
public enum SecSoldBackColumnName {
	
	id("C_IDEN"),
	
	/**
	 * 证券代码
	 */
	c_SEC_CODE("C_SEC_CODE"),
	
	/**
	 * 上市代码
	 */
	c_SEC_MKT_CODE("C_SEC_MKT_CODE"),
	
	/**
	 * 市场代码
	 */
	c_MKT_CODE("C_MKT_CODE"),
	
	/**
	 * 回售价格
	 */
	n_SOLDBACK_PRICE("N_SOLDBACK_PRICE"),
	
	/**
	 * 回售开始日期
	 */
	d_SOLDBACK_BEGIN("D_SOLDBACK_BEGIN"),
	
	/**
	 * 回售结束日期
	 */
	d_SOLDBACK_END("D_SOLDBACK_END"),
	
	/**
	 * 回售资金到账日期
	 */
	d_FINAL("D_FINAL"),
	
	operator("C_CHECK_BY"),

	auditDate("C_CHECK_TIME"),

	modifier("C_UPDATE_BY"),

	modifyDate("C_UPDATE_TIME"),

	auditState("N_CHECK_STATE");
	
	private String value ;
	
	private SecSoldBackColumnName(String value){
		this.value = value;
	}
	
	public String toString(){
		return this.value.toString();
	}

}
