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
    /// 计费证券信息
    /// added by HeLiang.2016-09-14.STORY #31596 运营费用-支持资产净值扣不计费证券需求
    /// </summary>
    public class SecBaseJf : EnclosurePojo
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
        /// 证券品种代码
        /// </summary>
        private string c_sec_var_code = "";

        /// <summary>
        /// 计费证券（是否计提：0-否，1-是）
        /// </summary>
        private string c_SFJT = "";

        /// <summary>
        /// 组合代码
        /// </summary>
        private string c_PORT_CODE = "";

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
        /// 属性: 证券上市代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_SEC_MKT_CODE")]
        public string C_SEC_MKT_CODE
        {
            set { c_sec_mkt_code = value; }

            get { return c_sec_mkt_code; }
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
        /// 属性: 计费证券信息 （是否计提）
        /// </summary>
        [JsonProperty(PropertyName = "c_SFJT")]
        public string C_SFJT
        {
            set { c_SFJT = value; }

            get { return c_SFJT; }
        }

        /// <summary>
        /// 属性: 组合代码
        /// </summary>
        [JsonProperty(PropertyName = "c_PORT_CODE")]
        public string C_PORT_CODE
        {
            get { return c_PORT_CODE; }
            set { c_PORT_CODE = value; }
        }

    }
}
