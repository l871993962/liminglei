﻿using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;
////using YssInformation.Sys.Dictionary.Pojo;

////namespace YssPara.Pojo.Bi
////namespace YssInformation.Sys.Dictionary.Pojo
namespace YssInformation.Support.Sys.Dictionary.Pojo
{
    /// <summary>
    /// 证券基本信息Pojo类,带证券属性表字段
    /// </summary>
    public class SecVarExtend : SecVar
    {
        /// <summary>
        /// 品种属性的父级代码
        /// </summary>
        private string c_DA_CODE_P = "";

        /// <summary>
        /// 证券属性名称
        /// </summary>
        private string c_DA_NAME = "";

        /// <summary>
        /// 顺序
        /// add by Yuntao Lau 2015.11.16 STORY #26999
        /// </summary>
        private decimal n_ORDER = decimal.Zero;

        /// <summary>
        /// 属性: 品种属性的父级代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_DA_CODE_P")]
        public string C_DA_CODE_P
        {
            set { c_DA_CODE_P = value; }

            get { return c_DA_CODE_P; }
        }

        /// <summary>
        /// 属性: 证券属性名称 
        /// </summary>
        [JsonProperty(PropertyName = "c_DA_NAME")]
        public string C_DA_NAME
        {
            set { c_DA_NAME = value; }

            get { return c_DA_NAME; }
        }

        /// <summary>
        /// 属性: 顺序
        /// </summary>
        [JsonProperty(PropertyName = "n_ORDER")]
        public decimal N_ORDER
        {
            set { n_ORDER = value; }

            get { return n_ORDER; }
        }
    }
}




