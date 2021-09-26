using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;
using FAST.Common.Service.Pojo.Base;

namespace YssElecReco.Pojo.Er
{
    /// <summary>
    /// 净资产变动表
    /// </summary>
    public class ErJzcbdb : BasePojo
    {
        ///<summary>
        ///文件类型
        ///</summary>
        private string c_FILE_TYPE = "";

        ///<summary>
        ///资产代码
        ///</summary>
        private string c_ASS_CODE = "";

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
        ///报文序号
        ///</summary>
        private string c_SN = "";

        ///<summary>
        ///指标代码
        ///</summary>
        private string c_INDEX_CODE = "";

        ///<summary>
        ///指标名称
        ///</summary>
        private string c_INDEX_NAME = "";

        ///<summary>
        ///本期数
        ///</summary>
        private decimal n_CUR_VALUE = 0;

        ///<summary>
        ///本年数
        ///</summary>
        private decimal n_TOL_VALUE = 0;

        ///<summary>
        ///文件类型
        ///</summary>
        [JsonProperty(PropertyName = "c_FILE_TYPE")]
        public string C_FILE_TYPE
        {
            set { c_FILE_TYPE = value; }
            get { return c_FILE_TYPE; }
        }

        ///<summary>
        ///资产代码
        ///</summary>
        [JsonProperty(PropertyName = "c_ASS_CODE")]
        public string C_ASS_CODE
        {
            set { c_ASS_CODE = value; }
            get { return c_ASS_CODE; }
        }

        ///<summary>
        ///报表类型
        ///</summary>
        [JsonProperty(PropertyName = "c_RPT_TYPE")]
        public string C_RPT_TYPE
        {
            set { c_RPT_TYPE = value; }
            get { return c_RPT_TYPE; }
        }

        ///<summary>
        ///开始日期
        ///</summary>
        [JsonProperty(PropertyName = "d_START_DATE")]
        public string D_START_DATE
        {
            set { d_START_DATE = value; }
            get { return d_START_DATE; }
        }

        ///<summary>
        ///结束日期
        ///</summary>
        [JsonProperty(PropertyName = "d_END_DATE")]
        public string D_END_DATE
        {
            set { d_END_DATE = value; }
            get { return d_END_DATE; }
        }

        ///<summary>
        ///报文序号
        ///</summary>
        [JsonProperty(PropertyName = "c_SN")]
        public string C_SN
        {
            set { c_SN = value; }
            get { return c_SN; }
        }

        ///<summary>
        ///指标代码
        ///</summary>
        [JsonProperty(PropertyName = "c_INDEX_CODE")]
        public string C_INDEX_CODE
        {
            set { c_INDEX_CODE = value; }
            get { return c_INDEX_CODE; }
        }

        ///<summary>
        ///指标名称
        ///</summary>
        [JsonProperty(PropertyName = "c_INDEX_NAME")]
        public string C_INDEX_NAME
        {
            set { c_INDEX_NAME = value; }
            get { return c_INDEX_NAME; }
        }

        ///<summary>
        ///本期数
        ///</summary>
        [JsonProperty(PropertyName = "n_CUR_VALUE")]
        public decimal N_CUR_VALUE
        {
            set { n_CUR_VALUE = value; }
            get { return n_CUR_VALUE; }
        }

        ///<summary>
        ///本年数
        ///</summary>
        [JsonProperty(PropertyName = "n_TOL_VALUE")]
        public decimal N_TOL_VALUE
        {
            set { n_TOL_VALUE = value; }
            get { return n_TOL_VALUE; }
        }
    }
}