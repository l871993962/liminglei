package com.yss.ams.base.information.support.sys.automaticSet.pojo;


import com.yss.framework.api.common.co.AuditableParamPojo;

/** 
 * 组合与券商、接口、路径关系pojo类
 * @ClassName: AutomaticSetPathPojo
 * @date 2021年05月29日
 * @Stroy105821
 * @author zhuziqing
 */
public class AutomaticSetPathPojo extends AuditableParamPojo{
	
	
	private static final long serialVersionUID = 1L;

	/**  
	 * 渠道明细代码
	 */
	private String c_CHANEL_CODE = "";
	
	/**
	 * 渠道明细类型
	 */
	private String c_CHANEL_TYPE = "";
	
	/**
	 * 产品业务代码
	 */
	private String c_PRODUCT_CODE="";
	/**
	 * 产品业务分类
	 */
	private String c_PRODUCT_NAME = "";
	/**
	 * 组合代码
	 */
	private String c_PORT_CODE = "";
	/**
	 * 组合名称
	 */
	private String c_PORT_NAME = "";
	/**
	 * 接口分组
	 */
	private String c_INTERFACE_GROUP ="";
	/**
	 * 接口代码
	 */
	private String c_INTERFACE_CODE = "";
	/**
	 * 接口名称
	 */
	private String c_INTERFACE_NAME = "";
	/**
	 * 接口对应的路径
	 */
	private String c_INTERFACE_PATH = "";
	/**
	 * 父级id
	 */
	private String  c_INTERFACE_P_ID = "";
	/**
	 * 指标编号
	 */
	private String c_INDEX_ORDER = "";
	/**
	 * 指标代码
	 */
	private String c_INDEX_CODE = "";
	/**
	 * 指标名称
	 */
	private String c_INDEX_NAME = "";
	/**
	 * 估值表别名
	 */
	private String c_VA_ALIAS = "";
	/**
	 * 所属估值表日期
	 */
	private String c_VA_TIME = "";
	/**
	 * 业务类型代码
	 */
	private String c_BUSINESS_TYPE_CODE ="";
	
	public String getC_BUSINESS_TYPE_CODE() {
		return c_BUSINESS_TYPE_CODE;
	}
	public void setC_BUSINESS_TYPE_CODE(String c_BUSINESS_TYPE_CODE) {
		this.c_BUSINESS_TYPE_CODE = c_BUSINESS_TYPE_CODE;
	}
	public String getC_PORT_NAME() {
		return c_PORT_NAME;
	}
	public void setC_PORT_NAME(String c_PORT_NAME) {
		this.c_PORT_NAME = c_PORT_NAME;
	}
	public String getC_INDEX_ORDER() {
		return c_INDEX_ORDER;
	}
	public void setC_INDEX_ORDER(String c_INDEX_ORDER) {
		this.c_INDEX_ORDER = c_INDEX_ORDER;
	}
	public String getC_INDEX_CODE() {
		return c_INDEX_CODE;
	}
	public void setC_INDEX_CODE(String c_INDEX_CODE) {
		this.c_INDEX_CODE = c_INDEX_CODE;
	}
	public String getC_INDEX_NAME() {
		return c_INDEX_NAME;
	}
	public void setC_INDEX_NAME(String c_INDEX_NAME) {
		this.c_INDEX_NAME = c_INDEX_NAME;
	}
	public String getC_VA_ALIAS() {
		return c_VA_ALIAS;
	}
	public void setC_VA_ALIAS(String c_VA_ALIAS) {
		this.c_VA_ALIAS = c_VA_ALIAS;
	}
	public String getC_VA_TIME() {
		return c_VA_TIME;
	}
	public void setC_VA_TIME(String c_VA_TIME) {
		this.c_VA_TIME = c_VA_TIME;
	}
	public String getC_CHANEL_CODE() {
		return c_CHANEL_CODE;
	}
	public void setC_CHANEL_CODE(String c_CHANEL_CODE) {
		this.c_CHANEL_CODE = c_CHANEL_CODE;
	}
	public String getC_CHANEL_TYPE() {
		return c_CHANEL_TYPE;
	}
	public void setC_CHANEL_TYPE(String c_CHANEL_TYPE) {
		this.c_CHANEL_TYPE = c_CHANEL_TYPE;
	}
	public String getC_PRODUCT_CODE() {
		return c_PRODUCT_CODE;
	}
	public void setC_PRODUCT_CODE(String c_PRODUCT_CODE) {
		this.c_PRODUCT_CODE = c_PRODUCT_CODE;
	}
	public String getC_PRODUCT_NAME() {
		return c_PRODUCT_NAME;
	}
	public void setC_PRODUCT_NAME(String c_PRODUCT_NAME) {
		this.c_PRODUCT_NAME = c_PRODUCT_NAME;
	}
	public String getC_PORT_CODE() {
		return c_PORT_CODE;
	}
	public void setC_PORT_CODE(String c_PORT_CODE) {
		this.c_PORT_CODE = c_PORT_CODE;
	}
	public String getC_INTERFACE_GROUP() {
		return c_INTERFACE_GROUP;
	}
	public void setC_INTERFACE_GROUP(String c_INTERFACE_GROUP) {
		this.c_INTERFACE_GROUP = c_INTERFACE_GROUP;
	}
	public String getC_INTERFACE_CODE() {
		return c_INTERFACE_CODE;
	}
	public void setC_INTERFACE_CODE(String c_INTERFACE_CODE) {
		this.c_INTERFACE_CODE = c_INTERFACE_CODE;
	}
	public String getC_INTERFACE_NAME() {
		return c_INTERFACE_NAME;
	}
	public void setC_INTERFACE_NAME(String c_INTERFACE_NAME) {
		this.c_INTERFACE_NAME = c_INTERFACE_NAME;
	}
	public String getC_INTERFACE_PATH() {
		return c_INTERFACE_PATH;
	}
	public void setC_INTERFACE_PATH(String c_INTERFACE_PATH) {
		this.c_INTERFACE_PATH = c_INTERFACE_PATH;
	}
	public String getC_INTERFACE_P_ID() {
		return c_INTERFACE_P_ID;
	}
	public void setC_INTERFACE_P_ID(String c_INTERFACE_P_ID) {
		this.c_INTERFACE_P_ID = c_INTERFACE_P_ID;
	}
}
