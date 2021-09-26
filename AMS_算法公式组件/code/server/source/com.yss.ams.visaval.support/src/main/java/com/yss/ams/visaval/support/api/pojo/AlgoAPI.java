package com.yss.ams.visaval.support.api.pojo;

import com.yss.framework.api.common.co.BasePojo;

/**
 * 封装前台树对象
 * 
 * @author 马向峰
 * 
 */
public class AlgoAPI extends BasePojo{

	private static final long serialVersionUID = 1L;

	/**
	 * 算法code
	 */
	private String algoCode;

	/**
	 * 算法的父节点
	 */
	private String algoParent;

	/**
	 * 算法名称
	 */
	private String algoText;

	/**
	 * 算法的值
	 */
	private String algoValue;

	/**
	 * 算法的描述
	 */
	private String algoDesc;

	public String getAlgoCode() {
		return algoCode;
	}

	public void setAlgoCode(String algoCode) {
		this.algoCode = algoCode;
	}

	public String getAlgoParent() {
		return algoParent;
	}

	public void setAlgoParent(String algoParent) {
		this.algoParent = algoParent;
	}

	public String getAlgoText() {
		return algoText;
	}

	public void setAlgoText(String algoText) {
		this.algoText = algoText;
	}

	public String getAlgoValue() {
		return algoValue;
	}

	public void setAlgoValue(String algoValue) {
		this.algoValue = algoValue;
	}

	public String getAlgoDesc() {
		return algoDesc;
	}

	public void setAlgoDesc(String algoDesc) {
		this.algoDesc = algoDesc;
	}

}
