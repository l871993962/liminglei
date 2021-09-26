package com.yss.ams.product.information.support.modules.ab.port.vo;

import java.sql.Connection;
import java.util.List;

import com.yss.framework.api.common.co.Port;
import com.yss.framework.api.restful.annotations.ControllerMixArgu;
@ControllerMixArgu
public class ConnDeleteVo {

	private List<Port> list;
	private Connection conn;
	public List<Port> getList() {
		return list;
	}
	public void setList(List<Port> list) {
		this.list = list;
	}
	public Connection getConn() {
		return conn;
	}
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	
}
