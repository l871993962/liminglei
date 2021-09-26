using FAST.Common.Service.Pojo.Base;
using Newtonsoft.Json;

namespace YssInformation.Support.Sys.PortBusinessRange.Pojo
{
    /// <summary>
    /// 业务类型关联组合pojo类
    /// </summary>
    public class PortBusinessRangePojo : AuditableParamPojo
    {
        /// <summary>
        /// 组合代码
        /// </summary>    
        private string c_PORT_CODE = "";

        /// <summary>
        /// 业务类型
        /// </summary>
        private string c_BUSINESS_CODE = "";

        /// <summary>
        /// 属性：组合代码
        /// </summary>
        [JsonProperty(PropertyName = "c_PORT_CODE")]
        public string C_PORT_CODE
        {
            set { c_PORT_CODE = value; }

            get { return c_PORT_CODE; }
        }

        /// <summary>
        /// 属性：业务类型
        /// </summary>
        [JsonProperty(PropertyName = "c_BUSINESS_CODE")]
        public string C_BUSINESS_CODE
        {
            set { c_BUSINESS_CODE = value; }

            get { return c_BUSINESS_CODE; }
        }
    }
}

