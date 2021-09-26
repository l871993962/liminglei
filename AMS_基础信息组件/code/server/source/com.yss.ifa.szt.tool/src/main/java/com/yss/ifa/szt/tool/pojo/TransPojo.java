package com.yss.ifa.szt.tool.pojo;

import java.util.List;

/**
 * 估值系统传给通用接口的数据
 * @author weijj
 *
 */
public class TransPojo implements Cloneable{
	/**
	 * 返回错误原因
	 */
	private String errInfo = "";
	/**
	 * 返回发送结果 -1 接收对账结果  0 发送成功  1 发送失败 
	 * -深证通链接检测时：-1： 链路通畅；0：估值到伺服器通畅，伺服器到深圳通不通；1：估值到伺服器不通
	 */
	private String result  = "";
	/**
	 * 目标用户
	 */
	private String toUser = "";
	/**
	 * 目标应用
	 */
	private String toApp = "";
	/**
	 * 源用户
	 */
	private String fromUser = "";
	/**
	 * 源应用
	 */
	private String fromApp = ""; 
	/**
	 * 包发送密码
	 */
	private String pkgSecretCode = "";
	/**
	 * 通道类型
	 * by weijj 20150715 STORY #24165
	 */
	private String commType = ""; 
	/**
	 * STORY39802南方基金-支持设置通过深证通发送指令、余额明细查询报文的发送优先级
	 * 发送优先级 0为普通；1为紧急
	 */
	private int priority = 0;
	/**
	 * 深圳通数据编号
	 */
	private String pkgId = "";
	/**
	 * 交换的数据内容
	 */
	private String sendStr = "";
	/**
	 * 加密方式
	 * STORY60297【富国基金】支持电子对账参数设置支持民生银行多管理人不同秘钥配置
	 */
	private String secretType = "";
	/**
	 * 密钥
	 * STORY60297【富国基金】支持电子对账参数设置支持民生银行多管理人不同秘钥配置
	 */
	private String secretKey = "";
	/**
	 * 报文编码
	 * STORY60297【富国基金】支持电子对账参数设置支持民生银行多管理人不同秘钥配置
	 */
	private String charSet = "";
	
	/**
	 * 报文类型	填写报文类型，如：1241/1271/1295
	 * STORY #85229 电子对账发送电子指令时需调用支付接口校验通过才能发送
	 */
	private String msgType = "";
	
	/**
	 * 业务主键 如指令编号：填写指令ID，唯一
	 * STORY #85229 电子对账发送电子指令时需调用支付接口校验通过才能发送
	 */
	private String busiKey = "";
	
	/**
	 * 回调验证地址
	 * STORY #85229 电子对账发送电子指令时需调用支付接口校验通过才能发送
	 */
	private String url = "";
	
	/**
	 * 业务类型 填写电子对账设置中的业务类型：如：BUSI_DZ/BUSI_DZZL
	 * BUG #329074 【富国基金】0228电子对账处理深证通返回报文判断FUND_ID是不是拆分代码，电子指令不需要此逻辑
	 */
	private String busType;
	
	/**
	 * 深圳通反馈编号
	 */
	private String corPkgid = "";
	
	public String getCorPkgid() {
		return corPkgid;
	}
	public void setCorPkgid(String corPkgid) {
		this.corPkgid = corPkgid;
	}
	public String getPkgId() {
		return pkgId;
	}
	public void setPkgId(String pkgId) {
		this.pkgId = pkgId;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
		/**
	 * 伺服器连接配置信息
	 */
	private List<MrInfo> mrInfoList = null;
	
	public List<MrInfo> getMrInfoList() {
		return mrInfoList;
	}
	public void setMrInfoList(List<MrInfo> mrInfoList) {
		this.mrInfoList = mrInfoList;
	}
	
	public String getErrInfo() {
		return errInfo;
	}
	public void setErrInfo(String errInfo) {
		this.errInfo = errInfo;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getSendStr() {
		return sendStr;
	}
	public void setSendStr(String sendStr) {
		this.sendStr = sendStr;
	}
	public String getToUser() {
		return toUser;
	}
	public void setToUser(String toUser) {
		this.toUser = toUser;
	}
	public String getToApp() {
		return toApp;
	}
	public void setToApp(String toApp) {
		this.toApp = toApp;
	}
	public String getFromUser() {
		return fromUser;
	}
	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}
	public String getFromApp() {
		return fromApp;
	}
	public void setFromApp(String fromApp) {
		this.fromApp = fromApp;
	}
	public String getPkgPassword() {
		return pkgSecretCode;
	}
	public void setPkgPassword(String pkgPassword) {
		this.pkgSecretCode = pkgPassword;
	}
	public String getCommType() {
		return commType;
	}
	public void setCommType(String commType) {
		this.commType = commType;
	}
	
	public String getSecretType() {
		return secretType;
	}
	public void setSecretType(String secretType) {
		this.secretType = secretType;
	}
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public String getCharSet() {
		return charSet;
	}
	public void setCharSet(String charSet) {
		this.charSet = charSet;
	}
	/**
	 * Orlando 20160105 增加该字段做为数据唯一标识，用于伺服器接收数据后输出日志
	 */
	private String key = "";
	
	/**
	 * 消息的唯一标识，该值重复的会被丢弃
	 */
	private String primaryKey = "";

	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	public String getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getBusiKey() {
		return busiKey;
	}
	public void setBusiKey(String busiKey) {
		this.busiKey = busiKey;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getBusType() {
		return busType;
	}
	public void setBusType(String busType) {
		this.busType = busType;
	}
	
}
