package com.yss.ams.syncdata.support.modules.base.pojo;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "SyncSaleOrg")
public class SyncSaleOrg extends SyncBasePojo{
	
	//销售商名称
	@XmlAttribute(name = "c_NET_NAME")
	private String c_NET_NAME = "";
	
	//销售商代码
	@XmlAttribute(name = "c_NET_CODE")
	private String c_NET_CODE = "";
	
	//销售商类型
	@XmlAttribute(name = "c_DV_NET_TYPE")
	private String c_DV_NET_TYPE = "";

	@XmlTransient
	public String getC_NET_NAME() {
		return c_NET_NAME;
	}

	public void setC_NET_NAME(String c_NET_NAME) {
		this.c_NET_NAME = c_NET_NAME;
	}

	@XmlTransient
	public String getC_NET_CODE() {
		return c_NET_CODE;
	}

	public void setC_NET_CODE(String c_NET_CODE) {
		this.c_NET_CODE = c_NET_CODE;
	}

	@XmlTransient
	public String getC_DV_NET_TYPE() {
		return c_DV_NET_TYPE;
	}

	public void setC_DV_NET_TYPE(String c_DV_NET_TYPE) {
		this.c_DV_NET_TYPE = c_DV_NET_TYPE;
	}

	@Override
	public String toString() {
		return "SyncSaleOrg [c_NET_NAME=" + c_NET_NAME + ", c_NET_CODE="
				+ c_NET_CODE + ", c_DV_NET_TYPE=" + c_DV_NET_TYPE + "]";
	}
	

	
	
}
