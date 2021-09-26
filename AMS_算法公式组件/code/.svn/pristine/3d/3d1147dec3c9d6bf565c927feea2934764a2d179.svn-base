package com.yss.ams.visaval.support.util.pojo;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 对应AlgoAPI.xml节点的algo
 * 此对象仅供解析XML封装数据临时使用
 * @author 马向峰
 * 
 */
public class AlgoU {

	@XmlAttribute(name = "name")
	private String name;

	@XmlAttribute(name = "text")
	private String text;
	
	@XmlElement(name="variables")
	private VariableAPIU variableAPI;
	
	

	@XmlTransient
	public VariableAPIU getVariableAPI() {
		return variableAPI;
	}

	public void setVariableAPI(VariableAPIU variableAPI) {
		this.variableAPI = variableAPI;
	}

	@XmlTransient
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlTransient
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	
}
