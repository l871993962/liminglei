package com.yss.uco.elecreco.er.erresult.pojo;


/**
 * 电子对账结果浏览实体pojo类
 * @author chenyoulong 20121214
 * @version v1.0.0.6
 *
 */
public class ErResultQuery extends ErResult {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String c_B_KM_NAME;
	private String c_D_KM_NAME;
	public String getC_B_KM_NAME() {
		return c_B_KM_NAME;
	}
	public void setC_B_KM_NAME(String c_B_KM_NAME) {
		this.c_B_KM_NAME = c_B_KM_NAME;
	}
	public String getC_D_KM_NAME() {
		return c_D_KM_NAME;
	}
	public void setC_D_KM_NAME(String c_D_KM_NAME) {
		this.c_D_KM_NAME = c_D_KM_NAME;
	}
	
}
