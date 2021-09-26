package com.yss.uco.elecreco.support.bean;

import com.yss.framework.api.common.co.AuditableParamPojo;

public class ErBbInfo extends AuditableParamPojo  {
	
	private static final long serialVersionUID = 4366187116066683856L;

	/**
	 * 报文序号
	 */
	private String c_SN = "";

	/**
	 * 报文方向
	 */
	private String c_DV_ER_WAY = "";
	
	/**
	 * 基金代码
	 */
	private String c_ASS_CODE = "";
	
	/**
	 * 组合代码
	 */
	private String c_PORT_CODE = "";
	
	/**
	 * 文件类型
	 */
	private String c_FILE_TYPE = "";
	
	/**
	 * 报表类型
	 */
	private String c_RPT_TYPE = "";
	
	/**
	 * 处理状态
	 */
	private String c_STATE = "";
	
	/**
	 * 对账日期
	 */
	private String d_DATE = "";

	/**
	 * 错误信息
	 */
	private String c_ERR_INFO = "";
	
	/**
	 * 净值确认编号
	 */
	private String c_CONFIRM_ID = "";
	
	/**
	 * 净值确认状态
	 */
	private String c_CONFIRM_EXECUTE = "";
	
	/**
	 * STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求）
	 * 托管行代码
	 */
	private String c_TGH_CODE = "";
	
	/**
	 * STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求）
	 * 拆分代码
	 */
	private String c_SPLIT_CODE = "";
	
	/**
	 * 手工原因
	 */
	private String c_DV_RESULT = "";
	
	/**
	 * 说明
	 */
	private String c_SUMMARY = "";
	
	/**
	 * STORY58759嘉实基金-电子对账-电子对账管理界面净值确认按钮修改
	 * 净值锁定状态
	 */
	private String c_LOCK_EXECUTE = "";
	
	/**
	 * STORY #104480 净值确认管理优化整合需求对电子对账管理的影响评估优化
	 */
	private int n_JZAUDIT_STATE = 0; 
	
	/**
	 * STORY #109004 【公共需求】电子对账管理中增加显示“反馈联系人”列
	 */
	private String c_DEALER;
	
	public int getN_JZAUDIT_STATE() {
		return n_JZAUDIT_STATE;
	}

	public void setN_JZAUDIT_STATE(int n_JZAUDIT_STATE) {
		this.n_JZAUDIT_STATE = n_JZAUDIT_STATE;
	}

	public String getC_LOCK_EXECUTE() {
		return c_LOCK_EXECUTE;
	}

	public void setC_LOCK_EXECUTE(String c_LOCK_EXECUTE) {
		this.c_LOCK_EXECUTE = c_LOCK_EXECUTE;
	}
	
	
	public String getC_TGH_CODE() {
		return c_TGH_CODE;
	}

	public void setC_TGH_CODE(String c_TGH_CODE) {
		this.c_TGH_CODE = c_TGH_CODE;
	}

	public String getC_SPLIT_CODE() {
		return c_SPLIT_CODE;
	}

	public void setC_SPLIT_CODE(String c_SPLIT_CODE) {
		this.c_SPLIT_CODE = c_SPLIT_CODE;
	}
	public String getC_DV_RESULT() {
		return c_DV_RESULT;
	}

	public void setC_DV_RESULT(String cDVRESULT) {
		this.c_DV_RESULT = cDVRESULT;
	}

	public String getC_SUMMARY() {
		return c_SUMMARY;
	}

	public void setC_SUMMARY(String cSUMMARY) {
		this.c_SUMMARY = cSUMMARY;
	}

	public String getC_PORT_CODE() {
		return c_PORT_CODE;
	}

	public void setC_PORT_CODE(String c_PORT_CODE) {
		this.c_PORT_CODE = c_PORT_CODE;
	}
	
	public String getC_ERR_INFO() {
		return c_ERR_INFO;
	}

	public void setC_ERR_INFO(String cERRINFO) {
		c_ERR_INFO = cERRINFO;
	}


	public String getD_DATE() {
		return d_DATE;
	}


	public void setD_DATE(String dDATE) {
		d_DATE = dDATE;
	}


	public String getC_SN() {
		return c_SN;
	}


	public void setC_SN(String cSN) {
		c_SN = cSN;
	}


	public String getC_DV_ER_WAY() {
		return c_DV_ER_WAY;
	}


	public void setC_DV_ER_WAY(String cDVERWAY) {
		c_DV_ER_WAY = cDVERWAY;
	}

	public String getC_ASS_CODE() {
		return c_ASS_CODE;
	}

	public void setC_ASS_CODE(String cASSCODE) {
		c_ASS_CODE = cASSCODE;
	}


	public String getC_FILE_TYPE() {
		return c_FILE_TYPE;
	}


	public void setC_FILE_TYPE(String cFILETYPE) {
		c_FILE_TYPE = cFILETYPE;
	}


	public String getC_RPT_TYPE() {
		return c_RPT_TYPE;
	}


	public void setC_RPT_TYPE(String cRPTTYPE) {
		c_RPT_TYPE = cRPTTYPE;
	}


	public String getC_STATE() {
		return c_STATE;
	}


	public void setC_STATE(String cSTATE) {
		c_STATE = cSTATE;
	}

	
	public String getC_CONFIRM_ID() {
		return c_CONFIRM_ID;
	}

	public void setC_CONFIRM_ID(String c_CONFIRM_ID) {
		this.c_CONFIRM_ID = c_CONFIRM_ID;
	}

	public String getC_CONFIRM_EXECUTE() {
		return c_CONFIRM_EXECUTE;
	}

	public void setC_CONFIRM_EXECUTE(String c_CONFIRM_EXECUTE) {
		this.c_CONFIRM_EXECUTE = c_CONFIRM_EXECUTE;
	}

	public String getC_DEALER() {
		return c_DEALER;
	}

	public void setC_DEALER(String c_DEALER) {
		this.c_DEALER = c_DEALER;
	}
	 
}
