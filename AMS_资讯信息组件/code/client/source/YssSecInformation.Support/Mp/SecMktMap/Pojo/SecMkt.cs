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
namespace YssSecInformation.Support.Mp.SecMktMap.Pojo
{
    /// <summary>
    /// 证券行情Pojo类
    /// added by ll 20120926
    /// </summary>
    public class SecMkt : AuditableParamPojo
    {
        /// <summary>
        /// 证券内码
        /// </summary>
        private string c_SEC_CODE = "";

        /// <summary>
        /// 行情来源
        /// </summary>
        private string c_DV_PLAT = "";

        /// <summary>
        /// 行情日期
        /// </summary>
        private string d_MKT = "";

        /// <summary>
        /// 行情分类
        /// </summary>
        private string c_MKT_CLS = "";

        /// <summary>
        /// 行情时间
        /// </summary>
        private string c_MKT_TIME = "";

        /// <summary>
        /// 最新价
        /// </summary>
        private string n_PRICE_NEW = "0";

        /// <summary>
        /// 买入价
        /// </summary>
        private string n_PRICE_BUY = "0";

        /// <summary>
        /// 卖出价
        /// </summary>
        private string n_PRICE_SELL = "0";

        /// <summary>
        /// 最高价
        /// </summary>
        private string n_PRICE_HIGH = "0";

        /// <summary>
        /// 最低价
        /// </summary>
        private string n_PRICE_LOW = "0";

        /// <summary>
        /// 收盘价
        /// </summary>
        private string n_PRICE_CLOSE = "0";

        /// <summary>
        ///  开盘价
        /// </summary>
        private string n_PRICE_OPEN = "0";

        /// <summary>
        /// 平均价
        /// </summary>
        private string n_PRICE_AVG = "0";

        /// <summary>
        /// 结算价
        /// add by liyanjun 2016-4-29 STORY29425个股期权行情获取收盘价
        /// </summary>
        private string n_PRICE_SETT = "0";

        /// <summary>
        /// 公告日期
        /// </summary>
        private string d_PUB = "";

        /// <summary>
        ///  昨日收盘价 STORY #25937 交易所行情清算需要支持保存昨日收盘价 guoguangyi 2015-10-15
        /// </summary>
        private string n_PRICE_ZRCLOSE = "0";

        /// <summary>
        /// 描述
        /// </summary>
        private string c_DESC = "";

        /// <summary>
        /// 数据来源
        /// </summary>
        private string c_DATA_IDF = "";

        /// <summary>
        /// 成交数量 Added by dingxukun STORY 34828
        /// </summary>
        private string n_TD_AMOUNT = "0";

        /// <summary>
        /// 成交金额 Added by dingxukun STORY 34828
        /// </summary>
        private string n_TD_MONEY = "0";

        /// <summary>
        /// 行情状态
        /// </summary>
        private string c_HQZT_CODE = "";

        /// <summary>
        /// 投资组合
        /// STORY #35336 （嘉实QD需求）原4.0需求：26150 需求北京-[嘉实基金]QDII资产管理系统[高]2015928001（QDII汇率导入需求)
        /// </summary>
        private string c_PORT_CODE = "";

        /// <summary>
        /// 属性
        /// STORY #35353 （嘉实QD需求）原4.0需求：10354 【QDV4嘉实2011年11月09日01_A】估值检查步骤中，增加对除权股票的行情状态的判断
        /// </summary>
        private string c_MKT_STATUS = "";

        /// <summary>
        /// 属性
        /// STORY #35353 （嘉实QD需求）原4.0需求：10354 【QDV4嘉实2011年11月09日01_A】估值检查步骤中，增加对除权股票的行情状态的判断
        /// </summary>
        private string n_AMOUNT_TOTAL = "";

        /// <summary>
        /// 属性
        /// STORY #35353 （嘉实QD需求）原4.0需求：10354 【QDV4嘉实2011年11月09日01_A】估值检查步骤中，增加对除权股票的行情状态的判断
        /// </summary>
        private string n_PRICE_TOTAL = "";

        /// <summary>
        /// 属性 
        /// STORY #35353 （嘉实QD需求）原4.0需求：10354 【QDV4嘉实2011年11月09日01_A】估值检查步骤中，增加对除权股票的行情状态的判断
        /// </summary>
        [JsonProperty(PropertyName = "c_MKT_STATUS")]
        public string C_MKT_STATUS
        {
            set { c_MKT_STATUS = value; }

            get { return c_MKT_STATUS; }
        }

        /// <summary>
        /// 属性 
        /// STORY #35353 （嘉实QD需求）原4.0需求：10354 【QDV4嘉实2011年11月09日01_A】估值检查步骤中，增加对除权股票的行情状态的判断
        /// </summary>
        [JsonProperty(PropertyName = "n_AMOUNT_TOTAL")]
        public string N_AMOUNT_TOTAL
        {
            set { n_AMOUNT_TOTAL = value; }

            get { return n_AMOUNT_TOTAL; }
        }

        /// <summary>
        /// 属性 
        /// STORY #35353 （嘉实QD需求）原4.0需求：10354 【QDV4嘉实2011年11月09日01_A】估值检查步骤中，增加对除权股票的行情状态的判断
        /// </summary>
        [JsonProperty(PropertyName = "n_PRICE_TOTAL")]
        public string N_PRICE_TOTAL
        {
            set { n_PRICE_TOTAL = value; }

            get { return n_PRICE_TOTAL; }
        }

