package com.yss.ams.syncdata.support.modules.base.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "syncdata")
public class XMLSyncData {
	
	@XmlAttribute(name = "startSync")
	private String startSync;
	
	@XmlAttribute(name = "address")
	private String address;
	
	@XmlAttribute(name = "systemCode")
	private String systemCode;
	
	@XmlTransient
	public String getStartSync() {
		return startSync;
	}

	public void setStartSync(String startSync) {
		this.startSync = startSync;
	}

	@XmlTransient
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@XmlTransient
	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

}
