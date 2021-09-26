package com.yss.uco.elecreco.er.template.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 部署文件信息
 * 
 * @author liuxiang
 * @date 2015年2月12日
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "dzcfg")
public class Deploy {

	/**
	 * 基础信息
	 */
	@XmlElement(name = "info")
	protected Info info;

	/**
	 * 模板
	 */
	@XmlElement(name = "template")
	protected BasicTemplate basicTemplate;

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public BasicTemplate getBasicTemplate() {
		return basicTemplate;
	}

	public void setBasicTemplate(BasicTemplate basicTemplate) {
		this.basicTemplate = basicTemplate;
	}
}
