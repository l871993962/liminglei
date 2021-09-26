package com.yss.ifa.szt.tool.thread;

import com.yss.ifa.szt.tool.pojo.TransPojo;

public abstract class SplitErExecuterService implements IErExecuterService {
	protected TransPojo transPojo = null;
	/**
	 * 当该组合配置了拆分代码时，为拆分代码，否则是资产代码
	 */
	protected String assCode = null;
	/**
	 * 是否拆分生成发送
	 * @return
	 */
	protected boolean isSplit = false;
	public TransPojo getTransPojo() {
		return transPojo;
	}

	public void setTransPojo(TransPojo transPojo) {
		this.transPojo = transPojo;
	}

	public String getAssCode() {
		return assCode;
	}

	public void setAssCode(String assCode) {
		this.assCode = assCode;
	}

	public boolean isSplit() {
		return isSplit;
	}

	public void setSplit(boolean isSplit) {
		this.isSplit = isSplit;
	}

	public void initSplitParams(TransPojo pojo, String fUND_ID, boolean isSplit) {
		this.transPojo = pojo;
		this.assCode = fUND_ID;
		this.isSplit = isSplit;
	}
	
}
