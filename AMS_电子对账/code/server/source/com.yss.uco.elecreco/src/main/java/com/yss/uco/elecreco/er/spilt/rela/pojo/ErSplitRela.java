package com.yss.uco.elecreco.er.spilt.rela.pojo;
import com.yss.framework.api.common.co.AuditableParamPojo;
public class ErSplitRela extends AuditableParamPojo  {
	private static final long serialVersionUID = 1L;

	/**
	*投资组合
	*/
	private String c_PORT_CODE = "";
	/**
	*托管银行
	*/
	private String c_TGH_CODE = "";
	/**
	*拆分代码
	*/
	private String c_SPLIT_CODE = "";
	/**
	*生效日期
	*/
	private String d_START_DATE = "";
	/**
	*失效日期
	*/
	private String d_END_DATE = "";
	public String getC_PORT_CODE(){
		return c_PORT_CODE;
	}
	public void setC_PORT_CODE(String c_PORT_CODE){
		 this.c_PORT_CODE = c_PORT_CODE;
	}
	public String getC_TGH_CODE(){
		return c_TGH_CODE;
	}
	public void setC_TGH_CODE(String c_TGH_CODE){
		 this.c_TGH_CODE = c_TGH_CODE;
	}
	public String getC_SPLIT_CODE(){
		return c_SPLIT_CODE;
	}
	public void setC_SPLIT_CODE(String c_SPLIT_CODE){
		 this.c_SPLIT_CODE = c_SPLIT_CODE;
	}
	public String getD_START_DATE(){
		return d_START_DATE;
	}
	public void setD_START_DATE(String d_START_DATE){
		 this.d_START_DATE = d_START_DATE;
	}
	public String getD_END_DATE(){
		return d_END_DATE;
	}
	public void setD_END_DATE(String d_END_DATE){
		 this.d_END_DATE = d_END_DATE;
	}
}