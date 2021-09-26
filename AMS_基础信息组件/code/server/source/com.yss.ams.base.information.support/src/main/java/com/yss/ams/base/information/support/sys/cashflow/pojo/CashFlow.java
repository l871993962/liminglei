package com.yss.ams.base.information.support.sys.cashflow.pojo;

import com.yss.framework.api.common.co.BasePojo;

/**
 * 现金流标记字典实体类
 * @author yuankai 基础信息拆分  2017.5.31
 *
 */
public class CashFlow extends BasePojo {
	/**
	 *  现金流标记字典实体类
	 *  作者：hs
	 *  @date 2016-08-16
	 */
	private static final long serialVersionUID = 1L;
	private String c_CASH_FLOW_CODE = "";
	private String c_CASH_FLOW_NAME = "";
	private String c_DVA_ITEM_CODE = "";
	private String c_DVA_ITEM_NAME = "";
	private String c_SEC_VAR_CODE = "";
	private String c_DT_CODE = "";
	private String c_FEE_CODE = "";
	public String getC_CASH_FLOW_CODE() {
		return c_CASH_FLOW_CODE;
	}
	public void setC_CASH_FLOW_CODE(String c_CASH_FLOW_CODE) {
		this.c_CASH_FLOW_CODE = c_CASH_FLOW_CODE;
	}
	public String getC_CASH_FLOW_NAME() {
		return c_CASH_FLOW_NAME;
	}
	public void setC_CASH_FLOW_NAME(String c_CASH_FLOW_NAME) {
		this.c_CASH_FLOW_NAME = c_CASH_FLOW_NAME;
	}
	public String getC_DVA_ITEM_CODE() {
		return c_DVA_ITEM_CODE;
	}
	public void setC_DVA_ITEM_CODE(String c_DVA_ITEM_CODE) {
		this.c_DVA_ITEM_CODE = c_DVA_ITEM_CODE;
	}
	public String getC_DVA_ITEM_NAME() {
		return c_DVA_ITEM_NAME;
	}
	public void setC_DVA_ITEM_NAME(String c_DVA_ITEM_NAME) {
		this.c_DVA_ITEM_NAME = c_DVA_ITEM_NAME;
	}
	public String getC_SEC_VAR_CODE() {
		return c_SEC_VAR_CODE;
	}
	public void setC_SEC_VAR_CODE(String c_SEC_VAR_CODE) {
		this.c_SEC_VAR_CODE = c_SEC_VAR_CODE;
	}
	public String getC_DT_CODE() {
		return c_DT_CODE;
	}
	public void setC_DT_CODE(String c_DT_CODE) {
		this.c_DT_CODE = c_DT_CODE;
	}
	public String getC_FEE_CODE() {
		return c_FEE_CODE;
	}
	public void setC_FEE_CODE(String c_FEE_CODE) {
		this.c_FEE_CODE = c_FEE_CODE;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
