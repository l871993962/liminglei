package com.yss.ams.sec.information.support.modules.sv.base.pojo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.yss.framework.api.common.NoColumnName;
import com.yss.framework.api.common.co.AuditableParamPojo;
import com.yss.framework.api.util.StringUtil;

public class SecBase extends AuditableParamPojo {

	/**
	 * 证券代码
	 */
	private String c_SEC_CODE = "";

	/**
	 * 证券名称
	 */
	private String c_SEC_NAME = "";

	/**
	 * 证券上市代码
	 */
	private String c_SEC_MKT_CODE = "";

	/**
	 * 证券ISIN代码
	 */
	private String c_SEC_ISIN_CODE = "";
	
	/**
	 * 合并需求：STORY #38433 南方基金-OA债券分销：债券临时代码和正式代码设置转换关系 
	 * 证券分销代码
	 */
	private String c_FX_CODE = "";

	/**
	 * 交易市场代码
	 */
	private String c_MKT_CODE = "";

	/**
	 * 证券品种代码
	 */
	private String c_SEC_VAR_CODE = "";

	/**
	 * 币种代码
	 */
	private String c_DC_CODE = "";
	
	/**
	 * 平仓盈亏币种代码  STORY35879【招商基金】【QDII】远期外汇需支持交割期间部分多次平仓业务  远期品种信息 增加平仓盈亏币种
	 */
	private String c_PCYK_CURY = "";

	/**
	 * 报价因子
	 */
	private String n_PRICE_FCR = "";
	
	/**
	 * 发行机构
	 */
	private String c_FXJG_CODE = "";
	
	/**
	 * 标的证券
	 */
	private String c_SEC_CODE_TRG = "";

	/**
	 * 每手数量
	 */
	private String n_AMOUNT_HD = "";

	/**
	 * 发行面值
	 */
	private String n_FV_ISSUE = "";

	/**
	 * 市日期
	 */
	private Date d_TO_LIST;

	/**
	 * 退市日期
	 */
	private Date d_OFF_LIST;

	/**
	 * 品种期限
	 */
	private String c_DV_VAR_DUR = "";

	/**
	 * 报价方式
	 */
	private String c_DV_QUT_MOD = "";

	/**
	 * 税率
	 */
	private BigDecimal n_RATE = BigDecimal.ZERO;

	/**
	 * 票面利率
	 */
	private BigDecimal n_FV_IR = BigDecimal.ZERO;

	/**
	 * 发行价格
	 */
	private BigDecimal n_PRICE_ISSUE = BigDecimal.ZERO;

	/**
	 * 计息方式
	 */
	private String c_DV_AI_MOD = "";

	/**
	 * 付息公式
	 */
	private String c_DV_PI_MOD = "";

	/**
	 * 计息起始日
	 */
	private String d_AI_BEGIN = "";
	
	/**
	 * 首期起息日期
	 */
	private String d_SQAI_BEGIN = "";

	/**
	 * 计息截止日
	 */
	private String d_AI_END = "";

	/**
	 * //描述
	 */
	private String c_DESC = "";

	/**
	 * 计息公式
	 */
	private String c_DV_AI_EXPR = "";

	/**
	 * 机构代码
	 */
	private String c_ORG_CODE = "";

	/**
	 * 出票人
	 */
	private String c_ORG_NAME = "";

	/**
	 * 承兑人行名
	 */
	private String c_OPEN_ACC_NAME = "";

	/**
	 * 承兑人行号
	 */
	private String c_OPEN_ACC_NO = "";

	/**
	 * 大额支付号
	 */
	private String c_SYS_CODE = "";

	/**
	 * 发行类型 add by tangshifeng 2013-07-03
	 */
	private String c_DV_ISSUE = "";

	/**
	 * 担保方式 add by tangshifeng 2013-07-03
	 */
	private String c_DV_ASSURE = "";

	/**
	 * 债券信用评级
	 */
	private String C_CREDIT_RATING = "";

	/**
	 * 券中文名称 add by fjl 20140315
	 */
	private String c_SEC_NAME_CN = "";

	/**
	 * 税率 1 add By Jinghehe 2014-07-30 个股期权业务
	 */
	private BigDecimal n_RATIO = BigDecimal.ZERO;

	/**
	 * 到期日 add By Jinghehe 2014-07-30 个股期权业务
	 */
	private String d_END = "";
	
	/**
	 * ETF类型
	 */
	private String c_ETF_TYPE = ""; 
	
	/**
	 * 结算机构
	 */
	private String c_SETT_ORG = ""; 
	
	/**
	 * 含权标志
	 */
	private String c_DV_RIGHT = ""; 
	
	/**
	 * 融资融券标示
	 */
	private String c_RZRQ_MARK = ""; 
	
	/**
	 * (合并代码 STORY24925)add by dingshlau 20160405 STORY #28335
	 * 利率类型 
	 */
	private String c_DV_RTA = ""; 
	
	/**
	 * (合并代码 STORY24925)add by dingshlau 20160405 STORY #28335
	 * 利差
	 */
	private BigDecimal n_SPREAD =BigDecimal.ZERO ; 
	
	/**
	 * (合并代码 STORY24925)add by dingshlau 20160405 STORY #28335
	 * 利率代码  
	 */
	private String c_EXPR_CODE = ""; 
	/**
	 * (合并代码 STORY24925)add by dingshlau 20160405 STORY #28335
	 * 比例系数  
	 */
	private BigDecimal n_BLXS =BigDecimal.ZERO ; 
	
	/**
	 * (合并代码 STORY24925)add by dingshlau 20160405 STORY #28335
	 * 检查间隔 
	 */
	private String c_JCJG = ""; 
	
	/**
	 * (合并代码 STORY24925)add by dingshlau 20160405 STORY #28335
	 * 利率上限  
	 */
	private BigDecimal n_UPPER_LIMIT =BigDecimal.ZERO ;
	
	/**
	 * (合并代码 STORY24925)add by dingshlau 20160405 STORY #28335
	 * 利率下限  
	 */
	private BigDecimal n_LOWER_LIMIT =BigDecimal.ZERO ;
	
	/**
	 * (合并代码 STORY24925)add by dingshlau 20160405 STORY #28335
	 * 间隔时间周期 
	 */	
	private String c_INTERVAL_TIME="";
	
	/**
	 * (合并代码 STORY24925)add by dingshlau 20160405 STORY #28335
	 * 周期天数
	 */
	private String c_INTERVAL_DAY="";
	
	/**
	 * 组合代码 
	 * @author liuxiang
	 * 2016-5-9 STORY #25484 YSS:债券品种信息和理财品种信息增加组合私有化功能
	 */
	private String c_PORT_CODE = "";
	
