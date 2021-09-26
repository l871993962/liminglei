using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Pojo.Base;
using FAST.Common.Service.Datastructure;
using Newtonsoft.Json;

namespace YssSztTool.Pojo.Para
{
    /// <summary>
    /// 词汇管理Pojo
    /// </summary>
    /// by lihaizhi
    [NodeDesc(ParentNode = "C_DV_CODE", TreeNode = "C_DV_TYPE")]
    public class ErVoc : BasePojo
    {
        /// <summary>
        /// 词汇代码
        /// </summary>
        private string c_DV_CODE = "";

        /// <summary>
        /// 词汇名称
        /// </summary>
        private string c_DV_NAME = "";

        /// <summary>
        /// 词汇类型
        /// </summary>
        private string c_DV_TYPE = "";

        /// <summary>
        /// 序号
        /// </summary>
        private string n_ORDER = "";

        /// <summary>
        /// 描述
        /// </summary>
        private string c_DESC = "";

        /// <summary>
        /// 所属权限机构
        /// </summary>
        private string c_AUTH_ORG_CODE = "";

        /// <summary>
        /// 属性: 词汇代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_CODE")]
        public string C_DV_CODE
        {
            set { c_DV_CODE = value; }

            get { return c_DV_CODE; }
        }

        /// <summary>
        /// 属性: 词汇名称 
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_NAME")]
        public string C_DV_NAME
        {
            set { c_DV_NAME = value; }

            get { return c_DV_NAME; }
        }

        /// <summary>
        /// 属性: 词汇类型 
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_TYPE")]
        public string C_DV_TYPE
        {
            set { c_DV_TYPE = value; }

            get { return c_DV_TYPE; }
        }

        /// <summary>
        /// 属性: 序号 
        /// </summary>
        [JsonProperty(PropertyName = "n_ORDER")]
        public string N_ORDER
        {
            set { n_ORDER = value; }

            get { return n_ORDER; }
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
        /// 所属部门代码
        /// </summary>
        [JsonProperty(PropertyName = "c_AUTH_ORG_CODE")]
        public string C_AUTH_ORG_CODE
        {
            set
            {
                c_AUTH_ORG_CODE = value;
            }

            get
            {
                return c_AUTH_ORG_CODE;
            }
        }
    }
}
