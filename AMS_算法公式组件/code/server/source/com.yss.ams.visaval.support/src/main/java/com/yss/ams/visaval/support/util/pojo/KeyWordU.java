package com.yss.ams.visaval.support.util.pojo;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

public class KeyWordU {
	@XmlAttribute(name = "code")
	private String code;
	@XmlElement(name = "value")
	private List<KeyWordValU> list;

	@XmlTransient
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@XmlTransient
	public List<KeyWordValU> getList() {
		return list;
	}

	public void setList(List<KeyWordValU> list) {
		this.list = list;
	}

}
