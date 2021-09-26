package com.yss.ams.syncdata.support.modules.base.pojo;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 产品同步实体类
 * @description 
 * @author zhangyongzhao
 * @version 1.0, 2018年6月13日
 */
public class SyncPort{
	
	/*产品简称*/
	@XmlAttribute(name = "c_PORT_NAME_ST")
	private String c_PORT_NAME_ST = "";
	
	/*产品全称*/
	@XmlAttribute(name = "c_PORT_NAME")
	private String c_PORT_NAME = "";
	
	/*资产代码*/
	@XmlAttribute(name = "c_ASS_CODE")
	private String c_ASS_CODE = "";
	
	/*产品归属部门*/
	@XmlAttribute(name = "c_CORP_ORG_CODE")
	private String c_CORP_ORG_CODE = "";
	
	/*资产类型*/
	@XmlAttribute(name = "c_DAT_CODE")
	private String c_DAT_CODE = "";
	
	/*产品类型*/
	@XmlAttribute(name = "c_INVEST_CODE")
	private String c_INVEST_CODE = "";
	
	/*管理类型*/
	@XmlAttribute(name = "c_COLLECT_CODE")
	private String c_COLLECT_CODE = "";
	
	/*风险等级*/
	@XmlAttribute(name = "c_RISK_LEVEL")
	private String c_RISK_LEVEL = "";
	
	/*产品转型*/
	@XmlAttribute(name = "c_PRO_TRANS")
	private String c_PRO_TRANS = "";
	
	/*管理人*/
	@XmlAttribute(name = "c_DV_MANAGER")
	private String c_DV_MANAGER = "";
	
	/*托管人*/
	@XmlAttribute(name = "c_DV_TRUSTEE")
	private String c_DV_TRUSTEE = "";
	
	/*委托人*/
	@XmlAttribute(name = "c_DV_CONSIGNER")
	private String c_DV_CONSIGNER = "";
	
	/*初始规模（元）*/
	@XmlAttribute(name = "c_INI_SCALE")
	private String c_INI_SCALE = "";
	
	/*合同生效日*/
	@XmlAttribute(name = "d_CONTRACT_START")
	private Date d_CONTRACT_START ;
	
	/*成立日期*/
	@XmlAttribute(name = "d_BUILD")
	private String d_BUILD ;
	
	/*终止日期*/
	@XmlAttribute(name = "d_CLOSE")
	private String d_CLOSE ;
	
	/*募集开始日*/
	@XmlAttribute(name = "d_COLLECT_START")
	private Date d_COLLECT_START ;
	
	/*募集截止日*/
	@XmlAttribute(name = "d_COLLECT_END")
	private Date d_COLLECT_END ;
	
	/*提前结束募集日*/
	@XmlAttribute(name = "d_RAISE_END")
	private Date d_RAISE_END ;
	
	/*净值精度*/
	@XmlAttribute(name = "c_NETVALUE_ACC")
	private String c_NETVALUE_ACC = "";
	
	/*销售方式*/
	@XmlAttribute(name = "c_SALE_MODE")
	private String c_SALE_MODE = "";
	
	/*披露媒体*/
	@XmlAttribute(name = "c_PUB_MEDIA")
	private String c_PUB_MEDIA = "";
	
	/*注册登记机构*/
	@XmlAttribute(name = "c_REGIS_ORG")
	private String c_REGIS_ORG = "";
	
	/*是否分级*/
	@XmlAttribute(name = "c_WHE_GRADE")
	private String c_WHE_GRADE = "";
	
	/*是否有投顾人*/
	@XmlAttribute(name = "c_WHE_INV_ADVISER")
	private String c_WHE_INV_ADVISER = "";
	
	/*投资顾问*/
	@XmlAttribute(name = "c_INV_ADVISER")
	private String c_INV_ADVISER = "";
	
	/*是否有网下打新资格*/
	@XmlAttribute(name = "c_WHE_NEW_SHARES")
	private String c_WHE_NEW_SHARES = "";
	
	/*是否开立银行间账户*/
	@XmlAttribute(name = "c_WHE_INTER_BANK")
	private String c_WHE_INTER_BANK = "";
	
	/*交易资格信息*/
	@XmlAttribute(name = "c_TRADE_INFO")
	private String c_TRADE_INFO = "";

	/*合同存续期*/
	@XmlAttribute(name = "n_PRO_TIME")
	private String n_PRO_TIME = "";
	
	/*期限单位*/
	@XmlAttribute(name = "c_PT_UNIT")
	private String c_PT_UNIT = "";
	
	/*结算模式*/
	@XmlAttribute(name = "c_CLEAR_MODE")
	private String c_CLEAR_MODE = "";
	
