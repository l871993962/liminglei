using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;

namespace YssElecReco.Pojo.Er
{
    /// <summary>
    /// 电子对账结果实体对象
    /// </summary>
    public class ErResultQuery : ErResult
    {
        /// <summary>
        /// 文件类型
        /// </summary>
        private string c_B_KM_NAME = "";

        /// <summary>
        /// 组合代码
        /// </summary>
        private string c_D_KM_NAME = "";

        /// <summary>
        /// 属性: 对方金额1 
        /// </summary>
        [JsonProperty(PropertyName = "c_B_KM_NAME")]
        public string C_B_KM_NAME
        {
            set { c_B_KM_NAME = value; }
            get { return c_B_KM_NAME; }
        }

        /// <summary>
        /// 属性: 对比类型 
        /// </summary>
        [JsonProperty(PropertyName = "c_D_KM_NAME")]
        public string C_D_KM_NAME
        {
            set { c_D_KM_NAME = value; }
            get { return c_D_KM_NAME; }
        }
    }
}
