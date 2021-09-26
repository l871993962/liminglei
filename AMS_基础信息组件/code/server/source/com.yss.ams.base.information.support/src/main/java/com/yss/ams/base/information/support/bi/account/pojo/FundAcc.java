package com.yss.ams.base.information.support.bi.account.pojo;

import java.util.Date;

import com.yss.framework.api.common.co.AuditableParamPojo;

/**
 * @ClassName FundAcc
 * @Description 统一支付平台代码独立，从uco复制该对象<br>产品账户信息
 * @author liminghong@ysstech.com
 * @CreateDate 2016年12月12日上午9:26:24
 * @Version V4.5.0.1 
 * @Copyright (c) 2016, 深圳赢时胜 All Rights Reserved.
 */
public class FundAcc extends AuditableParamPojo{
	
	/**
	 * 组合代码
	 */
	private String c_PORT_CODE = "";
	
	/**
	 * 开户名称
	 */
	private String c_OPEN_ACC_NAME = "";

	/**
	 * 账户代码
	 */
	private String c_CA_CODE = "";

	/**
	 * 货币代码
	 */
	private String c_DC_CODE = "";

	/**
	 * 开户地址
	 */
	private String c_OPEN_ADDR = "";

	/**
	 * 开户账号
	 */
	private String c_OPEN_ACC_NO = "";
	
	/**
	 * 机构代码/所有人
	 */
	private String c_ORG_CODE = "";
	
	/**
	 * 资产代码
	 */
	private String c_ASS_CODE = "";
	
	/**
	 *所有人 
	 */
	private String c_HOLDER = "";
	
	/**
	 * 划款用途
	 */
	private String c_USAGE = "" ;

	/**
	 * 划款备注
	 */
	private String c_DESC = "" ;
	
	/**
	 * 开户日期
	 */
	private Date d_BEGIN = null;
	
	/**
	 * 关户日期
	 */
	private Date d_END = null;
	
	//STORY #39954 支付产品账户信息list界面增加大额支付号列
    //BUG #157433 清算指令界面报错
	/**
	 * 大额支付号
	 */
	private String c_PAY_CODE = "";
	
	/**
	 * 流水账号
	 * STORY #56733 实现OA数据与指令数据、指令数据与银行明细流水的报表汇总统计
	 */
	private String c_FLOW_ACC_NO = "";

	/**
	 * 账号是否是已使用
	 * STORY #26009 华泰证券-增加收款账户第一次使用时提醒功能 
	 * 20150924_赵冠超_需求26009,t_c_cp_fund_acc添加字段C_HAVEUSED
	 */
	private String c_HAVEUSED;
	
	/**
	 * 账户类型
	 * 20160219 zhaoguanchao STORY #28868 华泰证券-直销业务清算指令管理
	 */
	private String c_ACCOUNT_TYPE = "";
	
	/**
	 * 中间行
	 * STORY #34439 【招商基金】【紧急】QDII：划款指令模板设计 
	 */
	private String c_INTER_ORG_CODE = "";
	
	/**
	 * 开户省份
	 * BUG #173020 支付产品账户信息：点击新增，维护‘开户省份’和‘开户城市’后，点击保存，未成功保存‘开户省份’和‘开户城市’
	 */
	private String c_PROVINCE = "";
	
	/**
	 * 开户城市
	 * BUG #173020 支付产品账户信息：点击新增，维护‘开户省份’和‘开户城市’后，点击保存，未成功保存‘开户省份’和‘开户城市’
	 */
	private String c_CITY = "";
	
	/**
	 * 开户方式  
	 * 清算账户体系一体化
	 */
	private String c_OPEN_MODE = "";
	
	/**
	 * 中行机构号 
	 * 清算账户体系一体化
	 */
	private String C_BC_ORG_CODE = "";
	
	/**
	 * 流水账号  
	 * 清算账户体系一体化
	 */
	private String C_RUNNING_ACC = "";
	
	/**
	 * 中行联行号  
	 * 清算账户体系一体化
	 */
	private String C_BC_LINK_NO = "";
	
