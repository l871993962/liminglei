package com.yss.uco.elecreco.er.ws.pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

public class ResponseHeader {
	/**
	 * 响应时间 （yyyyMMddhhmmss）
	 */
	@XmlElement(name = "DS_RESP_TM")
	private String repTime;

	/**
	 * 响应编码 
	 */
	@XmlElement(name = "DS_RESP_CODE")
	private String repCode;	

	/**
	 * 返回描述
	 */
	@XmlElement(name = "DS_RESP_DESC")
	private String repDesc;	

	
	@XmlTransient
	public String getRepTime() {
		return repTime;
	}

	public void setRepTime(String repTime) {
		this.repTime = repTime;
	}
	
	@XmlTransient
	public String getRepCode() {
		return repCode;
	}

	public void setRepCode(String repCode) {
		this.repCode = repCode;
	}

	@XmlTransient
	public String getRepDesc() {
		return repDesc;
	}

	public void setRepDesc(String repDesc) {
		this.repDesc = repDesc;
	}
}
