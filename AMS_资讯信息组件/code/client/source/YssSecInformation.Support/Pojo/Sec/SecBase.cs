﻿using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Interface;
using FAST.Common.Service.Pojo;
using Newtonsoft.Json;
using YssSecInformation.Support.Sv.Pojo;

namespace YssSecInformation.Support.Pojo.Sec
{
    /// <summary>
    /// ///  chenbo
    /// 2017-06-22
    /// #42948 资讯信息管理组件化拆分
    /// ----------------------------------
    /// 证券基本信息
    /// </summary>
    public class SecBase : EnclosurePojo, I_SecBase
    {
        /// <summary>
        /// 证券代码
        /// </summary>
        private string c_sec_code = "";

        /// <summary>
        /// 证券名称
        /// </summary>
        private string c_sec_name = "";

        /// <summary>
        /// 证券上市代码
        /// </summary>
        private string c_sec_mkt_code = "";

        /// <summary>
        /// 证券ISIN代码
        /// </summary>
        private string c_sec_isin_code = "";

        /// <summary>
        /// 证券分销代码
        /// </summary>
        private string c_fx_code = " ";

        /// <summary>
        /// 交易市场代码
        /// </summary>
        private string c_mkt_code = "";

        /// <summary>
        /// 证券品种代码
        /// </summary>
        private string c_sec_var_code = "";

        /// <summary>
        ///币种代码
        /// </summary>
        private string c_dc_code = "";

        /// <summary>
        ///平仓盈亏币种代码
        /// </summary>
        private string c_PCYK_CURY = "";

        /// <summary>
        ///报价因子
        /// </summary>
        private string n_price_fcr = "0";

        /// <summary>
        /// 发行机构
        /// </summary>
        private string c_fxjg_code = "";

        /// <summary>
        /// 标的证券
        /// </summary>
        private string c_sec_code_trg = "";

        /// <summary>
        ///每手数量
        /// </summary>
        private string n_amount_hd = "0";

        /// <summary>
        ///发行面值
        /// </summary>
        private string n_fv_issue = "0";

        /// <summary>
        /// 市日期
        /// </summary>
        private DateTime d_to_list;

        /// <summary>
        ///退市日期
        /// </summary>
        private DateTime d_off_list;

        /// <summary>
        ///品种期限
        /// </summary>
        private string c_dv_var_dur = "";

        /// <summary>
        ///报价方式
        /// </summary>
        private string c_dv_qut_mod = "";

        /// <summary>
        /// 税率
        /// </summary>
        private string n_rate = "0";

        /// <summary>
        /// 票面利率
        /// </summary>
        private string n_fv_ir = "0";

        /// <summary>
        ///发行价格
        /// </summary>
        private string n_price_issue = "0";

        /// <summary>
        ///计息方式
        /// </summary>
        private string c_dv_ai_mod = "";

        /// <summary>
        ///付息公式
        /// </summary>
        private string c_dv_pi_mod = "";

        /// <summary>
        ///计息起始日
        /// </summary>
        private string d_ai_begin = "1900-01-01";

        /// <summary>
        ///首期起息日期  BUG #201919 富国基金-优先股历史付息生成错误/更改默认值为1990-1-1
        /// </summary>
        private string d_SQAI_BEGIN = "1900-01-01";

        /// <summary>
        /// 计息截止日
        /// </summary>
        private string d_ai_end = "9998-12-31";

        /// <summary>
        ///  //描述
        /// </summary>
        private string c_desc = "";

        /// <summary>
        ///计息公式
        /// </summary>
        private string c_DV_AI_EXPR = "";

        /// <summary>
        /// 机构代码
        /// </summary>
        private string c_ORG_CODE = "";

        /// <summary>
        /// 出票人
        /// </summary>
        private string c_org_name = "";

        /// <summary>
        /// 承兑人行名
        /// </summary>
        private string c_open_acc_name = "";

        /// <summary>
        /// 承兑人行号
        /// </summary>
        private string c_open_acc_code = "";

        /// <summary>
        /// 贴现行行名
        /// </summary>
        private string c_TXH_NAME = "";

        /// <summary>
        /// 贴现行行号
        /// </summary>
        private string c_TXH_CODE = "";

        /// <summary>
        /// 大额支付号
        /// </summary>
        private string c_sys_code = "";

        /// <summary>
        /// 证券属性
        /// </summary>
        private string c_Da_Code = "";

        /// <summary>
        /// 发行类型
        /// add by tangshifeng 2013-07-03
        /// </summary>
        private string c_dv_issue = "";

        /// <summary>
        /// 担保方式
        /// add by tangshifeng 2013-07-03
        /// </summary>
        private string c_dv_assure = "";

        /// <summary>
        /// 信用评级
        /// 20130619czbank
        /// zgy
        /// </summary>
        private string c_credit_rating = "";

        /// <summary>
        /// 证券中文名称
        /// add by fjl 20140315 目前用在 期权基本信息
        /// </summary>
        private string c_SEC_NAME_CN = "";

        /// <summary>
        /// 税率1 By Jinghehe 2014-7-31
        /// </summary>
        private string n_ratio = "0";

        /// <summary>
        /// 到期日 By Jinghehe 2014-7-31 目前用在 期权基本信息
        /// </summary>
        private string d_end = "9998-12-31";

        /// <summary>
        /// ETF类型
        /// </summary>
        private string c_ETF_TYPE = "";

        /// <summary>
        /// 结算机构
        /// </summary>
        private string c_SETT_ORG = "";

        /// <summary>
        /// 含权标志
        /// </summary>
        private string c_DV_RIGHT = "";

        /// <summary>
        /// 融资融券标示
        /// </summary>
        private string c_RZRQ_MARK = "";

        /// <summary>
        /// 利率类型  2015-11-3 addby wangsongmao
        /// STORY24925深国投：非标资产交易业务接口改造需求
        /// </summary>
        private string c_DV_RTA = "";

        /// <summary>
        /// 利差2015-11-3 addby wangsongmao
        /// </summary>
        private string n_SPREAD = "0";

        /// <summary>
        /// 利率代码  2015-11-3 addby wangsongmao
        /// </summary>
        private string c_EXPR_CODE = "";

        /// <summary>
        ///  比例系数  2015-11-3 addby wangsongmao
        /// </summary>
        private string n_BLXS = "0";

        /// <summary>
        /// 检查间隔 2015-11-3 addby wangsongmao
        /// </summary>
        private string c_JCJG = "";

        /// <summary>
        /// 利率上限  2015-11-3 addby wangsongmao
        /// </summary>
        private string n_UPPER_LIMIT = "0";

        /// <summary>
        /// 利率下限  2015-11-3 addby wangsongmao
        /// </summary>
        private string n_LOWER_LIMIT = "0";

        /// <summary>
        /// 检查间隔周期
        /// </summary>
        private string c_INTERVAL_TIME = "";

        /// <summary>
        /// 周期天数
        /// </summary>
        private string c_INTERVAL_DAY = "";

        /// <summary>
        /// 组合代码 liuxiang 2016-5-9 STORY #25484 YSS:债券品种信息和理财品种信息增加组合私有化功能
        /// </summary>
        private string c_PORT_CODE = "";

        /// <summary>
        /// STORY #39209 属性：转换起始日
        /// </summary>
        private string d_TRA_BEGIN = "";

        /// <summary>
        /// STORY #39209 属性：转换截止日
        /// </summary>
        private string d_TRA_END = "";

        /// <summary>
        /// STORY #47088 债券基本信息，增加发行机构类型字段，区分金融机构和非金融机构
        /// edit by yuanyafeng 20171025
        /// 主体性质
        /// </summary>
        private string c_MAIN_PROP = "";

        /// <summary>
        /// STORY #64802 【招商基金】增加fxzt字段的读入及增加债券基本信息该字段的展示
        /// 主体名称
        /// </summary>
        private string c_MAIN_NAME = "";

