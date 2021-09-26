package com.yss.ams.base.information.support.bi.org.pojo;

import java.math.BigDecimal;
import java.util.Date;

import com.yss.framework.api.common.co.AuditableParamPojo;

/**
 * 机构pojo
 * @author 马向峰  拆分  2017.0527
 *
 */
public class Org extends AuditableParamPojo {


	/**
	 * 机构代码
	 */
	private String c_ORG_CODE = "";

	/**
	 * 机构名称
	 */
	private String c_ORG_NAME = "";

	/**
	 * 中文名称
	 */
	private String c_ORG_NAME_CN = "";

	/**
	 * 机构简称
	 */
	private String c_ORG_NAME_ST = "";

	/**
	 * 母公司
	 */
	private String c_ORG_CODE_P = "";

	/**
	 * 法人代表
	 */
	private String c_CORP_REP = "";

	/**
	 * 资本币种
	 */
	private String c_DC_CODE = "";

	/**
	 * 注册资本
	 */
	private BigDecimal N_REG_CAP = BigDecimal.ZERO;

	/**
	 * 注册地址
	 */
	private String c_REG_ADDR = "";

	/**
	 * 办公地址
	 */
	private String c_OFFIC_ADDR = "";

	/**
	 * 机构类型
	 */
	private String c_DV_ORG_TYPE = "";

	/**
	 * 公司代码
	 */
	private String c_CORP_CODE = "";

	/**
	 * 联系人
	 */
	private String c_LINK_MAN = "";
	
	/**
	 * 岗位
	 * STORY #81326 【华宝兴业】关联结构设置 中联系信息维护调整
	 */
	private String c_POST_NAME = "";

	/**
	 * 联系电话
	 */
	private String c_LINK_TEL = "";

	/**
	 * 手机号码
	 */
	private String c_MO_TEL = "";

	/**
	 * 电子邮箱
	 */
	private String c_EMAIL = "";

	/**
	 * 注册邮编
	 */
	private String c_REG_POST = "";

	/**
	 * 机构描述
	 */
	private String c_DESC = "";

	/**
	 * 办公邮编
	 */
	private String c_OFFIC_POST = "";

	/**
	 * 国际互联网址 add by Yuntao Lau 2015.09.15 STORY #25681
	 */
	private String c_WWW_ADDR = "";

	/**
	 * 传真号码 add by Yuntao Lau 2015.09.15 STORY #25681
	 */
	private String c_FAX_TEL = "";

	/**
	 * 市场代码
	 */
	private String c_MKT_CODE = "";

	/**
	 ** 管理人
	 */
	private String c_DV_MANAGER = "";
	
	//STORY #32976 嘉实基金-管理人信息区分实际管理人和名义管理人 add by shijian 2016-09-26
	/**
	 ** 次管理人
	 */
	private String c_DV_MANAGER_SEC = "";

	/**
	 ** 托管人
	 */
	private String c_DV_TRUSTEE = "";
	
	/**
	 ** 次托管人
	 */
	private String c_DV_TRUSTEE_SEC = "";

	/**
	 ** 担保人
	 */
	private String c_DV_WARRANTOR = "";

	/**
	 ** 投资顾问
	 */
	private String c_DV_INVEST_ADVISER = "";

	/**
	 ** 信托人
	 */
	private String c_DV_TRUSTEE_XT = "";

	/**
	 ** 销售渠道
	 */
	private String c_DV_SALES_CHANNELS = "";

	/**
	 ** 结算会员
	 */
	private String c_DV_CLEARING_MEMBER = "";

	/**
	 * 机构资质
	 */
	private String c_QUALIFICATION = "";

	/**
	 ** c_PLACE_SETTLEMENT
	 **/
	private String c_PLACE_SETTLEMENT = "";

	/**
	 ** c_CLEAT_ACCOUNT
	 **/
	private String c_CLEAR_ACCOUNT = "";

	/**
	 ** c_BROKER_ID
	 **/
	private String c_BROKER_ID = "";

	/**
	 ** c_BROKER_NAME
	 **/
	private String c_BROKER_NAME = "";

	/**
	 ** c_BROKER_ID_TYPE
	 **/
	private String c_BROKER_ID_TYPE = "";

	/**
	 ** c_CLEARER_ID
	 **/
	private String c_CLEARER_ID = "";

	/**
	 ** c_CLEARER_NAME
	 **/
	private String c_CLEARER_NAME = "";

	/**
	 ** c_CLEARER_ID_TYPE
	 **/
	private String c_CLEARER_ID_TYPE = "";

