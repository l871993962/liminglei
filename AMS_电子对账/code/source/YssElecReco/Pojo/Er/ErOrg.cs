using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;
using FAST.Common.Service.Pojo.Base;

namespace YssElecReco.Pojo.Er
{
    /// <summary>
    /// 电子对账机构
    /// </summary>
    public class ErOrg : AuditableParamPojo
    {   
        /// <summary>
        /// 机构代码
        /// </summary>
        private string c_ORG_CODE = "";

        /// <summary>
        /// 机构名称
        /// </summary>
        private string c_ORG_NAME = "";

        /// <summary>
        /// 中文名称
        /// </summary>
        private string c_ORG_NAME_CN = "";

        /// <summary>
        ///  机构简称
        /// </summary>
        private string c_ORG_NAME_ST = "";

        /// <summary>
        /// 母公司
        /// </summary>
        private string c_ORG_CODE_P = "";

        /// <summary>
        /// 机构类型
        /// </summary>
        private string c_DV_ORG_TYPE = "";

        /// <summary>
        /// 公司代码
        /// </summary>
        private string c_CORP_CODE = "";

        /// <summary>
        /// 管理人
        /// </summary>
        private string c_DV_MANAGER = "";

        /// <summary>
        /// 托管人
        /// </summary>
        private string c_DV_TRUSTEE = "";

        /// <summary>
        ///  次托管人
        /// </summary>
        private string c_DV_TRUSTEE_SEC = "";

        /// <summary>
        /// 主托管人
        /// </summary>
        private string c_DV_TRUSTEE_MA = "";

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
        /// 中文名称
        /// </summary>
        [JsonProperty(PropertyName = "c_ORG_NAME_CN")]
        public string C_ORG_NAME_CN
        {
            get { return c_ORG_NAME_CN; }
            set { c_ORG_NAME_CN = value; }
        }

        
        /// <summary>
        ///  机构简称
        /// </summary>
        [JsonProperty(PropertyName = "c_ORG_NAME_ST")]
        public string C_ORG_NAME_ST
        {
            get { return c_ORG_NAME_ST; }
            set { c_ORG_NAME_ST = value; }
        }
        
        /// <summary>
        ///  母公司
        /// </summary>
        [JsonProperty(PropertyName = "c_ORG_CODE_P")]
        public string C_ORG_CODE_P
        {
            get { return c_ORG_CODE_P; }
            set { c_ORG_CODE_P = value; }
        }
        
        /// <summary>
        ///  机构类型
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_ORG_TYPE")]
        public string C_DV_ORG_TYPE
        {
            get { return c_DV_ORG_TYPE; }
            set { c_DV_ORG_TYPE = value; }
        }

        
        /// <summary>
        ///  公司代码
        /// </summary>
        [JsonProperty(PropertyName = "c_CORP_CODE")]
        public string C_CORP_CODE
        {
            get { return c_CORP_CODE; }
            set { c_CORP_CODE = value; }
        }
        
        /// <summary>
        ///  管理人
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_MANAGER")]
        public string C_DV_MANAGER
        {
            get { return c_DV_MANAGER; }
            set { c_DV_MANAGER = value; }
        }

        
        /// <summary>
        ///  托管人
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_TRUSTEE")]
        public string C_DV_TRUSTEE
        {
            get { return c_DV_TRUSTEE; }
            set { c_DV_TRUSTEE = value; }
        }

        
        /// <summary>
        ///   次托管人
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_TRUSTEE_SEC")]
        public string C_DV_TRUSTEE_SEC
        {
            get { return c_DV_TRUSTEE_SEC; }
            set { c_DV_TRUSTEE_SEC = value; }
        }

        
        /// <summary>
        ///  主托管人
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_TRUSTEE_MA")]
        public string C_DV_TRUSTEE_MA
        {
            get { return c_DV_TRUSTEE_MA; }
            set { c_DV_TRUSTEE_MA = value; }
        }

    }
}
