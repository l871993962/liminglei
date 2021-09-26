package com.yss.ams.visaval.support.util.sqlpojo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="params")
public class ParamsSqlU {

	@XmlElement(name = "param")
	private List<ParamSqlU> paramSqlList;

	@XmlTransient
	public List<ParamSqlU> getParamSqlList() {
		return paramSqlList;
	}

	public void setParamSqlList(List<ParamSqlU> paramSqlList) {
		this.paramSqlList = paramSqlList;
	}
	
	
}
