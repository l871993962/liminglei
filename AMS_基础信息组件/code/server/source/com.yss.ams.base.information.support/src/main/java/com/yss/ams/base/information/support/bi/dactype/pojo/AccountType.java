package com.yss.ams.base.information.support.bi.dactype.pojo;

import com.yss.framework.api.common.co.BasePojo;

/**
 * @ClassName HkType
 * @Description 款项类型设置
 * @author liminghong@ysstech.com
 * @CreateDate 2017年5月22日
 * @Version V1.21.5.0
 * @Copyright (c) 2017, 深圳赢时胜 All Rights Reserved.
 */
public class AccountType extends BasePojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2851650637836582731L;

	/**
	 * 账户类型父级节点代码
	 */
	private String C_DAC_CODE_P = "";

	/**
	 * 账户类型代码
	 */
	private String C_DAC_CODE = "";

	/**
	 * 账户类型名称
	 */
	private String C_DAC_NAME = "";
	
	/**
	 * 排序
	 */
	private String N_ORDER = "";
	
	/**
	 * 定位为资金户还是证券户
	 */
	private String C_DAC_TYPE = "";
	
	/**
	 * 是否启用
	 */
	private String C_DV_STATE = "";
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getC_DAC_CODE_P() {
		return C_DAC_CODE_P;
	}


	public void setC_DAC_CODE_P(String c_DAC_CODE_P) {
		C_DAC_CODE_P = c_DAC_CODE_P;
	}


	public String getC_DAC_CODE() {
		return C_DAC_CODE;
	}


	public void setC_DAC_CODE(String c_DAC_CODE) {
		C_DAC_CODE = c_DAC_CODE;
	}


	public String getC_DAC_NAME() {
		return C_DAC_NAME;
	}


	public void setC_DAC_NAME(String c_DAC_NAME) {
		C_DAC_NAME = c_DAC_NAME;
	}


	public String getN_ORDER() {
		return N_ORDER;
	}


	public void setN_ORDER(String n_ORDER) {
		N_ORDER = n_ORDER;
	}


	public String getC_DAC_TYPE() {
		return C_DAC_TYPE;
	}


	public void setC_DAC_TYPE(String c_DAC_TYPE) {
		C_DAC_TYPE = c_DAC_TYPE;
	}


	public String getC_DV_STATE() {
		return C_DV_STATE;
	}


	public void setC_DV_STATE(String c_DV_STATE) {
		C_DV_STATE = c_DV_STATE;
	}
	
}
