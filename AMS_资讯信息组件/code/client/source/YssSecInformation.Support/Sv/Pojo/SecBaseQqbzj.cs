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
    /// add by Jinghehe 20140730
    /// </summary>
    public class SecBaseQqbzj : SecBaseQhbzj
    {
        /// <summary>
        /// 保证金比例1
        /// </summary>
        private decimal n_RATIO = decimal.Zero;

        /// <summary>
        /// 保证金比例1
        /// </summary>
        [JsonProperty(PropertyName = "n_RATIO")]
        public decimal N_RATIO
        {
            get { return n_RATIO; }
            set { n_RATIO = value; }
        }
    }
}




