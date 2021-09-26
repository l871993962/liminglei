using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;
using FAST.Common.Service.Pojo.Base;

namespace YssElecReco.Pojo.Er.Reverse
{
    /// <summary>
    /// 映射关系
    /// </summary>
    public class ResRela : BasePojo  
    {
        ///<summary>
        ///结果关联
        ///</summary>
        private string c_RESULT_RELA = "";

        ///<summary>
        ///科目代码
        ///</summary>
        private string c_KM_CODE = "";

        ///<summary>
        ///科目名称
        ///</summary>
        private string c_KM_NAME = "";

        ///<summary>
        ///托管行代码
        ///</summary>
        private string c_TGH_CODE = "";

        ///<summary>
        ///科目范围
        ///</summary>
        private string c_DV_KM_SCOPE = "";

        ///<summary>
        ///n_JE14
        ///</summary>
        private string n_JE14 = "";

        ///<summary>
        ///n_JE13
        ///</summary>
        private string n_JE13 = "";

        ///<summary>
        ///n_JE12
        ///</summary>
        private string n_JE12 = "";

        ///<summary>
        ///n_JE11
        ///</summary>
        private string n_JE11 = "";

        ///<summary>
        ///n_JE10
        ///</summary>
        private string n_JE10 = "";

        ///<summary>
        ///n_JE9
        ///</summary>
        private string n_JE9 = "";


        ///<summary>
        ///n_JE8
        ///</summary>
        private string n_JE8 = "";


        ///<summary>
        ///n_JE7
        ///</summary>
        private string n_JE7 = "";


        ///<summary>
        ///n_JE6
        ///</summary>
        private string n_JE6 = "";

        ///<summary>
        ///n_SL1
        ///</summary>
        private string n_SL1 = "";

        ///<summary>
        ///n_JE3
        ///</summary>
        private string n_JE3 = "";

        ///<summary>
        ///n_JE4
        ///</summary>
        private string n_JE4 = "";

        ///<summary>
        ///n_JE5
        ///</summary>
        private string n_JE5 = "";

        ///<summary>
        ///c_BY1
        ///</summary>
        private string c_BY1 = "";

        ///<summary>
        ///n_JE2
        ///</summary>
        private string n_JE2 = "";

        ///<summary>
        ///n_JE1
        ///</summary>
        private string n_JE1 = "";

        ///<summary>
        ///忽略标志
        ///</summary>
        private string c_IGNORE_FLAG = "";


        ///<summary>
        ///C_IGNORE_FLAG
        ///</summary>
        [JsonProperty(PropertyName = "c_IGNORE_FLAG")]
        public string C_IGNORE_FLAG
        {
            set { c_IGNORE_FLAG = value; }
            get { return c_IGNORE_FLAG; }
        }

        ///<summary>
        ///C_RESULT_RELA
        ///</summary>
        [JsonProperty(PropertyName = "c_RESULT_RELA")]
        public string C_RESULT_RELA
        {
            set { c_RESULT_RELA = value; }
            get { return c_RESULT_RELA; }
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
        ///C_DV_KM_SCOPE
        ///</summary>
        [JsonProperty(PropertyName = "c_DV_KM_SCOPE")]
        public string C_DV_KM_SCOPE
        {
            set { c_DV_KM_SCOPE = value; }
            get { return c_DV_KM_SCOPE; }
        }

        ///<summary>
        ///N_JE14
        ///</summary>
        [JsonProperty(PropertyName = "n_JE14")]
        public string N_JE14
        {
            set { n_JE14 = value; }
            get { return n_JE14; }
        }

        ///<summary>
        ///N_JE13
        ///</summary>
        [JsonProperty(PropertyName = "n_JE13")]
        public string N_JE13
        {
            set { n_JE13 = value; }
            get { return n_JE13; }
        }

        ///<summary>
        ///N_JE12
        ///</summary>
        [JsonProperty(PropertyName = "n_JE12")]
        public string N_JE12
        {
            set { n_JE12 = value; }
            get { return n_JE12; }
        }

        ///<summary>
        ///N_JE11
        ///</summary>
        [JsonProperty(PropertyName = "n_JE11")]
        public string N_JE11
        {
            set { n_JE11 = value; }
            get { return n_JE11; }
        }

        ///<summary>
        ///N_JE10
        ///</summary>
        [JsonProperty(PropertyName = "n_JE10")]
        public string N_JE10
        {
            set { n_JE10 = value; }
            get { return n_JE10; }
        }

        ///<summary>
        ///N_JE9
        ///</summary>
        [JsonProperty(PropertyName = "n_JE9")]
        public string N_JE9
        {
            set { n_JE9 = value; }
            get { return n_JE9; }
        }


        ///<summary>
        ///N_JE8
        ///</summary>
        [JsonProperty(PropertyName = "n_JE8")]
        public string N_JE8
        {
            set { n_JE8 = value; }
            get { return n_JE8; }
        }


        ///<summary>
        ///N_JE7
        ///</summary>
        [JsonProperty(PropertyName = "n_JE7")]
        public string N_JE7
        {
            set { n_JE7 = value; }
            get { return n_JE7; }
        }


        ///<summary>
        ///N_JE6
        ///</summary>
        [JsonProperty(PropertyName = "n_JE6")]
        public string N_JE6
        {
            set { n_JE6 = value; }
            get { return n_JE6; }
        }

        ///<summary>
        ///N_SL1
        ///</summary>
        [JsonProperty(PropertyName = "n_SL1")]
        public string N_SL1
        {
            set { n_SL1 = value; }
            get { return n_SL1; }
        }

        ///<summary>
        ///N_JE3
        ///</summary>
        [JsonProperty(PropertyName = "n_JE3")]
        public string N_JE3
        {
            set { n_JE3 = value; }
            get { return n_JE3; }
        }

        ///<summary>
        ///N_JE4
        ///</summary>
        [JsonProperty(PropertyName = "n_JE4")]
        public string N_JE4
        {
            set { n_JE4 = value; }
            get { return n_JE4; }
        }

        ///<summary>
        ///N_JE5
        ///</summary>
        [JsonProperty(PropertyName = "n_JE5")]
        public string N_JE5
        {
            set { n_JE5 = value; }
            get { return n_JE5; }
        }

        ///<summary>
        ///C_BY1
        ///</summary>
        [JsonProperty(PropertyName = "c_BY1")]
        public string C_BY1
        {
            set { c_BY1 = value; }
            get { return c_BY1; }
        }

        ///<summary>
        ///N_JE2
        ///</summary>
        [JsonProperty(PropertyName = "n_JE2")]
        public string N_JE2
        {
            set { n_JE2 = value; }
            get { return n_JE2; }
        }

        ///<summary>
        ///N_JE1
        ///</summary>
        [JsonProperty(PropertyName = "n_JE1")]
        public string N_JE1
        {
            set { n_JE1 = value; }
            get { return n_JE1; }
        }
    }
}