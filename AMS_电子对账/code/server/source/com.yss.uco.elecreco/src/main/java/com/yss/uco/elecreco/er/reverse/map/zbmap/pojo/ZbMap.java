package com.yss.uco.elecreco.er.reverse.map.zbmap.pojo;
import com.yss.framework.api.common.co.AuditableParamPojo;
public class ZbMap extends AuditableParamPojo  {
	private static final long serialVersionUID = 1L;

	/**
	*对账类型
	*/
	private String c_FILE_TYPE = "";
	/**
	*内部指标代码
	*/
	private String c_ZB_CODE = "";
	/**
	*内部指标名称
	*/
	private String c_ZB_NAME = "";
	/**
	*外部指标代码
	*/
	private String c_ZB_CODE_OUT = "";
	/**
	*外部指标名称
	*/
	private String c_ZB_NAME_OUT = "";
	/**
	*产品组合
	*/
	private String c_PORT_CODE = "";
	/**
	*托管机构
	*/
	private String c_TGH_CODE = "";
	public String getC_FILE_TYPE() {
		return c_FILE_TYPE;
	}
	public void setC_FILE_TYPE(String c_FILE_TYPE) {
		this.c_FILE_TYPE = c_FILE_TYPE;
	}
	public String getC_ZB_CODE() {
		return c_ZB_CODE;
	}
	public void setC_ZB_CODE(String c_ZB_CODE) {
		this.c_ZB_CODE = c_ZB_CODE;
	}
	public String getC_ZB_NAME() {
		return c_ZB_NAME;
	}
	public void setC_ZB_NAME(String c_ZB_NAME) {
		this.c_ZB_NAME = c_ZB_NAME;
	}
	public String getC_ZB_CODE_OUT() {
		return c_ZB_CODE_OUT;
	}
	public void setC_ZB_CODE_OUT(String c_ZB_CODE_OUT) {
		this.c_ZB_CODE_OUT = c_ZB_CODE_OUT;
	}
	public String getC_ZB_NAME_OUT() {
		return c_ZB_NAME_OUT;
	}
	public void setC_ZB_NAME_OUT(String c_ZB_NAME_OUT) {
		this.c_ZB_NAME_OUT = c_ZB_NAME_OUT;
	}
	public String getC_PORT_CODE() {
		return c_PORT_CODE;
	}
	public void setC_PORT_CODE(String c_PORT_CODE) {
		this.c_PORT_CODE = c_PORT_CODE;
	}
	public String getC_TGH_CODE() {
		return c_TGH_CODE;
	}
	public void setC_TGH_CODE(String c_TGH_CODE) {
		this.c_TGH_CODE = c_TGH_CODE;
	}
	
	
	
}