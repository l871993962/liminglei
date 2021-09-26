package com.yss.uco.elecreco.er.reverse.map.kmmap.pojo;
import com.yss.framework.api.common.co.BasePojo;
public class KmMap extends BasePojo  {
	private static final long serialVersionUID = 1L;

	/**
	*映射关系ID
	*/
	private String c_IDEN_RELA = "";
	/**
	*产品组合
	*/
	private String c_PORT_CODE = "";
	/**
	*科目代码
	*/
	private String c_KM_CODE = "";
	/**
	*科目名称
	*/
	private String c_KM_NAME = "";
	/**
	*托管机构
	*/
	private String c_TGH_CODE = "";
	/**
	*科目类型(KC_ZC:资产类;KC_FZ:负债类;KC_GT:共同类;KC_QY:权益类;KC_SY:损益类)
	*/
	private String c_DV_KM_CLS = "";
	/**
	*科目范围（REVE_KMFW_INNER:内部科目；REVE_KMFW_OUT：外部科目）
	*/
	private String c_DV_KM_SCOPE = "";
	
	public String getC_IDEN_RELA(){
		return c_IDEN_RELA;
	}
	public void setC_IDEN_RELA(String c_IDEN_RELA){
		 this.c_IDEN_RELA = c_IDEN_RELA;
	}
	public String getC_PORT_CODE(){
		return c_PORT_CODE;
	}
	public void setC_PORT_CODE(String c_PORT_CODE){
		 this.c_PORT_CODE = c_PORT_CODE;
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
	public String getC_TGH_CODE(){
		return c_TGH_CODE;
	}
	public void setC_TGH_CODE(String c_TGH_CODE){
		 this.c_TGH_CODE = c_TGH_CODE;
	}
	public String getC_DV_KM_CLS(){
		return c_DV_KM_CLS;
	}
	public void setC_DV_KM_CLS(String c_DV_KM_CLS){
		 this.c_DV_KM_CLS = c_DV_KM_CLS;
	}
	public String getC_DV_KM_SCOPE(){
		return c_DV_KM_SCOPE;
	}
	public void setC_DV_KM_SCOPE(String c_DV_KM_SCOPE){
		 this.c_DV_KM_SCOPE = c_DV_KM_SCOPE;
	}
}