	/**
	 * add by zhd 2016-09-07
	 * STORY34120【南方基金】【紧急】现金存放业务，流水加入标识，4级科目不明确到品种，而是按照标识来区分
	 * 实际所属证券
	 */
	private String c_SJSSZQ = "";
		
	/**
	   *合并需求： STORY #39209
	   *  属性：转换起始日
	   */
	private String d_TRA_BEGIN;
	  
	  /**
	   * 合并需求：STORY #39209 属性：
	   * 转换截止日
	   */
  	private String d_TRA_END;
	
	/**
     * 理财产品付息日释放包含当天的利息
     */
    private double n_TODAY_INTEREST = 0;
	
	private String c_BANK_HEAD = "";
	
	private String c_BANK_BRANCH = "";
	
	/**
	 * 计提基准_标准资产
	 */
	private String c_JTJZ4STD_SETT = "";
	
	/**
	 * 计提基准_非标准资产
	 */
	private String c_JTJZ4NOTSTD_SETT = "";
	
	/**
	 * STORY #63023 【中金公司】彭博证券信息，彭博债券信息接口，公共信息处理时，提示是否覆盖手工调整过的信息
	 * 数据来源
	 */
	private String c_DATA_IDF = "";
	
	/**
	 * 指数品种  STORY #75904 第一批：QDII-【博时基金】抗通胀债券需求 add by sunyanlin 20190910
	 */
	private String c_INDEX_TYPE = "";
	
	/**
	 * add by ZhangJM  STORY #76306 银华基金：投资分级基金A类产品，每日计提收益，卖出时进行冲减
	 * 票面日利率
	 */
	private BigDecimal n_FV_DAY_IR = BigDecimal.ZERO;
	
	/**
	 * add by dingshalu 20200320 STORY #84448 华夏基金-QD税收矩阵设置
	 * 注册地国家
	 */
	private String c_ZCDGJ = "";
	
	/**
	 * add by dingshalu 20200320 STORY #84448 华夏基金-QD税收矩阵设置
	 * 交易所所在国
	 */
	private String c_JYSSZG = "";
	
	/**
	 * STORY #98178 【估值核算】【国寿资产】【V1.300.7.0.20200831.1103】-O32红利投资接口增加控制是否包含节假日收益参数
	 * 含节假日收益
	 */
	private String c_DV_IS_JJRSY = null;
	
	/**
	 * 发行所在国
	 * add by fuzhendong 20201128  STORY #97105 新华资产-彭博证券基本信息文件，增加四列信息，估值报表系统需要使用，协助更新此文件接口
	 */
	private String c_FXSZG = "";
	
	/**
	 * 发行人所在国
	 * add by fuzhendong 20201128  STORY #97105 新华资产-彭博证券基本信息文件，增加四列信息，估值报表系统需要使用，协助更新此文件接口
	 */
	private String c_FXRSZG = "";
	
	/**
	 * GICS分类
	 * add by fuzhendong 20201128  STORY #97105 新华资产-彭博证券基本信息文件，增加四列信息，估值报表系统需要使用，协助更新此文件接口
	 */
	private String c_GICS = "";
	
	/**
	 * 宽限期
	 * STORY #105860 【招银理财】非标资产逾期业务
	 * addby mxlee 2021-07-13
	 */
	private BigDecimal n_GRACE_PERIOD = BigDecimal.ZERO;
	
	/**
	 * 宽限期计息规则
	 * STORY #105860 【招银理财】非标资产逾期业务
	 * addby mxlee 2021-07-13
	 */
	private String c_GRACE_PERIOD_RULE = "";
	
	/**
	 * STORY #108912 【大家资产】估值核算20210723---证券基本信息中新增融资人，担保人字段
	 * 担保人
	 */
	private String c_DV_DBR = "";
	
	/**
	 * STORY #108912 【大家资产】估值核算20210723---证券基本信息中新增融资人，担保人字段
	 * 融资人
	 */
	private String c_DV_RZR = "";
	
	public String getC_DV_DBR() {
		return c_DV_DBR;
	}

	public void setC_DV_DBR(String c_DV_DBR) {
		this.c_DV_DBR = c_DV_DBR;
	}

	public String getC_DV_RZR() {
		return c_DV_RZR;
	}

	public void setC_DV_RZR(String c_DV_RZR) {
		this.c_DV_RZR = c_DV_RZR;
	}

	public String getC_FXSZG() {
		return c_FXSZG;
	}

	public void setC_FXSZG(String c_FXSZG) {
		this.c_FXSZG = c_FXSZG;
	}

	public String getC_FXRSZG() {
		return c_FXRSZG;
	}

	public void setC_FXRSZG(String c_FXRSZG) {
		this.c_FXRSZG = c_FXRSZG;
	}

	public String getC_GICS() {
		return c_GICS;
	}

	public void setC_GICS(String c_GICS) {
		this.c_GICS = c_GICS;
	}

	public String getC_DV_IS_JJRSY() {
		return c_DV_IS_JJRSY;
	}

	public void setC_DV_IS_JJRSY(String c_DV_IS_JJRSY) {
		this.c_DV_IS_JJRSY = c_DV_IS_JJRSY;
	}

	/**
	 * add by sunqiangwen 20200814 STORY #92285 华夏基金-彭博接口的证券代码取值逻辑变更-证券基本信息
	 * 彭博内码
	 * @return
	 */
	private String c_PBSEC_CODE = null;
	
	public BigDecimal getN_FV_DAY_IR() {
		return n_FV_DAY_IR;
	}

	public void setN_FV_DAY_IR(BigDecimal nFVDAYIR) {
		n_FV_DAY_IR = nFVDAYIR;
	}
	
	public String getC_INDEX_TYPE() {
		return c_INDEX_TYPE;
	}

	public void setC_INDEX_TYPE(String c_INDEX_TYPE) {
		this.c_INDEX_TYPE = c_INDEX_TYPE;
	}

	public String getC_DATA_IDF() {
		return c_DATA_IDF;
	}

	public void setC_DATA_IDF(String c_DATA_IDF) {
		this.c_DATA_IDF = c_DATA_IDF;
	}

	/**
     * 份额上限
     */
    private BigDecimal n_FESX = BigDecimal.ZERO;
    
    /**
     * 份额下限
     */
    private BigDecimal n_FEXX = BigDecimal.ZERO;
	
    /**
  	*add zhangmingbo story 45603 [易方达QDII]证券信息数据导入清算 2017 9 16
  	*/
      private String c_ISSUERS_CODE="";
      private String c_ISSUERS_NAME="";
      
	/**
     * 保本类型
     * add by wangpeixu 20170606
     * STORY37961财税140号文件，针对资管增值税系统改造需求
     */
  	private String c_GUAR_TYPE = "";
  	
