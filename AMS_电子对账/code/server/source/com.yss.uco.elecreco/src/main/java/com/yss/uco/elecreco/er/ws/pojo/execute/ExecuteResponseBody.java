package com.yss.uco.elecreco.er.ws.pojo.execute;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

public class ExecuteResponseBody {

	/**
	 * 组合
	 */
	@XmlElement(name = "PROT_CODE")
	private String c_PORT_CODE;
	
	/**
	 * 日期
	 */
	@XmlElement(name = "DATE")
	private String d_TRADE;
	
	/**
	 * 对账类型
	 */
	@XmlElement(name = "DZCODE")
	private String c_DZCODE;
	
	@XmlElement(name = "DZNAME")
	private String c_DZNAME;
	
	/**
	 * 执行状态
	 */
	@XmlElement(name = "STATE")
	private String c_DOING_TYPE;
	
	/**
	 * 执行结果
	 */
	@XmlElement(name = "MES")
	private String c_MES_TEXT;
	
	/**
	 * 详细日志
	 */
	@XmlElement(name = "MES_DETAIL")
	private String c_DETAIL_MES;

	@XmlTransient
	public String getC_PORT_CODE() {
		return c_PORT_CODE;
	}

	public void setC_PORT_CODE(String c_PORT_CODE) {
		this.c_PORT_CODE = c_PORT_CODE;
	}

	@XmlTransient
	public String getD_TRADE() {
		return d_TRADE;
	}

	public void setD_TRADE(String d_TRADE) {
		this.d_TRADE = d_TRADE;
	}

	@XmlTransient
	public String getC_DOING_TYPE() {
		return c_DOING_TYPE;
	}

	public void setC_DOING_TYPE(String c_DOING_TYPE) {
		this.c_DOING_TYPE = c_DOING_TYPE;
	}

	@XmlTransient
	public String getC_MES_TEXT() {
		return c_MES_TEXT;
	}

	public void setC_MES_TEXT(String c_MES_TEXT) {
		this.c_MES_TEXT = c_MES_TEXT;
	}

	@XmlTransient
	public String getC_DETAIL_MES() {
		return c_DETAIL_MES;
	}

	public void setC_DETAIL_MES(String c_DETAIL_MES) {
		this.c_DETAIL_MES = c_DETAIL_MES;
	}

	@XmlTransient
	public String getC_DZCODE() {
		return c_DZCODE;
	}

	public void setC_DZCODE(String c_DZCODE) {
		this.c_DZCODE = c_DZCODE;
	}

	@XmlTransient
	public String getC_DZNAME() {
		return c_DZNAME;
	}

	public void setC_DZNAME(String c_DZNAME) {
		this.c_DZNAME = c_DZNAME;
	}
	
}
