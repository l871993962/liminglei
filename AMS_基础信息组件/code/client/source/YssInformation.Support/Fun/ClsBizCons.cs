using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

////namespace YssBaseCls.Fun
namespace YssInformation.Support.Fun
{
    /// <summary>
    /// 业务常量
    /// </summary>
    public class ClsBizCons : FAST.Core.Context.ClsConstant
    {
        #region 交易清算常量

        /// <summary>
        /// 股票
        /// </summary>
        public const string VAL_TYPE = "GP";

        /// <summary>
        /// 股票的发行方式为 未上市_网下_非公开的方式
        /// </summary>
        public const string OFF_LINE_PRV = "WSS_WX_FGK";


        /// <summary>
        /// 交易性金融资产
        /// </summary>
        public const string IVT_CLS_IC_TD = "IC_TD";

        /// <summary>
        /// 可供出售性金融资产
        /// </summary>
        public const string IVT_CLS_IC_FS = "IC_FS";

        /// <summary>
        /// 持有到期金融资产
        /// </summary>
        public const string IVT_CLS_IC_HM = "IC_HM";

        #endregion

        #region 定义财务凭证常量

        /// <summary>
        /// 凭证审核
        /// </summary>
        public static readonly string VCHAUDIT = "VCHAUDIT";

        /// <summary>
        /// 审核
        /// </summary>
        public static readonly string AUDIT = "AUDIT";

        /// <summary>
        /// 反审核
        /// </summary>
        public static readonly string UNAUDIT = "UNAUDIT";

        /// <summary>
        /// 结算
        /// </summary>
        public static readonly string CLOSE = "CLOSE";

        /// <summary>
        /// 反结算
        /// </summary>
        public static readonly string UNCLOSE = "UNCLOSE";
        #endregion

        #region 科目设置定义的变量

        /// <summary>
        /// 判断当前级别是否为辅助核算的标示
        /// </summary>
        public static readonly string isX = "X";

        /// <summary>
        /// 判断当前核算级别是否采用辅助核算的标示
        /// </summary>
        public static readonly string isUseX = "1";

        /// <summary>
        /// 资产
        /// </summary>
        public static readonly string KC_ZC = "KC_ZC";

        /// <summary>
        /// 负债
        /// </summary>
        public static readonly string KC_FZ = "KC_FZ";

        /// <summary>
        /// 共同
        /// </summary>
        public static readonly string KC_GT = "KC_GT";

        /// <summary>
        /// 权益
        /// </summary>
        public static readonly string KC_QY = "KC_QY";

        /// <summary>
        /// 损益
        /// </summary>
        public static readonly string KC_SY = "KC_SY";

        /// <summary>
        /// 表外
        /// </summary>
        public static readonly string KC_BW = "KC_BW";

        /// <summary>
        /// 借贷方向：借 YSSUCO赢时胜2012年9月14日08_A byleeyu 20121025
        /// </summary>
        public static readonly string JD_J = "JD_J";

        /// <summary>
        /// 借贷方向：借名称
        /// </summary>
        public static readonly string JD_J_NAME = "借";

        /// <summary>
        /// 借贷方向：贷 YSSUCO赢时胜2012年9月14日08_A byleeyu 20121025
        /// </summary>
        public static readonly string JD_D = "JD_D";

        /// <summary>
        /// 借贷方向：贷名称
        /// </summary>
        public static readonly string JD_D_NAME = "贷";

        /// <summary>
        /// 借贷方向：平 YSSUCO赢时胜2012年9月14日08_A byleeyu 20121025
        /// </summary>
        public static readonly string JD_P = "JD_P";

        /// <summary>
        /// 借贷方向：平名称
        /// </summary>
        public static readonly string JD_P_NAME = "平";

        ///// 这些参数定义 移到父类FAST.Core.Context.ClsConstant中定义byleeyu20121204
        /////// <summary>
        /////// 发行方式
        /////// </summary>
        ////public static readonly string ISSUE_MODE = "ISSUE_MODE";