  	/**
  	 * STORY #47088 债券基本信息，增加发行机构类型字段，区分金融机构和非金融机构
  	 * add by yuanyafeng 20171024
  	 * 主体性质
  	 */
  	private String c_MAIN_PROP = "";
  	
  	/**
  	 * STORY #64802 【招商基金】增加fxzt字段的读入及增加债券基本信息该字段的展示 
  	 * 主体名称
  	 */
  	private String c_MAIN_NAME = "";
  	
  	/**
  	 * 金融商品
  	 * add by wangpeixu 20170606
  	 * STORY37961财税140号文件，针对资管增值税系统改造需求
  	 */
  	private String c_FINA_COMM = "";
	
	/**
  	 * 分类标识
  	 * add by chenshenzhou 20171112
  	 * STORY47986 - 理财区分公募养老金等产品类型，以及特殊管理费计提方式需求
  	 */
  	private String c_LC_SEC_TAG = "";
	
	/**
  	 * 购回日期类型
  	 * add by shijian 2017-11-09 STORY #47805 嘉实基金QD—回购业务需求
  	 */
  	private String c_REPO_TYPE = "";

  	/**
	 * 债券上市日期
	 * add by lipeidong 20170725
	*/
	private String d_TO_LIST_ZQ  = "";
  	
  	/**
  	 * 回购收益计算起始日期
  	 * add by shijian 2017-11-09 STORY #47805 嘉实基金QD—回购业务需求
  	 */
  	private String c_REPO_INCOME_FROM = "";
  	
  	/**
  	 * 回购收益计算结束日期
  	 * add by shijian 2017-11-09 STORY #47805 嘉实基金QD—回购业务需求
  	 */
  	private String c_REPO_INCOME_TO = "";
	
  	/**
     * add by liutao 2017-05-17
     * STORY #39539 【南方基金】FOF基金系统改造
     * 托管人代码
     */
    private String c_TGR_CODE = "";
    
    /**
     * add by liutao 2017-05-17
     * STORY #39539 【南方基金】FOF基金系统改造
     * 托管人名称
     */
    private String c_TGR_NAME = "";
    
    /**
     * add by liutao 2017-05-17
     * STORY #39539 【南方基金】FOF基金系统改造
     * 销售服务费率
     */
    private double n_XSFWFL = 0;
    
    /**
     * add by dudexu 2019-07-08
     * STORY #74585 【华宝基金】——标准证券界面——理财产品信息中增加两个字段（合并需求）  
     * 管理费费率
     */
    private double n_GLF_RATE = 0;
    
    /**
     * add by dudexu 2019-07-08
     * STORY #74585 【华宝基金】——标准证券界面——理财产品信息中增加两个字段（合并需求）  
     * 托管费费率
     */
    private double n_TGF_RATE = 0;
    
    /**
	 * 金融工具
	 * added by HeLiang 2017-11-14 STORY #43829 新企业会计准则（Ifrs9）解决方案
	 */
	private String c_FINANCE_TOOL = "";
	
	/**
	 * STORY #40222
	 */
	private String c_SEC_VAR_CLS = "";
	
	/**
	 * 集资方式 Add By HeMing 20180628
	 * STORY #53605 【鹏华基金】债券品种信息投资分类增加“公募”“私募”用以区分不同的估值方案
	 */
	private String c_JZFS_CODE = "";
	
	private String c_ORG_TYPE ="";
	
	/**
	 * 付息日期类型
	 * add by cxj 20170705
	 * STORY32772【南方基金】【V2.5需求】南方基金：协定存款付息方式增加
	 */
  	private String c_PAY_DATE_TYPE = "";
    
  	/**
	 * 付息天数
	 * add by cxj 20170705
	 * STORY32772【南方基金】【V2.5需求】南方基金：协定存款付息方式增加
	 */
  	private int n_PAY_DATE = 0;
  	
  	/**
	 * 基准日期类型
	 * add by cxj 20170705
	 * STORY32772【南方基金】【V2.5需求】南方基金：协定存款付息方式增加
	 */
  	private String c_BASE_DATE_TYPE = "";
  	
  	/**
	 * 基准天数
	 * add by cxj 20170705
	 * STORY32772【南方基金】【V2.5需求】南方基金：协定存款付息方式增加
	 */
  	private int n_BASE_DATE = 0;
  	
  	/**
  	 * STORY52063仅在大宗协议平台上市的债券，收盘价不作为估值行情处理
  	 * 添加“交易平台”字段
  	 */
  	private String c_DV_PLAT = "";
  	
  	/**
  	 * STORY #63605 富国基金-【公募】存放业务无法生成正确得付息信息和支取流水
  	 * 付息日计算
  	 */
  	private String c_PAY_CALCULATE = "";
  	
  	/**
  	 * 英文名称
  	 */
  	private String c_SEC_NAME_EN = "";
  	
  	public String getC_SEC_NAME_EN() {
		return c_SEC_NAME_EN;
	}

	public void setC_SEC_NAME_EN(String c_SEC_NAME_EN) {
		this.c_SEC_NAME_EN = c_SEC_NAME_EN;
	}

	public String getC_DV_RIGHTMX() {
		return c_DV_RIGHTMX;
	}

	public void setC_DV_RIGHTMX(String c_DV_RIGHTMX) {
		this.c_DV_RIGHTMX = c_DV_RIGHTMX;
	}

	/**
  	 * 含权明细
  	 */
  	private String c_DV_RIGHTMX = "";
  	
  	public String getC_PAY_CALCULATE() {
		return c_PAY_CALCULATE;
	}

	public void setC_PAY_CALCULATE(String c_PAY_CALCULATE) {
		this.c_PAY_CALCULATE = c_PAY_CALCULATE;
	}

	public String getC_DV_PLAT() {
		return c_DV_PLAT;
	}

	public void setC_DV_PLAT(String c_DV_PLAT) {
		this.c_DV_PLAT = c_DV_PLAT;
	}

  	/**
  	 * 省内资产
  	 * STORY #59902 改造存放品种信息界面，新增存放品种 add by wenxiyang 20180814
  	 */
  	private String c_SNZC = "";
  	
  	/**
  	 * 控制程度
  	 * STORY #62079 人保资产-支持人保资产新科目体系核算
  	 */
  	private String C_KZCD_GQ = "";
  	
  	/**
  	 * STORY #66077 人保资产-支持银行间债券A/A特殊计息
  	 * 计息天数
  	 */
  	private int n_JXTS = 0;
  	
  	/**
  	 * 境外市场名称
  	 * STORY #51261 [易方达] 彭博证券信息读入境外市场
  	 * @return
  	 */
  	private String c_MKT_CODE_JC = "";
	
