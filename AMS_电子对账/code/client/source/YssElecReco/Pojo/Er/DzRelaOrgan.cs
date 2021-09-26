using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using YssPara.Pojo.Ab;
using Newtonsoft.Json;
using FAST.Common.Service.Datastructure;
using FAST.Common.Service.Pojo.Base;

namespace YssElecReco.Pojo.Er
{
    /// <summary>
    /// 组合关联机构
    /// </summary>
    ////[NodeDesc(ParentNode = "C_ORG_CODE_P", TreeNode = "C_ORG_CODE")]
    public class DzRelaOrgan : AuditableParamPojo
    {
        /// <summary>
        /// 机构代码
        /// </summary>
        private string c_ORG_CODE = "";

        /// <summary>
        /// 机构名称
        /// </summary>
        private string c_ORG_NAME = "";

        /// <summary>
        /// 机构类型
        /// </summary>
        private string c_DV_ORG_TYPE = "";

        /// <summary>
        /// 机构父代码
        /// </summary>
        private string c_ORG_NAME_P = "";

        /// <summary>
        /// 组合代码
        /// </summary>
        private string c_PORT_CODE = "";

        /// <summary>
        /// 关联机构
        /// </summary>
        private string c_DV_TYPE_CODE = "";

        /// <summary>
        /// 关联类型
        /// </summary>
        private string c_RELA_TYPE = "";

        /// <summary>
        /// 关联代码
        /// </summary>
        private string c_RELA_CODE = "";

        /// <summary>
        /// 关联机构
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_TYPE_CODE")]
        public string C_DV_TYPE_CODE
        {
            get { return c_DV_TYPE_CODE; }
            set { c_DV_TYPE_CODE = value; }
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
        /// 组合代码
        /// </summary>
        [JsonProperty(PropertyName = "c_PORT_CODE")]
        public string C_PORT_CODE
        {
            get { return c_PORT_CODE; }
            set { c_PORT_CODE = value; }
        }
        
        /// <summary>
        /// 机构父代码
        /// </summary>
        [JsonProperty(PropertyName = "c_ORG_NAME_P")]
        public string C_ORG_NAME_P
        {
            get { return c_ORG_NAME_P; }
            set { c_ORG_NAME_P = value; }
        }

        /// <summary>
        /// 机构代码
        /// </summary>
        [JsonProperty(PropertyName = "c_ORG_CODE")]
        public string C_ORG_CODE
        {
            get { return c_ORG_CODE; }
            set { c_ORG_CODE = value; }
        }

        /// <summary>
        /// 机构名称
        /// </summary>
        [JsonProperty(PropertyName = "c_ORG_NAME")]
        public string C_ORG_NAME
        {
            get { return c_ORG_NAME; }
            set { c_ORG_NAME = value; }
        }

        /// <summary>
        /// 机构类型
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_ORG_TYPE")]
        public string C_DV_ORG_TYPE
        {
            get { return c_DV_ORG_TYPE; }
            set { c_DV_ORG_TYPE = value; }
        }
    }
}
