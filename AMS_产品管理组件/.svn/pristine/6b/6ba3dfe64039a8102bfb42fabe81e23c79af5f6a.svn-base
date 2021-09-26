using FAST.Common.Service.Pojo.Base;

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Newtonsoft.Json;
using FAST.Common.Service.Datastructure;

namespace YssProductInfo.Support.Ab.AssetsTree.Pojo
{
    /// <summary>
    /// 资产属性结构B区pojo类
    /// </summary>
    [NodeDesc(ParentNode = "C_TR_CODE_R", TreeNode = "C_PORT_CODE")]
    public class AssetsTree_B : AuditableParamPojo
    {
        /// <summary>
        /// 结构代码
        /// </summary>
        private string c_TR_CODE = "";

        /// <summary>
        /// 组合代码
        /// </summary>
        private string c_PORT_CODE = "";

        /// <summary>
        /// 顶级节点代码
        /// </summary>
        private string c_TR_CODE_R = "";

        /// <summary>
        /// add by zhoushuhang 2018-03-09 STORY49928产品树形结构界面优化
        /// 判断是否是父级节点。1代表是父级节点，0代表不是父级节点
        /// </summary>
        private int isParent = 0;

        /// <summary>
        /// 属性: 结构代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_TR_CODE")]
        public string C_TR_CODE
        {
            set { c_TR_CODE = value; }

            get { return c_TR_CODE; }
        }

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
        /// 属性: 顶级节点代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_TR_CODE_R")]
        public string C_TR_CODE_R
        {
            set { c_TR_CODE_R = value; }

            get { return c_TR_CODE_R; }
        }

        /// <summary>
        /// add by zhoushuhang 2018-03-09 STORY49928产品树形结构界面优化
        /// 是否是父级节点
        /// </summary>
        [JsonProperty(PropertyName = "isParent")]
        public int IsParent
        {
            get { return isParent; }
            set { isParent = value; }
        }
    }
}


