/**
 *
 * @Title: PortBusinessRelaColumnName.java 
 * @Package com.yss.ams.base.information.modules.sys.portbusinessrela.dao 
 * @date 2019年5月13日 下午5:41:23 
 * @version V1.0
 * @Stroy/Bug
 * @author xiadeqi   
 */
package com.yss.ams.base.information.modules.sys.portbusinessrange.dao;

/** 
 * 产品业务范围数据库字段枚举类
 * @ClassName: PortBusinessRelaColumnName 
 * @date 2019年5月13日 下午5:41:23
 * @Stroy72335/Bug
 * @author xiadeqi 
 */
public enum PortBusinessRangeColumnName {
	/**
	 * 组合代码
	 */
	c_PORT_CODE("C_PORT_CODE"),
	
	/**
	 * 业务类型代码
	 */
	c_BUSINESS_CODE("C_BUSINESS_CODE"),
	
	id("C_IDEN"),

	operator("C_CHECK_BY"),

	auditDate("C_CHECK_TIME"),

	modifier("C_UPDATE_BY"),

	modifyDate("C_UPDATE_TIME"),

	auditState("N_CHECK_STATE");
	
	private String value;
	
	private PortBusinessRangeColumnName(String value){
		this.value = value;
	}
	
	public String toString(){
		return value;
	}
}