	/**
     * 对价派息启用
     * add by ZhuJinYang 20181127
     * STORY65338工银瑞信基金-非标产品需要控制是否生成对价派息信息（紧急）
     */
  	private String c_DJPX_OPEN = "";
  	
	/**
	 * added by hezhigang 20181212
	 * STORY64492【易方达香港】【一期】境外回购支持按年利率计算日收益进行计提
	 */
	private String c_DV_JXFS = "";
	
	/**
	 * 股票SDOL码
	 */
	private String c_SEDOLCODE = "";
	
	/**
	 * 源制作时间
	 */
	private String c_UPDATE_TIMEWEB;
	
	/**
	 * 系统来源
	 */
	private String c_SYSOURCE;
	

	/**
     * 理财产品定义付息日
  	 */
  	private PayDay payDays = null;
  	
    

  	/**
  	 * 是否境外投资
  	 * add by jiaxinxiao
  	 * STORY #78461 【上海银行】与私募产品运行检测表相关的项目字段新增
  	 */
  	private String c_JWTZ = "";
  	
  	/**
  	 * 项目企业股票代码
  	 * add by jiaxinxiao
  	 * STORY #78461 【上海银行】与私募产品运行检测表相关的项目字段新增
  	 */
  	private String c_QYGPDM = "";
  	
  	/**
  	 * 成立日期
  	 * add by jiaxinxiao
  	 * STORY #78461 【上海银行】与私募产品运行检测表相关的项目字段新增
  	 */
  	private String d_QY_DATE = "";
  	
  	/**
  	 * 项目企业统一社会信用编码
  	 * add by jiaxinxiao
  	 * STORY #78461 【上海银行】与私募产品运行检测表相关的项目字段新增
  	 */
  	private String c_SHXYBM = "";
  	
  	/**
  	 * 项目企业注册地
  	 * add by jiaxinxiao
  	 * STORY #78461 【上海银行】与私募产品运行检测表相关的项目字段新增
  	 */
  	private String c_QYZCD = "";
  	
  	/**
  	 * 投资行业
  	 * add by jiaxinxiao
  	 * STORY #78461 【上海银行】与私募产品运行检测表相关的项目字段新增
  	 */
  	private String c_TZHY = "";
  	
  	/**
  	 * 投资阶段
  	 * add by jiaxinxiao
  	 * STORY #78461 【上海银行】与私募产品运行检测表相关的项目字段新增
  	 */
  	private String c_TZJD = "";
  	

	/**
	 * 贴现行行名
	 */
	private String c_TXH_NAME = "";

	/**
	 * 贴现行行号
	 */
	private String c_TXH_CODE = "";
	/**
	 * SM代码
	 * wulongxing 20191023 STORY81024证券基本信息界面新增字段TisCode
	 */
	private String c_SM_CODE = "";
	
	/**
	 * ticker码
	 * weijingjing 20191023 STORY #90922 华夏基金-估值表要展示彭博ticker码
	 */
	private String c_TICKER_CODE = "";
	
	/**
	 * STORY #68358属性： 明细市场
	 */
	private String c_MKT_DETAIL;
	
	/**
	 * 转存方式
	 */
	private String c_ZCFS;
	
	/**
	 * 转存频率
	 */
	private String c_ZCPL;
	
//	/**
//	 * 转存日期
//	 */
//	private String c_ZCRQ;
//	
//	/**
//	 * 转存日期类型
//	 */
//	private String c_ZCRQLX;
	
	/**
	 * 理财付息周期设置   add by gh 20161021 BUG #140945 
	 */
	private List<SecBaseLcfxzq> secBaseLcfxzq = null;
	
	/**
	 * 基金管理人
	 * add by zhoushuhang 20210115 STORY96254华夏基金QD-彭博证券基本信息接口，增加直存“基金管理人”和“基金系列名称”的逻辑
	 */
	private String c_MANAGER_NAME = "";
	
	/**
	 * 基金系列名称
	 * add by zhoushuhang 20210115 STORY96254华夏基金QD-彭博证券基本信息接口，增加直存“基金管理人”和“基金系列名称”的逻辑
	 */
	private String c_FUND_NAME = "";
	
	/**
  	 *投资者收益账户
  	 *STORY #76847 【华宝基金】511990 SH华宝添益红利投资特殊处理 
  	 */
	private String c_INVEST_ACCOUNT = "";
	
	/**
	 * STORY #98154 AMS国寿资产V4.5-新增永续债提醒功能，若买入时该券是永续债，则给与提醒
	 * 清偿顺序  
	 */
	private String C_PAY_OFF_ORDER = "" ;
	
	/**
	 * 正股转换代码
	 * STORY #84434 财汇债券基本信息导入清算接口改造
	 */
	private String c_ZGZH_CODE = "";
	
	/**
	 * 存放品种节假日群
	 * huangkaixuan 20210727
	 * STORY109057博时基金-公募2.5已实现-存放业务品种信息节假日群的选择
	 */
	private String c_HDAY_CODE_CK = " ";
	
	public String getC_PAY_OFF_ORDER() {
		return C_PAY_OFF_ORDER;
	}

	public void setC_PAY_OFF_ORDER(String c_PAY_OFF_ORDER) {
		C_PAY_OFF_ORDER = c_PAY_OFF_ORDER;
	}

	public List<SecBaseLcfxzq> getSecBaseLcfxzq() {
		return secBaseLcfxzq;
	}

	public void setSecBaseLcfxzq(List<SecBaseLcfxzq> secBaseLcfxzq) {
		this.secBaseLcfxzq = secBaseLcfxzq;
	}
	

  	public String getC_TICKER_CODE() {
		return c_TICKER_CODE;
	}

	public void setC_TICKER_CODE(String c_TICKER_CODE) {
		this.c_TICKER_CODE = c_TICKER_CODE;
	}

	public String getC_SM_CODE() {
		return c_SM_CODE;
	}

	public void setC_SM_CODE(String c_SM_CODE) {
		this.c_SM_CODE = c_SM_CODE;
	}

	public String getC_JWTZ() {
		return c_JWTZ;
	}

	public void setC_JWTZ(String c_JWTZ) {
		this.c_JWTZ = c_JWTZ;
	}

	public String getC_QYGPDM() {
		return c_QYGPDM;
	}

	public void setC_QYGPDM(String c_QYGPDM) {
		this.c_QYGPDM = c_QYGPDM;
	}

	public String getD_QY_DATE() {
		return d_QY_DATE;
	}

	public void setD_QY_DATE(String d_QY_DATE) {
		this.d_QY_DATE = d_QY_DATE;
	}

	public String getC_SHXYBM() {
		return c_SHXYBM;
	}

	public void setC_SHXYBM(String c_SHXYBM) {
		this.c_SHXYBM = c_SHXYBM;
	}

	public String getC_QYZCD() {
		return c_QYZCD;
	}

