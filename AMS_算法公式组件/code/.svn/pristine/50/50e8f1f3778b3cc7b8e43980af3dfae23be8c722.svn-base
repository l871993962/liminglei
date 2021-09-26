package com.yss.ams.visaval.support.util.pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 对应functions节点
 * 此对象仅供解析XML封装数据临时使用
 * @author 马向峰
 *
 */
public class APIFunsU {

	@XmlElement(name = "parent")
	private ParentAPIU parentAPI;
	
	@XmlElement(name = "child")
	private ChildAPIU childAPI;

	@XmlTransient
	public ParentAPIU getParentAPI() {
		return parentAPI;
	}

	public void setParentAPI(ParentAPIU parentAPI) {
		this.parentAPI = parentAPI;
	}

	@XmlTransient
	public ChildAPIU getChildAPI() {
		return childAPI;
	}

	public void setChildAPI(ChildAPIU childAPI) {
		this.childAPI = childAPI;
	}
	
	

	
}
