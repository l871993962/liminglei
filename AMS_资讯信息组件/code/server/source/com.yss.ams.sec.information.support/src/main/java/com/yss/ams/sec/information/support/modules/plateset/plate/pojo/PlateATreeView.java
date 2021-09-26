package com.yss.ams.sec.information.support.modules.plateset.plate.pojo;

public class PlateATreeView extends Plate{
	/**
	 * 板块信息左侧树形栏pojo
	 * @author 马向峰  拆分
	 * @Date 20170531
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
