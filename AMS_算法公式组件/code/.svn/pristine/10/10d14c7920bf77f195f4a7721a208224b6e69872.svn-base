package com.yss.ams.visaval.support.util.pojo;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 对应param节点 此对象仅供解析XML封装数据临时使用
 * 
 * @author 马向峰
 * 
 */
public class ParamU {

	@XmlAttribute(name = "code")
	private String code;
	@XmlAttribute(name = "name")
	private String name;
	@XmlAttribute(name = "value")
	private String value;
	@XmlAttribute(name = "isdefault")
	private boolean isDefault;
	@XmlAttribute(name = "keyword")
	private String keyWord;
	@XmlAttribute(name="source")
	private String source;
	@XmlAttribute(name="hasdetails")
	private boolean hasDetails;
	@XmlAttribute(name="isFromFile")
	private boolean isFromFile;
	
	@XmlTransient
	public boolean isFromFile() {
		return isFromFile;
	}

	public void setFromFile(boolean isFromFile) {
		this.isFromFile = isFromFile;
	}

	@XmlTransient
	public boolean isHasDetails() {
		return hasDetails;
	}

	public void setHasDetails(boolean hasDetails) {
		this.hasDetails = hasDetails;
	}

	@XmlTransient
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@XmlTransient
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@XmlTransient
	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	@XmlTransient
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlTransient
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@XmlTransient
	public boolean isDefault() {
		return isDefault;
	}

	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}
}
