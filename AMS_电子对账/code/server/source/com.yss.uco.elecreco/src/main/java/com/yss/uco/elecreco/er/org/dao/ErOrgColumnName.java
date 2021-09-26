package com.yss.uco.elecreco.er.org.dao;

/**
 * 机构表相关字段
 * @author 马向峰 拆分
 * @Date 20170531
 *
 */
public enum ErOrgColumnName {

	/**
	 * 机构代码
	 */
	c_ORG_CODE("C_ORG_CODE"),

	/**
	 * 机构名称
	 */
	c_ORG_NAME("C_ORG_NAME"),

	/**
	 * 中文名称
	 */
	c_ORG_NAME_CN("C_ORG_NAME_CN"),

	/**
	 * 机构简称
	 */
	c_ORG_NAME_ST("C_ORG_NAME_ST"),

	/**
	 * 母公司
	 */
	c_ORG_CODE_P("C_ORG_CODE_P"),
	
	/**
	 ** 机构类型
	 */
	c_DV_ORG_TYPE("C_DV_ORG_TYPE"),
	
	/**
	 ** 公司代码
	 */
	c_CORP_CODE("C_CORP_CODE"),

	/**
	 ** 管理人
	 */
	c_DV_MANAGER("C_DV_MANAGER"),

	/**
	 ** 托管人
	 */
	c_DV_TRUSTEE("C_DV_TRUSTEE"),

	/**
	 ** 次托管人
	 */
	c_DV_TRUSTEE_SEC("C_DV_TRUSTEE_SEC"),

	

	id("C_IDEN"),

	startUseDate(""), endUseDate(""),

	operator("C_CHECK_BY"),

	auditDate("C_CHECK_TIME"),

	modifier("C_UPDATE_BY"),

	modifyDate("C_UPDATE_TIME"),

	auditState("N_CHECK_STATE"),
		
	/**
	 *  BUG #147121 【云平台万联证券】主体信息设置，资质为外包服务机构的，无法关联到组合 
	 *  主托管人
	 */
	c_DV_TRUSTEE_MA("C_DV_TRUSTEE_MA");

	private String value;

	private ErOrgColumnName(String value) {
		this.value = value;
	}

	public String toString() {
		return this.value.toString();
	}

}