	// 机构logo
	private String c_LOGO_NAME = "";

	// 大额支付号
	private String c_PAY_CODE = "";

	// 联行号
	private String c_BANK_CODE = "";

	// 第3方委托
	private String c_DV_TRD_CLIENT = "";

	// 委托人
	private String c_DV_CONSIGNER = "";

	//受托人
	private String c_DV_DEPOSITARY = "";

	// 保险委托
	private String c_DV_BX_CLIENT = "";
	
    /// 主体资质字段汇总
    private String c_DV_SUM = "";   
    
    /**
     * 主体性质（默认为“金融机构”）
     * STORY49170【招商证券】【营改增】银行间交易需要区分对手方
     * liuxiang 2017-12-22
     */
    private String c_DV_FIN_ORG  = "JRJG";
    
    /**
     * 联系人信息
     * STORY #81326 【华宝兴业】关联结构设置 中联系信息维护调整
     */
    private String c_LINKMAN_LIST = "";
    
    /**
     * STORY #63833 关联机构设置中新增财汇机构代码和财汇机构简称字段 
	 * 财汇机构代码
	 */
	private String c_CH_ORG_CODE = "";

	/**
	 * STORY #63833 关联机构设置中新增财汇机构代码和财汇机构简称字段 
	 * 财汇机构名称
	 */
	private String c_CH_ORG_NAME = "";
    
    /**
     * 单位电话
     * STORY #48731 长江养老电子发票需求-委托人信息接口导入 add by qinxinglin 2018-1-11
     */
    private String C_CORP_TEL = "";
    
    /**
     * 开票类型
     * STORY #48731 长江养老电子发票需求-委托人信息接口导入 add by qinxinglin 2018-1-11
     */
    private String c_DV_INOVIC_TYPE = "";
    
    /**
	* 关联机构开始日期
	*/
	  private Date d_RELA_START ;

	/**
	* 关联机构结束日期
	*/
	  private Date d_RELA_END ;
	
	/**
	 * 是否为关联方  add by lujianhao 20180705 STORY #51721 光大证券-监管类信息完善
	*/
	private String c_ISRElATED = "";
	
	/**
	 * STORY #108912 【大家资产】估值核算20210723---证券基本信息中新增融资人，担保人字段
	 */
	private String c_DV_RZR = "";
		
	public String getC_DV_RZR() {
		return c_DV_RZR;
	}

	public void setC_DV_RZR(String c_DV_RZR) {
		this.c_DV_RZR = c_DV_RZR;
	}

	public String getC_CH_ORG_CODE() {
		return c_CH_ORG_CODE;
	}

	public void setC_CH_ORG_CODE(String c_CH_ORG_CODE) {
		this.c_CH_ORG_CODE = c_CH_ORG_CODE;
	}

	public String getC_CH_ORG_NAME() {
		return c_CH_ORG_NAME;
	}

	public void setC_CH_ORG_NAME(String c_CH_ORG_NAME) {
		this.c_CH_ORG_NAME = c_CH_ORG_NAME;
	}

    
    public String getC_DV_FIN_ORG() {
		return c_DV_FIN_ORG;
	}

	public void setC_DV_FIN_ORG(String c_DV_FIN_ORG) {
		this.c_DV_FIN_ORG = c_DV_FIN_ORG;
	}

	public String getC_DV_SUM() {
		return c_DV_SUM;
	}

	public void setC_DV_SUM(String c_DV_SUM) {
		this.c_DV_SUM = c_DV_SUM;
	}

	/**
	 * 机构属性
	 * 
	 * @author liuxiang 2015-9-6 STORY #22255 保监会报表需要细化银行属性
	 */
	private String c_DV_ORG_ATTR = "";

	/**
	 * 成立时间 MeiYuan 2016.2.25
	 */
	private String d_FOUND_TIME = "";

	/**
	 * 发行人 add by wangyaokang STORY #29358 【中行】产品创设及发行（产品要素改造）
	 */
	private String c_DV_ISSUER = "";

	/**
	 * 20160330 added by liubo.STORY #29359 【中行】交易对手方、评级机构的维护 对手方
	 */
	private String c_DV_COUNTERPARTY = "";

	/**
	 * 20160411 added by liubo.STORY #29359 【中行】交易对手方、评级机构的维护 行业类别
	 */
	private String c_INDUSTRY_TYPE = "";

	/**
	 * c_BROKER_ACCOUNT 2016-5-10 addby wsm STORY30235
	 * 【招商基金母公司】[QDII]TradeBlotter_EXP TradeBlotter招行导出接口
	 */
	private String c_BROKER_ACCOUNT = "";