        /// <summary>
        /// add by zhd 2016-09-07
        /// STORY34120【南方基金】【紧急】现金存放业务，流水加入标识，4级科目不明确到品种，而是按照标识来区分
        /// 实际所属证券
        /// </summary>
        private string c_SJSSZQ = "";

        /// <summary>
        /// 计提基准_标准资产
        /// </summary>
        private string c_JTJZ4STD_SETT = "";

        /// <summary>
        /// 计提基准_非标准资产
        /// </summary>
        private string c_JTJZ4NOTSTD_SETT = "";

        /// <summary>
        /// 银行总行
        /// </summary>
        private string c_BANK_HEAD = "";

        /// <summary>
        /// 银行支行
        /// </summary>
        private string c_BANK_BRANCH = "";

        /// <summary>
        /// add by wangpeixu 2017-06-06
        /// STORY37961财税140号文件，针对资管增值税系统改造需求
        /// 保本类型
        /// </summary>
        private string c_GUAR_TYPE = "";

        /// <summary>
        /// add by wagnpeixu 2017-06-06
        /// STORY37961财税140号文件，针对资管增值税系统改造需求
        /// 金融商品
        /// </summary>
        private string c_FINA_COMM = "";

        /// <summary>
        /// STORY #41687 含付息日当天利息，默认“否”
        /// </summary>
        private string n_TODAY_INTEREST = "0";

        /// <summary>
        /// STORY #40480 托管人代码
        /// </summary>
        private string c_TGR_CODE = "";

        /// <summary>
        ///  STORY #40480 托管人名称
        /// </summary>
        private string c_TGR_NAME = "";

        /// <summary>
        ///  STORY #40480 销售服务费率
        /// </summary>
        private string n_XSFWFL = "0";

        /// <summary>
        ///  STORY #74585 【华宝基金】——标准证券界面——理财产品信息中增加两个字段（合并需求）
        /// </summary>
        private string n_GLF_RATE = "0";

        /// <summary>
        ///  STORY #74585 【华宝基金】——标准证券界面——理财产品信息中增加两个字段（合并需求）
        /// </summary>
        private string n_TGF_RATE = "0";

        /// <summary>
        /// 证券品种代码分级
        /// </summary>
        private string c_sec_var_cls = "";

        /// <summary>
        /// 份额上限
        /// </summary>
        private string n_FESX = "0";

        /// <summary>
        /// 份额下限
        /// </summary>
        private string n_FEXX = "0";

        ///<summary>
        ///add zhangmingbo story 45603 [易方达QDII]证券信息数据导入清算 2017 9 16
        ///公司英文名称
        ///</summary>
        private string c_ISSUERS_CODE = "";

        ///<summary>
        ///add zhangmingbo story 45603 [易方达QDII]证券信息数据导入清算 2017 9 16
        ///公司英文名称
        ///</summary>
        private string c_ISSUERS_NAME = "";

        /// <summary>
        ///  STORY #47986  分类标志
        /// </summary>
        private string c_LC_SEC_TAG = "";

        /// <summary>
        /// 购回日期类型
        /// add by shijian 2017-11-09 STORY #47805 嘉实基金QD—回购业务需求
        /// </summary>
        private string c_REPO_TYPE = "";

        /// <summary>
        /// 回购收益计算起始日期
        /// add by shijian 2017-11-09 STORY #47805 嘉实基金QD—回购业务需求
        /// </summary>
        private string c_REPO_INCOME_FROM = "";

        /// <summary>
        /// 回购收益计算结束日期
        /// add by shijian 2017-11-09 STORY #47805 嘉实基金QD—回购业务需求
        /// </summary>
        private string c_REPO_INCOME_TO = "";

        /// <summary>
        /// 金融工具
        /// added by HeLiang 2017-11-14 STORY #43829 新企业会计准则（Ifrs9）解决方案
        /// </summary>
        private string c_FINANCE_TOOL = "";

        /// <summary>
        /// SEDOL码
        /// STORY #50934 [易方达基金] [QDII] 新增接口更新A股的ISIN和英文信息 add by qiguanzhi 20180119
        /// </summary>
        private string c_SEDOLCODE = "";

        /// <summary>
        /// 机构类型
        /// STORY #56789 【易方达子公司】【非标】【紧急】增加理财品种的需求
        /// </summary>
        private string c_ORG_TYPE = "";

        /// <summary>
        /// 集资方式
        /// add By HeMing  20180628
        /// STORY #53605 【鹏华基金】债券品种信息投资分类增加“公募”“私募”用以区分不同的估值方案
        /// </summary>
        private string c_JZFS_CODE = "";

        /// <summary>
        /// add by cxj 2017-07-05
        /// STORY32772【南方基金】【V2.5需求】南方基金：协定存款付息方式增加
        /// 付息日期类型
        /// </summary>
        private string c_PAY_DATE_TYPE = "";

        /// <summary>
        /// add by cxj 2017-07-05
        /// STORY32772【南方基金】【V2.5需求】南方基金：协定存款付息方式增加
        /// 付息天数
        /// </summary>
        private int n_PAY_DATE = 0;

        /// <summary>
        /// add by cxj 2017-07-05
        /// STORY32772【南方基金】【V2.5需求】南方基金：协定存款付息方式增加
        /// 基准日期类型
        /// </summary>
        private string c_BASE_DATE_TYPE = "";

        /// <summary>
        /// add by cxj 2017-07-05
        /// STORY32772【南方基金】【V2.5需求】南方基金：协定存款付息方式增加
        /// 基准天数
        /// </summary>
        private int n_BASE_DATE = 0;

        /// <summary>
        /// 省内资产
        /// STORY #59902 改造存放品种信息界面，新增存放品种 add by wenxiyang 20180814
        /// </summary>
        private string c_SNZC = "";

        /// <summary>
        /// 控制程度
        /// STORY #62079 人保资产-支持人保资产新科目体系核算
        /// </summary>
        private string c_KZCD_GQ = "";

        /// <summary>
        /// STORY #63023 【中金公司】彭博证券信息，彭博债券信息接口，公共信息处理时，提示是否覆盖手工调整过的信息
        /// 数据来源
        /// </summary>
        private string c_DATA_IDF = "";

        /// <summary>
        /// 境外交易市场
        /// STORY #51261 彭博证券信息读入境外市场
        /// </summary>
        private string c_MKT_CODE_JC = "";

        /// <summary>
        /// add by ZhuJinYang 20181127
        /// STORY65338工银瑞信基金-非标产品需要控制是否生成对价派息信息（紧急）
        /// 对价派息启用
        /// </summary>
        private string c_DJPX_OPEN = "";

        /// <summary>
        /// STORY #66077 人保资产-支持银行间债券A/A特殊计息
        /// 计息天数
        /// </summary>
        private int n_JXTS = 0;

        /// <summary>
        /// 计息方式
        /// added by hezhigang 20181212 STORY64492【易方达香港】【一期】境外回购支持按年利率计算日收益进行计提
        /// </summary>
        private string c_DV_JXFS = "";

        /// <summary>
        /// 源制作时间 STORY #48553 webservice中开放申赎等业务，流水号取值逻辑调整
        /// </summary>
        private string c_UPDATE_TIMEWEB = null;

        /// <summary>
        /// 系统来源.
        /// </summary>
        private string c_SYSOURCE = null;

        /// <summary>
        ///  STORY #63605 富国基金-【公募】存放业务无法生成正确得付息信息和支取流水
        ///  付息日计算
        /// </summary>
        private string c_PAY_CALCULATE = "";

        /// <summary>
        /// STORY52063仅在大宗协议平台上市的债券，收盘价不作为估值行情处理
        /// 交易平台
        /// </summary>
        private string c_DV_PLAT = "";

        /// <summary>
        /// 债券上市日期
        /// </summary>
        private string d_TO_LIST_ZQ = "";

        /// <summary>
        /// STORY #75904 第一批：QDII-【博时基金】抗通胀债券需求
        /// 指数品种
        /// </summary>
        private string c_INDEX_TYPE = "";

        /// <summary>
        /// story 68666
        /// 自定义付息日期
        /// </summary>
        private PayDay payDays = null;

