﻿using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Exceptions;


using FAST.Core.Context;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Newtonsoft.Json;

namespace YssSecInformation.Support.Aa.Etf.Pojo
{
    /// <summary>
    /// ETF基本信息pojo类
    /// </summary>
    public class Etf : AuditableParamPojo
    {
        /// <summary>
        /// 投资组合
        /// </summary>
        private string c_PORT_CODE = "";

        /// <summary>
        /// 交易市场
        /// </summary>
        private string c_MKT_CODE = "";

        /// <summary>
        /// 申赎席位
        /// </summary>
        private string c_TD_CHAN_CODE = "";

        ////zhoushuhang 2016-3-8 在ETF参数设置界面中删除补票方式字段。删除参数补票方式。
        /////// <summary>
        /////// 补票方式
        /////// </summary>
        ////private string c_DV_SUPPLY_MODE = "";

        /// <summary>
        /// 台账显示模式
        /// </summary>
        private string c_DV_BOOK_MODE = "";

        /// <summary>
        /// 申赎代码
        /// </summary>
        private string c_SR_CODE = "";

        /// <summary>
        /// 交易代码
        /// </summary>
        private string c_TRADE_CODE = "";

        /// <summary>
        /// 资金结算代码
        /// </summary>
        private string c_SF_CODE = "";

        /// <summary>
        /// 描述
        /// </summary>
        private string c_DESC = "";

        /// <summary>
        /// 基准份额
        /// </summary>
        private string n_BASE_AMOUNT = "";

        /////// <summary>
        /////// 开始日期
        /////// </summary>
        ////private string d_BEGIN = "";

        /////// <summary>
        /////// 结束日期
        /////// </summary>
        ////private string d_END = "";

        /// <summary>
        /// 开始日期
        /// </summary>
        private DateTime d_BEGIN;

        /// <summary>
        /// 结束日期
        /// </summary>
        private DateTime d_END;

        /// <summary>
        /// 确认天数
        /// </summary>
        private string n_CONFIRM = "";

        /// <summary>
        /// ETF类型
        /// </summary>
        private string c_ETF_TYPE = "";

        /// <summary>
        /// 申赎汇率
        /// </summary>
        private string c_RATE_SR = "";

        /// <summary>
        /// 补票汇率
        /// </summary>
        private string c_RATE_SUPPLY = "";

        /// <summary>
        /// 股票篮溢价比 
        /// </summary>
        private string n_INFER_PROTION = "";


        /// <summary>
        /// 席位类别 By Jinghehe 2014-8-14
        /// </summary>
        private string c_DV_TYPE_CODE = "";


        /// <summary>
        /// 席位类别 By Jinghehe 2014-8-14
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_TYPE_CODE")]
        public string C_DV_TYPE_CODE
        {
            get { return c_DV_TYPE_CODE; }
            set { c_DV_TYPE_CODE = value; }
        }


        /// <summary>
        /// 属性: 投资组合 
        /// </summary>
        [JsonProperty(PropertyName = "c_PORT_CODE")]
        public string C_PORT_CODE
        {
            set { c_PORT_CODE = value; }

            get { return c_PORT_CODE; }
        }

        /// <summary>
        /// 属性: 股票篮溢价比 
        /// </summary>
        [JsonProperty(PropertyName = "n_INFER_PROTION")]
        public string N_INFER_PROTION
        {
            set { n_INFER_PROTION = value; }

            get { return n_INFER_PROTION; }
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
        /// 属性: 申赎席位 
        /// </summary>
        [JsonProperty(PropertyName = "c_TD_CHAN_CODE")]
        public string C_TD_CHAN_CODE
        {
            set { c_TD_CHAN_CODE = value; }

            get { return c_TD_CHAN_CODE; }
        }

        ////zhoushuhang 2016-3-8 在ETF参数设置界面中删除补票方式字段。删除参数补票方式。
        ////<summary>
        ////属性: 补票方式 
        ////</summary>
        ////[JsonProperty(PropertyName = "c_DV_SUPPLY_MODE")]
        ////public string C_DV_SUPPLY_MODE
        ////{
           //// set { c_DV_SUPPLY_MODE = value; }
        ////
           //// get { return c_DV_SUPPLY_MODE; }
        ////}

        /// <summary>
        /// 属性: 台账显示模式 
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_BOOK_MODE")]
        public string C_DV_BOOK_MODE
        {
            set { c_DV_BOOK_MODE = value; }

            get { return c_DV_BOOK_MODE; }
        }

        /// <summary>
        /// 属性: 申赎代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_SR_CODE")]
        public string C_SR_CODE
        {
            set { c_SR_CODE = value; }

            get { return c_SR_CODE; }
        }

        /// <summary>
        /// 属性: 交易代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_TRADE_CODE")]
        public string C_TRADE_CODE
        {
            set { c_TRADE_CODE = value; }

            get { return c_TRADE_CODE; }
        }

        /// <summary>
        /// 属性: 资金结算代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_SF_CODE")]
        public string C_SF_CODE
        {
            set { c_SF_CODE = value; }

            get { return c_SF_CODE; }
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
        /// 属性: 基准份额 
        /// </summary>
        [JsonProperty(PropertyName = "n_BASE_AMOUNT")]
        public string N_BASE_AMOUNT
        {
            set { n_BASE_AMOUNT = value; }

            get { return n_BASE_AMOUNT; }
        }

        /// <summary>
        /// 属性: 开始日期 
        /// </summary>
        [JsonProperty(PropertyName = "startUseDate")]
        public DateTime D_BEGIN
        {
            set { d_BEGIN = value; }

            get { return d_BEGIN; }
        }

        /// <summary>
        /// 属性: 结束日期 
        /// </summary>
        [JsonProperty(PropertyName = "endUseDate")]
        public DateTime D_END
        {
            set { d_END = value; }

            get { return d_END; }
        }

        /// <summary>
        /// 属性: 确认天数 
        /// </summary>
        [JsonProperty(PropertyName = "n_CONFIRM")]
        public string N_CONFIRM
        {
            set { n_CONFIRM = value; }

            get { return n_CONFIRM; }
        }

        /// <summary>
        /// 属性: ETF类型 
        /// </summary>
        [JsonProperty(PropertyName = "c_ETF_TYPE")]
        public string C_ETF_TYPE
        {
            set { c_ETF_TYPE = value; }

            get { return c_ETF_TYPE; }
        }

        /// <summary>
        /// 属性: 申赎汇率 
        /// </summary>
        [JsonProperty(PropertyName = "c_RATE_SR")]
        public string C_RATE_SR
        {
            set { c_RATE_SR = value; }

            get { return c_RATE_SR; }
        }

        /// <summary>
        /// 属性: 补票汇率 
        /// </summary>
        [JsonProperty(PropertyName = "c_RATE_SUPPLY")]
        public string C_RATE_SUPPLY
        {
            set { c_RATE_SUPPLY = value; }

            get { return c_RATE_SUPPLY; }
        }




    }
}




