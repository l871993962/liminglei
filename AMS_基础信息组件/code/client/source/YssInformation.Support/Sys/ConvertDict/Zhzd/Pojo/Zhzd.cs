using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;
using FAST.Common.Service.Pojo.Base;

namespace YssInformation.Support.Sys.ConvertDict.Zhzd.Pojo
{
    public class Zhzd : AuditableParamPojo
    {
        /// <summary>
        /// 转换字典组代码
        /// </summary>
        private string c_GROUP_CODE = "";


        /// <summary>
        /// 源代码
        /// </summary>
        private string c_S_CODE = "";


        /// <summary>
        /// 转换后代码
        /// </summary>
        private string c_T_CODE = "";

        //////////////////////////
        /// <summary>
        /// 转换字典组代码
        /// </summary>
        [JsonProperty(PropertyName = "c_GROUP_CODE")]
        public string C_GROUP_CODE
        {
            get { return c_GROUP_CODE; }
            set { c_GROUP_CODE = value; }
        }

        /// <summary>
        /// 源代码
        /// </summary>
        [JsonProperty(PropertyName = "c_S_CODE")]
        public string C_S_CODE
        {
            get { return c_S_CODE; }
            set { c_S_CODE = value; }
        }

        /// <summary>
        /// 转换后代码
        /// </summary>
        [JsonProperty(PropertyName = "c_T_CODE")]
        public string C_T_CODE
        {
            get { return c_T_CODE; }
            set { c_T_CODE = value; }
        }

    }
}