	/*场内简称*/
	@XmlAttribute(name = "c_CN_NAME_ST")
	private String c_CN_NAME_ST = "";
	
	/*前端交易代码*/
	@XmlAttribute(name = "c_QDJY_CODE")
	private String c_QDJY_CODE = "";
	
	/*后端交易代码*/
	@XmlAttribute(name = "c_HDJY_CODE")
	private String c_HDJY_CODE = "";
	
	/*基金上市交易所*/
	@XmlAttribute(name = "c_FUND_LAUNCH_MKT")
	private String c_FUND_LAUNCH_MKT = "";
	
	/*上市日期*/
	@XmlAttribute(name = "d_LAUNCH_DATE")
	private Date d_LAUNCH_DATE ;
	
	/*产品二级分类*/
	@XmlAttribute(name = "c_INVEST_SECOND_CODE")
	private String c_INVEST_SECOND_CODE = "";
	
	/*组合代码*/
	@XmlAttribute(name = "c_PORT_CODE")
	private String c_PORT_CODE = "";
	
	/*组合币种*/
	@XmlAttribute(name = "c_DC_CODE")
	private String c_DC_CODE = "";
	
	/*组合级别*/
	@XmlAttribute(name = "c_DV_PORT_CODE")
	private String c_DV_PORT_CODE = "";
	
	/*节假日群*/
	@XmlAttribute(name = "c_HDAY_CODE")
	private String c_HDAY_CODE = "";
	
	/*资产类别*/
	@XmlAttribute(name = "c_DAT_CLS")
	private String c_DAT_CLS = "";
	
	/*产品状态*/
	@XmlAttribute(name = "c_DV_PROD_STATE")
	private String c_DV_PROD_STATE = "";
	
	/*产品分级*/
	@XmlElement(name = "SyncPortCls")
	private List<SyncPortCls> SyncPortCls;
	
	/*明细资产类型*/
	@XmlAttribute(name = "c_DAT_TYPE")
	private String c_DAT_TYPE = "";

	@XmlTransient
	public String getC_DAT_TYPE() {
		return c_DAT_TYPE;
	}

	public void setC_DAT_TYPE(String c_DAT_TYPE) {
		this.c_DAT_TYPE = c_DAT_TYPE;
	}

	@XmlTransient
	public String getC_PORT_NAME_ST() {
		return c_PORT_NAME_ST;
	}

	public void setC_PORT_NAME_ST(String c_PORT_NAME_ST) {
		this.c_PORT_NAME_ST = c_PORT_NAME_ST;
	}

	@XmlTransient
	public String getC_PORT_NAME() {
		return c_PORT_NAME;
	}

	public void setC_PORT_NAME(String c_PORT_NAME) {
		this.c_PORT_NAME = c_PORT_NAME;
	}

	@XmlTransient
	public String getC_ASS_CODE() {
		return c_ASS_CODE;
	}

	public void setC_ASS_CODE(String c_ASS_CODE) {
		this.c_ASS_CODE = c_ASS_CODE;
	}

	@XmlTransient
	public String getC_CORP_ORG_CODE() {
		return c_CORP_ORG_CODE;
	}

	public void setC_CORP_ORG_CODE(String c_CORP_ORG_CODE) {
		this.c_CORP_ORG_CODE = c_CORP_ORG_CODE;
	}

	@XmlTransient
	public String getC_DAT_CODE() {
		return c_DAT_CODE;
	}

	public void setC_DAT_CODE(String c_DAT_CODE) {
		this.c_DAT_CODE = c_DAT_CODE;
	}

	@XmlTransient
	public String getC_INVEST_CODE() {
		return c_INVEST_CODE;
	}

	public void setC_INVEST_CODE(String c_INVEST_CODE) {
		this.c_INVEST_CODE = c_INVEST_CODE;
	}

	@XmlTransient
	public String getC_COLLECT_CODE() {
		return c_COLLECT_CODE;
	}

	public void setC_COLLECT_CODE(String c_COLLECT_CODE) {
		this.c_COLLECT_CODE = c_COLLECT_CODE;
	}

	@XmlTransient
	public String getC_RISK_LEVEL() {
		return c_RISK_LEVEL;
	}

	public void setC_RISK_LEVEL(String c_RISK_LEVEL) {
		this.c_RISK_LEVEL = c_RISK_LEVEL;
	}

	@XmlTransient
	public String getC_PRO_TRANS() {
		return c_PRO_TRANS;
	}

	public void setC_PRO_TRANS(String c_PRO_TRANS) {
		this.c_PRO_TRANS = c_PRO_TRANS;
	}

	@XmlTransient
	public String getC_DV_MANAGER() {
		return c_DV_MANAGER;
	}

