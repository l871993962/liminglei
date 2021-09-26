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
    /// ///  chenbo
    /// 2017-06-22
    /// #42948 资讯信息管理组件化拆分
    /// ----------------------------------
    /// 指数成分券信息实体类
    /// </summary>
    public class IndexStock : EnclosurePojo
    {
        /// <summary>
        /// 指数代码
        /// </summary>
        private string c_INDEX_CODE = "";

        /// <summary>
        /// 成分券内码
        /// </summary>
        private string c_SEC_CODE = "";

        /// <summary>
        /// 成分券上市代码
        /// </summary>
        private string c_SEC_MKT_CODE = "";

        /// <summary>
        /// 启用日期
        /// </summary>
        private DateTime d_BEGIN;

        /// <summary>
        /// 描述信息
        /// </summary>
        private string c_DESC = "";

        /// <summary>
        /// 指数代码
        /// </summary>
        [JsonProperty(PropertyName = "c_INDEX_CODE")]
        public string C_INDEX_CODE
        {
            get { return c_INDEX_CODE; }
            set { c_INDEX_CODE = value; }
        }

        /// <summary>
        /// 成分券内码
        /// </summary>
        [JsonProperty(PropertyName = "c_SEC_CODE")]
        public string C_SEC_CODE
        {
            get { return c_SEC_CODE; }
            set { c_SEC_CODE = value; }
        }

        /// <summary>
        /// 成分券上市代码
        /// </summary>
        [JsonProperty(PropertyName = "c_SEC_MKT_CODE")]
        public string C_SEC_MKT_CODE
        {
            get { return c_SEC_MKT_CODE; }
            set { c_SEC_MKT_CODE = value; }
        }

        /// <summary>
        /// 启用日期
        /// </summary>
        [JsonProperty(PropertyName = "d_BEGIN")]
        public DateTime D_BEGIN
        {
            get { return d_BEGIN; }
            set { d_BEGIN = value; }
        }

        /// <summary>
        /// 描述信息
        /// </summary>
        [JsonProperty(PropertyName = "c_DESC")]
        public string C_DESC
        {
            get { return c_DESC; }
            set { c_DESC = value; }
        }
    }
}