        /// <summary>
        /// 是否境外投资
        /// add by jiaxinxiao 2019-9-11
        /// STORY #78461 【上海银行】与私募产品运行检测表相关的项目字段新增
        /// </summary>
        private string c_JWTZ = "";

        /// <summary>
        /// 项目企业股票代码
        /// add by jiaxinxiao 2019-9-11
        /// STORY #78461 【上海银行】与私募产品运行检测表相关的项目字段新增
        /// </summary>
        private string c_QYGPDM = "";

        /// <summary>
        /// 项目企业情况成立日期
        /// add by jiaxinxiao 2019-9-11
        /// STORY #78461 【上海银行】与私募产品运行检测表相关的项目字段新增
        /// </summary>
        private string d_QY_DATE = "";

        /// <summary>
        /// 项目企业统一社会信用编码
        /// add by jiaxinxiao 2019-9-11
        /// STORY #78461 【上海银行】与私募产品运行检测表相关的项目字段新增
        /// </summary>
        private string c_SHXYBM = "";

        /// <summary>
        /// 项目企业注册地
        /// add by jiaxinxiao 2019-9-11
        /// STORY #78461 【上海银行】与私募产品运行检测表相关的项目字段新增
        /// </summary>
        private string c_QYZCD = "";

        /// <summary>
        /// 投资行业
        /// add by jiaxinxiao 2019-9-11
        /// STORY #78461 【上海银行】与私募产品运行检测表相关的项目字段新增
        /// </summary>
        private string c_TZHY = "";

        /// <summary>
        /// 投资阶段
        /// add by jiaxinxiao 2019-9-11
        /// STORY #78461 【上海银行】与私募产品运行检测表相关的项目字段新增
        /// </summary>
        private string c_TZJD = "";

        /// <summary>
        /// SM代码
        /// wulongxing 20191023 STORY81024证券基本信息界面新增字段TisCode
        /// </summary>
        private string c_SM_CODE = "";

        /// <summary>
        /// ticker码
        /// wulongxing 20191023 STORY81024证券基本信息界面新增字段TisCode
        /// </summary>
        private string c_TICKER_CODE = "";

        /// <summary>
        /// STORY #68358 属性：明细市场
        /// </summary>
        private string c_MKT_DETAIL = "";

        /// <summary>
        /// 属性：票面日利率
        /// </summary>
        private string n_FV_DAY_IR = "0";

        /// <summary>
        /// add by dingshalu 20200320 STORY #84448 华夏基金-QD税收矩阵设置
        /// 注册地国家
        /// </summary>
        private string c_ZCDGJ = "";

        /// <summary>
        /// add by dingshalu 20200320 STORY #84448 华夏基金-QD税收矩阵设置
        /// 交易所所在国
        /// </summary>
        private string c_JYSSZG = "";

        /// <summary>
        /// 英文名称
        /// </summary>
        private string c_SEC_NAME_EN = "";

        /// <summary>
        /// 含权明细
        /// </summary>
        private string c_DV_RIGHTMX = "";

        /// <summary>
        /// 转存方式
        /// STORY #90124 社保基金新增通知存款核算需求
        /// </summary>
        private string c_ZCFS = "";

        /// <summary>
        /// 转存频率
        /// STORY #90124 社保基金新增通知存款核算需求
        /// </summary>
        private string c_ZCPL = "";

        /// <summary>
        /// 彭博代码；
        /// STORY #95445 华夏基金QD-综合证券信息界面增加BBGID查询功能
        /// </summary>
        private string c_PBSEC_CODE = "";

        /// <summary>
        /// 转存日期
        /// STORY #90124 社保基金新增通知存款核算需求
        /// </summary>
        ////private string c_ZCRQ = "";

        /// <summary>
        /// 转存日期类型
        /// STORY #90124 社保基金新增通知存款核算需求
        /// </summary>
        ////private string c_ZCRQLX = "";

        /// <summary>
        /// 理财付息周期设置  add by gh 20161021 BUG #140945 
        /// </summary>
        private List<SecBaseLcfxzq> secBaseLcfxzq = null;

        /// <summary>
        /// 基金管理人
        /// add by zhoushuhang 20210115 STORY96254华夏基金QD-彭博证券基本信息接口，增加直存“基金管理人”和“基金系列名称”的逻辑
        /// </summary>
        private string c_MANAGER_NAME = "";

        /// <summary>
        /// 基金系列名称
        /// add by zhoushuhang 20210115 STORY96254华夏基金QD-彭博证券基本信息接口，增加直存“基金管理人”和“基金系列名称”的逻辑
        /// </summary>
        private string c_FUND_NAME = "";

        /// <summary>
        /// 投资者收益账户
        /// STORY #76847 【华宝基金】511990 SH华宝添益红利投资特殊处理 
        /// </summary>
        private string c_INVEST_ACCOUNT = "";

        /// <summary>
        /// 清偿顺序
        /// STORY #98154 AMS国寿资产V4.5-新增永续债提醒功能，若买入时该券是永续债，则给与提醒
        /// </summary>
        private string c_PAY_OFF_ORDER = "";


        /// <summary>
        /// 含节假日收益
        /// STORY #98178 【估值核算】【国寿资产】【V1.300.7.0.20200831.1103】-O32红利投资接口增加控制是否包含节假日收益参数
        /// </summary>
        private string c_DV_IS_JJRSY = "";

        /// <summary>
        /// 正股转换代码
        /// </summary>
        private string c_ZGZH_CODE = "";

        /// <summary>
        /// STORY #97105 新华资产-彭博证券基本信息文件，增加四列信息，估值报表系统需要使用，协助更新此文件接口
        /// 发行所在国
        /// </summary>
        private string c_FXSZG = "";

        /// <summary>
        /// STORY #97105 新华资产-彭博证券基本信息文件，增加四列信息，估值报表系统需要使用，协助更新此文件接口
        /// 发行人所在国
        /// </summary>
        private string c_FXRSZG = "";

        /// <summary>
        /// STORY #97105 新华资产-彭博证券基本信息文件，增加四列信息，估值报表系统需要使用，协助更新此文件接口
        /// GICS分类
        /// </summary>
        private string c_GICS = "";

        /// <summary>
        /// add by mxlee 2021-07-15
        /// STORY #105860 【招银理财】非标资产逾期业务
        /// 宽限期
        /// </summary>
        private string n_GRACE_PERIOD = "";

        /// <summary>
        /// add by mxlee 2021-07-15
        /// STORY #105860 【招银理财】非标资产逾期业务
        /// 宽限期计息规则
        /// </summary>
        private string c_GRACE_PERIOD_RULE = "";

        /// <summary>
        /// 黄凯旋 2021-07-27
        /// STORY109057博时基金-公募2.5已实现-存放业务品种信息节假日群的选择
        /// 节假日群
        /// </summary>
        private string c_HDAY_CODE_CK = " ";

        /// <summary>
        /// STORY #108912 【大家资产】估值核算20210723---证券基本信息中新增融资人，担保人字段
        /// </summary>
        private string c_DV_DBR = " ";

        /// <summary>
        /// STORY #108912 【大家资产】估值核算20210723---证券基本信息中新增融资人，担保人字段
        /// </summary>
        private string c_DV_RZR = " ";

        /// <summary>
        /// 属性: 融资人 
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_RZR")]
        public string C_DV_RZR
        {
            set { c_DV_RZR = value; }

            get { return c_DV_RZR; }
        }

        /// <summary>
        /// 属性: 担保人 
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_DBR")]
        public string C_DV_DBR
        {
            set { c_DV_DBR = value; }

            get { return c_DV_DBR; }
        }

        /// <summary>
        /// 属性: 节假日代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_HDAY_CODE_CK")]
        public string C_HDAY_CODE_CK
        {
            set { c_HDAY_CODE_CK = value; }

            get { return c_HDAY_CODE_CK; }
        }