        /////// <summary>
        /////// 期限
        /////// </summary>
        ////public static readonly string VAR_DUR = "VAR_DUR";

        /////// <summary>
        /////// 分级组合
        /////// </summary>
        ////public static readonly string PORT_CLS = "PORT_CLS";

        /////// <summary>
        /////// 现金账户
        /////// </summary>
        ////public static readonly string CA = "CA";

        /////// <summary>
        /////// 证券内码
        /////// </summary>
        ////public static readonly string SEC = "SEC";

        /////// <summary>
        /////// 费用
        /////// </summary>
        ////public static readonly string FEE = "FEE";

        /////// <summary>
        /////// 网点
        /////// </summary>
        ////public static readonly string NET = "NET";

        /////// <summary>
        /////// 交易类型
        /////// </summary>
        ////public static readonly string DT = "DT";

        /////// <summary>
        /////// 交易属性 YSSUCO赢时胜2012年9月14日08_A byleeyu 20121025
        /////// </summary>
        ////public static readonly string DTA = "DTA";

        /////// <summary>
        /////// 市场代码
        /////// </summary>
        ////public static readonly string MKT = "MKT";

        /////// <summary>
        /////// 证券品种
        /////// </summary>
        ////public static readonly string SEC_VAR = "SEC_VAR";

        /////// <summary>
        /////// 账户类型
        /////// </summary>
        ////public static readonly string ACC_TYPE = "ACC_TYPE";

        /////// <summary>
        /////// 费用类型
        /////// </summary>
        ////public static readonly string FEE_TYPE = "FEE_TYPE";
        
        /////// <summary>
        /////// 销售类型
        /////// </summary>
        ////public static readonly string DS = "DS";

        /////// <summary>
        /////// 交易渠道
        /////// </summary>
        ////public static readonly string TD_CHAN = "TD_CHAN";

        #endregion

        #region 组合关联用的词汇常量
         /// <summary>
         /// 股东交易账户
         /// </summary>
         public static readonly string RELA_SH_ACC = "RELA_SH_ACC";

         /// <summary>
         /// 
         /// </summary>
         public static readonly string RELA_TD_SEAT = "RELA_TD_SEAT";

         /// <summary>
         /// 投资经理
         /// </summary>
         public static readonly string RELA_INV_MGR = "RELA_INV_MGR";

         /// <summary>
         /// 关联机构
         /// </summary>
         public static readonly string RELA_ORG = "RELA_ORG";

         /// <summary>
         /// 客户编号
         /// </summary>
         public static readonly string RELA_CL_NUM = "RELA_CL_NUM";
         ////public static readonly string 
         ////public static readonly string 

        #endregion

         #region 收益前置用到的变量

         /// <summary>
         /// 预付
         /// </summary>
         public static readonly string SYQZ_YF = "SYQZ_YF";

         /// <summary>
         /// 预收
         /// </summary>
         public static readonly string SYQZ_YS = "SYQZ_YS";

         #endregion

         #region 存款投资用到的变量
         /// <summary>
         /// 存款
         /// </summary>
         public static readonly string CK = "CK";

         /// <summary>
         /// 首期
         /// </summary>
         public static readonly string CKTZ_SQ = "CKTZ_SQ";

         /// <summary>
         /// 转入
         /// </summary>
         public static readonly string CKTZ_ZR = "CKTZ_ZR";

         /// <summary>
         /// 支取
         /// </summary>
         public static readonly string CKTZ_ZQ = "CKTZ_ZQ";

         /// <summary>
         /// 到期
         /// </summary>
         public static readonly string CKTZ_DQ = "CKTZ_DQ";

         /// <summary>
         /// 滚存
         /// </summary>
         public static readonly string CKTZ_GC = "CKTZ_GC";

         /// <summary>
         /// 存款投资
         /// </summary>
         public static readonly string CKTZ = "CKTZ";

