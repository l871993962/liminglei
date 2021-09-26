package com.yss.ams.sec.information.modules.mp.indexmp.dao;


/**
 * 指数行情资料 数据库表对应字段
 * @author gongyue
 * 资讯信息拆分	2017.7.5 STORY #42948 资讯信息管理组件化拆分
 */
public enum IndexMpColumnName {

	/**
	 * 证券内码
	 */
	c_SEC_CODE("C_SEC_CODE"),

	/**
	 * 行情来源
	 */
	c_DV_PLAT("C_DV_PLAT"),

	
	/**
	 * 行情状态
	 */
	c_HQZT_CODE("N_STATE"),

	/**
	 * 行情日期
	 */
	d_MKT("D_MKT"),

	/**
	 * 行情分类
	 */
	c_MKT_CLS("C_MKT_CLS"),

	/**
	 * 行情时间
	 */
	c_MKT_TIME("C_MKT_TIME"),

	/**
	 * 最新价
	 */
	n_PRICE_NEW("N_PRICE_NEW"),

	/**
	 * 买入价
	 */
	n_PRICE_BUY("N_PRICE_BUY"),

	/**
	 * 卖出价
	 */
	n_PRICE_SELL("N_PRICE_SELL"),

	/**
	 * 最高价
	 */
	n_PRICE_HIGH("N_PRICE_HIGH"),

	/**
	 * 最低价
	 */
	n_PRICE_LOW("N_PRICE_LOW"),

	/**
	 * 收盘价
	 */
	n_PRICE_CLOSE("N_PRICE_CLOSE"),
	
	/**
	 *  昨日收盘价 by guoguangyi STORY #25937 交易所行情清算需要支持保存昨日收盘价
	 */
	n_PRICE_ZRCLOSE("N_PRICE_ZRCLOSE"),
	/**
	 * 开盘价
	 */
	n_PRICE_OPEN("N_PRICE_OPEN"),

	/**
	 * 平均价
	 */
	n_PRICE_AVG("N_PRICE_AVG"),

	/**
	 * 结算价
	 * add by liyanjun 2016-4-29 STORY29425个股期权行情获取收盘价
	 */
	n_PRICE_SETT("N_PRICE_SETT"),
		
	/**
	 * 公告日期
	 */
	d_PUB("D_PUB"),

	/**
	 * 描述
	 */
	c_DESC("C_DESC"),

	/**
	 * 数据来源
	 */
	c_DATA_IDF("C_DATA_IDF"),

	/**
	 * 成交数量  added by dingxukun 20161010
	 * STORY #34828 【南方基金】【紧急】增加参数控制项不活跃债券公允价值估值需求
	 */
	n_TD_AMOUNT("N_TD_AMOUNT"),
	
	/**
	 * 成交金额  Added by dingxukun 20161010
	 * STORY #34828 【南方基金】【紧急】增加参数控制项不活跃债券公允价值估值需求
	 */
	n_TD_MONEY("N_TD_MONEY"),
	
	id("C_IDEN"),
	
	endUseDate(""),
	
	startUseDate(""),

	creator("C_CREATE_BY"),

	createDate("C_CREATE_TIME"),

	operator("C_CHECK_BY"),

	auditDate("C_CHECK_TIME"),

	modifier("C_UPDATE_BY"),

	modifyDate("C_UPDATE_TIME"),

	auditState("N_CHECK_STATE");

	private String value;

	private IndexMpColumnName(String value) {
		this.value = value;
	}

	public String toString() {
		return this.value.toString();
	}

}
