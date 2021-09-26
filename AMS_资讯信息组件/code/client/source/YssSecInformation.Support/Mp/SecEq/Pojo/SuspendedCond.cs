﻿using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Exceptions;


using FAST.Core.Context;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Newtonsoft.Json;

namespace YssSecInformation.Support.Mp.SecEq.Pojo
{
    /// <summary>
    /// /// /// chenbo
    /// 2017-06-22
    /// #42948 资讯信息管理组件化拆分
    /// ----------------------------------
    /// 停牌信息功能选项实体类
    /// </summary>
    public class SuspendedCond : BasePojo
    {
        /// <summary>
        /// 项目代码
        /// </summary>
        private string c_ITEM_CODE = "";

        /// <summary>
        /// 项目名称
        /// </summary>
        private string c_ITEM_NAME = "";

        /// <summary>
        /// 逻辑判断
        /// </summary>
        private string c_LOGICAL_JUDGMENT = "";

        /// <summary>
        /// 项目值
        /// </summary>
        private string c_ITEM_VALUE = "";
    
        /// <summary>
        /// 单位类型
        /// </summary>
        private string c_VALUE_TYPE = "";
        
        /// <summary>
        /// 组合代码
        /// </summary>
        private string c_PORT_CODE = "";

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
        /// 属性: 项目代码
        /// </summary>
        [JsonProperty(PropertyName = "c_ITEM_CODE")]
        public string C_ITEM_CODE
        {
            set { c_ITEM_CODE = value; }

            get { return c_ITEM_CODE; }
        }

        /// <summary>
        /// 属性: 项目名称
        /// </summary>
        [JsonProperty(PropertyName = "c_ITEM_NAME")]
        public string C_ITEM_NAME
        {
            set { c_ITEM_NAME = value; }

            get { return c_ITEM_NAME; }
        }

        /// <summary>
        /// 属性: 逻辑判断
        /// </summary>
        [JsonProperty(PropertyName = "c_LOGICAL_JUDGMENT")]
        public string C_LOGICAL_JUDGMENT
        {
            set { c_LOGICAL_JUDGMENT = value; }

            get { return c_LOGICAL_JUDGMENT; }
        }

        /// <summary>
        /// 属性: 项目值
        /// </summary>
        [JsonProperty(PropertyName = "c_ITEM_VALUE")]
        public string C_ITEM_VALUE
        {
            set { c_ITEM_VALUE = value; }

            get { return c_ITEM_VALUE; }
        }

        /// <summary>
        /// 属性: 单位类型
        /// </summary>
        [JsonProperty(PropertyName = "c_VALUE_TYPE")]
        public string C_VALUE_TYPE
        {
            set { c_VALUE_TYPE = value; }

            get { return c_VALUE_TYPE; }
        }
    }
}




