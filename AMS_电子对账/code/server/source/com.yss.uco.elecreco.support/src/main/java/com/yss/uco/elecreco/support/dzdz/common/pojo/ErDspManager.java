package com.yss.uco.elecreco.support.dzdz.common.pojo;

import java.util.Date;

import com.yss.framework.api.common.co.AuditableParamPojo;

public class ErDspManager extends AuditableParamPojo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	*参数代码
	*/
	private String c_DSP_CODE = "";
	/**
	*参数名称
	*/
	private String c_DSP_NAME = "";
	/**
	*参数类型(字符串:VARCHAR；日期:DATE；词汇:VOC；数字：NUMBER)
	*/
	private String c_DSP_VALUE_TYPE = "";
	/**
	*词汇类型
	*/
	private String c_DV_TYPE = "";
	/**
	*默认值
	*/
	private String c_DV_PLAT_VALUE = "";
	/**
	*描述
	*/
	private String c_DESC = "";
	/**
	*词汇缓存
	*/
	private String c_DS_TPYE = "";
	
	/**
	 * 参数类型
	 */
	private String c_PARA_TPYE = "";
	
	/**
	*组合代码或者群组代码
	*/
	private String c_PORT_CODE = "";
	/**
	*组合名称或者群组名称
	*/
	private String c_PORT_NAME = "";
	/**
	*应用范围
	*/
	private String c_DV_PARAM_TYPE = "";
	
	/**
	*参数值
	*/
	private String c_DV_PARAMS_VALUE = "";
	/**
	*分级组合代码
	*/
	private String c_PORT_CLS_CODE = "";
	/**
	*开始时间
	*/
	private Date d_BEGIN = null;
	/**
	*结束时间
	*/
	private Date d_END = null;
	public String getC_PORT_CODE(){
		return c_PORT_CODE;
	}
	public void setC_PORT_CODE(String c_PORT_CODE){
		 this.c_PORT_CODE = c_PORT_CODE;
	}
	public String getC_DV_PARAM_TYPE(){
		return c_DV_PARAM_TYPE;
	}
	public void setC_DV_PARAM_TYPE(String c_DV_PARAM_TYPE){
		 this.c_DV_PARAM_TYPE = c_DV_PARAM_TYPE;
	}
	public String getC_DSP_CODE(){
		return c_DSP_CODE;
	}
	public void setC_DSP_CODE(String c_DSP_CODE){
		 this.c_DSP_CODE = c_DSP_CODE;
	}
	public String getC_DV_PARAMS_VALUE(){
		return c_DV_PARAMS_VALUE;
	}
	public void setC_DV_PARAMS_VALUE(String c_DV_PARAMS_VALUE){
		 this.c_DV_PARAMS_VALUE = c_DV_PARAMS_VALUE;
	}
	public String getC_PORT_CLS_CODE(){
		return c_PORT_CLS_CODE;
	}
	public void setC_PORT_CLS_CODE(String c_PORT_CLS_CODE){
		 this.c_PORT_CLS_CODE = c_PORT_CLS_CODE;
	}
	public Date getD_BEGIN(){
		return d_BEGIN;
	}
	public void setD_BEGIN(Date d_BEGIN){
		 this.d_BEGIN = d_BEGIN;
	}
	public Date getD_END(){
		return d_END;
	}
	public void setD_END(Date d_END){
		 this.d_END = d_END;
	}
	public String getC_DSP_NAME(){
		return c_DSP_NAME;
	}
	public void setC_DSP_NAME(String c_DSP_NAME){
		 this.c_DSP_NAME = c_DSP_NAME;
	}
	public String getC_DSP_VALUE_TYPE(){
		return c_DSP_VALUE_TYPE;
	}
	public void setC_DSP_VALUE_TYPE(String c_DSP_VALUE_TYPE){
		 this.c_DSP_VALUE_TYPE = c_DSP_VALUE_TYPE;
	}
	public String getC_DV_TYPE(){
		return c_DV_TYPE;
	}
	public void setC_DV_TYPE(String c_DV_TYPE){
		 this.c_DV_TYPE = c_DV_TYPE;
	}
	public String getC_DV_PLAT_VALUE(){
		return c_DV_PLAT_VALUE;
	}
	public void setC_DV_PLAT_VALUE(String c_DV_PLAT_VALUE){
		 this.c_DV_PLAT_VALUE = c_DV_PLAT_VALUE;
	}
	public String getC_DESC(){
		return c_DESC;
	}
	public void setC_DESC(String c_DESC){
		 this.c_DESC = c_DESC;
	}
	public String getC_DS_TPYE(){
		return c_DS_TPYE;
	}
	public void setC_DS_TPYE(String c_DS_TPYE){
		 this.c_DS_TPYE = c_DS_TPYE;
	}
	public String getC_PORT_NAME() {
		return c_PORT_NAME;
	}
	public void setC_PORT_NAME(String c_PORT_NAME) {
		this.c_PORT_NAME = c_PORT_NAME;
	}
	public String getC_PARA_TPYE() {
		return c_PARA_TPYE;
	}
	public void setC_PARA_TPYE(String c_PARA_TPYE) {
		this.c_PARA_TPYE = c_PARA_TPYE;
	}
}
