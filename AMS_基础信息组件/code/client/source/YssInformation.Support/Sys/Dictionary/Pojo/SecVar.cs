﻿using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Pojo.Base;
using FAST.Common.Service.Interface;
using FAST.Core.Exceptions;


using FAST.Core.Context;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Newtonsoft.Json;
////using YssBaseCls.Interface;
using FAST.Common.Service.Datastructure;
using YssInformation.Support.Interface;

////namespace YssPara.Pojo.Bi
////namespace YssInformation.Sys.Dictionary.Pojo
namespace YssInformation.Support.Sys.Dictionary.Pojo
{
    /// <summary>
    /// 证券品种信息pojo类
    /// </summary>
    [NodeDesc(ParentNode = "C_DA_CODE_P", TreeNode = "C_DA_CODE")]
    public class SecVar : AuditableParamPojo, I_SecVar
    {
        /// <summary>
        /// 证券品种代码
        /// </summary>
        private string c_SEC_VAR_CODE = "";

        /// <summary>
        /// 证券品种名称
        /// </summary>
        private string c_SEC_VAR_NAME = "";

        /// <summary>
        ///  证券属性代码
        /// </summary>
        private string c_DA_CODE = "";

        /// <summary>
        ///  品种属性的父级代码
        /// </summary>
        private string c_DA_CODE_P = "";

        /// <summary>
        /// 属性: 证券品种代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_SEC_VAR_CODE")]
        public string C_SEC_VAR_CODE
        {
            set { c_SEC_VAR_CODE = value; }

            get { return c_SEC_VAR_CODE; }
        }

        /// <summary>
        /// 属性: 证券品种名称 
        /// </summary>
        [JsonProperty(PropertyName = "c_SEC_VAR_NAME")]
        public string C_SEC_VAR_NAME
        {
            set { c_SEC_VAR_NAME = value; }

            get { return c_SEC_VAR_NAME; }
        }

        /// <summary>
        /// 属性: 证券属性代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_DA_CODE")]
        public string C_DA_CODE
        {
            set { c_DA_CODE = value; }

            get { return c_DA_CODE; }
        }

        /// <summary>
        /// 属性: 品种属性的父级代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_DA_CODE_P")]
        public string C_DA_CODE_P
        {
            set { c_DA_CODE_P = value; }

            get { return c_DA_CODE_P; }
        }

        /// <summary>
        /// auditDate
        /// </summary>
        [JsonProperty(PropertyName = "auditDate")]
        public string AuditDate { get; set; }

        /// <summary>
        /// AuditDate
        /// </summary>
        [JsonProperty(PropertyName = "auditState")]
        public int AuditState { get; set; }

    }
}




