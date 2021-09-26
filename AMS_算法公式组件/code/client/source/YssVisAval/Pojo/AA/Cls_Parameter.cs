using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Exceptions;


using FAST.Core.Context;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Newtonsoft.Json;

namespace YssVisAval.Pojo.AA
{
    /// <summary>
    /// 高级算法参数类
    /// </summary>
    public class Cls_Parameter : BasePojo
    {
        /// <summary>
        /// 参数代码
        /// </summary>
        [JsonProperty(PropertyName = "C_PARA_CODE")]
        public string C_PARA_CODE { get; set; }

        /// <summary>
        /// 参数名称
        /// </summary>
        [JsonProperty(PropertyName = "C_PARA_NAME")]
        public string C_PARA_NAME { get; set; }

        /// <summary>
        /// 参数类型
        /// </summary>
        [JsonProperty(PropertyName = "C_DV_PARA_TYPE")]
        public string C_DV_PARA_TYPE { get; set; }

        /// <summary>
        /// 控件类型
        /// </summary>
        [JsonProperty(PropertyName = "C_DV_CTL_TYPE")]
        public string C_DV_CTL_TYPE { get; set; }

        /// <summary>
        /// 返回值类型
        /// </summary>
        [JsonProperty(PropertyName = "C_DV_VALUE_TYPE")]
        public string C_DV_VALUE_TYPE { get; set; }

        /// <summary>
        /// 控件属性
        /// </summary>
        [JsonProperty(PropertyName = "C_CTL_ATTR")]
        public string C_CTL_ATTR { get; set; }

        /// <summary>
        /// 控件数据源
        /// </summary>
        [JsonProperty(PropertyName = "C_DS_ATTR")]
        public string C_DS_ATTR { get; set; }

        /// <summary>
        /// 扩展条件
        /// </summary>
        [JsonProperty(PropertyName = "C_EXPAND_COND")]
        public string C_EXPAND_COND { get; set; }

        /// <summary>
        /// 描述
        /// </summary>
        [JsonProperty(PropertyName = "C_DESC")]
        public string C_DESC { get; set; }
    }
}


