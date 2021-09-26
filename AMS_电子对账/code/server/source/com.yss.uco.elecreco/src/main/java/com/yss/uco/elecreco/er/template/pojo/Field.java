package com.yss.uco.elecreco.er.template.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * @author liuxiang 2015年2月12日
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Field {

	/**
	 * 目标字段
	 */
	@XmlAttribute(name = "targetFiled")
	protected String targetFiled;

	/**
	 * 源字段
	 */
	@XmlAttribute(name = "sourceField")
	protected String sourceField;

	public String getTargetFiled() {
		return targetFiled;
	}

	public void setTargetFiled(String targetFiled) {
		this.targetFiled = targetFiled;
	}

	public String getSourceField() {
		return sourceField;
	}

	public void setSourceField(String sourceField) {
		this.sourceField = sourceField;
	}

}