	/**
	 * 20160727 added by heliang.STORY #30646 【云平台】万联证券关联机构设置增加外包服务机构类型 外包服务机构
	 */
	private String c_DV_WBFWJG = "";

	/**
	 ** 主托管人
	 */
	private String c_DV_TRUSTEE_MA = "";
	
	/**
	 * 20170113 added by HeLiang STORY #37662 光大银行新增自定义资产情况报表
	 * 销售机构
	 */
	private String c_DV_MARKETING = "";
	/**
	 * @author xuyuanhao STORY37444针对中登FISP平台改造主动信息模块 2017-2-14
	 */

	// 法人代表证件代码
	private String c_REP_CARD_CODE = "";

	//法人代表证件类型
	private String c_DV_REPCARD_TYPE = "";

	// 法人证件有效期
	private String d_CARD_VAL_DUR = "";
	
	// 法人证件有效期结束日期
	private String d_CARD_VAL_DUR_END = "";

	// 投管人代码
	private String c_ADMIN_CODE = "";

	//投管人名称
	private String c_ADMIN_NAME = "";

	// 投管人性质
	private String c_ADMIN_NATURE = "";

	// 投资人证件类型
	private String c_IVT_CARD_TYPE = "";

	//投资人证件号码
	private String c_IVT_CARD_NO = "";

	// 投资人证件号码有效期
	private String d_IVT_CARD_VALDUR = "";
	
	// 投资人证件号码有效期结束日期
	private String d_IVT_CARD_VALDUR_END = "";
	
	/**
	 * Author : zhoushuhang
	 * Date   : 2017-05-19
	 * Status : Add
	 * Comment: BUG #160288 点击资本币种下拉框报错
	 * @return  主体编码
	 */
	private String c_ORG_ENCODE = "";
	
	/**
	 * STORY #43971 需求上海-[长江养老]金融资产管理平台V4.5[高]20170705（关联机构设置界面增加“纳税人识别号”文本框）
	 * 纳税人识别号
	 */
    private String c_TAXPAYER_NO = "";
    
   /**
    * STORY #43971 需求上海-[长江养老]金融资产管理平台V4.5[高]20170705（关联机构设置界面增加“纳税人识别号”文本框）
    * 纳税人类型
    */
    private String c_DV_TAXPAYER_TYPE = "";
    
   /**
    * STORY #43971 需求上海-[长江养老]金融资产管理平台V4.5[高]20170705（关联机构设置界面增加“纳税人识别号”文本框）
    * 银行账户
    */
    private String c_BANK_ACC = "";
    
    /**
     * STORY #43971 需求上海-[长江养老]金融资产管理平台V4.5[高]20170705（关联机构设置界面增加“纳税人识别号”文本框）
     * 开户银行 
     */
    private String c_DEPOSIT_BANK = "";
    
    /**
     * STORY #43971 需求上海-[长江养老]金融资产管理平台V4.5[高]20170705（关联机构设置界面增加“纳税人识别号”文本框）
     * 主管税务机关
     */
    private String c_TAX_AUTHORITIES = "";
    
    /**
	 * STORY #43111 【海通证券】 关联机构设置新增机构内码字段，区分现有的机构代码字段
	 */
	private String c_ORG_INNER_CODE="";
	
	/**
     * 备注
     * add by guoguangyi 20171025 STORY #46387 【安信基金场外交易】主体信息界面增加备注字段 
     */
    private String c_REMARK = "";
	
    /**
	 * c_BROKER_ACCOUNT 2016-5-10 addby wsm STORY30235
	 * 【招商基金母公司】[QDII]TradeBlotter_EXP TradeBlotter招行导出接口
	 * By Jinghehe 2017-8-11 原字段修改成  C_PLATE_CODE 前台list 界面可以代码转名称，否则无法进行转换 
	 */
	private String c_PLATE_CODE = "";
	
	/**
	 * STORY #103374 【中欧基金】【版本V1.300.7.0.20210228.0323】导给彭博的净值f5888inav.inc、现金余额f5888icsh.inc接口文件及TA申赎f5888icsh.inc文件
	 */
	private String c_PB_CODE = "";
	
	public String getC_PB_CODE() {
		return c_PB_CODE;
	}

	public void setC_PB_CODE(String c_PB_CODE) {
		this.c_PB_CODE = c_PB_CODE;
	}
	
	public String getC_PLATE_CODE() {
		return c_PLATE_CODE;
	}

