package com.yss.ams.base.information.support.sys.dttdmode.pojo;

import com.yss.framework.api.common.co.BasePojo;

/**
 * 交易方式字典表T_S_DT_TD_MODE pojo
 *
 */
public class DttdMode extends BasePojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 交易方式代码
	 */
	private String c_DT_CODE = "";
	
	/**
	 * 交易方式名称
	 */
	private String c_DT_NAME = "";
	
	/**
	 * 业务类型
	 */
	private String c_BUSI_TYPE = "";
	
	/**
	 * 资金方向（1－流入, -1-流出，0－无)
	 */
	private int n_FUND_WAY = 0;
	
	/**
	 * 资本方向（1－流入, -1-流出，0－无)
	 */
	private int n_CAPI_WAY = 0;
	
	/**
	 * 编号
	 */
	private int n_ORDER = 0;

	public String getC_DT_CODE() {
		return c_DT_CODE;
	}

	public void setC_DT_CODE(String cDTCODE) {
		c_DT_CODE = cDTCODE;
	}

	public String getC_DT_NAME() {
		return c_DT_NAME;
	}

	public void setC_DT_NAME(String cDTNAME) {
		c_DT_NAME = cDTNAME;
	}

	public String getC_BUSI_TYPE() {
		return c_BUSI_TYPE;
	}

	public void setC_BUSI_TYPE(String cBUSITYPE) {
		c_BUSI_TYPE = cBUSITYPE;
	}

	public int getN_FUND_WAY() {
		return n_FUND_WAY;
	}

	public void setN_FUND_WAY(int nFUNDWAY) {
		n_FUND_WAY = nFUNDWAY;
	}

	public int getN_CAPI_WAY() {
		return n_CAPI_WAY;
	}

	public void setN_CAPI_WAY(int nCAPIWAY) {
		n_CAPI_WAY = nCAPIWAY;
	}

	public int getN_ORDER() {
		return n_ORDER;
	}

	public void setN_ORDER(int nORDER) {
		n_ORDER = nORDER;
	}
}
