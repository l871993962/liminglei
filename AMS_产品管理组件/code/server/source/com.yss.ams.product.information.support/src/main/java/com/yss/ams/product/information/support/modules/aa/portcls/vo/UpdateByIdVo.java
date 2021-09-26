package com.yss.ams.product.information.support.modules.aa.portcls.vo;

import java.sql.Connection;

import com.yss.ams.product.information.support.modules.aa.portcls.pojo.PortCls;
import com.yss.framework.api.restful.annotations.ControllerMixArgu;
@ControllerMixArgu
public class UpdateByIdVo {
	
	private PortCls pojo;
	private Connection conn;
	public PortCls getPojo() {
		return pojo;
	}
	public void setPojo(PortCls pojo) {
		this.pojo = pojo;
	}
	public Connection getConn() {
		return conn;
	}
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	

}
