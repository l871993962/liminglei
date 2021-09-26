using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;
using FAST.Common.Service.Datastructure;
using FAST.Common.Service.Pojo.Base;

namespace YssProductInfo.Support.Ab.Port.Pojo
{
    /// <summary>
    /// 定义Ａ区组合专用POJO类
    /// 以少量的属性实现数据的快速加载
    /// </summary>
    [NodeDesc(ParentNode = "C_PORT_CODE_P", TreeNode = "C_PORT_CODE")]
    public class Port_A : BasePojo
    {
        /// <summary>
        /// 组合代码
        /// </summary>
        private string c_PORT_CODE = "";

        /// <summary>
        /// 组合名称
        /// </summary>
        private string c_PORT_NAME = "";

        /// <summary>
        /// 组合简称
        /// </summary>
        private string c_PORT_NAME_ST = "";

        /// <summary>
        /// 资产类别
        /// </summary>
        private string c_DAT_CLS = "";

        /// <summary>
        ///  资产代码
        /// </summary>
        private string c_ASS_CODE = "";

        /// <summary>
        /// 资产结构代码
        /// </summary>
        private string c_DAT_CODE = "";

        /// <summary>
        /// 组合级别
        /// </summary>
        private string c_DV_PORT_CODE = "";

        /// <summary>
        /// 上级组合代码
        /// </summary>
        private string c_PORT_CODE_P = "";

        /// <summary>
        /// 类型，如果为PORT_TYPE则为组合
        /// </summary>
        private string dATA_TYPE = "";

        /// <summary>
        /// 类型，如果为PORT_TYPE则为组合
        /// </summary>
        [JsonProperty(PropertyName = "dATA_TYPE")]
        public string DATA_TYPE
        {
            set { dATA_TYPE = value; }

            get { return dATA_TYPE; }
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
        /// 属性: 组合名称 
        /// </summary>
        [JsonProperty(PropertyName = "c_PORT_NAME")]
        public string C_PORT_NAME
        {
            set { c_PORT_NAME = value; }

            get { return c_PORT_NAME; }
        }

        /// <summary>
        /// 属性: 组合简称 
        /// </summary>
        [JsonProperty(PropertyName = "c_PORT_NAME_ST")]
        public string C_PORT_NAME_ST
        {
            set { c_PORT_NAME_ST = value; }

            get { return c_PORT_NAME_ST; }
        }

        /// <summary>
        /// 属性: 资产结构
        /// </summary>
        [JsonProperty(PropertyName = "c_DAT_CODE")]
        public string C_DAT_CODE
        {
            get { return c_DAT_CODE; }
            set { c_DAT_CODE = value; }
        }

        /// <summary>
        /// 属性: 资产类别
        /// </summary>
        [JsonProperty(PropertyName = "c_DAT_CLS")]
        public string C_DAT_CLS
        {
            get { return c_DAT_CLS; }
            set { c_DAT_CLS = value; }
        }

        /// <summary>
        /// 属性: 资产代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_ASS_CODE")]
        public string C_ASS_CODE
        {
            set { c_ASS_CODE = value; }

            get { return c_ASS_CODE; }
        }

        /// <summary>
        /// 属性: 组合级别 
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_PORT_CODE")]
        public string C_DV_PORT_CODE
        {
            set { c_DV_PORT_CODE = value; }

            get { return c_DV_PORT_CODE; }
        }

        /// <summary>
        /// 属性: 上级组合代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_PORT_CODE_P")]
        public string C_PORT_CODE_P
        {
            set { c_PORT_CODE_P = value; }

            get { return c_PORT_CODE_P; }
        }
    }
}
