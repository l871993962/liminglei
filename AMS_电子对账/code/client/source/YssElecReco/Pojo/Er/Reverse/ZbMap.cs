using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;
using FAST.Common.Service.Pojo.Base;
namespace YssElecReco.Pojo.Er.Reverse
{
    public class ZbMap : AuditableParamPojo  
    {


        ///<summary>
        ///对账类型
        ///</summary>
        private string c_FILE_TYPE = "";

        ///<summary>
        ///内部指标代码
        ///</summary>
        private string c_ZB_CODE = "";

        ///<summary>
        ///内部指标名称
        ///</summary>
        private string c_ZB_NAME = "";

        ///<summary>
        ///外部指标代码
        ///</summary>
        private string c_ZB_CODE_OUT = "";

        ///<summary>
        ///外部指标名称
        ///</summary>
        private string c_ZB_NAME_OUT = "";

        ///<summary>
        ///产品组合
        ///</summary>
        private string c_PORT_CODE = "";

        ///<summary>
        ///托管机构
        ///</summary>
        private string c_TGH_CODE = "";

        ///<summary>
        ///
        ///</summary>
        [JsonProperty(PropertyName = "c_FILE_TYPE")]
        public string C_FILE_TYPE{
            set { c_FILE_TYPE = value; }
            get { return c_FILE_TYPE; }
        }

        ///<summary>
        ///
        ///</summary>
        [JsonProperty(PropertyName = "c_ZB_CODE")]
        public string C_ZB_CODE{
            set { c_ZB_CODE = value; }
            get { return c_ZB_CODE; }
        }

        ///<summary>
        ///
        ///</summary>
        [JsonProperty(PropertyName = "c_ZB_NAME")]
        public string C_ZB_NAME{
            set { c_ZB_NAME = value; }
            get { return c_ZB_NAME; }
        }

        ///<summary>
        ///
        ///</summary>
        [JsonProperty(PropertyName = "c_ZB_CODE_OUT")]
        public string C_ZB_CODE_OUT{
            set { c_ZB_CODE_OUT = value; }
            get { return c_ZB_CODE_OUT; }
        }

        ///<summary>
        ///
        ///</summary>
        [JsonProperty(PropertyName = "c_ZB_NAME_OUT")]
        public string C_ZB_NAME_OUT{
            set { c_ZB_NAME_OUT = value; }
            get { return c_ZB_NAME_OUT; }
        }

        ///<summary>
        ///
        ///</summary>
        [JsonProperty(PropertyName = "c_PORT_CODE")]
        public string C_PORT_CODE{
            set { c_PORT_CODE = value; }
            get { return c_PORT_CODE; }
        }

        ///<summary>
        ///
        ///</summary>
        [JsonProperty(PropertyName = "c_TGH_CODE")]
        public string C_TGH_CODE{
            set { c_TGH_CODE = value; }
            get { return c_TGH_CODE; }
        }
    }}