        /// <summary>
        /// 发行所在国
        /// </summary>
        [JsonProperty(PropertyName = "c_FXSZG")]
        public string C_FXSZG
        {
            set { c_FXSZG = value; }

            get { return c_FXSZG; }
        }

        /// <summary>
        /// 发行人所在国
        /// </summary>
        [JsonProperty(PropertyName = "c_FXRSZG")]
        public string C_FXRSZG
        {
            set { c_FXRSZG = value; }

            get { return c_FXRSZG; }
        }

        /// <summary>
        /// GICS分类
        /// </summary>
        [JsonProperty(PropertyName = "c_GICS")]
        public string C_GICS
        {
            set { c_GICS = value; }

            get { return c_GICS; }
        }

        /// <summary>
        /// 含节假日收益
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_IS_JJRSY")]
        public string C_DV_IS_JJRSY
        {
            get { return c_DV_IS_JJRSY; }
            set { c_DV_IS_JJRSY = value; }
        }


        /// <summary>
        /// 属性: 理财付息周期设置  add by gh 20161021 BUG #140945 
        /// </summary>
        [JsonProperty(PropertyName = "secBaseLcfxzq")]
        public List<SecBaseLcfxzq> SecBaseLcfxzq
        {
            set { secBaseLcfxzq = value; }

            get { return secBaseLcfxzq; }
        }

        /// <summary>
        /// 清偿顺序
        /// </summary>
        [JsonProperty(PropertyName = "c_PAY_OFF_ORDER")]
        public string C_PAY_OFF_ORDER
        {
            get { return c_PAY_OFF_ORDER; }
            set { c_PAY_OFF_ORDER = value; }
        }

        /// <summary>
        /// 票面日利率
        /// </summary>
        [JsonProperty(PropertyName = "n_FV_DAY_IR")]
        public string N_FV_DAY_IR
        {
            get { return n_FV_DAY_IR; }
            set { n_FV_DAY_IR = value; }
        }

        /// <summary>
        /// SM代码
        /// </summary>
        [JsonProperty(PropertyName = "c_SM_CODE")]
        public string C_SM_CODE
        {
            get { return c_SM_CODE; }
            set { c_SM_CODE = value; }
        }

        /// <summary>
        /// ticker码
        /// </summary>
        [JsonProperty(PropertyName = "c_TICKER_CODE")]
        public string C_TICKER_CODE
        {
            get { return c_TICKER_CODE; }
            set { c_TICKER_CODE = value; }
        }

        /// <summary>
        /// 自定义付息日期
        /// </summary>
        [JsonProperty(PropertyName = "payDays")]
        public PayDay PayDays
        {
            get { return payDays; }
            set { payDays = value; }
        }

        /// <summary>
        /// STORY #75904 第一批：QDII-【博时基金】抗通胀债券需求
        /// 指数品种
        /// </summary>
        [JsonProperty(PropertyName = "c_INDEX_TYPE")]
        public string C_INDEX_TYPE
        {
            get { return c_INDEX_TYPE; }
            set { c_INDEX_TYPE = value; }
        }

        /// <summary>
        /// 交易平台
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_PLAT")]
        public string C_DV_PLAT
        {
            get { return c_DV_PLAT; }
            set { c_DV_PLAT = value; }
        }

        /// <summary>
        /// 属性: 系统来源.
        /// </summary>
        [JsonProperty(PropertyName = "c_SYSOURCE")]
        public string C_SYSOURCE
        {
            set { c_SYSOURCE = value; }

            get { return c_SYSOURCE; }
        }

        /// <summary>
        /// 属性: 源制作时间.
        /// </summary>
        [JsonProperty(PropertyName = "c_UPDATE_TIMEWEB")]
        public string C_UPDATE_TIMEWEB
        {
            set { c_UPDATE_TIMEWEB = value; }

            get { return c_UPDATE_TIMEWEB; }
        }

        /// <summary>
        /// 计息天数
        /// </summary>
        [JsonProperty(PropertyName = "n_JXTS")]
        public int N_JXTS
        {
            set { n_JXTS = value; }

            get { return n_JXTS; }
        }

        /// <summary>
        /// 对价派息启用
        /// </summary>
        [JsonProperty(PropertyName = "c_DJPX_OPEN")]
        public string C_DJPX_OPEN
        {
            set { c_DJPX_OPEN = value; }

            get { return c_DJPX_OPEN; }
        }

        /// <summary>
        /// 数据来源
        /// </summary>
        [JsonProperty(PropertyName = "c_DATA_IDF")]
        public string C_DATA_IDF
        {
            set { c_DATA_IDF = value; }

            get { return c_DATA_IDF; }
        }

        /// <summary>
        /// 控制程度
        /// STORY #62079 人保资产-支持人保资产新科目体系核算
        /// </summary>
        [JsonProperty(PropertyName = "c_KZCD_GQ")]
        public string C_KZCD_GQ
        {
            set { c_KZCD_GQ = value; }

            get { return c_KZCD_GQ; }
        }

        /// <summary>
        /// 省内资产
        /// STORY #59902 改造存放品种信息界面，新增存放品种 add by wenxiyang 20180814
        /// </summary>
        [JsonProperty(PropertyName = "c_SNZC")]
        public string C_SNZC
        {
            set { c_SNZC = value; }

            get { return c_SNZC; }
        }

        /// <summary>
        /// 付息日计算
        /// </summary>
        [JsonProperty(PropertyName = "c_PAY_CALCULATE")]
        public string C_PAY_CALCULATE
        {
            get { return c_PAY_CALCULATE; }
            set { c_PAY_CALCULATE = value; }
        }

        /// <summary>
        /// STORY37137太平保险-理财品种信息增加付息日处理
        /// </summary>
        [JsonProperty(PropertyName = "d_SQAI_BEGIN")]
        public string D_SQAI_BEGIN
        {
            set { d_SQAI_BEGIN = value; }

            get { return d_SQAI_BEGIN; }
        }

        /// <summary>
        /// add by zhd 2016-09-07
        /// STORY34120【南方基金】【紧急】现金存放业务，流水加入标识，4级科目不明确到品种，而是按照标识来区分
        /// 实际所属证券
        /// </summary>
        [JsonProperty(PropertyName = "c_SJSSZQ")]
        public string C_SJSSZQ
        {
            set { c_SJSSZQ = value; }

            get { return c_SJSSZQ; }
        }

        /// <summary>
        ///利率类型
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_RTA")]
        public string C_DV_RTA
        {
            set { c_DV_RTA = value; }

            get { return c_DV_RTA; }
        }

        /// <summary>
        ///利差
        /// </summary>
        [JsonProperty(PropertyName = "n_SPREAD")]
        public string N_SPREAD
        {
            set { n_SPREAD = value; }

            get { return n_SPREAD; }
        }

        /// <summary>
        ///利率代码
        /// </summary>
        [JsonProperty(PropertyName = "c_EXPR_CODE")]
        public string C_EXPR_CODE
        {
            set { c_EXPR_CODE = value; }

            get { return c_EXPR_CODE; }
        }

        /// <summary>
        ///比例系数
        /// </summary>
        [JsonProperty(PropertyName = "n_BLXS")]
        public string N_BLXS
        {
            set { n_BLXS = value; }

            get { return n_BLXS; }
        }

        /// <summary>
        ///检查间隔
        /// </summary>
        [JsonProperty(PropertyName = "c_JCJG")]
        public string C_JCJG
        {
            set { c_JCJG = value; }

            get { return c_JCJG; }
        }

        /// <summary>
        ///利率上限
        /// </summary>
        [JsonProperty(PropertyName = "n_UPPER_LIMIT")]
        public string N_UPPER_LIMIT
        {
            set { n_UPPER_LIMIT = value; }

            get { return n_UPPER_LIMIT; }
        }

        /// <summary>
        ///利率下限
        /// </summary>
        [JsonProperty(PropertyName = "n_LOWER_LIMIT")]
        public string N_LOWER_LIMIT
        {
            set { n_LOWER_LIMIT = value; }

            get { return n_LOWER_LIMIT; }
        }

