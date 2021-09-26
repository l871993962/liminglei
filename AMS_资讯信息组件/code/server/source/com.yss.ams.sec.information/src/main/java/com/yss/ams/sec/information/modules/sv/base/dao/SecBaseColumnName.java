package com.yss.ams.sec.information.modules.sv.base.dao;

/**
 * 证券基本信息  数据库表对应字段
 * @author 马向峰
 *
 */
public enum SecBaseColumnName {

	/**
	* 证券代码 
	*/
	c_SEC_CODE("C_sec_code"),

	/**
	* 证券名称 
	*/
	c_SEC_NAME("C_sec_name"),

	/**
	* 证券上市代码 
	*/
	c_SEC_MKT_CODE("C_sec_mkt_code"),

	/**
	* 证券ISIN代码 
	*/
	c_SEC_ISIN_CODE("C_sec_isin_code"),
	
	/**
	 * 合并需求：STORY #38433 南方基金-OA债券分销：债券临时代码和正式代码设置转换关系 
	 * 分销代码
	 */
	c_FX_CODE("C_fx_code"),

	/**
	* 交易市场代码 
	*/
	c_MKT_CODE("C_mkt_code"),

	/**
	* 证券品种代码 
	*/
	c_SEC_VAR_CODE("C_sec_var_code"),

	/**
	* 币种代码 
	*/
	c_DC_CODE("C_dc_code"),
	
	/**
	* 平仓盈亏币种代码   合并需求：zhaijiajia STORY35879【招商基金】【QDII】远期外汇需支持交割期间部分多次平仓业务  远期品种信息 增加平仓盈亏币种
	*/
	c_PCYK_CURY("C_pcyk_cury"),

	/**
	* 报价因子 
	*/
	n_PRICE_FCR("N_price_fcr"),

	/**
	 * 发行机构
	 */
	c_FXJG_CODE("C_fxjg_code"),
	
	/**
	* 标的证券 
	*/
	c_SEC_CODE_TRG("C_sec_code_trg"),

	/**
	* 每手数量 
	*/
	n_AMOUNT_HD("N_amount_hd"),

	/**
	* 发行面值 
	*/
	n_FV_ISSUE("N_fv_issue"),

	/**
	* 市日期 
	*/
	d_TO_LIST("D_to_list"),

	/**
	* 退市日期 
	*/
	d_OFF_LIST("D_off_list"),
	
//	/**
//	* 开始日期 
//	*/
//	startUseDate("D_to_list"),
//
//	/**
//	* 结束日期 
//	*/
//	endUseDate("D_off_list"),

	/**
	* 品种期限 
	*/
	c_DV_VAR_DUR("C_dv_var_dur"),
	
	/**
	 * add by zhd 2016-09-07
	 * STORY34120【南方基金】【紧急】现金存放业务，流水加入标识，4级科目不明确到品种，而是按照标识来区分
	 * 实际所属证券
	 */
	c_SJSSZQ("C_SJSSZQ"),

	/**
	* 报价方式 
	*/
	c_DV_QUT_MOD("C_dv_qut_mod"),

	/**
	* 税率 
	*/
	n_RATE("N_rate"),

	/**
	* 票面利率 
	*/
	n_FV_IR("N_fv_ir"),

	/**
	* add by ZhangJM  STORY #76306 银华基金：投资分级基金A类产品，每日计提收益，卖出时进行冲减
	* 票面日利率 
	*/
	n_FV_DAY_IR("N_fv_day_ir"),
	
	/**
	* 发行价格 
	*/
	n_PRICE_ISSUE("N_price_issue"),

	/**
	* 计息方式 
	*/
	c_DV_AI_MOD("C_dv_ai_mod"),

	/**
	* 付息公式 
	*/
	c_DV_PI_MOD("C_dv_pi_mod"),

	/**
	* 计息起始日 
	*/
	d_AI_BEGIN("D_ai_begin"),

	/**
	 * 首期起息日期
	 */
	d_SQAI_BEGIN("D_sqai_begin"),
	
