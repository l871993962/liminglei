package com.yss.uco.elecreco.er.template.pojo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author liuxiang
 * 2015年2月12日
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class DataSet {

	/**
	 * 数据源
	 */
	@XmlAttribute(name="dataSource")
	protected String dataSource;
	
	/**
	 * 字段列表
	 */
	@XmlElement(name="field")
	protected List<Field> listField;

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public List<Field> getListField() {
		return listField;
	}

	public void setListField(List<Field> listField) {
		this.listField = listField;
	}
}
