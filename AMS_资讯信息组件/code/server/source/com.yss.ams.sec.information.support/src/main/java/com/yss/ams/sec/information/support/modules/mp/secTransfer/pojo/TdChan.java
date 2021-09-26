package com.yss.ams.sec.information.support.modules.mp.secTransfer.pojo;

import com.yss.framework.api.common.co.AuditableParamPojo;

/**
 * #42948 资讯信息管理组件化拆分
 * @author chenbo
 *2017-07-06
 */
public class TdChan extends AuditableParamPojo {
	/**
	* 交易渠道代码 
	*/
	  private String c_TD_CHAN_CODE = "";

	/**
	* 交易渠道名称 
	*/
	  private String c_TD_CHAN_NAME = "";

	/**
	* 机构代码 
	*/
	  private String c_ORG_CODE = "";

	/**
	* 交易所代码 
	*/
	  private String c_MKT_CODE = "";

	/**
	* 渠道类型 
	*/
	  private String c_DV_CHAN_TYPE = "";

	/**
	* 描述 
	*/
	  private String c_DESC = "";

	private static final long serialVersionUID = 1L ;

	public String getC_TD_CHAN_CODE() {
		return c_TD_CHAN_CODE;
	}

	public void setC_TD_CHAN_CODE(String cTDCHANCODE) {
		c_TD_CHAN_CODE = cTDCHANCODE;
	}

	public String getC_TD_CHAN_NAME() {
		return c_TD_CHAN_NAME;
	}

	public void setC_TD_CHAN_NAME(String cTDCHANNAME) {
		c_TD_CHAN_NAME = cTDCHANNAME;
	}

	public String getC_ORG_CODE() {
		return c_ORG_CODE;
	}

	public void setC_ORG_CODE(String cORGCODE) {
		c_ORG_CODE = cORGCODE;
	}

	public String getC_MKT_CODE() {
		return c_MKT_CODE;
	}

	public void setC_MKT_CODE(String cMKTCODE) {
		c_MKT_CODE = cMKTCODE;
	}

	public String getC_DV_CHAN_TYPE() {
		return c_DV_CHAN_TYPE;
	}

	public void setC_DV_CHAN_TYPE(String cDVCHANTYPE) {
		c_DV_CHAN_TYPE = cDVCHANTYPE;
	}

	public String getC_DESC() {
		return c_DESC;
	}

	public void setC_DESC(String cDESC) {
		c_DESC = cDESC;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
