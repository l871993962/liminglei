package com.yss.uco.elecreco.er.ws.pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

public class RequestHeader {
	
	/**
	 * 版本号（1.0.0） 
	 */
	@XmlElement(name = "DS_VER")
	private String version;
	
	/**
	 * 签名
	 */
	@XmlElement(name = "DS_SIGN")
	private String sign;
	
	/**
	 * 签名方式
	 */
	@XmlElement(name = "DS_SIGNTYPE")
	private String signType;
	
	/**
	 * 消息类型
	 */
	@XmlElement(name = "DS_MSG_TYPE")
	private String msgType;
	
	/**
	 * 推送发系统标识
	 */
	@XmlElement(name = "DS_SYS_CODE")
	private String sysCode;
	
	/**
	 * 功能码
	 */
	@XmlElement(name = "DS_FUNC")
	private String funCode;
	
	/**
	 * 通知时间
	 */
	@XmlElement(name = "DS_MSG_TM")
	private String msgTime;

	/**
	 * 消息报文体
	 */
	@XmlElement(name = "DS_MSG_BODY")
	private String msgBody;

	
	@XmlTransient
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@XmlTransient
	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	@XmlTransient
	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	@XmlTransient
	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	@XmlTransient
	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	@XmlTransient
	public String getFunCode() {
		return funCode;
	}

	public void setFunCode(String funCode) {
		this.funCode = funCode;
	}

	@XmlTransient
	public String getMsgTime() {
		return msgTime;
	}

	public void setMsgTime(String msgTime) {
		this.msgTime = msgTime;
	}

	@XmlTransient
	public String getMsgBody() {
		return msgBody;
	}

	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}
	
	
}
