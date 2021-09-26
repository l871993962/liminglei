using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Newtonsoft.Json;
using FAST.Common.Service.Pojo.Base;
using FAST.Common.Service.Datastructure;


namespace YssElecReco.Pojo.Er
{   
    /// <summary>
    /// 功能简介：电子对账数据生成pojo
    /// 创建版本：betaV1.0.0.19
    /// 创建人： fangjialiang  
    /// 创建日期： 2013.01.21
    /// </summary>
    [NodeDesc(ParentNode = "C_PARENT_CODE", TreeNode = "C_ELEC_CODE")]
    public class ElecGroupRela : BasePojo
    {
        /// <summary>
        /// 核对项代码
        /// </summary>
        private string c_ELEC_CODE = "";

        /// <summary>
        /// 核对项名称
        /// </summary>
        private string c_ELEC_NAME = "";

        /// <summary>
        /// 核对项父ID
        /// </summary>
        private string c_PARENT_CODE = "";

        /// <summary>
        /// 核对项代码
        /// </summary>
        [JsonProperty(PropertyName = "c_ELEC_CODE")]
        public string C_ELEC_CODE
        {
            get { return c_ELEC_CODE; }
            set { c_ELEC_CODE = value; }
        }

        /// <summary>
        /// 核对项名称
        /// </summary>
        [JsonProperty(PropertyName = "c_ELEC_NAME")]
        public string C_ELEC_NAME
        {
            get { return c_ELEC_NAME; }
            set { c_ELEC_NAME = value; }
        }

        /// <summary>
        /// 核对项父级代码
        /// </summary>
        [JsonProperty(PropertyName = "c_PARENT_CODE")]
        public string C_PARENT_CODE
        {
            get { return c_PARENT_CODE; }
            set { c_PARENT_CODE = value; }
        }
    }
}
