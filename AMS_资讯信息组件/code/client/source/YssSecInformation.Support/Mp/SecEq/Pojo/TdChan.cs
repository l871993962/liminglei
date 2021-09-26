using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Exceptions;


using FAST.Core.Context;

using Newtonsoft.Json;
using FAST.Common.Service.Datastructure;

namespace YssSecInformation.Support.Mp.SecEq.Pojo
{
    /// <summary>
    /// /// /// chenbo
    /// 2017-06-22
    /// #42948 资讯信息管理组件化拆分
    /// ----------------------------------
    /// 证券交易渠道
    /// </summary>
    [NodeDesc(ParentNode = "C_DV_CHAN_TYPE", TreeNode = "C_TD_CHAN_CODE")]
    public class TdChan : AuditableParamPojo
    {
        /// <summary>
        ///   交易渠道代码
        /// </summary>
        private string c_TD_CHAN_CODE = "";

        /// <summary>
        ///  交易渠道名称
        /// </summary>
        private string c_TD_CHAN_NAME = "";

        /// <summary>
        /// 机构代码
        /// </summary>
        private string c_ORG_CODE = "";

        /// <summary>
        /// 交易所代码
        /// </summary>
        private string c_MKT_CODE = "";

        /// <summary>
        /// 渠道类型
        /// </summary>
        private string c_DV_CHAN_TYPE = "";

        /// <summary>
        /// 描述
        /// </summary>
        private string c_DESC = "";

        /// <summary>
        /// 属性: 交易渠道代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_TD_CHAN_CODE")]
        public string C_TD_CHAN_CODE
        {
            set { c_TD_CHAN_CODE = value; }

            get { return c_TD_CHAN_CODE; }
        }

        /// <summary>
        /// 属性: 交易渠道名称 
        /// </summary>
        [JsonProperty(PropertyName = "c_TD_CHAN_NAME")]
        public string C_TD_CHAN_NAME
        {
            set { c_TD_CHAN_NAME = value; }

            get { return c_TD_CHAN_NAME; }
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
        /// 属性: 交易所代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_MKT_CODE")]
        public string C_MKT_CODE
        {
            set { c_MKT_CODE = value; }

            get { return c_MKT_CODE; }
        }

        /// <summary>
        /// 属性: 渠道类型 
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_CHAN_TYPE")]
        public string C_DV_CHAN_TYPE
        {
            set { c_DV_CHAN_TYPE = value; }

            get { return c_DV_CHAN_TYPE; }
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


