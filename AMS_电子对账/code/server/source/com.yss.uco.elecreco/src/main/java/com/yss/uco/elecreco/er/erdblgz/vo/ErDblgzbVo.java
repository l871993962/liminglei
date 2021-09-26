package com.yss.uco.elecreco.er.erdblgz.vo;

import java.sql.Connection;
import java.util.List;

import com.yss.uco.elecreco.er.erdblgz.pojo.ErDblgzb;

public class ErDblgzbVo {

	private  List<ErDblgzb> list;
	private  Connection conn;
	public List<ErDblgzb> getList() {
		return list;
	}
	public void setList(List<ErDblgzb> list) {
		this.list = list;
	}
	public Connection getConn() {
		return conn;
	}
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	
	
}
