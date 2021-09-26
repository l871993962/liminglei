package com.yss.ams.syncdata.support.modules.base.pojo;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 期货账户
 * @author chenyoucai
 *
 */
public class SyncFuturesAcc {

	/**期货编码*/
	@XmlAttribute(name = "c_SH_ACC_CODE")
	private String c_SH_ACC_CODE = "";
	
	/**账户名称*/
	@XmlAttribute(name = "c_SH_ACC_NAME")
	private String c_SH_ACC_NAME = "";
	
	/**交易市场*/
	@XmlAttribute(name = "c_MKT_CODE")
	private String c_MKT_CODE = "";
	
	/**开户日期*/
	@XmlAttribute(name = "d_BEGIN")
	private Date d_BEGIN = null;
	
	/**交易类型*/
	@XmlAttribute(name = "c_DV_ACC_TYPE")
	private String c_DV_ACC_TYPE = "";
	
	/**产品名称*/
	@XmlAttribute(name = "c_PORT_NAME")
	private String c_PORT_NAME = "";
	
	/**资产代码*/
	@XmlAttribute(name = "c_ASS_CODE")
	private String c_ASS_CODE = "";
	
	/**组合代码*/
	@XmlAttribute(name = "c_PORT_CODE")
	private String c_PORT_CODE = "";

	@XmlTransient
	public String getC_PORT_CODE() {
		return c_PORT_CODE;
	}

	public void setC_PORT_CODE(String c_PORT_CODE) {
		this.c_PORT_CODE = c_PORT_CODE;
	}

	public String getC_SH_ACC_CODE() {
		return c_SH_ACC_CODE;
	}

	public void setC_SH_ACC_CODE(String c_SH_ACC_CODE) {
		this.c_SH_ACC_CODE = c_SH_ACC_CODE;
	}


	public String getC_MKT_CODE() {
		return c_MKT_CODE;
	}

	public void setC_MKT_CODE(String c_MKT_CODE) {
		this.c_MKT_CODE = c_MKT_CODE;
	}

	public Date getD_BEGIN() {
		return d_BEGIN;
	}

	public void setD_BEGIN(Date d_BEGIN) {
		this.d_BEGIN = d_BEGIN;
	}

	public String getC_DV_ACC_TYPE() {
		return c_DV_ACC_TYPE;
	}

	public void setC_DV_ACC_TYPE(String c_DV_ACC_TYPE) {
		this.c_DV_ACC_TYPE = c_DV_ACC_TYPE;
	}

	public String getC_PORT_NAME() {
		return c_PORT_NAME;
	}

	public void setC_PORT_NAME(String c_PORT_NAME) {
		this.c_PORT_NAME = c_PORT_NAME;
	}
	
	public String getC_SH_ACC_NAME() {
		return c_SH_ACC_NAME;
	}

	public void setC_SH_ACC_NAME(String c_SH_ACC_NAME) {
		this.c_SH_ACC_NAME = c_SH_ACC_NAME;
	}
	
	public String getC_ASS_CODE() {
		return c_ASS_CODE;
	}

	public void setC_ASS_CODE(String c_ASS_CODE) {
		this.c_ASS_CODE = c_ASS_CODE;
	}
	
}
