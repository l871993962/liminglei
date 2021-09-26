using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;
using FAST.Common.Service.Pojo.Base;

namespace YssInformation.Support.Bi.Account.Pojo
{
    /// <summary>
    /// 组合关联信息pojo类
    /// </summary>
    public class PortRela : AuditableParamPojo
    {
        /// <summary>
        /// 组合代码
        /// </summary>
        private string c_PORT_CODE = "";

        /// <summary>
        /// 关联类型
        /// </summary>
        private string c_RELA_TYPE = "";

        /// <summary>
        /// 关联代码
        /// </summary>
        private string c_RELA_CODE = "";

        /// <summary>
        /// 类型代码
        /// </summary>
        private string c_DV_TYPE_CODE = "";

        /// <summary>
        ///  描述
        /// </summary>
        private string c_DESC = "";

        /// <summary>
        ///  资金账号
        /// </summary>
        private string c_CA_CODE = "";

        /// <summary>
        /// 属性: 组合代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_PORT_CODE")]
        public string C_PORT_CODE
        {
            set { c_PORT_CODE = value; }

            get { return c_PORT_CODE; }
        }

        /// <summary>
        /// 属性: 关联类型 
        /// </summary>
        [JsonProperty(PropertyName = "c_RELA_TYPE")]
        public string C_RELA_TYPE
        {
            set { c_RELA_TYPE = value; }

            get { return c_RELA_TYPE; }
        }

        /// <summary>
        /// 属性: 关联代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_RELA_CODE")]
        public string C_RELA_CODE
        {
            set { c_RELA_CODE = value; }

            get { return c_RELA_CODE; }
        }

        /// <summary>
        /// 属性: 类型代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_TYPE_CODE")]
        public string C_DV_TYPE_CODE
        {
            set { c_DV_TYPE_CODE = value; }

            get { return c_DV_TYPE_CODE; }
        }

        /// <summary>
        /// 属性: 描述 
        /// </summary>
        [JsonProperty(PropertyName = "c_DESC")]
        public string C_DESC
        {
            set { c_DESC = value; }

            get { return c_DESC; }
        }

        /// <summary>
        /// 属性: 资金账号 
        /// </summary>
        [JsonProperty(PropertyName = "c_CA_CODE")]
        public string C_CA_CODE
        {
            set { c_CA_CODE = value; }

            get { return c_CA_CODE; }
        }


    }
}
