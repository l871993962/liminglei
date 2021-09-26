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
using FAST.Common.Service.Datastructure;

namespace YssSecInformation.Support.PlateSet.plate.Pojo
{
    /// <summary>
    /// 版块信息设置pojo类A区
    /// </summary>
    [NodeDesc(ParentNode = "C_PLATE_CODE_P", TreeNode = "C_PLATE_CODE")]
    public class Plate : AuditableParamPojo
    {
        /// <summary>
        ///  板块分类代码
        /// </summary>
        private string c_PLATE_CODE = "";

        /// <summary>
        /// 板块分类名称
        /// </summary>
        private string c_PLATE_NAME = "";

        /// <summary>
        /// 上级板块代码
        /// </summary>
        private string c_PLATE_CODE_P = "";

        /// <summary>
        /// 板块分类标准
        /// </summary>
        private string c_PLATE_TYPE = "";

        /// <summary>
        /// 行业指数代码
        /// </summary>
        private string c_INDEX_CODE = "";

        /// <summary>
        /// 描述
        /// </summary>
        private string c_DESC = "";

        /// <summary>
        /// 开始日期
        /// </summary>
        private DateTime d_BEGIN;

        /// <summary>
        /// 结束日期
        /// </summary>
        private DateTime d_END;

        /// <summary>
        /// 属性: 板块分类标准
        /// </summary>
        [JsonProperty(PropertyName = "c_PLATE_TYPE")]
        public string C_PLATE_TYPE
        {
            set { c_PLATE_TYPE = value; }

            get { return c_PLATE_TYPE; }
        }

        /// <summary>
        /// 属性: 行业指数代码
        /// </summary>
        [JsonProperty(PropertyName = "c_INDEX_CODE")]
        public string C_INDEX_CODE
        {
            set { c_INDEX_CODE = value; }

            get { return c_INDEX_CODE; }
        }

        /// <summary>
        /// 属性: 开始日期 
        /// </summary>
        [JsonProperty(PropertyName = "startUseDate")]
        public DateTime D_BEGIN
        {
            set { d_BEGIN = value; }

            get { return d_BEGIN; }
        }

        /// <summary>
        /// 属性: 结束日期 
        /// </summary>
        [JsonProperty(PropertyName = "endUseDate")]
        public DateTime D_END
        {
            set { d_END = value; }

            get { return d_END; }
        }


        /// <summary>
        /// 属性: 板块分类代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_PLATE_CODE")]
        public string C_PLATE_CODE
        {
            set { c_PLATE_CODE = value; }

            get { return c_PLATE_CODE; }
        }

        /// <summary>
        /// 属性: 板块分类名称 
        /// </summary>
        [JsonProperty(PropertyName = "c_PLATE_NAME")]
        public string C_PLATE_NAME
        {
            set { c_PLATE_NAME = value; }

            get { return c_PLATE_NAME; }
        }

        /// <summary>
        /// 属性: 上级板块代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_PLATE_CODE_P")]
        public string C_PLATE_CODE_P
        {
            set { c_PLATE_CODE_P = value; }

            get { return c_PLATE_CODE_P; }
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




