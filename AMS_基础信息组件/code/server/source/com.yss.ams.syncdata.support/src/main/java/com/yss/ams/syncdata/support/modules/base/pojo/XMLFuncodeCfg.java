package com.yss.ams.syncdata.support.modules.base.pojo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "systemCodes")
public class XMLFuncodeCfg {
	
	@XmlElement(name = "systemCode")
	private List<XMLSystemCode> systemCode;
	
	@XmlTransient
	public List<XMLSystemCode> getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(List<XMLSystemCode> systemCode) {
		this.systemCode = systemCode;
	}
}
