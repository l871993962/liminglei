package com.yss.ams.visaval.support.util.pojo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 对应algos节点
 * 此对象仅供解析XML封装数据临时使用
 * @author 马向峰
 *
 */
public class AlgosU {
	@XmlElement(name = "algo")
	private List<AlgoU> list;

	@XmlTransient
	public List<AlgoU> getList() {
		return list;
	}

	public void setList(List<AlgoU> list) {
		this.list = list;
	}
}
