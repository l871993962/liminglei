package com.yss.uco.elecreco.er.spilt.rule.pojo;
import com.yss.framework.api.common.co.AuditableParamPojo;
public class ErSplitRule extends AuditableParamPojo  {
	private static final long serialVersionUID = 1L;

	/**
	*关联的拆分映射关系
	*/
	private String c_IDEN_RELA = "";
	/**
	*科目代码
	*/
	private String c_KM_CODE = "";
	/**
	*科目名称
	*/
	private String c_KM_NAME = "";
	public String getC_IDEN_RELA(){
		return c_IDEN_RELA;
	}
	public void setC_IDEN_RELA(String c_IDEN_RELA){
		 this.c_IDEN_RELA = c_IDEN_RELA;
	}
	public String getC_KM_CODE(){
		return c_KM_CODE;
	}
	public void setC_KM_CODE(String c_KM_CODE){
		 this.c_KM_CODE = c_KM_CODE;
	}
	public String getC_KM_NAME(){
		return c_KM_NAME;
	}
	public void setC_KM_NAME(String c_KM_NAME){
		 this.c_KM_NAME = c_KM_NAME;
	}
}