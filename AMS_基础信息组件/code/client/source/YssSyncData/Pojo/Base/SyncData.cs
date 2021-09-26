using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Pojo.Base;
using Newtonsoft.Json;

namespace YssSyncData.Pojo.Base
{
    /// <summary>
    /// 数据同步 pojo类
    /// </summary>
    public class SyncData : BasePojo
    {
        /// <summary>
        /// 消息状态
        /// </summary>
        private string c_DV_STATE = "";

        /// <summary>
        /// 接收时间
        /// </summary>
        private string c_RECEIVE_TIME = "";

        /// <summary>
        /// 发送时间
        /// </summary>
        private string c_SEND_TIME = "";

        /// <summary>
        ///  发送人
        /// </summary>
        private string c_SENDER = "";

        /// <summary>
        /// 数据ID
        /// </summary>
        private string c_DATA_ID = "";

        /// <summary>
        /// 操作类型
        /// </summary>
        private string c_DV_OPER_TYPE = "";

        /// <summary>
        /// 同步系统
        /// </summary>
        private string c_SYSTEM_CODE = "";

        /// <summary>
        /// 同步模块code
        /// </summary>
        private string c_DV_MODULE_CODE = "";

        /// <summary>
        /// 消息
        /// </summary>
        private string c_MESSAGE = "";

        /// <summary>
        /// 操作人
        /// </summary>
        private string c_UPDATE_BY = "";

        /// <summary>
        /// 操作时间
        /// </summary>
        private string c_UPDATE_TIME = "";

        /// <summary>
        /// 消息状态
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_STATE")]
        public string C_DV_STATE
        {
            get { return c_DV_STATE; }
            set { c_DV_STATE = value; }
        }

        /// <summary>
        /// 接收时间
        /// </summary>
        [JsonProperty(PropertyName = "c_RECEIVE_TIME")]
        public string C_RECEIVE_TIME
        {
            get { return c_RECEIVE_TIME; }
            set { c_RECEIVE_TIME = value; }
        }

        /// <summary>
        /// 发送时间
        /// </summary>
        [JsonProperty(PropertyName = "c_SEND_TIME")]
        public string C_SEND_TIME
        {
            get { return c_SEND_TIME; }
            set { c_SEND_TIME = value; }
        }

        /// <summary>
        /// 发送人
        /// </summary>
        [JsonProperty(PropertyName = "c_SENDER")]
        public string C_SENDER
        {
            get { return c_SENDER; }
            set { c_SENDER = value; }
        }

        /// <summary>
        /// 数据ID
        /// </summary>
        [JsonProperty(PropertyName = "c_DATA_ID")]
        public string C_DATA_ID
        {
            get { return c_DATA_ID; }
            set { c_DATA_ID = value; }
        }

        /// <summary>
        /// 操作类型
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_OPER_TYPE")]
        public string C_DV_OPER_TYPE
        {
            get { return c_DV_OPER_TYPE; }
            set { c_DV_OPER_TYPE = value; }
        }

        /// <summary>
        /// 同步系统
        /// </summary>
        [JsonProperty(PropertyName = "c_SYSTEM_CODE")]
        public string C_SYSTEM_CODE
        {
            get { return c_SYSTEM_CODE; }
            set { c_SYSTEM_CODE = value; }
        }

        /// <summary>
        /// 同步模块
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_MODULE_CODE")]
        public string C_DV_MODULE_CODE
        {
            get { return c_DV_MODULE_CODE; }
            set { c_DV_MODULE_CODE = value; }
        }

        /// <summary>
        /// 消息
        /// </summary>
        [JsonProperty(PropertyName = "c_MESSAGE")]
        public string C_MESSAGE
        {
            get { return c_MESSAGE; }
            set { c_MESSAGE = value; }
        }

        /// <summary>
        /// 操作人
        /// </summary>
        [JsonProperty(PropertyName = "c_UPDATE_BY")]
        public string C_UPDATE_BY
        {
            get { return c_UPDATE_BY; }
            set { c_UPDATE_BY = value; }
        }

        /// <summary>
        /// 操作时间
        /// </summary>
        [JsonProperty(PropertyName = "c_UPDATE_TIME")]
        public string C_UPDATE_TIME
        {
            get { return c_UPDATE_TIME; }
            set { c_UPDATE_TIME = value; }
        }
    }
}