	/**
	* 计息截止日 
	*/
	d_AI_END("D_ai_end"),

	/**
	* //描述 
	*/
	c_DESC("C_desc"),

	/**
	* 计息公式 
	*/
	c_DV_AI_EXPR("C_DV_AI_EXPR"),

	/**
	 * 机构代码字段
	 */
	c_ORG_CODE("C_ORG_CODE"),
	
	/**
	 * 出票人
	 */
	c_ORG_NAME("C_ORG_NAME"),
	
	/**
	 * 贴现行行名
	 */
	c_TXH_NAME("C_TXH_NAME"),
	
	/**
	 * 贴现行行号
	 */
	c_TXH_CODE("C_TXH_CODE"),
	
	/**
	 * 承兑人行名
	 */
	c_OPEN_ACC_NAME("C_OPEN_ACC_NAME"),
	
	/**
	 * 承兑人行号
	 */
	c_OPEN_ACC_NO("C_OPEN_ACC_NO"),
	
	/**
	 * 大额支付号
	 */
	c_SYS_CODE("C_SYS_CODE"),
	
	/**
	 * 发行类型
	 * add by tangshifeng 2013-07-03
	 */
	c_DV_ISSUE("C_DV_ISSUE"),
	
	/**
	 * 担保方式
	 * add by tangshifeng 2013-07-03
	 */
	c_DV_ASSURE("C_DV_ASSURE"),
	
	/**
	 * 债券新用评级
	 */
	c_CREDIT_RATING("C_CREDIT_RATING"),
	
	/**
	 * 券中文名称
	 */
	c_SEC_NAME_CN("c_SEC_NAME_CN"),
	
	/**
	 * ETF类型
	 */
	c_ETF_TYPE("c_ETF_TYPE"),
	
	/**
	 * (合并代码 STORY24925)add by dingshlau 20160405 STORY #28335
	 * 利率类型  
	 */
	c_DV_RTA("C_dv_rta"),
	
	/**
     *STORY #55718 【南方基金】关于香港市场节假日与国内节假日出现非对称节假日情况的估值问题
     *
	 */
	d_TO_LIST_ZQ("D_to_list_zq"),

	/**
	 * (合并代码 STORY24925)add by dingshlau 20160405 STORY #28335
	 * 利差
	 */
	n_SPREAD("N_spread"),
	/**
	 * (合并代码 STORY24925)add by dingshlau 20160405 STORY #28335
	 * 利率代码 
	 */
	c_EXPR_CODE("C_expr_code"),
	/**
	 * (合并代码 STORY24925)add by dingshlau 20160405 STORY #28335
	 * 比例系数
	 */
	n_BLXS("N_blxs"),
	/**
	 * (合并代码 STORY24925)add by dingshlau 20160405 STORY #28335
	 * 检查间隔 
	 */
	c_JCJG("C_jcjg"),
	/**
	 * (合并代码 STORY24925)add by dingshlau 20160405 STORY #28335
	 * 利率上限
	 */
	n_UPPER_LIMIT("N_upper_limit"),
	/**
	 * (合并代码 STORY24925)add by dingshlau 20160405 STORY #28335
	 * 利率下限 
	 */
	n_LOWER_LIMIT("N_lower_limit"),
	/**
	 * (合并代码 STORY24925)add by dingshlau 20160405 STORY #28335
	 * 间隔时间周期
	 */
	c_INTERVAL_TIME("C_interval_time"),
	/**
	 * (合并代码 STORY24925)add by dingshlau 20160405 STORY #28335
	 * 周期天数
	 */
	c_INTERVAL_DAY("C_interval_day"),

	/**
	 * 组合代码
	 * @author liuxiang 2016-5-11 STORY #25484 YSS:债券品种信息和理财品种信息增加组合私有化功能 
	 */
	c_PORT_CODE("C_PORT_CODE"),
	
	/**
	 * STORY #47088 债券基本信息，增加发行机构类型字段，区分金融机构和非金融机构
	 * 主体性质
	 * edit by yuanyafeng 20171025
	 */
	c_MAIN_PROP("C_MAIN_PROP"),
	
