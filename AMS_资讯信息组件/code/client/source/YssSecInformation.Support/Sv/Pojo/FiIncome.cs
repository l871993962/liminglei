using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Exceptions;


using FAST.Core.Context;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Newtonsoft.Json;

namespace YssSecInformation.Support.Sv.Pojo
{
    /// <summary>
    /// 债券每日利息Pojo类
    /// added by ll 20120928
    /// </summary>
    public class FiIncome : EnclosurePojo
    {
        /// <summary>
        /// 证券代码
        /// </summary>
        private string c_SEC_CODE = "";

        /// <summary>
        /// 计息日期
        /// </summary>
        private string d_INCOME = "";

        /// <summary>
        /// 报价方式
        /// </summary>
        private string c_DV_QUT_MOD = "";

        /// <summary>
        /// 计息天数
        /// </summary>
        private string n_INCOME_DAYS = "";

        /// <summary>
        /// 票面利率
        /// </summary>
        private string n_COUP_RATE = "";

        /// <summary>
        /// 剩余本金
        /// </summary>
        private string n_REM_COR = "";

        /// <summary>
        /// 税前计息利息
        /// </summary>
        private string n_INCOME_PT = "";

        /// <summary>
        /// 税后计息利息
        /// </summary>
        private string n_INCOME_AT = "";

        /// <summary>
        /// 税后付息利息
        /// </summary>
        private string n_INCOME_PT_DUE = "";

        /// <summary>
        /// 税后付息利息
        /// </summary>
        private string n_INCOME_AT_DUE = "";

        /// <summary>
        /// 描述
        /// </summary>
        private string c_DESC = "";

        /// <summary>
        /// 属性: 证券代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_SEC_CODE")]
        public string C_SEC_CODE
        {
            set { c_SEC_CODE = value; }

            get { return c_SEC_CODE; }
        }

        /// <summary>
        /// 属性: 计息日期 
        /// </summary>
        [JsonProperty(PropertyName = "d_INCOME")]
        public string D_INCOME
        {
            set { d_INCOME = value; }

            get { return d_INCOME; }
        }

        /// <summary>
        /// 属性: 报价方式 
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_QUT_MOD")]
        public string C_DV_QUT_MOD
        {
            set { c_DV_QUT_MOD = value; }

            get { return c_DV_QUT_MOD; }
        }

        /// <summary>
        /// 属性: 计息天数 
        /// </summary>
        [JsonProperty(PropertyName = "n_INCOME_DAYS")]
        public string N_INCOME_DAYS
        {
            set { n_INCOME_DAYS = value; }

            get { return n_INCOME_DAYS; }
        }

        /// <summary>
        /// 属性: 票面利率 
        /// </summary>
        [JsonProperty(PropertyName = "n_COUP_RATE")]
        public string N_COUP_RATE
        {
            set { n_COUP_RATE = value; }

            get { return n_COUP_RATE; }
        }

        /// <summary>
        /// 属性: 剩余本金 
        /// </summary>
        [JsonProperty(PropertyName = "n_REM_COR")]
        public string N_REM_COR
        {
            set { n_REM_COR = value; }

            get { return n_REM_COR; }
        }

        /// <summary>
        /// 属性: 税前计息利息 
        /// </summary>
        [JsonProperty(PropertyName = "n_INCOME_PT")]
        public string N_INCOME_PT
        {
            set { n_INCOME_PT = value; }

            get { return n_INCOME_PT; }
        }

        /// <summary>
        /// 属性: 税后计息利息 
        /// </summary>
        [JsonProperty(PropertyName = "n_INCOME_AT")]
        public string N_INCOME_AT
        {
            set { n_INCOME_AT = value; }

            get { return n_INCOME_AT; }
        }

        /// <summary>
        /// 属性: 税后付息利息 
        /// </summary>
        [JsonProperty(PropertyName = "n_INCOME_PT_DUE")]
        public string N_INCOME_PT_DUE
        {
            set { n_INCOME_PT_DUE = value; }

            get { return n_INCOME_PT_DUE; }
        }

        /// <summary>
        /// 属性: 税后付息利息 
        /// </summary>
        [JsonProperty(PropertyName = "n_INCOME_AT_DUE")]
        public string N_INCOME_AT_DUE
        {
            set { n_INCOME_AT_DUE = value; }

            get { return n_INCOME_AT_DUE; }
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


