using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Pojo.Base;
using Newtonsoft.Json;

namespace YssProductInfo.Support.PortPlan.Pojo
{
    /// <summary>
    /// 组合方案pojo类
    /// add by zhd 2016-09-26
    /// STORY33239【南方基金】复制建仓参照组合能默认勾选继承内容即增加保存按钮
    /// </summary>
    public class PortPlan : AuditableParamPojo
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
        /// 业务项目
        /// </summary>
        private string c_ITEM_CODE = "";

        /// <summary>
        /// 方案类型
        /// </summary>
        private string c_PLAN_TYPE = "";

        /// <summary>
        /// 描述
        /// </summary>
        private string c_DESC = "";

        /// <summary>
        /// 业务项
        /// </summary>
        /// modified by HeLiang 2017-06-14 STORY #42921 产品信息组件拆分开发.临时注释
        ////private List<CopyData> itemList = null;

        /// <summary>
        /// 方案代码
        /// </summary>
        [JsonProperty(PropertyName = "c_PLAN_CODE")]
        public string C_PLAN_CODE
        {
            set { c_PLAN_CODE = value; }

            get { return c_PLAN_CODE; }
        }

        /// <summary>
        /// 方案名称
        /// </summary>
        [JsonProperty(PropertyName = "c_PLAN_NAME")]
        public string C_PLAN_NAME
        {
            set { c_PLAN_NAME = value; }

            get { return c_PLAN_NAME; }
        }

        /// <summary>
        /// 业务项目
        /// </summary>
        [JsonProperty(PropertyName = "c_ITEM_CODE")]
        public string C_ITEM_CODE
        {
            set { c_ITEM_CODE = value; }

            get { return c_ITEM_CODE; }
        }

        /// <summary>
        /// 方案类型
        /// </summary>
        [JsonProperty(PropertyName = "c_PLAN_TYPE")]
        public string C_PLAN_TYPE
        {
            set { c_PLAN_TYPE = value; }

            get { return c_PLAN_TYPE; }
        }

        /// <summary>
        /// 描述
        /// </summary>
        [JsonProperty(PropertyName = "c_DESC")]
        public string C_DESC
        {
            set { c_DESC = value; }

            get { return c_DESC; }
        }

        /// <summary>
        /// 业务项
        /// </summary>
        /// modified by HeLiang 2017-06-14 STORY #42921 产品信息组件拆分开发.临时注释
        ////[JsonProperty(PropertyName = "itemList")]
        ////public List<CopyData> ItemList
        ////{
        ////    set { itemList = value; }
        ////    get { return itemList; }
        ////}
    }
}