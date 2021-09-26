using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Pojo.Base;
using Newtonsoft.Json;

namespace YssElecReco.Pojo.Er
{
    /// <summary>
    /// 主要指标方案
    /// </summary>
    public class ErResview : AuditableParamPojo
    {
        /// <summary>
        /// 方案代码
        /// </summary>
        private string c_PLAN_CODE = "";

        /// <summary>
        /// 方案名称
        /// </summary>
        private string c_PLAN_NAME = "";

        /// <summary>
        /// 方案项目指标
        /// </summary>
        private string c_ITEM_CODE = "";

        /// <summary>
        /// 方案类型
        /// </summary>
        private string c_PLAN_TYPE = "";

        /// <summary>
        /// 方案说明
        /// </summary>
        private string c_DESC = "";

        /// <summary>
        /// 属性: 方案代码
        /// </summary>
        [JsonProperty(PropertyName = "c_PLAN_CODE")]
        public string C_PLAN_CODE
        {
            get { return c_PLAN_CODE; }
            set { c_PLAN_CODE = value; }
        }

        /// <summary>
        /// 属性: 方案名称
        /// </summary>
        [JsonProperty(PropertyName = "c_PLAN_NAME")]
        public string C_PLAN_NAME
        {
            get { return c_PLAN_NAME; }
            set { c_PLAN_NAME = value; }
        }

        /// <summary>
        /// 属性: 方案项目指标
        /// </summary>
        [JsonProperty(PropertyName = "c_ITEM_CODE")]
        public string C_ITEM_CODE
        {
            get { return c_ITEM_CODE; }
            set { c_ITEM_CODE = value; }
        }

        /// <summary>
        /// 属性: 方案类型
        /// </summary>
        [JsonProperty(PropertyName = "c_PLAN_TYPE")]
        public string C_PLAN_TYPE
        {
            get { return c_PLAN_TYPE; }
            set { c_PLAN_TYPE = value; }
        }

        /// <summary>
        /// 属性: 方案说明
        /// </summary>
        [JsonProperty(PropertyName = "c_DESC")]
        public string C_DESC
        {
            get { return c_DESC; }
            set { c_DESC = value; }
        }

    }
}