        /// <summary>
        ///周期天数
        /// </summary>
        [JsonProperty(PropertyName = "c_INTERVAL_DAY")]
        public string C_INTERVAL_DAY
        {
            set { c_INTERVAL_DAY = value; }

            get { return c_INTERVAL_DAY; }
        }

        /// <summary>
        ///周期
        /// </summary>
        [JsonProperty(PropertyName = "c_INTERVAL_TIME")]
        public string C_INTERVAL_TIME
        {
            set { c_INTERVAL_TIME = value; }

            get { return c_INTERVAL_TIME; }
        }

        /// <summary>
        /// ETF类型
        /// </summary>
        [JsonProperty(PropertyName = "c_ETF_TYPE")]
        public string C_ETF_TYPE
        {
            set { c_ETF_TYPE = value; }

            get { return c_ETF_TYPE; }
        }

        /// <summary>
        /// 属性: 税率1
        /// </summary>
        [JsonProperty(PropertyName = "n_RATIO")]
        public string N_RATIO
        {
            set { n_ratio = value; }

            get { return n_ratio; }
        }

        /// <summary>
        /// 属性:  到期日
        /// </summary>
        [JsonProperty(PropertyName = "d_END")]
        public string D_END
        {
            set { d_end = value; }

            get { return d_end; }
        }

        /// <summary>
        /// 属性: 证券中文名称
        /// </summary>
        [JsonProperty(PropertyName = "c_SEC_NAME_CN")]
        public string C_SEC_NAME_CN
        {
            set { c_SEC_NAME_CN = value; }

            get { return c_SEC_NAME_CN; }
        }

        /// <summary>
        /// 属性: 证券代码
        /// </summary>
        [JsonProperty(PropertyName = "c_SEC_CODE")]
        public string C_SEC_CODE
        {
            set { c_sec_code = value; }

            get { return c_sec_code; }
        }

        /// <summary>
        /// 属性: 证券名称
        /// </summary>
        [JsonProperty(PropertyName = "c_SEC_NAME")]
        public string C_SEC_NAME
        {
            set { c_sec_name = value; }

            get { return c_sec_name; }
        }

        /// <summary>
        /// 证券名称拼音简拼-仅用于证券下拉框快速检索。
        /// 证券名称简拼，用于支持下拉框快速检索。STORY #37886 张绍林-20170207
        /// </summary>
        public string C_SEC_NAME_PY
        {
            get;
            set;
        }

        /// <summary>
        /// 属性: 证券上市代码
        /// </summary>
        [JsonProperty(PropertyName = "c_SEC_MKT_CODE")]
        public string C_SEC_MKT_CODE
        {
            set { c_sec_mkt_code = value; }

            get { return c_sec_mkt_code; }
        }

        /// <summary>
        /// 属性: 证券ISIN代码
        /// </summary>
        [JsonProperty(PropertyName = "c_SEC_ISIN_CODE")]
        public string C_SEC_ISIN_CODE
        {
            set { c_sec_isin_code = value; }

            get { return c_sec_isin_code; }
        }

        /// <summary>
        /// 属性: 证券分销代码
        /// </summary>
        [JsonProperty(PropertyName = "c_FX_CODE")]
        public string C_FX_CODE
        {
            set { c_fx_code = value; }

            get { return c_fx_code; }
        }

        /// <summary>
        /// 属性: 交易市场代码
        /// </summary>
        [JsonProperty(PropertyName = "c_MKT_CODE")]
        public string C_MKT_CODE
        {
            set { c_mkt_code = value; }

            get { return c_mkt_code; }
        }

        /// <summary>
        /// 属性: 证券品种代码
        /// </summary>
        [JsonProperty(PropertyName = "c_SEC_VAR_CODE")]
        public string C_SEC_VAR_CODE
        {
            set { c_sec_var_code = value; }

            get { return c_sec_var_code; }
        }

        /// <summary>
        /// 属性: 币种代码
        /// </summary>
        [JsonProperty(PropertyName = "c_DC_CODE")]
        public string C_DC_CODE
        {
            set { c_dc_code = value; }

            get { return c_dc_code; }
        }

        /// <summary>
        /// 属性: 平仓盈亏币种代码
        /// </summary>
        [JsonProperty(PropertyName = "c_PCYK_CURY")]
        public string C_PCYK_CURY
        {
            set { c_PCYK_CURY = value; }

            get { return c_PCYK_CURY; }
        }

        /// <summary>
        /// 属性: 报价因子
        /// </summary>
        [JsonProperty(PropertyName = "n_PRICE_FCR")]
        public string N_PRICE_FCR
        {
            set { n_price_fcr = value; }

            get { return n_price_fcr; }
        }

        /// <summary>
        /// 属性: 发行机构
        /// </summary>
        [JsonProperty(PropertyName = "c_FXJG_CODE")]
        public string C_FXJG_CODE
        {
            set { c_fxjg_code = value; }
            get { return c_fxjg_code; }
        }

        /// <summary>
        /// 属性: 标的证券
        /// </summary>
        [JsonProperty(PropertyName = "c_SEC_CODE_TRG")]
        public string C_SEC_CODE_TRG
        {
            set { c_sec_code_trg = value; }

            get { return c_sec_code_trg; }
        }

        /// <summary>
        /// 属性: 每手数量
        /// </summary>
        [JsonProperty(PropertyName = "n_AMOUNT_HD")]
        public string N_AMOUNT_HD
        {
            set { n_amount_hd = value; }

            get { return n_amount_hd; }
        }

        /// <summary>
        /// 属性: 发行面值
        /// </summary>
        [JsonProperty(PropertyName = "n_FV_ISSUE")]
        public string N_FV_ISSUE
        {
            set { n_fv_issue = value; }

            get { return n_fv_issue; }
        }

        /// <summary>
        /// 属性: 市日期
        /// </summary>
        [JsonProperty(PropertyName = "d_TO_LIST")]
        public DateTime D_TO_LIST
        {
            set { d_to_list = value; }

            get { return d_to_list; }
        }

        /// <summary>
        /// 属性: 退市日期
        /// </summary>
        [JsonProperty(PropertyName = "d_OFF_LIST")]
        public DateTime D_OFF_LIST
        {
            set { d_off_list = value; }

            get { return d_off_list; }
        }

        /// <summary>
        /// 属性: 品种期限
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_VAR_DUR")]
        public string C_DV_VAR_DUR
        {
            set { c_dv_var_dur = value; }

            get { return c_dv_var_dur; }
        }

        /// <summary>
        /// 属性: 报价方式
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_QUT_MOD")]
        public string C_DV_QUT_MOD
        {
            set { c_dv_qut_mod = value; }

            get { return c_dv_qut_mod; }
        }

        /// <summary>
        /// 属性: 税率
        /// </summary>
        [JsonProperty(PropertyName = "n_RATE")]
        public string N_RATE
        {
            set { n_rate = value; }

            get { return n_rate; }
        }

        /// <summary>
        /// 属性: 票面利率
        /// </summary>
        [JsonProperty(PropertyName = "n_FV_IR")]
        public string N_FV_IR
        {
            set { n_fv_ir = value; }

            get { return n_fv_ir; }
        }

        /// <summary>
        /// 属性: 发行价格
        /// </summary>
        [JsonProperty(PropertyName = "n_PRICE_ISSUE")]
        public string N_PRICE_ISSUE
        {
            set { n_price_issue = value; }

            get { return n_price_issue; }
        }

        /// <summary>
        /// 属性: 计息方式
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_AI_MOD")]
        public string C_DV_AI_MOD
        {
            set { c_dv_ai_mod = value; }

            get { return c_dv_ai_mod; }
        }

        /// <summary>
        /// 属性: 付息公式
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_PI_MOD")]
        public string C_DV_PI_MOD
        {
            set { c_dv_pi_mod = value; }

            get { return c_dv_pi_mod; }
        }

