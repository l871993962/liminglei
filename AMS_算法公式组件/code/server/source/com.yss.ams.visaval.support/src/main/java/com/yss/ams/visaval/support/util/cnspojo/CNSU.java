package com.yss.ams.visaval.support.util.cnspojo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="cns")
public class CNSU {

	@XmlElement(name="cn")
	private List<CNU> cnus ;

	@XmlTransient
	public List<CNU> getCnus() {
		return cnus;
	}

	public void setCnus(List<CNU> cnus) {
		this.cnus = cnus;
	}
	
	
}