	/**
	 * 正股转换代码
	 * STORY #84434 财汇债券基本信息导入清算接口改造
	 */
	c_ZGZH_CODE("C_ZGZH_CODE"),
	
	id("C_IDEN"),

	operator("C_CHECK_BY"),

	auditDate("C_CHECK_TIME"),

	modifier("C_UPDATE_BY"),

	modifyDate("C_UPDATE_TIME"),

	auditState("N_CHECK_STATE"),
	
	n_RATIO("N_RATIO"),

	d_END("D_END"),
	
	/**
	 * 结算机构
	 */
	c_SETT_ORG("C_SETT_ORG"),
	
	/**
	 * 含权标志
	 */
	c_DV_RIGHT("C_DV_RIGHT"),

	/**
	 * 融资融券标示
	 */
	c_RZRQ_MARK("C_RZRQ_MARK"),
	
	/**
	 * 合并需求：STORY #39209
	 * 	转换起始日
	 */
	d_TRA_BEGIN("D_TRA_BEGIN"),
	
	/**
	 * *合并需求： STORY #39209
	 * 转换截止日
	 */
	d_TRA_END("D_TRA_END"),
	
	c_JTJZ4STD_SETT("C_JTJZ4STD_SETT"),
	c_JTJZ4NOTSTD_SETT("C_JTJZ4NOTSTD_SETT"),
	c_BANK_HEAD("C_BANK_CODE"),
	c_BANK_BRANCH("C_BRANCH_BANK_CODE"),
	
	/**
	 * STORY 41687
	 * 含付息日当天利息
	 */
	n_TODAY_INTEREST("N_TODAY_INTEREST"),
	
    /**
	 * STORY 40480
	 * 托管人代码
	 */
	c_TGR_CODE("C_TGR_CODE"),
	
	/**
	 * STORY 40480
	 * 托管人名称
	 */
	c_TGR_NAME("C_TGR_NAME"),
	
	/**
	 * STORY 40480
	 * 销售服务费率
	 */
	n_XSFWFL("N_XSFWFL"),
	
	/**
	 * STORY #74585 【华宝基金】——标准证券界面——理财产品信息中增加两个字段（合并需求）  
	 * 服务费费率
	 */
	n_GLF_RATE("N_GLF_RATE"),
	
	/**
	 * STORY #74585 【华宝基金】——标准证券界面——理财产品信息中增加两个字段（合并需求）  
	 * 管理费费率
	 */
	n_TGF_RATE("N_TGF_RATE"),
	
	/**
	 * STORY 40222
	 */
	c_SEC_VAR_CLS("C_SEC_VAR_CLS"),
	
	/**
	 * STORY#42965
	 * 份额上线
	 */
	n_FESX("N_FESX"),
	
	/**
	 * STORY 40480
	 * 销售服务费率
	 */
	n_FEXX("N_FEXX"),
	//add zhangmingbo story 45603 [易方达QDII]证券信息数据导入清算 2017 9 16
	c_ISSUERS_CODE("C_ISSUERS_CODE"),
	c_ISSUERS_NAME("C_ISSUERS_NAME"),
	
	/**
	 * 保本类型
	 * add by wangpeixu 20170606
	 * STORY37961财税140号文件，针对资管增值税系统改造需求
	 */
	c_GUAR_TYPE("C_GUAR_TYPE"),
	
	/**
	 * 金融商品
	 * add by wangpeixu 20170606
	 * STORY37961财税140号文件，针对资管增值税系统改造需求
	 */
	c_FINA_COMM("C_FINA_COMM"),
	
	/**
  	 * 分类标识
  	 * add by chenshenzhou 20171112
  	 * STORY47986 - 理财区分公募养老金等产品类型，以及特殊管理费计提方式需求
  	 */
	c_LC_SEC_TAG("C_LC_SEC_TAG"),
	
