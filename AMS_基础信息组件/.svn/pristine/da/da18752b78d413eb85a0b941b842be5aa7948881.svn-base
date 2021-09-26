package com.yss.ifa.szt.tool.para.dao;

public enum DzParaColumnName {

	id("C_IDEN"),
	
	/**
	 * 机构许可信息
	 */
	c_DV_LICORG("C_DV_LICORG"),
	
	/**
	 * STORY60297【富国基金】支持电子对账参数设置支持民生银行多管理人不同秘钥配置
	 * 密钥
	 */
	c_SECRETKEY("C_SECRETKEY"),
	
	/**
	 * STORY60297【富国基金】支持电子对账参数设置支持民生银行多管理人不同秘钥配置
	 * 加密类型
	 */
	c_DV_SECRETTYPE("C_DV_SECRETTYPE"),
	
	/**
	 * STORY60297【富国基金】支持电子对账参数设置支持民生银行多管理人不同秘钥配置
	 * 报文编码
	 */
	c_DV_CHARSET("C_DV_CHARSET"),
	
	/**
	 *管理人机构
	 *STORY55269【富国基金】支持电子对账参数设置支持多管理人
	 */
	c_MANAGE_CODE("C_MANAGE_CODE"),

	/**
	 * 基金代码
	 */
	c_ASS_CODE("C_ASS_CODE"),

	/**
	 * 基金公司
	 */
	c_DEPT_CODE("C_DEPT_CODE"),

	/**
	 * 证书ID
	 */
	c_CERT_ID("C_CERT_ID"),

	/**
	 * 托管行代码
	 */
	c_TGH_CODE("C_TGH_CODE"),
	
	/**
	 * 托管行名称
	 */
	c_TGH_NAME("C_TGH_NAME"),

	/**
	 * 业务类型
	 */
	c_BUS_TYPE("C_BUS_TYPE"),

	/**
	 * 连接类型
	 */
	c_COMM_TYPE("C_COMM_TYPE"),

	/**
	 * 目标用户
	 */
	c_TARGET_USER("C_TARGET_USER"),

	/**
	 * 目标用户标识
	 */
	c_TARGET_APP_LOGO("C_TARGET_APP_LOGO"),

	/**
	 * 源应用
	 */
	c_SRC_APP_LOGO("C_SRC_APP_LOGO"),
	
	/**
	 *  wlx 20160907 STORY34149【广发证券】【紧急】申请修改电子对账支持多用户的需求
	 * 源用户
	 */
	c_SRC_USER("C_TARGET_USER_LOGO"),
	
	c_MR_IP("C_MR_IP"),
	
	c_MR_PORT("C_MR_PORT"),
	
	/**
	 * 包发送密码
	 */
	c_PKG_PASSWORD("C_PKG_PASSWORD"),
    c_GZB_MODE("C_GZB_MODE"),
	
	mrInfoList(""),
	 /**
     * 深圳通参数代码
     */
	c_ErPara_Code("c_ErPara_Code"),
	
	operator("C_CHECK_BY"),

	auditDate("C_CHECK_TIME"),

	modifier("C_UPDATE_BY"),

	modifyDate("C_UPDATE_TIME"),

	auditState("N_CHECK_STATE"),
	
	endUseDate(""),
	
	startUseDate(""),
		/**
	 * 重发最高次数STORY#35703 估值表自检以及自动生成发送电子对账
	 */
	c_High_Time("c_High_Time"),
	
	/**
	 * 每次间隔时间
	 */
	c_Interval("c_Interval"),
	
	/**
	 * 对账模式
	 */
	c_DZ_MODE("C_DZ_MODE"),
	
	/**
	 * 是否应用分机构
	 */
	c_Has_Branch("c_Has_Branch"),
	
	 /**
     * 科目名称长度 
     * STORY #58495 需求单-【海通证券】电子对账的科目名称需要优化
     * author:cls
     */
	c_KM_NAME_LENGTH("c_KM_NAME_LENGTH");

	
	private String value;

	private DzParaColumnName(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value.toString();
	}
}