	/**
	 * 虚拟号
	 * 清算账户体系一体化
	 */
	private String C_CNX = "";
	
	/**
	 * 银行国际统一码  
	 * 清算账户体系一体化
	 */
	private String C_SWIFT_CODE = "";

	/**
	 * 开户行地址
	 * 清算账户体系一体化
	 */
	private String C_BANK_ADDR = "";
	
	/**
	 * 数据来源 
	 */
	private String C_SOURCE_FLAG = "";
	
	/**
	 * 币种名称
	 */
	private String c_DC_NAME = "";
	
	/**
	 * 账户状态
	 */
	private String C_DISCARD_STATUS = "";
	
	/**
	 * 附件条数
	 */
	private int n_FILE_COUNT = 0;
	
	/**
	 * 划款秘钥C_PAYMENT_KEY
	 */
	private String c_PAYMENT_KEY = "";
	
	/**
	 * 开户行简称
	 */
	private String c_OPEN_JC = "";
	
	
	
	public String getC_OPEN_JC() {
		return c_OPEN_JC;
	}

	public void setC_OPEN_JC(String c_OPEN_JC) {
		this.c_OPEN_JC = c_OPEN_JC;
	}

	public int getN_FILE_COUNT() {
		return n_FILE_COUNT;
	}

	public void setN_FILE_COUNT(int n_FILE_COUNT) {
		this.n_FILE_COUNT = n_FILE_COUNT;
	}
	
    public String getC_DC_NAME() {
		return c_DC_NAME;
	}

	public void setC_DC_NAME(String c_DC_NAME) {
		this.c_DC_NAME = c_DC_NAME;
	}
	public String getC_BC_ORG_CODE() {
		return C_BC_ORG_CODE;
	}

	public void setC_BC_ORG_CODE(String c_BC_ORG_CODE) {
		C_BC_ORG_CODE = c_BC_ORG_CODE;
	}

	public String getC_RUNNING_ACC() {
		return C_RUNNING_ACC;
	}

	public void setC_RUNNING_ACC(String c_RUNNING_ACC) {
		C_RUNNING_ACC = c_RUNNING_ACC;
	}

	public String getC_BC_LINK_NO() {
		return C_BC_LINK_NO;
	}

	public void setC_BC_LINK_NO(String c_BC_LINK_NO) {
		C_BC_LINK_NO = c_BC_LINK_NO;
	}

	public String getC_CNX() {
		return C_CNX;
	}

	public void setC_CNX(String c_CNX) {
		C_CNX = c_CNX;
	}

	public String getC_SWIFT_CODE() {
		return C_SWIFT_CODE;
	}

	public void setC_SWIFT_CODE(String c_SWIFT_CODE) {
		C_SWIFT_CODE = c_SWIFT_CODE;
	}

	public String getC_BANK_ADDR() {
		return C_BANK_ADDR;
	}

	public void setC_BANK_ADDR(String c_BANK_ADDR) {
		C_BANK_ADDR = c_BANK_ADDR;
	}

	
	public String getC_PROVINCE() {
		return c_PROVINCE;
	}

	public void setC_PROVINCE(String c_PROVINCE) {
		this.c_PROVINCE = c_PROVINCE;
	}

	public String getC_CITY() {
		return c_CITY;
	}

	public void setC_CITY(String c_CITY) {
		this.c_CITY = c_CITY;
	}
	
	public String getC_PAY_CODE() {
		return c_PAY_CODE;
	}

	public void setC_PAY_CODE(String c_PAY_CODE) {
		this.c_PAY_CODE = c_PAY_CODE;
	}
	
    public String getC_ASS_CODE() {
		return c_ASS_CODE;
	}

	public void setC_ASS_CODE(String c_ASS_CODE) {
		this.c_ASS_CODE = c_ASS_CODE;
	}

	public String getC_HOLDER() {
		return c_HOLDER;
	}

	public void setC_HOLDER(String c_HOLDER) {
		this.c_HOLDER = c_HOLDER;
	}

	public String getC_OPEN_ACC_NAME() {
		return c_OPEN_ACC_NAME;
	}