         //// 新增业务类型字段
	     //// 需求 RQFII需要系统定期存款可以支持滚存
	     //// Added By xzl
         #region 存款业务类型
         /// <summary>
         /// 首期的首期
         /// </summary>
         public static readonly string SQ_SQ = "SQ_SQ";

         /// <summary>
         /// 滚存的首期
         /// </summary>
         public static readonly string GC_SQ = "GC_SQ";

         /// <summary>
         /// 首期的支取
         /// </summary>
         public static readonly string SQ_ZQ = "SQ_ZQ";

         /// <summary>
         /// 滚存的支取
         /// </summary>
         public static readonly string GC_ZQ = "GC_ZQ";

         /// <summary>
         /// 首期的到期
         /// </summary>
         public static readonly string SQ_DQ = "SQ_DQ";

         /// <summary>
         /// 滚存的到期
         /// </summary>
         public static readonly string GC_DQ = "GC_DQ";

        #endregion 存款业务类型

         /// <summary>
         /// 主记录 
         /// </summary>
         public static readonly string ETF_SG = "SG"; 
         
         /// <summary>
         /// 替代
         /// </summary>
         public static readonly string EFT_TD = "TD"; 

         /// <summary>
         /// 逐笔计息法
         /// </summary>
         public static readonly string DRA_AI_ITEM = "DRA_AI_ITEM";


         /// <summary>
         /// 固定利率
         /// </summary>
         public static readonly string RTA_FIX = "RTA_FIX";

         /// <summary>
         /// 利率品种
         /// </summary>
         public static readonly string RTA_RATE = "RTA_RATE";

        /// <summary>
        /// 固定收益
        /// </summary>
         public static readonly string RTA_INC = "RTA_INC";

        /// <summary>
        /// 比率公式
        /// Add by ： zhengguiyu 20140109
        /// </summary>
         public static readonly string RTA_RATIO = "RTA_RATIO";

         /// <summary>
         /// 分段利率
         /// Add by ： wangtangyao 20160729
         /// </summary>
         public static readonly string RTA_SEGMENTED = "RTA_SEGMENTED";

         /// <summary>
         /// 基准浮动利率
         /// Add by ： dingshalu 20151211
         /// </summary>
         public static readonly string RTA_FLOAT_BASE = "RTA_FLOAT_BASE";

         /// <summary>
         /// 挂钩浮动利率
         /// Add by ：  dingshalu 20151211
         /// </summary>
         public static readonly string RTA_FLOAT_LIKED = "RTA_FLOAT_LIKED";

         
         /// <summary>
         ///高级算法 
         /// </summary>
         public static readonly string RTA_ADV = "RTA_ADV";

         /// <summary>
         /// ETF申赎的主记录
         /// </summary>
         public static readonly string ETF_Z = "ZJL";

         /// <summary>
         /// ETF申赎子记录
         /// </summary>
         public static readonly string ETF_F = "FJL";

         /// <summary>
         /// ETF现金替代
         /// </summary>
         public static readonly string ETF_X = "XJTD";
         
         /// <summary>
         /// 预估现金差额
         /// </summary>
         public static readonly string XJCE_YG = "CNSS_XJCE_YG";
         
         /// <summary>
         /// 可收退补款
         /// </summary>
         public static readonly string TBK_KS = "CNSS_TBK_KS";

         /// <summary>
         /// 新股锁定 申请信息
         /// </summary>
         public static readonly string XGSD_S = "SQ";

         /// <summary>
         /// 新股锁定 确认信息
         /// </summary>
         public static readonly string XGSD_Q = "QR";

         /// <summary>
         /// 新债申购的 申请信息
         /// </summary>
         public static readonly string XZSG_S = "SQ";

         /// <summary>
         /// 新债申购 的 确认信息
         /// </summary>
         public static readonly string XZSG_Q = "QR";

         /// <summary>
         /// 权证行权业务 行权
         /// </summary>
         public static readonly string XQ = "XQ";

