package com.yss.ams.sec.information.modules.mp.secequ.dao;

/**
 * #42948 资讯信息管理组件化拆分
 * @author chenbo
 *2017-07-06
 */

public enum SecEquColumnName {

	/**
	 * 数据标识
	 */
	c_DATA_IDF("C_DATA_IDF"),
	
	/**
	 * 数据来源
	 */
	c_EQU_CLS("C_EQU_CLS"),

	/**
	 * 证券代码
	 */
	c_SEC_CODE("C_SEC_CODE"),

	/**
	 * 标的证券
	 */
	c_SEC_CODE_TAG("C_SEC_CODE_TAG"),

	/**
	 * 临时证券
	 */
	c_SEC_CODE_TMP("C_SEC_CODE_TMP"),
	
	/**
	 * 权益类型
	 */
	c_DS_CODE("C_DS_CODE"),
	
	/**
	 * 折算类型
	 */
	c_ZS_CODE("C_ZS_CODE"),

	/**
	 * 税前权益比例
	 */
	n_EQU_RATIO_PT("N_EQU_RATIO_PT"),

	/**
	 * 税后权益比例
	 */
	n_EQU_RATIO_AT("N_EQU_RATIO_AT"),

	/**
	 * 配售价格
	 */
	n_PRICE_PLAC("N_PRICE_PLAC"),

	/**
	 * 分红币种
	 */
	c_DC_CODE("C_DC_CODE"),

	/**
	 * 锁定期限
	 */
	c_DV_VAR_DUR("C_DV_VAR_DUR"),

	/**
	 * 发行方式/分红类型
	 */
	c_DV_CODE("C_DV_CODE"),

	/**
	 * 登记日期
	 */
	d_REG("D_REG"),

	/**
	 * 缴款截止日
	 */
	d_FINAL("D_FINAL"),

	/**
	 * 除权日期
	 */
	d_EXR("D_EXR"),

	/**
	 * 交易属性
	 */
	c_DTA_CODE("C_DTA_CODE"),

	/**
	 * 交易市场
	 */
	c_MKT_CODE("C_MKT_CODE"),

	/**
	 * 描述
	 */
	c_DESC("C_DESC"),

	id("C_IDEN"),
	
	endUseDate(""),
	
	startUseDate(""),

	creator("C_CREATE_BY"),

	createDate("C_CREATE_TIME"),

	operator("C_CHECK_BY"),

	auditDate("C_CHECK_TIME"),

	modifier("C_UPDATE_BY"),

	modifyDate("C_UPDATE_TIME"),

	auditState("N_CHECK_STATE"),
	/**
	 * 	ISIN前缀
	 */
	c_SEC_ISIN_CODE("C_SEC_ISIN_CODE"),
	/**
	 * 	接口代码
	 */
	c_CFG_CODE_EQU("C_CFG_CODE_EQU");
	

	private String value;

	private SecEquColumnName(String value) {
		this.value = value;
	}

	public String toString() {
		return this.value.toString();
	}

}
