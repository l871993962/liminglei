using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;
using FAST.Common.Service.Pojo.Base;
using FAST.Common.Service.Datastructure;

namespace YssInformation.Support.Bi.AccountTree.Pojo
{
    /// <summary>
    /// 组合关联信息pojo类
    /// </summary>
    [NodeDesc(ParentNode = "C_NODE_CODE_P", TreeNode = "C_NODE_CODE")]
    public class AccountTreeA : AuditableParamPojo
    {
        /// <summary>
        /// 节点代码
        /// </summary>
        private string c_NODE_CODE = "";

        /// <summary>
        /// 节点名称
        /// </summary>
        private string c_NODE_NAME = "";

        /// <summary>
        /// 父级节点
        /// </summary>
        private string c_NODE_CODE_P = "";

        /// <summary>
        /// 岗位代码
        /// </summary>
        private string c_POST_CODE = "";

        /// <summary>
        /// 属性: 节点代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_NODE_CODE")]
        public string C_NODE_CODE
        {
            set { c_NODE_CODE = value; }

            get { return c_NODE_CODE; }
        }

        /// <summary>
        /// 属性: 节点名称 
        /// </summary>
        [JsonProperty(PropertyName = "c_NODE_NAME")]
        public string C_NODE_NAME
        {
            set { c_NODE_NAME = value; }

            get { return c_NODE_NAME; }
        }

        /// <summary>
        /// 属性:父级节点
        /// </summary>
        [JsonProperty(PropertyName = "c_NODE_CODE_P")]
        public string C_NODE_CODE_P
        {
            set { c_NODE_CODE_P = value; }

            get { return c_NODE_CODE_P; }
        }

        /// <summary>
        /// 属性: 岗位代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_POST_CODE")]
        public string C_POST_CODE
        {
            set { c_POST_CODE = value; }

            get { return c_POST_CODE; }
        }

       
    }
}
