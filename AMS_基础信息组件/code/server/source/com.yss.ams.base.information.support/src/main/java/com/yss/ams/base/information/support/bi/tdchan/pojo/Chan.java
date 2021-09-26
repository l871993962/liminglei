package com.yss.ams.base.information.support.bi.tdchan.pojo;

import com.yss.framework.api.common.co.AuditableParamPojo;

public class Chan extends AuditableParamPojo{

	/**
	* 交易渠道代类型
	*/
	  private String c_RELA_TYPE = "";

	/**
	* 交易渠道代码 
	*/
	  private String c_RELA_CODE = "";

	/**
	* 机构代码 
	*/
	  private String c_DV_TYPE_CODE = "";
		 
	/**
	* 描述 
	*/
	  private String c_DESC = "";
	  
	  /**
		* 描述 
		*/
	 private String c_CA_CODE = "";
	 
	 private String c_MKT_CODE = "";
	 
	 
	private static final long serialVersionUID = 1L ;


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getC_RELA_TYPE() {
		return c_RELA_TYPE;
	}


	public void setC_RELA_TYPE(String c_RELA_TYPE) {
		this.c_RELA_TYPE = c_RELA_TYPE;
	}


	public String getC_RELA_CODE() {
		return c_RELA_CODE;
	}


	public void setC_RELA_CODE(String c_RELA_CODE) {
		this.c_RELA_CODE = c_RELA_CODE;
	}


	public String getC_DV_TYPE_CODE() {
		return c_DV_TYPE_CODE;
	}


	public void setC_DV_TYPE_CODE(String c_DV_TYPE_CODE) {
		this.c_DV_TYPE_CODE = c_DV_TYPE_CODE;
	}


	public String getC_DESC() {
		return c_DESC;
	}


	public void setC_DESC(String c_DESC) {
		this.c_DESC = c_DESC;
	}


	public String getC_CA_CODE() {
		return c_CA_CODE;
	}


	public void setC_CA_CODE(String c_CA_CODE) {
		this.c_CA_CODE = c_CA_CODE;
	}
	
	public String getC_MKT_CODE() {
		return c_MKT_CODE;
	}


	public void setC_MKT_CODE(String c_MKT_CODE) {
		this.c_MKT_CODE = c_MKT_CODE;
	}
}
