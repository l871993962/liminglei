package com.yss.ifa.szt.tool.para.dao;

/**
 *  /// STORY42784中国银行_深证通伺服器要求采用热备模式
 *  /// STORY42660【中国银行】深证通伺服器要求采用热备模式
 * @ClassName: ErParaColumnName 
 * @Description: 深圳通参数设置
 * @author wulongxing
 * @date 2017年6月13日 下午2:51:31 
 *
 */
public enum ErParaColumnName {

	id("C_IDEN"),

	/**
	 * 参数代码
	 */
	c_Para_Code("c_Para_Code"),

	/**
	 * 参数名称
	 */
	c_Para_Name("c_Para_Name"),

	/**
	 * 源用户
	 */
	c_Src_UserId("c_Src_UserId"),

	/**
	 * 源应用
	 */
	c_Src_AppId("c_Src_AppId"),

	/**
	 * 发送密码
	 */
	c_Pkg_Password("c_Pkg_Password"),

	/**
	 * 公司代码
	 */
	c_Dept_Code("c_Dept_Code"),

	/**
	 * 证书ID
	 */
	c_Cert_Id("c_Cert_Id"),

	/**
	 * 伺服器配置信息
	 */
	mrInfoList(""),

	operator("C_CHECK_BY"),

	auditDate("C_CHECK_TIME"),

	modifier("C_UPDATE_BY"),

	modifyDate("C_UPDATE_TIME"),

	auditState("N_CHECK_STATE"),

	endUseDate(""),

	startUseDate("");

	private String value;

	private ErParaColumnName(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value.toString();
	}

}
