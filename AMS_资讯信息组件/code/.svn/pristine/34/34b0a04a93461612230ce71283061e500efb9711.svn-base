package com.yss.ams.sec.information.support.modules.aa.etf.pojo;

import java.math.BigDecimal;
import java.util.List;

import com.yss.framework.api.common.co.AuditableParamPojo;
import com.yss.framework.api.common.co.IEffectivable;
import com.yss.ams.sec.information.support.modules.aa.etf.pojo.EtfStd;
/**
 * ETF基本信息 pojo类
 * @author gongyue
 * 资讯信息拆分	2017.7.5 STORY #42948 资讯信息管理组件化拆分
 */
public class Etf extends AuditableParamPojo implements IEffectivable {
	/**
	 * 投资组合
	 */
	private String c_PORT_CODE = "";

	/**
	 * 交易市场
	 */
	private String c_MKT_CODE = "";

	/**
	 * 申赎席位
	 */
	private String c_TD_CHAN_CODE = "";

	/**
	 * 补票方式
	 */
	private String c_DV_SUPPLY_MODE = "";

	/**
	 * 台账显示模式
	 */
	private String c_DV_BOOK_MODE = "";

	/**
	 * 申赎代码
	 */
	private String c_SR_CODE = "";

	/**
	 * 交易代码
	 */
	private String c_TRADE_CODE = "";

	/**
	 * 资金结算代码
	 */
	private String c_SF_CODE = "";

	/**
	 * 描述
	 */
	private String c_DESC = "";

	/**
	 * 基准份额
	 */
	private BigDecimal n_BASE_AMOUNT = BigDecimal.ZERO;

	// /**
	// * 开始日期
	// */
	// private String d_BEGIN = "";
	//
	// /**
	// * 结束日期
	// */
	// private String d_END = "";

	/**
	 * 确认天数
	 */
	private int n_CONFIRM = 0;

	/**
	 * ETF类型
	 */
	private String c_ETF_TYPE = "";

	/**
	 * 补票汇率
	 */
	private String c_RATE_SUPPLY = "";

	/**
	 * 申赎汇率
	 */
	private String c_RATE_SR = "";

	/**
	 * 股票篮溢价比 By Jinghehe 2013-12-24
	 */
	private String n_INFER_PROTION ="";
	
	
	/**
	 * 席位类别 By Jinghehe 2014-8-14
	 */
	
	private String c_DV_TYPE_CODE ="";
	
	/**
	 * 补票日期List  // editby liyongjun 2016-03-08  STORY28860上交所跨市场ETF基金产品整体需求  补票方式修改从补票日期设置界面获取，处理多市场不同补票方式的问题
	 */
	private List<EtfStd> etfStdList = null;
	
	public String getC_DV_TYPE_CODE() {
		return c_DV_TYPE_CODE;
	}

	public void setC_DV_TYPE_CODE(String c_DV_TYPE_CODE) {
		this.c_DV_TYPE_CODE = c_DV_TYPE_CODE;
	}

	public String getN_INFER_PROTION() {
		return n_INFER_PROTION;
	}

	public void setN_INFER_PROTION(String n_INFER_PROTION) {
		this.n_INFER_PROTION = n_INFER_PROTION;
	}

	private static final long serialVersionUID = 1L;

	public String getC_PORT_CODE() {
		return c_PORT_CODE;
	}

	public void setC_PORT_CODE(String cPORTCODE) {
		c_PORT_CODE = cPORTCODE;
	}

	public String getC_MKT_CODE() {
		return c_MKT_CODE;
	}

	public void setC_MKT_CODE(String cMKTCODE) {
		c_MKT_CODE = cMKTCODE;
	}

	public String getC_TD_CHAN_CODE() {
		return c_TD_CHAN_CODE;
	}

	public void setC_TD_CHAN_CODE(String cTDCHANCODE) {
		c_TD_CHAN_CODE = cTDCHANCODE;
	}

	public String getC_DV_SUPPLY_MODE() {
		return c_DV_SUPPLY_MODE;
	}

	public void setC_DV_SUPPLY_MODE(String cDVSUPPLYMODE) {
		c_DV_SUPPLY_MODE = cDVSUPPLYMODE;
	}

	public String getC_DV_BOOK_MODE() {
		return c_DV_BOOK_MODE;
	}

	public void setC_DV_BOOK_MODE(String cDVBOOKMODE) {
		c_DV_BOOK_MODE = cDVBOOKMODE;
	}

	public String getC_SR_CODE() {
		return c_SR_CODE;
	}

	public void setC_SR_CODE(String cSRCODE) {
		c_SR_CODE = cSRCODE;
	}

	public String getC_TRADE_CODE() {
		return c_TRADE_CODE;
	}

	public void setC_TRADE_CODE(String cTRADECODE) {
		c_TRADE_CODE = cTRADECODE;
	}

	public String getC_SF_CODE() {
		return c_SF_CODE;
	}

	public void setC_SF_CODE(String cSFCODE) {
		c_SF_CODE = cSFCODE;
	}

	public String getC_DESC() {
		return c_DESC;
	}

	public void setC_DESC(String cDESC) {
		c_DESC = cDESC;
	}

	public BigDecimal getN_BASE_AMOUNT() {
		return n_BASE_AMOUNT;
	}

	public void setN_BASE_AMOUNT(BigDecimal nBASEAMOUNT) {
		n_BASE_AMOUNT = nBASEAMOUNT;
	}

	public int getN_CONFIRM() {
		return n_CONFIRM;
	}

	public void setN_CONFIRM(int nCONFIRM) {
		n_CONFIRM = nCONFIRM;
	}

	public String getC_ETF_TYPE() {
		return c_ETF_TYPE;
	}

	public void setC_ETF_TYPE(String cETFTYPE) {
		c_ETF_TYPE = cETFTYPE;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String bizPrimeKeyNames() {
		// TODO Auto-generated method stub
		return "c_PORT_CODE,c_TD_CHAN_CODE";
	}

	public String getC_RATE_SUPPLY() {
		return c_RATE_SUPPLY;
	}

	public void setC_RATE_SUPPLY(String cRATESUPPLY) {
		c_RATE_SUPPLY = cRATESUPPLY;
	}

	public String getC_RATE_SR() {
		return c_RATE_SR;
	}

	public void setC_RATE_SR(String cRATESR) {
		c_RATE_SR = cRATESR;
	}

	public List<EtfStd> getEtfStdList() {
		return etfStdList;
	}

	public void setEtfStdList(List<EtfStd> etfStdList) {
		this.etfStdList = etfStdList;
	}
}
