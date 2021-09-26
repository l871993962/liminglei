using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;
using FAST.Common.Service.Pojo.Base;

namespace YssElecReco.Pojo.Er.Reverse
{
    /// <summary>
    /// 对账结果
    /// </summary>
    public class ReveResult : BasePojo  
    {
        /// <summary>
        /// 对方数据
        /// </summary>
        private List<ResRela> list_RESRELA_OUT = null;
       
        /// <summary>
        /// 本方数据
        /// </summary>
        private List<ResRela> list_RESRELA_INNER = null;

        ///<summary>
        ///对账类型
        ///</summary>
        private string c_FILE_TYPE = "";

        ///<summary>
        ///组合代码
        ///</summary>
        private string c_PORT_CODE = "";

        ///<summary>
        ///报表类型
        ///</summary>
        private string c_RPT_TYPE = "";

        ///<summary>
        ///开始日期
        ///</summary>
        private string d_START_DATE = "";

        ///<summary>
        ///结束日期
        ///</summary>
        private string d_END_DATE = "";

        ///<summary>
        ///对账结果
        ///</summary>
        private string c_DV_DZ_RESULT = "";

        ///<summary>
        ///结果详情
        ///</summary>
        private string c_RESULT_INFO = "";

        ///<summary>
        ///备注
        ///</summary>
        private string c_NOTE = "";

        ///<summary>
        ///处理人
        ///</summary>
        private string c_DEALER = "";

        ///<summary>
        ///时间
        ///</summary>
        private string d_TIME = "";

        ///<summary>
        ///序号
        ///</summary>
        private string c_SN = "";

        ///<summary>
        ///n_CE_JE14
        ///</summary>
        private string n_CE_JE14 = "";

        ///<summary>
        ///n_CE_JE13
        ///</summary>
        private string n_CE_JE13 = "";

        ///<summary>
        ///n_CE_JE12
        ///</summary>
        private string n_CE_JE12 = "";

        ///<summary>
        ///n_CE_JE11
        ///</summary>
        private string n_CE_JE11 = "";

        ///<summary>
        ///n_CE_JE10
        ///</summary>
        private string n_CE_JE10 = "";

        ///<summary>
        ///n_CE_JE9
        ///</summary>
        private string n_CE_JE9 = "";


        ///<summary>
        ///n_CE_JE8
        ///</summary>
        private string n_CE_JE8 = "";


        ///<summary>
        ///n_CE_JE7
        ///</summary>
        private string n_CE_JE7 = "";


        ///<summary>
        ///n_CE_JE7
        ///</summary>
        private string n_CE_JE6 = "";

        ///<summary>
        ///n_CE_SL1
        ///</summary>
        private string n_CE_SL1 = "";

        ///<summary>
        ///n_CE_JE3
        ///</summary>
        private string n_CE_JE3 = "";

        ///<summary>
        ///n_CE_JE4
        ///</summary>
        private string n_CE_JE4 = "";

        ///<summary>
        ///n_CE_JE5
        ///</summary>
        private string n_CE_JE5 = "";

        ///<summary>
        ///c_CE_BY1
        ///</summary>
        private string c_CE_BY1 = "";

        ///<summary>
        ///n_CE_JE2
        ///</summary>
        private string n_CE_JE2 = "";

        ///<summary>
        ///n_CE_JE1
        ///</summary>
        private string n_CE_JE1 = "";


        ///<summary>
        ///LIST_RESRELA_OUT
        ///</summary>
        [JsonProperty(PropertyName = "list_RESRELA_OUT")]
        public List<ResRela> LIST_RESRELA_OUT
        {
            set { list_RESRELA_OUT = value; }
            get { return list_RESRELA_OUT; }
        }

        ///<summary>
        ///LIST_RESRELA_INNER
        ///</summary>
        [JsonProperty(PropertyName = "list_RESRELA_INNER")]
        public List<ResRela> LIST_RESRELA_INNER
        {
            set { list_RESRELA_INNER = value; }
            get { return list_RESRELA_INNER; }
        }



