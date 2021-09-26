package com.yss.ams.sec.information.support.modules.sv.suspendedcond.pojo;

import com.yss.framework.api.common.co.BasePojo;


/**
 * #42948 资讯信息管理组件化拆分
 * @author chenbo
 *2017-07-06
 */
public class SuspendedCond extends BasePojo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 组合代码
	 */
	String c_PORT_CODE = "";
	
	/**
	 * 项目代码
	 */
	String c_ITEM_CODE = "";
	
	/**
	 * 项目名称
	 */
	String c_ITEM_NAME = "";
	
	/**
	 * 逻辑判断
	 */
	String c_LOGICAL_JUDGMENT = "";
	
	/**
	 * 项目值
	 */
	String c_ITEM_VALUE = "";
	
	/**
	 * 单位类型
	 */
	String c_VALUE_TYPE = "";
	
	/**
	 * 默认值
	 */
	String c_DEFAULT_VALUE = "";

	public String getC_ITEM_CODE() {
		return c_ITEM_CODE;
	}

	public void setC_ITEM_CODE(String c_ITEM_CODE) {
		this.c_ITEM_CODE = c_ITEM_CODE;
	}

	public String getC_ITEM_NAME() {
		return c_ITEM_NAME;
	}

	public void setC_ITEM_NAME(String c_ITEM_NAME) {
		this.c_ITEM_NAME = c_ITEM_NAME;
	}

	public String getC_ITEM_VALUE() {
		return c_ITEM_VALUE;
	}

	public void setC_ITEM_VALUE(String c_ITEM_VALUE) {
		this.c_ITEM_VALUE = c_ITEM_VALUE;
	}

	public String getC_VALUE_TYPE() {
		return c_VALUE_TYPE;
	}

	public void setC_VALUE_TYPE(String c_VALUE_TYPE) {
		this.c_VALUE_TYPE = c_VALUE_TYPE;
	}
	public String getC_LOGICAL_JUDGMENT() {
		return c_LOGICAL_JUDGMENT;
	}

	public void setC_LOGICAL_JUDGMENT(String c_LOGICAL_JUDGMENT) {
		this.c_LOGICAL_JUDGMENT = c_LOGICAL_JUDGMENT;
	}

	public String getC_DEFAULT_VALUE() {
		return c_DEFAULT_VALUE;
	}

	public void setC_DEFAULT_VALUE(String c_DEFAULT_VALUE) {
		this.c_DEFAULT_VALUE = c_DEFAULT_VALUE;
	}
	
	public String getC_PORT_CODE() {
		return c_PORT_CODE;
	}

	public void setC_PORT_CODE(String c_PORT_CODE) {
		this.c_PORT_CODE = c_PORT_CODE;
	}


}
