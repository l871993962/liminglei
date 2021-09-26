package com.yss.ams.product.information.support.modules.ab.port.pojo;

import com.yss.framework.api.common.co.Port;

/**
 * <产品基本信息>组合树实体类
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
public class PortTreeView extends Port{
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