	public void setC_PLATE_CODE(String c_PLATE_CODE) {
		this.c_PLATE_CODE = c_PLATE_CODE;
	}
    
	public String getC_REMARK() {
		return c_REMARK;
	}

	public void setC_REMARK(String c_REMARK) {
		this.c_REMARK = c_REMARK;
	}

	public String getC_ORG_INNER_CODE() {
		return c_ORG_INNER_CODE;
	}

	public void setC_ORG_INNER_CODE(String c_ORG_INNER_CODE) {
		this.c_ORG_INNER_CODE = c_ORG_INNER_CODE;
	}

	public String getD_CARD_VAL_DUR_END() {
		return d_CARD_VAL_DUR_END;
	}

	public void setD_CARD_VAL_DUR_END(String d_CARD_VAL_DUR_END) {
		this.d_CARD_VAL_DUR_END = d_CARD_VAL_DUR_END;
	}

	public String getD_IVT_CARD_VALDUR_END() {
		return d_IVT_CARD_VALDUR_END;
	}

	public void setD_IVT_CARD_VALDUR_END(String d_IVT_CARD_VALDUR_END) {
		this.d_IVT_CARD_VALDUR_END = d_IVT_CARD_VALDUR_END;
	}

	public String getC_REP_CARD_CODE() {
		return c_REP_CARD_CODE;
	}

	public void setC_REP_CARD_CODE(String c_REP_CARD_CODE) {
		this.c_REP_CARD_CODE = c_REP_CARD_CODE;
	}

	public String getC_DV_REPCARD_TYPE() {
		return c_DV_REPCARD_TYPE;
	}

	public void setC_DV_REPCARD_TYPE(String c_DV_REPCARD_TYPE) {
		this.c_DV_REPCARD_TYPE = c_DV_REPCARD_TYPE;
	}

	public String getD_CARD_VAL_DUR() {
		return d_CARD_VAL_DUR;
	}

	public void setD_CARD_VAL_DUR(String d_CARD_VAL_DUR) {
		this.d_CARD_VAL_DUR = d_CARD_VAL_DUR;
	}

	public String getC_ADMIN_CODE() {
		return c_ADMIN_CODE;
	}

	public void setC_ADMIN_CODE(String c_ADMIN_CODE) {
		this.c_ADMIN_CODE = c_ADMIN_CODE;
	}

	public String getC_ADMIN_NAME() {
		return c_ADMIN_NAME;
	}

	public void setC_ADMIN_NAME(String c_ADMIN_NAME) {
		this.c_ADMIN_NAME = c_ADMIN_NAME;
	}

	public String getC_ADMIN_NATURE() {
		return c_ADMIN_NATURE;
	}

	public void setC_ADMIN_NATURE(String c_ADMIN_NATURE) {
		this.c_ADMIN_NATURE = c_ADMIN_NATURE;
	}

	public String getC_IVT_CARD_TYPE() {
		return c_IVT_CARD_TYPE;
	}

	public void setC_IVT_CARD_TYPE(String c_IVT_CARD_TYPE) {
		this.c_IVT_CARD_TYPE = c_IVT_CARD_TYPE;
	}

	public String getC_IVT_CARD_NO() {
		return c_IVT_CARD_NO;
	}

	public void setC_IVT_CARD_NO(String c_IVT_CARD_NO) {
		this.c_IVT_CARD_NO = c_IVT_CARD_NO;
	}

	public String getD_IVT_CARD_VALDUR() {
		return d_IVT_CARD_VALDUR;
	}

	public void setD_IVT_CARD_VALDUR(String d_IVT_CARD_VALDUR) {
		this.d_IVT_CARD_VALDUR = d_IVT_CARD_VALDUR;
	}

	/**
	 * 托管账户标号 HuangJin 2016.10.19 STORY #28264 机构基本信息 字段优化
	 */
	private String c_TG_ACCOUNT_CODE;

	/**
     * Author : ChenLong
     * Date   : 2016-11-22
     * Status : Add
     * Comment: 电子对账
     */
    private String c_ELEC_RECONCILIATION;
	
	public String getC_BROKER_ACCOUNT() {
		return c_BROKER_ACCOUNT;
	}

	public void setC_BROKER_ACCOUNT(String c_BROKER_ACCOUNT) {
		this.c_BROKER_ACCOUNT = c_BROKER_ACCOUNT;
	}

	public String getC_INDUSTRY_TYPE() {
		return c_INDUSTRY_TYPE;
	}

