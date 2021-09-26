package com.yss.uco.elecreco.support.bean;
import com.yss.framework.api.common.co.AuditableParamPojo;
public class ErSpecialZb extends AuditableParamPojo  {
	private static final long serialVersionUID = 1L;

	/**
	* 指标代码
	*/
	private String c_KEY_CODE = "";

	/**
	* 指标名称
	*/
	private String c_KEY_NAME = "";

	/**
	* 科目代码
	*/
	private String c_KM_CODE = "";

	/**
	* 资产类别
	*/
	private String c_DAT_CLS = "";

	/**
	* 资产类别名称
	*/
	private String c_DAT_CLS_NAME = "";

	/**
	* @return 指标代码
	*/
	public String getC_KEY_CODE(){
		return c_KEY_CODE;
	}

	/**
	* @param c_KEY_CODE 指标代码
	*/
	public void setC_KEY_CODE(String c_KEY_CODE){
		 this.c_KEY_CODE = c_KEY_CODE;
	}

	/**
	* @return 指标名称
	*/
	public String getC_KEY_NAME(){
		return c_KEY_NAME;
	}

	/**
	* @param c_KEY_NAME 指标名称
	*/
	public void setC_KEY_NAME(String c_KEY_NAME){
		 this.c_KEY_NAME = c_KEY_NAME;
	}

	/**
	* @return 科目代码
	*/
	public String getC_KM_CODE(){
		return c_KM_CODE;
	}

	/**
	* @param c_KM_CODE 科目代码
	*/
	public void setC_KM_CODE(String c_KM_CODE){
		 this.c_KM_CODE = c_KM_CODE;
	}

	/**
	* @return 资产类别
	*/
	public String getC_DAT_CLS(){
		return c_DAT_CLS;
	}

	/**
	* @param c_DAT_CLS 资产类别
	*/
	public void setC_DAT_CLS(String c_DAT_CLS){
		 this.c_DAT_CLS = c_DAT_CLS;
	}

	/**
	* @return 资产类别名称
	*/
	public String getC_DAT_CLS_NAME(){
		return c_DAT_CLS_NAME;
	}

	/**
	* @param c_DAT_CLS_NAME 资产类别名称
	*/
	public void setC_DAT_CLS_NAME(String c_DAT_CLS_NAME){
		 this.c_DAT_CLS_NAME = c_DAT_CLS_NAME;
	}

}