	/**
	 *  购回日期类型
	 *  add by shijian 2017-11-09 STORY #47805 嘉实基金QD—回购业务需求
	 */
	c_REPO_TYPE("C_REPO_TYPE"),
	
	/**
	 *  回购收益计算起始日期
	 *  add by shijian 2017-11-09 STORY #47805 嘉实基金QD—回购业务需求
	 */
	c_REPO_INCOME_FROM("C_REPO_INCOME_FROM"),
	
	/**
	 *  回购收益计算结束日期
	 *  add by shijian 2017-11-09 STORY #47805 嘉实基金QD—回购业务需求
	 */
	c_REPO_INCOME_TO("C_REPO_INCOME_TO"),
	
	/**
	 * 金融工具
	 * added by HeLiang 2017-11-14 STORY #43829 新企业会计准则（Ifrs9）解决方案
	 */
	c_FINANCE_TOOL("C_FINANCE_TOOL"),
	
	/**
	 * SEDOLCODE码
	 * STORY #50934 [易方达基金] [QDII] 新增接口更新A股的ISIN和英文信息 add by qiguanzhi 20180119
	 */
	c_SEDOLCODE("C_SEDOLCODE"),
	
	/**
	 * 集资方式   Add By HeMing 
	 * STORY #53605 【鹏华基金】债券品种信息投资分类增加“公募”“私募”用以区分不同的估值方案
	 * */
	c_JZFS_CODE("C_JZFS_CODE"),
	
	/**
	 * STORY #56789 【易方达子公司】【非标】【紧急】增加理财品种的需求
	 */
	c_ORG_TYPE("C_ORG_TYPE"),
			
	/**
	 * 付息日期类型
	 * add by cxj 20170705
	 * STORY32772【南方基金】【V2.5需求】南方基金：协定存款付息方式增加
	 */
	c_PAY_DATE_TYPE("C_PAY_DATE_TYPE"),
	
	/**
	 * 付息天数
	 * add by cxj 20170705
	 * STORY32772【南方基金】【V2.5需求】南方基金：协定存款付息方式增加
	 */
	n_PAY_DATE("N_PAY_DATE"),
	
	/**
	 * 基准日期类型
	 * add by cxj 20170705
	 * STORY32772【南方基金】【V2.5需求】南方基金：协定存款付息方式增加
	 */
	c_BASE_DATE_TYPE("C_BASE_DATE_TYPE"),
	
	/**
	 * 基准天数
	 * add by cxj 20170705
	 * STORY32772【南方基金】【V2.5需求】南方基金：协定存款付息方式增加
	 */
	n_BASE_DATE("N_BASE_DATE"),
	
	/**
	 * STORY52063仅在大宗协议平台上市的债券，收盘价不作为估值行情处理
  	 * 添加“交易平台”字段
	 */
	c_DV_PLAT("C_DV_PLAT"),
	
	/**
  	 * STORY #63605 富国基金-【公募】存放业务无法生成正确得付息信息和支取流水
  	 * 付息日计算
  	 */
	c_PAY_CALCULATE("C_PAY_CALCULATE"),
	
	/**
	 * 省内资产
	 * STORY # 59902 代码开发-改造存放品种信息界面，新增存放品种
	 * add by wenxiyang 20180815
	 */
	c_SNZC("C_SNZC"),
	
	/**
	 * 数据来源
	 */
	c_DATA_IDF("C_DATA_IDF"),
	
	/**
	 * 控制程度
	 * STORY #62079 人保资产-支持人保资产新科目体系核算
	 */
	c_KZCD_GQ("C_KZCD_GQ"),
	/**
	 * 标识
	 */
	c_BZ("C_BZ"),
	
	/**
	 * STORY #66077 人保资产-支持银行间债券A/A特殊计息
	 * 计息天数
	 */
	n_JXTS("N_JXTS"),
	
	/**
	 * 对手方账户
	 */
	c_DSFZH("C_DSFZH"),
	
