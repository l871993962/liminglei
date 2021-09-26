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

namespace YssSecInformation.Support.Sv.Pojo
{
    /// <summary>
    /// 期货保证金调整信息
    /// add by chenwenhai 20140705
    /// </summary>
    public class SecBaseQhbzj : BasePojo
    {
        /// <summary>
        /// 证券代码
        /// </summary>
        private string c_SEC_CODE = "";

        /// <summary>
        /// 保证金比例
        /// </summary>
        private decimal n_RATE = decimal.Zero;

        /// <summary>
        /// 固定保证金
        /// </summary>
        private decimal n_PRICE_ISSUE = decimal.Zero;

        /// <summary>
        /// 启用日期
        /// </summary>
        private DateTime d_START;

        /// <summary>
        /// 证券代码
        /// </summary>
        [JsonProperty(PropertyName = "c_SEC_CODE")]
        public string C_SEC_CODE
        {
            get { return c_SEC_CODE; }
            set { c_SEC_CODE = value; }
        }

        /// <summary>
        /// 保证金比例
        /// </summary>
        [JsonProperty(PropertyName = "n_RATE")]
        public decimal N_RATE
        {
            get { return n_RATE; }
            set { n_RATE = value; }
        }

        /// <summary>
        /// 固定保证金
        /// </summary>
        [JsonProperty(PropertyName = "n_PRICE_ISSUE")]
        public decimal N_PRICE_ISSUE
        {
            get { return n_PRICE_ISSUE; }
            set { n_PRICE_ISSUE = value; }
        }

        /// <summary>
        /// 启用日期
        /// </summary>
        [JsonProperty(PropertyName = "d_START")]
        public DateTime D_START
        {
            get { return d_START; }
            set { d_START = value; }
        }
    }
}




