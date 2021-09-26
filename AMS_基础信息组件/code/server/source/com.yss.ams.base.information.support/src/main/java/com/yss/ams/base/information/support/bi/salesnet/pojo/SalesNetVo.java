package com.yss.ams.base.information.support.bi.salesnet.pojo;

import java.sql.Connection;
import java.util.List;

import com.yss.framework.api.restful.annotations.ControllerMixArgu;
@ControllerMixArgu
public class SalesNetVo {
	
	private List<String> vendorCodes;
	private Connection conn;
	public List<String> getVendorCodes() {
		return vendorCodes;
	}
	public void setVendorCodes(List<String> vendorCodes) {
		this.vendorCodes = vendorCodes;
	}
	public Connection getConn() {
		return conn;
	}
	public void setConn(Connection conn) {
		this.conn = conn;
	}

	
}
