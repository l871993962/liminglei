package com.yss.uco.elecreco.er.template.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 模板基础信息
 * 
 * @author liuxiang
 * @date 2015年2月12日
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Info {
	/**
	 * 模板代码
	 */
	@XmlElement(name = "code")
	protected String code = "";

	/**
	 * 模板名称
	 */
	@XmlElement(name = "name")
	protected String name = "";

	/**
	 * 模板类型
	 */
	@XmlElement(name = "type")
	protected String type = "";

	/**
	 * 描述信息
	 */
	@XmlElement(name = "desc")
	protected String desc = "";

	/**
	 * 版本号
	 */
	@XmlElement(name = "version")
	protected String version = "";

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}