	public void setC_OPEN_ACC_NAME(String cOPENACCNAME) {
		c_OPEN_ACC_NAME = cOPENACCNAME;
	}

	public String getC_CA_CODE() {
		return c_CA_CODE;
	}

	public void setC_CA_CODE(String cCACODE) {
		c_CA_CODE = cCACODE;
	}

	private static final long serialVersionUID = 1L ;

	public String getC_DC_CODE() {
		return c_DC_CODE;
	}

	public void setC_DC_CODE(String cDCCODE) {
		c_DC_CODE = cDCCODE;
	}

	public String getC_OPEN_ADDR() {
		return c_OPEN_ADDR;
	}

	public void setC_OPEN_ADDR(String cOPENADDR) {
		c_OPEN_ADDR = cOPENADDR;
	}

	public String getC_OPEN_ACC_NO() {
		return c_OPEN_ACC_NO;
	}

	public void setC_OPEN_ACC_NO(String cOPENACCNO) {
		c_OPEN_ACC_NO = cOPENACCNO;
	}

	public String getC_ORG_CODE() {
		return c_ORG_CODE;
	}

	public void setC_ORG_CODE(String c_ORG_CODE) {
		this.c_ORG_CODE = c_ORG_CODE;
	}

	public String getC_USAGE() {
		return c_USAGE;
	}

	public void setC_USAGE(String cUSAGE) {
		c_USAGE = cUSAGE;
	}

	public String getC_DESC() {
		return c_DESC;
	}

	public void setC_DESC(String cDESC) {
		c_DESC = cDESC;
	}
	
	public String getC_PORT_CODE() {
		return c_PORT_CODE;
	}

	public void setC_PORT_CODE(String cPORTCODE) {
		c_PORT_CODE = cPORTCODE;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getD_BEGIN() {
		return d_BEGIN;
	}

	public void setD_BEGIN(Date dBEGIN) {
		d_BEGIN = dBEGIN;
	}

	public Date getD_END() {
		return d_END;
	}

	public void setD_END(Date dEND) {
		d_END = dEND;
	}

	public String getC_HAVEUSED() {
		return c_HAVEUSED;
	}

	public void setC_HAVEUSED(String c_HAVEUSED) {
		this.c_HAVEUSED = c_HAVEUSED;
	}

	public String getC_ACCOUNT_TYPE() {
		return c_ACCOUNT_TYPE;
	}

	public void setC_ACCOUNT_TYPE(String c_ACCOUNT_TYPE) {
		this.c_ACCOUNT_TYPE = c_ACCOUNT_TYPE;
	}

	public String getC_INTER_ORG_CODE() {
		return c_INTER_ORG_CODE;
	}

	public void setC_INTER_ORG_CODE(String c_INTER_ORG_CODE) {
		this.c_INTER_ORG_CODE = c_INTER_ORG_CODE;
	}

	public String getC_OPEN_MODE() {
		return c_OPEN_MODE;
	}

	public void setC_OPEN_MODE(String c_OPEN_MODE) {
		this.c_OPEN_MODE = c_OPEN_MODE;
	}

	public String getC_SOURCE_FLAG() {
		return C_SOURCE_FLAG;
	}

	public void setC_SOURCE_FLAG(String c_SOURCE_FLAG) {
		C_SOURCE_FLAG = c_SOURCE_FLAG;
	}

	public String getC_DISCARD_STATUS() {
		return C_DISCARD_STATUS;
	}

	public void setC_DISCARD_STATUS(String c_DISCARD_STATUS) {
		C_DISCARD_STATUS = c_DISCARD_STATUS;
	}

	public String getC_FLOW_ACC_NO() {
		return c_FLOW_ACC_NO;
	}

	public void setC_FLOW_ACC_NO(String c_FLOW_ACC_NO) {
		this.c_FLOW_ACC_NO = c_FLOW_ACC_NO;
	}
	
	public String getC_PAYMENT_KEY() {
		return c_PAYMENT_KEY;
	}

	public void setC_PAYMENT_KEY(String c_PAYMENT_KEY) {
		this.c_PAYMENT_KEY = c_PAYMENT_KEY;
	}
	
}
