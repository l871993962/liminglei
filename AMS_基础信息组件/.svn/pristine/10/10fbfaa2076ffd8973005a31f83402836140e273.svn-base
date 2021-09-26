using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Newtonsoft.Json;
using FAST.Common.Service.Pojo.Base;
using FAST.Common.Service.Datastructure;


namespace YssInformation.Support.Sys.ConvertDict.Zdorg.Pojo
{
    /// <summary>
    /// 转换字典A区
    /// </summary>
    [NodeDesc(ParentNode = "C_GROUP_CODE_P", TreeNode = "C_GROUP_CODE")]
    public class ZdCorpOrg : AuditableParamPojo
    {
        /// <summary>
        /// 接口组代码
        /// </summary>
        private string c_GROUP_CODE = "";

        /// <summary>
        /// 接口组名称
        /// </summary>
        private string c_GROUP_NAME = "";

        /// <summary>
        /// 接口组父级节点
        /// </summary>
        private string c_GROUP_CODE_P = "[root]";

        /// <summary>
        /// 应用场景
        /// </summary>
        private string c_DV_SCENE = "SCENE_CUSTOM";

        /// <summary>
        /// 序号
        /// </summary>
        private decimal n_ORDER = decimal.Zero;

        /// <summary>
        /// 描述
        /// </summary>
        private string c_DESC = "";

        /// <summary>
        /// 接口组代码
        /// </summary>
        [JsonProperty(PropertyName = "c_GROUP_CODE")]
        public string C_GROUP_CODE
        {
            get { return c_GROUP_CODE; }
            set { c_GROUP_CODE = value; }
        }

        /// <summary>
        /// 接口组名称
        /// </summary>
        [JsonProperty(PropertyName = "c_GROUP_NAME")]
        public string C_GROUP_NAME
        {
            get { return c_GROUP_NAME; }
            set { c_GROUP_NAME = value; }
        }

        /// <summary>
        /// 接口组父级节点
        /// </summary>
        [JsonProperty(PropertyName = "c_GROUP_CODE_P")]
        public string C_GROUP_CODE_P
        {
            get { return c_GROUP_CODE_P; }
            set { c_GROUP_CODE_P = value; }
        }

        /// <summary>
        /// 应用场景
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_SCENE")]
        public string C_DV_SCENE
        {
            get { return c_DV_SCENE; }
            set { c_DV_SCENE = value; }
        }

        /// <summary>
        /// 序号
        /// </summary>
        [JsonProperty(PropertyName = "n_ORDER")]
        public decimal N_ORDER
        {
            get { return n_ORDER; }
            set { n_ORDER = value; }
        }

        /// <summary>
        /// 描述
        /// </summary>
        [JsonProperty(PropertyName = "c_DESC")]
        public string C_DESC
        {
            get { return c_DESC; }
            set { c_DESC = value; }
        }
    }
}
