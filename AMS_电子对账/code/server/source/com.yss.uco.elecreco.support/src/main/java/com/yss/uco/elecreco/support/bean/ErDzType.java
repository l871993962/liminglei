package com.yss.uco.elecreco.support.bean;
import com.yss.framework.api.common.co.BasePojo;
public class ErDzType extends BasePojo  {
	private static final long serialVersionUID = 1L;

	/**
	* 对账类型代码
	*/
	private String c_DZ_CODE = "";

	/**
	* 对账类型名称
	*/
	private String c_DZ_NAME = "";
	
	/**
	* 对账结果映射标志
	*/
	private String c_CHECK_FLAG = "";

	/**
	* 对账类型父级代码
	*/
	private String c_DZ_CODE_P = "";

	/**
	* @return 对账类型代码
	*/
	public String getC_DZ_CODE(){
		return c_DZ_CODE;
	}

	/**
	* @param c_DZ_CODE 对账类型代码
	*/
	public void setC_DZ_CODE(String c_DZ_CODE){
		 this.c_DZ_CODE = c_DZ_CODE;
	}

	/**
	* @return 对账类型名称
	*/
	public String getC_DZ_NAME(){
		return c_DZ_NAME;
	}

	/**
	* @param c_DZ_NAME 对账类型名称
	*/
	public void setC_DZ_NAME(String c_DZ_NAME){
		 this.c_DZ_NAME = c_DZ_NAME;
	}

	/**
	* @return 对账类型父级代码
	*/
	public String getC_DZ_CODE_P(){
		return c_DZ_CODE_P;
	}

	/**
	* @param c_DZ_CODE_P 对账类型父级代码
	*/
	public void setC_DZ_CODE_P(String c_DZ_CODE_P){
		 this.c_DZ_CODE_P = c_DZ_CODE_P;
	}

	/**
	 * @return 对账结果映射标志
	 */
	public String getC_CHECK_FLAG() {
		return c_CHECK_FLAG;
	}

	/**
	 * @param c_CHECK_FLAG 对账结果映射标志
	 */
	public void setC_CHECK_FLAG(String c_CHECK_FLAG) {
		this.c_CHECK_FLAG = c_CHECK_FLAG;
	}

}