         /// <summary>
         /// 权证行权业务 实物结算
         /// </summary>
         public static readonly string SWJS = "SWJS";

         /// <summary>
         /// 国债期货_交割
         /// </summary>
         public static readonly string GZQH_JG = "GZQH_JG";

         /// <summary>
         /// 国债期货_违约
         /// </summary>
         public static readonly string GZQH_WY = "GZQH_WY";

         //// 参数定义 移到父类FAST.Core.Context.ClsConstant中定义byleeyu20121204
         /////// <summary>
         /////// 查询缓存的类型
         /////// </summary>
         ////public static readonly string CacheType  = "CacheType";
         #endregion
         #region 回购交易用到的变量
         /// <summary>
         /// 回购
         /// </summary>
         public static readonly string HG = "HG";

         /// <summary>
         /// 回购交易
         /// </summary>
         public static readonly string HGJY = "HGJY";

         /// <summary>
         /// 首期
         /// </summary>
         public static readonly string HGJY_SQ = "HGJY_SQ";

         /// <summary>
         /// 转入
         /// </summary>
         public static readonly string HGJY_ZR = "HGJY_ZR";

         /// <summary>
         /// 支取
         /// </summary>
         public static readonly string HGJY_ZQ = "HGJY_ZQ";

         /// <summary>
         /// 到期
         /// </summary>
         public static readonly string HGJY_DQ = "HGJY_DQ";

         /// <summary>
         /// 交易手续费
         /// </summary>
         public static readonly string ZQL_JYSXF = "ZQL_JYSXF";

         /// <summary>
         /// 正回购
         /// </summary>
         public static readonly string HGJY_NHG = "HGJY_NHG";

         /// <summary>
         /// 逆回购
         /// </summary>
         public static readonly string HGJY_ZHG = "HGJY_ZHG";
         
         /// <summary>
         /// 经手费
         /// </summary>
         public static readonly string ZQL_JSOF = "ZQL_JSOF";

         /// <summary>
         /// 净佣金
         /// </summary>
         public static readonly string YJL_JYJ = "YJL_JYJ";
        #endregion
        #region 拆借交易
         /// <summary>
         /// 拆借
         /// </summary>
         public static readonly string CJ = "CJ";

         /// <summary>
         /// 拆借交易
         /// </summary>
         public static readonly string CJJY = "CJJY";

        /// <summary>
        /// 首期
        /// </summary>
        public static readonly string CJJY_SQ = "CJJY_SQ";

        /// <summary>
        /// 转入
        /// </summary>
        public static readonly string CJJY_ZR = "CJJY_ZR";

        /// <summary>
        /// 支取
        /// </summary>
        public static readonly string CJJY_ZQ = "CJJY_ZQ";

        /// <summary>
        /// 到期
        /// </summary>
        public static readonly string CJJY_DQ = "CJJY_DQ";
        #endregion

         #region 高级算法

         /// <summary>
         /// 后台验证表达式错误的标示
         /// </summary>
         public static readonly string CheckResult = "false";

         /// <summary>
         /// 关键字类型
         /// </summary>
         public static readonly string KEY_TYPE = "KEY_TYPE";
         
         /// <summary>
         /// 函数类型
         /// </summary>
         public static readonly string FUNC_TYPE = "FUNC_TYPE";
         
        #endregion
        #region 债券交易业务
        
        /// <summary>
        /// 上交所
        /// </summary>
        public static readonly string MARKET_SS = "XSHG";
         
        /// <summary>
        /// 深交所
        /// </summary>
        public static readonly string MARKET_SZ = "XSHE";
        #endregion

        #region 场外申赎业务
        /// <summary>
        /// 开放申赎 申请数据
        /// </summary>
        public static readonly string KFSQ = "KFSQ";

        /// <summary>
        /// 开放申赎 确认数据
        /// </summary>
        public static readonly string KFQR = "KFQR";