	/**
     * 对价派息启用
     * add by ZhuJinYang 20181127
     * STORY65338工银瑞信基金-非标产品需要控制是否生成对价派息信息（紧急）
     */
	c_DJPX_OPEN("C_DJPX_OPEN"),
	
	/**
	 * 计息方式
	 * STORY64492【易方达香港】【一期】境外回购支持按年利率计算日收益进行计提
	 */
	c_DV_JXFS("C_DV_JXFS"),
	
	/**
	 * 境外市场名称
	 * STORY #51261 [易方达] 彭博证券信息读入境外市场
	*/
	c_MKT_CODE_JC(""),
	
	c_UPDATE_TIMEWEB(""),
	
	c_SYSOURCE(""),
	
	/**
	 * STORY #75904 第一批：QDII-【博时基金】抗通胀债券需求  add by sunyanlin 20190910
	 */
	c_INDEX_TYPE("C_INDEX_TYPE"),
	
	/**
  	 * 是否境外投资
  	 * add by jiaxinxiao
  	 * STORY #78461 【上海银行】与私募产品运行检测表相关的项目字段新增
  	 */
	c_JWTZ("C_JWTZ"),
	/**
  	 * 项目企业股票代码
  	 * add by jiaxinxiao
  	 * STORY #78461 【上海银行】与私募产品运行检测表相关的项目字段新增
  	 */
	c_QYGPDM("C_QYGPDM"),
	/**
  	 * 成立日期
  	 * add by jiaxinxiao
  	 * STORY #78461 【上海银行】与私募产品运行检测表相关的项目字段新增
  	 */
	d_QY_DATE("D_QY_DATE"),
	/**
  	 * 项目企业统一社会信用编码
  	 * add by jiaxinxiao
  	 * STORY #78461 【上海银行】与私募产品运行检测表相关的项目字段新增
  	 */
	c_SHXYBM("C_SHXYBM"),
	/**
  	 * 项目企业注册地
  	 * add by jiaxinxiao
  	 * STORY #78461 【上海银行】与私募产品运行检测表相关的项目字段新增
  	 */
	c_QYZCD("C_QYZCD"),
	/**
  	 * 投资行业
  	 * add by jiaxinxiao
  	 * STORY #78461 【上海银行】与私募产品运行检测表相关的项目字段新增
  	 */
	c_TZHY("C_TZHY"),
	/**
  	 * 投资阶段
  	 * add by jiaxinxiao
  	 * STORY #78461 【上海银行】与私募产品运行检测表相关的项目字段新增
  	 */
	c_TZJD("C_TZJD"),
	/**
	 * SM代码
	 * wulongxing 20191023 STORY81024证券基本信息界面新增字段TisCode
	 */
	c_SM_CODE("c_SM_CODE"),
	
	/**
	 * Ticker码
	 * weijingjing 20200623 STORY #90922 华夏基金-估值表要展示彭博ticker码
	 */
	c_TICKER_CODE("c_TICKER_CODE"),
	
	/**
	 * add by dingshalu 20200320 STORY #84448 华夏基金-QD税收矩阵设置
	 * 注册地国家
	 */
	c_ZCDGJ("C_ZCDGJ"),
	
	/**
	 * 发行所在国
	 * add by fuzhendong 20201128  STORY #97105 新华资产-彭博证券基本信息文件，增加四列信息，估值报表系统需要使用，协助更新此文件接口
	 */
	c_FXSZG("C_FXSZG"),
	
	/**
	 * 发行人所在国
	 * add by fuzhendong 20201128  STORY #97105 新华资产-彭博证券基本信息文件，增加四列信息，估值报表系统需要使用，协助更新此文件接口
	 */
	c_FXRSZG("C_FXRSZG"),
	
	/**
	 * GICS分类
	 * add by fuzhendong 20201128  STORY #97105 新华资产-彭博证券基本信息文件，增加四列信息，估值报表系统需要使用，协助更新此文件接口
	 */
	c_GICS("C_GICS"),
	
	/**
	 * add by dingshalu 20200320 STORY #84448 华夏基金-QD税收矩阵设置
	 * 交易所所在国
	 */
	c_JYSSZG("C_JYSSZG"),
	
