package com.yss.ams.visaval.support.util.pojo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 对应parent节点 此对象仅供解析XML封装数据临时使用
 * 
 * @author 马向峰
 * 
 */
public class ParentAPIU {

	@XmlElement(name = "function")
	private List<ParentFunU> parentFunList;

	@XmlTransient
	public List<ParentFunU> getParentFunList() {
		return parentFunList;
	}

	public void setParentFunList(List<ParentFunU> parentFunList) {
		this.parentFunList = parentFunList;
	}

}