	public void setC_DV_MANAGER(String c_DV_MANAGER) {
		this.c_DV_MANAGER = c_DV_MANAGER;
	}

	@XmlTransient
	public String getC_DV_TRUSTEE() {
		return c_DV_TRUSTEE;
	}

	public void setC_DV_TRUSTEE(String c_DV_TRUSTEE) {
		this.c_DV_TRUSTEE = c_DV_TRUSTEE;
	}
	
	@XmlTransient
	public String getC_DV_CONSIGNER() {
		return c_DV_CONSIGNER;
	}

	public void setC_DV_CONSIGNER(String c_DV_CONSIGNER) {
		this.c_DV_CONSIGNER = c_DV_CONSIGNER;
	}

	@XmlTransient
	public String getC_INI_SCALE() {
		return c_INI_SCALE;
	}

	public void setC_INI_SCALE(String c_INI_SCALE) {
		this.c_INI_SCALE = c_INI_SCALE;
	}

	@XmlTransient
	public Date getD_CONTRACT_START() {
		return d_CONTRACT_START;
	}

	public void setD_CONTRACT_START(Date d_CONTRACT_START) {
		this.d_CONTRACT_START = d_CONTRACT_START;
	}

	@XmlTransient
	public String getD_BUILD() {
		return d_BUILD;
	}

	public void setD_BUILD(String d_BUILD) {
		this.d_BUILD = d_BUILD;
	}
	
	@XmlTransient
	public String getD_CLOSE() {
		return d_CLOSE;
	}

	public void setD_CLOSE(String d_CLOSE) {
		this.d_CLOSE = d_CLOSE;
	}

	@XmlTransient
	public Date getD_COLLECT_START() {
		return d_COLLECT_START;
	}

	public void setD_COLLECT_START(Date d_COLLECT_START) {
		this.d_COLLECT_START = d_COLLECT_START;
	}

	@XmlTransient
	public Date getD_COLLECT_END() {
		return d_COLLECT_END;
	}

	public void setD_COLLECT_END(Date d_COLLECT_END) {
		this.d_COLLECT_END = d_COLLECT_END;
	}

	@XmlTransient
	public Date getD_RAISE_END() {
		return d_RAISE_END;
	}

	public void setD_RAISE_END(Date d_RAISE_END) {
		this.d_RAISE_END = d_RAISE_END;
	}

	@XmlTransient
	public String getC_NETVALUE_ACC() {
		return c_NETVALUE_ACC;
	}

	public void setC_NETVALUE_ACC(String c_NETVALUE_ACC) {
		this.c_NETVALUE_ACC = c_NETVALUE_ACC;
	}

	@XmlTransient
	public String getC_SALE_MODE() {
		return c_SALE_MODE;
	}

	public void setC_SALE_MODE(String c_SALE_MODE) {
		this.c_SALE_MODE = c_SALE_MODE;
	}

	@XmlTransient
	public String getC_PUB_MEDIA() {
		return c_PUB_MEDIA;
	}

	public void setC_PUB_MEDIA(String c_PUB_MEDIA) {
		this.c_PUB_MEDIA = c_PUB_MEDIA;
	}

	@XmlTransient
	public String getC_REGIS_ORG() {
		return c_REGIS_ORG;
	}

	public void setC_REGIS_ORG(String c_REGIS_ORG) {
		this.c_REGIS_ORG = c_REGIS_ORG;
	}

	@XmlTransient
	public String getC_WHE_GRADE() {
		return c_WHE_GRADE;
	}

	public void setC_WHE_GRADE(String c_WHE_GRADE) {
		this.c_WHE_GRADE = c_WHE_GRADE;
	}

	@XmlTransient
	public String getC_WHE_INV_ADVISER() {
		return c_WHE_INV_ADVISER;
	}

	public void setC_WHE_INV_ADVISER(String c_WHE_INV_ADVISER) {
		this.c_WHE_INV_ADVISER = c_WHE_INV_ADVISER;
	}

	@XmlTransient
	public String getC_INV_ADVISER() {
		return c_INV_ADVISER;
	}

	public void setC_INV_ADVISER(String c_INV_ADVISER) {
		this.c_INV_ADVISER = c_INV_ADVISER;
	}

	@XmlTransient
	public String getC_WHE_NEW_SHARES() {
		return c_WHE_NEW_SHARES;
	}

	public void setC_WHE_NEW_SHARES(String c_WHE_NEW_SHARES) {
		this.c_WHE_NEW_SHARES = c_WHE_NEW_SHARES;
	}

	@XmlTransient
	public String getC_WHE_INTER_BANK() {
		return c_WHE_INTER_BANK;
	}