        /// <summary>
        /// 属性: 计息起始日
        /// </summary>
        [JsonProperty(PropertyName = "d_AI_BEGIN")]
        public string D_AI_BEGIN
        {
            set { d_ai_begin = value; }

            get { return d_ai_begin; }
        }

        /// <summary>
        /// 属性: 计息截止日
        /// </summary>
        [JsonProperty(PropertyName = "d_AI_END")]
        public string D_AI_END
        {
            set { d_ai_end = value; }

            get { return d_ai_end; }
        }

        /// <summary>
        /// 属性: //描述
        /// </summary>
        [JsonProperty(PropertyName = "c_DESC")]
        public string C_DESC
        {
            set { c_desc = value; }

            get { return c_desc; }
        }

        /// <summary>
        /// 属性: 计息公式
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_AI_EXPR")]
        public string C_DV_AI_EXPR
        {
            set { c_DV_AI_EXPR = value; }

            get { return c_DV_AI_EXPR; }
        }

        /// <summary>
        /// 机构代码
        /// </summary>
        [JsonProperty(PropertyName = "c_ORG_CODE")]
        public string C_ORG_CODE
        {
            get { return c_ORG_CODE; }
            set { c_ORG_CODE = value; }
        }

        /// <summary>
        /// 出票人
        /// </summary>
        [JsonProperty(PropertyName = "c_ORG_NAME")]
        public string C_ORG_NAME
        {
            set { c_org_name = value; }

            get { return c_org_name; }
        }

        /// <summary>
        /// 承兑人行名
        /// </summary>
        [JsonProperty(PropertyName = "c_OPEN_ACC_NAME")]
        public string C_OPEN_ACC_NAME
        {
            set { c_open_acc_name = value; }

            get { return c_open_acc_name; }
        }

        /// <summary>
        /// 承兑人行号
        /// </summary>
        [JsonProperty(PropertyName = "c_OPEN_ACC_NO")]
        public string C_OPEN_ACC_NO
        {
            set { c_open_acc_code = value; }

            get { return c_open_acc_code; }
        }

        /// <summary>
        /// 贴现行行名
        /// </summary>
        [JsonProperty(PropertyName = "c_TXH_NAME")]
        public string C_TXH_NAME
        {
            set { c_TXH_NAME = value; }

            get { return c_TXH_NAME; }
        }

        /// <summary>
        /// 贴现行行号
        /// </summary>
        [JsonProperty(PropertyName = "c_TXH_CODE")]
        public string C_TXH_CODE
        {
            set { c_TXH_CODE = value; }

            get { return c_TXH_CODE; }
        }

        /// <summary>
        /// 大额支付号
        /// </summary>
        [JsonProperty(PropertyName = "c_SYS_CODE")]
        public string C_SYS_CODE
        {
            set { c_sys_code = value; }

            get { return c_sys_code; }
        }

        /// <summary>
        /// 证券属性
        /// </summary>
        public string C_DA_CODE
        {
            get { return c_Da_Code; }

            set { c_Da_Code = value; }
        }

        /// <summary>
        /// 发行类型
        /// add by tangshifeng 2013-07-03
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_ISSUE")]
        public string C_DV_ISSUE
        {
            set { c_dv_issue = value; }

            get { return c_dv_issue; }
        }

        /// <summary>
        /// 担保方式
        /// add by tangshifeng 2013-07-03
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_ASSURE")]
        public string C_DV_ASSURE
        {
            set { c_dv_assure = value; }

            get { return c_dv_assure; }
        }

        /// <summary>
        /// 信用评级
        /// </summary>
        [JsonProperty(PropertyName = "c_CREDIT_RATING")]
        public string C_CREDIT_RATING
        {
            set { c_credit_rating = value; }

            get { return c_credit_rating; }
        }

        /// <summary>
        /// 结算机构
        /// </summary>
        [JsonProperty(PropertyName = "c_SETT_ORG")]
        public string C_SETT_ORG
        {
            set { c_SETT_ORG = value; }

            get { return c_SETT_ORG; }
        }

        /// <summary>
        /// 含权标志
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_RIGHT")]
        public string C_DV_RIGHT
        {
            set { c_DV_RIGHT = value; }

            get { return c_DV_RIGHT; }
        }

        /// <summary>
        /// 含权标志
        /// </summary>
        [JsonProperty(PropertyName = "c_RZRQ_MARK")]
        public string C_RZRQ_MARK
        {
            set { c_RZRQ_MARK = value; }

            get { return c_RZRQ_MARK; }
        }

        /// <summary>
        /// 组合代码 liuxiang 2016-5-9 STORY #25484 YSS:债券品种信息和理财品种信息增加组合私有化功能
        /// </summary>
        [JsonProperty(PropertyName = "c_PORT_CODE")]
        public string C_PORT_CODE
        {
            get { return c_PORT_CODE; }
            set { c_PORT_CODE = value; }
        }

        /// <summary>
        /// STORY #39209 属性：转换起始日
        /// </summary>
        [JsonProperty(PropertyName = "d_TRA_BEGIN")]
        public string D_TRA_BEGIN
        {
            get { return d_TRA_BEGIN; }
            set { d_TRA_BEGIN = value; }
        }

        /// <summary>
        /// STORY #39209 属性：转换截止日
        /// </summary>
        [JsonProperty(PropertyName = "d_TRA_END")]
        public string D_TRA_END
        {
            get { return d_TRA_END; }
            set { d_TRA_END = value; }
        }

        /// <summary>
        /// 属性: 计提基准_标准资产
        /// </summary>
        [JsonProperty(PropertyName = "c_JTJZ4STD_SETT")]
        public string C_JTJZ4STD_SETT
        {
            set { c_JTJZ4STD_SETT = value; }

            get { return c_JTJZ4STD_SETT; }
        }

        /// <summary>
        /// 属性: 计提基准_非标准资产
        /// </summary>
        [JsonProperty(PropertyName = "c_JTJZ4NOTSTD_SETT")]
        public string C_JTJZ4NOTSTD_SETT
        {
            set { c_JTJZ4NOTSTD_SETT = value; }

            get { return c_JTJZ4NOTSTD_SETT; }
        }

        /// <summary>
        /// 银行总行
        /// </summary>
        [JsonProperty(PropertyName = "c_BANK_HEAD")]
        public string C_BANK_HEAD
        {
            set { c_BANK_HEAD = value; }

            get { return c_BANK_HEAD; }
        }

        /// <summary>
        /// 属性: 银行支行
        /// </summary>
        [JsonProperty(PropertyName = "c_BANK_BRANCH")]
        public string C_BANK_BRANCH
        {
            set { c_BANK_BRANCH = value; }

            get { return c_BANK_BRANCH; }
        }

        /// <summary>
        /// add by wangpeixu 2017-06-06
        /// STORY37961财税140号文件，针对资管增值税系统改造需求
        /// 保本类型
        /// </summary>
        [JsonProperty(PropertyName = "c_GUAR_TYPE")]
        public string C_GUAR_TYPE
        {
            set { c_GUAR_TYPE = value; }

            get { return c_GUAR_TYPE; }
        }

        /// <summary>
        /// add by wangpeixu 2017-06-06
        /// STORY37961财税140号文件，针对资管增值税系统改造需求
        /// 金融商品
        /// </summary>
        [JsonProperty(PropertyName = "c_FINA_COMM")]
        public string C_FINA_COMM
        {
            set { c_FINA_COMM = value; }

            get { return c_FINA_COMM; }
        }

        /// <summary>
        /// STORY #41687 含付息日当天利息，默认“否”
        /// </summary>
        [JsonProperty(PropertyName = "n_TODAY_INTEREST")]
        public string N_TODAY_INTEREST
        {
            get { return n_TODAY_INTEREST; }
            set { n_TODAY_INTEREST = value; }
        }

        /// <summary>
        /// STORY #40480 托管人代码
        /// </summary>
        [JsonProperty(PropertyName = "c_TGR_CODE")]
        public string C_TGR_CODE
        {
            get { return c_TGR_CODE; }
            set { c_TGR_CODE = value; }
        }

