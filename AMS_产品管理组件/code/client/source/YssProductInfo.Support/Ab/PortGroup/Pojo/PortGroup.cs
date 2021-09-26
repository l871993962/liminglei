using FAST.Common.Service.Pojo.Base;

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Newtonsoft.Json;

namespace YssProductInfo.Support.Ab.PortGroup.Pojo
{
    /// <summary>
    /// 创建简述：群组管理A区实体类
    /// 创建人：chenwenhai
    /// 创建时间：20140516
    /// </summary>
    public class PortGroup : AuditableParamPojo
    {
        /// <summary>
        /// 群组代码
        /// </summary>
        private string c_GROUP_CODE = "";

        /// <summary>
        /// 群组名称
        /// </summary>
        private string c_GROUP_NAME = "";


        /// <summary>
        /// 群组代码
        /// </summary>
        [JsonProperty(PropertyName = "c_GROUP_CODE")]
        public string C_GROUP_CODE
        {
            get { return c_GROUP_CODE; }
            set { c_GROUP_CODE = value; }
        }

        /// <summary>
        /// 群组名称
        /// </summary>
        [JsonProperty(PropertyName = "c_GROUP_NAME")]
        public string C_GROUP_NAME
        {
            get { return c_GROUP_NAME; }
            set { c_GROUP_NAME = value; }
        }

    }
}