	public void setC_QYZCD(String c_QYZCD) {
		this.c_QYZCD = c_QYZCD;
	}

	public String getC_TZHY() {
		return c_TZHY;
	}

	public void setC_TZHY(String c_TZHY) {
		this.c_TZHY = c_TZHY;
	}

	public String getC_TZJD() {
		return c_TZJD;
	}

	public void setC_TZJD(String c_TZJD) {
		this.c_TZJD = c_TZJD;
	}
	
  	@NoColumnName
	public PayDay getPayDays() {
		return payDays;
	}

	public void setPayDays(PayDay payDays) {
		this.payDays = payDays;
	}

	public String getC_SYSOURCE() {
		return c_SYSOURCE;
	}

	public void setC_SYSOURCE(String c_SYSOURCE) {
		this.c_SYSOURCE = c_SYSOURCE;
	}

	public String getC_UPDATE_TIMEWEB() {
		return c_UPDATE_TIMEWEB;
	}

	public void setC_UPDATE_TIMEWEB(String c_UPDATE_TIMEWEB) {
		this.c_UPDATE_TIMEWEB = c_UPDATE_TIMEWEB;
	}

	
  	
  	public String getC_DJPX_OPEN() {
		return c_DJPX_OPEN;
	}

	public void setC_DJPX_OPEN(String c_DJPX_OPEN) {
		this.c_DJPX_OPEN = c_DJPX_OPEN;
	}
	
  	public String getC_SNZC() {
		return c_SNZC;
	}

	public void setC_SNZC(String c_SNZC) {
		this.c_SNZC = c_SNZC;
	}

	public String getC_PAY_DATE_TYPE() {
		return c_PAY_DATE_TYPE;
	}

	public void setC_PAY_DATE_TYPE(String c_PAY_DATE_TYPE) {
		this.c_PAY_DATE_TYPE = c_PAY_DATE_TYPE;
	}

	public int getN_PAY_DATE() {
		return n_PAY_DATE;
	}

	public void setN_PAY_DATE(int n_PAY_DATE) {
		this.n_PAY_DATE = n_PAY_DATE;
	}

	public String getC_BASE_DATE_TYPE() {
		return c_BASE_DATE_TYPE;
	}

	public void setC_BASE_DATE_TYPE(String c_BASE_DATE_TYPE) {
		this.c_BASE_DATE_TYPE = c_BASE_DATE_TYPE;
	}

	public int getN_BASE_DATE() {
		return n_BASE_DATE;
	}

	public void setN_BASE_DATE(int n_BASE_DATE) {
		this.n_BASE_DATE = n_BASE_DATE;
	}
	
	public String getC_JZFS_CODE() {
		return c_JZFS_CODE;
	}

	public void setC_JZFS_CODE(String c_JZFS_CODE) {
		this.c_JZFS_CODE = c_JZFS_CODE;
	}

	public String getC_LC_SEC_TAG() {
		return c_LC_SEC_TAG;
	}

	public void setC_LC_SEC_TAG(String c_LC_SEC_TAG) {
		this.c_LC_SEC_TAG = c_LC_SEC_TAG;
	}
	public String getC_TGR_CODE() {
		return c_TGR_CODE;
	}

	public void setC_TGR_CODE(String c_TGR_CODE) {
		this.c_TGR_CODE = c_TGR_CODE;
	}


	public String getC_ISSUERS_CODE() {
		return c_ISSUERS_CODE;
	}

	public void setC_ISSUERS_CODE(String c_ISSUERS_CODE) {
		this.c_ISSUERS_CODE = c_ISSUERS_CODE;
	}

	public String getC_ISSUERS_NAME() {
		return c_ISSUERS_NAME;
	}

	public void setC_ISSUERS_NAME(String c_ISSUERS_NAME) {
		this.c_ISSUERS_NAME = c_ISSUERS_NAME;
	}

	
	public String getC_TGR_NAME() {
		return c_TGR_NAME;
	}

	public void setC_TGR_NAME(String c_TGR_NAME) {
		this.c_TGR_NAME = c_TGR_NAME;
	}

	public double getN_XSFWFL() {
		return n_XSFWFL;
	}

	public void setN_XSFWFL(double n_XSFWFL) {
		this.n_XSFWFL = n_XSFWFL;
	}

  	
	public String getC_JTJZ4STD_SETT() {
		return c_JTJZ4STD_SETT;
	}

	public void setC_JTJZ4STD_SETT(String c_JTJZ4STD_SETT) {
		this.c_JTJZ4STD_SETT = c_JTJZ4STD_SETT;
	}

	public String getC_JTJZ4NOTSTD_SETT() {
		return c_JTJZ4NOTSTD_SETT;
	}

	public void setC_JTJZ4NOTSTD_SETT(String c_JTJZ4NOTSTD_SETT) {
		this.c_JTJZ4NOTSTD_SETT = c_JTJZ4NOTSTD_SETT;
	}	
	
	public String getC_SJSSZQ() {
		return c_SJSSZQ;
	}
	
	public void setC_SJSSZQ(String c_SJSSZQ) {
		this.c_SJSSZQ = c_SJSSZQ;
	}
		
	public String getC_INTERVAL_DAY() {
		return c_INTERVAL_DAY;
	}

	public void setC_INTERVAL_DAY(String c_INTERVAL_DAY) {
		this.c_INTERVAL_DAY = c_INTERVAL_DAY;
	}

	public String getC_INTERVAL_TIME() {
		return c_INTERVAL_TIME;
	}

	public void setC_INTERVAL_TIME(String c_INTERVAL_TIME) {
		this.c_INTERVAL_TIME = c_INTERVAL_TIME;
	}

	public String getC_DV_RTA() {
		return c_DV_RTA;
	}
 
	public void setC_DV_RTA(String c_DV_RTA) {
		this.c_DV_RTA = c_DV_RTA;
	}

	public BigDecimal getN_SPREAD() {
		return n_SPREAD;
	}

	public void setN_SPREAD(BigDecimal n_SPREAD) {
		this.n_SPREAD = n_SPREAD;
	}

	public String getC_EXPR_CODE() {
		return c_EXPR_CODE;
	}

	public void setC_EXPR_CODE(String c_EXPR_CODE) {
		this.c_EXPR_CODE = c_EXPR_CODE;
	}

	public BigDecimal getN_BLXS() {
		return n_BLXS;
	}

	public void setN_BLXS(BigDecimal n_BLXS) {
		this.n_BLXS = n_BLXS;
	}

	public String getC_JCJG() {
		return c_JCJG;
	}

	public void setC_JCJG(String c_JCJG) {
		this.c_JCJG = c_JCJG;
	}

	public BigDecimal getN_UPPER_LIMIT() {
		return n_UPPER_LIMIT;
	}

