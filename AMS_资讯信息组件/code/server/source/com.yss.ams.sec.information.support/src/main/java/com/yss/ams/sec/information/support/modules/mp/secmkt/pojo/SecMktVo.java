package com.yss.ams.sec.information.support.modules.mp.secmkt.pojo;

import java.util.Date;
import java.util.List;

import com.yss.framework.api.restful.annotations.ControllerMixArgu;

@ControllerMixArgu
public class SecMktVo {
	private List<String> secCodelist;
	private Date date;
	private String secCode;
	private String portCode;
	public List<String> getSecCodelist() {
		return secCodelist;
	}
	public void setSecCodelist(List<String> secCodelist) {
		this.secCodelist = secCodelist;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getSecCode() {
		return secCode;
	}
	public void setSecCode(String secCode) {
		this.secCode = secCode;
	}
	public String getPortCode() {
		return portCode;
	}
	public void setPortCode(String portCode) {
		this.portCode = portCode;
	}
	
	
}
