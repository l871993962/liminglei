using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Pojo.Base;
using Newtonsoft.Json;

namespace YssProductInfo.Support.DataCopy.Pojo
{
    /// <summary>
    /// STORY64614富国基金-【运维】产品参数复制功能优化，需考虑重复复制的情况
    /// add by zhaijiajia 20190222
    /// </summary>
    public class CopyDataCheck : BasePojo
    {
        /// <summary>
        /// 源组合
        /// </summary>
        private string c_SOURCE_CODE;

        /// <summary>
        /// 目标组合
        /// </summary>
        private string c_PORT_CODE;

        /// <summary>
        /// 数据项名称
        /// </summary>
        private string c_DATA_NAME;

        /// <summary>
        /// 数据项代码
        /// </summary>
        private string c_DATA_CODE;

        /// <summary>
        /// 复制状态
        /// </summary>
        private string c_COPY_STATE;

        /// <summary>
        /// 源组合
        /// </summary>
        [JsonProperty(PropertyName = "c_SOURCE_CODE")]
        public string C_SOURCE_CODE
        {
            get { return c_SOURCE_CODE; }
            set { c_SOURCE_CODE = value; }
        }

        /// <summary>
        /// 目标组合
        /// </summary>
        [JsonProperty(PropertyName = "c_PORT_CODE")]
        public string C_PORT_CODE
        {
            get { return c_PORT_CODE; }
            set { c_PORT_CODE = value; }
        }

        /// <summary>
        /// 数据项名称
        /// </summary>
        [JsonProperty(PropertyName = "c_DATA_NAME")]
        public string C_DATA_NAME
        {
            get { return c_DATA_NAME; }
            set { c_DATA_NAME = value; }
        }

        /// <summary>
        /// 数据项代码
        /// </summary>
        [JsonProperty(PropertyName = "c_DATA_CODE")]
        public string C_DATA_CODE
        {
            get { return c_DATA_CODE; }
            set { c_DATA_CODE = value; }
        }

        /// <summary>
        /// 复制状态
        /// </summary>
        [JsonProperty(PropertyName = "c_COPY_STATE")]
        public string C_COPY_STATE
        {
            get { return c_COPY_STATE; }
            set { c_COPY_STATE = value; }
        }
    }
}
