////using YssBaseCls.Fun;
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

////namespace YssData.Pojo.Mp
namespace YssSecInformation.Support.Pojo.Mp
{
    /// <summary>
    /// 估值行情
    /// 
    /// </summary>
    public class GzPrice : AuditableParamPojo
    {
        /// <summary>
        /// 组合代码
        /// </summary>
        private string c_PORT_CODE = "";

        /// <summary>
        /// 估值核算日期
        /// </summary>
        private DateTime d_VAL_ACCT;

        /// <summary>
        /// 证券代码
        /// </summary>
        private string c_VAL_PR_CODE = "";


        /// <summary>
        /// 估值行情
        /// </summary>
        private string n_VA_PRICE = "0";

        /// <summary>
        /// 报价因子
        /// </summary>
        private string d_PRICE_FACTOR = "1";

        /// <summary>
        /// 停牌信息
        /// </summary>
        private string c_SUSPENSION = "";

        /// <summary>
        /// 投资分类
        /// </summary>
        private string c_DV_INVEST_CLS = "";

        /// <summary>
        /// 发行方式
        /// </summary>
        private string c_DV_ISSUE_MODE = "";

        /// <summary>
        /// 汇率算术
        /// </summary>
        private string c_RATE_Expr = "";

         /// <summary>
        /// 类型
        /// </summary>
        private string c_PR_TYPE = "";

        /// <summary>
        /// 数据来源
        /// </summary>
        private string c_DATA_IDF = "";

        /// <summary>
        /// 组合币种
        /// </summary>
        private string c_DC_CODE = "";

        /// <summary>
        /// 交易币种显示用字段
        /// </summary>
        private string c_DC_CODE_I = "";

        /// <summary>
        /// 卖一价
        /// By Jinghehe 2014-10-13
        /// </summary>
        private string n_PRICE_SELL = "0";

        /// <summary>
        /// 买一价
        /// By Jinghehe 2014-10-13
        /// </summary>
        private string n_PRICE_BUY = "0";

        /// <summary>
        /// 收盘价
        /// By Jinghehe 2014-10-13
        /// </summary>
        private string n_PRICE_CLOSE = "0";

        /// <summary>
        /// 交易市场
        /// By Wangtangyao 2016-11-28
        /// </summary>
        private string c_MKT_CODE = " ";

        /// <summary>
        /// 行情源日期
        /// By liushifa 2017-01-03
        /// </summary>
        private DateTime d_HQYRQ;

        /// <summary>
        /// 属性: 卖一价
        /// </summary>
        [JsonProperty(PropertyName = "n_PRICE_SELL")]
        public string N_PRICE_SELL
        {
            set { n_PRICE_SELL = value; }

            get { return n_PRICE_SELL; }
        }

        /// <summary>
        /// 属性:买一价
        /// </summary>
        [JsonProperty(PropertyName = "n_PRICE_BUY")]
        public string N_PRICE_BUY
        {
            set { n_PRICE_BUY = value; }

            get { return n_PRICE_BUY; }
        }

        /// <summary>
        /// 属性: 收盘价
        /// </summary>
        [JsonProperty(PropertyName = "n_PRICE_CLOSE")]
        public string N_PRICE_CLOSE
        {
            set { n_PRICE_CLOSE = value; }

            get { return n_PRICE_CLOSE; }
        }

        /// <summary>
        /// 属性: 组合币种
        /// </summary>
        [JsonProperty(PropertyName = "c_DC_CODE")]
        public string C_DC_CODE
        {
            set { c_DC_CODE = value; }

            get { return c_DC_CODE; }
        }

        /// <summary>
        /// 属性: 交易币种显示用字段
        /// </summary>
        [JsonProperty(PropertyName = "c_DC_CODE_I")]
        public string C_DC_CODE_I
        {
            set { c_DC_CODE_I = value; }

            get { return c_DC_CODE_I; }
        }

        /// <summary>
        /// 属性: 数据来源
        /// </summary>
        [JsonProperty(PropertyName = "c_DATA_IDF")]
        public string C_DATA_IDF
        {
            set { c_DATA_IDF = value; }

            get { return c_DATA_IDF; }
        }

        /// <summary>
        /// 属性: 类型
        /// </summary>
        [JsonProperty(PropertyName = "c_PR_TYPE")]
        public string C_PR_TYPE
        {
            set { c_PR_TYPE = value; }

            get { return c_PR_TYPE; }
        }


        /// <summary>
        /// 属性: 汇率算术 
        /// </summary>
        [JsonProperty(PropertyName = "c_RATE_Expr")]
        public string C_RATE_Expr
        {
            set { c_RATE_Expr = value; }

            get { return c_RATE_Expr; }
        }

        /// <summary>
        /// 属性: 组合代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_PORT_CODE")]
        public string C_PORT_CODE
        {
            set { c_PORT_CODE = value; }

            get { return c_PORT_CODE; }
        }

        /// <summary>
        /// 属性: 估值核算日期 
        /// </summary>
        [JsonProperty(PropertyName = "d_VAL_ACCT")]
        public DateTime D_VAL_ACCT
        {
            set { d_VAL_ACCT = value; }

            get { return d_VAL_ACCT; }
        }

        /// <summary>
        /// 属性: 证券代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_VAL_PR_CODE")]
        public string C_VAL_PR_CODE
        {
            set { c_VAL_PR_CODE = value; }

            get { return c_VAL_PR_CODE; }
        }

        /// <summary>
        /// 属性: 估值行情 
        /// </summary>
        [JsonProperty(PropertyName = "n_VA_PRICE")]
        public string N_VA_PRICE
        {
            set { n_VA_PRICE = value; }

            get { return n_VA_PRICE; }
        }

        /// <summary>
        /// 属性: 报价因子 
        /// </summary>
        [JsonProperty(PropertyName = "n_PRICE_FACTOR")]
        public string D_PRICE_FACTOR
        {
            set { d_PRICE_FACTOR = value; }

            get { return d_PRICE_FACTOR; }
        }

        /// <summary>
        /// 属性: 停牌信息 
        /// </summary>
        [JsonProperty(PropertyName = "c_SUSPENSION")]
        public string C_SUSPENSION
        {
            set { c_SUSPENSION = value; }

            get { return c_SUSPENSION; }
        }

        /// <summary>
        /// 属性: 投资分类 
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_INVEST_CLS")]
        public string C_DV_INVEST_CLS
        {
            set { c_DV_INVEST_CLS = value; }

            get { return c_DV_INVEST_CLS; }
        }

        /// <summary>
        /// 属性: 发行方式 
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_ISSUE_MODE")]
        public string C_DV_ISSUE_MODE
        {
            set { c_DV_ISSUE_MODE = value; }

            get { return c_DV_ISSUE_MODE; }
        }

        /// <summary>
        /// 属性: 交易市场
        /// </summary>
        [JsonProperty(PropertyName = "c_MKT_CODE")]
        public string C_MKT_CODE
        {
            set { c_MKT_CODE = value; }

            get { return c_MKT_CODE; }
        }

        /// <summary>
        /// 属性：行情源日期
        /// </summary>
        [JsonProperty(PropertyName = "d_HQYRQ")]
        public DateTime D_HQYRQ
        {
            set { d_HQYRQ = value; }

            get { return d_HQYRQ; }
        }
    }



}


