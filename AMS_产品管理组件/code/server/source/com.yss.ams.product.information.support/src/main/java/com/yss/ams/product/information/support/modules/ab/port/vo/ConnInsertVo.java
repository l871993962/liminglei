package com.yss.ams.product.information.support.modules.ab.port.vo;

import java.sql.Connection;

import com.yss.framework.api.common.co.Port;
import com.yss.framework.api.restful.annotations.ControllerMixArgu;
@ControllerMixArgu
public class ConnInsertVo {

	private Port pojo;
	private Connection conn;
	public Port getPojo() {
		return pojo;
	}
	public void setPojo(Port pojo) {
		this.pojo = pojo;
	}
	public Connection getConn() {
		return conn;
	}
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	
	
}