	/**
	 * * STORY #68358
	 * 明细市场
	 */
	c_MKT_DETAIL("C_MKT_DETAIL"),
	
	/**
	 * 英文名称
	 */
	c_SEC_NAME_EN("C_SEC_NAME_EN"),
	
	/**
	 * 含权明细
	 */
	c_DV_RIGHTMX("C_DV_RIGHTMX"),
	
	/* STORY #90124 社保基金新增通知存款核算需求 */
	/**
	 * 转存方式
	 */
	c_ZCFS("C_ZCFS"),
	
	/**
	 * 转存频率
	 */
	c_ZCPL("C_ZCPL"),
	
//	/**
//	 * 转存日期
//	 */
//	c_ZCRQ("C_ZCRQ"),
//	
//	/**
//	 * 转存日期类型
//	 */
//	c_ZCRQLX("C_ZCRQLX"),
	
    /**
     * STORY #92285 华夏基金-彭博接口的证券代码取值逻辑变更-证券基本信息 
     * 彭博内码
     */
    c_PBSEC_CODE("C_PBSEC_CODE"),
    /**
     * add by zhoushuhang 20210115 STORY96254华夏基金QD-彭博证券基本信息接口，增加直存“基金管理人”和“基金系列名称”的逻辑
	 * 基金管理人
	 */
    c_MANAGER_NAME("C_MANAGER_NAME"),
	
    /**
     * add by zhoushuhang 20210115 STORY96254华夏基金QD-彭博证券基本信息接口，增加直存“基金管理人”和“基金系列名称”的逻辑
	 * 基金系列名称
	 */
    c_FUND_NAME("C_FUND_NAME"),
    
    /**
	 * 投资收益者账户
	 * STORY #76847 【华宝基金】511990 SH华宝添益红利投资特殊处理 
	 */
	c_INVEST_ACCOUNT("C_INVEST_ACCOUNT"),
	
	/**
	 * 理财付息周期设置  add by gh 20161021 BUG #140945 
	 */
    /**
	 * STORY #98154 AMS国寿资产V4.5-新增永续债提醒功能，若买入时该券是永续债，则给与提醒
	 * 清偿顺序
	 */
	c_PAY_OFF_ORDER("C_PAY_OFF_ORDER"),
	
	/**
	 * STORY #98178 【估值核算】【国寿资产】【V1.300.7.0.20200831.1103】-O32红利投资接口增加控制是否包含节假日收益参数
	 * 含节假日收益
	 */
	c_DV_IS_JJRSY("C_DV_IS_JJRSY"),
	
	/**
	 * STORY #105860 【招银理财】非标资产逾期业务
	 * addby mxlee 2021-07-15
	 * 宽限期
	 */
	n_GRACE_PERIOD("N_GRACE_PERIOD"),
	
	/**
	 * STORY #105860 【招银理财】非标资产逾期业务
	 * addby mxlee 2021-07-15
	 * 宽限期计息规则
	 */
	c_GRACE_PERIOD_RULE("C_GRACE_PERIOD_RULE"),
	
	/**
	 * 节假日群
	 * huangkaixuan 20210727
	 * STORY109057博时基金-公募2.5已实现-存放业务品种信息节假日群的选择
	 */
	c_HDAY_CODE_CK("C_HDAY_CODE_CK"),
	
	/**
	 * STORY #108912 【大家资产】估值核算20210723---证券基本信息中新增融资人，担保人字段
	 * 担保人
	 */
	c_DV_DBR("C_DV_DBR"),
	
	/**
	 * STORY #108912 【大家资产】估值核算20210723---证券基本信息中新增融资人，担保人字段
	 * 融资人
	 */
	c_DV_RZR("C_DV_RZR"),
	
	secBaseLcfxzq("");
	
	private String value ;

	private SecBaseColumnName(String value){
	this.value = value;
	}

	public String toString(){
	return this.value.toString();
	}

}
