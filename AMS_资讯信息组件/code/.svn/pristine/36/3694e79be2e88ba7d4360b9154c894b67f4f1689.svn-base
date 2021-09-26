using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Exceptions;


using FAST.Core.Context;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Newtonsoft.Json;

namespace YssSecInformation.Support.Mp.PreStock.Pojo
{
    /// <summary>
    /// 优先股计息信息Pojo类
    /// added by liyongjun 20151208
    /// </summary>
    public class PreStockInterest : AuditableParamPojo
    {
        /// <summary>
        /// 证券内码
        /// </summary>
        private string c_SEC_CODE = "";

        /// <summary>
        /// 标的证券
        /// </summary>
        private string c_SEC_CODE_TRG = "";

        /// <summary>
        /// 计息起始日
        /// </summary>
        private DateTime d_AI_BEGIN;

        /// <summary>
        /// 计息截止日
        /// </summary>
        private DateTime d_AI_END;

        /// <summary>
        /// 票面利率
        /// </summary>
        private decimal n_FV_IR = 0;

        /// <summary>
        /// 税率
        /// </summary>
        private decimal n_RATE = 0;

        /// <summary>
        /// 计息方式
        /// </summary>
        private string c_DV_AI_MOD = "";

        /// <summary>
        /// 计息公式
        /// </summary>
        private string c_DV_AI_EXPR = "";

        /// <summary>
        /// 付息频率
        /// </summary>
        private string c_DV_VAR_DUR = "";

        /// <summary>
        /// 付息公式
        /// </summary>
        private string c_DV_PI_MOD = "";

        /// <summary>
        /// 核算类型
        /// </summary>
        private string c_DV_ACCOUNT_CODE = "";

        /// <summary>
        /// 累计股息
        /// </summary>
        private string c_DV_TOTAL_INCOME = "";

        /// <summary>
        /// 参与分红
        /// </summary>
        private string c_DV_ATTEND_PROFIT = "";

        /// <summary>
        /// 转换成普通股
        /// </summary>
        private string c_DV_EXCHGE_STOCK = "";

        /// <summary>
        /// 市场代码
        /// </summary>
        private string c_MKT_CODE = "";

        /// <summary>
        ///报价方式
        /// </summary>
        private string c_DV_QUT_MOD = "";

        /// <summary>
        ///  延迟支付
        /// </summary>
        private string c_DV_DELAY_PAY = "";

        /// <summary>
        /// 属性: 证券内码 
        /// </summary>
        [JsonProperty(PropertyName = "c_SEC_CODE")]
        public string C_SEC_CODE
        {
            set { c_SEC_CODE = value; }

            get { return c_SEC_CODE; }
        }

        /// <summary>
        /// 属性: 标的证券 
        /// </summary>
        [JsonProperty(PropertyName = "c_SEC_CODE_TRG")]
        public string C_SEC_CODE_TRG
        {
            set { c_SEC_CODE_TRG = value; }

            get { return c_SEC_CODE_TRG; }
        }

        /// <summary>
        /// 属性: 计息起始日 
        /// </summary>
        [JsonProperty(PropertyName = "d_AI_BEGIN")]
        public DateTime D_AI_BEGIN
        {
            set { d_AI_BEGIN = value; }

            get { return d_AI_BEGIN; }
        }

        /// <summary>
        /// 属性: 计息截止日 
        /// </summary>
        [JsonProperty(PropertyName = "d_AI_END")]
        public DateTime D_AI_END
        {
            set { d_AI_END = value; }

            get { return d_AI_END; }
        }

        /// <summary>
        /// 属性: 票面利率 
        /// </summary>
        [JsonProperty(PropertyName = "n_FV_IR")]
        public decimal N_FV_IR
        {
            set { n_FV_IR = value; }

            get { return n_FV_IR; }
        }

        /// <summary>
        /// 属性: 税率 
        /// </summary>
        [JsonProperty(PropertyName = "n_RATE")]
        public decimal N_RATE
        {
            set { n_RATE = value; }

            get { return n_RATE; }
        }

        /// <summary>
        /// 属性: 计息方式 
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_AI_MOD")]
        public string C_DV_AI_MOD
        {
            set { c_DV_AI_MOD = value; }

            get { return c_DV_AI_MOD; }
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
        /// 属性: 付息频率 
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_VAR_DUR")]
        public string C_DV_VAR_DUR
        {
            set { c_DV_VAR_DUR = value; }

            get { return c_DV_VAR_DUR; }
        }

        /// <summary>
        /// 属性: 付息公式 
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_PI_MOD")]
        public string C_DV_PI_MOD
        {
            set { c_DV_PI_MOD = value; }

            get { return c_DV_PI_MOD; }
        }

        /// <summary>
        /// 属性: 核算类型 
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_ACCOUNT_CODE")]
        public string C_DV_ACCOUNT_CODE
        {
            set { c_DV_ACCOUNT_CODE = value; }

            get { return c_DV_ACCOUNT_CODE; }
        }

        /// <summary>
        /// 属性: 累计股息 
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_TOTAL_INCOME")]
        public string C_DV_TOTAL_INCOME
        {
            set { c_DV_TOTAL_INCOME = value; }

            get { return c_DV_TOTAL_INCOME; }
        }


        /// <summary>
        /// 属性: 参与分红
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_ATTEND_PROFIT")]
        public string C_DV_ATTEND_PROFIT
        {
            set { c_DV_ATTEND_PROFIT = value; }

            get { return c_DV_ATTEND_PROFIT; }
        }

        /// <summary>
        /// 属性: 转换成普通股 
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_EXCHGE_STOCK")]
        public string C_DV_EXCHGE_STOCK
        {
            set { c_DV_EXCHGE_STOCK = value; }

            get { return c_DV_EXCHGE_STOCK; }
        }

        /// <summary>
        /// 属性: 市场代码
        /// </summary>
        [JsonProperty(PropertyName = "c_MKT_CODE")]
        public string C_MKT_CODE
        {
            set { c_MKT_CODE = value; }

            get { return c_MKT_CODE; }
        }

        /// <summary>
        /// 属性: 报价方式 
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_QUT_MOD")]
        public string C_DV_QUT_MOD
        {
            set { c_DV_QUT_MOD = value; }

            get { return c_DV_QUT_MOD; }
        }

        /// <summary>
        /// 属性：延迟支付
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_DELAY_PAY")]
        public string C_DV_DELAY_PAY
        {
            set { c_DV_DELAY_PAY = value; }

            get { return c_DV_DELAY_PAY; }
        }
    }
}