	public void setC_WHE_INTER_BANK(String c_WHE_INTER_BANK) {
		this.c_WHE_INTER_BANK = c_WHE_INTER_BANK;
	}

	@XmlTransient
	public String getC_TRADE_INFO() {
		return c_TRADE_INFO;
	}

	public void setC_TRADE_INFO(String c_TRADE_INFO) {
		this.c_TRADE_INFO = c_TRADE_INFO;
	}

	@XmlTransient
	public String getN_PRO_TIME() {
		return n_PRO_TIME;
	}

	public void setN_PRO_TIME(String n_PRO_TIME) {
		this.n_PRO_TIME = n_PRO_TIME;
	}

	@XmlTransient
	public String getC_PT_UNIT() {
		return c_PT_UNIT;
	}

	public void setC_PT_UNIT(String c_PT_UNIT) {
		this.c_PT_UNIT = c_PT_UNIT;
	}

	@XmlTransient
	public String getC_CLEAR_MODE() {
		return c_CLEAR_MODE;
	}

	public void setC_CLEAR_MODE(String c_CLEAR_MODE) {
		this.c_CLEAR_MODE = c_CLEAR_MODE;
	}

	@XmlTransient
	public String getC_CN_NAME_ST() {
		return c_CN_NAME_ST;
	}

	public void setC_CN_NAME_ST(String c_CN_NAME_ST) {
		this.c_CN_NAME_ST = c_CN_NAME_ST;
	}

	@XmlTransient
	public String getC_QDJY_CODE() {
		return c_QDJY_CODE;
	}

	public void setC_QDJY_CODE(String c_QDJY_CODE) {
		this.c_QDJY_CODE = c_QDJY_CODE;
	}

	@XmlTransient
	public String getC_HDJY_CODE() {
		return c_HDJY_CODE;
	}

	public void setC_HDJY_CODE(String c_HDJY_CODE) {
		this.c_HDJY_CODE = c_HDJY_CODE;
	}

	@XmlTransient
	public String getC_FUND_LAUNCH_MKT() {
		return c_FUND_LAUNCH_MKT;
	}

	public void setC_FUND_LAUNCH_MKT(String c_FUND_LAUNCH_MKT) {
		this.c_FUND_LAUNCH_MKT = c_FUND_LAUNCH_MKT;
	}

	@XmlTransient
	public Date getD_LAUNCH_DATE() {
		return d_LAUNCH_DATE;
	}

	public void setD_LAUNCH_DATE(Date d_LAUNCH_DATE) {
		this.d_LAUNCH_DATE = d_LAUNCH_DATE;
	}

	@XmlTransient
	public String getC_INVEST_SECOND_CODE() {
		return c_INVEST_SECOND_CODE;
	}

	public void setC_INVEST_SECOND_CODE(String c_INVEST_SECOND_CODE) {
		this.c_INVEST_SECOND_CODE = c_INVEST_SECOND_CODE;
	}

	@XmlTransient
	public String getC_PORT_CODE() {
		return c_PORT_CODE;
	}

	public void setC_PORT_CODE(String c_PORT_CODE) {
		this.c_PORT_CODE = c_PORT_CODE;
	}

	@XmlTransient
	public String getC_DC_CODE() {
		return c_DC_CODE;
	}

	public void setC_DC_CODE(String c_DC_CODE) {
		this.c_DC_CODE = c_DC_CODE;
	}

	@XmlTransient
	public String getC_DV_PORT_CODE() {
		return c_DV_PORT_CODE;
	}

	public void setC_DV_PORT_CODE(String c_DV_PORT_CODE) {
		this.c_DV_PORT_CODE = c_DV_PORT_CODE;
	}

	@XmlTransient
	public String getC_HDAY_CODE() {
		return c_HDAY_CODE;
	}

	public void setC_HDAY_CODE(String c_HDAY_CODE) {
		this.c_HDAY_CODE = c_HDAY_CODE;
	}

	@XmlTransient
	public String getC_DAT_CLS() {
		return c_DAT_CLS;
	}

	public void setC_DAT_CLS(String c_DAT_CLS) {
		this.c_DAT_CLS = c_DAT_CLS;
	}

	@XmlTransient
	public String getC_DV_PROD_STATE() {
		return c_DV_PROD_STATE;
	}

	public void setC_DV_PROD_STATE(String c_DV_PROD_STATE) {
		this.c_DV_PROD_STATE = c_DV_PROD_STATE;
	}

	@XmlTransient
	public List<SyncPortCls> getSyncPortCls() {
		return SyncPortCls;
	}

	public void setSyncPortCls(List<SyncPortCls> syncPortCls) {
		SyncPortCls = syncPortCls;
	}

}