        /// <summary>
        /// 开放申赎 申赎数据
        /// </summary>
        public static readonly string KFSH = "KFSH";

        /// <summary>
        /// 开放申赎 申赎数据
        /// </summary>
        public static readonly string KFSS = "KFSS";

        /// <summary>
        /// 开放申赎-提取
        /// </summary>
        public static readonly string KFTQ = "KFTQ";

        /// <summary>
        /// 场外申赎-认购申请
        /// </summary>
        public static readonly string CWSS_RGSQ = "CWSS_RGSQ";
        
        /// <summary>
        /// 场外申赎-认购确认
        /// </summary>
        public static readonly string CWSS_RGQR = "CWSS_RGQR";

        /// <summary>
        /// 场外申赎-申购申请
        /// </summary>
        public static readonly string CWSS_SGSQ = "CWSS_SGSQ";

        /// <summary>
        /// 场外申赎-申购确认
        /// </summary>
        public static readonly string CWSS_SGQR = "CWSS_SGQR";

        /// <summary>
        /// 场外申赎-赎回确认
        /// </summary>
        public static readonly string CWSS_SHQR = "CWSS_SHQR";

        /// <summary>
        /// 场外申赎-申购
        /// </summary>
        public static readonly string CWSS_SG = "CWSS_SG";

        /// <summary>
        /// 场外申赎-认购
        /// </summary>
        public static readonly string CWSS_RG = "CWSS_RG";

        /// <summary>
        /// 场外申赎-赎回
        /// </summary>
        public static readonly string CWSS_SH = "CWSS_SH";

        /// <summary>
        /// 场外申赎-提取
        /// </summary>
        public static readonly string CWSS_TQ = "CWSS_TQ";

        /// <summary>
		/// add by xuhanbing  2016/1/9 (合并代码)STORY #27214 开放申赎业务—新增—业务类型只有“认购”、“申购”，建议新增“赎回”选项
        ///  开放申赎-赎回到账
        /// </summary>
        public static readonly string KFSHDZ = "KFSHDZ";

        /// <summary>
        /// add by liyanjun 2016-6-2 STORY #28572 投资货币etf_申赎银华日利511880交易型货币基金业务需求
        /// 场外申赎-申购现金差额
        /// </summary>
        public static readonly string CWSS_SGXJCE = "CWSS_SGXJCE";

        /// <summary>
        /// add by liyanjun 2016-6-2 STORY #28572 投资货币etf_申赎银华日利511880交易型货币基金业务需求
        /// 场外申赎-赎回现金差额
        /// </summary>
        public static readonly string CWSS_SHXJCE = "CWSS_SHXJCE";

        #endregion 

        #region 新股申购业务和新债申购业务

        /// <summary>
        /// 新股申购业务类型
        /// </summary>
        public static readonly string XGSG = "XGSG";

        /// <summary>
        /// 新债申购业务类型
        /// </summary>
        public static readonly string XZSG = "XZSG";

        /// <summary>
        /// 申请标识
        /// </summary>
        public static readonly string SQ = "SQ";

        /// <summary>
        /// 确认标识
        /// </summary>
        public static readonly string QR = "QR";

        /// <summary>
        /// 新股申购申请
        /// </summary>
        public static readonly string XGSG_SQ = "XGSG_SQ";

        /// <summary>
        /// 新股申购确认
        /// </summary>
        public static readonly string XGSG_QR = "XGSG_QR";

        /// <summary>
        /// 新债申购申请
        /// </summary>
        public static readonly string XZSG_SQ = "XZSG_SQ";

        /// <summary>
        /// 新债申购确认
        /// </summary>
        public static readonly string XZSG_QR = "XZSG_QR";
        #endregion 

        #region 远期交易业务

        /// <summary>
        /// 远期交易业务
        /// </summary>
        public static readonly string YQJY = "YQJY";

        /// <summary>
        /// 远期交易业务类型-首期
        /// </summary>
        public static readonly string YQJY_SQ = "YQJY_SQ";

