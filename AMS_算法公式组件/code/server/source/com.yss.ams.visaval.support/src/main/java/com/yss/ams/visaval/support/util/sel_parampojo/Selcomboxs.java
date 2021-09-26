package com.yss.ams.visaval.support.util.sel_parampojo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
@XmlRootElement(name="selcomboxs")
public class Selcomboxs {

	@XmlElement(name="selcombox")
	private List<Selcombox> selcomboxs;

	@XmlTransient
	public List<Selcombox> getSelcomboxs() {
		return selcomboxs;
	}

	public void setSelcomboxs(List<Selcombox> selcomboxs) {
		this.selcomboxs = selcomboxs;
	}
	
	
}
