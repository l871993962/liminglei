using Newtonsoft.Json;
using FAST.Common.Service.Pojo.Base;

namespace YssElecReco.pojo.Er
{
    /// <summary>
    /// STORY #88316 华夏基金-MOM产品个性化需求——场外和银行间交易数据发送托管行 
    /// </summary>
    public class DzTransRepCfg : AuditableParamPojo
    {
        /// <summary>
        /// 机构代码
        /// </summary>
        private string c_ORG_CODE = " ";

        /// <summary>
        /// 机构名称
        /// </summary>
        private string c_ORG_NAME = "";

        /// <summary>
        /// 交易数据接口
        /// </summary>
        private string c_TRANS_CODE = "";

        /// <summary>
        /// 交易数据接口名称
        /// </summary>
        private string c_TRANS_NAME = "";

        /// <summary>
        /// 接口配置模板
        /// </summary>
        private string c_TPL_CODE = "";

        /// <summary>
        /// 接口配置模板名称
        /// </summary>
        private string c_TPL_NAME = "";

        /// <summary>
        /// 组合代码
        /// </summary>
        private string c_PORT_CODE = "";

        /// <summary>
        /// 投资组合
        /// </summary>
        private string c_PORT_NAME = "";

        /// <summary>
        /// STORY60117【鹏华基金】电子对账报表配置增加资产类型控制
        /// 资产类型
        /// </summary>
        private string c_DAT_CODE = "";

        /// <summary>
        /// 机构代码
        /// </summary>
        [JsonProperty(PropertyName = "c_ORG_CODE")]
        public string C_ORG_CODE
        {
            get { return c_ORG_CODE; }
            set { c_ORG_CODE = value; }
        }

        /// <summary>
        /// 机构名称
        /// </summary>
        [JsonProperty(PropertyName = "c_ORG_NAME")]
        public string C_ORG_NAME
        {
            get { return c_ORG_NAME; }
            set { c_ORG_NAME = value; }
        }

        /// <summary>
        ///交易数据接口
        /// </summary>
        [JsonProperty(PropertyName = "c_TRANS_CODE")]
        public string C_TRANS_CODE
        {
            get { return c_TRANS_CODE; }
            set { c_TRANS_CODE = value; }
        }

        /// <summary>
        /// 交易数据接口名称
        /// </summary>
        [JsonProperty(PropertyName = "c_TRANS_NAME")]
        public string C_TRANS_NAME
        {
            get { return c_TRANS_NAME; }
            set { c_TRANS_NAME = value; }
        }

        /// <summary>
        /// 接口配置模板
        /// </summary>
        [JsonProperty(PropertyName = "c_TPL_CODE")]
        public string C_TPL_CODE
        {
            get { return c_TPL_CODE; }
            set { c_TPL_CODE = value; }
        }

        /// <summary>
        /// 接口配置模板名称
        /// </summary>
        [JsonProperty(PropertyName = "c_TPL_NAME")]
        public string C_TPL_NAME
        {
            get { return c_TPL_NAME; }
            set { c_TPL_NAME = value; }
        }

        /// <summary>
        /// 组合代码
        /// </summary>
        [JsonProperty(PropertyName = "c_PORT_CODE")]
        public string C_PORT_CODE
        {
            get { return c_PORT_CODE; }
            set { c_PORT_CODE = value; }
        }

        /// <summary>
        /// 组合名称
        /// </summary>
        [JsonProperty(PropertyName = "c_PORT_NAME")]
        public string C_PORT_NAME
        {
            get { return c_PORT_NAME; }
            set { c_PORT_NAME = value; }
        }

        /// <summary>
        /// 资产类型
        /// </summary>
        [JsonProperty(PropertyName = "c_DAT_CODE")]
        public string C_DAT_CODE
        {
            get { return c_DAT_CODE; }
            set { c_DAT_CODE = value; }
        }
    }
}
