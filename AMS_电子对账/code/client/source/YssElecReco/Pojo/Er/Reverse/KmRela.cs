using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;
using FAST.Common.Service.Pojo.Base;

namespace YssElecReco.Pojo.Er.Reverse
{
    /// <summary>
    /// 科目关联
    /// </summary>
    public class KmRela : AuditableParamPojo  
    {
        ///<summary>
        ///产品组合
        ///</summary>
        private string c_PORT_CODE = "";

        ///<summary>
        ///托管机构
        ///</summary>
        private string c_TGH_CODE = "";

        ///<summary>
        ///映射类型（REVE_YSLX_OTO:一对一，REVE_YSLX_OTM:一对多，REVE_YSLX_MTM:多对多）
        ///</summary>
        private string c_DV_MAP_TYPE = "";

        ///<summary>
        ///映射范围（REVE_YSFW_GGYS:公共映射，REVE_YSFW_TGFYS:托管方映射,REVE_YSFW_CPYS:产品映射）
        ///</summary>
        private string c_DV_MAP_SCOPE = "";

        ///<summary>
        ///科目类别
        ///</summary>
        private string c_DV_KM_CLS = "";

        ///<summary>
        ///C_PORT_CODE
        ///</summary>
        [JsonProperty(PropertyName = "c_PORT_CODE")]
        public string C_PORT_CODE
        {
            set { c_PORT_CODE = value; }
            get { return c_PORT_CODE; }
        }

        ///<summary>
        ///C_TGH_CODE
        ///</summary>
        [JsonProperty(PropertyName = "c_TGH_CODE")]
        public string C_TGH_CODE
        {
            set { c_TGH_CODE = value; }
            get { return c_TGH_CODE; }
        }

        ///<summary>
        ///C_DV_MAP_TYPE
        ///</summary>
        [JsonProperty(PropertyName = "c_DV_MAP_TYPE")]
        public string C_DV_MAP_TYPE
        {
            set { c_DV_MAP_TYPE = value; }
            get { return c_DV_MAP_TYPE; }
        }

        ///<summary>
        ///C_DV_MAP_SCOPE
        ///</summary>
        [JsonProperty(PropertyName = "c_DV_MAP_SCOPE")]
        public string C_DV_MAP_SCOPE
        {
            set { c_DV_MAP_SCOPE = value; }
            get { return c_DV_MAP_SCOPE; }
        }

        ///<summary>
        ///C_DV_KM_CLS
        ///</summary>
        [JsonProperty(PropertyName = "c_DV_KM_CLS")]
        public string C_DV_KM_CLS
        {
            set { c_DV_KM_CLS = value; }
            get { return c_DV_KM_CLS; }
        }
    }
}