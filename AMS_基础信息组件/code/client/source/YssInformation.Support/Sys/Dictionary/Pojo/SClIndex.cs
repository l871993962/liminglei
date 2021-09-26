﻿using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Exceptions;


using FAST.Core.Context;

using Newtonsoft.Json;
using FAST.Common.Service.Datastructure;

////namespace YssPara.Pojo.Cl
////namespace YssInformation.Sys.Dictionary.Pojo
namespace YssInformation.Support.Sys.Dictionary.Pojo
{
    /// <summary>
    /// 创建简述：指标项目字典类
    /// 创建人：lizuzheng
    /// 创建时间：2013-04-13
    /// </summary>
    [NodeDesc(ParentNode = "C_INDEX_CODE_P", TreeNode = "C_INDEX_CODE")]
    public class SClIndex : AuditableParamPojo
    {
        /// <summary>
        /// 指标代码
        /// </summary>
        private string c_INDEX_CODE = "";

        /// <summary>
        /// 指标名称
        /// </summary>
        private string c_INDEX_NAME = "";

        /// <summary>
        /// 指标类型
        /// </summary>
        private string c_INDEX_TYPE = "";

        /// <summary>
        /// 指标分类
        /// </summary>
        private string c_IDNEX_CLS = "";

        /// <summary>
        /// 指标上级代码
        /// </summary>
        private string c_INDEX_CODE_P = "";
               
        /// <summary>
        /// 序号
        /// </summary>
        private decimal n_ORDER = 0;
               
        /// <summary>
        /// 参考违规阀值
        /// </summary>
        private string c_VALUE_DEF = "";
               
        /// <summary>
        /// 属性: C_INDEX_CODE 
        /// </summary>
        [JsonProperty(PropertyName = "c_INDEX_CODE")]
        public string C_INDEX_CODE
        {
            set { c_INDEX_CODE = value; }
            get { return c_INDEX_CODE; }
        }

        /// <summary>
        /// 属性: C_INDEX_NAME 
        /// </summary>
        [JsonProperty(PropertyName = "c_INDEX_NAME")]
        public string C_INDEX_NAME
        {
            set { c_INDEX_NAME = value; }
            get { return c_INDEX_NAME; }
        }

        /// <summary>
        /// 属性: C_INDEX_TYPE 
        /// </summary>
        [JsonProperty(PropertyName = "c_INDEX_TYPE")]
        public string C_INDEX_TYPE
        {
            set { c_INDEX_TYPE = value; }
            get { return c_INDEX_TYPE; }
        }

        /// <summary>
        /// 属性: C_IDNEX_CLS 
        /// </summary>
        [JsonProperty(PropertyName = "c_IDNEX_CLS")]
        public string C_IDNEX_CLS
        {
            set { c_IDNEX_CLS = value; }
            get { return c_IDNEX_CLS; }
        }

        /// <summary>
        /// 属性: C_INDEX_CODE_P 
        /// </summary>
        [JsonProperty(PropertyName = "c_INDEX_CODE_P")]
        public string C_INDEX_CODE_P
        {
            set { c_INDEX_CODE_P = value; }
            get { return c_INDEX_CODE_P; }
        }

        /// <summary>
        /// 属性: N_ORDER 
        /// </summary>
        [JsonProperty(PropertyName = "n_ORDER")]
        public decimal N_ORDER
        {
            set { n_ORDER = value; }
            get { return n_ORDER; }
        }
       
        /// <summary>
        /// 属性: C_VALUE_DEF 
        /// </summary>
        [JsonProperty(PropertyName = "c_VALUE_DEF")]
        public string C_VALUE_DEF
        {
            set { c_VALUE_DEF = value; }
            get { return c_VALUE_DEF; }
        }
    }
}




