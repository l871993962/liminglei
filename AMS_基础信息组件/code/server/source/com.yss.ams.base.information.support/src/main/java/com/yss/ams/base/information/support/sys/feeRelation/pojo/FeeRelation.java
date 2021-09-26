package com.yss.ams.base.information.support.sys.feeRelation.pojo;

import com.yss.framework.api.common.co.BasePojo;

/**
 * @classDesc 费用关联pojo类
 * @version 1.0 2012-9-22
 * @author yh
 */
/**
 * @author yuankai 公共信息拆分 2017.5.31
 */
public class FeeRelation extends BasePojo {

	/**  */
	private static final long serialVersionUID = 1409296878737016295L;

	/**
	 * 费用代码
	 */
	private String c_FEE_CODE = null;
	
	/**
	 * 交易类型
	 */
	private String c_TD_TYPE = null;
	
	/**
	 * 费用类型
	 */
	private String c_IE_CODE = null;
	
	/**
	 * 序号
	 */
	private int n_ORDER = 0;

	/**
	 * @return the c_FEE_CODE
	 */
	public String getC_FEE_CODE() {
		return c_FEE_CODE;
	}

	/**
	 * @param cFEECODE the c_FEE_CODE to set
	 */
	public void setC_FEE_CODE(String cFEECODE) {
		c_FEE_CODE = cFEECODE;
	}

	/**
	 * @return the c_TD_TYPE
	 */
	public String getC_TD_TYPE() {
		return c_TD_TYPE;
	}

	/**
	 * @param cTDTYPE the c_TD_TYPE to set
	 */
	public void setC_TD_TYPE(String cTDTYPE) {
		c_TD_TYPE = cTDTYPE;
	}

	/**
	 * @return the n_ORDER
	 */
	public int getN_ORDER() {
		return n_ORDER;
	}

	/**
	 * @param nORDER the n_ORDER to set
	 */
	public void setN_ORDER(int nORDER) {
		n_ORDER = nORDER;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the c_DV_FEE_TYPE
	 */
	public String getC_IE_CODE() {
		return c_IE_CODE;
	}

	/**
	 * @param cDVFEETYPE the c_DV_FEE_TYPE to set
	 */
	public void setC_IE_CODE(String cDVFEETYPE) {
		c_IE_CODE = cDVFEETYPE;
	}
	
	
}
