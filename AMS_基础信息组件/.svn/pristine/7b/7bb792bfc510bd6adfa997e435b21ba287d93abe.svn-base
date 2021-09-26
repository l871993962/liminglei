package com.yss.ifa.szt.tool.thread;

import com.yss.ifa.szt.tool.pojo.TransPojo;

public abstract class ErExecuterBase implements IErExecuterService{
	
	private TransPojo pojo = null;
	
	private String sendStr;

	public void setPojo(TransPojo pojo) {
		this.pojo = pojo;
	}

	public TransPojo getPojo() {
		return pojo;
	}
	
	public String getSendStr() {
		return sendStr;
	}

	public void setSendStr(String sendStr) {
		this.sendStr = sendStr;
	}

	/**
	 * 报文日志信息
	 * @return
	 */
	public String messageInfo() {
		if(null == pojo){
			return "";
		}
		return "TransPojo [toUser=" + pojo.getToUser() + ", toApp=" + pojo.getToApp()
				+ ", fromUser=" + pojo.getFromUser() + ", fromApp=" + pojo.getFromApp()
				+ ", pkgId=" + pojo.getPkgId() + ", sendStr=" + sendStr + "]";
	}
	
}
