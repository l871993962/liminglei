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

namespace YssInformation.Support.Bi.Account.Pojo
{
    /// <summary>
    /// 现金账户设置pojo类
    /// </summary>
    public class CashAcc : AuditableParamPojo
    {
        /// <summary>
        /// 现金账户代码  
        /// </summary>
        private string c_CA_CODE = "";

        /// <summary>
        /// 现金账户名称 
        /// </summary>
        private string c_CA_NAME = "";

        /// <summary>
        /// 账户类型
        /// </summary>
        private string c_DV_ACC_TYPE = "";

        /// <summary>
        /// 投资组合
        /// </summary>
        private string c_PORT_CODE = "";

        /// <summary>
        /// 币种代码  
        /// </summary>
        private string c_DC_CODE = "";

        /// <summary>
        /// 账户性质
        /// </summary>
        private string c_DV_ACC_NATURE = "";

        /// <summary>
        /// 描述
        /// </summary>
        private string c_DESC = "";


        /// <summary>
        /// 账户明细类型
        /// liuxiang 2016年3月21日 STORY #28610 华泰证券：估值4.5对接托管清算的账户同步和资金划拨指令（4.5系统）
        /// </summary>
        private string c_DV_DETAIL_TYPE = "";
		
        /// <summary>
        /// 机构名称
        /// add by Yuntao Lau 2015.11.17 STORY #27225
        /// </summary>
        private string c_ORG_CODE = "";

        /// <summary>
        /// 开户名称
        /// </summary>
        private string c_OPEN_ACC_NO = "";

        /// <summary>
        /// STORY30930【深国投】紧急--关于增加‘现金账户’用途的需求   add liuyanin 20160513
        /// 账户用途
        /// </summary>
        private string c_CA_USE = "";

        /// <summary>
        /// 属性：开户账号
        /// </summary>
        [JsonProperty(PropertyName = "c_OPEN_ACC_NO")]
        public string C_OPEN_ACC_NO
        {
            get { return c_OPEN_ACC_NO; }
            set { c_OPEN_ACC_NO = value; }
        }

        /// <summary>
        /// 属性: 现金账户代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_CA_CODE")]
        public string C_CA_CODE
        {
            set { c_CA_CODE = value; }

            get { return c_CA_CODE; }
        }

        /// <summary>
        /// 属性: 现金账户名称 
        /// </summary>
        [JsonProperty(PropertyName = "c_CA_NAME")]
        public string C_CA_NAME
        {
            set { c_CA_NAME = value; }

            get { return c_CA_NAME; }
        }

        /// <summary>
        /// 属性: 账户类型 
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_ACC_TYPE")]
        public string C_DV_ACC_TYPE
        {
            set { c_DV_ACC_TYPE = value; }

            get { return c_DV_ACC_TYPE; }
        }

        /// <summary>
        /// 属性: 投资组合
        /// </summary>
        [JsonProperty(PropertyName = "c_PORT_CODE")]
        public string C_PORT_CODE
        {
            set { c_PORT_CODE = value; }

            get { return c_PORT_CODE; }
        }

        /// <summary>
        /// 属性: 币种代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_DC_CODE")]
        public string C_DC_CODE
        {
            set { c_DC_CODE = value; }

            get { return c_DC_CODE; }
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
        /// 属性: 账户性质 
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_ACC_NATURE")]
        public string C_DV_ACC_NATURE
        {
            set { c_DV_ACC_NATURE = value; }

            get { return c_DV_ACC_NATURE; }
        }

        /// <summary>
        /// 账户明细类型
        /// liuxiang 2016年3月21日 STORY #28610 华泰证券：估值4.5对接托管清算的账户同步和资金划拨指令（4.5系统）
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_DETAIL_TYPE")]
        public string C_DV_DETAIL_TYPE
        {
            get { return c_DV_DETAIL_TYPE; }
            set { c_DV_DETAIL_TYPE = value; }
        }
		
        /// <summary>
        /// 属性: 机构名称
        /// </summary>
        [JsonProperty(PropertyName = "c_ORG_CODE")]
        public string C_ORG_CODE
        {
            set { c_ORG_CODE = value; }

            get { return c_ORG_CODE; }
        }

        /// <summary>
        /// 属性: 账户用途
        /// </summary>
        [JsonProperty(PropertyName = "c_CA_USE")]
        public string C_CA_USE
        {
            set { c_CA_USE = value; }

            get { return c_CA_USE; }
        }
    }
}



