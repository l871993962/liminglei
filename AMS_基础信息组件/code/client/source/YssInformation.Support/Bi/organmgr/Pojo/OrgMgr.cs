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

namespace YssInformation.Support.Bi.organmgr.Pojo
{
    /// <summary>
    /// 机构结算会员pojo类
    /// </summary>
    public class OrgMgr : AuditableParamPojo
    {
        /// <summary>
        /// 会员号
        /// </summary>
        private string c_MBR_CODE = "";

        /// <summary>
        /// 机构代码
        /// </summary>
        private string c_ORG_CODE = "";

        /// <summary>
        /// 资金账号
        /// </summary>
        private string c_ACC_CODE = "";

        /// <summary>
        /// 开户行名称
        /// </summary>
        private string c_ORG_NAME = "";

        /// <summary>
        /// 账户代码
        /// </summary>
        private string c_CA_CODE = "";

        /// <summary>
        /// 账户名称
        /// </summary>
        private string c_CA_NAME = "";

        /// <summary>
        /// 开始日期
        /// </summary>
        private string d_BEGIN;

        /// <summary>
        /// 结束日期
        /// </summary>
        private string d_END;

        /// <summary>
        /// 描述
        /// </summary>
        private string c_DESC = "";

        /// <summary>
        /// 券商代码
        /// TASK190987:AddBy-Bohua.Gu-2016/02/26
        /// </summary>
        private string c_BROKER_CODE = "";

        /// <summary>
        /// 属性: 会员号 
        /// </summary>
        [JsonProperty(PropertyName = "c_MBR_CODE")]
        public string C_MBR_CODE
        {
            set { c_MBR_CODE = value; }

            get { return c_MBR_CODE; }
        }

        /// <summary>
        /// 属性: 机构代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_ORG_CODE")]
        public string C_ORG_CODE
        {
            set { c_ORG_CODE = value; }

            get { return c_ORG_CODE; }
        }

        /// <summary>
        /// 属性: 资金账号 
        /// </summary>
        [JsonProperty(PropertyName = "c_ACC_CODE")]
        public string C_ACC_CODE
        {
            set { c_ACC_CODE = value; }

            get { return c_ACC_CODE; }
        }

        /// <summary>
        /// 属性: 开户行名称 
        /// </summary>
        [JsonProperty(PropertyName = "c_ORG_NAME")]
        public string C_ORG_NAME
        {
            set { c_ORG_NAME = value; }

            get { return c_ORG_NAME; }
        }

        /// <summary>
        /// 属性: 账户代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_CA_CODE")]
        public string C_CA_CODE
        {
            set { c_CA_CODE = value; }

            get { return c_CA_CODE; }
        }

        /// <summary>
        /// 属性: 账户名称 
        /// </summary>
        [JsonProperty(PropertyName = "c_CA_NAME")]
        public string C_CA_NAME
        {
            set { c_CA_NAME = value; }

            get { return c_CA_NAME; }
        }

        /// <summary>
        /// 属性: 开始日期 
        /// </summary>
        [JsonProperty(PropertyName = "d_BEGIN")]
        public string D_BEGIN
        {
            set { d_BEGIN = value; }

            get { return d_BEGIN; }
        }

        /// <summary>
        /// 属性: 结束日期 
        /// </summary>
        [JsonProperty(PropertyName = "d_END")]
        public string D_END
        {
            set { d_END = value; }

            get { return d_END; }
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

        /// <summary>
        /// 属性: 券商代码 
        /// TASK190987:AddBy-Bohua.Gu-2016/02/26
        /// </summary>
        [JsonProperty(PropertyName = "c_BROKER_CODE")]
        public string C_BROKER_CODE
        {
            set { c_BROKER_CODE = value; }

            get { return c_BROKER_CODE; }
        }

    }
}




