package com.yss.ams.syncdata.support.modules.base.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.FIELD)
public class XMLFuncode {
	
	@XmlAttribute(name = "code")
	private String code;
	
	@XmlAttribute(name = "name")
	private String name;
	
	@XmlAttribute(name = "pojo")
	private String pojo;
	
	@XmlAttribute(name = "serviceId")
	private String serviceId;

	@XmlTransient
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@XmlTransient
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlTransient
	public String getPojo() {
		return pojo;
	}

	public void setPojo(String pojo) {
		this.pojo = pojo;
	}

	@XmlTransient
	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

}
