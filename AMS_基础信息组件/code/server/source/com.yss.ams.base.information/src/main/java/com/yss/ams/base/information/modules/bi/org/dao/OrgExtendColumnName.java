package com.yss.ams.base.information.modules.bi.org.dao;

public enum OrgExtendColumnName {

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
	* 法人代表 
	*/
	c_CORP_REP("C_CORP_REP"),

	/**
	* 资本币种 
	*/
	c_DC_CODE("C_DC_CODE"),

	/**
	* 注册资本 
	*/
	n_REG_CAP("N_REG_CAP"),

	/**
	* 注册地址 
	*/
	c_REG_ADDR("C_REG_ADDR"),

	/**
	* 办公地址 
	*/
	c_OFFIC_ADDR("C_OFFIC_ADDR"),

	/**
	* 机构类型 
	*/
	c_DV_ORG_TYPE("C_DV_ORG_TYPE"),

	/**
	* 公司代码 
	*/
	c_CORP_CODE("C_CORP_CODE"),

	/**
	* 联系人 
	*/
	c_LINK_MAN("C_LINK_MAN"),

	/**
	* 联系电话 
	*/
	c_LINK_TEL("C_LINK_TEL"),

	/**
	* 手机号码 
	*/
	c_MO_TEL("C_MO_TEL"),

	/**
	* 电子邮箱 
	*/
	c_EMAIL("C_EMAIL"),

	/**
	* 注册邮编 
	*/
	c_REG_POST("C_REG_POST"),

	/**
	* 机构描述 
	*/
	c_DESC("C_DESC"),

	/**
	* 办公邮编 
	*/
	c_OFFIC_POST("C_OFFIC_POST"),

	/**
	* 市场代码 
	*/
	c_MKT_CODE("C_MKT_CODE"),

	/**
	* 父节点 
	*/
	c_P_CODE("C_P_CODE"),

	c_DV_TRD_CLIENT("C_DV_TRD_CLIENT"),
	
	c_DV_BX_CLIENT("C_DV_BX_CLIENT"),
	
	/// 机构logo
    c_LOGO_NAME("C_LOGO_NAME"),
    /// 大额支付号
    c_PAY_CODE("C_PAY_CODE"),
    /// 联行号
    c_BANK_CODE("C_BANK_CODE"),
	
	id("C_IDEN"),
	
	endUseDate(""),
	
	startUseDate(""),

	operator("C_CHECK_BY"),

	auditDate("C_CHECK_TIME"),

	modifier("C_UPDATE_BY"),

	modifyDate("C_UPDATE_TIME"),

    auditState("N_CHECK_STATE");
	
	
	private String value ;

	private OrgExtendColumnName(String value){
	this.value = value;
	}

	public String toString(){
	return this.value.toString();
	}

}