	public void setN_UPPER_LIMIT(BigDecimal n_UPPER_LIMIT) {
		this.n_UPPER_LIMIT = n_UPPER_LIMIT;
	}

	public BigDecimal getN_LOWER_LIMIT() {
		return n_LOWER_LIMIT;
	}

	public void setN_LOWER_LIMIT(BigDecimal n_LOWER_LIMIT) {
		this.n_LOWER_LIMIT = n_LOWER_LIMIT;
	}

	public String getC_RZRQ_MARK() {
		return c_RZRQ_MARK;
	}

	public void setC_RZRQ_MARK(String c_RZRQ_MARK) {
		this.c_RZRQ_MARK = c_RZRQ_MARK;
	}

	public String getC_ETF_TYPE() {
		return c_ETF_TYPE;
	}

	public void setC_ETF_TYPE(String c_ETF_TYPE) {
		this.c_ETF_TYPE = c_ETF_TYPE;
	}

	public BigDecimal getN_RATIO() {
		return n_RATIO;
	}

	public void setN_RATIO(BigDecimal n_RATIO) {
		this.n_RATIO = n_RATIO;
	}

	public String getD_END() {
		return d_END;
	}

	public void setD_END(String d_END) {
		this.d_END = d_END;
	}

	public String getC_SEC_NAME_CN() {
		return c_SEC_NAME_CN;
	}

	public void setC_SEC_NAME_CN(String cSECNAMECN) {
		c_SEC_NAME_CN = cSECNAMECN;
	}

	private static final long serialVersionUID = 1L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getC_SEC_CODE() {
		return c_SEC_CODE;
	}

	public void setC_SEC_CODE(String cSECCODE) {
		c_SEC_CODE = cSECCODE;
	}

	public String getC_SEC_NAME() {
		return c_SEC_NAME;
	}

	public void setC_SEC_NAME(String cSECNAME) {
		c_SEC_NAME = cSECNAME;
	}

	public String getC_SEC_MKT_CODE() {
		return c_SEC_MKT_CODE;
	}

	public void setC_SEC_MKT_CODE(String cSECMKTCODE) {
		c_SEC_MKT_CODE = cSECMKTCODE;
	}

	public String getC_SEC_ISIN_CODE() {
		return c_SEC_ISIN_CODE;
	}

	public void setC_SEC_ISIN_CODE(String cSECISINCODE) {
		c_SEC_ISIN_CODE = cSECISINCODE;
	}

	public String getC_MKT_CODE() {
		return c_MKT_CODE;
	}

	public void setC_MKT_CODE(String cMKTCODE) {
		c_MKT_CODE = cMKTCODE;
	}

	public String getC_SEC_VAR_CODE() {
		return c_SEC_VAR_CODE;
	}

	public void setC_SEC_VAR_CODE(String cSECVARCODE) {
		c_SEC_VAR_CODE = cSECVARCODE;
	}

	public String getC_DC_CODE() {
		return c_DC_CODE;
	}

	public void setC_DC_CODE(String cDCCODE) {
		c_DC_CODE = cDCCODE;
	}
	
	public String getC_PCYK_CURY() {
		return c_PCYK_CURY;
	}

	public void setC_PCYK_CURY(String cPCYKCURY) {
		c_PCYK_CURY = cPCYKCURY;
	}

	public String getC_SEC_CODE_TRG() {
		return c_SEC_CODE_TRG;
	}

	public void setC_SEC_CODE_TRG(String cSECCODETRG) {
		c_SEC_CODE_TRG = cSECCODETRG;
	}

	public Date getD_TO_LIST() {
		return d_TO_LIST;
	}

	public void setD_TO_LIST(Date dTOLIST) {
		d_TO_LIST = dTOLIST;
	}

	public Date getD_OFF_LIST() {
		return d_OFF_LIST;
	}

	public void setD_OFF_LIST(Date dOFFLIST) {
		d_OFF_LIST = dOFFLIST;
	}

	public String getC_DV_VAR_DUR() {
		return c_DV_VAR_DUR;
	}

	public String getN_PRICE_FCR() {
		return n_PRICE_FCR;
	}

	public void setN_PRICE_FCR(String n_PRICE_FCR) {
		this.n_PRICE_FCR = n_PRICE_FCR;
	}

	public String getN_AMOUNT_HD() {
		return n_AMOUNT_HD;
	}

	public void setN_AMOUNT_HD(String n_AMOUNT_HD) {
		this.n_AMOUNT_HD = n_AMOUNT_HD;
	}

	public String getN_FV_ISSUE() {
		return n_FV_ISSUE;
	}

	public void setN_FV_ISSUE(String n_FV_ISSUE) {
		this.n_FV_ISSUE = n_FV_ISSUE;
	}

	public void setC_DV_VAR_DUR(String cDVVARDUR) {
		c_DV_VAR_DUR = cDVVARDUR;
	}

	public String getC_DV_QUT_MOD() {
		return c_DV_QUT_MOD;
	}

	public void setC_DV_QUT_MOD(String cDVQUTMOD) {
		c_DV_QUT_MOD = cDVQUTMOD;
	}

	public BigDecimal getN_RATE() {
		return n_RATE;
	}

	public void setN_RATE(BigDecimal nRATE) {
		n_RATE = nRATE;
	}

	public BigDecimal getN_FV_IR() {
		return n_FV_IR;
	}

	public void setN_FV_IR(BigDecimal nFVIR) {
		n_FV_IR = nFVIR;
	}

	public BigDecimal getN_PRICE_ISSUE() {
		return n_PRICE_ISSUE;
	}

	public void setN_PRICE_ISSUE(BigDecimal nPRICEISSUE) {
		n_PRICE_ISSUE = nPRICEISSUE;
	}

	public String getC_DV_AI_MOD() {
		return c_DV_AI_MOD;
	}

	public void setC_DV_AI_MOD(String cDVAIMOD) {
		c_DV_AI_MOD = cDVAIMOD;
	}

	public String getC_DV_PI_MOD() {
		return c_DV_PI_MOD;
	}

	public void setC_DV_PI_MOD(String cDVPIMOD) {
		c_DV_PI_MOD = cDVPIMOD;
	}

	public String getD_AI_BEGIN() {
		return d_AI_BEGIN;
	}

	public void setD_AI_BEGIN(String dAIBEGIN) {
		d_AI_BEGIN = dAIBEGIN;
	}

	public String getD_AI_END() {
		return d_AI_END;
	}

	public void setD_AI_END(String dAIEND) {
		d_AI_END = dAIEND;
	}

	public String getC_DESC() {
		return c_DESC;
	}

	public void setC_DESC(String cDESC) {
		c_DESC = cDESC;
	}

