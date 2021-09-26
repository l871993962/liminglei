package com.yss.uco.elecreco.er.template.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author liuxiang 2015年2月12日
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Table {

	/**
	 * 表名
	 */
	@XmlAttribute(name = "name")
	protected String name;

	/**
	 * 描述信息
	 */
	@XmlAttribute(name = "desc")
	protected String desc;

	/**
	 * 数据源
	 */
	@XmlElement(name = "dataset")
	protected DataSet dataSet;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public DataSet getDataSet() {
		return dataSet;
	}

	public void setDataSet(DataSet dataSet) {
		this.dataSet = dataSet;
	}

}
