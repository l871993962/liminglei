package com.yss.uco.elecreco.er.template.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

/**
 * @author liuxiang 2015年2月12日
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class DataSource {

	/**
	 * sql
	 */
	@XmlValue
	protected String value;

	/**
	 * 数据源
	 */
	@XmlAttribute(name = "code")
	protected String code;

	/**
	 * 是否循环
	 */
	@XmlAttribute(name = "isLoop")
	protected String isLoop;

	/**
	 * 循环条件
	 */
	@XmlAttribute(name = "loopBy")
	protected String loopBy;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIsLoop() {
		return isLoop;
	}

	public void setIsLoop(String isLoop) {
		this.isLoop = isLoop;
	}

	public String getLoopBy() {
		return loopBy;
	}

	public void setLoopBy(String loopBy) {
		this.loopBy = loopBy;
	}

}
