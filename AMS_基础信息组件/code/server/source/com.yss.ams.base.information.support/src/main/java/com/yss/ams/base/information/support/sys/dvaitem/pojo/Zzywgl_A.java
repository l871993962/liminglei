package com.yss.ams.base.information.support.sys.dvaitem.pojo;

//import dataservice.comm.pojo.DvaItem;

public class Zzywgl_A extends DvaItem{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int n_Level = 1;
	
	private String nodeCode;
	
	private String parentCode;

	public int getN_Level() {
		return n_Level;
	}

	public void setN_Level(int nLevel) {
		n_Level = nLevel;
	}

	public String getNodeCode() {
		return nodeCode;
	}

	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
}
