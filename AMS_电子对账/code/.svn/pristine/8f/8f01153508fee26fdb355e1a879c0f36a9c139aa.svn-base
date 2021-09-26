using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;
using FAST.Common.Service.Pojo.Base;

namespace YssElecReco.Pojo.Er.Reverse
{
    /// <summary>
    /// 组合映射
    /// </summary>
    public class AssMap : AuditableParamPojo  
    {
        ///<summary>
        ///投资组合代码
        ///</summary>
        private string c_PORT_CODE = "";

        ///<summary>
        ///对账类型
        ///</summary>
        private string c_FILE_TYPE = "";

        ///<summary>
        ///托管银行
        ///</summary>
        private string c_TGH_CODE = "";

        ///<summary>
        ///托管行资产代码
        ///</summary>
        private string c_PORT_CODE_OUT = "";

        ///<summary>
        ///对账模式（DZMODE_SZT：深圳通模式；DZMODE_QT：其他模式）
        ///</summary>
        private string c_DV_DZ_MODE = "";

        ///<summary>
        ///投资组合代码
        ///</summary>
        [JsonProperty(PropertyName = "c_PORT_CODE")]
        public string C_PORT_CODE
        {
            set { c_PORT_CODE = value; }
            get { return c_PORT_CODE; }
        }

        ///<summary>
        ///对账类型
        ///</summary>
        [JsonProperty(PropertyName = "c_FILE_TYPE")]
        public string C_FILE_TYPE
        {
            set { c_FILE_TYPE = value; }
            get { return c_FILE_TYPE; }
        }

        ///<summary>
        ///托管银行
        ///</summary>
        [JsonProperty(PropertyName = "c_TGH_CODE")]
        public string C_TGH_CODE
        {
            set { c_TGH_CODE = value; }
            get { return c_TGH_CODE; }
        }

        ///<summary>
        ///对方组合代码
        ///</summary>
        [JsonProperty(PropertyName = "c_PORT_CODE_OUT")]
        public string C_PORT_CODE_OUT
        {
            set { c_PORT_CODE_OUT = value; }
            get { return c_PORT_CODE_OUT; }
        }

        ///<summary>
        ///对账模式
        ///</summary>
        [JsonProperty(PropertyName = "c_DV_DZ_MODE")]
        public string C_DV_DZ_MODE
        {
            set { c_DV_DZ_MODE = value; }
            get { return c_DV_DZ_MODE; }
        }
    }
}