        /// <summary>
        /// 远期交易业务类型-平仓
        /// </summary>
        public static readonly string YQJY_PC = "YQJY_PC";

        /// <summary>
        /// 远期交易业务类型-交割
        /// </summary>
        public static readonly string YQJY_JG = "YQJY_JG";

        /// <summary>
        /// 远期交易首期
        /// </summary>
        public static readonly string YQ_SQ = "YQJY";

        // <summary>
        /// 远期交易平仓
        /// </summary>
        public static readonly string YQ_PC = "YQPC";

        /// <summary>
        /// 远期交易交割
        /// </summary>
        public static readonly string YQ_JG = "YQJG";

        #endregion

        #region 利率互换业务

        /// <summary>
        /// 开仓
        /// </summary>
        public const string HHJY_KC = "HHJY_KC";

        /// <summary>
        /// 提前平盘
        /// </summary>
        public const string HHJY_PC = "HHJY_PC";

        /// <summary>
        /// 到期
        /// </summary>
        public const string HHJY_DQ = "HHJY_DQ";

        #endregion

        #region 收益互换业务

        /// <summary>
        /// 首期
        /// </summary>
        public static readonly string SYHH_SQ = "SYHH_SQ";

        /// <summary>
        /// 到期
        /// </summary>
        public static readonly string SYHH_DQ = "SYHH_DQ";

        /// <summary>
        /// 利率类型_固定利率
        /// </summary>
        public static readonly string DV_VOC_RATE_TYPE_FIXED = "SYFS_GDLL";

        /// <summary>
        /// 利率类型_浮动利率
        /// </summary>
        public static readonly string DV_VOC_RATE_TYPE_FLOATING = "SYFS_FDSY";

        #endregion

        #region 划款指令模块常量

        /// <summary>
        /// 指令类型--收款
        /// </summary>
        public const string ZLLX_SK = "ZLLX_SK";

        /// <summary>
        /// 指令类型--付款
        /// </summary>
        public const string ZLLX_FK = "ZLLX_FK";

        /// <summary>
        /// 指令类型--划拨
        /// </summary>
        public const string ZLLX_HB = "ZLLX_HB";

        /// <summary>
        /// 账户类型-- 产品账户
        /// </summary>
        public const string FUNDACC = "FundAcc";

        /// <summary>
        /// 账户类型-- 公用账户
        /// </summary>
        public const string PUBACC = "PubAcc";

        /// <summary>
        /// 下拉框类型 -- 收款人、付款人
        /// </summary>
        public const string OPENNAME = "OpenName";

        /// <summary>
        /// 下拉框类型-- 收款账号、付款账号
        /// </summary>
        public const string OPENNO = "OpenNo";


        #endregion

        #region 资产池配置常量

        /// <summary>
        /// 资产池
        /// </summary>
        public const string ASS_POOL = "POOL";

        /// <summary>
        /// 品牌
        /// </summary>
        public const string BRAND = "BRAND";

        #endregion

        #region 产品状态常量

        /// <summary>
        /// 产品库中的产品状态（除草稿状态以外）
        /// </summary>
        public const string PS0 = "PS0";

        /// <summary>
        /// 草稿
        /// </summary>
        public const string PS1 = "PS1";

        /// <summary>
        /// 待发行
        /// </summary>
        public const string PS2 = "PS2";

        /// <summary>
        /// 募集期
        /// </summary>
        public const string PS3 = "PS3";

        /// <summary>
        /// 存续期
        /// </summary>
        public const string PS4 = "PS4";

        /// <summary>
        /// 已到期
        /// </summary>
        public const string PS5 = "PS5";

        /// <summary>
        /// 已清算
        /// </summary>
        public const string PS6 = "PS6";

        //产品操作状态常量
        /// <summary>
        /// 确认
        /// </summary>
        public const string CF = "CF";

        /// <summary>
        /// 撤销
        /// </summary>
        public const string C = "C";

