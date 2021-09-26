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

namespace YssSecInformation.Support.PlateSet.plateSub.Pojo
{
    /// <summary>
    /// 版块信息pojo类
    /// </summary>
    public class PlateSubExtend : PlateSub
    {
        /// <summary>
        /// 证券名称
        /// </summary>
        private string c_SEC_NAME = "";

        /// <summary>
        /// 属性: 证券名称 
        /// </summary>
        [JsonProperty(PropertyName = "c_SEC_NAME")]
        public string C_SEC_NAME
        {
            set { c_SEC_NAME = value; }

            get { return c_SEC_NAME; }
        }


    }
}




