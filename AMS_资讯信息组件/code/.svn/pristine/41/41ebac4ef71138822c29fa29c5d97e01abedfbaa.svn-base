﻿using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Pojo.Base;
using FAST.Common.Service.Interface;
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
    /// ///  chenbo
    /// 2017-06-22
    /// #42948 资讯信息管理组件化拆分
    /// ----------------------------------
    /// 证券回售基本信息
    /// </summary>
    public class SecSoldBack : EnclosurePojo
    {
        /// <summary>
        /// 证券代码
        /// </summary>
        private string c_sec_code = "";

        /// <summary>
        /// 证券上市代码
        /// </summary>
        private string c_sec_mkt_code = "";

        /// <summary>
        /// 交易市场代码
        /// </summary>
        private string c_mkt_code = "";

        /// <summary>
        /// 回售价格
        /// </summary>
        private string n_soldback_price = "0";

        /// <summary>
        /// 回售开始日期 
        /// </summary>
        private string d_soldback_begin = "1900-01-01";

        /// <summary>
        /// 回售结束日期
        /// </summary>
        private string d_soldback_end = "9998-12-31";

        /// <summary>
        /// 回售资金到账日期
        /// </summary>
        private string d_final = "9998-12-31";

      
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
        /// 属性: 证券上市代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_SEC_MKT_CODE")]
        public string C_SEC_MKT_CODE
        {
            set { c_sec_mkt_code = value; }

            get { return c_sec_mkt_code; }
        }

        /// <summary>
        /// 属性: 交易市场代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_MKT_CODE")]
        public string C_MKT_CODE
        {
            set { c_mkt_code = value; }

            get { return c_mkt_code; }
        }

        /// <summary>
        /// 属性: 回售价格 
        /// </summary>
        [JsonProperty(PropertyName = "n_SOLDBACK_PRICE")]
        public string N_SOLDBACK_PRICE
        {
            set { n_soldback_price = value; }

            get { return n_soldback_price; }
        }   

        /// <summary>
        /// 属性: 回售开始日期
        /// </summary>
        [JsonProperty(PropertyName = "d_SOLDBACK_BEGIN")]
        public string D_SOLDBACK_BEGIN
        {
            set { d_soldback_begin = value; }

            get { return d_soldback_begin; }
        }

        /// <summary>
        /// 属性: 回售结束日期 
        /// </summary>
        [JsonProperty(PropertyName = "d_SOLDBACK_END")]
        public string D_SOLDBACK_END
        {
            set { d_soldback_end = value; }

            get { return d_soldback_end; }
        }

        /// <summary>
        /// 属性: 回售资金到账日期
        /// </summary>
        [JsonProperty(PropertyName = "d_FINAL")]
        public string D_FINAL
        {
            set { d_final = value; }

            get { return d_final; }
        }
  
    }
}




