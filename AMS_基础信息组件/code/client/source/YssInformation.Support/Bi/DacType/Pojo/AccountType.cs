using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;
using FAST.Common.Service.Pojo.Base;
using FAST.Common.Service.Datastructure;

namespace YssInformation.Support.Bi.DacType.Pojo
{
    [NodeDesc(ParentNode = "C_DAC_CODE_P", TreeNode = "C_DAC_CODE")]
    public class AccountType : BasePojo
    {
        /// <summary>
        /// 账户类型父级节点代码
        /// </summary>
        private string c_DAC_CODE_P = "";

        /// <summary>
        ///  账户类型代码
        /// </summary>
        private string c_DAC_CODE = "";

        /// <summary>
        ///  账户类型名称
        /// </summary>
        private string c_DAC_NAME = "";

        /// <summary>
        /// 排序
        /// </summary>
        private string n_ORDER = "";

        /// <summary>
        /// 定位为资金户还是证券户
        /// </summary>
        private string c_DAC_TYPE = "";

        /// <summary>
        /// 是否启用
        /// </summary>
        private string c_DV_STATE = "";

        /// <summary>
        /// 账户类型父级节点代码
        /// </summary>
        [JsonProperty(PropertyName = "c_DAC_CODE_P")]
        public string C_DAC_CODE_P
        {
            get { return c_DAC_CODE_P; }
            set { c_DAC_CODE_P = value; }
        }

        /// <summary>
        /// 账户类型代码
        /// </summary>
        [JsonProperty(PropertyName = "c_DAC_CODE")]
        public string C_DAC_CODE
        {
            get { return c_DAC_CODE; }
            set { c_DAC_CODE = value; }
        }

        /// <summary>
        /// 账户类型名称
        /// </summary>
        [JsonProperty(PropertyName = "c_DAC_NAME")]
        public string C_DAC_NAME
        {
            get { return c_DAC_NAME; }
            set { c_DAC_NAME = value; }
        }

        /// <summary>
        /// 排序
        /// </summary>
        [JsonProperty(PropertyName = "n_ORDER")]
        public string N_ORDER
        {
            get { return n_ORDER; }
            set { n_ORDER = value; }
        }

        /// <summary>
        /// 定位为资金户还是证券户
        /// </summary>
        [JsonProperty(PropertyName = "c_DAC_TYPE")]
        public string C_DAC_TYPE
        {
            get { return c_DAC_TYPE; }
            set { c_DAC_TYPE = value; }
        }

        /// <summary>
        /// 是否启用
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_STATE")]
        public string C_DV_STATE
        {
            get { return c_DV_STATE; }
            set { c_DV_STATE = value; }
        }
    }
}
