package com.yss.ams.visaval.support.util.pojo;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 对应child下function节点
 * 此对象仅供解析XML封装数据临时使用
 * @author 马向峰
 *
 */
public class ChildFunU {

	@XmlAttribute(name = "code")
	private String code;
	@XmlAttribute(name = "parent")
	private String parent;
	@XmlAttribute(name = "text")
	private String text;
	@XmlAttribute(name = "value")
	private String value;
	@XmlElement(name = "desc")
	private String desc;
	@XmlAttribute(name="hasConnection")
	private boolean hasConnection;
	@XmlElement(name = "return")
	private ReturnValU returnVal;
	@XmlElement(name = "params")
	private ParamAPIU paramAPI;

	@XmlTransient
	public boolean isHasConnection() {
		return hasConnection;
	}

	public void setHasConnection(boolean hasConnection) {
		this.hasConnection = hasConnection;
	}

	@XmlTransient
	public ParamAPIU getParamAPI() {
		return paramAPI;
	}

	public void setParamAPI(ParamAPIU paramAPI) {
		this.paramAPI = paramAPI;
	}

	@XmlTransient
	public ReturnValU getReturnVal() {
		return returnVal;
	}

	public void setReturnVal(ReturnValU returnVal) {
		this.returnVal = returnVal;
	}

	@XmlTransient
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@XmlTransient
	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	@XmlTransient
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@XmlTransient
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@XmlTransient
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
