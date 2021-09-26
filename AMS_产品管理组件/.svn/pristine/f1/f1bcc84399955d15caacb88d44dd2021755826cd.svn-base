using FAST.Core.Util;
using FAST.Common.Service.Pojo;

using FAST.Core.Exceptions;

using FAST.Core.Context;

using FAST.Common.Service.Pojo.Base;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Newtonsoft.Json;
using FAST.Common.Service.Interface;


namespace YssProductInfo.Support.Plan.Pojo
{
    /// <summary>
    /// 业务方案pojo类
    /// Add By : zhengguiyu 20140315
    /// Edit By leijianhua 20170324
    /// comment: BusinessPlan 实现接口I_BusinessPlan，否则调度方案项目的方案下拉框会报错
    /// </summary>
    public class BusinessPlan : AuditableParamPojo, I_BusinessPlan
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
        private List<DvaItem> itemList = null;

        /// <summary>
        /// add by huyingzhao STORY #62315 需求一般：【日常做账】功能涉及【估值方案-设置为首选方案】
        /// 所有范围
        /// </summary>
        private string c_SHARE_LEVEL = "";

        /// <summary>
        /// 选中框自定义下拉list显示值
        /// </summary>
        private string c_DOWN = "";

        /// <summary>
        /// 选中框自定义下拉选定显示值
        /// </summary>
        private string c_SELECT = "";

        /// <summary>
        /// 用户代码
        /// </summary>
        private string c_USER_CODES = "";

        /// <summary>
        /// 标志
        /// </summary>
        private string c_SIGN = "";

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
        [JsonProperty(PropertyName = "itemList")]
        public List<DvaItem> ItemList
        {
            set { itemList = value; }
            get { return itemList; }
        }

        /// <summary>
        /// 使用范围
        /// </summary>
        [JsonProperty(PropertyName = "c_SHARE_LEVEL")]
        public string C_SHARE_LEVEL
        {
            set { c_SHARE_LEVEL = value; }
            get { return c_SHARE_LEVEL; }
        }

        /// <summary>
        /// 选中框自定义下拉list显示值
        /// </summary>
        public string C_DOWN
        {
            set { c_DOWN = value; }
            get { return c_DOWN; }
        }

        /// <summary>
        /// 选中框展示自定义下拉选定值
        /// </summary>
        public string C_SELECT
        {
            set { c_SELECT = value; }
            get { return c_SELECT; }
        }


        /// <summary>
        /// 用户代码
        /// </summary>
        [JsonProperty(PropertyName = "c_USER_CODES")]
        public string C_USER_CODES
        {
            set { c_USER_CODES = value; }
            get { return c_USER_CODES; }
        }

        /// <summary>
        /// 标志
        /// </summary>
        [JsonProperty(PropertyName = "c_SIGN")]
        public string C_SIGN
        {
            set { c_SIGN = value; }
            get { return c_SIGN; }
        }

    }
}