	public String getC_DV_AI_EXPR() {
		return c_DV_AI_EXPR;
	}

	public void setC_DV_AI_EXPR(String cDVAIEXPR) {
		c_DV_AI_EXPR = cDVAIEXPR;
	}

	public String getC_ORG_CODE() {
		return c_ORG_CODE;
	}

	public void setC_ORG_CODE(String cORGCODE) {
		c_ORG_CODE = cORGCODE;
	}

	public String getC_ORG_NAME() {
		return c_ORG_NAME;
	}

	public void setC_ORG_NAME(String c_ORG_NAME) {
		this.c_ORG_NAME = c_ORG_NAME;
	}

	public String getC_OPEN_ACC_NAME() {
		return c_OPEN_ACC_NAME;
	}

	public void setC_OPEN_ACC_NAME(String c_OPEN_ACC_NAME) {
		this.c_OPEN_ACC_NAME = c_OPEN_ACC_NAME;
	}

	public String getC_OPEN_ACC_NO() {
		return c_OPEN_ACC_NO;
	}

	public void setC_OPEN_ACC_NO(String c_OPEN_ACC_NO) {
		this.c_OPEN_ACC_NO = c_OPEN_ACC_NO;
	}

	public String getC_SYS_CODE() {
		return c_SYS_CODE;
	}

	public void setC_SYS_CODE(String c_SYS_CODE) {
		this.c_SYS_CODE = c_SYS_CODE;
	}

	public String getC_CREDIT_RATING() {
		return C_CREDIT_RATING;
	}

	public void setC_CREDIT_RATING(String cCREDITRATING) {
		C_CREDIT_RATING = cCREDITRATING;
	}

	public String getC_DV_ISSUE() {
		return c_DV_ISSUE;
	}

	public void setC_DV_ISSUE(String c_DV_ISSUE) {
		this.c_DV_ISSUE = c_DV_ISSUE;
	}

	public String getC_DV_ASSURE() {
		return c_DV_ASSURE;
	}

	public void setC_DV_ASSURE(String c_DV_ASSURE) {
		this.c_DV_ASSURE = c_DV_ASSURE;
	}
	
	public String getC_SETT_ORG() {
		return c_SETT_ORG;
	}

	public void setC_SETT_ORG(String c_SETT_ORG) {
		this.c_SETT_ORG = c_SETT_ORG;
	}

	public String getC_DV_RIGHT() {
		return c_DV_RIGHT;
	}

	public void setC_DV_RIGHT(String c_DV_RIGHT) {
		this.c_DV_RIGHT = c_DV_RIGHT;
	}

	public String getC_PORT_CODE() {
		return this.c_PORT_CODE;
	}

	public void setC_PORT_CODE(String c_PORT_CODE) {
		this.c_PORT_CODE = c_PORT_CODE;
	}
	
	public String getD_SQAI_BEGIN() {
		return d_SQAI_BEGIN;
	}

	public void setD_SQAI_BEGIN(String d_SQAI_BEGIN) {
		this.d_SQAI_BEGIN = d_SQAI_BEGIN;
	}
	
	public String getD_TRA_BEGIN() {
		return d_TRA_BEGIN;
	}

	public void setD_TRA_BEGIN(String d_TRA_BEGIN) {
		this.d_TRA_BEGIN = d_TRA_BEGIN;
	}

	public String getD_TRA_END() {
		return d_TRA_END;
	}

	public void setD_TRA_END(String d_TRA_END) {
		this.d_TRA_END = d_TRA_END;
	}

	public String getC_FX_CODE() {
		return c_FX_CODE;
	}

	public void setC_FX_CODE(String c_FX_CODE) {
		this.c_FX_CODE = c_FX_CODE;
	}
	
	public String getC_BANK_HEAD() {
		return c_BANK_HEAD;
	}

	public void setC_BANK_HEAD(String c_BANK_HEAD) {
		this.c_BANK_HEAD = c_BANK_HEAD;
	}

	public String getC_BANK_BRANCH() {
		return c_BANK_BRANCH;
	}

	public void setC_BANK_BRANCH(String c_BANK_BRANCH) {
		this.c_BANK_BRANCH = c_BANK_BRANCH;
	}
	
	public BigDecimal getN_FESX() {
		return n_FESX;
	}

	public void setN_FESX(BigDecimal n_FESX) {
		this.n_FESX = n_FESX;
	}

	public BigDecimal getN_FEXX() {
		return n_FEXX;
	}

	public void setN_FEXX(BigDecimal n_FEXX) {
		this.n_FEXX = n_FEXX;
	}
	public String getC_GUAR_TYPE() {
		return c_GUAR_TYPE;
	}

	public void setC_GUAR_TYPE(String c_GUAR_TYPE) {
		this.c_GUAR_TYPE = c_GUAR_TYPE;
	}

	public String getC_FINA_COMM() {
		return c_FINA_COMM;
	}

	public void setC_FINA_COMM(String c_FINA_COMM) {
		this.c_FINA_COMM = c_FINA_COMM;
	}
	
	public double getN_TODAY_INTEREST() {
		return n_TODAY_INTEREST;
	}

	public void setN_TODAY_INTEREST(double n_TODAY_INTEREST) {
		this.n_TODAY_INTEREST = n_TODAY_INTEREST;
	}
	
	
	public String getC_REPO_TYPE() {
		return c_REPO_TYPE;
	}

	public void setC_REPO_TYPE(String c_REPO_TYPE) {
		this.c_REPO_TYPE = c_REPO_TYPE;
	}

	public String getC_REPO_INCOME_FROM() {
		return c_REPO_INCOME_FROM;
	}

	public void setC_REPO_INCOME_FROM(String c_REPO_INCOME_FROM) {
		this.c_REPO_INCOME_FROM = c_REPO_INCOME_FROM;
	}

	public String getC_REPO_INCOME_TO() {
		return c_REPO_INCOME_TO;
	}

	public void setC_REPO_INCOME_TO(String c_REPO_INCOME_TO) {
		this.c_REPO_INCOME_TO = c_REPO_INCOME_TO;
	}

	public String getC_FINANCE_TOOL() {
		return c_FINANCE_TOOL;
	}

	public void setC_FINANCE_TOOL(String c_FINANCE_TOOL) {
		this.c_FINANCE_TOOL = c_FINANCE_TOOL;
	}

	public String getC_MAIN_PROP() {
		return c_MAIN_PROP;
	}

	public void setC_MAIN_PROP(String c_MAIN_PROP) {
		this.c_MAIN_PROP = c_MAIN_PROP;
	}
	
	public String getC_MAIN_NAME() {
		return c_MAIN_NAME;
	}

	public void setC_MAIN_NAME(String c_MAIN_NAME) {
		this.c_MAIN_NAME = c_MAIN_NAME;
	}
	
