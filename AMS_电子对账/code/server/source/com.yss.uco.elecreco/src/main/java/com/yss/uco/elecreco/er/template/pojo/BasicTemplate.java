package com.yss.uco.elecreco.er.template.pojo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 模板主体信息
 * 
 * @author liuxiang
 * @date 2015年2月12日
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class BasicTemplate {

	/**
	 * 目标表
	 */
	@XmlElement(name = "table")
	protected Table table;

	/**
	 * 数据源列表
	 */
	@XmlElement(name = "datasource")
	protected List<DataSource> listDataSource;

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public List<DataSource> getListDataSource() {
		return listDataSource;
	}

	public void setListDataSource(List<DataSource> listDataSource) {
		this.listDataSource = listDataSource;
	}

}