        /// <summary>
        /// STORY #40480 托管人名称
        /// </summary>
        [JsonProperty(PropertyName = "c_TGR_NAME")]
        public string C_TGR_NAME
        {
            get { return c_TGR_NAME; }
            set { c_TGR_NAME = value; }
        }

        /// <summary>
        /// STORY #40480 销售服务费率
        /// </summary>
        [JsonProperty(PropertyName = "n_XSFWFL")]
        public string N_XSFWFL
        {
            get { return n_XSFWFL; }
            set { n_XSFWFL = value; }
        }

        /// <summary>
        /// 属性: 证券品种代码
        /// </summary>
        [JsonProperty(PropertyName = "c_SEC_VAR_CLS")]
        public string C_SEC_VAR_CLS
        {
            set { c_sec_var_cls = value; }

            get { return c_sec_var_cls; }
        }

        /// <summary>
        /// 属性: 证券品种代码
        /// </summary>
        [JsonProperty(PropertyName = "n_FEXX")]
        public string N_FEXX
        {
            set { n_FEXX = value; }

            get { return n_FEXX; }
        }

        /// <summary>
        /// 属性: 证券品种代码
        /// </summary>
        [JsonProperty(PropertyName = "n_FESX")]
        public string N_FESX
        {
            set { n_FESX = value; }

            get { return n_FESX; }
        }

        /// <summary>
        /// STORY #47986 分类标志
        /// </summary>
        [JsonProperty(PropertyName = "c_LC_SEC_TAG")]
        public string C_LC_SEC_TAG
        {
            get { return c_LC_SEC_TAG; }
            set { c_LC_SEC_TAG = value; }
        }

        /// <summary>
        /// 回购日期类型
        /// add by shijian 2017-11-09 STORY #47805 嘉实基金QD—回购业务需求
        /// </summary>
        [JsonProperty(PropertyName = "c_REPO_TYPE")]
        public string C_REPO_TYPE
        {
            get { return c_REPO_TYPE; }
            set { c_REPO_TYPE = value; }
        }

        /// <summary>
        /// 回购收益计算起始日期
        /// add by shijian 2017-11-09 STORY #47805 嘉实基金QD—回购业务需求
        /// </summary>
        [JsonProperty(PropertyName = "c_REPO_INCOME_FROM")]
        public string C_REPO_INCOME_FROM
        {
            get { return c_REPO_INCOME_FROM; }
            set { c_REPO_INCOME_FROM = value; }
        }

        /// <summary>
        /// 回购收益计算结束日期
        /// add by shijian 2017-11-09 STORY #47805 嘉实基金QD—回购业务需求
        /// </summary>
        [JsonProperty(PropertyName = "c_REPO_INCOME_TO")]
        public string C_REPO_INCOME_TO
        {
            get { return c_REPO_INCOME_TO; }
            set { c_REPO_INCOME_TO = value; }
        }

        /// <summary>
        /// 金融工具
        /// added by HeLiang 2017-11-14 STORY #43829 新企业会计准则（Ifrs9）解决方案
        /// </summary>
        [JsonProperty(PropertyName = "c_FINANCE_TOOL")]
        public string C_FINANCE_TOOL
        {
            get { return c_FINANCE_TOOL; }
            set { c_FINANCE_TOOL = value; }
        }

        /// <summary>
        /// add by yuanyafeng 20170926
        /// STORY #47088 债券基本信息，增加发行机构类型字段，区分金融机构和非金融机构
        /// 主体性质
        /// </summary>
        [JsonProperty(PropertyName = "c_MAIN_PROP")]
        public string C_MAIN_PROP
        {
            set { c_MAIN_PROP = value; }

            get { return c_MAIN_PROP; }
        }

        /// <summary>
        /// STORY #64802 【招商基金】增加fxzt字段的读入及增加债券基本信息该字段的展示
        /// 主体名称
        /// </summary>
        [JsonProperty(PropertyName = "c_MAIN_NAME")]
        public string C_MAIN_NAME
        {
            set { c_MAIN_NAME = value; }

            get { return c_MAIN_NAME; }
        }

        /// <summary>
        /// 属性: 公司英文代码
        /// </summary>
        [JsonProperty(PropertyName = "c_ISSUERS_CODE")]
        public string C_ISSUERS_CODE
        {
            set { c_ISSUERS_CODE = value; }

            get { return c_ISSUERS_CODE; }
        }

        /// <summary>
        /// 属性: 公司中文代码
        /// </summary>
        [JsonProperty(PropertyName = "c_ISSUERS_NAME")]
        public string C_ISSUERS_NAME
        {
            set { c_ISSUERS_NAME = value; }

            get { return c_ISSUERS_NAME; }
        }

        /// <summary>
        /// SEDOL码
        /// STORY #50934 [易方达基金] [QDII] 新增接口更新A股的ISIN和英文信息 add by qiguanzhi 20180119
        /// </summary>
        [JsonProperty(PropertyName = "c_SEDOLCODE")]
        public string C_SEDOLCODE
        {
            get { return c_SEDOLCODE; }
            set { c_SEDOLCODE = value; }
        }

        /// <summary>
        /// 集资方式
        /// </summary>
        [JsonProperty(PropertyName = "c_JZFS_CODE")]
        public string C_JZFS_CODE
        {
            get { return c_JZFS_CODE; }
            set { c_JZFS_CODE = value; }
        }

        /// <summary>
        /// 机构类型
        /// STORY #56789 【易方达子公司】【非标】【紧急】增加理财品种的需求
        /// </summary>
        [JsonProperty(PropertyName = "c_ORG_TYPE")]
        public string C_ORG_TYPE
        {
            get { return c_ORG_TYPE; }
            set { c_ORG_TYPE = value; }
        }

        /// <summary>
        /// add by cxj 2017-07-05
        /// STORY32772【南方基金】【V2.5需求】南方基金：协定存款付息方式增加
        /// 付息日期类型
        /// </summary>
        [JsonProperty(PropertyName = "c_PAY_DATE_TYPE")]
        public string C_PAY_DATE_TYPE
        {
            set { c_PAY_DATE_TYPE = value; }

            get { return c_PAY_DATE_TYPE; }
        }

        /// <summary>
        /// add by cxj 2017-07-05
        /// STORY32772【南方基金】【V2.5需求】南方基金：协定存款付息方式增加
        /// 付息天数
        /// </summary>
        [JsonProperty(PropertyName = "n_PAY_DATE")]
        public int N_PAY_DATE
        {
            set { n_PAY_DATE = value; }

            get { return n_PAY_DATE; }
        }

        /// <summary>
        /// add by cxj 2017-07-05
        /// STORY32772【南方基金】【V2.5需求】南方基金：协定存款付息方式增加
        /// 基准日期类型
        /// </summary>
        [JsonProperty(PropertyName = "c_BASE_DATE_TYPE")]
        public string C_BASE_DATE_TYPE
        {
            set { c_BASE_DATE_TYPE = value; }

            get { return c_BASE_DATE_TYPE; }
        }

        /// <summary>
        /// add by cxj 2017-07-05
        /// STORY32772【南方基金】【V2.5需求】南方基金：协定存款付息方式增加
        /// 基准天数
        /// </summary>
        [JsonProperty(PropertyName = "n_BASE_DATE")]
        public int N_BASE_DATE
        {
            set { n_BASE_DATE = value; }

            get { return n_BASE_DATE; }
        }

        /// <summary>
        /// add by lisi 2018-10-23
        /// STORY51261【易方达积极】彭博证券信息读入境外市场
        /// 境外市场
        /// </summary>
        [JsonProperty(PropertyName = "c_MKT_CODE_JC")]
        public string C_MKT_CODE_JC
        {
            set { c_MKT_CODE_JC = value; }

            get { return c_MKT_CODE_JC; }
        }

        /// <summary>
        /// 计息方式
        /// added by hezhigang 20181212 STORY64492【易方达香港】【一期】境外回购支持按年利率计算日收益进行计提
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_JXFS")]
        public string C_DV_JXFS
        {
            set { c_DV_JXFS = value; }

            get { return c_DV_JXFS; }
        }

