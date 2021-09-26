using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Pojo.Base;
using Newtonsoft.Json;
using FAST.Common.Service.Pojo;


namespace YssSecInformation.Support.Mp.SecEq.Pojo
{
    /// <summary>
    /// /// chenbo
    /// 2017-06-22
    /// #42948 资讯信息管理组件化拆分
    /// ----------------------------------
    /// 证券代码转换Pojo类
    /// added by gh 20161024
    /// </summary>
    public class SecTransfer : EnclosurePojo
    {
        /// <summary>
        /// 证券代码
        /// </summary>
        private string c_sec_code = "";   

        /// <summary>
        /// 转换规则
        /// </summary>
        private string c_transfer_code = "";

        /// <summary>
        /// 披露代码
        /// </summary>
        private string c_pub_code = "";

        /// <summary>
        /// 数据来源
        /// </summary>
        private string c_DATA_IDF = "";

        /// <summary>
        /// 描述
        /// </summary>
        private string c_desc = "";

        /// <summary>
        /// 渠道类别
        /// </summary>
        private string c_DV_CHAN_TYPE = "";

        /// <summary>
        /// 类型
        /// </summary>
        private string c_TYPE = "";

        /// <summary>
        /// 属性: 类型 
        /// </summary>
        [JsonProperty(PropertyName = "c_TYPE")]
        public string C_TYPE
        {
            set { c_TYPE = value; }

            get { return c_TYPE; }
        }

        /// <summary>
        /// 属性: 证券代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_SEC_CODE")]
        public string C_SEC_CODE
        {
            set { c_sec_code = value; }

            get { return c_sec_code; }
        }

        /// <summary>
        /// 属性: 转换规则 
        /// </summary>
        [JsonProperty(PropertyName = "c_TRANSFER_CODE")]
        public string C_TRANSFER_CODE
        {
            set { c_transfer_code = value; }

            get { return c_transfer_code; }
        }

        /// <summary>
        /// 属性: 披露代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_PUB_CODE")]
        public string C_PUB_CODE
        {
            set { c_pub_code = value; }

            get { return c_pub_code; }
        }


        /// <summary>
        /// 属性：数据来源
        /// </summary>
        [JsonProperty(PropertyName = "c_DATA_IDF")]
        public string C_DATA_IDF
        {
            get { return c_DATA_IDF; }
            set { c_DATA_IDF = value; }
        }

        /// <summary>
        /// 属性: 描述 
        /// </summary>
        [JsonProperty(PropertyName = "c_DESC")]
        public string C_DESC
        {
            set { c_desc = value; }

            get { return c_desc; }
        }
    }
}
