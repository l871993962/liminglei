using System;
using System.Collections.Generic;
using System.Text;

namespace YssInformation.Support.Fun
{
    /// <summary>
    /// 核算元素 标识符与字段属性的映射
    /// </summary>
    public class ClsDaeMapping
    {
        /// <summary>
        /// 币种
        /// </summary>
        public const string DC = "DC";

        /// <summary>
        /// 核算项目
        /// </summary>
        public const string DAI = "DAI";

        /// <summary>
        /// 交易属性
        /// </summary>
        public const string DTA = "DTA";

        /// <summary>
        /// 发行方式
        /// </summary>
        public const string ISSUE_MODE = "ISSUE_MODE";

        /// <summary>
        /// 期限
        /// </summary>
        public const string VAR_DUR = "VAR_DUR";

        /// <summary>
        /// 分级组合
        /// </summary>
        public const string PORT_CLS = "PORT_CLS";

        /// <summary>
        /// 账户
        /// </summary>
        public const string CA = "CA";

        /// <summary>
        /// 证券内码
        /// </summary>
        public const string SEC = "SEC";

        /// <summary>
        /// 费用代码
        /// </summary>
        public const string FEE = "FEE";

        /// <summary>
        /// 网点代码
        /// </summary>
        public const string NET = "NET";

        /// <summary>
        /// 交易方式
        /// </summary>
        public const string DT = "DT";

        /// <summary>
        /// 交易市场
        /// </summary>
        public const string MKT = "MKT";

        /// <summary>
        /// 证券品种
        /// </summary>
        public const string SEC_VAR = "SEC_VAR";

        /// <summary>
        /// 帐户类型
        /// </summary>
        public const string ACC_TYPE = "ACC_TYPE";

        /// <summary>
        /// 费用品种
        /// </summary>
        public const string FEE_TYPE = "FEE_TYPE";

        /// <summary>
        /// 销售方式
        /// </summary>
        public const string DS = "DS";

        /// <summary>
        /// 交易渠道
        /// </summary>
        public const string TD_CHAN = "TD_CHAN";

        /// <summary>
        /// 科目代码
        /// </summary>
        public const string KM_CODE = "KM_CODE";

        /// <summary>
        /// 投资分类
        /// </summary>
        public const string INVEST_CLS = "INVEST_CLS";

        /// <summary>
        /// 付息频率
        /// </summary>
        public const string PI_FQCY_FI = "PI_FQCY_FI";
		
        /// <summary>
        /// 银行总行
        /// </summary>
        public const string ORG1 = "ORG1";

        /// <summary>
        /// 银行支行
        /// </summary>
        public const string ORG2 = "ORG2";
		
        #region 科目辅助元素 add by Yuntao Lau 2015.12.01 STORY #26998

        /// <summary>
        /// 机构名称
        /// </summary>
        public const string ORG = "ORG";

        /// <summary>
        /// 投资方式
        /// </summary>
        public const string INVEST_MODE = "INVEST_MODE";

        /// <summary>
        /// 品种期限
        /// </summary>
        public const string SEC_DUR = "SEC_DUR";

        /// <summary>
        /// 权证类型
        /// </summary>
        public const string WART_TYPE = "WART_TYPE";

        /// <summary>
        /// 行权方式
        /// </summary>
        public const string EXER_MODE = "EXER_MODE";

        #endregion

        /// <summary>
        /// 存放核算元素项
        /// </summary>
        public static Dictionary<string, string> daeItem = new Dictionary<string, string>();

        /// <summary>
        /// 根据核算元素的标识符获取核算元素的名称
        /// </summary>
        /// <param name="daeKey">核算元素的标识符</param>
        /// <returns>核算元素的代码</returns>
        public static string getDaeName(string daeKey)
        {
            if (daeItem.Count == 0)
            {
                init();
            }

            if (daeItem.ContainsKey(daeKey))
            {
                return daeItem[daeKey];
            }

            return null;
        }

        /// <summary>
        /// 根据核算元素的名称获取核算元素的标识符
        /// </summary>
        /// <param name="daeName">核算元素的</param>
        /// <returns>核算元素的标识符</returns>
        public static string getDaeKey(string daeName)
        {
            if (daeItem.Count == 0)
            {
                init();
            }

            foreach (KeyValuePair<string, string> kv in daeItem)
            {
                if (kv.Value.Equals(daeName))
                {
                    return kv.Key;
                }
            }

            return null;
        }

        /// <summary>
        /// 检查核算元素的键是否正确
        /// </summary>
        /// <param name="daeKey">键</param>
        /// <returns>若正确则返回 真值</returns>
        public static bool checkDaeKey(string daeKey)
        {
            if (daeItem.Count == 0)
            {
                init();
            }

            if (daeItem.ContainsKey(daeKey))
            {
                return true;
            }

            return false;
        }

        /// <summary>
        /// 初始化方法
        /// </summary>
        public static void init()
        {
            daeItem.Clear();
            daeItem.Add(DC, "C_DC_CODE"); // 币种
            daeItem.Add(DAI, "C_DAI_CODE"); // 核算类型
            daeItem.Add(DTA, "C_DTA_CODE"); // 交易属性
            daeItem.Add(ISSUE_MODE, "C_DV_ISSUE_MODE"); // 　发行方式
            daeItem.Add(VAR_DUR, "C_DV_VAR_DUR"); // 　期限
            daeItem.Add(PORT_CLS, "C_PORT_CLS_CODE"); // 分级组合
            daeItem.Add(CA, "C_CA_CODE"); // 账户代码
            daeItem.Add(SEC, "C_SEC_CODE"); // 证券内码
            daeItem.Add(FEE, "C_FEE_CODE"); // 费用代码
            daeItem.Add(NET, "C_NET_CODE"); // 　网点代码
            daeItem.Add(DT, "C_DT_CODE"); // 交易类型
            daeItem.Add(MKT, "C_MKT_CODE"); // 交易市场
            daeItem.Add(SEC_VAR, "C_SEC_VAR_CODE"); // 证券品种
            daeItem.Add(ACC_TYPE, "C_DV_ACC_TYPE"); // 账户类型
            daeItem.Add(FEE_TYPE, "C_DV_FEE_TYPE"); // 费用品种
            daeItem.Add(DS, "C_DS_CODE"); // 销售方式
            daeItem.Add(TD_CHAN, "C_TD_CHAN_CODE"); // 交易渠道
            daeItem.Add(INVEST_CLS, "C_DV_INVEST_CLS"); // 　投资分类
            //// add by Yuntao Lau 2015.12.01 STORY #26998
            daeItem.Add(ORG, "C_ORG_CODE"); //// 机构名称
            daeItem.Add(INVEST_MODE, "C_DV_INVEST_MODE"); //// 投资方式
            daeItem.Add(SEC_DUR, "C_DV_SEC_DUR"); //// 品种期限
            daeItem.Add(WART_TYPE, "C_WART_TYPE"); //// 权证类型
            daeItem.Add(EXER_MODE, "C_EXER_MODE"); //// 行权方式
            daeItem.Add(KM_CODE, "C_KM_CODE"); ////科目代码
			daeItem.Add(ORG1, "C_BANK_CODE"); //// 银行总行
            daeItem.Add(ORG2, "C_BRANCH_BANK_CODE"); //// 银行支行  
            daeItem.Add(PI_FQCY_FI, "C_DV_VAR_DUR_R"); ////付息频率 
        }
    }
}
