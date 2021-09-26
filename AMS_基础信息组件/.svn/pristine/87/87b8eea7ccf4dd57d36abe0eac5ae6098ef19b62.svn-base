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
using FAST.Common.Service.Datastructure;

namespace YssInformation.Support.Bi.Market.Pojo
{
    /// <summary>
    /// 交易市场信息pojo类,有树形结构
    /// </summary>
    [NodeDesc(ParentNode = "C_PARAENT_CODE", TreeNode = "C_MKT_CODE")]
    public class MktExtend : Mkt
    {
        /// <summary>
        /// 父节点
        /// </summary>
        private string c_PARAENT_CODE = "";

        /// <summary>
        /// 属性: 父节点 
        /// </summary>
        [JsonProperty(PropertyName = "c_PARAENT_CODE")]
        public string C_PARAENT_CODE
        {
            set { c_PARAENT_CODE = value; }

            get { return c_PARAENT_CODE; }
        }
    }
}




