package com.yss.ams.base.information.support.sys.dccury.pojo;

import com.yss.framework.api.common.co.BasePojo;

/**
 * 国际货币pojo
 * @author 马向峰  拆分  2017.0527
 *
 */
public class DcCury extends BasePojo{

	/**
	* 币种代码 
	*/
	  private String c_DC_CODE = "";

	/**
	* 币种名称 
	*/
	  private String c_DC_NAME = "";

	/**
	* 币种符号 
	*/
	private String c_DC_SIGN = "";

	/**
	 * 显示状态
	 * Added by huangsq 20160728 STORY #32244 词汇资源可控制是否显示
	 */
	private String c_DV_STATE = "";

	private int n_ORDER;
	
	private static final long serialVersionUID = 1L ;

	public String getC_DC_CODE() {
		return c_DC_CODE;
	}

	public void setC_DC_CODE(String cDCCODE) {
		c_DC_CODE = cDCCODE;
	}

	public String getC_DC_NAME() {
		return c_DC_NAME;
	}

	public void setC_DC_NAME(String cDCNAME) {
		c_DC_NAME = cDCNAME;
	}

	public String getC_DC_SIGN() {
		return c_DC_SIGN;
	}

	public void setC_DC_SIGN(String cDCSIGN) {
		c_DC_SIGN = cDCSIGN;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getC_DV_STATE() {
		return c_DV_STATE;
	}

	public void setC_DV_STATE(String c_DV_STATE) {
		this.c_DV_STATE = c_DV_STATE;
	}

	public int getN_ORDER() {
		return n_ORDER;
	}

	public void setN_ORDER(int nORDER) {
		n_ORDER = nORDER;
	}

}
