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

namespace YssInformation.Support.Statepojo
{
    /// <summary>
    ///状态Pojo
    /// Added by huangsq 20160728 STORY #32244 词汇资源可控制是否显示
    /// </summary>
    public class StatePojo : BasePojo
    {
        /// <summary>
        ///  显示状态
        /// </summary>
        protected string c_DV_STATE = "";

        /// <summary>
        /// 显示状态
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_STATE")]
        public string C_DV_STATE
        {
            set { c_DV_STATE = value; }
            get { return c_DV_STATE; }
        }

    }
}




