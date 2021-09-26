package com.yss.ams.base.information.modules.bi.tdchan.pojo;

import com.yss.ams.base.information.support.bi.tdchan.pojo.TdChan;


public class TdChanExtend extends TdChan{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String c_P_CODE = "";
	private String c_MKT_NAME;
	private String c_TD_CHAN_CODE_EX;

	public String getC_P_CODE() {
		return c_P_CODE;
	}

	public void setC_P_CODE(String cPCODE) {
		c_P_CODE = cPCODE;
	}
	
	public String getC_MKT_NAME() {
		return c_MKT_NAME;
	}

	public void setC_MKT_NAME(String c_MKT_NAME) {
		this.c_MKT_NAME = c_MKT_NAME;
	}

	public String getC_TD_CHAN_CODE_EX() {
		String c_MKT_CODE = getC_MKT_CODE();
		String c_TD_CHAN_CODE = getC_TD_CHAN_CODE();
		c_MKT_CODE = c_MKT_CODE == null ? "" : c_MKT_CODE.trim();
		c_TD_CHAN_CODE = c_TD_CHAN_CODE == null ? "" : c_TD_CHAN_CODE.trim();
		
		if(!"[root]".equals(c_P_CODE)){
			c_TD_CHAN_CODE_EX = c_MKT_CODE + ";" + c_TD_CHAN_CODE;
		}else{
			c_TD_CHAN_CODE_EX = c_TD_CHAN_CODE;
		}
		
		return c_TD_CHAN_CODE_EX;
	}
}