	public void setC_INDUSTRY_TYPE(String c_INDUSTRY_TYPE) {
		this.c_INDUSTRY_TYPE = c_INDUSTRY_TYPE;
	}

	public String getC_DV_COUNTERPARTY() {
		return c_DV_COUNTERPARTY;
	}

	public void setC_DV_COUNTERPARTY(String c_DV_COUNTERPARTY) {
		this.c_DV_COUNTERPARTY = c_DV_COUNTERPARTY;
	}

	private static final long serialVersionUID = 1L;

	public String getC_ORG_CODE() {
		return c_ORG_CODE;
	}

	public void setC_ORG_CODE(String cORGCODE) {
		c_ORG_CODE = cORGCODE;
	}

	public String getC_ORG_NAME() {
		return c_ORG_NAME;
	}

	public void setC_ORG_NAME(String cORGNAME) {
		c_ORG_NAME = cORGNAME;
	}

	public String getC_ORG_NAME_CN() {
		return c_ORG_NAME_CN;
	}

	public void setC_ORG_NAME_CN(String cORGNAMECN) {
		c_ORG_NAME_CN = cORGNAMECN;
	}

	public String getC_ORG_NAME_ST() {
		return c_ORG_NAME_ST;
	}

	public void setC_ORG_NAME_ST(String cORGNAMEST) {
		c_ORG_NAME_ST = cORGNAMEST;
	}

	public String getC_ORG_CODE_P() {
		return c_ORG_CODE_P;
	}

	public void setC_ORG_CODE_P(String cORGCODEP) {
		c_ORG_CODE_P = cORGCODEP;
	}

	public String getC_CORP_REP() {
		return c_CORP_REP;
	}

	public void setC_CORP_REP(String cCORPREP) {
		c_CORP_REP = cCORPREP;
	}

	public String getC_DC_CODE() {
		return c_DC_CODE;
	}

	public void setC_DC_CODE(String cDCCODE) {
		c_DC_CODE = cDCCODE;
	}

	public BigDecimal getN_REG_CAP() {
		return N_REG_CAP;
	}

	public void setN_REG_CAP(BigDecimal nREGCAP) {
		N_REG_CAP = nREGCAP;
	}

	public String getC_REG_ADDR() {
		return c_REG_ADDR;
	}

	public void setC_REG_ADDR(String cREGADDR) {
		c_REG_ADDR = cREGADDR;
	}

	public String getC_OFFIC_ADDR() {
		return c_OFFIC_ADDR;
	}

	public void setC_OFFIC_ADDR(String cOFFICADDR) {
		c_OFFIC_ADDR = cOFFICADDR;
	}

	public String getC_DV_ORG_TYPE() {
		return c_DV_ORG_TYPE;
	}

	public void setC_DV_ORG_TYPE(String cDVORGTYPE) {
		c_DV_ORG_TYPE = cDVORGTYPE;
	}

	public String getC_CORP_CODE() {
		return c_CORP_CODE;
	}

	public void setC_CORP_CODE(String cCORPCODE) {
		c_CORP_CODE = cCORPCODE;
	}

	public String getC_LINK_MAN() {
		return c_LINK_MAN;
	}

	public void setC_LINK_MAN(String cLINKMAN) {
		c_LINK_MAN = cLINKMAN;
	}

	public String getC_POST_NAME() {
		return c_POST_NAME;
	}

	public void setC_POST_NAME(String c_POST_NAME) {
		this.c_POST_NAME = c_POST_NAME;
	}

	public String getC_LINK_TEL() {
		return c_LINK_TEL;
	}

	public void setC_LINK_TEL(String cLINKTEL) {
		c_LINK_TEL = cLINKTEL;
	}

	public String getC_MO_TEL() {
		return c_MO_TEL;
	}

	public void setC_MO_TEL(String cMOTEL) {
		c_MO_TEL = cMOTEL;
	}

	public String getC_EMAIL() {
		return c_EMAIL;
	}

	public void setC_EMAIL(String cEMAIL) {
		c_EMAIL = cEMAIL;
	}

	public String getC_REG_POST() {
		return c_REG_POST;
	}

	public void setC_REG_POST(String cREGPOST) {
		c_REG_POST = cREGPOST;
	}

	public String getC_DESC() {
		return c_DESC;
	}

	public void setC_DESC(String cDESC) {
		c_DESC = cDESC;
	}

	public String getC_OFFIC_POST() {
		return c_OFFIC_POST;
	}

	public void setC_OFFIC_POST(String cOFFICPOST) {
		c_OFFIC_POST = cOFFICPOST;
	}

