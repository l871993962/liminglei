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

namespace YssInformation.Support.Bi.CuryPair.Pojo
{
    /// <summary>
    /// 货币对设置pojo类
    /// </summary>
    public class CuryPair : AuditableParamPojo
    {
        /// <summary>
        /// 货币对代码
        /// </summary>
        private string c_CURY_PAIR_CODE = "";

        /// <summary>
        /// 货币对名称
        /// </summary>
        private string c_CURY_PAIR_NAME = "";

        /// <summary>
        /// 基准货币
        /// </summary>
        private string c_DC_CODE_MARK = "";

        /// <summary>
        /// 计价货币
        /// </summary>
        private string c_DC_CODE_PRICE = "";

        /// <summary>
        /// 报价因子
        /// </summary>
        private decimal n_QTE_FACTO = decimal.Zero;

        /// <summary>
        /// 描述
        /// </summary>
        private string c_DESC = "";

        /// <summary>
        /// 属性: 货币对代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_CURY_PAIR_CODE")]
        public string C_CURY_PAIR_CODE
        {
            set { c_CURY_PAIR_CODE = value; }

            get { return c_CURY_PAIR_CODE; }
        }

        /// <summary>
        /// 属性: 货币对名称 
        /// </summary>
        [JsonProperty(PropertyName = "c_CURY_PAIR_NAME")]
        public string C_CURY_PAIR_NAME
        {
            set { c_CURY_PAIR_NAME = value; }

            get { return c_CURY_PAIR_NAME; }
        }

        /// <summary>
        /// 属性: 基准货币 
        /// </summary>
        [JsonProperty(PropertyName = "c_DC_CODE_MARK")]
        public string C_DC_CODE_MARK
        {
            set { c_DC_CODE_MARK = value; }

            get { return c_DC_CODE_MARK; }
        }

        /// <summary>
        /// 属性: 计价货币 
        /// </summary>
        [JsonProperty(PropertyName = "c_DC_CODE_PRICE")]
        public string C_DC_CODE_PRICE
        {
            set { c_DC_CODE_PRICE = value; }

            get { return c_DC_CODE_PRICE; }
        }

        /// <summary>
        /// 属性: 报价因子 
        /// </summary>
        [JsonProperty(PropertyName = "n_QTE_FACTO")]
        public decimal N_QTE_FACTO
        {
            set { n_QTE_FACTO = value; }

            get { return n_QTE_FACTO; }
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
        /// 证券名称拼音简拼-仅用于证券下拉框快速检索。
        /// 证券名称简拼，用于支持下拉框快速检索。STORY #37886 张绍林-20170207
        /// </summary>
        public string C_SEC_NAME_PY
        {
            get;
            set;
        }
    }
}




