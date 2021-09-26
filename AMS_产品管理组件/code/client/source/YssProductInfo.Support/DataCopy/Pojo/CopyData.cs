using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Pojo.Base;
using Newtonsoft.Json;
using FAST.Common.Service.Datastructure;

namespace YssProductInfo.Support.DataCopy.Pojo
{
    /// <summary>
    /// 数据复制的pojo
    /// </summary>
    [NodeDesc(ParentNode = "C_DATA_CODE_P", TreeNode = "C_DATA_CODE")]
    public class CopyData : BasePojo
    {
        /// <summary>
        /// 数据项名称
        /// </summary>
        private string c_DATA_NAME;

        /// <summary>
        /// 数据项代码
        /// </summary>
        private string c_DATA_CODE;

        /// <summary>
        /// 数据项父级代码
        /// </summary>
        private string c_DATA_CODE_P;

        /// <summary>
        /// 启用状态
        /// </summary>
        private string c_DV_STATE;

        /// <summary>
        /// serviceId
        /// </summary>
        private string c_SERVICE_CODE;

        /// <summary>
        /// 数据项名称
        /// </summary>
        [JsonProperty(PropertyName = "c_DATA_NAME")]
        public string C_DATA_NAME
        {
            get { return c_DATA_NAME; }
            set { c_DATA_NAME = value; }
        }

        /// <summary>
        /// 数据项代码
        /// </summary>
        [JsonProperty(PropertyName = "c_DATA_CODE")]
        public string C_DATA_CODE
        {
            get { return c_DATA_CODE; }
            set { c_DATA_CODE = value; }
        }

        /// <summary>
        /// 数据项父级代码
        /// </summary>
        [JsonProperty(PropertyName = "c_DATA_CODE_P")]
        public string C_DATA_CODE_P
        {
            get { return c_DATA_CODE_P; }
            set { c_DATA_CODE_P = value; }
        }

        /// <summary>
        /// 启用状态
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_STATE")]
        public string C_DV_STATE
        {
            get { return c_DV_STATE; }
            set { c_DV_STATE = value; }
        }

        /// <summary>
        /// serviceID
        /// </summary>
        [JsonProperty(PropertyName = "c_SERVICE_CODE")]
        public string C_SERVICE_CODE
        {
            get { return c_SERVICE_CODE; }
            set { c_SERVICE_CODE = value; }
        }

    }
}
