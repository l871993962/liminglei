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

namespace YssInformation.Support.Bi.Holidays_A.Pojo
{
    /// <summary>
    /// 节假日群信息pojo类
    /// </summary>
    public class HdayGroup : AuditableParamPojo
    {
        /// <summary>
        /// 节假日群代码
        /// </summary>
        private string c_HDAY_CODE = "";

        /// <summary>
        /// 节假日群名称
        /// </summary>
        private string c_HDAY_NAME = "";

        /// <summary>
        /// 描述
        /// </summary>
        private string c_DESC = "";

        /// <summary>
        /// 属性: 节假日群代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_HDAY_CODE")]
        public string C_HDAY_CODE
        {
            set { c_HDAY_CODE = value; }

            get { return c_HDAY_CODE; }
        }

        /// <summary>
        /// 属性: 节假日群名称 
        /// </summary>
        [JsonProperty(PropertyName = "c_HDAY_NAME")]
        public string C_HDAY_NAME
        {
            set { c_HDAY_NAME = value; }

            get { return c_HDAY_NAME; }
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


    }
}