	public String getC_FXJG_CODE() {
		return c_FXJG_CODE;
	}

	public void setC_FXJG_CODE(String c_FXJG_CODE) {
		this.c_FXJG_CODE = c_FXJG_CODE;
	}
	
	public String getC_SEC_VAR_CLS() {
		return c_SEC_VAR_CLS;
	}

	public void setC_SEC_VAR_CLS(String c_SEC_VAR_CLS) {
		this.c_SEC_VAR_CLS = c_SEC_VAR_CLS;
	}
	
	public String getC_ORG_TYPE() {
		return c_ORG_TYPE;
	}

	public void setC_ORG_TYPE(String c_ORG_TYPE) {
		this.c_ORG_TYPE = c_ORG_TYPE;
	}

	public String getC_KZCD_GQ() {
		return C_KZCD_GQ;
	}

	public void setC_KZCD_GQ(String c_KZCD_GQ) {
		C_KZCD_GQ = c_KZCD_GQ;
	}
	
	public String getC_MKT_CODE_JC() {
		return c_MKT_CODE_JC;
	}

	public void setC_MKT_CODE_JC(String c_MKT_CODE_JC) {
		this.c_MKT_CODE_JC = c_MKT_CODE_JC;
	}
	
  	public int getN_JXTS() {
		return n_JXTS;
	}

	public void setN_JXTS(int n_JXTS) {
		this.n_JXTS = n_JXTS;
	}
	
	public String getC_DV_JXFS() {
		return c_DV_JXFS;
	}

	public void setC_DV_JXFS(String c_DV_JXFS) {
		this.c_DV_JXFS = c_DV_JXFS;
	}
	
	public String getC_SEDOLCODE() {
		return c_SEDOLCODE;
	}

	public void setC_SEDOLCODE(String c_SEDOLCODE) {
		this.c_SEDOLCODE = c_SEDOLCODE;
	}

	public double getN_GLF_RATE() {
		return n_GLF_RATE;
	}

	public void setN_GLF_RATE(double n_GLF_RATE) {
		this.n_GLF_RATE = n_GLF_RATE;
	}

	public double getN_TGF_RATE() {
		return n_TGF_RATE;
	}

	public void setN_TGF_RATE(double n_TGF_RATE) {
		this.n_TGF_RATE = n_TGF_RATE;
	}
	
	public void setD_TO_LIST_ZQ(String d_TO_LIST_Z) {
		this.d_TO_LIST_ZQ = d_TO_LIST_Z;
	}
	
	public String getD_TO_LIST_ZQ(){
		return d_TO_LIST_ZQ;
	}
	
	public String getC_TXH_NAME() {
		return c_TXH_NAME;
	}

	public void setC_TXH_NAME(String c_TXH_NAME) {
		this.c_TXH_NAME = c_TXH_NAME;
	}

	public String getC_TXH_CODE() {
		return c_TXH_CODE;
	}

	public void setC_TXH_CODE(String c_TXH_CODE) {
		this.c_TXH_CODE = c_TXH_CODE;
	}
	
	public String getC_MKT_DETAIL() {
		return c_MKT_DETAIL;
	}

	public void setC_MKT_DETAIL(String c_MKT_DETAIL) {
		this.c_MKT_DETAIL = c_MKT_DETAIL;
	}

	public String getC_JYSSZG() {
		return c_JYSSZG;
	}

	public void setC_JYSSZG(String c_JYSSZG) {
		this.c_JYSSZG = c_JYSSZG;
	}

	public String getC_ZCDGJ() {
		return c_ZCDGJ;
	}

	public void setC_ZCDGJ(String c_ZCDGJ) {
		this.c_ZCDGJ = c_ZCDGJ;
	}
	
	public String getC_PBSEC_CODE() {
		return c_PBSEC_CODE;
	}

	public void setC_PBSEC_CODE(String c_PBSEC_CODE) {
		this.c_PBSEC_CODE = StringUtil.IsNullOrEmptyT(c_PBSEC_CODE) ? null : c_PBSEC_CODE;
	}

	public String getC_ZCFS() {
		return c_ZCFS;
	}

	public void setC_ZCFS(String c_ZCFS) {
		this.c_ZCFS = c_ZCFS;
	}

	public String getC_ZCPL() {
		return c_ZCPL;
	}

	public void setC_ZCPL(String c_ZCPL) {
		this.c_ZCPL = c_ZCPL;
	}

	public String getC_MANAGER_NAME() {
		return c_MANAGER_NAME;
	}

	public void setC_MANAGER_NAME(String c_MANAGER_NAME) {
		this.c_MANAGER_NAME = c_MANAGER_NAME;
	}

	public String getC_FUND_NAME() {
		return c_FUND_NAME;
	}

	public void setC_FUND_NAME(String c_FUND_NAME) {
		this.c_FUND_NAME = c_FUND_NAME;
	}

	public String getC_INVEST_ACCOUNT() {
		return c_INVEST_ACCOUNT;
	}

	public void setC_INVEST_ACCOUNT(String c_INVEST_ACCOUNT) {
		this.c_INVEST_ACCOUNT = c_INVEST_ACCOUNT;
	}
	
	public String getC_ZGZH_CODE() {
		return c_ZGZH_CODE;
	}

	public void setC_ZGZH_CODE(String c_ZGZH_CODE) {
		this.c_ZGZH_CODE = c_ZGZH_CODE;
	}

	public BigDecimal getN_GRACE_PERIOD() {
		return n_GRACE_PERIOD;
	}

	public void setN_GRACE_PERIOD(BigDecimal n_GRACE_PERIOD) {
		this.n_GRACE_PERIOD = n_GRACE_PERIOD;
	}

	public String getC_GRACE_PERIOD_RULE() {
		return c_GRACE_PERIOD_RULE;
	}

	public void setC_GRACE_PERIOD_RULE(String c_GRACE_PERIOD_RULE) {
		this.c_GRACE_PERIOD_RULE = c_GRACE_PERIOD_RULE;
	}

	public String getC_HDAY_CODE_CK() {
		return c_HDAY_CODE_CK;
	}

	public void setC_HDAY_CODE_CK(String c_HDAY_CODE_CK) {
		this.c_HDAY_CODE_CK = c_HDAY_CODE_CK;
	}
	
	
//	public String getC_ZCRQ() {
//		return c_ZCRQ;
//	}
//
//	public void setC_ZCRQ(String c_ZCRQ) {
//		this.c_ZCRQ = c_ZCRQ;
//	}
//
//	public String getC_ZCRQLX() {
//		return c_ZCRQLX;
//	}
//
//	public void setC_ZCRQLX(String c_ZCRQLX) {
//		this.c_ZCRQLX = c_ZCRQLX;
//	}
}