        /// <summary>
        /// 属性: 投资组合 
        /// STORY #35336 （嘉实QD需求）原4.0需求：26150 需求北京-[嘉实基金]QDII资产管理系统[高]2015928001（QDII汇率导入需求)
        /// </summary>
        [JsonProperty(PropertyName = "c_PORT_CODE")]
        public string C_PORT_CODE
        {
            set { c_PORT_CODE = value; }

            get { return c_PORT_CODE; }
        }

        /// <summary>
        /// 属性: 行情状态 
        /// </summary>
        [JsonProperty(PropertyName = "c_HQZT_CODE")]
        public string C_HQZT_CODE
        {
            set { c_HQZT_CODE = value; }

            get { return c_HQZT_CODE; }
        }

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
        /// 属性: 行情来源 
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_PLAT")]
        public string C_DV_PLAT
        {
            set { c_DV_PLAT = value; }

            get { return c_DV_PLAT; }
        }

        /// <summary>
        /// 属性: 行情日期 
        /// </summary>
        [JsonProperty(PropertyName = "d_MKT")]
        public string D_MKT
        {
            set { d_MKT = value; }

            get { return d_MKT; }
        }

        /// <summary>
        /// 属性: 行情分类 
        /// </summary>
        [JsonProperty(PropertyName = "c_MKT_CLS")]
        public string C_MKT_CLS
        {
            set { c_MKT_CLS = value; }

            get { return c_MKT_CLS; }
        }

        /// <summary>
        /// 属性: 行情时间 
        /// </summary>
        [JsonProperty(PropertyName = "c_MKT_TIME")]
        public string C_MKT_TIME
        {
            set { c_MKT_TIME = value; }

            get { return c_MKT_TIME; }
        }

        /// <summary>
        /// 属性: 最新价 
        /// </summary>
        [JsonProperty(PropertyName = "n_PRICE_NEW")]
        public string N_PRICE_NEW
        {
            set { n_PRICE_NEW = value; }

            get { return n_PRICE_NEW; }
        }

        /// <summary>
        /// 属性: 买入价 
        /// </summary>
        [JsonProperty(PropertyName = "n_PRICE_BUY")]
        public string N_PRICE_BUY
        {
            set { n_PRICE_BUY = value; }

            get { return n_PRICE_BUY; }
        }

        /// <summary>
        /// 属性: 卖出价 
        /// </summary>
        [JsonProperty(PropertyName = "n_PRICE_SELL")]
        public string N_PRICE_SELL
        {
            set { n_PRICE_SELL = value; }

            get { return n_PRICE_SELL; }
        }

        /// <summary>
        /// 属性: 最高价 
        /// </summary>
        [JsonProperty(PropertyName = "n_PRICE_HIGH")]
        public string N_PRICE_HIGH
        {
            set { n_PRICE_HIGH = value; }

            get { return n_PRICE_HIGH; }
        }

        /// <summary>
        /// 属性: 最低价 
        /// </summary>
        [JsonProperty(PropertyName = "n_PRICE_LOW")]
        public string N_PRICE_LOW
        {
            set { n_PRICE_LOW = value; }

            get { return n_PRICE_LOW; }
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
        /// 属性: 开盘价 
        /// </summary>
        [JsonProperty(PropertyName = "n_PRICE_OPEN")]
        public string N_PRICE_OPEN
        {
            set { n_PRICE_OPEN = value; }

            get { return n_PRICE_OPEN; }
        }

        /// <summary>
        /// 属性: 平均价 
        /// </summary>
        [JsonProperty(PropertyName = "n_PRICE_AVG")]
        public string N_PRICE_AVG
        {
            set { n_PRICE_AVG = value; }

            get { return n_PRICE_AVG; }
        }

        /// <summary>
        /// 属性: 结算价 
        /// </summary>
        [JsonProperty(PropertyName = "n_PRICE_SETT")]
        public string N_PRICE_SETT
        {
            set { n_PRICE_SETT = value; }

            get { return n_PRICE_SETT; }
        }

        /// <summary>
        /// 属性: 公告日期 
        /// </summary>
        [JsonProperty(PropertyName = "d_PUB")]
        public string D_PUB
        {
            set { d_PUB = value; }

            get { return d_PUB; }
        }

        /// <summary>
        /// 属性: 描述 
        /// </summary>
        [JsonProperty(PropertyName = "c_DESC")]
        public string C_DESC
        {
            set { c_DESC = value; }

            get { return c_DESC; }
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
        /// 成交数量
        /// </summary>
        [JsonProperty(PropertyName = "n_TD_AMOUNT")]
        public string N_TD_AMOUNT
        {
            set { n_TD_AMOUNT = value; }
            get { return n_TD_AMOUNT; }
        }

        /// <summary>
        /// 成交金额
        /// </summary>
        [JsonProperty(PropertyName = "n_TD_MONEY")]
        public string N_TD_MONEY
        {
            set { n_TD_MONEY = value; }
            get { return n_TD_MONEY; }
        }

        /// <summary>
        /// 属性：昨日收盘价  STORY #25937 交易所行情清算需要支持保存昨日收盘价 guoguangyi 2015-10-15
        /// </summary>
        [JsonProperty(PropertyName = "n_PRICE_ZRCLOSE")]
        public string N_PRICE_ZRCLOSE
        {
            get { return n_PRICE_ZRCLOSE; }
            set { n_PRICE_ZRCLOSE = value; }
        }

    }
}
