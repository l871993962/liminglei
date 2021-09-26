package com.yss.ams.product.information.support.modules.ab.portPdAttribute.pojo;

import com.yss.framework.api.common.co.AuditableParamPojo;
import com.yss.framework.api.restful.annotations.ControllerMixArgu;

/**
 * 组合品种属性pojo类
 * @author zhengguiyu
 *
 */
/**
 * <产品属性分类>实体类
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
@ControllerMixArgu
public class PortPdAttribute extends AuditableParamPojo{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 深市配售对象编码 
	 * add 2019-04-25 nmj BUG #254952 华安基金、深市网下新股配售结果接口清算不出新股申购新股确认流水
	 */
	private String c_SSPSDXBM = "";
	
	public String getC_SSPSDXBM() {
		return c_SSPSDXBM;
	}

	public void setC_SSPSDXBM(String c_SSPSDXBM) {
		this.c_SSPSDXBM = c_SSPSDXBM;
	}

	/**
	 * 组合代码
	 */
	private String c_PORT_CODE = "";
	
	/**
	 * 资产类型
	 */
	private String c_DAT_CODE = "";
	
	/**
	 * 组合标识
	 * add by zhangyi STORY58803【鹏华基金】产品基本信息增加字段区分是原组合还是复制组合
	 */
	private String c_DV_ZHBZ = "";

	public String getC_DV_ZHBZ() {
		return c_DV_ZHBZ;
	}

	public void setC_DV_ZHBZ(String c_DV_ZHBZ) {
		this.c_DV_ZHBZ = c_DV_ZHBZ;
	}

	/**
	 * 明细资产类型
	 */
	private String c_DAT_MXCODE = "";

	/**
	 * 运作类型
	 */
	private String c_OPER_TYPE = "";

	/**
	 * 募集对象
	 */
	private String c_COLLECT_CODE = "";
	
	/**
	 * 客户类型
	 */
	private String c_CLIENT_TYPE = "";
	
	/**
	 * 投资对象
	 */
	private String c_INVEST_CODE = "";
	
	/**
	 * 资产种类
	 */
	private String c_ASSETS_CODE = "";
	
	/**
	 * 证券结算模式
	 */
	private String c_SETT_MODE = "";
	
	/**
	 * 客户性质
	 */
	private String C_KH_NATURE = "";
	
	/**
	 * 描述
	 */
	private String c_DESC = "";
    
	/**
	 * 组合类别
	 */
	private String c_PORT_TYPE = "";
	
	/**
	** 产品编号
	*/
	private String c_PRODUCT_NUM = "";
	
	/**
	 * add by guohui 20161026    STORY34547【南方基金】保险资产报表需要区分产品编码(使用协会统一编码）与短码
	** 产品编号
	*/
	private String c_SHORT_NUM = "";

	/**
	** 合同名称
	*/
	private String c_CONTRACT_NAME = "";
	
	/**
	 * 次托托管账户 add by wsm 2016-5-7 STORY #30235【招商基金母公司】[QDII]TradeBlotter_EXP TradeBlotter招行导出接口
	 */
	private String c_CTTG_ACCOUNT="";
	
	/**
	 * 证监会简称 add by wgl STORY #36888 【南方基金】【紧急】产品信息多加一列证监会简称字段
	 */
	private String c_ZJHJC="";
	
	/*
	 * 委托人类型
       STORY39908 产品属性分类中增加资金来源字段  addby wsm 2017-3-30
	 */
    private  String c_TRUSTOR_TYPE="";
    /**
     * 资金来源  STORY39908 产品属性分类中增加资金来源字段  addby wsm 2017-3-30
     */
    private String  c_FUND_SOURCE="";
    /**
	 * 关联类型 STORY38001【南方基金】 - 复制创建新组合时增加复制产品属性分类信息 add by liutao 2017-04-27
	 */
	private String c_PLAN_TYPE = "";
    
	/**
	 * 备案审查日期 add by xml STORY #38387 【2.5升级】鹏华基金：专户周报需求
	 */
	private String d_RECORD_REVIEW_DATE = "";
	
	/**
	 * 委托户数add by xml STORY #38387 【2.5升级】鹏华基金：专户周报需求
	 */
    /**
	 * 导出O32投资分类 add by wzh 2017-7-26 STORY #44026 宏源证券-恒生O32报表导出投资分类可以加参数进行控制
	 */
	private String c_DV_INVEST_CLS="";
	
	/**
	 * STORY43224【南方基金】产品属性分类加字段方便报表查询   做账顺延天数
	 */	
	private int n_ACCOUNTING_DAYS=0;
	
	public int getN_ACCOUNTING_DAYS() {
		return n_ACCOUNTING_DAYS;
	}

	public void setN_ACCOUNTING_DAYS(int n_ACCOUNTING_DAYS) {
		this.n_ACCOUNTING_DAYS = n_ACCOUNTING_DAYS;
	}
	
	public String getC_DV_INVEST_CLS() {
		return c_DV_INVEST_CLS;
	}

	public void setC_DV_INVEST_CLS(String c_DV_INVEST_CLS) {
		this.c_DV_INVEST_CLS = c_DV_INVEST_CLS;
	}
	private String n_ENTRUST = "";
	
	/**
	 * add by sunyanlin 2017-9-19 STORY #46343 中信证券-产品信息的产品属性分类中新增组织形式字段并取值
	 * 组织形式
	*/
	private String C_ORGANIZATION_TYPE = "";
	
	/**
	 * o32套账号
	 * STORY62312人保资产-交易所的交易涉及到的共用股东代码数据需要与恒生系统进行关联，有系统进行自动拆分【接口12】
	** 
	*/
	private String c_COVER_ACCOUNTS = "";
	
	/**
	 * add by xiadeqi 2018-11-27 STORY #63055 增加四个字段 客户性质、产品类别、产品属性_资管新规、产品属性_保监会
	 * 客户性质
	 */
	private String c_KHXZ = "";
	
	/**
	 * add by xiadeqi 2018-11-27 STORY #63055 增加四个字段 客户性质、产品类别、产品属性_资管新规、产品属性_保监会
	 * 产品类别
	 */
	private String c_CPLB = "";
	
	/**
	 * add by xiadeqi 2018-11-27 STORY #63055 增加四个字段 客户性质、产品类别、产品属性_资管新规、产品属性_保监会
	 * 产品属性_资管新规
	 */
	private String c_CPSX_ZGXG = "";
	
	/**
	 * add by xiadeqi 2018-11-27 STORY #63055 增加四个字段 客户性质、产品类别、产品属性_资管新规、产品属性_保监会
	 * 产品属性_保监会
	 */
	private String c_CPSX_BJH = "";

	
	/**
	 * start  STORY #67912 （QDII）收益分配统计明细及汇总报表新增相关参数 
	 */
	private String n_FHBL= "";
	
	private String c_SYFPMS = "";
	//end  STORY #67912 （QDII）收益分配统计明细及汇总报表新增相关参数 
	
	/**
	 * 计算方法 edit by baoqiaolin 2020-02-19 STORY #80028 华夏基金年金组合“资金追加提取”业务根据金额法份额法区分核算需求
	 */
	private String c_JSFF = "";
	
	/**
	 * 委托方式  STORY #95794 AMS国寿资产V4.5-产品属性分类设置模块新增[委托方式]字段  edit by sunqiangwen 2020年10月20日
	 */
	private String c_ENTRUST_WAY = "";
	
	public String getC_JSFF() {
		return c_JSFF;
	}

	public void setC_JSFF(String c_JSFF) {
		this.c_JSFF = c_JSFF;
	}
	public String getC_KH_NATURE() {
		return C_KH_NATURE;
	}

	public void setC_KH_NATURE(String c_KH_NATURE) {
		C_KH_NATURE = c_KH_NATURE;
	}
	
	public String getC_COVER_ACCOUNTS() {
		return c_COVER_ACCOUNTS;
	}

	public void setC_COVER_ACCOUNTS(String c_COVER_ACCOUNTS) {
		this.c_COVER_ACCOUNTS = c_COVER_ACCOUNTS;
	}

	public String getD_RECORD_REVIEW_DATE() {
		return d_RECORD_REVIEW_DATE;
	}

	public void setD_RECORD_REVIEW_DATE(String c_RECORD_REVIEW_DATE) {
		d_RECORD_REVIEW_DATE = c_RECORD_REVIEW_DATE;
	}

	public String getN_ENTRUST() {
		return n_ENTRUST;
	}

	public void setN_ENTRUST(String n_ENTRUST) {
		this.n_ENTRUST = n_ENTRUST;
	}
	
	public String getC_PLAN_TYPE() {
		return c_PLAN_TYPE;
	}
	
	/*
	 * T+N估值属性
	 * STORY #42394 daibing 2017-05-23
	 */
	private String n_T_DAYS = "";
	
	/**
	** TA类型
	*  add by qinxinglin 2017-10-30 STORY #48306 【海通证券】估值系统销售网点增加字段区分市场和区分自TA与分TA数据
	*/
	private String c_TA_TYPE = "";
    
    /**
	 * 协会代码  
	 * add by qinxinglin 2018-1-24 STORY #47356 需求单-在估值系统增加“协会代码”参数方便系统导出估值表名称与公示信息一致
	 */
	private String c_ASSOCIATION_CODE = "";
	
	
	public String getN_T_DAYS() {
		return n_T_DAYS;
	}

	public void setN_T_DAYS(String n_T_DAYS) {
		this.n_T_DAYS = n_T_DAYS;
	}

	public void setC_PLAN_TYPE(String c_PLAN_TYPE) {
		this.c_PLAN_TYPE = c_PLAN_TYPE;
	}

	public String getC_TRUSTOR_TYPE() {
		return c_TRUSTOR_TYPE;
	}

	public void setC_TRUSTOR_TYPE(String c_TRUSTOR_TYPE) {
		this.c_TRUSTOR_TYPE = c_TRUSTOR_TYPE;
	}

	public String getC_FUND_SOURCE() {
		return c_FUND_SOURCE;
	}

	public void setC_FUND_SOURCE(String c_FUND_SOURCE) {
		this.c_FUND_SOURCE = c_FUND_SOURCE;
	}

	public String getC_ZJHJC() {
		return c_ZJHJC;
	}

	public void setC_ZJHJC(String c_ZJHJC) {
		this.c_ZJHJC = c_ZJHJC;
	}
	
	public String getC_CTTG_ACCOUNT() {
		return c_CTTG_ACCOUNT;
	}

	public void setC_CTTG_ACCOUNT(String c_CTTG_ACCOUNT) {
		this.c_CTTG_ACCOUNT = c_CTTG_ACCOUNT;
	}

	public String getC_DESC() {
		return c_DESC;
	}

	public void setC_DESC(String c_DESC) {
		this.c_DESC = c_DESC;
	}
	
	public String getC_PORT_CODE() {
		return c_PORT_CODE;
	}

	public void setC_PORT_CODE(String c_PORT_CODE) {
		this.c_PORT_CODE = c_PORT_CODE;
	}


	
	public String getC_DAT_CODE() {
		return c_DAT_CODE;
	}

	public void setC_DAT_CODE(String c_DAT_CODE) {
		this.c_DAT_CODE = c_DAT_CODE;
	}
	
	public String getC_OPER_TYPE() {
		return c_OPER_TYPE;
	}

	public void setC_OPER_TYPE(String c_OPER_TYPE) {
		this.c_OPER_TYPE = c_OPER_TYPE;
	}

	public String getC_COLLECT_CODE() {
		return c_COLLECT_CODE;
	}

	public void setC_COLLECT_CODE(String c_COLLECT_CODE) {
		this.c_COLLECT_CODE = c_COLLECT_CODE;
	}
	
	public String getC_DAT_MXCODE() {
		return c_DAT_MXCODE;
	}

	public void setC_DAT_MXCODE(String c_DAT_MXCODE) {
		this.c_DAT_MXCODE = c_DAT_MXCODE;
	}

	public String getC_CLIENT_TYPE() {
		return c_CLIENT_TYPE;
	}

	public void setC_CLIENT_TYPE(String c_CLIENT_TYPE) {
		this.c_CLIENT_TYPE = c_CLIENT_TYPE;
	}

	public String getC_INVEST_CODE() {
		return c_INVEST_CODE;
	}

	public void setC_INVEST_CODE(String c_INVEST_CODE) {
		this.c_INVEST_CODE = c_INVEST_CODE;
	}

	public String getC_ASSETS_CODE() {
		return c_ASSETS_CODE;
	}

	public void setC_ASSETS_CODE(String c_ASSETS_CODE) {
		this.c_ASSETS_CODE = c_ASSETS_CODE;
	}
	
	public String getC_SETT_MODE() {
		return c_SETT_MODE;
	}

	public void setC_SETT_MODE(String c_SETT_MODE) {
		this.c_SETT_MODE = c_SETT_MODE;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getC_PRODUCT_NUM() {
		return c_PRODUCT_NUM;
	}

	public String getC_CONTRACT_NAME() {
		return c_CONTRACT_NAME;
	}

	public void setC_PRODUCT_NUM(String c_PRODUCT_NUM) {
		this.c_PRODUCT_NUM = c_PRODUCT_NUM;
	}

	public void setC_CONTRACT_NAME(String c_CONTRACT_NAME) {
		this.c_CONTRACT_NAME = c_CONTRACT_NAME;
	}

	public String getC_PORT_TYPE() {
		return c_PORT_TYPE;
	}

	public void setC_PORT_TYPE(String c_PORT_TYPE) {
		this.c_PORT_TYPE = c_PORT_TYPE;
	}
	
	public String getC_SHORT_NUM() {
		return c_SHORT_NUM;
	}

	public void setC_SHORT_NUM(String c_SHORT_NUM) {
		this.c_SHORT_NUM = c_SHORT_NUM;
	}

	public String getC_TA_TYPE() {
		return c_TA_TYPE;
	}

	public void setC_TA_TYPE(String c_TA_TYPE) {
		this.c_TA_TYPE = c_TA_TYPE;
	}
	public String getC_ORGANIZATION_TYPE() {
		return C_ORGANIZATION_TYPE;
	}

	public void setC_ORGANIZATION_TYPE(String c_ORGANIZATION_TYPE) {
		C_ORGANIZATION_TYPE = c_ORGANIZATION_TYPE;
	}
	
	public String getC_KHXZ() {
		return c_KHXZ;
	}

	public void setC_KHXZ(String c_KHXZ) {
		this.c_KHXZ = c_KHXZ;
	}

	public String getC_CPLB() {
		return c_CPLB;
	}

	public void setC_CPLB(String c_CPLB) {
		this.c_CPLB = c_CPLB;
	}

	public String getC_CPSX_ZGXG() {
		return c_CPSX_ZGXG;
	}

	public void setC_CPSX_ZGXG(String c_CPSX_ZGXG) {
		this.c_CPSX_ZGXG = c_CPSX_ZGXG;
	}

	public String getC_CPSX_BJH() {
		return c_CPSX_BJH;
	}

	public void setC_CPSX_BJH(String c_CPSX_BJH) {
		this.c_CPSX_BJH = c_CPSX_BJH;
	}
	
	public String getC_ASSOCIATION_CODE() {
		return c_ASSOCIATION_CODE;
	}

	public void setC_ASSOCIATION_CODE(String c_ASSOCIATION_CODE) {
		this.c_ASSOCIATION_CODE = c_ASSOCIATION_CODE;
	}

	public String getN_FHBL() {
		return n_FHBL;
	}

	public void setN_FHBL(String n_FHBL) {
		this.n_FHBL = n_FHBL;
	}

	public String getC_SYFPMS() {
		return c_SYFPMS;
	}

	public void setC_SYFPMS(String c_SYFPMS) {
		this.c_SYFPMS = c_SYFPMS;
	}

	public String getC_ENTRUST_WAY() {
		return c_ENTRUST_WAY;
	}

	public void setC_ENTRUST_WAY(String c_ENTRUST_WAY) {
		this.c_ENTRUST_WAY = c_ENTRUST_WAY;
	}
}