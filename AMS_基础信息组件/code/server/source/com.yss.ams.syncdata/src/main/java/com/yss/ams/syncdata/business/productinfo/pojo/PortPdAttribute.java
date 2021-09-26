package com.yss.ams.syncdata.business.productinfo.pojo;

import com.yss.framework.api.common.co.AuditableParamPojo;

/**
 * 组合品种属性pojo类
 * @author zhengguiyu
 *
 */
/**
 * <产品属性分类>实体类
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
public class PortPdAttribute extends AuditableParamPojo{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 组合代码
	 */
	private String c_PORT_CODE = "";
	
	/**
	 * 资产类型
	 */
	private String c_DAT_CODE = "";

	/**
	 * 明细资产类型
	 */
	private String c_DAT_MXCODE = "";

	/**
	 * 运作类型
	 */
	private String c_OPER_TYPE = "";

	/**
	 * 募集对象
	 */
	private String c_COLLECT_CODE = "";
	
	/**
	 * 客户类型
	 */
	private String c_CLIENT_TYPE = "";
	
	/**
	 * 投资对象
	 */
	private String c_INVEST_CODE = "";
	
	/**
	 * 资产种类
	 */
	private String c_ASSETS_CODE = "";
	
	/**
	 * 证券结算模式
	 */
	private String c_SETT_MODE = "";
	
	/**
	 * 客户性质
	 */
	private String C_KH_NATURE = "";
	
	/**
	 * 描述
	 */
	private String c_DESC = "";
    
	/**
	 * 组合类别
	 */
	private String c_PORT_TYPE = "";
	
	/**
	** 产品编号
	*/
	private String c_PRODUCT_NUM = "";
	
	/**
	 * add by guohui 20161026    STORY34547【南方基金】保险资产报表需要区分产品编码(使用协会统一编码）与短码
	** 产品编号
	*/
	private String c_SHORT_NUM = "";

	/**
	** 合同名称
	*/
	private String c_CONTRACT_NAME = "";
	
	/**
	 * 次托托管账户 add by wsm 2016-5-7 STORY #30235【招商基金母公司】[QDII]TradeBlotter_EXP TradeBlotter招行导出接口
	 */
	private String c_CTTG_ACCOUNT="";
	
	/**
	 * 证监会简称 add by wgl STORY #36888 【南方基金】【紧急】产品信息多加一列证监会简称字段
	 */
	private String c_ZJHJC="";
	
	
	public String getC_KH_NATURE() {
		return C_KH_NATURE;
	}

	public void setC_KH_NATURE(String c_KH_NATURE) {
		C_KH_NATURE = c_KH_NATURE;
	}

	public String getC_ZJHJC() {
		return c_ZJHJC;
	}

	public void setC_ZJHJC(String c_ZJHJC) {
		this.c_ZJHJC = c_ZJHJC;
	}
	
	public String getC_CTTG_ACCOUNT() {
		return c_CTTG_ACCOUNT;
	}

	public void setC_CTTG_ACCOUNT(String c_CTTG_ACCOUNT) {
		this.c_CTTG_ACCOUNT = c_CTTG_ACCOUNT;
	}

	public String getC_DESC() {
		return c_DESC;
	}

	public void setC_DESC(String c_DESC) {
		this.c_DESC = c_DESC;
	}
	
	public String getC_PORT_CODE() {
		return c_PORT_CODE;
	}

	public void setC_PORT_CODE(String c_PORT_CODE) {
		this.c_PORT_CODE = c_PORT_CODE;
	}


	
	public String getC_DAT_CODE() {
		return c_DAT_CODE;
	}

	public void setC_DAT_CODE(String c_DAT_CODE) {
		this.c_DAT_CODE = c_DAT_CODE;
	}
	
	public String getC_OPER_TYPE() {
		return c_OPER_TYPE;
	}

	public void setC_OPER_TYPE(String c_OPER_TYPE) {
		this.c_OPER_TYPE = c_OPER_TYPE;
	}

	public String getC_COLLECT_CODE() {
		return c_COLLECT_CODE;
	}

	public void setC_COLLECT_CODE(String c_COLLECT_CODE) {
		this.c_COLLECT_CODE = c_COLLECT_CODE;
	}
	
	public String getC_DAT_MXCODE() {
		return c_DAT_MXCODE;
	}

	public void setC_DAT_MXCODE(String c_DAT_MXCODE) {
		this.c_DAT_MXCODE = c_DAT_MXCODE;
	}

	public String getC_CLIENT_TYPE() {
		return c_CLIENT_TYPE;
	}

	public void setC_CLIENT_TYPE(String c_CLIENT_TYPE) {
		this.c_CLIENT_TYPE = c_CLIENT_TYPE;
	}

	public String getC_INVEST_CODE() {
		return c_INVEST_CODE;
	}

	public void setC_INVEST_CODE(String c_INVEST_CODE) {
		this.c_INVEST_CODE = c_INVEST_CODE;
	}

	public String getC_ASSETS_CODE() {
		return c_ASSETS_CODE;
	}

	public void setC_ASSETS_CODE(String c_ASSETS_CODE) {
		this.c_ASSETS_CODE = c_ASSETS_CODE;
	}
	
	public String getC_SETT_MODE() {
		return c_SETT_MODE;
	}

	public void setC_SETT_MODE(String c_SETT_MODE) {
		this.c_SETT_MODE = c_SETT_MODE;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getC_PRODUCT_NUM() {
		return c_PRODUCT_NUM;
	}

	public String getC_CONTRACT_NAME() {
		return c_CONTRACT_NAME;
	}

	public void setC_PRODUCT_NUM(String c_PRODUCT_NUM) {
		this.c_PRODUCT_NUM = c_PRODUCT_NUM;
	}

	public void setC_CONTRACT_NAME(String c_CONTRACT_NAME) {
		this.c_CONTRACT_NAME = c_CONTRACT_NAME;
	}

	public String getC_PORT_TYPE() {
		return c_PORT_TYPE;
	}

	public void setC_PORT_TYPE(String c_PORT_TYPE) {
		this.c_PORT_TYPE = c_PORT_TYPE;
	}
	
	public String getC_SHORT_NUM() {
		return c_SHORT_NUM;
	}

	public void setC_SHORT_NUM(String c_SHORT_NUM) {
		this.c_SHORT_NUM = c_SHORT_NUM;
	}
}
