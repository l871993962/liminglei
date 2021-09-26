package com.yss.uco.elecreco.er.ws.pojo.dzdzstate;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 返回报文
 * 
 * @author lenovo
 * 
 */
@XmlRootElement(name = "RESPONSE")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResponseDzStateXml {
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
	
	@XmlElement(name = "PROT_DZDZ")
	private List<DzdzCommon> dzdzList;

	
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

	@XmlTransient
	public List<DzdzCommon> getDzdzList() {
		return dzdzList;
	}

	public void setDzdzList(List<DzdzCommon> dzdzList) {
		this.dzdzList = dzdzList;
	}
	
	
}
