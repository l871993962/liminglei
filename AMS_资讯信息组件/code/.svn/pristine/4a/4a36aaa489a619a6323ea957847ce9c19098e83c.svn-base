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
    /// 国债期货债券转换因子
    /// </summary>
    public class FutureFactor : AuditableParamPojo
    {
        /// <summary>
        /// 合约代码
        /// </summary>
        private string c_CONTRACT_CODE = "";

        /// <summary>
        /// 债券代码
        /// </summary>
        private string c_SEC_CODE = "";

        /// <summary>
        /// 交易市场
        /// </summary>
        private string c_MKT_CODE = "";

        /// <summary>
        /// 转换因子
        /// </summary>
        private decimal n_CONVERT_FACTOR = decimal.One;

        /// <summary>
        /// 属性: 合约代码
        /// </summary>
        [JsonProperty(PropertyName = "c_CONTRACT_CODE")]
        public string C_CONTRACT_CODE
        {
            set { c_CONTRACT_CODE = value; }

            get { return c_CONTRACT_CODE; }
        }

        /// <summary>
        /// 属性: 债券代码
        /// </summary>
        [JsonProperty(PropertyName = "c_SEC_CODE")]
        public string C_SEC_CODE
        {
            set { c_SEC_CODE = value; }

            get { return c_SEC_CODE; }
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
        /// 属性: 转换因子
        /// </summary>
        [JsonProperty(PropertyName = "n_CONVERT_FACTOR")]
        public decimal N_CONVERT_FACTOR
        {
            set { n_CONVERT_FACTOR = value; }

            get { return n_CONVERT_FACTOR; }
        }
    }
}