        //产品功能代码
        /// <summary>
        /// 产品库
        /// </summary>
        public const string PD_CPK = "pdCpk";

        /// <summary>
        /// 计划期
        /// </summary>
        public const string PD_PLAN = "pdPlan";

        /// <summary>
        /// 待发行
        /// </summary>
        public const string PD_DFX = "pdDfx";

        /// <summary>
        /// 募集期
        /// </summary>
        public const string PD_MJQ = "pdMjq";

        /// <summary>
        /// 存续期
        /// </summary>
        public const string PD_CXQ = "pdCxq";

        /// <summary>
        /// 已到期
        /// </summary>
        public const string PD_YDQ = "pdYdq";

        /// <summary>
        /// 已清算
        /// </summary>
        public const string PD_YQS = "pdYqs";



        #endregion

        #region 尾差调整
        /// <summary>
        /// 总分调尾
        /// </summary>
        public const string PRO_ADJUST = "PRO_ADJUST";

        /// <summary>
        /// 总分调尾
        /// </summary>
        public const string PRO_ADJUST_NAME = "总分调尾";

        /// <summary>
        /// 汇集业务
        /// </summary>
        public const string PRO_COLLECTION = "PRO_COLLECTION";

        /// <summary>
        /// 汇集业务
        /// </summary>
        public const string PRO_COLLECTION_NAME = "汇集业务";

        #endregion

        #region 组合级别
        /// <summary>
        /// 单元层
        /// </summary>
        public const string PORT_LAYER_UNIT = "UNIT_LAYER";

        /// <summary>
        /// 组合层
        /// </summary>
        public const string PORT_LAYER_PORT = "PORT_LAYER";

        /// <summary>
        /// 计划层
        /// </summary>
        public const string PORT_LAYER_PLAN = "PLAN_LAYER";
        #endregion

        #region 执行结果
        /// <summary>
        /// 成功
        /// </summary>
        public const string RES_SUCCESS = "Success";

        /// <summary>
        /// 失败
        /// </summary>
        public const string RES_FAULT = "Fault";
        
        #endregion

        #region 对价派息

        /// <summary>
        /// 分红派息
        /// </summary>
        public const string DJPX_FHPX = "DJPX_FHPX";

        /// <summary>
        /// 现金对价
        /// </summary>
        public const string DJPX_XJDJ = "DJPX_XJDJ";

        /// <summary>
        /// 红利补税
        /// </summary>
        public const string DJPX_HLBS = "DJPX_HLBS";

        /// <summary>
        /// 分红转投
        /// </summary>
        public const string DJPX_FHZT = "DJPX_FHZT";

        /// <summary>
        /// 红利投资
        /// </summary>
        public const string DJPX_HLTZ = "DJPX_HLTZ";

        /// <summary>
        /// 收益提取
        /// </summary>
        public const string DJPX_SYTC = "DJPX_SYTC";

        #endregion

        #region  数据来源标识

        /// <summary>
        /// 数据来源--自动
        /// </summary>
        public const string Z = "Z";

        /// <summary>
        /// 数据来源--手工
        /// </summary>
        public const string H = "H";

        /// <summary>
        /// 数据来源--自动—手工
        /// </summary>
        public const string Z_H = "Z_H";

        /// <summary>
        /// 数据来源--库存
        /// </summary>
        public const string S = "S";

        /// <summary>
        /// 数据来源--库存-手工
        /// </summary>
        public const string S_H = "S_H";

        #endregion

        #region 票据交易
        /// <summary>
        /// 票据
        /// </summary>
        public const string PJ = "PJ";

        /// <summary>
        /// 票据交易
        /// </summary>
        public const string PJJY = "PJJY";

        /// <summary>
        /// 票据卖出
        /// </summary>
        public const string PJJY_SELL = "PJJY_SELL";

        /// <summary>
        /// 票据交易-逆回购
        /// </summary>
        public const string PJJY_NHG = "PJJY_NHG";