        /// <summary>
        /// add by lipeidong 2017-07-25
        /// STORY #44526 债券信息中需要区分发行日期和上市日期，上市日期做为不活跃估值方式的判断信息
        /// </summary>
        [JsonProperty(PropertyName = "d_TO_LIST_ZQ")]
        public string D_TO_LIST_ZQ
        {
            set { d_TO_LIST_ZQ = value; }

            get { return d_TO_LIST_ZQ; }
        }

        /// <summary>
        /// STORY #74585 【华宝基金】——标准证券界面——理财产品信息中增加两个字段（合并需求）
        /// </summary>
        [JsonProperty(PropertyName = "n_GLF_RATE")]
        public string N_GLF_RATE
        {
            get { return n_GLF_RATE; }
            set { n_GLF_RATE = value; }
        }

        /// <summary>
        /// STORY #74585 【华宝基金】——标准证券界面——理财产品信息中增加两个字段（合并需求）
        /// </summary>
        [JsonProperty(PropertyName = "n_TGF_RATE")]
        public string N_TGF_RATE
        {
            get { return n_TGF_RATE; }
            set { n_TGF_RATE = value; }
        }

        /// <summary>
        /// STORY #78461 【上海银行】与私募产品运行检测表相关的项目字段新增
        /// </summary>
        [JsonProperty(PropertyName = "c_JWTZ")]
        public string C_JWTZ
        {
            set { c_JWTZ = value; }

            get { return c_JWTZ; }
        }

        /// <summary>
        /// STORY #78461 【上海银行】与私募产品运行检测表相关的项目字段新增
        /// </summary>
        [JsonProperty(PropertyName = "c_QYGPDM")]
        public string C_QYGPDM
        {
            set { c_QYGPDM = value; }

            get { return c_QYGPDM; }
        }

        /// <summary>
        /// STORY #78461 【上海银行】与私募产品运行检测表相关的项目字段新增
        /// </summary>
        [JsonProperty(PropertyName = "d_QY_DATE")]
        public string D_QY_DATE
        {
            set { d_QY_DATE = value; }

            get { return d_QY_DATE; }
        }

        /// <summary>
        /// STORY #78461 【上海银行】与私募产品运行检测表相关的项目字段新增
        /// </summary>
        [JsonProperty(PropertyName = "c_SHXYBM")]
        public string C_SHXYBM
        {
            set { c_SHXYBM = value; }

            get { return c_SHXYBM; }
        }

        /// <summary>
        /// STORY #78461 【上海银行】与私募产品运行检测表相关的项目字段新增
        /// </summary>
        [JsonProperty(PropertyName = "c_QYZCD")]
        public string C_QYZCD
        {
            set { c_QYZCD = value; }

            get { return c_QYZCD; }
        }

        /// <summary>
        /// STORY #78461 【上海银行】与私募产品运行检测表相关的项目字段新增
        /// </summary>
        [JsonProperty(PropertyName = "c_TZHY")]
        public string C_TZHY
        {
            set { c_TZHY = value; }

            get { return c_TZHY; }
        }

        /// <summary>
        /// STORY #78461 【上海银行】与私募产品运行检测表相关的项目字段新增
        /// </summary>
        [JsonProperty(PropertyName = "c_TZJD")]
        public string C_TZJD
        {
            set { c_TZJD = value; }

            get { return c_TZJD; }
        }

        /// <summary>
        /// STORY #68358 属性：明细市场
        /// </summary>
        [JsonProperty(PropertyName = "c_MKT_DETAIL")]
        public string C_MKT_DETAIL
        {
            get { return c_MKT_DETAIL; }
            set { c_MKT_DETAIL = value; }
        }

        /// <summary>
        /// 注册地国家
        /// </summary>
        [JsonProperty(PropertyName = "c_ZCDGJ")]
        public string C_ZCDGJ
        {
            set { c_ZCDGJ = value; }

            get { return c_ZCDGJ; }
        }

        /// <summary>
        /// 交易所所在国
        /// </summary>
        [JsonProperty(PropertyName = "c_JYSSZG")]
        public string C_JYSSZG
        {
            set { c_JYSSZG = value; }

            get { return c_JYSSZG; }
        }

        /// <summary>
        /// 转存方式
        /// </summary>
        [JsonProperty(PropertyName = "c_ZCFS")]
        public string C_ZCFS
        {
            set { c_ZCFS = value; }

            get { return c_ZCFS; }
        }

        /// <summary>
        /// 转存频率
        /// </summary>
        [JsonProperty(PropertyName = "c_ZCPL")]
        public string C_ZCPL
        {
            set { c_ZCPL = value; }

            get { return c_ZCPL; }
        }

        /// <summary>
        /// 转存日期
        /// </summary>
        ////[JsonProperty(PropertyName = "c_ZCRQ")]
        ////public string C_ZCRQ
        ////{
        ////    set { c_ZCRQ = value; }

        ////    get { return c_ZCRQ; }
        ////}

        /// <summary>
        /// 转存日期类型
        /// </summary>
        ////[JsonProperty(PropertyName = "c_ZCRQLX")]
        ////public string C_ZCRQLX
        ////{
        ////    set { c_ZCRQLX = value; }

        ////    get { return c_ZCRQLX; }
        ////}

        /// <summary>
        /// 英文名称
        /// </summary>
        [JsonProperty(PropertyName = "c_SEC_NAME_EN")]
        public string C_SEC_NAME_EN
        {
            set { c_SEC_NAME_EN = value; }

            get { return c_SEC_NAME_EN; }
        }

        /// <summary>
        /// 含权明细
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_RIGHTMX")]
        public string C_DV_RIGHTMX
        {
            set { c_DV_RIGHTMX = value; }

            get { return c_DV_RIGHTMX; }
        }

        /// <summary>
        /// 获取或设置彭博代码。
        /// </summary>
        [JsonProperty(PropertyName = "c_PBSEC_CODE")]
        public string C_PBSEC_CODE
        {
            set { c_PBSEC_CODE = value; }

            get { return c_PBSEC_CODE; }
        }

        /// <summary>
        /// 基金管理人
        /// </summary>
        [JsonProperty(PropertyName = "c_MANAGER_NAME")]
        public string C_MANAGER_NAME
        {
            set { c_MANAGER_NAME = value; }

            get { return c_MANAGER_NAME; }
        }

        /// <summary>
        /// 基金系列名称
        /// </summary>
        [JsonProperty(PropertyName = "c_FUND_NAME")]
        public string C_FUND_NAME
        {
            set { c_FUND_NAME = value; }

            get { return c_FUND_NAME; }
        }

        /// <summary>
        /// 投资者收益账户
        /// STORY #76847 【华宝基金】511990 SH华宝添益红利投资特殊处理 
        /// </summary>
        [JsonProperty(PropertyName = "c_INVEST_ACCOUNT")]
        public string C_INVEST_ACCOUNT
        {
            set { c_INVEST_ACCOUNT = value; }

            get { return c_INVEST_ACCOUNT; }
        }

        /// <summary>
        /// 正股转换代码
        /// </summary>
        [JsonProperty(PropertyName = "c_ZGZH_CODE")]
        public string C_ZGZH_CODE
        {
            get { return c_ZGZH_CODE; }
            set { c_ZGZH_CODE = value; }
        }

        /// <summary>
        /// 宽限期
        /// </summary>
        [JsonProperty(PropertyName = "n_GRACE_PERIOD")]
        public string N_GRACE_PERIOD
        {
            get { return n_GRACE_PERIOD; }
            set { n_GRACE_PERIOD = value; }
        }

        /// <summary>
        /// 宽限期计息规则
        /// </summary>
        [JsonProperty(PropertyName = "c_GRACE_PERIOD_RULE")]
        public string C_GRACE_PERIOD_RULE
        {
            get { return c_GRACE_PERIOD_RULE; }
            set { c_GRACE_PERIOD_RULE = value; }
        }

    }
}