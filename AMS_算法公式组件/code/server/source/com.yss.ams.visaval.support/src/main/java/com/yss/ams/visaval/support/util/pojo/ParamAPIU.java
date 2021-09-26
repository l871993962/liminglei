package com.yss.ams.visaval.support.util.pojo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 对应params节点
 * 此对象仅供解析XML封装数据临时使用
 * @author 马向峰
 *
 */
public class ParamAPIU {


	@XmlElement(name = "param")
	private List<ParamU> paramList;
	
	@XmlTransient
	public List<ParamU> getParamList() {
		return paramList;
	}

	public void setParamList(List<ParamU> paramList) {
		this.paramList = paramList;
	}
}
