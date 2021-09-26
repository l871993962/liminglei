package com.yss.ams.base.information.support.sys.daeelem.pojo;

import com.yss.framework.api.common.co.BasePojo;

public class DaeElem extends BasePojo {

	/**
	 * 核算元素代码
	 */
	private String c_DAE_CODE = "";

	/**
	 * 核算元素名称
	 */
	private String c_DAE_NAME = "";

	/**
	 * 数据来源字符串.
	 */
	private String c_DS_TYPE = "";

	/**
	 * 对应于核算级别表的字段名称
	 */
	private String c_DAI_FIELD = "";

	private static final long serialVersionUID = 1L;

	public String getC_DAE_CODE() {
		return c_DAE_CODE;
	}

	public void setC_DAE_CODE(String cDAECODE) {
		c_DAE_CODE = cDAECODE;
	}

	public String getC_DAE_NAME() {
		return c_DAE_NAME;
	}

	public void setC_DAE_NAME(String cDAENAME) {
		c_DAE_NAME = cDAENAME;
	}

	public String getC_DS_TYPE() {
		return c_DS_TYPE;
	}

	public void setC_DS_TYPE(String cDSTYPE) {
		c_DS_TYPE = cDSTYPE;
	}

	public String getC_DAI_FIELD() {
		return c_DAI_FIELD;
	}

	public void setC_DAI_FIELD(String cDAIFIELD) {
		c_DAI_FIELD = cDAIFIELD;
	}

}
