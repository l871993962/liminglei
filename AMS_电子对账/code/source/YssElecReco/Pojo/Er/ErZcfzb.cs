using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Linq;
using System.Reflection;
using System.Windows.Forms;

using Newtonsoft.Json;
using FAST.Common.Service.Pojo.Base;

namespace YssElecReco.pojo.Er
{
    /// <summary>
    /// 资产负债表
    /// </summary>
    public class ErZcfzb : BasePojo
    {
        /// <summary>
        /// 报文序号
        /// </summary>
        private string c_SN = "";

        /// <summary>
        /// 资产代码
        /// </summary>
        private string c_ASS_CODE = "";

        /// <summary>
        /// 文件类型
        /// </summary>
        private string c_FILE_TYPE = "";

        /// <summary>
        /// 报表类型
        /// </summary>
        private string c_RPT_TYPE = "";

        /// <summary>
        /// 开始日期
        /// </summary>
        private string d_START_DATE = "";

        /// <summary>
        /// 结束日期
        /// </summary>
        private string d_END_DATE = "";

        /// <summary>
        /// 公司代码
        /// </summary>
        private string c_DEPT_CODE = "";

        /// <summary>
        /// 证书代码
        /// </summary>
        private string c_CERT_ID = "";

        /// <summary>
        /// 指标代码
        /// </summary>
        private string c_INDEX_CODE = "";

        /// <summary>
        /// 指标名称
        /// </summary>
        private string c_INDEX_NAME = "";

        /// <summary>
        /// 期初值
        /// </summary>
        private decimal n_BEGIN_VALUE = 0;

        /// <summary>
        /// 期末值
        /// </summary>
        private decimal n_END_VALUE = 0;

        /// <summary>
        /// 报文序号
        /// </summary>
        [JsonProperty(PropertyName = "c_SN")]
        public string C_SN
        {
            get { return c_SN; }
            set { c_SN = value; }
        }

        /// <summary>
        /// 属性: 资产代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_ASS_CODE")]
        public string C_ASS_CODE
        {
            set { c_ASS_CODE = value; }
            get { return c_ASS_CODE; }
        }

        /// <summary>
        /// 文件类型
        /// </summary>
        [JsonProperty(PropertyName = "c_FILE_TYPE")]
        public string C_FILE_TYPE
        {
            set { c_FILE_TYPE = value; }
            get { return c_FILE_TYPE; }
        }

        /// <summary>
        /// 报表类型
        /// </summary>
        [JsonProperty(PropertyName = "c_RPT_TYPE")]
        public string C_RPT_TYPE
        {
            set { c_RPT_TYPE = value; }
            get { return c_RPT_TYPE; }
        }

        /// <summary>
        /// 开始日期
        /// </summary>
        [JsonProperty(PropertyName = "d_START_DATE")]
        public string D_START_DATE
        {
            get { return d_START_DATE; }
            set { d_START_DATE = value; }
        }

        /// <summary>
        /// 结束日期
        /// </summary>
        [JsonProperty(PropertyName = "d_END_DATE")]
        public string D_END_DATE
        {
            get { return d_END_DATE; }
            set { d_END_DATE = value; }
        }

        /// <summary>
        ///  公司代码
        /// </summary>
        [JsonProperty(PropertyName = "c_DEPT_CODE")]
        public string C_DEPT_CODE
        {
            get { return c_DEPT_CODE; }
            set { c_DEPT_CODE = value; }
        }

        /// <summary>
        /// 证书代码
        /// </summary>
        [JsonProperty(PropertyName = "c_CERT_ID")]
        public string C_CERT_ID
        {
            get { return c_CERT_ID; }
            set { c_CERT_ID = value; }
        }

        /// <summary>
        /// 指标代码
        /// </summary>
        [JsonProperty(PropertyName = "c_INDEX_CODE")]
        public string C_INDEX_CODE
        {
            get { return c_INDEX_CODE; }
            set { c_INDEX_CODE = value; }
        }

        /// <summary>
        /// 指标名称
        /// </summary>
        [JsonProperty(PropertyName = "c_INDEX_NAME")]
        public string C_INDEX_NAME
        {
            get { return c_INDEX_NAME; }
            set { c_INDEX_NAME = value; }
        }

        /// <summary>
        /// 期初值
        /// </summary>
        [JsonProperty(PropertyName = "n_BEGIN_VALUE")]
        public decimal N_BEGIN_VALUE
        {
            get { return n_BEGIN_VALUE; }
            set { n_BEGIN_VALUE = value; }
        }

        /// <summary>
        /// 期末值
        /// </summary>
        [JsonProperty(PropertyName = "n_END_VALUE")]
        public decimal N_END_VALUE
        {
            get { return n_END_VALUE; }
            set { n_END_VALUE = value; }
        }
    }
}
