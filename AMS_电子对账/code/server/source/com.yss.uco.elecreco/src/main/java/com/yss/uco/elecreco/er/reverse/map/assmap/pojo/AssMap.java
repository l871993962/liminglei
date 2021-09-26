package com.yss.uco.elecreco.er.reverse.map.assmap.pojo;
import com.yss.framework.api.common.co.AuditableParamPojo;
public class AssMap extends AuditableParamPojo  {
	private static final long serialVersionUID = 1L;

	/**
	*投资组合代码
	*/
	private String c_PORT_CODE = "";
	/**
	*对账类型
	*/
	private String c_FILE_TYPE = "";
	/**
	*托管银行
	*/
	private String c_TGH_CODE = "";
	/**
	*托管行资产代码
	*/
	private String c_PORT_CODE_OUT = "";
	/**
	*对账模式（DZMODE_SZT：深圳通模式；DZMODE_QT：其他模式）
	*/
	private String c_DV_DZ_MODE = "";
	public String getC_PORT_CODE() {
		return c_PORT_CODE;
	}
	public void setC_PORT_CODE(String c_PORT_CODE) {
		this.c_PORT_CODE = c_PORT_CODE;
	}
	public String getC_FILE_TYPE() {
		return c_FILE_TYPE;
	}
	public void setC_FILE_TYPE(String c_FILE_TYPE) {
		this.c_FILE_TYPE = c_FILE_TYPE;
	}
	public String getC_TGH_CODE() {
		return c_TGH_CODE;
	}
	public void setC_TGH_CODE(String c_TGH_CODE) {
		this.c_TGH_CODE = c_TGH_CODE;
	}
	public String getC_PORT_CODE_OUT() {
		return c_PORT_CODE_OUT;
	}
	public void setC_PORT_CODE_OUT(String c_PORT_CODE_OUT) {
		this.c_PORT_CODE_OUT = c_PORT_CODE_OUT;
	}
	public String getC_DV_DZ_MODE() {
		return c_DV_DZ_MODE;
	}
	public void setC_DV_DZ_MODE(String c_DV_DZ_MODE) {
		this.c_DV_DZ_MODE = c_DV_DZ_MODE;
	}
	
	
	
}