        ///<summary>
        ///C_FILE_TYPE
        ///</summary>
        [JsonProperty(PropertyName = "c_FILE_TYPE")]
        public string C_FILE_TYPE
        {
            set { c_FILE_TYPE = value; }
            get { return c_FILE_TYPE; }
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
        ///C_RPT_TYPE
        ///</summary>
        [JsonProperty(PropertyName = "c_RPT_TYPE")]
        public string C_RPT_TYPE
        {
            set { c_RPT_TYPE = value; }
            get { return c_RPT_TYPE; }
        }

        ///<summary>
        ///D_START_DATE
        ///</summary>
        [JsonProperty(PropertyName = "d_START_DATE")]
        public string D_START_DATE
        {
            set { d_START_DATE = value; }
            get { return d_START_DATE; }
        }

        ///<summary>
        ///D_END_DATE
        ///</summary>
        [JsonProperty(PropertyName = "d_END_DATE")]
        public string D_END_DATE
        {
            set { d_END_DATE = value; }
            get { return d_END_DATE; }
        }

        ///<summary>
        ///C_DV_DZ_RESULT
        ///</summary>
        [JsonProperty(PropertyName = "c_DV_DZ_RESULT")]
        public string C_DV_DZ_RESULT
        {
            set { c_DV_DZ_RESULT = value; }
            get { return c_DV_DZ_RESULT; }
        }

        ///<summary>
        ///C_RESULT_INFO
        ///</summary>
        [JsonProperty(PropertyName = "c_RESULT_INFO")]
        public string C_RESULT_INFO
        {
            set { c_RESULT_INFO = value; }
            get { return c_RESULT_INFO; }
        }

        ///<summary>
        ///C_NOTE
        ///</summary>
        [JsonProperty(PropertyName = "c_NOTE")]
        public string C_NOTE
        {
            set { c_NOTE = value; }
            get { return c_NOTE; }
        }

        ///<summary>
        ///C_DEALER
        ///</summary>
        [JsonProperty(PropertyName = "c_DEALER")]
        public string C_DEALER
        {
            set { c_DEALER = value; }
            get { return c_DEALER; }
        }

        ///<summary>
        ///D_TIME
        ///</summary>
        [JsonProperty(PropertyName = "d_TIME")]
        public string D_TIME
        {
            set { d_TIME = value; }
            get { return d_TIME; }
        }

        ///<summary>
        ///C_SN
        ///</summary>
        [JsonProperty(PropertyName = "c_SN")]
        public string C_SN
        {
            set { c_SN = value; }
            get { return c_SN; }
        }

        ///<summary>
        ///N_CE_JE14
        ///</summary>
        [JsonProperty(PropertyName = "n_CE_JE14")]
        public string N_CE_JE14
        {
            set { n_CE_JE14 = value; }
            get { return n_CE_JE14; }
        }

        ///<summary>
        ///N_CE_JE13
        ///</summary>
        [JsonProperty(PropertyName = "n_CE_JE13")]
        public string N_CE_JE13
        {
            set { n_CE_JE13 = value; }
            get { return n_CE_JE13; }
        }

        ///<summary>
        ///N_CE_JE12
        ///</summary>
        [JsonProperty(PropertyName = "n_CE_JE12")]
        public string N_CE_JE12
        {
            set { n_CE_JE12 = value; }
            get { return n_CE_JE12; }
        }

        ///<summary>
        ///N_CE_JE11
        ///</summary>
        [JsonProperty(PropertyName = "n_CE_JE11")]
        public string N_CE_JE11
        {
            set { n_CE_JE11 = value; }
            get { return n_CE_JE11; }
        }

        ///<summary>
        ///N_CE_JE10
        ///</summary>
        [JsonProperty(PropertyName = "n_CE_JE10")]
        public string N_CE_JE10
        {
            set { n_CE_JE10 = value; }
            get { return n_CE_JE10; }
        }

        ///<summary>
        ///N_CE_JE9
        ///</summary>
        [JsonProperty(PropertyName = "n_CE_JE9")]
        public string N_CE_JE9
        {
            set { n_CE_JE9 = value; }
            get { return n_CE_JE9; }
        }


        ///<summary>
        ///N_CE_JE8
        ///</summary>
        [JsonProperty(PropertyName = "n_CE_JE8")]
        public string N_CE_JE8
        {
            set { n_CE_JE8 = value; }
            get { return n_CE_JE8; }
        }


        ///<summary>
        ///N_CE_JE7
        ///</summary>
        [JsonProperty(PropertyName = "n_CE_JE7")]
        public string N_CE_JE7
        {
            set { n_CE_JE7 = value; }
            get { return n_CE_JE7; }
        }


        ///<summary>
        ///N_CE_JE6
        ///</summary>
        [JsonProperty(PropertyName = "n_CE_JE6")]
        public string N_CE_JE6
        {
            set { n_CE_JE6 = value; }
            get { return n_CE_JE6; }
        }

        ///<summary>
        ///N_CE_SL1
        ///</summary>
        [JsonProperty(PropertyName = "n_CE_SL1")]
        public string N_CE_SL1
        {
            set { n_CE_SL1 = value; }
            get { return n_CE_SL1; }
        }

        ///<summary>
        ///N_CE_JE3
        ///</summary>
        [JsonProperty(PropertyName = "n_CE_JE3")]
        public string N_CE_JE3
        {
            set { n_CE_JE3 = value; }
            get { return n_CE_JE3; }
        }

        ///<summary>
        ///N_CE_JE4
        ///</summary>
        [JsonProperty(PropertyName = "n_CE_JE4")]
        public string N_CE_JE4
        {
            set { n_CE_JE4 = value; }
            get { return n_CE_JE4; }
        }

        ///<summary>
        ///N_CE_JE5
        ///</summary>
        [JsonProperty(PropertyName = "n_CE_JE5")]
        public string N_CE_JE5
        {
            set { n_CE_JE5 = value; }
            get { return n_CE_JE5; }
        }

        ///<summary>
        ///C_CE_BY1
        ///</summary>
        [JsonProperty(PropertyName = "c_CE_BY1")]
        public string C_CE_BY1
        {
            set { c_CE_BY1 = value; }
            get { return c_CE_BY1; }
        }

        ///<summary>
        ///N_CE_JE2
        ///</summary>
        [JsonProperty(PropertyName = "n_CE_JE2")]
        public string N_CE_JE2
        {
            set { n_CE_JE2 = value; }
            get { return n_CE_JE2; }
        }

        ///<summary>
        ///N_CE_JE1
        ///</summary>
        [JsonProperty(PropertyName = "n_CE_JE1")]
        public string N_CE_JE1
        {
            set { n_CE_JE1 = value; }
            get { return n_CE_JE1; }
        }
    }
}