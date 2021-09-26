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
using FAST.Common.Service.Datastructure;
using YssInformation.Support.Interface;
////using YssBaseCls.Interface;

namespace YssInformation.Support.Bi.IeLinks.Pojo
{
    /// <summary>
    /// 收支链接设置
    /// </summary>
    [NodeDesc(ParentNode = "C_FEE_CODE_P", TreeNode = "C_FEE_CODE")]
    public class IeLink : AuditableParamPojo, I_IE_RELA
    {
        /// <summary>
        /// 上级费用
        /// </summary>
        private string c_FEE_CODE_P = null;

        /// <summary>
        /// 收支代码
        /// </summary>
        private string c_FEE_CODE = null;

        /// <summary>
        /// 收支项目
        /// </summary>
        private string c_IE_CODE = null;

        /// <summary>
        /// 来源标识
        /// </summary>
        private string c_SRC_MARK = null;

        /// <summary>
        ///  描述
        /// </summary>
        private string c_DESC = null;

        /// <summary>
        /// 收支名称
        /// </summary>
        private string c_FEE_NAME = null;

        /// <summary>
        /// 上级费用
        /// </summary>
        [JsonProperty(PropertyName = "c_FEE_CODE_P")]
        public string C_FEE_CODE_P
        {
            get { return c_FEE_CODE_P; }
            set { c_FEE_CODE_P = value; }
        }

        /// <summary>
        /// 收支代码
        /// </summary>
        [JsonProperty(PropertyName = "c_FEE_CODE")]
        public string C_FEE_CODE
        {
            get { return c_FEE_CODE; }
            set { c_FEE_CODE = value; }
        }

        /// <summary>
        /// 收支项目
        /// </summary>
        [JsonProperty(PropertyName = "c_IE_CODE")]
        public string C_IE_CODE
        {
            get { return c_IE_CODE; }
            set { c_IE_CODE = value; }
        }

        /// <summary>
        /// 来源标识
        /// </summary>
        [JsonProperty(PropertyName = "c_SRC_MARK")]
        public string C_SRC_MARK
        {
            get { return c_SRC_MARK; }
            set { c_SRC_MARK = value; }
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

        /// <summary>
        /// 属性: 收支名称 
        /// </summary>
        [JsonProperty(PropertyName = "c_FEE_NAME")]
        public string C_FEE_NAME
        {
            set { c_FEE_NAME = value; }

            get { return c_FEE_NAME; }
        }


        #region I_IE_RELA 成员

        string I_IE_RELA.C_FEE_CODE
        {
            get
            {
                throw new NotImplementedException();
            }
            set
            {
                throw new NotImplementedException();
            }
        }

        string I_IE_RELA.C_IE_CODE
        {
            get
            {
                throw new NotImplementedException();
            }
            set
            {
                throw new NotImplementedException();
            }
        }

        string I_IE_RELA.C_SRC_MARK
        {
            get
            {
                throw new NotImplementedException();
            }
            set
            {
                throw new NotImplementedException();
            }
        }

        string I_IE_RELA.C_FEE_NAME
        {
            get
            {
                throw new NotImplementedException();
            }
            set
            {
                throw new NotImplementedException();
            }
        }

        #endregion
    }
}




