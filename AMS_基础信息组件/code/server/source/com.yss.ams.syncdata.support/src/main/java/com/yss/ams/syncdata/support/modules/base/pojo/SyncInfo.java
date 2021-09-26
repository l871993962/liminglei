package com.yss.ams.syncdata.support.modules.base.pojo;

import com.yss.framework.api.common.co.BasePojo;
/**
 * 数据同步消息 pojo类
 * @author chenyoucai
 */
public class SyncInfo extends BasePojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	/**
	 * 同步模块代码
	 */
	private String funCode = "";
	
	/**
	 * 同步模块名称
	 */
	private String funName = "";
	
	/**
	 * 数据ID
	 */
	private String  dataId = "";
	
	/**
	 * 数据状态
	 */
	private String  dataStatus = "";
	
	/**
	 * 消息内容
	 */
	private String  theme = "";
	
	/**
	 * 发送人
	 */
	private String  sender = "";
	
	/**
	 * 发送时间
	 */
	private String  sendTime = "";

	public String getFunCode() {
		return funCode;
	}

	public void setFunCode(String funCode) {
		this.funCode = funCode;
	}

	public String getFunName() {
		return funName;
	}

	public void setFunName(String funName) {
		this.funName = funName;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public String getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(String dataStatus) {
		this.dataStatus = dataStatus;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "SyncDataMessage [funCode=" + funCode + ", funName=" + funName
				+ ", dataId=" + dataId + ", dataStatus=" + dataStatus
				+ ", theme=" + theme + ", sender=" + sender + ", sendTime="
				+ sendTime + "]";
	}
}