	public String getC_WWW_ADDR() {
		return c_WWW_ADDR;
	}

	public void setC_WWW_ADDR(String c_WWW_ADDR) {
		this.c_WWW_ADDR = c_WWW_ADDR;
	}

	public String getC_FAX_TEL() {
		return c_FAX_TEL;
	}

	public void setC_FAX_TEL(String c_FAX_TEL) {
		this.c_FAX_TEL = c_FAX_TEL;
	}

	public String getC_MKT_CODE() {
		return c_MKT_CODE;
	}

	public void setC_MKT_CODE(String cMKTCODE) {
		c_MKT_CODE = cMKTCODE;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getC_DV_MANAGER() {
		return c_DV_MANAGER;
	}

	public void setC_DV_MANAGER(String c_DV_MANAGER) {
		this.c_DV_MANAGER = c_DV_MANAGER;
	}
	
	public String getC_DV_MANAGER_SEC() {
		return c_DV_MANAGER_SEC;
	}
	
	public void setC_DV_MANAGER_SEC(String c_DV_MANAGER_SEC) {
		this.c_DV_MANAGER_SEC = c_DV_MANAGER_SEC;
	}

	public String getC_DV_TRUSTEE() {
		return c_DV_TRUSTEE;
	}

	public void setC_DV_TRUSTEE(String c_DV_TRUSTEE) {
		this.c_DV_TRUSTEE = c_DV_TRUSTEE;
	}

	public String getC_DV_TRUSTEE_SEC() {
		return c_DV_TRUSTEE_SEC;
	}

	public void setC_DV_TRUSTEE_SEC(String c_DV_TRUSTEE_SEC) {
		this.c_DV_TRUSTEE_SEC = c_DV_TRUSTEE_SEC;
	}

	public String getC_DV_WARRANTOR() {
		return c_DV_WARRANTOR;
	}

	public void setC_DV_WARRANTOR(String c_DV_WARRANTOR) {
		this.c_DV_WARRANTOR = c_DV_WARRANTOR;
	}

	public String getC_DV_INVEST_ADVISER() {
		return c_DV_INVEST_ADVISER;
	}

	public void setC_DV_INVEST_ADVISER(String c_DV_INVEST_ADVISER) {
		this.c_DV_INVEST_ADVISER = c_DV_INVEST_ADVISER;
	}

	public String getC_DV_TRUSTEE_XT() {
		return c_DV_TRUSTEE_XT;
	}

	public void setC_DV_TRUSTEE_XT(String c_DV_TRUSTEE_XT) {
		this.c_DV_TRUSTEE_XT = c_DV_TRUSTEE_XT;
	}

	public String getC_DV_SALES_CHANNELS() {
		return c_DV_SALES_CHANNELS;
	}

	public void setC_DV_SALES_CHANNELS(String c_DV_SALES_CHANNELS) {
		this.c_DV_SALES_CHANNELS = c_DV_SALES_CHANNELS;
	}

	public String getC_DV_CLEARING_MEMBER() {
		return c_DV_CLEARING_MEMBER;
	}

	public void setC_DV_CLEARING_MEMBER(String c_DV_CLEARING_MEMBER) {
		this.c_DV_CLEARING_MEMBER = c_DV_CLEARING_MEMBER;
	}

	public String getC_QUALIFICATION() {
		return c_QUALIFICATION;
	}

	public void setC_QUALIFICATION(String c_QUALIFICATION) {
		this.c_QUALIFICATION = c_QUALIFICATION;
	}

	public String getC_PLACE_SETTLEMENT() {
		return c_PLACE_SETTLEMENT;
	}

	public void setC_PLACE_SETTLEMENT(String c_PLACE_SETTLEMENT) {
		this.c_PLACE_SETTLEMENT = c_PLACE_SETTLEMENT;
	}

	public String getC_CLEAR_ACCOUNT() {
		return c_CLEAR_ACCOUNT;
	}

	public void setC_CLEAR_ACCOUNT(String c_CLEAR_ACCOUNT) {
		this.c_CLEAR_ACCOUNT = c_CLEAR_ACCOUNT;
	}

	public String getC_BROKER_ID() {
		return c_BROKER_ID;
	}

	public void setC_BROKER_ID(String c_BROKER_ID) {
		this.c_BROKER_ID = c_BROKER_ID;
	}

	public String getC_BROKER_NAME() {
		return c_BROKER_NAME;
	}

	public void setC_BROKER_NAME(String c_BROKER_NAME) {
		this.c_BROKER_NAME = c_BROKER_NAME;
	}

	public String getC_BROKER_ID_TYPE() {
		return c_BROKER_ID_TYPE;
	}

	public void setC_BROKER_ID_TYPE(String c_BROKER_ID_TYPE) {
		this.c_BROKER_ID_TYPE = c_BROKER_ID_TYPE;
	}

	public String getC_CLEARER_ID() {
		return c_CLEARER_ID;
	}

	public void setC_CLEARER_ID(String c_CLEARER_ID) {
		this.c_CLEARER_ID = c_CLEARER_ID;
	}

	public String getC_CLEARER_NAME() {
		return c_CLEARER_NAME;
	}

	public void setC_CLEARER_NAME(String c_CLEARER_NAME) {
		this.c_CLEARER_NAME = c_CLEARER_NAME;
	}

	public String getC_CLEARER_ID_TYPE() {
		return c_CLEARER_ID_TYPE;
	}

	public void setC_CLEARER_ID_TYPE(String c_CLEARER_ID_TYPE) {
		this.c_CLEARER_ID_TYPE = c_CLEARER_ID_TYPE;
	}

	public String getC_LOGO_NAME() {
		return c_LOGO_NAME;
	}

	public void setC_LOGO_NAME(String c_LOGO_NAME) {
		this.c_LOGO_NAME = c_LOGO_NAME;
	}

	public String getC_PAY_CODE() {
		return c_PAY_CODE;
	}

	public void setC_PAY_CODE(String c_PAY_CODE) {
		this.c_PAY_CODE = c_PAY_CODE;
	}

	public String getC_BANK_CODE() {
		return c_BANK_CODE;
	}

	public void setC_BANK_CODE(String c_BANK_CODE) {
		this.c_BANK_CODE = c_BANK_CODE;
	}

	public String getC_DV_TRD_CLIENT() {
		return c_DV_TRD_CLIENT;
	}

	public void setC_DV_TRD_CLIENT(String c_DV_TRD_CLIENT) {
		this.c_DV_TRD_CLIENT = c_DV_TRD_CLIENT;
	}

	public String getC_DV_CONSIGNER() {
		return c_DV_CONSIGNER;
	}

	public void setC_DV_CONSIGNER(String c_DV_CONSIGNER) {
		this.c_DV_CONSIGNER = c_DV_CONSIGNER;
	}

	public String getC_DV_BX_CLIENT() {
		return c_DV_BX_CLIENT;
	}

	public void setC_DV_BX_CLIENT(String c_DV_BX_CLIENT) {
		this.c_DV_BX_CLIENT = c_DV_BX_CLIENT;
	}

	public String getC_DV_DEPOSITARY() {
		return c_DV_DEPOSITARY;
	}

	public void setC_DV_DEPOSITARY(String c_DV_DEPOSITARY) {
		this.c_DV_DEPOSITARY = c_DV_DEPOSITARY;
	}

	public String getC_DV_ORG_ATTR() {
		return c_DV_ORG_ATTR;
	}

	public void setC_DV_ORG_ATTR(String c_DV_ORG_ATTR) {
		this.c_DV_ORG_ATTR = c_DV_ORG_ATTR;
	}

	public String getD_FOUND_TIME() {
		return d_FOUND_TIME;
	}

	public void setD_FOUND_TIME(String d_FOUND_TIME) {
		this.d_FOUND_TIME = d_FOUND_TIME;
	}

	public String getC_DV_ISSUER() {
		return c_DV_ISSUER;
	}

	public void setC_DV_ISSUER(String c_DV_ISSUER) {
		this.c_DV_ISSUER = c_DV_ISSUER;
	}

	/** Start 20160727 added by heliang.STORY #30646 【云平台】万联证券关联机构设置增加外包服务机构类型 */
	/**
	 * 
	 * @Title: getC_DV_WBFWJG
	 * @Description: TODO 获取外包服务机构
	 * @param @return
	 * @return String
	 * @throws
	 */
	public String getC_DV_WBFWJG() {
		return c_DV_WBFWJG;
	}

	/**
	 * 
	 * @Title: setC_DV_WBFWJG
	 * @Description: TODO 设置外包服务机构
	 * @param @param c_DV_WBFWJG
	 * @return void
	 * @throws
	 */
	public void setC_DV_WBFWJG(String c_DV_WBFWJG) {
		this.c_DV_WBFWJG = c_DV_WBFWJG;
	}

	/** End 20160727 added by heliang.STORY #30646 【云平台】万联证券关联机构设置增加外包服务机构类型 */

	public String getC_DV_TRUSTEE_MA() {
		return c_DV_TRUSTEE_MA;
	}

	public void setC_DV_TRUSTEE_MA(String c_DV_TRUSTEE_MA) {
		this.c_DV_TRUSTEE_MA = c_DV_TRUSTEE_MA;
	}

	public String getC_DV_MARKETING() {
		return c_DV_MARKETING;
	}

	public void setC_DV_MARKETING(String c_DV_MARKETING) {
		this.c_DV_MARKETING = c_DV_MARKETING;
	}
	
	public String getC_TG_ACCOUNT_CODE() {
		return c_TG_ACCOUNT_CODE;
	}

	public void setC_TG_ACCOUNT_CODE(String c_TG_ACCOUNT_CODE) {
		this.c_TG_ACCOUNT_CODE = c_TG_ACCOUNT_CODE;
	}
	
	public String getC_ELEC_RECONCILIATION() {
		return c_ELEC_RECONCILIATION;
	}

	public void setC_ELEC_RECONCILIATION(String c_ELEC_RECONCILIATION) {
		this.c_ELEC_RECONCILIATION = c_ELEC_RECONCILIATION;
	}
	
	/**
	 * Author : zhoushuhang
	 * Date   : 2017-05-19
	 * Status : Add
	 * Comment: BUG #160288 点击资本币种下拉框报错
	 * @return  主体编码
	 */
	public String getC_ORG_ENCODE() {
		return c_ORG_ENCODE;
	}

	public void setC_ORG_ENCODE(String c_ORG_ENCODE) {
		this.c_ORG_ENCODE = c_ORG_ENCODE;
	}

	public String getC_TAXPAYER_NO() {
		return c_TAXPAYER_NO;
	}

	public void setC_TAXPAYER_NO(String c_TAXPAYER_NO) {
		this.c_TAXPAYER_NO = c_TAXPAYER_NO;
	}

	public String getC_DV_TAXPAYER_TYPE() {
		return c_DV_TAXPAYER_TYPE;
	}

	public void setC_DV_TAXPAYER_TYPE(String c_DV_TAXPAYER_TYPE) {
		this.c_DV_TAXPAYER_TYPE = c_DV_TAXPAYER_TYPE;
	}

	public String getC_BANK_ACC() {
		return c_BANK_ACC;
	}

	public void setC_BANK_ACC(String c_BANK_ACC) {
		this.c_BANK_ACC = c_BANK_ACC;
	}

	public String getC_DEPOSIT_BANK() {
		return c_DEPOSIT_BANK;
	}

	public void setC_DEPOSIT_BANK(String c_DEPOSIT_BANK) {
		this.c_DEPOSIT_BANK = c_DEPOSIT_BANK;
	}

	public String getC_TAX_AUTHORITIES() {
		return c_TAX_AUTHORITIES;
	}

	public void setC_TAX_AUTHORITIES(String c_TAX_AUTHORITIES) {
		this.c_TAX_AUTHORITIES = c_TAX_AUTHORITIES;
	}

	public String getC_LINKMAN_LIST() {
		return c_LINKMAN_LIST;
	}

	public void setC_LINKMAN_LIST(String c_LINKMAN_LIST) {
		this.c_LINKMAN_LIST = c_LINKMAN_LIST;
	}
	
	public String getC_CORP_TEL() {
		return C_CORP_TEL;
	}

	public void setC_CORP_TEL(String c_CORP_TEL) {
		C_CORP_TEL = c_CORP_TEL;
	}

	public String getC_DV_INOVIC_TYPE() {
		return c_DV_INOVIC_TYPE;
	}

	public void setC_DV_INOVIC_TYPE(String c_DV_INOVIC_TYPE) {
		this.c_DV_INOVIC_TYPE = c_DV_INOVIC_TYPE;
	}

	public Date getD_RELA_START() {
		return d_RELA_START;
	}

	public void setD_RELA_START(Date d_RELA_START) {
		this.d_RELA_START = d_RELA_START;
	}

	public Date getD_RELA_END() {
		return d_RELA_END;
	}

	public void setD_RELA_END(Date d_RELA_END) {
		this.d_RELA_END = d_RELA_END;
	}
	
	/**
	 ** c_ISRElATED
	 **/
	public String getC_ISRElATED() {
		return c_ISRElATED;
	}
	
	/**
	 ** c_ISRElATED
	 **/
	public void setC_ISRElATED(String c_ISRElATED) {
		this.c_ISRElATED = c_ISRElATED;
	}
}
