using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Pojo.Base;
using Newtonsoft.Json;

namespace YssSyncData.Pojo.Base
{
    /// <summary>
    /// 同步模块设置pojo类
    /// </summary>
    public class SyncModule : BasePojo
    {
        /// <summary>
        /// 监听模块code
        /// </summary>
        private string c_MODULE_CODE = "";

        /// <summary>
        /// 监听模块名称
        /// </summary>
        private string c_MODULE_NAME = "";

        /// <summary>
        /// 是否已配置
        /// </summary>
        private string c_CONFIGURED = "";

        /// <summary>
        /// 监听模块code
        /// </summary>
        [JsonProperty(PropertyName = "c_MODULE_CODE")]
        public string C_MODULE_CODE
        {
            get { return c_MODULE_CODE; }
            set { c_MODULE_CODE = value; }
        }

        /// <summary>
        /// 监听模块名称
        /// </summary>
        [JsonProperty(PropertyName = "c_MODULE_NAME")]
        public string C_MODULE_NAME
        {
            get { return c_MODULE_NAME; }
            set { c_MODULE_NAME = value; }
        }

        /// <summary>
        /// 是否已配置
        /// </summary>
        [JsonProperty(PropertyName = "c_CONFIGURED")]
        public string C_CONFIGURED
        {
            get { return c_CONFIGURED; }
            set { c_CONFIGURED = value; }
        }

    }
}
