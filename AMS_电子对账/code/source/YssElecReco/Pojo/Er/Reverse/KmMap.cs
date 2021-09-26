using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;
using FAST.Common.Service.Pojo.Base;

namespace YssElecReco.Pojo.Er.Reverse
{
    /// <summary>
    /// 科目映射
    /// </summary>
    public class KmMap : BasePojo  
    {
        ///<summary>
        ///映射关系ID
        ///</summary>
        private string c_IDEN_RELA = "";

        ///<summary>
        ///产品组合
        ///</summary>
        private string c_PORT_CODE = "";

        ///<summary>
        ///科目代码
        ///</summary>
        private string c_KM_CODE = "";

        ///<summary>
        ///科目名称
        ///</summary>
        private string c_KM_NAME = "";

        ///<summary>
        ///托管机构
        ///</summary>
        private string c_TGH_CODE = "";

        ///<summary>
        ///科目类型(KC_ZC:资产类;KC_FZ:负债类;KC_GT:共同类;KC_QY:权益类;KC_SY:损益类)
        ///</summary>
        private string c_DV_KM_CLS = "";

        ///<summary>
        ///科目范围（REVE_KMFW_INNER:内部科目；REVE_KMFW_OUT：外部科目）
        ///</summary>
        private string c_DV_KM_SCOPE = "";

        ///<summary>
        ///C_IDEN_RELA
        ///</summary>
        [JsonProperty(PropertyName = "c_IDEN_RELA")]
        public string C_IDEN_RELA
        {
            set { c_IDEN_RELA = value; }
            get { return c_IDEN_RELA; }
        }

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
        ///C_KM_CODE
        ///</summary>
        [JsonProperty(PropertyName = "c_KM_CODE")]
        public string C_KM_CODE 
        {
            set { c_KM_CODE = value; }
            get { return c_KM_CODE; }
        }

        ///<summary>
        ///C_KM_NAME
        ///</summary>
        [JsonProperty(PropertyName = "c_KM_NAME")]
        public string C_KM_NAME 
        {
            set { c_KM_NAME = value; }
            get { return c_KM_NAME; }
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
        ///C_DV_KM_CLS
        ///</summary>
        [JsonProperty(PropertyName = "c_DV_KM_CLS")]
        public string C_DV_KM_CLS
        {
            set { c_DV_KM_CLS = value; }
            get { return c_DV_KM_CLS; }
        }

        ///<summary>
        ///C_DV_KM_SCOPE
        ///</summary>
        [JsonProperty(PropertyName = "c_DV_KM_SCOPE")]
        public string C_DV_KM_SCOPE
        {
            set { c_DV_KM_SCOPE = value; }
            get { return c_DV_KM_SCOPE; }
        }
    }
}