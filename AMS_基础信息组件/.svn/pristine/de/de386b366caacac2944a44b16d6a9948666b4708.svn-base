package com.yss.ams.syncdata.support.modules.base.pojo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.FIELD)
public class XMLSystemCode {
	
	@XmlAttribute(name = "code")
	private String code;
	
	@XmlAttribute(name = "name")
	private String name;
			
	@XmlElement(name = "funcode")
	private List<XMLFuncode> funcodes;
	
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
	public List<XMLFuncode> getFuncodes() {
		return funcodes;
	}

	public void setFuncodes(List<XMLFuncode> funcodes) {
		this.funcodes = funcodes;
	}

}
