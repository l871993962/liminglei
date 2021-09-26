using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Pojo.Base;
using Newtonsoft.Json;

namespace YssInformation.Support.Bi.Account.Pojo
{
    /// <summary>
    /// 省份城市
    /// </summary>
    public class AreaCity : BasePojo
    {
        /// <summary>
        /// 区域代码
        /// </summary>
        private string c_Code;

        /// <summary>
        /// 区域名
        /// </summary>
        private string c_Name;

        /// <summary>
        /// 区域级别
        /// </summary>
        private string c_Level;

        /// <summary>
        /// 上级区域代码
        /// </summary>
        private string c_Parent_Code;

        [JsonProperty(PropertyName = "c_Code")]
        public string C_Code
        {
            get { return c_Code; }
            set { c_Code = value; }
        }

        [JsonProperty(PropertyName = "c_Name")]
        public string C_Name
        {
            get { return c_Name; }
            set { c_Name = value; }
        }

        [JsonProperty(PropertyName = "c_Level")]
        public string C_Level
        {
            get { return c_Level; }
            set { c_Level = value; }
        }

        [JsonProperty(PropertyName = "c_Parent_Code")]
        public string C_Parent_Code
        {
            get { return c_Parent_Code; }
            set { c_Parent_Code = value; }
        }
    }
}
