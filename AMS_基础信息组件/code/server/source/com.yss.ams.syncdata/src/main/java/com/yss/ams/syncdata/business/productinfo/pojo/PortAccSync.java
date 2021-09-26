package com.yss.ams.syncdata.business.productinfo.pojo;

import com.yss.ams.base.information.support.bi.account.pojo.FundAcc;

/**
 * STORY #58468 生命周期系统中的账户信息数据同步至基础组件的银行账户信息
 * @author lenovo
 *
 */
public class PortAccSync extends FundAcc {

	private static final long serialVersionUID = 1L;

	/**
	 * 久悬状态
	 * 
	 * @return
	 */
	private String c_DISCARD_STATUS;

	/**
	 * 渠道模式
	 */
	private String C_CHAN_MODE = "";

	public String getC_DISCARD_STATUS() {
		return c_DISCARD_STATUS;
	}

	public void setC_DISCARD_STATUS(String c_DISCARD_STATUS) {
		this.c_DISCARD_STATUS = c_DISCARD_STATUS;
	}

	public String getC_CHAN_MODE() {
		return C_CHAN_MODE;
	}

	public void setC_CHAN_MODE(String c_CHAN_MODE) {
		C_CHAN_MODE = c_CHAN_MODE;
	}
}