        /// <summary>
        /// 回购式
        /// </summary>
        public const string HGS = "HGS";

        /// <summary>
        /// 买断式
        /// </summary>
        public const string MDS = "MDS";

        /// <summary>
        /// 票据交易-合同信息
        /// </summary>
        public const string PJJY_BS = "PJJY_BS";

        #endregion

        #region 基金交易
        /// <summary>
        /// 基金交易
        /// </summary>
        public const string JJ = "JJ";

        /// <summary>
        /// 理财
        /// </summary>
        public const string LC = "LC";

        /// <summary>
        /// 基金交易
        /// </summary>
        public const string JJJY = "JJJY";

        /// <summary>
        /// 基金交易-卖出
        /// </summary>
        public const string JJJY_SELL = "JJJY_SELL";

        #endregion

        #region 债券交易
        /// <summary>
        /// 债券
        /// </summary>
        public const string ZQ = "ZQ";

        /// <summary>
        /// 债券交易
        /// </summary>
        public const string ZQJY = "ZQJY";

        /// <summary>
        /// 债券交易-卖出
        /// </summary>
        public const string ZQJY_SELL = "ZQJY_SELL";

        #endregion

        #region 股票交易
        /// <summary>
        /// 股票
        /// </summary>
        public const string GP = "GP";

        /// <summary>
        /// 股票交易
        /// </summary>
        public const string GPJY = "GPJY";

        /// <summary>
        /// 股票交易-卖出
        /// </summary>
        public const string GPJY_SELL = "GPJY_SELL";

        #endregion
		
        #region 股票质押式回购

        /// <summary>
        /// 初始回购
        /// </summary>
        public const string HGJY_CSHG = "HGJY_CSHG";

        /// <summary>
        /// 购回交易
        /// </summary>
        public const string HGJY_GHJY = "HGJY_GHJY";

        /// <summary>
        /// 补充质押
        /// </summary>
        public const string HGJY_BCZY = "HGJY_BCZY";

        /// <summary>
        /// 解除质押
        /// </summary>
        public const string HGJY_JCZY = "HGJY_JCZY";

        #endregion

        #region 卖空交易

        /// <summary>
        /// 借入证券
        /// </summary>
        public const string ZQJD_JRZQ = "ZQJD_JRZQ";

        /// <summary>
        /// 归还证券
        /// </summary>
        public const string ZQJD_GHZQ = "ZQJD_GHZQ";

        /// <summary>
        /// 借出证券
        /// </summary>
        public const string ZQJD_JCZQ = "ZQJD_JCZQ";

        /// <summary>
        /// 收回证券
        /// </summary>
        public const string ZQJD_SHZQ = "ZQJD_SHZQ";

        /// <summary>
        /// 卖出证券
        /// </summary>
        public const string ZQMK_MCZQ = "ZQMK_MCZQ";

        /// <summary>
        /// 买入证券
        /// </summary>
        public const string ZQMK_MRZQ = "ZQMK_MRZQ";

        #endregion

        #region 业务方案类型
        /// <summary>
        /// 业务方案--核算方案
        /// Add by :zhengguiyu 20140317
        /// </summary>
        public const string ACT_PLAN = "BUSSINESS_PLAN_ACT";

        /// <summary>
        /// 业务方案--清算方案
        /// </summary>
        public const string CLEAR_PLAN = "BUSSINESS_PLAN_CLEAR"; 
        
        /// <summary>
        /// 业务方案--公共清算方案
        /// </summary>
        public const string PUBCLEAR_PLAN = "BUSSINESS_PLAN_PUBCLEAR"; 

        /// <summary>
        /// 业务方案--组合代码方案
        /// </summary>
        public const string PORT_PLAN = "BUSSINESS_PLAN_PORT";
        #endregion

        #region 数据来源
        public const string DATA_PLAT_BID = "PLAT_BID";
        #endregion
    }
}
